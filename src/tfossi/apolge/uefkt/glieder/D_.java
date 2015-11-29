/**
 * 
 */
package tfossi.apolge.uefkt.glieder;


import tfossi.apolge.data.guide.GuideDigitData;


/**
 * D-Glied<br>
 * <code>y<sub>k</sub> = K<sub>d</sub> (x<sub>k</sub> - x<sub>k-1</sub>)</code>
 *  
 * @author tfossi
 * @version 26.01.2015
 * @modified -
 * @since Java 1.6
 */
public final class D_  extends  _Glied{
	/** k */
	public final static int k = 2;
	/** Kd */
	private final double Kd;
	
	/**
	 * @param Kd
	 * 			Differentialfaktor	 
	 * @param V ??
	 * @param vg ??
	 * @modified -
	 */
	public D_(Double Kd, Double V, GuideDigitData vg){
		this.Kd = Kd.doubleValue();
	}
	/* (non-Javadoc)
	 * @see tfossi.apolge.general.regler._Glied#fkt(double[])
	 */
	@Override
	public double[] fkt(final double[] xk, final double[] yk) {
		for(int i = yk.length; --i>0;)
			yk[i]=yk[i-1];
		yk[0] = this.Kd * (xk[0] - xk[1]);
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
		return this.getClass().getSimpleName()+": "+this.Kd;
	}
}
