package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import br.ufrgs.inf.cmp592.Funcionario;
import br.ufrgs.inf.cmp592.Gerente;

public class GerenteTest {

	@Test
	public void testDefaultValues() {
		Funcionario f = new Gerente();
		assertTrue(f.getSalarioFixo() == 300);
		assertTrue(f.getSalarioTotalTurno() == f.getSalarioFixo());
		assertTrue(f.getPorcentagemComissao()==0.03);	
		assertTrue(f.getCodigo() > 0);
		assertTrue(f.getID()!=null);
		assertTrue(f.getID().contains("Gerente"));
	}
	
	@Test
	public void testGettersAndSetters() {
		Funcionario f = new Gerente();
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
		Funcionario f1 = new Gerente();
		Funcionario f2 = new Gerente();
		assertTrue(f2.getCodigo() > f1.getCodigo());
	}

}
