package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.ufrgs.inf.cmp592.AuxiliarDeCozinha;
import br.ufrgs.inf.cmp592.Conta;
import br.ufrgs.inf.cmp592.Dados;
import br.ufrgs.inf.cmp592.Funcionario;

public class Conta_AuxiliarDeCozinha_Test {

	Funcionario aux1 = new AuxiliarDeCozinha();
	
	@Before
	public void setUp(){
		Dados.getInstance().zeraEstoque();	
	}
		
	@Test
	public void testAdicionarAuxiliar() {
		Conta c1 = new Conta();
		c1.setAuxiliarDeCozinha((AuxiliarDeCozinha) aux1);
		assertTrue(c1.getAuxiliarDeCozinha().getSalarioFixo()==100.0);
		assertTrue(c1.getAuxiliarDeCozinha().getPorcentagemComissao()==0.03);
	}
	
}
