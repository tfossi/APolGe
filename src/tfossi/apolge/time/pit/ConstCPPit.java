/** 
 * ConstCPPit.java
 * Branch master
 * APolGe
 * tfossi-team
 * licence GPLv3  
 */
package tfossi.apolge.time.pit;

import java.util.TimeZone;

import org.apache.log4j.Logger;

/**
 * Konstanten und konstante Methoden für CPPit
 * 
 * @see CPiT
 * @see PPiT
 * 
 * @author tfossi
 * @modified -
 * @version 10.08.2014
 * @since Java 1.6
 */
public class ConstCPPit {

	static {
		// Neutrale Zeitzone einstellen (Weltzeit)
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}


	/**
	 * Abstand in Milli-Sekunden
	 * 
	 * @param dt1
	 *            ist der Meßzeitpunkt
	 * @param dt2
	 *            ist Vergleichszeitpunkt
	 * @return Abstand in Milli-Sekunden
	 */
	public final static long difference(final CPiT dt1, final CPiT dt2) {
		return 0L;

	}

	// ---- Selbstverwaltung --------------------------------------------------
	/** logger */
	@SuppressWarnings("unused")
	private final static Logger logger = Logger.getLogger(ConstCPPit.class
			.getPackage().getName() + ".ConstCPPit.class");
}
