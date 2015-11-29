/**
 * AView.java
 * Branch hci
 * APolGe
 * tfossi-team
 * licence GPLv3  
 */
package tfossi.apolge.common.hci;

import static tfossi.apolge.common.constants.ConstValue.*;
import static tfossi.apolge.common.constants.ConstValueExtension.*;

import java.util.Arrays;
import java.util.Observable;

import org.apache.log4j.Logger;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Widget;

import tfossi.apolge.common.cmd.ACmd;
import tfossi.apolge.common.cmd.CommandArray;
import tfossi.apolge.common.cmd.CommandList;
import tfossi.apolge.io.IContent;
import tfossi.apolge.io.Screen;
import tfossi.apolge.io.screenfactory.Cntr;
import tfossi.apolge.io.screenfactory.NotifyScreens;

/**
 * Abstrakter Viewer. Ist Basisklasse für die konkreten Viewer<br>
 * Der Viewer ist in Gruppen eingeteilt: <li>Der Centralscreen ist die Hauptanzeige</li>
 * <li>Der Befehlsscreen mit seinen zwei Gruppen: Allgemeine Befehle und spezielle auf
 * die Situation bezogene Befehle</li> <li>Der Informationsscreen mit den zwei Gruppen:
 * Allgemeine Informationen und spezielle auf die Situation bezogene Informationen</li>
 * <li>Nachrichtenscreen</li> <li>Titel ist für die Fensterüberschriften</li>
 * 
 * @.pattern MVC: abstract viewer
 * @.pattern Observer: observer (Anmeldung am AMenu)
 * @see AMenu
 * see AModel
 * @see ACmd
 * @author tfossi
 * @version 12.08.2014
 * @modified -
 * @since Java 1.6
 */
public abstract class AView implements IView {
	/** Speichert die IO-Fassade */
	private final Cntr cntr;

	/** Counter, um Mehrfachdarstellungen über cnt==1 zu vermeiden. */
	private static int cnt = 0;

	// ---- --------------------------------------------------------------------

	/** Befehle des Viewers */
	protected final CommandList viewcommandlist;

	/*
	 * (non-Javadoc)
	 * 
	 * @see name.tfossi.apolge.hci.IView#getStateCmdList()
	 */
	@Override
	public final CommandList getViewCmdList() {
		return this.viewcommandlist;
	}

	/* (non-Javadoc)
	 * @see tfossi.apolge.common.hci.IView#getParaCmdList()
	 */
	@Override
	public final CommandList getParaCmdList() {
		return null;
	}
//
//	//
//	// /**
//	// * Zeigt an, das die Console {@link #updateConsole(IMenu, Object[])} in use ist
//	// */
//	// private static boolean console = false;
//
//	/** Speicher für StateContext */
//	protected IStateContext statecontext;
//
//	// ---- Verlasse den Viewer ( Statewechsel) -------------------------------
//
	/** true: Viewer verlassen */
	protected boolean exitSWTView;

	/* (non-Javadoc)
	 * @see tfossi.apolge.common.hci.IView#setExitSWTView()
	 */
	@Override
	public final void setExitSWTView() {
		this.exitSWTView = true;
	}

	/* (non-Javadoc)
	 * @see tfossi.apolge.common.hci.IView#clearExitSWTView()
	 */
	@Override
	public final void clearExitSWTView() {
		this.exitSWTView = false;
	}

