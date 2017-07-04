package tests;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

import br.ufrgs.inf.cmp592.Dados;
import br.ufrgs.inf.cmp592.Garcom;
import br.ufrgs.inf.cmp592.Item;
import br.ufrgs.inf.cmp592.Pedido;
import br.ufrgs.inf.cmp592.Pedido.EstadoPedido;

public class PedidoTest {


	@Before
	public void setUp(){
		Dados.getInstance().zeraEstoque();
	}
	
	@Test
	public void testAddItem_T1(){
		
		Pedido p = new Pedido();
		
		//Adição de um item válido a um pedido, porém sem estoque suficiente;
		Item i1 = Dados.getInstance().getItemDoCardapio(15);//pega um item valido do cardapio
		//adiciona item sem haver ingredientes no estoque
		assertFalse(p.addItem(i1));
		
		//Adição de um item inválido a um pedido;
		assertFalse(p.addItem(null));
	}
	
	@Test
	public void testAddItem_T2(){
		
		Pedido p = new Pedido();
		
		//Adição de um item válido a um pedido, com estoque suficiente, e já presente no pedido
		//(item é bebida pronta e pedido não possui itens pendentes de preparo na cozinha);
		Item i1 = Dados.getInstance().getItemDoCardapio(30); //uma bebida pronta
		i1.insereItemNoEstoque();//adiciona ao estoque para poder inserir no pedido		
		assertTrue(p.addItem(i1));
		
		//adiciona ao estoque para poder inserir no pedido
		i1.insereItemNoEstoque();

		//testa a inserção no pedido pela segunda vez 
		//para satisfazer os requisitos do teste
		assertTrue(i1.isEstoqueSuficiente());
		assertTrue(p.addItem(i1));
		assertFalse(i1.isEstoqueSuficiente());	
		assertTrue(p.getEstadoPedido()==EstadoPedido.FECHADO);
		assertTrue(p.getQuantidadeItem(i1.getCodigo())==2);
		assertTrue(p.getCustoPedido()==i1.getCusto()*2);
		assertTrue(p.getValorPedido() == i1.getValorDeVenda()*2);
		assertTrue(p.getItens().contains(i1));
	}
	
	@Test
	public void testAddItem_T3(){
		
		Pedido p = new Pedido();
		
		//Adição de um item válido a um pedido, com estoque suficiente, e já presente no pedido
		//(item não é bebida pronta ou pedido possui itens pendentes de preparo na cozinha);
		Item i1 = Dados.getInstance().getItemDoCardapio(11); //uma sopa
		i1.insereItemNoEstoque();//adiciona ao estoque para poder inserir no pedido		
		assertTrue(p.addItem(i1));
		
		//adiciona ao estoque para poder inserir no pedido
		i1.insereItemNoEstoque();

		//testa a inserção no pedido pela segunda vez 
		//para satisfazer os requisitos do teste
		assertTrue(i1.isEstoqueSuficiente());
		assertTrue(p.addItem(i1));
		assertFalse(i1.isEstoqueSuficiente());	
		assertTrue(p.getEstadoPedido()==EstadoPedido.COM_PENDENCIAS);
		assertTrue(p.getQuantidadeItem(i1.getCodigo())==2);
		assertTrue(p.getCustoPedido()==i1.getCusto()*2);
		assertTrue(p.getValorPedido() == i1.getValorDeVenda()*2);
		assertTrue(p.getItens().contains(i1));
	}
	
	@Test
	public void testAddItem_T4(){
		
		Pedido p = new Pedido();
		
		//	Adição de um item válido a um pedido, com estoque suficiente, e ainda não presente no pedido
		//(item é bebida pronta e pedido não possui itens pendentes de preparo na cozinha);
		Item i1 = Dados.getInstance().getItemDoCardapio(34); //uma bebida pronta
		i1.insereItemNoEstoque();//adiciona ao estoque para poder inserir no pedido		
		assertTrue(i1.isEstoqueSuficiente());
		assertTrue(p.addItem(i1));
		assertFalse(i1.isEstoqueSuficiente());	
		assertTrue(p.getEstadoPedido()==EstadoPedido.FECHADO);
		assertTrue(p.getQuantidadeItem(i1.getCodigo())==1);
		assertTrue(p.getCustoPedido()==i1.getCusto());
		assertTrue(p.getValorPedido() == i1.getValorDeVenda());
		assertTrue(p.getItens().contains(i1));
	}
	
