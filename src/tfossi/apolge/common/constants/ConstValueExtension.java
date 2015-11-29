/**
 * ConstValueExtension.java
 * Branch master
 * APolGe
 * tfossi-team
 * licence GPLv3  
 */
package tfossi.apolge.common.constants;

import java.util.Collections;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import tfossi.apolge.common.scripting.LoadScript;
import tfossi.apolge.common.scripting.LoadScriptException;
import tfossi.apolge.common.scripting.p.ParseException;
import tfossi.apolge.common.scripting.t.Table;

/**
 * Konstanten, die aus dem Script 'config' eingelesen werden.
 * 
 */
@SuppressWarnings("unchecked")
public class ConstValueExtension extends ConstValue {

	/** Konfigurationsscript */
	public static Table CONFIG_SCRIPT;

	// Einlesen der Konfigurationstabelle
	static {

		// Schalte das Loggen zum Einlesen der Configseite aus
		List<Logger> loggers;
		Level[] levArr;
		if (!CONFIGLOGGER) {			
			loggers = Collections.<Logger> list(LogManager.getCurrentLoggers());
			loggers.add(LogManager.getRootLogger());

			levArr = new Level[loggers.size()];
			for (Logger logger : loggers) {
				levArr[loggers.indexOf(logger)] = logger.getLevel();
				logger.setLevel(Level.OFF);
			}
		}
		try {

			CONFIG_SCRIPT = new LoadScript(ConstValue.CONFIGFILE + "config",
					null, true).getTable();

		} catch (LoadScriptException e) {
			e.printStackTrace();
			System.err.println("Abbruch: " + e.getMessage());
			System.exit(-2);
		} catch (ParseException e) {
			e.printStackTrace();
			System.err.println("Abbruch: " + e.getMessage());
			System.exit(-2);
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
			System.err.println("Abbruch: " + e.getMessage());
			System.exit(-2);
		} catch (NullPointerException e) {
			e.printStackTrace();
			System.err.println("Abbruch: " + e.getMessage());
			System.exit(-2);
		}
		
		// ggfs. Logging wieder einschalten
		if (!CONFIGLOGGER) {
			for (Logger logger : loggers) {
				logger.setLevel(levArr[loggers.indexOf(logger)]);
			}
		}
	}

	// ---- Version -----------------------------------------------------------
	/**
	 * Enthält die Versionsnummer von APolGe in 1000er Schritten. Wird genutzt,
	 * damit sich die <code>serialVersionUID</code> nur in den Versionsnummer
	 * x.xxx ändert ACHTUNG: <br>
	 * Zum Scripttesten mit Loadscript ist eine fixe VERSIONS-Nummer zu
	 * verwenden!
	 */
	// public final static long VERSION = -1L;
	public final static long VERSION = LoadScript.getLongValue(CONFIG_SCRIPT,
			"VERSION");

	/**
	 * Versionsnummer als String vom Type x.yy.zzz. Nur für Versionsanzeige im
	 * Header genutzt
	 */
	 public final static String VERSION_NUMBER = new String(String.format(
	 "%d.%02d.%03d",
	 new Long((VERSION - (VERSION % 100000L)) / 100000L), new Long(
	 ((VERSION - (VERSION % 1000L)) % 100000L) / 1000L),
	 new Long((VERSION % 1000L))));

		// ---- Zeitsteuerung: Time-Shifts ----------------------------------------

		// Die Events laufen in der Reihenfolge
		// PRE5 ... PRE1 , 0 Event, POST1 ... POST5
		//
		/**
		 * TODO Comment
		 *
		 * @author tfossi
		 * @version 11.08.2014
		 * @modified -
		 * @since Java 1.6
		 */
		public static enum Shift {
			// PRE5 bis POST5 bitte genau in dieser Reihenfolge
			// stehen lassen!
			/**
			 * Vor dem Zeitpunkt VOR der letzten Eventgruppe -999
			 */
			START(LoadScript.getLongValue(CONFIG_SCRIPT,"START")),
			/** Vor dem Zeitpunkt die letzte Eventgruppe -500 */
			PRE5(LoadScript.getLongValue(CONFIG_SCRIPT,"PRE5")),
			/** Vor dem Zeitpunkt die vorletzte Eventgruppe -400 */
			PRE4(LoadScript.getLongValue(CONFIG_SCRIPT,"PRE4")),
			/** Vor dem Zeitpunkt die zweite Eventgruppe -300 */
			PRE3(LoadScript.getLongValue(CONFIG_SCRIPT,"PRE3")),
			/** Vor dem Zeitpunkt die erste Eventgruppe -200 */
			PRE2(LoadScript.getLongValue(CONFIG_SCRIPT,"PRE2")),
			/** Vor dem Zeitpunkt die erste Eventgruppe -100 */
			PRE1(LoadScript.getLongValue(CONFIG_SCRIPT,"PRE1")),
			/** Standard 0 */
			NORMAL(LoadScript.getLongValue(CONFIG_SCRIPT,"NORMAL")),
			/** Nach dem Zeitpunkt der erste Nachtrag 100 */
			POST1(LoadScript.getLongValue(CONFIG_SCRIPT,"POST1")),
			/** Nach dem Zeitpunkt der zweite Nachtrag 200 */ 
			POST2(LoadScript.getLongValue(CONFIG_SCRIPT,"POST2")),
			/** Nach dem Zeitpunkt der mittlere Nachtrag 300 */
			POST3(LoadScript.getLongValue(CONFIG_SCRIPT,"POST3")),
			/** Nach dem Zeitpunkt der vorletzte Nachtrag 400 */
			POST4(LoadScript.getLongValue(CONFIG_SCRIPT,"POST4")),
			/** Nach dem Zeitpunkt der letzte Nachtraggruppe 500 */
			POST5(LoadScript.getLongValue(CONFIG_SCRIPT,"POST5")),

			/** Nicht definiert und Test */
			UNDEF(LoadScript.getLongValue(CONFIG_SCRIPT,"UNDEF"));

			/** ms */
			public final long ms;

			/**
			 * TODO Comment
			 * @param ms -
			 * @modified -
			 */
			private Shift(long ms) {
				this.ms = ms;
			}
		}
	 
	// ---- Konsolen-Konstanten -----------------------------------------------
	/** Zellenbreite für Consolenanzeige */
	 public static final int BREITE =
	 LoadScript.getIntValue(CONFIG_SCRIPT,"BREITE");
	/** Screenbreite für Consolenanzeige */
	public static final int MAXBREITE = LoadScript.getIntValue(CONFIG_SCRIPT,
			"MAXBREITE");

}
