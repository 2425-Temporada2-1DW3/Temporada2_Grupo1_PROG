package javainterfaz;

import java.io.Serializable;
import java.util.List;

//Clase Temporada
	class Temporada implements Serializable {
 /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
	private int anio;
 private List<Equipo> equipos;

 public Temporada(int anio, List<Equipo> equipos) {
     this.anio = anio;
     this.equipos = equipos;
 }

 public int getAnio() {
     return anio;
 }

 public List<Equipo> getEquipos() {
     return equipos;
 }
	}
