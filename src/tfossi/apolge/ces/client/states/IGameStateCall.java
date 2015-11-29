/** 
 * IGameStateCall.java
 * Branch cmd
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
public interface IGameStateCall extends INetReceiver{
	/**
	 * TODO Comment
	 * @param gs - 
	 * @modified - 
	 */
	public void setGs(GameState gs);
	/**
	 * TODO Comment
	 * @param gs - 
	 * @param errorcounter - 
	 * @modified - 
	 */
	public void setGs(GameState gs, int errorcounter);
	
	/**
	 * TODO Comment
	 * @param daten - 
	 * @param value - 
	 * @modified - 
	 */
	public void aufrufGs(Object daten, String ... value);
	
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
	
}
