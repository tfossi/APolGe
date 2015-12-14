/**
 * ArrayIndexInnerBoundsException.java
 * Branch master
 * APolGe
 * tfossi-team
 * licence GPLv3  
 */
package tfossi.apolge.common.scripting;

/**
 * Array ist länger als bei der Auswertung berücksichtigt!
 * @author tfossi
 * @version 18.08.2014
 * @modified -
 * @since Java 1.6
 */
public class ArrayIndexInnerBoundsException extends Exception {
	/**
	 * Exception 
	 * @param string -
	 * @modified -
	 */
	public ArrayIndexInnerBoundsException(String string) {
		super(string);
	}
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
}
