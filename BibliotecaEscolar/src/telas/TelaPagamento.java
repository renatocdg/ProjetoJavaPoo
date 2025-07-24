package telas;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.*;
import dao.*;
import java.util.List;

public class TelaPagamento extends JFrame {

	private JTextField campoMatricula, campoValor;
	private JComboBox<MetodoPagamento> comboMetodo;

	public TelaPagamento() {
		setTitle("Registrar Pagamento de Multa");
		setSize(350, 250);
		setLayout(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JLabel l1 = new JLabel("Matrícula:");
		l1.setBounds(20, 30, 100, 25);
		add(l1);

		campoMatricula = new JTextField();
		campoMatricula.setBounds(120, 30, 180, 25);
		add(campoMatricula);

		JLabel l2 = new JLabel("Valor:");
		l2.setBounds(20, 70, 100, 25);
		add(l2);

		campoValor = new JTextField();
		campoValor.setBounds(120, 70, 180, 25);
		add(campoValor);

		JLabel l3 = new JLabel("Método:");
		l3.setBounds(20, 110, 100, 25);
		add(l3);

		comboMetodo = new JComboBox<>(MetodoPagamento.values());
		comboMetodo.setBounds(120, 110, 180, 25);
		add(comboMetodo);

		JButton botaoConfirmar = new JButton("Confirmar");
		botaoConfirmar.setBounds(120, 160, 100, 30);
		add(botaoConfirmar);

		botaoConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registrarPagamento();
			}
		});

		setVisible(true);
	}

	private void registrarPagamento() {
		String matricula = campoMatricula.getText().trim();
		String valorStr = campoValor.getText().trim();

		if (matricula.isEmpty() || valorStr.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Preencha todos os campos.");
			return;
		}

		try {
			double valor = Double.parseDouble(valorStr);
			MetodoPagamento metodo = (MetodoPagamento) comboMetodo.getSelectedItem();

			List<Usuario> usuarios = UsuarioDao.carregar();
			boolean usuarioExiste = usuarios.stream().anyMatch(u -> u.getMatricula().equalsIgnoreCase(matricula));

			if (!usuarioExiste) {
				JOptionPane.showMessageDialog(this, "Usuário não encontrado.");
				return;
			}

			Pagamento pagamento = new Pagamento(matricula, valor, metodo);
			List<Pagamento> pagamentos = PagamentoDao.carregar();
			pagamentos.add(pagamento);
			PagamentoDao.salvar(pagamentos);

			JOptionPane.showMessageDialog(this, "Pagamento registrado com sucesso!");
			limparCampos();
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Valor inválido.");
		}
	}

	private void limparCampos() {
		campoMatricula.setText("");
		campoValor.setText("");
		comboMetodo.setSelectedIndex(0);
	}

	public static void main(String[] args) {
		new TelaPagamento();
	}
}