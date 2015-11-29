/** 
 * INetReceiver.java
 * Branch net
 * APolGe
 * tfossi-team
 * licence GPLv3 
 */
package tfossi.apolge.common.net;

import tfossi.apolge.common.cmd.ACmd;
import tfossi.apolge.common.cmd.cmds.Gamelist;
import tfossi.apolge.common.hci.menu.IReceiver;




/**
 * Schnittstelle zum den speziellen Netzbefehlen<br>
 * Enthält die Methoden, die der Receiver für den Netzverkehr beherrschen muss.<br>
 * 
 * @.pattern Command: receiver
 * @see ACmd
 * @author tfossi
 * @version 12.08.2014
 * @modified -
 * @since Java 1.6
 */
public interface INetReceiver extends IReceiver{

//	/**
//	 * Sendet Pssport
//	 * @param daten
//	 * 			Binärdaten mit Serverkey
//	 * @param value
//	 * 			-
//	 * @return
//	 * 			Error: 	ErrApp[]
//	 * 			OK:		String[]
//	 */
	/**
	 * TODO Comment
	 * @param daten -
	 * @param value -
	 * @return -
	 * @modified - 
	 */
	public Object[] passportUs(Object daten, String[] value);
//	
	/**
	 * TODO Comment
	  * @param daten -
	 * @param value -
	 * @return -
	 * @modified - 
	 */
	public Object[] verifyPassportUs(Object daten, String[] value);
//	
	/**
	 * TODO Comment
	 * @param daten -
	 * @param value -
	 * @modified - 
	 */
	public void openServer(Object daten, String[] value);
//
	/**
	 * TODO Comment
	 * @param us -
	 * @param cmd -
	 * @param data -
	 * @param value -
	 * @return -
	 * @modified - 
	 */
	public Object[] sendGamelist(UserSession us, Gamelist.c cmd, Object data, String... value);
//		
	/**
	 * TODO Comment
	 * @param us -
	 * @param daten -
	 * @param value -
	 * @return -
	 * @modified - 
	 */
	public Object[] loadGs(UserSession us, Object daten, String[] value);
//	
	/**
	 * TODO Comment
	 * @param daten -
	 * @param value -
	 * @return -
	 * @modified - 
	 */
	public Object[] verifyAnswerGs(Object daten, String[] value);
////			throws InvalidClassException, SocketException, IOException;
}
