package telas;

//“Cadastro, edição e exclusão de usuários do sistema (alunos, professores, servidores).”

import javax.swing.*;
import modelo.*;
import dao.UsuarioDao;
import java.awt.event.*;
import java.util.List;

public class TelaUsuario extends JFrame {

	private JTextField campoMatricula;
	private JTextField campoNome;
	private JTextField campoTelefone;
	private JTextField campoEmail;
	private JComboBox<TipoUsuario> comboTipo;

	public TelaUsuario() {
		setTitle("Cadastro de Usuário");
		setSize(350, 300);
		setLayout(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JLabel l1 = new JLabel("Matrícula:");
		l1.setBounds(20, 20, 100, 25);
		add(l1);

		campoMatricula = new JTextField();
		campoMatricula.setBounds(120, 20, 180, 25);
		add(campoMatricula);

		JLabel l2 = new JLabel("Nome:");
		l2.setBounds(20, 60, 100, 25);
		add(l2);

		campoNome = new JTextField();
		campoNome.setBounds(120, 60, 180, 25);
		add(campoNome);

		JLabel l3 = new JLabel("Tipo:");
		l3.setBounds(20, 100, 100, 25);
		add(l3);

		comboTipo = new JComboBox<>(TipoUsuario.values());
		comboTipo.setBounds(120, 100, 180, 25);
		add(comboTipo);

		JLabel l4 = new JLabel("Telefone:");
		l4.setBounds(20, 140, 100, 25);
		add(l4);

		campoTelefone = new JTextField();
		campoTelefone.setBounds(120, 140, 180, 25);
		add(campoTelefone);

		JLabel l5 = new JLabel("Email:");
		l5.setBounds(20, 180, 100, 25);
		add(l5);

		campoEmail = new JTextField();
		campoEmail.setBounds(120, 180, 180, 25);
		add(campoEmail);

		JButton botaoCadastrar = new JButton("Cadastrar");
		botaoCadastrar.setBounds(120, 220, 100, 30);
		add(botaoCadastrar);

		botaoCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cadastrarUsuario();
			}
		});

		setVisible(true);
	}

	private void cadastrarUsuario() {
		String matricula = campoMatricula.getText().trim();
		String nome = campoNome.getText().trim();
		String telefone = campoTelefone.getText().trim();
		String email = campoEmail.getText().trim();
		TipoUsuario tipo = (TipoUsuario) comboTipo.getSelectedItem();

		if (matricula.isEmpty() || nome.isEmpty() || telefone.isEmpty() || email.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Preencha todos os campos.");
			return;
		}

		List<Usuario> usuarios = UsuarioDao.carregar();
		for (Usuario u : usuarios) {
			if (u.getMatricula().equals(matricula)) {
				JOptionPane.showMessageDialog(this, "Já existe um usuário com essa matrícula.");
				return;
			}
		}

		Usuario novo = new Usuario(matricula, nome, tipo, telefone, email);
		usuarios.add(novo);
		UsuarioDao.salvar(usuarios);
		JOptionPane.showMessageDialog(this, "Usuário cadastrado com sucesso!");
		limparCampos();
	}

	private void limparCampos() {
		campoMatricula.setText("");
		campoNome.setText("");
		campoTelefone.setText("");
		campoEmail.setText("");
		comboTipo.setSelectedIndex(0);
	}

	public static void main(String[] args) {
		new TelaUsuario();
	}
}