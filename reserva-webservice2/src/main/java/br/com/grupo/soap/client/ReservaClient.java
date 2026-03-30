package br.com.grupo.soap.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class ReservaClient {

    private static final String ENDPOINT = "http://localhost:8080/reserva";
    private static final String NAMESPACE = "http://service.soap.grupo.com.br/";

    public static void main(String[] args) throws Exception {
        System.out.println("================================================");
        System.out.println("  Cliente SOAP - Sistema de Reserva de Salas   ");
        System.out.println("================================================\n");

        System.out.println(">>> [1] Listando salas disponiveis...");
        System.out.println(chamarSOAP(buildListarSalas()));

        System.out.println(">>> [2] Reservando Sala 1 para Joao Silva...");
        System.out.println(chamarSOAP(buildReservarSala(1, "Joao Silva", "30/03/2026", "14:00")));

        System.out.println(">>> [3] Tentando reservar novamente (conflito esperado)...");
        System.out.println(chamarSOAP(buildReservarSala(1, "Maria Souza", "30/03/2026", "14:00")));

        System.out.println(">>> [4] Consultando reserva de ID 1...");
        System.out.println(chamarSOAP(buildConsultarReserva(1)));

        System.out.println(">>> [5] Cancelando reserva de ID 1...");
        System.out.println(chamarSOAP(buildCancelarReserva(1)));

        System.out.println(">>> [6] Tentando cancelar reserva ja cancelada...");
        System.out.println(chamarSOAP(buildCancelarReserva(1)));

        System.out.println("================================================");
        System.out.println("  Fim das chamadas ao WebService SOAP           ");
        System.out.println("================================================");
    }

    private static String chamarSOAP(String xmlBody) throws Exception {
        URL url = new URL(ENDPOINT);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type", "text/xml;charset=UTF-8");

        try (OutputStream os = conn.getOutputStream()) {
            os.write(xmlBody.getBytes(StandardCharsets.UTF_8));
        }

        int statusCode = conn.getResponseCode();
        BufferedReader reader;

        if (statusCode == 200) {
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
        } else {
            reader = new BufferedReader(new InputStreamReader(conn.getErrorStream(), StandardCharsets.UTF_8));
        }

        StringBuilder sb = new StringBuilder();
        String linha;
        while ((linha = reader.readLine()) != null) {
            sb.append(linha).append("\n");
        }
        reader.close();

        return sb.toString();
    }

    private static String buildListarSalas() {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
               "<soapenv:Envelope\n" +
               "    xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"\n" +
               "    xmlns:ser=\"" + NAMESPACE + "\">\n" +
               "    <soapenv:Header/>\n" +
               "    <soapenv:Body>\n" +
               "        <ser:listarSalas/>\n" +
               "    </soapenv:Body>\n" +
               "</soapenv:Envelope>";
    }

    private static String buildReservarSala(int salaId, String responsavel, String data, String horario) {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
               "<soapenv:Envelope\n" +
               "    xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"\n" +
               "    xmlns:ser=\"" + NAMESPACE + "\">\n" +
               "    <soapenv:Header/>\n" +
               "    <soapenv:Body>\n" +
               "        <ser:reservarSala>\n" +
               "            <salaId>" + salaId + "</salaId>\n" +
               "            <responsavel>" + responsavel + "</responsavel>\n" +
               "            <data>" + data + "</data>\n" +
               "            <horario>" + horario + "</horario>\n" +
               "        </ser:reservarSala>\n" +
               "    </soapenv:Body>\n" +
               "</soapenv:Envelope>";
    }

    private static String buildCancelarReserva(int reservaId) {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
               "<soapenv:Envelope\n" +
               "    xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"\n" +
               "    xmlns:ser=\"" + NAMESPACE + "\">\n" +
               "    <soapenv:Header/>\n" +
               "    <soapenv:Body>\n" +
               "        <ser:cancelarReserva>\n" +
               "            <reservaId>" + reservaId + "</reservaId>\n" +
               "        </ser:cancelarReserva>\n" +
               "    </soapenv:Body>\n" +
               "</soapenv:Envelope>";
    }

    private static String buildConsultarReserva(int reservaId) {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
               "<soapenv:Envelope\n" +
               "    xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"\n" +
               "    xmlns:ser=\"" + NAMESPACE + "\">\n" +
               "    <soapenv:Header/>\n" +
               "    <soapenv:Body>\n" +
               "        <ser:consultarReserva>\n" +
               "            <reservaId>" + reservaId + "</reservaId>\n" +
               "        </ser:consultarReserva>\n" +
               "    </soapenv:Body>\n" +
               "</soapenv:Envelope>";
    }
}
