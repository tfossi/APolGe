/** 
 * DataUser.java
 * Branch ces
 * APolGe
 * tfossi-team
 * licence GPLv3 
 */
package tfossi.apolge.ces.server.hci;
import static tfossi.apolge.common.constants.ConstValue.LOGGER;
import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;

import org.apache.log4j.Logger;

/**
 * TODO Comment
 *
 * @author tfossi
 * @version 17.08.2014
 * @modified -
 * @since Java 1.6
 */
public class DataUser {
	{
		if(LOGGER) System.out.println("_TestBase V"+serialVersionUID);
	}
	/**
	 * Universal Identifier. Die Identifikationsnummer eines Datensatzes ist
	 * einmalig
	 */
	private long uid = -1L;
	
	/** nickname */
	private String nickname;
	/** password */
	private String password;
//	private String name;
//	private String call; 
	/**
	 * @return the uid
	 */
	public final long getUid() {
		return this.uid;
	}
	/**
	 * @param uid the uid to set
	 */
	public final void setUid(long uid) {
		this.uid = uid;
	}
	/**
	 * @return the nickname
	 */
	public final String getNickname() {
		return this.nickname;
	}
	/**
	 * @param nickname the nickname to set
	 */
	public final void setNickname(String nickname) {
		this.nickname = nickname;
	}
	/**
	 * @return the password
	 */
	public final String getPassword() {
		return this.password;
	}
	/**
	 * @param password the password to set
	 */
	public final void setPassword(String password) {
		this.password = password;
	}
	// ---- Selbstverwaltung --------------------------------------------------

	/** serialVersionUID.java */
	private final static long serialVersionUID = VERSION;
	/** logger */
	@SuppressWarnings("unused")
	private final static Logger logger = Logger.getLogger(DataUser.class
			.getPackage().getName());

}
