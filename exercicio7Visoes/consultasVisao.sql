/* a) Usando apenas as relações Classes e Navios, produza todas as informações disponíveis sobre
navios, incluindo as informações disponíveis na relação Classes. Você deve produzir informações
sobre uma classe mesmo se não existir nenhum navio da classe na relação Navios. */
SELECT * FROM classes left natural outer JOIN navios;

/* b) Liste todos os dados disponíveis (i.e., nome e data) de todas as batalhas que aparecem no BD.
Uma batalha deve aparecer na resposta mesmo quando não é possível saber em que data ela
ocorreu. */
SELECT batalhas.data, resultados.* FROM batalhas LEFT OUTER JOIN resultados on nome = batalha;


/* c) Forneça informações sobre todos os produtos do BD (PCs, laptops e impressoras), incluindo o
seu fabricante se disponível, e quaisquer outras informações sobre o produto que sejam relevantes
(ou seja, as informações encontradas na relação para o tipo de produto em questão). */
select * from produto left outer join (pc natural full outer join (laptop natural full outer join impressora)) AS pc_lap_imp on (pc_lap_imp.modelo = produto.modelo);

/* a) Uma visão ImpressorasColoridas, que fornece modelo, tipo e preço das impressoras coloridas. */

/* b) Uma visão LaptopsIguaisPCs, que fornece todos os atributos dos laptops que possuem
velocidade igual a de um PC do BD. */

/* c) Uma visão InfoImpressoras que fornece o nome do fabricante, o modelo, o tipo e o preço das
impressoras. */

/* d) Uma visão NaviosMaisNovos, que mostra o nome e o ano de lançamento dos navios lançados
mais recentemente. */

/* e) Uma visão NaviosAfundados, que mostra o nome, a classe e o ano de lançamento de todos osnavios que afundaram em batalha. */

/* f) Uma visão NaviosPorClasse, que possui três atributos – classe, numNavios, ultLancamento –
que mostra, para cada classe, o número de navios na classe e o ano do último lançamento de navio
na classe. Na visão, as classes devem ser listadas por ordem decrescente de número de navios. Uma
classe que não tiver navios não precisa aparecer na visão. */
