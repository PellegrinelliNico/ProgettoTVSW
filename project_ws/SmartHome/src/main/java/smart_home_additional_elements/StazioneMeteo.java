package smart_home_additional_elements;

//@ code_bigint_math
public class StazioneMeteo {
	/*@ spec_public@*/ double[] tempInterna;
	/*@ spec_public@*/ int[] umiditaInterna;
	
	//@ public invariant tempInterna != null && tempInterna.length == 7;
	//@ public invariant umiditaInterna != null && umiditaInterna.length == 7;
	
	//@ requires ti != null && ti.length == 7 && ui != null && ui.length == 7;
	/*@ requires (\forall int i; 0<=i && i<7; 
					ti[i] >= 0 && ti[i] <= 40 && ui[i] > 0 && ui[i] <= 100); @*/
	//@ ensures tempInterna == ti && umiditaInterna == ui;
	public StazioneMeteo(double[] ti, int[] ui) {
		tempInterna = ti;
		umiditaInterna = ui;
	}
	
	//@ ensures (\forall int i; 0<=i && i<7; \result >= tempInterna[i]);
	//@ ensures (\exists int i; 0<=i && i<7; \result == tempInterna[i]);
	public double getMaxTemp() {
		double max = tempInterna[0];
		/*@ loop_invariant i >= 0 && i <= 7 && 
		 				(\forall int j; 0<=j && j<i; max >= tempInterna[j]) &&
		 				(\exists int j; 0<=j && j<i; max == tempInterna[j]); @*/
		for (int i = 1; i < 7; i++) {
			if(tempInterna[i]>=max) max = tempInterna[i];
		}
		return max;
	}
	
	//@ ensures \result == (\sum int i; 0<=i && i<7; umiditaInterna[i]) / 7;
	public double getUmiditaMedia() {
		double sum = 0;
		/*@ loop_invariant i >= 0 && i <= 7 && 
			sum == (\sum int j; 0<=j && j<i; umiditaInterna[j]); @*/
		for (int i = 0; i < 7; i++) {
			sum += umiditaInterna[i];
		}
		return (double)sum/7;
	}
	
	/*@ ensures ((\result == (T - (100 - RH) / 5)) <==
			(!(T < -90 || T > 60 || RH <= 0 || RH > 100))); @*/
	/*@ ensures ((\result == Double.NaN) <== 
			(T < -90 || T > 60 || RH <= 0 || RH > 100)); @*/
	public double calcoloDewPoint(double T, int RH) {
		double Tmin = -90, Tmax = 60; //Minima (e massima) temperatura rilevata sulla Terra
		if (T < Tmin || T > Tmax || RH <= 0 || RH > 100)
			return Double.NaN;
		return T - (100 - RH) / 5;
	}
	
	//@ requires UVI >= 0 && rainRate >= 0 && wind >= 0
	/*@ ensures \result <==> 
				(T <= -10 || T >= 30 || UVI > 5 
				|| (rainRate > 10 && wind > 50) 
	   			|| (T<=0 && rainRate > 0)); @*/
	public boolean allerta(double T, int UVI, double rainRate, double wind) {
		boolean allerta = false;
		if (T <= -10 || T >= 30 || UVI > 5 || (rainRate > 10 && wind > 50) 
										   || (T<=0 && rainRate > 0))
			allerta = true;
		return allerta;
	}

}
