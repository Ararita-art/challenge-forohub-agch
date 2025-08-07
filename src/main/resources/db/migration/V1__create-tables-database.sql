CREATE TABLE usuarios(
    id bigint NOT NULL auto_increment,
    nombre VARCHAR(100) NOT NULL,
    correo_electronico VARCHAR(100) NOT NULL UNIQUE,
    contrasena VARCHAR(300) NOT NULL,

    PRIMARY KEY (id)
);

CREATE TABLE cursos(
    id bigint NOT NULL auto_increment,
    nombre VARCHAR(300) NOT NULL,
    categoria VARCHAR(100) NOT NULL,

    PRIMARY KEY (id)
);

CREATE TABLE topicos(

    id bigint NOT NULL auto_increment,
    titulo VARCHAR(250) NOT NULL,
    mensaje VARCHAR(500) NOT NULL,
    fecha datetime NOT NULL,
    estatus tinyint NOT NULL,
    usuario_id bigint NOT NULL,
    curso_id bigint NOT NULL,

    PRIMARY KEY (id),
    CONSTRAINT fk_topicos_usuario_id FOREIGN KEY(usuario_id) REFERENCES usuarios(id),
    CONSTRAINT fk_topicos_curso_id FOREIGN KEY(curso_id) REFERENCES cursos(id)
);

CREATE TABLE respuestas(
    id bigint NOT NULL auto_increment,
    mensaje VARCHAR(500) NOT NULL,
    topico_id bigint NOT NULL,
    fecha datetime NOT NULL,
    usuario_id bigint NOT NULL,
    solucion tinyint NOT NULL,

    PRIMARY KEY (id),
    CONSTRAINT fk_respuestas_topico_id FOREIGN KEY(topico_id) REFERENCES topicos(id),
    CONSTRAINT fk_respuestas_usuario_id FOREIGN KEY(usuario_id) REFERENCES usuarios(id)
);

CREATE TABLE perfiles(
    id bigint NOT NULL auto_increment,
    nombre VARCHAR(100) NOT NULL,

    PRIMARY KEY(id)
);