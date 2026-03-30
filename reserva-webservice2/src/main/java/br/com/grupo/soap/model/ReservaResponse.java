package br.com.grupo.soap.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ReservaResponse {

    private boolean sucesso;
    private String mensagem;
    private Reserva reserva;

    public ReservaResponse() {}

    public ReservaResponse(boolean sucesso, String mensagem) {
        this.sucesso = sucesso;
        this.mensagem = mensagem;
    }

    public ReservaResponse(boolean sucesso, String mensagem, Reserva reserva) {
        this.sucesso = sucesso;
        this.mensagem = mensagem;
        this.reserva = reserva;
    }

    public boolean isSucesso() { return sucesso; }
    public void setSucesso(boolean sucesso) { this.sucesso = sucesso; }

    public String getMensagem() { return mensagem; }
    public void setMensagem(String mensagem) { this.mensagem = mensagem; }

    public Reserva getReserva() { return reserva; }
    public void setReserva(Reserva reserva) { this.reserva = reserva; }

    @Override
    public String toString() {
        return "ReservaResponse{sucesso=" + sucesso + ", mensagem='" + mensagem + "', reserva=" + reserva + "}";
    }
}
