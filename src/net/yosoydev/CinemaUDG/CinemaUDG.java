package net.yosoydev.CinemaUDG;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 *
 * @author jesus
 */
public final class CinemaUDG {
    private static List<Sala> salas;
    private static final String[] peliculas = {
        "La Hera de Hielo", "Spiderman 3", "Deadpool", "Thor: Ragnarok", "Ted", "Ted 2", "Ted 3",
        "El Haro", "Spoderman 1", "Spiderman 2", "Lo que el viento se llevo", "Coco", "Insideout"
    };
    private static final String[] nombres = {
        "Pedro", "Pepe", "Juan", "Sebastian", "Julian", "Jesus", "Pablo", "Jose", "Maria", "Saraid",
        "Juliana", "Alicia", "Luis", "Sergio", "Erick", "Miguel", "Israel", "Lucia", "Sandra", "Lupita", 
        "Alondra", "Isabel", "Marquez", "Alberto", "Cristian", "Carlos", "Salvador", "Saul", "Natalia",
        "Roberto", "Ivan", "Paola", "Alexis", "Alejandro", "Gilberto", "Adrian", "Martin", "Diodoro"
    };
    private static Queue<Cliente> clientes;
    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private static ScheduledFuture<?> timerHandle;
    
    public static void main(String[] args) {
        clientes = new LinkedList();
        salas = new ArrayList<>();
        generarSalas();
        generarColaParaCajas();
    }
    
    public CinemaUDG() {
    }
    
    public static void generarColaParaCajas() {
        final Runnable timer = () -> {
            boolean disponibles = salasCerradas();
            System.out.println("Hola");
            if(disponibles) {
                int personas = obtenerNumeroAleatorio(0, 8);
                System.out.println("Aleatorio: " + personas);
                for (int i = 0; i < personas; i++) {
                    Cliente c = new Cliente(nombres[obtenerNumeroAleatorio(0, (nombres.length-1))]);
                    clientes.add(c);
                    System.out.println(c.toString());
                }
            } else {
                timerHandle.cancel(true);
                System.out.println("CinemaUDG Cerrado");
            }
        };
        timerHandle = scheduler.scheduleAtFixedRate(timer, 15, 15, SECONDS);
    }
    
    public static void generarSalas() {
        for (int i = 0; i < 6; i++) {
            salas.add(new Sala("S"+i, peliculas[obtenerNumeroAleatorio(0, (peliculas.length-1))], 10, 6));
        }
    }
    
    public List<Sala> obtenerSalas() {
        return salas;
    }
    
    private static boolean salasCerradas() {
        int retornar = 0;
        for (int i = 0; i < (salas.size()-1); i++) {
            if (salas.get(i).hayAsientosDisponibles()) retornar++;
        }
        return retornar > 0;
    }
    
    private static int obtenerNumeroAleatorio(int n, int m) {
        return (int) (Math.random() * m) + n;
    }
}
