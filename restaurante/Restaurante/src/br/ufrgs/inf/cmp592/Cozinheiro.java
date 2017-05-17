package br.ufrgs.inf.cmp592;

public class Cozinheiro extends Funcionario{
	
public Cozinheiro(){
		
	this.setSalarioFixo(Dados.getInstance().getSalarioFixo(Dados.COZINHEIRO));
	this.setPorcentagemComissao(Dados.getInstance().getPorcentagemComissao(Dados.COZINHEIRO));
	this.setSalarioTotalTurno(getSalarioFixo());
}

}
