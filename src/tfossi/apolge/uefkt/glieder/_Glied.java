/**
 * 
 */
package tfossi.apolge.uefkt.glieder;

/**
 * Scnittstelle zu den Übertragungsliedern:<br>
 * <ul>
 * <li>P_ (Proprotionalglied):<br>
 * <code>y<sub>k</sub> = Kp x<sub>k</sub></code></li>
 * <li>PT1_ :<br>
 * <code>y<sub>k</sub> = K1 ( Kp x<sub>k</sub> - y<sub>k-1</sub> ) + y<sub>k-1</sub></code>
 * <li>PT2_:<br>
 * <code>y<sub>k</sub> = y<sub>k-1</sub> + K1 *  u<sub>k-1</sub><br>
 * u<sub>k</sub> = u<sub>k-1</sub> + K1 * [Kp x<sub>k</sub> − y<sub>k-1</sub> − 2D u<sub>k-1</sub> ]<br>
 * K1 = T/dt</code>
 * </li>
 * <li>I- (Integralglied):<br>
 * <code>y<sub>k</sub> = y<sub>k-1</sub> + Ki x<sub>k</sub></code></li>
 * <li>D- (Differenzierglied n.relbar.):<br>
 * <code>y<sub>k</sub> = K<sub>d</sub> (x<sub>k</sub> - x<sub>k-1</sub>)</code></li>
 * <li>DT1_ (n.relbar.):<br>
 * <code>y<sub>k</sub> = K<sub>1</sub> y<sub>(k-1)</sub> + 
		K<sub>d</sub> (x<sub>k</sub> - x<sub>k-1</sub>)</code></li>
 * <li>PI_:<br>
 * <code>y<sub>k</sub> = y<sub>k-1</sub> + (K<sub>p</sub> + K<sub>i</sub>) x<sub>k</sub> -
 *  K<sub>p</sub> x<sub>k-1</sub></code></li>
 * <li>PD_:<br>
 * <code>y<sub>k</sub> = (K<sub>p</sub> + K<sub>d</sub>) * x<sub>k</sub>
 *  - K<sub>d</sub> x<sub>k-1</sub></code></li>
 * <li>PDT1_:<br>
 * <code>y<sub>k</sub> = K<sub>1</sub> y<sub>k-1</sub> + (K<sub>p</sub> + K<sub>d</sub>)
 * x<sub>k</sub> -  (K<sub>p</sub> * K<sub>1</sub> + K<sub>d</sub> ) x<sub>k-1</sub></code></li>
 * <li>PID_:<br>
 * <code>y<sub>k</sub> = y<sub>k-1</sub> + (K<sub>p</sub> + K<sub>i</sub> + K<sub>d</sub>) x<sub>k</sub> -
 * 	(K<sub>p</sub> + 2 K<sub>d</sub>) x<sub>k-1</sub> + K<sub>d</sub> x<sub>k-2</sub></code></li>
 * <li>PIDT1_-Regler:<br>
 * <code>y<sub>k</sub> = (1 + K<sub>1</sub>) y<sub>k-1</sub> - K<sub>1</sub> y<sub>k-2</sub> + 
 * 			(K<sub>p</sub> + K<sub>i</sub> + K<sub>d</sub> ) x<sub>k</sub>  -
 * 			(K<sub>p</sub> (1+K<sub>1</sub>) + K<sub>1</sub> K<sub>i</sub> + 2 K<sub>d</sub>) x<sub>k-1</sub> +
 * 			(K<sub>p</sub> K<sub>1</sub> + K<sub>d</sub>) x<sub>k-2</sub></code></li>
 * <li>T (Totzeitglied)<br>
 * <code>y<sub>k</sub> = y<sub>k-K</sub><code></li>
 * </ul>
 *
 
 * @author tfossi
 * @version 26.01.2015
 * @modified -
 * @since Java 1.6
 */
