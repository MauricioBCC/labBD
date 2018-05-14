/* Mauricio Luiz Abreu Cardoso NUSP 6796479*/


/* a) Faça uma função que receba como entrada uma string e que devolva os nomes de navios que
contêm essa string. Você deve considerar também os nomes de navios que aparecem na relação
Resultados, além dos que aparecem em Navios. (Dica: o operador || concatena duas strings.) */
CREATE OR REPLACE FUNCTION nomeContem(TEXT) RETURNS SETOF varchar AS
$$
DECLARE
palavra TEXT := '%' || $1 || '%';
BEGIN
	return query SELECT nome FROM ((SELECT nome FROM Navios) UNION (SELECT navio AS nome FROM Resultados)) 
	AS navios WHERE nome LIKE palavra;
END;
$$ LANGUAGE plpgsql;

SELECT nomeContem('Ro');

/* b) Faça uma função que receba uma data como entrada e que devolva o nome da batalha que
ocorreu mais perto dessa data. (Dica: a função abs(n) devolve o valor absoluto de um número n.) */
CREATE OR REPLACE FUNCTION batalhaProx(d DATE) RETURNS TEXT AS $$
DECLARE
linha RECORD;
diferenca INT;
navio TEXT;

BEGIN
	diferenca = abs((SELECT MAX(data) FROM batalhas) - d);
	FOR linha IN SELECT * FROM batalhas
		LOOP
			IF diferenca >= abs(linha.data - d) THEN
				diferenca = abs(linha.data - d);
				navio = linha.nome;
			END IF;
		END LOOP;
	RETURN navio;
END;
$$ LANGUAGE plpgsql;



/* c) Faça um procedimento que receba um nome de uma batalha como parâmetro de entrada e, para
cada navio que participou da batalha e que não está cadastrado na tabela Navios, insira o navio em
Navios, usando como ano de lançamento do navio o ano em que a batalha ocorreu. Insira como
classe para o “novo” navio uma classe com o mesmo nome do navio e com demais atributos iguais
ao da classe ‘Revenge’. Dica: a função extract(year from d) do PostgreSQL devolve o ano de uma
data. */
CREATE OR REPLACE FUNCTION resultadosParaNavios(battle TEXT) RETURNS VOID AS $$
DECLARE
linha RECORD;
revenge classes%ROWTYPE;

BEGIN
	SELECT * INTO revenge FROM classes WHERE classe = 'Revenge'; 
	FOR linha IN SELECT * FROM (SELECT navio, batalha FROM resultados WHERE batalha = battle AND NOT EXISTS (SELECT * FROM navios WHERE navios.nome = navio)) AS n JOIN batalhas on batalha = nome
		LOOP
			INSERT INTO navios (nome, classe, lancamento) VALUES (linha.navio, linha.navio, extract(year from linha.data));
			INSERT INTO classes (classe, tipo, pais, numArmas, calibre, deslocamento) VALUES (linha.navio, revenge.tipo, revenge.pais, revenge.numArmas, revenge.calibre, revenge.deslocamento);
		END LOOP;
END;
$$ LANGUAGE plpgsql;



SELECT * FROM (SELECT navio, batalha FROM resultados WHERE batalha = 'Guadalcanal' AND NOT EXISTS (SELECT * FROM navios WHERE navios.nome = navio)) AS n JOIN batalhas on batalha = nome;
SELECT * FROM (SELECT navio, batalha FROM resultados WHERE batalha = 'North Cape' AND NOT EXISTS (SELECT * FROM navios WHERE navios.nome = navio)) AS n NATURAL JOIN batalhas;
select * from resultados join batalhas on batalha = nome;

SELECT navio AS nome, batalha FROM resultados WHERE batalha = 'Guadalcanal' AND NOT EXISTS (SELECT * FROM navios WHERE navios.nome = navio);

select resultadosparanavios('Guadalcanal');


/* d) Faça um procedimento que devolva um conjunto ordenado de strings, onde cada string
corresponde ao nome de um navio, acompanhado pelos seu respectivo deslocamento e sua unidade
de medida. Entretanto, quando o deslocamento for acima de 40000 toneladas, ele deve ser
mostrado em quilotoneladas (kt). Exemplo de resposta esperada:
‘California – 32000 t’
‘Haruna – 32000 t’
...
‘Iowa – 46 kt’
...
‘Yamato – 65 kt’ */
CREATE OR REPLACE FUNCTION navioDeslocamento() RETURNS SETOF TEXT AS $$
DECLARE
linha RECORD;
desloc INT;
unidade TEXT;

BEGIN
	CREATE TEMP TABLE naviodesl (nome_desl TEXT);
	FOR linha IN SELECT deslocamento, nome FROM classes NATURAL JOIN navios
		LOOP
			IF linha.deslocamento <= 40000 THEN
				desloc = linha.deslocamento;
				unidade = ' t';
			ELSE
				desloc = linha.deslocamento / 1000;
				unidade = ' kt';
			END IF;
			INSERT INTO navioDesl (nome_desl) VALUES (linha.nome || ' - ' || desloc || unidade);
		END LOOP;
	return query SELECT * FROM naviodesl ORDER BY nome_desl;
END;
$$ LANGUAGE plpgsql;


/* e) Faça uma função que receba um nome de navio, uma classe e um ano de lançamento como
parâmetros de entrada e que insira esses dados como um novo navio no BD. Entretanto, se já existir
um outro navio com o mesmo nome na relação Navios (problema que será assinalado por meio de
uma exceção com SQLSTATE igual a '23000', correspondente a uma violação da restrição de
primary key), acrescente um sufixo numérico no nome do navio e tente inseri-lo novamente. Caso o
novo nome também já exista, vá “incrementando” o número do sufixo até encontrar um nome de
navio que ainda não esteja no BD. Por exemplo, caso o procedimento seja chamado para incluir o
navio “Rei dos Mares”, mas esse nome de navio já esteja no BD, então modifique o nome do novo
navio para “Rei dos Mares 2” e tente a inclusão novamente. Caso esse novo nome também já esteja
no BD, tente incluir “Rei dos Mares 3”, e assim por diante. Ao final, sua função deve devolver o
nome do navio cadastrado. */

CREATE OR REPLACE FUNCTION insere_navio(nomeNavio VARCHAR, nomeClasse VARCHAR, ano INT) RETURNS VOID AS $$
 RETURN SELECT rec(nomeNavio, nomeClasse, ano, 0);
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION rec(nomeNavio VARCHAR, nomeClasse VARCHAR, ano INT, i INT) RETURNS TEXT AS $$
BEGIN
	IF i = 0 THEN
		INSERT INTO navios VALUES (nomeNavio, nomeClasse, ano);
		RAISE EXCEPTION SQLSTATE '23000';
		BEGIN
			SELECT rec(nomeNavio, nomeClasse, ano, 1);
		END;
		RETURN nomeNavio;
	ELSE
		INSERT INTO navios VALUES (nomeNavio || ' ' || i, nomeClasse, ano);
		RAISE EXCEPTION SQLSTATE '23000';
			BEGIN
				RETURN SELECT rec(nomeNavio, nomeClasse, ano, i + 1);
			END;
		RETURN nomeNavio || ' ' || i;
	END IF;
END;
$$ LANGUAGE plpgsql;











