-- V2__agregar_columnas_contacto_y_fecha.sql

-- 1. Añadir la columna fecha_nacimiento con una fecha por defecto para los registros antiguos
ALTER TABLE cocineros ADD COLUMN fecha_nacimiento DATE NOT NULL DEFAULT '2000-01-01';

-- 2. Añadir la columna telefono con un valor por defecto temporal
ALTER TABLE cocineros ADD COLUMN telefono VARCHAR(20) NOT NULL DEFAULT '000000000';

-- 3. Añadir la columna email con un valor por defecto temporal
ALTER TABLE cocineros ADD COLUMN email VARCHAR(255) NOT NULL DEFAULT 'sin-correo@email.com';