	/* (non-Javadoc)
	 * @see tfossi.apolge.common.hci.IView#isExitSWTView()
	 */
	@Override
	public boolean isExitSWTView() {
		return this.exitSWTView;
	}
//
//	// ---- Eingabe von Daten ----------------------------------------------------
//
//	// /**
//	// * Eingabe des Befehls. Dabei wird geprüft, dass der Befehl mit einem Befehl aus
//	// der
//	// * Vorgabeliste übereinstimmt.
//	// *
//	// * @param cmds
//	// * Liste der möglichen Befehle
//	// * @param menu
//	// * das aufrufende Menu
//	// * @return Befehl und Parameter in AbstractCmd
//	// * @see ACmd
//	// */
//	// private final ICmd inputOrder(final CommandList cmds, final String menu) {
//	// ICmd cmd = null;
//	// try {
//	// cmd = Key.eingabe(cmds, menu).clone();
//	// if(LOGGER) logger.debug("Der Befehl lautet " + cmd + NTAB
//	// + (cmd == null ? "" : "mit Parameter: " + cmd.getParameter().toString()));
//	// } catch (NullPointerException e) {
//	// if(LOGGER) logger.debug("ABBRUCH");
//	// } catch (Exception e) {
//	// ErrApp.NDEF.erraufruf(logger, "");
//	// }
//	// return cmd;
//	// }
//
	// ---- update (Observer): Aufforderung zur Aktualisierung der Datenanzeige --

	/**
	 * Observeraufruf durch den Observable (Menu). Der Bildschirm wird dargestellt und
	 * Viewer wartet auf weitere Eingaben. Die Konsolensteuerung ist unter
	 * {@link #updateConsole} zu finden.
	 * 
	 * @param menuIn
	 *            ist das aufrufende Menu
	 * @param notifyScreens
	 *            Schalter, die festlegen, welcher Screen ein update erhalten soll. Ist
	 *            vom Typ <code>Screen</code>
	 * @see AMenu#actionState()
	 * @see Screen
	 */
	@Override
	public void update(final Observable menuIn, final Object notifyScreens) {
		cnt++;
		NotifyScreens ns = (NotifyScreens) notifyScreens;
		final IMenu menu = (IMenu) menuIn;
		if(LOGGER) logger.debug("IO::Observer\tAnforderung auf Aktualisierung der Anzeige erhalten." + NTAB
				+ Arrays.asList( ns.scr));
		// #### this.show(menu, (Screen[]) notifyScreens);
		this.cntr.show(menu, ns, this.commands);
		this.cntr.update(menu, ns, this.commands, cnt);
		// if (this.statecontext.isGrafik()) {
		// // Leite über zum spezifischen Teil "Grafische Steuerung"
		// this.updateGUI(menu, notifyScreens, cnt==1);
		// } else {
		//
		// // Leite über zum spezifischen Teil "Textkonsole"
		//
		// if(LOGGER) logger.info("IO::Console Start der Aus- und Eingabe." + NTAB + "Menu(State): "
		// + menu.getClass().getSimpleName() + NTAB
		// + "Die Eingabe wird nur verlassen, wenn" + NTAB
		// + "die Order syntaktisch in Ordnung ist."+NTAB+
		// cnt);
		// this.updateConsole(menu, null);
		// }
		cnt--;
	}

	/** Speichert die Liste der Commands für Konsole */
	protected CommandList commands = new CommandArray();

