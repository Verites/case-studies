package tests;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import br.ufrgs.inf.cmp592.Atendente;
import br.ufrgs.inf.cmp592.AuxiliarDeCozinha;
import br.ufrgs.inf.cmp592.Cozinheiro;
import br.ufrgs.inf.cmp592.Dados;
import br.ufrgs.inf.cmp592.Funcionario;
import br.ufrgs.inf.cmp592.Garcom;
import br.ufrgs.inf.cmp592.Gerente;
import br.ufrgs.inf.cmp592.Item;
import br.ufrgs.inf.cmp592.Item.CategoriaItem;
import br.ufrgs.inf.cmp592.Receita;

public class DadosTest{

	//zera o banco de dados de ingredientes, que é uma variável estática
	@Before
	public void setUpIngredientes(){

		Dados.getInstance().zeraEstoque();		
	}
	
	@Test
	public void testConsultaQuantidadeIngredientesNoEstoque() {		
		
		Dados.getInstance().addNoEstoque(null, 5.0);
		assertTrue(Dados.getInstance().consultaQuantidadeNoEstoque(null)==0);
		
		assertTrue(Dados.getInstance().consultaQuantidadeNoEstoque("Ingr1")==0);
		assertTrue(Dados.getInstance().consultaQuantidadeNoEstoque("Ingr2")==0);
		assertTrue(Dados.getInstance().consultaQuantidadeNoEstoque("Ingr3")==0);
		assertTrue(Dados.getInstance().consultaQuantidadeNoEstoque("Ingr4")==0);
		Dados.getInstance().addNoEstoque("Ingr1", 5.0);
		assertTrue(Dados.getInstance().consultaQuantidadeNoEstoque("Ingr1")==5.0);
		Dados.getInstance().addNoEstoque("Ingr1", -1.0);
		assertTrue(Dados.getInstance().consultaQuantidadeNoEstoque("Ingr1")==5.0);
		Dados.getInstance().addNoEstoque("Ingr2", 7.0);
		assertTrue(Dados.getInstance().consultaQuantidadeNoEstoque("Ingr1")==5.0);
		assertTrue(Dados.getInstance().consultaQuantidadeNoEstoque("Ingr2")==7.0);
		Dados.getInstance().addNoEstoque("Ingr3", 10.0);
		assertTrue(Dados.getInstance().consultaQuantidadeNoEstoque("Ingr1")==5.0);
		assertTrue(Dados.getInstance().consultaQuantidadeNoEstoque("Ingr2")==7.0);
		assertTrue(Dados.getInstance().consultaQuantidadeNoEstoque("Ingr3")==10.0);
		Dados.getInstance().addNoEstoque("Ingr4", 5.0);
		assertTrue(Dados.getInstance().consultaQuantidadeNoEstoque("Ingr1")==5.0);
		assertTrue(Dados.getInstance().consultaQuantidadeNoEstoque("Ingr2")==7.0);
		assertTrue(Dados.getInstance().consultaQuantidadeNoEstoque("Ingr3")==10.0);
		assertTrue(Dados.getInstance().consultaQuantidadeNoEstoque("Ingr4")==5.0);
		Dados.getInstance().addNoEstoque("Ingr3", 5.0);
		assertTrue(Dados.getInstance().consultaQuantidadeNoEstoque("Ingr1")==5.0);
		assertTrue(Dados.getInstance().consultaQuantidadeNoEstoque("Ingr2")==7.0);
		assertTrue(Dados.getInstance().consultaQuantidadeNoEstoque("Ingr3")==15.0);
		assertTrue(Dados.getInstance().consultaQuantidadeNoEstoque("Ingr4")==5.0);

	}
	
	@Test
	public void testImpressaoBD(){
		Dados.getInstance().imprimeCardapio();
		Dados.getInstance().imprimeCargosSalarios();
		Dados.getInstance().preencheEstoque();
		Dados.getInstance().imprimeEstoque();
		Dados.getInstance().imprimeListaDeSetores();
	}
	
