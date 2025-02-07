
package javainterfaz;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Icono extends JFrame {

    private static final long serialVersionUID = 1L;
    
    // Icono para el JFrame utilizando una imagen específica.
    private final ImageIcon nflIconFrame = new ImageIcon(Login.class.getResource("/img/imagenes/logo.png"));
    
    // Variables para almacenar imágenes redimensionadas.
    private Image logo;
    private static Image logoStatic;

    // Constructor de la clase Icono
    public Icono() {
        // Establece el icono de la ventana (JFrame) con la imagen definida.
        this.setIconImage(nflIconFrame.getImage());
        
        // Configura el cierre del JFrame al cerrar la ventana.
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Hace visible la ventana.
        this.setVisible(true);
    }

    // Cambia el tamaño de una imagen dada para ajustarla a un ancho y alto especificados.
    protected Image ResizeIcon(String ImagePath, int width, int height) {
        // Obtiene la imagen desde el recurso especificado y la redimensiona.
        logo = new ImageIcon(Login.class.getResource(ImagePath)).getImage()
                .getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return logo;
    }

    // Cambia el tamaño de una imagen dada para ajustarla a un ancho y alto especificados (versión estática).
    public static Image ResizeIconStatic(String ImagePath, int width, int height) {
        // Obtiene la imagen desde el recurso especificado y la redimensiona.
        logoStatic = new ImageIcon(Login.class.getResource(ImagePath)).getImage()
                .getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return logoStatic;
    }
}
