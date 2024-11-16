package Plataforma.Controllers;

import Plataforma.Controllers.DAO.UsuarioDAO;

public class LoginController {
    public static String validarUsuario(String usuario, String contraseña) {
        return UsuarioDAO.obtenerRolUsuario(usuario, contraseña); // Método que consulta la base de datos
    }
}
