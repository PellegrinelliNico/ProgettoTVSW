package smart_home_additional_elements;

//Tradotto dall'ASMETA vascaidro
public class VascaIdromassaggio {
	private int livello;
	
	public VascaIdromassaggio() {
		livello = 0;
	}
	
	public void riempi_completamente() {
		livello = 100;
	}
	
	public void svuota_completamente() {
		livello = 0;
	}
	
	public void riempi_25_percento() {
		if (livello + 25 > 100)
			livello = 100;
		else
			livello += 25;
	}
	
	public void svuota_25_percento() {
		if (livello - 25 < 0)
			livello = 0;
		else
			livello -= 25;
	}
	
	//Rappresenta la rule skip dell'asmeta, quando non tutti le monitoreb boolean sono false
	public void skip() {
		return;
	}
	
	public int getLivello() {
		return livello;
	}
}
