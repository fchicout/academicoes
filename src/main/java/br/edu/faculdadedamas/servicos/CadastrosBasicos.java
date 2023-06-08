package br.edu.faculdadedamas.servicos;

import java.time.LocalDate;

import br.edu.faculdadedamas.basicas.Curso;

public class CadastrosBasicos {
    public Curso newCurso(String nome, LocalDate dtAutorizacao, String dtReconhecimento, String turno){
        Curso c = new Curso();
        c.setNome(nome);
        c.setDtAutorizacao(dtAutorizacao);
        c.setDtReconhecimento(dtReconhecimento);
        c.setTurno(turno);
        c.create();
        return c;
    };
    
}
