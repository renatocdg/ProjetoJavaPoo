package projeto_poo;

import java.util.ArrayList;
import java.util.List;

public class ControleUsuario {
	private List<Usuario> ListaDeUsuarios;

//Construtor
	public ControleUsuario() {
		this.ListaDeUsuarios = new ArrayList<>();
	}

	public void CadastrarUsuario(Usuario usuario) {

		for (Usuario i : ListaDeUsuarios) {
			if (i.getMatricula().equals(usuario.getMatricula())) {
				System.out.println("Já existe um usuário com essa matrícula já cadastrado.");
				return;
			}
		}
		ListaDeUsuarios.add(usuario);
		System.out.println("Usuário cadastrado com sucesso.");
	}

	public void ListarUsuarios() {

		if (ListaDeUsuarios.isEmpty()) {
			System.out.println("Nenhum usuário cadastrado.");
			return;
		}
		for (Usuario i : ListaDeUsuarios) {
			System.out.println(i);
		}
	}

	public void EditarUsuario(String Matricula, String NovoTelefone, String NovoEmail) {

		for (Usuario i : ListaDeUsuarios) {
			if (i.getMatricula().equals(Matricula)) {
				i.setTelefone(NovoTelefone);
				i.setEmail(NovoEmail);
				System.out.println("Usuário atualizado com sucesso.");
				return;
			} else {
				System.out.println("Usuário não encontrado.");
			}
		}
	}

	public void excluirUsuario(String Matricula) {
		for (Usuario i : ListaDeUsuarios) {
			if (i.getMatricula().equals(Matricula)) {
				ListaDeUsuarios.remove(i);
				System.out.println("Usuário removido com sucesso.");
				return;
			} else {
				System.out.println("Usuário não encontrado.");
			}
		}

	}
}