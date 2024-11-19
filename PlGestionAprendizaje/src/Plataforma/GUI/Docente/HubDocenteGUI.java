package Plataforma.GUI.Docente;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class HubDocenteGUI extends JFrame {
    private JButton btnCrearCurso, btnGestionarCursos, btnCerrarSesion;
    private String usuarioDocente;

    public HubDocenteGUI(String nombreDocente) {
        setTitle("Hub del Docente");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Obtener el usuarioDocente logeado
        usuarioDocente = nombreDocente; // Guardar el nombre del docente

        // Panel principal
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Etiqueta de bienvenida
        JLabel lblBienvenida = new JLabel("Bienvenido, " + nombreDocente, SwingConstants.CENTER);
        lblBienvenida.setFont(new Font("SansSerif", Font.BOLD, 20));
        panel.add(lblBienvenida, BorderLayout.NORTH);

        // Panel de botones
        JPanel panelBotones = new JPanel(new GridLayout(4, 1, 10, 10));

        // Botón para Crear Curso
        btnCrearCurso = new JButton("Crear Curso");
        btnCrearCurso.addActionListener(this::abrirCrearCurso);
        estilizarBoton(btnCrearCurso);
        panelBotones.add(btnCrearCurso);

        btnGestionarCursos = new JButton("Gestionar Cursos");
        btnGestionarCursos.addActionListener(this::abrirGestionarCursos);
        estilizarBoton(btnGestionarCursos);
        panelBotones.add(btnGestionarCursos);

        panel.add(panelBotones, BorderLayout.CENTER);
        add(panel);

        // Botón para Cerrar Sesión
        btnCerrarSesion = new JButton("Cerrar Sesión");
        btnCerrarSesion.addActionListener(this::cerrarSesion);
        estilizarBoton(btnCerrarSesion);
        panelBotones.add(btnCerrarSesion);

        panel.add(panelBotones, BorderLayout.CENTER);

        add(panel);
    }

    /**
     * Método para estilizar los botones.
     */
    private void estilizarBoton(JButton boton) {
        boton.setFont(new Font("SansSerif", Font.BOLD, 14));
        boton.setBackground(new Color(33, 150, 243));
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    /**
     * Acción para abrir la ventana de Crear Curso.
     */
    private void abrirCrearCurso(ActionEvent e) {
        if (usuarioDocente == null) {
            JOptionPane.showMessageDialog(this, "No se ha encontrado un docente logueado.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Abrir la ventana para crear un curso, pasando el usuarioDocente
        CrearCursoGUI crearCursoGUI = new CrearCursoGUI(usuarioDocente);
        crearCursoGUI.setVisible(true);
    }

    /**
     * Acción para abrir la ventana de Gestionar Cursos.
     */
    // En la clase HubDocenteGUI, al abrir GestionarCursos
    private void abrirGestionarCursos(ActionEvent e) {
        if (usuarioDocente == null) {
            JOptionPane.showMessageDialog(this, "No se ha encontrado un docente logueado.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Abrir la ventana de GestionarCursosGUI, pasando el usuarioDocente
        GestionarCursosGUI gestionarCursosGUI = new GestionarCursosGUI(usuarioDocente);
        gestionarCursosGUI.setVisible(true);
    }

    /**
     * Acción para cerrar sesión.
     */
    private void cerrarSesion(ActionEvent e) {
        int confirm = JOptionPane.showConfirmDialog(this, "¿Estás seguro de que deseas cerrar sesión?",
                "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            this.dispose();
            // Volver al LoginGUI
            SwingUtilities.invokeLater(() -> new Plataforma.GUI.LoginGUI().setVisible(true));
        }
    }

}
