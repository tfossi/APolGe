/**
 * 
 */
package tfossi.apolge.uefkt.glieder;

import tfossi.apolge.data.guide.GuideDigitData;




/**
 * P-Glied.<br>
 * <code>y<sub>k</sub> = Kp x<sub>k</sub></code>
 *
 * @author tfossi
 * @version 26.01.2015
 * @modified -
 * @since Java 1.6
 */
public final class P_  extends  _Glied {
	/** k */
	public final static int k = 2;
	/** Kp */
	private final double Kp;
	
	/**
	 * @param Kp 	
	 * 			Proportionalfaktor	
	 * @param V ???
	 * @param vg ???
	 */
	public P_(Double Kp, Double V, GuideDigitData vg){ //, Double[] y0){	
		this.Kp = Kp.doubleValue(); 
	}

	/* (non-Javadoc)
	 * @see tfossi.apolge.general.regler._Glied#fkt(double)
	 */
	@Override
	public double[] fkt(double[] xk, double[] yk) {
		for(int i = xk.length; --i>0;)
			yk[i]=yk[i-1];
		yk[0] =  this.Kp * xk[0];
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
		return this.getClass().getSimpleName()+" kp="+this.Kp;
	}
}
