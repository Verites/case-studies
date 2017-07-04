package br.ufrgs.inf.cmp592;

public abstract class Funcionario {

	private String ID;
	private double salarioFixo;
	private double salarioVariavel;
	private double porcentagemComissao;
	private double salarioTotalTurno;
	private static int counter = 0;
	private int codigo;
	
	protected Funcionario(){
		counter++;
		codigo = counter;
		//TODO For test purposes only
		ID = this.getClass().getSimpleName()+"_"+codigo;
	}
	
	public int getCodigo(){
		return codigo;
	}
	
	public String getID() {
		return ID;
	}
	
	public double getSalarioFixo() {
		return salarioFixo;
	}
	public void setSalarioFixo(double salarioFixo) {
		this.salarioFixo = salarioFixo;
	}
	
	public double getSalarioVariavel() {
		return salarioVariavel;
	}
	public void setSalarioVariavel(double salarioVariavel) {
		this.salarioVariavel = salarioVariavel;
	}
	
	public double getPorcentagemComissao() {
		return porcentagemComissao;
	}
	public void setPorcentagemComissao(double porcentagemComissao) {
		this.porcentagemComissao = porcentagemComissao;
	}
	public double getSalarioTotalTurno() {
		return salarioTotalTurno;
	}
	public void setSalarioTotalTurno(double salarioTotalTurno) {
		this.salarioTotalTurno = salarioTotalTurno;
	}
	
}
