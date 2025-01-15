package javainterfaz;

import java.io.*;
import java.util.HashMap;

public class GestionUsuarios {
	
	private static final String USER_FILE = "users.ser";

    public static void saveUser(String username, char[] userPassword) {
        HashMap<String, String> users = readUsers();

        

        // Serialize and save the HashMap to a file
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USER_FILE))) {
            oos.writeObject(users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @SuppressWarnings("unchecked")
	private static HashMap<String, String> readUsers() {
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
