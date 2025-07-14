package Observadores;

public interface ISujeito {
    void registrarObservador(IObservador observador);
    void removerObservador(IObservador observador);
    void notificarObservadores();
}
