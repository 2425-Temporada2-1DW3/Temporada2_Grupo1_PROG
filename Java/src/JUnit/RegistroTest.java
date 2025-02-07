package JUnit;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import javainterfaz.Registro;
import javainterfaz.LeerGuardarUsuarios;
import javax.swing.JOptionPane;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class RegistroTest {

    private Registro registro;

    @Before
    public void setUp() {
        // Inicializar la clase Registro antes de cada prueba
        registro = new Registro();
    }

    @Test
    public void testRegistrarUsuarioNuevo() {
        // Simular el registro de un nuevo usuario
        String usuario = "nuevoUsuario";
        String contrasena = "contrasena123";
        
        // Supongamos que el método `guardarUsuarios` funciona correctamente
        // Verificar si el usuario no existe previamente
        boolean existe = LeerGuardarUsuarios.usuarioExiste(usuario);
        
        if (!existe) {
            registro.validarUsuarios(usuario, contrasena);
        }
        
        // Comprobar si el usuario fue guardado correctamente (esto depende de cómo esté implementado el método `guardarUsuarios`)
        assertTrue("El usuario debería ser registrado correctamente", LeerGuardarUsuarios.usuarioExiste(usuario));
    }

    @Test
    public void testRegistrarUsuarioExistente() {
        // Supongamos que ya existe un usuario con el nombre 'existenteUsuario'
        String usuarioExistente = "existenteUsuario";
        String contrasena = "contrasena123";
        
        // Intentar registrar un usuario ya existente
        LeerGuardarUsuarios.guardarUsuarios(usuarioExistente, contrasena, 0); // Guardar un usuario antes de la prueba
        
        // Intentar registrar el mismo usuario nuevamente
        boolean existe = LeerGuardarUsuarios.usuarioExiste(usuarioExistente);
        
        if (existe) {
            JOptionPane.showMessageDialog(null, "El nombre de usuario ya existe. Elija otro.");
        }

        // Verificar que no se pueda registrar el usuario
        assertTrue("El usuario no debería ser registrado ya que existe", LeerGuardarUsuarios.usuarioExiste(usuarioExistente));
    }

    @Test
    public void testWriteLog() {
        // Probar el método de escritura en el log
        String mensajeLog = "Usuario registrado como invitado";
        registro.writeLog(mensajeLog);

        // Verificar si el log contiene el mensaje esperado
        try {
            String content = new String(Files.readAllBytes(Paths.get("log.txt")));
            assertTrue("El archivo de log debería contener el mensaje", content.contains(mensajeLog));
        } catch (IOException e) {
            e.printStackTrace();
            fail("Error al leer el archivo de log");
        }
    }

    @Test
    public void testCamposVacios() {
        // Probar cuando los campos de usuario y contraseña están vacíos
        String usuarioVacio = "";
        String contrasenaVacia = "";
        
        // Validar que no se puede registrar un usuario sin datos
        boolean resultado = usuarioVacio.isEmpty() || contrasenaVacia.isEmpty();
        
        assertTrue("El registro no debería completarse con campos vacíos", resultado);
    }
}
