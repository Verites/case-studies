package tests;




import java.text.ParseException;


import org.junit.Before;
import org.junit.Test;

import br.ufrgs.inf.cmp592.Dados;


public class Mesa_Reserva_Test {

//	Mesa m1 = new Mesa();
//	Reserva r1 = new Reserva();
//	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
	
	public void prepararReserva() throws ParseException{
//		String dataDaReservaStr = "10-05-2016 10:30:00";
//		Date dataDaReserva = dateFormat.parse(dataDaReservaStr);
//		r1.setDataReserva(dataDaReserva);
//		r1.setNomeCliente("Cliente da Reserva - 10-05-2016 10:30");
		
	}
	
	@Before
	public void setUp(){
		Dados.getInstance().zeraEstoque();	
	}
	
	@Test
	public void adicionarReservaParaMesa() throws ParseException {
		//TODO: Desenvolver os testes
//		prepararReserva();
//		m1.setReserva(r1);
//		assertTrue(m1.getReserva().getDataReserva().equals("10-05-2016 10:30:00"));
//		assertTrue(m1.getReserva().getNomeCliente().equals("Cliente da Reserva - 10-05-2016 10:30"));
		
	}

}
