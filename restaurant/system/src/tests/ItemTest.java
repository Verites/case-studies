package tests;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import br.ufrgs.inf.cmp592.Dados;
import br.ufrgs.inf.cmp592.Item;
import br.ufrgs.inf.cmp592.Item.CategoriaItem;
import br.ufrgs.inf.cmp592.Item.EstadoItem;
import br.ufrgs.inf.cmp592.Receita;

public class ItemTest {
	
	@Before
	public void setUP(){
		Dados.getInstance().zeraEstoque();
	}

	@Test
	public void testDefaultValues() {
		Item i = new Item();
		assertNull(i.getCategoriaItem());
		assertTrue(i.getCodigo()==0);
		assertTrue(i.getCusto()==0);
		assertTrue(i.getEstadoItem()==Item.EstadoItem.PENDENTE);
		assertNull(i.getNome());
		assertTrue(i.getReceita()!=null);
		assertTrue(i.getReceita().getIngredientes().values().size()==0);
		assertTrue(i.getTempoDePreparo()==0);
		assertTrue(i.getValorDeVenda()==0);		
	}
	
	@Test
	public void testGettersAndSetters(){
		Item i = new Item();
		
		i.setCategoriaItem(CategoriaItem.BEDIDA);		
		assertTrue(i.getCategoriaItem()==CategoriaItem.BEDIDA);
		
		i.setCodigo(99);
		assertTrue(i.getCodigo()==99);
		
		i.setCodigo(-8);
		assertTrue(i.getCodigo()==-8);
		
		i.setCusto(88.7);
		assertTrue(i.getCusto()==88.7);
		
		i.setCusto(-12.34);
		assertTrue(i.getCusto()==-12.34);
		
		i.setEstadoItem(EstadoItem.EM_PREPARACAO);
		assertTrue(i.getEstadoItem()==Item.EstadoItem.EM_PREPARACAO);
		
		i.setNome("Nome Item");
		assertTrue(i.getNome().equals("Nome Item"));
		
		i.setNome("");
		assertTrue(i.getNome().equals(""));
		
		
		i.setReceita(null);
		assertTrue(i.getReceita()!=null);
		assertTrue(i.getReceita().getIngredientes().values().size()==0);
		
		Receita r = new Receita();
		r.addIngrediente("Ingr1", 6);
		r.addIngrediente("Ingr2", 8);
		i.setReceita(r);

		assertTrue(i.getReceita()!=null);
		assertTrue(i.getReceita().getIngredientes().values().size()==2);
		assertTrue(i.getReceita().equals(r));
		
		assertTrue(i.getTempoDePreparo()==0);
		
		i.setValorDeVenda(34.56);
		assertTrue(i.getValorDeVenda()==34.56);
		
		i.setValorDeVenda(-56.73);
		assertTrue(i.getValorDeVenda()==-56.73);	
		
		i.setTempoDePreparo(7);
		assertTrue(i.getTempoDePreparo()==7);
		
		i.setTempoDePreparo(-9);
		assertTrue(i.getTempoDePreparo()==-9);
		
	}
	@Test
	public void testIsEstoqueSuficienteItemNovo(){
		Item i = new Item();
		assertFalse(i.isEstoqueSuficiente());
	}
	
	@Test
	public void testTestAddRemoveItemNovoNoEstoque(){
		
		Item i = new Item();
		i.insereItemNoEstoque();
		assertFalse(i.removeItemDoEstoque());
	}
	
	@Test
	public void testIsEstoqueSuficienteEntrada(){
		
		//uma entrada
		Item i = Dados.getInstance().getItemDoCardapio(1);
		assertFalse(i.isEstoqueSuficiente());
		for (Map.Entry<String, Double> entry : i.getReceita().getIngredientes().entrySet()){
			Dados.getInstance().addNoEstoque(entry.getKey(), 0.0);
			}
		assertFalse(i.isEstoqueSuficiente());
		for (Map.Entry<String, Double> entry : i.getReceita().getIngredientes().entrySet()){
			Dados.getInstance().addNoEstoque(entry.getKey(), entry.getValue());
			}
		assertTrue(i.isEstoqueSuficiente());
	}
	