	@Test
	public void testAddRemoveIngredientesNoEstoque() {
		
		assertTrue(Dados.getInstance().consultaQuantidadeNoEstoque("Ingr1")==0);
		assertTrue(Dados.getInstance().consultaQuantidadeNoEstoque("Ingr2")==0);
		assertTrue(Dados.getInstance().consultaQuantidadeNoEstoque("Ingr3")==0);
		assertTrue(Dados.getInstance().consultaQuantidadeNoEstoque("Ingr4")==0);
		
		
		//removendo ingrediente que não existe no estoque
		assertFalse(Dados.getInstance().removeDoEstoque("Ingr1", 6.0));

		Dados.getInstance().addNoEstoque("Ingr1", 5.0);
		Dados.getInstance().addNoEstoque("Ingr2", 7.0);
		Dados.getInstance().addNoEstoque("Ingr3", 10.0);
		Dados.getInstance().addNoEstoque("Ingr4", 5.0);
		assertTrue(Dados.getInstance().removeDoEstoque("Ingr1", 5.0));
		assertTrue(Dados.getInstance().consultaQuantidadeNoEstoque("Ingr1")==0);
		
		assertTrue(Dados.getInstance().removeDoEstoque("Ingr2", 5.0));
		assertTrue(Dados.getInstance().consultaQuantidadeNoEstoque("Ingr2")==2.0);
		
		assertFalse(Dados.getInstance().removeDoEstoque("Ingr3", 15.0));
		assertTrue(Dados.getInstance().consultaQuantidadeNoEstoque("Ingr3")==10.0);
		
		assertFalse(Dados.getInstance().removeDoEstoque("Ingr4", -2.0));
		assertTrue(Dados.getInstance().consultaQuantidadeNoEstoque("Ingr4")==5.0);
		
		assertFalse(Dados.getInstance().removeDoEstoque(null, 3.0));
		assertTrue(Dados.getInstance().consultaQuantidadeNoEstoque("Ingr4")==5.0);
		
		assertTrue(Dados.getInstance().removeDoEstoque("Ingr4", 0.0));
		assertTrue(Dados.getInstance().consultaQuantidadeNoEstoque("Ingr4")==5.0);

	}
	
