package relatorios;

import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.List;
import java.time.format.DateTimeFormatter;
import modelo.*;
import dao.EmprestimoDao;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

public class RelatorioPdf {

	public static void gerarRelatorioEmprestimosDoMes(String caminhoArquivo) {

		try {
			List<Emprestimo> lista = EmprestimoDao.carregar();
			Document doc = new Document();
			PdfWriter.getInstance(doc, new FileOutputStream(caminhoArquivo));
			doc.open();

			doc.add(new Paragraph("Relatório de Empréstimos do Mês",
					FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16)));
			doc.add(new Paragraph("Data de geração: " + LocalDate.now()));
			doc.add(new Paragraph(" ")); // linha em branco

			PdfPTable tabela = new PdfPTable(5);
			tabela.addCell("Usuário");
			tabela.addCell("Tipo");
			tabela.addCell("Obra");
			tabela.addCell("Data Empréstimo");
			tabela.addCell("Data Prevista");

			DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			int total = 0;

			for (Emprestimo e : lista) {
				if (e.getDataEmprestimo().getMonth() == LocalDate.now().getMonth()) {
					Usuario u = e.getUsuario();
					Obra o = e.getObra();

					tabela.addCell(u.getNome());
					tabela.addCell(u.getTipoUsuario().name());
					tabela.addCell(o.getTitulo());
					tabela.addCell(e.getDataEmprestimo().format(fmt));
					tabela.addCell(e.getDataPrevistaDevolucao().format(fmt));
					total++;
				}
			}

			doc.add(tabela);
			doc.add(new Paragraph("Total de empréstimos no mês: " + total));
			doc.close();

			System.out.println("Relatório gerado com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//Relatório das obras mais emprestadas do sistema

	public static void gerarRelatorioObrasMaisEmprestadas(String caminhoArquivo) {
		try {
			List<Emprestimo> lista = EmprestimoDao.carregar();
			Map<String, Integer> contagem = new HashMap<>();

			for (Emprestimo e : lista) {
				String chave = e.getObra().getTitulo() + " - " + e.getObra().getAutor();
				contagem.put(chave, contagem.getOrDefault(chave, 0) + 1);
			}

			List<Map.Entry<String, Integer>> ranking = new ArrayList<>(contagem.entrySet());
			ranking.sort((a, b) -> b.getValue().compareTo(a.getValue())); // ordem decrescente

			Document doc = new Document();
			PdfWriter.getInstance(doc, new FileOutputStream(caminhoArquivo));
			doc.open();

			doc.add(new Paragraph("Relatório de Obras Mais Emprestadas",
					FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16)));
			doc.add(new Paragraph("Data de geração: " + LocalDate.now()));
			doc.add(new Paragraph(" "));

			PdfPTable tabela = new PdfPTable(2);
			tabela.addCell("Obra");
			tabela.addCell("Quantidade de Empréstimos");

			for (Map.Entry<String, Integer> entry : ranking) {
				tabela.addCell(entry.getKey());
				tabela.addCell(String.valueOf(entry.getValue()));
			}

			doc.add(tabela);
			doc.close();
			System.out.println("Relatório de obras mais emprestadas gerado.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}