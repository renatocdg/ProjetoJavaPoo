package dao;

import modelo.Pagamento;
import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class PagamentoDao {

	private static final String ARQUIVO = "pagamentos.json";
	private static final Gson gson = new Gson();

	public static List<Pagamento> carregar() {
		try (FileReader reader = new FileReader(ARQUIVO)) {
			return gson.fromJson(reader, new TypeToken<List<Pagamento>>() {
			}.getType());
		} catch (Exception e) {
			return new ArrayList<>();
		}
	}

	public static void salvar(List<Pagamento> pagamentos) {
		try (FileWriter writer = new FileWriter(ARQUIVO)) {
			gson.toJson(pagamentos, writer);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}