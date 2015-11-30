/** 
 * UserSession.java
 * Branch net
 * APolGe
 * tfossi-team
 * licence GPLv3 
 */
package tfossi.apolge.common.net;

import static tfossi.apolge.common.constants.ConstValue.LFCR;
import static tfossi.apolge.common.constants.ConstValue.LOGGER;
import static tfossi.apolge.common.constants.ConstValue.LOGTAB;
import static tfossi.apolge.common.constants.ConstValue.TAB;
import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;

import java.io.EOFException;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.OptionalDataException;
import java.io.StreamCorruptedException;
import java.net.Socket;
import java.net.SocketException;
import java.util.Arrays;

import org.apache.log4j.Logger;

import tfossi.apolge.ces.client.states.GameState;
import tfossi.apolge.ces.client.states.IGameStateCall;
import tfossi.apolge.ces.client.states.IUserStateCall;
import tfossi.apolge.ces.client.states.UserState;
import tfossi.apolge.ces.server.hci.ServerMenu;
import tfossi.apolge.common.cmd.CommandArray;
import tfossi.apolge.common.cmd.CommandList;
import tfossi.apolge.common.cmd.ICmd;
import tfossi.apolge.common.cmd.cmds.Gamelist;
import tfossi.apolge.common.error.UnknownOrderException;
import tfossi.apolge.common.hci.AMenu;
import tfossi.apolge.common.hci.menu.IReceiver;
import tfossi.apolge.io.console.Key;

/**
 * UserSession wird vom Server für jeden Client eingerichtet und steuert den
 * NetVerkehr
 * 
 * @author tfossi
 * @version 12.08.2014
 * @modified -
 * @since Java 1.6
 */
