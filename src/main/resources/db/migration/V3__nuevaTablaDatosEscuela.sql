-- 2. Tabla de Datos de Escuela (con FK apuntando a cocineros, o al revés)
CREATE TABLE datos_escuela (
                               id SERIAL PRIMARY KEY,
                               nombre VARCHAR(100) NOT NULL,
                               direccion VARCHAR(150),
                               ciudad VARCHAR(50),
                               cocinero_id INT,
                               CONSTRAINT fk_cocinero_escuela FOREIGN KEY (cocinero_id) REFERENCES cocineros(id) ON DELETE CASCADE
);
