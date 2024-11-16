package Plataforma.Controllers;

public class Validaciones {

    /**
     * Valida que un nombre o apellido no contenga números y tenga un tamaño razonable.
     * @param texto El nombre o apellido a validar.
     * @return true si el texto es válido, false en caso contrario.
     */
    public static boolean validarNombreOApellido(String texto) {
        return texto.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]{2,50}$"); // Letras, espacios, y mínimo 2 caracteres
    }

    /**
     * Valida que una cédula tenga exactamente 10 caracteres y solo sean números.
     * @param cedula La cédula a validar.
     * @return true si la cédula es válida, false en caso contrario.
     */
    public static boolean validarCedula(String cedula) {
        return cedula.matches("^\\d{10}$"); // Exactamente 10 dígitos
    }

    /**
     * Valida que un email contenga "@" y termine con ".com" o ".edu".
     * @param email El email a validar.
     * @return true si el email es válido, false en caso contrario.
     */
    public static boolean validarEmail(String email) {
        return email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.(com|edu|ec|org)$");
    }

    /**
     * Valida que una dirección sea razonable (sin códigos postales ni caracteres especiales).
     * @param direccion La dirección a validar.
     * @return true si la dirección es válida, false en caso contrario.
     */
    public static boolean validarDireccion(String direccion) {
        return direccion.matches("^[a-zA-Z0-9áéíóúÁÉÍÓÚñÑ\\s,#.-]{5,100}$"); // Letras, números, y caracteres básicos
    }
}
