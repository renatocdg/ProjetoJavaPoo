package dao;

import modelo.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;
import java.io.FileReader;
import java.util.List;

public class UsuarioDao {

	private static final String ARQUIVO = "usuarios.json";
	private static final Gson gson = new Gson();

	public static List<Usuario> carregarTodos() {
		try (FileReader reader = new FileReader(ARQUIVO)) {
			return gson.fromJson(reader, new TypeToken<List<Usuario>>() {
			}.getType());
		} catch (Exception e) {
			return List.of();
		}
	}

	public static Usuario buscarPorMatricula(String matricula) {
		for (Usuario u : carregarTodos()) {
			if (u.getMatricula().equalsIgnoreCase(matricula)) {
				return u;
			}
		}
		return null;
	}
}