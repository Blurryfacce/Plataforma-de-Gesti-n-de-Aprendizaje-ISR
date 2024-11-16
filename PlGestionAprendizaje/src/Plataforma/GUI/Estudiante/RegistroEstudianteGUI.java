package Plataforma.GUI.Estudiante;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import Plataforma.Controllers.RegistroEstudianteController;

public class RegistroEstudianteGUI extends JFrame {
    private JTextField txtCedula, txtNombre, txtApellido, txtEmail, txtDireccion;
    private JButton btnRegistrarEstudiante;
    private RegistroEstudianteController controller;

    public RegistroEstudianteGUI() {
        setTitle("Registro de Estudiante");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        controller = new RegistroEstudianteController();

        JPanel panel = new JPanel(new GridLayout(6, 2, 5, 5));
        panel.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panel.add(txtNombre);

        panel.add(new JLabel("Apellido:"));
        txtApellido = new JTextField();
        panel.add(txtApellido);

        panel.add(new JLabel("Cédula:"));
        txtCedula = new JTextField();
        panel.add(txtCedula);

        panel.add(new JLabel("Email:"));
        txtEmail = new JTextField();
        panel.add(txtEmail);

        panel.add(new JLabel("Dirección:"));
        txtDireccion = new JTextField();
        panel.add(txtDireccion);

        btnRegistrarEstudiante = new JButton("Registrar");
        btnRegistrarEstudiante.addActionListener(this::registrarEstudiante);
        panel.add(btnRegistrarEstudiante);

        add(panel);
    }

    private void registrarEstudiante(ActionEvent e) {
        String nombre = txtNombre.getText().trim();
        String apellido = txtApellido.getText().trim();
        String cedula = txtCedula.getText().trim();
        String email = txtEmail.getText().trim();
        String direccion = txtDireccion.getText().trim();

        if (nombre.isEmpty() || apellido.isEmpty() || cedula.isEmpty() || email.isEmpty() || direccion.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.");
            return;
        }

        boolean resultado = controller.registrarEstudiante(nombre, apellido, cedula, email, direccion);

        if (resultado) {
            JOptionPane.showMessageDialog(this, "Estudiante registrado con éxito.");
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(this, "Error al registrar al estudiante.");
        }
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtApellido.setText("");
        txtCedula.setText("");
        txtEmail.setText("");
        txtDireccion.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RegistroEstudianteGUI().setVisible(true));
    }
}
