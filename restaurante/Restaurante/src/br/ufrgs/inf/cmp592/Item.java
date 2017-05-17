package br.ufrgs.inf.cmp592;


public class Item {
		
	private int codigo;
	private String nome;
	private double custo;
	private double valorDeVenda;
	private int tempoDePreparo;
	private Receita receita;
	private EstadoItem estadoItem;
	private CategoriaItem categoriaItem;
	
	public Item(){
		this.estadoItem = EstadoItem.PENDENTE;
	}
	
	public boolean isEstoqueSuficiente(){
		if(this.categoriaItem!=null && (this.categoriaItem!=CategoriaItem.BEDIDA || (this.categoriaItem==CategoriaItem.BEDIDA && !this.receita.getIngredientes().values().isEmpty()))){
			return getReceita().isEstoqueSuficiente();
		}else{
			return (Dados.getInstance().consultaQuantidadeNoEstoque(this.nome)>=1.0);
		}
	}
	
	public boolean removeItemDoEstoque(){
		
		if(isEstoqueSuficiente()){
			if(this.categoriaItem!=CategoriaItem.BEDIDA || (this.categoriaItem==CategoriaItem.BEDIDA && !this.receita.getIngredientes().values().isEmpty())){
				getReceita().removeItensReceitaDoEstoque();
			}else{
				Dados.getInstance().removeDoEstoque(this.getNome(), 1.0);
			}
			
			return true;
		}else{
			System.out.println("Item "+this.nome+ " não removido do estoque.");
			return false;
		}
	}
	
	public void insereItemNoEstoque(){
		
			if(this.categoriaItem!=null && (this.categoriaItem!=CategoriaItem.BEDIDA || (this.categoriaItem==CategoriaItem.BEDIDA && !this.receita.getIngredientes().values().isEmpty()))){
				this.receita.devolveItensReceitaParaEstoque();
			}else{
				Dados.getInstance().addNoEstoque(this.getNome(), 1.0);
			}

	}
	
	public void setCusto(double custo) {
		this.custo = custo;
	}

	public void setValorDeVenda(double valorDeVenda) {
		this.valorDeVenda = valorDeVenda;
	}

	public void setTempoDePreparo(int tempoDePreparo) {
		this.tempoDePreparo = tempoDePreparo;
	}

	public void setReceita(Receita receita) {
		this.receita = receita;
	}

	public void setCategoriaItem(CategoriaItem categoriaItem) {
		this.categoriaItem = categoriaItem;
	}

public enum EstadoItem{
	PENDENTE, EM_PREPARACAO, PRONTO, ENTREGUE;
}

public enum CategoriaItem{
	ENTRADA, SALADA, SOPA, PRATO_PRINCIPAL, SOBREMESA, BEDIDA;
}

public int getCodigo() {
	return codigo;
}

public void setCodigo(int codigo) {
	this.codigo= codigo;
}

public String getNome() {
		return nome;
}

public void setNome(String nome) {
	this.nome = nome;
}

public double getCusto() {
	return custo;
}

public double getValorDeVenda() {
	return valorDeVenda;
}


public int getTempoDePreparo() {
	return tempoDePreparo;
}


public Receita getReceita() {
	if (receita ==null){
		receita = new Receita();
	}
	return receita;
}


public EstadoItem getEstadoItem() {
	return estadoItem;
}

public void setEstadoItem(EstadoItem estadoItem) {
	this.estadoItem = estadoItem;
}

public CategoriaItem getCategoriaItem() {
	return categoriaItem;
}

}
