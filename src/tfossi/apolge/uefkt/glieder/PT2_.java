/**
 *
 */
package tfossi.apolge.uefkt.glieder;

import tfossi.apolge.data.guide.GuideDigitData;




/**
 * PT2-Glied.<br>
 * <code>y<sub>k</sub> = y<sub>k-1</sub> + T/to *  x<sub>k-1</sub><br>
 * x<sub>k</sub> = x<sub>k-1</sub> + T/to * [e<sub>k</sub> − y<sub>k-1</sub> − 2D x<sub>k-1</sub> ]</code>
 *
 * @author tfossi
 * @version 26.01.2015
 * @modified -
 * @since Java 1.6
 */
public final class PT2_  extends  _Glied {
	/** k */
	public final static int k = 2;
	/** Kp */
	private final double Kp;
	/** K1 */
	private final double K1;
	/** D2 */
	private final double D2;
		
	/** x */
	private final double x[] = new double[k];
	/**
	 * @param Kp 	
	 * 			Proportionalfaktor	
	 * @param K1 ???
	 * @param D ???
	 * @param V ???
	 * @param vg ???
	 */
	public PT2_(Double Kp, Double K1, Double D, Double V, GuideDigitData vg){ //, Double[] y0){	
		this.Kp = Kp.doubleValue(); 
		this.K1 = K1.doubleValue();
		this.D2 = 2. * D.doubleValue();
		this.x[0]=0.;
	}

	/* (non-Javadoc)
	 * @see tfossi.apolge.general.regler._Glied#fkt(double)
	 */
	@Override
	public double[] fkt(double[] uk, double[] yk) {	
		// y(k)=y(k-1) + T/T0 x(k-1)							(1)
		// x(k)=x(k-1) + T/T0 [v · e(k) − y(k-1) − 2D x(k-1) ]	(2)
		
		for(int i = yk.length; --i>0;)
			yk[i]=yk[i-1];
		
//		(1) x(0) ist hier noch alter Wert, also eigentlich x(1)
		yk[0] = yk[0] + this.K1 * this.x[0];
//		(2) x(0) ist hier noch alter Wert, also eigentlich x(1)
		this.x[0] = this.x[0] + this.K1 * (this.Kp* uk[0] - yk[0] - this.D2 * this.x[0]);
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
		return this.getClass().getSimpleName()+": "+this.Kp;
	}
}
