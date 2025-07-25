package telas;

import javax.swing.*;
import controle.*;

public class TelaEmprestimo extends JFrame {
	
	public TelaEmprestimo() {
		setTitle("Empréstimo de Obra");
		setSize(300, 200);
		setLayout(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		JLabel lblMatricula = new JLabel("Matrícula:");
		lblMatricula.setBounds(20, 20, 80, 25);
		add(lblMatricula);

		JTextField campoMatricula = new JTextField();
		campoMatricula.setBounds(100, 20, 150, 25);
		add(campoMatricula);

		JLabel lblCodigo = new JLabel("Código da Obra:");
		lblCodigo.setBounds(20, 60, 100, 25);
		add(lblCodigo);

		JTextField campoCodigo = new JTextField();
		campoCodigo.setBounds(130, 60, 120, 25);
		add(campoCodigo);

		JButton botaoEmprestar = new JButton("Emprestar");
		botaoEmprestar.setBounds(90, 110, 100, 30);
		add(botaoEmprestar);

		botaoEmprestar.addActionListener(e -> {
			String matricula = campoMatricula.getText().trim();
			String codigo = campoCodigo.getText().trim();

			if (matricula.isEmpty() || codigo.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Preencha todos os campos!");
				return;
			}

			boolean sucesso = EmprestimoControle.registrarEmprestimo(matricula, codigo);

			if (sucesso) {
				JOptionPane.showMessageDialog(this, "Empréstimo registrado com sucesso!");
				dispose(); // fecha a tela
			} else {
				JOptionPane.showMessageDialog(this,
						"Erro ao registrar empréstimo.\nVerifique a matrícula ou se a obra está disponível.");
			}
		});

		setVisible(true);
	}
}