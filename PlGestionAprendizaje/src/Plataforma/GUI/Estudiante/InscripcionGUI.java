package Plataforma.GUI.Estudiante;

import java.awt.BorderLayout;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Plataforma.Controllers.DAO.CursoDAO;
import Plataforma.Controllers.DAO.InscripcionDAO;
import Plataforma.Models.Curso;
import Plataforma.Models.Inscripcion;

public class InscripcionGUI extends JFrame {
    private JTable tablaCursos;
    private JButton btnInscribirse;
    private int estudianteId; // Recibido al instanciar la ventana

    public InscripcionGUI(int estudianteId) {
        this.estudianteId = estudianteId;
        setTitle("Inscripción a cursos");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Tabla de cursos
        tablaCursos = new JTable();
        actualizarTablaCursos();

        JScrollPane scrollPane = new JScrollPane(tablaCursos);
        add(scrollPane, BorderLayout.CENTER);

        // Botón para inscribirse
        btnInscribirse = new JButton("Inscribirse");
        btnInscribirse.addActionListener(e -> inscribirseEnCurso());
        add(btnInscribirse, BorderLayout.SOUTH);
    }

    private void actualizarTablaCursos() {
        List<Curso> cursos = CursoDAO.obtenerCursos();
        String[] columnas = {"ID", "Nombre", "Descripción"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);
        for (Curso curso : cursos) {
            modelo.addRow(new Object[]{curso.getId(), curso.getNombre(), curso.getDescripcion()});
        }
        tablaCursos.setModel(modelo);
    }
    private void inscribirseEnCurso() {
        int filaSeleccionada = tablaCursos.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un curso.");
            return;
        }
    
        // No actualizar la tabla antes de comprobar la fila seleccionada
        actualizarTablaCursos();
    
        int cursoId = (int) tablaCursos.getValueAt(filaSeleccionada, 0);
        Inscripcion inscripcion = new Inscripcion(0, estudianteId, cursoId, new java.sql.Date(new Date().getTime()));
        if (InscripcionDAO.registrarInscripcion(inscripcion)) {
            JOptionPane.showMessageDialog(this, "Inscripción registrada. El docente será notificado.");
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Error al registrar la inscripción.");
        }
    }
    
    
}
