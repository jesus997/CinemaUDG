package net.yosoydev.CinemaUDG;

/**
 *
 * @author jesus
 */
public class Sala {
    private final String nombre;
    private final String pelicula;
    private final int filas;
    private final int columnas;
    private final int[][] asientos; // simulará los asientos de la sala
    private int asientosOcupados;
    
    public Sala(String nombre, String pelicula, int filas, int columnas) {
        this.nombre = nombre;
        this.pelicula = pelicula;
        this.filas = filas;
        this.columnas = columnas;
        this.asientos = new int[filas][columnas];
        this.limpiarSala();
    }
    
    public String obtenerNombre() {
        return this.nombre;
    }
    
    public String obtenerPelicula() {
        return this.pelicula;
    }
    
    public int obtenerCapacidadMaxima() {
        return this.filas * this.columnas;
    }
    
    public int obtenerAsientosOcupados() {
        return this.asientosOcupados;
    }
    
    public int obtenerAsientosDisponibles() {
        return this.obtenerCapacidadMaxima() - this.obtenerAsientosOcupados();
    }
    
    public boolean hayAsientosDisponibles() {
        return this.obtenerAsientosDisponibles() > 0;
    }
    
    public boolean asignarAsientos(Cliente cliente) {
        int ocupados = 0;
        System.out.println("");
        System.out.println("Asignando asientos en "+this.nombre);
        System.out.println("Boletos a asignar: " + cliente.obtenerNumeroDeAsientos());
        for(int i = 0; i < this.filas; i++) {
            for(int j = 0; j < this.columnas; j++) {
                if(this.asientos[i][j] == 0) {
                    if(ocupados >= cliente.obtenerNumeroDeAsientos()) {
                        return true;
                    }
                    this.asientos[i][j] = 1; // marcamos como ocupado el asiento en la simulación
                    cliente.asignarAsiento(obtenerLetra(i)+(j+1));
                    this.asientosOcupados++;
                    ocupados++;
                    System.out.println("Boleto "+obtenerLetra(i)+(j+1)+" asginado.");
                }
            }
        }
        System.out.println("Fin de asignacion.");
        System.out.println("Boletos asignados: "+ocupados);
        System.out.println("");
        return false;
    }
    
    private void limpiarSala() {
        for(int i = 0; i < this.filas; i++) {
            for(int j = 0; j < this.columnas; j++) {
                this.asientos[i][j] = 0;
            }
        }
        this.asientosOcupados = 0;
    }
    
    private String obtenerLetra(int i) {
        String[] letras = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
        if(i>letras.length) return "U";
        return letras[i];
    }
    
    @Override
    public String toString() {
        return "Sala " + this.nombre + "\n" +
                "Pelicula: " + this.pelicula + "\n" +
                "Asientos: " + this.obtenerCapacidadMaxima() + "\n";
    }
}
