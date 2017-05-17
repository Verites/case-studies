package br.ufrgs.inf.cmp592;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Conta {

	private Map<Integer,Pedido> pedidos;
	private EstadoConta estadoConta;
	private double custoTotal;
	private double valorTotal;
	private boolean comissaoInclusa;
	private Calendar dataFechamento;
	private AuxiliarDeCozinha auxiliarDeCozinha;
	private static int counter = 0;
	private int codigo;
	private double comissao;
	
	public Conta(){
		counter++;
		codigo = counter;
		pedidos = new HashMap<Integer,Pedido>();
		comissaoInclusa = false;
		estadoConta = EstadoConta.EM_ABERTO;
		valorTotal = 0;
		custoTotal = 0; //adicionado após execução do teste de integração entre conta/pedido
		dataFechamento = Calendar.getInstance();
		comissao = 0;

	}
	
	public int getCodigo(){
		return codigo;
	}
	
	public enum EstadoConta{
		EM_ABERTO, CALCULADA, PAGA_SEM_COMISSAO, PAGA_COM_COMISSAO, FECHADA;
	}

	public List<Pedido> getPedidos() {
		return new ArrayList<Pedido>(pedidos.values());
	}
	
	public Pedido getPedido(int codigoPedido) {
		return pedidos.get(codigoPedido);
	}

	public boolean addPedido(Pedido pedido) {
		//Adicionada a clausula de validação do valor do pedido ser maior que zero
		if (pedido !=null && pedido.getValorPedido()>0){
			pedidos.put(pedido.getCodigo(), pedido);
			estadoConta = EstadoConta.EM_ABERTO;
			System.out.println("Pedido: "+pedido.getCodigo()+" adicionado à conta. Custo do Pedido: "+pedido.getCustoPedido()+ " Valor do Pedido: "+pedido.getValorPedido()+ " ID do Garçom: "+pedido.getGarcom().getCodigo());
			return true;
		}
		else{
			System.out.println("O pedido "+pedido.getCodigo()+" está vazio e não pode ser adicionado à conta.");
			return false;
		}
	}

	
	public EstadoConta getEstadoConta() {
		return estadoConta;
	}

	public void setEstadoConta(EstadoConta estadoConta) {
		this.estadoConta = estadoConta;
	}

	public boolean isComissaoInclusa() {
		return comissaoInclusa;
	}

	public void setComissaoInclusa(boolean comissaoInclusa) {
		if(this.estadoConta == EstadoConta.FECHADA){
			throw new Error("Conta Fechada! Não é possível alterar comissão!");
		}
		this.comissaoInclusa = comissaoInclusa;
	}

	public Date getDataFechamento() {
		return dataFechamento.getTime();
	}

	public AuxiliarDeCozinha getAuxiliarDeCozinha() {
		return auxiliarDeCozinha;
	}

	public void setAuxiliarDeCozinha(AuxiliarDeCozinha auxiliarDeCozinha) {
		this.auxiliarDeCozinha = auxiliarDeCozinha;
	}

	public double getValorTotal() {
		return valorTotal;
	}
	
	public double getCustoTotal() {
		return custoTotal;
	}
	
	public double getComissao(){
		return comissao;
	}
	
	public void pagaConta(boolean comissaoInclusa){
		this.comissaoInclusa= comissaoInclusa;
		if(comissaoInclusa){
			this.estadoConta = EstadoConta.PAGA_COM_COMISSAO;
		}
		else{
			this.estadoConta = EstadoConta.PAGA_SEM_COMISSAO;
		}
	}
	
	public void fechaConta(){
		
		//se a conta não está fechada, calcula o valor total e sinaliza como fechada
		if (estadoConta != EstadoConta.FECHADA){
		List<Pedido> allPedidos = new ArrayList<Pedido>(pedidos.values());
			for(int i=0;i<allPedidos.size();i++){
				Pedido p = allPedidos.get(i);
				valorTotal+=p.getValorPedido();
				custoTotal+=p.getCustoPedido();
			}
		}
		//grava a data de fechamento e atualiza o status
		dataFechamento.setTime(new Date());
		estadoConta = EstadoConta.FECHADA;
		if(comissaoInclusa){
			this.comissao = valorTotal * 0.1;
		}		
	}
	
	public void imprimeConta(){
		
		//TODO verificar como fica quando há itens repetidos
		Map<Item, Integer> entradas = new HashMap<Item,Integer>();
		Map<Item, Integer> saladas = new HashMap<Item,Integer>();
		Map<Item, Integer> sopas = new HashMap<Item,Integer>();
		Map<Item, Integer> principais = new HashMap<Item,Integer>();
		Map<Item, Integer> sobremesas = new HashMap<Item,Integer>();
		Map<Item, Integer> bebidas = new HashMap<Item,Integer>();

		List<Pedido> allPedidos = new ArrayList<Pedido>(pedidos.values());
		for(int i=0;i<allPedidos.size();i++){
			Pedido p = allPedidos.get(i);
			List<Item> itens = p.getItens();
			for (int j=0;j<itens.size();j++){
				Item item = itens.get(j);
				switch(item.getCategoriaItem()){
					case BEDIDA:
						bebidas.put(item,p.getQuantidadeItem(item.getCodigo()));
						break;
					case ENTRADA:
						entradas.put(item,p.getQuantidadeItem(item.getCodigo()));
						break;
					case PRATO_PRINCIPAL:
						principais.put(item,p.getQuantidadeItem(item.getCodigo()));
						break;
					case SALADA:
						saladas.put(item,p.getQuantidadeItem(item.getCodigo()));
						break;
					case SOBREMESA:
						sobremesas.put(item,p.getQuantidadeItem(item.getCodigo()));
						break;
					case SOPA:
						sopas.put(item,p.getQuantidadeItem(item.getCodigo()));
						break;
							
				}
			}
		}	
		int count = 1;
		if (entradas.size()>0){
			System.out.println("\nENTRADAS:");
			for (Map.Entry<Item, Integer> entry : entradas.entrySet()){
				int codigo = entry.getKey().getCodigo();
				String nomeItem = entry.getKey().getNome();
				double valorItem = entry.getKey().getValorDeVenda();
				int quantidade = entry.getValue();
				
				System.out.println(count+": Codigo:"+codigo+"\tNome:"+nomeItem+"\tCusto Unitário:"+valorItem+"\tQuantidade:"+quantidade+"\tCusto Total:"+valorItem*quantidade);
				count++;
				}
		}
		count=1;
		if (saladas.size()>0){
			System.out.println("\nSALADAS:");
			for (Map.Entry<Item, Integer> entry : saladas.entrySet()){
				int codigo = entry.getKey().getCodigo();
				String nomeItem = entry.getKey().getNome();
				double valorItem = entry.getKey().getValorDeVenda();
				int quantidade = entry.getValue();
				
				System.out.println(count+": Codigo:"+codigo+"\tNome:"+nomeItem+"\tCusto Unitário:"+valorItem+"\tQuantidade:"+quantidade+"\tCusto Total:"+valorItem*quantidade);
				count++;
				}
		}
		count=1;
		if (sopas.size()>0){
			System.out.println("\nSOPAS:");
			for (Map.Entry<Item, Integer> entry : sopas.entrySet()){
				int codigo = entry.getKey().getCodigo();
				String nomeItem = entry.getKey().getNome();
				double valorItem = entry.getKey().getValorDeVenda();
				int quantidade = entry.getValue();
				
				System.out.println(count+": Codigo:"+codigo+"\tNome:"+nomeItem+"\tCusto Unitário:"+valorItem+"\tQuantidade:"+quantidade+"\tCusto Total:"+valorItem*quantidade);
				count++;
				}
		}
		count=1;
		if (principais.size()>0){
			System.out.println("\nPRATOS PRINCIPAIS:");
			for (Map.Entry<Item, Integer> entry : principais.entrySet()){
				int codigo = entry.getKey().getCodigo();
				String nomeItem = entry.getKey().getNome();
				double valorItem = entry.getKey().getValorDeVenda();
				int quantidade = entry.getValue();
				
				System.out.println(count+": Codigo:"+codigo+"\tNome:"+nomeItem+"\tCusto Unitário:"+valorItem+"\tQuantidade:"+quantidade+"\tCusto Total:"+valorItem*quantidade);
				count++;
				}
		}
		count=1;
		if (sobremesas.size()>0){
			System.out.println("\nSOBREMESAS:");
			for (Map.Entry<Item, Integer> entry : sobremesas.entrySet()){
				int codigo = entry.getKey().getCodigo();
				String nomeItem = entry.getKey().getNome();
				double valorItem = entry.getKey().getValorDeVenda();
				int quantidade = entry.getValue();
				
				System.out.println(count+": Codigo:"+codigo+"\tNome:"+nomeItem+"\tCusto Unitário:"+valorItem+"\tQuantidade:"+quantidade+"\tCusto Total:"+valorItem*quantidade);
				count++;
				}
		}
		count=1;
		if (bebidas.size()>0){
			System.out.println("\nBEBIDAS:");
			for (Map.Entry<Item, Integer> entry : bebidas.entrySet()){
				int codigo = entry.getKey().getCodigo();
				String nomeItem = entry.getKey().getNome();
				double valorItem = entry.getKey().getValorDeVenda();
				int quantidade = entry.getValue();
				
				System.out.println(count+": Codigo:"+codigo+"\tNome:"+nomeItem+"\tCusto Unitário:"+valorItem+"\tQuantidade:"+quantidade+"\tCusto Total:"+valorItem*quantidade);
				count++;
				}
			}
			System.out.println("\nConsumo: "+(valorTotal));
			System.out.println("Taxa de Serviço: "+comissao);
			System.out.println("Valor Total: "+(valorTotal+comissao));
	}
		
	
	
}//end Class
