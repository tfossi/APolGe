/** 
 * APolGe.java
 * Branch base
 * APolGe
 * tfossi-team
 * licence GPLv3 
 */
package tfossi.apolge;

import static tfossi.apolge.common.constants.ConstValue.LOGGER;
import static tfossi.apolge.common.constants.ConstValue.NTAB;
import static tfossi.apolge.common.constants.ConstValue.ON;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import tfossi.apolge.ces.client.Client;
import tfossi.apolge.ces.editor.Editor;
import tfossi.apolge.ces.server.Server;
import tfossi.apolge.common.system.Environment;
import tfossi.apolge.common.system.PreLoad;
import tfossi.apolge.common.system.SystemProps;


/**
 * Aufrufklasse von APolGe. Der Aufruf erfolgt mit
 * <code>java apolge [option]</code>. Je nach Option (Environment) wird
 * gestartet:<br />
 * <b>EDITOR:</b> Der Editor<br />
 * <b>SERVER:</b> Der Server<br />
 * <b>CLIENT:</b> Der Client <br />
 * <br />
 * Dazu kann die Ausgabe festgelegt werden:<br />
 * <b>CON:</b> Ausgabe auf Konsole [default]<br />
 * <b>GUI:</b> Ausgabe auf SWT<br />
 * <br />
 * Für Fehlercheck ist der Logger-Level festlegbar:<br />
 * <b>ALL</b><br />
 * <b>TRACE</b><br />
 * <b>DEBUG</b><br />
 * <b>INFO</b><br />
 * <b>WARN</b><br />
 * <b>FATAL</b> [default]<br />
 * <b>OFF</b><br />
 * Die Logdarstellung ist in <b>log4j.properties</b> beschrieben. <br>
 * <br>
 *
 * @author tfossi
 * @version 11.08.2014
 * @modified -
 * @since Java 1.6
 */
public class APolGe {


	/** Sollte zum Testen mit JavaUnit auf <code>true</code> gesetzt werden. */
	static boolean juflag = false;
	
	/** Servermanagerinstanz */
	private final Server sessionmanager;

	/** Clientinstanz */
	private final Client client;

	/** Editorinstanz */
	private final Editor editor;

	/** Ladebeschleunigung */
	private final PreLoad preload;

	// /** Session */
	// private Session session;

	/**
	 * Enthält im Property die Environment-Daten
	 */
	private final Properties props = new Properties();

	/**
	 * Programmeinstieg
	 * 
	 * @param args
	 *            Environments ALL, TRACE, DEBUG, INFO, WARN, FATAL, OFF GUI CON
	 *            SERVER CLIENT EDITOR HELP / ?...
	 */
	@SuppressWarnings("unused")
	public final static void main(final String[] args) {
		PropertyConfigurator.configure("log4j.properties");
		System.out.println("Starte APolGe ...");
		
		if(juflag) System.out.println("JUFLAG EIN");
		
		// DEV clear next lines ...Testaufruf
		String[] str = args;
		// assert (str = new String[] { "SERVER",
		// "-l", "WARN",
		// "-s", "GUI" }) != null;
		// "-s", "CON" }) != null;
		// ... ends here
		new APolGe(str);
		System.out.println("APolGe ist beendet.");
	}

	/**
	 * Start der APolGeinstance
	 * 
	 * @param args
	 *            Aufrufparameter
	 */
	private APolGe(String[] args) {
		// DEV clear next lines ...Testaufruf
		String s = "Aufruf:";

		if(args!=null)
			for (String str : args) {
				s += " " + str;
			}
		else {
			args = new String[]{""};
			s = "";
		}
		s = s.replaceAll("-", NTAB + "-");
		System.out.println(s);

		// if(LOGGER) logger.debug(s.replaceAll(NTAB, LOGTAB));
		// ... ends here

		// Optionen einstellen
		if( !Environment.set(args, this.props)){
			System.err.println(Environment.helptext);
			this.sessionmanager = null;
			this.preload = null;
			this.client = null;
			this.editor = null;
			return;
		}
		// Systemproportionen anzeigen (Level DEBUG notwendig)
		SystemProps systemProps = new SystemProps(this.props);
		// Tabellen vorladen
		this.preload = new PreLoad();

		if (this.props.get("session").equals(new Boolean(ON))) {
			// Serverinstanz starten
			// this.session = new Session(this.preload, this.props);
			this.client = null;
			this.editor = null;
			this.sessionmanager = null;
		} else if (this.props.get("sessionmanager").equals(new Boolean(ON))) {
			// Serverinstanz starten
			this.sessionmanager = new Server(this.preload, this.props);
			this.client = null;
			this.editor = null;
			// this.session = null;
		} else if (this.props.get("editor").equals(new Boolean(ON))) {
			// Editorinstanz starten
			this.editor = new Editor(this.preload, this.props);
			this.sessionmanager = null;
			this.client = null;
			// this.session = null;
		} else {
			// Clientinstanz starten
			this.props.put("client", new Boolean(ON));
			this.client = new Client(this.preload, this.props);
			this.sessionmanager = null;
			this.editor = null;
			// this.session = null;
		}

		try {
			if(LOGGER) logger.debug("Warte auf Threadende!");
			// Auf Ende der Sessioninstanz warten
			// if (this.session != null)
			// this.session.join();
			// Auf Ende der Sessionmanager warten
			if (this.sessionmanager != null)
				this.sessionmanager.join();
			 // Auf Ende der Editorinstanz warten //
			if (this.editor != null)
				this.editor.join();
			// Auf Ende der Clientinstanz warten //
			if (this.client != null)
				this.client.join();
			// // Vorsichtshalber: Preload abbrechen
			this.preload.interrupt();
			this.preload.join();
		} catch (InterruptedException e) {
			if(LOGGER) logger.fatal("Einer der Threads wirft eine Exception.", e);
			if(LOGGER) logger.fatal(systemProps);
		}
	}

	// ---- Selbstverwaltung --------------------------------------------------
	/** serialVersionUID */
	public final static long serialVersionUID = 000001L;

	/** logger */
	private final static Logger logger = Logger.getLogger(APolGe.class);
}
