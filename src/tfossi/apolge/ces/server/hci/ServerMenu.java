/** 
 * ServerMenu.java
 * Branch ces
 * APolGe
 * tfossi-team
 * licence GPLv3 
 */
package tfossi.apolge.ces.server.hci;

import static tfossi.apolge.common.constants.ConstValue.LOGGER;
import static tfossi.apolge.common.constants.ConstValue.LOGTAB;
import static tfossi.apolge.common.constants.ConstValue.NTAB;
import static tfossi.apolge.common.constants.ConstValueExtension.GRAYEDSERVERMENU;
import static tfossi.apolge.common.constants.ConstValueExtension.HEADTEXT;
import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;

import java.io.IOException;
import java.util.Arrays;

import org.apache.log4j.Logger;

import tfossi.apolge.common.cmd.ICmd;
import tfossi.apolge.common.cmd.cmds.Gamelist;
import tfossi.apolge.common.hci.AMenu;
import tfossi.apolge.common.hci.AModel;
import tfossi.apolge.common.hci.IStateContext;
import tfossi.apolge.common.macrorecorder.IRecorder;
import tfossi.apolge.common.net.INetReceiver;
import tfossi.apolge.common.net.LoginServer;
import tfossi.apolge.common.net.UserSession;
import tfossi.apolge.io.ContentString;
import tfossi.apolge.io.Screen;

/**
 * Ist das Servermenu mit allen Methoden des Servers
 * 
 * @.pattern State: state
 * @author tfossi
 * @version 12.08.2014
 * @modified -
 * @since Java 1.6
 */
public class ServerMenu extends AMenu implements INetReceiver {
	/** ls */
	private LoginServer ls;
 
	/* (non-Javadoc)
	 * @see tfossi.apolge.common.hci.AMenu#setInformation(java.lang.String, tfossi.apolge.io.Screen)
	 */
	@Override
	public synchronized final void setInformation(String s, Screen scr) {
		this.setInformation(this.getStateContext().getCntr(), scr, new ContentString(s), true,
				false, false);
	} 

	/**
	 * TODO Comment
	 * @param fromState -
	 * @param statecontext -
	 * @modified -
	 */
	public ServerMenu(AMenu fromState, IStateContext statecontext) {
		super(fromState, statecontext, GRAYEDSERVERMENU);

		if(LOGGER) logger.info("Beziehe das Datenmodell des Servers");
		this.setModel(ServerModel.getInstance());
		if(LOGGER) logger.info("Baue Viewer des Servers");
		// Instanziert den View des MVC mit sich selber als Observable
		// this.setViewer(new ServerView(this.getStateContext().getCntr()));

		// this.starteServer();
	}

	// ---- Menuwechsel -------------------------------------------------------

	// ---- State -------------------------------------------------------------


	/* (non-Javadoc)
	 * @see tfossi.apolge.common.hci.menu.AMenuState#actionState()
	 */
	@Override
	public void actionState() {
		// Anmelden an den Screen Message, GenInfo und Central. Sobald sich
		// dort etwas
		// ändert, wird das Menu informiert. Damit kann die Bildschirmausgabe
		// erfolgen.
		// Da Singleton, sind die Daten in den Klassen selber enthalten und
		// ändern sich
		// zwischen den Menüs nicht!
		this.getStateContext().getCntr().initMenuWidgets(this);
		if(LOGGER) logger.debug("STATE::STATECONTEXT\tKontrolle an [" + this.getClass().getSimpleName()
				+ "] übergeben.");

		this.initiate(ServerModel.getInstance(), new ServerView(this.getStateContext().getCntr()));

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

		this.setInformation(this.getStateContext().getCntr(), Screen.C, new ContentString(
				new String[] { " ", "APolGe-Server", "V" + HEADTEXT + " ", " ", " ", " ",
						"(c) Thomas tfossi" }), false, false, false);

		this.setInformation(this.getStateContext().getCntr(), Screen.M, new ContentString(
				"Initiiere Server...."), false, false, false);

		this.setInformation(this.getStateContext().getCntr(), Screen.MI, new ContentString(
				"Es liegen keine MI Nachrichten vor"), false, false, false);

		this.setInformation(this.getStateContext().getCntr(), Screen.VI, new ContentString(
				"Es liegen keine VI Nachrichten vor"), false, false, false);
		// this.starteLoginServer();
		IRecorder.recorder.loadRecord("autostart");
		IRecorder.recorder.setPlayON();

		// Informiere den Viewer über die geänderten Daten. Da ist die große
		// Eingabeschleife!
		this.notifyViewer(this.getView());

		// Hier komme ich vorbei, wenn ich das Menu wechsle /UP
		// this.facade.unvisibleViewScreen();
		// this.facade.unvisibleParameterScreen();
		this.getStateContext().getCntr().unvisibleMenuScreen();

		// this.getStateContext().getCntr().disposedMenuScreen();

		if(LOGGER) logger.trace("STATE::STATECONTEXT\tVerlasse " + this.getClass().getSimpleName() + NTAB
				+ "Kontrolle wieder an StateContext \""
				+ this.getStateContext().getClass().getSimpleName() + "\" zurück.");
	}

