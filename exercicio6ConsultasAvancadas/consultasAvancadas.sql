/* a) Encontre os nomes dos navios que são os únicos em sua classe. */
SELECT nome FROM navios navio WHERE NOT EXISTS (SELECT * FROM navios WHERE navio.nome <> nome AND navio.classe = classe);

/* b) Encontre o deslocamento dos navios que foram lançados mais recentemente. */
SELECT nome, deslocamento FROM classes class, (SELECT nome, classe FROM navios ORDER BY lancamento DESC) navios WHERE navios.classe = class.classe;

/* c) Encontre os nomes dos navios cujo número de armas é o menor entre os navios do mesmo tipo. */
SELECT nome FROM navios n, classes c, (SELECT tipo, MIN(numArmas) numArmas FROM classes GROUP BY tipo) nomesMenorNumArmasPorTipo 
WHERE n.classe = c.classe AND c.tipo = nomesMenorNumArmasPorTipo.tipo AND c.numArmas = nomesMenorNumArmasPorTipo.numArmas;

/* d) Encontre os nomes dos navios que possuem um número de armas maior que o número de armas
dos navios da classe Revenge. */
SELECT nome FROM navios n, classes c WHERE n.classe = c.classe AND c.numArmas >= ALL (SELECT numArmas FROM classes WHERE classe = 'Revenge');

/* e) Encontre os navios mais novos entre os navios das classes que não possuem o menor calibre */
SELECT nome FROM navios n, (SELECT * FROM classes WHERE calibre >= ALL (SELECT calibre FROM classes)) classes WHERE classes.classe = n.classe ORDER BY lancamento;

/* f) Encontre o número médio de armas das classes de navios que têm deslocamento acima de 40000. */
SELECT AVG(numArmas) FROM classes WHERE deslocamento >= 40000;

/* g) Encontre o número médio de armas dos navios das classes que têm deslocamento acima de
40000. (Atente para a diferença entre este item e o anterior!) */
SELECT nome, AVG(numArmas) FROM (SELECT n.nome, c.numArmas FROM navios n, classes c WHERE c.classe = n.classe AND deslocamento >= 40000) naviosArmasDistG4000 GROUP BY nome;

/* h) Encontre, para cada classe, o número de navios da classe que não participaram de nenhuma
batalha. */
SELECT COUNT(nome) SELECT FROM batalhas b, resultados r r.batalha = b.nome; 