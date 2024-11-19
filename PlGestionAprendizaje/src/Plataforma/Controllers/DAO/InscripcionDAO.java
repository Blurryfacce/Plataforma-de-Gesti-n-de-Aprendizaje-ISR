package Plataforma.Controllers.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Plataforma.Database.DatabaseConnection;
import Plataforma.Models.Inscripcion;

public class InscripcionDAO {

    public static boolean registrarInscripcion(Inscripcion inscripcion) {
        String sql = "INSERT INTO Inscripciones (estudiante_id, curso_id, fecha_inscripcion, estado) VALUES (?, ?, ?, 'pendiente')";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, inscripcion.getEstudianteId());
            pstmt.setInt(2, inscripcion.getCursoId());
            pstmt.setString(3, new java.text.SimpleDateFormat("yyyy-MM-dd").format(inscripcion.getFechaInscripcion()));
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al registrar la inscripción: " + e.getMessage());
            return false;
        }
    }
    

    public static List<Inscripcion> obtenerInscripcionesPorCurso(int cursoId) {
        List<Inscripcion> inscripciones = new ArrayList<>();
        String sql = "SELECT * FROM Inscripciones WHERE curso_id = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, cursoId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                inscripciones.add(new Inscripcion(
                        rs.getInt("id"),
                        rs.getInt("estudiante_id"),
                        rs.getInt("curso_id"),
                        rs.getDate("fecha_inscripcion")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener las inscripciones: " + e.getMessage());
        }
        return inscripciones;
    }

    public static boolean eliminarInscripcion(int id) {
        String sql = "DELETE FROM Inscripciones WHERE id = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al eliminar la inscripción: " + e.getMessage());
            return false;
        }
    }
}
