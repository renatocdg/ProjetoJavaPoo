package dao;

import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;
import modelo.Emprestimo;
import java.io.*;
import java.util.*;

public class EmprestimoDao {

	private static final String ARQUIVO = "emprestimos.json";
	private static Gson gson = new Gson();

	public static List<Emprestimo> carregar() {
		try (Reader r = new FileReader(ARQUIVO)) {
			return gson.fromJson(r, new TypeToken<List<Emprestimo>>() {
			}.getType());
		} catch (Exception e) {
			return new ArrayList<>();
		}
	}

	public static void salvar(List<Emprestimo> lista) {
		try (Writer w = new FileWriter(ARQUIVO)) {
			gson.toJson(lista, w);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
