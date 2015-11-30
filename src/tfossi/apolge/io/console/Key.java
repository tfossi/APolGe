/**
 * Key.java
 * Branch io
 * APolGe
 * tfossi-team
 * licence GPLv3  
 */
package tfossi.apolge.io.console;

import static tfossi.apolge.common.constants.ConstValue.LFCR;
import static tfossi.apolge.common.constants.ConstValue.LOGGER;
import static tfossi.apolge.common.constants.ConstValue.NTAB;
import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

import tfossi.apolge.common.cmd.ACmd;
import tfossi.apolge.common.cmd.CommandList;
import tfossi.apolge.common.cmd.ICmd;
import tfossi.apolge.common.error.UnknownOrderException;
import tfossi.apolge.common.macrorecorder.IRecorder;

/**
 * Nimmt Befehle entgegen. Überprüft auch auf gültige Eingaben und liefert verfiziertes Ergebnis
 * der Tastatureingabe zurück.
 * <p>
 * Die Besonderheit ist, das Key auch das Abspielen des Recorders übernimmt!<br>
 * <p>
 * Ein gültiger Befehl steht immer an erster Position <code>rc[0]</code>. Die nachfolgenden
 * Parameter ab <code>rc[1]</code> beginnen immer mit einem '-'
 * <p>
 * Befehl Befehl -para1 Befehl -para1 -para2 ... Befehl -para1 -para2 ...
 * 
 * @see IRecorder
 * @author tfossi
 * @version 11.08.2014
 * @modified -
 * @since Java 1.6
 */
public class Key {
	/**
	 * Analysiert den Netz-Eingabe und liefert den gültigen Befehl inkl. der Parameter.<br>
	 * <p>
	 * Ein gültiger Befehl steht immer an erster Position <code>rc[0]</code>.<br>
	 * Die nachfolgenden Parameter ab <code>rc[1]</code> beginnen immer mit einem '-'.
	 *
	 * @param cmds - 
	 * @param toCmd - 
	 * @param toObject - 
	 * @return - 
	 * @throws UnknownOrderException - 
	 * @modified - 
	 */
	public final static ICmd netEingabe(final CommandList cmds, final String toCmd,
			final Object toObject) throws UnknownOrderException {
		String[] rc = null;
		if (toCmd == null)
			return null;
		assert !(toObject instanceof String) : toObject;
		return checkit(rc, cmds, toCmd, toObject);
	}

