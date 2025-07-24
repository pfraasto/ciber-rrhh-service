-- ====================================
-- SISTEMA DE RECURSOS HUMANOS - MySQL
-- Base de datos con campos de auditoría y sistema de accesos
-- ====================================

-- Crear la base de datos
CREATE DATABASE IF NOT EXISTS rrhh_system;
USE rrhh_system;

-- ====================================
-- TABLAS DE SISTEMA DE ACCESOS
-- ====================================

-- Tabla de usuarios del sistema
CREATE TABLE usuarios (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          username VARCHAR(50) UNIQUE NOT NULL,
                          email VARCHAR(100) UNIQUE NOT NULL,
                          password_hash VARCHAR(255) NOT NULL,
                          nombre VARCHAR(100) NOT NULL,
                          apellido VARCHAR(100) NOT NULL,
                          activo BOOLEAN DEFAULT TRUE,
                          ultimo_acceso TIMESTAMP NULL,
    -- Campos de auditoría
                          fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          usuario_creacion VARCHAR(50) NOT NULL,
                          fecha_actualizacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                          usuario_actualizacion VARCHAR(50) NOT NULL
);

-- Tabla de roles
CREATE TABLE roles (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       nombre VARCHAR(50) UNIQUE NOT NULL,
                       descripcion TEXT,
                       activo BOOLEAN DEFAULT TRUE,
    -- Campos de auditoría
                       fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       usuario_creacion VARCHAR(50) NOT NULL,
                       fecha_actualizacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                       usuario_actualizacion VARCHAR(50) NOT NULL
);

-- Tabla de relación usuario-rol
CREATE TABLE usuario_roles (
                               id INT AUTO_INCREMENT PRIMARY KEY,
                               usuario_id INT NOT NULL,
                               rol_id INT NOT NULL,
                               activo BOOLEAN DEFAULT TRUE,
    -- Campos de auditoría
                               fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                               usuario_creacion VARCHAR(50) NOT NULL,
                               fecha_actualizacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                               usuario_actualizacion VARCHAR(50) NOT NULL,
                               FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE,
                               FOREIGN KEY (rol_id) REFERENCES roles(id) ON DELETE CASCADE,
                               UNIQUE KEY unique_usuario_rol (usuario_id, rol_id)
);

-- ====================================
-- TABLAS DE RECURSOS HUMANOS
-- ====================================

-- Tabla de departamentos
CREATE TABLE departamentos (
                               id INT AUTO_INCREMENT PRIMARY KEY,
                               nombre VARCHAR(100) NOT NULL,
                               descripcion TEXT,
                               codigo VARCHAR(10) UNIQUE NOT NULL,
                               presupuesto DECIMAL(12,2),
                               activo BOOLEAN DEFAULT TRUE,
    -- Campos de auditoría
                               fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                               usuario_creacion VARCHAR(50) NOT NULL,
                               fecha_actualizacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                               usuario_actualizacion VARCHAR(50) NOT NULL
);

-- Tabla de cargos
CREATE TABLE cargos (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        nombre VARCHAR(100) NOT NULL,
                        descripcion TEXT,
                        nivel_jerarquico INT,
                        salario_base DECIMAL(10,2),
                        departamento_id INT,
                        activo BOOLEAN DEFAULT TRUE,
    -- Campos de auditoría
                        fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        usuario_creacion VARCHAR(50) NOT NULL,
                        fecha_actualizacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                        usuario_actualizacion VARCHAR(50) NOT NULL,
                        FOREIGN KEY (departamento_id) REFERENCES departamentos(id) ON DELETE SET NULL
);

