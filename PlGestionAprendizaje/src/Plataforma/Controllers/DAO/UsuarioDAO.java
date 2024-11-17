package Plataforma.Controllers.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Plataforma.Database.DatabaseConnection;

public class UsuarioDAO {
    /**
     * Método para validar si un docente existe en la base de datos con las credenciales dadas.
     * @param usuario Nombre de usuario del docente.
     * @param clave Contraseña del docente.
     * @return true si las credenciales son válidas, false en caso contrario.
     */
    public boolean validarDocente(String usuario, String clave) {
        String sql = "SELECT * FROM Docentes WHERE usuario_Docent = ? AND clave = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, usuario);
            pstmt.setString(2, clave);
            ResultSet rs = pstmt.executeQuery();
            return rs.next(); // Devuelve true si encontró un registro
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Método para validar si un estudiante existe en la base de datos con las credenciales dadas.
     * @param usuario Nombre de usuario del estudiante.
     * @param clave Contraseña del estudiante.
     * @return true si las credenciales son válidas, false en caso contrario.
     */
    public boolean validarEstudiante(String usuario, String clave) {
        String sql = "SELECT * FROM Estudiantes WHERE usuario_Estu = ? AND clave = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, usuario);
            pstmt.setString(2, clave);
            ResultSet rs = pstmt.executeQuery();
            return rs.next(); // Devuelve true si encontró un registro
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
