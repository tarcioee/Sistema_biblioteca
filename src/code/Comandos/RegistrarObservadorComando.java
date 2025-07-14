package Comandos;

import Repositorio.Repositorio;
import Usuarios.IUsuario;
import Livros.ILivro;
import Observadores.IObservador;

public class RegistrarObservadorComando implements IComando {

    @Override
    public void executar(String[] args) {
        if (args.length != 2) {
            System.out.println("Uso: obs <codigoUsuario> <codigoLivro>");
            return;
        }

        int codUsuario = Integer.parseInt(args[0]);
        int codLivro = Integer.parseInt(args[1]);

        IUsuario usuario = Repositorio.getInstancia().buscarUsuarioPorCodigo(codUsuario);
        ILivro livro = Repositorio.getInstancia().buscarLivroPorCodigo(codLivro);

        if (usuario == null || livro == null) {
            System.out.println("Usuário ou livro não encontrado.");
            return;
        }

        if (!(usuario instanceof IObservador)) {
            System.out.println("Este usuário não pode ser observador.");
            return;
        }

        livro.registrarObservador((IObservador) usuario);
        System.out.println("Observador registrado com sucesso.");
    }
}
