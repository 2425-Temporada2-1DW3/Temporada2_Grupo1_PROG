package javainterfaz;

import java.io.Serializable;

import javax.swing.ImageIcon;

class Jugador implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombre;
    private ImageIcon imagen;

    public Jugador(String nombre, ImageIcon imagen) {
        this.nombre = nombre;
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public ImageIcon getImagen() {
        return imagen;
    }
}
