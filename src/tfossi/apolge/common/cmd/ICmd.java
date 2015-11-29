/** 
 * ICmd.java
 * Branch cmd
 * APolGe
 * tfossi-team
 * licence GPLv3 
 */
package tfossi.apolge.common.cmd;

import java.util.List;
import java.util.Queue;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Widget;

import tfossi.apolge.common.hci.AMenu;
import tfossi.apolge.common.hci.menu.IReceiver;
import tfossi.apolge.common.macrorecorder.IRecorder;
import tfossi.apolge.io.console.Key;
import tfossi.apolge.io.screenfactory.IBuildSWTWidget;

/**
 * Schnittstelle für die Ausführung von Commands.<br>
 * <li>Das ist die Befehlsvorbereitung selber - die Ausführung findet beim Receiver
 * (XMenu) statt,</li>
 * <li>Die Receivereinstellung</li>
 * <li>Die Parametersteuerung</li>
 * <li>und die SWT-Widgetsteuerung</li><br>
 * 
 * @see ACmd
 * @author tfossi
 * @version 11.08.2014
 * @modified -
 * @since Java 1.6
 */
public interface ICmd extends IBuildSWTWidget
{
	
	/**
	 * Menüpunkt ausgrauen
	 */
	public void setGrayed();
	/**
	 * Menüpunkt unsichtbar
	 * @return -
	 */
	public boolean isUnvisible();
		
	/**
	 * Ausführung des Befehls. Damit wird unabhängig vom Befehl immer diese Methode
	 * aufgerufen. Diese Methode leitet den Aufruf dann an die eigentliche ausführende
	 * Methode des <i>Receivers</i> in {@link AMenu} weiter. Der Aufrufer ist damit
	 * völlig unabhängig davon, wie die Ansteuerung des Befehls funktioniert. Damit
	 * lassen sich auch Macros, Undos (Memento-Pattern) und anderes einfach
	 * implementieren.
	 * 
	 * @.pre der Receiver ist definiert
	 * @.pre die Parameterqueue ist definiert
	 * @.inv der Receiver besitzt eine Methode für diesen Befehl
	 * @see AMenu
	 */
	public void command();
		
	/**
	 * Hilfe anzeigen
	 */
	public void help();
	
	/**
	 * Testet, ob es ein bekannter Parameter ist
	 * @param parameter
	 * 		der zu testende Parameter	 
	 * @return
	 * 		der Parameter oder <code>null</code>
	 */
	public String testParameter(String parameter);

	/**
	 * Liefert die Maximalzahl der Argumente bei Parameter
	 * @param parameter
	 * 			der Parameter 
	 * @return die maximale Anzahl der Argumente
	 */
	public int maxParameter(String parameter);

	/**
	 * Liefert die Minimalzahl der Argumente bei Parameter
	 * @param parameter
	 * 			der Parameter 
	 * @return die minimale Anzahl der Argumente
	 */
	public int minParameter(String parameter);
	
	/**
	 * Aufruf des Receivers mit Parameter und dessen Argumenten
	 * @param parameter
	 * 			der Parameter 		
	 * @param value
	 * 			die Argumente
	 */
	public void call(String parameter, String ...value);
	
	/**
	 * Im SWT werden die Parameter eines Befehls eingeblendet!
	 * @param e -
	 */
	public void showParameterCommand(SelectionEvent e);

	/**
	 * Erstellt und liefert die Widgetinstanz, die zum Command gehört
	 * 
	 * @param grp
	 *            ist die Gruppe, in der der Widget liegen soll
	 * @return Widgetinstanz des Commands
	 */
	@Override
	public List<Widget> buildSWTWidget(Group grp);

	/**
	 * Speichern des Befehlsempfängers
	 * 
	 * @param menu
	 *            ist der Befehlsempfänger Menu
	 * @param grayedArray -
	 * @see AMenu
	 */
	public void setReceiver(final IReceiver menu, final String[] grayedArray);

	/**
	 * Liefert den gespeicherten Befehlsempfänger/ Menu
	 * 
	 * @return der Befehlsempfänger
	 * @see AMenu
	 */
	public IReceiver getReceiver();

	// ---- Parameter ------------------------------------------------------------

	/**
	 * Trage einen Parameterwert in Queue ein.
	 * 
	 * @param parameterIn
	 *            ist der Parameterwert, der eingetragen werden soll. Vor dem Parameterwert wird ein '-' eingetragen.
	 * @.post Der Parameterwert ist eingetragen
	 * @.inv Die Queue ist definiert und nicht null.
	 */
	public void setParameter(final String parameterIn); 
	
	/**
	 * Liefert die Parameterqueue. Jeder Parameter wird ein der Reihenfolge des
	 * Abspeicherns wieder aus dem Speicher ausgelesen FIFO-Speicher.
	 * 
	 * @return den Parameterqueue
	 */
	public Queue<String> getParameter();

	/** Alle Parameter löschen */
	public void clearParameter();
	
	// ---- Key ------------------------------------------------------------------
	
	/**
	 * Liefert den Stringcode des Befehls. Dies ist notwendig beim Abspielen von Records,
	 * da bei {@link Key#eingabe(CommandList, String)}, wo der Record abgespielt wird,
	 * ein String als Rückgabewert notwendig ist. Der Record selber hat keine
	 * Informationen über den Receiver. Es handelt sich um einen Befehlstemplate
	 * 
	 * @return der Stringcode des Befehls
	 * @see Key
	 * @see IRecorder
	 */
	public String getCode();
	
	
	/**
	 * TODO Comment
	 * @return -
	 * @modified - 
	 */
	public ICmd clone();

	/**
	 * TODO Comment
	 * @param data -
	 * @modified - 
	 */
	public void setData(Object data);
	/**
	 * TODO Comment
	 * @return -
	 * @modified - 
	 */
	public Object getData();
	/**
	 * TODO Comment
	 * @param grp -
	 * @modified - 
	 */
	public void /* List<Widget> */buildParameterWidgets(
			final Group grp);
}
