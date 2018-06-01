package bd;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import modelo.Curso;

public class CursoDAO {

	// a conexão com o banco de dados
	private Connection conexao;
	public final int NOME_CURSO_PARTE = 1;
	public final int NOME_CURSO_EXATO = 2;
	public final int NOME_PROF_PARTE = 1;
	public final int NOME_PROF_EXATO = 2;


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
					+ "FROM curso WHERE nome = ?");
			stmt.setString(1, nomecurso);
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Curso> cursosNome(String nomeCurso, int operacaoCurso) {
		try {
			List<Curso> cursoLista = new ArrayList<Curso>();
			Curso curso = null;
			String buscaCurso = null;
			String padraoCurso = null;

			if(operacaoCurso == NOME_CURSO_PARTE) {
				buscaCurso = "nome LIKE ";
				padraoCurso = "%" + nomeCurso + "%";
			}
			else if(operacaoCurso == NOME_CURSO_EXATO) {
				buscaCurso = "nome = ";
				padraoCurso = nomeCurso;
			}		

			/* prepara a query */
			String sql = "SELECT curso.* FROM curso WHERE "
			+ buscaCurso + " ?";

			PreparedStatement stmt = conexao.prepareStatement(sql);

			stmt.setString(1, padraoCurso);

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

	public List<Curso> cursosProf(String nomeProf, int operacaoProf) {
		try {
			List<Curso> cursoLista = new ArrayList<Curso>();
			Curso curso = null;
			String buscaProf = null;
			String padraoProf = null;

			if(operacaoProf == NOME_PROF_PARTE) {
				buscaProf = "nomeprof LIKE ";
				padraoProf = "%" + nomeProf + "%";
			}
			else if(operacaoProf == NOME_PROF_EXATO) {
				buscaProf = "nomeprof = ";
				padraoProf = nomeProf;
			}		

			/* prepara a query */
			String sql = "SELECT curso.* FROM curso, professor WHERE "
			+ "curso.idprof = professor.idprof AND " + buscaProf + " ?";

			PreparedStatement stmt = conexao.prepareStatement(sql);

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

	public List<Curso> cursosNomeProf(String nomeCurso, String nomeProf, 
		int operacaoCurso, int operacaoProf) {
		try {
			List<Curso> cursoLista = new ArrayList<Curso>();
			Curso curso = null;
			String buscaCurso = null;
			String padraoCurso = null;
			String buscaProf = null;
			String padraoProf = null;

			if(operacaoCurso == NOME_CURSO_PARTE) {
				buscaCurso = "nome LIKE ";
				padraoCurso = "%" + nomeCurso + "%";
			}
			else if(operacaoCurso == NOME_CURSO_EXATO) {
				buscaCurso = "nome = ";
				padraoCurso = nomeCurso;
			}

			if(operacaoProf == NOME_PROF_PARTE) {
				buscaProf = "nomeprof LIKE ";
				padraoProf = "%" + nomeProf + "%";
			}
			else if(operacaoProf == NOME_PROF_EXATO) {
				buscaProf = "nomeprof = ";
				padraoProf = nomeProf;
			}			

			/* prepara a query */
			String sql = "SELECT curso.* FROM curso, professor WHERE "
			+ "curso.idprof = professor.idprof AND " + buscaCurso + " ? AND "
			+ buscaProf + " ? ";

			PreparedStatement stmt = conexao.prepareStatement(sql);

			stmt.setString(1, padraoCurso);
			stmt.setString(2, padraoProf);

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
