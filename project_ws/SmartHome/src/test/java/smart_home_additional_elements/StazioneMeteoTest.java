package smart_home_additional_elements;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class StazioneMeteoTest {
	
	@Test
	//Per copertura MCDC di allerta
	public void testAllerta() {
		StazioneMeteo sm = new StazioneMeteo(
				new double[] {20,20,20,20,20,20,20},
				new int[] {50,50,50,50,50,50,50});
		assertTrue(sm.allerta(-20, 3, 0, 0));
		assertTrue(sm.allerta(40, 3, 0, 0));
		assertTrue(sm.allerta(15, 7, 0, 0));
		assertTrue(sm.allerta(15, 3, 20, 60));
		assertTrue(sm.allerta(0, 3, 5, 0));
		
		assertFalse(sm.allerta(15, 3, 0, 0));
		assertFalse(sm.allerta(15, 3, 15, 0));
		assertFalse(sm.allerta(0, 3, 0, 0));
	}
	
	//Test per la copertura di statement, branch e term dell'intera classe
	@Test
	public void testMaxTemp() {
		StazioneMeteo sm = new StazioneMeteo(
									new double[] {18,21,17.5,22.3,21,20,20.1},
									new int[] {50,50,50,50,50,50,50});
		
		assertEquals(22.3, sm.getMaxTemp());
	}
	
	@Test
	public void testUmiditaMedia() {
		StazioneMeteo sm = new StazioneMeteo(
									new double[] {20,20,20,20,20,20,20},
									new int[] {14,21,51,72,66,34,12});
		
		assertEquals(38.57, sm.getUmiditaMedia(), 0.01);
	}
}
