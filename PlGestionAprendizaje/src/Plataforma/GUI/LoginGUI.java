package Plataforma.GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Plataforma.Controllers.LoginController;
import Plataforma.GUI.Docente.HubDocenteGUI;
import Plataforma.GUI.Estudiante.HubEstudianteGUI;

public class LoginGUI extends JFrame {
    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private JButton btnLogin, btnVolver;

    public LoginGUI() {
        setTitle("Iniciar Sesión");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel principal
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setBackground(new Color(30, 30, 30));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Etiqueta y campo de texto para usuario
        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setForeground(Color.WHITE);
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(lblUsuario, gbc);

        txtUsuario = new JTextField();
        estilizarCampo(txtUsuario);
        gbc.gridx = 1; gbc.gridy = 0;
        panel.add(txtUsuario, gbc);

        // Etiqueta y campo de texto para contraseña
        JLabel lblPassword = new JLabel("Contraseña:");
        lblPassword.setForeground(Color.WHITE);
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(lblPassword, gbc);

        txtPassword = new JPasswordField();
        estilizarCampo(txtPassword);
        gbc.gridx = 1; gbc.gridy = 1;
        panel.add(txtPassword, gbc);

        // Botón de iniciar sesión
        btnLogin = new JButton("Iniciar Sesión");
        estilizarBoton(btnLogin);
        btnLogin.addActionListener(this::iniciarSesion);
        gbc.gridx = 0; gbc.gridy = 2;
        gbc.gridwidth = 2;
        panel.add(btnLogin, gbc);

        // Botón de volver
        btnVolver = new JButton("Volver");
        estilizarBoton(btnVolver);
        btnVolver.addActionListener(e -> {
            new HomeGUI().setVisible(true);
            dispose(); // Cierra esta ventana
        });
        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(btnVolver, gbc);

        add(panel);
    }

    /**
     * Método para iniciar sesión.
     */
    private void iniciarSesion(ActionEvent e) {
        String usuario = txtUsuario.getText().trim();
        String contraseña = new String(txtPassword.getPassword());

        // Validación de campos vacíos
        if (usuario.isEmpty() || contraseña.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validación con el controlador
        String rol = LoginController.validarUsuario(usuario, contraseña); // "estudiante", "docente", o null

        if ("docente".equals(rol)) {
            JOptionPane.showMessageDialog(this, "Bienvenido Docente.");
            System.out.println("FALTA DOCENTE");
            new HubDocenteGUI(usuario).setVisible(true); // Abre la interfaz del docente
            dispose();
        } else if ("estudiante".equals(rol)) {
            JOptionPane.showMessageDialog(this, "Bienvenido Estudiante.");
            new HubEstudianteGUI().setVisible(true); // Abre la interfaz del estudiante
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Estiliza un botón.
     */
    private void estilizarBoton(JButton boton) {
        boton.setBackground(new Color(45, 45, 45));
        boton.setForeground(Color.WHITE);
        boton.setFont(new Font("Arial", Font.BOLD, 16));
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
    }

    /**
     * Estiliza un campo de texto.
     */
    private void estilizarCampo(JTextField campo) {
        campo.setBackground(new Color(45, 45, 45));
        campo.setForeground(Color.WHITE);
        campo.setCaretColor(Color.WHITE);
        campo.setFont(new Font("Arial", Font.PLAIN, 14));
        campo.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
    }

    // public static void main(String[] args) {
    //     // Aplica el tema oscuro
    //     try {
    //         UIManager.setLookAndFeel(new FlatDarkFlatIJTheme());
    //     } catch (UnsupportedLookAndFeelException ex) {
    //         ex.printStackTrace();
    //     }

    //     SwingUtilities.invokeLater(() -> new LoginGUI().setVisible(true));
    // }
}
