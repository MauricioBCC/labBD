package modelo;

public class Matriculado {

	// chave primaria composta (nroaluno, nomecurso)
	private Long nroaluno; 
	private String nomecurso;

	public Long getNroaluno() {
		return this.nroaluno;
	}

	public void setNroaluno(Long novo) {
		this.nroaluno = novo;
	}

	public String getNomecurso() {
		return this.nomecurso;
	}

	public void setNomecurso(String novo) {
		this.nomecurso = novo;
	}
}