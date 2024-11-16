package Plataforma.Controllers;

import Plataforma.Models.Estudiante;

public class RegistroEstudianteController {

    public boolean registrarEstudiante(String nombre, String apellido, String cedula, String email, String direccion) {
        // Aquí se podría conectar a una base de datos o guardar el estudiante en memoria.
        if (nombre.isEmpty() || apellido.isEmpty() || cedula.isEmpty() || email.isEmpty() || direccion.isEmpty()) {
            return false; // Indica que el registro no se realizó por datos incompletos
        }

        Estudiante estudiante = new Estudiante(nombre, apellido, cedula, email, direccion);
        System.out.println("Estudiante registrado: " + estudiante); // Simula el registro
        return true;
    }
}
