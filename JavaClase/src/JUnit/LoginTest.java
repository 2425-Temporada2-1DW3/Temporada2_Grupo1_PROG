package JUnit;

import org.junit.Before;
import org.junit.Test;

import javainterfaz.Login;

import static org.junit.Assert.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class LoginTest {

    private Login login;

    @Before
    public void setUp() {
        // Inicializar la clase Login antes de cada prueba
        login = new Login();
    }

    @Test
    public void testValidarUsuario_ConUsuarioSuperAdmin() {
        // Simular un login con el superusuario
        login.validarUsuario("admin", "admin123");
        // Aquí, se espera que se muestre el mensaje para el superadmin
        // Verificar con un framework adecuado o mediante logs.
    }

    @Test
    public void testValidarUsuario_UsuarioValido() {
        // Crear un HashMap simulado con un usuario válido
        HashMap<String, Object[]> usuarios = new HashMap<>();
        usuarios.put("user1", new Object[]{"password123", 2}); // Usuario con permisos de administrador
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("usuarios.ser"))) {
            oos.writeObject(usuarios);
        } catch (IOException e) {
            e.printStackTrace();
        }

        login.validarUsuario("user1", "password123");
        // Aquí deberías verificar si se ha abierto la ventana correcta según el permiso
        // Esto puede requerir un enfoque más avanzado para verificar las ventanas abiertas.
    }

    @Test
    public void testValidarUsuario_UsuarioInvalido() {
        // Caso de usuario no registrado
        login.validarUsuario("userNotFound", "somePassword");
        // Verificar que se muestra un mensaje de error
        // Esto también puede requerir una forma de capturar la salida de JOptionPane.
    }

    @Test
    public void testWriteLog() {
        // Probar el método de escritura en el log
        String mensajeLog = "Usuario registrado como invitado";
        Login.writeLog(mensajeLog);

        // Verificar si el log contiene el mensaje esperado
        try {
            String content = new String(Files.readAllBytes(Paths.get("log.txt")));
            assertTrue("El archivo de log debería contener el mensaje", content.contains(mensajeLog));
        } catch (IOException e) {
            e.printStackTrace();
            fail("Error al leer el archivo de log");
        }
    }

    // Puedes añadir más pruebas como la verificación de que los botones sean visibles o no
    // dependiendo del nivel de permisos.
}
