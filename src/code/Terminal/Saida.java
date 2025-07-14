package Terminal;

public class Saida {
    public static void sucesso(String msg) {
        System.out.println("[OK] " + msg);
    }

    public static void erro(String msg) {
        System.out.println("[ERRO] " + msg);
    }

    public static void info(String msg) {
        System.out.println("[INFO] " + msg);
    }
}
