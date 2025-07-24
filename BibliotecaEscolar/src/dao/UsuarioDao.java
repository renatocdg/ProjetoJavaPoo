package dao;

import modelo.Usuario;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDao {

	private static final String ARQUIVO = "usuarios.json";
	private static final Gson gson = new Gson();

	public static List<Usuario> carregar() {
		try (FileReader reader = new FileReader(ARQUIVO)) {
			return gson.fromJson(reader, new TypeToken<List<Usuario>>() {
			}.getType());
		} catch (Exception e) {
			return List.of();
		}
	}

	public static void salvar(List<Usuario> lista) {
		try (FileWriter writer = new FileWriter(ARQUIVO)) {
			gson.toJson(lista, writer);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Usuario buscarPorMatricula(String matricula) {
		for (Usuario u : carregar()) {
			if (u.getMatricula().equalsIgnoreCase(matricula)) {
				return u;
			}
		}
		return null;
	}
}