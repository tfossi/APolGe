/** 
 * CreditMenu.java
 * Branch ces
 * APolGe
 * tfossi-team
 * licence GPLv3 
 */
package tfossi.apolge.ces.subs.credit;

import tfossi.apolge.common.hci.*;

import static tfossi.apolge.common.constants.ConstValue.*;
import static tfossi.apolge.common.constants.ConstValue.NTAB;
import static tfossi.apolge.common.constants.ConstValueExtension.*;

import org.apache.log4j.Logger;

import tfossi.apolge.common.cmd.ICmd;
import tfossi.apolge.common.hci.AMenu;
import tfossi.apolge.common.hci.IStateContext;
import tfossi.apolge.io.ContentString;
import tfossi.apolge.io.Screen;

/**
 * Zustandklasse zu Menus und Screen: Creditmenu Console Controller für das Creditmenü
 * Viewer ist von hier aus auzusprechen.
 * 
 * @author tfossi
 * @version 13.08.2014
 * @modified -
 * @since Java 1.6
 */
public class CreditMenu extends AMenu {

	{	if (LOGGER)
		System.out.println(this.getClass().getSimpleName()+" V" + serialVersionUID);
	}
	
	@Override
	public synchronized final void setInformation(String s, Screen scr) {
		this.setInformation(this.getStateContext().getCntr(), scr,
				new ContentString(s), true, false, false);
	}
	 /**
	 * Constructor
	 *
	 * @param fromState
	 * Enthält das aufrufende (==instanzierende) Menu. Ein
	 * callerIn-Wert von {@code null} wird das Programm beim
	 * Rücksprung beendet.
	 * @param statecontext
	 * Enthält die Rootinstanz mit den Konstanten usw.
	 * @since 0.00.034
	 */
	public CreditMenu(final AMenu fromState, final IStateContext statecontext) {
		super(fromState, statecontext, GRAYEDCREDITMENU);

		if(LOGGER) logger.info("Beziehe das Datenmodell des Servers");
		this.setModel(CreditModel.getInstance());
		if(LOGGER) logger.info("Baue Viewer des Servers");
		// Instanziert den View des MVC mit sich selber als Observable
		this.setViewer(new CreditView(this.getStateContext().getCntr()));
	}

	//
	// // ---- Command Pattern
	// // ------------------------------------------------------
	//
	// /*
	// * (non-Javadoc)
	// *
	// * @see name.tfossi.apolge.command.IReceiver#first()
	// */
	// @Override
	// public final void first() {
	// // this.nextView = new CreditViewRoot(this.menuCommands);
	// // if(LOGGER) logger.debug("MVC::\tVerlasse View: [" +
	// // this.getView().getClass().getSimpleName()
	// // + "] " + ConstValue.NTAB + "\tWechsle zu: ["
	// // + this.nextView.getClass().getSimpleName() + "]");
	// // this.getView().setExitSWTView();
	// }
	//
	// /*
	// * (non-Javadoc)
	// *
	// * @see name.tfossi.apolge.hci.AMenu#root()
	// */
	// @Override
	// public final void root() {
	// assert false;
	// }
	//
	// /*
	// * (non-Javadoc)
	// *
	// * @see name.tfossi.apolge.command.IReceiver#show()
	// */
	// public void showGui(final String which) {
	// ErrApp.NI.erraufruf(logger, "");
	// }
	//
	// ---- State
	// -----------------------------------------------------------------

