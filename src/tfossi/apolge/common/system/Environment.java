/** 
 * Environment.java
 * Branch base
 * APolGe
 * tfossi-team
 * licence GPLv3 
 */
package tfossi.apolge.common.system;

import static tfossi.apolge.common.constants.ConstValue.LFCR;
import static tfossi.apolge.common.constants.ConstValue.LOGGER;
import static tfossi.apolge.common.constants.ConstValue.NTAB;
import static tfossi.apolge.common.constants.ConstValue.OFF;
import static tfossi.apolge.common.constants.ConstValue.ON;
import static tfossi.apolge.common.constants.ConstValue.TAB;
import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;

import java.util.Enumeration;
import java.util.Properties;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

import tfossi.apolge.common.error.ErrApp;

/**
 * Optionen bei Programmaufruf <code>java apolge [Option]</code> auswerten.<br>
 * <code>-g, -game NAME: Name des Games [default: LESSION]</code><br>
 * <code>-url HOST: URL des Servers [default: localhost]</code><br>
 * <code>-p, -port PORT: Port des Sockets [default: 3544]</code><br>
 * <code>-l, -logger LEVEL: Loggingstufen (ALL | TRACE | DEBUG | [INFO] | WARN | FATAL | OFF</code>
 * <br>
 * <code>-s, -screen SCREEN: SWT-Gui oder Konsole einschalten: GUI | CON </code><br>
 * <code> SERVER </code><br>
 * <code> EDITOR </code><br>
 * <code> CLIENT </code><br>
 * <br>
 * Hinweis:<br>
 * Es gibt eine assert-Anweisung beim Aufruf von APolGe, wo bei gesetztem -ea Schalter
 * die Optionen hart verdrahtet übertragen werden! <br>
 *
 * @author tfossi
 * @version 11.08.2014
 * @modified -
 * @since Java 1.6
 */
public class Environment {

	/**
	 * Hilfetext, der auf der Konsole ausgegeben wird. Bei Environment
	 * <code>Help, ?</code> wird die Consolenhilfe gestartet oder bei fehlerhaften
	 * Startoptionen.
	 */
	public final static String helptext = LFCR+LFCR+"Aufruf: java apolge [Option]" + LFCR + "Optionen:" + LFCR
			+ TAB + "-?, -Help                    " + TAB + "Initiale Hilfe zum Starten von APolGe"
			+ LFCR + LFCR + TAB + "-g, -game NAME               " + TAB
			+ "Name des Games [default: LESSION]" + LFCR + TAB + "-url HOST                    "
			+ TAB + "URL des Servers [default: localhost]" + LFCR + TAB
			+ "-p, -port PORT               " + TAB + "Port des Sockets [default: 3544]" + LFCR
			+ TAB + "-l, -logger LEVEL            " + TAB
			+ "Loggingstufen (ALL | TRACE | DEBUG | INFO | WARN | FATAL | OFF) [default: INFO]" +LFCR
			+ TAB + "-probs                       " +TAB
			+ "Anzeige der Systemeinstellungen"
			+ LFCR + TAB + "-s, -screen SCREEN            " + TAB
			+ "SWT-Gui oder Konsole einschalten (GUI | CON)" + LFCR + LFCR + TAB
			+ "SERVER -g -p [-l]            " + TAB + "Server für ein Game" + LFCR + TAB
			+ "CLIENT -url -p [-g] [-l] [-s]" + TAB + "Client" + LFCR + TAB
			+ "EDITOR -g -l -s              " + TAB + "Editor" + LFCR + LFCR + LFCR
			
			+ "Systemanforderungen:" + LFCR + "Bedienung:" + LFCR + "";

	/** Optionen für Hilfe und Loggerlevel einstellen 
	 * @param props - 
	 * @return - 
	 * @modified - 
	 */
	private final static boolean environment(final Properties props) {
		// Show Helptext on Demand '?' or 'Help'
		if (props.get("help") != null) {
			return false;
		}
		// Den Environment-Debuglevel im Root(!) einstellen
		if (props.get("logger") != null)
			Logger.getRootLogger().setLevel((Level) props.get("logger"));

		Enumeration<?> propnames = props.propertyNames();
		String properties = "APolGe Einstellungen:";
		while (propnames.hasMoreElements()) {
			String propname = (String) propnames.nextElement();
			properties += NTAB + propname + "=" + props.get(propname);
		}
		if(Logger.getRootLogger().getLevel().toInt()<=Priority.INFO_INT)
			System.out.println(properties);
		return true;
	}

