/**
 * AStorage.java
 * Branch io
 * APolGe
 * tfossi-team
 * licence GPLv3  
 */
package tfossi.apolge.io.screenfactory;

import tfossi.apolge.common.hci.IMenu;
import tfossi.apolge.common.hci.IView;
import tfossi.apolge.io.IContent;
import tfossi.apolge.io.Screen;


/**
 * Der Storage speichert die Bildschirmdaten für Cnsl und GUI.
 * 
 * @.pattern Abstract Factory: Abstract Product
 * @see Cntr
 * @see AFactory
 * @see ALoop
 * @see AWidget
 * @author tfossi
 * @version 12.08.2014
 * @modified -
 * @since Java 1.6
 */
public abstract class AStorage {
//	/**
//	 * Speichert Screeninformationen für Message
//	 * 
//	 * @see Screen#M
//	 * @param content
//	 *            Screeninformationen
//	 * @param delete
//	 *            Sollen die Altinformationen gelöscht werden?
//	 */
//	abstract void storeM_Content(IContent content, final boolean delete);
//
//	/**
//	 * liefert Screeninformationen für Message
//	 * 
//	 * @see Screen#M
//	 * @return Screeninformationen für Message
//	 */
//	abstract IContent getM_Content();
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
//	abstract void storeMI_Content(Class<? extends IMenu> clazz, IContent content,
//			final boolean delete);
//
//	/**
//	 * liefert Screeninformationen für Menu-Informationen
//	 * 
//	 * @see Screen#MI
//	 * @param clazz
//	 *            die aktuelle Menuklasse
//	 * @return Screeninformationen für Menu-Informationen
//	 */
//	abstract IContent getMI_Content(Class<? extends IMenu> clazz);
//
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
	abstract void store_Content(IView view, IMenu menu, Screen scr, IContent content,
			final boolean delete);

	/**
	 * liefert Screeninformationen für View-Informationen
	 * 
	 * @see Screen#VI
	 * @param view
	 *            der aktuelle View
	 * @param menu
	 *            das aktuelle Menu
	 * @param scr
	 * 			  der gewünschte Screen
	 * @return Screeninformationen für Menu-Informationen
	 */
	abstract IContent get_Content(IView view, IMenu menu, Screen scr);
//
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
////	abstract void storeC_Content(IView view, IContent content, boolean delete);
//
//	/**
//	 * liefert Screeninformationen für Central-Informationen
//	 * 
//	 * @see Screen#C
//	 * @param clazz
//	 *            die aktuelle Viewklasse
//	 * @return Screeninformationen für Central-Informationen
//	 */
//	abstract IContent getC_Content(Class<? extends IView> clazz);

}
