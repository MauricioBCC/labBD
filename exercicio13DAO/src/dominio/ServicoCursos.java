package dominio;

import modelo.Aluno;
import modelo.Professor;
import modelo.Curso;
import modelo.Matriculado;

import bd.AlunoDAO;
import bd.ProfessorDAO;
import bd.CursoDAO;
import bd.MatriculadoDAO;

import java.lang.RuntimeException;
import java.util.List;

public class ServicoCursos {
	AlunoDAO alunoDao = null;
	ProfessorDAO professorDao = null;
	CursoDAO cursoDao = null;
	MatriculadoDAO matriculadoDao = null;
	public final int NOME_CURSO_PARTE = 1;
	public final int NOME_CURSO_EXATO = 2;
	public final int NOME_PROF_PARTE = 1;
	public final int NOME_PROF_EXATO = 2;


	public String insereAluno(Long nroaluno, String nomealuno, 
		String formacao, String nivel, int idade) {
		Aluno aluno = new Aluno();
		aluno.setNroaluno(nroaluno);
		aluno.setNomealuno(nomealuno);
		aluno.setFormacao(formacao);
		aluno.setNivel(nivel);
		aluno.setIdade(idade);

		if(alunoDao == null)
			alunoDao = new AlunoDAO();

		try {
			alunoDao.adiciona(aluno);
		}
		catch (RuntimeException e) {
			return("Erro ao inserir aluno");
		}

		return("Aluno adicionado com sucesso");
	}

	public String alteraAluno(Long nroaluno, String nomealuno, 
		String formacao, String nivel, int idade) {
		Aluno aluno = new Aluno();
		aluno.setNroaluno(nroaluno);
		aluno.setNomealuno(nomealuno);
		aluno.setFormacao(formacao);
		aluno.setNivel(nivel);
		aluno.setIdade(idade);

		if(alunoDao == null)
			alunoDao = new AlunoDAO();

		try {
			alunoDao.altera(aluno);
		}
		catch (RuntimeException e) {
			return("Erro ao alterar aluno");
		}

		return("Aluno alterado com sucesso");
	}

	public String removeAluno(Long nroaluno) {
		if(alunoDao == null)
			alunoDao = new AlunoDAO();

		try {
			alunoDao.remove(nroaluno);
		}
		catch (RuntimeException e) {
			return("Erro ao excluir aluno");
		}

		return("Aluno excluido com sucesso");
	}

	public String insereProfessor(Long idprof, String nomeprof,
		 int iddepto) {
		Professor professor = new Professor();
		professor.setIdprof(idprof);
		professor.setNomeprof(nomeprof);
		professor.setIddepto(iddepto);

		if(professorDao == null)
			professorDao = new ProfessorDAO();

		try {
			professorDao.adiciona(professor);
		}
		catch (RuntimeException e) {
			return("Erro ao inserir professor");
		}

		return("Professor adicionado com sucesso");
	}

	public String alteraProfessor(Long idprof, String nomeprof,
		 int iddepto) {
		Professor professor = new Professor();
		professor.setIdprof(idprof);
		professor.setNomeprof(nomeprof);
		professor.setIddepto(iddepto);

		if(professorDao == null)
			professorDao = new ProfessorDAO();

		try {
			professorDao.altera(professor);
		}
		catch (RuntimeException e) {
			return("Erro ao alterar professor");
		}

		return("Professor alterado com sucesso");
	}

	public String removeProfessor(Long idprof) {
		if(professorDao == null)
			professorDao = new ProfessorDAO();

		try {
			professorDao.remove(idprof);
		}
		catch (RuntimeException e) {
			return("Erro ao remover professor");
		}

		return("Professor removido com sucesso");
	}

	public String insereCurso(String nome, String horario,
	 String sala, Long idprof) {
		Curso curso = new Curso();
		curso.setNome(nome);
		curso.setHorario(horario);
		curso.setSala(sala);
		curso.setIdprof(idprof);

		if(cursoDao == null)
			cursoDao = new CursoDAO();

		try {
			cursoDao.adiciona(curso);
		}
		catch (RuntimeException e) {
			return("Erro ao inserir curso");
		}

		return("Curso adicionado com sucesso");
	}

