# Especificaciones

## Separación en Subpaquetes

+ `gui`: Aloja el código relacionado con la interfaz gráfica. Se subdivide según la funcionalidad específica (estudiantes, docentes y comunes).
controllers: Implementa la lógica de negocio que conecta la interfaz gráfica con las operaciones del sistema.
+ `dao`: Contiene el acceso a la base de datos para cada entidad (CRUD).
+ `models`: Define las entidades del sistema (por ejemplo, Estudiante, Docente).
+ `utils`: Provee herramientas útiles, como la conexión a la base de datos o validaciones.

## Archivo de Punto de Entrada (app.java)

Este archivo principal inicia tu aplicación y puede estar en el paquete raíz (com.tuempresa.plataforma).

## Directorios Existentes (lib)

Mantén esta carpeta para las bibliotecas externas que podrías usar, como MySQL Connector para bases de datos o JavaFX para GUI.
