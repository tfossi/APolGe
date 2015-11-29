/**
 * 
 */
package tfossi.apolge.uefkt.glieder;

import tfossi.apolge.data.guide.GuideDigitData;



/**
 * I-Glied: <br>
 * <code>y<sub>k</sub> = y<sub>k-1</sub> + Ki x<sub>k</sub></code>
 *
 * @author tfossi
 * @version 26.01.2015
 * @modified -
 * @since Java 1.6
 */
public final class I_  extends  _Glied{
	/** Ki */
	private final double Ki;
	/** k */
	public final static int k = 2;
	/**
	 * @param Ki
	 * 			Integralfaktor
	 * @param V ??
	 * @param vg ??
	 */
	public I_(Double Ki, Double V, GuideDigitData vg){ //, Double[] y0){
		this.Ki = Ki.doubleValue();
	}
	
	/* (non-Javadoc)
	 * @see tfossi.apolge.general.regler._Glied#fkt(double[])
	 */
	@Override
	public double[] fkt(double[] xk, double[] yk) {		 
		for(int i = xk.length; --i>0;)
			yk[i]=yk[i-1];		
		yk[0] = yk[0] + this.Ki * xk[0];
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
		return this.getClass().getSimpleName()+": "+this.Ki;
	}
}
