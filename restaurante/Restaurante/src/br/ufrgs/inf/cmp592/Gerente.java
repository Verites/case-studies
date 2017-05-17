package br.ufrgs.inf.cmp592;

public class Gerente extends Funcionario {

	public Gerente() {
				
		this.setSalarioFixo(Dados.getInstance().getSalarioFixo(Dados.GERENTE));
		this.setPorcentagemComissao(Dados.getInstance().getPorcentagemComissao(Dados.GERENTE));
		this.setSalarioTotalTurno(getSalarioFixo());
	}
	
	
	
}
