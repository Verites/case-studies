package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.ufrgs.inf.cmp592.AuxiliarDeCozinha;
import br.ufrgs.inf.cmp592.Conta;
import br.ufrgs.inf.cmp592.Dados;
import br.ufrgs.inf.cmp592.Funcionario;
import br.ufrgs.inf.cmp592.Mesa;

public class Mesa_Conta_Test {

	Conta c2 = new Conta();
	Funcionario aux1 = new AuxiliarDeCozinha();
	Mesa m1 = new Mesa();
	
	@Before
	public void setUp(){
		Dados.getInstance().zeraEstoque();	
	}
	

	public void prepararAuxiliar() {
		aux1.setPorcentagemComissao(0.05);
		aux1.setSalarioTotalTurno(150.0);
		aux1.setSalarioVariavel(200.0);
	}

	@Test
	public void testVincularMesaComUmaConta() {
		Conta c1 = new Conta();
		prepararAuxiliar();
		c1.setAuxiliarDeCozinha((AuxiliarDeCozinha) aux1);
		c1.setComissaoInclusa(false);
		m1.setCapacidade(5);
		m1.setConta(c1);
		assertTrue(m1.getConta().isComissaoInclusa()==false);
		assertTrue(m1.getConta().getAuxiliarDeCozinha().getPorcentagemComissao()==0.05);
		assertTrue(m1.getConta().getAuxiliarDeCozinha().getSalarioVariavel()==200.0);
		assertTrue(m1.getConta().getAuxiliarDeCozinha().getSalarioTotalTurno()==150.0);
		assertTrue(m1.getConta().getEstadoConta().toString().equals("EM_ABERTO"));
		assertTrue(m1.getConta().getCustoTotal()==0.0);
		assertTrue(m1.getConta().getValorTotal()==0.0);
		assertTrue(m1.getUltimaConta()==c1.getCodigo());
	}
	
	@Test
	public void testValidarIDDaUltimaContaDaMesa() {
		Conta c1 = new Conta();
		Conta c2 = new Conta();
		Conta c3 = new Conta();
		assertTrue(m1.getUltimaConta()==0);
		m1.setConta(c1);
		assertTrue(m1.getUltimaConta()==c1.getCodigo());
		m1.setConta(c2);
		assertTrue(m1.getUltimaConta()==c2.getCodigo());
		m1.setConta(c3);
		assertTrue(m1.getUltimaConta()==c3.getCodigo());
		m1.setConta(c1);
		assertTrue(m1.getUltimaConta()==c1.getCodigo());
	}
	
}
