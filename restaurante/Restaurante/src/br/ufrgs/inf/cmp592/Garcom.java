package br.ufrgs.inf.cmp592;

public class Garcom extends Funcionario {

	public Garcom() {
		
		this.setSalarioFixo(Dados.getInstance().getSalarioFixo(Dados.GARCOM));
		this.setPorcentagemComissao(Dados.getInstance().getPorcentagemComissao(Dados.GARCOM));
		this.setSalarioTotalTurno(getSalarioFixo());
	}
	
	
	
}
