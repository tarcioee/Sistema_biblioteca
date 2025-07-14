package Usuario;



public class AlunoPosGraduacao extends Usuario {
    
    public int limiteDiasEmprestimo() {
        return 5; 
    }

    public int limiteDeEmprestimosEmAberto(){
        return 3;
    }

    public Boolean ignoraReservas() {
        return true;
    }
    


}
