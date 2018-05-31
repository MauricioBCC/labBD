package bd;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import modelo.Matriculado;

public class MatriculadoDAO {

	// a conexão com o banco de dados
	private Connection conexao;

	public MatriculadoDAO() {
		this.conexao = FabricaDeConexao.obterInstancia().obterConexao();
	}

	public void adiciona(Matriculado matriculado) {
		String sql = "INSERT INTO matriculado "
				+ "(nroaluno, nomecurso)" + " VALUES (?,?)";

		try {
			// prepared statement para inserção
			PreparedStatement stmt = conexao.prepareStatement(sql);

			// seta os valores
			stmt.setLong(1, matriculado.getNroaluno());
			stmt.setString(2, matriculado.getNomecurso());

			// executa
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			// A SQLException é "encapsulada" em uma RuntimeException
			// para desacoplar o código da API de JDBC
			throw new RuntimeException(e);
		}
	}


	// Retorna uma lista de matriculados tal que o curso é o argumento "nomecurso"
	public List<Matriculado> listaMatriculado(String nomecurso) {
		try {
			List<Matriculado> matriculadoLista = new ArrayList<Matriculado>();
			Matriculado matriculado = null;
			
			PreparedStatement stmt = conexao.prepareStatement("SELECT * "
					+ "FROM matriculado WHERE nomecurso = ?");
			stmt.setString(1, nomecurso);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				matriculado = new Matriculado();
				matriculado.setNroaluno(rs.getLong("nroaluno"));
				matriculado.setNomecurso(rs.getString("nomecurso"));

				matriculadoLista.add(matriculado);

			}
			rs.close();
			stmt.close();
			return matriculadoLista;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
