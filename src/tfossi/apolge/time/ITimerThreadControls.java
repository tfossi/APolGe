/**
 * ITimerThreadControls.java
 * Branch time
 * APolGe
 * tfossi-team
 * licence GPLv3  
 */
package tfossi.apolge.time;

/**
 * Alle öffentlichen Methoden zum Steuern des TimeThreads<br>
 * <ul>
 * <li>Starten des Timers</li>
 * <li>Pausieren des Timers</li>
 * <li>Restart des Timers nach einer Pause</li>
 * <li>Einstellen der Schrittweite des Timers</li>
 * </ul>
 * Der Timer triggert jede Sekunde den Schedular in
 * {@linkplain TimesController#schedular()}. Damit kann der Zeitraffer eingestellt
 * werden. Ist die Vorgabe der Schrittweite <code>setTimer(1)</code> bedeutet dies einen
 * Ablauf in Normalzeit.(Bei Console)
 *  
 * @author tfossi
 * @version 17.08.2014
 * @modified -
 * @since Java 1.6
 */
public interface ITimerThreadControls {
	/**
	 * Einstellen des gewünschten Zeitraffers in Stufen<br>
	 * 1 Sec, 5 Sec, 30 Sec,<br>
	 * 1 Min, 2 Min, 5 Min, 15 Min,<br>
	 * 1 Std, 2 Std, 4 Std, 8 Std, 12 Std, 24 Std
	 * 
	 * @param timestep
	 *            ist Schrittweite die eingestellt werden soll (Nur Console)
	 * */
	// public void timeslider(int timestep);

	/** Starte Timer. Der Aufruf erfolgt beim Initialiiseren des GameModels */
	// public abstract void timestart();

	/** Pausiere Timer */
	// public abstract void timepause();

	/** Timer wieder auf 1:1 stellen */
	// public abstract void timenormal();

	/** Beende Timer */
	// public abstract void timestop();
}