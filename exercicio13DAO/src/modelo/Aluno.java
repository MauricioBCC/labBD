package modelo;

public class Aluno {

	private Long nroaluno;
	private String nomealuno;
	private String formacao;
	private String nivel;
	private int idade;


	public Long getNroaluno() {
		return this.nroaluno;
	}

	public void setNroaluno(Long novo) {
		this.nroaluno = novo;
	}

	public String getNomeAluno() {
		return this.nomealuno;
	}

	public void setNomeAluno(String novo) {
		this.nomealuno = novo;
	}

	public String getFormacao() {
		return this.formacao;
	}

	public void setFormacao(String novo) {
		this.formacao = novo;
	}

	public String getNivel() {
		return this.nivel;
	}

	public void setNivel(String novo) {
		this.nivel = novo;
	}

	public int getIdade() {
		return this.idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}
}
