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
}