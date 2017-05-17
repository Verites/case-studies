package tests;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import br.ufrgs.inf.cmp592.Reserva;

public class ReservaTest {

	@Test
	public void testGettersandSetters() {
		Reserva r1 = new Reserva();
		r1.setNomeCliente("Marcelo da Silva");
		Calendar dataReserva = Calendar.getInstance();
		dataReserva.setTime(new Date());//now
		r1.setDataReserva(dataReserva.getTime());
		assertTrue("Marcelo da Silva".equals(r1.getNomeCliente()));
		assertTrue(dataReserva.getTime().equals(r1.getDataReserva()));
		try {
			Thread.sleep(25);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail(e.getMessage());
		}
		assertTrue(new Date().after(r1.getDataReserva()));
	}
	
	@Test
	public void testIsReservaValida(){
		Reserva r1 = new Reserva();
		assertTrue(r1.isReservaValida());
		Calendar dataReserva = Calendar.getInstance();
		dataReserva.setTime(new Date());//now
		r1.setDataReserva(dataReserva.getTime());
		assertTrue(r1.isReservaValida());
		dataReserva.add(Calendar.MINUTE, -29);//29 minutos atrás
		r1.setDataReserva(dataReserva.getTime());
		assertTrue(r1.isReservaValida());		
		dataReserva.add(Calendar.MINUTE, -31);//31 minutos atrás
		r1.setDataReserva(dataReserva.getTime());
		assertFalse(r1.isReservaValida());
	}

}
