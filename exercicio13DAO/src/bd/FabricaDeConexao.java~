/* Exemplo adaptado de:  http://www.caelum.com.br/apostila-java-web/bancos-de-dados-e-jdbc/  */

package bd;

import java.sql.*;
import java.util.Properties;

public class FabricaDeConexao {

	// MODIFIQUE O VALOR DESTAS CONSTANTES COM OS DADOS DO SEU BD
	public static final String NOME_DRIVER = "org.postgresql.Driver";
	public static final String URL_BD = "jdbc:postgresql://postgresql.linux.ime.usp.br:5432/SEU_USUARIO";
	public static final String USUARIO_BD = "SEU_USUARIO";
	public static final String SENHA_BD = "SUA_SENHA";

	private static FabricaDeConexao fabricaDeConexao = null;

	private FabricaDeConexao() {
		try {
			Class.forName(NOME_DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Connection obterConexao() {

		Properties props = new Properties();
		props.setProperty("ssl","true");
		props.setProperty("sslfactory","org.postgresql.ssl.NonValidatingFactory");
		props.setProperty("user", USUARIO_BD);
		props.setProperty("password", SENHA_BD);

		try {
			return DriverManager.getConnection(URL_BD, props);
		} catch (SQLException e) {
			// A SQLException é "encapsulada" em uma RuntimeException
			// para desacoplar o código da API de JDBC
			throw new RuntimeException(e);
		}
	}
	
	public static FabricaDeConexao obterInstancia() {
		// A fábrica é um singleton
		if (fabricaDeConexao == null) {
			fabricaDeConexao = new FabricaDeConexao();
		}
		return fabricaDeConexao;
	}

}
