package SistemaBiblioteca;

import Terminal.Entrada;
import Repositorio.Repositorio;

import Usuarios.AlunoGraduacao;
import Usuarios.AlunoPosGraduacao;
import Usuarios.Professor;
import Livros.Livro;
import Livros.Exemplar;

public class SistemaBiblioteca {

    public static void iniciar() {
        Repositorio repo = Repositorio.getInstancia();

        // --- USUÁRIOS ---
        repo.adicionarUsuario(new AlunoGraduacao(123, "João da Silva"));
        repo.adicionarUsuario(new AlunoPosGraduacao(456, "Luiz Fernando Rodrigues"));
        repo.adicionarUsuario(new AlunoGraduacao(789, "Pedro Paulo"));
        repo.adicionarUsuario(new Professor(100, "Carlos Lucena"));

        // --- LIVROS E EXEMPLARES ---
        Livro l1 = new Livro(100, "Engenharia de Software", "Addison Wesley", "Ian Sommerville", "6ª", 2000);
        l1.adicionarExemplar(new Exemplar(10001));
        l1.adicionarExemplar(new Exemplar(10002));
        repo.adicionarLivro(l1);

        Livro l2 = new Livro(101, "UML - Guia do Usuário", "Campus", "Booch, Rumbaugh, Jacobson", "7ª", 2000);
        l2.adicionarExemplar(new Exemplar(10103));
        repo.adicionarLivro(l2);

        Livro l3 = new Livro(200, "Code Complete", "Microsoft Press", "Steve McConnell", "2ª", 2014);
        l3.adicionarExemplar(new Exemplar(20004));
        repo.adicionarLivro(l3);

        Livro l4 = new Livro(201, "Agile Software Development", "Prentice Hall", "Robert Martin", "1ª", 2002);
        l4.adicionarExemplar(new Exemplar(20105));
        repo.adicionarLivro(l4);

        Livro l5 = new Livro(300, "Refactoring", "Addison Wesley Professional", "Martin Fowler", "1ª", 1999);
        l5.adicionarExemplar(new Exemplar(30006));
        l5.adicionarExemplar(new Exemplar(30007));
        repo.adicionarLivro(l5);

        Livro l6 = new Livro(400, "Design Patterns", "Addison Wesley Professional", "Gamma, Helm, Johnson, Vlissides", "1ª", 1994);
        l6.adicionarExemplar(new Exemplar(40008));
        l6.adicionarExemplar(new Exemplar(40009));
        repo.adicionarLivro(l6);

        Livro l7 = new Livro(301, "Software Metrics", "CRC Press", "Fenton, Bieman", "3ª", 2014);
        repo.adicionarLivro(l7);

        Livro l8 = new Livro(401, "UML Distilled", "Addison Wesley Professional", "Martin Fowler", "3ª", 2003);
        repo.adicionarLivro(l8);

        // --- INICIAR TERMINAL ---
        Entrada entrada = new Entrada();
        entrada.iniciar();
    }
}
