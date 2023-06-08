package br.edu.faculdadedamas.servicos;

import br.edu.faculdadedamas.basicas.Aluno;
import br.edu.faculdadedamas.basicas.Curriculo;
import br.edu.faculdadedamas.basicas.CurriculosDisciplinas;
import br.edu.faculdadedamas.basicas.Curso;
import br.edu.faculdadedamas.basicas.Disciplina;
import br.edu.faculdadedamas.basicas.Turma;

public class AcademicoUtil {
    public String geraMatriculaAluno(Aluno aluno){
        String mat = "";


        Matricula m = new Matricula();
        m.getFkAluno(aluno.getId());
        Turma t = m.getPrimeiraTurma();
        Disciplina d = t.getFkDisciplina();
        CurriculosDisciplinas cd = new CurriculosDisciplinas();
        cd.setDisciplina(d);
        cd.retrieve();
        Curriculo curr = cd.getCurriculo();
        Curso curso = curr.getCurso();

        mat += d.getPeriodo();
        mat += curr.getSequencial();
        mat += curso.getId();
        mat += curso.getRegime();
        mat += curso.getTurno().substring(0, 1).toUpperCase();



        return mat;
    }

    public String geraMatriculaProfessor(){

    }
}
