package Plataforma.GUI.Docente;

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

import Plataforma.Controllers.DAO.CursoDAO;

public class CrearCursoGUI extends JFrame {
    private JTextField txtNombreCurso, txtDescripcion, txtDuracion;
    private JButton btnGuardar, btnCancelar;

    public CrearCursoGUI() {
        setTitle("Crear Curso");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel principal
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(new Color(245, 245, 245));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Etiqueta y campo de texto para el nombre del curso
        JLabel lblNombreCurso = new JLabel("Nombre del Curso:");
        lblNombreCurso.setFont(new Font("SansSerif", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(lblNombreCurso, gbc);

        txtNombreCurso = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(txtNombreCurso, gbc);

        // Etiqueta y campo de texto para la descripción
        JLabel lblDescripcion = new JLabel("Descripción:");
        lblDescripcion.setFont(new Font("SansSerif", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(lblDescripcion, gbc);

        txtDescripcion = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(txtDescripcion, gbc);

        // Etiqueta y campo de texto para la duración
        JLabel lblDuracion = new JLabel("Duración (en horas):");
        lblDuracion.setFont(new Font("SansSerif", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(lblDuracion, gbc);

        txtDuracion = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(txtDuracion, gbc);

        // Botón Guardar
        btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(this::guardarCurso);
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(btnGuardar, gbc);

        // Botón Cancelar
        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> dispose());
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(btnCancelar, gbc);

        add(panel, BorderLayout.CENTER);
    }

    /**
     * Acción para guardar un curso.
     */
    private void guardarCurso(ActionEvent e) {
    String nombre = txtNombreCurso.getText().trim();
    String descripcion = txtDescripcion.getText().trim();
    String duracion = txtDuracion.getText().trim();

    if (nombre.isEmpty() || descripcion.isEmpty() || duracion.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    try {
        int duracionHoras = Integer.parseInt(duracion);

        // Llamar al método de la clase CursoDAO para guardar el curso en la base de datos
        boolean cursoGuardado = CursoDAO.guardarCurso(nombre, descripcion, duracionHoras);

        if (cursoGuardado) {
            JOptionPane.showMessageDialog(this, "Curso guardado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            dispose(); // Cerrar la ventana
        } else {
            JOptionPane.showMessageDialog(this, "Hubo un error al guardar el curso. Intenta nuevamente.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "La duración debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}

}
