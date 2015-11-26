/**
 * Operation.java
 * Branch master
 * APolGe
 * tfossi-team
 * licence GPLv3  
 */
package tfossi.apolge.common.scripting.vp.pm;

import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;
import tfossi.apolge.time.pit.PPiT;

/**
 * Eine Operation berechnet einen Teil einer Formel. Eine typische Operation ist
 * z.B. der '<'-Vergleich zweier Werte.
 * 
 * @author tfossi
 * @version 1.07.2014 {@value #serialVersionUID}
 * @modified Coderevision, tfossi, 31.07.2014
 * @since Java 1.6
 */
public interface CompareOperation extends Operation{
	/** serialVersionUID */
	public final static long serialVersionUID = VERSION;

	/**
	 * Berechne zwei Objekte
	 * 
	 * @author tfossi
	 * @version 01.08.2014
	 * @modified -
	 * @since Java 1.6
	 * 
	 * @param a
	 *            Object A
	 * @param b
	 *            Object B
	 * @return Ergebnis Object oder <code>null</code> bei Fehler/Nicht möglich
	 */
	@Override
	Object calculate(Object a, Object b);

	/**
	 * Berechne zwei String
	 * 
	 * @author tfossi
	 * @version 01.08.2014
	 * @modified -
	 * @since Java 1.6
	 * 
	 * @param a
	 *            String A
	 * @param b
	 *            String B
	 * @return Ergebnis String oder <code>null</code> bei Fehler/Nicht möglich
	 */
	String calculate(String a, String b);
	/**
	 * Berechne zwei PPiT
	 * 
	 * @author tfossi
	 * @version 01.08.2014
	 * @modified -
	 * @since Java 1.6
	 * 
	 * @param a 
	 *            PPiT A
	 * @param b
	 *            PPiT B
	 * @return Ergebnis PPiT oder <code>null</code> bei Fehler/Nicht möglich
	 */
	Long calculate(PPiT a, PPiT b);	
}
