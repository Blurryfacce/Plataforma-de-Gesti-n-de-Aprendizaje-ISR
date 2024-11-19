package Plataforma.GUI.Docente;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import Plataforma.Database.DatabaseConnection;

public class GestionarCursosGUI extends JFrame {
    private DefaultTableModel modeloEstudiantes;
    private JTable tablaCursos;
    private JButton btnEditar, btnEliminar, btnVerEstudiantes, btnSubirRecurso, btnVerRecursos, btnAceptarCupo;
    private DefaultTableModel modeloCursos;
    private JTable tablaEstudiantes;

    public GestionarCursosGUI(String usuarioDocente) {

       
        setTitle("Gestionar Cursos");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Panel principal
        JPanel panel = new JPanel(new BorderLayout());

        // Crear tabla de cursos
        modeloCursos = new DefaultTableModel();
        modeloCursos.addColumn("ID");
        modeloCursos.addColumn("Nombre");
        modeloCursos.addColumn("Descripción");
        modeloCursos.addColumn("Duración");
        modeloCursos.addColumn("Número de Estudiantes");

        tablaCursos = new JTable(modeloCursos);
        JScrollPane scrollPane = new JScrollPane(tablaCursos);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Panel de botones
        JPanel panelBotones = new JPanel();
        btnEditar = new JButton("Editar");
        btnEditar.addActionListener(this::editarCurso);
        btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(this::eliminarCurso);
        btnVerEstudiantes = new JButton("Ver Estudiantes");
        btnVerEstudiantes.addActionListener(this::verEstudiantes);

        btnSubirRecurso = new JButton("Subir Recurso");
        btnSubirRecurso.addActionListener(this::subirRecurso);

        btnVerRecursos = new JButton("Ver Recursos");
        btnVerRecursos.addActionListener(this::verRecursos);

    btnAceptarCupo = new JButton("Aceptar Cupo");
    btnAceptarCupo.addActionListener(this::aceptarCupo);



        // Añadir los botones al panel
        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnVerEstudiantes);
        panelBotones.add(btnSubirRecurso);
        panelBotones.add(btnVerRecursos);
        panelBotones.add(btnAceptarCupo);

        panel.add(panelBotones, BorderLayout.SOUTH);

        add(panel);
        cargarCursos();
    }


// Método para cargar cursos de la base de datos
private void cargarCursos() {
    modeloCursos.setRowCount(0); // Limpiar la tabla
    String sql = "SELECT c.id, c.nombre, c.descripcion, c.duracion, " +
            "(SELECT COUNT(*) FROM Inscripciones i WHERE i.curso_id = c.id) AS num_estudiantes " +
            "FROM Cursos c";

    try (Connection conn = DatabaseConnection.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()) {

        while (rs.next()) {
            modeloCursos.addRow(new Object[] {
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("descripcion"),
                    rs.getInt("duracion"),
                    rs.getInt("num_estudiantes") // Columna de número de estudiantes
            });
        }

    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Error al cargar los cursos: " + ex.getMessage(), "Error",
                JOptionPane.ERROR_MESSAGE);
    }
}
private void aceptarCupo(ActionEvent e) {
    int filaSeleccionada = tablaEstudiantes.getSelectedRow();
    if (filaSeleccionada == -1) {
        // Mensaje de advertencia si no se selecciona un estudiante
        JOptionPane.showMessageDialog(this, 
            "Debe seleccionar un estudiante para aceptar el cupo.", 
            "Advertencia", 
            JOptionPane.WARNING_MESSAGE);
        return; // Salir del método si no hay selección
    }

    // Obtener la información del estudiante seleccionado
    String cedulaEstudiante = (String) modeloEstudiantes.getValueAt(filaSeleccionada, 0);
    String nombreEstudiante = (String) modeloEstudiantes.getValueAt(filaSeleccionada, 1);
    String apellidoEstudiante = (String) modeloEstudiantes.getValueAt(filaSeleccionada, 2);

    // Confirmar la acción de aceptar el cupo
    int confirm = JOptionPane.showConfirmDialog(this, 
        "¿Está seguro de que desea aceptar el cupo para el estudiante: " 
        + nombreEstudiante + " " + apellidoEstudiante + "?", 
        "Confirmar aceptación", 
        JOptionPane.YES_NO_OPTION);

    if (confirm == JOptionPane.YES_OPTION) {
        aceptarCupoDeEstudiante(cedulaEstudiante, nombreEstudiante, apellidoEstudiante);
    }
}

