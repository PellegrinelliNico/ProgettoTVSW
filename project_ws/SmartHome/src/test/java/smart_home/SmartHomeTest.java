package smart_home;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SmartHomeTest {
	int n_luci, n_tapparelle;
	SmartHome sm;
	
	@Before
	public void inizializzaSH() {
		n_luci = 5;
		n_tapparelle = 3;
		sm = new SmartHome(n_luci, n_tapparelle);
	}
	
	@Test
	// Tradotto dallo scenario avalla apertura_totale
	public void testScenario() {		
		//Stato iniziale
		for (int i = 0; i < n_luci; i++)
			assertEquals(StatoLuce.SPENTA, sm.luci[i]);
		
		for (int i = 0; i < n_tapparelle; i++)
			assertEquals(100, sm.tapparelle[i]);
		
		assertEquals(StatoPorta.CHIUSA, sm.porte.get(Porta.PRINCIPALE));
		assertEquals(StatoPorta.CHIUSA, sm.porte.get(Porta.RETRO));
		assertEquals(StatoPorta.CHIUSA, sm.porte.get(Porta.GARAGE));
		
		assertTrue(sm.antifurto);
		
		//Apro tutte le tapparelle e le porte
		sm.apriTutto();
		
		for (int i = 0; i < n_luci; i++)
			assertEquals(StatoLuce.SPENTA, sm.luci[i]);
		
		for (int i = 0; i < n_tapparelle; i++)
			assertEquals(0, sm.tapparelle[i]);
		
		assertEquals(StatoPorta.APERTA, sm.porte.get(Porta.PRINCIPALE));
		assertEquals(StatoPorta.APERTA, sm.porte.get(Porta.RETRO));
		assertEquals(StatoPorta.APERTA, sm.porte.get(Porta.GARAGE));
		
		assertTrue(sm.antifurto);
		
		//Accendo la prima luce
		sm.accendiLuce(0);
		
		assertEquals(StatoLuce.ACCESA, sm.luci[0]);
		
		for (int i = 1; i < n_luci; i++)
			assertEquals(StatoLuce.SPENTA, sm.luci[i]);
		
		for (int i = 0; i < n_tapparelle; i++)
			assertEquals(0, sm.tapparelle[i]);
		
		assertEquals(StatoPorta.APERTA, sm.porte.get(Porta.PRINCIPALE));
		assertEquals(StatoPorta.APERTA, sm.porte.get(Porta.RETRO));
		assertEquals(StatoPorta.APERTA, sm.porte.get(Porta.GARAGE));
		
		assertTrue(sm.antifurto);
	}
	
	//Test volti alla copertura del 100% degli statement
	@Test
	public void testAllarme() {		
		sm.allarme();
		
		for (int i = 0; i < n_luci; i++)
			assertEquals(StatoLuce.ACCESA, sm.luci[i]);
		
		for (int i = 0; i < n_tapparelle; i++)
			assertEquals(100, sm.tapparelle[i]);
		
		assertEquals(StatoPorta.CHIUSA, sm.porte.get(Porta.PRINCIPALE));
		assertEquals(StatoPorta.CHIUSA, sm.porte.get(Porta.RETRO));
		assertEquals(StatoPorta.CHIUSA, sm.porte.get(Porta.GARAGE));
	}
	@Test
	public void testAntifurto() {		
		sm.toggleAntifurto();
		
		assertTrue(sm.antifurto);
		assertEquals(StatoPorta.CHIUSA, sm.porte.get(Porta.PRINCIPALE));
		
		sm.apriPorta(Porta.PRINCIPALE);
		sm.toggleAntifurto();
		assertFalse(sm.antifurto);
		assertEquals(StatoPorta.APERTA, sm.porte.get(Porta.PRINCIPALE));
		sm.toggleAntifurto();
		assertTrue(sm.antifurto);
	}
	@Test
	public void testChiuidiTutto() {		
		sm.apriTutto();
		sm.chiudiTutto();
		
		for (int i = 0; i < n_tapparelle; i++)
			assertEquals(100, sm.tapparelle[i]);
		
		assertEquals(StatoPorta.CHIUSA, sm.porte.get(Porta.PRINCIPALE));
		assertEquals(StatoPorta.CHIUSA, sm.porte.get(Porta.RETRO));
		assertEquals(StatoPorta.CHIUSA, sm.porte.get(Porta.GARAGE));
		
		assertTrue(sm.antifurto);
	}
	@Test
	public void testLuci() {		
		sm.luceSoffusa(1);
		assertEquals(StatoLuce.SOFFUSA, sm.luci[1]);
		sm.spegniLuce(1);
		assertEquals(StatoLuce.SPENTA, sm.luci[1]);
	}
	@Test
	public void testTapparelle(){
		sm.apriTapparelle();
		for (int i = 0; i < n_tapparelle; i++)
			assertEquals(0, sm.tapparelle[i]);
		sm.chiudiTapparelle();
		for (int i = 0; i < n_tapparelle; i++)
			assertEquals(100, sm.tapparelle[i]);
		sm.impostaTapparella(1, 75);
		assertEquals(75, sm.tapparelle[1]);
	}
	@Test
	public void testPorte(){
		sm.accendiLuce(1);
		sm.apriPorta(Porta.PRINCIPALE);
		assertEquals(StatoPorta.APERTA, sm.porte.get(Porta.PRINCIPALE));
		for (int i = 0; i < n_tapparelle; i++)
			assertEquals(100, sm.tapparelle[i]);
		
		sm.chiudiPorta(Porta.PRINCIPALE);
		assertEquals(StatoPorta.CHIUSA, sm.porte.get(Porta.PRINCIPALE));
		
		sm.spegniLuce(1);
		sm.apriPorta(Porta.PRINCIPALE);
		assertEquals(StatoPorta.APERTA, sm.porte.get(Porta.PRINCIPALE));
		for (int i = 0; i < n_tapparelle; i++)
			assertEquals(50, sm.tapparelle[i]);
		
		sm.toggleAntifurto();
		sm.chiudiPorta(Porta.PRINCIPALE);
		assertEquals(StatoPorta.APERTA, sm.porte.get(Porta.PRINCIPALE));
	}
	
	//Test volti alla copertura del 100% dei branch
	@Test
	public void testAllarmeNoAntifurto(){
		sm.apriPorta(Porta.PRINCIPALE);
		sm.toggleAntifurto();
		sm.allarme();
		
		for (int i = 0; i < n_luci; i++)
			assertEquals(StatoLuce.SPENTA, sm.luci[i]);
		
		assertEquals(StatoPorta.APERTA, sm.porte.get(Porta.PRINCIPALE));
	}
	
	@Test
	public void testApriPortaNoTapparelle(){
		sm.apriTapparelle();
		sm.apriPorta(Porta.PRINCIPALE);
		assertEquals(StatoPorta.APERTA, sm.porte.get(Porta.PRINCIPALE));
		for (int i = 0; i < n_tapparelle; i++)
			assertEquals(0, sm.tapparelle[i]);
	}
}
