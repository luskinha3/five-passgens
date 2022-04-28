package br.com.five.travels.modelo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import br.com.five.travels.exception.RotaException;
import br.com.five.travels.exception.UserException;

public class SistemaReservas {

	private Set<Usuario> cadastrado = new HashSet<>();
	private Map<Integer, Rota> rotas = new HashMap<>();
	private Usuario usuario = null;
	private File script = new File("script.txt");

	public SistemaReservas() {

		this.adicionarRota(new Rota("Rio - São Paulo"));
		this.adicionarRota(new Rota("Manaus - Recife"));
		this.adicionarRota(new Rota("Fortaleza - Paris"));
		this.adicionarRota(new Rota("Natal - Brasilia"));
		this.adicionarRota(new Rota("João Pessoa - Gramado"));
		this.adicionarRota(new Rota("Salvador - Foz do iguaçu"));
	}

	// Ações:
	public void cadastro(Usuario usuario) {
		if (cadastrado.contains(usuario)) {
			throw new UserException("usuario já cadastrado");
		}
		this.cadastrado.add(usuario);
		System.out.println("Usuario " + usuario.getNome() + " cadastrado com sucesso!");
	}

	public void listarRotas() {
		System.out.println("----------------------------------------");
		System.out.println("|Rotas disponíveis|");
		System.out.println("----------------------------------------");
		Collection<Rota> values = rotas.values();
		values.forEach(rota -> {
			System.out.println(rota);
		});
	}

	public void reservarRota(Usuario usuario, Rota rota) {
		usuario.reservar(rota);
		System.out.println("Reserva feita com sucesso! " + "| Suas reservas: ");
		usuario.mostrarReservas();
	}

	public void cancelarReserva(Usuario usuario, Rota rota) {
		if (!cadastrado.contains(usuario)) {
			throw new UserException("Usuario não está logado");
		}
		usuario.cancelar(rota);
		System.out.println("Reserva cancelada com sucesso! " + "| Suas reservas: ");
		usuario.mostrarReservas();
	}

	private void login(Usuario usuario) {
		if (!cadastrado.contains(usuario)) {
			throw new UserException("Algo deu errado. Informações incorretas !");
		}
		this.script = new File("script2.txt");
		this.usuario = usuario;
	}
	
	private void logout() {
		if(!cadastrado.contains(usuario)) {
			throw new UserException("Você não está logado");
		}
		System.out.println("---------------------------------------------------------");
		System.out.println("| Logout feito com sucesso, até a proxima " + usuario.getNome()+" |");
		System.out.println("---------------------------------------------------------");
		this.script = new File("script.txt");
		this.usuario = null;
		
	}

	// auxiliar
	public void adicionarRota(Rota rota1) {
		rota1.setId(rotas.size() + 1);
		rotas.put(rota1.getId(), rota1);

	}
	
	
	// menu de ações
	public void iniciar() throws FileNotFoundException {

		
		while (true) {
			
			Scanner scanner = new Scanner(script);
			while (scanner.hasNextLine()) {
				String linha = scanner.nextLine();
				System.out.println(linha);
			}
			scanner.close();

			if (usuario == null) {
				System.out.println("Aviso: Faça o cadastro e o login para acessar o sistema por completo");
				System.out.println("---------------------------------------------------------------------------");
			} else {
				System.out.println("Usuario Logado: " + usuario.getNome());
				System.out.println("---------------------------------------------------------------------------");
			}
			
			Scanner scanner2 = new Scanner(System.in);
			System.out.println("Escolha sua ação:");
			String entradaString = scanner2.next();
			try {
				Integer entradaInt = Integer.parseInt(entradaString);
				this.acao(entradaInt);
			}catch(Exception e) {
				System.out.println("-----------------------------------------------");
				System.out.println("| " + e.getMessage() + " |");
					
			}			
			

		}
	}

	// chama ações
	public void acao(Integer entradaSt) throws FileNotFoundException {
		
		if (entradaSt > 3 && this.usuario == null) {
			throw new NullPointerException("Ação invalida");
		}
		
		if (entradaSt > 7 || entradaSt < 0){
			throw new NullPointerException("Ação invalida");
		}
		
		
		
		// listar
		if (entradaSt.equals(1)) {
			 this.listarRotas();
			
		}
		// cadastrar
		if (entradaSt.equals(2)) {
			if(usuario != null) {
				throw new NullPointerException("Ação invalida");
			}
			System.out.println("----------------------------------------");
			System.out.println("| Bem vindo ao cadastro |");
			Scanner scanner = new Scanner(System.in);
			System.out.println("----------------------------------------");
			System.out.println("Digite seu nome:");
			String nome = scanner.nextLine();
			System.out.println("----------------------------------------");
			System.out.println("Digite seu CPF:");
			String cpf = scanner.nextLine();
			System.out.println("----------------------------------------");
			this.cadastro(new Usuario(nome, cpf));
			
		}
		// Login
		if (entradaSt.equals(3)) {
			if(usuario != null) {
				throw new NullPointerException("Ação invalida");
			}
			System.out.println("----------------------------------------");
			System.out.println("| Bem vindo ao Login |");
			System.out.println("----------------------------------------");
			Scanner scanner = new Scanner(System.in);
			System.out.println("Digite seu nome:");
			String nome = scanner.nextLine();
			System.out.println("----------------------------------------");
			System.out.println("Digite seu CPF:");
			String cpf = scanner.nextLine();
			this.login(new Usuario(nome, cpf));
			

		}
		// cancelar reserva
		if (entradaSt.equals(4)) {
			if (!cadastrado.contains(usuario)) {
				throw new UserException("Usuario não está logado");
			}
			if (usuario.getReservas().isEmpty()) {
				throw new RotaException("Nenhuma reserva disponivel para ser cancelada");
			}
			System.out.println("----------------------------------------");
			usuario.mostrarReservas();
			System.out.println("----------------------------------------");

			System.out.println("Informe o id da rota que deseja cancelar: ");
			Scanner scanner = new Scanner(System.in);
			Integer id = scanner.nextInt();

			Rota rota = usuario.getReservas().get(id);
			if (rota == null) {
				throw new RotaException("Informe um id valido");
			}
			this.cancelarReserva(this.usuario, rota);

			

		}
		// Listar reservas
		if (entradaSt.equals(5)) {
			if (!cadastrado.contains(usuario)) {
				throw new UserException("Usuario não está logado");
			}
			if (usuario.getReservas().isEmpty()) {
				System.out.println("----------------------------------------");
				throw new UserException("Você não tem nenhum reserva!");
			}

			System.out.println("----------------------------------------");
			System.out.println("Suas reversas:");

			usuario.mostrarReservas();

			
		}
		// reservar
		if (entradaSt.equals(6)) {

			
			if (!cadastrado.contains(usuario)) {
				throw new UserException("Usuario não está logado");
			}
			System.out.println("----------------------------------------");
			this.listarRotas();
			System.out.println("----------------------------------------");

			System.out.println("Informe o id da rota que deseja reservar: ");
			Scanner scanner = new Scanner(System.in);
			Integer id = scanner.nextInt();

			if (id > rotas.size() + 1) {
				scanner.close();
				throw new NullPointerException("rota invalida");
			}
			Rota rota = rotas.get(id);
			this.reservarRota(this.usuario, rota);
			
		}
		// logout
		if (entradaSt.equals(7)) {
			this.logout();
			
		}
		// finalizar programa
		if (entradaSt.equals(0)) {
			System.out.println("Fim do programa, obrigado por utilizar nosso sistema !");
			System.exit(0);
		} 

		

	}

	

}
