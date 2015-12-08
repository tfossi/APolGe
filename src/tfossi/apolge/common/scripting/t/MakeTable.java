/**
 * MakeTable.java
 * Branch master
 * APolGe
 * tfossi-team
 * licence GPLv3  
 */
package tfossi.apolge.common.scripting.t;

import static tfossi.apolge.common.constants.ConstValue.*;
import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;

import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import tfossi.apolge.common.scripting.LoadScript;
import tfossi.apolge.common.scripting.vp.VP_ArrayTokenlist;
import tfossi.apolge.common.scripting.vp.VP_Tokenlist;

/**
 * Erstelle aus einer List<String>tokenline eine Anweisungstabelle
 *
 * @author tfossi
 * @version 25.11.2015
 * @modified -
 * @since Java 1.6
 */
public class MakeTable {
	
	/** Letzte Zeichenposition in der aktuellen tokenline */
	private int end = 0;

	/** tokenline */
	private final List<String> tokenline;

	/** lev */
	@SuppressWarnings("unused")
	private Level lev;

	/**
	 * Konstruktor
	 * @param tokenline
	 * 			tokenline, die in Tables übersetzt werden soll
	 * @modified -
	 */
	public MakeTable(List<String> tokenline) {
		this.tokenline = tokenline;
	}

	/**
	 * Kernmethode zur Umwandlung der tokenline in Tables
	 * @param quotes TODO
	 * @return entwickelte Table
	 * @throws TableException
	 * 			Fehlerexception
	 * @modified - 
	 */
	public Table makeTable(List<String>quotes) throws TableException {
		/** Aktuelle Table */
		final Table blocktable = new TableMap(null, null, null);

		// Table erzeugen
		if ((this.end = this.tokenline.size() - 1) >= 0) {
			if (LOGGER)
				logger.debug("Erzeuge aus Tokenliste eine Blocktable.");
			blocktable.putAll(makeTable(this.tokenline, new TableMap(null, null,
					null), 0, "",quotes));

		} else {
			logger.warn("Script leer!");
		}
		return this.makeTable(this.tokenline, blocktable, 0, null, quotes);
	}

	/**
	 * Erzeugt ein Table aus tokenline für den Block blocktable
	 * 
	 * @param tokenLine
	 *            Liste der bereinigten Tokens
	 * @param blocktable
	 *            Table, in der die Ergebnisse eingetragen werden
	 * @param offset
	 *            Ebene der Table
	 * @param preKey
	 *            ???
	 * @param quotes TODO
	 * @return Ergebnis der Tokenline in Table Operation
	 */
	private final Table makeTable(List<String> tokenLine, Table blocktable,
			int offset, String preKey, List<String>quotes) {

		int begin = offset;
		String key = null; // preKey;

		VP_Tokenlist<String> value = new VP_ArrayTokenlist<String>();
		assert begin <= this.end;

		// Solange nicht die letzte Zeichenposition in tokenline erreicht ist.
		while (begin < this.end) {
			if (LOGGER)
				if (tokenLine.size() > begin + 1)
					logger.trace("Check :>" + tokenLine.get(begin) + "< >"
							+ tokenLine.get(begin + 1)+"<"+LOGTAB+"Key  : "+key+LOGTAB+"Value: "+value);

			// Blockendezeichen
			if (tokenLine.get(begin).contains("}")) {

				tokenLine.remove(begin); // }
				this.end--;
				if (key != null) {
					if (LOGGER)
						logger.trace("INSERT(}) " + key + ": " + value);
					quotesEinsetzen(value,0,value.size()-1, quotes);
					blocktable.put(key, value);
				}else{
					if (LOGGER)
						logger.trace("NOINSERT(}) " + key + ": " + value);					
				}
				return blocktable;
			}
			// Anweisungsendezeichen, wie Block nur ohne Ebenenwechsel }
			if (tokenLine.get(begin).contains(";")) {
				tokenLine.remove(begin);
				this.end--;

				// Zeichen löschen. 
				// Es gibt einen Keywert, dann wird dieser mit dem value gespeichert
				if (key != null) {
					if (LOGGER)
						logger.trace("Set(;) " + key + ": " + value+ LFCR
						+ tokenLine);
					quotesEinsetzen(value,0,value.size()-1, quotes);
					blocktable.put(key, value);
				}else{
					if (LOGGER)
						logger.trace("NO Set(;) " + key + ": " + value+ LFCR
						+ tokenLine);
				}

				// Alles wieder vorbereiten für die nächste Anweisung
				key = null;
				value = new VP_ArrayTokenlist<String>();
				continue;
			}
			// New Block
			if (tokenLine.get(begin).contains("{")) {

				if (LOGGER)
					logger.trace("BLOCK({) " + key + "= " + value);

				// Bei { ohne Zuweisung (abc=) ist key ==null.
				// Wird als Liste behandelt:
				assert key!=null;
				if (key == null)
					key = "\\?" + String.valueOf(blocktable.size());
				tokenLine.remove(begin);
				this.end--;
				
				VP_Tokenlist<Table> block = new VP_ArrayTokenlist<Table>();
				block.add(makeTable(tokenLine, new TableMap(blocktable.root(),
						blocktable, key + "."), begin, key
						+ (key == "" ? "" : "."), quotes));
				if (LOGGER)
					logger.trace("RETBLOCK({) >"+ tokenLine.get(begin) + "< >"
							+ (tokenLine.size()>begin+1? tokenLine.get(begin + 1):"")+"<"+LOGTAB+"Key  : "+key+LOGTAB+"Value: "+value+ LFCR
							+ tokenLine+ LFCR +blocktable+LFCR+block);

				if (blocktable.containsKey(key)) {
					try {
						insertKnownKey(key, value, blocktable);
					} catch (ClassCastException e) {

						logger.fatal("KEY= " + key + LFCR + blocktable + LFCR
								+ e.getMessage());
						assert false : e.getMessage();
					}
					assert false;
				} else if (value.getTable()!=null && !value.getTable().isEmpty()) {
					if (LOGGER)
						logger.trace("NewKey INSERT " + key + ": " + value);
					quotesEinsetzen(value,0,value.size()-1, quotes);
					blocktable.put(key, value);
					assert false;
				} else{

//					quotesEinsetzen(block,0,block.size(), quotes);
					blocktable.put(key, block);
				}
				key = null;

				value = new VP_ArrayTokenlist<String>();
				continue;
			}
						
			// Keyanweisung ist immer Key=Irgentwas
			if (tokenLine.get(begin + 1).equals("=") ) {

				key = tokenLine.get(begin);

				tokenLine.remove(begin); // Key entfernen
				tokenLine.remove(begin); // '=' entfernen
				this.end--;
				this.end--;

				if (LOGGER)
					logger.trace("New Key " + key + ": " + value);
				continue;
				
				
			} else if (key == null) {
				if (LOGGER)
					logger.trace("null----" + key + ": " + value);
				key = "?" + String.valueOf(blocktable.size());
				
				assert false : key + ": " + value;
			}
			// Dürfte es nicht geben!
			assert value!=null:begin+LFCR+key+LFCR+tokenLine;
			value.add(tokenLine.get(begin));
			tokenLine.remove(begin);
			this.end--;
		}
		if (key != null) {
			if (LOGGER)
				logger.trace("Last INSERT " + key + ": " + value);
			assert value.get(0) != null : key + " " + value;
			
			quotesEinsetzen(value,0,value.size()-1, quotes);
			blocktable.put(key, value);
		}
	
		return blocktable;
	}
	
