/**
 * IGameStateCall.java
 * Branch hci
 * APolGe
 * tfossi-team
 * licence GPLv3  
 */
package tfossi.apolge.common.hci;


/**
 * TODO Comment
 *
 * @author tfossi
 * @version 13.08.2014
 * @modified -
 * @since Java 1.6
 */
public interface IGameStateCall {
	/**
	 * TODO Comment
	 * @param gs -
	 * @modified - 
	 */
	public void setGs(GameState gs);
	
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
}
