package br.ufrgs.inf.cmp592;

public class Atendente extends Funcionario{

public Atendente(){
		
	this.setSalarioFixo(Dados.getInstance().getSalarioFixo(Dados.ATENDENTE));
	this.setPorcentagemComissao(Dados.getInstance().getPorcentagemComissao(Dados.ATENDENTE));
	this.setSalarioTotalTurno(getSalarioFixo());

}
	
}
