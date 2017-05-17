package br.ufrgs.inf.cmp592;

import java.io.File;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import br.ufrgs.inf.cmp592.Item.CategoriaItem;

public final class Dados {
	

	private Map<Integer,Item> cardapio ;
	private Map<String,Double> estoque;
	private Map<String,Double[]> cargosSalarios;
	private List<Setor> listaDeSetores;
	private Map<Integer,Funcionario> listaFuncionarios;
	
	//caminhos para os arquivos de configuração
	private String path =".\\resources\\";
	private String fileName1 = "MapaDeAssentos_Restaurante1.txt";
	private String filename2 = "Salarios.txt";
	private String filename3 = "Cardapio.txt";
	
	//Constantes utilizadas nos arquivos de configuração
	public static final String ATENDENTE = "Atendente";
	public static final String GERENTE = "Gerente";
	public static final String GARCOM = "Garçom";
	public static final String AUX_COZINHA = "Auxiliar de cozinha";
	public static final String COZINHEIRO = "Cozinheiro";
	
	
	//inicializa as variáveis
	private Dados(){
		listaDeSetores = Util.readSetoresFromFile(new File(path+fileName1));
		cargosSalarios = Util.readCargosSalariosFromFile(new File(path+filename2));
		cardapio = Util.readItemsFromFile(new File(path+filename3));
		estoque = new HashMap<String,Double>();
		listaFuncionarios = new HashMap<Integer,Funcionario>();
	}
	

	private static Dados instance = null;
	
	public static Dados getInstance(){
		if(instance ==null){
			instance = new Dados();
		}
		return instance;
	}
	
	//for test purposes only
	public static Dados getInstance(boolean reloadData){
		if(reloadData || instance == null){
			instance = new Dados();
		}
		return instance;
	}
	
	public void zeraEstoque(){
		estoque = new HashMap<String,Double>();
	}
	
	public Map <Integer,Item> getCardapio() {
		return cardapio;
	}
	
	public double getSalarioFixo(String cargo){
		Double[] d = cargosSalarios.get(cargo);
		if(d!=null){
			return d[0];
		}else{
			return 0;
		}
	}
	
	public double getPorcentagemComissao(String cargo){
		Double[] d = cargosSalarios.get(cargo);
		if(d!=null){
			return d[1];
		}else{
			return 0;
		}
	}
	
	public Item getItemDoCardapio(int codigoItem){
			return cardapio.get(codigoItem);
	}
	
	
	public void addNoEstoque(String ingrediente, Double quantidade) {
		if(ingrediente==null || quantidade<=0){
			System.out.println("Item não adicionado no estoque: "+ ingrediente+ " Quantidade adicionada: "+ quantidade + " Total no estoque: "+Dados.getInstance().consultaQuantidadeNoEstoque(ingrediente));
			return;
		} 
		if (estoque.containsKey(ingrediente)){
			estoque.put(ingrediente,estoque.get(ingrediente)+quantidade);
			System.out.println("Item adicionado no estoque: "+ ingrediente+ " Quantidade adicionada: "+ quantidade + " Total no estoque: "+Dados.getInstance().consultaQuantidadeNoEstoque(ingrediente));
		}
		else{
			estoque.put(ingrediente,quantidade);
			System.out.println("Item adicionado no estoque: "+ ingrediente+ " Quantidade adicionada: "+ quantidade + " Total no estoque: "+Dados.getInstance().consultaQuantidadeNoEstoque(ingrediente));
		}
		}
	
	public boolean removeDoEstoque(String ingrediente, Double quantidade) {
		//alterado após o teste
		if(quantidade < 0 || ingrediente == null){
			return false;
		}
		if (estoque.containsKey(ingrediente)){
			double qtdd = estoque.get(ingrediente);
			if(qtdd >= quantidade){
			estoque.put(ingrediente,qtdd-quantidade);
			System.out.println("Item removido do estoque: "+ ingrediente+ " Quantidade removida: "+ quantidade + " Total no estoque: "+Dados.getInstance().consultaQuantidadeNoEstoque(ingrediente));
			return true;
			}
		}
		return false;
		}
	
