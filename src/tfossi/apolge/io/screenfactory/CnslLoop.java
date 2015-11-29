/** 
 * CnslLoop.java
 * Branch io
 * APolGe
 * tfossi-team
 * licence GPLv3 
 */
package tfossi.apolge.io.screenfactory;


import static tfossi.apolge.common.constants.ConstValue.LOGGER;
import static tfossi.apolge.common.constants.ConstValue.NTAB;
import static tfossi.apolge.common.constants.ConstValue.TAB;
import static tfossi.apolge.common.constants.ConstValueExtension.CONSOLENSCREENS;
import static tfossi.apolge.common.constants.ConstValueExtension.HEADTEXT;
import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;

import java.util.Arrays;
import java.util.Observable;

import org.apache.log4j.Logger;

import tfossi.apolge.common.cmd.CommandList;
import tfossi.apolge.common.cmd.ICmd;
import tfossi.apolge.common.error.ErrApp;
import tfossi.apolge.common.hci.IMenu;
import tfossi.apolge.common.hci.IView;
import tfossi.apolge.io.Screen;
import tfossi.apolge.io.console.Cnlout;
import tfossi.apolge.io.console.Key;

/**
 * Programmsteuerung für Cnsl
 * 
 * @.pattern Abstract Factory: Concrete Product
 * @see Cntr
 * @see AFactory
 * @see ALoop
 * @author tfossi
 * @version 12.08.2014
 * @modified -
 * @since Java 1.6
 */
