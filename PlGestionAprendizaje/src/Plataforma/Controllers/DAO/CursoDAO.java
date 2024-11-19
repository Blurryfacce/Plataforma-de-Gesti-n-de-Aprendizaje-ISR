package Plataforma.Controllers.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Plataforma.Database.DatabaseConnection;
import Plataforma.Models.Curso;

public class CursoDAO {
    private static final String INSERT_COURSE_SQL = "INSERT INTO Cursos (nombre, descripcion, duracion, id_Docente)\r\n"
            + //
            "            VALUES (?, ?, ?, ?)";

    public static boolean guardarCurso(String nombre, String descripcion, int duracion, String usuarioDocente) {
        String INSERT_COURSE_SQL = """
                    INSERT INTO Cursos (nombre, descripcion, duracion, id_Docente)
                    VALUES (?, ?, ?, ?)
                """;

        // Obtener el id_Docente a partir del nombre de usuario
        int idDocente = obtenerIdDocente(usuarioDocente);

        // Si no se encontró un docente con el nombre de usuario, retornar false
        if (idDocente == -1) {
            System.out.println("Docente no encontrado.");
            return false;
        }

        try (Connection conn = DatabaseConnection.connect();
                PreparedStatement preparedStatement = conn.prepareStatement(INSERT_COURSE_SQL)) {

            // Establecer los valores en el PreparedStatement
            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, descripcion);
            preparedStatement.setInt(3, duracion);
            preparedStatement.setInt(4, idDocente); // El id_docente que se obtiene del nombre de usuario

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0; // Si se insertaron filas, el curso se guardó correctamente
        } catch (SQLException e) {
            System.out.println("Error al guardar el curso: " + e.getMessage());
            return false;
        }
    }

    private static int obtenerIdDocente(String usuarioDocente) {
        String query = "SELECT id_Docente FROM Docentes WHERE usuario_Docent = ?";
        try (Connection conn = DatabaseConnection.connect();
                PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, usuarioDocente);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("id_Docente"); // Retorna el ID del docente
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener el ID del docente: " + e.getMessage());
        }
        return -1; // Retorna -1 si no se encuentra el docente
    }

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

    public static List<Curso> obtenerCursos() {
        String sql = "SELECT id, nombre, descripcion, duracion, id_Docente FROM Cursos";
        List<Curso> cursos = new ArrayList<>();
        try (Connection conn = DatabaseConnection.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Curso curso = new Curso(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getInt("duracion"));
                cursos.add(curso);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener los cursos: " + e.getMessage());
        }
        return cursos;
    }

    public static Curso obtenerCursoPorId() {
        String sql = "SELECT * FROM Cursos WHERE id = ?";
        try (Connection conn = DatabaseConnection.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Curso(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getInt("id_Docente"));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener el curso por ID: " + e.getMessage());
        }
        return null;
    }

    public static List<Curso> obtenerCursosPorDocente(int idDocente) {
        String sql = "SELECT * FROM Cursos WHERE id_Docente = ?";
        List<Curso> cursos = new ArrayList<>();
        try (Connection conn = DatabaseConnection.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idDocente);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Curso curso = new Curso(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getInt("id_Docente"));
                cursos.add(curso);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener los cursos del docente: " + e.getMessage());
        }
        return cursos;
    }

}