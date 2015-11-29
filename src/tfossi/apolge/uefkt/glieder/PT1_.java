/**
 *
 */
package tfossi.apolge.uefkt.glieder;

import tfossi.apolge.data.guide.GuideDigitData;



/**
 * P-Glied.<br>
 * <code>y<sub>k</sub> = K1 ( Kp x<sub>k</sub> - y<sub>k-1</sub> ) + y<sub>k-1</sub></code> 
 *
 * @author tfossi
 * @version 26.01.2015
 * @modified -
 * @since Java 1.6
 */
public final class PT1_  extends  _Glied {
	/** k */
	public final static int k = 2;
	/** Kp */
	private final double Kp;
	/** K1 */
	private final double K1;
	/**
	 * @param Kp 	
	 * 			Proportionalfaktor	
	 * @param K1 ???
	 * @param V ???
	 * @param vg ???
	 */
	public PT1_(Double Kp, Double K1, Double V, GuideDigitData vg){ //, Double[] y0){	
		this.Kp = Kp.doubleValue(); 
		this.K1 = K1.doubleValue();
	}

	/* (non-Javadoc)
	 * @see tfossi.apolge.general.regler._Glied#fkt(double)
	 */
	@Override
	public double[] fkt(double[] xk, double[] yk) {

//	    T^{*} = \frac {1}{\frac T {\Delta t}+1} 
//	    y_{n} = T^{*} \cdot ( K u_{n} -y_{n-1} ) + y_{n-1}
		
		for(int i = xk.length; --i>0;)
			yk[i]=yk[i-1];
		yk[0] = this.K1 * (this.Kp * xk[0] - yk[0]) + yk[0];
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