-- Tabla de empleados
CREATE TABLE empleados (
                           id INT AUTO_INCREMENT PRIMARY KEY,
                           numero_empleado VARCHAR(20) UNIQUE NOT NULL,
                           nombres VARCHAR(100) NOT NULL,
                           apellidos VARCHAR(100) NOT NULL,
                           documento_identidad VARCHAR(20) UNIQUE NOT NULL,
                           tipo_documento ENUM('DNI', 'PASAPORTE', 'CARNET_EXTRANJERIA') NOT NULL,
                           fecha_nacimiento DATE,
                           genero ENUM('M', 'F', 'OTRO'),
                           estado_civil ENUM('SOLTERO', 'CASADO', 'DIVORCIADO', 'VIUDO'),
                           telefono VARCHAR(20),
                           email_personal VARCHAR(100),
                           email_corporativo VARCHAR(100),
                           direccion TEXT,
                           fecha_ingreso DATE NOT NULL,
                           fecha_salida DATE NULL,
                           estado ENUM('ACTIVO', 'INACTIVO', 'SUSPENDIDO', 'TERMINADO') DEFAULT 'ACTIVO',
                           cargo_id INT,
                           departamento_id INT,
                           supervisor_id INT,
    -- Campos de auditoría
                           fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                           usuario_creacion VARCHAR(50) NOT NULL,
                           fecha_actualizacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                           usuario_actualizacion VARCHAR(50) NOT NULL,
                           FOREIGN KEY (cargo_id) REFERENCES cargos(id) ON DELETE SET NULL,
                           FOREIGN KEY (departamento_id) REFERENCES departamentos(id) ON DELETE SET NULL,
                           FOREIGN KEY (supervisor_id) REFERENCES empleados(id) ON DELETE SET NULL
);

-- Tabla de salarios
CREATE TABLE salarios (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          empleado_id INT NOT NULL,
                          salario_base DECIMAL(10,2) NOT NULL,
                          bonificaciones DECIMAL(10,2) DEFAULT 0,
                          deducciones DECIMAL(10,2) DEFAULT 0,
                          salario_neto DECIMAL(10,2) NOT NULL,
                          fecha_inicio DATE NOT NULL,
                          fecha_fin DATE NULL,
                          activo BOOLEAN DEFAULT TRUE,
                          observaciones TEXT,
    -- Campos de auditoría
                          fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          usuario_creacion VARCHAR(50) NOT NULL,
                          fecha_actualizacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                          usuario_actualizacion VARCHAR(50) NOT NULL,
                          FOREIGN KEY (empleado_id) REFERENCES empleados(id) ON DELETE CASCADE
);

-- Tabla de proyectos
CREATE TABLE proyectos (
                           id INT AUTO_INCREMENT PRIMARY KEY,
                           nombre VARCHAR(200) NOT NULL,
                           descripcion TEXT,
                           codigo VARCHAR(20) UNIQUE NOT NULL,
                           fecha_inicio DATE NOT NULL,
                           fecha_fin_estimada DATE,
                           fecha_fin_real DATE NULL,
                           presupuesto DECIMAL(12,2),
                           estado ENUM('PLANIFICACION', 'EN_PROGRESO', 'PAUSADO', 'COMPLETADO', 'CANCELADO') DEFAULT 'PLANIFICACION',
                           responsable_id INT,
                           departamento_id INT,
    -- Campos de auditoría
                           fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                           usuario_creacion VARCHAR(50) NOT NULL,
                           fecha_actualizacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                           usuario_actualizacion VARCHAR(50) NOT NULL,
                           FOREIGN KEY (responsable_id) REFERENCES empleados(id) ON DELETE SET NULL,
                           FOREIGN KEY (departamento_id) REFERENCES departamentos(id) ON DELETE SET NULL
);

-- Tabla de asignaciones de empleados a proyectos
CREATE TABLE asignaciones_proyecto (
                                       id INT AUTO_INCREMENT PRIMARY KEY,
                                       empleado_id INT NOT NULL,
                                       proyecto_id INT NOT NULL,
                                       fecha_asignacion DATE NOT NULL,
                                       fecha_fin_asignacion DATE NULL,
                                       rol_proyecto VARCHAR(100),
                                       horas_asignadas INT,
                                       activo BOOLEAN DEFAULT TRUE,
    -- Campos de auditoría
                                       fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                       usuario_creacion VARCHAR(50) NOT NULL,
                                       fecha_actualizacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                       usuario_actualizacion VARCHAR(50) NOT NULL,
                                       FOREIGN KEY (empleado_id) REFERENCES empleados(id) ON DELETE CASCADE,
                                       FOREIGN KEY (proyecto_id) REFERENCES proyectos(id) ON DELETE CASCADE
);

