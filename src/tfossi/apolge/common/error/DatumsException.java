/**
 * DatumsException.java
 * Branch master
 * APolGe
 * tfossi-team
 * licence GPLv3  
 */
package tfossi.apolge.common.error;

/**
 * Exception bei fehlerhaften Datumsoperationen
 * 
 * @author tfossi
 * @since Java 1.6
 * @version 10.08.2014
 * @modified -
 */
public class DatumsException extends Exception {
	/**
	 * Constructs a new exception. The cause is not initialized, and may subsequently be
	 * initialized by a call to Throwable.initCause(java.lang.Throwable).
	 */
	public DatumsException() {
		super();
	}

	/**
	 * Constructs a new exception with the specified detail message. The cause is not
	 * initialized, and may subsequently be initialized by a call to
	 * Throwable.initCause(java.lang.Throwable).
	 * 
	 * @param message
	 *            the detail message. The detail message is saved for later retrieval by
	 *            the Throwable.getMessage() method.
	 */
	public DatumsException(String message) {
		super(message);
	}

	// ---- Selbstverwaltung -----------------------------------------------------
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

}