	 /**
	 * Die Eingabe und Ausgabe der Konsole
	 *
	 * @param menu
	 * ist das aufrufende Menu
	 * @param screens
	 * Schalter, die festlegen, welcher Screen ein update erhalten soll.
	 */
	 @SuppressWarnings("unused")
	 private final void updateConsole(final IMenu menu, Object screens) {
//	//
//	// // CommandList commands = new CommandArray();
//	//
//	// // Anzeige der Screen
//	// // for (Screen scr : SCREENS) {
//	// // 1. Beziehe den fixen Inhalt
//	// // 2. Beziehe die Parameter
//	// // 3. Setze die Parameter in 1. ein
//	// // 4. Zeige das Ganze
//	// // Cnlout.toutLeftText(scr.getTitel());
//	// // IContent screen = scr.getcontent();
//	// // if (screen != null) {
//	// // String[] str = screen.getInformation();
//	// // if (str != null) {
//	// // for (String row : str)
//	// // Cnlout.touttxt(row);
//	// // } else {
//	// // Cnlout.touttxt(" ");
//	// // }
//	// // if (screen instanceof CommandList) {
//	// // CommandList cmds = (CommandList) screen;
//	// // if (cmds != null)
//	// // commands.addAll(cmds);
//	// // }
//	// // }
//	// // }
//	// // Ausgabe der Abschlußlinie
//	// // Cnlout.toutFrameRow();
//	//
//	// // fixen Das ist zu überprüfen: Nicht jede Screendarstellung kann
//	// // einen neuen
//	// // updateConsole starten, wenn der alte Befehl nicht abgearbeitet ist:
//	// // Stack
//	// // Overflow
//	// // Es geht um Input Command
//	// if (AView.console) {
//	// if(LOGGER) logger.debug("Input Order ist in USE");
//	// return;
//	// }
//	// // Die Befehlseingabe
//	// ICmd cmd = null;
//	// try {
//	// AView.console = true;
//	// if(LOGGER) logger.info("Gesamtliste der Commands:" + NTAB + this.commands.toString());
//	// if ((cmd = this.inputOrder(this.commands, menu.toString())) == null)
//	// return;
//	// if(LOGGER) logger.info("Den Befehl [" + cmd + "] ausführen.");
//	//
//	// // Befehl ausführen ...
//	// cmd.command();
//	// // ...!
//	//
//	// if(LOGGER) logger.trace("Der Befehl [" + cmd + "] ist ausgeführt.");
//	// AView.console = false;
//	// } catch (NullPointerException e) {
//	// ErrApp.NDEF.erraufruf(logger, "Controller: " + menu + NTAB + "Cmd       : " +
//	// cmd);
//	// }
	 }

	/* (non-Javadoc)
	 * @see tfossi.apolge.common.hci.IView#buildVIWidget(org.eclipse.swt.widgets.Group)
	 */
	@Override
	public Widget buildVIWidget(Group basegrp) {
////		if(LOGGER) logger.debug("SWT::" + this.toString() + "\tNeue Widgetinstanz auf [" + basegrp
////				+ "] anlegen");
////		List rc = new List(basegrp, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL);
////
////		FormData data = new FormData();
////			data.right = new FormAttachment(100, 0);
////		data.left = new FormAttachment(0, 0);
////		data.top = new FormAttachment(0, 0);
////		data.bottom = new FormAttachment(100, 0);
////		((Control) rc).setLayoutData(data);
////		rc.setItems(new String[] { "HALLO from SCREENS" });
////		rc.setTopIndex(rc.getItemCount() - 3);
//		
//		ErrApp.NI_W.erraufruf("SWT::" + this.toString() + "\tNeue Widgetinstanz auf [" + basegrp
//				+ "] anlegen");
		return null;
	}	

	/* (non-Javadoc)
	 * @see tfossi.apolge.common.hci.IView#get_GuiContent(org.eclipse.swt.widgets.Widget, tfossi.apolge.io.Screen)
	 */
	@Override
	public IContent get_GuiContent(Widget base, Screen scr) {		
//		ErrApp.NI_X.erraufruf("SWT::" + this.toString() + "\tContent abholen"+NTAB
//				+"für "+scr+NTAB
//				+"auf Base "+base);
		return null;
	}
	
	
	// ---- Selbstverwaltung --------------------------------------------------
	/** serialVersionUID */
	@SuppressWarnings("unused")
	private final static long serialVersionUID = VERSION;
	/** logger */
	private final static Logger logger = Logger.getLogger(AView.class.getPackage().getName());

