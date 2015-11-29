/**
 * NoComment.java
 * Branch master
 * APolGe
 * tfossi-team
 * licence GPLv3  
 */
package tfossi.apolge.common.scripting.p;

import static tfossi.apolge.common.constants.ConstValue.LFCR;
import static tfossi.apolge.common.constants.ConstValue.LOGGER;
import static tfossi.apolge.common.constants.ConstValue.LOGTAB;
import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;


/**
 * Liest den Eingabestrom aus, entfernt die Kommentare und liefert einen um die
 * Kommentare bereinigten StringBuffer.<br>
 * Falls erforderlich, wird ein vorbelegter StringBuffer zuerst ausgelesen.
 * 
 * 
 * @author tfossi
 * @version 24.11.2015
 * @modified -
 * @since Java 1.6
 */
public class NoComment {
	/** Kennzeichnet einen Kommentarbereich */
	private boolean blockComment = false;

	/**
	 * Liest den Eingabestrom aus, entfernt die Kommentare und liefert einen um
	 * die Kommentare bereinigten StringBuffer.<br>
	 * Falls erforderlich, wird ein vorbelegter StringBuffer zuerst ausgelesen.
	 * 
	 * @param din
	 *            Eingabestrom
	 * @param in2
	 *            Vorbelegung
	 * @param resultBuffer
	 *            Ergebnisbuffer
	 * 
	 * @throws ParseException
	 *             Fehlerexception beim Parsen mit Hinweis zum Grund der
	 *             Exception
	 * @modified -
	 */
	public NoComment(final BufferedReader din, final StringBuffer in2,
			final StringBuffer resultBuffer) throws ParseException {
		noComment(din, in2, resultBuffer);

	}

	/**
	 * Liest den Eingabestrom aus, entfernt die Kommentare und liefert einen um
	 * die Kommentare bereinigten StringBuffer.<br>
	 * Falls erforderlich, wird ein vorbelegter StringBuffer zuerst ausgelesen.
	 * 
	 * @param din
	 *            Eingabestrom
	 * @param in2
	 *            Vorbelegung
	 * @param resultBuffer
	 *            Ergebnisbuffer
	 * @return Ergebnisbuffer für rekursive Abschnitte
	 * @throws ParseException
	 *             Fehlerexception beim Parsen mit Hinweis zum Grund der
	 *             Exception
	 * @modified -
	 */
	private final StringBuffer noComment(final BufferedReader din,
			final StringBuffer in2, final StringBuffer resultBuffer)
			throws ParseException {

		if (LOGGER)
			logger.debug("Initiales Einlesen des Buffers, " + LOGTAB
					+ "Abschalten des Config-Loggings" + LOGTAB
					+ "Zeilen- und Blockkommentare entfernen");

		// Um Kommentare bereinigter Rückgabebuffer

		// Vorbelegung der Line prüfen und ggfs. eintragen <code>!=null</code>
		String preline = (in2 == null ? null : in2.toString());

		// RegEx-Matcher zur Patternmethode
		Matcher m = null;

		// Aktuelle Zeile, die eingelesen wird
		String line = null;

		// Einleseschleife
		while (true) {
			try {
				if (preline != null) {
					// Liegt eine Vorbelegung vor, ist diese zuerst zu
					// bearbeiten
					line = new String(preline);
					preline = null;
				} else {
					// Neue Zeile einlesen
					if ((line = din.readLine()) == null) {
						// Ende des Files erreicht. Liefert Ergebnis zurück.
						if (LOGGER)
							logger.debug("Ergebnis:" + LFCR + resultBuffer);

						return resultBuffer.append(";");
					}
					
					// Zeilenendezeichen wird nicht mit eingelesen, daher hier
					// nachgetragen
					line += LFCR;
				}

				// Beginn Blockkommentar
				m = startCommentPattern.matcher(line);
				if (m.find()) {
					this.blockComment = true;
					// Was vor dem Kommentarbeginn steht ist zuberücksichtigen
					preline = line.substring(0, m.start());
					line = line.substring(m.end());
				}

				// Ende Blockkommentar
				m = endCommentPattern.matcher(line);
				if (m.find()) {
					this.blockComment = false;
					// Was nach dem Kommentarende steht ist zuberücksichtigen
					if (preline == null)
						preline = line.substring(m.end());
					else
						// In Preline steht schon was.
						preline += line.substring(m.end());
					continue;
				} else if (this.blockComment && preline != null) {
					// Preline ist gefüllt, aber Kommentar läuft noch.
					if (!preline.endsWith(LFCR))
						// Endezeichen nachliefern.
						preline += LFCR;
					resultBuffer.append(preline);
					preline = null;
				}

				// Mitten im Kommentar. Keine Auswertung erforderlich.
				if (this.blockComment)
					continue;

				// Zeilenkommentar.
				m = singleLineCommentPattern.matcher(line);
				if (m.find()) {
					preline = line.substring(0, m.start()) + LFCR;
					continue;
				}

				// Keine Kommentare. Einfach dranhängen.
				resultBuffer.append(line);

			} catch (IOException e) {
				throw new ParseException(
						"Fehler im Einlesen des StringBuffers!" + LFCR
								+ e.getMessage());
			}
		}
	}

	// ---- noComment-RegEx

	/** RegEx um ein Zeilenkommentar zu finden. */
	private static final Pattern singleLineCommentPattern = Pattern
			.compile("/{2}.*");

	/**
	 * RegEx um den Beginn eines Blockkommentars
	 * 
	 * <pre>/* zu finden.
	 */
	private static final Pattern startCommentPattern = Pattern
			.compile("\\/\\*");

	/** RegEx um das Ende eines Blockkommentars zu finden. */
	private static final Pattern endCommentPattern = Pattern.compile("\\*\\/");

	// ---- Selbstverwaltung --------------------------------------------------
	/**
	 * serialVersionUID<br>
	 * Hint: Maybe <code>VERSION</code> does not exists at this moment
	 */
	@SuppressWarnings("unused")
	private final static long serialVersionUID = VERSION;

	/** logger */
	private final static Logger logger = Logger.getLogger(NoComment.class
			.getPackage().getName() + ".NoComment");

}
