package modelo;

import java.time.LocalDate;
import java.util.UUID;

public class Pagamento {

	    private String id;
	    private String matriculaUsuario;
	    private double valor;
	    private LocalDate data;
	    private MetodoPagamento metodo;

	    public Pagamento(String matriculaUsuario, double valor, MetodoPagamento metodo) {
	        this.id = UUID.randomUUID().toString();
	        this.matriculaUsuario = matriculaUsuario;
	        this.valor = valor;
	        this.metodo = metodo;
	        this.data = LocalDate.now();
	    }

	    public String getId() {
	        return id;
	    }

	    public String getMatriculaUsuario() {
	        return matriculaUsuario;
	    }

	    public double getValor() {
	        return valor;
	    }

	    public LocalDate getData() {
	        return data;
	    }

	    public MetodoPagamento getMetodo() {
	        return metodo;
	    }
	}
