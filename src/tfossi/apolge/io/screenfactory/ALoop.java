/**
 * ALoop.java
 * Branch io
 * APolGe
 * tfossi-team
 * licence GPLv3  
 */
package tfossi.apolge.io.screenfactory;

import tfossi.apolge.common.cmd.CommandList;
import tfossi.apolge.common.hci.AMenu;
import tfossi.apolge.common.hci.IMenu;
import tfossi.apolge.common.hci.AView;
import tfossi.apolge.common.hci.IView;


/**
 * Programmsteuerung für Cnsl oder Gui
 * 
 * @.pattern Abstract Factory: Abstract Product
 * @see Cntr
 * @see AFactory
 * @see AWidget
 * @see AStorage
 * @author tfossi
 * @version 12.08.2014
 * @modified -
 * @since Java 1.6
 */
public abstract class ALoop {
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
	abstract void notifyViewer(IView viewer, IMenu menu);

	/**
	 * Der Bildschirm wird dargestellt und Viewer wartet auf weitere Eingaben. Ist für
	 * Cnsl-Steuerung
	 * 
	 * @see AMenu
	 * @see IMenu
	 * @param menu
	 *            ist das aufrufende Menu
	 * @param notifyScreens
	 *            Schalter, die festlegen, welcher Screen ein update erhalten soll. Ist
	 *            vom Typ <code>Screen</code>
	 * @param commands
	 *            die Liste der Commands für Konsole
	 */	
	abstract void show(IMenu menu, NotifyScreens notifyScreens, CommandList commands);

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
	abstract void update(IMenu menu, NotifyScreens notifyScreens, CommandList commands, int cnt);
}
