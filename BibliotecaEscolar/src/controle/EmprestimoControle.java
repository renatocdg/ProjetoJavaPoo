package controle;

import modelo.Emprestimo;
import dao.EmprestimoDao;
import java.util.List;

public class EmprestimoControle {

	private static List<Emprestimo> lista = EmprestimoDao.carregar();
	private static int proximoId = lista.size() + 1;

	public static void registrar(String matricula, String codigoObra, int dias) {
		Emprestimo i = new Emprestimo(obra, usuario);
		lista.add(i);
		EmprestimoDao.salvar(lista);
	}

	public static List<Emprestimo> listarTodos() {
		return lista;
	}
}
