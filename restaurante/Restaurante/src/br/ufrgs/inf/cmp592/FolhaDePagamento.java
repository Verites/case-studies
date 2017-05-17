package br.ufrgs.inf.cmp592;

import java.util.List;

import br.ufrgs.inf.cmp592.Conta.EstadoConta;

public class FolhaDePagamento {

	
	//calcula a folha de pagamento para todos os funcionários;
	//retorna true se todas as contas do turno foram fechadas, e false caso contrário
	//contas nao fechadas não entram no cálculo
	public static boolean calculaFolhaDePagamento(Turno turno){
		
		//flag utilizada para verificar se toas as contas estão fechadas
		boolean allContasFechadas = true;
		
		//reseta as comissões de todos os funcionarios;
		turno.getGerente().setSalarioTotalTurno(turno.getGerente().getSalarioFixo());
		turno.getCozinheiro().setSalarioTotalTurno(turno.getCozinheiro().getSalarioFixo());
		turno.getAtendente().setSalarioTotalTurno(turno.getAtendente().getSalarioFixo());						
		
		List<Garcom> garcons = turno.getListaDeGarcons();		
		for (int i=0;i<garcons.size();i++){
			Garcom g = garcons.get(i);
			g.setSalarioTotalTurno(g.getSalarioFixo());
		}		
		
		List<AuxiliarDeCozinha> auxiliares = turno.getListaDeAuxiliaresDeCozinha();
		for (int i=0;i<auxiliares.size();i++){
			AuxiliarDeCozinha aux = auxiliares.get(i);
			aux.setSalarioTotalTurno(aux.getSalarioFixo());
		}
		//reseta os valores do turno
		turno.setDespesasDoTurno(0);
		turno.setReceitasDoTurno(0);
		
		//verificar as contas do turno
		List<Conta> contasTurno = turno.getContas();
		for (int i=0;i<contasTurno.size();i++){
			Conta conta = contasTurno.get(i);
			if(conta.getEstadoConta() == EstadoConta.FECHADA){
				double custoConta = conta.getCustoTotal();
				double receitaConta = conta.getValorTotal();
				turno.setDespesasDoTurno(turno.getDespesasDoTurno()+custoConta);
				turno.setReceitasDoTurno(turno.getReceitasDoTurno()+receitaConta);	
				if (conta.isComissaoInclusa()){
					double comissaoConta = conta.getComissao();
					double deltaSalarioAuxiliar =  conta.getAuxiliarDeCozinha().getPorcentagemComissao()* 10 * comissaoConta;
					conta.getAuxiliarDeCozinha().setSalarioTotalTurno(conta.getAuxiliarDeCozinha().getSalarioTotalTurno()+deltaSalarioAuxiliar);
					List<Pedido> pedidosConta = conta.getPedidos();			
					//Como cada conta pode ter vários pedidos de vários garçons diferente,
					//a comissão de cada garçom é calculada para cada pedido
					for (int j=0;j<pedidosConta.size();j++){
						Pedido p = pedidosConta.get(j);
						//identifica o garçom do pedido
						Garcom g = p.getGarcom();
						double valorPedido = p.getValorPedido();						
						double deltaSalarioGarcom = g.getPorcentagemComissao()*10*comissaoConta*(valorPedido/conta.getValorTotal());
						g.setSalarioTotalTurno(g.getSalarioTotalTurno()+deltaSalarioGarcom);					
						}
				}
			}
			else{
				allContasFechadas = false;
			}
		double totalReceitasTurno = turno.getReceitasDoTurno();
		double totalDespesasTurno = turno.getDespesasDoTurno();
		double lucroTurno = totalReceitasTurno - totalDespesasTurno;
		
		if (lucroTurno >0){			
			//calcula os salários dos funcionários:
			//Salário fixo mais participação nos lucros
			turno.getGerente().setSalarioTotalTurno(turno.getGerente().getSalarioFixo()+lucroTurno*turno.getGerente().getPorcentagemComissao());
			turno.getCozinheiro().setSalarioTotalTurno(turno.getCozinheiro().getSalarioFixo()+lucroTurno*turno.getCozinheiro().getPorcentagemComissao());
			turno.getAtendente().setSalarioTotalTurno(turno.getAtendente().getSalarioFixo()+lucroTurno*turno.getAtendente().getPorcentagemComissao());		
			}
		}
		return allContasFechadas;
	}
}
