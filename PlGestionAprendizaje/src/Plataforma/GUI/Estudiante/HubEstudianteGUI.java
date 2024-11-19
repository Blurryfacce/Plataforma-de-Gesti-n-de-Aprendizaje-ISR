package Plataforma.GUI.Estudiante;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Plataforma.GUI.LoginGUI;

public class HubEstudianteGUI extends JFrame {
    private int idEstudiante;

    public HubEstudianteGUI() {
        // this.idEstudiante = idEstudiante;

        setTitle("Hub Estudiante");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(new Color(30, 30, 30));

        // Botones
        JButton btnInscribirse = new JButton("Inscribirse!");
        estilizarBoton(btnInscribirse);
        btnInscribirse.addActionListener(this::abrirInscripcion);

        JButton btnRecursos = new JButton("Ver Recursos");
        estilizarBoton(btnRecursos);

        JButton btnSalir = new JButton("Cerrar Sesión");
        estilizarBoton(btnSalir);

        btnSalir.addActionListener(e -> {
            new LoginGUI().setVisible(true);
            dispose();
        });

        panel.add(btnInscribirse);
        panel.add(btnRecursos);
        panel.add(btnSalir);

        add(panel);
    }

    private void abrirInscripcion(ActionEvent e) {
        // Pasa el idEstudiante al constructor de InscripcionGUI
        new InscripcionGUI(idEstudiante).setVisible(true);
    }

    private void estilizarBoton(JButton boton) {
        boton.setBackground(new Color(45, 45, 45));
        boton.setForeground(Color.WHITE);
        boton.setFont(new Font("Arial", Font.BOLD, 16));
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
    }
}
