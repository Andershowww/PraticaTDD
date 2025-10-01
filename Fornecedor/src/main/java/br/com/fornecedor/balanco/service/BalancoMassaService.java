package main.java.br.com.fornecedor.balanco.service;

import main.java.br.com.fornecedor.balanco.model.interfaces.IBalancoMassa;
import main.java.br.com.fornecedor.balanco.model.interfaces.ILoteRepository;
import main.java.br.com.fornecedor.balanco.model.Lote;
import main.java.br.com.fornecedor.balanco.model.BalancoMassa;
public class BalancoMassaService {

    private final ILoteRepository loteRepository;

    public BalancoMassaService(ILoteRepository loteRepository) {
        this.loteRepository = loteRepository;
    }

    public IBalancoMassa calculaPorLote(String idLote, double limiteToleravel) {
        Lote lote = loteRepository.buscarPorId(idLote);
        if (lote == null) {
            throw new IllegalArgumentException("Lote não encontrado");
        }
        IBalancoMassa balanco = new BalancoMassa(
            lote.getMateriaPrimaConsumida(),
            lote.getProdutoGerado(),
            lote.getPerdas()
        );
        balanco.setLimiteToleravel(limiteToleravel);
        balanco.calcula();
        return balanco;
    }
}