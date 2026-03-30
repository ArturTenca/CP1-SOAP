package br.com.grupo.soap.publisher;

import br.com.grupo.soap.service.ReservaService;

import javax.xml.ws.Endpoint;

public class ServicePublisher {

    public static void main(String[] args) {
        String url = "http://localhost:8080/reserva";

        System.out.println("========================================");
        System.out.println("  Sistema de Reserva de Salas - SOAP   ");
        System.out.println("========================================");
        System.out.println("Iniciando servidor...");

        Endpoint.publish(url, new ReservaService());

        System.out.println("Servico publicado com sucesso!");
        System.out.println();
        System.out.println("Endpoint : " + url);
        System.out.println("WSDL     : " + url + "?wsdl");
        System.out.println();
        System.out.println("Operacoes disponiveis:");
        System.out.println("  - listarSalas");
        System.out.println("  - reservarSala");
        System.out.println("  - cancelarReserva");
        System.out.println("  - consultarReserva");
        System.out.println();
        System.out.println("Pressione CTRL+C para encerrar.");
        System.out.println("========================================");
    }
}
