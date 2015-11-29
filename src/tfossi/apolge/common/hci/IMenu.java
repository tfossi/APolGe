/**
 * IModell.java
 * Branch hci
 * APolGe
 * tfossi-team
 * licence GPLv3  
 */
package tfossi.apolge.common.hci;

import java.util.Observable;
import java.util.Observer;

import tfossi.apolge.common.cmd.CommandList;
import tfossi.apolge.io.IContent;
import tfossi.apolge.io.Screen;
import tfossi.apolge.io.screenfactory.Cntr;

/**
 * Controllerschnittstelle
 * 
 * @author tfossi
 * @version 12.08.2014
 * @modified -
 * @since Java 1.6
 */
public interface IMenu extends Observer {
	/**
	 * Stellt die Informationen (Content) für die Screen einer GuiCntr ein<br>
	 * Beispiele:<br> 
	 * MI belegen <br><code>
	 * this.setInformation(this.getStateContext().getCntr(), Screen.MI, new ContentString(new String[]{"MIese or unMIese", ""+view.getClass().getSimpleName(), this.getClass().getSimpleName()}), false, false);
	 * </code><br>
	 * M belegen<br> <code>
	 * this.setInformation(this.getStateContext().getCntr(), Screen.M, new ContentString("NEUE MESSAGE"), false, false);
	 * </code><br>
	 * VI belegen <br> <code>
	 * this.setInformation(this.getStateContext().getCntr(), Screen.VI, new ContentString(new String[]{"VIp oder not VIp", ""+view.getClass().getSimpleName(), this.getClass().getSimpleName()}), false, false);
	 * </code><br>
	 * C belegen <br><code>
	 * this.setInformation(this.getStateContext().getCntr(), Screen.C, new ContentString("B A M B U L E"), false, false);
	 * </code><br>
	 * 
	 * 
	 * 
	 * @param cntr
	 *            Fassade für abstrakte Screenfactory
	 * @param scr
	 *            der aktuelle Screen
	 * @param content
	 *            der Inhalt, der eingestellt werden soll
	 * @param notify
	 *            Viewer sofort informieren
	 * @param delete
	 *            Alte Daten löschen
	 * @param input -
	 */
	public void setInformation(Cntr cntr, Screen scr, IContent content,
			boolean notify, boolean delete, boolean input);

	/** @return den MVC-Viewer zum Modell/Controller */
	public IView getView();

	/** @return den MVC-Model zum Viewer/Controller */
	public IModel getModel();

	/**
	 * @return den Status, ob das Menu verlassen werden soll.
	 */
	public boolean getExitMenu();

	/**
	 * Wird aufgerufen, wenn für den Screen neue Informationen vorliegen.
	 * 
	 * @param menu
	 *            Von wem die Information stammt
	 * @param scr
	 *            Für welchen Screen
	 */
	public void informObserver(Observable menu, Object scr);

	/**
	 * Baut den Viewer für das Menu zusammen
	 * 
	 * @param menu
	 *            das nächste Menu
	 */
	public void setNextView(IMenu menu);

	/**
	 * @return die Liste Applikationsbefehle
	 */
	public CommandList getApplCmdList();

	/**
	 * @return die Liste der State(Menu)befehle
	 */
	public CommandList getStateCmdList();

	/**
	 * @return die Liste der Stateparameterbefehle
	 */
	public CommandList getParaCmdList();

	/**
	 * @return den StateContext
	 */
	public IStateContext getStateContext();

	// public String[] getGrayed();
}

// public Object[] getCentralInformation(Object view);