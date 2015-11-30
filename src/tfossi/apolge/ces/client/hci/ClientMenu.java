/** 
 * ClientMenu.java
 * Branch ces
 * APolGe
 * tfossi-team
 * licence GPLv3 
 */
package tfossi.apolge.ces.client.hci;

import static tfossi.apolge.common.constants.ConstValue.LOGGER;
import static tfossi.apolge.common.constants.ConstValue.LOGTAB;
import static tfossi.apolge.common.constants.ConstValue.NTAB;
import static tfossi.apolge.common.constants.ConstValueExtension.GRAYEDCLIENTMENU;
import static tfossi.apolge.common.constants.ConstValueExtension.HEADTEXT;
import static tfossi.apolge.common.constants.ConstValueExtension.PORT_NETWORK;
import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Properties;

import org.apache.log4j.Logger;

import tfossi.apolge.ces.AApplication;
import tfossi.apolge.ces.client.states.GameState;
import tfossi.apolge.ces.client.states.IGameStateCall;
import tfossi.apolge.ces.client.states.IUserStateCall;
import tfossi.apolge.ces.client.states.UserState;
import tfossi.apolge.common.cmd.ICmd;
import tfossi.apolge.common.cmd.cmds.Gamelist;
import tfossi.apolge.common.hci.AMenu;
import tfossi.apolge.common.hci.AModel;
import tfossi.apolge.common.hci.IStateContext;
import tfossi.apolge.common.macrorecorder.IRecorder;
import tfossi.apolge.common.net.UserSession;
import tfossi.apolge.io.ContentString;
import tfossi.apolge.io.Screen;

/**
 * TODO Comment
 *
 * @author tfossi
 * @version 14.08.2014
 * @modified -
 * @since Java 1.6
 */
public class ClientMenu extends AMenu implements IUserStateCall, IGameStateCall {

	/** usersession */
	private Runnable usersession;

	
	/* (non-Javadoc)
	 * @see tfossi.apolge.ces.client.states.IUserStateCall#getCaller()
	 */
	@Override
	public final Class<? extends ClientMenu> getCaller() {
		return this.getClass();
	}


	/* (non-Javadoc)
	 * @see tfossi.apolge.ces.client.states.IUserStateCall#setUs(tfossi.apolge.ces.client.states.UserState)
	 */
	@Override
	public void setUs(UserState us) {
		((IUserStateCall) this.usersession).setUs(us);
	}


	/* (non-Javadoc)
	 * @see tfossi.apolge.ces.client.states.IUserStateCall#setUs(tfossi.apolge.ces.client.states.UserState, int)
	 */
	@Override
	public final void setUs(UserState us, int errorcounter) {
		assert false;
		if (errorcounter > 3)
			System.exit(0);
		((IUserStateCall) this.usersession).setUs(us, errorcounter);
	}


	/* (non-Javadoc)
	 * @see tfossi.apolge.ces.client.states.IUserStateCall#aufrufUs(java.lang.Object, java.lang.String[])
	 */
	@Override
	public void aufrufUs(Object daten, String... value) {
		if (value != null)
			if(LOGGER) if(LOGGER) logger.trace("Route DATEN: " + daten + " VALUE: "
					+ Arrays.asList(value));
		else
			if(LOGGER) if(LOGGER) logger.trace("Route DATEN: " + daten + " VALUE: null");
		((IUserStateCall) this.usersession).aufrufUs(daten, value);
	}


	/* (non-Javadoc)
	 * @see tfossi.apolge.common.net.INetReceiver#passportUs(java.lang.Object, java.lang.String[])
	 */
	@Override
	public Object[] passportUs(Object daten, String[] value) {
		Object[] o = (Object[]) daten;
		if(LOGGER) logger.info("IO:NET" + LOGTAB + "Daten: "
				+ (daten == null ? null : Arrays.asList(o)) + LOGTAB
				+ "Value: " + (value == null ? null : Arrays.asList(value)));
		// TODO if Key corrupted return new ErrApp[]{ErrApp.NETERROR};
		return new String[] { "ID", "PW" };
	}

	/* (non-Javadoc)
	 * @see tfossi.apolge.ces.client.states.IGameStateCall#setGs(tfossi.apolge.ces.client.states.GameState)
	 */
	@Override
	public void setGs(GameState gs) {
		((IGameStateCall) this.usersession).setGs(gs);		
	}

	/* (non-Javadoc)
	 * @see tfossi.apolge.ces.client.states.IGameStateCall#setGs(tfossi.apolge.ces.client.states.GameState, int)
	 */
	@Override
	public void setGs(GameState gs, int errorcounter) {
		((IGameStateCall) this.usersession).setGs(gs, errorcounter);		
	}

