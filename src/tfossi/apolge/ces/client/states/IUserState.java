/** 
 * IUserState.java
 * Branch ces
 * APolGe
 * tfossi-team
 * licence GPLv3 
 */
package tfossi.apolge.ces.client.states;

/**
 * Schnittstelle zu den UserStates
 *
 * @author tfossi
 * @version 14.08.2014
 * @modified -
 * @since Java 1.6
 */
public interface IUserState {
	/**
	 * Die spezifische UserStatemethode aufrufen und ggfs. den State ändern.
	 * @param u
	 * 			der Aufrufer
	 * @param data
	 * 			Binärdaten
	 * @param value
	 * 			Command- und Parameterstring
	 */
	public void chgUserState(IUserStateCall u, Object data, String ... value);
}