-- Tabla de evaluaciones de desempeño
CREATE TABLE evaluaciones (
                              id INT AUTO_INCREMENT PRIMARY KEY,
                              empleado_id INT NOT NULL,
                              evaluador_id INT NOT NULL,
                              periodo_inicio DATE NOT NULL,
                              periodo_fin DATE NOT NULL,
                              puntaje_total DECIMAL(5,2),
                              comentarios TEXT,
                              objetivos_cumplidos TEXT,
                              areas_mejora TEXT,
                              estado ENUM('PENDIENTE', 'EN_PROCESO', 'COMPLETADA', 'APROBADA') DEFAULT 'PENDIENTE',
    -- Campos de auditoría
                              fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                              usuario_creacion VARCHAR(50) NOT NULL,
                              fecha_actualizacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                              usuario_actualizacion VARCHAR(50) NOT NULL,
                              FOREIGN KEY (empleado_id) REFERENCES empleados(id) ON DELETE CASCADE,
                              FOREIGN KEY (evaluador_id) REFERENCES empleados(id) ON DELETE CASCADE
);

-- ====================================
-- ÍNDICES PARA OPTIMIZACIÓN
-- ====================================

-- Índices para usuarios
CREATE INDEX idx_usuarios_username ON usuarios(username);
CREATE INDEX idx_usuarios_email ON usuarios(email);
CREATE INDEX idx_usuarios_activo ON usuarios(activo);

-- Índices para empleados
CREATE INDEX idx_empleados_numero ON empleados(numero_empleado);
CREATE INDEX idx_empleados_documento ON empleados(documento_identidad);
CREATE INDEX idx_empleados_estado ON empleados(estado);
CREATE INDEX idx_empleados_cargo ON empleados(cargo_id);
CREATE INDEX idx_empleados_departamento ON empleados(departamento_id);
CREATE INDEX idx_empleados_supervisor ON empleados(supervisor_id);

-- Índices para salarios
CREATE INDEX idx_salarios_empleado ON salarios(empleado_id);
CREATE INDEX idx_salarios_activo ON salarios(activo);
CREATE INDEX idx_salarios_fecha_inicio ON salarios(fecha_inicio);

-- Índices para proyectos
CREATE INDEX idx_proyectos_codigo ON proyectos(codigo);
CREATE INDEX idx_proyectos_estado ON proyectos(estado);
CREATE INDEX idx_proyectos_responsable ON proyectos(responsable_id);

-- ====================================
-- DATOS DE PRUEBA
-- ====================================

-- Insertar roles
INSERT INTO roles (nombre, descripcion, usuario_creacion, usuario_actualizacion) VALUES
                                                                                     ('ADMIN', 'Administrador del sistema', 'system', 'system'),
                                                                                     ('RRHH', 'Recursos Humanos', 'system', 'system'),
                                                                                     ('GERENTE', 'Gerente de área', 'system', 'system'),
                                                                                     ('SUPERVISOR', 'Supervisor de equipo', 'system', 'system'),
                                                                                     ('EMPLEADO', 'Empleado estándar', 'system', 'system');

-- Insertar usuarios del sistema
INSERT INTO usuarios (username, email, password_hash, nombre, apellido, usuario_creacion, usuario_actualizacion) VALUES
                                                                                                                     ('admin', 'admin@empresa.com', '$2b$12$LQv3c1yqBWVHxkd0LHAkCOYz6TtxMQJqhN8/LeqgJ4J6jT6/pCKRG', 'Admin', 'Sistema', 'system', 'system'),
                                                                                                                     ('rrhh_manager', 'rrhh@empresa.com', '$2b$12$LQv3c1yqBWVHxkd0LHAkCOYz6TtxMQJqhN8/LeqgJ4J6jT6/pCKRG', 'Ana', 'García', 'admin', 'admin'),
                                                                                                                     ('gerente_ti', 'gerente.ti@empresa.com', '$2b$12$LQv3c1yqBWVHxkd0LHAkCOYz6TtxMQJqhN8/LeqgJ4J6jT6/pCKRG', 'Carlos', 'Mendoza', 'admin', 'admin'),
                                                                                                                     ('supervisor_ventas', 'supervisor.ventas@empresa.com', '$2b$12$LQv3c1yqBWVHxkd0LHAkCOYz6TtxMQJqhN8/LeqgJ4J6jT6/pCKRG', 'María', 'López', 'admin', 'admin');

