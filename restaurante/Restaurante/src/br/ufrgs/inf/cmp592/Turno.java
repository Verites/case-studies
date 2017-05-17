package br.ufrgs.inf.cmp592;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.ufrgs.inf.cmp592.Conta.EstadoConta;

public class Turno {

	private double receitasDoTurno;
	private double despesasDoTurno;
	private Map<Integer,Conta> contas;
	private Calendar dataInicioTurno;
	private Calendar dataFimTurno;
	private Map<String,Setor> listaDeSetores;
	private boolean turnoAtivo;
	private Gerente gerente;
	private Cozinheiro cozinheiro;
	private Atendente atendente;
	private Map<Integer,Garcom> listaDeGarcons;
	private Map<Integer,AuxiliarDeCozinha> listaDeAuxiliaresDeCozinha;
	
	public Turno(){
		receitasDoTurno = 0;
		despesasDoTurno = 0;
		contas = new HashMap<Integer,Conta>();
		dataInicioTurno = Calendar.getInstance();
		dataInicioTurno.setTime(new Date());
		listaDeSetores = new HashMap<String,Setor>();
		turnoAtivo = true;
		listaDeGarcons = new HashMap<Integer,Garcom>();
		listaDeAuxiliaresDeCozinha = new HashMap<Integer,AuxiliarDeCozinha>();
	}
	
	public double getReceitasDoTurno() {
		return receitasDoTurno;
	}

	public void setReceitasDoTurno(double receitasDoTurno) {
		this.receitasDoTurno = receitasDoTurno;
	}

	public double getDespesasDoTurno() {
		return despesasDoTurno;
	}

	public void setDespesasDoTurno(double despesasDoTurno) {
		this.despesasDoTurno = despesasDoTurno;
	}

	public Date getDataFimTurno() {
		return dataFimTurno.getTime();
	}

	public void setDataFimTurno(Date dataFimTurno) {
		this.dataFimTurno.setTime(dataFimTurno);
	}

	public boolean isTurnoAtivo() {
		return turnoAtivo;
	}

	public void setTurnoAtivo(boolean turnoAtivo) {
		this.turnoAtivo = turnoAtivo;
	}

	public Gerente getGerente() {
		return gerente;
	}

	public void setGerente(Gerente gerente) {
		if (gerente!=null){
			despesasDoTurno += gerente.getSalarioFixo();
		}else{
			if (this.gerente != null){
				despesasDoTurno -= this.gerente.getSalarioFixo();
			}
		}
		this.gerente = gerente;
	}

	public Cozinheiro getCozinheiro() {
		return cozinheiro;
	}

	public void setCozinheiro(Cozinheiro cozinheiro) {
		if (cozinheiro!=null){
			despesasDoTurno += cozinheiro.getSalarioFixo();
		}else{
			if (this.cozinheiro != null){
				despesasDoTurno -= this.cozinheiro.getSalarioFixo();
			}
		}
		this.cozinheiro = cozinheiro;
	}

	public Atendente getAtendente() {
		return atendente;
	}

	public void setAtendente(Atendente atendente) {
		if (atendente!=null){
			despesasDoTurno += atendente.getSalarioFixo();
		}else{
			if (this.atendente != null){
				despesasDoTurno -= this.atendente.getSalarioFixo();
			}
		}
		this.atendente = atendente;
	}

	public List<Conta> getContas() {
		return new ArrayList<Conta>(contas.values());
	}
	
	public boolean isAllContasFechadas() {
		List<Conta> allContas = new ArrayList<Conta>(contas.values());
		for(int i=1;i<allContas.size();i++){
			if(allContas.get(i).getEstadoConta()!=EstadoConta.FECHADA){
				return false;
			}
		}		
		return true;
	}
	
	public void addConta(Conta conta){
		if (conta !=null){
			contas.put(conta.getCodigo(), conta);
		}
		addAuxiliarDeCozinha(conta.getAuxiliarDeCozinha());
	}

	public Date getDataInicioTurno() {
		return dataInicioTurno.getTime();
	}

	public List<Setor> getListaDeSetores() {
		return new ArrayList<Setor>(listaDeSetores.values());
	}
	
	public void addSetor(Setor setor){
		if (setor !=null){
			listaDeSetores.put(setor.getNome(), setor);
		}
	}

	public List<Garcom> getListaDeGarcons() {
		return new ArrayList<Garcom>(listaDeGarcons.values());
	}
	
	public void addGarcom(Garcom garcom){
		if (garcom !=null){
			listaDeGarcons.put(garcom.getCodigo(), garcom);
			despesasDoTurno+=garcom.getSalarioFixo();
		}
	}

	public List<AuxiliarDeCozinha> getListaDeAuxiliaresDeCozinha() {
		return new ArrayList<AuxiliarDeCozinha>(listaDeAuxiliaresDeCozinha.values());
	}
	
	public void addAuxiliarDeCozinha(AuxiliarDeCozinha auxiliar){
		if (auxiliar !=null){
			listaDeAuxiliaresDeCozinha.put(auxiliar.getCodigo(), auxiliar);
			despesasDoTurno += auxiliar.getSalarioFixo();
		}
	}			
}
