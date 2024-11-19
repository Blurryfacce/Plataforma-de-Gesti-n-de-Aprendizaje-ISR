package Plataforma.GUI;

import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.SwingConstants;

import Plataforma.Controllers.LoginController;
import Plataforma.GUI.Docente.HubDocenteGUI;
import Plataforma.GUI.Estudiante.HubEstudianteGUI;

public class LoginGUI extends JFrame {
    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private JButton btnLogin, btnVolver, btnTogglePassword;
    private boolean mostrarPassword = false;

    public LoginGUI() {
        setTitle("Iniciar Sesión");
        setSize(500, 550); // Tamaño ajustado para mayor espacio
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel principal
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(new Color(30, 30, 30));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Etiqueta para el título
        JLabel lblTitulo = new JLabel("Iniciar Sesión");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(lblTitulo, gbc);

        // Etiqueta y campo de texto para usuario
        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setForeground(Color.WHITE);
        lblUsuario.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(lblUsuario, gbc);

        txtUsuario = new JTextField();
        estilizarCampo(txtUsuario);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(txtUsuario, gbc);

        // Etiqueta y campo de texto para contraseña
        JLabel lblPassword = new JLabel("Contraseña:");
        lblPassword.setForeground(Color.WHITE);
        lblPassword.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(lblPassword, gbc);

        txtPassword = new JPasswordField();
        estilizarCampo(txtPassword);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(txtPassword, gbc);

        // Botón para mostrar/ocultar contraseña
        btnTogglePassword = new JButton("Mostrar clave");
        estilizarBoton(btnTogglePassword);
        btnTogglePassword.setPreferredSize(new Dimension(45, 30));
        btnTogglePassword.addActionListener(e -> togglePasswordVisibility());
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST; // Alinea el botón del ojito al lado derecho
        panel.add(btnTogglePassword, gbc);

        // Botón de iniciar sesión
        btnLogin = new JButton("Iniciar Sesión");
        estilizarBoton(btnLogin);
        btnLogin.setFont(new Font("Arial", Font.BOLD, 18));
        btnLogin.addActionListener(this::iniciarSesion);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(btnLogin, gbc);
        getRootPane().setDefaultButton(btnLogin);

        // Botón de volver
        btnVolver = new JButton("Volver");
        estilizarBoton(btnVolver);
        btnVolver.addActionListener(e -> {
            new HomeGUI().setVisible(true);
            dispose(); // Cierra esta ventana
        });
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        panel.add(btnVolver, gbc);

        add(panel);
    }

    /**
     * Método para iniciar sesión.
     */
    private void iniciarSesion(ActionEvent e) {
        String usuario = txtUsuario.getText().trim();
        String clave = new String(txtPassword.getPassword());

        // Validación de campos vacíos
        if (usuario.isEmpty() || clave.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validación con el controlador
        String rol = LoginController.iniciarSesion(usuario, clave); // "Docente", "Estudiante" o null

        if ("Docente".equals(rol)) {
            JOptionPane.showMessageDialog(this, "Bienvenido, Docente.");
            new HubDocenteGUI(usuario).setVisible(true); // Abre la interfaz del docente
            dispose();
        } else if ("Estudiante".equals(rol)) {
            JOptionPane.showMessageDialog(this, "Bienvenido, Estudiante.");
            new HubEstudianteGUI(getDefaultCloseOperation()).setVisible(true); // Abre la interfaz del estudiante
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Alterna la visibilidad de la contraseña.
     */
    private void togglePasswordVisibility() {
        mostrarPassword = !mostrarPassword;
        txtPassword.setEchoChar(mostrarPassword ? '\0' : '•');
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
        campo.setPreferredSize(new Dimension(200, 40));
        campo.setBackground(new Color(45, 45, 45));
        campo.setForeground(Color.WHITE);
        campo.setCaretColor(Color.WHITE);
        campo.setFont(new Font("Arial", Font.PLAIN, 16));
        campo.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
    }

}
