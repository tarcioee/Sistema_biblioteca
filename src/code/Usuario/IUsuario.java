package Usuario;

import SistemaBiblioteca.IEntidadeBiblioteca;


public interface IUsuario extends IEntidadeBiblioteca{
    
    public String getNome();
    public abstract int limiteDiasEmprestimo();
    public int limiteDeEmprestimosEmAberto();
    
    
}
