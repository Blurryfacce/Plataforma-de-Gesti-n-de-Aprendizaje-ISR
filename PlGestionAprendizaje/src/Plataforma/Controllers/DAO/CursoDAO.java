package Plataforma.Controllers.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Plataforma.Database.DatabaseConnection;
import Plataforma.Models.Curso;

public class CursoDAO {

    public boolean agregarCurso(Curso curso) {
        String sql = "INSERT INTO Curso (nombre, descripcion, Docente id_Docente) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, curso.getNombre());
            pstmt.setString(2, curso.getDescripcion());
            pstmt.setString(3, curso.getid_Docente());
            pstmt.executeUpdate();
            return true;

        } catch (Exception e) {
            System.out.println("Error al agregar el curso: " + e.getMessage());
            return false;
        }
    }

    public List<Curso> obtenerCursos() {
        List<Curso> cursos = new ArrayList<>();
        String sql = "SELECT * FROM cursos";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Curso curso = new Curso(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getString("Docente")
                );
                cursos.add(curso);
            }
        } catch (Exception e) {
            System.out.println("Error al obtener los cursos: " + e.getMessage());
        }

        return cursos;
    }
}
