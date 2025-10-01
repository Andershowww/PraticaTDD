package main.java.br.com.fornecedor.balanco.repository;

import java.util.List;

import main.java.br.com.fornecedor.balanco.model.interfaces.ILoteRepository;
import main.java.br.com.fornecedor.balanco.model.Lote;

public class LoteRepository implements ILoteRepository {

    private final List<Lote> lotes;

    public LoteRepository(List<Lote> lotes) {
        this.lotes = lotes;
    }

    @Override
    public Lote buscarPorId(String idLote) {
        return lotes.stream()
                    .filter(l -> l.getId().equals(idLote))
                    .findFirst()
                    .orElse(null);
    }
}