	/* (non-Javadoc)
	 * @see tfossi.apolge.ces.client.states.IGameStateCall#aufrufGs(java.lang.Object, java.lang.String[])
	 */
	@Override
	public void aufrufGs(Object daten, String... value) {
		((IGameStateCall) this.usersession).aufrufGs(daten, value);		
	}
	
	/* (non-Javadoc)
	 * @see tfossi.apolge.common.net.INetReceiver#verifyAnswerGs(java.lang.Object, java.lang.String[])
	 */
	@Override
	public Object[] verifyAnswerGs(final Object daten, final String[] value) {
//		if (daten instanceof ErrApp[]) {
//			return (ErrApp[]) daten;
//		}
		Object[] o = (Object[]) daten;
		if(LOGGER) logger.info("IO:NET" + LOGTAB + "Daten: "
				+ (daten == null ? null : Arrays.asList(o)) + LOGTAB
				+ "Value: " + (value == null ? null : Arrays.asList(value)));
		return new Object[0];
	}


	/* (non-Javadoc)
	 * @see tfossi.apolge.common.net.INetReceiver#verifyPassportUs(java.lang.Object, java.lang.String[])
	 */
	@Override
	public Object[] verifyPassportUs(Object daten, String[] value) {
//		if (daten instanceof ErrApp[]) {
//			return (ErrApp[]) daten;
//		}
		Object[] o = (Object[]) daten;
		if(LOGGER) logger.info("IO:NET" + LOGTAB + "Daten: "
				+ (daten == null ? null : Arrays.asList(o)) + LOGTAB
				+ "Value: " + (value == null ? null : Arrays.asList(value)));
		return new Object[0];
	}

	/* (non-Javadoc)
	 * @see tfossi.apolge.common.net.INetReceiver#openServer(java.lang.Object, java.lang.String[])
	 */
	@Override
	public void openServer(final Object daten, final String[] value) {
		synchronized (this.t) {
			this.t.notify();
		}
	}

	/* (non-Javadoc)
	 * @see tfossi.apolge.common.net.INetReceiver#sendGamelist(tfossi.apolge.common.net.UserSession, tfossi.apolge.common.cmd.cmds.Gamelist.c, java.lang.Object, java.lang.String[])
	 */
	@Override
	public Object[] sendGamelist(final UserSession us, final Gamelist.c cmd, final Object data, final String... value) {
		switch (cmd) {
		case ask4sleepingGames:
			this.send("Gamelist -"+cmd, data);
			break;
		case ans4sleepingGames:
			((ClientModel) this.getModel()).setPassivGames((String [])data);
			if(LOGGER) logger.debug("Liste der Sleeping Games:"+LOGTAB+Arrays.asList(((ClientModel) this.getModel()).getPassivGames()));
			break;			
		default:
			assert false : Arrays.asList(value);
		}

		return null;
	}

	/* (non-Javadoc)
	 * @see tfossi.apolge.common.net.INetReceiver#loadGs(tfossi.apolge.common.net.UserSession, java.lang.Object, java.lang.String[])
	 */
	@Override
	public Object[] loadGs(final UserSession us, final Object daten, final String[] value){
		assert false;
		return null;
	}

	/* (non-Javadoc)
	 * @see tfossi.apolge.ces.client.states.IUserStateCall#send(java.lang.String, java.lang.Object)
	 */
	@Override
	public void send(String cmd, Object data) {
		((IUserStateCall) this.usersession).send(cmd, data);
	}

	//
	// private Socket clientID;
	/** props */
	private final Properties props;

	/* (non-Javadoc)
	 * @see tfossi.apolge.common.hci.AMenu#setInformation(java.lang.String, tfossi.apolge.io.Screen)
	 */
	@Override
	public synchronized final void setInformation(String s, Screen scr) {
		this.setInformation(this.getStateContext().getCntr(), scr,
				new ContentString(s), true, false, false);
	}

