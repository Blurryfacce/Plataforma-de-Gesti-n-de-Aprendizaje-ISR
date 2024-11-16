import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.formdev.flatlaf.intellijthemes.FlatDarkFlatIJTheme;

import Plataforma.Database.DatabaseInitializer;
import Plataforma.GUI.HomeGUI;

public class App {

    public static void main(String[] args) {
        // Inicializar la base de datos
        DatabaseInitializer.initializeDatabase();

        // Aplicar el tema de FlatLaf (FlatDarkFlatIJTheme)
        try {
            UIManager.setLookAndFeel(new FlatDarkFlatIJTheme());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Lanzar la interfaz gráfica principal (Login)
        SwingUtilities.invokeLater(() -> {
            new HomeGUI().setVisible(true);
        });
    }
}
