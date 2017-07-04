package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import br.ufrgs.inf.cmp592.Atendente;
import br.ufrgs.inf.cmp592.Funcionario;

public class AtendenteTest {

	@Test
	public void testDefaultValues() {
		Funcionario f = new Atendente();
		assertTrue(f.getSalarioFixo() == 150);
		assertTrue(f.getSalarioTotalTurno() == f.getSalarioFixo());
		assertTrue(f.getPorcentagemComissao()==0.01);	
		assertTrue(f.getCodigo() > 0);
		assertTrue(f.getID()!=null);
		assertTrue(f.getID().contains("Atendente"));
	}
	
	@Test
	public void testGettersAndSetters() {
		Funcionario f = new Atendente();
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
		Funcionario f1 = new Atendente();
		Funcionario f2 = new Atendente();
		assertTrue(f2.getCodigo() > f1.getCodigo());
	}

}
