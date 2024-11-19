package Plataforma.Controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Plataforma.Database.DatabaseConnection;

public class LoginController {

    /**
     * Método para iniciar sesión.
     * 
     * @param usuario Nombre de usuario ingresado.
     * @param clave   Contraseña ingresada.
     * @return "Docente", "Estudiante" o null dependiendo del rol.
     */
    public static String iniciarSesion(String usuario, String clave) {
        // Primero intenta validar en la tabla de docentes
        if (validarDocente(usuario, clave)) {
            return "Docente";
        }
        // Luego intenta validar en la tabla de estudiantes
        else if (validarEstudiante(usuario, clave)) {
            return "Estudiante";
        }

        // Si no coincide con ninguno, retorna null
        return null;
    }

    private static boolean validarDocente(String usuario, String clave) {
        String query = "SELECT * FROM Docentes WHERE usuario_Docent = ? AND clave = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
    
            pstmt.setString(1, usuario);
            pstmt.setString(2, clave);
            ResultSet rs = pstmt.executeQuery();
    
            return rs.next(); // Si encuentra una fila, el docente es válido
        } catch (SQLException e) {
            System.out.println("Error al validar docente: " + e.getMessage());
        }
        return false;
    }

    private static boolean validarEstudiante(String usuario, String clave) {
        String query = "SELECT * FROM Estudiantes WHERE usuario_Estu = ? AND clave = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
    
            pstmt.setString(1, usuario);
            pstmt.setString(2, clave);
            ResultSet rs = pstmt.executeQuery();
    
            return rs.next(); // Si encuentra una fila, el estudiante es válido
        } catch (SQLException e) {
            System.out.println("Error al validar estudiante: " + e.getMessage());
        }
        return false;
    }
    
    public static int obtenerIdDocente(String usuario) {
        String query = "SELECT nombre FROM Docentes WHERE usuario_Docent = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
    
            pstmt.setString(1, usuario);
            ResultSet rs = pstmt.executeQuery();
    
            if (rs.next()) {
                return rs.getInt("nombre"); // Devuelve el id del docente
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener el id del docente: " + e.getMessage());
        }
        return -1; // Si no se encuentra el docente, retorna -1
    }

    public static int obtenerIdEstudiante(String usuario) {
        String query = "SELECT id FROM Estudiantes WHERE usuario_Estu = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
    
            pstmt.setString(1, usuario);
            ResultSet rs = pstmt.executeQuery();
    
            if (rs.next()) {
                return rs.getInt("id"); // Retorna el ID del estudiante
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener el ID del estudiante: " + e.getMessage());
        }
        return -1; // Retorna -1 si no se encuentra el estudiante
    }
}
