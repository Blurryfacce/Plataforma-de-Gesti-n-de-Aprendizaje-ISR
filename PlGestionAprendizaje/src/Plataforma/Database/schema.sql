
-- Tabla de estudiantes
CREATE TABLE IF NOT EXISTS Estudiantes(
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre TEXT NOT NULL,
    apellido TEXT NOT NULL,
    cedula TEXT UNIQUE NOT NULL,
    email TEXT NOT NULL,
    direccion TEXT
);
-- Insertar datos 