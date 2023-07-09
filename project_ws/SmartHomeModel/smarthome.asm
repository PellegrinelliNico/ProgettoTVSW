asm smarthome

import StandardLibrary
import CTLlibrary
import LTLlibrary

signature:
	// DOMAINS
	//Domini rappresentanti gli elementi della smarthome
	domain Luci subsetof Integer
	domain Tapparelle subsetof Integer
	domain LivelloTapparella subsetof Integer
	//Domini rappresentanti lo stato degli elemsne
	enum domain Porte = {PRINCIPALE | RETRO | GARAGE}
	enum domain StatoPorta = {APERTA | CHIUSA}
	enum domain StatoLuce = {ACCESA | SOFFUSA | SPENTA}
	
	//Domini per le funzioni monitorate (input dal'environment)
	enum domain Elementi = {CASA | LUCI | TAPPARELLE | FINESTRE}
	enum domain AzioniCasa = {CHIUDI_TUTTO | APRI_TUTTO}
	enum domain AzioniLuci = {ACCENDI_LUCE | SPEGNI_LUCE | LUCE_SOFFUSA}
	enum domain AzioniTapparelle = {IMPOSTA_TAPPARELLA | APRI_TAPPARELLE | CHIUDI_TAPPARELLE}
	enum domain AzioniPorte = {APRI_PROTA | CHIUDI_PORTA}
	
	// FUNCTIONS
	controlled statoLuce: Luci -> StatoLuce
	controlled statoTapparella: Tapparelle -> LivelloTapparella
	controlled statoPorta: Porte -> StatoPorta

	monitored elemento: Elementi
	monitored azioneCasa: AzioniCasa
	monitored azioneLuci: AzioniLuci
	monitored azioneTapparelle: AzioniTapparelle
	monitored azionePorte: AzioniPorte
	
	monitored luce: Luci
	monitored tapparella: Tapparelle
	monitored porta: Porte
	
	monitored livello_tapparella: LivelloTapparella

	derived casaChiusa: Boolean //True se la casa ha luci spente, porte e tapparelle chiuse
	
	static n_luci: Integer
	static n_tapparelle: Integer
	static n_finestre: Integer

definitions:
	// DOMAIN DEFINITIONS
	domain Luci = {1 : n_luci}
	domain Tapparelle = {1 : n_finestre}
	domain LivelloTapparella = {0 : 100} //0% = completamente aperta; 100% = completamente chiusa

	// FUNCTION DEFINITIONS
	function n_luci = 5
	function n_finestre = 3
	
	function casaChiusa = 	(forall $l in Luci with statoLuce($l) = SPENTA) and
							(forall $t in Tapparelle with statoTapparella($t) = 100) and
							(forall $p in Porte with statoPorta($p) = CHIUSA)
	
	// RULE DEFINITIONS
	rule r_casa($azione in AzioniCasa) = skip
	
	rule r_luci($azione in AzioniLuci) = skip
	
	rule r_tapparelle($azione in AzioniTapperelle) = skip
	
	rule r_porte($azione in AzioniPorte) = skip

	// INVARIANTS

	// MAIN RULE
	main rule r_Main =
		if (elemento = CASA)
		then r_casa[azioneCasa]
		else 	if (elemento = LUCI)
				then r_luci[azioneLuci]
				else 	if (elemento = TAPPARELLE)
						then r_tapparelle[azioneTapparelle]
						else r_porte[azionePorte]
						endif
				endif
		endif
				


// INITIAL STATE
default init s0:
	function statoLuce($e in Luci) =  SPENTA
	function statoTapparella($t in Tapparelle) =  100
	function statoPorta($p in Porte) =  CHIUSA
