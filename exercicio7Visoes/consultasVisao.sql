/* Mauricio LUiz Abreu Cardoso nusp: 6796479 */

/*EXERCICIO SECAO 7.1 */

/* a) Usando apenas as relações Classes e Navios, produza todas as informações disponíveis sobre
navios, incluindo as informações disponíveis na relação Classes. Você deve produzir informações
sobre uma classe mesmo se não existir nenhum navio da classe na relação Navios. */
SELECT * FROM classes NATURAL LEFT OUTER JOIN navios;

/* b) Liste todos os dados disponíveis (i.e., nome e data) de todas as batalhas que aparecem no BD.
Uma batalha deve aparecer na resposta mesmo quando não é possível saber em que data ela
ocorreu. */
SELECT batalhas.data, resultados.* FROM batalhas LEFT OUTER JOIN resultados on nome = batalha;


/* c) Forneça informações sobre todos os produtos do BD (PCs, laptops e impressoras), incluindo o
seu fabricante se disponível, e quaisquer outras informações sobre o produto que sejam relevantes
(ou seja, as informações encontradas na relação para o tipo de produto em questão). */
SELECT * FROM produto LEFT OUTER JOIN (pc NATURAL FULL OUTER JOIN (laptop NATURAL FULL OUTER JOIN impressora)) AS pc_lap_imp ON (pc_lap_imp.modelo = produto.modelo);


/*EXERCICIOS SECAO 7.2 */

/* a) Uma visão ImpressorasColoridas, que fornece modelo, tipo e preço das impressoras coloridas. */
CREATE VIEW ImpressorasColoridas AS SELECT modelo, tipo, preco FROM impressora WHERE colorida = true;

/* b) Uma visão LaptopsIguaisPCs, que fornece todos os atributos dos laptops que possuem
velocidade igual a de um PC do BD. */
CREATE VIEW LaptopsIguaisPCs AS SELECT DISTINCT laptop.* FROM pc JOIN laptop ON pc.velocidade = laptop.velocidade;

/* c) Uma visão InfoImpressoras que fornece o nome do fabricante, o modelo, o tipo e o preço das
impressoras. */
CREATE VIEW InfoImpressoras AS SELECT fabricante, imp.modelo, imp.tipo, imp.preco FROM impressora AS imp JOIN produto ON imp.modelo = produto.modelo;

/* d) Uma visão NaviosMaisNovos, que mostra o nome e o ano de lançamento dos navios lançados
mais recentemente. */
CREATE VIEW NaviosMaisNovos AS SELECT nome, lancamento FROM navios ORDER BY lancamento;

/* e) Uma visão NaviosAfundados, que mostra o nome, a classe e o ano de lançamento de todos osnavios que afundaram em batalha. */
CREATE VIEW NaviosAfundados AS SELECT navios.* FROM navios JOIN resultados ON navios.nome = resultados.navio AND desfecho = 'afundado'; 

/* f) Uma visão NaviosPorClasse, que possui três atributos – classe, numNavios, ultLancamento –
que mostra, para cada classe, o número de navios na classe e o ano do último lançamento de navio
na classe. Na visão, as classes devem ser listadas por ordem decrescente de número de navios. Uma
classe que não tiver navios não precisa aparecer na visão. */
CREATE VIEW NaviosPorClasse AS SELECT classe, COUNT(nome) AS numNavios, MAX(lancamento) AS ultLancamento FROM navios GROUP BY classe;

/*EXERCICIOS SECAO 7.3 */

/* a) Listar o país das classes de navios encouraçados que possuem mais do que 2 navios. */
SELECT pais FROM NaviosPorClasse JOIN classes ON NaviosPorClasse.classe = classes.classe AND classes.tipo = 'ne' AND NaviosPorClasse.numNavios > 2;

/* b) Exibir o número médio de armas dos navios que afundaram em batalhas. */
SELECT AVG(numarmas) FROM NaviosAfundados JOIN classes ON classes.classe = NaviosAfundados.classe;

/* c) Listar o nomes dos navios afundados que foram os últimos a serem lançados em suas classes. */
SELECT nome FROM NaviosAfundados NATURAL JOIN (SELECT classe, MAX(lancamento) AS lancamento FROM NaviosAfundados GROUP BY classe) recAfundClass;
