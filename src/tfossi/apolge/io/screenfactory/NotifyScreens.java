/**
 * NotifyScreens.java
 * Branch io
 * APolGe
 * tfossi-team
 * licence GPLv3  
 */

package tfossi.apolge.io.screenfactory;

import tfossi.apolge.io.Screen;




/**
 * TODO Comment
 *
 * @author tfossi
 * @version 12.08.2014
 * @modified -
 * @since Java 1.6
 */
public class NotifyScreens {
	/** scr */
	public Screen [] scr;
	/** eingabe */
	public boolean eingabe = true;
	/**
	 * TODO Comment
	 * @param scr -
	 * @modified -
	 */
	public NotifyScreens(Screen[] scr){
		this.scr = scr;
	}
	/**
	 * TODO Comment
	 * @param scr -
	 * @param eingabe -
	 * @modified -
	 */
	public NotifyScreens(Screen[] scr, boolean eingabe){
		this(scr);
		this.eingabe = eingabe;
	}
}
