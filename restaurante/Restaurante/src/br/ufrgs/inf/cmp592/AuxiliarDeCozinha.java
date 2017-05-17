package br.ufrgs.inf.cmp592;

public class AuxiliarDeCozinha extends Funcionario {

	public AuxiliarDeCozinha(){
		
		this.setSalarioFixo(Dados.getInstance().getSalarioFixo(Dados.AUX_COZINHA));
		this.setPorcentagemComissao(Dados.getInstance().getPorcentagemComissao(Dados.AUX_COZINHA));
		this.setSalarioTotalTurno(getSalarioFixo());
}
	
}