	public String alteraCurso(String nome, String horario,
	 String sala, Long idprof) {
		Curso curso = new Curso();
		curso.setNome(nome);
		curso.setHorario(horario);
		curso.setSala(sala);
		curso.setIdprof(idprof);

		if(cursoDao == null)
			cursoDao = new CursoDAO();

		try {
			cursoDao.altera(curso);
		}
		catch (RuntimeException e) {
			return("Erro ao alterar curso");
		}

		return("Curso alterado com sucesso");
	}

	public String removeCurso(String nome) {
		if(cursoDao == null)
			cursoDao = new CursoDAO();

		try {
			cursoDao.remove(nome);
		}
		catch (RuntimeException e) {
			return("Erro ao remover curso");
		}

		return("Curso removido com sucesso");
	}

	public List<Curso> listaCursosNome(String nomeCurso, int operacaoCurso) {
		List<Curso> listaCursos = null;
		if(cursoDao == null)
			cursoDao = new CursoDAO();

		try {
			if(operacaoCurso == NOME_CURSO_PARTE) {
				listaCursos = cursoDao.cursosNome(nomeCurso, cursoDao.NOME_CURSO_PARTE);
			}
			else if(operacaoCurso == NOME_CURSO_EXATO) {
				listaCursos = cursoDao.cursosNome(nomeCurso, cursoDao.NOME_CURSO_EXATO);
			}
		}
		catch(RuntimeException e) {
			return null;
		}
		return listaCursos;
	}

	public List<Curso> listaCursosProf(String nomeProf, int operacaoProf) {
		List<Curso> listaCursos = null;
		if(cursoDao == null)
			cursoDao = new CursoDAO();

		try {
			if(operacaoProf == NOME_PROF_PARTE) {
				listaCursos = cursoDao.cursosProf(nomeProf, cursoDao.NOME_PROF_PARTE);
			}
			else if(operacaoProf == NOME_PROF_EXATO) {
				listaCursos = cursoDao.cursosProf(nomeProf, cursoDao.NOME_PROF_EXATO);
			}
		}
		catch(RuntimeException e) {
			return null;
		}
		return listaCursos;
	}

	public List<Curso> listaCursosNomeCursoProf(String nomeCurso, int operacaoCurso, 
		String nomeProf, int operacaoProf) {
		List<Curso> listaCursos = null;
		if(cursoDao == null)
			cursoDao = new CursoDAO();

		try {
			if(operacaoCurso == NOME_CURSO_PARTE && operacaoProf == NOME_PROF_PARTE) {
				listaCursos = cursoDao.cursosNomeProf(nomeCurso, nomeProf, cursoDao.NOME_CURSO_PARTE, 
					cursoDao.NOME_PROF_PARTE);
			}
			else if(operacaoCurso == NOME_CURSO_PARTE && operacaoProf == NOME_PROF_EXATO) {
				listaCursos = cursoDao.cursosNomeProf(nomeCurso, nomeProf, cursoDao.NOME_CURSO_PARTE, 
					cursoDao.NOME_PROF_EXATO);
			}
			else if(operacaoCurso == NOME_CURSO_EXATO && operacaoProf == NOME_PROF_EXATO) {
				listaCursos = cursoDao.cursosNomeProf(nomeCurso, nomeProf, cursoDao.NOME_CURSO_EXATO, 
					cursoDao.NOME_PROF_EXATO);
			}
		}
		catch(RuntimeException e) {
			return null;
		}
		return listaCursos;
	}


	public List<Aluno> listaAlunoMatriculado(String nomecurso) {
		List<Aluno> listaAlunos = null;

		if(matriculadoDao == null)
			matriculadoDao = new MatriculadoDAO();

		try {
			listaAlunos = matriculadoDao.listaAlunoMatriculado(nomecurso);
		}
		catch(RuntimeException e) {
			return null;
		}
		return listaAlunos;
	}
}