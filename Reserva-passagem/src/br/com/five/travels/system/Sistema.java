package br.com.five.travels.system;

import java.io.IOException;

import br.com.five.travels.modelo.SistemaReservas;

public class Sistema {
	
	public static void main(String[] args) throws IOException {
		SistemaReservas sistemaReservas = new SistemaReservas();
		sistemaReservas.iniciar();
	}
}
