package Plataforma.GUI.Docente;

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
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import Plataforma.Controllers.Validaciones;
import Plataforma.Controllers.DAO.DocenteDAO;

public class RegistrarDocenteGUI extends JFrame {
    private JTextField txtNombre, txtApellido, txtCedula, txtEmail, txtDepartamento;
    private JButton btnRegistrarDocente;

    public RegistrarDocenteGUI() {
        setTitle("Registro de Docente");
        setSize(500, 400);
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

        add(panel);
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
            JOptionPane.showMessageDialog(this, "El nombre no es válido. No debe contener números.", "Error",JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!Validaciones.validarNombreOApellido(apellido)) {
            JOptionPane.showMessageDialog(this, "El apellido no es válido. No debe contener números.", "Error",JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!Validaciones.validarCedula(cedula)) {
            JOptionPane.showMessageDialog(this, "La cédula debe contener exactamente 10 dígitos.", "Error",JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!Validaciones.validarEmail(email)) {
            JOptionPane.showMessageDialog(this, "El email no es válido. Debe contener '@' y un dominio válido como '.com' o '.edu'.", "Error",JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (departamento.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El campo de departamento no puede estar vacío.", "Error",JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Llamar al método del DAO para insertar en la base de datos
        boolean registrado = DocenteDAO.insertarDocente(nombre, apellido, cedula, email, departamento);

        if (registrado) {
            JOptionPane.showMessageDialog(this, "Docente registrado con éxito.");
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(this, "Error al registrar al docente.");
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