	@Test
	public void testAddItem_T5(){
		
		Pedido p = new Pedido();
		
		//Adição de um item válido a um pedido, com estoque suficiente, ainda não presente no pedido
		//(item não é bebida pronta ou pedido possui itens pendentes de preparo na cozinha);
		Item i1 = Dados.getInstance().getItemDoCardapio(15); //um prato principal
		i1.insereItemNoEstoque();//adiciona ao estoque para poder inserir no pedido		
		assertTrue(i1.isEstoqueSuficiente());
		assertTrue(p.addItem(i1));
		assertFalse(i1.isEstoqueSuficiente());		
		assertTrue(p.getEstadoPedido()==EstadoPedido.COM_PENDENCIAS);
		assertTrue(p.getQuantidadeItem(i1.getCodigo())==1);
		assertTrue(p.getCustoPedido()==i1.getCusto());
		assertTrue(p.getValorPedido() == i1.getValorDeVenda());
		assertTrue(p.getItens().contains(i1));
	}
	
	@Test
	public void testRemoveItem_T1(){
		
Pedido p = new Pedido();
		
		//Remoção de um item válido presente no pedido, cuja quantidade no pedido seja igual a 1 unidade,
		//e que após a remoção o pedido não fique vazio.
		//Após a remoção do item o pedido deve ter um item que necessite de preparo na cozinha
		//e dois itens que não necessitem preparo;
		Item i1 = Dados.getInstance().getItemDoCardapio(15); //um prato principal
		Item i2 = Dados.getInstance().getItemDoCardapio(22); //sobremesa
		Item i3 = Dados.getInstance().getItemDoCardapio(31); //bebida pronta
		Item i4 = Dados.getInstance().getItemDoCardapio(32); //bebida pronta
		
		//confirma que os itens não estão no estoque
		assertFalse(i1.isEstoqueSuficiente());
		assertFalse(i2.isEstoqueSuficiente());
		assertFalse(i3.isEstoqueSuficiente());
		assertFalse(i4.isEstoqueSuficiente());
		
		//adiciona itens ao estoque para poder inserir no pedido	
		i1.insereItemNoEstoque();	
		i2.insereItemNoEstoque();
		i3.insereItemNoEstoque();
		i4.insereItemNoEstoque();
		
		//confirma que os itens estão no estoque
		assertTrue(i1.isEstoqueSuficiente());
		assertTrue(i2.isEstoqueSuficiente());
		assertTrue(i3.isEstoqueSuficiente());
		assertTrue(i4.isEstoqueSuficiente());
		
		//confirma a inserção dos itens no pedido
		assertTrue(p.addItem(i1));
		assertTrue(p.addItem(i2));
		assertTrue(p.addItem(i3));
		assertTrue(p.addItem(i4));
		
		//confirma que os itens não estão mais no estoque
		assertFalse(i1.isEstoqueSuficiente());
		assertFalse(i2.isEstoqueSuficiente());
		assertFalse(i3.isEstoqueSuficiente());
		assertFalse(i4.isEstoqueSuficiente());
		
		//confirma o estado do pedido
		assertTrue(p.getEstadoPedido()==EstadoPedido.COM_PENDENCIAS);
		
		//confirma a quantidade do item 1
		assertTrue(p.getQuantidadeItem(i1.getCodigo())==1);
		
		//remove o item 1 do pedido para o teste
		assertTrue(p.removeItem(i1));
		
		//confirma que apenas o item 1 voltou para o estoque
		assertTrue(i1.isEstoqueSuficiente());
		assertFalse(i2.isEstoqueSuficiente());
		assertFalse(i3.isEstoqueSuficiente());
		assertFalse(i4.isEstoqueSuficiente());
			
		//confirma o estado do pedido
		assertTrue(p.getEstadoPedido()==EstadoPedido.COM_PENDENCIAS);	
	}
	
	
	@Test
	public void testRemoveItem_T2(){
		
		Pedido p = new Pedido();
		
		//Remoção de um item válido, porém não presente no pedido, ou;
		//Remoção de um item inválido de um pedido;

		Item i1 = Dados.getInstance().getItemDoCardapio(15);//pega um item valido do cardapio
		//adiciona item sem haver ingredientes no estoque
		assertFalse(p.removeItem(i1));
		
		//Adição de um item inválido a um pedido;
		assertFalse(p.removeItem(null));
	}
	
