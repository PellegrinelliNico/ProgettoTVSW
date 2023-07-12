package smart_home_additional_elements;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class VascaIdromassaggioTest {
	
	VascaIdromassaggio vs;
	
	@Before
	public void inizializzaSH() {
		vs = new VascaIdromassaggio();
	}
	
	//Tradotti dagli AVALLA generati da ATGT
	@Test
	public void testBR_r_Main_FFFF() {
		assertEquals(0, vs.getLivello());
		vs.skip();
		assertEquals(0, vs.getLivello());
	}
	
	@Test
	public void testBR_r_Main_FFFTF() {
		assertEquals(0, vs.getLivello());
		vs.skip();
		vs.riempi_completamente();
		assertEquals(100, vs.getLivello());
		vs.svuota_25_percento();
		assertEquals(75, vs.getLivello());
	}
	
	@Test
	public void testBR_r_Main_FT() {
		assertEquals(0, vs.getLivello());
		vs.skip();
		vs.svuota_completamente();
		assertEquals(0, vs.getLivello());
	}

	@Test
	public void testBR_r_Main_FFTT() {
		assertEquals(0, vs.getLivello());
		vs.skip();
		vs.riempi_completamente();
		assertEquals(100, vs.getLivello());
		vs.riempi_25_percento();
		assertEquals(100, vs.getLivello());
	}
	
	@Test
	public void testBR_r_Main_FFFTT() {
		assertEquals(0, vs.getLivello());
		vs.skip();
		vs.svuota_25_percento();
		assertEquals(0, vs.getLivello());
	}
	
	@Test
	public void testBR_r_Main_FFTF() {
		assertEquals(0, vs.getLivello());
		vs.skip();
		vs.riempi_25_percento();
		assertEquals(25, vs.getLivello());
	}
	
	@Test
	public void testBR_r_Main_T() {
		assertEquals(0, vs.getLivello());
		vs.skip();
		vs.riempi_completamente();
		assertEquals(100, vs.getLivello());
	}
	
	@Test
	public void testUR_r_Main_FT() {
		assertEquals(0, vs.getLivello());
		vs.skip();
		vs.riempi_completamente();
		assertEquals(100, vs.getLivello());
		vs.svuota_completamente();
		assertEquals(0, vs.getLivello());
	}


}
