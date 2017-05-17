package br.ufrgs.inf.cmp592;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {

	setUp();

	}
	
	
	//prepara o programa;
	private static void setUp(){
		
		Gerente gerente = new Gerente();
		Atendente atendente = new Atendente();
		Cozinheiro cozinheiro = new Cozinheiro();
		AuxiliarDeCozinha auxiliar1 = new AuxiliarDeCozinha();
		AuxiliarDeCozinha auxiliar2 = new AuxiliarDeCozinha();
		AuxiliarDeCozinha auxiliar3 = new AuxiliarDeCozinha();
		Garcom garcom1 = new Garcom();
		Garcom garcom2 = new Garcom();
		Garcom garcom3 = new Garcom();
		Garcom garcom4 = new Garcom();
		Garcom garcom5 = new Garcom();
		
		Dados.getInstance().preencheEstoque();
		Dados.getInstance().addFuncionario(gerente);
		Dados.getInstance().addFuncionario(atendente);
		Dados.getInstance().addFuncionario(cozinheiro);
		Dados.getInstance().addFuncionario(auxiliar1);
		Dados.getInstance().addFuncionario(auxiliar2);
		Dados.getInstance().addFuncionario(auxiliar3);
		Dados.getInstance().addFuncionario(garcom1);
		Dados.getInstance().addFuncionario(garcom2);
		Dados.getInstance().addFuncionario(garcom3);
		Dados.getInstance().addFuncionario(garcom4);
		Dados.getInstance().addFuncionario(garcom5);
		
	//	List<Item> allItens = new ArrayList<Item>(Dados.getInstance().getCardapio().values());		
		
		Turno t = new Turno();
		t.setAtendente(atendente);
		t.setGerente(gerente);
		t.setCozinheiro(cozinheiro);
		List<Mesa> mesas = new ArrayList<Mesa>();
		List<Setor> setores = Dados.getInstance().getListaDeSetores();
		for(int i=0;i<setores.size();i++){
			mesas.addAll(setores.get(i).getAllMessas());
		}
		Mesa mesa1 = mesas.get(1);
		Conta conta1 = new Conta();
		mesa1.setConta(conta1);
		conta1.setAuxiliarDeCozinha(auxiliar1);

		Pedido pedido1 = new Pedido();
		pedido1.setGarcom(garcom1);
//		pedido1.addItem(5);
//		pedido1.addItem(10);
//		pedido1.addItem(12);
//		pedido1.addItem(12);
//		pedido1.addItem(18);
//		pedido1.addItem(18);
		for (int i=1;i<=35;i++){
			pedido1.addItem(Dados.getInstance().getItemDoCardapio(i));
			pedido1.addItem(Dados.getInstance().getItemDoCardapio(i));
			pedido1.addItem(Dados.getInstance().getItemDoCardapio(i));
			pedido1.addItem(Dados.getInstance().getItemDoCardapio(i));
			pedido1.addItem(Dados.getInstance().getItemDoCardapio(i));
			pedido1.addItem(Dados.getInstance().getItemDoCardapio(i));
		}
	//	pedido1.addItem(26);
		conta1.addPedido(pedido1);

		Pedido pedido2 = new Pedido();
		
		pedido2.setGarcom(garcom2);
		conta1.addPedido(pedido2);
		for (int i=1;i<=35;i++){
			pedido1.addItem(Dados.getInstance().getItemDoCardapio(i));
			pedido1.addItem(Dados.getInstance().getItemDoCardapio(i));
			pedido1.addItem(Dados.getInstance().getItemDoCardapio(i));
			pedido1.addItem(Dados.getInstance().getItemDoCardapio(i));
			pedido1.addItem(Dados.getInstance().getItemDoCardapio(i));
			pedido1.addItem(Dados.getInstance().getItemDoCardapio(i));
		}
		
		Pedido pedido3 = new Pedido();
		pedido3.setGarcom(garcom1);
		
		Conta conta2 = new Conta();
		conta2.addPedido(pedido3);
		conta2.setAuxiliarDeCozinha(auxiliar1);
		
		for (int i=1;i<=35;i++){
			pedido1.addItem(Dados.getInstance().getItemDoCardapio(i));
			pedido1.addItem(Dados.getInstance().getItemDoCardapio(i));
			pedido1.addItem(Dados.getInstance().getItemDoCardapio(i));
			pedido1.addItem(Dados.getInstance().getItemDoCardapio(i));
			pedido1.addItem(Dados.getInstance().getItemDoCardapio(i));
			pedido1.addItem(Dados.getInstance().getItemDoCardapio(i));
		}
		
		Conta conta3 = new Conta();
		conta3.setAuxiliarDeCozinha(auxiliar2);
		
		boolean flag = true;
		for(int i=0;i<=10;i++){
			Pedido p = new Pedido();
			conta3.addPedido(p);
			if(flag){
				p.setGarcom(garcom3);
				flag = false;
			}else{
				p.setGarcom(garcom2);
				flag = true;
			}
			for (int j=1;j<=35;j++){

				pedido1.addItem(Dados.getInstance().getItemDoCardapio(j));
				pedido1.addItem(Dados.getInstance().getItemDoCardapio(j));
				pedido1.addItem(Dados.getInstance().getItemDoCardapio(j));
				pedido1.addItem(Dados.getInstance().getItemDoCardapio(j));
			}
		}
		
		
		//conta1.imprimeConta();
		t.addConta(conta1);
		t.addConta(conta2);
		t.addConta(conta3);
		conta1.setComissaoInclusa(true);
		conta2.setComissaoInclusa(true);
		conta3.setComissaoInclusa(true);
		conta1.fechaConta();
		conta2.fechaConta();
		conta3.fechaConta();
	//	conta2.imprimeConta();
		//System.out.println(garcom1.getSalarioTotalTurno());
		System.out.println(FolhaDePagamento.calculaFolhaDePagamento(t));

		//conta1.imprimeConta();
		
		System.out.println("Receitas Turno: "+t.getReceitasDoTurno());
		System.out.println("Despesas Turno: "+t.getDespesasDoTurno());
		System.out.println("Lucro Turno: "+(t.getReceitasDoTurno()-t.getDespesasDoTurno()));
		System.out.println("Garcom1: "+garcom1.getSalarioTotalTurno());
		System.out.println("Garcom2: "+garcom2.getSalarioTotalTurno());
		System.out.println("Garcom3: "+garcom3.getSalarioTotalTurno());
		System.out.println("Auxiliar1: "+auxiliar1.getSalarioTotalTurno());
		System.out.println("Auxiliar2: "+auxiliar2.getSalarioTotalTurno());
		System.out.println("Gerente: "+gerente.getSalarioTotalTurno());
		System.out.println("Atendente: "+atendente.getSalarioTotalTurno());
		System.out.println("Cozinheiro: "+cozinheiro.getSalarioTotalTurno());

	}
	
}
