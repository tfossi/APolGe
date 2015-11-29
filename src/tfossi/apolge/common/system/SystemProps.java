/** 
 * SystemProps.java
 * Branch base
 * APolGe
 * tfossi-team
 * licence GPLv3 
 */
package tfossi.apolge.common.system;
import static tfossi.apolge.common.constants.ConstValue.NTAB;
import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;

import java.util.Enumeration;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

/**
 * Ausgabe der Systemeinstellungen<br>
 * Die Systemeinstellungen werden über System.getProperties() erfasst und formatiert
 * ausgegeben (Bedingung Loggerlevel <= INFO).<br>
 * Dient zur Unterstützung bei der Fehlerbehebung.
 *
 * @author tfossi
 * @version 11.08.2014
 * @modified -
 * @since Java 1.6
 */
public class SystemProps {
	/**
	 * Ausgabe der Systemeinstellungen<br>
	 * Die Systemeinstellungen werden über System.getProperties() erfasst und formatiert
	 * ausgegeben (Bedingung Loggerlevel <= INFO).<br>
	 * Dient zur Fehlerbehebung.	
	 * @param probs -
	 * @modified -
	 */
	public SystemProps(Properties probs) {
		if(probs.get("probs").equals(new Boolean(false)))
			return;
		Properties sysprops = System.getProperties(); // Systemeinstellungen beziehen
		Enumeration<?> propnames = sysprops.propertyNames();
		String properties = "Systemeinstellungen:";
		while (propnames.hasMoreElements()) {
			String propname = (String) propnames.nextElement();
			properties += NTAB + propname + "=" + System.getProperty(propname);
		}
		if(Logger.getRootLogger().getLevel().toInt()<=Priority.DEBUG_INT)
			System.out.println(properties);
		
	}

	// ---- Selbstverwaltung --------------------------------------------------
	/** serialVersionUID */
	@SuppressWarnings("unused")
	private final static long serialVersionUID = VERSION;
	/** logger */
	@SuppressWarnings("unused")
	private final static Logger logger = Logger.getLogger(SystemProps.class.getPackage().getName());
}
