/** 
 * AApplication.java
 * Branch ces
 * APolGe
 * tfossi-team
 * licence GPLv3 
 */
package tfossi.apolge.ces;

import static tfossi.apolge.common.constants.ConstValue.LOGGER;
import static tfossi.apolge.common.constants.ConstValue.NTAB;
import static tfossi.apolge.common.constants.ConstValue.ON;
import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;

import java.util.Properties;
import java.util.Stack;

import org.apache.log4j.Logger;

import tfossi.apolge.ces.client.Client;
import tfossi.apolge.ces.editor.Editor;
import tfossi.apolge.ces.server.Server;
import tfossi.apolge.common.hci.IState;
import tfossi.apolge.common.hci.IStateContext;
import tfossi.apolge.common.system.PreLoad;
import tfossi.apolge.io.screenfactory.CnslFactory;
import tfossi.apolge.io.screenfactory.Cntr;
import tfossi.apolge.io.screenfactory.GuiFactory;
import tfossi.apolge.io.screenfactory.SWTGrafik;
 
/**
 * Zentrale Klasse für die Applikationsklassen {@link Editor}, {@link Server}
 * und {@link Client}.
 * <p>
 * Zentrale Komponente ist die Statesteuerung eines State Pattern. ( Controller
 * {@link IStateContext}, State {@link IState}), mit deren Hilfe das Menu
 * angesteuert wird. Die Application selber läuft als Thread unter APolGe.
 * </p>
 * <p>
 * Eingestellt wird ein neues Menü mit<br>
 * <br>
 * <code>this.setActualState(editorMenu);</code>
 * </p>
 * <p>
 * Umgeschaltet auf das neue Menu wird mit<br>
 * <br>
 * <code>this.switchToState();</code>
 * </p>
 * 
 * @.pattern State: IStateContext
 * @see IStateContext
 * @see IState
 * @author tfossi
 * @version 11.08.2014
 * @modified -
 * @since Java 1.6
 */
public abstract class AApplication extends Thread implements IStateContext 
{

	// --- Grafik -------------------------------------------------------------
	/**
	 * Nimmt die Instanz der Ausgabefactory
	 */
	private Cntr cntr;


	/* (non-Javadoc)
	 * @see tfossi.apolge.common.hci.IStateContext#getCntr()
	 */
	@Override
	public final Cntr getCntr() {
		return this.cntr;
	}

	/**
	 * Einrichten der Grafik des Applicationscreens
	 * 
	 * param model
	 *            das aktuelle Model (ist nur für MCM-Screen notwendig!)
	 * @TODO Link zum Screenaufbau
	 */
	protected final void einrichtenGrafik() {
		SWTGrafik swt = null;
		if (this.props.get("screen").equals(new Boolean(ON))) {
			assert false: "ErrApp.NI_X.erraufruf(Grafik ist nicht implementiert!";
			if(LOGGER) logger.debug("SWT::Stelle Ein- und Ausgabe auf SWT-Betrieb ein!");
	 		this.cntr = new Cntr(new GuiFactory());
			swt = new SWTGrafik(this.getId());
		} else {
			if(LOGGER) logger.debug("Stelle Ein- und Ausgabe auf Konsolenbetrieb ein!");
			this.cntr = new Cntr(new CnslFactory());
		}
		this.cntr.generiereProdukte(swt);
	}

	// ---- PreLoad -----------------------------------------------------------

	/**
	 * Speichert die Properties. Es wird danach entschieden, was alles geladen
	 * werden soll
	 */
	protected final Properties props;

	/**
	 * Getter: Liefert die Properties
	 * 
	 * @return properties
	 */
	public final Properties getProperties() {
		return this.props;
	}

	/**
	 * Liefert die Properties "Name"
	 * 
	 * @param name
	 *            Name des Property
	 * @return der Property
	 */
	public final Object getProperties(String name) {
		return this.props.get(name);
	}

