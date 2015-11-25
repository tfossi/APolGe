/**
 * NoPseudoListen.java
 * Branch master
 * APolGe
 * tfossi-team
 * licence GPLv3
 */
package tfossi.apolge.common.scripting.p;

import static tfossi.apolge.common.constants.ConstValue.LOGGER;
import static tfossi.apolge.common.constants.ConstValue.operator;
import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

/**
 *Vereinigt zeilenübergreifende Anweisungen und löscht Leerzeilen
 * 
 * @author tfossi
 * @version 01.08.2014
 * @modified -
 * @since Java 1.6
 */
public class NoPseudoListen {

	/**
	 * Vereinigt zeilenübergreifende Anweisungen und löscht Leerzeilen
	 * 
	 * 
	 * @param resultbuffer
	 *            der Eingabebuffer
	 */
	public NoPseudoListen(StringBuffer resultbuffer) {
		if (LOGGER)
			logger.debug("Input :"+resultbuffer);

		Matcher m = pseudoListenPattern.matcher(resultbuffer);
		while (m.find()) {
			resultbuffer.replace(m.start(), m.end(), "");
			m.reset();
		}
		
		m = leerBlock.matcher(resultbuffer);
		while (m.find()) {
			resultbuffer.replace(m.start(), m.end(), "}");
			m.reset();
		}
		
		if (LOGGER)
			logger.debug("Output :"+resultbuffer);

	}

	// ---- pseudoListenPattern-RegEx

	/**
	 * RegEx um zeilenübergreifende Anweisungen zu finden und Leerzeilen löschen
	 * zu können
	 */
	private static final Pattern pseudoListenPattern = Pattern.compile("(?<=["
			+ operator + ";=&&[^\\)]]);|;(?=[" + operator + ";=])");

	/** RegEx, um einen leeren Block ;} findet */
	private static final Pattern leerBlock = Pattern.compile(";}");
	
	// ---- Selbstverwaltung --------------------------------------------------

	/**
	 * serialVersionUID<br>
	 * Hint: Maybe <code>VERSION</code> does not exists at this moment
	 */
	@SuppressWarnings("unused")
	private final static long serialVersionUID = VERSION;

	/** logger */
	private final static Logger logger = Logger.getLogger(NoPseudoListen.class
			.getPackage().getName() + ".NoPseudoListen");

}
