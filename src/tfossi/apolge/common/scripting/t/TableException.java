/**
 * TableException.java
 * Branch master
 * APolGe
 * tfossi-team
 * licence GPLv3  
 */
package tfossi.apolge.common.scripting.t;

import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;

/**
 * Exception beim Table erstellen
 * 
 * @author tfossi
 * @since Java 1.6
 * @version 10.08.2014
 * @modified -
 *
 */
public class TableException extends Exception {

	/**
	 * Exception beim Parsen
	 * @param string
	 * 			Fehlertext
	 * @modified -
	 */
	public TableException(String string) {
		super(string);
	}

	/** serialVersionUID */
	private static final long serialVersionUID = VERSION;

}
