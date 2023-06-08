package br.edu.faculdadedamas;

import br.edu.faculdadedamas.basicas.Aluno;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        Aluno a = new Aluno();
        a.setCpf("12312312311");
        a.setDtMatricula("2022-05-23");
        a.setDtNascimento("1987-10-19");
        a.setMatricula("201233544");
        a.setNome("Fabio");

        System.out.println(a);

    }
}