public abstract class _Glied {
	/**
	 * Scnittstelle zu den Übertragungsliedern:<br>
	 * Berechnet die Übertragungsfunktion y() -> x()<br>
	 * <ul>
	 * <li>P_ (Proprotionalglied):<br>
	 * <code>y<sub>k</sub> = Kp x<sub>k</sub></code></li>
	 * <li>PT1_ :<br>
	 * <code>y<sub>k</sub> = K1 ( Kp x<sub>k</sub> - y<sub>k-1</sub> ) + y<sub>k-1</sub></code>
	 * <li>PT2_:<br>
	 * <code>y<sub>k</sub> = y<sub>k-1</sub> + T/to *  u<sub>k-1</sub><br>
	 * u<sub>k</sub> = u<sub>k-1</sub> + T/to * [x<sub>k</sub> − y<sub>k-1</sub> − 2D u<sub>k-1</sub> ]</code>
	 * </li>
	 * <li>I- (Integralglied):<br>
	 * <code>y<sub>k</sub> = y<sub>k-1</sub> + Ki x<sub>k</sub></code></li>
	 * <li>D- (Differenzierglied n.relbar.):<br>
	 * <code>y<sub>k</sub> = K<sub>d</sub> (x<sub>k</sub> - x<sub>k-1</sub>)</code></li>
	 * <li>DT1_ (n.relbar.):<br>
	 * <code>y<sub>k</sub> = K<sub>1</sub> y<sub>(k-1)</sub> + 
			K<sub>d</sub> (x<sub>k</sub> - x<sub>k-1</sub>)</code></li>
	 * <li>PI_:<br>
	 * <code>y<sub>k</sub> = y<sub>k-1</sub> + (K<sub>p</sub> + K<sub>i</sub>) x<sub>k</sub> -
	 *  K<sub>p</sub> x<sub>k-1</sub></code></li>
	 * <li>PD_:<br>
	 * <code>y<sub>k</sub> = (K<sub>p</sub> + K<sub>d</sub>) * x<sub>k</sub>
	 *  - K<sub>d</sub> x<sub>k-1</sub></code></li>
	 * <li>PDT1_:<br>
	 * <code>y<sub>k</sub> = K<sub>1</sub> y<sub>k-1</sub> + (K<sub>p</sub> + K<sub>d</sub>)
	 * x<sub>k</sub> -  (K<sub>p</sub> * K<sub>1</sub> + K<sub>d</sub> ) x<sub>k-1</sub></code></li>
	 * <li>PID_:<br>
	 * <code>y<sub>k</sub> = y<sub>k-1</sub> + (K<sub>p</sub> + K<sub>i</sub> + K<sub>d</sub>) x<sub>k</sub> -
	 * 	(K<sub>p</sub> + 2 K<sub>d</sub>) x<sub>k-1</sub> + K<sub>d</sub> x<sub>k-2</sub></code></li>
	 * <li>PIDT1_-Regler:<br>
	 * <code>y<sub>k</sub> = (1 + K<sub>1</sub>) y<sub>k-1</sub> - K<sub>1</sub> y<sub>k-2</sub> + 
	 * 			(K<sub>p</sub> + K<sub>i</sub> + K<sub>d</sub> ) x<sub>k</sub>  -
	 * 			(K<sub>p</sub> (1+K<sub>1</sub>) + K<sub>1</sub> K<sub>i</sub> + 2 K<sub>d</sub>) x<sub>k-1</sub> +
	 * 			(K<sub>p</sub> K<sub>1</sub> + K<sub>d</sub>) x<sub>k-2</sub></code></li>
	 * <li>T (Totzeitglied)<br>
	 * <code>y<sub>k</sub> = y<sub>k-K</sub><code></li>
	 * </ul>
	 * <br>
	 * k: Aktuelle Abtastung<br>
	 * 
	 * @param xk
	 *            Eingangsgröße
	 * @param yk
	 *            Alte Ausgangsgröße k-1
	 * @return y<sub>k<sub>0</sub></sub><br>
	 *         Neue Ausgangsgröße<br>
	 *         y<sub>k<sub>n</sub></sub<br>
	 *         Alte Ausgangsgröße        
	 */
	public abstract double[] fkt(double[] xk, double[] yk);
	/**
	 * Soll-/Istwertvergleich
	 * @param w0
	 * 			Sollwert
	 * @param y0
	 * 			Istwert
	 * @return Differenz
	 * @modified - 
	 */
	@SuppressWarnings("static-method")
	public double diff(final double w0, final double y0){
		return w0 - y0;
	}

	/**
	 * @return Anzahl der Abtastungen k, die notwendig sind.
	 */
	public abstract int getK();

}
