/**
 * LoadScript.java
 * Branch master
 * APolGe
 * tfossi-team
 * licence GPLv3  
 */
package tfossi.apolge.common.scripting;

import static tfossi.apolge.common.constants.ConstValue.FS;
import static tfossi.apolge.common.constants.ConstValue.LFCR;
import static tfossi.apolge.common.constants.ConstValue.LOGGER;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import tfossi.apolge.common.constants.ConstValue;
import tfossi.apolge.common.scripting.p.DoMAT;
import tfossi.apolge.common.scripting.p.Initwork;
import tfossi.apolge.common.scripting.p.NoComment;
import tfossi.apolge.common.scripting.p.NoIndex;
import tfossi.apolge.common.scripting.p.NoPseudoListen;
import tfossi.apolge.common.scripting.p.NoQuote;
import tfossi.apolge.common.scripting.p.NoWhiteblancsLFCR;
import tfossi.apolge.common.scripting.p.NormalListen;
import tfossi.apolge.common.scripting.p.ParseException;
import tfossi.apolge.common.scripting.t.MakeTable;
import tfossi.apolge.common.scripting.t.Table;
import tfossi.apolge.common.scripting.t.TableException;
import tfossi.apolge.common.scripting.t.TableMap;

/**
 * Aufgabe ist
 * <ul>
 * <li>das Laden von einem Script in eine Roottable</li>
 * <li>das zur Verfügung stellen von Methodiken, um die Scriptdaten zu
 * überführen</li>
 * </ul>
 * Alle Daten aus dem Script sind ersteinmal vom Typ "String"<br>
 * Die Daten werden in Tables abgelegt. Das Roottable (Rootkey = '_') ist das
 * oberste Table<br>
 * Tables sind typsichere {@link java.util.Map} und {@link java.util.HashMap}
 *
 * @see Table
 * @see TableMap
 * 
 * @author tfossi
 * @version scripting 1.0
 * @since java version "1.6.0_0"
 */
public class LoadScript {
	
	/** Datenstrom Filename */
	private InputStream fis;

	/** POST-decoded Script */
	private StringBuffer postscript = new StringBuffer();
	
	/** Token-decoded postscript */
	private List<String> tokenline = new LinkedList<String>();
	
	/** Roottabelle der Daten */
	private final Table roottable = new TableMap(null, null, null);

	/** Erster und einziger Key im Root-Table */
	public static final String rootKey = "_";

	/**
	 * Enthält eine geordnete Liste von Strings, die vor dem Parsen im
	 * Untersuchungsstring durch ihre Indexnummer ersetzt werden. Dadurch werden
	 * die im String verwendeten Steuerzeichen nicht interpretiert.
	 */
	private final List<String> quotes = new LinkedList<String>();

	/**
	 * @return liefert die Liste der Strings, die durch ihre Indexnummer im
	 *         Untersuchungsstring ersetz werden
	 * @modified -
	 */
	final List<String> getQuotes() {
		return this.quotes;
	}

	
	/**
	 * Liefert das Table-Objekt des Eintrags <code>name</code> im Table
	 * <code>table</code>.
	 * 
	 * @param table
	 *            Die Table
	 * @param name
	 *            Name des Objekt
	 * @return das Objekt
	 * @throws NoSuchFieldException
	 *             Der Eintrag [name] in der Tabelle [table] wurde nicht
	 *             gefunden!
	 * @throws ArrayIndexOutOfBoundsException
	 *             Es gibt für name keine Einträge in der Liste
	 * @throws NullPointerException
	 *             Die Tabelle ist nicht initiiert [<code>null</code>]!
	 */
	private final static Object getObject(final Table table, final String name)
			throws NoSuchFieldException, ArrayIndexOutOfBoundsException,
			NullPointerException {
		return getObject(table, name, 0);		
	}

