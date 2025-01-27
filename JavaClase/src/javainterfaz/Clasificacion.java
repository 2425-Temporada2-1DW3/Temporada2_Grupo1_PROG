package javainterfaz;

import java.util.ArrayList;
import java.util.List;

class Clasificacion {
    private List<Equipo> equipos;

    // Constructor
    public Clasificacion() {
        equipos = new ArrayList<>();
    }

    // Método para agregar equipos
    public void agregarEquipo(Equipo equipo) {
        equipos.add(equipo);
    }

    // Método para actualizar los puntos después de un partido
    public void actualizarClasificacion(String nombreEquipoLocal, int golesLocal, String nombreEquipoVisitante, int golesVisitante) {
        Equipo equipoLocal = obtenerEquipo(nombreEquipoLocal);
        Equipo equipoVisitante = obtenerEquipo(nombreEquipoVisitante);

        // Actualizar puntos según el resultado del partido
        if (golesLocal > golesVisitante) {
            equipoLocal.sumarPuntos(3); // El equipo local gana
        } else if (golesLocal < golesVisitante) {
            equipoVisitante.sumarPuntos(3); // El equipo visitante gana
        } else {
            equipoLocal.sumarPuntos(1); // Empate
            equipoVisitante.sumarPuntos(1); // Empate
        }
    }

    // Método para obtener un equipo por su nombre
    private Equipo obtenerEquipo(String nombreEquipo) {
        for (Equipo equipo : equipos) {
            if (equipo.getNombre().equals(nombreEquipo)) {
                return equipo;
            }
        }
        return null; // Si no se encuentra el equipo
    }

    // Método para obtener la clasificación ordenada por puntos (descendente)
    public List<Equipo> obtenerClasificacion() {
        equipos.sort((e1, e2) -> Integer.compare(e2.getPuntos(), e1.getPuntos()));
        return equipos;
    }

    // Método para mostrar la clasificación (esto puede ser útil para depuración)
    public void mostrarClasificacion() {
        List<Equipo> clasificados = obtenerClasificacion();
        for (Equipo equipo : clasificados) {
            System.out.println(equipo.getNombre() + " - " + equipo.getPuntos() + " puntos");
        }
    }
}
