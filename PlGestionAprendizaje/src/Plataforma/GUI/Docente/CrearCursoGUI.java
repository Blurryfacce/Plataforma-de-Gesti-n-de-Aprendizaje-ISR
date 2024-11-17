package Plataforma.GUI.Docente;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

import Plataforma.Controllers.DAO.CursoDAO;
import Plataforma.Models.Curso;

public class CrearCursoGUI extends JFrame {
    private JTextField txtNombre;
    private JTextArea txtDescripcion;
    private JTextField txtDocente;

    public CrearCursoGUI() {
        setTitle("Crear Curso");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Campos de entrada
        JPanel panelCampos = new JPanel(new GridLayout(3, 2, 10, 10));

        JLabel lblNombre = new JLabel("Nombre del Curso:");
        txtNombre = new JTextField();

        JLabel lblDescripcion = new JLabel("Descripción:");
        txtDescripcion = new JTextArea(3, 20);
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setWrapStyleWord(true);

        JLabel lblDocente = new JLabel("id_Docente:");
        txtDocente = new JTextField();

        panelCampos.add(lblNombre);
        panelCampos.add(txtNombre);
        panelCampos.add(lblDescripcion);
        panelCampos.add(new JScrollPane(txtDescripcion));
        panelCampos.add(lblDocente);
        panelCampos.add(txtDocente);

        panel.add(panelCampos, BorderLayout.CENTER);

        // Botón Guardar
        JButton btnGuardar = new JButton("Guardar Curso");
        btnGuardar.addActionListener(this::guardarCurso);
        panel.add(btnGuardar, BorderLayout.SOUTH);

        add(panel);
    }

    private void guardarCurso(ActionEvent e) {
        String nombre = txtNombre.getText().trim();
        String descripcion = txtDescripcion.getText().trim();
        String docente = txtDocente.getText().trim();

        if (nombre.isEmpty() || descripcion.isEmpty() || docente.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Curso curso = new Curso(0, nombre, descripcion, docente);
        CursoDAO cursoDAO = new CursoDAO();

        if (cursoDAO.agregarCurso(curso)) {
            JOptionPane.showMessageDialog(this, "Curso creado exitosamente!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            dispose(); // Cerrar la ventana después de guardar
        } else {
            JOptionPane.showMessageDialog(this, "Error al crear el curso.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}