	/**
	 * @param table
	 *            Die Table
	 * @param name
	 *            Name des Objekt
	 * @param index
	 *            Index
	 * @return das Objekt
	 * @throws NoSuchFieldException
	 *             Der Eintrag [name] in der Tabelle [table] wurde nicht
	 *             gefunden!
	 * @throws NullPointerException
	 *             Die Tabelle ist nicht initiiert [<code>null</code>]!
	 * @throws ArrayIndexOutOfBoundsException
	 *             Es gibt für name keine Einträge in der Liste
	 */
	private final static Object getObject(final Table table, final String name,
			final int index) throws NoSuchFieldException, NullPointerException,
			ArrayIndexOutOfBoundsException {
		if (table == null)
			throw new NoSuchFieldException("Table-Value ist nicht initiiert!");
		
		else if (table.containsKey(name)) {
			if (table.get(name) instanceof List) {
				@SuppressWarnings("unchecked")
				List<Object> liste = (List<Object>) table.get(name);
				if (liste == null)
					throw new NoSuchFieldException("Table-Value [" + name
							+ "] ist nicht initiiert!");
				
				if (liste.isEmpty())
					throw new NoSuchFieldException("Es gibt für [" + name
							+ "] keine Valueinträge in der Tabelle!");
				if (liste.size() <= index)
					throw new ArrayIndexOutOfBoundsException("Es gibt für ["
							+ name + "] keine [" + index + "/"
							+ (liste.size() - 1)
							+ "] Valueinträge in der Tabelle!");
				return liste.get(index);
			}
			assert false;
			return table.get(name);
		}
		throw new NoSuchFieldException("Der Eintrag [" + name + "] in "
				+ table.toString() + " nicht gefunden!");
	}

	
	/**
	 * Liefert ein Objekt eines Eintrags [name] in der Tabelle [table]
	 * 
	 * @param table
	 *            die Tabelle
	 * @param name
	 *            der Eintrag
	 * @return das Objekt oder Fehlerausgabe.
	 * @throws NoSuchFieldException
	 *             Name des Object existiert nicht
	 * @throws NullPointerException
	 *             Object existiert nicht
	 * @throws ArrayIndexOutOfBoundsException
	 *             Es gibt für name keine Einträge in der Liste
	 */
	public final synchronized static Object getObjectValue(Table table,
			String name) throws NullPointerException,
			ArrayIndexOutOfBoundsException, NoSuchFieldException {

		return LoadScript.getObject(table, name);

	}

	
	/**
	 * Liefert ein Objekt eines Eintrags [name] in der Tabelle [table]
	 * 
	 * @param table
	 *            die Tabelle
	 * @param name
	 *            der Eintrag
	 * @return das Objekt oder Fehlerausgabe.
	 * @throws NoSuchFieldException
	 *             Name des Object existiert nicht
	 * @throws NullPointerException
	 *             Object existiert nicht
	 * @throws ArrayIndexOutOfBoundsException
	 *             Es gibt für name keine Einträge in der Liste
	 */
	public final synchronized static Table getTableValue(final Table table,
			final String name) throws NullPointerException,
			ArrayIndexOutOfBoundsException, NoSuchFieldException {

		return (Table) LoadScript.getObject(table, name);

	}

	
	/** @return die Roottable */
	public synchronized final Table getTable() {
		return (Table) this.roottable.get(LoadScript.rootKey);
	}

	