	public double consultaQuantidadeNoEstoque(String ingrediente) {
		if (estoque.containsKey(ingrediente)){
			return estoque.get(ingrediente);
		}else{	
			return 0;
		}	
	}
	
	
	//imprime os itens do estoque em ordem alfabetica
	public void imprimeEstoque(){
		
		SortedSet<String> keys = new TreeSet<String>(estoque.keySet());
		for (String key : keys) { 
		   System.out.print(key+"\t\t\t\t");
		   System.out.println(truncateDecimal(estoque.get(key),2));
		}
	}
	
	public void imprimeCardapio(){
		
		CategoriaItem categoria = null;
    	for (Map.Entry<Integer,Item> entry : cardapio.entrySet()){	    	
	    	CategoriaItem catItem = entry.getValue().getCategoriaItem();
	    	if (catItem!=categoria){
	    		System.out.println(catItem);
	    		categoria = catItem;
	    	}
	    	System.out.print("\nCodigo: "+entry.getKey()+"\n");
	    	System.out.print("Nome Prato: "+entry.getValue().getNome()+"\n");
	    	System.out.print("Custo: "+entry.getValue().getCusto()+"\t");
	    	System.out.print("Preço: "+entry.getValue().getValorDeVenda()+"\t");
	    	System.out.print("Tempo de Preparo: "+entry.getValue().getTempoDePreparo()+"\t");
	    	System.out.print("\nIngredientes:\n");
	    	Map<String, Double> ing = entry.getValue().getReceita().getIngredientes();
	    	for (Map.Entry<String, Double> entry2 : ing.entrySet()){
	    	    System.out.println(truncateDecimal(entry2.getValue(),2)+"\t"+entry2.getKey());
	    	}
	    }
	}
	
	public void imprimeCargosSalarios(){
		
    	for (Map.Entry<String, Double[]> entry : cargosSalarios.entrySet()){	    	
	    	System.out.println("\nCargo:"+ entry.getKey());
	    	Double[] d = entry.getValue();
	    	if (d!=null){
	    	System.out.println("Salario Fixo: "+truncateDecimal(d[0],2));
	    	System.out.println("Salario Variavel: "+truncateDecimal(d[1],2));
	    	}
	    }
	}
	
	public void imprimeListaDeSetores(){
		for (int i=0;i<getListaDeSetores().size();i++){
			getListaDeSetores().get(i).imprimeDadosSetor();
		}
	}
	
	private double truncateDecimal(double x,int numberofDecimals){
	    if ( x > 0) {
	        return new BigDecimal(String.valueOf(x)).setScale(numberofDecimals, BigDecimal.ROUND_FLOOR).doubleValue();
	    } else {//não aplicável, pois salários e quantidades serão sempre positivos.
	        return new BigDecimal(String.valueOf(x)).setScale(numberofDecimals, BigDecimal.ROUND_CEILING).doubleValue();
	    }
	}	
	
	//preenche o estoque com os ingredientes dos itens do cardapio
	public void preencheEstoque(){
		
		for (Map.Entry<Integer,Item> entry : cardapio.entrySet()){	
	    	Map<String, Double> ing = entry.getValue().getReceita().getIngredientes();
	    	for (Map.Entry<String, Double> entry2 : ing.entrySet()){
	    		addNoEstoque(entry2.getKey(), entry2.getValue()+100.0);
	    	}
	    }
	}
	
	public void addFuncionario(Funcionario f){
		if (f!=null){
			listaFuncionarios.put(f.getCodigo(), f);
		}
	}
	
	public boolean removeFuncionario(Funcionario f){
		if (f!=null){
			if (listaFuncionarios.containsKey(f.getCodigo())){
				listaFuncionarios.remove(f.getCodigo());
				return true;
			}
		}
		return false;
	}
	
	public Map<Integer,Funcionario> getListaDeFuncionariosAtivos(){
		return listaFuncionarios;
		
	}
	
	public List<Setor> getListaDeSetores(){
		return listaDeSetores;
	}
	
}
