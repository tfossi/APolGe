/**
 * NormalListen.java
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
 * Normalisiere die Listen<br>
 * Listen: lks kein = und kein {};; a,b,c Suche die Zeichen zwischen den
 * Grenzeichen ohne '='
 * <ol>
 * <li>;x; ;..;</li>
 * <li>{x; {..;</li>
 * <li>;x} ;..}</li>
 * </ol>
 * 
 * @author tfossi
 * @version 01.08.2014
 * @modified -
 * @since Java 1.6
 */
public class NormalListen {

	/**
	 * Normalisiere die Listen<br>
	 * Listen: lks kein = und kein {};; a,b,c Suche die Zeichen zwischen den
	 * Grenzeichen ohne '='
	 * <ol>
	 * <li>;x; ;..;</li>
	 * <li>{x; {..;</li>
	 * <li>;x} ;..}</li>
	 * </ol>
	 * 
	 * @param resultbuffer
	 *            der Eingabebuffer
	 */
	public NormalListen(StringBuffer resultbuffer) {
		
		if (LOGGER)
			logger.debug("Normalisiere Listen und setzte virtuelle Zuweisung '?=' ein."
					+ NTAB + resultbuffer);
		
		// Elementzähler ?count
		int count = 0;

		Matcher semik = noListenPattern.matcher(resultbuffer);
		int start = 0;
		while (semik.find(start)) {
			if (equal.matcher(
					resultbuffer.substring(semik.start(), semik.end())).find()) {
				start += resultbuffer.substring(semik.start(), semik.end())
						.length();
				continue;
			}
			
			if (semik.start() + 1 < semik.end()
					|| (semik.start() + 1 == semik.end() && resultbuffer
							.charAt(semik.start()) != ' ')) {
				Matcher mequal = equal.matcher(resultbuffer.substring(
						semik.start(), semik.end()));
				if (!mequal.find()) {
					String was = "?" + (count++) + "=";
					start = semik.start();
					resultbuffer.insert(start + 1, was);
					start += was.length();
					semik.reset();
				}
			} else {

				resultbuffer.deleteCharAt(semik.start());
				start += 2;
			}
		}
	}

	/** Zuweisung '=' */
	private final static Pattern equal = Pattern.compile("\\="); // {1}?");
	/** RegEx, um Listenelement ;x; {x; ;x} {x} zu finden */
	private static final Pattern noListenPattern = Pattern
			.compile("(;|\\{)[^=]*?(;|}){1}?");

	// ---- Selbstverwaltung --------------------------------------------------

	/**
	 * serialVersionUID<br>
	 * Hint: Maybe <code>VERSION</code> does not exists at this moment
	 */
	@SuppressWarnings("unused")
	private final static long serialVersionUID = VERSION;

	/** logger */
	private final static Logger logger = Logger.getLogger(NormalListen.class
			.getPackage().getName() + ".NormalListen");

}
