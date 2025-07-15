package Services;

import Livros.ILivro;
import Livros.IExemplar;
import Repositorio.Repositorio;
import Reserva.Reserva;
import Usuarios.IUsuario;
import Observadores.IObservador;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import Emprestimo.Emprestimo;

public class BibliotecaService {

    public String consultarLivro(int codLivro) {
        ILivro livro = Repositorio.getInstancia().buscarLivroPorCodigo(codLivro);

        if (livro == null) {
            return "Livro não encontrado.";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Título: ").append(livro.getTitulo()).append("\n");
        sb.append("Quantidade Reservas: ").append(livro.getReservas().size()).append("\n");

        if (!livro.getReservas().isEmpty()) {
            sb.append("Nome dos Usuários com reserva:\n");
            for (Reserva r : livro.getReservas()) {
                sb.append("- ").append(r.getUsuario().getNome()).append("\n");
            }
        }

        sb.append("Exemplares:\n");
        for (IExemplar ex : livro.getExemplares()) {
            String status = ex.estaDisponivel() ? "Disponível" : "Emprestado";
            sb.append("Exemplar ").append(ex.getCodigo()).append(" - ").append(status);

            if (!ex.estaDisponivel() && ex.getUsuarioAtual() != null) {
                IUsuario usuario = ex.getUsuarioAtual();
                sb.append(" (Emprestado para: ").append(usuario.getNome());

                var emprestimo = usuario.getEmprestimos().stream()
                        .filter(e -> e.estaEmAndamento() && e.getExemplar().getCodigo() == ex.getCodigo())
                        .findFirst()
                        .orElse(null);

                if (emprestimo != null) {
                    sb.append(", Data de empréstimo: ").append(emprestimo.getDataEmprestimo());
                    sb.append(", Data prevista de devolução: ").append(emprestimo.getDataDevolucaoPrevista());
                }

                sb.append(")");
            }

            sb.append("\n");
        }

        return sb.toString();
    }

    public String consultarNotificacoesRecebidas(int codUsuario) {
        IUsuario usuario = Repositorio.getInstancia().buscarUsuarioPorCodigo(codUsuario);

        if (usuario == null) {
            return "Usuário não encontrado.";
        }

        if (usuario instanceof IObservador observador) {
            return "Notificações recebidas: " + observador.getContadorNotificacoes();
        } else {
            return "Este usuário não é observador de livros.";
        }
    }
    
    public String consultarDadosDoUsuario(int codUsuario) {
        IUsuario usuario = Repositorio.getInstancia().buscarUsuarioPorCodigo(codUsuario);

        if (usuario == null) {
            return "Usuário não encontrado.";
        }

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        StringBuilder sb = new StringBuilder();

        sb.append("Empréstimos:\n");
        for (Emprestimo emp : usuario.getEmprestimos()) {
            String status = emp.estaEmAndamento() ? "Em andamento" : "Finalizado";
            sb.append("- Titulo: ").append(emp.getExemplar().getLivro().getTitulo());
            sb.append(" | Data: ").append(emp.getDataEmprestimo().format(fmt));
            sb.append(" | Status: ").append(status);

            if (emp.getDataDevolucaoReal() != null) {
                sb.append(" | Devolvido em: ").append(emp.getDataDevolucaoReal().format(fmt));
            } else {
                sb.append(" | Devolução Prevista para: ").append(emp.getDataDevolucaoPrevista().format(fmt));
            }

            sb.append("\n");
        }

        sb.append("Reservas:\n");
        for (Reserva res : usuario.getReservas()) {
            sb.append("- Titulo: ").append(res.getLivro().getTitulo());
            sb.append(" | Data da reserva: ").append(res.getDataReserva().format(fmt));
            sb.append("\n");
        }

        return sb.toString();
    }

    public String realizarDevolucao(int codUsuario, int codLivro) {
        IUsuario usuario = Repositorio.getInstancia().buscarUsuarioPorCodigo(codUsuario);
        if (usuario == null) {
            return "Usuário não encontrado.";
        }

        Emprestimo emprestimo = usuario.getEmprestimos().stream()
            .filter(e -> e.estaEmAndamento() && e.getExemplar().getLivro().getCodigo() == codLivro)
            .findFirst()
            .orElse(null);

        if (emprestimo == null) {
            return "Empréstimo não encontrado.";
        }

        emprestimo.registrarDevolucao(LocalDate.now());
        return "Devolução registrada com sucesso.";
    }

    public String realizarEmprestimo(int codUsuario, int codLivro) {
    Repositorio repo = Repositorio.getInstancia();
    IUsuario usuario = repo.buscarUsuarioPorCodigo(codUsuario);
    ILivro livro = repo.buscarLivroPorCodigo(codLivro);

    if (usuario == null || livro == null) {
        return "Usuário ou livro não encontrado.";
    }

    if (!usuario.getEstrategiaEmprestimo().podeEmprestar(usuario, livro)) {
        return usuario.getEstrategiaEmprestimo().motivoFalha(usuario, livro);
    }

    IExemplar exemplarDisponivel = livro.getExemplares().stream()
        .filter(IExemplar::estaDisponivel)
        .findFirst()
        .orElse(null);

    if (exemplarDisponivel == null) {
        return "Nenhum exemplar disponível.";
    }

    exemplarDisponivel.emprestar(usuario);
    Emprestimo emprestimo = new Emprestimo(usuario, exemplarDisponivel, LocalDate.now());
    usuario.getEmprestimos().add(emprestimo);
    livro.removerReserva(usuario);

    return "Empréstimo realizado com sucesso.";
}

    public String registrarObservador(int codUsuario, int codLivro) {
        Repositorio repo = Repositorio.getInstancia();
        IUsuario usuario = repo.buscarUsuarioPorCodigo(codUsuario);
        ILivro livro = repo.buscarLivroPorCodigo(codLivro);

        if (usuario == null || livro == null) {
            return "Usuário ou livro não encontrado.";
        }

        if (!(usuario instanceof IObservador observador)) {
            return "Este usuário não pode ser observador.";
        }

        livro.registrarObservador(observador);
        return "Observador registrado com sucesso.";
    }

    public String realizarReserva(int codUsuario, int codLivro) {
        Repositorio repo = Repositorio.getInstancia();
        IUsuario usuario = repo.buscarUsuarioPorCodigo(codUsuario);
        ILivro livro = repo.buscarLivroPorCodigo(codLivro);

        if (usuario == null || livro == null) {
            return "Usuário ou livro não encontrado.";
        }

        boolean jaReservado = usuario.getReservas().stream()
            .anyMatch(r -> r.getLivro().getCodigo() == codLivro);

        if (jaReservado) {
            return "Usuário já possui uma reserva para este livro.";
        }

        Reserva reserva = new Reserva(usuario, livro, LocalDate.now());
        livro.adicionarReserva(reserva);
        usuario.getReservas().add(reserva);

        return "Reserva registrada com sucesso.";
    }

}
