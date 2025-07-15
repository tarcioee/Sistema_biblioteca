package Comandos;

import Services.BibliotecaService;

public class DevolucaoComando implements IComando {

    private BibliotecaService servico = new BibliotecaService();

    @Override
    public void executar(String[] args) {
        if (args.length != 2) {
            System.out.println("Uso: dev <codigoUsuario> <codigoLivro>");
            return;
        }

        int codUsuario = Integer.parseInt(args[0]);
        int codLivro = Integer.parseInt(args[1]);

        String resultado = servico.realizarDevolucao(codUsuario, codLivro);
        System.out.println(resultado);
    }
}
