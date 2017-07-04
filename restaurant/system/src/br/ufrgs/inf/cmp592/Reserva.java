package br.ufrgs.inf.cmp592;

import java.util.Calendar;
import java.util.Date;

public class Reserva {

	private Calendar dataReserva;
	private String nomeCliente;
	
	public Reserva(){
		dataReserva = Calendar.getInstance();
		dataReserva.setTime(new Date());
	}
	
	public Date getDataReserva() {
		return dataReserva.getTime();
	}
	public void setDataReserva(Date dataReserva) {
		this.dataReserva.setTime(dataReserva);
	}
	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	
	public boolean isReservaValida(){
			Calendar now = Calendar.getInstance();
			now.setTime(new Date());
			//monta uma data 30 minutos no passado
			now.add(Calendar.MINUTE, -30);
			//verifica se a data da reserva é anterior a 30 minutos no passado
			//se sim, a reserva está expirada e retorna false;
			//se não, retorna true
			return (!(dataReserva.compareTo(now) < 0));
	}
	
	
}