	@Test
	public void testRemoveItem_T3(){
		
Pedido p = new Pedido();
		
		//Remoção de um item válido presente no pedido, cuja quantidade no pedido seja igual a 1 unidade,
		//e que após a remoção o pedido não fique vazio. 
		//Após a remoção do item o pedido deve ter dois itens que necessitem de preparo na cozinha
		//e nenhum que não necessite preparo;
		Item i1 = Dados.getInstance().getItemDoCardapio(31); //uma bebida pronta
		Item i2 = Dados.getInstance().getItemDoCardapio(22); //sobremesa
		Item i3 = Dados.getInstance().getItemDoCardapio(15); //um prato principal

		
		//confirma que os itens não estão no estoque
		assertFalse(i1.isEstoqueSuficiente());
		assertFalse(i2.isEstoqueSuficiente());
		assertFalse(i3.isEstoqueSuficiente());
		
		//adiciona itens ao estoque para poder inserir no pedido	
		i1.insereItemNoEstoque();	
		i2.insereItemNoEstoque();
		i3.insereItemNoEstoque();
		
		//confirma que os itens estão no estoque
		assertTrue(i1.isEstoqueSuficiente());
		assertTrue(i2.isEstoqueSuficiente());
		assertTrue(i3.isEstoqueSuficiente());
		
		//confirma a inserção dos itens no pedido
		assertTrue(p.addItem(i1));
		assertTrue(p.addItem(i2));
		assertTrue(p.addItem(i3));
		
		//confirma que os itens não estão mais no estoque
		assertFalse(i1.isEstoqueSuficiente());
		assertFalse(i2.isEstoqueSuficiente());
		assertFalse(i3.isEstoqueSuficiente());
		
		//confirma o estado do pedido
		assertTrue(p.getEstadoPedido()==EstadoPedido.COM_PENDENCIAS);
		
		//confirma a quantidade do item 1
		assertTrue(p.getQuantidadeItem(i1.getCodigo())==1);
		
		//remove o item 1 do pedido para o teste
		assertTrue(p.removeItem(i1));
		
		//confirma que apenas o item 1 voltou para o estoque
		assertTrue(i1.isEstoqueSuficiente());
		assertFalse(i2.isEstoqueSuficiente());
		assertFalse(i3.isEstoqueSuficiente());
			
		//confirma o estado do pedido
		assertTrue(p.getEstadoPedido()==EstadoPedido.COM_PENDENCIAS);	
	}
	
	@Test
	public void testRemoveItem_T4(){
		
		Pedido p = new Pedido();
		
		//Remoção de um item válido presente no pedido, cuja quantidade no pedido seja igual a 1 unidade, 
		//e que após a remoção o pedido não fique vazio. 
		//Após a remoção do item o pedido deve ter um item que necessite de preparo na cozinha
		//e nenhum que não necessite preparo;
		Item i1 = Dados.getInstance().getItemDoCardapio(15); //um prato principal
		Item i2 = Dados.getInstance().getItemDoCardapio(22); //sobremesa

		
		//confirma que os itens não estão no estoque
		assertFalse(i1.isEstoqueSuficiente());
		assertFalse(i2.isEstoqueSuficiente());
		
		//adiciona itens ao estoque para poder inserir no pedido	
		i1.insereItemNoEstoque();	
		i2.insereItemNoEstoque();
		
		//confirma que os itens estão no estoque
		assertTrue(i1.isEstoqueSuficiente());
		assertTrue(i2.isEstoqueSuficiente());
		
		//confirma a inserção dos itens no pedido
		assertTrue(p.addItem(i1));
		assertTrue(p.addItem(i2));
		
		//confirma que os itens não estão mais no estoque
		assertFalse(i1.isEstoqueSuficiente());
		assertFalse(i2.isEstoqueSuficiente());
		
		//confirma o estado do pedido
		assertTrue(p.getEstadoPedido()==EstadoPedido.COM_PENDENCIAS);
		
		//confirma a quantidade do item 1
		assertTrue(p.getQuantidadeItem(i1.getCodigo())==1);
		
		//remove o item 1 do pedido para o teste
		assertTrue(p.removeItem(i1));
		
		//confirma que apenas o item 1 voltou para o estoque
		assertTrue(i1.isEstoqueSuficiente());
		assertFalse(i2.isEstoqueSuficiente());
			
		//confirma o estado do pedido
		assertTrue(p.getEstadoPedido()==EstadoPedido.COM_PENDENCIAS);	
	}
	
