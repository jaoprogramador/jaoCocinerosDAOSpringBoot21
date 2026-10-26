-- 2. Tabla de Datos de Escuela (con FK apuntando a cocineros, o al revés)
--ALTER TABLE public.cocineros ADD fecha_Nacimiento varchar(100) NOT NULL;
ALTER TABLE cocineros ALTER COLUMN fecha_nacimiento TYPE VARCHAR(255);