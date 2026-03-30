package br.com.grupo.soap.repository;

import br.com.grupo.soap.model.Reserva;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReservaRepository {

    private static final List<Reserva> reservas = new ArrayList<>();
    private static int contadorId = 1;

    public Reserva salvar(Reserva reserva) {
        reserva.setId(contadorId++);
        reservas.add(reserva);
        return reserva;
    }

    public List<Reserva> listarTodas() {
        return new ArrayList<>(reservas);
    }

    public Optional<Reserva> buscarPorId(int id) {
        for (Reserva r : reservas) {
            if (r.getId() == id) return Optional.of(r);
        }
        return Optional.empty();
    }

    public List<Reserva> listarPorSala(int salaId) {
        List<Reserva> resultado = new ArrayList<>();
        for (Reserva r : reservas) {
            if (r.getSalaId() == salaId && "ATIVA".equals(r.getStatus())) {
                resultado.add(r);
            }
        }
        return resultado;
    }

    public boolean existeConflito(int salaId, String data, String horario) {
        for (Reserva r : reservas) {
            if (r.getSalaId() == salaId
                    && r.getData().equals(data)
                    && r.getHorario().equals(horario)
                    && "ATIVA".equals(r.getStatus())) {
                return true;
            }
        }
        return false;
    }
}