	/**
	 * Viewerklasse im MVC. Stellt die Basics von IView. Fügt den Viewer als Observer zum
	 * Controller ({@link AMenu}) hinzu.
	 * 
	 * @.post Der Controller (Menu) bekommt den Viewer als Observer angemeldet
	 * @.post im IStateContext this.sc wird der passende StateContext eingetragen.
	 *        Statecontext ist auch das Menu im MVC
	 * @param cntr
	 *            ist die Fassade zur IO-Factory
	 * @param listViewCommands
	 *            Enthält die Liste der Commands dieses Viewers
	 */
	public AView(Cntr cntr, CommandList listViewCommands) {
		this.cntr = cntr;
		this.viewcommandlist = listViewCommands;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see name.tfossi.apolge.hci.IView#initiate(name.tfossi.apolge.hci.IMenu,
	 * name.tfossi.apolge.hci.IStateContext)
	 */
	@Override
	public IView initiate(IMenu menu, IStateContext statecontext) {
//		if(LOGGER) logger.debug("MVC::VIEWSCREEN\tStelle Viewscreen [" + this.getClass().getSimpleName()
//				+ "] ein!");
//		this.statecontext = statecontext;
//		this.statecontext.getCntr().initViewWidgets(this, menu);
//		
		return this;
	}
//
//	/** @return Liefert SimpleName() der Klasse */
//	@Override
//	public String toString() {
//		return this.getClass().getSimpleName();
//	}
}

//
// SWTGrafik swt = this.statecontext.getSWTGrafik();
// final Shell shell = swt.getShell();
// final Display display = swt.getDisplay();

// for (Object o : screens) {
// // // 1. Beziehe den fixen Inhalt
// // // 2. Beziehe die Parameter
// // // 3. Setze die Parameter in 1. ein
// // // 4. Zeige das Ganze
// Screen screen = (Screen) o;

// Ob es nötig ist?
// Screen neu anzeigen
// screen.refresh();
// screen.refreshLayout();
// }

// Problem: Datenänderung läuft hier auch durch!
// Die Dispatchschleife läuft noch!

//
// // FIXEN this.updateGUI(am, ss);
//
// // FIXEN && !Recorder.getInstance().isPlay()){
// // SS_GenMenu.open();
// // SS_SpzMenu.open();
// // SS_Message.open();

// --------------------------------------------------

// **
// * Es werden die Widget der Elemente <code>cmds</code> und
// <code>mess</code>
// * zusammengetragen und per <code>seggrp.layout()</code> angezeigt.
// *
// * @param menu
// *
// public final void open(@SuppressWarnings("unused")
// String menu) {
// assert false : " STSSTPP";
// assert false;
// if(LOGGER) logger.trace("Enter ");
// // if(this.cmds!=null){
// // if(LOGGER) logger.finest(":this.cmds!=null");
// // for(AbstractCmd ac : (CommandList)this.cmds.get(menu)){
// //
// // ac.buildWidget(this.seggrp);
// // }
// // }else
// // if(LOGGER) logger.finest(":ES LIEGEN KEINE CMDS VOR.");
// // if(this.mess!=null){
// // if(LOGGER) logger.finest(":this.mess!=null");
// this..mess.buildSWTWidget(StdLayout.M);
// // }
// try {
//
// if(LOGGER) logger.trace("this.basegrp=" + this.basegrp);
// if(LOGGER) logger.trace("this.basegrpchilds=" + this.basegrp.getChildren());
// for (Control cntrl : this.basegrp.getChildren()) {
//
// if (!(cntrl instanceof Composite))
// continue;
// Composite c = (Composite) cntrl;
// if(LOGGER) logger.trace("cntrl=" + c);
// if(LOGGER) logger.trace("cntrl=" + c.getChildren());
// for (Control cntrl2 : c.getChildren()) {
// if(LOGGER) logger.trace("CC2=" + cntrl2);
// // cntrl2.pack();
// // if(LOGGER) logger.finest(":cntrl="+cntrl2.getChildren());
// }
// c.layout();
// }
// this.basegrp.layout();
// } catch (Exception e) {
// e.printStackTrace();
// assert false : " STSSTPP";
// }
// if(LOGGER) logger.trace("Exit " + this.name() + " open()");
// }

// -----------------------------------------------

