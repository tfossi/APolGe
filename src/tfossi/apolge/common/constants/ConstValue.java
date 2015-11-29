/**
 * ConstValue.java
 * Branch master
 * APolGe
 * tfossi-team
 * licence GPLv3  
 */
package tfossi.apolge.common.constants;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.PropertyConfigurator;

/**
 * Globale Konstanten
 * 
 * @author tfossi
 * @version 11.08.2014
 * @modified -
 * @since Java 1.6
 */
public class ConstValue {

	// Prepare Logging
	static {
		PropertyConfigurator.configure("log4j.properties");
	}

	/**
	 * If <code>LOGGER</code> is equals false, the JIT eliminate the Codeline<br>
	 * <code>if(LOGGER)...<br>, because its ever wrong
	 */
	public static final boolean LOGGER = true;
	
	/** CONFIGLOGGER=false, then no Logging during reading the configfile(s) */
	protected static final boolean CONFIGLOGGER = false;
	
	 /**
	  * Zeit Debuggen on/off
	  */
	public static final boolean TIMEEM = true;
	 /**
	 * Threadpause --> 0 / automatik
	 */
	 public static final boolean TIMESTEPOFF = true;

	// ---- Textsteuerung Konsole --------------------------------------------
	/**
	 * Zeilenende-String. This is the value of the line.separator property at
	 * the moment that the SimpleFormatter was created.
	 */
	public final static String LFCR = System.getProperty("line.separator");

	/** Tabulator-String. This is the value of the Tabulator */
	public final static String TAB = "\t";

	/** Häufige Kombination aus LFCR und TAB */
	public final static String NTAB = LFCR + TAB;

	/** Tabulator für Logger ist weiter eingerückt */
	public final static String LOGTAB = NTAB + TAB + TAB + TAB + TAB + TAB
			+ TAB + "";
	/** OFF */
	public final static boolean OFF = false;
	/** ON */
	public final static boolean ON = true;

	// ---- Zeitmessung -------------------------------------------------------

	/** applicationstarttime */
	public static final long applicationstarttime = System.currentTimeMillis();

	static {
		System.out
				.println((System.currentTimeMillis() - ConstValue.applicationstarttime)
						+ "ms Load Configuration");

	}

	// ---- Path, Extensions, Pre and File ------------------------------------
	// § Path ends with FS

	/** Fileseparator */
	public final static String FS = System.getProperty("file.separator");

	/** Path to the Configurationfile */
	public final static String CONFIGFILE = System.getProperty("user.dir") + FS
			+ "configs" + FS;

	/** Path to the Instalation: Programm, Scripts, Makros, Documentation */
	public final static String INSTALLATION_PATH = System
			.getProperty("user.dir") + FS;

	/** Path to the Scripts */
	public final static String SCRIPT_PATH = INSTALLATION_PATH + "script" + FS;

	/** Path to the Macros */
	public final static String MACRO_PATH = INSTALLATION_PATH + "macros" + FS;

	/** Script-Extension */
	public final static String SCRIPT_EXTENSION = ".apo";

	/** Datastructurfile */
	public final static String SCRIPT_DATASTRUCTURFILE = "datastructure"
			+ SCRIPT_EXTENSION;

	/** XML-Files-Extension */
	public final static String XXT = ".ser.xml";

	// Parser Pattern ...

	/** Listentrenner */
	public static final String LISTENTRENNER = ",";
	/** Listenkey */
	public static final String LISTENKEY = "?=";

	/** RegEx, um Zahlen mit wissenschaftlicher Notation zu finden */
	public static final String extractWissDigittoken = "([0-9.]+[eE][-+]?[0-9.]+[fF]?)";

	// ---- Regular Expressions -----------------------------------------------

	/** RegEx, um Operatoren zu finden */
	public static String operator = "\\+\\-\\*\\/\\&\\|\\^\\%"
			+ "\\<\\>\\<>\\==\\<=\\>=";

	// ... Parser Pattern End

	/** SEPARATOR */
	public static final char SEPARATOR = ';';
	/** KOMMA */
	public static final char KOMMA = ',';
	/** OPEN */
	public static final Character OPEN = new Character('(');
	/** CLOSE */
	public static final Character CLOSE = new Character(')');

	/** Methoden, die zur Elementerstellung ausgeführt werden */
	public static final String[] twoPass = new String[] { "random", "rint","nextInt",
			"ident", "ADR" };

	/** Methoden, die zur Laufzeit der Elemente ausgeführt werden werden */
	public static final String[] threePass = new String[] { "ADR" };

	/** Methoden, die nicht registriert werden sollen */
	public static final List<String> noRegister = Arrays.asList(new String[] {
			"hashCode", "equals", "toString", "notify", "notifyAll", "wait" });

	// ---- Grenzwerte --------------------------------------------------------

	/** Definition UNDEF bei int-Zahlen */
	public final static int INTUNDEF = Integer.MAX_VALUE;

	// ---- Hilfe ------------------------------------------------------------

	// ---- Konsolen-Konstanten -----------------------------------------------
	/** '+'-Zeichen */
	public static final char PLUS = '+';
	/** '-'-Zeichen */
	public static final char MINUS = '-';
	/** ' '-Zeichen */
	public static final char LEER = ' ';
	/** '|'-Zeichen */
	public static final char PIPE = '|';
	/** '.'-Zeichen */
	public static final char DOT = '.';
	/** '%'-Zeichen */
	public static final char PROZENT = '%';
	/** 'S'-Zeichen */
	public static final char S = 's';

}
