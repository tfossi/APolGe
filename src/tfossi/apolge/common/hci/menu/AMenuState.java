/**
 * AMenuState.java
 * Branch hci
 * APolGe
 * tfossi-team
 * licence GPLv3  
 */
package tfossi.apolge.common.hci.menu;


import static tfossi.apolge.common.constants.ConstValue.LOGGER;
import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;

import java.util.Observable;

import org.apache.log4j.Logger;

import tfossi.apolge.common.hci.AMenu;
import tfossi.apolge.common.hci.IState;
import tfossi.apolge.common.hci.IStateContext;

/**
 * Es werden alle Methoden zum Umschalten zwischen den Menüs (States) bereitgestellt.<br>
 * 
 * @.pattern State: state
 * @author tfossi
 * @version 12.08.2014
 * @modified -
 * @since Java 1.6
 */
public abstract class AMenuState extends Observable implements IState {
	// ---- State -------------------------------------------------------------

	/**
	 * Enthält Statecontext (Editor, Server, Client). Dort ist die Statesteuerung.
	 */
	private final IStateContext statecontext;


	/* (non-Javadoc)
	 * @see tfossi.apolge.common.hci.IState#getStateContext()
	 */
	@Override
	public final IStateContext getStateContext() {
		return this.statecontext;
	}

	/**
	 * Der Aufrufer dieses Menus wird gespeichert, der diesen State instanziert und
	 * aufruft. Damit kann dieser State gezielt auf den Aufrufer reagieren. Ist wichtig
	 * bei Help, um wieder zurückzukommen und bei Back("Zurück")
	 * 
	 */
	private final IState fromState;

	/* (non-Javadoc)
	 * @see tfossi.apolge.common.hci.IState#fromState()
	 */
	@Override
	public final IState fromState() {
		return this.fromState;
	}

	/* (non-Javadoc)
	 * @see tfossi.apolge.common.hci.IState#actionState()
	 */
	@Override
	abstract public void actionState();

	// ---- Selbstverwaltung --------------------------------------------------
	/** serialVersionUID */
	@SuppressWarnings("unused")
	private final static long serialVersionUID = VERSION;
	/** logger */
	private final static Logger logger = Logger.getLogger(AMenuState.class.getPackage().getName());

	/**
	 * Stellt die Methoden für {@link IState}, IMenu und IReceiver bereit
	 * 
	 * @.post this.caller = callFromMenu: Das aufrufende Menu/State<br>
	 * @.post this.sc = statecontext: IStateContext<br>
	 * @param fromState
	 *            <ul>
	 *            <li>!=null: Enthält das aufrufende (==instanzierende) Menu. Es ist der
	 *            Aufrufer, der bei link #back()} wieder angesprungen wird.</li>
	 *            <li>==null: Ist das initiale Menu und das Programm wird beim Rücksprung
	 *            link #back()} beendet.</li>
	 *            </ul>
	 * @param statecontext
	 *            Enthält den {@link IStateContext StateContext}, der die Statesteuerung
	 *            übernimmt. Siehe auch {@link #getStateContext()}
	 */
	public AMenuState(final AMenu fromState, final IStateContext statecontext) {
		assert statecontext != null : "StateContext ist null";
		assert (fromState == null && statecontext.isFirstState())
				|| (fromState != null && !statecontext.isFirstState()) : "fromState==" + fromState
				+ " && statecontext.isFirstState()==" + statecontext.isFirstState()
				+ " bei erstem Eintrag";
		if(LOGGER) logger.debug((fromState == null ? "State für Erstmenü" : "State für "
				+ fromState.getClass().getSimpleName()));

		// IStateContext
		this.statecontext = statecontext;
		// Aufrufender State
		this.fromState = fromState;

		if(LOGGER) logger.debug("Der Aufrufer \"" + this.fromState + "\" und StateContext \""
				+ this.statecontext.getClass().getSimpleName() + "\" sind eingetragen");
	}

	/** @return Liefert SimpleName() der Klasse */
	@Override
	public String toString() {
		return this.getClass().getSimpleName();
	}
}
//
// /* (non-Javadoc)
// * @see name.tfossi.apolge200.common.hci.IState#peek()
// */
// public final IState peek(){
// return this.statecontext.peek();
// }