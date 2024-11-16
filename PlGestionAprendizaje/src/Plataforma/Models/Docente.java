package Plataforma.Models;

public class Docente {
    
    private String nombreDocente;
    private String apellidoDocente;
    private String cedulaDocente;
    private String telefonoDocente;
    private String direccion;
    private String emailDocente;

    public String getNombreDocente() {
        return nombreDocente;
    }

    public String getApellidoDocente() {
        return apellidoDocente;
    }

    public String getCedulaDocente() {
        return cedulaDocente;
    }

    public String getTelefonoDocente() {
        return telefonoDocente;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getEmailDocente() {
        return emailDocente;
    }

    public Docente(String apellidoDocente, String cedulaDocente, String direccion, String emailDocente, String nombreDocente, String telefonoDocente) {
        this.apellidoDocente = apellidoDocente;
        this.cedulaDocente = cedulaDocente;
        this.direccion = direccion;
        this.emailDocente = emailDocente;
        this.nombreDocente = nombreDocente;
        this.telefonoDocente = telefonoDocente;
    }

    @Override
    public String toString() {
        return "Docente [nombreDocente=" + nombreDocente + ", apellidoDocente=" + apellidoDocente + ", cedulaDocente="
                + cedulaDocente + ", telefonoDocente=" + telefonoDocente + ", direccion=" + direccion
                + ", emailDocente=" + emailDocente + "]";
    }
    
}
