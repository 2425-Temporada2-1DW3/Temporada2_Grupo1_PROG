package javainterfaz;

import java.util.ArrayList;
import java.util.List;

public class Jornada {
    private int numero; // Número de la jornada
    private List<Partido> partidos; // Lista de partidos en la jornada
    private boolean finalizada; // Si la jornada ha finalizado o no

    public Jornada(int numero) {
        this.numero = numero;
        this.partidos = new ArrayList<>();
        this.finalizada = false;
    }

    public int getNumero() {
        return numero;
    }

    public List<Partido> getPartidos() {
        return partidos;
    }

    public void agregarPartido(Partido partido) {
        partidos.add(partido);
    }

    public boolean isFinalizada() {
        return finalizada;
    }

    public void marcarComoFinalizada() {
        this.finalizada = true;
    }

    @Override
    public String toString() {
        return "Jornada " + numero;
    }
}
