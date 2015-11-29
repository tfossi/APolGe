/**
 * Cntr.java
 * Branch io
 * APolGe
 * tfossi-team
 * licence GPLv3  
 */
package tfossi.apolge.io.screenfactory;

import java.util.List;

import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Widget;

import tfossi.apolge.common.cmd.CommandList;
import tfossi.apolge.common.constants.ConstValueExtension;
import tfossi.apolge.common.hci.AMenu;
import tfossi.apolge.common.hci.AView;
import tfossi.apolge.common.hci.IMenu;
import tfossi.apolge.common.hci.IModel;
import tfossi.apolge.common.hci.IView;
import tfossi.apolge.io.IContent;
import tfossi.apolge.io.Screen;

/**
 * Fassade für Screenfactory.
 * 
 * @.pattern Fassade:
 * @see AFactory
 * @see ALoop
 * @see AStorage
 * @see AWidget
 * @author tfossi
 * @version 12.08.2014
 * @modified -
 * @since Java 1.6
 */
public class Cntr {

	/** Die Instanz der Ausgabefactory für Cnsl oder Gui */
	private final AFactory fabrik;

	/** Das Factoryprodukt der Programmsteuerung für Cnsl oder Gui */
	private ALoop loop = null;

	/** Das Factoryprodukt der Screendatenspeicherung für Cnsl oder Gui */
	private AStorage storage = null;

	/** Das Factoryprodukt der GUI-Steuerung für Cnsl(==nope) oder Gui */
	private AWidget widget = null;

	/**
	 * Screensteuerung instanzieren
	 * 
	 * @param fabrik
	 *            die gewünschte Fabrik für Cnsl oder GUI
	 */
	public Cntr(AFactory fabrik) {
		this.fabrik = fabrik;
	}

	/**
	 * Produkte erzeugen
	 * 
	 * @see ALoop
	 * @see AStorage
	 * @see AWidget
	 * @param swt
	 *            null bei Cnsl
	 */
	public void generiereProdukte(SWTGrafik swt) {
		if (this.fabrik != null) {
			this.loop = this.fabrik.loop(this);
			this.storage = this.fabrik.storage();
			this.widget = this.fabrik.widgets(this, swt);
		}
	}

	// ---- Fassade -----------------------------------------------------------
	// 
	// LOOP

	/**
	 * Viewer des MVC aktualisieren
	 * 
	 * @see IView
	 * @see AView
	 * @see AMenu
	 * @see IMenu
	 * @param viewer
	 *            ist der aktuelle Viewer des aktiven Menus
	 * @param menu
	 *            das aktive, aufrufende Menu
	 */
	public void notifyViewer(IView viewer, IMenu menu) {
		this.loop.notifyViewer(viewer, menu);
	}

	/**
	 * Der Bildschirm wird dargestellt und Viewer wartet auf weitere Eingaben.<br> 
	 * (GUI ist <i>not used</i>)
	 * @param menu
	 *            ist das aufrufende Menu
	 * @param notifyScreens
	 *            Schalter, die festlegen, welcher Screen ein update erhalten soll. Ist
	 *            vom Typ <code>Screen</code>
	 * @param commands
	 *            die Liste der Commands für Konsole
	 */
	public void show(IMenu menu, NotifyScreens notifyScreens, CommandList commands) {
		this.loop.show(menu, notifyScreens, commands);
	}

	/**
	 * Ruft die Eingabe- und Command-Funktion der Cnsl bzw. Screenrefresh, DisplayLive
	 * Loop der GUI auf
	 * 
	 * @param menu
	 *            ist das aufrufende Menu
	 * @param notifyScreens
	 *            Schalter, die festlegen, welcher Screen ein update erhalten soll. Ist
	 *            vom Typ <code>Screen</code>
	 * @param commands
	 *            die Liste der Commands für Konsole
	 * @param cnt
	 *            Counter, um Mehrfachdarstellungen über cnt==1 zu vermeiden.
	 */
	public void update(IMenu menu, NotifyScreens notifyScreens, CommandList commands, int cnt) {
		this.loop.update(menu, notifyScreens, commands, cnt);
	}