// // SS_Central.open();
// // // SS_GenInfo.open();
// // // new Screen().buildScreen(this.commands, am,
// // // this.getSWT().getGeneralInformationGroup(), SS_GenInfo.is(arg) );
// //
// // showHeaderRow(am, SS_Header.is(arg));
// //
// //
// // //loadMessageScreen(o);
// // // if (ScreenSets.SS_DTScrn.is(arg)) showDateTimeScreen(); //o);
// // // // if ((arg & APolGe.INFOSPECIAL) != 0) updateSpecialInfoScreen(o);
// // // // if ((arg & APolGe.INFOGENERAL) != 0) updateGeneralInfoScreen(o);
// // // // Zeigt Bilder an
// // // // APolGe.shell.open();
// // // // APolGe.log.config("**7**\n\t\tWarte auf
// // Befehlseingabe....exit=="+this.exitviewer+
// // // //
// //
// "\n-------------------------------------------------------------------------------\n");
// // //
// // logger
// // .finer("SWT:Starte die Eingabeschleife.\nDie Eingabeschleife wird nur
// verlassen,
// // wenn\n"
// // + "a. das Fenster geschlossen wird\n"
// // + "b. die Order zum Verlassen des Viewers this.exitviewer=="
// // + "true erteilt wird (Normalfall)\n"
// // + "Hier: "); //+this.exitviewer);
// assert !this.shellloop;
// this.shellloop = true;

// label.setText(System.currentTimeMillis() + "");

// // Fixen: Wenn die Shell schon läuft, dann ist sie nicht noch
// einmal zu
// starten

// if (Recorder.getInstance().isPlay()) {
// this.updateConsole(menu, screens);
// } else {
// if(LOGGER) logger.info("****exitSWTView****" + NTAB + NTAB + "START" + NTAB + NTAB
// + "****exitSWTView****");
// while (!shell.isDisposed() && !this.exitSWTView) {
// if (!display.readAndDispatch())
// display.sleep();
// }
// if(LOGGER) logger.info("****exitSWTView****" + NTAB + NTAB + "ENDE" + NTAB + NTAB
// + "****exitSWTView****");
// }
// for (Object o : screens) {

// // // 1. Beziehe den fixen Inhalt
// // // 2. Beziehe die Parameter
// // // 3. Setze die Parameter in 1. ein
// // // 4. Zeige das Ganze
// Screen screen = (Screen) o;
// screen.refresh();
// screen.dispose();
// Screen.SM.dispose();
// Screen.GM.dispose();
// }
// DISPOSE??!!
// // // new Screen().disposeScreen( am,
// // // this.getSWT().getGeneralCommandGroup(), SS_GenMenu, arg);
// // // new Screen().disposeScreen( am,
// // // this.getSWT().getGeneralCommandGroup(), SS_SpzMenu, arg);
// // // if(LOGGER) logger.fine("Verlasse die Eingabeschleife.");
// // //
// // // // if (arg != (APolGe.MESSAGE + APolGe.VIEWEXITYES) )
// // // //
// System.out.println("Beende Viewer "+o.getClass().getSimpleName());
// //
// // SS_GenMenu.dispose();
// // SS_SpzMenu.dispose();
// // // SS_Message.dispose();
// // SS_Central.dispose();
// // //

//
// EditorMenu menu = (EditorMenu) editorMenu;
// // Zur weiteren Bearbeitung
// super.update(menu, notifyScreens);
// if(LOGGER) logger.trace("Exit ");
// Es muss entschieden werden, was angezeigt werden soll."
// Dazu müssen alle Screen-Anforderungen durchgegangen werden.

// fixen: null ist komplettausfbau !if (notifyScreens == null)
// throw new NullPointerException("notifyScreens ist null");
// if(notifyScreens instanceof Screen[])
// Object[] screens = null; //(Object[]) notifyScreens;
// if(LOGGER) logger.debug("Nachricht von " + Arrays.asList(screens));

// /** @return swt-Instanz */
// public final SWTGrafik getSWT() {
// return this.statecontext.getGrfCntr().getGrafik();
// }

// /*
// * (non-Javadoc)
// *
// * @see name.tfossi.apolge.hci.IView#getStateCommandInformation()
// */
// public final String[] getStateCommandInformation() {
// return this.statecommandlist.getInformation();
// }

