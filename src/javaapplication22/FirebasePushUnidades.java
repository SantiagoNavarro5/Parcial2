package javaapplication22;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

public class FirebasePushUnidades {
    
    public static void PushUnidades(Map<String, Object> x){
        new FirebasePushUnidades().saveUsingPush((Bodega) x);
    }
    
    public static void main(String[] args) {
        Bodega nuevoProducto = new Bodega("Laptop HP", "5", "750.99");
       
        new FirebasePushUnidades().saveUsingPush(nuevoProducto);
    }
       
    private FirebaseDatabase firebaseDatabase;

    /**
     * inicialización de firebase.
     */
    private void initFirebase() {
       
        try {
           
            FirebaseOptions firebaseOptions = new FirebaseOptions.Builder()
                    .setDatabaseUrl("https://ejemplo1-f971f-default-rtdb.firebaseio.com/")
                    .setServiceAccount(new FileInputStream(new File("C:\\Users\\santi\\Downloads\\parcial2poo-firebase-adminsdk-fbsvc-254c018f6f.json")))
            
                    .build();

            FirebaseApp.initializeApp(firebaseOptions);
            firebaseDatabase = FirebaseDatabase.getInstance();
            System.out.println("La conexión se realizo exitosamente...");
           
        }catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        catch (RuntimeException ex) {  
            ex.printStackTrace();
        }
       
       
    }

   
    private void saveUsingPush(Bodega usuario) {
        if (usuario != null) {
            initFirebase();
           
            /* Get database root reference */
            DatabaseReference databaseReference = firebaseDatabase.getReference("/");
           
            /* Get existing child or will be created new child. */
            DatabaseReference childReference = databaseReference.child("CarpetaPrueba");

            /**
             * The Firebase Java client uses daemon threads, meaning it will not prevent a process from exiting.
             * So we'll wait(countDownLatch.await()) until firebase saves record. Then decrement `countDownLatch` value
             * using `countDownLatch.countDown()` and application will continues its execution.
             */
            CountDownLatch countDownLatch = new CountDownLatch(1);
           
            /**
             * push()
             * Add to a list of data in the database. Every time you push a new node onto a list,
             * your database generates a unique key, like items/unique-item-id/data
             */
            childReference.push().setValue(usuario, new DatabaseReference.CompletionListener() {

                @Override
                public void onComplete(DatabaseError de, DatabaseReference dr) {
                    System.out.println("Registro guardado!");
                    // decrement countDownLatch value and application will be continues its execution.
                    countDownLatch.countDown();
                }
            });
            try {
                //wait for firebase to saves record.
                countDownLatch.await();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    void save(ArrayList<Map<String, Object>> x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}