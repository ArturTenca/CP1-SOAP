package br.com.grupo.soap.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class SalaListResponse {

    private List<Sala> salas;
    private int total;

    public SalaListResponse() {}

    public SalaListResponse(List<Sala> salas) {
        this.salas = salas;
        this.total = salas != null ? salas.size() : 0;
    }

    public List<Sala> getSalas() { return salas; }
    public void setSalas(List<Sala> salas) { this.salas = salas; }

    public int getTotal() { return total; }
    public void setTotal(int total) { this.total = total; }
}
