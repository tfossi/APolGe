/** 
 * TransO.java
 * Branch net
 * APolGe
 * tfossi-team
 * licence GPLv3 
 */
package tfossi.apolge.common.net;

import java.io.Serializable;
import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;

/**
 * Ist das Datenobjekt im Datenverkehr
 * 
 * @author tfossi
 * @version 12.08.2014
 * @modified -
 * @since Java 1.6
 */
public class TransO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = VERSION;
	/** ID ist Identifier der Session */
	private int id = -1;
	/**
	 * TODO Comment
	 * @return -
	 * @modified - 
	 */
	int getID(){
		return this.id;
	}
	/** Command + Parameter */
	private String cmd = null;
	/**
	 * TODO Comment
	 * @return -
	 * @modified - 
	 */
	String getCmd() {
		return this.cmd;
	}

	/** Daten */
	private Object o = null;
	/**
	 * TODO Comment
	 * @return -
	 * @modified - 
	 */
	Object getData() {
		return this.o;
	}

	
	/**
	 * TODO Comment
	 * @modified -
	 */
	public TransO() {

	}

	/**
	 * Vollständige Object ohne Binärdaten
	 * 
	 * @param command
	 *            Command und Parameterzeile
	 */
	public TransO(String command) {
		this.id = command.hashCode();
		this.cmd = command;
	}

	/**
	 * Vollständige Object
	 * 
	 * @param cmdpara
	 *            Command und Parameterzeile
	 * @param data
	 *            Binärdaten
	 */
	public TransO(String cmdpara, Object data) {
		this.id = cmdpara.hashCode();
		this.cmd = cmdpara;
		this.o = data;
	}
	/**
	 * Vollständige Object
	 * @param id -
	 * 
	 * @param cmdpara
	 *            Command und Parameterzeile
	 * @param data
	 *            Binärdaten
	 */
	public TransO(int id, String cmdpara, Object data) {
		this.id = id;
		this.cmd = cmdpara;
		this.o = data;
	}
}