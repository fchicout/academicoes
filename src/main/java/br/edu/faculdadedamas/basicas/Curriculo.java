package br.edu.faculdadedamas.basicas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Curriculo {
    private int id;
    private int sequencial;
    private LocalDate dtAtivacao;
    private Curso curso;

    public Curriculo() {
        super();
    }

    public Curriculo(int id, int sequencial, LocalDate dtAtivacao, Curso curso) {
        this.id = id;
        this.sequencial = sequencial;
        this.dtAtivacao = dtAtivacao;
        this.curso = curso;
    }

    public Curriculo(int id, int sequencial, LocalDate dtAtivacao, int idCurso) {
        this.id = id;
        this.sequencial = sequencial;
        this.dtAtivacao = dtAtivacao;

        Curso c = new Curso();
        c.setId(idCurso);
        c.retrieve();

        this.curso = c;
    }

    public void create() throws Exception {
        if (getCurso() == null) {
            throw new Exception("Não posso criar currículo para curso inexistente");
        }

        Curso dbCurso = new Curso();
        dbCurso.setId(getCurso().getId());
        dbCurso.retrieve();

        if (dbCurso.getNome() == null) {
            throw new Exception("Curso indicado inexistente");
        }

        try {
            Connection conn = DriverManager
                    .getConnection("jdbc:mariadb://localhost:3306/AcademicoES?user=root&password=qwerty@123");
            String query = "insert into Curriculos (sequencial, dtAtivacao, fk_Cursos_id) values (" + getSequencial()
                    + ", \'" + getDtAtivacao() + "\', " + getCurso().getId() + ")";
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.executeQuery();

            stmt.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void retrieve() {
        try {
            Connection conn = DriverManager
                    .getConnection("jdbc:mariadb://localhost:3306/AcademicoES?user=root&password=qwerty@123");
            String query = "select * from Curriculos where id=" + getId() + ";";
            PreparedStatement stmt = conn.prepareStatement(query);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                setSequencial(rs.getInt("sequencial"));
                setDtAtivacao(rs.getDate("dtAtivacao").toLocalDate());

                Curso c = new Curso();
                c.setId(rs.getInt("fk_Cursos_id"));
                c.retrieve();

                setCurso(c);

            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete() {
        try {
            Connection conn = DriverManager
                    .getConnection("jdbc:mariadb://localhost:3306/AcademicoES?user=root&password=qwerty@123");
            String query = "delete from Curriculos where id=" + getId() + ";";
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.executeQuery();

            stmt.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        String valuesToUpdate = "";
        Curriculo dbCurriculo = new Curriculo();
        dbCurriculo.setId(getId());
        dbCurriculo.retrieve();
        
        if (getSequencial() != dbCurriculo.getSequencial()) {
            valuesToUpdate += "sequencial = " + getSequencial() + " ";
        }
        if (getDtAtivacao() != dbCurriculo.getDtAtivacao()) {
            valuesToUpdate += "dtAtivacao = " + getDtAtivacao() + " ";
        }
        if (getCurso().getId() != dbCurriculo.getCurso().getId()) {
            valuesToUpdate += "fk_Cursos_id = " + getCurso().getId() + " ";
        }

        try {
            Connection conn = DriverManager
                    .getConnection("jdbc:mariadb://localhost:3306/AcademicoES?user=root&password=qwerty@123");
            String query = "update Curriculos set " + valuesToUpdate + "where id=" + getId() + ";";
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.executeQuery();

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

    public int getSequencial() {
        return sequencial;
    }

    public void setSequencial(int sequencial) {
        this.sequencial = sequencial;
    }

    public LocalDate getDtAtivacao() {
        return dtAtivacao;
    }

    public void setDtAtivacao(LocalDate dtAtivacao) {
        this.dtAtivacao = dtAtivacao;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    @Override
    public String toString() {
        return "Curriculo [id=" + id + ", sequencial=" + sequencial + ", dtAtivacao=" + dtAtivacao + ", curso=" + curso
                + "]";
    }

}