private void aceptarCupoDeEstudiante(String cedulaEstudiante, String nombreEstudiante, String apellidoEstudiante) {
    String sql = "UPDATE inscripciones SET estado = 'Aceptado' WHERE estudiante_cedula = ?";
    try (Connection conn = DatabaseConnection.connect();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setString(1, cedulaEstudiante);
        pstmt.executeUpdate();

        // Mostrar mensaje de confirmación al docente
        JOptionPane.showMessageDialog(this, 
            "El cupo del estudiante " + nombreEstudiante + " " + apellidoEstudiante + " ha sido aceptado con éxito.", 
            "Cupo Aceptado", 
            JOptionPane.INFORMATION_MESSAGE);

        // Actualizar la tabla de estudiantes
        verEstudiantes(null);

    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, 
            "Error al aceptar el cupo: " + ex.getMessage(), 
            "Error", 
            JOptionPane.ERROR_MESSAGE);
    }
}


     // Acción para subir recursos
    private void subirRecurso(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File archivo = fileChooser.getSelectedFile();
            String directorioDestino = "recursos_subidos/";
            File carpetaDestino = new File(directorioDestino);
            if (!carpetaDestino.exists()) {
                carpetaDestino.mkdirs(); // Crear carpeta si no existe
            }
            File destino = new File(directorioDestino + archivo.getName());
            try {
                Files.copy(archivo.toPath(), destino.toPath(), StandardCopyOption.REPLACE_EXISTING);
                JOptionPane.showMessageDialog(this, "Recurso subido con éxito!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error al subir el recurso: " + ex.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Acción para ver recursos
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
                    fileChooser.setSelectedFile(archivoPDF);
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

    // Acción para editar un curso
    private void editarCurso(ActionEvent e) {
        int filaSeleccionada = tablaCursos.getSelectedRow();
        if (filaSeleccionada != -1) {

            // Aquí puedes abrir otra ventana para editar el curso
            JOptionPane.showMessageDialog(this, "Funcionalidad para editar aún no implementada.");
        } else {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un curso para editar.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // Acción para eliminar un curso
    private void eliminarCurso(ActionEvent e) {
        int filaSeleccionada = tablaCursos.getSelectedRow();
        if (filaSeleccionada != -1) {
            int idCurso = (int) modeloCursos.getValueAt(filaSeleccionada, 0);

            int confirm = JOptionPane.showConfirmDialog(this,
                    "¿Está seguro que desea eliminar este curso? Los datos no podrán recuperarse.",
                    "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                eliminarCursoDeBaseDeDatos(idCurso);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un curso para eliminar.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para eliminar un curso de la base de datos
    private void eliminarCursoDeBaseDeDatos(int idCurso) {
        String sql = "DELETE FROM cursos WHERE id = ?";
        try (Connection conn = DatabaseConnection.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idCurso);
            pstmt.executeUpdate();

            // Actualizar la tabla después de eliminar
            cargarCursos();

            JOptionPane.showMessageDialog(this, "Curso eliminado con éxito!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al eliminar el curso: " + ex.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void verEstudiantes(ActionEvent e) {
        // Crear la ventana de estudiantes
        JFrame ventanaEstudiantes = new JFrame("Ver Estudiantes");
        ventanaEstudiantes.setSize(600, 400);
        ventanaEstudiantes.setLocationRelativeTo(this);

        // Inicializar modelo de estudiantes si no está inicializado
        if (modeloEstudiantes == null) {
            String[] columnas = { "Cédula", "Nombre", "Apellido", "Email", "Dirección" };
            modeloEstudiantes = new DefaultTableModel(columnas, 0);
        } else {
            modeloEstudiantes.setRowCount(0); // Limpiar tabla para evitar duplicados
        }

        // No crear una nueva tabla, usar la instancia existente
        tablaEstudiantes = new JTable(modeloEstudiantes);

        // Cargar estudiantes en la tabla
        String sql = "SELECT cedula, nombre, apellido, email, direccion FROM estudiantes";
        try (Connection conn = DatabaseConnection.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                modeloEstudiantes.addRow(new Object[] {
                        rs.getString("cedula"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("email"),
                        rs.getString("direccion")
                });
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar los estudiantes: " + ex.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

        // Crear un botón de editar
        JButton btnEditarEstudiante = new JButton("Editar Estudiante");
        btnEditarEstudiante.addActionListener(ev -> editarEstudiante(tablaEstudiantes.getSelectedRow()));
        // Panel para la tabla y el botón
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JScrollPane(tablaEstudiantes), BorderLayout.CENTER);
        panel.add(btnEditarEstudiante, BorderLayout.SOUTH);
        ventanaEstudiantes.add(panel);
        ventanaEstudiantes.setVisible(true);
    }

    private void editarEstudiante(int filaSeleccionada) {
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un estudiante para editar.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Obtener los datos del estudiante seleccionado
        String cedula = (String) modeloEstudiantes.getValueAt(filaSeleccionada, 0);
        String nombre = (String) modeloEstudiantes.getValueAt(filaSeleccionada, 1);
        String apellido = (String) modeloEstudiantes.getValueAt(filaSeleccionada, 2);
        String email = (String) modeloEstudiantes.getValueAt(filaSeleccionada, 3);
        String direccion = (String) modeloEstudiantes.getValueAt(filaSeleccionada, 4);

        // Crear una ventana para editar la información del estudiante
        JPanel panelEdicion = new JPanel(new GridLayout(5, 2));

        JTextField txtNombre = new JTextField(nombre);
        JTextField txtApellido = new JTextField(apellido);
        JTextField txtEmail = new JTextField(email);
        JTextField txtDireccion = new JTextField(direccion);

        panelEdicion.add(new JLabel("Nombre:"));
        panelEdicion.add(txtNombre);
        panelEdicion.add(new JLabel("Apellido:"));
        panelEdicion.add(txtApellido);
        panelEdicion.add(new JLabel("Email:"));
        panelEdicion.add(txtEmail);
        panelEdicion.add(new JLabel("Dirección:"));
        panelEdicion.add(txtDireccion);

        int option = JOptionPane.showConfirmDialog(this, panelEdicion, "Editar Estudiante",
                JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            // Actualizar los datos del estudiante en la base de datos
            String nuevoNombre = txtNombre.getText().trim();
            String nuevoApellido = txtApellido.getText().trim();
            String nuevoEmail = txtEmail.getText().trim();
            String nuevaDireccion = txtDireccion.getText().trim();

            // Realizar la actualización en la base de datos
            String sqlUpdate = "UPDATE estudiantes SET nombre = ?, apellido = ?, email = ?, direccion = ? WHERE cedula = ?";
            try (Connection conn = DatabaseConnection.connect();
                    PreparedStatement pstmt = conn.prepareStatement(sqlUpdate)) {

                pstmt.setString(1, nuevoNombre);
                pstmt.setString(2, nuevoApellido);
                pstmt.setString(3, nuevoEmail);
                pstmt.setString(4, nuevaDireccion);
                pstmt.setString(5, cedula);
                pstmt.executeUpdate();

                JOptionPane.showMessageDialog(this, "Estudiante actualizado con éxito.");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error al actualizar el estudiante: " + ex.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
