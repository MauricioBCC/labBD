/* Mauricio Luiz Abreu Cardoso NUSP:6796479 */


/* a) Garanta, em todas as circunstâncias que podem causar uma violação, que o preço de uma
impressora seja limitado a R$250,00 (ou seja, na tentativa de atribuição de um preço superior a
R$250,00, substitua-o por R$250,00). */
CREATE OR REPLACE FUNCTION AtualizaPrecoImpressora250()
RETURNS TRIGGER AS $$
BEGIN
	UPDATE Impressora
	preco = 250
	WHERE modelo = NEW.modelo;
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER tetoPrecoImpressoraUpdate
AFTER INSERT OR UPDATE OF preco ON Impressora
FOR EACH ROW
WHEN (NEW.preco > 250)
	EXECUTE PROCEDURE AtualizaPrecoImpressora250();

/* b) Na inserção ou alteração de um laptop, só permita que a operação seja realizada se o tamanho de
HD informado para ele existir como um tamanho de HD na relação de PC (caso o tamanho de HD
seja informado). */

CREATE OR REPLACE FUNCTION verificaHD()
RETURNS TRIGGER AS $$
BEGIN
    IF(NOT EXISTS (SELECT * FROM PC WHERE NEW.hd = PC.hd))
		THEN RETURN NULL;
	ELSE
	   RETURN NEW;
	END IF;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER verificaHdLaptopEmPC
BEFORE INSERT OR UPDATE OF hd ON laptop
FOR EACH ROW
WHEN (NEW.hd <> null)
	EXECUTE PROCEDURE verificaHD();

/*c- Na inserção de um novo PC, caso ele não esteja cadastrado ainda na tabela de Produtos, inclua-o
em Produto, usando 'H' como fabricante.*/

CREATE OR REPLACE FUNCTION inserePcProduto()
RETURNS TRIGGER AS $$
BEGIN
    IF(NOT EXISTS (SELECT * FROM Produtos WHERE NEW.modelo = Produtos.modelo))
		THEN INSERT INTO Produto (fabricante, modelo, tipo) VALUES ('H', NEW.modelo, NEW.tipo);
	ELSE
	   RETURN NEW;
	END IF;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER relacionaProdutoHd
BEFORE INSERT ON PC
FOR EACH ROW
WHEN ()
	EXECUTE PROCEDURE inserePcProduto();

/*d- Garanta, em todas as circunstâncias que podem causar uma violação, que cada fabricante venda
no máximo 7 equipamentos.*/

CREATE OR REPLACE FUNCTION fabricanteMaxProduto()
RETURNS TRIGGER AS $$
BEGIN
    IF((SELECT COUNT(modelo) FROM Produto WHERE NEW.fabricante = Produto.fabricante) >= 7) THEN
		RETURN NULL;
	ELSE
	   RETURN NEW;
	END IF;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER maximoProdutoFabricante
BEFORE UPDATE OF fabricante ON Produto
FOR EACH ROW
	EXECUTE PROCEDURE fabricanteMaxProduto();

/*e- Na remoção de qualquer equipamento das relações PC, laptop ou impressora, remova o registro
correspondente na relação Produto. */


CREATE OR REPLACE FUNCTION removeLinhaProduto()
RETURNS TRIGGER AS $$
BEGIN
    DELETE FROM Produto WHERE OLD.modelo = modelo;
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER removePcProduto
AFTER DELETE ON PC
FOR EACH ROW
	EXECUTE PROCEDURE removeLinhaProduto();

CREATE TRIGGER removeLaptopProduto
AFTER DELETE ON PC
FOR EACH ROW
	EXECUTE PROCEDURE removeLinhaProduto();

CREATE TRIGGER removeImpressoraProduto
AFTER DELETE ON PC
FOR EACH ROW
	EXECUTE PROCEDURE removeLinhaProduto();



/* ----------------SEGUNDA PARTE-----------------------*/

/* a) Essa visão é atualizável? Por quê? */
/* Não é atualizável, pois as colunas retornadas pela visão pertencem a duas tabelas distintas */

/* b) Escreva um trigger do tipo INSTEAD OF para tratar inserções nessa visão. Você deve evitar
anomalias de inserção na visão. */

CREATE OR REPLACE FUNCTION trataInsercao()
RETURNS TRIGGER AS $$
BEGIN
    INSERT INTO Navios (nome, classe, lancamento) VALUES (NEW.nome, NEW.classe, NEW.lancamento);
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER insereVisaoNavios
INSTEAD OF INSERT ON NaviosAmericanos
FOR EACH ROW
	EXECUTE PROCEDURE trataInsercao();
