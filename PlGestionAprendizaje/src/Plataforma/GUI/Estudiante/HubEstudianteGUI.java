package Plataforma.GUI.Estudiante;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import Plataforma.GUI.LoginGUI;

public class HubEstudianteGUI extends JFrame {
    private int idEstudiante;

    public HubEstudianteGUI(int idEstudiante) {
        this.idEstudiante = idEstudiante;

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
        btnRecursos.addActionListener(this::verRecursos);

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

    private void verRecursos(ActionEvent e) {
        String directorioRecursos = "recursos_subidos/";
        File carpeta = new File(directorioRecursos);
        if (!carpeta.exists() || !carpeta.isDirectory()) {
            JOptionPane.showMessageDialog(this, "No se encontraron recursos subidos.", "Información",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        File[] archivos = carpeta.listFiles((dir, name) -> name.toLowerCase().endsWith(".pdf")); // Filtramos solo PDFs
        if (archivos == null || archivos.length == 0) {
            JOptionPane.showMessageDialog(this, "No hay recursos disponibles.", "Información",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // Crear un JList para mostrar los archivos PDF
        DefaultListModel<String> modeloLista = new DefaultListModel<>();
        for (File archivo : archivos) {
            modeloLista.addElement(archivo.getName());
        }

        JList<String> listaArchivos = new JList<>(modeloLista);
        listaArchivos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaArchivos.setVisibleRowCount(10);
        JScrollPane scrollPane = new JScrollPane(listaArchivos);

        // Crear un dialogo con la lista de archivos
        int option = JOptionPane.showOptionDialog(this, scrollPane, "Selecciona un recurso",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
                new Object[] { "Descargar", "Cancelar" }, "Descargar");

        // Si el usuario hace clic en "Descargar"
        if (option == 0) {
            String archivoSeleccionado = listaArchivos.getSelectedValue();
            if (archivoSeleccionado != null) {
                File archivoPDF = new File(directorioRecursos + archivoSeleccionado);
                if (archivoPDF.exists()) {
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setDialogTitle("Selecciona el destino de descarga");
                    fileChooser.setSelectedFile(new File(archivoSeleccionado));
                    int result = fileChooser.showSaveDialog(this);
                    if (result == JFileChooser.APPROVE_OPTION) {
                        File destino = fileChooser.getSelectedFile();
                        try {
                            // Copiar el archivo al destino seleccionado
                            Files.copy(archivoPDF.toPath(), destino.toPath(), StandardCopyOption.REPLACE_EXISTING);
                            JOptionPane.showMessageDialog(this, "Recurso descargado con éxito!");
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(this, "Error al descargar el recurso: " + ex.getMessage(),
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "El archivo no existe.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, selecciona un archivo.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void estilizarBoton(JButton boton) {
        boton.setBackground(new Color(45, 45, 45));
        boton.setForeground(Color.WHITE);
        boton.setFont(new Font("Arial", Font.BOLD, 16));
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
    }
}
