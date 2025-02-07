package javaapplication22;
/*
import java.util.ArrayList;
import java.util.HashMap;
*/

public class Bodega {
    
    private String nombre;
    private String cantidad;
    private String precio;
    
    private String usuario;
    private String contrasena;

    public Bodega(String nombre, String cantidad, String precio) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public Bodega(String usuario, String contrasena) {
        this.usuario = nombre;
        this.contrasena = contrasena;
    }

    public Bodega() {
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    
    
    public static void main(String[] args) {
        //this.setVisible(false); // Ocultar el frame actual
        new Login().setVisible(true);
        //new MostrarUnidades().setVisible(true);
    }
    
}
