package Plataforma.GUI.Docente;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Plataforma.Controllers.DAO.CursoDAO;
import Plataforma.Models.Curso;

public class CrearCursoGUI extends JFrame {
    private JTextField txtNombreCurso;
    private JTextField txtDescripcion;
    private JTextField txtNombreDocente;
    private JButton btnCrearCurso;

    public CrearCursoGUI() {
        setTitle("Crear Curso");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel lblNombreCurso = new JLabel("Nombre del Curso:");
        lblNombreCurso.setBounds(20, 20, 150, 25);
        add(lblNombreCurso);

        txtNombreCurso = new JTextField();
        txtNombreCurso.setBounds(180, 20, 150, 25);
        add(txtNombreCurso);

        JLabel lblDescripcion = new JLabel("Descripción:");
        lblDescripcion.setBounds(20, 60, 150, 25);
        add(lblDescripcion);

        txtDescripcion = new JTextField();
        txtDescripcion.setBounds(180, 60, 150, 25);
        add(txtDescripcion);

        JLabel lblNombreDocente = new JLabel("Nombre y apellido: ");
        lblNombreDocente.setBounds(20, 100, 150, 25);
        add(lblNombreDocente);

        txtNombreDocente = new JTextField();
        txtNombreDocente.setBounds(180, 100, 150, 25);
        add(txtNombreDocente);

        btnCrearCurso = new JButton("Crear Curso");
        btnCrearCurso.setBounds(120, 150, 150, 30);
        add(btnCrearCurso);

        btnCrearCurso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreCurso = txtNombreCurso.getText();
                String descripcion = txtDescripcion.getText();
                String nombreDocente = txtNombreDocente.getText();
        
                CursoDAO cursoDAO = new CursoDAO();
                int idDocente = cursoDAO.obtenerIdDocentePorNombre(nombreDocente);
        
                if (idDocente == -1) {
                    JOptionPane.showMessageDialog(null, "No hay un docente designado para esta materia.", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (cursoDAO.cursoExiste(nombreCurso)) {
                    JOptionPane.showMessageDialog(null, "Ya existe un curso con ese nombre.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    Curso curso = new Curso();
                    curso.setNombre(nombreCurso);
                    curso.setDescripcion(descripcion);
                    curso.setIdDocente(idDocente);
        
                    if (cursoDAO.agregarCurso(curso)) {
                        JOptionPane.showMessageDialog(null, "Curso creado exitosamente.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al crear el curso.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        

   
    }
}