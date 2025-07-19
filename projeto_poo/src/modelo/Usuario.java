package modelo;

public class Usuario {

	private String Matricula;
	private String Nome;
	private String TipoUsuario;
	private String Telefone;
	private String Email;

//construtor
	public Usuario(String Matricula, String Nome, String TipoUsuario, String Telefone, String Email) {

		this.Matricula = Matricula;
		this.Nome = Nome;
		this.TipoUsuario = TipoUsuario;
		this.Telefone = Telefone;
		this.Email = Email;
	}

	public String getMatricula() {
		return Matricula;
	}

	public void setMatricula(String matricula) {
		Matricula = matricula;
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}

	public String getTipoUsuario() {
		return TipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		TipoUsuario = tipoUsuario;
	}

	public String getTelefone() {
		return Telefone;
	}

	public void setTelefone(String telefone) {
		Telefone = telefone;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}
}