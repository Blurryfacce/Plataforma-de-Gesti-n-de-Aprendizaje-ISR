package Plataforma.Controllers.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Plataforma.Database.DatabaseConnection;
import Plataforma.Models.Docente;

public class DocenteDAO {

    /**
     * Inserta un docente en la base de datos.
     * 
     * @param docente Objeto Docente que contiene los datos a insertar.
     * @return true si el registro fue exitoso, false en caso contrario.
     */
    public static boolean insertarDocente(Docente docente) {
        String sql = "INSERT INTO Docentes (nombre, apellido, cedula, email, departamento, usuario_Docent, clave) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            // Configuración de parámetros
            pstmt.setString(1, docente.getNombre());
            pstmt.setString(2, docente.getApellido());
            pstmt.setString(3, docente.getCedula());
            pstmt.setString(4, docente.getEmail());
            pstmt.setString(5, docente.getDepartamento());
            pstmt.setString(6, docente.getUsuario());
            pstmt.setString(7, docente.getClave());

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
