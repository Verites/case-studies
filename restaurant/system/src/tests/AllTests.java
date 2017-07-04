package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
	//testes unitários
	AtendenteTest.class, 
	AuxiliarDeCozinhaTest.class, 
	CozinheiroTest.class, 
	DadosTest.class,
	GarcomTest.class, 
	GerenteTest.class, 
	ItemTest.class,
	PedidoTest.class,
	ReceitaTest.class,
	ReservaTest.class,
	
	//testes de integração
	Item_Receita_Test.class,
	Pedido_Item_Test.class,
	Pedido_Garcom_Test.class,
	Conta_Pedido_Test.class,
	Conta_AuxiliarDeCozinha_Test.class,
	Mesa_Conta_Test.class
	})
public class AllTests {

		
}
