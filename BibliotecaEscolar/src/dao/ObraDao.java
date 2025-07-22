package dao;

import modelo.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;
import java.util.ArrayList;

public class ObraDao {

	private static final String ARQUIVO = "obras.json";
	private static final Gson gson = new Gson();

	public static List<Obra> carregarTodas() {
		try (FileReader reader = new FileReader(ARQUIVO)) {
			return gson.fromJson(reader, new TypeToken<List<Obra>>() {
			}.getType());
		} catch (Exception e) {
			return new ArrayList<>();
		}
	}

	public static Obra buscarPorCodigo(String codigoStr) {
		try {
			int codigo = Integer.parseInt(codigoStr);
			for (Obra o : carregarTodas()) {
				if (o != null && o.getCodigo() == codigo) {
					return o;
				}
			}
		} catch (NumberFormatException e) {
			System.out.println("Código inválido: " + codigoStr);
		}
		return null;
	}

	public static void salvarTodas(List<Obra> lista) {
		try (FileWriter writer = new FileWriter(ARQUIVO)) {
			gson.toJson(lista, writer);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
