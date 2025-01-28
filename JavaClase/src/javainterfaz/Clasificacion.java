package javainterfaz;



public class Clasificacion {
    private Equipo equipo;
    private int puntos;
    private int partidosJugados;
    private int golesFavor;
    private int golesContra;

    public Clasificacion(Equipo equipo) {
        this.equipo = equipo;
        this.puntos = 0;
        this.partidosJugados = 0;
        this.golesFavor = 0;
        this.golesContra = 0;
    }

    public void actualizar(int golesFavor, int golesContra) {
        this.partidosJugados++;
        this.golesFavor += golesFavor;
        this.golesContra += golesContra;

        if (golesFavor > golesContra) {
            this.puntos += 3; // Victoria
        } else if (golesFavor == golesContra) {
            this.puntos += 1; // Empate
        }
        // Derrota no suma puntos
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public int getPuntos() {
        return puntos;
    }

    public int getPartidosJugados() {
        return partidosJugados;
    }

    public int getGolesFavor() {
        return golesFavor;
    }

    public int getGolesContra() {
        return golesContra;
    }
}

