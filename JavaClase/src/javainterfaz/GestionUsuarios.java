
package javainterfaz;

import java.io.*;
import java.util.HashMap;

public class GestionUsuarios {
	
	private static final String USER_FILE = "usuarios.ser";

    public static void guardarUsuarios(String nombreUsuario, String contraseñaUsuario) {
        HashMap<String, String> usuarios = leerUsuarios();
        
        usuarios.put(nombreUsuario, contraseñaUsuario);
        
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USER_FILE))) {
            oos.writeObject(usuarios);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @SuppressWarnings("unchecked")
	private static HashMap<String, String> leerUsuarios() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(USER_FILE))) {
            Object obj = ois.readObject();
            if (obj instanceof HashMap) {
                return (HashMap<String, String>) obj;
            }
        } catch (IOException | ClassNotFoundException e) {
            // If the file doesn't exist or is empty, return an empty HashMap
            return new HashMap<>();
        }

        return new HashMap<>();
    }


}