	/**
	 * <b>Menüsteuerung</b><br>
	 * State Pattern<br>
	 * <b>Daten- und Viewersteuerung</b><br>
	 * MVC Pattern<br>
	 * 
	 * @param fromState
	 *            <ul>
	 *            <li>!=null: Enthält das aufrufende (==instanzierende) Menu. Es
	 *            ist der Aufrufer, der bei {@link #back()} wieder angesprungen
	 *            wird.</li>
	 *            <li>==null: Ist das initiale Menu und das Programm wird beim
	 *            Rücksprung {@link #back()} beendet.</li>
	 * @param statecontext
	 *            Enthält den {@link IStateContext StateContext}, der die
	 *            Statesteuerung übernimmt. Siehe auch
	 *            {@link #getStateContext()}
	 * @.post Menu,Model und Viewer vom EditorMenu sind instanziert
	 */
	public ClientMenu(AMenu fromState, IStateContext statecontext) {
		super(fromState, statecontext, GRAYEDCLIENTMENU);

		if(LOGGER) logger.debug("Beziehe das Datenmodell des Clients");
		this.setModel(ClientModel.getInstance());
		if(LOGGER) logger.debug("Baue Viewer des Clients");

		this.props = ((AApplication) statecontext).getProperties();
		// // Instanziert den View des MVC mit sich selber als Observable
		// this.setViewer(new ClientViewRoot(this, statecontext));
	}

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
		if(LOGGER) logger.debug("STATE::STATECONTEXT\tKontrolle an ["
				+ this.getClass().getSimpleName() + "] übergeben.");
		this.initiate(ClientModel.getInstance(), new ClientView(this
				.getStateContext().getCntr()));

		try {

			@SuppressWarnings("resource")
			Socket s = new Socket((String) this.props.get("server"), PORT_NETWORK);

			this.usersession = new UserSession(this, s);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		} catch (ConnectException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}

		((AModel) this.getModel()).addObserver(this);
		// Die Daten sind individuell zu den Menus und daher müssen sich die
		// Menüs jedesmal neu an den Screen angemeldet werden.
		// Cntr cntr = this.getStateContext().getCntr();

		// cntr.initMenuWidgets(this);

		this.getView().initiate(this, this.getStateContext());

		// Bei den Befehlen den Receiver eintragen
		for (ICmd ac : this.getModel().getApplCmdList()) {
			ac.setReceiver(this, this.GRAYEDMENU);

		}
		// Bei den Befehlen den Receiver eintragen
		for (ICmd ac : this.getModel().getStateCmdList()) {
			ac.setReceiver(this, this.GRAYEDMENU);
		}

		this.setInformation(this.getStateContext().getCntr(), Screen.C,
				new ContentString(
						new String[] { " ", "APolGe-Client",
								"V" + HEADTEXT + " ", " ", " ", " ",
								"(c) Thomas tfossi" }), false, false, false);

		this.setInformation(this.getStateContext().getCntr(), Screen.M,
				new ContentString("Initiiere Client...."), false, false, false);

		this.setInformation(this.getStateContext().getCntr(), Screen.MI,
				new ContentString("Es liegen keine MI Nachrichten vor"), false,
				false, false);

		this.setInformation(this.getStateContext().getCntr(), Screen.VI,
				new ContentString("Es liegen keine VI Nachrichten vor"), false,
				false, false);
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

		/* @modified tfossi, 14.08.2014, Interface Macrorecorder */
		IRecorder.recorder.loadRecord("clientstart");
		/* @modified tfossi, 14.08.2014, Interface Macrorecorder */
		IRecorder.recorder.setPlayON();

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

	// private Object getCentralInformation(ClientView av) {
	//
	// return av.outputCentralInformation();
	// }
	// /*
	// * (non-Javadoc)
	// *
	// * @see
	// name.tfossi.apolge.hci.IMenu#getCentralInformation(java.lang.Object)
	// */
	// public Object[] getCentralInformation(Object view) {
	// if (view instanceof EditorViewName)
	// return this.getCentralInformation((EditorViewName) view);
	// if (view instanceof EditorViewGame)
	// return this.getCentralInformation((EditorViewGame) view);
	// if (view instanceof EditorViewNation)
	// return this.getCentralInformation((EditorViewNation) view);
	// if (view instanceof EditorViewClan)
	// return this.getCentralInformation((EditorViewClan) view);
	// if (view instanceof EditorViewClanPeList)
	// return this.getCentralInformation((EditorViewClanPeList) view);
	// if (view instanceof EditorViewPerson)
	// return this.getCentralInformation((EditorViewPerson) view);
	// if (view instanceof EditorViewRole)
	// return this.getCentralInformation((EditorViewRole) view);
	//
	// return (Object [])this.getCentralInformation((ClientView) view);
	// }

	// ---- Command Pattern
	// ------------------------------------------------------


	/* (non-Javadoc)
	 * @see tfossi.apolge.common.hci.menu.AMenuReceiver#first()
	 */
	@Override
	public final void first() {
		 this.nextView = new ClientView(this.getStateContext().getCntr());
		 if(LOGGER) logger.debug("MVC::\tVerlasse View: ["
		 + this.getView().getClass().getSimpleName() + "] " + NTAB
		 + "\tWechsle zu: [" + this.nextView.getClass().getSimpleName()
		 + "]");
		 this.getView().setExitSWTView();
	}

