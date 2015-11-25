/**
 * PPiT.java
 * Branch master
 * APolGe
 * tfossi-team
 * licence GPLv3  
 */
package tfossi.apolge.time.pit;

import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;

import org.apache.log4j.Logger;

/**
 * Point in Time ist das Zeitformat
 * 
 * @see CPiT
 * 
 * @author tfossi
 * @version 1.07.2014
 * @modified -
 * @since Java 1.6
 */
public class PPiT implements Cloneable {


	/**
	 * @return Umwandlung von PPiT auf CPiT-Format 
	 * @modified - 
	 */
	@SuppressWarnings("static-method")
	public final CPiT toCPit() {
		return null;
	}
	
	// ---- Selbstverwaltung --------------------------------------------------
	
	/** serialVersionUID */
	@SuppressWarnings("unused")
	private final static long serialVersionUID = VERSION;
	
	/** logger */
	@SuppressWarnings("unused")
	private final static Logger logger = Logger.getLogger(PPiT.class
			.getPackage().getName());
}
