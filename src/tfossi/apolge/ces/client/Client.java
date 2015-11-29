/** 
 * Client.java
 * Branch ces
 * APolGe
 * tfossi-team
 * licence GPLv3 
 */
package tfossi.apolge.ces.client;

import static tfossi.apolge.common.constants.ConstValue.LOGGER;
import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;

import java.util.Properties;

import org.apache.log4j.Logger;

import tfossi.apolge.ces.AApplication;
import tfossi.apolge.ces.client.hci.ClientMenu;
import tfossi.apolge.common.hci.IState;
import tfossi.apolge.common.hci.IStateContext;
import tfossi.apolge.common.system.Environment;
import tfossi.apolge.common.system.PreLoad;


/**
 * Clientkomponente von APolGe.
 * <p>Zentrale Komponente ist die Statesteuerung eines State Pattern. ( Controller 
 * {@link IStateContext}, State {@link IState}), mit deren Hilfe das Menu angesteuert wird.
 * Die Application selber läuft als Thread unter APolGe.</p>
 * <p>Der Client selber läuft als Thread unter APolGe.</p>
 * <p>Das Startmenü ist {@link ClientMenu}</p>
 * 
 * @.pattern State: IStateContext
 * @see AApplication
 * @see IStateContext
 * @see IState
 * @see ClientMenu 
 * @author tfossi
 * @version 11.08.2014
 * @modified -
 * @since Java 1.6
 */
public class Client extends AApplication {
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public final void run() {
//		// Einrichten der Grafik on Demand		
//		einrichtenGrafik();
//		// Den ersten State einstellen
//		// Voreingestellt ist null für initiales Menu
//		IState startMenu = new ClientMenu(null, this);  
//		try {
//			if(LOGGER) logger.trace("Warte auf Abschluss des PreLoads");
//			this.pL.join();
//			if(LOGGER) logger.trace("Es geht weiter...");
//		} catch (InterruptedException e) {
//			ErrApp.THREADBEAK.erraufruf("Der Preload-Thread ist unterbrochen!");
//		}
//		// Stelle als ersten State ClientMenu ein
//		this.setActualState(startMenu);
//		// Schalte auf State um
//		this.switchToState();	
//		// Abmelden der Grafik, wenn erforderlich
////		this.getCntr().disposedApplicationScreen();
	}

	// ---- Selbstverwaltung -----------------------------------------------------
	/** serialVersionUID */
	@SuppressWarnings("unused")
	private final static long serialVersionUID = VERSION;
	/** logger */
	private final static Logger logger = Logger.getLogger(Client.class
			.getPackage().getName());

	/**
	 * Clientkomponente von APolGe
	 * 
	 * @param preload
	 *        ist Thread, der Daten und Tabellen vorläd. Ist der Vorgang nicht abgeschlossen, wartet
	 *        der Editor mit der weitere Bearbeitung.
	 * @param props
	 *            ist Properties. Dort ist enthalten, ob GUI oder Console gestarten
	 *            werden soll.
	 */
	public Client(final PreLoad preload, final Properties props) {
		super(preload, props);
		this.setName("Client");
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
		Client client = new Client(preload, props);		
	}

}
