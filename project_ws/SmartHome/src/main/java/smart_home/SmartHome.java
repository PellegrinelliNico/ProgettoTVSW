package smart_home;

import java.util.HashMap;

public class SmartHome {
	protected boolean antifurto;
	protected int[] tapparelle;
	protected StatoLuce[] luci;
	protected HashMap<Porta, StatoPorta> porte;
	
	//protected StazioneMeteo sm;
	
	public SmartHome(int n_luci, int n_tapparelle) {
		//sm = new StazioneMeteo();
		
		antifurto = true;
		
		luci = new StatoLuce[n_luci];
		for (int i = 0; i < luci.length; i++)
			luci[i] = StatoLuce.SPENTA;
		
		tapparelle = new int[n_tapparelle];
		for (int i = 0; i < tapparelle.length; i++)
			tapparelle[i] = 100;
		
		porte = new HashMap<Porta, StatoPorta>();
		porte.put(Porta.PRINCIPALE, StatoPorta.CHIUSA);
		porte.put(Porta.RETRO, StatoPorta.CHIUSA);
		porte.put(Porta.GARAGE, StatoPorta.CHIUSA);
		
	}
	
	// Quando scatta l'allarme, chiudi porte e tapparelle e accendi le luci
	public void allarme() {
		if (antifurto) {
			for (int i = 0; i < luci.length; i++)
				luci[i] = StatoLuce.ACCESA;
			
			for (int i = 0; i < tapparelle.length; i++)
				tapparelle[i] = 100;
			
			porte.replace(Porta.PRINCIPALE, StatoPorta.CHIUSA);
			porte.replace(Porta.RETRO, StatoPorta.CHIUSA);
			porte.replace(Porta.GARAGE, StatoPorta.CHIUSA);
		}
	}
	
	// Quando attivo l'antifurto, la porta principale si chiude
	// e non posso disattivarlo se la porta principale è aperta
	public void toggleAntifurto() {
		if (antifurto) {
			if (porte.get(Porta.PRINCIPALE) == StatoPorta.APERTA)
				antifurto = false;
		}else {
			antifurto = true;
			porte.replace(Porta.PRINCIPALE, StatoPorta.CHIUSA);
		}
	}
	
	// Le azioni totali sulla casa aprono (o chiudono) tutte le porte e tapparelle
	// se chiudo tutto attivo anche l'antifurto
	public void chiudiTutto() {
		for (int i = 0; i < tapparelle.length; i++)
			tapparelle[i] = 100;
		
		porte.replace(Porta.PRINCIPALE, StatoPorta.CHIUSA);
		porte.replace(Porta.RETRO, StatoPorta.CHIUSA);
		porte.replace(Porta.GARAGE, StatoPorta.CHIUSA);
		
		antifurto = true;
	}
	public void apriTutto() {	
		for (int i = 0; i < tapparelle.length; i++)
			tapparelle[i] = 0;
		
		porte.replace(Porta.PRINCIPALE, StatoPorta.APERTA);
		porte.replace(Porta.RETRO, StatoPorta.APERTA);
		porte.replace(Porta.GARAGE, StatoPorta.APERTA);
	}
	
	// Le azioni sulle luci ne modificano solo una (accesa, spenta o soffusa)
	public void accendiLuce(int i) { luci[i] = StatoLuce.ACCESA; }
	public void spegniLuce(int i) { luci[i] = StatoLuce.SPENTA;	}
	public void luceSoffusa(int i) { luci[i] = StatoLuce.SOFFUSA; }
	
	// Le azioni sulle tapparelle le aprono o chiudono tutte
	// o impostano la percentuale di chiusura di una
	public void impostaTapparella(int i, int livello) {
		tapparelle[i] = livello;			
	}
	public void apriTapparelle() {
		for (int i = 0; i < tapparelle.length; i++)
			tapparelle[i] = 0;
	}
	public void chiudiTapparelle() {
		for (int i = 0; i < tapparelle.length; i++)
			tapparelle[i] = 100;
	}
	
		
	// Le azioni sulle porte ne modificano solo una (aperta o chiusa)
	// Quando apro la porta principale, se nessuna luce è accesa, 
	// tutte le tapparelle chiuse più del 50%, si aprono al 50%
	// La porta si può chiudere solo se l'antifurto è attivo
	public void apriPorta(Porta p) {
		porte.replace(p, StatoPorta.APERTA);
		
		boolean luceAccesa = false;
		for (int i = 0; i < luci.length; i++) {
			if (luci[i] == StatoLuce.ACCESA) {
				luceAccesa = true;
				break;
			}
		}
		if (p == Porta.PRINCIPALE && !luceAccesa) {
			for (int i = 0; i < tapparelle.length; i++)
				if (tapparelle[i] > 50) tapparelle[i] = 50;
		}
			
	}
	public void chiudiPorta(Porta p) {
		if (p == Porta.PRINCIPALE && !antifurto)
			return;
		porte.replace(p, StatoPorta.CHIUSA);
	}
	
}