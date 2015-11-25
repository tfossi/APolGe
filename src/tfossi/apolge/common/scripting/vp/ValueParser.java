/**
 * ValueParser.java
 * Branch scripting
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

import tfossi.apolge.common.constants.ConstValue;
import tfossi.apolge.common.scripting.ScriptException;
import tfossi.apolge.common.scripting.p.ParseException;
import tfossi.apolge.common.scripting.t.Table;
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

	/** Muster für Standardberechnungen: Mathematische Methoden, APO-Methoden, Operatoren */
	final PatternMaps patternmaps = new PatternMaps();
	
	/** vp_parse */
	private final VP_Parse vp_parse = new VP_Parse();
	
	/** vp_transfer */
	private final VP_Transfer vp_transfer = new VP_Transfer();
	
//	/** vp_rootblock */
//	@SuppressWarnings("unused")
//	private Table vp_rootblock;
//
//	/** STEUERVARIABLE */
//	@SuppressWarnings("unused")
//	private final static List<String> STEUERVARIABLE = Arrays.asList("IGL",
//			"TERMIN", "CONTEXT", "TRANSITION");

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
		
		// // Steuerkennzeichen vorbelegen! Falsch. Führt zu Dateienhaufen.
		// if ((mode == PMODE0)) {
		// StringBuffer doString = new StringBuffer();
		// // Einlesen der Steuervariablen
		// for (String s : STEUERVARIABLE) {
		// // Nur vorbelegen, wenn Steuervariable noch nicht existiert!
		// if (!block.containsKey(s))
		// doString.append("!" + s + "={ }");
		// }
		// Table table = new Parser().readBlock(new BufferedReader(
		// new StringReader(doString.toString())), null, quotes);
		//
		// for (Object o : table.values()) {
		// VP_Tokenlist v = (VP_Tokenlist) o;
		// v.setSCONMarker();
		// }
		//
		// block.putAll(table);
		// }

		if (LOGGER) {
			logger.debug("Parse Values:"+LFCR + block);
		}

//		this.vp_rootblock = block;

//		PatternMaps pm = 
		transferValuetokenlines(eb, prePM, root, block, quotes,
				prename, mode);
		
		// assert false:block.toString()+LFCR+pm.toString();
		// Leeren der Tabellen. Inhalte werden nicht mehr gebraucht! Übrig sind
		// flache
		// Daten.
		// String[] pmKeys = null;
		// pmKeys = pm.indi.keySet().toArray(new String[0]);
		// for (String key : pmKeys) {
		// if (pm.indi.get(key).isTable())
		// pm.indi.clear(); //.remove(key);
		//
		// }
		// pmKeys = pm.svar.keySet().toArray(new String[0]);
		// for (String key : pmKeys) {
		// if (pm.svar.get(key).isTable())
		// pm.svar.clear(); //.remove(key);
		// }
		// pmKeys = pm.scon.keySet().toArray(new String[0]);
		// for (String key : pmKeys) {
		// if (pm.scon.get(key).isTable())
		// pm.scon.clear(); //.remove(key);
		// }
		// pmKeys = pm.auxvar.keySet().toArray(new String[0]);
		// for (String key : pmKeys) {
		// if (pm.auxvar.get(key).isTable())
		// pm.auxvar.clear(); //.remove(key);
		// }
