package Plataforma.Controllers.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Plataforma.Database.DatabaseConnection;

public class EstudianteDAO {
    public static boolean insertarEstudiante(String nombre, String apellido, String cedula, String email, String direccion){
        String sql = "INSERT INTO estudiantes (nombre, apellido, cedula, email, direccion) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setString(1, nombre);
            pstmt.setString(2, apellido);
            pstmt.setString(3, cedula);
            pstmt.setString(4, email);
            pstmt.setString(5, direccion);
            
            pstmt.executeUpdate();
            System.out.println("Estudiante registrado exitosamente en la base de datos.");
            return true; // Inserción exitosa

        } catch (SQLException e) {
            System.out.println("Error al insertar estudiante: " + e.getMessage());
            return false; // Inserción fallida
        }
    }
}
