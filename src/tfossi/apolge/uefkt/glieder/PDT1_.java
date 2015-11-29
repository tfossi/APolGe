/**
 *
 */
package tfossi.apolge.uefkt.glieder;

import tfossi.apolge.data.guide.GuideDigitData;




/**
 * PDT1-Glied<br> * 
 * <code>y<sub>k</sub> = K<sub>1</sub> y<sub>k-1</sub> + 
 * 				(K<sub>p</sub> + K<sub>d</sub>) x<sub>k</sub> -
 * 				(K<sub>p</sub> * K<sub>1</sub> + K<sub>d</sub> ) x<sub>k-1</sub></code>
 *
 * @author tfossi
 * @version 26.01.2015
 * @modified -
 * @since Java 1.6
 */
public final class PDT1_  extends  _Glied {
	/** Kp */
	private final double Kp;
	/** Kd */
	private final double Kd;
	/** K1 */
	private final double K1;
	/** k */
	public final static int k = 2;


	/**
	 * @param Kp
	 * 			Proportionalfaktor	
	 * @param Kd
	 * 			Differentialfaktor
	 * @param K1
	 * 			Verzögerungsfaktor
	 * @param V ???
	 * @param vg ???
	 */
	public PDT1_(Double Kp, Double Kd, Double K1, Double V,GuideDigitData vg) {
		this.Kp = Kp.doubleValue();
		this.Kd = Kd.doubleValue();
		this.K1 = K1.doubleValue();
	}

	/* (non-Javadoc)
	 * @see tfossi.apolge.general.regler._Glied#fkt(double[])
	 */
	@Override
	public double[] fkt(final double[] xk, final double[] yk) {
		for(int i = xk.length; --i>0;)
			yk[i]=yk[i-1];
		yk[0] =  this.K1 * yk[0] + (this.Kp + this.Kd) * xk[0]
				- (this.Kp * this.K1 + this.Kd) * xk[1];
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
		return this.getClass().getSimpleName()+": "+this.Kp+", "+this.Kd+", "+this.K1;
	}
}