//		if (LOGGER)
//			logger.debug("ABSCHLUSS" + LFCR + "Block: " + block + LFCR
//					+ "Quotes: " + quotes + LFCR + "Prename: " + prename + LFCR
//					+ "Mode: " + (mode == PMODEFLAT ? "PMODEFLAT" : new Byte(mode))
//					+ LFCR + "CONSTANTS: "
//					+ PatternMaps.finalConstants.entrySet() + LFCR
//					+ "CONSTVALUE: " + this.patternmaps.constantsPattern + LFCR
//					+ "SCON: " + this.patternmaps.scon.entrySet() + LFCR
//					+ "SCONPATTERN: " + this.patternmaps.sconPattern + LFCR
//					+ "SVAR: " + this.patternmaps.svar.entrySet() + LFCR
//					+ "SVARPATTERN: " + this.patternmaps.svarPattern + LFCR
//					+ "INDI: " + this.patternmaps.indi.entrySet() + LFCR
//					+ "INDIPATTERN: " + this.patternmaps.indiPattern + LFCR
//					+ "AUXVAR: " + this.patternmaps.auxv.entrySet() + LFCR
//					+ "AUXVARPATTERN: " + this.patternmaps.auxvPattern + LFCR
//					+ "ADDRESSES: " + this.patternmaps.addresses.entrySet()
//					+ LFCR + "ADDRESESVALUE: "
//					+ this.patternmaps.addressesValue
//					);
//		return pm;

		return eb;
	}

	/**
	 * DOC Valueparsing
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
	 *            {@linkplain ConstValue#PMODE0 } FIXME vollständige Definition
	 *            steht noch aus PatternMapsODE0 0: First Pass PatternMapsODEGF0
	 *            2: #-Pass
	 * @return ????
	 * @throws ScriptException ????
	 */
	private final void transferValuetokenlines(_ElementBuilder eb, PatternMaps prePM, Table root,
			Table block, List<String> quotes, String prename, final byte mode)
			throws ScriptException {
		// KEY ist schon falsch!

		if (block == null)
			throw new ScriptException("Leere Anweisung!" + LFCR + root + LFCR
					+ block);
//		assert block != null : "BLOCK IST NULL";
//
		if (LOGGER)
			logger.debug("Transfer from Script to Value " + NTAB + "BLOCK: "
					+ block + NTAB + "Prename: " + prename);
//		assert prename == null || !prename.endsWith("..") : prename;
//
//		// Kniff, um ConstValue nicht zu loggen
//		Level lev = logger.getLevel();
//
////		if (mode == PMODEGF) {
////			// Hilfsvariablen sind streng lokal, deswegen die Alten im neuen
////			// Lauf löschen
////			this.patternmaps.auxvPattern = Pattern.compile("");
////			this.patternmaps.auxv.clear();
////		}

		// Alle Keys durchgehen und parsen
		// Jeder Key repräsentiert eine Eigenschaft des Elements
		for (String key : block.keySet()) {

//			// ConstValue wird nicht geloggt!
//			if (key.equals("VERSION")) {
//				logger.warn("  Schalte Logger für Const... aus!");
//				logger.setLevel(Level.OFF);
//			}
			if (LOGGER)
				logger.trace("  Check all keys. Next: [" + key + "]"+NTAB+"Zuweisung: "+block.get(key));

			
//			assert block.get(key) instanceof String:block.get(key).getClass();
			// Bei TableMap nächste Ebene untersuchen.
			@SuppressWarnings("unchecked")
			VP_Tokenlist<Object> valuetokens = (VP_Tokenlist<Object>) block.get(key);
			
			markerKey();
			

			// Alle Valueeinträge der Zeile in valuetoken parsen
			valuetokens = VP_Parse.parse(prePM, this, root, block,
					valuetokens, quotes, key, mode);
					
//			eb.addEigenschaften(key, valuetokens);
			
			if (LOGGER)
				logger.debug("parse-Ergebnis: "+NTAB+key +"="+ valuetokens+ " ("
//			+valuetokens.getMarker()
			+")");

			// Geht in die Berechnung.
//			if (
			this.vp_transfer.transfer(key, block, valuetokens, quotes, mode);
			// Ergebnis sichern	
			block.put(key, valuetokens);
			eb.addEigenschaften(key, valuetokens);
//					) {
//				valuetokens.setComplete();
//			}
//			if (LOGGER)
//				logger.debug("transfer-Result Block=ValueToken (Marker): "+NTAB+key +"="+ valuetokens+ " ("+valuetokens.getMarker()+")");

		}

//		// assert false:block.toString()+LFCR+this.patternmaps.toString();
//		// Am Ende Rückgängig machen: ConstValue wird nicht geloggt!
//		if (logger != null && logger.getLevel() != null
//				&& !(logger.getLevel().equals(lev))) {
//			logger.setLevel(lev);
//			logger.warn("Schalte Logger nach Const... wieder zurück!");
//		}
//
//		return this.patternmaps;
//		return null;
	}
	/**
	 * TODO Comment
	 * @modified - 
	 */
	private void markerKey(){
//		assert valuetokens != null;
//
//		// Markiere KEY
//		switch (mode) {
//		case PMODEFLAT:
//			// Bei flachen Scripten, wie Config, gibt es nur keine
//			// Unterschiede mit Markern!
//			setSCON(valuetokens, key);
//			break;
//		case PMODE0:
//
//			for (Object o : valuetokens) {
//				if (!(o instanceof TableMap)
//						&& ((String) o).startsWith("@")) {
//					valuetokens.setAddressesMarker();
//					break;
//				}
//			}
//
//			// § Überprüfe, ob eine Variable schon einmal definiert wurde.
//			// Dann abbrechen!
//			if (this.patternmaps.auxvPattern.matcher(key).matches()
//					|| this.patternmaps.sconPattern.matcher(key).matches()
//					|| this.patternmaps.indiPattern.matcher(key).matches()
//					|| this.patternmaps.svarPattern.matcher(key).matches()) {
//				throw new ScriptException("PMODE0: Key [" + key + "="
//						+ valuetokens + "] ist mehrfach angelegt!");
//			}
//
//			// Kennzeichen des Variablentyps durch Eintrag in die Kategorie
//			if (valuetokens.isINDIMarker()) {
//				setINDI(valuetokens, key, mode);
//			} else if (valuetokens.isSCONMarker()) {
//				setSCON(valuetokens, key);
//			} else if (valuetokens.isSVARMarker()) {
//				setSVAR(valuetokens, key, mode);
//			} else if (valuetokens.isAUXVMarker()) {
//				setAUXV(valuetokens, key);
//			} else {
//				assert false;
//			}
//
//			// if(valuetokens.isTable()){
//			//
//			// Table subTable = valuetokens.getTable();
//			// // final PatternMaps transferValuetokenlines(PatternMaps
//			// prePM, Table root,
//			// // Table block, List<String> quotes, String prename, final
//			// byte mode)
//			// // throws ScriptException {
//			// final PatternMaps transferValuetokenlines(PatternMaps prePM,
//			// Table root,
//			// Table block, quotes, String prename, final byte mode)
//			// // throws ScriptException {
//			// }
//			// assert
//			// false:valuetokens.toString()+LFCR+valuetokens.getMarker();
//			break;
//		case PMODEGF:
//
//			for (Object o : valuetokens) {
//				if (!(o instanceof TableMap)
//						&& ((String) o).startsWith("@")) {
//					valuetokens.setAddressesMarker();
//					break;
//				}
//			}
//
//			if (valuetokens.isINDIMarker()) {
//				// § Key ist in INDI abgelegt und typenfremd
//				if (this.patternmaps.auxvPattern.matcher(key).matches()
//						|| this.patternmaps.sconPattern.matcher(key)
//								.matches()
//						|| this.patternmaps.svarPattern.matcher(key)
//								.matches()) {
//					throw new ScriptException("PMODEGF: Key [" + key + "="
//							+ valuetokens
//							+ "] ist schon als INDI angelegt!");
//				}
//				if (this.patternmaps.indiPattern.matcher(key).matches()) {
//					// § Key ist in INDI abgelegt und wird überschrieben
//					logger.info("PMODEGF: " + this.patternmaps.indi
//							+ LOGTAB + "wird überschrieben mit" + LOGTAB
//							+ key + "=" + valuetokens + "!");
//				}
//				// § Key ist neu
//				setINDI(valuetokens, key, mode);
//			}
//			if (valuetokens.isSVARMarker()) {
//				// § Key ist in SVAR abgelegt und typenfremd
//				if (this.patternmaps.auxvPattern.matcher(key).matches()
//						|| this.patternmaps.sconPattern.matcher(key)
//								.matches()
//						|| this.patternmaps.indiPattern.matcher(key)
//								.matches()) {
//					throw new ScriptException("PMODEGF: Key [" + key + "="
//							+ valuetokens
//							+ "] ist schon als SVAR angelegt!");
//				}
//				if (this.patternmaps.svarPattern.matcher(key).matches()) {
//					// § Key ist in SVAR abgelegt und wird überschrieben
//					logger.info("PMODEGF: " + this.patternmaps.svar
//							+ LOGTAB + "wird überschrieben mit" + LOGTAB
//							+ key + "=" + valuetokens + "!");
//				}
//				// § Key ist neu
//				setSVAR(valuetokens, key, mode);
//			}
//			if (valuetokens.isSCONMarker()) {
//				// § Key ist in SCON abgelegt! Das geht nicht!
//				if (this.patternmaps.auxvPattern.matcher(key).matches()
//						|| this.patternmaps.svarPattern.matcher(key)
//								.matches()
//						|| this.patternmaps.indiPattern.matcher(key)
//								.matches()
//						|| this.patternmaps.sconPattern.matcher(key)
//								.matches()) {
//					throw new ScriptException("PMODEGF: Key [" + key + "="
//							+ valuetokens
//							+ "] ist schon als SCON angelegt!");
//				}
//				// § Key ist neu
//				setSCON(valuetokens, key);
//			}
//			if (valuetokens.isAUXVMarker()) {
//				// § Key ist in AUXV abgelegt! Das geht nicht!
//				if (this.patternmaps.svarPattern.matcher(key).matches()
//						|| this.patternmaps.indiPattern.matcher(key)
//								.matches()
//						|| this.patternmaps.sconPattern.matcher(key)
//								.matches()) {
//					throw new ScriptException("PMODEGF: Key [" + key + "="
//							+ valuetokens
//							+ "] ist schon als AUXV angelegt!");
//				}
//				// § Key ist neu
//				setAUXV(valuetokens, key);
//			}
//			break;
//		default:
//			break;
//		}
//
		}
	
	
	/**
	 * Legt DEFINITIONS an, wenn noch nicht angelegt ist
	 * 
	 * @author tfossi
	 * @version 01.08.2014
	 * @modified -
	 * @since Java 1.6
	 * 
	 * @param tokenliste
	 *            Liste der Token
	 * @param key
	 *            Schlüsselwort
	 * @param mode ???
	 * @return INDIVIDUALS ist angelegt?
	 * @throws ScriptException ???
	 */
	private final boolean setSVAR(final VP_Tokenlist tokenliste,
			final String key, int mode) throws ScriptException {
//		// Key gibt es noch nicht, wird neu eingetragen
//		if (!this.patternmaps.svarPattern.matcher(key).matches()) {
//			if (LOGGER)
//				logger.trace("[" + key + "] wird in SVAR angelegt.");
//			String r = (this.patternmaps.svarPattern.pattern().isEmpty() ? "("
//					+ key + ")" : this.patternmaps.svarPattern.pattern() + "|("
//					+ key + ")");
//			this.patternmaps.svarPattern = Pattern.compile(r);
//		} else {
//			logger.fatal("FATALER FEHLER!" + LOGTAB + "[" + key
//					+ "] ist in SVAR schon angelegt." + LFCR
//					+ this.patternmaps.sconPattern.toString());
//			throw new ScriptException("[" + key + "] ist schon angelegt!");
//		}
//		// Wert eintragen/überschreiben
//		this.patternmaps.svar.put(key, tokenliste);
		return true;
	}

	/**
	 * INDI-Key und Value in Pattern und pattern.indi eintragen
	 * 
	 * @param tokenliste
	 *            Liste der Werte
	 * @param key
	 *            Key
	 * @param mode ???
	 * @return TODO fix true
	 * @throws ScriptException ???
	 * @modified -
	 */
	private final boolean setINDI(final VP_Tokenlist tokenliste,
			final String key, int mode) throws ScriptException {
//
//		// Key gibt es noch nicht, wird neu eingetragen
//		if (!this.patternmaps.indiPattern.matcher(key).matches()) {
//			if (LOGGER)
//				logger.trace("[" + key + "] wird in INDI angelegt.");
//			// if (LOGGER)
//			// logger.trace("Ergebnisliste gelöscht. " + key
//			// + " ist in GF-File zu bearbeiten!");
//			String r = (this.patternmaps.indiPattern.pattern().isEmpty() ? "("
//					+ key + ")" : this.patternmaps.indiPattern.pattern() + "|("
//					+ key + ")");
//			this.patternmaps.indiPattern = Pattern.compile(r);
//		} else {
//			logger.fatal("FATALER FEHLER!" + LOGTAB + "[" + key
//					+ "] ist in INDI schon angelegt." + LFCR
//					+ this.patternmaps.sconPattern.toString());
//			throw new ScriptException("[" + key + "] ist schon angelegt!");
//		}
//		// Wert eintragen/überschreiben
//		this.patternmaps.indi.put(key, tokenliste);
		return true;
	}

	/**
	 * Eintrag der Zuweisung x=f(y) in SCON.<br>
	 * 
	 * @param tokenliste der Zuweisung f(y)
	 * @param key Schlüssel- oder Kurzname x
	 * @return Eintrag erfolgreich?
	 * @throws ScriptException Fehler im Script
	 * @modified -
	 */
	private final boolean setSCON(final VP_Tokenlist tokenliste,
			final String key) throws ScriptException {
//		// Key gibt es noch nicht, wird neu eingetragen
//		if (!this.patternmaps.sconPattern.matcher(key).matches()) {
//			if (LOGGER)
//				logger.trace("[" + key + "] wird in SCON angelegt.");
//			// "Ergebnisliste gelöscht. " + key
//			// + " ist in GF-File zu bearbeiten!");
//			String r = (this.patternmaps.sconPattern.pattern().isEmpty() ? "("
//					+ key + ")" : this.patternmaps.sconPattern.pattern() + "|("
//					+ key + ")");
//			this.patternmaps.sconPattern = Pattern.compile(r);
//
//		} else {
//			logger.fatal("FATALER FEHLER!" + LOGTAB + "[" + key
//					+ "] ist in SCON schon angelegt." + LFCR
//					+ this.patternmaps.sconPattern.toString());
//			throw new ScriptException("[" + key + "] ist schon angelegt!");
//		}
//		// Wert eintragen/überschreiben
//		this.patternmaps.scon.put(key, tokenliste);
		return true;
	}

	/**
	 * Legt neue Tempores an, wenn keine vorhanden ist
	 * 
	 * @author tfossi
	 * @version 01.08.2014
	 * @modified -
	 * @since Java 1.6
	 * 
	 * @param tokenliste
	 *            Liste der Token
	 * @param key
	 *            Schlüsselwort
	 * @return Temporäre ist angelegt?
	 * @throws ScriptException ????
	 */
	private final boolean setAUXV(final VP_Tokenlist tokenliste,
			final String key) throws ScriptException {
//
//		// Key gibt es noch nicht, wird neu eingetragen
//		if (!this.patternmaps.auxvPattern.matcher(key).matches()) {
//			if (LOGGER)
//				logger.trace("[" + key + "] wird in AUXV angelegt.");
//			// if (LOGGER)
//			// logger.trace("Ergebnisliste gelöscht. " + key
//			// + " ist in GF-File zu bearbeiten!");
//			String r = (this.patternmaps.auxvPattern.pattern().isEmpty() ? "("
//					+ key + ")"
//					: this.patternmaps.auxvPattern.pattern() + "|(" + key
//							+ ")");
//			this.patternmaps.auxvPattern = Pattern.compile(r);
//
//		} else {
//			logger.fatal("FATALER FEHLER!" + LOGTAB + "[" + key
//					+ "] ist in AUXV schon angelegt." + LFCR
//					+ this.patternmaps.auxvPattern.toString());
//			throw new ScriptException("[" + key + "] ist schon angelegt!");
//		}
//		// Wert eintragen/überschreiben
//		this.patternmaps.auxv.put(key, tokenliste);
		return true;
	}

	// ---- Selbstverwaltung --------------------------------------------------

	/** serialVersionUID */
	@SuppressWarnings("unused")
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
//		this.vp_transfer = new VP_Transfer(this.patternmaps);
	}
}
