/** 
 * IGameState.java
 * Branch ces
 * APolGe
 * tfossi-team
 * licence GPLv3 
 */
package tfossi.apolge.ces.client.states;

 
/**
 * TODO Comment
 *
 * @author tfossi
 * @version 14.08.2014
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
