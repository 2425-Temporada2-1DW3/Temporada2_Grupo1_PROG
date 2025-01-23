package javainterfaz;

import java.io.Serializable;

public class Partido implements Serializable {
	
	    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private Equipo local;  // Primer equipo
	    private Equipo visitante;  // Segundo equipo

	    // Constructor
	    public Partido(Equipo local, Equipo visitante) {
	        this.local = local;
	        this.visitante = visitante;
	    }

	    // Métodos getters
	    public Equipo getlocal() {
	        return local;
	    }

	    public Equipo getvisitante() {
	        return visitante;
	    }

	    // Método para representar el partido como String (facilita la visualización)
	    @Override
	    public String toString() {
	        return local.getNombre() + " vs " + visitante.getNombre();
	    }
	}