	// STORAGE
//
//	/**
//	 * Speichert Screeninformationen für Message
//	 * 
//	 * @see Screen#M
//	 * @param content
//	 *            Screeninformationen
//	 * @param delete
//	 *            Sollen die Altinformationen gelöscht werden?
//	 */
//	public void storeM_Content(IContent content, final boolean delete) {
//		this.storage.storeM_Content(content, delete);
//	}
//
//	/**
//	 * liefert Screeninformationen für Message
//	 * 
//	 * @see Screen#M
//	 * @return Screeninformationen für Message
//	 */
//	public IContent getM_Content() {
//		return this.storage.getM_Content();
//	}
//
//	/**
//	 * Speichert Screeninformationen für Menu-Informationen
//	 * 
//	 * @see Screen#MI
//	 * @param clazz
//	 *            die aktuelle Menuklasse
//	 * @param content
//	 *            Screeninformationen
//	 * @param delete
//	 *            Sollen die Altinformationen gelöscht werden?
//	 */
//	public void storeMI_Content(Class<? extends IMenu> clazz, IContent content,
//			final boolean delete) {
//		this.storage.storeMI_Content(clazz, content, delete);
//	}
//
//	/**
//	 * liefert Screeninformationen für Menu-Informationen
//	 * 
//	 * @see Screen#MI
//	 * @param clazz
//	 *            die aktuelle Menuklasse
//	 * @return Screeninformationen für Menu-Informationen
//	 */
//	public IContent getMI_Content(Class<? extends IMenu> clazz) {
//		return this.storage.getMI_Content(clazz);
//	}

	/**
	 * Speichert Screeninformationen für View-Informationen
	 * 
	 * @see Screen#VI
	 * @param view
	 *            der aktuelle View
	 * @param menu
	 *            das aktuelle Menu
	 * @param scr
	 * 			  der gewünschte Screen
	 * @param content
	 *            Screeninformationen
	 * @param delete
	 *            Sollen die Altinformationen gelöscht werden?
	 */
	public void store_Content(IView view, IMenu menu, Screen scr, IContent content,
			final boolean delete) {
		this.storage.store_Content(view, menu, scr, content, delete);
	}

	/**
	 * liefert Screeninformationen für View-Informationen
	 * @param view 
	 *            die aktuelle Viewklasse
	 * @param menu -
	 * 
	 * @see Screen#VI

	 *            die aktuelle Viewklasse
	 * @param scr
	 * 			  der gewünschte Screen
	 * @return Screeninformationen für Menu-Informationen
	 */
	public IContent get_Content(IView view, IMenu menu, Screen scr) {
		return this.storage.get_Content(view, menu, scr);
	}

//	/**
//	 * Speichert Screeninformationen für Central-Informationen
//	 * 
//	 * @see Screen#C
//	 * @param view
//	 *            aktueller View
//	 * @param content
//	 *            Screeninformationen
//	 * @param delete
//	 *            Sollen die Altinformationen gelöscht werden?
//	 */
//	public void storeC_Content(IView view, IContent content, boolean delete) {
//		this.storage.storeC_Content(view, content, delete);
//	}
//
//	
//	/**
//	 * liefert Screeninformationen für Central-Informationen
//	 * 
//	 * @see Screen#C
//	 * @param clazz
//	 *            die aktuelle Viewklasse
//	 * @return Screeninformationen für Central-Informationen
//	 */
//	public IContent getC_Content(Class<? extends IView> clazz) {
//		return this.storage.getC_Content(clazz);
//	}

	// WIDGET

	/**
	 * Das Widget layouten
	 */
	public void layout() {
		this.widget.layout();
	}

	/**
	 * Erstellen der Application-Widgets <br>
	 * (Console <i>not used</i>)
	 * 
	 * @see ConstValueExtension#APPLICATIONSCREENS
	 * @param model
	 *            das aktuelle Modell
	 */
	public void initApplWidgets(IModel model) {
		this.widget.initApplWidgets(model);
	}