	@Test
	public void testTestAddRemoveEntradaNoEstoque(){
		
		//uma entrada
		Item i = Dados.getInstance().getItemDoCardapio(4);
		assertFalse(i.isEstoqueSuficiente());
		assertFalse(i.removeItemDoEstoque());
		i.insereItemNoEstoque();
		assertTrue(i.removeItemDoEstoque());
		assertFalse(i.isEstoqueSuficiente());
	}
	
	@Test
	public void testIsEstoqueSuficienteSalada(){
		//uma salada
		Item i = Dados.getInstance().getItemDoCardapio(6);
		assertFalse(i.isEstoqueSuficiente());
		for (Map.Entry<String, Double> entry : i.getReceita().getIngredientes().entrySet()){
			Dados.getInstance().addNoEstoque(entry.getKey(), 0.0);
			}
		assertFalse(i.isEstoqueSuficiente());
		for (Map.Entry<String, Double> entry : i.getReceita().getIngredientes().entrySet()){
			Dados.getInstance().addNoEstoque(entry.getKey(), entry.getValue());
			}
		assertTrue(i.isEstoqueSuficiente());
	}
	
	@Test
	public void testTestAddRemoveSaladaNoEstoque(){
		
		//uma salada
		Item i = Dados.getInstance().getItemDoCardapio(7);
		assertFalse(i.isEstoqueSuficiente());
		assertFalse(i.removeItemDoEstoque());
		i.insereItemNoEstoque();
		assertTrue(i.removeItemDoEstoque());
		assertFalse(i.isEstoqueSuficiente());
	}
	
		@Test
		public void testIsEstoqueSuficienteSopa(){

		//uma sopa
		Item i = Dados.getInstance().getItemDoCardapio(10);
		assertFalse(i.isEstoqueSuficiente());
		for (Map.Entry<String, Double> entry : i.getReceita().getIngredientes().entrySet()){
			Dados.getInstance().addNoEstoque(entry.getKey(), 0.0);
			}
		assertFalse(i.isEstoqueSuficiente());
		for (Map.Entry<String, Double> entry : i.getReceita().getIngredientes().entrySet()){
			Dados.getInstance().addNoEstoque(entry.getKey(), entry.getValue());
			}
		assertTrue(i.isEstoqueSuficiente());
		}
		
		@Test
		public void testTestAddRemoveSopaNoEstoque(){
			
			//uma sopa
			Item i = Dados.getInstance().getItemDoCardapio(11);
			assertFalse(i.isEstoqueSuficiente());
			assertFalse(i.removeItemDoEstoque());
			i.insereItemNoEstoque();
			assertTrue(i.removeItemDoEstoque());
			assertFalse(i.isEstoqueSuficiente());
		}
		
		@Test
		public void testIsEstoqueSuficientePratoPrincipal(){
		//um prato principal
		Item i = Dados.getInstance().getItemDoCardapio(15);
		assertFalse(i.isEstoqueSuficiente());
		for (Map.Entry<String, Double> entry : i.getReceita().getIngredientes().entrySet()){
			Dados.getInstance().addNoEstoque(entry.getKey(), 0.0);
			}
		assertFalse(i.isEstoqueSuficiente());
		for (Map.Entry<String, Double> entry : i.getReceita().getIngredientes().entrySet()){
			Dados.getInstance().addNoEstoque(entry.getKey(), entry.getValue());
			}
		assertTrue(i.isEstoqueSuficiente());
		}
		