	//
	// /**
	// * @param av
	// * @return
	// */
	// private Object[] getCentralInformation(@SuppressWarnings("unused")
	// ServerView av)
	// {
	// assert false;
	// return null; //av.outputCentralInformation();
	// }
	// /*
	// * (non-Javadoc)
	// *
	// * @see
	// name.tfossi.apolge.hci.IMenu#getCentralInformation(java.lang.Object)
	// */
	// public Object[] getCentralInformation(Object view) {
	// assert false;
	// // if (view instanceof EditorViewName)
	// // return this.getCentralInformation((EditorViewName) view);
	// // if (view instanceof EditorViewGame)
	// // return this.getCentralInformation((EditorViewGame) view);
	// // if (view instanceof EditorViewNation)
	// // return this.getCentralInformation((EditorViewNation) view);
	// // if (view instanceof EditorViewClan)
	// // return this.getCentralInformation((EditorViewClan) view);
	// // if (view instanceof EditorViewClanPeList)
	// // return this.getCentralInformation((EditorViewClanPeList) view);
	// // if (view instanceof EditorViewPerson)
	// // return this.getCentralInformation((EditorViewPerson) view);
	// // if (view instanceof EditorViewRole)
	// // return this.getCentralInformation((EditorViewRole) view);
	//
	// return this.getCentralInformation((ServerView) view);
	// }

	// ---- Command Pattern ---------------------------------------------------


	/* (non-Javadoc)
	 * @see tfossi.apolge.common.hci.menu.AMenuReceiver#first()
	 */
	@Override
	public final void first() {

		// this.setInformation(this.getStateContext().getCntr(), Screen.M, new
		// ContentString("NEUE INFO"), true, false);
		// this.setInformation(this.getStateContext().getCntr(), Screen.MI, new
		// ContentString("Info in MI"), true, false);
		// this.setInformation(this.getStateContext().getCntr(), Screen.C, new
		// ContentString("B A M B U L E"), true, false);

		// this.setInformation(this.getStateContext().getCntr(), Screen.M, new
		// ContentString(
		// "NEUE INFO"), false, false);
		// this.setInformation(this.getStateContext().getCntr(), Screen.MI, new
		// ContentString(
		// "Info in MI"), false, false);
		// this.setInformation(this.getStateContext().getCntr(), Screen.C, new
		// ContentString(
		// "B A M B U L E"), false, false);
		// this.nextView = new ServerView();
		// if(LOGGER) logger.debug("MVC::\tVerlasse View: ["
		// + this.getView().getClass().getSimpleName() + "] " + NTAB
		// + "\tWechsle zu: [" + this.nextView.getClass().getSimpleName()
		// + "]");
		// this.getView().setExitSWTView();
	}

	// /**
	// * @param c
	// * @param object
	// * @param object2
	// */
	// public void sendLogin(SendLogin.c c){//, Object object, Object object2) {
	// switch (c) {
	// case active:
	// this.setInformation("Aktive " + object, Screen.VI);
	// this.ls.out("Informationen über Aktive",
	// Integer.valueOf((String) object).intValue());
	// break;
	// case waiting:
	// this.setInformation("Waiting " + object, Screen.VI);
	// this.ls.out("Informationen über Wartende",
	// Integer.valueOf((String) object).intValue());
	// break;
	// case sleeping:
	//
	//
	// IContent content = new ContentString(
	// ((ServerModel)this.getModel()).getPassivGames());
	// this.setInformation(this.getStateContext().getCntr(), Screen.VI,
	// content, true, true, false);
	//
	// this.setInformation("Sleeping " + object, Screen.VI);
	// this.ls.out(content,
	// Integer.valueOf((String) object).intValue());
	// break;
	// }
	// }
	/**
	 * TODO Comment
	 * @return -
	 * @modified - 
	 */
	public Object getPassivGames() {
		return ((ServerModel) this.getModel()).getPassivGames();
	}

