package Livros.EstadoExemplar;

public class ExemplarEmprestado implements IExemplarEstado {
    @Override
    public boolean estaDisponivel() {
        return false;
    }
}
