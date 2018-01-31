package net.yosoydev.CinemaUDG;

/**
 *
 * @author jesus
 */
public class Caja {
    private String nombre;
    private double montoRecabado;
    private int clientesAtendidos;
    private long apertura; // tiempo de apertura
    private long cierre; // tiempo de cierre
    
    public Caja(String nombre) {
        this.nombre = nombre;
        this.montoRecabado = 0.0d;
        this.clientesAtendidos = 0;
        this.apertura = 0l;
        this.cierre = 0l;
    }
    
    public String obtenerNombre() {
        return this.nombre;
    }
    
    public double obtenerMontoRecabado() {
        return this.montoRecabado;
    }
    
    public double obtenerClientesAtendidos() {
        return this.clientesAtendidos;
    }
    
    public double obtenerApertura() {
        return this.apertura;
    }
    
    public void establecerApertura(long a) {
        this.apertura = a;
    }
    
    public double obtenerCierre() {
        return this.cierre;
    }
    
    public void establecerCierre(long c) {
        this.cierre = c;
    }
    
    public void iniciarVenta(CinemaUDG cinema) { // Inicia la venta de boletos
        
    }
}
