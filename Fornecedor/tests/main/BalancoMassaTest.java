package main;

import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

import main.java.br.com.fornecedor.balanco.model.BalancoMassa;
import main.java.br.com.fornecedor.balanco.model.Lote;
import main.java.br.com.fornecedor.balanco.model.interfaces.IBalancoMassa;
import main.java.br.com.fornecedor.balanco.model.interfaces.ILoteRepository;
import main.java.br.com.fornecedor.balanco.repository.LoteRepository;
import main.java.br.com.fornecedor.balanco.service.BalancoMassaService;

public class BalancoMassaTest {

    @Test
    public void testeCalculoNormalBalancoMassa() {
        IBalancoMassa balanco = new BalancoMassa(1000, 900, 50);
        balanco.calcula();
        assertEquals(50, balanco.getDesvio());
        assertEquals(5.0, balanco.getDesvioPerc(), 0.001);
    }

    @Test
    public void testeCalculoDentroLimiteToleravel() {
        IBalancoMassa balanco = new BalancoMassa(1000, 950, 30);
        balanco.setLimiteToleravel(5);
        balanco.calcula();
        assertEquals(20, balanco.getDesvio()); 
        assertEquals(2.0, balanco.getDesvioPerc(), 0.001);
        assertFalse(balanco.alerta());
    }

    @Test
    public void testeCalculoAcimaLimiteToleravel() {
        IBalancoMassa balanco = new BalancoMassa(1000, 840, 100);
        balanco.setLimiteToleravel(5);
        balanco.calcula();
        assertEquals(60, balanco.getDesvio());
        assertEquals(6.0, balanco.getDesvioPerc(), 0.001);
        assertTrue(balanco.alerta());
    }

    @Test
    public void testeDadosObrigatoriosAusentesOuZerados() {
        IBalancoMassa balanco = new BalancoMassa(0, 0, 0);
        try {
            balanco.calcula();
            fail("Deveria lançar IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("Todos os campos obrigatórios devem ser preenchidos corretamente", e.getMessage());
        }
    }

    @Test
    public void testeCalculoPorLote() {
        List<Lote> lotes = Arrays.asList(
            new Lote("XYZ", 1000, 950, 30),
            new Lote("ABC", 1200, 1100, 50)
        );

        ILoteRepository repo = new LoteRepository(lotes);
        BalancoMassaService service = new BalancoMassaService(repo);

        IBalancoMassa resultado = service.calculaPorLote("XYZ", 5);
        assertEquals(20, resultado.getDesvio());
        assertEquals(2.0, resultado.getDesvioPerc(), 0.01);
        assertFalse(resultado.alerta());
    }
}
