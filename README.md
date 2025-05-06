CREATE DATABASE IF NOT EXISTS transporteinterprovincial;
USE transporteinterprovincial;

-- Insertar roles
INSERT INTO rol (name) VALUES ('ROLE_USER'), ('ROLE_ADMIN'), ('ROLE_SUPER');

-- Insertar usuarios
INSERT INTO usuario (username, password) VALUES 
('admin', '$2a$12$q5vCPpoFsMABKixJ/6sTY.1d.jO4K8vu0Ekimb2NL0fB8kM7v43dG'),
('user', '$2a$12$q5vCPpoFsMABKixJ/6sTY.1d.jO4K8vu0Ekimb2NL0fB8kM7v43dG'),
('supervisor', '$2a$12$q5vCPpoFsMABKixJ/6sTY.1d.jO4K8vu0Ekimb2NL0fB8kM7v43dG');  -- usa la misma contraseña bcrypt

-- Asignar roles a los usuarios
INSERT INTO user_roles (user_id, role_name) VALUES
(1, 'ROLE_ADMIN'),
(1, 'ROLE_USER'),
(2, 'ROLE_USER'),
(3, 'ROLE_SUPER');

