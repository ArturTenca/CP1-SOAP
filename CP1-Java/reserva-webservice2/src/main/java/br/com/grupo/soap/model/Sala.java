package br.com.grupo.soap.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Sala {

    private int id;
    private String nome;
    private int capacidade;
    private String localizacao;
    private boolean disponivel;

    public Sala() {}

    public Sala(int id, String nome, int capacidade, String localizacao, boolean disponivel) {
        this.id = id;
        this.nome = nome;
        this.capacidade = capacidade;
        this.localizacao = localizacao;
        this.disponivel = disponivel;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public int getCapacidade() { return capacidade; }
    public void setCapacidade(int capacidade) { this.capacidade = capacidade; }

    public String getLocalizacao() { return localizacao; }
    public void setLocalizacao(String localizacao) { this.localizacao = localizacao; }

    public boolean isDisponivel() { return disponivel; }
    public void setDisponivel(boolean disponivel) { this.disponivel = disponivel; }

    @Override
    public String toString() {
        return "Sala{id=" + id + ", nome='" + nome + "', capacidade=" + capacidade
                + ", localizacao='" + localizacao + "', disponivel=" + disponivel + "}";
    }
}