	@Test
	public void testAddRemoveFuncionarios(){
		
		assertTrue(Dados.getInstance().getListaDeFuncionariosAtivos()!=null);
		assertTrue(Dados.getInstance().getListaDeFuncionariosAtivos().isEmpty());
		Dados.getInstance().addFuncionario(null);
		Dados.getInstance().removeFuncionario(null);
		Funcionario f1 = new Garcom();
		Funcionario f2 = new Garcom();
		Funcionario f3 = new Garcom();
		Funcionario f4 = new Gerente();
		Funcionario f5 = new Atendente();
		Funcionario f6 = new AuxiliarDeCozinha();
		Funcionario f7 = new Cozinheiro();
		
		Map<Integer,Funcionario> funcionarios = Dados.getInstance().getListaDeFuncionariosAtivos();
		
		assertFalse(funcionarios.values().contains(f1));
		assertFalse(funcionarios.values().contains(f2));
		assertFalse(funcionarios.values().contains(f3));
		assertFalse(funcionarios.values().contains(f4));
		assertFalse(funcionarios.values().contains(f5));
		assertFalse(funcionarios.values().contains(f6));
		assertFalse(funcionarios.values().contains(f7));
		
	
		Dados.getInstance().addFuncionario(f1);
		assertTrue(funcionarios.values().contains(f1));
		assertFalse(funcionarios.values().contains(f2));
		assertFalse(funcionarios.values().contains(f3));
		assertFalse(funcionarios.values().contains(f4));
		assertFalse(funcionarios.values().contains(f5));
		assertFalse(funcionarios.values().contains(f6));
		assertFalse(funcionarios.values().contains(f7));
		
		Dados.getInstance().addFuncionario(f2);
		assertTrue(funcionarios.values().contains(f1));
		assertTrue(funcionarios.values().contains(f2));
		assertFalse(funcionarios.values().contains(f3));
		assertFalse(funcionarios.values().contains(f4));
		assertFalse(funcionarios.values().contains(f5));
		assertFalse(funcionarios.values().contains(f6));
		assertFalse(funcionarios.values().contains(f7));
		
		Dados.getInstance().addFuncionario(f3);
		assertTrue(funcionarios.values().contains(f1));
		assertTrue(funcionarios.values().contains(f2));
		assertTrue(funcionarios.values().contains(f3));
		assertFalse(funcionarios.values().contains(f4));
		assertFalse(funcionarios.values().contains(f5));
		assertFalse(funcionarios.values().contains(f6));
		assertFalse(funcionarios.values().contains(f7));
		
		Dados.getInstance().addFuncionario(f4);
		assertTrue(funcionarios.values().contains(f1));
		assertTrue(funcionarios.values().contains(f2));
		assertTrue(funcionarios.values().contains(f3));
		assertTrue(funcionarios.values().contains(f4));
		assertFalse(funcionarios.values().contains(f5));
		assertFalse(funcionarios.values().contains(f6));
		assertFalse(funcionarios.values().contains(f7));
		
		Dados.getInstance().addFuncionario(f5);
		assertTrue(funcionarios.values().contains(f1));
		assertTrue(funcionarios.values().contains(f2));
		assertTrue(funcionarios.values().contains(f3));
		assertTrue(funcionarios.values().contains(f4));
		assertTrue(funcionarios.values().contains(f5));
		assertFalse(funcionarios.values().contains(f6));
		assertFalse(funcionarios.values().contains(f7));
		
		Dados.getInstance().addFuncionario(f6);
		assertTrue(funcionarios.values().contains(f1));
		assertTrue(funcionarios.values().contains(f2));
		assertTrue(funcionarios.values().contains(f3));
		assertTrue(funcionarios.values().contains(f4));
		assertTrue(funcionarios.values().contains(f5));
		assertTrue(funcionarios.values().contains(f6));
		assertFalse(funcionarios.values().contains(f7));
		
		Dados.getInstance().addFuncionario(f7);
		assertTrue(funcionarios.values().contains(f1));
		assertTrue(funcionarios.values().contains(f2));
		assertTrue(funcionarios.values().contains(f3));
		assertTrue(funcionarios.values().contains(f4));
		assertTrue(funcionarios.values().contains(f5));
		assertTrue(funcionarios.values().contains(f6));
		assertTrue(funcionarios.values().contains(f7));

		
		Dados.getInstance().addFuncionario(f1);
		assertTrue(funcionarios.values().contains(f1));
		assertTrue(Dados.getInstance().removeFuncionario(f1));
		assertFalse(funcionarios.values().contains(f1));
		assertFalse(Dados.getInstance().removeFuncionario(f1));
		assertFalse(funcionarios.values().contains(f1));
		assertTrue(funcionarios.values().contains(f2));
		assertTrue(funcionarios.values().contains(f3));
		assertTrue(funcionarios.values().contains(f4));
		assertTrue(funcionarios.values().contains(f5));
		assertTrue(funcionarios.values().contains(f6));
		assertTrue(funcionarios.values().contains(f7));
		
		assertTrue(Dados.getInstance().removeFuncionario(f7));
		assertFalse(funcionarios.values().contains(f1));
		assertTrue(funcionarios.values().contains(f2));
		assertTrue(funcionarios.values().contains(f3));
		assertTrue(funcionarios.values().contains(f4));
		assertTrue(funcionarios.values().contains(f5));
		assertTrue(funcionarios.values().contains(f6));
		assertFalse(funcionarios.values().contains(f7));
		
		assertFalse(Dados.getInstance().removeFuncionario(null));
		
		assertTrue(funcionarios.values().size()==5);
		
	}
	
