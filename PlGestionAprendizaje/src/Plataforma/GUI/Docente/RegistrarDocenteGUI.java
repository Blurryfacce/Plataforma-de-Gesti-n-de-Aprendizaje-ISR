package Plataforma.GUI.Docente;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
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
import javax.swing.SwingUtilities;

import Plataforma.Controllers.Validaciones;
import Plataforma.Controllers.DAO.DocenteDAO;
import Plataforma.GUI.HomeGUI;
import Plataforma.GUI.LoginGUI;

public class RegistrarDocenteGUI extends JFrame {
    private JTextField txtNombre, txtApellido, txtCedula, txtEmail, txtDepartamento;
    private JButton btnRegistrarDocente, btnVolver;

    public RegistrarDocenteGUI() {
        setTitle("Registro de Docente");
        setSize(500, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel principal
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Etiqueta y campo para Nombre
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Nombre:"), gbc);
        txtNombre = new JTextField();
        estilizarCampo(txtNombre);
        gbc.gridx = 1; gbc.gridy = 0;
        panel.add(txtNombre, gbc);

        // Etiqueta y campo para Apellido
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Apellido:"), gbc);
        txtApellido = new JTextField();
        estilizarCampo(txtApellido);
        gbc.gridx = 1;
        panel.add(txtApellido, gbc);

        // Etiqueta y campo para Cédula
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Cédula:"), gbc);
        txtCedula = new JTextField();
        estilizarCampo(txtCedula);
        gbc.gridx = 1;
        panel.add(txtCedula, gbc);

        // Etiqueta y campo para Email
        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(new JLabel("Email:"), gbc);
        txtEmail = new JTextField();
        estilizarCampo(txtEmail);
        gbc.gridx = 1;
        panel.add(txtEmail, gbc);

        // Etiqueta y campo para Departamento
        gbc.gridx = 0; gbc.gridy = 4;
        panel.add(new JLabel("Departamento:"), gbc);
        txtDepartamento = new JTextField();
        estilizarCampo(txtDepartamento);
        gbc.gridx = 1;
        panel.add(txtDepartamento, gbc);

        // Botón de Registrar
        btnRegistrarDocente = new JButton("Registrar Docente");
        estilizarBoton(btnRegistrarDocente);
        btnRegistrarDocente.addActionListener(this::registrarDocente);
        gbc.gridx = 0; gbc.gridy = 5;
        gbc.gridwidth = 2;
        panel.add(btnRegistrarDocente, gbc);
        getRootPane().setDefaultButton(btnRegistrarDocente);

        // Botón Volver
        btnVolver = new JButton("< Volver");
        btnVolver.addActionListener(e -> volverAlHome());
        gbc.gridx = 0; gbc.gridy = 6; gbc.gridwidth = 2;
        panel.add(btnVolver, gbc);
        add(panel);
    }        
        
    private void volverAlHome() {
        this.dispose(); //Cierra ventana actual
        new HomeGUI().setVisible(true);
    }

    /**
     * Método para estilizar campos de texto.
     */
    private void estilizarCampo(JTextField campo) {
        campo.setPreferredSize(new Dimension(300, 30));
        campo.setBackground(new Color(30, 30, 30));
        campo.setForeground(Color.WHITE);
        campo.setCaretColor(Color.WHITE);
        campo.setBorder(BorderFactory.createLineBorder(new Color(70, 70, 70)));
        campo.setFont(new Font("Arial", Font.PLAIN, 14));
    }

    /**
     * Método para estilizar botones.
     */
    private void estilizarBoton(JButton boton) {
        boton.setBackground(new Color(45, 45, 45));
        boton.setForeground(Color.WHITE);
        boton.setFont(new Font("Arial", Font.BOLD, 14));
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    }

    /**
     * Método para registrar un docente en la base de datos.
     */
    private void registrarDocente(ActionEvent e) {
        // Obtener datos de los campos de texto
        String nombre = txtNombre.getText().trim();
        String apellido = txtApellido.getText().trim();
        String cedula = txtCedula.getText().trim();
        String email = txtEmail.getText().trim();
        String departamento = txtDepartamento.getText().trim();

        // Validaciones
        if (!Validaciones.validarNombreOApellido(nombre)) {
            JOptionPane.showMessageDialog(this, "El nombre no es válido. No debe contener números.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!Validaciones.validarNombreOApellido(apellido)) {
            JOptionPane.showMessageDialog(this, "El apellido no es válido. No debe contener números.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!Validaciones.validarCedula(cedula)) {
            JOptionPane.showMessageDialog(this, "La cédula debe contener exactamente 10 dígitos.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!Validaciones.validarEmail(email)) {
            JOptionPane.showMessageDialog(this,
                    "El email no es válido. Debe contener '@' y un dominio válido como '.com' o '.edu'.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (departamento.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El campo de departamento no puede estar vacío.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Mostrar panel de diálogo para usuario y contraseña
        JPanel dialogPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        JTextField txtUsuario = new JTextField();
        JPasswordField txtContrasena = new JPasswordField();
        JPasswordField txtConfirmarContrasena = new JPasswordField();

        dialogPanel.add(new JLabel("Usuario:"));
        dialogPanel.add(txtUsuario);
        dialogPanel.add(new JLabel("Contraseña:"));
        dialogPanel.add(txtContrasena);
        dialogPanel.add(new JLabel("Confirmar Contraseña:"));
        dialogPanel.add(txtConfirmarContrasena);

        int option = JOptionPane.showConfirmDialog(this, dialogPanel, "Crear credenciales",
                JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            String usuario = txtUsuario.getText().trim();
            String contrasena = new String(txtContrasena.getPassword());
            String confirmarContrasena = new String(txtConfirmarContrasena.getPassword());

            // Validar credenciales
            if (usuario.isEmpty() || contrasena.isEmpty() || confirmarContrasena.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos los campos de credenciales son obligatorios.");
                return;
            }

            if (!contrasena.equals(confirmarContrasena)) {
                JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden.");
                return;
            }

            try {
                boolean registrado = DocenteDAO.insertarDocente(nombre, apellido, cedula, email, departamento,
                        usuario, contrasena);
                if (registrado) {
                    JOptionPane.showMessageDialog(this, "Docente registrado con éxito.");
                    this.limpiarCampos();
                    this.dispose();
                    new LoginGUI().setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, "Error al registrar al docente. Intente nuevamente.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Ocurrió un error: " + ex.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace(); // Imprime el error en la consola
            }

        }
    }

    /**
     * Método para limpiar los campos después de un registro exitoso.
     */
    private void limpiarCampos() {
        txtNombre.setText("");
        txtApellido.setText("");
        txtCedula.setText("");
        txtEmail.setText("");
        txtDepartamento.setText("");
    }

    /**
     * Método principal para pruebas.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RegistrarDocenteGUI().setVisible(true));
    }
}
