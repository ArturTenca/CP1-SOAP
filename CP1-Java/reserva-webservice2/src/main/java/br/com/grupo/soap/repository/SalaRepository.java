package br.com.grupo.soap.repository;

import br.com.grupo.soap.model.Sala;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SalaRepository {

    private static final List<Sala> salas = new ArrayList<>();

    static {
        salas.add(new Sala(1, "Sala Azul",     10, "Bloco A - 1º Andar", true));
        salas.add(new Sala(2, "Sala Verde",    20, "Bloco A - 2º Andar", true));
        salas.add(new Sala(3, "Auditório",     80, "Bloco B - Térreo",   true));
        salas.add(new Sala(4, "Sala Vermelha",  6, "Bloco C - 1º Andar", true));
        salas.add(new Sala(5, "Sala Executive", 12, "Bloco A - 3º Andar", true));
    }

    public List<Sala> listarTodas() {
        return new ArrayList<>(salas);
    }

    public List<Sala> listarDisponiveis() {
        List<Sala> disponiveis = new ArrayList<>();
        for (Sala s : salas) {
            if (s.isDisponivel()) disponiveis.add(s);
        }
        return disponiveis;
    }

    public Optional<Sala> buscarPorId(int id) {
        for (Sala s : salas) {
            if (s.getId() == id) return Optional.of(s);
        }
        return Optional.empty();
    }

    public void setDisponivel(int salaId, boolean disponivel) {
        for (Sala s : salas) {
            if (s.getId() == salaId) {
                s.setDisponivel(disponivel);
                return;
            }
        }
    }
}
