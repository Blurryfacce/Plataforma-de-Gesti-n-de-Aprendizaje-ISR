package Plataforma.Controllers.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Plataforma.Database.DatabaseConnection;

public class EstudianteDAO {
    public static boolean insertarEstudiante(String nombre, String apellido, String cedula, String email, String direccion, String usuario, String contrasena) {
        try (Connection conn = DatabaseConnection.connect(); // Reemplaza con tu clase de conexión
             PreparedStatement stmt = conn.prepareStatement(
                 "INSERT INTO Estudiantes (nombre, apellido, cedula, email, direccion, usuario_Estu, clave) VALUES (?, ?, ?, ?, ?, ?, ?)")) {
    
            stmt.setString(1, nombre);
            stmt.setString(2, apellido);
            stmt.setString(3, cedula);
            stmt.setString(4, email);
            stmt.setString(5, direccion.isEmpty() ? null : direccion); // Manejo de dirección opcional
            stmt.setString(6, usuario);
            stmt.setString(7, contrasena); // Nota: encriptar contraseñas antes de almacenarlas
    
            
            int filasInsertadas = stmt.executeUpdate();
            return filasInsertadas > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static boolean existeUsuario(String usuario) {
    try (Connection conn = DatabaseConnection.connect();
         PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM Estudiantes WHERE usuario = ?")) {

        stmt.setString(1, usuario);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            return rs.getInt(1) > 0;
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return false;
    }

    
}
