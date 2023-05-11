package br.com.mercado.model;

public class Cliente {

    private int id;
    private String nome;
    private int celular;
    private boolean desconto;

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

    public int getCelular() {
        return celular;
    }

    public void setCelular(int celular) {
        this.celular = celular;
    }

    public boolean getDesconto() {
        return desconto;
    }

    public void setDesconto(boolean desconto) {
        this.desconto = desconto;
    }

}
