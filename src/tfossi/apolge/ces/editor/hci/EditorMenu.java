/** 
 * EditorMenu.java
 * Branch ces
 * APolGe
 * tfossi-team
 * licence GPLv3 
 */
package tfossi.apolge.ces.editor.hci;

import static tfossi.apolge.common.constants.ConstValue.*;
import static tfossi.apolge.common.constants.ConstValueExtension.*;

import org.apache.log4j.Logger;

import tfossi.apolge.ces.editor.Editor;
import tfossi.apolge.common.cmd.ICmd;
import tfossi.apolge.common.hci.AMenu;
import tfossi.apolge.common.hci.AModel;
import tfossi.apolge.common.hci.IStateContext;
import tfossi.apolge.data.MetaData;
import tfossi.apolge.io.ContentString;
import tfossi.apolge.io.Screen;
import tfossi.apolge.io.screenfactory.Cntr;

/**
 * <b>Menüsteuerung</b><br>
 * Stellt für das State Pattern konkrete Methoden zur Verfügung. Insbesondere
 * die spezialisierte Ausprägung {@link #actionState()} für das Editormenu
 * liegt hier vor. Als State Context fungieren die Applikationen Editor, Server
 * und Client.<br>
 * <br>
 * <b>Daten- und Viewersteuerung</b><br>
 * Im Editormenu läuft die Steuerung des Editorkomponenten Viewer und Model
 * zusammen. Die drei Komponenten bilden ein MVC-Pattern ab.
 * 
 * @.pattern State: concrete state
 * @.pattern MVC: concrete Controller
 * @.pattern Obervable (EditorView)
 * @see EditorView
 * @see EditorModel
 * @see Editor
 * TODO Comment
 *
 * @author tfossi
 * @version 13.08.2014
 * @modified -
 * @since Java 1.6
 */
public class EditorMenu extends AMenu {

	/* (non-Javadoc)
	 * @see tfossi.apolge.common.hci.AMenu#setInformation(java.lang.String, tfossi.apolge.io.Screen)
	 */
	@Override
	public synchronized final void setInformation(String s, Screen scr) {
		this.setInformation(this.getStateContext().getCntr(), scr,
				new ContentString(s), true, false, false);
	}
//	// Der aktuelle Datensatz, der gerade editiert wird
//	private long editDataGameUID = -1L;
//
//	private long editDataRoleUID = -1L;
//
//	private long editDataNationUID = -1L;
//
//	private long editDataClanUID = -1L;
//
//	private long editDataPersonUID = -1L;
//
//	private final void cleargameuid() {
//		// this.editDataGameUID = -1L;
//		this.clearnationuid();
//	}
//
//	private final void clearnationuid() {
//		this.editDataNationUID = -1L;
//		this.clearclanuid();
//	}
//
//	private final void clearclanuid() {
//		this.editDataClanUID = -1L;
//		this.clearpersonuid();
//	}
//
//	private final void clearpersonuid() {
//		this.editDataPersonUID = -1L;
//	}
//
//	private CCName.c names = null;
//
//	private enum suborder {
//		pl, a, add, d, delete, r, rename
//	}

