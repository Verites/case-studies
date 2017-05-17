package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import br.ufrgs.inf.cmp592.AuxiliarDeCozinha;
import br.ufrgs.inf.cmp592.Funcionario;

public class AuxiliarDeCozinhaTest {

	@Test
	public void testDefaultValues() {
		Funcionario f = new AuxiliarDeCozinha();
		assertTrue(f.getSalarioFixo() == 100);
		assertTrue(f.getSalarioTotalTurno() == f.getSalarioFixo());
		assertTrue(f.getPorcentagemComissao()==0.03);	
		assertTrue(f.getCodigo() > 0);
		assertTrue(f.getID()!=null);
		assertTrue(f.getID().contains("AuxiliarDeCozinha"));
	}
	
	@Test
	public void testGettersAndSetters() {
		Funcionario f = new AuxiliarDeCozinha();
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
		Funcionario f1 = new AuxiliarDeCozinha();
		Funcionario f2 = new AuxiliarDeCozinha();
		assertTrue(f2.getCodigo() > f1.getCodigo());
	}

}
