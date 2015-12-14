/**
 * ValueParser.java
 * Branch master
 * APolGe
 * tfossi-team
 * licence GPLv3   
 */
package tfossi.apolge.common.scripting.vp;

import static tfossi.apolge.common.constants.ConstValue.LFCR;
import static tfossi.apolge.common.constants.ConstValue.LOGGER;
import static tfossi.apolge.common.constants.ConstValue.NTAB;
import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;

import java.util.List;

import org.apache.log4j.Logger;

import tfossi.apolge.common.scripting.ScriptException;
import tfossi.apolge.common.scripting.p.ParseException;
import tfossi.apolge.common.scripting.t.Table;

/**
 * Liefert eine Konfigurationsseite.<br>
 * Element Eigenschaften Gültigkeit lokale für Konfiguration Änderbarkeit
 * Konstante/Variable
 * 
 * Wert mit Initiale Belegung Kontinuierliche Berechnung Datentyp Wert
 * 
 * @author tfossi
 * @version 01.08.2014
 * @modified -
 * @since Java 1.6
 */
public class ValueParser {
	{
		if (LOGGER)
			System.out.println(this.getClass().getSimpleName() + " V"
					+ serialVersionUID);
	}

	/** vp_transfer */
	private final VP_Transfer vp_transfer = new VP_Transfer();

	/**
	 * Baut eine Scriptseite zu _ElementBuilder zusammen<br>
	 * Bearbeitet die Value-Tokenliste.<br>
	 * 
	 * @author tfossi
	 * @version 01.08.2014
	 * @param root
	 *            Roottable 
	 * @param quotes
	 *            Liste der Strings, die in $x$ eingesetzt werden muss.
	 * @param mode
	 *            FIXME vollständige Definition steht noch aus PMODE0 0: First
	 *            Pass PMODEGF0 2: #-Pass
	 * @throws ScriptException
	 *             Fehler im Script
	 * @throws ParseException
	 *             Fehler beim Parsen
	 * @modified -
	 */
	public final void valueParser(Table root,
			List<String> quotes, final byte mode) throws ScriptException,
			ParseException {

		if (LOGGER && logger!=null) {
			logger.debug("Parse Values:" + LFCR + root);
		}
		transferValuetokenlines(root, root, quotes, "", mode);
	}

	/**
	 * Zeilenweise die Zuweisungen untersuchen
	 * 
	 * @param root
	 *           Roottable
	 * @param block
	 *            Table die untersucht wird
	 * @param quotes
	 *            Stringliste
	 * @param prename 
	 * 			vorgestellter Name
	 * @param mode
	 *            0,2,3
	 * @throws ScriptException
	 *             Scriptfehler
	 */
	final void transferValuetokenlines(Table root,
			Table block, List<String> quotes, final String prename, final byte mode)
			throws ScriptException {
		// KEY ist schon falsch!

		if (block == null)
			throw new ScriptException("Leere Anweisung!" + LFCR + root + LFCR
					+ block);

		if (LOGGER && logger!=null)
			logger.debug("Transfer from Script to Value " + NTAB + "BLOCK: "
					+ block);

		// Alle Keys durchgehen und parsen
		// Jeder Key repräsentiert eine Eigenschaft des Elements
		for (String key : block.keySet()) {

			if (LOGGER && logger!=null)
				logger.trace("Check all keys. Next: [" + key + "]" + NTAB
						+ "Zuweisung: " + block.get(key));

			// TableMap not VP_Tokenlist
			// Bei TableMap nächste Ebene untersuchen.
			@SuppressWarnings("unchecked")
			VP_Tokenlist<Object> valuetokens = (VP_Tokenlist<Object>) block
					.get(key);

			// Alle Valueeinträge der Zeile in valuetoken parsen
			valuetokens = VP_Parse.parse(this, root, block, valuetokens,
					quotes, key, mode);

			if (LOGGER && logger!=null)
				logger.debug("parse-Ergebnis: " + NTAB + key + "="
						+ valuetokens + " (" + ")");
			
			// Geht in die Berechnung.
			this.vp_transfer.transfer(key, block, valuetokens, quotes, mode);

			if (LOGGER && logger!=null)
				logger.debug("transfer-Ergebnis: " + NTAB + key + "="
						+ valuetokens + " (" + ")");
			// Ergebnis sichern
			block.put(key, valuetokens);
		}
	}

	/**
	 * Quotes wieder einsetzen
	 * 
	 * @param valuetokens
	 *            Tokenliste
	 * @param firstElement
	 *            ????
	 * @param lastElement
	 *            ????
	 * @param quotes
	 *            Liste der Quotes
	 * @modified -
	 */
	private static final void quotesEinsetzen(VP_Tokenlist<Object> valuetokens,
			final int firstElement, final int lastElement, List<String> quotes) {

		quotesEinsetzen(valuetokens,0,valuetokens.size(), quotes);
		
		System.err.println(valuetokens.toString());
		for (int ndx = firstElement; ndx <= lastElement; ndx++) {
			Object actToken = valuetokens.get(ndx);

			System.err.println(actToken);
			if (actToken instanceof String) {

				String aT = (String) actToken;
				if (aT.startsWith("$") && aT.endsWith("$")) {
					int varStart = 1;
					int varEnd = aT.length() - 1;
					int nr = Integer.valueOf(aT.substring(varStart, varEnd))
							.intValue();
					System.err.println(valuetokens.get(ndx));
					valuetokens.remove(ndx);
					valuetokens.add(ndx, quotes.get(nr));
					System.err.println(valuetokens.get(ndx));
				}
			}
		}
	}
	// ---- Selbstverwaltung --------------------------------------------------

	/** serialVersionUID */
	private final static long serialVersionUID = VERSION;
	/** logger */
	final static Logger logger = Logger.getLogger(ValueParser.class
			.getPackage().getName() + ".ValueParser");

	/**
	 * Constructor
	 * @modified -
	 */
	public ValueParser() {
		//
	}
}
