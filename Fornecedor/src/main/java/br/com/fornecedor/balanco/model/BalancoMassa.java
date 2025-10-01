package main.java.br.com.fornecedor.balanco.model;
import main.java.br.com.fornecedor.balanco.model.interfaces.IBalancoMassa;

public class BalancoMassa implements IBalancoMassa {

    private int materiaPrimaConsumida;
    private int produtoGerado;
    private int perdas;
    private int desvio;
    private double desvioPerc;
    private double limiteToleravel;

    public BalancoMassa(int materiaPrimaConsumida, int produtoGerado, int perdas) {
        this.materiaPrimaConsumida = materiaPrimaConsumida;
        this.produtoGerado = produtoGerado;
        this.perdas = perdas;
    }

    @Override
    public void calcula() {
        if (materiaPrimaConsumida <= 0 || produtoGerado <= 0 || perdas < 0) {
            throw new IllegalArgumentException("Todos os campos obrigatórios devem ser preenchidos corretamente");
        }
        desvio = materiaPrimaConsumida - (produtoGerado + perdas);
        desvioPerc = ((double) desvio / materiaPrimaConsumida) * 100;
    }

    @Override
    public int getDesvio() { return desvio; }

    @Override
    public double getDesvioPerc() { return desvioPerc; }

    @Override
    public void setLimiteToleravel(double limite) { this.limiteToleravel = limite; }

    @Override
    public boolean alerta() { return desvioPerc > limiteToleravel; }
}
