/** 
 * CommandMaps.java
 * Branch cmd
 * APolGe
 * tfossi-team
 * licence GPLv3 
 */
package tfossi.apolge.common.cmd;


import static tfossi.apolge.common.constants.ConstValue.LOGGER;
import static tfossi.apolge.common.constants.ConstValue.LOGTAB;
import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.apache.log4j.Logger;

import tfossi.apolge.common.scripting.ArrayIndexInnerBoundsException;
import tfossi.apolge.common.scripting.LoadScript;
import tfossi.apolge.common.scripting.t.Table;

/**
 * Enthält alle Befehle vom Typ {@link ACmd} in Maps.
 * Liefert aus einer Wunschliste eine zusammengestellte Map
 * {@link #fetchList(String...)} aller möglichen Befehle.
 * 
 * 
 * @see ACmd
 * @see CommandMap
 * @see CommandList
 * @author tfossi
 * @version 13.08.2014
 * @modified -
 * @since Java 1.6
 */
public class CommandMaps {
	/**
	 * Speichert alle möglichen Befehle vom Typ
	 * <code>HashMap &lt;String, AbstractCmd&gt;</code><br>
	 */
	private final static CommandMap cmds = new CommandHash();

	/**
	 * Erweitert die Kommandoliste um die gewünschten
	 * Befehle.
	 * 
	 * @param wanted
	 *            die gewünschten Befehle
	 * @return die Mapliste
	 * @see ACmd
	 */
	@SuppressWarnings("static-method")
	public synchronized final CommandList fetchList(final String... wanted) {
		if(LOGGER) logger.trace("Die Commandlist zusammenstellen");
		// Ergebnisliste
		CommandList rc = new CommandArray();
		if (wanted != null) {
			// Gehe alle Befehle durch und baue eine Liste
			// nach der Anforderung
			// wanted
			// zusammen!
			for (String key : wanted) {
				if (!cmds.containsKey(key))
					assert false; //	ErrApp.WRONGCOMMANDKEY.erraufruf("Key=" + key);
				// Wichtig: Clone, da sonst die Receiver im
				// Command
				// überschrieben werden!
				// Bei konkretem Befehl wieder einen Clone
				// verwenden, der
				// Receiver ist
				// derselbe,
				// die Parameter sind anders!
				ACmd ac = cmds.get(key).clone();
				rc.add(ac);
			}
		}
		return rc;
	}

	/**
	 * TODO Comment
	 * @param wanted -
	 * @return -
	 * @modified - 
	 */
	@SuppressWarnings("static-method")
	public synchronized final ICmd fetchCmd(final String wanted) {
		if(LOGGER) logger.trace("Einen Command indentifizieren");
		if (wanted != null) {
			// Suche den Befehl
			if (!cmds.containsKey(wanted))
				assert false; //	ErrApp.WRONGCOMMANDKEY.erraufruf("Key=" + wanted + LFCR + cmds.keySet());
			// Wichtig: Clone, da sonst die Receiver im
			// Command
			// überschrieben werden!
			// Bei konkretem Befehl wieder einen Clone
			// verwenden, der
			// Receiver ist
			// derselbe,
			// die Parameter sind anders!
			return cmds.get(wanted).clone();
		}
		return null;
	}

	// ---- Selbstverwaltung
	// -----------------------------------------------------
	/** serialVersionUID */
	@SuppressWarnings("unused")
	private final static long serialVersionUID = VERSION;
	/** logger */
	private final static Logger logger = Logger.getLogger(CommandMaps.class.getPackage().getName());