	@Test
	public void testRemoveItem_T5(){
		
		//teste original = infeasible
		//este teste possui uma sidetrip em relação ao original
		Pedido p = new Pedido();
		
		//Remoção de um item válido presente no pedido,
		//cuja quantidade no pedido seja maior que 1 unidade,
		//e que após a remoção o pedido não fique vazio.
		//Após a remoção o pedido deve conter 1 item que não necessite de preparo na cozinha.
		Item i1 = Dados.getInstance().getItemDoCardapio(32); //uma bebida pronta
		//confirma que os itens não estão no estoque
		assertFalse(i1.isEstoqueSuficiente());
		
		//adiciona itens ao estoque para poder inserir no pedido	
		i1.insereItemNoEstoque();	
		i1.insereItemNoEstoque();	
		
		//confirma que os itens estão no estoque
		assertTrue(i1.isEstoqueSuficiente());
		
		//confirma a inserção dos itens no pedido
		assertTrue(p.addItem(i1));
		assertTrue(p.addItem(i1));
		
		//confirma que os itens não estão mais no estoque
		assertFalse(i1.isEstoqueSuficiente());
		
		//confirma o estado do pedido
		assertTrue(p.getEstadoPedido()==EstadoPedido.FECHADO);
		
		//confirma a quantidade do item 1
		assertTrue(p.getQuantidadeItem(i1.getCodigo())==2);
		
		//remove o item 1 do pedido para o teste
		assertTrue(p.removeItem(i1));
		
		//confirma que apenas o item 1 voltou para o estoque
		assertTrue(i1.isEstoqueSuficiente());
		
		//confirma a quantidade do item 1
		assertTrue(p.getQuantidadeItem(i1.getCodigo())==1);
		
		//confirma o estado do pedido
		assertTrue(p.getEstadoPedido()==EstadoPedido.FECHADO);		
		
	}
	
	@Test
	public void testRemoveItem_T6(){
		
		Pedido p = new Pedido();
		
		//Remoção de um item válido presente no pedido, cuja quantidade no pedido 
		//seja igual a 1 unidade, e que após a remoção o pedido fique vazio.
		Item i1 = Dados.getInstance().getItemDoCardapio(15); //um prato principal
		//confirma que os itens não estão no estoque
		assertFalse(i1.isEstoqueSuficiente());
		
		//adiciona itens ao estoque para poder inserir no pedido	
		i1.insereItemNoEstoque();	
		
		//confirma que os itens estão no estoque
		assertTrue(i1.isEstoqueSuficiente());
		
		//confirma a inserção dos itens no pedido
		assertTrue(p.addItem(i1));
		
		//confirma que os itens não estão mais no estoque
		assertFalse(i1.isEstoqueSuficiente());
		
		//confirma o estado do pedido
		assertTrue(p.getEstadoPedido()==EstadoPedido.COM_PENDENCIAS);
		
		//confirma a quantidade do item 1
		assertTrue(p.getQuantidadeItem(i1.getCodigo())==1);
		
		//remove o item 1 do pedido para o teste
		assertTrue(p.removeItem(i1));
		
		//confirma que apenas o item 1 voltou para o estoque
		assertTrue(i1.isEstoqueSuficiente());
			
		//confirma o estado do pedido
		assertTrue(p.getEstadoPedido()==EstadoPedido.VAZIO);		
	}
	
	@Test
	public void testRemoveItem_T7(){
		
		//infeasible
	}
	
