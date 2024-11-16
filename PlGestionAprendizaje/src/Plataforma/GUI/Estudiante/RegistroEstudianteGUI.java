package Plataforma.GUI.Estudiante;

import java.awt.BorderLayout;
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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.formdev.flatlaf.intellijthemes.FlatDarkFlatIJTheme;

import Plataforma.Controllers.DAO.EstudianteDAO;

public class RegistroEstudianteGUI extends JFrame {
    private JTextField txtCedula, txtNombre, txtApellido, txtEmail, txtDireccion;
    private JButton btnRegistrarEstudiante;

    public RegistroEstudianteGUI() {
        setTitle("Registro de Estudiante");
        setSize(450, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Configuración del layout y estilo
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JLabel lblTitulo = new JLabel("Registro de Estudiante", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 18));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        mainPanel.add(lblTitulo, BorderLayout.NORTH);

        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Etiqueta y campo para Nombre
        gbc.gridx = 0; gbc.gridy = 0;
        panelFormulario.add(new JLabel("Nombre:"), gbc);
        txtNombre = new JTextField();
        gbc.gridx = 1; gbc.gridy = 0;
        panelFormulario.add(estilizarCampo(txtNombre), gbc);

        // Etiqueta y campo para Apellido
        gbc.gridx = 0; gbc.gridy = 1;
        panelFormulario.add(new JLabel("Apellido:"), gbc);
        txtApellido = new JTextField();
        gbc.gridx = 1; gbc.gridy = 1;
        panelFormulario.add(estilizarCampo(txtApellido), gbc);

        // Etiqueta y campo para Cédula
        gbc.gridx = 0; gbc.gridy = 2;
        panelFormulario.add(new JLabel("Cédula:"), gbc);
        txtCedula = new JTextField();
        gbc.gridx = 1; gbc.gridy = 2;
        panelFormulario.add(estilizarCampo(txtCedula), gbc);

        // Etiqueta y campo para Email
        gbc.gridx = 0; gbc.gridy = 3;
        panelFormulario.add(new JLabel("Email:"), gbc);
        txtEmail = new JTextField();
        gbc.gridx = 1; gbc.gridy = 3;
        panelFormulario.add(estilizarCampo(txtEmail), gbc);

        // Etiqueta y campo para Dirección
        gbc.gridx = 0; gbc.gridy = 4;
        panelFormulario.add(new JLabel("Dirección:"), gbc);
        txtDireccion = new JTextField();
        gbc.gridx = 1; gbc.gridy = 4;
        panelFormulario.add(estilizarCampo(txtDireccion), gbc);

        // Botón de Registrar
        btnRegistrarEstudiante = new JButton("Registrar Estudiante");
        btnRegistrarEstudiante.addActionListener(this::registrarEstudiante);
        gbc.gridx = 0; gbc.gridy = 5;
        gbc.gridwidth = 2;
        panelFormulario.add(estilizarBoton(btnRegistrarEstudiante), gbc);

        mainPanel.add(panelFormulario, BorderLayout.CENTER);
        add(mainPanel);
    }

    private void registrarEstudiante(ActionEvent e) {
        String nombre = txtNombre.getText().trim();
        String apellido = txtApellido.getText().trim();
        String cedula = txtCedula.getText().trim();
        String email = txtEmail.getText().trim();
        String direccion = txtDireccion.getText().trim();

        if (nombre.isEmpty() || apellido.isEmpty() || cedula.isEmpty() || email.isEmpty() || direccion.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean registrado = EstudianteDAO.insertarEstudiante(nombre, apellido, cedula, email, direccion);

        if (registrado) {
            JOptionPane.showMessageDialog(this, "Estudiante registrado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(this, "Error al registrar al estudiante.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtApellido.setText("");
        txtCedula.setText("");
        txtEmail.setText("");
        txtDireccion.setText("");
    }

    private JTextField estilizarCampo(JTextField campo) {
        campo.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(100, 100, 100), 1),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        return campo;
    }

    private JButton estilizarBoton(JButton boton) {
        boton.setBackground(new Color(50, 50, 50));
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(100, 100, 100), 1),
            BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));
        return boton;
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatDarkFlatIJTheme());
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> new RegistroEstudianteGUI().setVisible(true));
    }
}
