/**
 * NoWhiteblancsLFCR.java
 * Branch master
 * APolGe
 * tfossi-team
 * licence GPLv3
 */
package tfossi.apolge.common.scripting.p;

import static tfossi.apolge.common.constants.ConstValue.LFCR;
import static tfossi.apolge.common.constants.ConstValue.LOGGER;
import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

/**
 * Entferne die Whiteblancs (nicht Leerzeichen), tausche LFCR gegen ';', lösche
 * überflüssige (leere) Zeichen, setze führendes ';' ein, ersetzt Leerzeichen in
 * Vektoren und Matrix mit ','.
 * 
 * @author tfossi
 * @version 01.08.2014
 * @modified -
 * @since Java 1.6
 */
public class NoWhiteblancsLFCR {
	/**
	 * Entferne die Whiteblancs (nicht Leerzeichen), tausche LFCR gegen ';',
	 * lösche überflüssige (leere) Zeichen, setze führendes ';' ein, ersetzt
	 * Leerzeichen in Vektoren und Matrix mit ','.
	 * 
	 * @param resultbuffer
	 *            der Eingabebuffer
	 */
	public NoWhiteblancsLFCR(StringBuffer resultbuffer) {

		if (LOGGER)
			logger.debug("Input: " + resultbuffer);

		// LFCR gegen ; tauschen!
		Matcher m = lfcrPattern.matcher(resultbuffer);
		resultbuffer.replace(0, resultbuffer.length(), m.replaceAll(";"));
		if (LOGGER)
			logger.trace("lfcrPattern: " + resultbuffer);

		// Führendes ; einsetzen
		resultbuffer.insert(0, ";");
		if (LOGGER)
			logger.trace("insert 0: " + resultbuffer);

		// Sorgt dafür, dass Vektoranweisungen der Form [A B C D] in [A,B,C,D]
		// überführt werden.
		m = matrixPattern.matcher(resultbuffer);
		while (m.find()) {
			resultbuffer.replace(m.start() + 1, m.end() - 1, ",");
			m.reset();
		}
		if (LOGGER)
			logger.trace("matrixPattern: " + resultbuffer);

		// Blanks löschen
		m = whiteblancs.matcher(resultbuffer);
		while (m.find()) {
			resultbuffer.replace(m.start(), m.end(), "");
			m.reset();
		}
		if (LOGGER)
			logger.trace("whiteblancs: " + resultbuffer);

		// leere Zeichen und doppelte ;; löschen
		m = leereZeile.matcher(resultbuffer);
		while (m.find()) {
			resultbuffer.replace(m.start(), m.end(), ";");
			m.reset();
		}
		if (LOGGER)
			logger.trace("leereZeile: " + resultbuffer);
		
		// Überflüssige ;} löschen
		m = blockEndPattern.matcher(resultbuffer);
		resultbuffer.replace(0, resultbuffer.length(), m.replaceAll("}"));
		if (LOGGER)
			logger.trace("lsP: " + resultbuffer);
		
		// Überflüssige {; löschen
		m = blockStartPattern.matcher(resultbuffer);
		resultbuffer.replace(0, resultbuffer.length(), m.replaceAll("{"));

		if (LOGGER)
			logger.debug("Output: " + resultbuffer);
	}

	// ---- whiteblancs-RegEx

	/**
	 * RegEx, um die Whiteblancs zu entfernen und LFCR gegen ';' zu tauschen
	 * können.
	 */
	private final static Pattern whiteblancs = Pattern.compile("[\\s&&[^"
			+ LFCR + "]]");

	/** RegEx, um Zeilenvorschub zu finden */
	private final static Pattern lfcrPattern = Pattern
			.compile("[" + LFCR + "]");
	
	/** RegEx, um Vektoranweisungen der Form [A B C D] zu finden */
	private final static Pattern matrixPattern = Pattern.compile("([\\p{Alnum}]) +\\p{Alnum}");

	/** RegEx, um leere Zeichen und doppelte ;; zu finden */
	private final static Pattern leereZeile = Pattern.compile("; *;");
	
	/** RegEx, um Blockabschlüsse zu finden */
	private final static Pattern blockEndPattern = Pattern.compile(";}");
	
	/** RegEx, um Blockstart zu finden */
	private final static Pattern blockStartPattern = Pattern.compile("\\{;");
	
	// ---- Selbstverwaltung --------------------------------------------------

	/**
	 * serialVersionUID<br>
	 * Hint: Maybe <code>VERSION</code> does not exists at this moment
	 */
	@SuppressWarnings("unused")
	private final static long serialVersionUID = VERSION;

	/** logger */
	private final static Logger logger = Logger
			.getLogger(NoWhiteblancsLFCR.class.getPackage().getName()
					+ ".NoWhiteblancsLFCR");

}
