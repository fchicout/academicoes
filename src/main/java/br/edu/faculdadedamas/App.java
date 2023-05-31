package br.edu.faculdadedamas;

import br.edu.faculdadedamas.basicas.Curso;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        Curso c = new Curso();
        c.setId(1);
        c.retrieve();
        System.out.println(c);
    }
}
