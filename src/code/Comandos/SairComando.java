//ok
package Comandos;

public class SairComando implements IComando {
    @Override
    public void executar(String[] args) {
        System.out.println("Saindo do sistema...");
        System.exit(0);
    }
}
