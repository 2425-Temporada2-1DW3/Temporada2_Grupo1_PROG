package javainterfaz;

import java.io.Serializable;
import java.util.List;

class Equipo implements Serializable{
    /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
	private String nombre;
    private List<String> jugadores;

    public Equipo(String nombre, List<String> jugadores) {
        this.nombre = nombre;
        this.jugadores = jugadores;
    }

    public String getNombre() {
        return nombre;
    }

    public List<String> getJugadores() {
        return jugadores;
    }
}