	// ---- Command Pattern
	// ------------------------------------------------------

//	/**
//	 * Bearbeiten der Namensliste
//	 * <p>
//	 * Konzeption:
//	 * <ol>
//	 * <li><i>names</i> schaltet auf Namesview um und zeigt die möglichen
//	 * Namensliste</li>
//	 * <li><i>names -man</i>, wie [1] ohne Anzeige, aktiviert die <i>man</i>-Liste</li>
//	 * und zeigt sie an.
//	 * <li><i>names -man</i> a|add <i>Name</i> wie[1] ohne Anzeige, [2] ohne
//	 * Anzeige und fügt einen neuen Namen hinzu und zeigt die Liste.</li>
//	 * <li><i>names -man</i> d|delete <i>Name</i> wie[1] ohne Anzeige, [2]
//	 * ohne Anzeige und löscht den Namen und zeigt die Liste.</li>
//	 * <li><i>names -man</i> r|rename <i>Oldname Newname</i> wie[1] ohne
//	 * Anzeige, [2] ohne Anzeige und ändert einen Namen und zeigt die Liste.</li>
//	 * <li><i>names -fl</i> [namensliste] läd alle oder eine spezifische
//	 * Namensliste</li>
//	 * <li><i>names -fs</i> [namensliste] speichert alle oder eine spezifische
//	 * Namensliste</li>
//	 * </ol>
//	 * 
//	 * @param c
//	 *            der Befehl
//	 * @param para1
//	 *            erster Parameter
//	 * @param para2
//	 *            zweiter Parameter
//	 */
//	public final void names(CCName.c c, String para1, String para2) {
//
//		MetaData md = ((EditorModel) this.getModel()).getMetaData();
//
//		switch (c) {
//		case sw:
//			// Instanziert den Names-View des MVC mit sich selber als Observable
//			this.setViewer(new EditorViewName(this, this.getStateContext()));
//			break;
//		// Umschalten auf speziellen Datensatz
//		case man:
//		case woman:
//		case clan:
//		case nation:
//		case town:
//		case nature:
//
//			if (this.names == null || !this.names.equals(c))
//				this.setInformation(Screen.MI, new String[] { "Editiere "
//						+ c.toString() }, true, false);
//			this.names = c;
//			if (para1 == null || para2 == null)
//				break;
//			switch (suborder.valueOf(para1)) {
//			case a:
//			case add:
//				MetaData.getInstance().addName(c.toString(), para2);
//				break;
//			case d:
//			case delete:
//				MetaData.getInstance().removeName(c.toString(), para2);
//				break;
//			case r:
//			case rename:
//				String[] paraX = para2.split(" ", 2);
//				if (paraX[0] == null || paraX[1] == null)
//					break;
//				MetaData.getInstance().renameName(c.toString(), paraX[0],
//						paraX[1]);
//				break;
//			default:
//
//			}
//			break;
//		case fl:
//			// Basisdaten laden
//			try {
//				md.loadNames(para1);
//
//				this.setInformation(Screen.MI, new String[] {
//						"Namenssatz " + para1 + " geladen",
//						"Typ  : "
//								+ this.getView().getClass().getSimpleName()
//										.substring(10) }, false, true);
//			} catch (Exception e) {
//				ErrApp.NDEF.erraufruf(logger, "");
//			}
//			break;
//		case fs:
//			// Basisdaten speichern
//			md.saveNames(para1);
//			this.setInformation(Screen.MI, new String[] {
//					"Namenssatz " + para1 + " gespeichert",
//					"Typ  : "
//							+ this.getView().getClass().getSimpleName()
//									.substring(10) }, false, true);
//			break;
//		default:
//			throw new NullPointerException();
//		}
//
//		String[] str = new String[] { "NAMES",
//				"editDataGameUID  : " + this.editDataGameUID,
//				"editDataPlayerUID: " + this.editDataRoleUID,
//				"editDataNationUID: " + this.editDataNationUID,
//				"editDataClanUID  : " + this.editDataClanUID,
//				"editDataPersonUID: " + this.editDataPersonUID };
//		this.setInformation(Screen.MI, str, false, true);
//	}
//
	/**
	 * Bearbeiten der Gamesliste
	 * <p>
	 * Konzeption:
	 * <ol>
	 * <li><i>games</i> schaltet auf Gamessview um und zeigt die möglichen
	 * Namensliste</li>
	 * <li><i>games -fl</i> wie [1] ohne Anzeige und läd alle
	 * Game-Datensätze</li>
	 * <li><i>games -fs</i> wie [1] ohne Anzeige und speichert alle
	 * Game-Datensätze</li>
	 * <li><i>names -man</i> a|add <i>Name</i> wie[1] ohne Anzeige, [2] ohne
	 * Anzeige und fügt einen neuen Namen hinzu und zeigt die Liste.</li>
	 * <li><i>names -man</i> d|delete <i>Name</i> wie[1] ohne Anzeige, [2]
	 * ohne Anzeige und löscht den Namen und zeigt die Liste.</li>
	 * <li><i>names -man</i> r|rename <i>Oldname Newname</i> wie[1] ohne
	 * Anzeige, [2] ohne Anzeige und ändert einen Namen und zeigt die Liste.</li>
	 * </ol>
	 * 
	 * @param c -
	 * @param para1 -
	 * @param para2 -
	 */
	public final void games(Object /*CSPara.c*/ c, String para1, String para2) {
		@SuppressWarnings("unused") MetaData md = ((EditorModel) this.getModel()).getMetaData();

		// this.deleteObserver(this.getView());
//		switch (c) {
//		case a:
//			if (para1 != null && para2 == null) {
//				md.loadNames(null);
//				this.editDataGameUID = md.createDataGame(para1);
//			}
		// $FALL-THROUGH$
//		case sw:
			// Instanziert den Names-View des MVC mit sich selber als Observable
//			this.cleargameuid();
//			this.setViewer(new EditorViewGame(this, this.getStateContext()));
//			break;
//		case simu:
//			if (this.editDataGameUID != 1L) {
//				DataGame dg = md.getDataGame(this.editDataGameUID);
//
//				TimesController tc = dg.getTc();
//				for (ITermin t : tc.getSchedular()) {
//					System.out.println(t.getName() + ": "
//							+ t.getPiT().getDatetime());
//				}
//
//				try {
//					new Zeitreise(dg, dg.getDsc().getDataNation(
//							this.editDataNationUID));
//				} catch (LuaException e1) {
//					ErrApp.LUAEXCEPTION.erraufruf(logger, "");
//				} catch (Exception e) {
//					ErrApp.NDEF.erraufruf(logger, "");
//				}
//
//				// tc.timestart();
//				// tc.timerestart();
//			}
//			break;
		// case fl:
		// // Basisdaten laden
		// try {
		//
		// // assert false: this.getView().getClass().;
		// if (this.getView() instanceof EditorViewGame)
		// md.loadData((EditorViewGame) this.getView());
		// else
		// assert false;
		// GenInfo.getInstance()
		// .setInformation(
		// new String[] {
		// "Datensatz geladen",
		// "Typ : "
		// + this.getView().getClass().getSimpleName()
		// .substring(10) }, false, true);
		// } catch (Exception e) {
		// ErrApp.NDEF.erraufruf(logger, "");
		// }
		// break;
		// case fs:
		// // Basisdaten speichern
		// try {
		// // assert false: this.getView().getClass().;
		// if (this.getView() instanceof EditorViewGame)
		// md.saveData((EditorViewGame) this.getView());
		// else
		// assert false;
		// GenInfo.getInstance()
		// .setInformation(
		// new String[] {
		// "Datensatz gespeichert",
		// "Typ : "
		// + this.getView().getClass().getSimpleName()
		// .substring(10) }, false, true);
		// } catch (Exception e)
		// ErrApp.NDEF.erraufruf(logger, "");
		// }
		// break;
//		case d:
//			if (para1 != null && para1.matches("\\d*") && para2 == null)
//				md.deleteDataGame(Long.parseLong(para1));
//			break;
//		case r:
//			if (para1 != null && para2 != null)
//				md.changeDataGame(para1, para2);
//			break;
//		case e:
//			if (para1 != null && para1.matches("\\d*")) {
//				long newUID = Long.parseLong(para1);
//				if (newUID != this.editDataGameUID) {
//					this.cleargameuid();
//					this.editDataGameUID = newUID;
//				}
//			}
//			break;
//		default:
//			assert false : " Der Programmierer hat gepennt: " + c;
//			break;
//		}
//		String[] str = new String[] { "GAMES",
//				"editDataGameUID  : " + this.editDataGameUID,
//				"editDataPlayerUID: " + this.editDataRoleUID,
//				"editDataNationUID: " + this.editDataNationUID,
//				"editDataClanUID  : " + this.editDataClanUID,
//				"editDataPersonUID: " + this.editDataPersonUID };
//		this.setInformation(Screen.MI, str, false, true);
	}
//
//	@SuppressWarnings("boxing")
//	public final void roles(CCRole.c c, String para1, String para2) {
//		MetaData md = ((EditorModel) this.getModel()).getMetaData();
//		// this.deleteObserver(this.getView());
//		switch (c) {
//		case sw:
//			// Instanziert den Names-View des MVC mit sich selber als Observable
//			this.editDataRoleUID = -1L;
//			this.setViewer(new EditorViewRole(this, this.getStateContext()));
//			break;
//		case fl:
//			// Basisdaten laden
//			try {
//
//				// assert false: this.getView().getClass().;
//				if (this.getView() instanceof EditorViewRole)
//					md.loadData((EditorViewRole) this.getView());
//				else
//					assert false;
//				this.setInformation(Screen.MI, new String[] {
//						"Datensatz geladen",
//						"Typ  : "
//								+ this.getView().getClass().getSimpleName()
//										.substring(10) }, false, true);
//			} catch (Exception e) {
//				ErrApp.NDEF.erraufruf(logger, "");
//			}
//			break;
//		case fs:
//			// Basisdaten speichern
//			try {
//				// assert false: this.getView().getClass().;
//				if (this.getView() instanceof EditorViewRole)
//					md.saveData((EditorViewRole) this.getView());
//				else
//					assert false;
//				this.setInformation(Screen.MI, new String[] {
//						"Datensatz gespeichert",
//						"Typ  : "
//								+ this.getView().getClass().getSimpleName()
//										.substring(10) }, false, true);
//			} catch (Exception e) {
//				ErrApp.NDEF.erraufruf(logger, "");
//			}
//			break;
//		case a:
//			if (para1 != null)
//				this.editDataRoleUID = md.createDataRole(para1);
//			break;
//		case d:
//			if (para1 != null && para1.matches("\\d*"))
//				md.deleteDataRole(Long.parseLong(para1));
//			break;
//		case r:
//			if (para1 != null && para2 != null)
//				md.changeDataRole(para1, para2);
//			break;
//		case e:
//			if (para1 != null && para1.matches("\\d*"))
//				this.editDataRoleUID = Long.parseLong(para1);
//			if (this.editDataRoleUID >= 0L && para1 == null)
//				this.editDataRoleUID = -1L;
//			break;
//		case n:
//			if (this.editDataRoleUID >= 0L && para1 != null)
//				md.getDataRole(this.editDataRoleUID).setName(para1.trim());
//			break;
//		default:
//			break;
//		}
//		String[] str = new String[] { "PLAYERS",
//				"editDataGameUID  : " + this.editDataGameUID,
//				"editDataPlayerUID: " + this.editDataRoleUID,
//				"editDataNationUID: " + this.editDataNationUID,
//				"editDataClanUID  : " + this.editDataClanUID,
//				"editDataPersonUID: " + this.editDataPersonUID };
//		this.setInformation(Screen.MI, str, false, true);
//	}
//
//	public final void nations(CCNation.c c, String para1, String para2) {
//		MetaData md = ((EditorModel) this.getModel()).getMetaData();
//		// Nationen sind Games zugeordnet. Game muss im Editor liegen
//		if (this.editDataGameUID < 0L) {
//			this.setInformation(Screen.MI,
//					new String[] { "Bitte vorher ein Spiel auswählen!" },
//					false, true);
//			return;
//		}
//		DataGame dg = md.getDataGame(this.editDataGameUID);
//		assert dg != null;
//		DataNation dn = dg.getDsc().getDataNation(this.editDataNationUID);
//
//		switch (c) {
//		case sw:
//			// Instanziert den Names-View des MVC mit sich selber als Observable
//			// Warum immer gleich löschen???
//			this.clearnationuid();
//			this.setViewer(new EditorViewNation(this, this.getStateContext()));
//			break;
//		case r:
//			if (this.editDataNationUID < 0L || para1 != null)
//				dn.setShortname(para1.trim());
//			break;
//		case n:
//			if (this.editDataNationUID < 0L || para1 != null)
//				dn.setName(para1.trim());
//			break;
//		case e:
//			if (para1 != null && para1.matches("\\d*")) {
//				long uid = Long.parseLong(para1);
//				if (dg.getDsc().getDataNation(uid) != null)
//					this.editDataNationUID = uid;
//			}
//			break;
//		case simu:
//			if (this.editDataGameUID != 1L && this.editDataNationUID != -1L) {
//
//				// TimesController tc = dg.getTc();
//				// for(ITermin t : tc.getSchedular()){
//				// System.out.println(t.getName()+":
//				// "+t.getPiT().getDatetime());
//				// }
//
//				try {
//					new Zeitreise(dg, dn);
//				} catch (LuaException e1) {
//					ErrApp.LUAEXCEPTION.erraufruf(logger, "");
//				} catch (Exception e) {
//					ErrApp.NDEF.erraufruf(logger, "");
//				}
//
//				// tc.timestart();
//				// tc.timerestart();
//			}
//			break;
//		// case clanz:
//		// if (this.editDataNationUID < 0L || !dn.getListUID().isEmpty() ||
//		// para1 == null
//		// || !para1.matches("\\d*"))
//		// break;
//		// int clanz = Integer.valueOf(para1).intValue();
//		// if (clanz < 1)
//		// break;
//		// dn.setAnzahlClans(dg, clanz);
//		// break;
//		// case clclear:
//		// if (this.editDataNationUID < 0L || para1 != null)
//		// break;
//		// dn.setAnzahlClans(dg, 0);
//		// break;
//		default:
//			break;
//		}
//		String[] str = new String[] { "NATIONS",
//				"editDataGameUID  : " + this.editDataGameUID,
//				"editDataPlayerUID: " + this.editDataRoleUID,
//				"editDataNationUID: " + this.editDataNationUID,
//				"editDataClanUID  : " + this.editDataClanUID,
//				"editDataPersonUID: " + this.editDataPersonUID };
//		this.setInformation(Screen.MI, str, false, true);
//	}
//
//	public final void clans(CCClan.c c, String para1, String para2) {
//		MetaData md = ((EditorModel) this.getModel()).getMetaData();
//		// Clans sind Nationen zugeordnet. Nation und Game muss im Editor liegen
//		if (this.editDataGameUID < 0L || this.editDataNationUID < 0L) {
//			this
//					.setInformation(
//							Screen.MI,
//							new String[] { "Bitte vorher ein Spiel/Nation auswählen!" },
//							false, true);
//			return;
//		}
//		DataGame dg = md.getDataGame(this.editDataGameUID);
//		assert dg != null;
//		DataNation dn = dg.getDsc().getDataNation(this.editDataNationUID);
//		assert dn != null;
//		DataClan dc = dg.getDsc().getDataClan(this.editDataClanUID);
//
//		switch (c) {
//		case sw:
//			// Instanziert den Names-View des MVC mit sich selber als Observable
//			// Warum immer gleich löschen???
//			this.clearclanuid();
//			this.setViewer(new EditorViewClan(this, this.getStateContext()));
//			break;
//		case r:
//			if (this.editDataClanUID < 0L || para1 != null)
//				dc.setShortname(para1.trim());
//			break;
//		case n:
//			if (this.editDataClanUID < 0L || para1 != null)
//				dc.setName(para1.trim());
//			break;
//		case e:
//			if (para1 != null && para1.matches("\\d*")) {
//				long uid = Long.parseLong(para1);
//				if (dg.getDsc().getDataClan(uid) != null)
//					this.editDataClanUID = uid;
//			}
//			break;
//		// case peanz:
//		// if (this.editDataClanUID < 0L || !dc.getListUID().isEmpty() || para1
//		// == null
//		// || !para1.matches("\\d*"))
//		// break;
//		// int peanz = Integer.valueOf(para1).intValue();
//		// if (peanz < 1)
//		// break;
//		// dc.setAnzahlPersons(dg, peanz);
//		// break;
//		// case peclear:
//		// if (this.editDataClanUID < 0L || para1 != null)
//		// break;
//		// dc.setAnzahlPersons(dg, 0);
//		// break;
//		case pelist:
//			// Instanziert den Names-View des MVC mit sich selber als Observable
//			this.setViewer(new EditorViewClanPeList(this, this
//					.getStateContext()));
//			break;
//		default:
//			break;
//		}
//		String[] str = new String[] { "CLANS",
//				"editDataGameUID  : " + this.editDataGameUID,
//				"editDataPlayerUID: " + this.editDataRoleUID,
//				"editDataNationUID: " + this.editDataNationUID,
//				"editDataClanUID  : " + this.editDataClanUID,
//				"editDataPersonUID: " + this.editDataPersonUID };
//		this.setInformation(Screen.MI, str, false, true);
//	}
//
//	public final void persons(CCPerson.c c, String para1, String para2) {
//		MetaData md = ((EditorModel) this.getModel()).getMetaData();
//		// this.deleteObserver(this.getView());
//		DataGame dg = md.getDataGame(this.editDataGameUID);
//		assert dg != null;
//		DataNation dn = dg.getDsc().getDataNation(this.editDataNationUID);
//		assert dn != null;
//		DataClan dc = dg.getDsc().getDataClan(this.editDataClanUID);
//		assert dc != null;
//		DataPerson dp = dg.getDsc().getDataPerson(this.editDataPersonUID);
//		switch (c) {
//		case sw:
//			// Instanziert den Names-View des MVC mit sich selber als Observable
//			// Warum immer gleich löschen???
//			this.clearpersonuid();
//			this.setViewer(new EditorViewPerson(this, this.getStateContext()));
//			break;
//		case r:
//			if (this.editDataPersonUID < 0L || para1 != null)
//				dp.setShortname(para1.trim());
//			break;
//		case n:
//			if (this.editDataPersonUID < 0L || para1 != null)
//				dp.setName(para1.trim());
//			break;
//		case e:
//			if (para1 != null && para1.matches("\\d*"))
//				this.editDataPersonUID = Long.parseLong(para1);
//			break;
//		default:
//			break;
//		}
//		String[] str = new String[] { "PERSONS",
//				"editDataGameUID  : " + this.editDataGameUID,
//				"editDataPlayerUID: " + this.editDataRoleUID,
//				"editDataNationUID: " + this.editDataNationUID,
//				"editDataClanUID  : " + this.editDataClanUID,
//				"editDataPersonUID: " + this.editDataPersonUID };
//		this.setInformation(Screen.MI, str, false, true);
//
//	}

