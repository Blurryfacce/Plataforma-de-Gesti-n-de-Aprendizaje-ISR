
import javax.swing.SwingUtilities;

import Plataforma.Database.DatabaseInitializer;
import Plataforma.GUI.Estudiante.RegistroEstudianteGUI;

public class App {
    public static void main(String[] args) {
        // Inicializar la base de datos
        DatabaseInitializer.initializeDatabase();

        // Lanza la interfaz grafica
        SwingUtilities.invokeLater(() -> {
            new RegistroEstudianteGUI().setVisible(true);
        });
    }
}