-- Asignar roles a usuarios
INSERT INTO usuario_roles (usuario_id, rol_id, usuario_creacion, usuario_actualizacion) VALUES
                                                                                            (1, 1, 'system', 'system'), -- admin -> ADMIN
                                                                                            (2, 2, 'admin', 'admin'),   -- rrhh_manager -> RRHH
                                                                                            (3, 3, 'admin', 'admin'),   -- gerente_ti -> GERENTE
                                                                                            (4, 4, 'admin', 'admin');   -- supervisor_ventas -> SUPERVISOR

-- Insertar departamentos
INSERT INTO departamentos (nombre, descripcion, codigo, presupuesto, usuario_creacion, usuario_actualizacion) VALUES
                                                                                                                  ('Tecnología', 'Departamento de Tecnología e Informática', 'TI', 500000.00, 'admin', 'admin'),
                                                                                                                  ('Ventas', 'Departamento de Ventas y Marketing', 'VEN', 300000.00, 'admin', 'admin'),
                                                                                                                  ('Recursos Humanos', 'Departamento de Recursos Humanos', 'RRHH', 200000.00, 'admin', 'admin'),
                                                                                                                  ('Finanzas', 'Departamento de Finanzas y Contabilidad', 'FIN', 250000.00, 'admin', 'admin'),
                                                                                                                  ('Operaciones', 'Departamento de Operaciones', 'OPE', 400000.00, 'admin', 'admin');

-- Insertar cargos
INSERT INTO cargos (nombre, descripcion, nivel_jerarquico, salario_base, departamento_id, usuario_creacion, usuario_actualizacion) VALUES
                                                                                                                                       ('Gerente General', 'Gerente General de la empresa', 1, 15000.00, NULL, 'admin', 'admin'),
                                                                                                                                       ('Gerente de TI', 'Gerente del departamento de TI', 2, 12000.00, 1, 'admin', 'admin'),
                                                                                                                                       ('Gerente de Ventas', 'Gerente del departamento de Ventas', 2, 11000.00, 2, 'admin', 'admin'),
                                                                                                                                       ('Gerente de RRHH', 'Gerente del departamento de RRHH', 2, 10000.00, 3, 'admin', 'admin'),
                                                                                                                                       ('Gerente de Finanzas', 'Gerente del departamento de Finanzas', 2, 10500.00, 4, 'admin', 'admin'),
                                                                                                                                       ('Desarrollador Senior', 'Desarrollador Senior de Software', 3, 8000.00, 1, 'admin', 'admin'),
                                                                                                                                       ('Desarrollador Junior', 'Desarrollador Junior de Software', 4, 5000.00, 1, 'admin', 'admin'),
                                                                                                                                       ('Analista de Sistemas', 'Analista de Sistemas', 3, 7000.00, 1, 'admin', 'admin'),
                                                                                                                                       ('Vendedor Senior', 'Vendedor Senior', 3, 6000.00, 2, 'admin', 'admin'),
                                                                                                                                       ('Vendedor Junior', 'Vendedor Junior', 4, 3500.00, 2, 'admin', 'admin'),
                                                                                                                                       ('Analista de RRHH', 'Analista de Recursos Humanos', 3, 5500.00, 3, 'admin', 'admin'),
                                                                                                                                       ('Contador', 'Contador', 3, 6500.00, 4, 'admin', 'admin'),
                                                                                                                                       ('Asistente Contable', 'Asistente Contable', 4, 4000.00, 4, 'admin', 'admin'),
                                                                                                                                       ('Supervisor de Operaciones', 'Supervisor de Operaciones', 3, 7500.00, 5, 'admin', 'admin'),
                                                                                                                                       ('Operario', 'Operario de producción', 4, 3000.00, 5, 'admin', 'admin');

