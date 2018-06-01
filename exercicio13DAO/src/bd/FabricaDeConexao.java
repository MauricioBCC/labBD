package bd;

import java.sql.*;
import java.util.Properties;

public class FabricaDeConexao {

	// MODIFIQUE O VALOR DESTAS CONSTANTES COM OS DADOS DO SEU BD
	public static final String NOME_DRIVER = "org.postgresql.Driver";
	public static final String URL_BD = "jdbc:postgresql:exercicio13";
	public static final String USUARIO_BD = "postgres";
	public static final String SENHA_BD = "postgres";

	private static FabricaDeConexao fabricaDeConexao = null;

	private FabricaDeConexao() {
		try {
			Class.forName(NOME_DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Connection obterConexao() {
		try {
			return DriverManager.getConnection(URL_BD, USUARIO_BD, SENHA_BD);
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
