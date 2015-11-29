/**
 * IModel.java
 * Branch hci
 * APolGe
 * tfossi-team
 * licence GPLv3  
 */
package tfossi.apolge.common.hci;

import tfossi.apolge.common.cmd.CommandList;
import tfossi.apolge.io.IContent;
import tfossi.apolge.io.Screen;
import tfossi.apolge.io.screenfactory.Cntr;

/** 
 * Schnittstelle zum Datenmodel
 *  
 * @author tfossi
 * @version 12.08.2014
 * @modified -
 * @since Java 1.6
 */
public interface IModel {
	/**
	 * liefert Screeninformationen für Menu-Commandmenu. Ein Storage existiert nicht, da
	 * das Menu erzeugt wird.
	 * 
	 * see AStorage#getMCM_Content(IModel model)
	 * @see Screen#MCM
	 * @return Screeninformationen für Menu-Commandmenu
	 */
	public IContent getApplCmdListContent();

	/**
	 * Stelle den Screencontent ein und aktiviere ggfs. das Widgets
	 * 
	 * @param cntr
	 *            Fassade zur abstrakten Screenfactory
	 * @param menu
	 *            aktuelles Menu
	 * @param scr
	 *            aktueller Screen
	 * @param content
	 *            der Inhalt
	 * @param notify
	 *            Viewer aktiv(sofort) informieren
	 * @param delete
	 *            Alte Nachrichten löschen
	 * @param input -
	 */
	public void setInformation(Cntr cntr, IMenu menu, Screen scr, IContent content, boolean notify,
			boolean delete, boolean input);

	/** @return Die Liste Applikationsbefehle */
	public CommandList getApplCmdList();

	/** @return Die Liste der State(Menu)befehle */
	public CommandList getStateCmdList();

	/** @return Die Liste der Parameterbefehle zu einem State */
	public CommandList getParaCmdList();

	/**
	 * Aktuellen Viewer einstellen
	 * 
	 * @param view
	 *            der aktuelle Viewer
	 */
	public void setViewer(IView view);

	/**
	 * Den aktuellen Viewer abrufen
	 * 
	 * @return der aktuelle Viewer
	 * 
	 */
	public IView getViewer();

	// ---- Filtergruppe ------------------------------------------------------

	/**
	 * @return the receiver
	 */
	public String getReceiverFilter();
	/**
	 * @param receiver the receiver to set
	 */
	public void setReceiverFilter(String receiver);

	/**
	 * @return the reset
	 */
	public boolean isResetFilter();
	/**
	 * @param reset the reset to set
	 */
	public void setResetFilter(boolean reset);

	
	/**
	 * @return the details
	 */
	public boolean isDetailsFilter();
	/**
	 * @param details the details to set
	 */
	public void setDetailsFilter(boolean details);

	
	/**
	 * @return the filter
	 */
	public String getPatternFilter();

	/**
	 * @param filter the filter to set
	 */
	public void setPatternFilter(String filter);
}
// public IGrfCntr getGrfCntr();
// public void storeApplWidget(Map<String, Widget> map, Screen scr);
// public void storeMenuWidget(Class<? extends IMenu> clazz,
// Map<String, Widget> map, Screen scr);
// public void storeViewWidget(Class<? extends IView> clazz,
// Map<String, Widget> map, Screen scr);

// public void setInformation( IGrfCntr session, IMenu menu, Screen scr, Widget content,
// boolean notify, boolean delete);
// /**
// * Liefert den Screencontent (für Console)
// * @param session
// * @param menu
// * @param scr
// * @return der Content
// */
// public String[] getInformation(Cntr cntr, IMenu menu, Screen scr);
//

//
// /**
// * @param string
// * @param para2
// */
// public void addName(String string, String para2);
//
// /**
// * @param string
// * @param para2
// */
// public void removeName(String string, String para2);
//
// /**
// * @param string
// * @param string2
// * @param string3
// */
// public void renameName(String string, String string2, String string3);
//
//