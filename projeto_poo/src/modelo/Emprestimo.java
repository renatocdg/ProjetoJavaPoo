package modelo;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Emprestimo {

	    private Obra obra;
	    private Usuario usuario;
	    private LocalDate dataEmprestimo;
	    private LocalDate dataPrevistaDevolucao;
	    private LocalDate dataDevolucao;
	    private boolean devolvido;

	    public Emprestimo(Obra obra, Usuario usuario) {
	        this.obra = obra;
	        this.usuario = usuario;
	        this.dataEmprestimo = LocalDate.now();
	        this.dataPrevistaDevolucao = dataEmprestimo.plusDays(obra.getTempoEmprestimo());
	        this.devolvido = false;
	        this.obra.setStatus("Emprestado");
	    }

	    public Obra getObra() {
	        return obra;
	    }

	    public Usuario getUsuario() {
	        return usuario;
	    }

	    public LocalDate getDataEmprestimo() {
	        return dataEmprestimo;
	    }

	    public LocalDate getDataPrevistaDevolucao() {
	        return dataPrevistaDevolucao;
	    }

	    public LocalDate getDataDevolucao() {
	        return dataDevolucao;
	    }

	    public boolean isDevolvido() {
	        return devolvido;
	    }

	    public void registrarDevolucao() {
	        this.dataDevolucao = LocalDate.now();
	        this.devolvido = true;
	        this.obra.setStatus("Disponível");
	    }

	    public boolean isAtrasado() {
	        if (!devolvido) {
	            return LocalDate.now().isAfter(dataPrevistaDevolucao);
	        } else {
	            return dataDevolucao.isAfter(dataPrevistaDevolucao);
	        }
	    }

	    public long getDiasAtraso() {
	        if (!isAtrasado()) return 0;
	        LocalDate dataReferencia = devolvido ? dataDevolucao : LocalDate.now();
	        return ChronoUnit.DAYS.between(dataPrevistaDevolucao, dataReferencia);
	    }

	    public String getStatusEmprestimo() {
	        if (devolvido) {
	            return "Devolvido";
	        } else if (isAtrasado()) {
	            return "Atrasado";
	        } else {
	            return "Em andamento";
	        }
	    }
	}
