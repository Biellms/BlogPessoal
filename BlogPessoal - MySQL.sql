-- Banco de Dados
CREATE DATABASE db_blogpessoal;
USE db_blogpessoal;

-- Tabela Tema
CREATE TABLE tb_temas (
	id bigint NOT NULL AUTO_INCREMENT,
	descricao varchar(255) NOT NULL,
	CONSTRAINT Temas_pk PRIMARY KEY (id)
);

-- Tabela Tema
CREATE TABLE tb_postagens (
	id bigint NOT NULL AUTO_INCREMENT,
	titulo varchar(255) NOT NULL,
	texto varchar(255) NOT NULL,
	data TIMESTAMP NOT NULL,
	tema_id bigint NOT NULL,
	usuario_id bigint NOT NULL,
	CONSTRAINT Postagens_pk PRIMARY KEY (id)
);

-- Tabela Tema
CREATE TABLE tb_usuarios (
	id bigint NOT NULL AUTO_INCREMENT,
	nome varchar(255) NOT NULL,
	usuario varchar(255) NOT NULL UNIQUE,
	senha varchar(255) NOT NULL UNIQUE,
	CONSTRAINT Usuarios_pk PRIMARY KEY (id)
);

-- Alterando dados
ALTER TABLE tb_postagens ADD CONSTRAINT Postagens_fk0 FOREIGN KEY (tema_id) REFERENCES tb_temas(id);
ALTER TABLE tb_postagens ADD CONSTRAINT Postagens_fk1 FOREIGN KEY (usuario_id) REFERENCES tb_temas(id);

-- Selects
SELECT * FROM tb_postagens;
SELECT * FROM tb_temas;
SELECT * FROM tb_usuarios;

DROP DATABASE db_blogpessoal;
