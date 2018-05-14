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

-- cursor
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