	// /**
	// * // * Bearbeiten der Namensliste // *
	// * <p>
	// * // * Konzeption: // *
	// * <ol>
	// * // *
	// * <li><i>names</i> schaltet auf Namesview um und zeigt die möglichen // *
	// * Namensliste</li>
	// * // *
	// * <li><i>names -man</i>, wie [1] ohne Anzeige, aktiviert die
	// * <i>man</i>-Liste</li>
	// * // * und zeigt sie an. // *
	// * <li><i>names -man</i> a|add <i>Name</i> wie[1] ohne Anzeige, [2] ohne
	// //
	// * * Anzeige und fügt einen neuen Namen hinzu und zeigt die Liste.</li>
	// * // *
	// * <li><i>names -man</i> d|delete <i>Name</i> wie[1] ohne Anzeige, [2] //
	// *
	// * ohne Anzeige und löscht den Namen und zeigt die Liste.</li>
	// * // *
	// * <li><i>names -man</i> r|rename <i>Oldname Newname</i> wie[1] ohne // *
	// * Anzeige, [2] ohne Anzeige und ändert einen Namen und zeigt die
	// Liste.</li>
	// * // *
	// * <li><i>names -fl</i> [namensliste] läd alle oder eine spezifische // *
	// * Namensliste</li>
	// * // *
	// * <li><i>names -fs</i> [namensliste] speichert alle oder eine spezifische
	// * // * Namensliste</li>
	// * // *
	// * </ol>
	// * // * // * @param c // * der Befehl // * @param para1 // * erster
	// * Parameter // * @param para2 // * zweiter Parameter
	// */
	// public final void gamelist(CSVGamelist.c c, String para1,
	// @SuppressWarnings("unused") String para2) {
	// String gamescriptpath = ConstValue.scriptPath + "games";
	// switch (c) {
	// case l:
	// // Zusammenstellen der Games
	// File gamescripts = new File(gamescriptpath);
	// int i = 0;
	// String[] fileList = new String[gamescripts.listFiles().length];
	// for (File file : gamescripts.listFiles())
	// fileList[i++] = file.getName();
	// IContent content = new ContentString(fileList);
	// this.setInformation(this.getStateContext().getCntr(), Screen.VI,
	// content, true, true, false);
	// break;
	// case dl:
	// // Zusammenstellen der Games
	// assert false : para1;
	// // MetaData.getInstance().addName(c.toString(), para2);
	// // File gamescripts = new File(gamescriptpath);
	// // int i = 0;
	// // String[] fileList = new String[gamescripts.listFiles().length];
	// // for (File file : gamescripts.listFiles())
	// // fileList[i++] = file.getName();
	// // IContent content = new ContentString(fileList);
	// // this.setInformation(this.getStateContext().getCntr(), Screen.VI,
	// // content, true, true);
	// break;
	// default:
	//
	// }
	// if (this.getView() instanceof ServerViewGamelist) {
	// if(LOGGER) logger.debug("Viewerwechsel nicht erforderlich, weil schon vorhanden.");
	// return;
	// }
	// this.nextView = new
	// ServerViewGamelist(this.getStateContext().getCntr());
	// if(LOGGER) logger.info("MVC::\tVerlasse View [" +
	// this.getView().getClass().getSimpleName() + "]!");
	// this.getView().setExitSWTView();

