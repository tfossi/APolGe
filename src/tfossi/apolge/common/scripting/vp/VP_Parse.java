/**
 * VP_Parse.java
 * Branch master
 * APolGe
 * tfossi-team
 * licence GPLv3  
 */
package tfossi.apolge.common.scripting.vp;

import static tfossi.apolge.common.constants.ConstValue.LOGGER;
import static tfossi.apolge.common.constants.ConstValue.LOGTAB;
import static tfossi.apolge.common.constants.ConstValue.NTAB;
import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;

import java.util.List;

import org.apache.log4j.Logger;

import tfossi.apolge.common.scripting.ScriptException;
import tfossi.apolge.common.scripting.t.Table;

/**
 * Übersetzt alle Einträge in der Tokenliste von Text(String) auf echtes
 * Datenformat, Methode, usw.
 * 
 * 
 * @author tfossi
 * @version 21.08.2014
 * @modified -
 * @since Java 1.6
 */
public class VP_Parse {
//	{
//		 if (LOGGER)
//		 System.out.println(this.getClass().getSimpleName() + " V"
//		 + serialVersionUID);
//	}

	/**
	 * Parse der Value-Token-Liste.
	 * <ul>
	 * <li>Zerlegung in Blöcke.</li>
	 * <li>Werte werden ermittelt und eingetragen</li>
	 * <li>Operatoren werden ermittelt und eintragen</li>
	 * <li>Klammern () werden ermittelt</li>
	 * </ul>
	 * 
	 * @param vp
	 *            ???
	 * @param root
	 *            ???
	 * 
	 * @param block
	 *            aktueller Block
	 * @param valuetokens
	 *            Alle Token einer Valuezuweisung
	 * @param quotes
	 *            Liste der Strings, die in $x$ eingesetzt werden muss.
	 * @param prename
	 *            Ist Vor dem Keynamen, um die gleichen Keys in
	 *            unterschiedlichen Ebenen abzugrenzen
	 * @param mode
	 *            0,2,3 für erster, zweiter und dritter Pass
	 * @return normierte Tokenliste
	 * @throws ScriptException
	 *             ???
	 * 
	 * @modified -
	 */
	final static VP_Tokenlist<Object> parse(final ValueParser vp,
			final Table root, final Table block, VP_Tokenlist<Object> valuetokens,
			List<String> quotes, String prename, final byte mode)
			throws ScriptException {

		if (LOGGER)
			logger.info("Übersetze die Textzeichen der Zuweisungszeile" + NTAB + valuetokens+LOGTAB+"für ["+prename+"=] in Werte und Methoden");
		
		assert prename == null || !prename.startsWith(null + ".") : prename;
		if (valuetokens == null)
			return valuetokens;

		/** Zu untersuchender Token */
		Object tk = null;

		/** vorheriger Token */
		Object tkpre = null;

		@SuppressWarnings("unused")
		String newPrename = "";

		// Jeden Eintrag durchgehen und Einordnen
		// BLÖCKE: Tables
		// OPERATIONEN: +-*/&| --> Operationsmethode eintragen

		for (int ndx = 0; ndx < valuetokens.size(); ndx++) {
			tk = valuetokens.get(ndx);

			tkpre = ndx > 0 ? valuetokens.get(ndx - 1) : null;

			if (LOGGER)
				logger.debug("Parse Eintragstoken " + NTAB + "[" + tk
						+ "] (ndx #" + ndx + ") in " + valuetokens);
			
			// Test1: Table
			if (tk instanceof Table) {
				if (LOGGER)
					logger.trace("Innerer BLOCK: " + tk);
				Table subblock = (Table) tk;
				vp.transferValuetokenlines( root, subblock, quotes,
						(prename == null ? "" : prename), mode);
							continue;
			}
			
			// Test2: Klammer auf (OPEN) und einsetzen
			if (VP_Tests.testOpenNChg(valuetokens, ndx, tk, tkpre)) {
				continue;
			}

			// Test3: Klammer zu (CLOSE) und einsetzen
			if (VP_Tests.testCloseNChg(valuetokens, ndx, tk, tkpre)) {
				continue;
			}
					
			// Testen und bei positivem Befunde in Number wandeln
			if (VP_Tests.testNumberNChg(valuetokens, ndx, tk, tkpre)) {
				logger.trace(valuetokens);
				continue;
			}

			// Testen und bei positivem Befunde in Boolean wandeln
			if (VP_Tests.testConstantsNChg(vp, valuetokens, ndx, tk, tkpre)) {
				logger.trace(valuetokens);
				continue;
			}
			
			 // Testergebnis, ob vorheriges Zeichen operabel ist.
			 if (VP_Tests.testOperableNChg(vp, valuetokens, ndx, tk, tkpre, mode)) {
			 continue;
			 }
			
			// Testergebnis, ob es eine Funktion ist. Wenn ja, setze alle
			// Lösungen ein
			if (VP_Tests.testFunktional(vp, valuetokens, ndx, tk, tkpre)) {
				continue;
			}
			
			 if (VP_Tests.testKommaNChg(valuetokens, ndx, tk, tkpre)) {
			 continue;
			 }
			 
			 if (VP_Tests.testQuote(valuetokens, ndx, tk, tkpre)) {
			 continue;
			 }

			logger.info("KEINE ÜBERSETZUNG VON: [" + tk+"]. Variable?");			
		}

		logger.trace("RETURN: " + valuetokens);
		return valuetokens;
	}

	// ---- Selbstverwaltung --------------------------------------------------
	/** serialVersionUID */
	private final static long serialVersionUID = VERSION;

	/** logger */
	private final static Logger logger = Logger.getLogger(VP_Parse.class
			.getPackage().getName() + ".VP_Parse");
}
