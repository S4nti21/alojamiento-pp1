-- Active: 1755188685255@@127.0.0.1@3306@alojamiento_pp1

USE alojamiento_pp1;

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
    imagen LONGTEXT,
    dni INT
);

CREATE TABLE IF NOT EXISTS hospedaje (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    direccion VARCHAR(100),
    descripcion VARCHAR(800),
    imagen LONGTEXT,
    precio_por_noche DOUBLE,
    fecha_creacion DATE,
    fecha_modificacion DATE,
    id_tipo_hospedaje INT,
    id_ciudad INT,
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
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
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
    FOREIGN KEY (id_hospedaje) REFERENCES hospedaje(id)
        ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id)
        ON DELETE CASCADE ON UPDATE CASCADE
);

