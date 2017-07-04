package br.ufrgs.inf.cmp592;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Setor {

	private String nome;
	private Map <Integer,Mesa> mesas;
	private Map <Integer,Garcom> garconsResponsaveis;
	
	public Setor(){
		mesas = new HashMap<Integer,Mesa>();
		garconsResponsaveis = new HashMap<Integer,Garcom>();
	}
	
	public String getNome(){
		return nome;
	}
	
	public void setNome(String nome){
		this.nome = nome;
	}
	
	public void addGarconResponsavel(Garcom garcom){
		if(garcom!=null){
			garconsResponsaveis.put(garcom.getCodigo(), garcom);
		}
	}
	
	public void addMesa(Mesa mesa){
		if(mesa!=null){
			mesas.put(mesa.getCodigo(), mesa);
		}
	}

	public void removeGarconResponsavel(int codigoGarcom){
			garconsResponsaveis.remove(codigoGarcom);
	}
	
	public Mesa getMesa(int codigoMesa){
		return mesas.get(codigoMesa);
	}
	
	public List<Mesa> getAllMessas(){
		return new ArrayList<Mesa>(mesas.values());
	}
	
	public boolean isGarcomResponsavel(int codigoGarcom){
		return garconsResponsaveis.containsKey(codigoGarcom);
	}
	
	public boolean isMesaInSetor(int codigoMesa){
		return mesas.containsKey(codigoMesa);
	}
	
	public void imprimeDadosSetor(){
		
    	System.out.println("\nNome Setor: "+nome+"\n");
    	for (Map.Entry<Integer,Mesa> entry : mesas.entrySet()){	    	
	    	System.out.print("Mesa : "+entry.getValue().getCodigo()+"\t\t");
	    	System.out.println("Capacidade: "+entry.getValue().getCapacidade());	    	
	    	}
    	System.out.println("\nGaçons Responsáveis:");
	for (Entry<Integer, Garcom> entry : garconsResponsaveis.entrySet()){	    	
    	System.out.print("Garcom : "+entry.getValue().getCodigo()+"\t");
    	System.out.println("ID: "+entry.getValue().getID());	    	
    	}
    }
}
