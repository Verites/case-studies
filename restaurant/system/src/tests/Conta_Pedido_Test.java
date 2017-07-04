package tests;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import br.ufrgs.inf.cmp592.AuxiliarDeCozinha;
import br.ufrgs.inf.cmp592.Conta;
import br.ufrgs.inf.cmp592.Conta.EstadoConta;
import br.ufrgs.inf.cmp592.Dados;
import br.ufrgs.inf.cmp592.Funcionario;
import br.ufrgs.inf.cmp592.Garcom;
import br.ufrgs.inf.cmp592.Item;
import br.ufrgs.inf.cmp592.Pedido;


public class Conta_Pedido_Test {

	Map<Integer, Item> cardapio = Dados.getInstance().getCardapio();
	Item i1;
	Item i2;
	Funcionario g1 = new Garcom();
	Funcionario aux1 = new AuxiliarDeCozinha();
	Pedido p1 = new Pedido();
	Pedido p2 = new Pedido();
	Pedido p3 = new Pedido();
	
	@Before
	public void setUp(){
		Dados.getInstance().zeraEstoque();	
	}

	public void prepararContaPedido(){
		i1 = cardapio.get(1);
		i2 = cardapio.get(2);
		i1.insereItemNoEstoque();
		i2.insereItemNoEstoque();
		p1.setGarcom((Garcom) g1);
		p1.addItem(i1);
		p2.setGarcom((Garcom) g1);
		p2.addItem(i2);
	}
	
	@Test
	public void testValidandoPedidos() {
		prepararContaPedido();
		Conta c1 = new Conta();
		c1.setComissaoInclusa(false);
		c1.setEstadoConta(EstadoConta.EM_ABERTO);
		c1.setAuxiliarDeCozinha((AuxiliarDeCozinha) aux1);
		c1.addPedido(p1);
		c1.addPedido(p2);
		
		assertTrue (c1.getPedido(p1.getCodigo()).getValorPedido()==17.0);
		assertTrue (c1.getPedido(p1.getCodigo()).getCustoPedido()==13.0);
		assertTrue (c1.getPedido(p2.getCodigo()).getValorPedido()==12.0);
		assertTrue (c1.getPedido(p2.getCodigo()).getCustoPedido()==9.0);
		assertTrue (c1.getPedido(p1.getCodigo()).getEstadoPedido().toString().equals("COM_PENDENCIAS"));
		assertTrue (c1.getPedido(p2.getCodigo()).getEstadoPedido().toString().equals("COM_PENDENCIAS"));
		assertTrue (c1.getPedido(p1.getCodigo()).getGarcom().getID()==g1.getID());
		assertTrue (c1.getPedido(p2.getCodigo()).getGarcom().getID()==g1.getID());
	}
	
	@Test
	public void testPedidoNaoAdicionadoAConta() {
		Conta c2 = new Conta();
		c2.setComissaoInclusa(false);
		c2.setEstadoConta(EstadoConta.EM_ABERTO);
		c2.setAuxiliarDeCozinha((AuxiliarDeCozinha) aux1);
		c2.addPedido(p3);		
		assertTrue (c2.getPedidos().isEmpty());
	}
	
	@Test
	public void testContaSemComissao() {
		prepararContaPedido();
		Conta c3 = new Conta();
		c3.setComissaoInclusa(false);
		c3.setEstadoConta(EstadoConta.EM_ABERTO);
		c3.setAuxiliarDeCozinha((AuxiliarDeCozinha) aux1);
		c3.addPedido(p1);
		c3.addPedido(p2);
		c3.fechaConta();
		assertTrue (c3.getValorTotal()==29.0);
		assertTrue (c3.getCustoTotal()==22.0);
		assertTrue (c3.getComissao()==0.0);
	}
	
	@Test
	public void testContaComComissao() {
		prepararContaPedido();
		Conta c4 = new Conta();
		c4.setComissaoInclusa(true);
		c4.setEstadoConta(EstadoConta.EM_ABERTO);
		c4.setAuxiliarDeCozinha((AuxiliarDeCozinha) aux1);
		c4.addPedido(p1);
		c4.addPedido(p2);
		c4.fechaConta();
		System.out.println(c4.getValorTotal()); // pq o valor total não soma a comissão?
		System.out.println(c4.getCustoTotal());
		System.out.println(c4.getComissao());
		assertTrue (c4.getValorTotal()==29.0);
		assertTrue (c4.getCustoTotal()==22.0);
		assertTrue (c4.getComissao()== c4.getValorTotal()*0.1); // acho que seria melhor arredondar para duas casas decimais...
	}
	

}
