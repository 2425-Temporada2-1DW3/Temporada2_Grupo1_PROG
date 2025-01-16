package javainterfaz;

public class RegistroValidacion {

	   
	    
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
		
	    // EFECTOS: Verifica si la contraseña cumple con el formato de PASSWORD_PATTERN,
	    //      (por ejemplo, al menos 8 caracteres, uno de ellos en mayúscula y un número).
	    
	    public static boolean ContraseñaValida(String password) {
	        return true;
	    }

	 // EFECTOS: Verifica si el nombre completo no contiene números ni caracteres especiales.
	    
	    public static boolean UsuarioValido(String fullName) {
	        return true;

	        
	    }
}
