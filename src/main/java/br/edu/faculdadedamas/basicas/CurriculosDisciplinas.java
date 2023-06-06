package br.edu.faculdadedamas.basicas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// Tabela Possui (relação n-n entre Curriculos e Disciplinas)
public class CurriculosDisciplinas {
    private Curriculo curriculo;
    private Disciplina disciplina;

    public CurriculosDisciplinas() {
        super();
    }

    public CurriculosDisciplinas(Curriculo curriculo, Disciplina disciplina) {
        this.curriculo = curriculo;
        this.disciplina = disciplina;
    }

    public CurriculosDisciplinas(int idCurriculo, int idDisciplina) {
        Disciplina d = new Disciplina();
        d.setId(idDisciplina);
        d.retrieve();

        Curriculo c = new Curriculo();
        c.setId(idCurriculo);
        c.retrieve();

        this.curriculo = c;
        this.disciplina = d;
    }

    public void create() throws Exception {
        if ((getCurriculo() == null) || (getDisciplina() == null)) {
            throw new Exception("Não posso relacionar Curriculos e/ou disciplinas inexistentes");
        }

        Curriculo dbCurriculo = new Curriculo();
        dbCurriculo.setId(getCurriculo().getId());
        dbCurriculo.retrieve();

        if (dbCurriculo.getCurso() == null) {
            throw new Exception("Curriculo indicado inexistente");
        }

        Disciplina dbDisciplina = new Disciplina();
        dbDisciplina.setId(getDisciplina().getId());
        dbDisciplina.retrieve();

        if (dbDisciplina.getNome() == null) {
            throw new Exception("Disciplina indicada inexistente");
        }

        try {
            Connection conn = DriverManager
                    .getConnection("jdbc:mariadb://localhost:3306/AcademicoES?user=root&password=qwerty@123");
            String query = "insert into Possui (fk_Curriculos_id, fk_Disciplinas_id) values (" + getCurriculo().getId()
                    + ", " + getDisciplina().getId() + ")";
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.executeQuery();

            stmt.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Curriculo getCurriculo() {
        return curriculo;
    }

    public void setCurriculo(Curriculo curriculo) {
        this.curriculo = curriculo;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    @Override
    public String toString() {
        return "CurriculosDisciplinas [curriculo=" + curriculo + ", disciplina=" + disciplina + "]";
    }
}
