package modelo;

import java.time.LocalDate;

public class Multa {

	    private String id;
	    private Usuario usuario;
	    private double valor;
	    private LocalDate dataPagamento;
	    private String metodoPagamento;

	    public Multa(String id, Usuario usuario, double valor, String metodoPagamento) {
	        this.id = id;
	        this.usuario = usuario;
	        this.valor = valor;
	        this.metodoPagamento = metodoPagamento;
	        this.dataPagamento = LocalDate.now();
	    }

	    public String getId() {
	        return id;
	    }

	    public Usuario getUsuario() {
	        return usuario;
	    }

	    public double getValor() {
	        return valor;
	    }

	    public LocalDate getDataPagamento() {
	        return dataPagamento;
	    }

	    public String getMetodoPagamento() {
	        return metodoPagamento;
	    }
	}
