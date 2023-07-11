package appliances;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class StazioneMeteoAllertaTest {
	
	@Test
	//Per copertura MCDC
	public void testAllerta() {
		StazioneMeteo sm = new StazioneMeteo();
		
		assertTrue(sm.allerta(-20, 3, 0, 0));
		assertTrue(sm.allerta(40, 3, 0, 0));
		assertTrue(sm.allerta(15, 7, 0, 0));
		assertTrue(sm.allerta(15, 3, 20, 60));
		assertTrue(sm.allerta(0, 3, 5, 0));
		
		assertFalse(sm.allerta(15, 3, 0, 0));
		assertFalse(sm.allerta(15, 3, 15, 0));
		assertFalse(sm.allerta(0, 3, 0, 0));
	}

}
