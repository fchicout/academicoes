package br.edu.faculdadedamas.basicas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Aluno {
    private int id;
    private String matricula;
    private String nome;
    private String cpf;
    private String dtNascimento;
    private String dtMatricula;

    public Aluno() {
        super();
    }

    public Aluno(int id, String matricula, String nome, String cpf, String dtNascimento, String dtMatricula) {
        this.id = id;
        this.matricula = matricula;
        this.nome = nome;
        this.cpf = cpf;
        this.dtNascimento = dtNascimento;
        this.dtMatricula = dtMatricula;
    }

    public void create() {
        try {
            Connection conn = DriverManager
                    .getConnection("jdbc:mariadb://localhost:3306/AcademicoES?user=root&password=qwerty@123");
            String query = "insert into Alunos (matricula, nome, cpf, dtNasc, dtMatricula) values (\'" + getMatricula()
                    + "\', \'" + getNome() + "\', \'" + getCpf() + "\', \'" + getDtNascimento() + "\', \'" + getDtMatricula() + "\')";
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
            String query = "select * from Alunos where id=" + getId() + ";";
            PreparedStatement stmt = conn.prepareStatement(query);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                setCpf(rs.getString("cpf"));
                setNome(rs.getString("nome"));
                setMatricula(rs.getString("matricula"));
                setDtMatricula(rs.getString("dtMatricula"));
                setDtNascimento(rs.getString("dtNasc"));
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
            String query = "delete from Alunos where id=" + getId() + ";";
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
        Aluno dbAluno = new Aluno();
        dbAluno.setId(getId());
        dbAluno.retrieve();
        
        if (!getNome().equals(dbAluno.getNome())) {
            valuesToUpdate += "nome = \'" + getNome() + "\' ";
        }
        if (!getCpf().equals(dbAluno.getCpf())) {
            valuesToUpdate += "cpf = \'" + getCpf() + "\' ";
        }
        if (!getDtMatricula().equals(dbAluno.getDtMatricula())) {
            valuesToUpdate += "dtReconhecimento = \'" + getDtMatricula() + "\' ";
        }
        if (!getDtNascimento().equals(dbAluno.getDtNascimento())) {
            valuesToUpdate += "dtNasc = \'" + getDtNascimento() + "\' ";
        }
        if (!getMatricula().equals(dbAluno.getMatricula())) {
            valuesToUpdate += "matricula = \'" + getMatricula() + "\' ";
        }

        try {
            Connection conn = DriverManager
                    .getConnection("jdbc:mariadb://localhost:3306/AcademicoES?user=root&password=qwerty@123");
            String query = "update Alunos set " + valuesToUpdate + "where id=" + getId() + ";";
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

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(String dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public String getDtMatricula() {
        return dtMatricula;
    }

    public void setDtMatricula(String dtMatricula) {
        this.dtMatricula = dtMatricula;
    }

    @Override
    public String toString() {
        return "Aluno [id=" + id + ", matricula=" + matricula + ", nome=" + nome + ", cpf=" + cpf + ", dtNascimento="
                + dtNascimento + ", dtMatricula=" + dtMatricula + "]";
    }

}
