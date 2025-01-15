package javainterfaz;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistroValidacion {

		private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,}$");
	    private static final Pattern CONTRASEÑA_PATTERN = Pattern.compile("^(?=.*[A-Z])(?=.*\\d).{8,}$");
	    private static final Pattern USUARIO_PATTERN = Pattern.compile("^[a-zA-Z]+( [a-zA-Z]+)*$");

	    
	    
	 // REQUIERE: Los campos no deben ser nulos.
	 // EFECTOS: Devuelve false si ningún campo está vacío; de lo contrario, devuelve true.
	    
		public static boolean CamposVacios(String... fields) {
		    for (String field : fields) {
		        if (!field.isEmpty()) {
		            return false;
		        }
		    }
		    return true;
		}


		 
		// EFECTOS: Verifica si el correo electrónico cumple con el formato de un correo electrónico.	
		
	    public static boolean EmailValido(String email) {
	        Matcher matcher = EMAIL_PATTERN.matcher(email);
	        return matcher.matches();
	    }

	    // EFECTOS: Verifica si la contraseña cumple con el formato de PASSWORD_PATTERN,
	    //      (por ejemplo, al menos 8 caracteres, uno de ellos en mayúscula y un número).
	    
	    public static boolean ContraseñaValida(String password) {
	        Matcher matcher = CONTRASEÑA_PATTERN.matcher(password);
	        return matcher.matches();
	    }

	 // EFECTOS: Verifica si el nombre completo no contiene números ni caracteres especiales.
	    
	    public static boolean UsuarioValido(String fullName) {
	        Matcher matcher = USUARIO_PATTERN.matcher(fullName);
	        return matcher.matches();
	    }
}
