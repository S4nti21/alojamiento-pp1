
USE alojamiento_pp1;

CREATE TABLE IF NOT EXISTS pais (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS ciudad (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    id_pais INT,
    FOREIGN KEY (id_pais) REFERENCES pais(id)
);

CREATE TABLE IF NOT EXISTS tipo_usuario (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS tipo_hospedaje (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS servicio (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS usuario (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    contraseña VARCHAR(100),
    email VARCHAR(100) UNIQUE,
    nombre VARCHAR(100),
    apellido VARCHAR(100),
    imagen LONGBLOB,
    dni INT,
    id_tipo_usuario INT,
    FOREIGN KEY (id_tipo_usuario) REFERENCES tipo_usuario(id)
);

CREATE TABLE IF NOT EXISTS hospedaje (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    descripcion VARCHAR(250),
    imagen VARCHAR(500),
    precio_por_noche DOUBLE,
    fecha_creacion DATE,
    fecha_modificacion DATE,
    id_tipo_hospedaje INT,
    id_ciudad INT,
    FOREIGN KEY (id_tipo_hospedaje) REFERENCES tipo_hospedaje(id),
    FOREIGN KEY (id_ciudad) REFERENCES ciudad(id)
);

CREATE TABLE IF NOT EXISTS servicio_hospedaje (
    id_servicio INT NOT NULL,
    id_hospedaje INT NOT NULL,
    PRIMARY KEY (id_servicio, id_hospedaje),
    FOREIGN KEY (id_servicio) REFERENCES servicio(id)
        ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (id_hospedaje) REFERENCES hospedaje(id)
        ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS reserva (
    id_hospedaje INT NOT NULL,
    id_usuario INT NOT NULL,
    fecha_check_in DATETIME,
    fecha_check_out DATETIME,
    cant_niños INT,
    cant_adultos INT,
    cant_bebes INT,
    cant_mascotas INT,
    importe_total DOUBLE,
    fecha_creacion DATETIME,
    fecha_modificacion DATETIME,
    PRIMARY KEY (id_hospedaje, id_usuario),
    FOREIGN KEY (id_hospedaje) REFERENCES hospedaje(id)
        ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id)
        ON DELETE CASCADE ON UPDATE CASCADE
);