	/**
	 * Übertragen der Environment-Parameter (Option) in Properities
	 * 
	 * @param args
	 *            die Environment-Parameter (Option)	 
	 * @param props - 
	 * @return - 
	 * @modified - 
	 */
	private final static boolean para2prop(final String[] args, final Properties props) {
		String ea = "OFF";
		assert (ea = "ON") != null;

		if (ea.equals("ON"))
			if(LOGGER) logger.debug("EA ist eingeschaltet!");
		props.put("game", "LESSION");
		props.put("server", "localhost");
		props.put("port", new Integer(3544));
		// Der in log4j.properties eingestellte Root-Loggerlevel wird initial
		// übernommen
		props.put("logger", Logger.getRootLogger().getLevel());
		props.put("screen", new Boolean(OFF));
		props.put("sessionmanager", new Boolean(OFF));
		props.put("session", new Boolean(OFF));
		props.put("editor", new Boolean(OFF));
		props.put("client", new Boolean(OFF));
		props.put("probs", new Boolean(OFF));

		String key = null;

		for (String arg : args) {
			if (arg.equalsIgnoreCase("-HELP") || arg.equals("?") || arg.equals("-?")) {
				props.put("help", new Boolean(ON));
				continue;
			}
			if (key == null && (arg.equals("-g") || arg.equals("-game"))) {
				key = "game";
				continue;
			}
			if (key == null && (arg.equals("-url"))) {
				key = "url";
				continue;
			}
			if (key == null && (arg.equals("-p") || arg.equals("-port"))) {
				key = "port";
				continue;
			}
			if (key == null && (arg.equals("-l") || arg.equals("-logger"))) {
				key = "logger";
				continue;
			}
			if (key == null && (arg.equals("-s") || arg.equals("-screen"))) {
				key = "screen";
				continue;
			}

			if (key == null && (arg.equals("-probs"))) {
				props.put("probs", new Boolean(ON));
				continue;
			}
			if (arg.equalsIgnoreCase("SERVER")) {
				props.put("sessionmanager", new Boolean(ON));
				props.put("screen", new Boolean(OFF));
				continue;
			}
			if (arg.equalsIgnoreCase("EDITOR")) {
				props.put("editor", new Boolean(ON));
				// DEV props.put("gui", new Boolean(ON));
				continue;
			}
			if (arg.equalsIgnoreCase("CLIENT")) {
				props.put("client", new Boolean(ON));
				continue;
			}
			if (arg.equalsIgnoreCase("SESSION")) {
				props.put("session", new Boolean(ON));
				props.put("screen", new Boolean(OFF));
				continue;
			}

			if (key == null){
				ErrApp.ENVIRONMENT.erraufruf("Schalter key unbekannt\nARG: " + arg + LFCR
						+ helptext);
				return false;
			}
			if (key.equals("game")) {
				props.put(key, arg);
				key = null;
				continue;
			}
			if (key.equals("url")) {
				props.put(key, arg);
				key = null;
				continue;
			}
			if (key.equals("port")) {
				props.put(key, Integer.getInteger(arg));
				key = null;
				continue;
			}
			if (key.equals("logger")) {
				if (arg.equalsIgnoreCase("ALL")) {
					props.put("logger", Level.ALL);
					key = null;
					continue;
				}
				if (arg.equalsIgnoreCase("FATAL")) {
					props.put("logger", Level.FATAL);
					key = null;
					continue;
				}
				if (arg.equalsIgnoreCase("WARN")) {
					props.put("logger", Level.WARN);
					key = null;
					continue;
				}
				if (arg.equalsIgnoreCase("INFO")) {
					props.put("logger", Level.INFO);
					key = null;
					continue;
				}
				if (arg.equalsIgnoreCase("DEBUG")) {
					props.put("logger", Level.DEBUG);
					key = null;
					continue;
				}
				if (arg.equalsIgnoreCase("TRACE")) {
					props.put("logger", Level.TRACE);
					key = null;
					continue;
				}
				if (arg.equalsIgnoreCase("OFF")) {
					props.put("logger", Level.WARN);
					key = null;
					continue;
				}
				
				ErrApp.ENVIRONMENT.erraufruf("Argument \"" + arg + "\" unbekannt\n"
						+ helptext);
				return false;
			}

			if (key.equals("screen")) {
				if (arg.equalsIgnoreCase("GUI")) {
					props.put("screen", new Boolean(ON));
					key = null;
					continue;
				}
				if (arg.equalsIgnoreCase("CON")) {
					props.put("screen", new Boolean(OFF));
					key = null;
					continue;
				}
				ErrApp.ENVIRONMENT.erraufruf("Argument \"" + arg + "\" unbekannt\n"
						+ helptext);
				return false;
			}

			ErrApp.ENVIRONMENT.erraufruf("Argument \"" + arg + "\" unbekannt\n"
					+ helptext);
			return false;
		}
		return true;
	}

	/**
	 * Environment auswerten
	 *
	 * @modified -
	 */
	public Environment(){
		// 
	}
	/**
	 * TODO Comment
	 * @param args - 
	 * @param props - 
	 * @return - 
	 * @modified - 
	 */
	public final static boolean set(String[] args, final Properties props) {
		if(	!Environment.para2prop(args, props))
			return false;
		if(!Environment.environment(props))return false;
		return true;
	}

	// ---- Selbstverwaltung --------------------------------------------------
	/** serialVersionUID */
	@SuppressWarnings("unused")
	private final static long serialVersionUID = VERSION;
	/** logger */
	private final static Logger logger = Logger.getLogger(Environment.class.getPackage().getName());
}
