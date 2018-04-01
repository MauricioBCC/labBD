/* Mauricio Luiz Abreu Cardoso nusp: 6796479 */

/* Observacao: o nome "professor" nao foi incluso na relacao "turma", e sim 
 na relacao "ministra", para que uma disciplina possa ter varios professores*/

CREATE DOMAIN tipo_cpf AS CHAR(11);

CREATE TABLE professor (
	nusp INT PRIMARY KEY,
	cpf tipo_cpf NOT NULL,
	nome VARCHAR(50) NOT NULL,
	sala VARCHAR(50) NOT NULL
);

CREATE TABLE titulo_professor (
	titulo VARCHAR(50),
	nusp_prof INT,
	PRIMARY KEY(titulo, nusp_prof),
	FOREIGN KEY(nusp_prof) REFERENCES professor(nusp),
	CHECK (titulo IN ('Doutorado','Mestrado','Especialização','Bacharelado', 'Licenciatura','Graduação Tecnológica'))
);

CREATE TABLE aluno (
	id_aluno INT NOT NULL PRIMARY KEY
);

CREATE TABLE aluno_regular (
	nusp INT PRIMARY KEY,
	cpf tipo_cpf NOT NULL,
	nome VARCHAR(50) NOT NULL,
	curso VARCHAR(50) DEFAULT 'Bacharelado em Ciência da Computação',
	nusp_orientador INT NOT NULL,
	id_aluno INT NOT NULL,
	FOREIGN KEY(nusp_orientador) REFERENCES professor(nusp),
	FOREIGN KEY(id_aluno) REFERENCES aluno(id_aluno)
);

CREATE TABLE aluno_especial (
	email VARCHAR(50) NOT NULL,
	nome VARCHAR(50) NOT NULL,
	id_aluno INT NOT NULL,
	FOREIGN KEY(id_aluno) REFERENCES aluno(id_aluno)
);

CREATE TABLE disciplina (
	codigo_disciplina INT PRIMARY KEY,
	nome VARCHAR(50)
);

CREATE TABLE turma (
	id_turma INT,
	semestre INT,
	ano INT,
	codigo_disciplina INT,
	id_aluno INT NOT NULL,
	nota DECIMAL(2,2) NOT NULL,
	frequencia INT NOT NULL,
	CONSTRAINT nota0a10 CHECK (nota >= 0 AND nota <= 10),
	PRIMARY KEY(id_turma, semestre, ano, codigo_disciplina),
	FOREIGN KEY(codigo_disciplina) REFERENCES disciplina(codigo_disciplina) ON DELETE RESTRICT,
	FOREIGN KEY(id_aluno) REFERENCES aluno(id_aluno)
);

CREATE TABLE ministra (
	nusp_prof INT,
	semestre INT,
	ano INT,
	codigo_disciplina INT,
	id_turma INT,
	PRIMARY KEY(nusp_prof, semestre, ano, codigo_disciplina, id_turma),
	FOREIGN KEY(id_turma, semestre, ano, codigo_disciplina) REFERENCES turma(id_turma, semestre, ano, codigo_disciplina),
	FOREIGN KEY(nusp_prof) REFERENCES professor(nusp),
	FOREIGN KEY(codigo_disciplina) REFERENCES disciplina(codigo_disciplina)
);

CREATE TABLE disc_pre_requisito (
	id_disciplina INT,
	id_disciplina_requisito INT,
	PRIMARY KEY(id_disciplina, id_disciplina_requisito),
	FOREIGN KEY(id_disciplina) REFERENCES disciplina(codigo_disciplina) ON DELETE CASCADE ON UPDATE CASCADE
);
