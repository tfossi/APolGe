/**
 * AWidget.java
 * Branch io
 * APolGe
 * tfossi-team
 * licence GPLv3  
 */
package tfossi.apolge.io.screenfactory;

import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Widget;

import tfossi.apolge.common.constants.ConstValueExtension;
import tfossi.apolge.common.hci.IMenu;
import tfossi.apolge.common.hci.IModel;
import tfossi.apolge.common.hci.IView;
import tfossi.apolge.io.Screen;

/** 
 * Die Widgets sind für die GUI-Steuerung von Belang. 
 * - Widgets einrichten(init) und entfernen(dispose)
 * - Widgets speichern (store)
 * - Widgets anzeigen (visible) und verstecken (unvisible)
 * - Widgetscontent aktivieren (activate)
 * - Displaysteuerung (displayLive, layout, isDisposed, refresh)
 * 
 * @.pattern Abstract Factory: Abstract Product
 * @see Cntr
 * @see AFactory
 * @see ALoop
 * @see AStorage
 * @author tfossi
 * @version 12.08.2014
 * @modified -
 * @since Java 1.6
 */
public abstract class AWidget {
	/**
	 * Das Widget layouten
	 */
	abstract void layout();

	/**
	 * Erstellen der Application-Widgets
	 * 
	 * @see ConstValueExtension#APPLICATIONSCREENS
	 * @param model
	 *            das aktuelle Modell
	 */
	abstract void initApplWidgets(IModel model);

	/**
	 * Erstellen der Menu-Widgets
	 * 
	 * @see ConstValueExtension#MENUSCREENS
	 * @param menu
	 *            das aktuelle Menu
	 */
	abstract void initMenuWidgets(IMenu menu);

	/**
	 * Erstellen der View-Widgets
	 * 
	 * @see ConstValueExtension#VIEWSCREENS
	 * @param view
	 *            der aktuelle View
	 * @param menu
	 *            das aktuelle Menu
	 */
	abstract void initViewWidgets(IView view, IMenu menu);

	/**
	 * Bildschirm neu aufbauen
	 * 
	 * @param screens
	 *            die Screen, die neu aufgebaut werden sollen
	 * @param view
	 *            der aktuelle Viewer
	 */
	abstract void refreshScreens(Screen[] screens, IView view);

	/**
	 * @return Shell ist disposed
	 */
	abstract boolean isDisposed();

	/**
	 * Display Schleife <code>this.display.readAndDispatch()</code>
	 */
	abstract void displayLive();

	/**
	 * Application-GUI disposen
	 */
	abstract void disposedApplicationScreen();

	/**
	 * ParameterCommand-GUI disposen
	 */
	abstract void disposedParameterScreen();

	
	/**
	 * TODO Comment
	 * @param view - 
	 * @param menu - 
	 * @param listOfWidgets - 
	 * @param scr - 
	 * @return - 
	 * @modified - 
	 */
	abstract boolean storeWidget(IView view, IMenu menu, java.util.List<Widget> listOfWidgets, Screen scr);
	//
//	/**
//	 * Application-Widget speichern
//	 * 
//	 * @param viewClazz
//	 *             Klasse des aktuellen Views
//	 * @param menuClazz
//	 *            Klasse des aktuellen Menus
//	 * @param widgetMap
//	 *            Widgets-Map
//	 * @param scr
//	 *            aktueller Screen
//	 */
//	abstract void storeApplWidget(Class<? extends IView> viewClazz,
//			Class<? extends IMenu> menuClazz, Map<String, Widget> widgetMap, Screen scr);
//
//	/**
//	 * Menu-Widget speichern
//	 * 
//	 * @param viewClazz            
//	 *             Klasse des aktuellen Views
//	 * @param menuClazz
//	 *             Klasse des aktuellen Menus
//	 * @param widgetMap
//	 *            Widgets-Map
//	 * @param scr
//	 *            aktueller Screen
//	 */
//	abstract void storeMenuWidget(Class<? extends IView> viewClazz,
//			Class<? extends IMenu> menuClazz, Map<String, Widget> widgetMap, Screen scr);
//
//	/**
//	 * View-Widget speichern
//	 * 
//	 * @param viewClazz
//	 *             Klasse des aktuellen Views
//	 * @param menuClazz
//	 *             Klasse des aktuellen Menus
//	 * @param widgetMap
//	 *            Widgets-Map
//	 * @param scr
//	 *            aktueller Screen
//	 */
//	abstract boolean storeWidget(Class<? extends IView> viewClazz,
//			Class<? extends IMenu> menuClazz, Map<String, Widget> widgetMap, Screen scr);
//
//	/**
//	 * Speichert Screeninformationen für Parameter-Informationen
//	 * 
//	 * @see Screen#CPM
//	 * @param clazz
//	 *             Klasse des aktuellen Views
//	 * @param widgetMap
//	 *            Widgets-Map der Parameter (<code>buildParameterWidgets(basegrp)</code>
//	 *            );
//	 */
//	abstract void storeParameterWidget(Class<? extends IView> clazz,
//			Map<String, Widget> widgetMap, Screen scr);

//	/**
//	 * Content zu Widgets zuordnen
//	 * 
//	 * @see Screen#M
//	 */
//	abstract void activateM_Widget();

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
	abstract void activate_Widget(IView view, IMenu menu, Screen scr);
//
//	/**
//	 * Content zu Widgets zuordnen
//	 * 
//	 * @see Screen#C
//	 * @param view
//	 *            aktueller View
//	 * @param clazz
//	 *            Klasse des aktuellen Menus
//	 */
//	abstract void activateC_Widget(IView view);
//
//	/**
//	 * Content zu Widgets zuordnen
//	 * 
//	 * @see Screen#MI
//	 * @param clazz
//	 *            Klasse des aktuellen Menus
//	 */
//	abstract void activateMI_Widget(Class<? extends IMenu> clazz);

	/**
	 * @return Widget-Basegroup für CPM-Screen
	 */
	abstract Group getCPMBaseGroup();

	/**
	 * Viewscreen wieder sichtbar machen
	 * 
	 * @param clazz
	 *            Klasse des aktuellen Views
	 */
	abstract void visibleViewScreen(Class<? extends IView> clazz);

	/**
	 * Menuscreen ausschalten
	 */
	abstract void unvisibleMenuScreen();

	/**
	 * Viewscreen ausschalten
	 */
	abstract void unvisibleViewScreen();

	/**
	 * Parameterscreen ausschalten
	 */
	abstract void unvisibleParameterScreen();

}
