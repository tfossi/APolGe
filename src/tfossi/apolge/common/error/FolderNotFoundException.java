/**
 * FolderNotFoundException.java
 * Branch master
 * APolGe
 * tfossi-team
 * licence GPLv3  
 */
package tfossi.apolge.common.error;

/**
 * Exception, wenn Laufwerksordner nicht gefunden wird
 *
 * @author tfossi
 * @version 11.08.2014
 * @modified -
 * @since Java 1.6
 */
public class FolderNotFoundException extends Exception {

	/**
	 * 
	 * @param string
	 * 			Exceptionstext
	 * @modified -
	 */
	public FolderNotFoundException(String string) {
		super(string);
	}

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

}
