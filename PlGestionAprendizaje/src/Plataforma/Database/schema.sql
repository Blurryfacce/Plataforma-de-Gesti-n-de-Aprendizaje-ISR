
-- Tabla de estudiantes
CREATE TABLE IF NOT EXISTS Estudiantes(
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre TEXT NOT NULL,
    apellido TEXT NOT NULL,
    cedula TEXT UNIQUE NOT NULL,
    email TEXT NOT NULL,
    direccion TEXT,
    usuario_Estu TEXT NOT NULL,
    clave TEXT NOT NULL
);

-- Tabla de docentes
CREATE TABLE IF NOT EXISTS Docentes (
    id_Docente INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    cedula CHAR(10) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL,
    departamento VARCHAR(100) NOT NULL,
    usuario_Docent TEXT NOT NULL,
    clave TEXT NOT NULL
);
