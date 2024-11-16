
import javax.swing.SwingUtilities;

import Plataforma.GUI.Estudiante.RegistroEstudianteGUI;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new RegistroEstudianteGUI().setVisible(true);
        });
    }
}
