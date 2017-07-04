package tests;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import br.ufrgs.inf.cmp592.Dados;
import br.ufrgs.inf.cmp592.Item;
import br.ufrgs.inf.cmp592.Receita;

public class Item_Receita_Test {

	@Before
	public void setUp(){
		Dados.getInstance().zeraEstoque();	
	}
	
	@Test
	public void testInstrucaoDoItem() {
		Map<Integer,Item> cardapio = Dados.getInstance().getCardapio();		
		Item i1 = cardapio.get(1);
		i1.getReceita().setInstrucoes("Nova Instrução");
		assertTrue(i1.getReceita().getInstrucoes().toString().equals("Nova Instrução"));		
	}

	@Test
	public void testAdicionarNovoIngredienteReceitaDoItemCardapio() {
		Map<Integer,Item> cardapio = Dados.getInstance().getCardapio();		
		Item i1 = cardapio.get(1);
		assertTrue(i1.getReceita().getIngredientes().toString().equals("{linguiças=2.0, pães=2.0, cebola=1.0}"));
		i1.getReceita().addIngrediente("alho", 0.5);
		assertTrue(i1.getReceita().getIngredientes().toString().equals("{linguiças=2.0, pães=2.0, cebola=1.0, alho=0.5}"));
	}

	@Test
	public void testReceitaDoItemNovo() {
		Item i1 = new Item();
		Receita r1 = new Receita();
		i1.setReceita(r1);
		assertTrue(i1.getReceita().getIngredientes().toString().equals("{}"));
		i1.getReceita().addIngrediente("alho", 0.5);
		assertTrue(i1.getReceita().getIngredientes().toString().equals("{alho=0.5}"));
	}
	
	@Test
	public void testItemEstoque() {
	
		Map<Integer,Item> cardapio = Dados.getInstance().getCardapio();
		Item i6 = cardapio.get(6);
		assertFalse(i6.getReceita().isEstoqueSuficiente());
		Dados.getInstance().addNoEstoque("tomate italiano", 1.0);
		assertFalse(i6.getReceita().isEstoqueSuficiente());
		Dados.getInstance().addNoEstoque("queijo grana padano", 30.0);
		assertFalse(i6.getReceita().isEstoqueSuficiente());
		Dados.getInstance().addNoEstoque("mozarela de búfala", 2.0);
		assertFalse(i6.getReceita().isEstoqueSuficiente());
		Dados.getInstance().addNoEstoque("molho de manjericão", 1.0);
		assertFalse(i6.getReceita().isEstoqueSuficiente());
		Dados.getInstance().addNoEstoque("molhos de folhas mix", 2.0);
		assertTrue(i6.getReceita().isEstoqueSuficiente());
	}
	
	@Test
	public void testItemRemoverDoEstoque() {
		Map<Integer,Item> cardapio = Dados.getInstance().getCardapio();
		Item i1 = cardapio.get(1);
		assertFalse(i1.getReceita().removeItensReceitaDoEstoque());
		Dados.getInstance().addNoEstoque("linguiças", 2.0);
		assertFalse(i1.getReceita().removeItensReceitaDoEstoque());
		Dados.getInstance().addNoEstoque("pães", 2.0);
		assertFalse(i1.getReceita().removeItensReceitaDoEstoque());
		Dados.getInstance().addNoEstoque("cebola", 1.0);
		assertTrue(i1.getReceita().removeItensReceitaDoEstoque());
	}
	
	@Test
	public void testItemDevolverAoEstoque() {
		Map<Integer,Item> cardapio = Dados.getInstance().getCardapio();
		Item i1 = cardapio.get(1);
		assertTrue(Dados.getInstance().consultaQuantidadeNoEstoque("linguiças")==0.0);
		assertTrue(Dados.getInstance().consultaQuantidadeNoEstoque("pães")==0.0);
		assertTrue(Dados.getInstance().consultaQuantidadeNoEstoque("cebola")==0.0);
		i1.getReceita().devolveItensReceitaParaEstoque();
		assertTrue(Dados.getInstance().consultaQuantidadeNoEstoque("linguiças")==2.0);
		assertTrue(Dados.getInstance().consultaQuantidadeNoEstoque("pães")==2.0);
		assertTrue(Dados.getInstance().consultaQuantidadeNoEstoque("cebola")==1.0);
	}
}
