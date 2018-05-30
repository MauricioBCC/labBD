package bd;

import java.sql.*;
/*import java.util.ArrayList;
import java.util.List;*/

import modelo.Curso;

public class CursoDAO {

	// a conexão com o banco de dados
	private Connection conexao;

	public ContatoDAO() {
		this.conexao = FabricaDeConexao.obterInstancia().obterConexao();
	}

	public void adiciona(Curso curso) {
		String sql = "INSERT INTO curso "
				+ "(nome, horario, sala, idprof)" + " VALUES (?,?,?,?)";

		try {
			// prepared statement para inserção
			PreparedStatement stmt = conexao.prepareStatement(sql);

			// seta os valores

			stmt.setString(1, curso.getNome());
			stmt.setString(2, curso.getHorario());
			stmt.setString(3, curso.getSala());
			stmt.setLong(4, curso.getIdprof());


			// executa
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			// A SQLException é "encapsulada" em uma RuntimeException
			// para desacoplar o código da API de JDBC
			throw new RuntimeException(e);
		}
	}

	public void altera(Curso curso) {
		String sql = "UPDATE curso SET horario=?, sala=?, idprof=? WHERE nome=?";
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, curso.getHorario());
			stmt.setString(2, curso.getSala());
			stmt.setLong(3, curso.getIdprof());
			stmt.setString(4, curso.getNome());

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void remove(Curso curso) {
		try {
			PreparedStatement stmt = conexao.prepareStatement("DELETE "
					+ "FROM curso WHERE idprof=?");
			stmt.setLong(1, curso.getIdprof());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

/*	public curso obter(int id) {
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