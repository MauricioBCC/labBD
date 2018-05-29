package modelo;

public class Curso {

	private String nome;
	private String horario;
	private String sala;
	private Long idprof;

	public String getNome() {
		return this.nome;
	}

	public void setNome(String novo) {
		this.nome = novo;
	}

	public String getHorario() {
		return this.horario;
	}

	public void setHorario(String novo) {
		this.horario = novo;
	}

	public String getSala() {
		return this.sala;
	}

	public void setSala(String novo) {
		this.sala = novo;
	}

	public Long getIdprof() {
		return this.idprof;
	}

	public void setIdprof(Long novo) {
		this.idprof = novo;
	}
}