	//
	// MetaData md = ((EditorModel) this.getModel()).getMetaData();
	//
	// switch (c) {
	// case sw:
	// Instanziert den Names-View des MVC mit sich selber als Observable
	// this.setViewer(new
	// ServerViewGamelist(this.getStateContext().getCntr()));
	// break;
	// // Umschalten auf speziellen Datensatz
	// case man:
	// case woman:
	// case clan:
	// case nation:
	// case town:
	// case nature:
	//
	// if (this.names == null || !this.names.equals(c))
	// this.setInformation(Screen.MI, new String[] { "Editiere "
	// + c.toString() }, true, false);
	// this.names = c;
	// if (para1 == null || para2 == null)
	// break;
	// switch (suborder.valueOf(para1)) {
	// case a:
	// case add:
	// MetaData.getInstance().addName(c.toString(), para2);
	// break;
	// case d:
	// case delete:
	// MetaData.getInstance().removeName(c.toString(), para2);
	// break;
	// case r:
	// case rename:
	// String[] paraX = para2.split(" ", 2);
	// if (paraX[0] == null || paraX[1] == null)
	// break;
	// MetaData.getInstance().renameName(c.toString(), paraX[0],
	// paraX[1]);
	// break;
	// default:
	//
	// }
	// break;
	// case fl:
	// // Basisdaten laden
	// try {
	// md.loadNames(para1);
	//
	// this.setInformation(Screen.MI, new String[] {
	// "Namenssatz " + para1 + " geladen",
	// "Typ  : "
	// + this.getView().getClass().getSimpleName()
	// .substring(10) }, false, true);
	// } catch (Exception e) {
	// ErrApp.NDEF.erraufruf(logger, "");
	// }
	// break;
	// case fs:
	// // Basisdaten speichern
	// md.saveNames(para1);
	// this.setInformation(Screen.MI, new String[] {
	// "Namenssatz " + para1 + " gespeichert",
	// "Typ  : "
	// + this.getView().getClass().getSimpleName()
	// .substring(10) }, false, true);
	// break;
	// default:
	// throw new NullPointerException();
	// }

	// }

	// public final void detailsgame(
	// @SuppressWarnings("unused") CSVDetailsGame.c c,
	// @SuppressWarnings("unused") String para1,
	// @SuppressWarnings("unused") String para2) {
	//
	// if (this.getView() instanceof ServerViewGamelist) {
	// if(LOGGER) logger.debug("Viewerwechsel nicht erforderlich, weil schon vorhanden.");
	// return;
	// }
	// this.nextView = new ServerViewGamelist(this.getStateContext().getCntr());
	// if(LOGGER) logger.info("MVC::\tVerlasse View ["
	// + this.getView().getClass().getSimpleName() + "]!");
	// this.getView().setExitSWTView();

	//
	// MetaData md = ((EditorModel) this.getModel()).getMetaData();
	//
	// switch (c) {
	// case sw:
	// Instanziert den Names-View des MVC mit sich selber als Observable
	// this.setViewer(new
	// ServerViewGamelist(this.getStateContext().getCntr()));
	// break;
	// // Umschalten auf speziellen Datensatz
	// case man:
	// case woman:
	// case clan:
	// case nation:
	// case town:
	// case nature:
	//
	// if (this.names == null || !this.names.equals(c))
	// this.setInformation(Screen.MI, new String[] { "Editiere "
	// + c.toString() }, true, false);
	// this.names = c;
	// if (para1 == null || para2 == null)
	// break;
	// switch (suborder.valueOf(para1)) {
	// case a:
	// case add:
	// MetaData.getInstance().addName(c.toString(), para2);
	// break;
	// case d:
	// case delete:
	// MetaData.getInstance().removeName(c.toString(), para2);
	// break;
	// case r:
	// case rename:
	// String[] paraX = para2.split(" ", 2);
	// if (paraX[0] == null || paraX[1] == null)
	// break;
	// MetaData.getInstance().renameName(c.toString(), paraX[0],
	// paraX[1]);
	// break;
	// default:
	//
	// }
	// break;
	// case fl:
	// // Basisdaten laden
	// try {
	// md.loadNames(para1);
	//
	// this.setInformation(Screen.MI, new String[] {
	// "Namenssatz " + para1 + " geladen",
	// "Typ  : "
	// + this.getView().getClass().getSimpleName()
	// .substring(10) }, false, true);
	// } catch (Exception e) {
	// ErrApp.NDEF.erraufruf(logger, "");
	// }
	// break;
	// case fs:
	// // Basisdaten speichern
	// md.saveNames(para1);
	// this.setInformation(Screen.MI, new String[] {
	// "Namenssatz " + para1 + " gespeichert",
	// "Typ  : "
	// + this.getView().getClass().getSimpleName()
	// .substring(10) }, false, true);
	// break;
	// default:
	// throw new NullPointerException();
	// }
	//
	// String[] str = new String[] { "NAMES",
	// "editDataGameUID  : " + this.editDataGameUID,
	// "editDataPlayerUID: " + this.editDataRoleUID,
	// "editDataNationUID: " + this.editDataNationUID,
	// "editDataClanUID  : " + this.editDataClanUID,
	// "editDataPersonUID: " + this.editDataPersonUID };
	// this.setInformation(Screen.MI, str, false, true);
	// }

