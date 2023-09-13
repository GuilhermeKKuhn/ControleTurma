package org.example;

import javax.persistence.*;
import java.util.List;

@Entity
public class Profissao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "Profissão")
    private String profissao;
    @Column(name = "Salario")
    private double salario;

    public Profissao() {
    }

    public Profissao(String profissao) {
        this.profissao = profissao;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    //lista profissoes
    public void listarProfissoes(){
        System.out.println(getId() + "." + getProfissao());
    }
}