	/**
	 * Erstellen der Menu-Widgets <br>
	 * (Console <i>not used</i>)
	 * 
	 * @see ConstValueExtension#MENUSCREENS
	 * @param menu
	 *            das aktuelle Menu
	 */
	public void initMenuWidgets(IMenu menu) {
		this.widget.initMenuWidgets(menu);
	}

	/**
	 * Erstellen der View-Widgets <br>
	 * (Console <i>not used</i>)
	 * 
	 * @see ConstValueExtension#VIEWSCREENS
	 * @param view
	 *            der aktuelle View
	 * @param menu
	 *            das aktuelle Menu
	 */
	public void initViewWidgets(IView view, IMenu menu) {
		this.widget.initViewWidgets(view, menu);
	}

	/**
	 * Bildschirm neu aufbauen
	 * 
	 * @param screens
	 *            die Screen, die neu aufgebaut werden sollen
	 * @param view
	 *            der aktuelle Viewer
	 */
	public final void refreshScreens(Screen[] screens, IView view) {
		this.widget.refreshScreens(screens, view);
	}

	/**
	 * @return Shell ist disposed, Console liefert immer <code>false</code>
	 */
	public final boolean isDisposed() {
		return this.widget.isDisposed();
	}

	/**
	 * Display Schleife <code>this.display.readAndDispatch()</code>
	 */
	public final void displayLive() {
		this.widget.displayLive();
	}

	/**
	 * Application-GUI abmelden
	 */
	public void disposedApplicationScreen() {
		this.widget.disposedApplicationScreen();
	}

	
	/**
	 * Menuscreen ausschalten
	 */
	public void unvisibleMenuScreen() {
		this.widget.unvisibleMenuScreen();

	}

	/**
	 * Viewscreen ausschalten
	 */
	void unvisibleViewScreen() {
		this.widget.unvisibleViewScreen();
	}

	/**
	 * Parameterscreen ausschalten
	 */
	void unvisibleParameterScreen() {
		this.widget.unvisibleParameterScreen();

	}

//	/**
//	 * Content zu Widgets zuordnen
//	 * 
//	 * @see Screen#M
//	 */
//	void activateM_Widget() {
//		this.widget.activateM_Widget();
//	}

	/**
	 * Content zu Widgets zuordnen
	 * 
	 * @see Screen#VI
	 * @param view
	 *            aktueller View
	 * @param menu
	 *            aktuelles Menu
	 * @param scr
	 *            aktueller Screen
	 */
	void activate_Widget(IView view, IMenu menu, Screen scr) {
		this.widget.activate_Widget(view, menu, scr);
	}
//
//	/**
//	 * Content zu Widgets zuordnen
//	 * 
//	 * @see Screen#C
//	 * @param view
//	 *            aktueller View
//	 */
//	void activateC_Widget(IView view) {
//		// assert false;
//		this.widget.activateC_Widget(view);
//	}

//	/**
//	 * Content zu Widgets zuordnen
//	 * 
//	 * @see Screen#MI
//	 * @param clazz
//	 *            aktuelles Menu
//	 */
//	void activateMI_Widget(Class<? extends IMenu> clazz) {
//		this.widget.activateMI_Widget(clazz);
//	}

	/**
	 * @return Widget-Basegroup für CPM-Screen
	 */
	public Group getCPMBaseGroup() {
		return this.widget.getCPMBaseGroup();
	}

	/**
	 * Speichert Screeninformationen für Parameter-Informationen
	 * 
	 * @see Screen#CPM
	 * 
	 * @param view
	 *            die aktuelle Viewerklasse
	 * @param menu
	 *            Widgets-Map der Parameter (<code>buildParameterWidgets(basegrp)</code>
	 *            );
	 * @param listOfWidgets -
	 * @param scr -
	 * @modified - 
	 */
	public void storeWidget(IView view, IMenu menu, List<Widget> listOfWidgets, Screen scr) {
		this.widget.storeWidget(view, menu, listOfWidgets, scr);
	}

}
