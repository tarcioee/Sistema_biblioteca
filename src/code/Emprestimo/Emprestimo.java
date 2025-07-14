package Emprestimo;

import java.time.LocalDate;
import Livros.IExemplar;
import Usuarios.IUsuario;

public class Emprestimo {
    private IUsuario usuario;
    private IExemplar exemplar;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucaoPrevista;
    private LocalDate dataDevolucaoReal;

    public Emprestimo(IUsuario usuario, IExemplar exemplar, LocalDate dataEmprestimo) {
        this.usuario = usuario;
        this.exemplar = exemplar;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucaoPrevista = dataEmprestimo.plusDays(usuario.getLimiteDiasEmprestimo());
        this.dataDevolucaoReal = null;
    }

    public IUsuario getUsuario() {
        return usuario;
    }

    public IExemplar getExemplar() {
        return exemplar;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public LocalDate getDataDevolucaoPrevista() {
        return dataDevolucaoPrevista;
    }

    public LocalDate getDataDevolucaoReal() {
        return dataDevolucaoReal;
    }

    public void registrarDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucaoReal = dataDevolucao;
        exemplar.devolver();
    }
    
    public boolean isFinalizado() {
        return dataDevolucaoReal != null;
    }
    public boolean estaEmAndamento() {
        return dataDevolucaoReal == null;
    }

    public boolean estaAtrasado() {
        return estaEmAndamento() && LocalDate.now().isAfter(dataDevolucaoPrevista);
    }
}
