/**
 * GuiLoop.java
 * Branch io
 * APolGe
 * tfossi-team
 * licence GPLv3  
 */
package tfossi.apolge.io.screenfactory;


import static tfossi.apolge.common.constants.ConstMethod.getScreens;
import static tfossi.apolge.common.constants.ConstValue.LOGGER;
import static tfossi.apolge.common.constants.ConstValue.NTAB;
import static tfossi.apolge.common.constants.ConstValueExtension.APPLICATIONSCREENS;
import static tfossi.apolge.common.constants.ConstValueExtension.MENUSCREENS;
import static tfossi.apolge.common.constants.ConstValueExtension.PARAMETERSCREENS;
import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;
import static tfossi.apolge.common.constants.ConstValueExtension.VIEWSCREENS;

import java.util.Observable;

import org.apache.log4j.Logger;

import tfossi.apolge.common.cmd.CommandList;
import tfossi.apolge.common.cmd.ICmd;
import tfossi.apolge.common.error.ErrApp;
import tfossi.apolge.common.hci.IMenu;
import tfossi.apolge.common.hci.IView;
import tfossi.apolge.io.Screen;
import tfossi.apolge.io.console.Key;
import tfossi.apolge.common.macrorecorder.IRecorder;

/**
 * Programmsteuerung für GUI
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
public class GuiLoop extends ALoop {
	/** Nimmt die Fassade auf */
	private final Cntr facade;

	/* (non-Javadoc)
	 * @see tfossi.apolge.io.screenfactory.ALoop#notifyViewer(tfossi.apolge.common.hci.IView, tfossi.apolge.common.hci.IMenu)
	 */
	@Override
	final void notifyViewer(IView viewer, IMenu menu) {
		// Die Schleife bei SWT-Einsatz
		// Nur bei Back oder neues Menu!

		Screen[] scr = getScreens(APPLICATIONSCREENS, MENUSCREENS, VIEWSCREENS,
				PARAMETERSCREENS);
		
		while (!menu.getExitMenu() && !this.facade.isDisposed()) {
			if(LOGGER) logger.debug("\n--------------\nSTART VIEWLOOP\n--------------\n");
			// Aufruf des Viewers. Nachrichtlich, welche Screen upgedated
			// werden sollen.
			// Markiert, dass das Object sich geändert hat.
			menu.informObserver((Observable) menu, (Object) scr);
			// Bei GUI-Netrieb geht es hier erst dann weiter, wenn das
			// Fenster geschlossen oder das Menu verlassen wurde.
			//
			if(LOGGER) logger.debug("\n--------------\nEND VIEWLOOP\n--------------\n");

			// Hier komme ich vorbei, wenn ich den View wechsle /UP
			this.facade.unvisibleViewScreen();
			this.facade.unvisibleParameterScreen();
			menu.setNextView(menu);
		}
	}

	/* (non-Javadoc)
	 * @see name.tfossi.apolge.io.screenfactory.ALoop#show(name.tfossi.xapolge201rc.hci.IMenu, name.tfossi.apolge.io.Screen[], name.tfossi.apolge.common.cmd.CommandList)
	 */
	@Override
	final void show(IMenu menu,
			NotifyScreens notifyScreens,
			CommandList commands) {
		// Not for GUI
	}

	/* (non-Javadoc)
	 * @see name.tfossi.apolge.io.screenfactory.ALoop#update(name.tfossi.xapolge201rc.hci.IMenu, name.tfossi.apolge.io.Screen[], name.tfossi.apolge.common.cmd.CommandList, int)
	 */
	@Override
	final void update(IMenu menu, NotifyScreens notifyScreens, CommandList commands, int cnt) {
		// Leite über zum spezifischen Teil "Grafische Steuerung"
		this.facade.refreshScreens(notifyScreens.scr, menu.getView());
		// @modified tfossi, 14.08.2014, Interface IRecorder
		if (IRecorder.recorder.isPlay() && notifyScreens.eingabe ) {
			if(LOGGER) logger.debug("IO::GUI\tStart der Aus- und Eingabe." + NTAB
					+ menu.getClass().getSimpleName() + NTAB
					+ "Der Recorder läuft, die Eingabe wird über Console umgeleitet.");
			this.updateConsole(menu, notifyScreens, commands);
		} else if (cnt == 1) {
			if(LOGGER) logger.debug("IO::GUI\tStart der Aus- und Eingabe." + NTAB
					+ menu.getClass().getSimpleName() + NTAB
					+ "Die Eingabe wird nur verlassen, wenn" + NTAB
					+ "die Order syntaktisch in Ordnung ist.");
			this.guiLoop(menu.getView());
		} else {
			if(LOGGER) logger.debug("IO::GUI\tStart der Aus- und Eingabe." + NTAB
					+ menu.getClass().getSimpleName() + NTAB + "Die Eingabe ist schon aktiv.");
		}
	}

	/** Markiert aktive Consoleneingabe */
	private static boolean console = false;

	/**
	 * Ist für Recorder: updatefunktion mit Befehlseingabe und Kommandoaufruf
	 * 
	 * @param menu
	 *            aktuelles Menu
	 * @param notifyScreens -
	 * @param commands
	 *            gültige Liste der Kommandos
	 */
	private final void updateConsole(IMenu menu, NotifyScreens notifyScreens, CommandList commands) {
		// Leite über zum spezifischen Teil "Textkonsole"

		if(!notifyScreens.eingabe) return; 
		if(LOGGER) logger.debug("IO::Console Start der Aus- und Eingabe." + NTAB + "Menu(State): "
				+ menu.getClass().getSimpleName() + NTAB + "Die Eingabe wird nur verlassen, wenn"
				+ NTAB + "die Order syntaktisch in Ordnung ist.");
		if (GuiLoop.console) {
			if(LOGGER) logger.debug("Input Order ist in USE");
			return;
		}
		// Die Befehlseingabe
		ICmd cmd = null;
		try {
			GuiLoop.console = true;
			if(LOGGER) logger.debug("Gesamtliste der Commands:" + NTAB + commands.toString());
			if ((cmd = this.inputOrder(commands, menu.toString())) == null)
				return;
			if(LOGGER) logger.debug("Den Befehl [" + cmd + "] ausführen.");

			// Befehl ausführen ...
			cmd.command();
			// ...!

			if(LOGGER) logger.trace("Der Befehl [" + cmd + "] ist ausgeführt.");
			GuiLoop.console = false;
		} catch (NullPointerException e) {
			ErrApp.NDEF.erraufruf("Controller: " + menu + NTAB + "Cmd       : " + cmd);
		}
	}

	/**
	 * Eingabe per Recorder
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
			if(LOGGER) logger.debug("Der Befehl lautet " + cmd + NTAB
					+ (cmd == null ? "" : "mit Parameter: " + cmd.getParameter().toString()));
		} catch (NullPointerException e) {
			if(LOGGER) logger.debug("ABBRUCH");
		} catch (Exception e) {
			ErrApp.NDEF.erraufruf("");
		}
		return cmd;
	}

	/**
	 * GUI - Display-Eingabeschleife
	 * 
	 * @param view
	 *            aktueller View
	 */
	private final void guiLoop(IView view) {
		if(LOGGER) logger.debug("SWT::****GUILOOP****" + NTAB + NTAB + "****GUILOOP****");
		while (!this.facade.isDisposed() && !view.isExitSWTView()) {
			this.facade.displayLive();
		}
	}

	// ---- Selbstverwaltung --------------------------------------------------
	/** serialVersionUID */
	@SuppressWarnings("unused")
	private final static long serialVersionUID = VERSION;

	/** logger */
	private final static Logger logger = Logger.getLogger(GuiLoop.class.getPackage().getName());

	/**
	 * @param cntr
	 *            Fassade
	 */
	public GuiLoop(Cntr cntr) {
		if(LOGGER) logger.trace("Factoryprodukt");
		this.facade = cntr;
	}

}
