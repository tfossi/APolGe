/**
 *
 */
package tfossi.apolge.uefkt.glieder;

import tfossi.apolge.data.guide.GuideDigitData;



/**
 * PD-Glied<br>
 * <code>y<sub>k</sub> = (K<sub>p</sub> + K<sub>d</sub>) * x<sub>k</sub>
 *  - K<sub>d</sub> x<sub>k-1</sub></code><br>
 * Der proportional-differential wirkende Regler kombiniert den P_Regler-Regler mit
 * einem D-Anteil. Der D-Anteil bewertet die Änderung einer Regelabweichung (er
 * differenziert) und berechnet so deren Änderungsgeschwindigkeit. Diese wird
 * mit dem Faktor Kd multipliziert und zum P_Regler-Anteil hinzuaddiert. Der PD_-Regler
 * reagiert damit schon auf Ankündigungen von Veränderungen, das bewirkt
 * sozusagen ein Vorhalten beim Regeln.
 * 
 * Der PD_-geregelte Kreis ist sehr schnell im Vergleich zu anderen Regelungen,
 * und manche Regelkreise (solche mit zweifacher Integration) sind ohne D-Anteil
 * überhaupt nicht stabilisierbar. Das Problem der proportionalen Regler, die
 * bleibende Regelabweichung, ist beim PD_-Regler allerdings weiterhin vorhanden!
 * 
 * Ein Nachteil aller Regler mit D-Anteil kann die Unruhe im Kreis sein. Ist das
 * Sensorsignal verrauscht, so wird dieses Rauschen durch die Differenziation
 * weiter verstärkt und wieder in den Kreis hineingegeben. Dadurch wird der
 * Aktuator stärker belastet. Macht der Regler insbesondere sehr hohe Ausschläge
 * als Folge von schnellen Änderungen des Sollwertes, dann kann es sein, dass
 * das Stellglied oder der Aktuator diese nicht umsetzen kann - die Wirkung des
 * D-Anteils würde dann durch die Begrenzung verpuffen, und das
 * Einschwingverhalten wäre nicht wie berechnet, sondern meist langsamer. Dies
 * gilt aber nur für große Sprünge. Bei den normalen kleinen Regelvorgängen zum
 * Ausgleich von Störeinflüssen wirkt der D-Anteil wie beabsichtigt.
 * 
 * @author tfossi
 * @version 26.01.2015
 * @modified -
 * @since Java 1.6
 */
public final class PD_  extends  _Glied {	
	/** Kp */
	private final double Kp;
	/** Kd */
	private final double Kd;
	/** k */
	public final static int k = 2;
	
	/* (non-Javadoc)
	 * @see tfossi.apolge.general.regler.IRegler#regler(double, double, int)
	 */
	@Override
	public double[] fkt(final double [] xk, final double[] yk) {
		for(int i = xk.length; --i>0;)
			yk[i]=yk[i-1];
		yk[0] = (this.Kp + this.Kd) * xk[0] - this.Kd * xk[1];
		return yk;
	}
	/* (non-Javadoc)
	 * @see tfossi.apolge.uefkt.glieder._Glied#getN()
	 */
	@Override
	public final int getK(){
		return k;
	}

	/**
	 * @param Kp
	 * 			Proportionalfaktor	
	 * @param Kd
	 * 			Differentialfaktor
	 * @param V ???
	 * @param vg ???
	 */
	public PD_(Double Kp, Double Kd, Double V, GuideDigitData vg) {
		this.Kp = Kp.doubleValue();
		this.Kd = Kd.doubleValue();
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public final String toString(){
		return this.getClass().getSimpleName()+": "+this.Kp+", "+this.Kd;
	}
}