	// ---- Recorder
	// ------------------------------------------------------------

//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see name.tfossi.apolge.hci.AMenu#playRecorder(java.lang.String)
//	 */
//
//	public final void playRecorder(final String macroname) {
//		if(LOGGER) logger.trace("Enter ");
//		super.playRecorder(macroname, this.getClass());
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

	// ---- Menuwechsel
	// ----------------------------------------------------------

//	/** Befehl zum Starten des Creditmenüs */
//	public final void credit() {
//		if(LOGGER) logger.trace("Enter ");
//
//		// Den Credit-State einstellen
//		IState creditMenu = new CreditMenu(this, this.getStateContext());
//		// Loggen, das zum nächstem Menu gesprungen wird
//		if(LOGGER) logger.trace("Weiter" + NTAB + "Eintrag \"fromState\" ist: \"" + this
//				+ "\"" + NTAB + "Nächster State (Menu) \"" + creditMenu
//				+ "\" wird aufgerufen.");
//		// Menu verlassen (Statewechsel, Konsole)
//		this.setExitMenu();
//		// Menu verlassen (Statewechsel, SWT)
//		this.getView().setExitSWTView();
//		// Neuen State CreditMenu einstellen
//		this.getStateContext().setActualState(creditMenu);
//
//		if(LOGGER) logger.trace("Exit ");
//	}

