/* Mauricio Luiz Abreu Cardoso nusp: 6796479 */

ALTER TABLE turma ADD COLUMN data_matricula DATE;

ALTER TABLE disciplina ADD CONSTRAINT nomeUnico UNIQUE(nome);

ALTER TABLE turma DROP CONSTRAINT nota0a10;
ALTER TABLE turma ADD CONSTRAINT nota0a100 CHECK (nota >= 0 AND nota <= 100);

ALTER TABLE turma DROP COLUMN frequencia;

ALTER TABLE aluno_especial ADD COLUMN nusp_orientador INT;
ALTER TABLE aluno_especial ADD CONSTRAINT orientado FOREIGN KEY(nusp_orientador) REFERENCES professor(nusp);