	/**
	 * Analysiert die Eingabe und liefert den gültigen Befehl inkl. der Parameter.<br>
	 * <p>
	 * Wird der Recorder abgespielt, ergibt die interne Prüfung ein lokales ac !=null
	 * <p>
	 * Ein gültiger Befehl steht immer an erster Position <code>rc[0]</code>.<br>
	 * Die nachfolgenden Parameter ab <code>rc[1]</code> beginnen immer mit einem '-'.
	 * 
	 * @param cmds
	 *            ist die Liste mit den gültigen Befehlen
	 * @param menu
	 *            ist das dazugehörige Menu
	 * @return der ausgewählte Befehl
	 * @see ACmd 
	 * @throws NullPointerException
	 *             CommandList cmds ist leer
	 * @modified tfossi, 14.08.2014, Interface IRecorder 
	 */
	public final static ICmd eingabe(final CommandList cmds, final String menu) {		
		if (cmds == null) {
			assert false; //ErrApp.CMDLISTNULLX.erraufruf("Menü: " + menu);
			return null;
		}
		if(LOGGER) logger.debug("Kandidaten zur Eingabe sind:" + NTAB + cmds);
		// Einlesebuffer bereitstellen
		BufferedReader din = new BufferedReader(new InputStreamReader(System.in));
		// Eingabe des Befehls und Überprüfung
		try {
			// Die Einleseschleife wird nur mit verifizierter Eingabe verlassen
			// oder
			// bei völliger Pleite == null
			
			while (true) {
				String prompt = "Eingabe [" + menu + "]"
						+ (IRecorder.recorder.isRecord() ? "!" : "") + "> ";
				Cnlout.poutln(prompt);
				String line = null;
				ICmd ac = IRecorder.recorder.getPlay();
				if(LOGGER) logger.trace("Recorder PLAY "
						+ (ac == null ? "ist ausgeschaltet." : "liefert \"" + ac + "\""));
				String[] rc = null;
				// Wenn der Recorder keine Makros abspielt ...
				if (ac == null) {
					// ... dann lese den Befehl von Tastatur ein
					if ((line = din.readLine()) == null)
						return null;
					return checkit(rc, cmds, line, null);
					//
					// Wenn der Recorder Makros abspielt ...
				}
				// Direkt ist der Befehl ac nicht zu verwenden, da Receiver
				// in den
				// Makros nicht eingetragen ist!
				if(LOGGER) logger.debug("Autobefehl \"" + ac.getCode() + "\" identifiziert!");
				// ... und jetzt die Parameter
				for (ICmd cmd : cmds) {
					if (cmd.getCode().equals(ac.getCode())) {
						if(LOGGER) logger.debug("Der gesuchte Code [" + ac.getCode() + "] ist gefunden: ["
								+ cmd.getCode() + "]");
						for (String o : ac.getParameter())
							if(LOGGER) logger.debug(o.toString());
						cmd.clearParameter();
						for (String o : ac.getParameter())
							cmd.setParameter(o);
						return cmd;
					}
				}
				rc = new String[] { null };

				if(LOGGER) logger.info(":" + LFCR + "Den Befehl " + rc[0] + " gibt es nicht!" + LFCR
						+ "Die möglichen Befehle können mit \"?\" angezeigt werden." + LFCR
						+ "Bitte einen gültigen Befehl eingeben.");
			}
		} catch (IOException e) {
			assert false; //ErrApp.IOERROR.erraufruf("");
		} catch (UnknownOrderException e) {
			if(LOGGER) logger.info(":" + LFCR + "Den Befehl " + e.getMessage() + " gibt es nicht!" + LFCR
					+ "Die möglichen Befehle können mit \"?\" angezeigt werden." + LFCR
					+ "Bitte einen gültigen Befehl eingeben.");

		}
		return null;
	}

	/**
	 * @param token1
	 *            das Array mit den Token aus der Eingabe
	 * @param cmds
	 *            Erlaubte 
	 * @param line
	 *            Eingabe
	 * @param data  - 
	 * @return bekannter Befehl oder <code>null</code>
	 * @throws UnknownOrderException - 
	 * @modified - 
	 */
	private static final ICmd checkit(String[] token1, final CommandList cmds, String line,
			Object data) throws UnknownOrderException {
		assert false:"TOKEN / TOKEN1";
		// Zerlege die Eingabe in Token
		String[] token = line.split("-");
		for (ICmd cmd : cmds) {
			String code = cmd.getCode();
			if (code.equalsIgnoreCase(token[0].trim())) {
				if(LOGGER) logger.debug("Befehl " + "[" + cmd + "]" + "identifiziert!");
				// Daten eintragen
				cmd.setData(data);
				// ... und jetzt die Parameter eintragen
				cmd.clearParameter();
				// negative Zahlen sind keine Parameter!
				for (int i = token.length - 1; i >= 0; i--) {
					token[i] = "-" + token[i].trim();
					int end = token[i].indexOf(' ');
					if (end < 0)
						end = token[i].length();
					if (token[i].substring(1, end).matches("\\d*")) {
						token[i - 1] += token[i];
						token[i] = null;
					}
				}
				for (int i = 1; i < token.length; i++)
					if (token[i] != null)
						cmd.setParameter(token[i]);
				// Verlassen der Eingabeschleife mit Befehl und Parameter
				return cmd;
			}
			if(LOGGER) logger.trace("Befehl \"" + code + "\" ist nicht \"" + token[0] + "\".");
		}
		throw new UnknownOrderException("Befehl \"" + token[0] + "\" ist nicht bekannt!");
	}

	// ---- Selbstverwaltung --------------------------------------------------
	/** serialVersionUID */
	@SuppressWarnings("unused")
	private final static long serialVersionUID = VERSION;
	/** logger */
	private final static Logger logger = Logger.getLogger(Key.class.getPackage().getName());
}
