package br.ufrgs.inf.cmp592;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.ufrgs.inf.cmp592.Item.CategoriaItem;

public class Pedido {
	
	private Map<Integer,Item> itens;
	private Map<Integer,Integer> quantidadeItens;
	private EstadoPedido estadoPedido;
	private Garcom garcom;
	private int codigo;
	private static int counter = 0;
	private double custoTotal;
	private double receitaTotal;
	
	public Pedido(){
		counter++;
		codigo = counter;
		itens = new HashMap<Integer,Item>();
		quantidadeItens = new HashMap<Integer,Integer>();
		estadoPedido = EstadoPedido.VAZIO;
		custoTotal = 0;
		receitaTotal = 0;
		
	}
	
	public enum EstadoPedido{
		VAZIO, COM_PENDENCIAS, FECHADO;
	}
	
	public int getCodigo(){
		return codigo;
	}

	public EstadoPedido getEstadoPedido() {
		return estadoPedido;
	}

	public void setEstadoPedido(EstadoPedido estadoPedido) {
		this.estadoPedido = estadoPedido;
	}

	public Garcom getGarcom() {
		return garcom;
	}

	public void setGarcom(Garcom garcom) {
		this.garcom = garcom;
	}

	public List<Item> getItens() {
		return new ArrayList<Item>(itens.values());
	}
	
	public boolean addItem(Item item){	
		

		if(item!=null && item.isEstoqueSuficiente()){
			itens.put(item.getCodigo(), item);
			custoTotal += item.getCusto();
			receitaTotal +=  item.getValorDeVenda();				
			if(quantidadeItens.containsKey(item.getCodigo())){
				quantidadeItens.put(item.getCodigo(), quantidadeItens.get(item.getCodigo())+1);
			}else{
				quantidadeItens.put(item.getCodigo(), 1);
				}
			if((item.getCategoriaItem() == CategoriaItem.BEDIDA && item.getReceita().getIngredientes().isEmpty()) && this.estadoPedido != EstadoPedido.COM_PENDENCIAS) {
				this.estadoPedido = EstadoPedido.FECHADO;
			}
			else{
				this.estadoPedido = EstadoPedido.COM_PENDENCIAS;
				}
			item.removeItemDoEstoque();
			System.out.println("Item "+item.getNome()+" adicionado ao pedido.");
			return true;
			}
		else{
			//System.out.println("Item "+ item.getNome() + " não foi adicionado ao pedido, estoque insuficiente. Para adicionar o item é preciso: "+item.getReceita().getIngredientes());
			return false;
		}
	}
	
public boolean removeItem(Item item){	
				
		if(item!=null && quantidadeItens.containsKey(item.getCodigo())){
			custoTotal -= item.getCusto();
			receitaTotal -=  item.getValorDeVenda();

			if(quantidadeItens.get(item.getCodigo())>1.0){
				quantidadeItens.put(item.getCodigo(), quantidadeItens.get(item.getCodigo())-1);
			}else{
				quantidadeItens.remove(item.getCodigo());
				itens.remove(item.getCodigo());

				}
			item.insereItemNoEstoque();
			
			//atualiza o estado do pedido após a remoção do item
			if(this.quantidadeItens.isEmpty()){
				this.estadoPedido = EstadoPedido.VAZIO;
			}else{
				this.estadoPedido = EstadoPedido.FECHADO;
			}
			for (Map.Entry<Integer, Item> entry : this.itens.entrySet()){
				Item i = this.itens.get(entry.getKey());
				if(i.getCategoriaItem()!=CategoriaItem.BEDIDA || (i.getCategoriaItem()==CategoriaItem.BEDIDA && !i.getReceita().getIngredientes().isEmpty())){
					this.estadoPedido = EstadoPedido.COM_PENDENCIAS;
				}
			}
			System.out.println("Item "+ item.getNome() + " removido do pedido com sucesso.");
			return true;
			}
		else{
			if(item == null){
				System.out.println("Item é nulo, logo não pode ser removido do pedido.");
				return false;
			}else{
				System.out.println("Item "+ item.getNome() + " não existe no pedido, logo não pode ser removido.");	
			}
			return false;
		}
	}
	
	public int getQuantidadeItem(int codigoItem){
		if(quantidadeItens.get(codigoItem)==null){
			return 0;
		}
		else{
			return quantidadeItens.get(codigoItem);
		}
	}
	
	public double getValorPedido(){
		return receitaTotal;
	}
	
	public double getCustoPedido(){
		return custoTotal;
	}
	
}
