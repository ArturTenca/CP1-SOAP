package br.com.grupo.soap.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Reserva {

    private int id;
    private int salaId;
    private String responsavel;
    private String data;
    private String horario;
    private String status;

    public Reserva() {}

    public Reserva(int id, int salaId, String responsavel, String data, String horario, String status) {
        this.id = id;
        this.salaId = salaId;
        this.responsavel = responsavel;
        this.data = data;
        this.horario = horario;
        this.status = status;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getSalaId() { return salaId; }
    public void setSalaId(int salaId) { this.salaId = salaId; }

    public String getResponsavel() { return responsavel; }
    public void setResponsavel(String responsavel) { this.responsavel = responsavel; }

    public String getData() { return data; }
    public void setData(String data) { this.data = data; }

    public String getHorario() { return horario; }
    public void setHorario(String horario) { this.horario = horario; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "Reserva{id=" + id + ", salaId=" + salaId + ", responsavel='" + responsavel
                + "', data='" + data + "', horario='" + horario + "', status='" + status + "'}";
    }
}
