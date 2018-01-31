package net.yosoydev.CinemaUDG;

/**
 *
 * @author jesus
 */
public class Cliente {
    public String nombre;
    public Sala sala;
    public String asiento;
    
    public Cliente(String nombre) {
        this.nombre = nombre;
        this.sala = new Sala("Undefined", "Undefined", 0, 0);
        this.asiento = "Undefined";
    }
    
    public String obtenerNombre() {
        return this.nombre;
    }
    
    public void asignarSala(Sala sala) {
        this.sala = sala;
    }
    
    public void asignarAsiento(String asiento) {
        this.asiento = asiento;
    }
    
    @Override
    public String toString() {
        return "Cliente: " + this.nombre + "\n" +
                "Sala: " + this.sala.toString() + "\n" +
                "Asiento: " + this.asiento + "\n";
    }
}