	@Test
	public void testGetSalarioEComissaoCargos(){
		
		assertTrue(Dados.getInstance().getSalarioFixo("Atendente") == 150);
		assertTrue(Dados.getInstance().getPorcentagemComissao("Atendente") == 0.01);
		assertTrue(Dados.getInstance().getSalarioFixo("Garçom") == 200);
		assertTrue(Dados.getInstance().getPorcentagemComissao("Garçom") == 0.07);
		assertTrue(Dados.getInstance().getSalarioFixo("Cozinheiro") == 350);
		assertTrue(Dados.getInstance().getPorcentagemComissao("Cozinheiro") == 0.05);
		assertTrue(Dados.getInstance().getSalarioFixo("Auxiliar de cozinha") == 100);
		assertTrue(Dados.getInstance().getPorcentagemComissao("Auxiliar de cozinha") == 0.03);
		assertTrue(Dados.getInstance().getSalarioFixo("Gerente") == 300);
		assertTrue(Dados.getInstance().getPorcentagemComissao("Gerente") == 0.03);
		assertTrue(Dados.getInstance().getSalarioFixo("Cargo não existente") == 0);
		assertTrue(Dados.getInstance().getPorcentagemComissao("Cargo não existente") == 0);
		
	}
	
	
	@Test
	public void testCardapio(){
		
		Map<Integer,Item> cardapio = Dados.getInstance().getCardapio();
		
		//Verifica se leu o arquivo de configuração corretamente
		assertTrue(cardapio.values().size()==35);
		
		//Entrada
		Item i1 = cardapio.get(1);
		assertTrue(Dados.getInstance().getItemDoCardapio(1).equals((i1)));
		assertTrue(i1.getCategoriaItem() == CategoriaItem.ENTRADA);
		assertTrue(i1.getCusto() == 13);
		
		//Salada
		Item i7 = cardapio.get(7);
		assertTrue(Dados.getInstance().getItemDoCardapio(7).equals((i7)));
		assertTrue(i7.getCategoriaItem() == CategoriaItem.SALADA);
		assertTrue(i7.getValorDeVenda()==17);
		
		//Sopa
		Item i10 = cardapio.get(10);
		assertTrue(Dados.getInstance().getItemDoCardapio(10).equals((i10)));
		assertTrue(i10.getCategoriaItem() == CategoriaItem.SOPA);
		assertTrue(i10.getNome().compareTo("Finlandesa") == 0);
		
		//Prato Principal
		Item i15 = cardapio.get(15);
		assertTrue(Dados.getInstance().getItemDoCardapio(15).equals((i15)));
		assertTrue(i15.getCategoriaItem() == CategoriaItem.PRATO_PRINCIPAL);
		assertTrue(i15.getEstadoItem() == Item.EstadoItem.PENDENTE);
		
		//Sobremesa
		Item i26 = cardapio.get(26);
		assertTrue(Dados.getInstance().getItemDoCardapio(26).equals((i26)));
		assertTrue(i26.getCategoriaItem() == CategoriaItem.SOBREMESA);
		Receita r = i26.getReceita();
		assertTrue(r!=null);
		assertTrue(r.getIngredientes().get("castanha") == 30);
		assertTrue(r.getIngredientes().get("chocolate amargo") == 50);
		assertTrue(r.getIngredientes().get("sorvete creme") == 100);
		
		
		//Bebida
		Item i35 = cardapio.get(35);
		assertTrue(Dados.getInstance().getItemDoCardapio(35).equals((i35)));
		assertTrue(i35.getCategoriaItem() == CategoriaItem.BEDIDA);
		assertTrue(i35.getReceita().getIngredientes().values().size()==0);
		
		
		//Item invalido
		Item invalido = cardapio.get(36);
		assertTrue(invalido==null);
		
	}
	
	

}