-- Insertar empleados
INSERT INTO empleados (numero_empleado, nombres, apellidos, documento_identidad, tipo_documento, fecha_nacimiento, genero, estado_civil, telefono, email_personal, email_corporativo, direccion, fecha_ingreso, cargo_id, departamento_id, supervisor_id, usuario_creacion, usuario_actualizacion) VALUES
                                                                                                                                                                                                                                                                                                       ('EMP001', 'Juan Carlos', 'Rodríguez Pérez', '12345678', 'DNI', '1985-03-15', 'M', 'CASADO', '999123456', 'juan.rodriguez@personal.com', 'juan.rodriguez@empresa.com', 'Av. Principal 123, Lima', '2020-01-15', 1, NULL, NULL, 'admin', 'admin'),
                                                                                                                                                                                                                                                                                                       ('EMP002', 'Carlos Alberto', 'Mendoza Silva', '23456789', 'DNI', '1982-07-22', 'M', 'SOLTERO', '999234567', 'carlos.mendoza@personal.com', 'carlos.mendoza@empresa.com', 'Calle Secundaria 456, Lima', '2020-02-01', 2, 1, 1, 'admin', 'admin'),
                                                                                                                                                                                                                                                                                                       ('EMP003', 'María Elena', 'López García', '34567890', 'DNI', '1990-11-08', 'F', 'CASADO', '999345678', 'maria.lopez@personal.com', 'maria.lopez@empresa.com', 'Jr. Tercera 789, Lima', '2020-03-10', 3, 2, 1, 'admin', 'admin'),
                                                                                                                                                                                                                                                                                                       ('EMP004', 'Ana Patricia', 'García Fernández', '45678901', 'DNI', '1987-05-30', 'F', 'SOLTERO', '999456789', 'ana.garcia@personal.com', 'ana.garcia@empresa.com', 'Av. Cuarta 321, Lima', '2020-04-05', 4, 3, 1, 'admin', 'admin'),
                                                                                                                                                                                                                                                                                                       ('EMP005', 'Roberto', 'Vargas Morales', '56789012', 'DNI', '1983-09-18', 'M', 'DIVORCIADO', '999567890', 'roberto.vargas@personal.com', 'roberto.vargas@empresa.com', 'Calle Quinta 654, Lima', '2020-05-12', 5, 4, 1, 'admin', 'admin'),
                                                                                                                                                                                                                                                                                                       ('EMP006', 'Luis Fernando', 'Martínez Ruiz', '67890123', 'DNI', '1992-01-25', 'M', 'SOLTERO', '999678901', 'luis.martinez@personal.com', 'luis.martinez@empresa.com', 'Jr. Sexta 987, Lima', '2020-06-20', 6, 1, 2, 'admin', 'admin'),
                                                                                                                                                                                                                                                                                                       ('EMP007', 'Patricia', 'Sánchez Torres', '78901234', 'DNI', '1994-04-12', 'F', 'SOLTERO', '999789012', 'patricia.sanchez@personal.com', 'patricia.sanchez@empresa.com', 'Av. Séptima 147, Lima', '2020-07-15', 7, 1, 2, 'admin', 'admin'),
                                                                                                                                                                                                                                                                                                       ('EMP008', 'Diego', 'Ramírez Castro', '89012345', 'DNI', '1991-08-07', 'M', 'CASADO', '999890123', 'diego.ramirez@personal.com', 'diego.ramirez@empresa.com', 'Calle Octava 258, Lima', '2020-08-10', 8, 1, 2, 'admin', 'admin'),
                                                                                                                                                                                                                                                                                                       ('EMP009', 'Carmen', 'Flores Herrera', '90123456', 'DNI', '1989-12-03', 'F', 'CASADO', '999901234', 'carmen.flores@personal.com', 'carmen.flores@empresa.com', 'Jr. Novena 369, Lima', '2020-09-05', 9, 2, 3, 'admin', 'admin'),
                                                                                                                                                                                                                                                                                                       ('EMP010', 'Andrés', 'Gutiérrez Vega', '01234567', 'DNI', '1995-06-28', 'M', 'SOLTERO', '999012345', 'andres.gutierrez@personal.com', 'andres.gutierrez@empresa.com', 'Av. Décima 741, Lima', '2020-10-01', 10, 2, 3, 'admin', 'admin'),
                                                                                                                                                                                                                                                                                                       ('EMP011', 'Sofía', 'Morales Jiménez', '11234567', 'DNI', '1988-02-14', 'F', 'SOLTERO', '999112345', 'sofia.morales@personal.com', 'sofia.morales@empresa.com', 'Calle Once 852, Lima', '2020-11-15', 11, 3, 4, 'admin', 'admin'),
                                                                                                                                                                                                                                                                                                       ('EMP012', 'Fernando', 'Castro Rojas', '22345678', 'DNI', '1986-10-20', 'M', 'CASADO', '999223456', 'fernando.castro@personal.com', 'fernando.castro@empresa.com', 'Jr. Doce 963, Lima', '2020-12-01', 12, 4, 5, 'admin', 'admin'),
                                                                                                                                                                                                                                                                                                       ('EMP013', 'Lucía', 'Herrera Mendoza', '33456789', 'DNI', '1993-07-11', 'F', 'SOLTERO', '999334567', 'lucia.herrera@personal.com', 'lucia.herrera@empresa.com', 'Av. Trece 159, Lima', '2021-01-10', 13, 4, 5, 'admin', 'admin'),
                                                                                                                                                                                                                                                                                                       ('EMP014', 'Miguel', 'Vega Paredes', '44567890', 'DNI', '1984-04-16', 'M', 'CASADO', '999445678', 'miguel.vega@personal.com', 'miguel.vega@empresa.com', 'Calle Catorce 357, Lima', '2021-02-05', 14, 5, 1, 'admin', 'admin'),
                                                                                                                                                                                                                                                                                                       ('EMP015', 'Gabriela', 'Paredes Moreno', '55678901', 'DNI', '1996-09-23', 'F', 'SOLTERO', '999556789', 'gabriela.paredes@personal.com', 'gabriela.paredes@empresa.com', 'Jr. Quince 468, Lima', '2021-03-01', 15, 5, 14, 'admin', 'admin');

