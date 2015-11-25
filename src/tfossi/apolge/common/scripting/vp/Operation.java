/**
 * Operation.java
 * Branch master
 * APolGe
 * tfossi-team
 * licence GPLv3  
 */
package tfossi.apolge.common.scripting.vp;

import static tfossi.apolge.common.constants.ConstValueExtension.*;
import Jama.Matrix;

import tfossi.apolge.time.pit.PPiT;

/**
 * Eine Operation berechnet einen Teil einer Formel. Eine typische Operation ist
 * z.B. die Addition "+", sie zählt zwei Zahlen zusammen.
 * 
 * @author tfossi
 * @version 1.07.2014 {@value #serialVersionUID}
 * @modified Coderevision, tfossi, 31.07.2014
 * @since Java 1.6
 */
public interface Operation {
	/** serialVersionUID */
	public final static long serialVersionUID = VERSION;

	/**
	 * Je höhe die Priorität einer Operation ist, desto früher wird sie
	 * berechnet.
	 * 
	 * @return Die Priorität
	 */
	public int getPriority();

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
	Object calculate(Object a, Object b);

	/**
	 * Berechne zwei Integer
	 * 
	 * @author tfossi
	 * @version 01.08.2014
	 * @modified -
	 * @since Java 1.6
	 * 
	 * @param a
	 *            Integer A
	 * @param b
	 *            Integer B
	 * @return Ergebnis Integer oder <code>null</code> bei Fehler/Nicht möglich
	 */
	Integer calculate(Integer a, Integer b);
	/**
	 * TODO Comment
	 * @param a
	 * @param b
	 * @return
	 * @modified - 
	 */
	Byte calculate(Byte a, Byte b);
	/**
	 * TODO Comment
	 * @param a
	 * @param b
	 * @return
	 * @modified - 
	 */
	Short calculate(Short a, Short b);

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

	/**
	 * Berechne zwei Long
	 * 
	 * @author tfossi
	 * @version 01.08.2014
	 * @modified -
	 * @since Java 1.6
	 * 
	 * @param a
	 *            Long A
	 * @param b
	 *            Long B
	 * @return Ergebnis Long oder <code>null</code> bei Fehler/Nicht möglich
	 */
	Long calculate(Long a, Long b);

	/**
	 * Berechne zwei Double
	 * 
	 * @author tfossi
	 * @version 01.08.2014
	 * @modified -
	 * @since Java 1.6
	 * 
	 * @param a
	 *            Double A
	 * @param b
	 *            Double B
	 * @return Ergebnis Double oder <code>null</code> bei Fehler/Nicht möglich
	 */
	Double calculate(Double a, Double b);

	/**
	 * Berechne zwei Float
	 * 
	 * @author tfossi
	 * @version 01.08.2014
	 * @modified -
	 * @since Java 1.6
	 * 
	 * @param a
	 *            Float A
	 * @param b
	 *            Float B
	 * @return Ergebnis Float oder <code>null</code> bei Fehler/Nicht möglich
	 */
	Float calculate(Float a, Float b);

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
	/**
	 * TODO Comment
	 * @param a
	 * @param b
	 * @return
	 * @modified - 
	 */
	Matrix calculate(Matrix a, Matrix b);
	/**
	 * TODO Comment
	 * @param a
	 * @param b
	 * @return
	 * @modified - 
	 */
	Matrix calculate(Matrix a, Number b);
	/**
	 * TODO Comment
	 * @param a
	 * @param b
	 * @return
	 * @modified - 
	 */
	Matrix calculate(Number a, Matrix b);
}