	// ---- State
	// ----------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * 
	 * @see name.tfossi.apolge.hci.AMenu#actionState()
	 */
	@Override
	public final void actionState() {
		if(LOGGER) logger.debug("STATE::STATECONTEXT\tKontrolle an ["
				+ this.getClass().getSimpleName() + "] übergeben.");
		this.initiate(EditorModel.getInstance(), new EditorView(this.getStateContext().getCntr()));
		((AModel) this.getModel()).addObserver(this);
		
		// Die Daten sind individuell zu den Menus und daher müssen sich die
		// Menüs jedesmal neu an den Screen angemeldet werden.
		// this.getModel().addCentralObserver(this);
		Cntr cntr = this.getStateContext().getCntr(); 
		
		cntr.initMenuWidgets(this);

		this.getView().initiate(this, this.getStateContext());
		// Bei den Befehlen den Receiver eintragen
		for (ICmd ac : this.getModel().getApplCmdList()) {
			ac.setReceiver(this, this.GRAYEDMENU);

		}
		// Bei den Befehlen den Receiver eintragen
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
		// Da ist die große Eingabeschleife!
		this.notifyViewer(this.getView());


		// Hier komme ich vorbei, wenn ich das Menu wechsle /UP
		this.getStateContext().getCntr().unvisibleMenuScreen();
		
		if(LOGGER) logger.trace("STATE::STATECONTEXT\tVerlasse "
				+ this.getClass().getSimpleName() + NTAB
				+ "Kontrolle wieder an StateContext \""
				+ this.getStateContext().getClass().getSimpleName()
				+ "\" zurück.");
	}
//	private Object getCentralInformation(EditorView av) {
//		return av.outputCentralInformation();
//	}
//	public String[] getCentralInformation(EditorViewName av) {
//		return av.output(this.names);
//	}
//
//	public String[] getCentralInformation(EditorViewGame av) {
//		return av.output(this, this.editDataGameUID);
//	}
//
//	public String[] getCentralInformation(EditorViewNation av) {
//		return av.output(this, this.editDataNationUID, this.editDataGameUID);
//	}
//
//	public String[] getCentralInformation(EditorViewClan av) {
//		return av.output(this, this.editDataClanUID, this.editDataGameUID);
//	}
//
//	/**
//	 * @param editorViewClanPeList
//	 * @return
//	 */
//	public String[] getCentralInformation(EditorViewClanPeList av) {
//		return av.output(this, this.editDataClanUID, this.editDataGameUID);
//	}
//
//	public String[] getCentralInformation(EditorViewPerson av) {
//		return av.output(this, this.editDataPersonUID, this.editDataGameUID);
//	}
//
//	/**
//	 * @param editorViewPlayers
//	 * @return
//	 */
//	public String[] getCentralInformation(EditorViewRole av) {
//		return av.output(this, this.editDataRoleUID);
//	}
//
//	public String[] getCentralInformation(EditorViewRoot av) {
//		assert false : "";
//		return null;
//	}	
//	
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see name.tfossi.apolge.hci.IMenu#getCentralInformation(java.lang.Object)
//	 */
//	public Object[] getCentralInformation(Object view) {
////		if (view instanceof EditorViewName)
////			return this.getCentralInformation((EditorViewName) view);
////		if (view instanceof EditorViewGame)
////			return this.getCentralInformation((EditorViewGame) view);
////		if (view instanceof EditorViewNation)
////			return this.getCentralInformation((EditorViewNation) view);
////		if (view instanceof EditorViewClan)
////			return this.getCentralInformation((EditorViewClan) view);
////		if (view instanceof EditorViewClanPeList)
////			return this.getCentralInformation((EditorViewClanPeList) view);
////		if (view instanceof EditorViewPerson)
////			return this.getCentralInformation((EditorViewPerson) view);
////		if (view instanceof EditorViewRole)
////			return this.getCentralInformation((EditorViewRole) view);
//
//		return (Object [])this.getCentralInformation((EditorView) view);
//	}
//	 ---- Command Pattern
	// ------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * 
	 * @see name.tfossi.apolge.command.IReceiver#first()
	 */
	@Override
	public final void first() {
		this.nextView = new EditorView(this.getStateContext().getCntr());
		if(LOGGER) logger.debug("MVC::\tVerlasse View: ["
				+ this.getView().getClass().getSimpleName() + "] " + NTAB
				+ "\tWechsle zu: [" + this.nextView.getClass().getSimpleName()
				+ "]");
		this.getView().setExitSWTView();
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see name.tfossi.apolge.hci.AMenu#root()
	 */
	@Override
	public final void root() {
		this.nextView = new EditorView(this.getStateContext().getCntr());
		if(LOGGER) logger.info("MVC::\tVerlasse View ["
				+ this.getView().getClass().getSimpleName() + "]!");
		this.getView().setExitSWTView();

	}
	

	// ---- Selbstverwaltung
	// -----------------------------------------------------
	/** serialVersionUID */
	@SuppressWarnings("unused")
	private final static long serialVersionUID = VERSION;

	/** logger */
	private final static Logger logger = Logger
			.getLogger(EditorMenu.class.getPackage().getName());

	/**
	 * <b>Menüsteuerung</b><br>
	 * State Pattern<br>
	 * <b>Daten- und Viewersteuerung</b><br>
	 * MVC Pattern<br>
	 * 
	 * @param fromState
	 *            <ul>
	 *            <li>!=null: Enthält das aufrufende (==instanzierende) Menu.
	 *            Es ist der Aufrufer, der bei {@link #back()} wieder
	 *            angesprungen wird.</li>
	 *            <li>==null: Ist das initiale Menu und das Programm wird beim
	 *            Rücksprung {@link #back()} beendet.</li>
	 * @param statecontext
	 *            Enthält den {@link IStateContext StateContext}, der die
	 *            Statesteuerung übernimmt. Siehe auch
	 *            {@link #getStateContext()}
	 * @.post Menu,Model und Viewer vom EditorMenu sind instanziert
	 */
	public EditorMenu(final AMenu fromState, final IStateContext statecontext) {
		super(fromState, statecontext, GRAYEDEDITORMENU);

		if(LOGGER) logger.info("Bezieht das Datenmodell des Editor-MVC");
		this.setModel(EditorModel.getInstance());

		if(LOGGER) logger.info("Baue Viewer für MVC zusammen");
//		 Instanziert den View des MVC mit sich selber als Observable
		this.setViewer(new EditorView(this.getStateContext().getCntr()));

		// this.getModel().setCentralText(new String[] { "Mir fiel noch was ein"
		// });
	}

}
