package Livros.EstadoExemplar;

public class ExemplarDisponivel implements IExemplarEstado {
    @Override
    public boolean estaDisponivel() {
        return true;
    }
}
