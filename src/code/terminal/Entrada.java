package Terminal;

import Comandos.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Entrada {
    private final Map<String, IComando> comandos = new HashMap<>();
    
    public Entrada() {
        comandos.put("emp", new EmprestimoComando());
        comandos.put("dev", new DevolucaoComando());
        comandos.put("res", new ReservaComando());
        comandos.put("liv", new ConsultaLivroComando());
        comandos.put("usu", new ConsultaUsuarioComando());
        comandos.put("ntf", new ConsultaNotificacoesComando());
        comandos.put("obs", new RegistrarObservadorComando());
        comandos.put("sai", new SairComando());
    }

    public void iniciar() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("> ");
            String linha = scanner.nextLine().trim();

            if (linha.isEmpty()) continue;

            String[] partes = linha.split(" ");
            String comandoStr = partes[0];
            String[] args = new String[partes.length - 1];
            System.arraycopy(partes, 1, args, 0, args.length);

            IComando comando = comandos.get(comandoStr);

            if (comando != null) {
                try {
                    comando.executar(args);
                } catch (Exception e) {
                    System.out.println("Erro ao executar comando: " + e.getMessage());
                }
            } else {
                System.out.println("Comando inválido.");
            }
        }

    }
}
