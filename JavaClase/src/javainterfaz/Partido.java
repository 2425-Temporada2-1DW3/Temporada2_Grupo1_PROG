package javainterfaz;

import java.io.Serializable;

public class Partido implements Serializable {
	
	    private Equipo equipo1;  // Primer equipo
	    private Equipo equipo2;  // Segundo equipo

	    // Constructor
	    public Partido(Equipo equipo1, Equipo equipo2) {
	        this.equipo1 = equipo1;
	        this.equipo2 = equipo2;
	    }

	    // Métodos getters
	    public Equipo getEquipo1() {
	        return equipo1;
	    }

	    public Equipo getEquipo2() {
	        return equipo2;
	    }

	    // Método para representar el partido como String (facilita la visualización)
	    @Override
	    public String toString() {
	        return equipo1.getNombre() + " vs " + equipo2.getNombre();
	    }
	}


