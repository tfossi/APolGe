/** 
 * ServerModel.java
 * Branch ces
 * APolGe
 * tfossi-team
 * licence GPLv3 
 */
package tfossi.apolge.ces.server.hci;


import static tfossi.apolge.common.constants.ConstValue.*;
import static tfossi.apolge.common.constants.ConstValueExtension.*;
import java.util.HashMap;
import java.util.Map;


import org.apache.log4j.Logger;

import tfossi.apolge.common.hci.AModel;


/**
 * Enthält die Daten des SessionManagers.
 * 
 * @.pattern Singleton
 * @.pattern MVC: concrete model
 * @see AModel
 * @see ServerMenu
 * @see ServerView
 * TODO Comment
 *
 * @author tfossi
 * @version 13.08.2014
 * @modified -
 * @since Java 1.6
 */
public class ServerModel extends AModel {
	/** users */
	private final Map<Long, DataUser> users = new HashMap<>();

	/**
	 * User hinzufügen
	 * @param user
	 * 			der Anwenderdatensatz
	 * @return der Anwenderdatensatz
	 */
	final DataUser addUser(DataUser user) {
		if (user == null)
			return null;
		if (user.getNickname() == null || user.getPassword() == null
				|| user.getUid() != -1L)
			return null;
		if (testUser(user.getUid()))
			return null;
		if(testUser(user))  return null;
		user.setUid(this.users.size());
		return this.users.put(new Long(user.getUid()), user);

	}

	/**
	 * TODO Comment
	 * @param uid -
	 * @return -
	 * @modified - 
	 */
	final boolean testUser(long uid) {
		return this.users.containsKey(new Long(uid));
	}

	/**
	 * TODO Comment
	 * @param user -
	 * @return -
	 * @modified - 
	 */
	final boolean testUser(DataUser user) {
		for(Long uid : this.users.keySet()){
			DataUser u = this.users.get(uid);
			if(u.getUid()== user.getUid()) return false;
			if(u.getNickname().equalsIgnoreCase(user.getNickname())) return false;			
		}
		return true;
	}

	/**
	 * TODO Comment
	 * @param uid -
	 * @return -
	 * @modified - 
	 */
	final DataUser getUser(long uid) {
		if (!testUser(uid))
			return null;
		return this.users.get(new Long(uid));
	}	

	/** passivGames */
	private String[] passivGames;

	/**
	 * TODO Comment
	 * @param liste -
	 * @modified - 
	 */
	final void setPassivGames(final String[] liste) {
		this.passivGames = liste.clone();
	}

	/**
	 * TODO Comment
	 * @return -
	 * @modified - 
	 */
	final String[] getPassivGames() {
		return this.passivGames.clone();
	}
	/** waitingGames */
	private String[] waitingGames;

	/**
	 * TODO Comment
	 * @param liste -
	 * @modified - 
	 */
	final void setWaitingGames(final String[] liste) {
		this.waitingGames = liste.clone();
	}

	/**
	 * TODO Comment
	 * @return -
	 * @modified - 
	 */
	final String[] getWaitingGames() {
		return this.waitingGames.clone();
	}
	/** activeGames */
	private String[] activeGames;

	/**
	 * TODO Comment
	 * @param liste -
	 * @modified - 
	 */
	final void setActiveGames(final String[] liste) {
		this.passivGames = liste.clone();
	}

	/**
	 * TODO Comment
	 * @return -
	 * @modified - 
	 */
	final String[] getActiveGames() {
		return this.activeGames.clone();
	}

	// ---- Selbstverwaltung --------------------------------------------------
	/** serialVersionUID */
	@SuppressWarnings("unused")
	private final static long serialVersionUID = VERSION;
	/** logger */
	private final static Logger logger = Logger.getLogger(ServerModel.class
			.getPackage().getName());
	/** itsInstance */
	private static ServerModel itsInstance = new ServerModel();

	/** Instanziert das Model */
	private ServerModel() {
		super("Server ");
		if(LOGGER) logger.debug("Habe Model eingerichtet.");
	}

	/** @return Instanz */
	public static final synchronized ServerModel getInstance() {
		return itsInstance;
	}
}
