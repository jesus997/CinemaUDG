package net.yosoydev.CinemaUDG;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author jesus
 */
public class Caja {
    private final String nombre;
    private double montoRecabado;
    private int clientesAtendidos;
    private int boletosVendidos;
    private boolean ocupado;
    
    public Caja(String nombre) {
        this.nombre = nombre;
        this.montoRecabado = 0.0d;
        this.clientesAtendidos = 0;
        this.boletosVendidos = 0;
        this.ocupado = false;
    }
    
    public String obtenerNombre() {
        return this.nombre;
    }
    
    public double obtenerMontoRecabado() {
        return this.montoRecabado;
    }
    
    public int obtenerClientesAtendidos() {
        return this.clientesAtendidos;
    }
    
    public int obtenerBoletosVendidos() {
        return this.boletosVendidos;
    }
    
    public boolean estaOcupada() {
        return ocupado;
    }
    
    public void iniciarVenta(Cliente cliente) { // Inicia la venta de boletos
        this.ocupado = true;
        Sala s = obtenerSala();
        if(s == null) {
            this.ocupado = false;
            CinemaUDG.cerrarCinema();
        } else {
            if(cliente.obtenerNumeroDeAsientos() > 0 && 
                    (s.obtenerAsientosDisponibles() < cliente.obtenerNumeroDeAsientos())) {
                if(s.obtenerAsientosDisponibles() < 1) {
                    iniciarVenta(cliente);
                } else {
                    int sobrante = cliente.obtenerNumeroDeAsientos() - s.obtenerAsientosDisponibles();
                    System.out.println("==================================================================");
                    System.out.println(" - Partición detectada -");
                    System.out.println(" > Cliente: " + cliente.nombre);
                    System.out.println(" > Numero de asientos solicitados: " + cliente.obtenerNumeroDeAsientos());
                    System.out.println(" > Numero de asientos asignados: " + s.obtenerAsientosDisponibles());
                    System.out.println(" > Numero de asientos sobrantes: " + sobrante);
                    System.out.println("");
                    iniciarVenta(cliente.particionarCliente(sobrante));
                }
            }
            if(cliente.obtenerNumeroDeAsientos() > 0) {
                if(s.hayAsientosDisponibles()) {
                    cliente.asignarSala(s);
                    s.asignarAsientos(cliente);
                    this.montoRecabado += CinemaUDG.PRECIO_BOLETO * cliente.obtenerNumeroDeAsientos();
                    this.boletosVendidos += cliente.obtenerNumeroDeAsientos();
                    this.clientesAtendidos++;
                    System.out.println("==================================================================");
                    System.out.println("Atiende: " + this.nombre);
                    System.out.println("Vendiendo boleto a: " + cliente.nombre);
                    System.out.println("Numero de boletos adquiridos: " + cliente.obtenerNumeroDeAsientos());
                    System.out.println("Sala: " + s.obtenerNombre() + " (" + s.obtenerAsientosDisponibles() + "/" + s.obtenerCapacidadMaxima() + ")" );
                    System.out.println("Pelicula: " + s.obtenerPelicula());
                    System.out.println("Asientos: " + cliente.asientosToString());
                    System.out.println("Total a pagar: " + moneda(CinemaUDG.PRECIO_BOLETO * cliente.obtenerNumeroDeAsientos()));
                    this.ocupado = false;
                } else {
                    System.out.println("==================================================================");
                    System.out.println(" - CLIENTE FUERA DE SALA -");
                    System.out.println("El cliente " + cliente.obtenerNombre() + " se ha quedado con " + cliente.obtenerNumeroDeAsientos() + " boletos sin atender.");
                    System.out.println("Esto debido a que el Cinema se ha quedado sin salas disponibles.");
                    System.out.println("==================================================================");
                }
            }
        }
    }
    
    public Sala obtenerSala() {
        List<Sala> salasDisponibles = new ArrayList<>();
        CinemaUDG.obtenerSalas().stream().filter((s) -> (s.hayAsientosDisponibles())).forEachOrdered((s) -> {
            salasDisponibles.add(s);
        });
        if(salasDisponibles.isEmpty()) return null;
        return salasDisponibles.get(((int) (Math.random() * salasDisponibles.size())));
    }
    
    private static String moneda(double valor) {
        Locale locale = new Locale("es", "MX");      
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        return currencyFormatter.format(valor);
    }
}
