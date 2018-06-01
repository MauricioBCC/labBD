package apresentacao;

import dominio.ServicoCursos;

import modelo.Aluno;
import modelo.Curso;

import java.util.Scanner;
import java.util.List;


public class CursoUI {
	public ServicoCursos scursos;

	public CursoUI() {
		scursos = new ServicoCursos();
	}

	public void adicionaAluno() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Digite o numero do aluno");
		Long nroaluno = sc.nextLong();

		System.out.println("Digite o nome do aluno");
		sc.useDelimiter(System.getProperty("line.separator"));
		String nome = sc.next();

		System.out.println("Digite a formacao do aluno");
		String formacao = sc.next();

		System.out.println("Digite o nivel do aluno");
		String nivel = sc.next();
		sc.reset();

		System.out.println("Digite a idade do aluno");
		int idade = sc.nextInt();

		System.out.println(scursos.insereAluno(nroaluno, nome, formacao, nivel, idade));
	}

	public void alteraAluno() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Digite o numero do aluno");
		Long nroaluno = sc.nextLong();

		System.out.println("Digite o nome do aluno");
		sc.useDelimiter(System.getProperty("line.separator"));
		String nome = sc.next();

		System.out.println("Digite a formacao do aluno");
		String formacao = sc.next();

		System.out.println("Digite o nivel do aluno");
		String nivel = sc.next();				
		sc.reset();

		System.out.println("Digite a idade do aluno");
		int idade = sc.nextInt();

		System.out.println(scursos.alteraAluno(nroaluno, nome, formacao, nivel, idade));
	}

	public void removeAluno() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Digite o numero do aluno");
		Long nroaluno = sc.nextLong();

		System.out.println(scursos.removeAluno(nroaluno));
	}

	public void adicionaProfessor() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Digite o numero do professor");
		Long idprof = sc.nextLong();

		System.out.println("Digite o nome do professor");		
		sc.useDelimiter(System.getProperty("line.separator"));
		String nome = sc.next();
		sc.reset();

		System.out.println("Digite o numero do departamento" 
			+ "que o professor pertence");
		int iddepto = sc.nextInt();

		System.out.println(scursos.insereProfessor(idprof, nome, iddepto));
	}

	public void alteraProfessor() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Digite o numero do professor");
		Long idprof = sc.nextLong();

		System.out.println("Digite o nome do professor");
		sc.useDelimiter(System.getProperty("line.separator"));
		String nome = sc.next();		
		sc.reset();

		System.out.println("Digite o numero do departamento"  
			+ "que o professor pertence");
		int iddepto = sc.nextInt();

		System.out.println(scursos.alteraProfessor(idprof, nome, iddepto));
	}

	public void removeProfessor() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Digite o numero do professor");
		Long idprof = sc.nextLong();

		System.out.println(scursos.removeProfessor(idprof));
	}

	public void adicionaCurso() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Digite o nome do curso");
		sc.useDelimiter(System.getProperty("line.separator"));
		String nome = sc.next();

		System.out.println("Digite o horario");
		String horario = sc.next();

		System.out.println("Digite a sala onde o curso e ministrado");
		String sala = sc.next();
		sc.reset();

		System.out.println("Digite o numero do professor");
		Long idprof = sc.nextLong();

		System.out.println(scursos.insereCurso(nome, horario, sala, idprof));
	}

	public void alteraCurso() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Digite o nome do curso");
		sc.useDelimiter(System.getProperty("line.separator"));
		String nome = sc.next();

		System.out.println("Digite o horario");
		String horario = sc.next();

		System.out.println("Digite a sala onde o curso e ministrado");
		String sala = sc.next();
		sc.reset();

		System.out.println("Digite o numero do professor");
		Long idprof = sc.nextLong();

		System.out.println(scursos.alteraCurso(nome, horario, sala, idprof));
	}

	public void removeCurso() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Digite o nome do curso");
		sc.useDelimiter(System.getProperty("line.separator"));
		String nome = sc.next();

		System.out.println(scursos.removeCurso(nome));
	}

	public void listaCursosNome() {
		Scanner sc = new Scanner(System.in);
		List<Curso> listaCursos = null;

		System.out.println("Digite 1 para procurar por parte do nome "
			+ "do curso ou 2 para procurar pelo nome exato");
		int escolha = sc.nextInt();

		System.out.println("Digite o nome do curso");
		/* necessario para o delimitador se tornar a quebra de linha em vez do espaco
		(que eh o delimitador default) */
		sc.useDelimiter(System.getProperty("line.separator"));
		String nome = sc.next();
		/* torna novamente o caracter espaco como delimitador*/
		sc.reset();

		if (escolha == 1) {
			listaCursos = scursos.listaCursosNome(nome, 
				scursos.NOME_CURSO_PARTE);
		}
		else if (escolha == 2) {
			listaCursos = scursos.listaCursosNome(nome, 
				scursos.NOME_CURSO_EXATO);
		}

		if (listaCursos != null) {
			for(Curso curso : listaCursos) {
				String dadosCurso = "Nome do curso: " + curso.getNome()
				+ ", horario: " + curso.getHorario()
				+ ", sala: " + curso.getSala()
				+ ", id do professor: " + curso.getIdprof();
				System.out.println(dadosCurso);

				List<Aluno> listaAlunos = scursos.listaAlunoMatriculado(curso.getNome());
				if(listaAlunos != null) {
					for(Aluno aluno : listaAlunos) {						
						String dadosAluno = "Numero do aluno: " + aluno.getNroaluno()
						+ ", nome: " + aluno.getNomealuno()
						+ ", formacao: " + aluno.getFormacao()
						+ ", nivel: " + aluno.getNivel()
						+ ", idade: " + aluno.getIdade();
						System.out.println(dadosAluno);
					}
				}
				System.out.println();
			}
		}

	}

	public void listaCursosProf() {
		Scanner sc = new Scanner(System.in);
		List<Curso> listaCursos = null;

		System.out.println("Digite 1 para procurar por parte do nome "
			+ "do professor ou 2 para procurar pelo nome exato");
		int escolha = sc.nextInt();

		System.out.println("Digite o nome do professor");
		sc.useDelimiter(System.getProperty("line.separator"));
		String nome = sc.next();
		sc.reset();

		if (escolha == 1) {
			listaCursos = scursos.listaCursosProf(nome, 
				scursos.NOME_PROF_PARTE);
		}
		else if (escolha == 2) {
			listaCursos = scursos.listaCursosProf(nome, 
				scursos.NOME_PROF_EXATO);
		}

		if (listaCursos != null) {
			for(Curso curso : listaCursos) {
				String dadosCurso = "Nome do curso: " + curso.getNome()
				+ ", horario: " + curso.getHorario()
				+ ", sala: " + curso.getSala()
				+ ", id do professor: " + curso.getIdprof();
				System.out.println(dadosCurso);

				List<Aluno> listaAlunos = scursos.listaAlunoMatriculado(curso.getNome());
				if(listaAlunos != null) {
					for(Aluno aluno : listaAlunos) {						
						String dadosAluno = "Numero do aluno: " + aluno.getNroaluno()
						+ ", nome: " + aluno.getNomealuno()
						+ ", formacao: " + aluno.getFormacao()
						+ ", nivel: " + aluno.getNivel()
						+ ", idade: " + aluno.getIdade();
						System.out.println(dadosAluno);
					}
				}
				System.out.println();
			}
		}
	}

	public void listaCursosNomeCursoProf() {
		Scanner sc = new Scanner(System.in);
		List<Curso> listaCursos = null;

		/* dados sobre o curso */
		System.out.println("Digite 1 para procurar por parte do nome "
			+ "do curso ou 2 para procurar pelo nome exato");
		int escolhaCurso = sc.nextInt();

		System.out.println("Digite o nome do curso");
		sc.useDelimiter(System.getProperty("line.separator"));
		String nomeCurso = sc.next();
		sc.reset();

		/* dados sobre o professor */
		System.out.println("Digite 1 para procurar por parte do nome "
			+ "do professor ou 2 para procurar pelo nome exato");
		int escolhaProf = sc.nextInt();

		System.out.println("Digite o nome do professor");
		sc.useDelimiter(System.getProperty("line.separator"));
		String nomeProf = sc.next();		
		sc.reset();

		/* busca a lista de cursos */
		if(escolhaCurso == 1 && escolhaProf == 1) {
			listaCursos = scursos.listaCursosNomeCursoProf(nomeCurso, 
				nomeProf, scursos.NOME_CURSO_PARTE, scursos.NOME_PROF_PARTE);
		}
		else if(escolhaCurso == 2 && escolhaProf == 1) {
			listaCursos = scursos.listaCursosNomeCursoProf(nomeCurso, 
				nomeProf, scursos.NOME_CURSO_EXATO, scursos.NOME_PROF_PARTE);
		}
		else if(escolhaCurso == 2 && escolhaProf == 2) {
			listaCursos = scursos.listaCursosNomeCursoProf(nomeCurso, 
				nomeProf, scursos.NOME_CURSO_EXATO, scursos.NOME_PROF_EXATO);
		}

		if (listaCursos != null) {
			for(Curso curso : listaCursos) {
				String dadosCurso = "Nome do curso: " + curso.getNome()
				+ ", horario: " + curso.getHorario()
				+ ", sala: " + curso.getSala()
				+ ", id do professor: " + curso.getIdprof();
				System.out.println(dadosCurso);

				List<Aluno> listaAlunos = scursos.listaAlunoMatriculado(curso.getNome());
				if(listaAlunos != null) {
					for(Aluno aluno : listaAlunos) {						
						String dadosAluno = "Numero do aluno: " + aluno.getNroaluno()
						+ ", nome: " + aluno.getNomealuno()
						+ ", formacao: " + aluno.getFormacao()
						+ ", nivel: " + aluno.getNivel()
						+ ", idade: " + aluno.getIdade();
						System.out.println(dadosAluno);
					}
				}
				System.out.println();
			}
		}
	}

	public static void main(String[] args) {
		CursoUI cursoUi = new CursoUI();
		Scanner sc = new Scanner(System.in);
		int opcao;

		while(true) {
			System.out.println("Entre com o numero da opção:");
			System.out.println("(1) adicionar aluno");
			System.out.println("(2) altera aluno");
			System.out.println("(3) remover aluno");
			System.out.println("(4) adicionar professor");
			System.out.println("(5) altera professor");
			System.out.println("(6) remover professor");
			System.out.println("(7) adicionar curso");
			System.out.println("(8) altera curso");
			System.out.println("(9) remover curso");
			System.out.println("(10) Listar cursos por nome do curso");
			System.out.println("(11) Listar cursos por nome do professor");
			System.out.println("(12) Listar cursos por nome do curso e professor");
			System.out.println("(13) Sair");

			opcao = sc.nextInt();

			switch(opcao) {
				case 1: cursoUi.adicionaAluno();
						break;
				case 2: cursoUi.alteraAluno();
						break;
				case 3: cursoUi.removeAluno();
						break;
				case 4: cursoUi.adicionaProfessor();
						break;
				case 5: cursoUi.alteraProfessor();
						break;
				case 6: cursoUi.removeProfessor();
						break;
				case 7: cursoUi.adicionaCurso();
						break;
				case 8: cursoUi.alteraCurso();
						break;
				case 9: cursoUi.removeCurso();
						break;
				case 10: cursoUi.listaCursosNome();
						break;
				case 11: cursoUi.listaCursosProf();
						break;
				case 12: cursoUi.listaCursosNomeCursoProf();
						break;
				case 13: return;
				default: System.out.println("Opcao invalida");;
			}
			System.out.println();
		} 


	}
}