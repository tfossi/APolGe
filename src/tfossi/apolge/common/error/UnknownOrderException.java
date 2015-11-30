package tfossi.apolge.common.error;

/**
 * TODO Comment
 *
 * @author tfossi
 * @version 11.08.2014
 * @modified -
 * @since Java 1.6
 */
public class UnknownOrderException extends Exception {
	/**
	 * Constructs a new exception. The cause is not initialized, and may subsequently be
	 * initialized by a call to Throwable.initCause(java.lang.Throwable).
	 */
	public UnknownOrderException() {
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
	public UnknownOrderException(String message) {
		super(message);
	}

	// ---- Selbstverwaltung -----------------------------------------------------
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

}
