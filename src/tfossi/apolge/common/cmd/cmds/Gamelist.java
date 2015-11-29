/** 
 * Gamelist.java
 * Branch cmd
 * APolGe
 * tfossi-team
 * licence GPLv3 
 */
package tfossi.apolge.common.cmd.cmds;

import static tfossi.apolge.common.constants.ConstValue.LOGGER;
import static tfossi.apolge.common.constants.ConstValue.TAB;
import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;

import java.io.IOException;
import java.io.InvalidClassException;
import java.net.SocketException;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Widget;

import tfossi.apolge.common.cmd.ACmd;
import tfossi.apolge.common.cmd.Command;
import tfossi.apolge.common.cmd.ICmd;
import tfossi.apolge.common.cmd.IUnvisible;
import tfossi.apolge.common.error.ErrApp;
import tfossi.apolge.common.hci.AMenu;
import tfossi.apolge.common.net.INetReceiver;
import tfossi.apolge.io.ContentString;

/**
 * Befehl: Anzeige aktuelles Datum <br>
 * Gehört zur Gruppe der allgemeinen Befehle.<br/>
 * <b>Befehl: </b>date<br/>
 * <b>Optionen:</b> <li>[keine]</li>
 * 
 * @serial 1L
 * @see ACmd
 * @see AMenu
 * 
 * @author tfossi
 * @version 13.08.2014
 * @modified -
 * @since Java 1.6
 */
public class Gamelist extends ACmd implements IUnvisible {

	// ---- Command Pattern
	// ------------------------------------------------------
	/**
	 * TODO Comment
	 * 
	 * @author tfossi
	 * @version 13.08.2014
	 * @modified -
	 * @since Java 1.6
	 */
	public static enum c {
		/** sw */
		sw(false, null, null, 0, 0),
		/** ask4activeGames */
		ask4activeGames(false, null, null, 0, 0) {

			@Override
			public void call(ICmd cmd, String... value)
					throws InvalidClassException, SocketException, IOException {
				((INetReceiver) cmd.getReceiver()).sendGamelist(null,
						c.ask4activeGames, cmd.getData(), new String[0]);
			}
		},
		/**
		 * TODO Comment
		 * 
		 * @author tfossi
		 * @version 13.08.2014
		 * @modified -
		 * @since Java 1.6
		 */
		ans4activeGames(false, null, null, 0, 0) {

			@Override
			public void call(ICmd cmd, String... value)
					throws InvalidClassException, SocketException, IOException {
				((INetReceiver) cmd.getReceiver()).sendGamelist(null,
						c.ans4activeGames, cmd.getData(), new String[0]);
			}
		},
		/**
		 * TODO Comment
		 * 
		 * @author tfossi
		 * @version 13.08.2014
		 * @modified -
		 * @since Java 1.6
		 */
		ask4waitingGames(false, null, null, 0, 0) {

			@Override
			public void call(ICmd cmd, String... value)
					throws InvalidClassException, SocketException, IOException {
				((INetReceiver) cmd.getReceiver()).sendGamelist(null,
						c.ask4waitingGames, cmd.getData(), new String[0]);
			}
		},
		/**
		 * TODO Comment
		 * 
		 * @author tfossi
		 * @version 13.08.2014
		 * @modified -
		 * @since Java 1.6
		 */
		ans4waitingGames(false, null, null, 0, 0) {

			@Override
			public void call(ICmd cmd, String... value)
					throws InvalidClassException, SocketException, IOException {
				((INetReceiver) cmd.getReceiver()).sendGamelist(null,
						c.ans4waitingGames, cmd.getData(), new String[0]);
			}
		},
		/**
		 * TODO Comment
		 * 
		 * @author tfossi
		 * @version 13.08.2014
		 * @modified -
		 * @since Java 1.6
		 */
		ask4sleepingGames(false, null, null, 0, 0) {

			@Override
			public void call(ICmd cmd, String... value)
					throws InvalidClassException, SocketException, IOException {
				((INetReceiver) cmd.getReceiver()).sendGamelist(null,
						c.ask4sleepingGames, cmd.getData(), new String[0]);
			}
		},
		/**
		 * TODO Comment
		 * 
		 * @author tfossi
		 * @version 13.08.2014
		 * @modified -
		 * @since Java 1.6
		 */
		ans4sleepingGames(false, null, null, 0, 0) {

			@Override
			public void call(ICmd cmd, String... value)
					throws InvalidClassException, SocketException, IOException {
				((INetReceiver) cmd.getReceiver()).sendGamelist(null,
						c.ans4sleepingGames, cmd.getData(), new String[0]);
			}
		};

