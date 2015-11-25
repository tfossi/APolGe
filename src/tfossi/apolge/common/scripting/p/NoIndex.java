/**
 * NoIndex.java
 * Branch master
 * APolGe
 * tfossi-team
 * licence GPLv3
 */
package tfossi.apolge.common.scripting.p;

import static tfossi.apolge.common.constants.ConstValue.LOGGER;
import static tfossi.apolge.common.constants.ConstValue.NTAB;
import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

/**
 * Suche Index [...] und löse den Index auf. <br>
 * <i>a[i] = v </i> nach <i>a = { i = v }</i>
 * 
 * TODO Index in Zuweisung a = b[Flow] 
 * 
 * @author tfossi
 * @version 01.08.2014
 * @modified -
 * @since Java 1.6
 * 
 * 
 */
public class NoIndex {

	/**
	 * Suche Index [...] und löse den Index auf. <br>
	 * <i>a[i] = v </i> nach <i>a = { i = v }</i>
	 * 
	 * @param resultbuffer
	 *            der Eingabebuffer
	 */
	public NoIndex(StringBuffer resultbuffer) {
		if (LOGGER)
			logger.debug("Input: " + NTAB + resultbuffer);

		Matcher mindex = indexPattern.matcher(resultbuffer);

		while (mindex.find()) {
			// Es wird gesucht:
			// a[b] = c ==> a = { a.b = c }

			if (LOGGER)
				logger.trace("loop      : " + NTAB+resultbuffer);
			// Key finden
			Matcher mkey = keyPattern.matcher(resultbuffer);

			if (mkey.find()) {
				if (LOGGER)
					logger.trace("key      : " + mkey.group());

				if (LOGGER)
					logger.trace("index    : " + mindex.group());

				// Zuweisung finden
				Matcher mequ = equPattern.matcher(resultbuffer);
				mequ.find();
				if (LOGGER)
					logger.trace("Zuweisung: " + mequ.group());

				// Abschlußklammer }
				resultbuffer.insert(mequ.end() - 1, "}");

				// Zuweisung =
				resultbuffer.insert(mequ.start() - 1, "={" + mindex.group());

				// Index löschen
				resultbuffer.delete(mindex.start() - 1, +mindex.end() + 1);

			} else {
			assert false;	
			}
		}

		if (LOGGER)
			logger.debug("Output: " + NTAB + resultbuffer);

	}

	/** keyPattern */
	private final static Pattern keyPattern = Pattern
			.compile("(?<=;)\\p{Alnum}+(?=(\\[.*?\\]=))");
	/** indexPattern */
	private final static Pattern indexPattern = Pattern
			.compile("(?<=(\\[))\\p{Alnum}+(?=(\\])){1}?");
	/** equPattern */
	private final static Pattern equPattern = Pattern
			.compile("(?<=(\\]=)).*?(;|}){1}?");

	// ---- Selbstverwaltung --------------------------------------------------

	/**
	 * serialVersionUID<br>
	 * Hint: Maybe <code>VERSION</code> does not exists at this moment
	 */
	@SuppressWarnings("unused")
	private final static long serialVersionUID = VERSION;

	/** logger */
	private final static Logger logger = Logger.getLogger(NoIndex.class
			.getPackage().getName() + ".NoIndex");

}
