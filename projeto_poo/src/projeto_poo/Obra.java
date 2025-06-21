package projeto_poo;

public abstract class Obra {

	protected int Codigo;
	protected String Titulo;
	protected String Autor;
	protected int AnoPublicacao;
	protected String Status;

//construtor padrão
	public Obra(int Codigo, String Titulo, String Autor, int AnoPublicacao, String Status) {

		this.Codigo = Codigo;
		this.Titulo = Titulo;
		this.Autor = Autor;
		this.AnoPublicacao = AnoPublicacao;
		this.Status = Status;
}
		
public abstract int getTempoEmprestimo(int Dias);

} 
