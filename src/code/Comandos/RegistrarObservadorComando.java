package Comandos;

import Services.BibliotecaService;

public class RegistrarObservadorComando implements IComando {

    private BibliotecaService servico = new BibliotecaService();

    @Override
    public void executar(String[] args) {
        if (args.length != 2) {
            System.out.println("Uso: obs <codigoUsuario> <codigoLivro>");
            return;
        }

        int codUsuario = Integer.parseInt(args[0]);
        int codLivro = Integer.parseInt(args[1]);

        String resultado = servico.registrarObservador(codUsuario, codLivro);
        System.out.println(resultado);
    }
}
