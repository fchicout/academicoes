package br.edu.faculdadedamas.basicas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Turma {
    private int id;
    private String codigo;
    private String turno;
    private Disciplina fkDisciplina;
    private Professor fkProfessor;

    public Turma() {
        super();
    }

    public Turma(int id, String codigo, String turno, Disciplina fkDisciplina, Professor fkProfessor) {
        this.id = id;
        this.codigo = codigo;
        this.turno = turno;
        this.fkDisciplina = fkDisciplina;
        this.fkProfessor = fkProfessor;
    }

    public Turma(int id, String codigo, String turno, int fkDisciplinaId, int fkProfessorId) {
        this.id = id;
        this.codigo = codigo;
        this.turno = turno;

        Disciplina d = new Disciplina();
        d.setId(fkDisciplinaId);
        d.retrieve();
        this.fkDisciplina = d;

        Professor p = new Professor();
        p.setId(fkProfessorId);
        p.retrieve();
        this.fkProfessor = p;
    }

    public void create() throws Exception {
        if (getFkDisciplina() == null) {
            throw new Exception("Não posso criar turmas que não estejam associadas à disciplinas");
        }

        Disciplina dbDisciplina = new Disciplina();
        dbDisciplina.setId(getFkDisciplina().getId());
        dbDisciplina.retrieve();

        if (dbDisciplina.getNome() == null) {
            throw new Exception("Disciplina indicada inexistente");
        }

        try {
            Connection conn = DriverManager
                    .getConnection("jdbc:mariadb://localhost:3306/AcademicoES?user=root&password=qwerty@123");
            String query = "insert into Turmas (codigo, turno, fk_Disciplinas_id, fk_Professores_id) values (\'" + getCodigo()
                    + "\', \'" + getTurno() + "\', "+ getFkDisciplina().getId() + ", "+ getFkProfessor().getId() +")";
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.executeQuery();

            stmt.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void retrieve(){
        try {
            Connection conn = DriverManager
                    .getConnection("jdbc:mariadb://localhost:3306/AcademicoES?user=root&password=qwerty@123");
            String query = "select * from Turmas where id=" + getId() + ";";
            PreparedStatement stmt = conn.prepareStatement(query);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                setCodigo(rs.getString("codigo"));
                setTurno(rs.getString("turno"));
                

                Disciplina d = new Disciplina();
                d.setId(rs.getInt("fk_Disciplinas_id"));
                d.retrieve();
                setFkDisciplina(d);

                Professor p = new Professor();
                p.setId(rs.getInt("fk_Professor_id"));
                p.retrieve();
                setFkProfessor(p);
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public Disciplina getFkDisciplina() {
        return fkDisciplina;
    }

    public void setFkDisciplina(Disciplina fkDisciplina) {
        this.fkDisciplina = fkDisciplina;
    }

    public Professor getFkProfessor() {
        return fkProfessor;
    }

    public void setFkProfessor(Professor fkProfessor) {
        this.fkProfessor = fkProfessor;
    }

    @Override
    public String toString() {
        return "Turma [id=" + id + ", codigo=" + codigo + ", turno=" + turno + ", fkDisciplina=" + fkDisciplina
                + ", fkProfessor=" + fkProfessor + "]";
    }

}
