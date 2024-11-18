package Plataforma.Controllers.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Plataforma.Database.DatabaseConnection;
import Plataforma.Models.Curso;

public class CursoDAO {

    public int obtenerIdDocentePorNombre(String nombreCompleto) {
        String sql = "SELECT id_Docente FROM Docentes WHERE (nombre || ' ' || apellido) = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nombreCompleto);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("id_Docente");
            } else {
                return -1; // Devuelve -1 si no existe
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener el ID del docente: " + e.getMessage());
            return -1;
        }
    }

    public boolean agregarCurso(Curso curso) {
        String sql = "INSERT INTO Curso (nombre, descripcion, id_Docente) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, curso.getNombre());
            pstmt.setString(2, curso.getDescripcion());
            pstmt.setInt(3, curso.getIdDocente());
            pstmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error al agregar el curso: " + e.getMessage());
            return false;
        }
    }

    public boolean cursoExiste(String nombreCurso) {
        String sql = "SELECT COUNT(*) FROM Curso WHERE nombre = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
    
            pstmt.setString(1, nombreCurso);
            ResultSet rs = pstmt.executeQuery();
    
            if (rs.next()) {
                return rs.getInt(1) > 0; // Retorna true si hay al menos un curso con ese nombre
            }
            return false; // Retorna false si no hay ningún curso con ese nombre
    
        } catch (SQLException e) {
            System.out.println("Error al verificar si el curso existe: " + e.getMessage());
            return false;
        }
    }
 


}