	/**
	 * Stellt die allgemeingültigen Kommandos zusammen bzw.
	 * erweitert die Liste um die gewünschten Befehle. Der
	 * Aufruf vom Viewer ist
	 * <code>new CommandMaps().fetchList([Befehlsliste])</code>
	 * 
	 * @.post Die Map mit den Befehlen ist angelegt
	 * @see ACmd
	 */
	@SuppressWarnings({ "null", "unused" })
	public CommandMaps() {

		if (cmds.size() == 0) { // Immutable: Einmal anlegen
			if(LOGGER) logger.debug("Die allgemeine Befehlsliste wird angelegt.");

			Table cpm = null;
//			FIXME 13.01.2015
//			try {
//				FIXME 13.01.2015
				cpm = null; //CONFIG_SCRIPT.getTableValue("CMP");
//				FIXME 13.01.2015
//			} catch (ParseException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//				assert false;
//			}
			for (String name : cpm.keySet()) {
				try {

					Table entry = LoadScript.getTableValue(cpm, name);
//					Boolean visible = new Boolean(!LoadScript.getBoolValue(entry, "visible"));
//					FIXME 13.01.2015
					Boolean visible = null; //new Boolean(LoadScript.getBoolValue(entry, "visible"));
//					FIXME 13.01.2015
					String classname = this.getClass().getPackage().getName() + ".cmds."; // + LoadScript.getStringValue(entry, "class");
					@SuppressWarnings("unchecked")
					Class<? extends ACmd> clazz = (Class<ACmd>) Class.forName(classname);

					Constructor<? extends ACmd> constructor = clazz.getConstructor(String.class, boolean.class);

					cmds.put(name, constructor.newInstance(name, Boolean.valueOf(!visible.booleanValue())));

					if(LOGGER) logger.debug("Order " + name + "=\"" + cpm.get(name) + "\" angelegt.");

				} catch (ClassNotFoundException e) {
					if(LOGGER) logger.warn("ClassNotFoundException Order " + name + "=\"" + cpm.get(name) + "\" ");
				} catch (SecurityException e) {
					if(LOGGER) logger.warn("SecurityException Order " + name + "=\"" + cpm.get(name) + "\" ");
				} catch (NoSuchMethodException e) {
					if(LOGGER) logger.warn("NoSuchMethodException Order " + name + "=\"" + cpm.get(name) + "\" ");
				} catch (IllegalArgumentException e) {
					if(LOGGER) logger.warn("Die Anzahl der Parameter ist falsch beziehungsweise eine Konvertierung der Parameterwerte in die benötigten Typen nicht möglich. IllegalArgumentException Order "
							+ name + "=\"" + cpm.get(name) + "\" ");
				} catch (InstantiationException e) {
					if(LOGGER) logger.warn("Das Constructor-Objekt bezieht sich auf einen Konstruktor einer abstrakten Klasse. InstantiationException Order "
							+ name + "=\"" + cpm.get(name) + "\" ");
				} catch (IllegalAccessException e) {
					if(LOGGER) logger.warn("Auf den Konstruktor kann nicht zugegriffen werden (zum Beispiel, weil er privat ist). IllegalAccessException Order "
							+ name + "=\"" + cpm.get(name) + "\" ");
				} catch (InvocationTargetException e) {

					if(LOGGER) logger.warn("InvocationTargetException Order " + name + "=\"" + cpm.get(name) + "\" " + LOGTAB + e.getCause()
							+ LOGTAB + e.getTargetException());
		
				} catch (NullPointerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchFieldException e) {
					logger.fatal(e.getMessage());
					// TODO Auto-generated catch block
					e.printStackTrace();
					assert false;
					System.exit(0);
				} catch (ArrayIndexOutOfBoundsException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					assert false;
					System.exit(0);
				} catch (ArrayIndexInnerBoundsException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					assert false;
					System.exit(0);
				}
			}

			// } else {
			// if(LOGGER) logger.info("Commandliste ist schon angelegt."
			// + NTAB
			// + "Es geht hier nur um die fetch-Methode.");

			// cmds.put("SendAccept", new SendAccept(9));
			// cmds.put("SendPassword", new
			// SendPassword(9));

			// cmds.put("Gamelist", new Gamelist(0));

			// cmds.put("CLTAutoRun", new CLTAutoRun(9));
			// cmds.put("SendLogin", new ChgUserState(9));

			// Hidebefehle
			// cmds.put("HCMLoadLists", new
			// HCMLoadLists(9));
			// cmds.put("HCMLoadLogins", new
			// HCMLoadLogins(9));
			// cmds.put("ActivateServer", new
			// ActivateServer(9));
			// cmds.put("Gamelist", new Gamelist(9));
			// cmds.put("HCMLoginTask", new
			// HCMLoginTask(9));
			// cmds.put("HCMGameLoader", new
			// HCMGameLoader(9));
			// cmds.put("HCMLoginGame", new
			// HCMLoginGame(9));

		}
	}

	/** @return Liefert SimpleName() der Klasse */
	@Override
	public final String toString() {
		return this.getClass().getSimpleName();
	}
}