	/* (non-Javadoc)
	 * @see tfossi.apolge.common.hci.AMenu#root()
	 */
	@Override
	public final void root() {
		this.nextView = new ClientView(this.getStateContext().getCntr());
		if(LOGGER) logger.info("MVC::\tVerlasse View ["
				+ this.getView().getClass().getSimpleName() + "]!");
		this.getView().setExitSWTView();

	}

	// private CallClient cc;

	/** t */
	private Thread t;
	/** inThread */
	public ObjectInputStream inThread;

	/**
	 * TODO Comment
	 * @modified - 
	 */
	public void autoRunClient() {
		this.setInformation("Starte Anruf", Screen.M);

		this.t = new Thread(this.usersession);
		this.t.start();
		synchronized (this.t) {
			try {
				this.t.wait();
				// Habe gewartet, kann jetzt loslegen.
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}

		// try {
		// this.cc.send("List");
		// Object o = this.cc.receive();
		// // Object o = this.cc.receive();
		// } catch (StreamCorruptedException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (ClassNotFoundException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		//
		// if (this.cc.isAlive())
		// this.setInformation("Es geht los", Screen.M);
	}

	// public void sendAccept() {
	// synchronized (this.t) {
	// // Habe etwas gemacht und informiere jetzt meinen Wartenden.
	// this.t.notify();
	// }
	// }

	// public void sendPassword(int tid) {
	// TransO to = new TransO("My Password " + this.hashCode());
	// to.id = tid;
	// this.cc.statement(to);
	// try {
	// ((CallClient) this.t).send();
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	// @Override
	// public void sendGamelist(Gamelist.c c, Object data, String... value) {
	// switch (c) {
	// case ans4activeGames:
	// assert false : c + "\n" + data + "\n" + Arrays.asList(value);
	// break;
	// case ans4sleepingGames:
	// System.out.println(Arrays.asList((String[]) data));
	// break;
	// default:
	// assert false : c;
	// }
	// }

	// @Override
	// public void chgUserState(Object data, String... value)
	// throws InvalidClassException, SocketException, IOException {
	// this.us.chgUserState(this, data, value);
	// }

	// public final void setUs(UserState us) {
	// this.us = us;
	// }

	// public void aufruf(Object daten, String ...value){
	// this.us.chgUserState(this, daten, value);
	//
	// UserState.RUN.wasbesonderes();
	// }
	// public void aufrufGs(Object daten, String ...value){
	// this.gs.chgGameState(this, daten, value);
	// }
	// /* (non-Javadoc)
	// * @see name.tfossi.apolge204rc.IUserStateCall#send(java.lang.String,
	// java.lang.Object)
	// */
	// public void send(String cmd, Object data) {
	// // TODO Auto-generated method stub
	//
	// }
	// ---- Recorder
	// ------------------------------------------------------------
	//
	// /*
	// * (non-Javadoc)
	// *
	// * @see name.tfossi.apolge.hci.AMenu#playRecorder(java.lang.String)
	// */
	//
	// public final void playRecorder(final String macroname) {
	// if(LOGGER) logger.trace("Enter ");
	// super.playRecorder(macroname, this.getClass());
	// if(LOGGER) logger.trace("Exit ");
	// }

	// /*
	// * (non-Javadoc)
	// *
	// * @see name.tfossi.apolge.hci.AbstractMenu#record()
	// */
	//
	// public final void recRecorder(final String macroname, final String
	// comment) {
	// if(LOGGER) logger.trace("Enter ");
	// super.recRecorder(macroname, comment, this.getClass());
	// if(LOGGER) logger.trace("Exit ");
	// }

	// @Override
	// public void setState(final ClientState s) {
	// this.state = s;
	// }

	// public void readZero() {
	// // Delegate...
	// state.readZero(this);
	// }
	//
	// public void readOne() {
	// // Delegate...
	// state.readOne(this);
	// }
	//
	// public boolean isInFinalState() {
	// // Delegate...
	// return state.isFinal();
	// }

	// @Override
	// public void statement(final String command, final Object data) throws
	// InvalidClassException, SocketException, IOException {
	// this.cc.statement(command, data);
	// }

	// ---- Selbstverwaltung
	// -----------------------------------------------------
	/** serialVersionUID */
	@SuppressWarnings("unused")
	private final static long serialVersionUID = VERSION;

	/** logger */
	private final static Logger logger = Logger.getLogger(ClientMenu.class
			.getPackage().getName());

}
