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
import tfossi.apolge.common.scripting.vp.pm.PatternMaps;
import tfossi.apolge.data.core._ElementBuilder;

/**
 * Liefert eine Konfigurationsseite.<br>
 * Element
 * Eigenschaften
 * Gültigkeit
 * lokale für Konfiguration
 * Änderbarkeit
 * Konstante/Variable
 * 
 * Wert mit
 * Initiale Belegung
 * Kontinuierliche Berechnung
 * Datentyp Wert 
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
	 * @param prePM ???
	 * @param root ???
	 * 
	 * @param block
	 *            unbearbeitete Tabelle mit den Tokenlisten {key=[t1,t2,...]}
	 * @param quotes
	 *            Liste der Strings, die in $x$ eingesetzt werden muss.
	 * @param prename
	 *            DOC
	 * @param mode
	 *            FIXME vollständige Definition steht noch aus PMODE0 0: First
	 *            Pass PMODEGF0 2: #-Pass
	 * @return PatternMap des Scripts mit den Ergebnissen des mode
	 * @throws ScriptException
	 *             Fehler im Script
	 * @throws ParseException ???	 
	 * @modified - 
	 */
	public final _ElementBuilder valueParser(PatternMaps prePM, Table root,
			Table block, List<String> quotes, String prename, final byte mode)
			throws ScriptException, ParseException {

		_ElementBuilder eb = new _ElementBuilder();
		
		if (LOGGER) {
			logger.debug("Parse Values:"+LFCR + block);
		}
 
		transferValuetokenlines(eb, prePM, root, block, quotes,
				prename, mode);
	
		return eb;
	}

	/**
	 * DOC Valueparsing
	 * @param eb TODO
	 * @param prePM  ????
	 * @param root  ????
	 * 
	 * @param block
	 *            Table die untersucht wird
	 * @param quotes
	 *            Stringliste
	 * @param prename
	 *            Ist vor dem Keynamen, um die gleichen Keys in
	 *            unterschiedlichen Ebenen abzugrenzen
	 * @param mode
	 *            0,2,3
	 * @throws ScriptException ????
	 */
	private final void transferValuetokenlines(_ElementBuilder eb, PatternMaps prePM, Table root,
			Table block, List<String> quotes, String prename, final byte mode)
			throws ScriptException {
		// KEY ist schon falsch!

		if (block == null)
			throw new ScriptException("Leere Anweisung!" + LFCR + root + LFCR
					+ block);

		if (LOGGER)
			logger.debug("Transfer from Script to Value " + NTAB + "BLOCK: "
					+ block + NTAB + "Prename: " + prename);

		// Alle Keys durchgehen und parsen
		// Jeder Key repräsentiert eine Eigenschaft des Elements
		for (String key : block.keySet()) {

			if (LOGGER)
				logger.trace("  Check all keys. Next: [" + key + "]"+NTAB+"Zuweisung: "+block.get(key));

			// Bei TableMap nächste Ebene untersuchen.
			@SuppressWarnings("unchecked")
			VP_Tokenlist<Object> valuetokens = (VP_Tokenlist<Object>) block.get(key);
			

			// Alle Valueeinträge der Zeile in valuetoken parsen
			valuetokens = VP_Parse.parse(prePM, this, root, block,
					valuetokens, quotes, key, mode);
								
			if (LOGGER)
				logger.debug("parse-Ergebnis: "+NTAB+key +"="+ valuetokens+ " ("+")");

			// Geht in die Berechnung.
			this.vp_transfer.transfer(key, block, valuetokens, quotes, mode);
			
			// Ergebnis sichern	
			block.put(key, valuetokens);
			eb.addEigenschaften(key, valuetokens);
		}
	}
	
	// ---- Selbstverwaltung --------------------------------------------------

	/** serialVersionUID */
	private final static long serialVersionUID = VERSION;
	/** logger */
	final static Logger logger = Logger.getLogger(ValueParser.class
			.getPackage().getName() + ".ValueParser");

	/**
	 * TODO Comment
	 * 
	 * @modified -
	 */
	public ValueParser() {
		//
	}
}
