package appliances;

//@ code_bigint_math
public class StazioneMeteo {
	
	/*@ ensures ((\result == (T - (100 - RH) / 5)) <==> 
			(!(T < -90 || T > 60 || RH <= 0 || RH > 100))); @*/
	/*@ ensures ((\result == -Double.NaN) <==> 
			(T < -90 || T > 60 || RH <= 0 || RH > 100)); @*/
	public double calcoloDewPoint(double T, int RH) {
		double Tmin = -90, Tmax = 60; //Minima (e massima) temperatura rilevata sulla Terra
		if (T < Tmin || T > Tmax || RH <= 0 || RH > 100)
			return Double.NaN;
		return T - (100 - RH) / 5;
	}
	
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
