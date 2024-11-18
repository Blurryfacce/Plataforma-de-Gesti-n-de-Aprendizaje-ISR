package Plataforma.Controllers.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Plataforma.Database.DatabaseConnection;
import Plataforma.Models.Estudiante;

public class EstudianteDAO {
    /**
     * Inserta un estudiante en la base de datos
     * @param estudiante
     * @return
     */
    public static boolean insertarEstudiante(Estudiante estudiante) {
        String sql = "INSERT INTO Estudiantes (nombre, apellido, cedula, email, direccion, usuario_Estu, clave) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            // Configuración de parámetros
            pstmt.setString(1, estudiante.getNombre());
            pstmt.setString(2, estudiante.getApellido());
            pstmt.setString(3, estudiante.getCedula());
            pstmt.setString(4, estudiante.getEmail());
            pstmt.setString(5, estudiante.getDireccion());
            pstmt.setString(6, estudiante.getUsuario());
            pstmt.setString(7, estudiante.getClave());

            // Ejecutar la consulta
            pstmt.executeUpdate();
            System.out.println("Estudiante registrado exitosamente en la base de datos.");
            return true;

        } catch (SQLException e) {
            System.err.println("Error al insertar el estudiante: " + e.getMessage());
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
