package br.com.five.travels.modelo;

public class Rota {
	private Integer id;
	private String nome;

	public Rota(String nome) {
		this.nome = nome;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	@Override
	public String toString() {

		return "ID: " + id + " | " + nome;
	}
	
	@Override
	public boolean equals(Object obj) {
		Rota rota = (Rota) obj;
		return this.nome.equals(rota.getNome());
	}
	
	@Override
	public int hashCode() {
		return getNome().hashCode();
	}

}
