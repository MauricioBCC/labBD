package bd;

import java.sql.*;

import modelo.Aluno;

public class AlunoDAO {

	// a conexão com o banco de dados
	private Connection conexao;

	public AlunoDAO() {
		this.conexao = FabricaDeConexao.obterInstancia().obterConexao();
	}

	public void adiciona(Aluno aluno) {
		String sql = "INSERT INTO aluno "
				+ "(nroaluno, nomealuno, formacao, nivel, idade)" + " VALUES (?,?,?,?,?)";

		try {
			// prepared statement para inserção
			PreparedStatement stmt = conexao.prepareStatement(sql);

			// seta os valores

			stmt.setLong(1, aluno.getNroaluno());
			stmt.setString(2, aluno.getNomealuno());
			stmt.setString(3, aluno.getFormacao());
			stmt.setString(4, aluno.getNivel());
			stmt.setInt(5, aluno.getIdade());

			// executa
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			// A SQLException é "encapsulada" em uma RuntimeException
			// para desacoplar o código da API de JDBC
			throw new RuntimeException(e);
		}
	}

	public void altera(Aluno aluno) {
		String sql = "UPDATE aluno SET nomealuno = ?, formacao = ?, nivel = ?,"
				+ "idade = ? WHERE nroaluno = ?";
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, aluno.getNomealuno());
			stmt.setString(2, aluno.getFormacao());
			stmt.setString(3, aluno.getNivel());			
			stmt.setInt(4, aluno.getIdade());
			stmt.setLong(5, aluno.getNroaluno());

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void remove(Long nroaluno) {
		try {
			PreparedStatement stmt = conexao.prepareStatement("DELETE "
					+ "FROM aluno WHERE nroaluno = ?");
			stmt.setLong(1, nroaluno);
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}

