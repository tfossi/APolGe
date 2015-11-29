/**
 * ITimes4Thread.java
 * Branch time
 * APolGe
 * tfossi-team
 * licence GPLv3  
 */
package tfossi.apolge.time;

import tfossi.apolge.events.IEvent;
import tfossi.apolge.time.pit.CPiT;

/**
 * Schnittstelle zu den Grundmethoden des TimesController
 *
 * @author tfossi
 * @version 17.08.2014
 * @modified -
 * @since Java 1.6
 */
public interface ITimes4Thread extends ITimerThreadControls {

	/**
	 * Arbeitet den Schedular ab:<br>
	 * <li>Legt den notwendigen Timestep fest, da der nächste Termine innerhalb des Timesteps
	 * liegen kann</li> <li>fügt neue Termine hinzu und</li> <li>
	 * löscht abgelaufene Termine</li>
	 */
	public void schedular();

	/**
	 * Wird vom Thread aufgerufen und berechnet und aktualisiert das Datum. <br>
	 */
	void addSekunden();

	/**
	 * Fügt einen neuen Termin in die Liste der CANDIDATES ein.<br>
	 * Standardmethode zum Anlegen von Terminen.
	 * 
	 * @param event
	 *            ist der Event, der anzulegen ist
	 */
	public void addTermin(IEvent event);

	 /**
	 * Event löschen
	 *
	 * @param event
	 * ist der Event der zu löschen ist
	 */
	 public void delTermin(IEvent event);

	/** @return das aktuelle Datum */
	public CPiT getActualdate();
}