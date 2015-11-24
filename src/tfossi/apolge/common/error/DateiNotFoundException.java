/**
 * DateiNotFoundException.java
 * Branch master
 * APolGe
 * tfossi-team
 * licence GPLv3  
 */
package tfossi.apolge.common.error;

/**
 * Datei nicht gefunden!
 *
 * @author tfossi
 * @version 11.08.2014
 * @modified -
 * @since Java 1.6
 */
public class DateiNotFoundException extends Exception {

	/**
	 * 
	 * @param string
	 * 			Exceptiontext
	 * @modified -
	 */
	public DateiNotFoundException(String string) {
		super(string);
	}

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

}