//
// /*
// * (non-Javadoc)
// *
// * @see
// * name.tfossi.apolge.hci.IView#getStateParameterInformation(name.tfossi.apolge.command
// * .ICmd)
// */
// public final String[] getCmdParameterInformation(@SuppressWarnings("unused") ICmd ic)
// {
// assert false;
// return null;
// }

// //
// /* (non-Javadoc)
// * @see
// name.tfossi.apolge200.common.gui.IBuildSWTWidget#buildSWTWidget(org.eclipse.swt.widgets.Group)
// */
// /**
// * @param group
// * @return
// */
// public Map<String, Widget> buildSWTWidget(@SuppressWarnings("unused") Group group)
// {
// return null;
// }
// /**
// * SWT-GUI aktualisieren. Hier ist die VIEW-Loop enthalten!
// *
// * @param menu
// * @param screens
// */
// private void updateGUI(final IMenu menu, Object screens, boolean io) {
//		
// IGrfCntr grfcntr = this.statecontext.getGrfCntr();
// // grfcntr.refreshScreens((Screen[]) screens, this);
//		
// // SWTGrafik swt = this.statecontext.getGrfCntr().getGrafik();
// // final Shell shell = swt.getShell();
// // final Display display = swt.getShell().getDisplay();
//		
// if (Recorder.getInstance().isPlay()) {
// if(LOGGER) logger.info("IO::GUI\tStart der Aus- und Eingabe." + NTAB
// + menu.getClass().getSimpleName() + NTAB
// + "Der Recorder läuft, die Eingabe wird über Console umgeleitet.");
// this.updateConsole(menu, screens);
// } else if(io){
// if(LOGGER) logger.info("IO::GUI\tStart der Aus- und Eingabe." + NTAB
// + menu.getClass().getSimpleName() + NTAB
// + "Die Eingabe wird nur verlassen, wenn" + NTAB
// + "die Order syntaktisch in Ordnung ist.");
// grfcntr.guiLoop(this);
// }else{
// if(LOGGER) logger.info("IO::GUI\tStart der Aus- und Eingabe." + NTAB
// + menu.getClass().getSimpleName() + NTAB
// + "Die Eingabe ist schon aktiv.");
// }
// }

// /**
// * Anzeige
// *
// * @param editorMenu
// * @param notifyScreens
// */
// protected void showGui(final IMenu menu, final Screen[] notifyScreens) {
// this.grfcntr.show(menu,notifyScreens,this.commands);
// // assert this.statecontext != null;
// // SWT regelt die Anzeige durch Events, Console muss explizit informiert
// // werden
// // if (!this.statecontext.isGrafik())
// // this.showConsole(editorMenu, notifyScreens);
// // else
// // if (this.statecontext.getGUI() != null)
// // this.showSWT(editorMenu, notifyScreens);
// // else
// // this.showConsole(editorMenu, notifyScreens);
// }

