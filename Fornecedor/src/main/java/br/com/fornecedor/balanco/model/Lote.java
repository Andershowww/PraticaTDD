package main.java.br.com.fornecedor.balanco.model;

public class Lote {
	private String id;
	private int materiaPrimaConsumida;
	private int produtoGerado;
	private int perdas;

	public Lote(String id, int materiaPrimaConsumida, int produtoGerado, int perdas) {
		this.id = id;
		this.materiaPrimaConsumida = materiaPrimaConsumida;
		this.produtoGerado = produtoGerado;
		this.perdas = perdas;
	}

	public String getId() { return id; }
	public int getMateriaPrimaConsumida() { return materiaPrimaConsumida; }
	public int getProdutoGerado() { return produtoGerado; }
	public int getPerdas() { return perdas; }
}