	/**
	 * Ist notwenig, um auf das Ende des Preloadsvorgangs - <i>Thread.join()</i>
	 * - warten zu können
	 */
	protected final PreLoad pL;

	/**
	 * @return Liefer PreLoad
	 */
	public final PreLoad getPreLoad() {
		return this.pL;
	}

	// ---- IStateContext -----------------------------------------------------

	/**
	 * Ist ein Stack mit den aufgerufenden Menüs (State, Zustandsklassen). Das
	 * aktuelle Menü liegt auf TOP. Ist es der einzige, handelt es sich um das
	 * Einstiegsmenü.
	 * 
	 * @see IState
	 */
	private final Stack<IState> actualState = new Stack<IState>();


	/* (non-Javadoc)
	 * @see tfossi.apolge.common.hci.IStateContext#isFirstState()
	 */
	@Override
	public final boolean isFirstState() {
		return this.actualState.empty();
	}

	/* (non-Javadoc)
	 * @see tfossi.apolge.common.hci.IStateContext#setPrevState()
	 */
	@Override
	public final void setPrevState() {
		if (this.actualState.isEmpty())
			return;
		this.actualState.pop();
	}


	/* (non-Javadoc)
	 * @see tfossi.apolge.common.hci.IStateContext#setClearState()
	 */
	@Override
	public final void setClearState() {
		this.actualState.clear();
	}

	/* (non-Javadoc)
	 * @see tfossi.apolge.common.hci.IStateContext#switchToState()
	 */
	@Override
	public final void switchToState() {

		while (!this.getCntr().isDisposed() && this.actualState != null
				&& !this.actualState.isEmpty()) {
			logger
					.info("Starte mit Eingabeschleife"
							+ NTAB
							+ "---------------------------------------------------------------------"
							+ NTAB
							+ this.actualState
							+ NTAB
							+ "---------------------------------------------------------------------");
			IState local = this.actualState.peek();

			if(LOGGER) logger.debug("STATE::STATECONTEXT\tStarte State [" + local + "]");
			// Schalte auf neuen State um
			local.actionState();
			if(LOGGER) logger.debug("STATE::STATECONTEXT\tBeende State [" + local + "]");

			logger
					.info("Beende Eingabeschleife"
							+ NTAB
							+ "---------------------------------------------------------------------"
							+ NTAB
							+ "Neues Ziel "
							+ this.actualState
							+ NTAB
							+ "---------------------------------------------------------------------");

		}
	}


	/* (non-Javadoc)
	 * @see tfossi.apolge.common.hci.IStateContext#setActualState(tfossi.apolge.common.hci.IState)
	 */
	@Override
	public final IState setActualState(final IState actualStateIn) {
		assert actualStateIn != null : "State ist nicht definiert!";
		// State speichern
		this.actualState.push(actualStateIn);
		if(LOGGER) logger.debug("State \"" + actualStateIn.toString() + "\" wird "
				+ "als " + this.actualState.size()
				+ ". Eintrag auf den Stack gepackt.");
		return actualStateIn;
	}

	// ---- Selbstverwaltung --------------------------------------------------
	/** serialVersionUID */
	@SuppressWarnings("unused")
	private final static long serialVersionUID = VERSION;
	/** logger */
	private final static Logger logger = Logger.getLogger(AApplication.class
			.getPackage().getName());

	/**
	 * Parentkomponente für die Applikationen von APolGe
	 * 
	 * @param preload
	 *            ist Thread, der Daten und Tabellen vorläd. Ist der Vorgang
	 *            nicht abgeschlossen, wartet der Editor mit der weitere
	 *            Bearbeitung.
	 * @param properties
	 *            ist Properties. Dort ist enthalten, ob GUI oder Console
	 *            gestarten werden soll.	
	 * @modified -
	 */
	public AApplication(final PreLoad preload, final Properties properties) {
		assert preload != null : "preload ist nicht definiert!";
		assert properties != null : "probs ist nicht definiert!";
		this.pL = preload;
		this.props = properties;
	}
}
