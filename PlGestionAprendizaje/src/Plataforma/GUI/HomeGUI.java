package Plataforma.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Plataforma.GUI.Docente.RegistrarDocenteGUI;

public class HomeGUI extends JFrame {

    public HomeGUI() {
        setTitle("Plataforma de Aprendizaje - Home");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel principal con layout
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panel.setBackground(new Color(30, 30, 30)); // Fondo oscuro

        // Mensaje de bienvenida
        JLabel lblBienvenida = new JLabel("¡Bienvenido a la Plataforma de Aprendizaje!", SwingConstants.CENTER);
        lblBienvenida.setForeground(Color.WHITE);
        lblBienvenida.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(lblBienvenida, BorderLayout.NORTH);

        // Panel central con botones
        JPanel panelBotones = new JPanel(new GridLayout(2, 2, 15, 15));
        panelBotones.setBackground(new Color(30, 30, 30)); // Fondo oscuro

        // Botón de Registro de Estudiante
        JButton btnRegistroEstudiante = new JButton("Registrar Estudiante");
        estilizarBoton(btnRegistroEstudiante);
        btnRegistroEstudiante.addActionListener(e -> {
            new Plataforma.GUI.Estudiante.RegistroEstudianteGUI().setVisible(true);
            dispose(); // Cierra la pantalla actual
        });
        panelBotones.add(btnRegistroEstudiante);

        // Botón de Registro de Docente
        JButton btnRegistroDocente = new JButton("Registrar Docente");
        estilizarBoton(btnRegistroDocente);
        btnRegistroDocente.addActionListener(e -> {
            new RegistrarDocenteGUI().setVisible(true);
            dispose();
        });
        panelBotones.add(btnRegistroDocente);

        // Botón de Inicio de Sesión
        JButton btnIniciarSesion = new JButton("Iniciar Sesión");
        estilizarBoton(btnIniciarSesion);
        btnIniciarSesion.addActionListener(e -> {
            new Plataforma.GUI.LoginGUI().setVisible(true);
            dispose();
        });
        panelBotones.add(btnIniciarSesion);

        // Botón de Entrar a un Curso (puedes vincularlo a un futuro panel)
        JButton btnEntrarCurso = new JButton("Entrar a un Curso");
        estilizarBoton(btnEntrarCurso);
        btnEntrarCurso.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Funcionalidad en desarrollo.", "Información", JOptionPane.INFORMATION_MESSAGE);
        });
        panelBotones.add(btnEntrarCurso);

        panel.add(panelBotones, BorderLayout.CENTER);

        // Footer
        JLabel lblFooter = new JLabel("Plataforma de Aprendizaje © 2024", SwingConstants.CENTER);
        lblFooter.setForeground(Color.GRAY);
        lblFooter.setFont(new Font("Arial", Font.PLAIN, 12));
        panel.add(lblFooter, BorderLayout.SOUTH);

        add(panel);
    }

    /**
     * Método para estilizar botones.
     */
    private void estilizarBoton(JButton boton) {
        boton.setBackground(new Color(45, 45, 45));
        boton.setForeground(Color.WHITE);
        boton.setFont(new Font("Arial", Font.BOLD, 16));
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
    }

}
