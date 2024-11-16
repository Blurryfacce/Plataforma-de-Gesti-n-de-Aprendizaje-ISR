package Plataforma.Controllers.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Plataforma.Database.DatabaseConnection;

public class DocenteDAO {

    /**
     * Inserta un docente en la base de datos.
     * 
     * @param nombre       El nombre del docente.
     * @param apellido     El apellido del docente.
     * @param cedula       La cédula del docente.
     * @param email        El email del docente.
     * @param departamento El departamento del docente.
     * @return true si el registro fue exitoso, false en caso contrario.
     */
    public static boolean insertarDocente(String nombre, String apellido, String cedula, String email, String departamento) {
        String sql = "INSERT INTO Docentes (nombre, apellido, cedula, email, departamento) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            // Configuración de parámetros
            pstmt.setString(1, nombre);
            pstmt.setString(2, apellido);
            pstmt.setString(3, cedula);
            pstmt.setString(4, email);
            pstmt.setString(5, departamento);

            // Ejecutar la consulta
            pstmt.executeUpdate();
            System.out.println("Docente registrado exitosamente en la base de datos.");
            return true;

        } catch (SQLException e) {
            System.err.println("Error al insertar el docente: " + e.getMessage());
            return false;
        }
    }
}
