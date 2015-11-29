/**
 * AFactory.java
 * Branch io
 * APolGe
 * tfossi-team
 * licence GPLv3  
 */
package tfossi.apolge.io.screenfactory;


/**
 * Screenfactory.
 * 
 * @.pattern Abstract Factory: Abstract Factory
 * @see Cntr
 * @see ALoop
 * @see AStorage
 * @see AWidget
 * @author tfossi
 * @version 12.08.2014
 * @modified -
 * @since Java 1.6
 */
public abstract class AFactory {
	/**
	 * Programmsteuerung für Cnsl oder Gui
	 * Factoryprodukt
	 * 
	 * @param cntr
	 *            Fassade
	 * @return GuiLoop oder CnslLoop
	 */
	abstract ALoop loop(Cntr cntr);

	/**
	 * Screendatenspeicherung für Cnsl oder Gui
	 * Factoryprodukt
	 * 
	 * @return GuiStorage oder CnslStorage
	 */
	abstract AStorage storage();

	/**
	 * der GUI-Steuerung für Cnsl(==nope) oder Gui
	 * Factoryprodukt
	 * 
	 * @param cntr
	 *            Fassade
	 * @param swt -
	 * @return GuiWidget oder CnslWidget
	 */
	abstract AWidget widgets(Cntr cntr, SWTGrafik swt);
}
