-- Banco de Dados
CREATE DATABASE db_blog_pessoal;
USE db_blog_pessoal;

-- Tabela Tema
CREATE TABLE Temas (
	id bigint NOT NULL AUTO_INCREMENT,
	descricao varchar(255) NOT NULL,
	CONSTRAINT Temas_pk PRIMARY KEY (id)
);

-- Tabela Tema
CREATE TABLE Postagens (
	id bigint NOT NULL AUTO_INCREMENT,
	titulo varchar(255) NOT NULL,
	texto varchar(255) NOT NULL,
	data TIMESTAMP NOT NULL,
	tema_id bigint NOT NULL,
	usuario_id bigint NOT NULL,
	CONSTRAINT Postagens_pk PRIMARY KEY (id)
);

-- Tabela Tema
CREATE TABLE Usuarios (
	id bigint NOT NULL AUTO_INCREMENT,
	nome varchar(255) NOT NULL,
	usuario varchar(255) NOT NULL UNIQUE,
	senha varchar(255) NOT NULL UNIQUE,
	CONSTRAINT Usuarios_pk PRIMARY KEY (id)
);

-- Alterando dados
ALTER TABLE Postagens ADD CONSTRAINT Postagens_fk0 FOREIGN KEY (tema_id) REFERENCES Temas(id);
ALTER TABLE Postagens ADD CONSTRAINT Postagens_fk1 FOREIGN KEY (usuario_id) REFERENCES Usuarios(id);



