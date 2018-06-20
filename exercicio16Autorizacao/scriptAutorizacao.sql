// Mauricio Luiz Abreu Cardoso NUSP: 6796479

// criacao de schema para o exercicio
CREATE schema exerciciopermissoes;
SET search_path TO exerciciopermissoes;

// criacao da tabela
CREATE TABLE Empregados (
  nome varchar(50),
  departamento varchar(50),
  salario integer
);

CREATE VIEW NomesDosEmpregados AS SELECT nome FROM Empregados;
CREATE VIEW InfosDosDepts AS SELECT departamento, AVG(salario) AS salariomedio FROM Empregados GROUP BY departamento;


