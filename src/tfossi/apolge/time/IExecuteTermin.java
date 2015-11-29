/**
 * TimeController.java
 * Branch time
 * APolGe
 * tfossi-team
 * licence GPLv3  
 */
package tfossi.apolge.time;

import java.util.Queue;

/**
 * Schnittstelle für die Ausführung (Execution) von Ereignissen (Events), die vom Schdeular bei Eintritt des Zeitpunkts (TargetDate) ausgeführt werden.
 *
 * @author tfossi
 * @version 17.08.2014
 * @modified -
 * @since Java 1.6
 */
public interface IExecuteTermin {
	/**
	 * Methode wird von dem Event aufgerufen, wenn der Termin ereicht ist.
	 * Liefert in einem Queue Ergebnisse zurück.	 
	 * @return
	 * 			<code>null</code>, wenn keine Ergebnisse geliefert werden, sonst eine spezifische Queue 
	 */
	public Queue<Object> execute();
//	public Queue<?> execute(ExecuteInitiator ei);
}
