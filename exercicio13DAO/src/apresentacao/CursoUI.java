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

	public static void main(String[] args) {
		CursoUI cursoUi = new CursoUI();
		Scanner sc = new Scanner(System.in);
		int opcao;

		while(true) {
			System.out.println("Entre com o numero da opção:");
			System.out.println("(1) adicionar aluno");
			System.out.println("(2) altera aluno:");
			System.out.println("(3)remover aluno:");
			System.out.println("(4) adicionar professor");
			System.out.println("(5) altera professor:");
			System.out.println("(6) remover professor:");
			System.out.println("(7) adicionar curso");
			System.out.println("(8) altera curso:");
			System.out.println("(9) remover curso:");

			opcao = sc.nextInt();

			switch(opcao) {
				case 1: cursoUi.adicionaAluno();
						break;
			}
		} 


	}
}