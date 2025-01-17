package javainterfaz;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistroValidacion {

    // Patrón para validar contraseñas: al menos 5 caracteres, una mayúscula y un número.
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[A-Z])(?=.*\\d).{5,}$");
    
    // Patrón para validar nombres: solo letras y espacios, sin números ni caracteres especiales.
    private static final Pattern FULL_NAME_PATTERN = Pattern.compile("^[a-zA-Z]+( [a-zA-Z]+)*$");

    // Verifica si la contraseña cumple con el formato definido en PASSWORD_PATTERN.
    public static boolean contraseñaValida(String contraseña) {
        Matcher matcher = PASSWORD_PATTERN.matcher(contraseña);
        return matcher.matches();
    }

    // Verifica si el nombre de usuario cumple con el formato definido en FULL_NAME_PATTERN.
    public static boolean usuarioValido(String nombreUsuario) {
        Matcher matcher = FULL_NAME_PATTERN.matcher(nombreUsuario);
        return matcher.matches();
    }
}
