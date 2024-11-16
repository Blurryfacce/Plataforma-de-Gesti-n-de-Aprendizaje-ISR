package Plataforma.GUI.Docente;

public class RegistrarDocenteGUI {
    

    private String cedula;
    private String nombre;
    private String especialidad;

    public String getCedula() {
        return cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public RegistrarDocenteGUI(String cedula, String especialidad, String nombre) {
        this.cedula = cedula;
        this.especialidad = especialidad;
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "RegistrarDocenteGUI [cedula=" + cedula + ", nombre=" + nombre + ", especialidad=" + especialidad + "]";
    }
}
