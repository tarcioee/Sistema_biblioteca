//Revisado, tudo certo
package Comandos;

import Observadores.IObservador;
import Repositorio.Repositorio;
import Usuarios.IUsuario;

public class ConsultaNotificacoesComando implements IComando {

    @Override
    public void executar(String[] args) {
        if (args.length != 1) {
            System.out.println("Uso: ntf <codigoUsuario>");
            return;
        }

        int codUsuario = Integer.parseInt(args[0]);
        IUsuario usuario = Repositorio.getInstancia().buscarUsuarioPorCodigo(codUsuario);

        if (usuario == null) {
            System.out.println("Usuário não encontrado.");
            return;
        }
        if (usuario instanceof IObservador) {
            IObservador observador = (IObservador) usuario;
            System.out.println("Notificações recebidas: " + observador.getContadorNotificacoes());
        } else {
            System.out.println("Este usuário não é observador de livros.");
        }
    }
}