public class UserSession extends Transmission implements Runnable,
		IUserStateCall, IGameStateCall 
{

	/** us */
	private UserState us = null;
	/** gs */
	private GameState gs = null;

	/** commands */
	private final CommandList commands = new CommandArray();
	/** menu */
	private final INetReceiver menu;

	/* (non-Javadoc)
	 * @see tfossi.apolge.ces.client.states.IUserStateCall#getCaller()
	 */
	@Override
	public final Class<? extends IReceiver> getCaller() {
		return this.menu.getClass();
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		if (this.menu instanceof ServerMenu) {
			this.us = UserState.INIT;
			this.us.chgUserState(this, null, (String[]) null);
			this.gs = GameState.WAITING;
		} else {
			this.us = UserState.CONNECTED;
			this.gs = GameState.REQUEST;
		}

		while (this.isConnected()) {
			if(LOGGER) logger.info("Warte auf Netzcommand. UserState: " + this.us.name()
					+ LOGTAB + "..." + LFCR);
			TransO to = null;
			try {
				to = this.auskunft();
			} catch (ClassNotFoundException e) {
				assert false; //	ErrApp.NETERROR.erraufruf("Class of a serialized object cannot be found." + LFCR
//								+ e.getMessage());
			} catch (InvalidClassException e) {
				assert false; //	ErrApp.NETERROR.erraufruf("Something is wrong with a class used by serialization."
//								+ LFCR + e.getMessage());
			} catch (StreamCorruptedException e) {
				assert false; //		ErrApp.NETERROR.erraufruf("Control information in the stream is inconsistent."
//								+ LFCR + e.getMessage());

			} catch (OptionalDataException e) {
				assert false; //		ErrApp.NETERROR.erraufruf(	"Primitive data was found in the stream instead of objects."
//								+ LFCR + e.getMessage());
			} catch (EOFException e) {
				assert false; //		ErrApp.NETERROR.erraufruf("If end of file is reached."
//						+ LFCR + e.getMessage());
				try {
					this.closeConnections();
				} catch (IOException e1) {
					assert false; //			ErrApp.NETERROR.erraufruf("IOException - If other I/O error has occurred during closing Connection."
//									+ LFCR + e1.getMessage());
				}
			} catch (IOException e) {
				assert false; //		ErrApp.NETERROR.erraufruf(
//						"IOException - If other I/O error has occurred." + LFCR
//								+ e.getMessage());
				try {
					this.closeConnections();
				} catch (IOException e1) {
					assert false; //				ErrApp.NETERROR.erraufruf(
//							"IOException - If other I/O error has occurred during closing Connection."
//									+ LFCR + e1.getMessage());
				}
			}
			if (to == null)
				return;

			ICmd cmd;
			try {
				if ((cmd = Key.netEingabe(this.commands, to.getCmd(), to.getData())) != null) {
					if(LOGGER) logger.trace("Command: "+cmd.getCode()+LOGTAB+Arrays.toString((Object[])cmd.getData())+
							LOGTAB+cmd.getParameter()+LOGTAB+"INPUT:"+LOGTAB+
							to.getCmd()+LOGTAB+Arrays.toString((Object[])to.getData()));
					cmd.command();
				}
			} catch (UnknownOrderException e) {
				assert false; //		ErrApp.WRONGCOMMANDKEY.erraufruf(e.getMessage());
			}
		}
		try {
			this.closeConnections();
		} catch (IOException e) {
			assert false; //		ErrApp.NETERROR.erraufruf(
//					"IOException - If other I/O error has occurred during closing Connection."
//							+ LFCR + e.getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see tfossi.apolge.ces.client.states.IUserStateCall#aufrufUs(java.lang.Object, java.lang.String[])
	 */
	@Override
	public void aufrufUs(Object daten, String... value) {
		this.us.chgUserState(this, daten, value);
	}

	/* (non-Javadoc)
	 * @see tfossi.apolge.common.net.INetReceiver#passportUs(java.lang.Object, java.lang.String[])
	 */
	@Override
	public Object[] passportUs(Object daten, String[] value) {
		return this.menu.passportUs(daten, value);
	}

	/* (non-Javadoc)
	 * @see tfossi.apolge.common.net.INetReceiver#verifyPassportUs(java.lang.Object, java.lang.String[])
	 */
	@Override
	public Object[] verifyPassportUs(Object daten, String[] value) {
		return this.menu.verifyPassportUs(daten, value);
	}

	/* (non-Javadoc)
	 * @see tfossi.apolge.common.net.INetReceiver#verifyAnswerGs(java.lang.Object, java.lang.String[])
	 */
	@Override
	public Object[] verifyAnswerGs(final Object daten, final String[] value) {
		return this.menu.verifyAnswerGs(daten, value);
	}

	/* (non-Javadoc)
	 * @see tfossi.apolge.common.net.INetReceiver#openServer(java.lang.Object, java.lang.String[])
	 */
	@Override
	public void openServer(final Object daten, final String[] value){
		this.menu.openServer(daten, value);
	}

	/* (non-Javadoc)
	 * @see tfossi.apolge.common.net.INetReceiver#sendGamelist(tfossi.apolge.common.net.UserSession, tfossi.apolge.common.cmd.cmds.Gamelist.c, java.lang.Object, java.lang.String[])
	 */
	@Override
	public Object[] sendGamelist(@SuppressWarnings({ "hiding" }) UserSession us, Gamelist.c cmd, Object data, String... value){
		return this.menu.sendGamelist(this, cmd, data, value);
	}

	/* (non-Javadoc)
	 * @see tfossi.apolge.ces.client.states.IUserStateCall#setUs(tfossi.apolge.ces.client.states.UserState)
	 */
	@Override
	public final void setUs(UserState u) {
		if(LOGGER) logger.info("from " + this.us + " to " + u);
		this.us = u;
	}

	/* (non-Javadoc)
	 * @see tfossi.apolge.ces.client.states.IUserStateCall#setUs(tfossi.apolge.ces.client.states.UserState, int)
	 */
	@Override
	public final void setUs(UserState u, int errorcounter) {
		if(LOGGER) logger.info("Fehler in der Netzverbindung: " + errorcounter);
		if (errorcounter > 3) {
			if(LOGGER) logger.fatal("Fehler in der Netzverbindung: " + errorcounter);
			try {
				this.closeConnections();
			} catch (IOException e1) {
				assert false; //ErrApp.NETERROR.erraufruf(
//						"IOException - If other I/O error has occurred during closing Connection."
//								+ LFCR + e1.getMessage());
			}
		}
		if(LOGGER) logger.info("from " + this.us + " to " + u);
		this.us = u;
	}

	/* (non-Javadoc)
	 * @see tfossi.apolge.common.net.INetReceiver#loadGs(tfossi.apolge.common.net.UserSession, java.lang.Object, java.lang.String[])
	 */
	@Override
	public Object[] loadGs(@SuppressWarnings({ "hiding" }) UserSession us, Object daten, String[] value){
		return this.menu.loadGs(this, daten, value);
	}

	/* (non-Javadoc)
	 * @see tfossi.apolge.ces.client.states.IGameStateCall#aufrufGs(java.lang.Object, java.lang.String[])
	 */
	@Override
	public void aufrufGs(Object daten, String... value) {
		this.gs.chgGameState(this, daten, value);
	}

	/* (non-Javadoc)
	 * @see tfossi.apolge.ces.client.states.IGameStateCall#setGs(tfossi.apolge.ces.client.states.GameState)
	 */
	@Override
	public void setGs(GameState g) {
		this.gs = g;
	}

	/* (non-Javadoc)
	 * @see tfossi.apolge.ces.client.states.IGameStateCall#setGs(tfossi.apolge.ces.client.states.GameState, int)
	 */
	@Override
	public final void setGs(final GameState g, final int errorcounter) {
		if(LOGGER) logger.info("Fehler im Gamenetz: " + errorcounter);
		if (errorcounter > 3) {
			if(LOGGER) logger.fatal("Fehler im Gamenetz: " + errorcounter);
			try {
				this.closeConnections();
			} catch (IOException e1) {
				assert false; ////				ErrApp.NETERROR.erraufruf(
//						"IOException - If other I/O error has occurred during closing Connection."
//								+ LFCR + e1.getMessage());
			}
		}
		if(LOGGER) logger.info("from " + this.gs + " to " + g);
		this.gs = g;
	}

	/* (non-Javadoc)
	 * @see tfossi.apolge.ces.client.states.IUserStateCall#send(java.lang.String, java.lang.Object)
	 */
	@Override
	public void send(String cmd, Object data) {
		if(LOGGER) logger.info("SEND " + cmd+TAB+Arrays.toString((Object[])data));
		try {
			this.statement(cmd, data);
		} catch (InvalidClassException e1) {
			assert false; //		ErrApp.NETEXCEPTION.erraufruf(e1.getMessage());
			return;
		} catch (SocketException e1) {
			assert false; //		ErrApp.NETEXCEPTION.erraufruf(e1.getMessage());
			return;
		} catch (IOException e1) {
			assert false; //		ErrApp.NETEXCEPTION.erraufruf(e1.getMessage());
			return;
		}
	}

	// ---- Selbstverwaltung --------------------------------------------------
	/** serialVersionUID */
	@SuppressWarnings("unused")
	private final static long serialVersionUID = VERSION;
	/** logger */
	private final static Logger logger = Logger.getLogger(UserSession.class
			.getPackage().getName());
//
	/**
	 * TODO Comment
	 * @param m -
	 * @param clientsocket -
	 * @throws IOException -
	 * @modified -
	 */
	public UserSession(final INetReceiver m, final Socket clientsocket)
			throws IOException {
		super(clientsocket);
		this.menu = m;
		// this.props = ((AApplication)
		// servermenu.getStateContext()).getProperties();

		this.commands.addAll(((AMenu) this.menu).getApplCmdList().clone());
		this.commands.addAll(((AMenu) this.menu).getStateCmdList().clone());

		for (ICmd cmd : this.commands)
			cmd.setReceiver(this, new String[0]);

	}
}
