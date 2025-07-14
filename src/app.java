import SistemaBiblioteca.SistemaBiblioteca;
import Repositorio.Repositorio;
import Usuarios.*;
import Livros.*;

public class app {
    public static void main(String[] args) {
        Repositorio repositorio = Repositorio.getInstancia();

        // Usuários
        IUsuario u1 = new AlunoGraduacao(123, "João da Silva");
        IUsuario u2 = new AlunoPosGraduacao(456, "Luiz Fernando Rodrigues");
        IUsuario u3 = new AlunoGraduacao(789, "Pedro Paulo");
        IUsuario u4 = new Professor(100, "Carlos Lucena");

        repositorio.adicionarUsuario(u1);
        repositorio.adicionarUsuario(u2);
        repositorio.adicionarUsuario(u3);
        repositorio.adicionarUsuario(u4);

        // Livros
        ILivro l1 = new Livro(100, "Engenharia de Software", "Addison Wesley", "Ian Sommerville", "6ª", 2000);
        ILivro l2 = new Livro(101, "UML - Guia do Usuário", "Campus", "Grady Booch, James Rumbaugh, Ivar Jacobson", "7ª", 2000);
        ILivro l3 = new Livro(200, "Code Complete", "Microsoft Press", "Steve McConnell", "2ª", 2014);
        ILivro l4 = new Livro(201, "Agile Software Development, Principles, Patterns and Practices", "Prentice Hall", "Robert Martin", "1ª", 2002);
        ILivro l5 = new Livro(300, "Refactoring: Improving the Design of Existing Code", "Addison Wesley Professional", "Martin Fowler", "1ª", 1999);
        ILivro l6 = new Livro(301, "Software Metrics: A Rigorous and Practical Approach", "CRC Press", "Norman Fenton, James Bieman", "3ª", 2014);
        ILivro l7 = new Livro(400, "Design Patterns: Elements of Reusable Object-Oriented Software", "Addison Wesley Professional", "Erich Gamma, Richard Helm, Ralph Johnson, John Vlissides", "1ª", 1994);
        ILivro l8 = new Livro(401, "UML Distilled: A Brief Guide to the Standard Object Modeling Language", "Addison Wesley Professional", "Martin Fowler", "3ª", 2003);

        repositorio.adicionarLivro(l1);
        repositorio.adicionarLivro(l2);
        repositorio.adicionarLivro(l3);
        repositorio.adicionarLivro(l4);
        repositorio.adicionarLivro(l5);
        repositorio.adicionarLivro(l6);
        repositorio.adicionarLivro(l7);
        repositorio.adicionarLivro(l8);

        // Exemplares
        l1.adicionarExemplar(new Exemplar(1, l1));
        l1.adicionarExemplar(new Exemplar(2, l1));
        l2.adicionarExemplar(new Exemplar(3, l2));
        l3.adicionarExemplar(new Exemplar(4, l3));
        l4.adicionarExemplar(new Exemplar(5, l4));
        l5.adicionarExemplar(new Exemplar(6, l5));
        l5.adicionarExemplar(new Exemplar(7, l5));
        l7.adicionarExemplar(new Exemplar(8, l7));
        l7.adicionarExemplar(new Exemplar(9, l7));

        // Reservas podem ser adicionadas aqui se quiser testar

        // Inicia o sistema
        SistemaBiblioteca.iniciar();
    }
}