	@Test
	public void testRemoveItem_T8(){
		
		//teste original = infeasible
		//este teste possui uma sidetrip em relação ao original
		Pedido p = new Pedido();
		
		//Remoção de um item válido presente no pedido,
		//cuja quantidade no pedido seja igual a 1 unidade,
		//e que após a remoção o pedido não fique vazio.
		//Após a remoção o pedido deve conter 1 item que não necessite de preparo na cozinha.
		Item i1 = Dados.getInstance().getItemDoCardapio(15); //um prato principal
		Item i2 = Dados.getInstance().getItemDoCardapio(33); //uma bebida pronta
		
		//confirma que os itens não estão no estoque
		assertFalse(i1.isEstoqueSuficiente());
		assertFalse(i2.isEstoqueSuficiente());
		
		//adiciona itens ao estoque para poder inserir no pedido	
		i1.insereItemNoEstoque();	
		i2.insereItemNoEstoque();	
		
		//confirma que os itens estão no estoque
		assertTrue(i1.isEstoqueSuficiente());
		assertTrue(i2.isEstoqueSuficiente());
		
		//confirma a inserção dos itens no pedido
		assertTrue(p.addItem(i1));
		assertTrue(p.addItem(i2));
		
		//confirma que os itens não estão mais no estoque
		assertFalse(i1.isEstoqueSuficiente());
		assertFalse(i2.isEstoqueSuficiente());
		
		//confirma o estado do pedido
		assertTrue(p.getEstadoPedido()==EstadoPedido.COM_PENDENCIAS);
		
		//confirma a quantidade dos itens
		assertTrue(p.getQuantidadeItem(i1.getCodigo())==1);
		assertTrue(p.getQuantidadeItem(i2.getCodigo())==1);
		
		//remove o item 1 do pedido para o teste
		assertTrue(p.removeItem(i1));
		
		//confirma que apenas o item 1 voltou para o estoque
		assertTrue(i1.isEstoqueSuficiente());
		
		//confirma a quantidade dos itens
		assertTrue(p.getQuantidadeItem(i1.getCodigo())==0);
		assertTrue(p.getQuantidadeItem(i2.getCodigo())==1);
		
		//confirma o estado do pedido
		assertTrue(p.getEstadoPedido()==EstadoPedido.FECHADO);	
		
	}
	
	@Test
	public void testRemoveItem_T9(){
		
		Pedido p = new Pedido();
		
		//Remoção de um item válido presente no pedido,
		//cuja quantidade no pedido seja maior que 1 unidade,
		//e que após a remoção o pedido não fique vazio.
		//Após a remoção o pedido deve conter 1 item que necessite de preparo na cozinha.
		Item i1 = Dados.getInstance().getItemDoCardapio(15); //um prato principal
		//confirma que os itens não estão no estoque
		assertFalse(i1.isEstoqueSuficiente());
		
		//adiciona itens ao estoque para poder inserir no pedido	
		i1.insereItemNoEstoque();	
		i1.insereItemNoEstoque();	
		
		//confirma que os itens estão no estoque
		assertTrue(i1.isEstoqueSuficiente());
		
		//confirma a inserção dos itens no pedido
		assertTrue(p.addItem(i1));
		assertTrue(p.addItem(i1));
		
		//confirma que os itens não estão mais no estoque
		assertFalse(i1.isEstoqueSuficiente());
		
		//confirma o estado do pedido
		assertTrue(p.getEstadoPedido()==EstadoPedido.COM_PENDENCIAS);
		
		//confirma a quantidade do item 1
		assertTrue(p.getQuantidadeItem(i1.getCodigo())==2);
		
		//remove o item 1 do pedido para o teste
		assertTrue(p.removeItem(i1));
		
		//confirma que apenas o item 1 voltou para o estoque
		assertTrue(i1.isEstoqueSuficiente());
			
		//confirma a quantidade do item 1
		assertTrue(p.getQuantidadeItem(i1.getCodigo())==1);
		
		//confirma o estado do pedido
		assertTrue(p.getEstadoPedido()==EstadoPedido.COM_PENDENCIAS);		
	}
	
	@Test
	public void testRemoveItem_T10(){
		
		//infeasible
	}
	
	@Test
	public void testRemoveItem_T11(){
		
		//infeasible
	}
	

	@Test
	public void testDefaultValues() {
		
		Pedido p = new Pedido();
		
		assertTrue(p.getCustoPedido()==0);
		assertTrue(p.getValorPedido()==0);
		assertTrue(p.getEstadoPedido()==EstadoPedido.VAZIO);
		assertNull(p.getGarcom());
		assertTrue(p.getItens().size()==0);
		assertTrue(p.getQuantidadeItem(0)==0);
		assertTrue(p.getCodigo() > 0);
	}
	
	@Test
	public void testGettersAndSetters(){
				
		Pedido p = new Pedido();
		
		p.setEstadoPedido(EstadoPedido.FECHADO);
		assertTrue(p.getEstadoPedido()==EstadoPedido.FECHADO);

		Garcom g = new Garcom();
		p.setGarcom(g);
		assertTrue(g.equals(p.getGarcom()));		
	}
	
}
