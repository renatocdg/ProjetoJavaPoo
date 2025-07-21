package telas;

import javax.swing.*;
import controle.EmprestimoControle;

public class TelaEmprestimo extends JFrame {

	public TelaEmprestimo() {
		setTitle("Empréstimo");
		setSize(280, 180);
		setLayout(null);

		JLabel lm = new JLabel("Matrícula:");
		lm.setBounds(10, 10, 100, 25);
		add(lm);

		JTextField tm = new JTextField();
		tm.setBounds(110, 10, 140, 25);
		add(tm);

		JLabel lc = new JLabel("Código Obra:");
		lc.setBounds(10, 50, 100, 25);
		add(lc);

		JTextField tc = new JTextField();
		tc.setBounds(110, 50, 140, 25);
		add(tc);

		JButton b = new JButton("Emprestar");
		b.setBounds(80, 100, 120, 30);
		add(b);

		b.addActionListener(e -> {
			String m = tm.getText().trim();
			String c = tc.getText().trim();
			if (m.isEmpty() || c.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
				return;
			}
			EmprestimoControle.registrar(m, c, 7); // usa 7 dias fixos
			JOptionPane.showMessageDialog(null, "Empréstimo registrado!");
			dispose();
		});

		setVisible(true);
	}
}
