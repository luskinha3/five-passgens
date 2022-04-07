package br.com.five.travels.modelo;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Usuario {
	private String nome;
	private String cpf;
	//private Set<Rota> reservas = new HashSet();
	private Map<Integer, Rota> reservas = new HashMap<>();

	public Usuario(String nome, String cpf) {
		if (nome.equals(null) | nome.trim().isEmpty()) {
			throw new NullPointerException("Nome invalido");
		}
		if (cpf.equals(null) | cpf.trim().isEmpty()) {
			throw new NullPointerException("cpf invalido");
		}
		this.nome = nome;
		this.cpf = cpf;

	}

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}

	
	public void mostrarReservas() {
		Collection<Rota> values = reservas.values();
		values.forEach(rota -> {System.out.println(rota);});
	}
	
	public Map<Integer, Rota> getReservas() {
		return Collections.unmodifiableMap(reservas);
	}

	public void reservar(Rota rota) {
		reservas.put(rota.getId(),rota);
	}

	public void cancelar(Rota rota) {
		reservas.remove(rota.getId());
	}

	@Override
	public boolean equals(Object obj) {
		Usuario usuario = (Usuario) obj;
		return this.cpf.equals(usuario.getCpf());
	}

	@Override
	public int hashCode() {
		return this.cpf.hashCode();
	}

}
