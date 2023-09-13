package org.example;

import javax.persistence.*;

@Entity
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    String nome;
    @Column(name = "documento")
    String cpf;

    String contato;

    int idade;
    String cidade;
    String rua;
    int numero;

    @ManyToOne
    @JoinColumn(name = "profissão_id")
    private Profissao profissao;

    public Pessoa(String nome, String cpf,String contato, int idade, String cidade, String rua, int numero, Profissao profissao) {
        this.nome = nome;
        this.cpf = cpf;
        this.contato = contato;
        this.idade = idade;
        this.cidade = cidade;
        this.rua = rua;
        this.numero = numero;
        this.profissao = profissao;
    }

    public Pessoa() {

    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Profissao getProfissao() {
        return profissao;
    }

    public void setProfissao(Profissao profissao) {
        this.profissao = profissao;
    }

    //lista pessoas
    public void listarPessoa(){
        System.out.println("ID: " + getId() +
            "\nNome: " + getNome()+
            "\nDocumento: " + getCpf() +
            "\nContato: " + getContato() +
            "\nIdade: " + getIdade() +
            "\nCidade: " + getCidade() +
            "\nRua: " + getRua() +
            "\nNumero: " + getNumero() +
            "\nProfissão: " + getProfissao().getProfissao() + "\n\n");
    }
}
