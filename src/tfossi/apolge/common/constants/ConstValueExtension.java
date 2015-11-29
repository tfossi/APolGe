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

	// ---- Konsolen-Konstanten -----------------------------------------------
	/** Zellenbreite für Consolenanzeige */
	 public static final int BREITE =
	 LoadScript.getIntValue(CONFIG_SCRIPT,"BREITE");
	/** Screenbreite für Consolenanzeige */
	public static final int MAXBREITE = LoadScript.getIntValue(CONFIG_SCRIPT,
			"MAXBREITE");

}