	// public final void detailsactivegame(
	// @SuppressWarnings("unused") CSVDetailsActiveGame.c c,
	// @SuppressWarnings("unused") String para1,
	// @SuppressWarnings("unused") String para2) {
	//
	// if (this.getView() instanceof ServerViewGamelist) {
	// if(LOGGER) logger.debug("Viewerwechsel nicht erforderlich, weil schon vorhanden.");
	// return;
	// }
	// this.nextView = new ServerViewGamelist(this.getStateContext().getCntr());
	// if(LOGGER) logger.info("MVC::\tVerlasse View ["
	// + this.getView().getClass().getSimpleName() + "]!");
	// this.getView().setExitSWTView();

	//
	// MetaData md = ((EditorModel) this.getModel()).getMetaData();
	//
	// switch (c) {
	// case sw:
	// Instanziert den Names-View des MVC mit sich selber als Observable
	// this.setViewer(new
	// ServerViewGamelist(this.getStateContext().getCntr()));
	// break;
	// // Umschalten auf speziellen Datensatz
	// case man:
	// case woman:
	// case clan:
	// case nation:
	// case town:
	// case nature:
	//
	// if (this.names == null || !this.names.equals(c))
	// this.setInformation(Screen.MI, new String[] { "Editiere "
	// + c.toString() }, true, false);
	// this.names = c;
	// if (para1 == null || para2 == null)
	// break;
	// switch (suborder.valueOf(para1)) {
	// case a:
	// case add:
	// MetaData.getInstance().addName(c.toString(), para2);
	// break;
	// case d:
	// case delete:
	// MetaData.getInstance().removeName(c.toString(), para2);
	// break;
	// case r:
	// case rename:
	// String[] paraX = para2.split(" ", 2);
	// if (paraX[0] == null || paraX[1] == null)
	// break;
	// MetaData.getInstance().renameName(c.toString(), paraX[0],
	// paraX[1]);
	// break;
	// default:
	//
	// }
	// break;
	// case fl:
	// // Basisdaten laden
	// try {
	// md.loadNames(para1);
	//
	// this.setInformation(Screen.MI, new String[] {
	// "Namenssatz " + para1 + " geladen",
	// "Typ  : "
	// + this.getView().getClass().getSimpleName()
	// .substring(10) }, false, true);
	// } catch (Exception e) {
	// ErrApp.NDEF.erraufruf(logger, "");
	// }
	// break;
	// case fs:
	// // Basisdaten speichern
	// md.saveNames(para1);
	// this.setInformation(Screen.MI, new String[] {
	// "Namenssatz " + para1 + " gespeichert",
	// "Typ  : "
	// + this.getView().getClass().getSimpleName()
	// .substring(10) }, false, true);
	// break;
	// default:
	// throw new NullPointerException();
	// }
	//
	// String[] str = new String[] { "NAMES",
	// "editDataGameUID  : " + this.editDataGameUID,
	// "editDataPlayerUID: " + this.editDataRoleUID,
	// "editDataNationUID: " + this.editDataNationUID,
	// "editDataClanUID  : " + this.editDataClanUID,
	// "editDataPersonUID: " + this.editDataPersonUID };
	// this.setInformation(Screen.MI, str, false, true);
	// }

