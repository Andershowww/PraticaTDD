package main.java.br.com.fornecedor.balanco.model.interfaces;
import main.java.br.com.fornecedor.balanco.model.Lote;

public interface ILoteRepository {
	Lote buscarPorId(String idLote);
}
