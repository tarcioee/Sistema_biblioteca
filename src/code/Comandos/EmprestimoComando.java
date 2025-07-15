package Comandos;

import Services.BibliotecaService;

public class EmprestimoComando implements IComando {

    private BibliotecaService servico = new BibliotecaService();

    @Override
    public void executar(String[] args) {
        if (args.length != 2) {
            System.out.println("Uso: emp <codigoUsuario> <codigoLivro>");
            return;
        }

        int codUsuario = Integer.parseInt(args[0]);
        int codLivro = Integer.parseInt(args[1]);

        String resultado = servico.realizarEmprestimo(codUsuario, codLivro);
        System.out.println(resultado);
    }
}