	// public final void activegamelist(
	// @SuppressWarnings("unused") CSVActivegamelist.c c,
	// @SuppressWarnings("unused") String para1,
	// @SuppressWarnings("unused") String para2) {
	//
	// if (this.getView() instanceof ServerViewGamelist) {
	// if(LOGGER) logger.debug("Viewerwechsel nicht erforderlich, weil schon vorhanden.");
	// return;
	// }
	// this.nextView = new ServerViewGamelist(this.getStateContext().getCntr());
	// if(LOGGER) logger.info("MVC::\tVerlasse View ["
	// + this.getView().getClass().getSimpleName() + "]!");
	// this.getView().setExitSWTView();

	//
	// MetaData md = ((EditorModel) this.getModel()).getMetaData();
	//
	// switch (c) {
	// case sw:
	// Instanziert den Names-View des MVC mit sich selber als Observable
	// this.setViewer(new
	// ServerViewGamelist(this.getStateContext().getCntr()));
	// break;
	// // Umschalten auf speziellen Datensatz
	// case man:
	// case woman:
	// case clan:
	// case nation:
	// case town:
	// case nature:
	//
	// if (this.names == null || !this.names.equals(c))
	// this.setInformation(Screen.MI, new String[] { "Editiere "
	// + c.toString() }, true, false);
	// this.names = c;
	// if (para1 == null || para2 == null)
	// break;
	// switch (suborder.valueOf(para1)) {
	// case a:
	// case add:
	// MetaData.getInstance().addName(c.toString(), para2);
	// break;
	// case d:
	// case delete:
	// MetaData.getInstance().removeName(c.toString(), para2);
	// break;
	// case r:
	// case rename:
	// String[] paraX = para2.split(" ", 2);
	// if (paraX[0] == null || paraX[1] == null)
	// break;
	// MetaData.getInstance().renameName(c.toString(), paraX[0],
	// paraX[1]);
	// break;
	// default:
	//
	// }
	// break;
	// case fl:
	// // Basisdaten laden
	// try {
	// md.loadNames(para1);
	//
	// this.setInformation(Screen.MI, new String[] {
	// "Namenssatz " + para1 + " geladen",
	// "Typ  : "
	// + this.getView().getClass().getSimpleName()
	// .substring(10) }, false, true);
	// } catch (Exception e) {
	// ErrApp.NDEF.erraufruf(logger, "");
	// }
	// break;
	// case fs:
	// // Basisdaten speichern
	// md.saveNames(para1);
	// this.setInformation(Screen.MI, new String[] {
	// "Namenssatz " + para1 + " gespeichert",
	// "Typ  : "
	// + this.getView().getClass().getSimpleName()
	// .substring(10) }, false, true);
	// break;
	// default:
	// throw new NullPointerException();
	// }
	//
	// String[] str = new String[] { "NAMES",
	// "editDataGameUID  : " + this.editDataGameUID,
	// "editDataPlayerUID: " + this.editDataRoleUID,
	// "editDataNationUID: " + this.editDataNationUID,
	// "editDataClanUID  : " + this.editDataClanUID,
	// "editDataPersonUID: " + this.editDataPersonUID };
	// this.setInformation(Screen.MI, str, false, true);
	// }

	/* (non-Javadoc)
	 * @see tfossi.apolge.common.hci.AMenu#root()
	 */
	@Override
	public final void root() {
		this.nextView = new ServerView(this.getStateContext().getCntr());
		if(LOGGER) logger.info("MVC::\tVerlasse View [" + this.getView().getClass().getSimpleName() + "]!");
		this.getView().setExitSWTView();

	}
//
	// Hidecommands

