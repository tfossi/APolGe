/**
 * IGameState.java
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
public interface IGameState {
	/**
	 * TODO Comment
	 * @param g -
	 * @param data -
	 * @param value -
	 * @modified - 
	 */
	public void chgGameState(IGameStateCall g, Object data, String ... value);
}