		/** show */
		final boolean show;
		/** buttontext */
		public final String buttontext;
		/** tooltiptext */
		final String tooltiptext;
		/** parameterMin */
		public final int parameterMin;
		/** parameterMax */
		public final int parameterMax;

		/**
		 * @param show
		 *            Anzeige im GUI
		 * @param buttontext
		 *            Test des Buttons
		 * @param tooltiptest
		 *            Hilfetext
		 * @param parameterMin
		 *            Minimalzahl der Parameter
		 * @param parameterMax
		 *            Maximalzahl der Parameter
		 */
		private c(boolean show, String buttontext, String tooltiptest,
				int parameterMin, int parameterMax) {
			this.show = show;
			this.buttontext = buttontext;
			this.tooltiptext = tooltiptest;
			this.parameterMin = parameterMin;
			this.parameterMax = parameterMax;

		}

		/**
		 * @return liefert den Parameterwert für GUI
		 */
		@SuppressWarnings("static-method")
		public ContentString getParameter() {
			return new ContentString((String[]) null);
		}

		/**
		 * TODO Comment
		 * 
		 * @param cmd
		 *            -
		 * @param value
		 *            -
		 * @throws InvalidClassException
		 *             -
		 * @throws SocketException
		 *             -
		 * @throws IOException
		 *             -
		 * @modified -
		 */
		@SuppressWarnings({ "static-method" })
		public void call(ICmd cmd, String... value)
				throws InvalidClassException, SocketException, IOException {
			ErrApp.NI_X
					.erraufruf("[public void call(ICmd cmd, String ...value)]");
		}

		/** @return bei <code>true</code> anzeigen, sonst nicht. */
		public boolean getShow() {
			return this.show;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see name.tfossi.apolge.command.ICmd#command()
	 */
	@Override
	public final void command() {
		Command.parameterCheck(this, true);
	}

	/** Vorbereitung und Aufruf der Hilfefunktion */
	@Override
	public final void help() {
		this.help(new String[] { "Aufruf", TAB + "game", "Optionen",
				TAB + TAB + "h, help                    Hilfe" });
	}

	// ---- SWT
	// -----------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * name.tfossi.apolge201.common.cmd.ACmd#buildSWTWidget(org.eclipse.swt.widgets
	 * .Group)
	 */
	@Override
	public final List<Widget> buildSWTWidget(Group grp) {
		// TODO Auto-generated method stub
		return null;
	}

	// ---- Selbstverwaltung
	// -----------------------------------------------------

	/** serialVersionUID */
	private final static long serialVersionUID = VERSION;
	/** logger */
	private final static Logger logger = Logger.getLogger(Gamelist.class
			.getPackage().getName());

	/**
	 * TODO Comment
	 * 
	 * @param name
	 *            -
	 * @param unvisibleMode
	 *            -
	 * @modified -
	 */
	public Gamelist(final String name, boolean unvisibleMode) {
		super(name, unvisibleMode);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see name.tfossi.apolge.command.ICmd#getCode()
	 */
	@Override
	public String getCode() {
		return "Gamelist";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * name.tfossi.xapolge201rc.common.cmd.ICmd#testParameter(java.lang.String)
	 */
	@Override
	public String testParameter(String parameter) {
		try {
			// Testen, ob Parameter bekannt ist
			c.valueOf(parameter);
		} catch (IllegalArgumentException e) {
			// Testen, ob es ein Sonderzeichen '?' im Button etc ist
			for (c cp : c.values()) {
				if (cp.buttontext.equals(parameter)) {
					return cp.name();
				}
			}
			return null;
		}
		return parameter;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * name.tfossi.xapolge201rc.common.cmd.ICmd#maxParameter(java.lang.String)
	 */
	@Override
	public int maxParameter(String parameter) {
		return c.valueOf(parameter).parameterMax;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * name.tfossi.xapolge201rc.common.cmd.ICmd#minParameter(java.lang.String)
	 */
	@Override
	public int minParameter(String parameter) {
		return c.valueOf(parameter).parameterMin;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see name.tfossi.xapolge201rc.common.cmd.ICmd#call(java.lang.String,
	 * java.util.Queue)
	 */
	@Override
	public void call(String parameter, String... value) {
		if (LOGGER)
			logger.info("Aufruf");
		try {
			c.valueOf(parameter).call(this, value);
		} catch (InvalidClassException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
