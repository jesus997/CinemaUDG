package net.yosoydev.CinemaUDG;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
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
    private static List<Caja> cajas;
    private static final String[] PELICULAS = {
        "La Hera de Hielo", "Spiderman 3", "Deadpool", "Thor: Ragnarok", "Ted", "Ted 2", "Ted 3",
        "El Haro", "Spiderman 1", "Spiderman 2", "Lo que el viento se llevo", "Coco", "Insideout",
        "La forma del agua", "Avengers: Infinity Wars", "Star Wars", "Charly y la Fabrica de Chocolates",
        "Harry Potter", "Deadpool 2", "Froozen", "Toy Story", "Bugs", "Ironman", "Superman", "Wonder Woman"
    };
    private static final String[] NOMBRES = {
        "Pedro", "Pepe", "Juan", "Sebastian", "Julian", "Jesus", "Pablo", "Jose", "Maria", "Saraid",
        "Juliana", "Alicia", "Luis", "Sergio", "Erick", "Miguel", "Israel", "Lucia", "Sandra", "Lupita", 
        "Alondra", "Isabel", "Marquez", "Alberto", "Cristian", "Carlos", "Salvador", "Saul", "Natalia",
        "Roberto", "Ivan", "Paola", "Alexis", "Alejandro", "Gilberto", "Adrian", "Martin", "Diodoro"
    };
    public static double PRECIO_BOLETO = 45d;
    private static Queue<Cliente> clientes;
    private static final ScheduledExecutorService SCHEDULER = Executors.newScheduledThreadPool(1);
    private static ScheduledFuture<?> timer15Handle;
    private static ScheduledFuture<?> timer1Handle;
    private static Date tiempoDeInicio;
    private static Date tiempoDeFin;
    
    public static void main(String[] args) {
        clientes = new LinkedList();
        salas = new ArrayList<>();
        cajas = new ArrayList<>();
        init();
    }
    
    public CinemaUDG() {
    }
    
    public static void init() {
        generarSalas();
        generarCajas();
        tiempoDeInicio = new Date(System.currentTimeMillis());
        generarColaParaCajas();
        iniciarProcesoDeCaja();
    }
    
    public static void generarColaParaCajas() {
        final Runnable timer = () -> {
            boolean disponibles = salasCerradas();
            if(disponibles) {
                int personas = Helper.obtenerNumeroAleatorio(0, 8);
                for (int i = 0; i < personas; i++) {
                    int boletos = Helper.obtenerNumeroAleatorio(1, 4); // obtiene los boletos a comoprar
                    Cliente c = new Cliente(NOMBRES[Helper.obtenerNumeroAleatorio(0, (NOMBRES.length-1))], boletos);
                    clientes.add(c);
                }
                System.out.println("------------------------------------------------------------------");
                System.out.println("==================================================================");
                if(personas > 1) {
                    System.out.print("/*");
                    Helper.print("LLegando muchedumbre de " + personas + " personas.");
                    System.out.println("*\\");
                } else if(personas == 1) {
                    System.out.print("/*");
                    Helper.print("Solo llego 1 persona.");
                    System.out.println("*\\");
                } else {
                    System.out.print("/*");
                    Helper.print("No llego nadie.");
                    System.out.println("*\\");
                }
                System.out.println("==================================================================");
                System.out.print("/*");
                Helper.print("Clientes en cola: " + clientes.size());
                System.out.println("*\\");
                System.out.println("==================================================================");
            } else {
                cerrarCinema();
            }
        };
        timer15Handle = SCHEDULER.scheduleAtFixedRate(timer, 0, 5, SECONDS);
    }
    
    public static void iniciarProcesoDeCaja() {
        final Runnable timer = () -> {
            boolean disponibles = salasCerradas();
            if(disponibles) {
                if(!clientes.isEmpty()) {
                    Caja cs = cajas.get(Helper.obtenerNumeroAleatorio(0, cajas.size()));
                    if(!cs.estaOcupada()) {
                        Cliente c = clientes.poll();
                        cs.iniciarVenta(c);
                    }
                }
            } else {
                cerrarCinema();
            }
        };
        timer1Handle = SCHEDULER.scheduleAtFixedRate(timer, 0, 1, SECONDS);
    }
    
    public static void generarSalas() {
        salas.clear();
        for (int i = 0; i < 6; i++) {
            salas.add(new Sala("S"+(i+1), PELICULAS[Helper.obtenerNumeroAleatorio(0, (PELICULAS.length-1))], 10, 6));
        }
    }
    
    public static void generarCajas() {
        cajas.clear();
        for(int i = 0; i < 5; i++) {
            cajas.add(new Caja("C"+(i+1)));
        }
    }
    
    public static List<Sala> obtenerSalas() {
        return salas;
    }
    
    private static boolean salasCerradas() {
        int retornar = 0;
        retornar = salas.stream().filter((s) -> (s.hayAsientosDisponibles())).map((_item) -> 1).reduce(retornar, Integer::sum);
        return retornar > 0;
    }
    
    public static int obtenerTotalClientesAtendidos() {
        int atendidos = 0;
        atendidos = cajas.stream().map((c) -> c.obtenerClientesAtendidos()).reduce(atendidos, Integer::sum);
        return atendidos;
    }
    
    public static int obtenerTotalBoletosVendidos() {
        int vendidos = 0;
        vendidos = cajas.stream().map((c) -> c.obtenerBoletosVendidos()).reduce(vendidos, Integer::sum);
        return vendidos;
    }
    
    public static double obtenerTotalRecaudado() {
        double recaudado = 0d;
        recaudado = cajas.stream().map((c) -> c.obtenerMontoRecabado()).reduce(recaudado, (accumulator, _item) -> accumulator + _item);
        return recaudado;
    }
    
    public static void cerrarCinema() {
        timer15Handle.cancel(true);
        timer1Handle.cancel(true);
        tiempoDeFin = new Date(System.currentTimeMillis());
        System.out.println("");
        System.out.println("==================================================================");
        System.out.print("|");
        Helper.print("CinemaUDG cerrado");
        System.out.println("|");
        System.out.println("==================================================================");
        System.out.println("Total de clientes atendidos: " + obtenerTotalClientesAtendidos());
        System.out.println("Total de boletos vendidos: " + obtenerTotalBoletosVendidos());
        System.out.println("Precio por boleto: " + moneda(PRECIO_BOLETO));
        System.out.println("Total recaudado: " + moneda(obtenerTotalRecaudado()));
        System.out.println("DESGLOSE:");
        System.out.println("");
        for(Caja c : cajas) {
            System.out.println(" > Caja " + c.obtenerNombre());
            System.out.println(" > Clientes atendidos: " + c.obtenerClientesAtendidos());
            System.out.println(" > Boletos vendidos: " + c.obtenerBoletosVendidos());
            System.out.println(" > Recaudado: " + moneda(c.obtenerMontoRecabado()));
            System.out.println("");
        }
        System.out.println("==================================================================");
        System.out.println("SALAS:");
        System.out.println("");
        for(Sala s : salas) {
            System.out.println(" > Sala " + s.obtenerNombre());
            System.out.println(" > Pelicula: " + s.obtenerPelicula());
            System.out.println(" > Total de asientos: " + s.obtenerCapacidadMaxima());
            System.out.println(" > Asientos ocupados: " + s.obtenerAsientosOcupados());
            System.out.println(" > Asientos libres: " + s.obtenerAsientosDisponibles());
            System.out.println("");
        }
        System.out.println("==================================================================");
        System.out.println("Hora de apertura del Cinema: " + tiempoDeInicio);
        System.out.println("Hora de cierre del Cinema: " + tiempoDeFin);
        System.out.println("Tiempo estimado de apertura: " + new Date(tiempoDeFin.getTime() - tiempoDeInicio.getTime()));
        System.out.println("==================================================================");
        Helper.println("CLIENTES RESTANTE EN LA FILA: " + clientes.size());
        System.out.println("==================================================================");
        System.exit(0);
    }
    
    private static String moneda(double valor) {
        Locale locale = new Locale("es", "MX");      
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        return currencyFormatter.format(valor);
    }
}
