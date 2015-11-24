/**
 * NoQuote.java
 * Branch master
 * APolGe
 * tfossi-team
 * licence GPLv3  
 */
package tfossi.apolge.common.scripting.p;

import static tfossi.apolge.common.constants.ConstValue.LFCR;
import static tfossi.apolge.common.constants.ConstValue.LOGGER;
import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

/**
 * Tauscht die Strings durch Platzhalter <code>$[Nummer]$</code> und sichert die
 * Strings in einer Liste.<br>
 * 
 * 
 * @author tfossi
 * @version 24.11.2015
 * @modified -
 * @since Java 1.6
 */
public class NoQuote {

	/**
	 * Tauscht die Strings durch Platzhalter <code>$[Nummer]$</code> und sichert
	 * die Strings in einer Liste.<br>
	 * 
	 * @param resultBuffer
	 *            Der Ein- und Ausgabebuffer
	 * @param quotes
	 *            Die Liste der Strings
	 * 
	 * @modified -
	 */
	public NoQuote(StringBuffer resultBuffer, List<String> quotes) {
		noQuote(resultBuffer, quotes);

	}

	/**
	 * Tauscht die Strings durch Platzhalter <code>$[Nummer]$</code> und sichert
	 * die Strings in einer Liste.<br>
	 * 
	 * @param resultBuffer
	 *            Der Ein- und Ausgabebuffer
	 * @param quotes
	 *            Die Liste der Strings
	 * @return Der AusgabeBuffer ohne Strings und mit Platzhaltern.
	 */
	@SuppressWarnings("static-method")
	private final StringBuffer noQuote(StringBuffer resultBuffer, List<String> quotes) {

		if (LOGGER)
			logger.debug("Strings durch Platzhalter tauschen");


		// Matcher zur Patternmethode (?s),
		Matcher m = bquPattern.matcher(resultBuffer);
		while (m.find()) {

			// Füge der Liste ein neues Element an.
			String s = m.group().startsWith("\"") ? m.group().substring(1) : m
					.group();
			s = s.endsWith("\"") ? s.substring(0, s.length() - 1) : s;
			quotes.add(s);

			s = m.replaceFirst("\\$" + (quotes.size() - 1) + "\\$");

			// Tausche String gegen Platzhalter
			resultBuffer.delete(0, resultBuffer.length()).append(s);

			m.reset();
			
		}

		if (LOGGER)
			logger.debug("Ergebnis:" + LFCR + resultBuffer + LFCR + "Quote(s):" + LFCR
					+ quotes);

		return resultBuffer;
	}

	// ---- noQuote-RegEx

	/**
	 * RegEx, um einen String zu finden und in <code>quotes</code> sichern zu
	 * können.
	 */
	private static final Pattern bquPattern = Pattern.compile("(?ms)\".*?\"");

	// ---- Selbstverwaltung --------------------------------------------------
	/**
	 * serialVersionUID<br>
	 * Hint: Maybe <code>VERSION</code> does not exists at this moment
	 */
	@SuppressWarnings("unused")
	private final static long serialVersionUID = VERSION;

	/** logger */
	private final static Logger logger = Logger.getLogger(NoQuote.class
			.getPackage().getName() + ".NoQuote");

}
