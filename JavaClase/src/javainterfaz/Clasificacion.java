package javainterfaz;

//CLASE CLASIFICACION
class Clasificacion {
    private String nombreEquipo;
    private int puntos;

    public Clasificacion(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
        this.puntos = 0; // Inicialmente, los equipos tienen 0 puntos
    }

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }
}
