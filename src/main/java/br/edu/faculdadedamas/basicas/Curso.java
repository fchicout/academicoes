package br.edu.faculdadedamas.basicas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Curso {
    private int id;
    private String nome;
    private LocalDate dtAutorizacao;
    private String dtReconhecimento;
    private String turno;

    public Curso() {
        super();
    }

    public Curso(int id, String nome, LocalDate dtAutorizacao, String dtReconhecimento, String turno) {
        this.id = id;
        this.nome = nome;
        this.dtAutorizacao = dtAutorizacao;
        this.dtReconhecimento = dtReconhecimento;
        this.turno = turno;
    }

    public void create() {
        try {
            Connection conn = DriverManager
                    .getConnection("jdbc:mariadb://localhost:3306/AcademicoES?user=root&password=qwerty@123");
            String query = "insert into Cursos (nome, dtAutorizacao, dtReconhecimento, turno) values (\'" + getNome()
                    + "\', \'" + getDtAutorizacao() + "\', \'" + getDtReconhecimento() + "\', \'" + getTurno() + "\')";
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
            String query = "select * from Cursos where id=" + getId() + ";";
            PreparedStatement stmt = conn.prepareStatement(query);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                setNome(rs.getString("nome"));
                setDtAutorizacao(rs.getDate("dtAutorizacao").toLocalDate());
                setDtReconhecimento(rs.getString("dtReconhecimento"));
                setTurno(rs.getString("turno"));
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
            String query = "delete from Cursos where id=" + getId() + ";";
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
        Curso dbCurso = new Curso();
        dbCurso.setId(getId());
        dbCurso.retrieve();
        
        if (!getNome().equals(dbCurso.getNome())) {
            valuesToUpdate += "nome = \'" + getNome() + "\' ";
        }
        if (getDtAutorizacao() != dbCurso.getDtAutorizacao()) {
            valuesToUpdate += "dtAutorizacao = \'" + getDtAutorizacao() + "\' ";
        }
        if (!getDtReconhecimento().equals(dbCurso.getDtReconhecimento())) {
            valuesToUpdate += "dtReconhecimento = \'" + getDtReconhecimento() + "\' ";
        }
        if (!getTurno().equals(dbCurso.getTurno())) {
            valuesToUpdate += "turno = \'" + getTurno() + "\' ";
        }

        try {
            Connection conn = DriverManager
                    .getConnection("jdbc:mariadb://localhost:3306/AcademicoES?user=root&password=qwerty@123");
            String query = "update Cursos set " + valuesToUpdate + "where id=" + getId() + ";";
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDtAutorizacao() {
        return dtAutorizacao;
    }

    public void setDtAutorizacao(LocalDate dtAutorizacao) {
        this.dtAutorizacao = dtAutorizacao;
    }

    public String getDtReconhecimento() {
        return dtReconhecimento;
    }

    public void setDtReconhecimento(String dtReconhecimento) {
        this.dtReconhecimento = dtReconhecimento;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    @Override
    public String toString() {
        return "Curso [id=" + id + ", nome=" + nome + ", dtAutorizacao=" + dtAutorizacao + ", dtReconhecimento="
                + dtReconhecimento + ", turno=" + turno + "]";
    }

}
