package Plataforma.Controllers.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Plataforma.Database.DatabaseConnection;

public class UsuarioDAO {

    /**
     * Valida las credenciales de usuario y devuelve el rol correspondiente.
     * 
     * @param usuario    El nombre de usuario o email ingresado.
     * @param contraseña La contraseña ingresada.
     * @return El rol del usuario ("docente", "estudiante") o null si no es válido.
     */
    public static String obtenerRolUsuario(String usuario, String contraseña) {
        String sql = "SELECT rol FROM usuarios WHERE usuario = ? AND contraseña = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario);
            stmt.setString(2, contraseña);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("rol"); // Retorna el rol: "docente" o "estudiante"
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null; // Credenciales inválidas
    }
}
