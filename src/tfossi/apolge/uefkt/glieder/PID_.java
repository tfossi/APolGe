/**
 *
 */
package tfossi.apolge.uefkt.glieder;

import tfossi.apolge.data.guide.GuideDigitData;




/**
 * PID-Glied<br>
 * <code>y<sub>k</sub> = y<sub>k-1</sub> + (K<sub>p</sub> + K<sub>i</sub> + K<sub>d</sub>) x<sub>k</sub> -
 * 	(K<sub>p</sub> + 2 K<sub>d</sub>) x<sub>k-1</sub> + K<sub>d</sub> x<sub>k-2</sub></code><br>
 * Der PID_ Regler ist der universellste der klassischen Regler und vereinigt die
 * guten Eigenschaften der anderen Regler. Zur Beschreibung der Eigenschaften
 * des P_Regler-, I- und D-Anteils siehe die anderen Reglertypen. Der PID_-geregelte
 * Kreis ist genau und sehr schnell. In den meisten Anwendungen kommt deshalb
 * der PID_-Regler zum Einsatz.
 * 
 * @author tfossi
 * @version 26.01.2015
 * @modified -
 * @since Java 1.6
 * FIXME PID-Problem kp,ki,kd-Reihenfolge
 */
public final class PID_  extends  _Glied{
	/** Kp */
	private final double Kp;
	/** Ki */
	private final double Ki;
	/** Kd */
	private final double Kd;

	/** k */
	public final static int k = 3;

	/**
	 * @param Kp
	 * 			Proportionalfaktor	
	 * @param Ki
	 * 			Integralfaktor
	 * @param Kd
	 * 			Differentialfaktor
	 * @param V ???
	 * @param vg ???
	 */
	public PID_(Double Kp, Double Ki, Double Kd, Double V, GuideDigitData vg) {
		this.Kp = Kp.doubleValue();
		this.Ki = Ki.doubleValue();
		this.Kd = Kd.doubleValue();
		System.err.println(this.Kp +" : "+
		this.Ki  +" : "+
		this.Kd);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tfossi.apolge.general.regler.IRegler#regler(double, double, int)
	 */
	@Override
	public double[] fkt(final double [] xk, final double[] yk) {
		for(int i = xk.length; --i>0;)
			yk[i]=yk[i-1];
		yk[0] = yk[0]+ (this.Kp + this.Ki + this.Kd) * xk[0] -
				(this.Kp + 2 * this.Kd) * xk[1] +
				this.Kd * xk[2];
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
		return this.getClass().getSimpleName()+": "+this.Kp+", "+this.Ki+", "+this.Kd;
	}
}
