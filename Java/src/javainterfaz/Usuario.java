package javainterfaz;

import java.io.*;
import java.util.*;

public class Usuario implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombre;
    private String contraseña;
    private int nivelPermiso;

    public Usuario(String nombre, String contraseña, int nivelPermiso) {
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.nivelPermiso = nivelPermiso;
    }

    public String getNombre() {
        return nombre;
    }

    public String getContraseña() {
        return contraseña;
    }

    public int getNivelPermiso() {
        return nivelPermiso;
    }

    // Método para cargar usuarios desde un archivo con HashMap
    @SuppressWarnings("unchecked")
	public static HashMap<String, Object[]> cargarUsuarios(String rutaArchivo) {
        HashMap<String, Object[]> usuarios = new HashMap<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(rutaArchivo))) {
            Object obj = ois.readObject();
            if (obj instanceof HashMap) {
                usuarios = (HashMap<String, Object[]>) obj;
            } else {
                System.out.println("El archivo no contiene un HashMap válido.");
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return usuarios;
    }
}