	@Override
	public final void actionState() {
		// Anmelden an den Screen Message, GenInfo und Central. Sobald sich
		// dort etwas
		// ändert, wird das Menu informiert. Damit kann die Bildschirmausgabe
		// erfolgen.
		// Da Singleton, sind die Daten in den Klassen selber enthalten und
		// ändern sich
		// zwischen den Menüs nicht!
		// ((AModel) this.getModel()).addObserver(this);

		this.getStateContext().getCntr().initMenuWidgets(this);
		if(LOGGER) logger.debug("STATE::STATECONTEXT\tKontrolle an [" + this.getClass().getSimpleName()
				+ "] übergeben.");

		this.initiate(CreditModel.getInstance(), new CreditView(this.getStateContext().getCntr()));

		((AModel) this.getModel()).addObserver(this);
		// Die Daten sind individuell zu den Menus und daher müssen sich die
		// Menüs
		// jedesmal neu an den Screenern angemeldet werden.
		// this.getModel().addCentralObserver(this);
		// IGrfCntr grfcntr = this.getStateContext().getGrfCntr();

		this.getView().initiate(this, this.getStateContext());

		// Bei den Befehlen den Receiver eintragen
		for (ICmd ac : this.getModel().getApplCmdList()) {
			ac.setReceiver(this, this.GRAYEDMENU);

		}
		// // Bei den Befehlen den Receiver eintragen
		for (ICmd ac : this.getModel().getStateCmdList()) {
			ac.setReceiver(this, this.GRAYEDMENU);
		}

		// ---- TIPP: VOREINSTELLUNGEN BEIM STATEWECHSEL
		// -------------------------
		// Ausgeführt bei jedem State-Wechsel nach EditorMenu
		// this.setInformation(session, Screen.C, new String[] {
		// "C: Editormenü (actionState)" }, false, false);
		// this.setInformation(session, Screen.M, new String[] {
		// "M: Editormenü (actionState)" }, false, false);
		// this.setInformation(session, Screen.MI, new String[] {
		// "MI: Editormenü (actionState)" }, false, false);
		// this.setInformation(session, Screen.VI, new String[] {
		// "VI: Editormenü (actionState)" }, false, false);
		// -----------------------------------------------------------------------

		// Informiere den Viewer über die geänderten Daten
		// Da ist die gro�e Eingabeschleife!
		this.notifyViewer(this.getView());

		// Hier komme ich vorbei, wenn ich das Menu wechsle /UP
		this.getStateContext().getCntr().unvisibleMenuScreen();
		// this.getStateContext().getCntr().disposedMenuScreen();

		if(LOGGER) logger.trace("STATE::STATECONTEXT\tVerlasse " + this.getClass().getSimpleName() + NTAB
				+ "Kontrolle wieder an StateContext \""
				+ this.getStateContext().getClass().getSimpleName() + "\" zurück.");
	}

	//	
	// private Object getCentralInformation(CreditView av) {
	// return av.outputCentralInformation();
	// }

	// /*
	// * (non-Javadoc)
	// *
	// * @see name.tfossi.apolge.hci.IMenu#getCentralInformation(java.lang.Object)
	// */
	// public Object[] getCentralInformation(Object view) {
	// return new Object[]{this.getCentralInformation((CreditView) view)};
	// }

	// ---- Command Pattern
	// ------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * 
	 * @see name.tfossi.apolge.command.IReceiver#first()
	 */
	@Override
	public final void first() {
		this.setInformation(this.getStateContext().getCntr(), Screen.M, new ContentString(
				"NEUE INFO"), false, false, false);
		this.setInformation(this.getStateContext().getCntr(), Screen.MI, new ContentString(
				"Info in MI"), false, false, false);
		this.setInformation(this.getStateContext().getCntr(), Screen.C, new ContentString(
				"B A M B U L E"), false, false, false);

		// this.setInformation(null, Screen.M, "NEUE INFO", true, false);
		// this.setInformation(null, Screen.MI, "Info in MI", true, false);
		// this.setInformation(null, Screen.C, "B A M B U L E", true, false);
		// this.nextView = new ServerView();
		// if(LOGGER) logger.debug("MVC::\tVerlasse View: ["
		// + this.getView().getClass().getSimpleName() + "] " + NTAB
		// + "\tWechsle zu: [" + this.nextView.getClass().getSimpleName()
		// + "]");
		// this.getView().setExitSWTView();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see name.tfossi.apolge.hci.AMenu#root()
	 */
	@Override
	public final void root() {
		this.nextView = new CreditView(this.getStateContext().getCntr());
		if(LOGGER) logger.info("MVC::\tVerlasse View [" + this.getView().getClass().getSimpleName() + "]!");
		this.getView().setExitSWTView();

	}

	// --- Recorder
	// --------------------------------------------------------------
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see name.tfossi.apolge.hci.AbstractMenu#playRecorder(java.lang.String)
//	 */
//	public final void playRecorder(final String macroname) {
//		if(LOGGER) logger.trace("Enter ");
////		super.playRecorder(macroname, this.getClass());
//		if(LOGGER) logger.trace("Exit ");
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see name.tfossi.apolge.hci.AbstractMenu#record()
//	 */
//
//	public final void recRecorder(final String macroname, final String comment) {
//		if(LOGGER) logger.trace("Enter ");
//		super.recRecorder(macroname, comment, this.getClass());
//		if(LOGGER) logger.trace("Exit ");
//	}

	// ---- Selbstverwaltung
	// -----------------------------------------------------
	/** serialVersionUID */
	private final static long serialVersionUID = VERSION;
	/** logger */
	private final static Logger logger = Logger.getLogger(CreditMenu.class.getPackage().getName());


}
