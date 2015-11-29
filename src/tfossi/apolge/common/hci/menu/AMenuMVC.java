/**
 * AMenuMVC.java
 * Branch hci
 * APolGe
 * tfossi-team
 * licence GPLv3  
 */
package tfossi.apolge.common.hci.menu;


import static tfossi.apolge.common.constants.ConstValue.LOGGER;
import static tfossi.apolge.common.constants.ConstValue.NTAB;
import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;

import org.apache.log4j.Logger;

import tfossi.apolge.common.cmd.ICmd;
import tfossi.apolge.common.hci.AMenu;
import tfossi.apolge.common.hci.IMenu;
import tfossi.apolge.common.hci.IModel;
import tfossi.apolge.common.hci.IState;
import tfossi.apolge.common.hci.IStateContext;
import tfossi.apolge.common.hci.IView;

/**
 * Beinhaltet alle Methoden zur MVC-Steuerung
 * 
 * @author tfossi
 * @version 12.08.2014
 * @modified -
 * @since Java 1.6
 */
public abstract class AMenuMVC extends AMenuState implements IMenu {
	/** GRAYEDMENU */
	protected final String[] GRAYEDMENU;

	// ---- MVC Pattern ---------------------------------------------------------

	/** Speichert den zum Menu (Controller) passendes Model */
	private IModel model;

	/**
	 * @param model
	 *            das Modell aus MVC-Pattern, das zum Controller gehört, eintragen.
	 */
	protected final void setModel(final IModel model) {
		this.model = model;
	}

	/* (non-Javadoc)
	 * @see tfossi.apolge.common.hci.IMenu#getModel()
	 */
	@Override
	public final IModel getModel() {
		return this.model;
	}

	/** Speichert den zum Menu (Controller) passender View */
	private IView view;

	/** Das ist der zukübftige View. Benötigt, wenn nur Views umgeschaltet werden */
	protected IView nextView;

	
	/* (non-Javadoc)
	 * @see tfossi.apolge.common.hci.IMenu#setNextView(tfossi.apolge.common.hci.IMenu)
	 */
	@Override
	public final void setNextView(IMenu menu) {
		if (this.nextView != null) {
			if(LOGGER) logger.info("MVC::VIEWER\tBaue Viewer " + this.nextView.getClass().getSimpleName()
					+ " des " + this.getClass().getSimpleName() + " .");
			this.setViewer(this.nextView);
			this.nextView = null;
			this.getView().initiate(this, this.getStateContext());
			// Bei den Befehlen den Receiver eintragen
			for (ICmd ac : this.getModel().getStateCmdList()) {
				ac.setReceiver((AMenu) menu, this.GRAYEDMENU);
			}
		}
	}

	/**
	 * @param view
	 *            der Viewer aus MVC-Pattern, das zum Controller gehört, eintragen.
	 */
	protected final void setViewer(final IView view) {

		if(LOGGER) logger.debug("IO::Observable\t"
				+ (this.getView() == null ? "" : "Lösche \""
						+ this.getView().getClass().getSimpleName() + "\"" + NTAB) + "Trage \""
				+ view.getClass().getSimpleName() + "\" ein.");

		this.deleteObserver(this.view);
		this.addObserver(view);
		this.view = view;
		this.getModel().setViewer(view);
	}

	/* (non-Javadoc)
	 * @see tfossi.apolge.common.hci.IMenu#getView()
	 */
	@Override
	public final IView getView() {
		return this.view;
	}

	/**
	 * Solange exitmenu == <b>false</b> ist, wird das Menü nicht verlassen. Die
	 * Auswertung erfolgt in link #notifyViewer(IView, Object[])}
	 */
	private boolean exitMenu;

	/* (non-Javadoc)
	 * @see tfossi.apolge.common.hci.IMenu#getExitMenu()
	 */
	@Override
	public final boolean getExitMenu() {
		return this.exitMenu;
	}

	/** Einstellen, dass das Menu NICHT verlassen wird */
	protected final void clearExitMenu() {
		if(LOGGER) logger.trace("Lösche Exit-Flag für das Menü.");
		this.exitMenu = false;
	}

	/** Einstellen, dass das Menu verlassen wird */
	protected final void setExitMenu() {
		this.exitMenu = true;
	}

	// ---- Selbstverwaltung --------------------------------------------------
	/** serialVersionUID */
	@SuppressWarnings("unused")
	private final static long serialVersionUID = VERSION;
	/** logger */
	private final static Logger logger = Logger.getLogger(AMenuMVC.class.getPackage().getName());

	/**
	 * Stellt die Methoden für {@link IState} bereit , IMenu und IReceiver bereit
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
	 * @param grayedMenu -
	 */
	public AMenuMVC(final AMenu fromState, final IStateContext statecontext, final String[] grayedMenu) {
		super(fromState, statecontext);
		this.GRAYEDMENU = grayedMenu;
		if(LOGGER) logger.debug("Richte MVC ein.");
		
	}

	/** @return Liefert SimpleName() der Klasse */
	@Override
	public String toString() {
		return this.getClass().getSimpleName();
	}
}
// /** Stellt die VIEW-Widgets ein */
// protected void setViewWidgets(){
// assert false;
// if(LOGGER) logger.info("SWT::\tTrage VIEWSCREENS für "+this.view.getClass().getSimpleName()+" ein!");
// // Die Daten sind individuell zu den Views und daher müssen sich die
// // Views jedesmal neu an den Screen angemeldet werden.
// SWTGrafik swt = this.getStateContext().getSWTGrafik(this.getStateContext());
// IGrfCntr session = MetaData.getInstance().getSession(this.getStateContext());
// if (swt != null) {
// for (Screen scr : ConstValue.VIEWSCREENS){
// Group group = swt.getGroup(scr);
// Widget widget = scr.buildSWTWidget(this.getModel(), group, null);
// MetaData.getInstance().storeViewWidget(session, this.view.getClass(), widget, scr);
// }
// }
// }