		@Test
		public void testTestAddRemovePratoPrincipalNoEstoque(){
			
			//um prato principal
			Item i = Dados.getInstance().getItemDoCardapio(17);
			assertFalse(i.isEstoqueSuficiente());
			assertFalse(i.removeItemDoEstoque());
			i.insereItemNoEstoque();
			assertTrue(i.removeItemDoEstoque());
			assertFalse(i.isEstoqueSuficiente());			
			i.insereItemNoEstoque();
			i.insereItemNoEstoque();
			assertTrue(i.removeItemDoEstoque());
			assertTrue(i.removeItemDoEstoque());
			assertFalse(i.removeItemDoEstoque());
		}
		
		
		@Test
		public void testIsEstoqueSuficienteSobremesa(){
		//uma sobremesa
		Item i = Dados.getInstance().getItemDoCardapio(26);
		assertFalse(i.isEstoqueSuficiente());
		for (Map.Entry<String, Double> entry : i.getReceita().getIngredientes().entrySet()){
			Dados.getInstance().addNoEstoque(entry.getKey(), 0.0);
			}
		assertFalse(i.isEstoqueSuficiente());
		for (Map.Entry<String, Double> entry : i.getReceita().getIngredientes().entrySet()){
			Dados.getInstance().addNoEstoque(entry.getKey(), entry.getValue());
			}
		assertTrue(i.isEstoqueSuficiente());
		}
		
		@Test
		public void testTestAddRemoveSobremesaNoEstoque(){
			
			//uma sobremesa
			Item i = Dados.getInstance().getItemDoCardapio(22);
			assertFalse(i.isEstoqueSuficiente());
			assertFalse(i.removeItemDoEstoque());
			i.insereItemNoEstoque();
			assertTrue(i.removeItemDoEstoque());
			assertFalse(i.isEstoqueSuficiente());
			i.insereItemNoEstoque();
			i.insereItemNoEstoque();
			assertTrue(i.removeItemDoEstoque());
			assertTrue(i.removeItemDoEstoque());
			assertFalse(i.removeItemDoEstoque());
		}
		
		@Test
		public void testIsEstoqueSuficienteBebidaPreparada(){	
		
		//uma bebida preparada
		Item i = Dados.getInstance().getItemDoCardapio(28);
		assertFalse(i.isEstoqueSuficiente());
		for (Map.Entry<String, Double> entry : i.getReceita().getIngredientes().entrySet()){
			Dados.getInstance().addNoEstoque(entry.getKey(), 0.0);
			}
		assertFalse(i.isEstoqueSuficiente());
		for (Map.Entry<String, Double> entry : i.getReceita().getIngredientes().entrySet()){
			Dados.getInstance().addNoEstoque(entry.getKey(), entry.getValue());
			}
		assertTrue(i.isEstoqueSuficiente());
		}
		
		@Test
		public void testTestAddRemoveBebidaPreparadaNoEstoque(){
			
			//uma bebida preparada
			Item i = Dados.getInstance().getItemDoCardapio(27);
			assertFalse(i.isEstoqueSuficiente());
			assertFalse(i.removeItemDoEstoque());
			i.insereItemNoEstoque();
			assertTrue(i.removeItemDoEstoque());
			assertFalse(i.isEstoqueSuficiente());
		}
		
		@Test
		public void testIsEstoqueSuficienteBebidaPronta(){
		//uma bebida pronta
		Item i = Dados.getInstance().getItemDoCardapio(31);
		assertFalse(i.isEstoqueSuficiente());
		Dados.getInstance().addNoEstoque(i.getNome(),0.0);
		assertFalse(i.isEstoqueSuficiente());
		Dados.getInstance().addNoEstoque(i.getNome(),1.0);
		assertTrue(i.isEstoqueSuficiente());
		i.insereItemNoEstoque();
		assertTrue(i.removeItemDoEstoque());
		assertTrue(i.removeItemDoEstoque());
		assertFalse(i.removeItemDoEstoque());
	}
		
		@Test
		public void testTestAddRemoveBebidaProntaNoEstoque(){
			
			//uma bebida pronta
			Item i = Dados.getInstance().getItemDoCardapio(35);
			assertFalse(i.isEstoqueSuficiente());
			assertFalse(i.removeItemDoEstoque());
			i.insereItemNoEstoque();
			assertTrue(i.removeItemDoEstoque());
			assertFalse(i.isEstoqueSuficiente());
			i.insereItemNoEstoque();
			i.insereItemNoEstoque();
			assertTrue(i.removeItemDoEstoque());
			assertTrue(i.removeItemDoEstoque());
			assertFalse(i.removeItemDoEstoque());
		}

}
