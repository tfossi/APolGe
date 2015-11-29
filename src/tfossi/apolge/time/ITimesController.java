/**
 * ITimeController.java
 * Branch time
 * APolGe
 * tfossi-team
 * licence GPLv3  
 */
package tfossi.apolge.time;

import java.util.List;

import tfossi.apolge.common.error.DatumsException;
import tfossi.apolge.events.IEvent;
import tfossi.apolge.report.IReport;
import tfossi.apolge.time.pit.CPiT;
import tfossi.apolge.time.pit.PPiT;

/**
 * Schnittstelle zu den Grundmethoden des TimesController
 * 
 *
 * @author tfossi
 * @version 17.08.2014
 * @modified -
 * @since Java 1.6
 */
public interface ITimesController // extends ITimerThreadControls
{
	

	/**
	 * Erzeugt einen neuen Termineintrag für den Schedular.  Event vom Typ link IEvent.Typen#} und fügt ihn in die Terminliste ein.
	 * 
	 * 
	 * @param reporter
	 * 				Bericht
	 * @param reportnumber
	 * 				Berichtsnummer (frei wählbar!)
	 * @param was
	 *            Name/Bezeichnung
	 * @param wann
	 *            ist der Termin, zudem das Event ausgeführt werden soll.
	 *            <ul>
	 *            <li>Form [TAG DD.MM.YYYY HH:MM:SS SHIFT].</li>
	 *            <li>Periodische Anweisungen sind mit [*] einzutragen.</li>
	 *            <li>[TAG] muss nicht gesetzt sein, dann gilt jeder Wochentag.</li>
	 *            <li>[SHIFT] wird nicht periodisiert.</li>
	 *            </ul>
	 * @param wie
	 *            Ausführende Klasse vom Typ IExecuteTermin
	 * @return das angelegte Event
	 * @throws DatumsException -
	 */
	public IEvent createTermin(final IReport reporter, final int reportnumber, final String was, 
			final PPiT wann /*String wann*/, final IExecuteTermin wie) throws DatumsException;

	/**
	 * Erzeugt einen neuen Event vom Typ link IEvent.Typen#} und fügt ihn in die Terminliste ein
	 * 
	 * 
	 * @param reporter
	 * 				Bericht
	 * @param reportnumber
	 * 				Berichtsnummer (frei wählbar!)
	 * @param was
	 *            Name/Bezeichnung
	 * @param wann
	 *            ist der Termin, zudem das Event ausgeführt werden soll.
	 *            <ul>
	 *            <li>Form [TAG DD.MM.YYYY HH:MM:SS SHIFT].</li>
	 *            <li>Periodische Anweisungen sind mit [*] einzutragen.</li>
	 *            <li>[TAG] muss nicht gesetzt sein, dann gilt jeder Wochentag.</li>
	 *            <li>[SHIFT] wird nicht periodisiert.</li>
	 *            </ul>
	 * @param bis
	 *            ist das Endetermin des (periodischen) Events in vollständiger Form
	  * @param wie
	 *            Ausführende Klasse vom Typ IExecuteTermin
	 * @return das angelegte Event
	 * @throws DatumsException -
	 */
	
	public IEvent createTermin(final IReport reporter, final int reportnumber, final String was,final PPiT wann /*String wann*/, final CPiT bis, final IExecuteTermin wie)
			throws DatumsException;
	/**
	 * TODO Comment
	 * @param counts -
	 * @param ext -
	 * @return -
	 * @modified - 
	 */
	public int setTestCounter(int counts, IExecuteTermin ext);
	/**
	 * Kann nur einmal gesetzt werden!
	 * @param wann
	 * 			Startdatum als  [DD.MM.YYYY HH:MM:SS]"
	 * @param report -
	 */
	public void setStartdate(String wann, IReport report);
	/**
	 * @param wann Endedatum als   [DD.MM.YYYY HH:MM:SS] oder <code>null</code> bei 'open end'
	 * @param report -
	 */
	public void setEnddate(String wann, IReport report);

	/** @return das Startdatum */
	public CPiT getStartdate();
	/**
	 * @return das Endedatum
	 */
	public CPiT getEnddate();

	/** @return das aktuelle Datum */
	public CPiT getActualdate();

	/** 
	 * @param step
	 * 			gewünschter maximale Zeitschritte der Termine.<br> 
	 * 			Bei 0L wird der maximal mögliche Step angenommen.
	 */
	public void setSollstep(long step);

	/**
	 * @param step
	 * 			Kalkulierter Timerstep ( <= Sollstep)
	 */
	public void setTimerstep(long step);
	
	/**
	 * @return Liste der etablierten Termine
	 */
	public List <String> showSchedular();

	/**
	 * @return Liste der neuen Termine (Kandidaten) 
	 */
	public List<String> showNeuerTermin();

	
	/**
	 * Die Löschaufträge anzeigen
	 * 
	 * @return Die Liste der Löschaufträge
	 */
	public List<String> showDeleteTermin();
	
	/**
	 * Set the Timerspeed.
	 * @param sleep
	 * 			the Timerspeed (default: 1s) 
	 */
	public void setSleep(long sleep);

	/** Starten des Timerthreads */
	public void start();

	/** Starten des Schedulars */
	public void schedularstart();

	/** Pause des Schedulars */
	public void schedularpause();

	/**
	 * Beenden des TimeThreads nach x Millisekunden.
	 * @param millis
	 * 			stop after x milliseconds or 0L for never
	 */
	void interruptafter(long millis);

}