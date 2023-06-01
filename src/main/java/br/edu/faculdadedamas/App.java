package br.edu.faculdadedamas;

import br.edu.faculdadedamas.basicas.Curriculo;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        Curriculo curriculo = new Curriculo();
        curriculo.setId(1);
        curriculo.retrieve();

        System.out.println(curriculo);

    }
}
