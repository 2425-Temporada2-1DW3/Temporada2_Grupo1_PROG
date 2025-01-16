package logica;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AsignacionEquipos {
    private String nombre;  // Atributo para almacenar el nombre del equipo

    // Constructor
    public AsignacionEquipos(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }

    public static void main(String[] args) {
        // Inicializar los equipos
        List<AsignacionEquipos> equipos = Arrays.asList(
            new AsignacionEquipos("Barcelona"),
            new AsignacionEquipos("Real Madrid"),
            new AsignacionEquipos("Atletico de Madrid"),
            new AsignacionEquipos("Sevilla"),
            new AsignacionEquipos("Villarreal"),
            new AsignacionEquipos("Sociedad")
        );

        int nJornadas = 10;  // Número de jornadas
        String[][][] partidos = new String[nJornadas][3][2]; // [jornada][partidos][equipo1, equipo2]

        for (int jornada = 0; jornada < nJornadas; jornada++) {
            List<String> participantes = new ArrayList<>();
            for (AsignacionEquipos equipo : equipos) {
                participantes.add(equipo.toString());
            }

            List<String[]> partidosJornada = new ArrayList<>();

            // Generar partidos sin repetir participantes en la misma jornada
            while (partidosJornada.size() < 3) { // Hasta 3 partidos por jornada
                Collections.shuffle(participantes); // Mezclar los participantes
                for (int i = 0; i < participantes.size() - 1; i += 2) {
                    // Añadir partidos como un array con dos elementos
                    partidosJornada.add(new String[] { participantes.get(i), participantes.get(i + 1) });
                    if (partidosJornada.size() == 3) break; // Limitar a 3 partidos
                }
            }

            // Guardar partidos de la jornada
            for (int k = 0; k < partidosJornada.size(); k++) {
                partidos[jornada][k][0] = partidosJornada.get(k)[0]; // Equipo 1
                partidos[jornada][k][1] = partidosJornada.get(k)[1]; // Equipo 2
            }

            // Mostrar la jornada
            System.out.println("Jornada " + (jornada + 1) + ":");
            for (String[] partido : partidosJornada) {
                System.out.println(partido[0] + " vs " + partido[1]);
            }
            System.out.println(); // Espacio entre jornadas
        }

        // Acceder a todos los equipos de todos los partidos
        for (int jornada = 0; jornada < nJornadas; jornada++) {
            for (int partidoIndex = 0; partidoIndex < partidos[jornada].length; partidoIndex++) {
                if (partidos[jornada][partidoIndex][0] != null) { // Verificar que el partido no sea nulo
                    String equipo1 = partidos[jornada][partidoIndex][0]; // Primer equipo
                    String equipo2 = partidos[jornada][partidoIndex][1]; // Segundo equipo
                    System.out.println("Jornada " + (jornada + 1) + ", Partido " + (partidoIndex + 1) + ": " +
                            "Equipo 1: " + equipo1 + ", Equipo 2: " + equipo2);
                }
            }
        }

        // Ejemplo: Acceder al primer equipo del primer partido de la primera jornada
        String primerEquipo = partidos[0][0][0]; // Primer equipo del primer partido de la primera jornada
        System.out.println("El primer equipo del primer partido de la primera jornada es: " + primerEquipo);
    }
}
