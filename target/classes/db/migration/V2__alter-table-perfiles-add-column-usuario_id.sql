ALTER TABLE perfiles
ADD COLUMN usuario_id BIGINT;

ALTER TABLE perfiles
ADD CONSTRAINT fk_perfil_usuario
FOREIGN KEY (usuario_id) REFERENCES usuarios(id);