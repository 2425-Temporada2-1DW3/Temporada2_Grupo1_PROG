package javainterfaz;

import java.util.ArrayList;
import java.util.List;

public class Jornada {
    private int numeroJornada;        // Número de la jornada
    private List<Partido> partidos;   // Lista de partidos de esta jornada
    private boolean jugada;           // Indicador si la jornada ya se jugó

    // Constructor
    public Jornada(int numeroJornada, List<Equipo> equipos) {
        this.numeroJornada = numeroJornada;
        this.partidos = new ArrayList<>();
        this.jugada = false;  // Inicialmente la jornada no se ha jugado

        // Generamos los partidos de la jornada sin repeticiones
        for (int i = 0; i < equipos.size(); i++) {
            for (int j = i + 1; j < equipos.size(); j++) {
                partidos.add(new Partido(equipos.get(i), equipos.get(j)));
            }
        }
    }

    // Métodos getter
    public int getNumeroJornada() {
        return numeroJornada;
    }

    public List<Partido> getPartidos() {
        return partidos;
    }

    public boolean esJugada() {
        return jugada;
    }

    // Método para marcar la jornada como jugada
    public void marcarComoJugada() {
        this.jugada = true;
    }

    // Método para mostrar los partidos de la jornada
    public void mostrarJornada() {
        System.out.println("Jornada " + numeroJornada + ":");
        for (Partido partido : partidos) {
            System.out.println(partido);
        }
    }

    // Método para mostrar solo los partidos no jugados
    public void mostrarPartidosPendientes() {
        if (!jugada) {
            mostrarJornada(); // Si no se ha jugado, mostramos todos los partidos
        } else {
            System.out.println("Esta jornada ya ha sido jugada.");
        }
    }

    // Método para mostrar si la jornada ya fue jugada
    public String estadoJornada() {
        return jugada ? "Jornada Finalizada" : "Jornada En Curso";
    }
}

