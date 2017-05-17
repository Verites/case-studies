package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.ufrgs.inf.cmp592.Dados;
import br.ufrgs.inf.cmp592.Receita;


public class ReceitaTest {

	@Before
	public void setUp(){
		Dados.getInstance().zeraEstoque();
	}
	
	@Test
	public void testDefaultValues() {
		Receita r = new Receita();
		assertFalse(r.getIngredientes() == null);
		assertTrue(r.getIngredientes().isEmpty());
		assertNull(r.getInstrucoes());
		r.setInstrucoes("Uma instrução");
		assertTrue("Uma instrução".equals(r.getInstrucoes()));
		
	}
	
	@Test
	public void testAddIngrediente_T1T2(){
		
		Receita r = new Receita();
		assertNull(r.getIngredientes().get("ingrediente x"));
		r.addIngrediente("ingrediente x", 5); //ingrediente nao existe na receita
		assertTrue(r.getIngredientes().get("ingrediente x")==5);
		r.addIngrediente("ingrediente x", 7);//ingrediente agora já existe na receita
		assertTrue(r.getIngredientes().get("ingrediente x")==12);
	}
	
	@Test
	public void testIsEstoqueSuficiente_T1(){
		
		Receita r = new Receita();
		Dados.getInstance().addNoEstoque("Ingrediente x", 1.0);
		r.addIngrediente("Ingrediente x", 1.0);
		assertTrue(r.isEstoqueSuficiente());
	}
	
	@Test
	public void testIsEstoqueSuficiente_T2(){
		
		Receita r = new Receita();
		Dados.getInstance().addNoEstoque("Ingrediente x", 1.0);
		r.addIngrediente("Ingrediente x", 1.0);
		r.addIngrediente("Ingrediente y", 1.0);
		assertFalse(r.isEstoqueSuficiente());
	}
	
	@Test
	public void testdevolveItensReceitaParaEstoque_T1(){
		
		Receita r = new Receita();
		Dados.getInstance().addNoEstoque("Ingrediente y", 1.0);
		r.addIngrediente("Ingrediente x", 1.0);
		r.addIngrediente("Ingrediente y", 1.0);
		r.addIngrediente("Ingrediente z", 1.0);

		r.devolveItensReceitaParaEstoque();
		assertTrue(Dados.getInstance().consultaQuantidadeNoEstoque("Ingrediente x")==1);
		assertTrue(Dados.getInstance().consultaQuantidadeNoEstoque("Ingrediente y")==2);
		assertTrue(Dados.getInstance().consultaQuantidadeNoEstoque("Ingrediente z")==1);
	}
	
	@Test
	public void testRemoveItensReceitaDoEstoque_T1(){
		Receita r = new Receita();
		Dados.getInstance().addNoEstoque("Ingrediente x", 2.0);
		Dados.getInstance().addNoEstoque("Ingrediente y", 3.0);
		r.addIngrediente("Ingrediente x", 1.0);
		r.addIngrediente("Ingrediente y", 3.0);
		assertTrue(r.removeItensReceitaDoEstoque());
	}
	
	@Test
	public void testRemoveItensReceitaDoEstoque_T2(){
		
		Receita r = new Receita();
		r.addIngrediente("Ingrediente qualquer não existente no estoque", 1.0);
		assertFalse(r.removeItensReceitaDoEstoque());
	}
	
	@Test
	public void testRemoveItensReceitaDoEstoque_T3(){
		
		Receita r = new Receita();
		assertTrue(r.removeItensReceitaDoEstoque());
	}
		
}
