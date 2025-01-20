package javainterfaz;

import java.io.*;
import java.util.HashMap;

public class LeerGuardarUsuarios {
    
    private static final String USER_FILE = "usuarios.ser"; // Siempre usa esta constante para la ruta del archivo

    // Guardar el usuario y la contraseña
    public static void guardarUsuarios(String nombreUsuario, String contraseñaUsuario) {
        // Leer usuarios actuales
        HashMap<String, String> usuarios = leerUsuarios();
        
        // Añadir el nuevo usuario y contraseña
        usuarios.put(nombreUsuario, contraseñaUsuario);
        
        // Guardar los datos nuevamente en el archivo
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USER_FILE))) {
            oos.writeObject(usuarios); // Escribe el HashMap completo en el archivo
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // Leer los usuarios desde el archivo serializado
    @SuppressWarnings("unchecked")
    private static HashMap<String, String> leerUsuarios() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(USER_FILE))) {
            Object obj = ois.readObject();
            if (obj instanceof HashMap) {
                return (HashMap<String, String>) obj;
            }
        } catch (IOException | ClassNotFoundException e) {
            // Si el archivo no existe o no se puede leer, devuelve un HashMap vacío
            return new HashMap<>();
        }
        return new HashMap<>();
    }

    // Verificar si un usuario existe en el archivo
    public static boolean usuarioExiste(String usuario) {
        HashMap<String, String> usuarios = leerUsuarios(); // Usar el método leerUsuarios()
        return usuarios.containsKey(usuario); // Devuelve true si el usuario está en el HashMap
    }

    // Obtener la contraseña del usuario
    public static String obtenerContraseña(String usuario) {
        HashMap<String, String> usuarios = leerUsuarios();
        return usuarios.get(usuario); // Retorna la contraseña correspondiente
    }
}
