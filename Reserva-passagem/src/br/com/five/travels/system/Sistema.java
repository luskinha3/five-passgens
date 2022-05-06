package br.com.five.travels.system;

import java.io.IOException;

import br.com.five.travels.exception.UserException;
import br.com.five.travels.modelo.SistemaReservas;

public class Sistema {
	
	public static void main(String[] args) throws IOException, UserException {
		SistemaReservas sistemaReservas = new SistemaReservas();
		try {
			sistemaReservas.iniciar();
		}catch(UserException ue) {
			System.out.println("-----------------------------------------------");
			System.out.println("| " + ue.getMessage() + " |");
		}finally {
			sistemaReservas.iniciar();
		}
	}
}
