package br.ufrgs.inf.cmp592;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import br.ufrgs.inf.cmp592.Item.CategoriaItem;

public class Util {

	public static Map<Integer,Item> readItemsFromFile(File f){
			
	Map<Integer,Item> itens = new HashMap<Integer,Item>();
	try (BufferedReader br = new BufferedReader(new FileReader(f))) {
	    String line;
	    Item item;
	    int codigo;
	    String nome;
	    double custo;
	    double valorDeVenda;
	    int tempoDePreparo;
	    Receita receita;
	    CategoriaItem ultimaCategoria = null;

	    while ((line = br.readLine()) != null) {
	    	if (line.startsWith("#")){
	    		CategoriaItem catAtual = getCategoriaItem(line);
	    		if(catAtual!=null){
		    		ultimaCategoria = catAtual;	    				
		    	}
	    	}else{
	    		if (!line.startsWith(" ") && !line.isEmpty()){		
	    			item = new Item();
			    	if(ultimaCategoria!=null ){
			    		//se não é bebida
			    		String[] s1 = line.split(Pattern.quote("."));
				    	codigo = Integer.parseInt(s1[0]);
				    	String[] s2 = s1[1].trim().split(" - ");				    	
				    	nome = s2[0];
				    	custo = Double.parseDouble(s2[1].replace(",", "."));
				    	valorDeVenda = Double.parseDouble(s2[2].replace(",", "."));
				    	receita = new Receita();
				    	try{
					    	tempoDePreparo = Integer.parseInt(s2[3]);
					    	String[] ingr = s2[4].split(Pattern.quote(","));					    	
					    	for (int i=0;i<ingr.length;i++){
					    		
					    		ingr[i] = ingr[i].trim();
					    		String qtdd;
					    		String descr;
					    		String[] lista = ingr[i].split(Pattern.quote(" "),2);
					    		qtdd = lista[0].replaceAll("g","").replaceAll("ml","").replace(",", ".");
					    		descr = lista[1].trim();
					    		if (descr.startsWith("de ")){
					    			descr = descr.replace("de ", "");
					    		}
					    		receita.addIngrediente(descr,Double.parseDouble(qtdd));
					    	}
				    	}
				    	catch (Exception e){
				    		tempoDePreparo = 0;
				    	}
				    	item.setCategoriaItem(ultimaCategoria);
				    	item.setCodigo(codigo);
				    	item.setNome(nome);
				    	item.setCusto(custo);
				    	item.setValorDeVenda(valorDeVenda);
				    	item.setTempoDePreparo(tempoDePreparo);
				    	item.setReceita(receita);
				    	itens.put(item.getCodigo(),item);
			    	}
//			    	else{
//			    		if (ultimaCategoria!=null){
//				    		//se é bebida
//				    		String[] s1 = line.split(Pattern.quote("."));
//					    	codigo = Integer.parseInt(s1[0]);
//					    	String[] s2 = s1[1].trim().split(" - ");				    	
//					    	nome = s2[0];
//					    	custo = Double.parseDouble(s2[1].replace(",", "."));
//					    	valorDeVenda = Double.parseDouble(s2[2].replace(",", "."));				  
//					    	item.setCategoriaItem(ultimaCategoria);
//					    	item.setCodigo(codigo);
//					    	item.setNome(nome);
//					    	item.setCusto(custo);
//					    	item.setValorDeVenda(valorDeVenda);
//					    	item.setReceita(new Receita());
//					    	itens.put(item.getCodigo(),item);
//				    		}
//			    	}
	    		}
	    	}

	    	}	
	       
	    }
	catch(Exception e){
		e.printStackTrace();
		return itens;
	}
	return itens;
	}

	
	private static CategoriaItem getCategoriaItem(String estado){
		
		switch (estado.trim()){
		case "#Entradas": return CategoriaItem.ENTRADA;
		case "#Saladas": return CategoriaItem.SALADA;
		case "#Sopas": return CategoriaItem.SOPA;
		case "#Pratos Principais": return CategoriaItem.PRATO_PRINCIPAL;
		case "#Sobremesas": return CategoriaItem.SOBREMESA;
		case "#Bebidas": return CategoriaItem.BEDIDA;
		default:return null;
		}				
	}
	
	public static List<Setor> readSetoresFromFile(File f){
		
		ArrayList<Setor> setores = new ArrayList<Setor>();
		try (BufferedReader br = new BufferedReader(new FileReader(f))) {
		    String line;
		    Setor setor = null;
		    int configMesa = 0;

		    while ((line = br.readLine()) != null) {
		    	//só avalia linhas que não comecem com #
		    	if (!line.startsWith("#")){
		    		try{
		    			//tenta converter o valor da linha para número
		    			configMesa = Integer.parseInt(line);
		    			//se conseguir antes de haver nome no setor, significa que é o número de mesas; ignorar
		    			if(setor !=null){
		    				Mesa m = new Mesa();
				    		m.setCapacidade(configMesa);
				    		setor.addMesa(m);
		    				}
			    		}
			    		catch (Exception e){			    			
			    			//se não conseguir fazer o parse, é porque é o nome do próximo setor
				    		setor = new Setor();
						    setor.setNome(line);
						    setores.add(setor);
			    		}
		    	}
		    }
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return setores;
		}
	
	
	public static Map<String,Double[]> readCargosSalariosFromFile(File f){
		
		
		Map<String,Double[]> cargosSalarios = new HashMap<String,Double[]>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(f))) {
		    String cargo;
		    double salarioFixo;
		    double salarioVariavel;
		    String line;
		    
		    while ((line = br.readLine()) != null) {
		    	if (!line.startsWith("#")){
				    String[] s1 = line.split(Pattern.quote(","));
					    	cargo = s1[0];				    	
					    	salarioFixo = Double.parseDouble(s1[1].trim());
					    	salarioVariavel = Double.parseDouble(s1[2].trim().replace("%", ""));
					    	cargosSalarios.put(cargo, new Double[]{salarioFixo,salarioVariavel/100});
		    		}	
		    	}
			}
		catch(Exception e){
			e.printStackTrace();
		}	

		return cargosSalarios;
	}		
}
