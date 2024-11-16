import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.formdev.flatlaf.intellijthemes.FlatDarkFlatIJTheme;

import Plataforma.Database.DatabaseInitializer;
import Plataforma.GUI.Estudiante.RegistroEstudianteGUI;

public class App {
    public static void main(String[] args) {
        // Configurar el tema FlatLaf
        try {
            UIManager.setLookAndFeel(new FlatDarkFlatIJTheme());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        // Inicializar la base de datos
        DatabaseInitializer.initializeDatabase();

        // Lanza la interfaz gráfica
        SwingUtilities.invokeLater(() -> {
            new RegistroEstudianteGUI().setVisible(true);
        });
    }
}
