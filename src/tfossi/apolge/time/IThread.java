/**
 * IThread.java
 * Branch time
 * APolGe
 * tfossi-team
 * licence GPLv3  
 */
package tfossi.apolge.time;
/**
 * Schnittstelle zum Timer
 *  
 *
 * @author tfossi
 * @version 17.08.2014
 * @modified -
 * @since Java 1.6
 */
public interface IThread {
	/**
	 * TODO Comment
	 * @param sleep -
	 * @modified - 
	 */
	public void setSleep(long sleep);
	/**
	 * Timerausführungen starten.
	 */
	public void start();
	/**
	 * Timerausführungen pausieren.
	 */
	public void pause();
	/** Timerausführungen nach Pause wieder starten */
	public void restart();
	/**
	 * TODO Comment
	 * @param millis -
	 * @modified - 
	 */
	void interruptafter(long millis);
	/**
	 * TODO Comment
	 * @modified - 
	 */
	void interrupt();
	/**
	 * TODO Comment
	 * @return -
	 * @modified - 
	 */
	boolean isAlive();
}
