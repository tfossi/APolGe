/** 
 * Editor.java
 * Branch ces
 * APolGe
 * tfossi-team
 * licence GPLv3 
 */
package tfossi.apolge.ces.editor;
import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;

import java.util.Properties;

import org.apache.log4j.Logger;

import tfossi.apolge.ces.AApplication;
import tfossi.apolge.ces.editor.hci.EditorMenu;
import tfossi.apolge.common.hci.IState;
import tfossi.apolge.common.hci.IStateContext;
import tfossi.apolge.common.system.PreLoad;

/**
 * Editorkomponente von APolGe.
 * <p>Zentrale Komponente ist die Statesteuerung eines State Pattern. ( Controller 
 * {@link IStateContext}, State {@link IState}), mit deren Hilfe das Menu angesteuert wird.
 * Die Application selber läuft als Thread unter APolGe.</p>
 * <p>Der Editor selber läuft als Thread unter APolGe.</p>
 * <p>Das Startmenü ist {@link EditorMenu}</p>
 * 
 * @.pattern State: IStateContext
 * @see AApplication
 * @see IStateContext
 * @see IState
 * @see EditorMenu
 * @author tfossi
 * @version 11.08.2014
 * @modified -
 * @since Java 1.6
 */
public class Editor extends AApplication {

	// ---- Thread ---------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	 	@Override
	public final void run() {
//
////	 	IGrfCntr is = MetaData.getInstance().loginNewSession(this);
//		// Grafik einrichten. Zuerst, da Modell Daten zieht!		
//		einrichtenGrafik();
//		// swt==null für Console, sonst nicht
////		SWTGrafik swt = this.getSWTGrafik();
//			
////		if (this.isGrafik()) {
////			SWTGrafik swt = this.getGrfCntr().getGrafik();
////			// SWT Screen
////			IModel model = EditorModel.getInstance();
////			// Alle Applikationsscreens einrichten
////			for (Screen scr : APPLICATIONSCREENS){
////				Group group = swt.getGroup(scr);	
////				Map<String, Widget> map = scr.buildSWTWidget(model, group);
////				assert map!=null;
////				this.storeApplWidget(map,scr);
//////				MetaData.getInstance().storeApplWidget(is, map, scr);
////			}
////		}		
//		
//		// Den ersten State einstellen
//		// Voreingestellt ist null für initiales Menu
//
//		IState startMenu = new EditorMenu(null, this);  
//
//		try {
//			if(LOGGER) logger.trace("Warte auf Abschluss des PreLoads");
//			this.pL.join();
//			if(LOGGER) logger.trace("Es geht weiter...");
//		} catch (InterruptedException e) {
//			ErrApp.THREADBEAK.erraufruf( "Der Preload-Thread ist unterbrochen!");
//		}
//		
//		// -------------------------------------------------------------------------
//		// DEV ... spiele testweise das Makro "T1" auf dem Makrorecorder ab
////		 ((IReceiver) editorMenu).playRecorder("T1");
//		// -------------------------------------------------------------------------
//		// Stelle als ersten State EditorMenu ein
//		this.setActualState(startMenu);
//		// Schalte auf State um
//		this.switchToState();	
//		// Abmelden der Grafik, wenn erforderlich
//		this.getCntr().disposedApplicationScreen();
	}

	// ---- Selbstverwaltung -----------------------------------------------------
	/** serialVersionUID */
	@SuppressWarnings("unused")
	private final static long serialVersionUID = VERSION;
	/** logger */
	@SuppressWarnings("unused")
	private final static Logger logger = Logger.getLogger(Editor.class.getPackage().getName());
	
	/**
	 * Editorkomponente von APolGe
	 * 
	 * @param preload
	 *            ist Thread, der Daten und Tabellen vorläd. Ist der Vorgang nicht
	 *            abgeschlossen, wartet der Editor mit der weitere Bearbeitung.
	 * @param props
	 *            ist Properties. Dort ist enthalten, ob GUI oder Console gestarten
	 *            werden soll.
	 */
	public Editor(final PreLoad preload, final Properties props) {
		super(preload, props);
		this.setName("Editor");
		// Starten des Threads
		this.start();
	}

}
