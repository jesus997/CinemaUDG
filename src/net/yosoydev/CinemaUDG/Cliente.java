package net.yosoydev.CinemaUDG;

/**
 *
 * @author jesus
 */
public class Cliente {
    public String nombre;
    public Sala sala;
    public String[] asientos;
    private int asientoAsignado;
    
    public Cliente(String nombre, int asientos) {
        this.nombre = nombre;
        this.sala = new Sala("Undefined", "Undefined", 0, 0);
        this.asientos = new String[asientos];
        for(int i = 0; i < asientos; i++) {
            this.asientos[i] = "Undefined";
        }
        this.asientoAsignado = 0;
    }
    
    public String obtenerNombre() {
        return this.nombre;
    }
    
    public void asignarSala(Sala sala) {
        this.sala = sala;
    }
    
    public void asignarAsientos(String[] asiento) {
        this.asientos = asiento;
    }
    
    public void asignarAsiento(String asiento) {
        if(this.asientoAsignado < this.asientos.length) {
            this.asientos[this.asientoAsignado] = asiento;
            this.asientoAsignado++;
        }
    }
    
    public int obtenerNumeroDeAsientos() {
        return this.asientos.length;
    }
    
    public Cliente particionarCliente(int asientos) {
        int nasientos = this.asientos.length - asientos;
        Cliente clon = new Cliente("C - " + this.nombre, nasientos);
        this.asientos = new String[nasientos];
        for(int i = 0; i < nasientos; i++) {
            this.asientos[i] = "Undefined";
        }
        return clon;
    }
    
    public String asientosToString() {
        String raw = "";
        int i = 0;
        for (String asiento : this.asientos) {
            if(i>0) raw += ", ";
            raw += asiento;
            i++;
        }
        
        return raw;
    }
    
    @Override
    public String toString() {
        return "Cliente: " + this.nombre + "\n" +
                "Sala: " + this.sala.toString() + "\n" +
                "Asiento: " + asientosToString() + "\n";
    }
}
