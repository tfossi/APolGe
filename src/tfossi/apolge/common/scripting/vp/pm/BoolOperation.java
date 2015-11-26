/**
 * Operation.java
 * Branch master
 * APolGe
 * tfossi-team
 * licence GPLv3  
 */
package tfossi.apolge.common.scripting.vp.pm;

import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;

/**
 * Eine Operation berechnet einen Teil einer Formel. Eine typische Operation ist
 * z.B. die Addition "||", sie verknüpft zwei boolesche Werte mit ODER.
 * 
 * @author tfossi
 * @version 1.07.2014 {@value #serialVersionUID}
 * @modified Coderevision, tfossi, 31.07.2014
 * @since Java 1.6
 */
public interface BoolOperation  extends Operation{
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
	 * Berechne zwei Boolean
	 * 
	 * @author tfossi
	 * @version 01.08.2014
	 * @modified -
	 * @since Java 1.6
	 * 
	 * @param a
	 *            Boolean A
	 * @param b
	 *            Boolean B
	 * @return Ergebnis Boolean oder <code>null</code> bei Fehler/Nicht möglich
	 */
	Boolean calculate(Boolean a, Boolean b);
		
}
