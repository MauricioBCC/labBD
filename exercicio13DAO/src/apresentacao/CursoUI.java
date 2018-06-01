package apresentacao;

import dominio.ServicoCursos;
import java.util.Scanner;

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
		String nome = sc.next();

		System.out.println("Digite a formacao do aluno");
		String formacao = sc.next();

		System.out.println("Digite o nivel do aluno");
		String nivel = sc.next();

		System.out.println("Digite a idade do aluno");
		int idade = sc.nextInt();

		System.out.println(scursos.insereAluno(nroaluno, nome, formacao, nivel, idade));
	}

	public void alteraAluno() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Digite o numero do aluno");
		Long nroaluno = sc.nextLong();

		System.out.println("Digite o nome do aluno");
		String nome = sc.next();

		System.out.println("Digite a formacao do aluno");
		String formacao = sc.next();

		System.out.println("Digite o nivel do aluno");
		String nivel = sc.next();

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
		String nome = sc.next();

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
		String nome = sc.next();

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
		String nome = sc.next();

		System.out.println("Digite o horario");
		String horario = sc.next();

		System.out.println("Digite a sala onde o curso e ministrado");
		String sala = sc.next();

		System.out.println("Digite o numero do professor");
		Long idprof = sc.nextLong();

		System.out.println(scursos.insereCurso(nome, horario, sala, idprof));
	}

	public void alteraCurso() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Digite o nome do curso");
		String nome = sc.next();

		System.out.println("Digite o horario");
		String horario = sc.next();

		System.out.println("Digite a sala onde o curso e ministrado");
		String sala = sc.next();

		System.out.println("Digite o numero do professor");
		Long idprof = sc.nextLong();

		System.out.println(scursos.alteraCurso(nome, horario, sala, idprof));
	}

	public void removeCurso() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Digite o nome do curso");
		String nome = sc.next();

		System.out.println(scursos.removeCurso(nome));
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
			System.out.println("(10) Sair");

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
				case 10: return;
				default: System.out.println("Opcao invalida");;
			}
			System.out.println();
		} 


	}
}