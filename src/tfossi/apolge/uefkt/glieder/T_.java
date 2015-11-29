/**
 *
 */
package tfossi.apolge.uefkt.glieder;


import tfossi.apolge.data.guide.GuideDigitData;


/**
 * T-Glied<br>
 * <code>y<sub>k</sub> = y<sub>k-K</sub><code><br>
 * Totzeitglied. Verzögert das Signal um <code>K</code>-Abtastungen.
 *
 * @author tfossi
 * @version 26.01.2015
 * @modified -
 * @since Java 1.6
 */
public class T_  extends _Glied{
	/** K */
	private final int K;
	/** vertex */
	@SuppressWarnings("unused")
	private final GuideDigitData vertex;
	

	/**
	 * @param K
	 * 			Totzeit
	 * @param V ???
	 * @param vg ???
	 */
	public T_(Double K, Double V, GuideDigitData vg) {		
		this.K = K.intValue();
		this.vertex = vg;
	}

	/* (non-Javadoc)
	 * @see tfossi.apolge.general.regler._Glied#fkt(double[])
	 */
	@Override
	public double[] fkt(final double[] xk, final double[] yk) {
//		Double y0 = new Double(yk[0].doubleValue());
		for (int k = this.K; k > 0; k--) yk[k] = yk[k - 1];
		yk[0] = xk[this.K];
		return yk; //new Double[]{yk[this.K], y0};
	}
	/* (non-Javadoc)
	 * @see tfossi.apolge.uefkt.glieder._Glied#diff(double, double)
	 */
	@Override
	public double diff(double w0, double y0){
		return w0;
	}
	/* (non-Javadoc)
	 * @see tfossi.apolge.uefkt.glieder._Glied#getN()
	 */
	@Override
	public final int getK(){
		return this.K+1;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public final String toString(){
		return this.getClass().getSimpleName()+": "+this.K;
	}
}
