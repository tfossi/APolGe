/**
 * IStateContext.java
 * Branch hci
 * APolGe
 * tfossi-team
 * licence GPLv3  
 */
package tfossi.apolge.common.hci;

import tfossi.apolge.io.screenfactory.Cntr;

/**
 * Schnittstelle für IStateContext. StateContext hält die Information über den aktuellen
 * und dem nächsten State. Letzteres benötigt der StateContext, da er auf den neuen State
 * umschaltet.<br>
 * Die State entscheiden daher <b>selber</b>, welches ihr Nachfolger ist! Die Integration
 * von neuen States ist daher großen Aufwand möglich.
 * 
 * @.pattern State: statecontext
 * @author tfossi
 * @version 12.08.2014
 * @modified -
 * @since Java 1.6
 */
public interface IStateContext {

	// ---- STATESTEUERUNG ----------------------------------------------------

	/**
	 * Anmeldung des neuen States(Menus)<br>
	 * Speichert die aktuelle Zustandsklasse (Menü)
	 * 
	 * @param state
	 *            ist die neue aktuelle Zustandsklasse (Menü). Ist diese
	 *            <code>null</code> wird das IStateContext verlassen
	 * @return den aktuellen State
	 * @see AMenu
	 */
	public IState setActualState(final IState state);

	/**
	 * Führt eine Methode in der aktuellen Zustandsklasse aus. Schaltet also auf ein
	 * anderes Menü um. Ist actualState == <b>null</b> ist das Programm zu beenden,
	 * ansonsten läuft an dieser Stelle das Programm in einer Dauerschleife.<br>
	 * Die Schnittstelle ist {@linkplain AMenu#actionState()}
	 */
	public void switchToState();

	/** Schaltet auf vorherigen State zurück */
	public void setPrevState();

	/**
	 * @return liefert <code>true</code>, wenn es keinen State gibt. Damit ist es der
	 *         nächste State das Root-Menü, das vom IStateContext eingetragen werden muss
	 */
	public boolean isFirstState();

	/**
	 * TODO Comment
	 * @modified - 
	 */
	public void setClearState(); 
	/**
	 * @return die Fassade der abstrakten Screenfactory
	 */
	public Cntr getCntr();
}

// public boolean isGrafik();
// public IGrfCntr getGrfCntr();
// public boolean isDisposed();
// public void disposedApplicationScreen();
// public void disposedViewScreen();
// public void storeApplWidget(Map<String, Widget> map, Screen scr);
// public void storeMenuWidget(Class<? extends IMenu> clazz,
// Map<String, Widget> map, Screen scr);
// public void storeViewWidget(Class<? extends IView> clazz,
// Map<String, Widget> map, Screen scr);