-- Insertar salarios actuales
INSERT INTO salarios (empleado_id, salario_base, bonificaciones, deducciones, salario_neto, fecha_inicio, usuario_creacion, usuario_actualizacion) VALUES
                                                                                                                                                       (1, 15000.00, 2000.00, 500.00, 16500.00, '2020-01-15', 'admin', 'admin'),
                                                                                                                                                       (2, 12000.00, 1500.00, 400.00, 13100.00, '2020-02-01', 'admin', 'admin'),
                                                                                                                                                       (3, 11000.00, 1200.00, 350.00, 11850.00, '2020-03-10', 'admin', 'admin'),
                                                                                                                                                       (4, 10000.00, 1000.00, 300.00, 10700.00, '2020-04-05', 'admin', 'admin'),
                                                                                                                                                       (5, 10500.00, 1100.00, 320.00, 11280.00, '2020-05-12', 'admin', 'admin'),
                                                                                                                                                       (6, 8000.00, 800.00, 250.00, 8550.00, '2020-06-20', 'admin', 'admin'),
                                                                                                                                                       (7, 5000.00, 300.00, 150.00, 5150.00, '2020-07-15', 'admin', 'admin'),
                                                                                                                                                       (8, 7000.00, 500.00, 200.00, 7300.00, '2020-08-10', 'admin', 'admin'),
                                                                                                                                                       (9, 6000.00, 400.00, 180.00, 6220.00, '2020-09-05', 'admin', 'admin'),
                                                                                                                                                       (10, 3500.00, 200.00, 100.00, 3600.00, '2020-10-01', 'admin', 'admin'),
                                                                                                                                                       (11, 5500.00, 350.00, 160.00, 5690.00, '2020-11-15', 'admin', 'admin'),
                                                                                                                                                       (12, 6500.00, 450.00, 190.00, 6760.00, '2020-12-01', 'admin', 'admin'),
                                                                                                                                                       (13, 4000.00, 250.00, 120.00, 4130.00, '2021-01-10', 'admin', 'admin'),
                                                                                                                                                       (14, 7500.00, 600.00, 220.00, 7880.00, '2021-02-05', 'admin', 'admin'),
                                                                                                                                                       (15, 3000.00, 150.00, 90.00, 3060.00, '2021-03-01', 'admin', 'admin');

-- Insertar proyectos
INSERT INTO proyectos (nombre, descripcion, codigo, fecha_inicio, fecha_fin_estimada, presupuesto, estado, responsable_id, departamento_id, usuario_creacion, usuario_actualizacion) VALUES
                                                                                                                                                                                         ('Sistema ERP', 'Implementación de sistema ERP para toda la empresa', 'PROJ001', '2024-01-15', '2024-12-31', 150000.00, 'EN_PROGRESO', 2, 1, 'admin', 'admin'),
                                                                                                                                                                                         ('Campaña Marketing Q1', 'Campaña de marketing para el primer trimestre', 'PROJ002', '2024-01-01', '2024-03-31', 50000.00, 'COMPLETADO', 3, 2, 'admin', 'admin'),
                                                                                                                                                                                         ('Digitalización RRHH', 'Digitalización de procesos de recursos humanos', 'PROJ003', '2024-02-01', '2024-08-31', 80000.00, 'EN_PROGRESO', 4, 3, 'admin', 'admin'),
                                                                                                                                                                                         ('Automatización Financiera', 'Automatización de procesos financieros', 'PROJ004', '2024-03-01', '2024-10-31', 120000.00, 'PLANIFICACION', 5, 4, 'admin', 'admin'),
                                                                                                                                                                                         ('Optimización Operaciones', 'Optimización de procesos operacionales', 'PROJ005', '2024-04-01', '2024-11-30', 100000.00, 'PLANIFICACION', 14, 5, 'admin', 'admin');

