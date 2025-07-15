package Terminal;

public class ConsoleHelper {
    public static void printErro(String msg) {
        System.out.println("[ERRO] " + msg);
    }

    public static void printSucesso(String msg) {
        System.out.println("[OK] " + msg);
    }

    public static boolean validarArgs(String[] args, int esperados) {
        if (args.length != esperados) {
            printErro("Número de argumentos inválido.");
            return false;
        }
        return true;
    }
}
