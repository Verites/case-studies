package br.ufrgs.inf.cmp592;

import java.util.HashMap;
import java.util.Map;

public class Receita {
	
	
	private Map<String,Double> ingredientes;
	private String instrucoes;
	
	
	
	public Receita(){
		ingredientes = new HashMap<String, Double>();
	}
	
	public Map<String, Double> getIngredientes() {
		return ingredientes;
	}
	
	public void addIngrediente(String descricao, double quantidade) {

		if(ingredientes.containsKey(descricao)){
			this.ingredientes.put(descricao, this.ingredientes.get(descricao)+quantidade);
		}
		else{
			this.ingredientes.put(descricao, quantidade);
		}
	}
	public String getInstrucoes() {
		return instrucoes;
	}
	public void setInstrucoes(String instrucoes) {
		this.instrucoes = instrucoes;
	}
	
	public boolean isEstoqueSuficiente(){
		for (Map.Entry<String, Double> entry : ingredientes.entrySet()){			
		if(Dados.getInstance().consultaQuantidadeNoEstoque(entry.getKey()) < entry.getValue()){
			return false;
			}
		}
		return true;
	}
	
	public void devolveItensReceitaParaEstoque(){
		for (Map.Entry<String, Double> entry : ingredientes.entrySet()){
		Dados.getInstance().addNoEstoque(entry.getKey(), entry.getValue());
		}
	}
	
	public boolean removeItensReceitaDoEstoque(){
		if (isEstoqueSuficiente()){
			for (Map.Entry<String, Double> entry : ingredientes.entrySet()){
			Dados.getInstance().removeDoEstoque(entry.getKey(), entry.getValue());
			}
			return true;
		}else{
			return false;
		}

	}
	
	

}
