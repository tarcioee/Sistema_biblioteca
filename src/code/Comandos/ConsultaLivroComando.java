package Comandos;

import Services.BibliotecaService;

public class ConsultaLivroComando implements IComando {

    private BibliotecaService servico = new BibliotecaService();

    @Override
    public void executar(String[] args) {
        if (args.length != 1) {
            System.out.println("Uso: liv <codigoLivro>");
            return;
        }

        int codLivro = Integer.parseInt(args[0]);
        String resultado = servico.consultarLivro(codLivro);
        System.out.println(resultado);
    }
}
