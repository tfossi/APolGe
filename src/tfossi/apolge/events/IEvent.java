/**
 * IEvent.java
 * Branch master
 * APolGe
 * tfossi-team
 * licence GPLv3  
 */
package tfossi.apolge.events;
import java.io.Serializable;

import tfossi.apolge.time.IExecuteTermin;
import tfossi.apolge.time.pit.CPiT;

/**
 * Schnittstelle für ein beliebiges Ereignis, das zu einem bestimmten Zeitpunkt einmal
 * oder wiederkehrend ausgelöst werden soll. Gespeichert wird das Ereignis im Schedular
 * von DataTime.
 * 
 * @author tfossi
 * @version 17.08.2014
 * @modified -
 * @since Java 1.6
 */
public interface IEvent extends Cloneable,Serializable {

	/** @return Name des Events */
	public String getName();
	/** @param name Name des Events */
	public void setName(final String name);

	/** @return Zeitpunkt der Auslösung. */
	public CPiT getPiT();

	/**
	 * Einstellen des Zeitpunkts der Auslösung
	 * 
	 * @param pit
	 *            das Datum
	 */
	public void setPiT(final CPiT pit);
	
	/**
	 * Methode, die zum Zeitpunkt der Auslösung durch den Schedular ausgeführt wird.
	 */
	public void termin();
	
	/**
	 * TODO Comment
	 * @return - 
	 * @modified - 
	 */
	public IExecuteTermin getExT();
}
