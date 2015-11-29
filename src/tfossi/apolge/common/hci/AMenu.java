/**
 * AMenu.java
 * Branch hci
 * APolGe
 * tfossi-team
 * licence GPLv3  
 */
package tfossi.apolge.common.hci;

import static tfossi.apolge.common.constants.ConstValue.LOGGER;
import static tfossi.apolge.common.constants.ConstValue.NTAB;
import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;

import java.util.Arrays;
import java.util.List;
import java.util.Observable;

import org.apache.log4j.Logger;

import tfossi.apolge.common.cmd.CommandArray;
import tfossi.apolge.common.cmd.CommandList;
import tfossi.apolge.common.cmd.ICmd;
import tfossi.apolge.common.hci.menu.AMenuReceiver;
import tfossi.apolge.common.hci.menu.IGeneralReceiver;
import tfossi.apolge.io.IContent;
import tfossi.apolge.io.Screen;
import tfossi.apolge.io.screenfactory.Cntr;
import tfossi.apolge.io.screenfactory.NotifyScreens;

/**
 * Stellt für das State Pattern die Methoden für State zur Verfügung. Als State
 * Context fungieren die Applicationen Editor, Server und Client.<br>
 * <br>
 * 
 * Basisklasse für die Zustandsklassen und MVC-Pattern. Definiert die
 * Schnittstellen und allgemeinen Befehle für die konkreten Subklassen.<br>
 * AbstractMenu Im MVC-Pattern bildet diese Klasse den Controller. Der Viewer
 * ist als {@link AView}, das Datenmodel ist als link AModel} benannt.
 * AbstractMenu definiert sich auch als Receiver für die AbstractCmd-Klassen
 * (Cmd...) und bietet eine Basis-Methoden für die allgemeingültigen
 * Muss-Befehle, wie z.B Rekorder und Back.<br>
 * <br>
 * 
 * 
 * @.pattern State: abstract state
 * @.pattern Observer: observable (für den Viewer)
 * @.pattern MVC: abstract controller <br>
 *           .pattern Command: receiver
 * @see IStateContext
 * @see IState
 * see AModel
 * @see AView
 * @see IGeneralReceiver
 * @author tfossi
 * @version 12.08.2014
 * @modified -
 * @since Java 1.6
 */
public abstract class AMenu extends AMenuReceiver  
{

