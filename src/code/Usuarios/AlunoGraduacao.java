package Usuarios;

import Emprestimo.Estrategias.IEstrategiaEmprestimo;
import Emprestimo.Estrategias.EmprestimoAluno;
public class AlunoGraduacao extends Usuario {

    public AlunoGraduacao(int codigo, String nome) {
        super(codigo, nome);
    }

    @Override
    public int getLimiteEmprestimos() {
        return 2;
    }

    @Override
    public int getLimiteDiasEmprestimo() {
        return 4;
    }

    public IEstrategiaEmprestimo getEstrategiaEmprestimo() {
		return new EmprestimoAluno();
	}
}
