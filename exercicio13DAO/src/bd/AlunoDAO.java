package bd;

import java.sql.*;
/*import java.util.ArrayList;
import java.util.List;*/

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
		String sql = "UPDATE aluno SET nomealuno=?, formacao=?, nivel=?"
				+ "idade=? WHERE nroaluno=?";
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

	public void remove(Aluno aluno) {
		try {
			PreparedStatement stmt = conexao.prepareStatement("DELETE "
					+ "FROM aluno WHERE nroaluno=?");
			stmt.setLong(1, aluno.getNroaluno());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

/*	public Aluno obter(int id) {
		try {
			Contato contato = null;
			
			PreparedStatement stmt = conexao.prepareStatement("select * "
					+ "from contatos where id=?");
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				contato = new Contato();
				contato.setId(rs.getLong("id"));
				contato.setNome(rs.getString("nome"));
				contato.setEmail(rs.getString("email"));
				contato.setEndereco(rs.getString("endereco"));

				// montando a data através do Calendar
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("dataNascimento"));
				contato.setDataNascimento(data);
			}
			rs.close();
			stmt.close();
			return contato;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Contato> obterLista() {
		try {
			List<Contato> contatos = new ArrayList<Contato>();
			PreparedStatement stmt = this.conexao
					.prepareStatement("select * from contatos");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				// criando o objeto Contato
				Contato contato = new Contato();
				contato.setId(rs.getLong("id"));
				contato.setNome(rs.getString("nome"));
				contato.setEmail(rs.getString("email"));
				contato.setEndereco(rs.getString("endereco"));

				// montando a data através do Calendar
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("dataNascimento"));
				contato.setDataNascimento(data);

				// adicionando o objeto à lista
				contatos.add(contato);
			}
			rs.close();
			stmt.close();
			return contatos;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}*/

}