	/**
	 * Einem bekannten Key aus Tabelle den Wert zuordnen.
	 * 
	 * @param key
	 *            der key
	 * @param value
	 *            der Value
	 * @param known
	 *            die Tabelle
	 * @return die Tabelle
	 */
	@SuppressWarnings("unchecked")
	private final Table insertKnownKey(String key, VP_Tokenlist<String> value,
			Table known) {
assert false;
		if (LOGGER)
			logger.trace("KnownKey INSERT " + key + LOGTAB + "New Line: "
					+ value + LOGTAB + "in Bestand-Table " + known);

		if (LoadScript.has(known, key)) {
			try {
				Table inKnown = LoadScript.getTableValue(known, key);
				if (LOGGER)
					logger.trace(inKnown);
				if (LOGGER)
					logger.trace(value);
				for (Object ov : value) {
					if (LOGGER)
						logger.trace("OV " + ov);
					Table tov = (Table) ov;
					for (String ovkey : tov.keySet()) {
						if (LOGGER)
							logger.trace("OV " + ovkey);
						insertKnownKey(ovkey, (VP_Tokenlist<String>) tov.get(ovkey),
								inKnown);
					}
				}

			} catch (Exception e) {
				assert false : e.fillInStackTrace();
			}

		} else {
			known.put(key, value);
		}
		return known;
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
	private static final void quotesEinsetzen(VP_Tokenlist<String> valuetokens,
			final int firstElement, final int lastElement, List<String> quotes) {

		for (int ndx = firstElement; ndx <= lastElement; ndx++) {
			Object actToken = valuetokens.get(ndx);

			if (actToken instanceof String) {

				String aT = (String) actToken;
				if (aT.startsWith("$") && aT.endsWith("$")) {
					int varStart = 1;
					int varEnd = aT.length() - 1;
					int nr = Integer.valueOf(aT.substring(varStart, varEnd))
							.intValue();

					valuetokens.remove(ndx);
					valuetokens.add(ndx, quotes.get(nr));
					
				}
			}
		}
	}
	
	// ---- Selbstverwaltung --------------------------------------------------

	/**
	 * serialVersionUID<br>
	 * Hint: Maybe <code>VERSION</code> does not exists at this moment
	 */
	@SuppressWarnings("unused")
	private final static long serialVersionUID = VERSION;

	/** logger */
	private final static Logger logger = Logger.getLogger(MakeTable.class
			.getPackage().getName() + ".MakeTable");
}
