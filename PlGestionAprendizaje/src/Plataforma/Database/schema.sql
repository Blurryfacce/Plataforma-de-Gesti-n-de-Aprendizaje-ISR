-- Tabla de estudiantes
CREATE TABLE IF NOT EXISTS Estudiantes (
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

-- Tabla de cursos
CREATE TABLE IF NOT EXISTS Cursos (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre TEXT NOT NULL,
    descripcion TEXT NOT NULL,
    duracion INTEGER NOT NULL,
    id_Docente INTEGER,
    FOREIGN KEY (id_docente) REFERENCES Docentes(id_Docente)
);


-- Tabla de inscripciones (relaciona a los estudiantes con los cursos)
CREATE TABLE IF NOT EXISTS Inscripciones (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    estudiante_id INTEGER NOT NULL,
    curso_id INTEGER NOT NULL,
    fecha_inscripcion TEXT NOT NULL,
    estado TEXT DEFAULT 'Pendiente',
    FOREIGN KEY (estudiante_id) REFERENCES Estudiantes(id),
    FOREIGN KEY (curso_id) REFERENCES Cursos(id)
);

-- Tabla de recursos
CREATE TABLE IF NOT EXISTS Recursos (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre_archivo TEXT NOT NULL,
    archivo BLOB NOT NULL,
    tipo_archivo TEXT NOT NULL,
    fecha_subida TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabla de estudiantes_cursos (relaciona estudiantes con cursos mediante la cédula)
CREATE TABLE IF NOT EXISTS Estudiantes_Cursos (
    id_curso INTEGER NOT NULL,
    cedula_estudiante TEXT NOT NULL,
    PRIMARY KEY (id_curso, cedula_estudiante),
    FOREIGN KEY (id_curso) REFERENCES Cursos(id),
    FOREIGN KEY (cedula_estudiante) REFERENCES Estudiantes(cedula)
);

-- Tabla de cursos_recursos (relaciona a los cursos con los recursos)
CREATE TABLE IF NOT EXISTS Cursos_Recursos (
    id_curso INTEGER NOT NULL,
    id_recurso INTEGER NOT NULL,
    PRIMARY KEY (id_curso, id_recurso),
    FOREIGN KEY (id_curso) REFERENCES Cursos(id),
    FOREIGN KEY (id_recurso) REFERENCES Recursos(id)
);


