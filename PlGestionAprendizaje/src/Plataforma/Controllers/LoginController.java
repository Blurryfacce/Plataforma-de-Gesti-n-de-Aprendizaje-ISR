package Plataforma.Controllers;

import Plataforma.Controllers.DAO.UsuarioDAO;

public class LoginController {
    /**
     * Método para iniciar sesión.
     * @param usuario Nombre de usuario ingresado.
     * @param clave Contraseña ingresada.
     * @return "Docente", "Estudiante" o null dependiendo del rol.
     */
    public static String iniciarSesion(String usuario, String clave) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        // Primero intenta validar en la tabla de docentes
        if (usuarioDAO.validarDocente(usuario, clave)) {
            return "Docente";
        }
        // Luego intenta validar en la tabla de estudiantes
        else if (usuarioDAO.validarEstudiante(usuario, clave)) {
            return "Estudiante";
        }

        // Si no coincide con ninguno, retorna null
        return null;
    }
}
