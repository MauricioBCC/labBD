package bd;

import java.sql.*;

import modelo.Professor;

public class ProfessorDAO {

	// a conexão com o banco de dados
	private Connection conexao;

	public ProfessorDAO() {
		this.conexao = FabricaDeConexao.obterInstancia().obterConexao();
	}

	public void adiciona(Professor professor) {
		String sql = "INSERT INTO professor "
				+ "(idprof, nomeprof, iddepto)" + " VALUES (?,?,?)";

		try {
			// prepared statement para inserção
			PreparedStatement stmt = conexao.prepareStatement(sql);

			// seta os valores

			stmt.setLong(1, professor.getIdprof());
			stmt.setString(2, professor.getNomeprof());
			stmt.setInt(3, professor.getIddepto());

			// executa
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			// A SQLException é "encapsulada" em uma RuntimeException
			// para desacoplar o código da API de JDBC
			throw new RuntimeException(e);
		}
	}

	public void altera(Professor professor) {
		String sql = "UPDATE professor SET nomeprof=?, iddepto=? WHERE idprof=?";
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, professor.getNomeprof());
			stmt.setInt(2, professor.getIddepto());
			stmt.setLong(3, professor.getIdprof());

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void remove(Professor professor) {
		try {
			PreparedStatement stmt = conexao.prepareStatement("DELETE "
					+ "FROM professor WHERE idprof=?");
			stmt.setLong(1, professor.getIdprof());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
