package dao;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import com.google.gson.Gson;

import modelo.Multa;

public class ArquivoDeMultas {

	private Gson gson = new Gson();

	public void salvar(List<Multa> lista, String caminho) {
		try {
			FileWriter escrever = new FileWriter(caminho);
			gson.toJson(lista, escrever);
			escrever.close();
		} catch (Exception e) {
			System.out.println("Erro ao salvar multas: " + e.getMessage());
		}
	}

	public List<Multa> carregar(String caminho) {
		try {
			FileReader reader = new FileReader(caminho);
			Multa[] array = gson.fromJson(reader, Multa[].class);
			reader.close();
			return new ArrayList<>(Arrays.asList(array));
		} catch (Exception e) {
			System.out.println("Erro ao carregar multas: " + e.getMessage());
			return new ArrayList<>();
		}
	}
}