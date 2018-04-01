CREATE DOMAIN tipo_cpf AS CHAR(11);

CREATE TABLE professor (
	nusp int PRIMARY KEY,
	cpf tipo_cpf NOT NULL,
	nome NOT NULL,
	sala VARCHAR(50) NOT NULL

);

CREATE TABLE aluno_regular (
	nusp int PRIMARY KEY,
	cpf tipo_cpf NOT NULL,
	nome VARCHAR(50) NOT NULL,
	nusp_orientador int NOT NULL,
	id_aluno int NOT NULL,
	FOREIGN KEY(nusp_orientador) REFERENCES professor(nusp),
	FOREIGN KEY(id_aluno) REFERENCES aluno(id_aluno)
);

CREATE TABLE aluno_especial (
	email VARCHAR(50) NOT NULL,
	nome VARCHAR(50) NOT NULL,
	id_aluno int NOT NULL,
	FOREIGN KEY(id_aluno) REFERENCES aluno(id_aluno),
);

CREATE TABLE aluno (
	id_aluno int NOT NULL PRIMARY KEY
);

