/**
 * 
 */
package tfossi.apolge.uefkt.glieder;

import tfossi.apolge.data.guide.GuideDigitData;





/**
 * PI-Glied<br>
 * <code>y<sub>k</sub> = y<sub>k-1</sub> + (K<sub>p</sub> + K<sub>i</sub>) x<sub>k</sub> -
 *  K<sub>p</sub> x<sub>k-1</sub></code><br> 
 * Der PI_-Regler ist die Kombination aus P_Regler- und I-Regler und kombiniert den
 * Vorteil des P_Regler-Reglers, nämlich schnelle Reaktion, mit dem Vorteil des
 * I-Reglers, der exakten Ausregelung. Der PI_-geregelte Kreis ist also genau und
 * mittelschnell. 
 *
 * @author tfossi
 * @version 26.01.2015
 * @modified -
 * @since Java 1.6
 */
public final class PI_  extends  _Glied {

	/** Ki */
	private final double Ki;
	/** Kp */
	private final double Kp;
	/** k */
	public final static int k = 2;

	/**
	 * @param Kp
	 * 			Proportionalfaktor	
	 * @param Ki
	 * 			Integralfaktor
	 * @param V ???
	 * @param vg ???
	 */
	public PI_(Double Kp, Double Ki, Double V, GuideDigitData vg) {		
		this.Kp = Kp.doubleValue();
		this.Ki = Ki.doubleValue();
	}	

	/* (non-Javadoc)
	 * @see tfossi.apolge.uefkt.glieder._Glied#fkt(double[], double[])
	 */
	@Override
	public double[] fkt(final double[] xk, final double[] yk) {
		for(int i = xk.length; --i>0;)
			yk[i]=yk[i-1];
		yk[0] = yk[0] + (this.Kp + this.Ki) * xk[0] - this.Kp * xk[1];
		return yk;
	}
	/* (non-Javadoc)
	 * @see tfossi.apolge.uefkt.glieder._Glied#getN()
	 */
	@Override
	public final int getK(){
		return k;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public final String toString(){
		return this.getClass().getSimpleName()+": "+this.Kp+", "+this.Ki;
	}

}
