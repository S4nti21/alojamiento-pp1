CREATE TABLE servicio(
    id PRIMARY KEY,
    nombre VARCHAR(100)
);

CREATE TABLE servicio_hospedaje(
    PRIMARY KEY (Id_Servicio, Id_Hospedaje),
    Foreign Key (id_servicio) REFERENCES servicio(id),
    Foreign Key (id_hospedaje) REFERENCES hospedaje(id)
);

CREATE TABLE hospedaje(
    id PRIMARY KEY,
    nombre VARCHAR(100),
    descripcion VARCHAR (250),
    imagen VARCHAR(500),
    precio_por_noche DOUBLE,
    fecha_creacion DATE,
    fecha_modificacion DATE,
    Foreign Key (id_tipo_hospedaje) REFERENCES hospedaje(id),
    Foreign Key (id_ciudad) REFERENCES ciudad(id)
);

CREATE TABLE tipo_hospedaje(
    id PRIMARY KEY,
    Nombre VARCHAR(100),
);

CREATE TABLE ciudad(
    id PRIMARY KEY,
    nombre VARCHAR(100),
    Foreign Key (id_pais) REFERENCES pais(id)
);

CREATE TABLE pais(
    id PRIMARY KEY,
    nombre VARCHAR(100)
);

CREATE TABLE reserva(
    PRIMARY KEY (id_hospedaje, id_usuario),
    fecha_check_in DATETIME,
    fecha_check_out DATETIME,
    cant_niños INTEGER,
    cant_adultos INTEGER,
    cant_bebes INTEGER,
    cant_mascotas INTEGER,
    importe_total DOUBLE,
    fecha_creacion DATETIME,
    fecha_modificacion DATETIME,
    Foreign Key (id_hospedaje) REFERENCES hospedaje(id),
    Foreign Key (id_usuario) REFERENCES usuario(id)
);

CREATE TABLE usuario(
    id PRIMARY KEY,
    userName VARCHAR(100),
    contraseña VARCHAR(100),
    email VARCHAR(100) UNIQUE,
    nombre VARCHAR(100),
    apellido VARCHAR(100),
    fecha_nacimiento DATE,
    fecha_creacion DATETIME,
    fecha_modificacion DATETIME,
    Foreign Key (id_tipo_usuario) REFERENCES tipo_usuario(id)
);

CREATE TABLE tipo_usuario(
    id PRIMARY KEY,
    nombre VARCHAR(100) 
);
