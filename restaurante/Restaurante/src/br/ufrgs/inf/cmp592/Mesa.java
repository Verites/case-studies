package br.ufrgs.inf.cmp592;

public class Mesa {

	private static int counter = 0;
	private int codigo;
	private int capacidade;
	private Conta conta;
	private EstadoMesa estadoMesa;
	private Reserva reserva;
	private int ultimaConta;
	
	
	public enum EstadoMesa{
		LIVRE, OCUPADA, RESERVADA, AGUARDANDO_LIMPEZA;
	}
	
	public Mesa(){
		counter++;
		codigo = counter;
		estadoMesa = EstadoMesa.LIVRE;
		
	}
	
	public int getCodigo(){
		return codigo;
	}

	public int getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
		ultimaConta = conta.getCodigo();
	}

	public int getUltimaConta() {
		return ultimaConta;
	}
	
	public EstadoMesa getEstadoMesa() {
		return estadoMesa;
	}

	public void setEstadoMesa(EstadoMesa estadoMesa) {
		if(estadoMesa==EstadoMesa.LIVRE){
			this.conta = null;
		}
		if(estadoMesa !=EstadoMesa.RESERVADA){
			this.reserva = null;
		}
		this.estadoMesa = estadoMesa;
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		
		if(reserva !=null){
			this.estadoMesa = EstadoMesa.RESERVADA;
		}		
		this.reserva = reserva;
				
	}
	
}
