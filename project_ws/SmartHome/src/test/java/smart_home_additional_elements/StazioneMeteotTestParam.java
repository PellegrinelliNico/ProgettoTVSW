package smart_home_additional_elements;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

//Combinatorial testing ACoC con test parametrico
@RunWith(Parameterized.class)
public class StazioneMeteotTestParam {
	
	StazioneMeteo sm;
	double dewPointAtteso;
	double T;
	int RH;
	
	public StazioneMeteotTestParam(double T, int RH, double dewPointAtteso) {
		sm = new StazioneMeteo(
				new double[] {20,20,20,20,20,20,20},
				new int[] {50,50,50,50,50,50,50});
		this.dewPointAtteso = dewPointAtteso;
		this.T = T;
		this.RH = RH;
	}
	
	@Parameters
	public static Collection<Object[]> setParameters(){
		return Arrays.asList(new Object[][] {
			{-100, -10, Double.NaN},
			{-100, 0, Double.NaN},
			{-100, 50, Double.NaN},
			{-100, 100, Double.NaN},
			{-100, 110, Double.NaN},
			
			{-15.5, -10, Double.NaN},
			{-15.5, 0, Double.NaN},
			{-15.5, 50, -25.5},
			{-15.5, 100, -15.5},
			{-15.5, 110, Double.NaN},
			
			{0, -10, Double.NaN},
			{0, 0, Double.NaN},
			{0, 50, -10},
			{0, 100, 0},
			{0, 110, Double.NaN},
			
			{32.6, -10, Double.NaN},
			{32.6, 0, Double.NaN},
			{32.6, 50, 22.6},
			{32.6, 100, 32.6},
			{32.6, 110, Double.NaN},
			
			{100, -10, Double.NaN},
			{100, 0, Double.NaN},
			{100, 50, Double.NaN},
			{100, 100, Double.NaN},
			{100, 110, Double.NaN},
		});
	}

	@Test
	public void testDewPoint() {
		assertEquals(dewPointAtteso, sm.calcoloDewPoint(T, RH));
	}

}
