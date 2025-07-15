package Comandos;

import Services.BibliotecaService;

public class ConsultaNotificacoesComando implements IComando {

    private BibliotecaService servico = new BibliotecaService();

    @Override
    public void executar(String[] args) {
        if (args.length != 1) {
            System.out.println("Uso: ntf <codigoUsuario>");
            return;
        }

        int codUsuario = Integer.parseInt(args[0]);
        String resultado = servico.consultarNotificacoesRecebidas(codUsuario);
        System.out.println(resultado);
    }
}