-- Insertar asignaciones a proyectos
INSERT INTO asignaciones_proyecto (empleado_id, proyecto_id, fecha_asignacion, rol_proyecto, horas_asignadas, usuario_creacion, usuario_actualizacion) VALUES
                                                                                                                                                           (2, 1, '2024-01-15', 'Project Manager', 160, 'admin', 'admin'),
                                                                                                                                                           (6, 1, '2024-01-20', 'Desarrollador Senior', 160, 'admin', 'admin'),
                                                                                                                                                           (7, 1, '2024-01-25', 'Desarrollador Junior', 160, 'admin', 'admin'),
                                                                                                                                                           (8, 1, '2024-02-01', 'Analista de Sistemas', 120, 'admin', 'admin'),
                                                                                                                                                           (3, 2, '2024-01-01', 'Gerente de Proyecto', 120, 'admin', 'admin'),
                                                                                                                                                           (9, 2, '2024-01-05', 'Vendedor Senior', 100, 'admin', 'admin'),
                                                                                                                                                           (10, 2, '2024-01-10', 'Vendedor Junior', 80, 'admin', 'admin'),
                                                                                                                                                           (4, 3, '2024-02-01', 'Gerente de Proyecto', 140, 'admin', 'admin'),
                                                                                                                                                           (11, 3, '2024-02-05', 'Analista de RRHH', 120, 'admin', 'admin'),
                                                                                                                                                           (5, 4, '2024-03-01', 'Gerente de Proyecto', 160, 'admin', 'admin'),
                                                                                                                                                           (12, 4, '2024-03-05', 'Contador', 120, 'admin', 'admin'),
                                                                                                                                                           (13, 4, '2024-03-10', 'Asistente Contable', 80, 'admin', 'admin'),
                                                                                                                                                           (14, 5, '2024-04-01', 'Supervisor de Proyecto', 160, 'admin', 'admin'),
                                                                                                                                                           (15, 5, '2024-04-05', 'Operario', 120, 'admin', 'admin');

-- Insertar evaluaciones de desempeño
INSERT INTO evaluaciones (empleado_id, evaluador_id, periodo_inicio, periodo_fin, puntaje_total, comentarios, objetivos_cumplidos, areas_mejora, estado, usuario_creacion, usuario_actualizacion) VALUES
                                                                                                                                                                                                      (2, 1, '2023-01-01', '2023-12-31', 4.5, 'Excelente desempeño como gerente de TI', 'Implementó nuevas tecnologías exitosamente', 'Mejorar comunicación con otros departamentos', 'COMPLETADA', 'admin', 'admin'),
                                                                                                                                                                                                      (3, 1, '2023-01-01', '2023-12-31', 4.2, 'Muy buen desempeño en ventas', 'Superó metas de ventas en un 15%', 'Desarrollar habilidades de negociación', 'COMPLETADA', 'admin', 'admin'),
                                                                                                                                                                                                      (4, 1, '2023-01-01', '2023-12-31', 4.8, 'Excelente gestión de recursos humanos', 'Redujo rotación de personal en un 20%', 'Ninguna área significativa de mejora', 'COMPLETADA', 'admin', 'admin'),                                                                                                                                                                                               (15, 14, '2023-01-01', '2023-12-31', 3.9, 'Cumple con lo esperado', 'Puntual y dedicado', 'Ampliar conocimientos técnicos', 'COMPLETADA', 'admin', 'admin');

-- ====================================
-- FIN DEL SCRIPT
-- ====================================

SELECT 'Sistema de Recursos Humanos cargado exitosamente.' AS mensaje;
