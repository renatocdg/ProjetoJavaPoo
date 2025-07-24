package telas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import modelo.Obra;
import dao.ObraDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

public class TelaConsultaObras extends JFrame {

	private JTextField campoBusca;
	private JComboBox<String> comboTipo;
	private JTable tabela;
	private DefaultTableModel modeloTabela;

	public TelaConsultaObras() {
		setTitle("Consulta de Obras");
		setSize(700, 400);
		setLayout(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JLabel l1 = new JLabel("Buscar por título ou autor:");
		l1.setBounds(20, 20, 200, 25);
		add(l1);

		campoBusca = new JTextField();
		campoBusca.setBounds(200, 20, 200, 25);
		add(campoBusca);

		JLabel l2 = new JLabel("Tipo:");
		l2.setBounds(420, 20, 40, 25);
		add(l2);

		comboTipo = new JComboBox<>(new String[] { "Todos", "Livro", "Revista", "Artigo" });
		comboTipo.setBounds(460, 20, 120, 25);
		add(comboTipo);

		JButton botaoBuscar = new JButton("Filtrar");
		botaoBuscar.setBounds(590, 20, 80, 25);
		add(botaoBuscar);

		modeloTabela = new DefaultTableModel();
		modeloTabela.setColumnIdentifiers(new String[] { "Código", "Título", "Autor", "Ano", "Tipo", "Status" });

		tabela = new JTable(modeloTabela);
		JScrollPane scroll = new JScrollPane(tabela);
		scroll.setBounds(20, 60, 650, 280);
		add(scroll);

		botaoBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aplicarFiltro();
			}
		});

		carregarTodas();
		setVisible(true);
	}

	private void carregarTodas() {
		modeloTabela.setRowCount(0);
		List<Obra> obras = ObraDao.carregar();
		for (Obra o : obras) {
			modeloTabela.addRow(new Object[] { o.getCodigo(), o.getTitulo(), o.getAutor(), o.getAno(),
					o.getClass().getSimpleName(), o.getStatus() });
		}
	}

	private void aplicarFiltro() {
		String busca = campoBusca.getText().trim().toLowerCase();
		String tipoSelecionado = (String) comboTipo.getSelectedItem();

		List<Obra> obras = ObraDao.carregar();
		List<Obra> filtradas = obras.stream().filter(o -> {
			boolean matchBusca = o.getTitulo().toLowerCase().contains(busca)
					|| o.getAutor().toLowerCase().contains(busca);
			boolean matchTipo = tipoSelecionado.equals("Todos")
					|| o.getClass().getSimpleName().equalsIgnoreCase(tipoSelecionado);
			return matchBusca && matchTipo;
		}).collect(Collectors.toList());

		modeloTabela.setRowCount(0);
		for (Obra o : filtradas) {
			modeloTabela.addRow(new Object[] { o.getCodigo(), o.getTitulo(), o.getAutor(), o.getAno(),
					o.getClass().getSimpleName(), o.getStatus() });
		}
	}

	public static void main(String[] args) {
		new TelaConsultaObras();
	}
}