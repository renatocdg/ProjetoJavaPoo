package telas;

import javax.swing.*;
import modelo.*;
import dao.UsuarioDao;
import java.awt.event.*;
import java.util.List;

public class TelaLogin extends JFrame {
	private JTextField campoMatricula;
	private JComboBox<TipoUsuario> comboTipo;

// Armazena o usuário logado (pode ser acessado em outras telas)
	public static Usuario usuarioLogado;

	public TelaLogin() {
		setTitle("Login");
		setSize(300, 200);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel l1 = new JLabel("Matrícula:");
		l1.setBounds(30, 30, 80, 25);
		add(l1);

		campoMatricula = new JTextField();
		campoMatricula.setBounds(110, 30, 140, 25);
		add(campoMatricula);

		JLabel l2 = new JLabel("Tipo:");
		l2.setBounds(30, 70, 80, 25);
		add(l2);

		comboTipo = new JComboBox<>(TipoUsuario.values());
		comboTipo.setBounds(110, 70, 140, 25);
		add(comboTipo);

		JButton botaoEntrar = new JButton("Entrar");
		botaoEntrar.setBounds(90, 120, 100, 30);
		add(botaoEntrar);

		botaoEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				autenticar();
			}
		});

		setVisible(true);
	}

	private void autenticar() {
		String matricula = campoMatricula.getText().trim();
		TipoUsuario tipoSelecionado = (TipoUsuario) comboTipo.getSelectedItem();

		List<Usuario> usuarios = UsuarioDao.carregar();
		for (Usuario u : usuarios) {
			if (u.getMatricula().equalsIgnoreCase(matricula) && u.getTipoUsuario() == tipoSelecionado) {
				usuarioLogado = u;
				JOptionPane.showMessageDialog(this, "Login realizado com sucesso como " + tipoSelecionado + ".");
				abrirMenu(tipoSelecionado);
				dispose(); // Fecha tela de login
				return;
			}
		}

		JOptionPane.showMessageDialog(this, "Usuário não encontrado ou tipo incorreto.");
	}

	private void abrirMenu(TipoUsuario tipo) {
		switch (tipo) {
		case SERVIDOR:
			JOptionPane.showMessageDialog(this, "Acesso total (Administrador).");
			break;
		case PROFESSOR:
			JOptionPane.showMessageDialog(this, "Acesso de Bibliotecário.");
			break;
		case ALUNO:
			JOptionPane.showMessageDialog(this, "Acesso restrito: Estagiário.");
			break;
		}
	}
	
	public static void main(String[] args) {
		new TelaLogin();
	}
}