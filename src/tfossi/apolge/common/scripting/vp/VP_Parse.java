/**
 * VP_Parse.java
 * Branch scripting
 * APolGe
 * tfossi-team
 * licence GPLv3  
 */
package tfossi.apolge.common.scripting.vp;

import static tfossi.apolge.common.constants.ConstValue.LFCR;
import static tfossi.apolge.common.constants.ConstValue.LOGGER;
import static tfossi.apolge.common.constants.ConstValue.LOGTAB;
import static tfossi.apolge.common.constants.ConstValue.NTAB;
import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;

import java.util.List;

import org.apache.log4j.Logger;

import tfossi.apolge.common.constants.ConstValue;
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
	{
		 if (LOGGER)
		 System.out.println(this.getClass().getSimpleName() + " V"
		 + serialVersionUID);
	}

	/**
	 * Parse der Value-Token-Liste.
	 * <ul>
	 * <li>Zerlegung in Blöcke.</li>
	 * <li>Werte werden ermittelt und eingetragen</li>
	 * <li>Operatoren werden ermittelt und eintragen</li>
	 * <li>Klammern () werden ermittelt</li>
	 * </ul>
	 * 
	 * @param prePM
	 *            ???
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
	 *            {@linkplain ConstValue#PMODE0}
	 * @return normierte Tokenliste
	 * @throws ScriptException
	 *             ???
	 * 
	 * @modified -
	 */
	final static VP_Tokenlist<Object> parse(PatternMaps prePM, final ValueParser vp,
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

			// // Test1: Table
			// if (tk instanceof Table) {
			// if (LOGGER)
			// logger.trace("Innerer BLOCK: " + tk);
			// Table subblock = (Table) tk;
			// vp.transferValuetokenlines(prePM, root, subblock, quotes,
			// (prename == null ? "" : prename), mode);
			// continue;
			// }
			//
			// if (tk instanceof String) {
			// String kandidat = (String) tk;
			// String relPath = kandidat.indexOf(".") > 0 ? kandidat
			// .substring(0, kandidat.lastIndexOf(".") + 1) : ".";
			//
			// int size = valuetokens.size();
			// valuetokens = chkIntAdresse(kandidat, relPath, ndx,
			// valuetokens, root, block, null);
			//
			// if (valuetokens.size() != size) {
			// ndx--;
			// continue;
			// }
			// }
			//
			// Test2: Klammer auf (OPEN) und einsetzen
			if (VP_Tests.testOpenNChg(valuetokens, ndx, tk, tkpre)) {
				continue;
			}

			// Test3: Klammer zu (CLOSE) und einsetzen
			if (VP_Tests.testCloseNChg(valuetokens, ndx, tk, tkpre)) {
				continue;
			}

			// Test4: INITIAL und einsetzen
			if (VP_Tests.testInitialNChg(valuetokens, ndx, tk, tkpre)) {
				continue;
			}

			// Test5: FLOW und einsetzen
			if (VP_Tests.testFlowNChg(valuetokens, ndx, tk, tkpre)) {
				continue;
			}
			
			// Test6: OperatorFLOW und einsetzen
			if (VP_Tests.testFlowNChg(valuetokens, ndx, tk, tkpre)) {
				continue;
			}
			
			
			//
			// // Operation ist schon eingetragen
			// if (tk instanceof Operation) {
			// assert false;
			// continue;
			// }
			//
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
			// // // Testergebnis, ob es eine Matrix ist.
			// // if (testMatrix(vp, valuetokens, ndx, tk, tkpre)) {
			// // logger.trace(valuetokens);
			// // continue;
			// // }
			 // Testergebnis, ob vorheriges Zeichen operabel ist.
			 if (VP_Tests.testOperableNChg(vp, valuetokens, ndx, tk, tkpre, mode)) {
			 continue;
			 }
			//
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


			logger.warn("KEINE ÜBERSETZUNG VON: " + tk);
			
		}

		logger.trace("RETURN: " + valuetokens);
		return valuetokens;
	}

	/**
	 * Einsetzen einer internen Adresse im Token-Element, wenn eine gefunden
	 * wird.<br>
	 * Interne Adressen beziehen sich auf Keys im APO-Script.
	 * 
	 * @pre tk im Adress-Format mit String 'A' oder 'C.B.A'
	 * @post tk im Adressformat mit {@link IntAddress}
	 * @inv tk ist keine Adresse, dann erfolgt keine Umstellung. Ist tk dabei im
	 *      Address-Format, erfolgt ein Hinweis.
	 * @see IntAddress
	 * @param kandidat
	 *            token-Element, das überprüft wird.
	 * @param relPath
	 *            relativer Pfad zum Kandidat vom akueller Ebene aus.
	 * @param ndx
	 *            Position von <code>tk</code> in der Liste der VP Tokens
	 * @param valuetokens
	 *            Alle Token einer Valuezuweisung
	 * @param root
	 *            Root(initial) bzw. relative root-Ebene
	 * @param block
	 *            aktueller Block
	 * @param key
	 *            Ist vor dem Keynamen, um die gleichen Keys in
	 *            unterschiedlichen Ebenen abzugrenzen
	 * @return modifizierte Liste der VP Tokens.
	 * @modified -
	 */
	// private VP_Tokenlist chkIntAdresse(String kandidat, String relPath,
	// int ndx, VP_Tokenlist valuetokens, Table root, Table block,
	// final String key) {
	//
	// // Trennt die Pfadelement. Erstes Element = Ebene gesucht
	// int newDot = relPath.indexOf(".");
	//
	// String ebene = newDot > 0 ? relPath.substring(0, newDot) : "";
	//
	// // Nächster Pfad
	// String newPath = newDot > 0 ? relPath.substring(newDot + 1) : ".";
	//
	// String lkey = key == null ? ebene : key + "." + ebene;
	//
	// logger.trace("Key(preName): " + lkey);
	// logger.trace("Kandidat    : " + kandidat);
	// logger.trace("rel. Pfad   : " + relPath);
	// logger.trace("Ebene       : " + ebene);
	// logger.trace("Rest        : " + newPath);
	// logger.trace("Blockkey    : " + block.keySet());
	// logger.trace("Rootkey     : " + root.keySet());
	// logger.trace("Mother: " + valuetokens + LFCR + valuetokens.getMarker());
	//
	// if (block.containsKey(kandidat)) {
	// VP_Tokenlist change = (VP_Tokenlist) block.get(kandidat);
	//
	// // Kandidat ist in Blockebene.
	// // Einträge austauschen!
	// VP_Tokenlist insert = new VP_ArrayTokenlist();
	// insert.add("(");
	//
	// insert.addAll(change);
	// insert.add(")");
	//
	// valuetokens.remove(ndx);
	// valuetokens.addAll(ndx, insert);
	// logger.debug("Xb-Change: " + change.makeString() + LOGTAB
	// + "Marker   : " + change.getMarker());
	// // AUXV < SCON < SVAR < INDI
	//
	// if (!valuetokens.getMarker().equals(change.getMarker())) {
	// if (valuetokens.isAUXVMarker()) {
	// if (change.isSCONMarker())
	// valuetokens.setSCONMarker();
	// if (change.isSVARMarker())
	// valuetokens.setSVARMarker();
	// if (change.isINDIMarker())
	// valuetokens.setINDIMarker();
	// } else if (valuetokens.isSCONMarker()
	// && (change.isSVARMarker() || change.isINDIMarker())) {
	// if (change.isSVARMarker())
	// valuetokens.setSVARMarker();
	// if (change.isINDIMarker())
	// valuetokens.setINDIMarker();
	//
	// } else if (valuetokens.isSVARMarker() && change.isINDIMarker()) {
	// valuetokens.setINDIMarker();
	// }
	// }
	//
	// } else if (root.containsKey(kandidat)) {
	// VP_Tokenlist change = (VP_Tokenlist) root.get(kandidat);
	// // Kandidat ist in Rootebene.
	// // Einträge austauschen!
	// VP_Tokenlist insert = new VP_ArrayTokenlist();
	// insert.add("(");
	// insert.addAll(change);
	// insert.add(")");
	//
	// valuetokens.remove(ndx);
	// valuetokens.addAll(ndx, insert);
	// logger.debug("Xr-Change " + change.makeString() + LOGTAB
	// + "Marker   : " + change.getMarker());
	// // AUXV < SCON < SVAR < INDI
	//
	// if (!valuetokens.getMarker().equals(change.getMarker())) {
	// if (valuetokens.isAUXVMarker()) {
	// if (change.isSCONMarker())
	// valuetokens.setSCONMarker();
	// if (change.isSVARMarker())
	// valuetokens.setSVARMarker();
	// if (change.isINDIMarker())
	// valuetokens.setINDIMarker();
	// } else if (valuetokens.isSCONMarker()
	// && (change.isSVARMarker() || change.isINDIMarker())) {
	// if (change.isSVARMarker())
	// valuetokens.setSVARMarker();
	// if (change.isINDIMarker())
	// valuetokens.setINDIMarker();
	//
	// } else if (valuetokens.isSVARMarker() && change.isINDIMarker()) {
	// valuetokens.setINDIMarker();
	// }
	// }
	// } else if (newDot > 0 && block.containsKey(lkey)) {
	// VP_Tokenlist nextEbene = (VP_Tokenlist) block.get(lkey);
	// // Key entspricht nächster Blockebene
	// // In nächste Blockebene gehen
	//
	// return chkIntAdresse(kandidat, newPath, ndx, valuetokens,
	// nextEbene.getTable(), block, lkey);
	//
	// } else if (newDot > 0 && root.containsKey(lkey)) {
	// VP_Tokenlist nextEbene = (VP_Tokenlist) root.get(lkey);
	// // Key entspricht nächster Rootebene
	// // In nächste Rootebene gehen
	//
	// return chkIntAdresse(kandidat, newPath, ndx, valuetokens,
	// nextEbene.getTable(), block, lkey);
	//
	// } else {
	// // Kandidat oder Pfad weder in Block noch in Root;
	// logger.debug("No X-Change in [" + kandidat + "]");
	// }
	//
	// return valuetokens;
	// }

	// ---- Selbstverwaltung --------------------------------------------------
	/** serialVersionUID */
	@SuppressWarnings("unused")
	private final static long serialVersionUID = VERSION;

	/** logger */
	private final static Logger logger = Logger.getLogger(VP_Parse.class
			.getPackage().getName() + ".VP_Parse");
}
