package JUnit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import javainterfaz.GestionUsuarios;
import javainterfaz.Usuario;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class GestionUsuariosTest {
    private GestionUsuarios gestionUsuarios;

    @Before
    public void setUp() throws Exception {
        gestionUsuarios = new GestionUsuarios();
    }

    @Test
    public void testCrearUsuario() {

    	String usuario = "testUser";
        String contraseña = "TestPassword1"; 
        String rol = "Administrador";
        
        gestionUsuarios.textUsuario.setText(usuario);
        gestionUsuarios.passwordField.setText(contraseña);

        gestionUsuarios.btnCrear.doClick();

        int rowCount = gestionUsuarios.model.getRowCount();
        boolean usuarioCreado = false;
        for (int i = 0; i < rowCount; i++) {
            String userName = (String) gestionUsuarios.model.getValueAt(i, 0);
            if (userName.equals(usuario)) {
                usuarioCreado = true;
                break;
            }
        }

        assertTrue("The user should be created and added to the table", usuarioCreado);
    }

    @Test
    public void testModificarUsuario() {
        testCrearUsuario();

        String newUser = "modifiedUser";
        String newPassword = "ModifiedPassword2";
        String newRole = "Árbitro";

        gestionUsuarios.textUsuario.setText(newUser);
        gestionUsuarios.passwordField.setText(newPassword);

        gestionUsuarios.table.setRowSelectionInterval(0, 0);

        gestionUsuarios.btnModificar.doClick();

        String updatedUser = (String) gestionUsuarios.model.getValueAt(0, 0);
        String updatedPassword = (String) gestionUsuarios.model.getValueAt(0, 1);
        String updatedRole = (String) gestionUsuarios.model.getValueAt(0, 2);

        assertEquals("The username should be updated", newUser, updatedUser);
        assertEquals("The password should be updated", newPassword, updatedPassword);
        assertEquals("The role should be updated", newRole, updatedRole);
    }

    @Test
    public void testEliminarUsuario() {
        testCrearUsuario();

        gestionUsuarios.table.setRowSelectionInterval(0, 0);

        gestionUsuarios.btnEliminar.doClick();

        int rowCount = gestionUsuarios.model.getRowCount();
        boolean usuarioEliminado = true;
        for (int i = 0; i < rowCount; i++) {
            String userName = (String) gestionUsuarios.model.getValueAt(i, 0);
            if (userName.equals("testUser")) {
                usuarioEliminado = false;
                break;
            }
        }

        assertTrue("The user should be deleted from the table", usuarioEliminado);
    }

    @Test
    public void testCargarUsuarios() {
        HashMap<String, Object[]> testData = new HashMap<>();
        testData.put("user1", new Object[]{"password1", 2});
        testData.put("user2", new Object[]{"password2", 1});

        Usuario.cargarUsuarios(testData); 

        gestionUsuarios.cargarUsuarios();

        int rowCount = gestionUsuarios.model.getRowCount();
        boolean user1Found = false;
        boolean user2Found = false;
        for (int i = 0; i < rowCount; i++) {
            String userName = (String) gestionUsuarios.model.getValueAt(i, 0);
            if (userName.equals("user1")) user1Found = true;
            if (userName.equals("user2")) user2Found = true;
        }

        assertTrue("User1 should be loaded", user1Found);
        assertTrue("User2 should be loaded", user2Found);
    }
}
