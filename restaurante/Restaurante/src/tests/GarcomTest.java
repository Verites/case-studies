package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import br.ufrgs.inf.cmp592.Funcionario;
import br.ufrgs.inf.cmp592.Garcom;

public class GarcomTest {

	@Test
	public void testDefaultValues() {
		Funcionario f = new Garcom();
		assertTrue(f.getSalarioFixo() == 200);
		assertTrue(f.getSalarioTotalTurno() == f.getSalarioFixo());
		assertTrue(f.getPorcentagemComissao()==0.07);	
		assertTrue(f.getCodigo() > 0);
		assertTrue(f.getID()!=null);
		assertTrue(f.getID().contains("Garcom"));
	}
	
	@Test
	public void testGettersAndSetters() {
		Funcionario f = new Garcom();
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
		Funcionario f1 = new Garcom();
		Funcionario f2 = new Garcom();
		assertTrue(f2.getCodigo() > f1.getCodigo());
	}

}
