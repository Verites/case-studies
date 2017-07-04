package tests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.ufrgs.inf.cmp592.Cozinheiro;
import br.ufrgs.inf.cmp592.Funcionario;

public class CozinheiroTest {

	@Test
	public void testDefaultValues() {
		Funcionario f = new Cozinheiro();
		assertTrue(f.getSalarioFixo() == 350);
		assertTrue(f.getSalarioTotalTurno() == f.getSalarioFixo());
		assertTrue(f.getPorcentagemComissao()==0.05);	
		assertTrue(f.getCodigo() > 0);
		assertTrue(f.getID()!=null);
		assertTrue(f.getID().contains("Cozinheiro"));
	}
	
	@Test
	public void testGettersAndSetters() {
		Funcionario f = new Cozinheiro();
		f.setSalarioFixo(300);
		f.setSalarioTotalTurno(500);
		f.setPorcentagemComissao(5);
		assertTrue(f.getSalarioFixo() == 300);
		assertTrue(f.getSalarioTotalTurno() == 500);
		assertTrue(f.getPorcentagemComissao()==5);	
		assertTrue(f.getCodigo() > 0);
	}

	@Test
	public void testCodigo() {
		Funcionario f1 = new Cozinheiro();
		Funcionario f2 = new Cozinheiro();
		assertTrue(f2.getCodigo() > f1.getCodigo());
	}
}
	

