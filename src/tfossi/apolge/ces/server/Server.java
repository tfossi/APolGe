/** 
 * Server.java
 * Branch ces
 * APolGe
 * tfossi-team
 * licence GPLv3 
 */
package tfossi.apolge.ces.server;

import static tfossi.apolge.common.constants.ConstValue.LOGGER;
import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;

import java.util.Properties;

import org.apache.log4j.Logger;

import tfossi.apolge.ces.AApplication;
import tfossi.apolge.ces.server.hci.ServerMenu;
import tfossi.apolge.common.error.ErrApp;
import tfossi.apolge.common.hci.IState;
import tfossi.apolge.common.hci.IStateContext;
import tfossi.apolge.common.system.Environment;
import tfossi.apolge.common.system.PreLoad;

/**
 * Der Server steuert alle Gamesessions. <br>
 * Ziele:
 * <ul>
 * <li>Starten einer Session</li>
 * <li>Beenden einer Session</li>
 * <li>Monitoren aller Sessions</li>
 * </ul>
 * 
 * @.pattern State: IStateContext
 * @see AApplication
 * @see IStateContext
 * @see IState
 * @see ServerMenu
 * @author tfossi
 * @version 11.08.2014
 * @modified -
 * @since Java 1.6
 */
public class Server extends AApplication {

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public final void run() {
		// Einrichten der Grafik on Demand
		einrichtenGrafik(); // ServerModel.getInstance()

		// Den ersten State einstellen
		// Voreingestellt ist null für initiales Menu
		IState startMenu = new ServerMenu(null, this);

		// this.getCntr().initApplWidgets(((IMenu) startMenu).getModel());
//		  ((IMenu) startMenu).setInformation(this.getCntr(), Screen.M, new
//		 ContentString( "***WELCOME***"), false, true);
		try {
			if(LOGGER) logger.trace("Warte auf Abschluss des PreLoads");
			this.pL.join();
			if(LOGGER) logger.trace("Es geht weiter...");
		} catch (InterruptedException e) {
			ErrApp.THREADBEAK.erraufruf(
					"Der Preload-Thread ist unterbrochen!");
		}

		// Stelle als ersten State ServerMenu ein
		this.setActualState(startMenu);
		// Schalte auf State um
		this.switchToState();
		// // Abmelden der Grafik, wenn erforderlich
		// this.getCntr().disposedApplicationScreen();
	
	}

	// ---- Selbstverwaltung --------------------------------------------------
	/** serialVersionUID */
	@SuppressWarnings("unused")
	private final static long serialVersionUID = VERSION;

	/** logger */
	private final static Logger logger = Logger.getLogger(Server.class
			.getPackage().getName());

	
	/**
	 * Serverkomponente von APolGe
	 * 
	 * @param preload
	 *            ist Thread, der Daten und Tabellen vorläd. Ist der Vorgang
	 *            nicht abgeschlossen, wartet der Editor mit der weitere
	 *            Bearbeitung.
	 * @param props
	 *            ist Properties. Dort ist enthalten, ob GUI oder Console
	 *            gestarten werden soll.
	 * @modified -
	 */
	public Server(final PreLoad preload, final Properties props) {
		super(preload, props);
		this.setName("Server");
		this.start();
	}

	/**
	 * TODO Comment
	 * @param args -
	 * @modified - 
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		System.out.println("Starte....");
		Properties props = new Properties();
		new Environment();
		if(!Environment.set(args, props))
			return;
		if(LOGGER) logger.debug("Optionen sind ausgewertet!");
		// Systemproportionen anzeigen (Level DEBUG notwendig)
		//new SystemProps();
		// Tabellen vorladen
		PreLoad preload = new PreLoad();
		Server server = new Server(preload, props);		
	}
}
