/* a) Encontre a tuplas de Laptop que possuem um preço maior que o seu número de modelo.*/
SELECT * from laptop WHERE preco > modelo;

/* b) Encontre o número do modelo, a velocidade e o preço para todos os PCs cuja velocidade está
entre 1000 e 1500 MHz. Na resposta, renomeie a coluna de velocidade para processador e a coluna
de preço para R$. */
SELECT modelo, velocidade, preco R$ FROM PC WHERE velocidade >= 1000 AND velocidade <= 1500;


/* c) Encontre os dados das impressoras do tipo ink-jet ou laser e que custem mais que R$380,00. O
resultado deve aparecer em ordem crescente de tipo e decrescente de preço. */
SELECT * FROM Impressora WHERE preco > 380 AND (tipo = 'ink-jet' OR tipo = 'laser') ORDER BY tipo, preco DESC;

/* d) Encontre os modelos dos produtos dos fabricantes cujo nome começa com uma letra que está
entre as 5 primeiras do alfabeto. O resultado deve aparecer ordenado em ordem crescente de nome
de fabricante. */
SELECT modelo FROM Produto WHERE fabricante LIKE 'A%' OR fabricante LIKE '%B' OR fabricante LIKE '%C' OR fabricante LIKE '%D' OR fabricante LIKE '%E' ORDER BY fabricante;


/* e) Encontre os tipos de produtos vendidos pelos fabricantes que possuem 3 ou mais palavras em seu
nome, sendo que uma dessas palavras é HW. */
SELECT tipo, fabricante FROM Produto WHERE fabricante LIKE 'HW % %' OR fabricante LIKE '% HW %' OR fabricante LIKE '% % HW' OR fabricante LIKE '% % HW %';

/* f) Encontre os tipos de produtos vendidos pelos fabricantes que possuem exatamente 2 palavras em
seu nome. */
SELECT tipo FROM produto WHERE (fabricante LIKE '% %' and fabricante NOT LIKE '% % %');

/* g) Encontre os fabricantes e modelos das impressoras coloridas que não são do tipo ink-jet. */
SELECT fabricante, Produto.modelo, preco FROM Produto, Impressora WHERE Impressora.tipo != 'ink-jet' AND Impressora.colorida = true AND Impressora.modelo = Produto.modelo;

/* h) Encontre os fabricantes que vendem tanto impressoras quanto PCs. */
(SELECT fabricante FROM Produto, Impressora WHERE Produto.modelo = Impressora.modelo)
INTERSECT (SELECT fabricante FROM Produto, PC WHERE Produto.modelo = PC.modelo);

/* i) Encontre as velocidades que aparecem em mais de um PC. */
SELECT DISTINCT pc1.velocidade FROM PC pc1, PC pc2 WHERE pc1.modelo != pc2.modelo AND pc1.velocidade = pc2.velocidade;

/* j) Encontre os pares de modelos de Laptop (i,j) tais que i e j possuem a mesma velocidade e a
diferença entre os seus preços é menor que R$500,00. Mas atenção: se um par de modelos (i,j) for
listado, então o par (j,i) não deve ser listado. */
SELECT lap1.modelo, lap2.modelo FROM Laptop lap1, Laptop lap2 WHERE lap1.velocidade = lap2.velocidade AND lap1.modelo - lap2.modelo < 500 AND lap1.modelo > lap2.modelo;


/* k) Liste o fabricante, o modelo e o preço de todos os produtos que aparecem no BD. */
(SELECT fabricante, Produto.modelo, preco FROM Produto, PC WHERE produto.modelo = PC.modelo)
 UNION (SELECT fabricante, Produto.modelo, preco FROM Produto, Impressora WHERE produto.modelo = Impressora.modelo)
 UNION (SELECT fabricante, Produto.modelo, preco FROM Produto, Laptop WHERE produto.modelo = Laptop.modelo);