	/**
	 * TODO Comment
	 * @modified - 
	 */
	public void loadLists() {
//		String gamescriptpath = ConstValue.SCRIPT_PATH + "games";
//		File gamescripts = new File(gamescriptpath);
//		if (!gamescripts.exists()) {
//			assert false : gamescriptpath;
//			if(LOGGER) logger.warn("Es gibt keine game-Files!");
//			return;
//		}
//		int i = 0;
//
//		String[] fileList = new String[gamescripts.listFiles().length];
//		for (File file : gamescripts.listFiles())
//			fileList[i++] = file.getName();
//		if(LOGGER) logger.trace(Arrays.asList(fileList));
//		((ServerModel) this.getModel()).setPassivGames(fileList);
	}
//
	/**
	 * TODO Comment
	 * @param value -
	 * @modified - 
	 */
	public void showLists(String[] value) {
//		if (value[0].equals("passive") || value[0].equals("all")) {
//			ContentString cs = new ContentString(((ServerModel) this.getModel()).getPassivGames());
//			this.setInformation(this.getStateContext().getCntr(), Screen.VI, cs, false, true,
//					false);
//			this.setInformation(this.getStateContext().getCntr(), Screen.VI, new ContentString(
//					"Passive Games:"), true, false, false);
//
//		}
//		if (value[0].equals("waiting") || value[0].equals("all")) {
//			ContentString cs = new ContentString(((ServerModel) this.getModel()).getWaitingGames());
//			this.setInformation(this.getStateContext().getCntr(), Screen.VI, cs, false, true,
//					false);
//			this.setInformation(this.getStateContext().getCntr(), Screen.VI, new ContentString(
//					"Waiting Games:"), true, false, false);
//
//		}
//		if (value[0].equals("active") || value[0].equals("all")) {
//			ContentString cs = new ContentString(((ServerModel) this.getModel()).getActiveGames());
//			this.setInformation(this.getStateContext().getCntr(), Screen.VI, cs, false, true,
//					false);
//			this.setInformation(this.getStateContext().getCntr(), Screen.VI, new ContentString(
//					"Active Games:"), true, false, false);
//
//		}
	}

	// /**
	// * Lade Userlisten
	// */
	// public void loadUser() {
	// ErrApp.NI_W.erraufruf(logger, "2. Einlesen der Logins" + NTAB
	// + "1. Liste der Logins");
	// }

