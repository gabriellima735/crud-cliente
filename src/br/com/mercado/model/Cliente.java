package br.com.mercado.model;

public class Cliente {

    private int id;
    private String nome;
    private int telefone;
    private String endereco;
    private boolean isTorcedorFlamengo;
    private boolean isFanOnePiece;
    private boolean isSouza;

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

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public boolean getTorcedorFlamengo() {
        return isTorcedorFlamengo;
    }

    public void setTorcedorFlamengo(boolean torcedorFlamengo) {
        isTorcedorFlamengo = torcedorFlamengo;
    }

    public boolean getFanOnePiece() {
        return isFanOnePiece;
    }

    public void setFanOnePiece(boolean fanOnePiece) {
        isFanOnePiece = fanOnePiece;
    }

    public boolean getSouza() {
        return isSouza;
    }

    public void setSouza(boolean souza) {
        isSouza = souza;
    }

}
