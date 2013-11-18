package entidades;

public class Coordenacao {

	private int id;
	private String nome;
	private Requerente requerente;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Requerente getRequerente() {
		return requerente;
	}

	public void setRequerente(Requerente requerente) {
		this.requerente = requerente;
	}
}
