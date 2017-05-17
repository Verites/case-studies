package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.ufrgs.inf.cmp592.Dados;
import br.ufrgs.inf.cmp592.Funcionario;
import br.ufrgs.inf.cmp592.Garcom;
import br.ufrgs.inf.cmp592.Pedido;

public class Pedido_Garcom_Test {

	@Before
	public void setUp(){
		Dados.getInstance().zeraEstoque();	
	}
	
	
	@Test
	public void testGarcomDoPedido() {
		Funcionario g1 = new Garcom();
		g1.setPorcentagemComissao(10.0);
		g1.setSalarioFixo(500.0);
		g1.setSalarioVariavel(15.0);
		g1.setSalarioTotalTurno(1200.0);
				
		Pedido p1 = new Pedido();
		p1.setGarcom((Garcom) g1);
		assertTrue(p1.getGarcom().getPorcentagemComissao()==10.0);
		assertTrue(p1.getGarcom().getSalarioFixo()==500.0);
		assertTrue(p1.getGarcom().getSalarioVariavel()==15.0);
		assertTrue(p1.getGarcom().getSalarioTotalTurno()==1200.0);
	}

}
