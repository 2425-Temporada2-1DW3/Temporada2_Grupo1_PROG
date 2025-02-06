package javainterfaz;

public class Clasificacion {
    private Equipo equipo;
    private int puntos;
    private int victorias;
    private int derrotas;

    public Clasificacion(Equipo equipo) {
        this.equipo = equipo;
        this.puntos = 0;
        this.victorias = 0;
        this.derrotas = 0;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public int getPuntos() {
        return puntos;
    }

    public int getVictorias() {
        return victorias;
    }

    public int getDerrotas() {
        return derrotas;
    }

    public void agregarVictoria() {
        victorias++;
        puntos += 3; // El equipo obtiene 3 puntos por victoria
    }

    public void agregarDerrota() {
        derrotas++;
    }

    public void agregarEmpate() {
        puntos++; // Ambos equipos obtienen 1 punto en caso de empate
    }

    @Override
    public String toString() {
        return equipo.getNombre() + " - Puntos: " + puntos + " - Victorias: " + victorias + " - Derrotas: " + derrotas;
    }
}
