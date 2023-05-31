package br.edu.faculdadedamas.basicas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Disciplina {
    private int id;
    private String nome;
    private int cargaHoraria;
    private int vagas;
    private int periodo;

    public Disciplina(){
        super();
    }

    public Disciplina(int id, String nome, int cargaHoraria, int vagas, int periodo) {
        this.id = id;
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
        this.vagas = vagas;
        this.periodo = periodo;
    }

    public void create() {
        try {
            Connection conn = DriverManager
                    .getConnection("jdbc:mariadb://localhost:3306/AcademicoES?user=root&password=qwerty@123");
            String query = "insert into Disciplinas (nome, cargaHoraria, vagas, periodo) values (\'" + getNome()
                    + "\', " + getCargaHoraria() + ", " + getVagas() + ", " + getPeriodo() + ")";
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
                setCargaHoraria(rs.getInt("cargaHoraria"));
                setVagas(rs.getInt("vagas"));
                setPeriodo(rs.getInt("periodo"));
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
            String query = "delete from Disciplinas where id=" + getId() + ";";
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
        Disciplina dbDisciplina = new Disciplina();
        dbDisciplina.setId(getId());
        dbDisciplina.retrieve();
        
        if (!getNome().equals(dbDisciplina.getNome())) {
            valuesToUpdate += "nome = \'" + getNome() + "\' ";
        }
        if (getCargaHoraria() != dbDisciplina.getCargaHoraria()) {
            valuesToUpdate += "cargaHoraria = " + getCargaHoraria() + " ";
        }
        if (getVagas() != dbDisciplina.getVagas()) {
            valuesToUpdate += "vagas = " + getVagas() + " ";
        }
        if (getPeriodo() != dbDisciplina.getPeriodo()) {
            valuesToUpdate += "periodo = " + getPeriodo() + " ";
        }

        try {
            Connection conn = DriverManager
                    .getConnection("jdbc:mariadb://localhost:3306/AcademicoES?user=root&password=qwerty@123");
            String query = "update Disciplinas set " + valuesToUpdate + "where id=" + getId() + ";";
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

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public int getVagas() {
        return vagas;
    }

    public void setVagas(int vagas) {
        this.vagas = vagas;
    }

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }

}
