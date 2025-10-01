package main.java.br.com.fornecedor.balanco.model.interfaces;

public interface IBalancoMassa {
	void calcula();
	int getDesvio();
	double getDesvioPerc();
	void setLimiteToleravel(double limite);
	boolean alerta();
}