	/*
	 * (non-Javadoc)
	 * 
	 * @see name.tfossi.apolge.hci.IMenu#getApplCmdList()
	 */
	@Override
	public CommandList getApplCmdList() {
		List <String> l = Arrays.asList(this.GRAYEDMENU);
		CommandList rc = new CommandArray();
		for(ICmd c : this.getModel().getApplCmdList()){
			if(l.contains(c.getCode())) continue;
			rc.add(c);
		}
		return rc;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see name.tfossi.apolge.hci.IMenu#getStateCmdList()
	 */
	@Override
	public CommandList getStateCmdList() {
		return this.getModel().getStateCmdList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see name.tfossi.apolge.hci.IMenu#getParaCmdList()
	 */
	@Override
	public CommandList getParaCmdList() {
		return this.getModel().getParaCmdList();
	}
//
	// ---- Information to Viewer-Observer-Pattern ----------------------------

	/**
	 * Viewer aktualisieren, bis das Menu verlassen wird. Der Aufruf erfolgt aus
	 * {@link #actionState()} im konkreten Menu. FIXME BAUSTELLE!
	 * 
	 * @param viewer
	 *            ist der aktuelle Viewer des aktiven Menus
	 * @see Screen
	 */
	protected final void notifyViewer(final IView viewer) {
//		if(LOGGER) logger.trace("Informiere den Viewer \"" + viewer
//				+ "\" über die Anforderung der Datenausgabe.");
//		// exitmenu verhindert bei <code>true</code>, dass das aktuelle Menu
//		// verlassen wird. [Default]
//		this.clearExitMenu();
//		this.getView().clearExitSWTView();
//
//		this.getStateContext().getCntr().notifyViewer(viewer, this);
	}

	// ---- Receiver ----------------------------------------------------------

	/**
	 * TODO Comment
	 * @param s -
	 * @param scr -
	 * @modified - 
	 */
	public abstract void setInformation(String s, Screen scr);
	

	/* (non-Javadoc)
	 * @see tfossi.apolge.common.hci.IMenu#setInformation(tfossi.apolge.io.screenfactory.Cntr, tfossi.apolge.io.Screen, tfossi.apolge.io.IContent, boolean, boolean, boolean)
	 */
	@Override
	public void setInformation(Cntr cntr, Screen scr,
			IContent content, boolean notify, boolean delete, boolean input) {
//		this.getModel().setInformation(cntr,
//				this, scr, content, notify, delete, input);
	}

	// ---- Observer Pattern --------------------------------------------------

	/**
	 * Aufruf des Viewers (Observer), wenn sich die Daten geändert haben.
	 * 
	 * @param o
	 *            ist das Datenmodel
	 * @param screens
	 *            sind die Screen, die betroffen sind
	 */
	@Override
	public final void update(final Observable o, final Object screens) {
		this.informObserver(o, screens);
	}


	/* (non-Javadoc)
	 * @see tfossi.apolge.common.hci.IMenu#informObserver(java.util.Observable, java.lang.Object)
	 */
	@Override
	public final void informObserver(final Observable o, final Object screens) {

		if(LOGGER) logger.debug("IO::Observable\t\"" + o.getClass().getSimpleName()
				+ "\" informiert den Viewer über neue Daten." + NTAB
				+ "Neuaufbau!" + NTAB + Arrays.asList( ((NotifyScreens) screens).scr));
		if (this.countObservers() == 0)
			assert false;
		// Damit notify wirkt
		this.setChanged();
		// Observer ist AView
		this.notifyObservers(screens);
		// Löscht Markierung
		this.clearChanged();
	}

	// ---- Selbstverwaltung --------------------------------------------------
	/** serialVersionUID */
	@SuppressWarnings("unused")
	private final static long serialVersionUID = VERSION;
	/** logger */
	private final static Logger logger = Logger
			.getLogger(AMenu.class.getPackage().getName());

	/**
	 * Stellt die Methoden für {@link IState} bereit , IMenu und IReceiver
	 * bereit
	 * 
	 * @.post this.caller = callFromMenu: Das aufrufende Menu/State<br>
	 * @.post this.sc = statecontext: IStateContext<br>
	 * @param fromState
	 *            <ul>
	 *            <li>!=null: Enthält das aufrufende (==instanzierende) Menu.
	 *            Es ist der Aufrufer, der bei {@link #back()} wieder
	 *            angesprungen wird.</li>
	 *            <li>==null: Ist das initiale Menu und das Programm wird beim
	 *            Rücksprung {@link #back()} beendet.</li>
	 *            </ul>
	 * @param statecontext
	 *            Enthält den {@link IStateContext StateContext}, der die
	 *            Statesteuerung übernimmt. Siehe auch
	 *            {@link #getStateContext()}
	 * @param grayedMenu -
	 */
	public AMenu(final AMenu fromState, final IStateContext statecontext, final String[] grayedMenu) {
		super(fromState, statecontext, grayedMenu);
	}

	/**
	 * Initiiere den Model/Viewereinsatz
	 * 
	 * @param model -
	 * @param view -
	 */
	protected void initiate(IModel model, IView view) {
//		this.setModel(model);
//
//		// Anmelden am Modell. Sobald sich Daten im Modell ändern, wird dies dem
//		// Menu mitgeteilt. Damit kann die Bildschirmausgabe, wenn das Menu die
//		// informationen an den Viewer weiterleitet.
//		if(LOGGER) logger.debug("MVC::OBSERVER\tAnmelden des Menus als Observer am Modell");
//		((AModel) model).addObserver(this);
//
//		IView doView = (model.getViewer() == null ? view : model.getViewer());
//		if(LOGGER) logger.debug("MVC::VIEWER\tBaue Viewer ["
//				+ doView.getClass().getSimpleName() + "] von ["
//				+ this.getClass().getSimpleName() + "].");
//		// Instanziert den View des MVC mit sich selber als Observable
//		this.setViewer(doView);
	}

	/** Schaltet auf Hauptscreen des Menus */
	abstract protected void root();
//
//	/** @return Liefert SimpleName() der Klasse */
//	@Override
//	public final String toString() {
//		return this.getClass().getSimpleName();
//	}
}

//
//
//

// Die Schleife bei SWT-Einsatz

// while (this.exitmenu==false){
// // Markiert, dass das Object sich geändert hat
// this.setChanged();
// // if (this.countObservers() == 0) {
// // throw new APolGeException(this, "actionState: SWT", logger,
// // ErrApp.ABSTRACTMENU_NOOBSERVER);
// // }
// //
// // if(LOGGER) logger.debug("Menu löst [update] für den Viewer zur Anzeige aus!");
// // Aufruf des Viewers. Nachrichtlich, welche Screen upgedated werden
// sollen
// this.notifyObservers(this.notifyScreens); // Observer ist AbstractView
// this.clearChanged(); // Löscht Markierung
// }
// // assert false;
// // // Solange es ein interer Befehl ist oder Shell nicht verlassen
// // wird

// V222222222222222222222222222222222222
// while (!shell.isDisposed() /* && !this.exitSWTView */) {
// if (!display.readAndDispatch())
// display.sleep();
// }
// exit no dispos no true
// y x false
// x y false
//
// assert false : !this.exitmenu + " && " + !shell.isDisposed() + " = "
// // + (!this.exitmenu && !shell.isDisposed());
// while (!this.getExitMenu() && !shell.isDisposed()) {
//
// if(LOGGER) logger.trace("exitmenu-Loop" + NTAB + NTAB + "PING" + NTAB);
//
// // Markiert, dass das Object sich geändert hat
// this.setChanged();
//
// // Markiert, dass das Object sich geändert hat
// this.setChanged();
// // Informiert den Observer ist AbstractView
// this.notifyObservers(whichScreen);

// Löscht Markierung
// this.clearChanged();
//
// if(LOGGER) logger.trace((!this.getExitMenu() && !shell.isDisposed()) ? "SCHLEIFE"
// : "VERLASSE SCHLEIFE");

// assert this.getExitMenu() : this.getExitMenu();
// assert !this.getExitMenu() : this.getExitMenu();
// }
// if(LOGGER) logger.trace("exitmenu-Loop" + NTAB + NTAB + "ENDE" + NTAB);


//if (this.getStateContext().isGrafik()) {

////	 final SWTGrafik swt =
////	 this.getStateContext().getGrfCntr().getGrafik();
//	// Die Schleife bei SWT-Einsatz
//	// Nur bei Back oder neues Menu!
//
//	 Screen[] scr = getScreens(APPLICATIONSCREENS, MENUSCREENS,
//	 VIEWSCREENS);
//	while (!this.getExitMenu()
//			&& !this.getStateContext().getCntr().isDisposed()) {
//		logger
//				.debug("\n--------------\nSTART VIEWLOOP\n--------------\n");
//		// Aufruf des Viewers. Nachrichtlich, welche Screen upgedated
//		// werden sollen.
//		// Markiert, dass das Object sich geändert hat.
//		this.informObserver(this, scr);
//		// Bei GUI-Netrieb geht es hier erst dann weiter, wenn das
//		// Fenster geschlossen oder das Menu verlassen wurde.
//		//
//		logger
//				.debug("\n--------------\nEND VIEWLOOP\n--------------\n");
//
//		// Hier komme ich vorbei, wenn ich den View wechsle /UP
//		this.getStateContext().getCntr().disposedViewScreen();
//
//		if (this.nextView != null) {
//			if(LOGGER) logger.info("MVC::VIEWER\tBaue Viewer "
//					+ this.nextView.getClass().getSimpleName()
//					+ " des " + this.getClass().getSimpleName() + " .");
//			this.setViewer(this.nextView);
//			this.nextView = null;
//			this.getView().initiate(this, this.getStateContext());
//			// Bei den Befehlen den Receiver eintragen
//			for (ICmd ac : this.getModel().getStateCmdList()) {
//				ac.setReceiver(this);
//			}
//		}
//	}
//} else {

//	// Die Schleife bei Consolen-Einsatz
//	// Nur bei Back oder neues Menu!
//
//	Screen[] scr = CONSOLENSCREENS;
//
//	while (this.getExitMenu() == false) {
////	while (!this.getExitMenu()
////			&& !this.getStateContext().getGrfCntr().isDisposed()) {
//		logger
//				.debug("\n--------------\nSTART VIEWLOOP\n--------------\n");
//		// Aufruf des Viewers. Nachrichtlich, welche Screen upgedated
//		// werden sollen.
//		// Markiert, dass das Object sich geändert hat.
//		this.informObserver(this, scr);
//		// Bei Consolenbetrieb geht es hier erst dann weiter, wenn ein
//		// Befehl eingegeben wurde.
//		logger
//				.debug("\n--------------\nEND VIEWLOOP\n--------------\n");
//		if (this.nextView != null) {
//			if(LOGGER) logger.info("MVC::VIEWER\tBaue Viewer "
//					+ this.nextView.getClass().getSimpleName()
//					+ " des " + this.getClass().getSimpleName() + " .");
//			this.setViewer(this.nextView);
//			this.nextView = null;
//			this.getView().initiate(this, this.getStateContext());
//			// Bei den Befehlen den Receiver eintragen
//			for (ICmd ac : this.getModel().getStateCmdList()) {
//				ac.setReceiver(this);
//			}
//		}
//	}
//}

// public void setInformation(IGrfCntr grfcntrn, Screen scr, Widget
// content, boolean
// notify, boolean delete) {
// if(session==null){
// this.getModel().setInformation(this.getStateContext().getGrfCntr(), this,
// scr,
// content, notify, delete);
// }else{
// this.getModel().setInformation(session, this, scr, content, notify,
// delete);
// }
// }

///*
// * (non-Javadoc)
// * 
// * @see name.tfossi.apolge.hci.IMenu#getInformation(name.tfossi.apolge.general.screen.Screens )
// */
//public String[] getInformation(Screen scr) {
//	if(LOGGER) logger.trace("I/O:: Ausgabe von \"" + scr);
//	return this.getModel().getInformation(
//			this.getStateContext().getCntr(), this, scr);
//}

//
///*
// * (non-Javadoc)
// * 
// * @see name.tfossi.apolge200.common.hci.IState#isEmpty()
// */
//public boolean isEmpty() {
//	return this.getStateContext().isEmpty();
//}
