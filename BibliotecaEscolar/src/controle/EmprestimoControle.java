package controle;

import java.util.List;
import modelo.*;
import dao.*;

public class EmprestimoControle {
	private static List<Emprestimo> lista = EmprestimoDao.carregar();
	private static int proximoId = lista.size() + 1;

	public static boolean registrarEmprestimo(String matricula, String codigo) {

		// 🔴 AQUI CORRIGE O ERRO: busca as variáveis ANTES de usar
		Usuario usuario = UsuarioDao.buscarPorMatricula(matricula);
		Obra obra = ObraDao.buscarPorCodigo(codigo);

		if (usuario == null || obra == null) {
			System.out.println("Usuário ou obra não encontrados.");
			return false;
		}

		if (!obra.getStatus().equalsIgnoreCase("disponível")) {
			System.out.println("Obra já emprestada.");
			return false;
		}

		Emprestimo i = new Emprestimo(obra, usuario);
		lista.add(i);
		obra.setStatus("emprestado");

		EmprestimoDao.salvar(lista);

		List<Obra> obras = ObraDao.carregarTodas();
		for (Obra o : obras) {
			if (o.getCodigo() == obra.getCodigo()) {
				o.setStatus("emprestado");
				break;
			}
		}
		ObraDao.salvarTodas(obras);

		return true;
	}

	public static boolean registrarDevolucao(String matricula, String codigo) {

		for (Emprestimo i : lista) {
			if (i.corresponde(matricula, codigo)) {
				i.setDevolvido(true);
				EmprestimoDao.salvar(lista);

				Obra obra = ObraDao.buscarPorCodigo(codigo);
				if (obra != null) {
					obra.setStatus("disponível");

					List<Obra> obras = ObraDao.carregarTodas();
					for (Obra o : obras) {
						if (o.getCodigo() == obra.getCodigo()) {
							o.setStatus("disponível");
							break;
						}
					}
					ObraDao.salvarTodas(obras);
				}
				return true;
			}
		}
		return false;
	}
}