/**
 * IState.java
 * Branch hci
 * APolGe
 * tfossi-team
 * licence GPLv3  
 */
package tfossi.apolge.common.hci;


/**
 * Schnittstelle zu den Menus (States). Die Menüs (States) können damit sich gegenseitig
 * aufrufen und die Steuerung abgeben
 * 
 * @.pattern State: state
 * @author tfossi
 * @version 12.08.2014
 * @modified -
 * @since Java 1.6
 */
public interface IState {
	/**
	 * Methode die von {@linkplain IStateContext} aufgerufen wird, um auf eine
	 * Zustandsklasse umzuschalten. Diese Methode muss von allen konkreten Menus
	 * implementiert werden, damit auch individuelle Eigenschaften genutzt werden können.
	 * 
	 * @.post Aufruf der Methode {@link AMenu#actionState()} durch das konkrete Menu
	 */
	abstract public void actionState();

	/** @return den StateContext(Menu), damit auch ein neuer State eingestellt werden kann */
	public IStateContext getStateContext();

	/**
	 * @return liefert den letzten State (oder null, wenn der aktuelle State der
	 *         Initialstate ist)
	 */
	public IState fromState();
}