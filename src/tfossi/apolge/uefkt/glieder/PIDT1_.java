/**
 *
 */
package tfossi.apolge.uefkt.glieder;

import tfossi.apolge.data.guide.GuideDigitData;




/**
 * PIDT1-Glied<br>
 * <code>y<sub>k</sub> = (1 + K<sub>1</sub>) y<sub>k-1</sub> - K<sub>1</sub> y<sub>k-2</sub> + 
 * 			(K<sub>p</sub> + K<sub>i</sub> + K<sub>d</sub> ) x<sub>k</sub>  -
 * 			(K<sub>p</sub> (1+K<sub>1</sub>) + K<sub>1</sub> K<sub>i</sub> + 2 K<sub>d</sub>) x<sub>k-1</sub> +
 * 			(K<sub>p</sub> K<sub>1</sub> + K<sub>d</sub>) x<sub>k-2</sub></code>
 *
 *
 * @author tfossi
 * @version 26.01.2015
 * @modified -
 * @since Java 1.6
 */
public final class PIDT1_  extends  _Glied {
	/** Kp */
	private final double Kp;
	/** Ki */
	private final double Ki;
	/** Kd */
	private final double Kd;
	/** K1 */
	private final double K1;
	/** k */
	public final static int k = 3;

	/**
	 * @param Kp
	 *            Proportionalfaktor
	 * @param Ki
	 *            Integralfaktor
	 * @param Kd
	 *            Differentialfaktor
	 * @param K1
	 *            Verzögerungsfaktor
	 * @param V ???
	 * @param vg ???
	 */
	public PIDT1_(Double Kp, Double Ki, Double Kd, Double K1, Double V, GuideDigitData vg) {
		this.Kp = Kp.doubleValue();
		this.Ki = Ki.doubleValue();
		this.Kd = Kd.doubleValue();
		this.K1 = K1.doubleValue();
		
		System.err.println(this.Kp +" : "+
		this.Ki +" : "+
		this.Kd  +" : "+
		this.K1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tfossi.apolge.general.regler.IRegler#regler(double, double, int)
	 */
	@Override
	public double[] fkt(final double[] xk, final double[] yk) {
		for(int i = xk.length; --i>0;)
			yk[i]=yk[i-1];
		yk[0] = (1 + this.K1) * yk[0] - this.K1 * yk[1]
				+ (this.Kp + this.Ki + this.Kd) * xk[0]
				- (this.Kp * (1 + this.K1) + this.K1 * this.Ki + 2 * this.Kd) * xk[1]
				+ (this.Kp * this.K1 + this.Kd) * xk[2];

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
		return this.getClass().getSimpleName()+": "+this.Kp+", "+this.Ki+", "+this.Kd+", "+this.K1;
	}
}
