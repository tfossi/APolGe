/**
 * LoadScriptException.java
 * branch master
 * APolGe
 * tfossi-team
 * licence GPLv3   
 */
package tfossi.apolge.common.scripting;

/**
 * General Loading Script Exception
 *
 * @author tfossi
 * @version 18.08.2014
 * @modified -
 * @since Java 1.6
 */
public class LoadScriptException extends Exception {
	/**
	 * Exception 
	 * @param string -
	 * @modified -
	 */
	public LoadScriptException(String string) {
		super(string);
	}

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
}