	/**
	 * Aktiviert den Gameserver
	 */
	public void activateGameServer() {
		try {
			this.ls = new LoginServer(this);
			this.ls.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// public void loginTask() {
	// ErrApp.NI_W.erraufruf(logger, "4. Anmeldung Client" + NTAB
	// + "1. Connection" + NTAB + "2. Client anlegen" + NTAB
	// + "3. Client authorisieren");
	// }
	//
	// public void gameLoader() {
	// ErrApp.NI_W.erraufruf(logger, "5. Gameleader" + NTAB
	// + "1. Auswahl aus Liste der Templates");
	// }
	//
	// public void loginGame() {
	// ErrApp.NI_W.erraufruf(logger, "6. Gameplayer" + NTAB
	// + "1. Auswahl aus Liste der Actives");
	// }

	// ---- Recorder ----------------------------------------------------------
	//
	// /*
	// * (non-Javadoc)
	// *
	// * @see name.tfossi.apolge.hci.AMenu#playRecorder(java.lang.String)
	// */
	// public final void playRecorder(final String macroname) {
	// ErrApp.NI.erraufruf(logger, "");
	// // if(LOGGER) logger.trace("Enter ");
	// // super.playRecorder(macroname, this.getClass());
	// // if(LOGGER) logger.trace("Exit ");
	// }

	// /* (non-Javadoc)
	// * @see
	// name.tfossi.apolge.common.cmd.IReceiver#recRecorder(java.lang.String,
	// java.lang.String)
	// */
	// public final void recRecorder(final String macroname, final String
	// comment) {
	// // ErrApp.NI.erraufruf(logger, "");
	// // if(LOGGER) logger.trace("Enter ");
	// super.recRecorder(macroname, comment, this.getClass());
	// // if(LOGGER) logger.trace("Exit ");
	// }

	/**
	 * @param uid -
	 * @return -
	 */
	final String getUserNickName(long uid) {
		return ((ServerModel) this.getModel()).getUser(uid).getNickname();
	}

	/**
	 * @param uid -
	 * @param password -
	 * @return -
	 */
	@SuppressWarnings("static-method")
	final boolean chkUserPassword(final long uid, final String password) {
		return true; // ((ServerModel)this.getModel()).getUser(uid).getPassword();
	}

	/* (non-Javadoc)
	 * @see tfossi.apolge.common.net.INetReceiver#passportUs(java.lang.Object, java.lang.String[])
	 */
	@Override
	public synchronized Object[] passportUs(Object daten, String[] value) {
		assert false;
		return null;
	}


	/* (non-Javadoc)
	 * @see tfossi.apolge.common.net.INetReceiver#verifyPassportUs(java.lang.Object, java.lang.String[])
	 */
	@Override
	public synchronized Object[] verifyPassportUs(Object daten, String[] value) {
//		if (daten instanceof ErrApp[]) {
//			ErrApp[] str = (ErrApp[]) daten;
//			if(LOGGER) logger.trace("IO:NET ERROR" + LOGTAB + "Daten: " + Arrays.asList(str) + LOGTAB
//					+ "Value: " + (value == null ? "NULL" : Arrays.asList(value)));
//			return str;
//		}
		if(LOGGER) logger.trace("IO:NET" + LOGTAB + "Daten: " + daten + LOGTAB + "Value: "
				+ (value == null ? "NULL" : Arrays.asList(value)));

		return new Object[] { daten, (value == null ? null : Arrays.asList(value)) };

	}

	/* (non-Javadoc)
	 * @see tfossi.apolge.common.net.INetReceiver#verifyAnswerGs(java.lang.Object, java.lang.String[])
	 */
	@Override
	public synchronized Object[] verifyAnswerGs(final Object daten, final String[] value) {
//		if (daten instanceof ErrApp[]) {
//			ErrApp[] str = (ErrApp[]) daten;
//			if(LOGGER) logger.trace("IO:NET ERROR" + LOGTAB + "Daten: " + Arrays.asList(str) + LOGTAB
//					+ "Value: " + (value == null ? "NULL" : Arrays.asList(value)));
//			return str;
//		}
		if(LOGGER) logger.trace("IO:NET" + LOGTAB + "Daten: " + daten + LOGTAB + "Value: "
				+ (value == null ? "NULL" : Arrays.asList(value)));

		return new Object[] { daten, (value == null ? null : Arrays.asList(value)) };
	}

	/* (non-Javadoc)
	 * @see tfossi.apolge.common.net.INetReceiver#openServer(java.lang.Object, java.lang.String[])
	 */
	@Override
	public void openServer(final Object daten, final String[] value) {
		//
	}

	/* (non-Javadoc)
	 * @see tfossi.apolge.common.net.INetReceiver#sendGamelist(tfossi.apolge.common.net.UserSession, tfossi.apolge.common.cmd.cmds.Gamelist.c, java.lang.Object, java.lang.String[])
	 */
	@Override
	public Object[] sendGamelist(final UserSession us, final Gamelist.c cmd, final Object data, final String... value) {
		switch (cmd) {
		case ask4sleepingGames:

			String[] rc = ((ServerModel) this.getModel()).getPassivGames();

			us.send("Gamelist -" + Gamelist.c.ans4sleepingGames, rc);
			return rc;
		default:
			assert false : cmd + LOGTAB + data + LOGTAB + Arrays.asList(value);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see tfossi.apolge.common.net.INetReceiver#loadGs(tfossi.apolge.common.net.UserSession, java.lang.Object, java.lang.String[])
	 */
	@Override
	public synchronized Object[] loadGs(final UserSession us, final Object daten, final String[] value) {
		// Laden eines Games:

//		@SuppressWarnings("unused")
//		DataRoot dg=null;
//		try {
//			dg = new BuildGame(FS, ((String[]) daten)[0]).newGame();
//		} catch (DateiNotFoundException e) {
//			logger.fatal("Script [" + ((String[]) daten)[0] + "] nicht gefunden: "
//					+ e.getMessage());
//			System.exit(-2);
//		} catch (FolderNotFoundException e) {
//			logger.fatal("Ordner [" + ((String[]) daten)[0] + "] nicht gefunden: "
//					+ e.getMessage());
//			System.exit(-3);
//		}
//		if(LOGGER) logger.debug(dg.getShortname());
//		if(LOGGER) logger.debug(dg.getAuthor());
//		if(LOGGER) logger.debug(dg.getName());
//		if(LOGGER) logger.debug(dg.getDescription());
//		dg.setUid(serialVersionUID + dg.hashCode());
//		if(LOGGER) logger.debug(dg.getUid());
//
//		if(LOGGER) logger.debug(dg.getCounterRegister());

		assert false;

		return null;

	}

	// ---- Selbstverwaltung --------------------------------------------------
	/** serialVersionUID */
	@SuppressWarnings("unused")
	private final static long serialVersionUID = VERSION;
	/** logger */
	private final static Logger logger = Logger.getLogger(ServerMenu.class.getPackage().getName());

}
