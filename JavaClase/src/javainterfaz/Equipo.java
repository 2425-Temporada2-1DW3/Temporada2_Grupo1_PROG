package javainterfaz;

import java.io.Serializable;
import java.util.List;

import javax.swing.ImageIcon;

class Equipo implements Serializable,  Comparable<Equipo>{
    /**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	private String nombre;
    private List<String> jugadores;
	private int puntos;
	private int victorias;
	private int derrotas;
	
    

    public Equipo(String nombre, List<String> jugadores) {
        this.nombre = nombre;
        this.jugadores = jugadores;
        this.puntos = 0;
        this.victorias = 0;
		this.derrotas = 0;
		
    }
    
    public int getPuntos() {
		return puntos;
	}
    
 // Setter para puntos
    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }
    
    public int getVictorias() {
		return victorias;
	}

	public int getderrotas() {
		return derrotas;
	}

    public String getNombre() {
        return nombre;
    }

    public List<String> getJugadores() {
        return jugadores;
    }
    
    public void sumarPuntos(int puntos) {
        this.puntos += puntos;
    }
    
    public void resetEstadisticas() {
        this.puntos = 0;
        this.victorias = 0;
        this.derrotas = 0;
    }
    
    public void actualizarEstadisticas(int golesPropios, int golesRivales) {
        if (golesPropios > golesRivales) {
            this.puntos += 3; // Gana
            this.victorias++;
        } else if (golesPropios < golesRivales) {
            this.derrotas++; // Pierde
        }
        // Si empatan, no hay cambios en victorias o derrotas, pero no se especificó manejo de empates.
    }
    
	public int compareTo(Equipo other) {
		// Compare based on points, goal difference, and then goals scored
		if (this.puntos != other.puntos) {
			return Integer.compare(other.puntos, this.puntos); // Higher points first
		}
		return derrotas;

			
		}
	
	@Override
	public String toString() {
	    return nombre; // Suponiendo que el atributo 'nombre' contiene el nombre del equipo
	}
	
}