// /**
// 
// *
// * @param menu
// * @param notifyScreens
// */
// @SuppressWarnings("unused")
// private void showSWT(final IMenu menu, final Screen[] notifyScreens) {
// if(LOGGER) logger.info("I/O::GUI\tAusgabe von \"" + menu.getClass().getSimpleName() +
// "\" " + NTAB
// + Arrays.asList(notifyScreens));
//		
// IGrfCntr grfcntr = menu.getStateContext().getGrfCntr();
// // assert false;
// for (Screen scr : notifyScreens) {
// // 1. Beziehe den fixen Inhalt
// // 2. Beziehe die Parameter
// // 3. Setze die Parameter in 1. ein
// // 4. Zeige das Ganze
// switch (scr) {
// // case TITEL:
// // String strt = (String) menu.getInformation(scr);
// // break;
// case C:
// grfcntr.activateC_Widget(this.getClass());
// // String[] strc = (String[]) menu.getInformation(scr);
// break;
// case VI:
// grfcntr.activateVI_Widget(this.getClass());
// // String[] strsi = (String[]) menu.getInformation(scr);
// break;
// case MI:
// grfcntr.activateSI_Widget(menu.getClass());
// // menu.getStateContext().getGrfCntr().activateSI_Widget(
// // menu, this, menu.getInformation(scr));
// break;
// case M:
// grfcntr.activateM_Widget();
// // menu, this, menu.getInformation(scr));
// // String[] strm = (String[]) menu.getInformation(scr);
// break;
// // case SCM:
// // String[] strsm = (String[]) menu.getInformation(scr);
// // // this.commands.addAll(menu.getStateCmdList());
// // break;
// // case CPM:
// // // Cnlout.toutFrameRow();
// // // String[] strsp = (String[])menu.getInformation(scr);
// // // if (strsp != null) {
// // // for (String row : strsp)
// // // Cnlout.touttxt(row);
// // // } else {
// // // Cnlout.touttxt(" ");
// // // }
// // // this.commands.addAll(menu.getParaCmdList(ic));
// // break;
// // case MCM:
// // String[] strgm = (String[]) menu.getInformation(Screen.MCM);
// // // this.commands.addAll(menu.getApplCmdList());
// // break;
// default:
// break;
// }
// }
// }
//
// @SuppressWarnings("unused")
// private void showConsole(final IMenu menu, final Screen[] notifyScreens) {
// assert false;
// if(LOGGER) logger.info("I/O::Console Ausgabe von \"" + menu.getClass().getSimpleName() +
// "\" " + NTAB
// + Arrays.asList(notifyScreens));
// Object[] info = null;
// // Anzeige der Screen
// for (Screen scr : notifyScreens) {
// // 1. Beziehe den fixen Inhalt
// // 2. Beziehe die Parameter
// // 3. Setze die Parameter in 1. ein
// // 4. Zeige das Ganze
// switch (scr) {
// case TITEL:
// String strt = (String) menu.getInformation(scr)[0];
// if (strt != null) {
// Cnlout.toutLeftText(strt + "- " + this.getClass().getSimpleName());
// } else {
// Cnlout.touttxt(" ");
// }
// break;
// case C:
// info = menu.getInformation(scr);
// if (info != null) {
// for (Object row : info)
// Cnlout.touttxt((String)row);
// } else {
// Cnlout.touttxt(" ");
// }
// break;
// case VI:
// Cnlout.toutFrameRow();
// info = menu.getInformation(scr);
// if (info != null) {
// for (Object row : info)
// Cnlout.touttxt((String)row);
// } else {
// Cnlout.touttxt(" ");
// }
// break;
// case MI:
// Cnlout.toutFrameRow();
// info = menu.getInformation(scr);
// if (info != null) {
// for (Object row : info)
// Cnlout.touttxt((String)row);
// } else {
// Cnlout.touttxt(" ");
// }
// break;
// case M:
// Cnlout.toutFrameRow();
// info = menu.getInformation(scr);
// if (info != null) {
// for (Object row : info)
// Cnlout.touttxt((String)row);
// } else {
// Cnlout.touttxt(" ");
// }break;
// case SCM:
// Cnlout.toutFrameRow();
// info = menu.getInformation(scr);
// if (info != null) {
// for (Object row : info)
// Cnlout.touttxt((String)row);
// } else {
// Cnlout.touttxt(" ");
// }
// this.commands.addAll(menu.getStateCmdList());
// break;
// case CPM:
// // Cnlout.toutFrameRow();
// // String[] strsp = (String[])menu.getInformation(scr);
// // if (strsp != null) {
// // for (String row : strsp)
// // Cnlout.touttxt(row);
// // } else {
// // Cnlout.touttxt(" ");
// // }
// // this.commands.addAll(menu.getParaCmdList(ic));
// break;
// case MCM:
// Cnlout.toutFrameRow();
// info = menu.getInformation(scr);
// if (info != null) {
// for (Object row : info)
// Cnlout.touttxt((String)row);
// } else {
// Cnlout.touttxt(" ");
// }
// this.commands.addAll(menu.getApplCmdList());
// break;
// default:
// break;
// }
// }
// // Ausgabe der Abschlußlinie
// Cnlout.toutFrameRow();
// }