public class CnslLoop extends ALoop {
	/** Nimmt die Fassade auf */
	private final Cntr facade;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * name.tfossi.apolge.io.screenfactory.ALoop#notifyViewer(name.tfossi
	 * .xapolge201rc.hci.IView, name.tfossi.xapolge201rc.hci.IMenu)
	 */
	@Override
	final void notifyViewer(IView viewer, IMenu menu) {
		// Die Schleife bei Consolen-Einsatz
		// Nur bei Back oder neues Menu!

		NotifyScreens ns = new NotifyScreens(CONSOLENSCREENS);

		while (!menu.getExitMenu()) {
			if(LOGGER) logger.debug("\n--------------\nSTART VIEWLOOP\n--------------\n");
			// Aufruf des Viewers. Nachrichtlich, welche Screen upgedated
			// werden sollen.
			// Markiert, dass das Object sich geändert hat.
			menu.informObserver((Observable) menu, (Object) ns);
			// Bei Consolenbetrieb geht es hier erst dann weiter, wenn ein
			// Befehl eingegeben wurde.
			if(LOGGER) logger.debug("\n--------------\nEND VIEWLOOP\n--------------\n");
			menu.setNextView(menu);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * name.tfossi.apolge.io.screenfactory.ALoop#show(name.tfossi.xapolge201rc
	 * .hci.IMenu, name.tfossi.apolge.io.Screen[],
	 * name.tfossi.apolge.common.cmd.CommandList)
	 */
	@Override
	final void show(final IMenu menu, final NotifyScreens notifyScreens,
			CommandList commands) {
		if(LOGGER) logger.debug("I/O::Console Ausgabe von \""
				+ menu.getClass().getSimpleName() + "\" " + NTAB
				+ Arrays.asList(notifyScreens));
		Object[] info = null;
		// Anzeige der Screen
		for (Screen scr : notifyScreens.scr) {
			// 1. Beziehe den fixen Inhalt
			// 2. Beziehe die Parameter
			// 3. Setze die Parameter in 1. ein
			// 4. Zeige das Ganze
			switch (scr) {
			case TITEL:
				String strt = menu.getView().getClass().getSimpleName();
				if (strt != null) {
					Cnlout.toutLeftText(strt + " - V" + HEADTEXT);
				} else {
					Cnlout.touttxt(" ");
				}
				break;
			case C:
				info = this.facade.get_Content(menu.getView(), null, scr)
						.getString();
				if (info != null) {
					for (Object row : info)
						Cnlout.touttxt((String) row);
				} else {
					Cnlout.touttxt(" ");
				}
				break;
			case VI:
				Cnlout.toutFrameRow();
				info = this.facade.get_Content(menu.getView(), null, scr)
						.getString();
				if (info != null) {
					for (Object row : info)
						Cnlout.touttxt((String) row);
				} else {
					Cnlout.touttxt(" ");
				}
				break;
			case MI:
				Cnlout.toutFrameRow();
				info = this.facade.get_Content(null, menu, scr).getString();
				if (info != null) {
					for (Object row : info)
						Cnlout.touttxt((String) row);
				} else {
					Cnlout.touttxt(" ");
				}
				break;
			case M:
				Cnlout.toutFrameRow();
				info = this.facade.get_Content(null, null, scr).getString();
				if (info != null) {
					for (Object row : info)
						Cnlout.touttxt((String) row);
				} else {
					Cnlout.touttxt(" ");
				}
				break;
			case VCM:
				Cnlout.toutFrameRow();
				if (menu.getStateCmdList() != null) {
					String txt = new String();
					for (ICmd cmd : menu.getStateCmdList()) {
						if(cmd.isUnvisible())
							continue;
						txt += cmd.getCode() + TAB;
					}
					Cnlout.touttxt(txt + TAB);
				} else {
					Cnlout.touttxt(" ");
				}
				commands.clear();
				commands.addAll(menu.getApplCmdList());
				commands.addAll(menu.getStateCmdList());
				break;
			case CPM:
				break;
			case MCM:
				Cnlout.toutFrameRow();
				if (menu.getApplCmdList() != null) {
					String txt = new String();
					for (ICmd cmd : menu.getApplCmdList()) {
						if(cmd.isUnvisible())
							continue;
						txt += cmd.getCode() + TAB;
					}
					Cnlout.touttxt(txt + TAB);
				} else {
					Cnlout.touttxt(" ");
				}
				commands.clear();
				commands.addAll(menu.getApplCmdList());
				commands.addAll(menu.getStateCmdList());
				break;
			default:
				break;
			}
		}
		// Ausgabe der Abschlußlinie
		Cnlout.toutFrameRow();
	}

	/** Markiert aktive Consoleneingabe */
	private static boolean console = false;

	/*
	 * (non-Javadoc)
	 * 
	 * @see name.tfossi.apolge.io.screenfactory.ALoop#update(name.tfossi.
	 * xapolge201rc.hci.IMenu, name.tfossi.apolge.io.Screen[],
	 * name.tfossi.apolge.common.cmd.CommandList, int)
	 */
	@Override
	final void update(IMenu menu, NotifyScreens notifyScreens,
			CommandList commands, int cnt) {
		// Leite über zum spezifischen Teil "Textkonsole"
		if (!notifyScreens.eingabe)
			return;
		if(LOGGER) logger.debug("IO::Console Start der Aus- und Eingabe." + NTAB
				+ "Menu(State): " + menu.getClass().getSimpleName() + NTAB
				+ "Die Eingabe wird nur verlassen, wenn" + NTAB
				+ "die Order syntaktisch in Ordnung ist.");
		if (CnslLoop.console) {
			if(LOGGER) logger.debug("Input Order ist in USE");
			assert false;
			return;
		}
		// Die Befehlseingabe
		ICmd cmd = null;
		try {
			CnslLoop.console = true;
			if(LOGGER) logger.debug("Gesamtliste der Commands:" + NTAB
					+ commands.toString());
			if ((cmd = this.inputOrder(commands, menu.toString())) != null) {
				if(LOGGER) logger.debug("Den Befehl [" + cmd + "] ausführen.");

				// Befehl ausführen ...
				cmd.command();
				// ...!

				if(LOGGER) logger.trace("Der Befehl [" + cmd + "] ist ausgeführt.");
			}
			CnslLoop.console = false;
		} catch (NullPointerException e) {
			ErrApp.NDEF.erraufruf("Controller: " + menu + NTAB
					+ "Cmd       : " + cmd);
		}
	}

	/**
	 * Eingabe per Tastatur oder Recorder
	 * 
	 * @param cmds
	 *            Liste der gültigen Kommandos
	 * @param menu
	 *            aktuelles Menu
	 * @return identifizierter Befehl
	 */
	@SuppressWarnings("static-method")
	private final ICmd inputOrder(final CommandList cmds, final String menu) {
		ICmd cmd = null;
		try {
			cmd = Key.eingabe(cmds, menu).clone();
			if(LOGGER) logger.debug("Der Befehl lautet "
					+ cmd
					+ NTAB
					+ (cmd == null ? "" : "mit Parameter: "
							+ cmd.getParameter().toString()));
		} catch (NullPointerException e) {
			if(LOGGER) logger.debug("ABBRUCH");
		} catch (Exception e) {
			ErrApp.NDEF.erraufruf("");
		}
		return cmd;
	}

	// ---- Selbstverwaltung --------------------------------------------------
	/** serialVersionUID */
	@SuppressWarnings("unused")
	private final static long serialVersionUID = VERSION;
	/** logger */
	private final static Logger logger = Logger.getLogger(CnslLoop.class
			.getPackage().getName());

	/**
	 * @param cntr
	 *            Fassade
	 */
	public CnslLoop(Cntr cntr) {
		if(LOGGER) logger.trace("Factoryprodukt");
		this.facade = cntr;
	}

}
