package tests;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import br.ufrgs.inf.cmp592.Dados;
import br.ufrgs.inf.cmp592.Item;
import br.ufrgs.inf.cmp592.Item.CategoriaItem;
import br.ufrgs.inf.cmp592.Item.EstadoItem;
import br.ufrgs.inf.cmp592.Pedido;
import br.ufrgs.inf.cmp592.Receita;

public class Pedido_Item_Test {
	
	@Before
	public void setUp(){
		Dados.getInstance().zeraEstoque();	
	}

	@Test
	public void testNaoAdicionarItemNoPedidoEstoqueInsuficiente() {
		Map<Integer, Item> cardapio = Dados.getInstance().getCardapio();
		Item i1 = cardapio.get(1);
		Pedido p1 = new Pedido();
		assertFalse (p1.addItem(i1));
	}
	
	@Test
	public void testAdicionarItemNoPedido() {
		Map<Integer, Item> cardapio = Dados.getInstance().getCardapio();
		Item i2 = cardapio.get(2);
		Pedido p2 = new Pedido();
		i2.getReceita().devolveItensReceitaParaEstoque();
		assertTrue (p2.addItem(i2));
	}

	@Test
	public void settersAndsGetters() {
		Item i2 = new Item();
		Receita r1 = new Receita();
		i2.setCodigo(150);
		i2.setNome("Amendoas");
		i2.setCusto(10.0);
		i2.setValorDeVenda(38.0);
		i2.setTempoDePreparo(15);
		i2.setReceita(r1);
		i2.setEstadoItem(EstadoItem.PENDENTE);
		i2.setCategoriaItem(CategoriaItem.ENTRADA);
		
		
		Pedido p2 = new Pedido();
		i2.getReceita().devolveItensReceitaParaEstoque();
		p2.addItem(i2);		
		for (Item item : p2.getItens()) {
			assertTrue(item==i2);
			assertTrue (item.getCodigo()==i2.getCodigo());
			assertTrue (item.getNome().equals(i2.getNome()));
			assertTrue (item.getCusto()==i2.getCusto());
			assertTrue (item.getValorDeVenda()==i2.getValorDeVenda());
			assertTrue (item.getTempoDePreparo()==i2.getTempoDePreparo());
			assertTrue (item.getReceita() == i2.getReceita());
			assertTrue (item.getEstadoItem().equals(i2.getEstadoItem()));
			assertTrue (item.getCategoriaItem().equals(i2.getCategoriaItem()));
		}
	}
	
}
