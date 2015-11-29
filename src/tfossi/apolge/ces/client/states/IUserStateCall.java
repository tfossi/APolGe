/** 
 * IUserStateCall.java
 * Branch ces
 * APolGe
 * tfossi-team
 * licence GPLv3 
 */
package tfossi.apolge.ces.client.states;

import tfossi.apolge.common.hci.menu.IReceiver;
import tfossi.apolge.common.net.INetReceiver;



/**
 * TODO Comment
 *
 * @author tfossi
 * @version 14.08.2014
 * @modified -
 * @since Java 1.6
 */
public interface IUserStateCall extends INetReceiver{

	/**
	 * @param us the us to set
	 */
	public void setUs(UserState us);
	/**
	 * @param us the us to set
	 * @param errorcounter
	 * 			<code>true</code> wenn Statefehler vorliegt
	 */
	public void setUs(UserState us, int errorcounter);

	/**
	 * Aufruf des UserStates 
	 * @param daten
	 * 			Binärdaten
	 * @param value
	 * 			Command- und Parameterstring
	 */
	public void aufrufUs(Object daten, String ...value);
	
	/**
	 * TODO Comment
	 * @param cmd -
	 * @param data -
	 * @modified - 
	 */
	public void send(String cmd, Object data);
	
	/**
	 * TODO Comment
	 * @return -
	 * @modified - 
	 */
	public Class<? extends IReceiver> getCaller();




//	public void connected(Object daten, String ... value);
}