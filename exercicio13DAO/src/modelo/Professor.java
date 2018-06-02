package modelo;

public class Professor {

	// chave primaria  (idprof)
	private Long idprof; 
	private String nomeprof;
	private int iddepto;

	public Long getIdprof() {
		return this.idprof;
	}

	public void setIdprof(Long novo) {
		this.idprof = novo;
	}

	public String getNomeprof() {
		return this.nomeprof;
	}

	public void setNomeprof(String novo) {
		this.nomeprof = novo;
	}

	public int getIddepto() {
		return this.iddepto;
	}

	public void setIddepto(int iddepto) {
		this.iddepto = iddepto;
	}
}