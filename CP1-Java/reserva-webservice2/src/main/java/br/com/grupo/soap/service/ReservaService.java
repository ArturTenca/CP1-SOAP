package br.com.grupo.soap.service;

import br.com.grupo.soap.model.Reserva;
import br.com.grupo.soap.model.ReservaResponse;
import br.com.grupo.soap.model.Sala;
import br.com.grupo.soap.model.SalaListResponse;
import br.com.grupo.soap.repository.ReservaRepository;
import br.com.grupo.soap.repository.SalaRepository;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;
import java.util.Optional;

@WebService(
    serviceName = "ReservaService",
    portName    = "ReservaPort",
    targetNamespace = "http://service.soap.grupo.com.br/"
)
public class ReservaService {

    private final SalaRepository salaRepository = new SalaRepository();
    private final ReservaRepository reservaRepository = new ReservaRepository();

    @WebMethod(operationName = "listarSalas")
    public SalaListResponse listarSalas() {
        List<Sala> disponiveis = salaRepository.listarDisponiveis();
        return new SalaListResponse(disponiveis);
    }

    @WebMethod(operationName = "reservarSala")
    public ReservaResponse reservarSala(
            @WebParam(name = "salaId")      int salaId,
            @WebParam(name = "responsavel") String responsavel,
            @WebParam(name = "data")        String data,
            @WebParam(name = "horario")     String horario
    ) {
        if (responsavel == null || responsavel.trim().isEmpty()) {
            return new ReservaResponse(false, "O campo responsavel é obrigatório.");
        }
        if (data == null || data.trim().isEmpty()) {
            return new ReservaResponse(false, "O campo data é obrigatório (dd/MM/yyyy).");
        }
        if (horario == null || horario.trim().isEmpty()) {
            return new ReservaResponse(false, "O campo horario é obrigatório (HH:mm).");
        }

        Optional<Sala> salaOpt = salaRepository.buscarPorId(salaId);
        if (salaOpt.isEmpty()) {
            return new ReservaResponse(false, "Sala com ID " + salaId + " não encontrada.");
        }

        Sala sala = salaOpt.get();

        if (!sala.isDisponivel()) {
            return new ReservaResponse(false, "A sala " + sala.getNome() + " não está disponível.");
        }

        if (reservaRepository.existeConflito(salaId, data, horario)) {
            return new ReservaResponse(false,
                    "Já existe uma reserva para " + sala.getNome() + " em " + data + " às " + horario + ".");
        }

        Reserva novaReserva = new Reserva(0, salaId, responsavel.trim(), data.trim(), horario.trim(), "ATIVA");
        Reserva salva = reservaRepository.salvar(novaReserva);

        return new ReservaResponse(true,
                "Reserva criada! ID: " + salva.getId() + " | Sala: " + sala.getNome()
                + " | " + data + " às " + horario, salva);
    }

    @WebMethod(operationName = "cancelarReserva")
    public ReservaResponse cancelarReserva(
            @WebParam(name = "reservaId") int reservaId
    ) {
        Optional<Reserva> reservaOpt = reservaRepository.buscarPorId(reservaId);

        if (reservaOpt.isEmpty()) {
            return new ReservaResponse(false, "Reserva com ID " + reservaId + " não encontrada.");
        }

        Reserva reserva = reservaOpt.get();

        if ("CANCELADA".equals(reserva.getStatus())) {
            return new ReservaResponse(false, "A reserva " + reservaId + " já estava cancelada.");
        }

        reserva.setStatus("CANCELADA");

        return new ReservaResponse(true, "Reserva " + reservaId + " cancelada com sucesso.", reserva);
    }

    @WebMethod(operationName = "consultarReserva")
    public ReservaResponse consultarReserva(
            @WebParam(name = "reservaId") int reservaId
    ) {
        Optional<Reserva> reservaOpt = reservaRepository.buscarPorId(reservaId);

        if (reservaOpt.isEmpty()) {
            return new ReservaResponse(false, "Reserva com ID " + reservaId + " não encontrada.");
        }

        return new ReservaResponse(true, "Reserva encontrada.", reservaOpt.get());
    }
}