	/**
	 * Prüft, ob die Table <code>table</code> einen Eintrag <code>name</code>
	 * hat.
	 * 
	 * @param table
	 *            die Tabelle
	 * @param name
	 *            der Eintrag
	 * @return <code>true</code> wenn der Eintrag existiert.
	 */
	public synchronized final static boolean has(final Table table,
			final String name) {
		try {
			return table.containsKey(name);
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * Parsed ein Script nach den Ausdrücken und erstellt eine normierte
	 * Liste zur Erstellung der APO-Tabellen.<br>
	 * Die Normierung hat grundsätzlich die Form <i>;key=value;</i>
	 * <p>
	 * <b>Regeln zum Parsen</b><br>
	 * <ul>
	 * <li>Eingabe
	 * <ul>
	 * <li>Eine einfache Zuweisung ist <i>a = 5</i></li>
	 * <li>Listen haben die Form <i>a,b,...,c,d</i></li>
	 * <li>Index hat die Form <i>a[i] = 5</i></li>
	 * <li>Vektoren haben die Form <i>a = [1 2 3]</i></li>
	 * <li>Matrizen haben die Form <i>a = [[1 2 3][4 5 6]]</i></li>
	 * <li>Transponierte Vektoren und Matrizen enden mit einem <i>[1 2 3]T</i></li>
	 * <li>Addressen haben die Form <i>@A.B. ... .C</i></li>
	 * <li>Bedingungen haben die Form <i>IF Bed. THEN Erg. ELSE Alt.</i></li>
	 * <li>Initiale Anweisungen haben die Form <i>a[INIT] = 5</i></li>
	 * <li>Prozessanweisungen haben die Form <i>a[PROC] = 5</i></li>
	 * <li>Zeilenkommentare haben die Form <i>\\</i></li>
	 * <li>Blockkommentare haben die Form <i>&#47;** ... *&#47;</i></li>
	 * <li>Funktionen sind aus dem Java Kontext und behinhalten mathematische,
	 * Zufalls und APO-eigene Methoden</li>
	 * <li>...</li>
	 * </ul>
	 * <li>Ausgabe
	 * <ul></li>
	 * <li>Eine Anweisung wird zwischen Semikolon erwartet <i>; ... ;</i></li>
	 * <li>Block- und Zeilenkommentare sind entfernt</li>
	 * <li>Alle Strings sind in <i>List &lt;String&gt; quote</i> gespeichert und
	 * ausgetauscht mit der Indexnummer <i>$Zahl$</i></li>
	 * <li>Leer-, LFCR- und redundante Zeichen sind entfernent und
	 * Blockbegrenzungen <i>;</> eingesetzt</i></li>
	 * <li>Listen sind normalisiert in der Form <i>?0=a,?1=b, ... ,?2=c,?3=d</i>
	 * </li>
	 * <li>Index wird aufgelöst in der Form <i>a={a.i=5}</i></li>
	 * <li>Vektoren sind als Methodenaufruf <i>VEKTOR(1,2,3)</i> vorbereitet</li>
	 * <li>Matrizen sind als Methodenaufruf
	 * <i>MATRIX(VEKTOR(1,2,3),VEKTOR(4,5,6))</i> vorbereitet</li>
	 * <li>Addressen sind als Methodenaufruf <i>ADR(A,B,C,D)</i> vorbereitet</li>
	 * <li>Bedingungen sind als Methodenaufruf <i>WENN(A,B,C)</i> vorbereitet</li>
	 * <li>...</li>
	 * </ul>
	 * </ul>
	 * 
	 * @param din
	 *            Eingabestrom
	 * @param in1
	 *            Vorbelegung
	 * @param quotes
	 *            quotes-Liste
	 * @throws ParseException
	 *             ParseException Fehlerexception beim Parsen mit Hinweis zum
	 *             Grund der Exception
	 * @modified -
	 */
	@SuppressWarnings("unused")
	private synchronized final void generatePostscript(
			final BufferedReader din, final StringBuffer in1,
			@SuppressWarnings("hiding") final List<String> quotes) throws ParseException {

		/** Entfernt die Kommentare, stellt Logger ein. */
		new NoComment(din, in1, this.postscript);

		/** Entfernt die Strings und stellt Platzhalter ein. */
		new NoQuote(this.postscript, quotes);

		/** Leerzeichen und LFCR-Steuerzeichen entfernen, ; einsetzen */
		new NoWhiteblancsLFCR(this.postscript);

		/** INIT/WORK */
		new Initwork(this.postscript);

		/** Vektoren, Matrizen und Funktionen codieren. */
		new DoMAT(this.postscript);

		/** Vereinigt zeilenübergreifende Anweisungen und löscht Leerzeilen */
		new NoPseudoListen(this.postscript);

		/** Normalisierte Listen */
		new NormalListen(this.postscript);

		/** Index auflösen */
		new NoIndex(this.postscript);

		if (LOGGER)
			logger.debug(this.postscript);
	}

	/**
	 * * Zerlege das Script in zusammengehörige Token, entferne die
	 * überflüssigen Zeichen und speichere die Token in einer Liste<String>
	 * 
	 * @throws ParseException
	 *             Parser Exception
	 */
	synchronized final void generateTokenlist() throws ParseException {
		
		try {
			tokenlist(this.tokenline, this.postscript);
		} catch (ParseException e) {
			throw new ParseException(e.getMessage() + LFCR + this.quotes);
		}

		if (LOGGER)
			logger.debug(this.tokenline);
	}
	
	/**
	 * Zerlege das Script in zusammengehörige Token, entferne die überflüssigen
	 * Zeichen und speichere die Token in einer <i>List&lt;String&gt;</i><br>
	 * <b>Bedingung</b>
	 * normierter Eintrag
	 * @param tokenline 
	 * 		Ergebnisliste der Token
	 * 
	 * @param resultbuffer
	 *            Eingabebuffer
	 * @throws ParseException
	 *             Fehler in der Tokenbildung
	 */
	@SuppressWarnings("static-method")
	private final void tokenlist(@SuppressWarnings("hiding") final List<String> tokenline,
			final StringBuffer resultbuffer) throws ParseException {
		if (LOGGER)
			logger.debug("Zerlege die Einträge in Token.");

		/** RegEx, um Keys zu finden: zwischen ;{ und = 
		 * p{Alnum} und '?' mindestens 1 oder mehr (+) gefolgt von einem '=' */
		String extractKeytoken = "([0-9a-zA-Z?]+)(?=[=])"; 
		
		/**
		 * RegEx, um Werte zu finden (Zahlen, Namen, Quotes, Addressen, Zeiten,
		 * Datum)
		 */
		String extractValtoken = "([0-9a-zA-Z@.$:]+)"; 

		/** RegEx, um Klammern zu finden */
		String extractKlammertoken = "([{}\\(\\),;=]{1}?)";
		
		/** RegEx, um algebra und bool-Operatoren zu finden */
		String op = "(==)|(\\<=)|(\\>=)|(\\<\\>)|(=)|([\\+\\-\\*\\/\\^\\<\\>!])";
		String op2 = "(&&)|(\\|\\|)|(!&)|(!\\|)";
		
		/**
		 * RegEx, um bekannte Token wie Zahlen, Funktionen, Werte, Klammern und
		 * Operatoren zu finden
		 */
		Pattern tokenLinePattern = Pattern.compile("(;)"
		// Operatoren herausfiltern
				+ "|" + op2 + "|" + op
				// + "|" + extractKeytoken
				// + "|"
				// "([#!]?[0-9a-zA-Z_@.$]+#)" + "|"
				+ "|" + extractKlammertoken
				// + extractWissDigittoken + "|"
				+ "|" + extractKeytoken
				// +
				// +
				// (PatternMaps.finalOperationValue==null?"":PatternMaps.finalOperationValue.pattern()
				// + "|")
				// +
				// (PatternMaps.finalFunctionValue==null?"":PatternMaps.finalFunctionValue.pattern()
				// + "|")
				// +
				// Wert Zahlen, String, Addressen, Datum,
				+ "|" + extractValtoken

		);

		Matcher m = tokenLinePattern.matcher(resultbuffer);
		while (m.find()) {
			tokenline.add(m.group());
		}
		
		if (tokenline.isEmpty()) {
			throw new ParseException("Fehler in der Tokenbildung:" + LFCR + resultbuffer);
		}
	}
	
	/**
	 * Tabelle erstellen
	 * @throws TableException
	 * 			Fehlerexception
	 * @modified - 
	 */
	synchronized final void generateTable() throws TableException {

		// Es wird die Roottabelle 'rootKey' erzeugt. Es werden ggfs. daraus
		// weitere Tables untergehängt.
		this.roottable.put(LoadScript.rootKey,
				new MakeTable(this.tokenline).makeTable());		
		if (LOGGER)
			logger.debug(this.tokenline);

	}
	
	// ----Selbstverwaltung----------------------------------------------------

	/**
	 * serialVersionUID<br>
	 * Hint: <code>VERSION</code> does not exists at this moment
	 */
	@SuppressWarnings("unused")
	private final static long serialVersionUID = -1L;

	/** logger */
	private final static Logger logger = Logger.getLogger(LoadScript.class
			.getPackage().getName());

	/**
	 * Liest ein Script aus File <code>fileName</code> oder String
	 * <code>doString</code> ein.<br>
	 * Im Fehlerfall wird eine Exception zum Fehler ausgeworfen.
	 * 
	 * @param fileName
	 *            Name of the File with Apo-Script.
	 * @param doString
	 *            String with Apo-Script.
	 * @throws LoadScriptException
	 *             Fehler mit Beschreibung der Fehlerursache
	 * @throws ParseException
	 *             Fehlerexception beim Parsen mit Hinweis zum Grund der
	 *             Exception
	 */
	public LoadScript(final String fileName, final String doString)
			throws LoadScriptException, ParseException {

		if (fileName != null && doString == null) {
			logger.info((System.currentTimeMillis() - ConstValue.applicationstarttime)
					+ "ms zum Einlesen der Datei ["
					+ fileName.substring(fileName.lastIndexOf(FS) + 1) + "]");

			try {
				this.fis = new FileInputStream(fileName);
			} catch (FileNotFoundException e) {
				// Prüfe, ob Pfad oder Filename falsch ist.
				int ch = fileName.lastIndexOf(FS);

				File file = new File(fileName.substring(0, ch));
				if (file.exists())
					throw new LoadScriptException("Datei [" + fileName
							+ "] existiert nicht!");
				throw new LoadScriptException("Ordner [" + fileName
						+ "] existiert nicht!");
			}

			// Es wird ein normiertes Postscript erzeugt. 
			generatePostscript(new BufferedReader(new InputStreamReader(
					this.fis)), null, this.quotes);

		} else if (fileName == null && doString != null) {
			logger.info((System.currentTimeMillis() - ConstValue.applicationstarttime)
					+ " Einlesen aus String\n" + doString);

		} else if (fileName == null && doString == null) {
			throw new LoadScriptException("Kein Script angegeben!");
		} else {
			throw new LoadScriptException("Zwei Scripte angegeben!");
		}
		this.closeScript();
	}

	/**
	 * Ends the use.
	 * 
	 * @throws LoadScriptException
	 *             Fehler mit Beschreibung der Fehlerursache
	 */
	public synchronized final void closeScript() throws LoadScriptException {
		try {
			if (this.fis != null)
				this.fis.close();
		} catch (IOException e) {
			throw new LoadScriptException(e.getMessage());
		}
	}


	/**
	 * Anzeigestring Eingabeformatierung
	 * @return String
	 * @modified - 
	 */
	public synchronized final String postscript2String() {
		return this.postscript.toString();
	}
	/**
	 * Anzeigestring Tokenliste
	 * @return String
	 * @modified - 
	 */
	public synchronized final String tokenlist2String() {
		return this.tokenline.toString();
	}
	/**
	 * Anzeigestring Tabellenliste
	 * @return String
	 * @modified - 
	 */
	public synchronized final String block2String() {
		return this.roottable.toString();
	}


}