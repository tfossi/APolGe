/**
 * IView.java
 * Branch hci
 * APolGe
 * tfossi-team
 * licence GPLv3  
 */
package tfossi.apolge.common.hci;

import java.util.Map;
import java.util.Observer;

import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Widget;

import tfossi.apolge.common.cmd.CommandList;
import tfossi.apolge.io.IContent;
import tfossi.apolge.io.Screen;
import tfossi.apolge.io.screenfactory.AStorage;
import tfossi.apolge.io.screenfactory.AWidget;
import tfossi.apolge.io.screenfactory.Cntr;

/**
 * Schnittstelle zum Viewer im MVC Model
 * 
 * @.pattern MVC: Viewer
 * @author tfossi
 * @version 12.08.2014
 * @modified -
 * @since Java 1.6
 */
public interface IView extends Observer {

	/** Stellt ein, das der Viewer im SWT-Modus (GUI=ON) verlassen werden soll. */
	public void setExitSWTView();

	/**
	 * Stellt ein, das der Viewer im SWT-Modus (GUI=ON) NICHT verlassen werden soll.
	 */
	public void clearExitSWTView();

	/**
	 * @return <code>true</code>, wenn Viewer verlassen werden soll.
	 */
	public boolean isExitSWTView();


	/**
	 * 	VI Widgetinstanz auf basegrp anlegen"
	 * @param basegrp
	 * 			Basisgruppe (SWT-Group), auf der das Widget angelegt werden soll
	 * @return das neue Widget mit der grafischen Grundinformation.
	 */
	public Widget buildVIWidget(Group basegrp);
	/**
	 * TODO Comment
	 * @param base -
	 * @param scr -
	 * @return -
	 * @modified - 
	 */
	public IContent get_GuiContent(Widget base, Screen scr);
	
	/**
	 * Baut ein individuelles grafisches Central-Widget auf der SWT-Gruppe "basegrp"
	 * 
	 * @param basegrp
	 *            die SWT-Gruppe
	 * @return -
	 */
	public java.util.List<Widget> buildCWidget(Group basegrp);

	/**
	 * Dem individuellem Widget {@link #buildCWidget(Group)} eines Views wird der
	 * individuelle Content zugeordnet. Das erfolgt zwingend im View. Die Informationen
	 * liegen hinter der Fassade {@link Cntr} in der Screenfactory.
	 * 
	 * @see #buildCWidget(Group)
	 * @see Cntr
	 * @see AStorage
	 * @see AWidget
	 * @param map
	 *            das Widget des Views
	 * @param content
	 *            der Inhalt, der zgeordnet werden soll.
	 * 
	 */
	public void activateC_Widget(Map<String, Widget> map, IContent content);

	/**
	 * @return den individuellen Inhalt des CentralScreens des Views für
	 *         {@link GuiWidget}, (nur GUI)
	 */
//	public IContent getC_GuiContent();

	/**
	 * @return die Liste der Stateparameterbefehle
	 */
	public CommandList getParaCmdList();

	/**
	 * Einstellen des Viewscreens
	 * 
	 * @param menu -
	 * @param statecontext -
	 * @return der View
	 */
	public IView initiate(IMenu menu, IStateContext statecontext);

	/**
	 * @return die Liste der ViewCommands eines Viewers
	 */
	public CommandList getViewCmdList();

}
