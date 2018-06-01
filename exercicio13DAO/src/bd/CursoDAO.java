package bd;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import modelo.Curso;

public class CursoDAO {

	// a conexão com o banco de dados
	private Connection conexao;

	public CursoDAO() {
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
		String sql = "UPDATE curso SET horario = ?, sala = ?, idprof = ? WHERE nome = ?";
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

	public void remove(String nomecurso) {
		try {
			PreparedStatement stmt = conexao.prepareStatement("DELETE "
					+ "FROM curso WHERE idprof = ?");
			stmt.setString(1, nomecurso);
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}


	// Retorna uma lista de cursos que tenham contidos em seu nome 
	// o argumento "nomeCurso"
	public List<Curso> cursosNomeCurso(String nomeCurso) {
		try {
			List<Curso> cursoLista = new ArrayList<Curso>();
			Curso curso = null;
			String padraoBusca = "%" + nomeCurso + "%";
			
			PreparedStatement stmt = conexao.prepareStatement("SELECT * "
					+ "FROM curso WHERE nome LIKE ?");
			stmt.setString(1, padraoBusca);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				curso = new Curso();
				curso.setNome(rs.getString("nome"));
				curso.setHorario(rs.getString("horario"));
				curso.setSala(rs.getString("sala"));
				curso.setIdprof(rs.getLong("idprof"));

				cursoLista.add(curso);

			}
			rs.close();
			stmt.close();
			return cursoLista;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// Retorna uma lista de cursos que o nome do professor contenha
	// a string nomeProf
	public List<Curso> cursosNomeProf(String nomeProf) {
		try {
			List<Curso> cursoLista = new ArrayList<Curso>();
			Curso curso = null;
			String padraoBusca = "%" + nomeProf + "%";
			
			PreparedStatement stmt = conexao.prepareStatement("SELECT curso.* "
					+ "FROM curso, professor WHERE curso.idprof = professor.idprof "
					 + "AND nomeprof LIKE ?");
			stmt.setString(1, padraoBusca);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				curso = new Curso();
				curso.setNome(rs.getString("nome"));
				curso.setHorario(rs.getString("horario"));
				curso.setSala(rs.getString("sala"));
				curso.setIdprof(rs.getLong("idprof"));

				cursoLista.add(curso);

			}
			rs.close();
			stmt.close();
			return cursoLista;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// Retorna uma lista de cursos que o nome do professor contenha
	// a string nomeCurso e o nome do curso contenha a string nomeCurso
	public List<Curso> cursosNomeProf(String nomeCurso, String nomeProf) {
		try {
			List<Curso> cursoLista = new ArrayList<Curso>();
			Curso curso = null;
			String padraoCurso = "%" + nomeCurso + "%";
			String padraoProf = "%" + nomeProf + "%";
			
			PreparedStatement stmt = conexao.prepareStatement("SELECT curso.* "
					+ "FROM curso, professor WHERE nome LIKE ? AND " +
					"curso.idprof = professor.idprof AND nomeprof LIKE ?");
			stmt.setString(1, padraoCurso);
			stmt.setString(1, padraoProf);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				curso = new Curso();
				curso.setNome(rs.getString("nome"));
				curso.setHorario(rs.getString("horario"));
				curso.setSala(rs.getString("sala"));
				curso.setIdprof(rs.getLong("idprof"));

				cursoLista.add(curso);

			}
			rs.close();
			stmt.close();
			return cursoLista;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
