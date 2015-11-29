/**
 * 
 */
package tfossi.apolge.common.state.guard;


import tfossi.apolge.data.core.ICoreData;
import tfossi.apolge.data.guide.IGuideData;

/**
 * Interface für alles Guards<br>
 * Die Guards überwachen, ob eine Transition/ContextSwitch erlaubt ist.
 * 
 *
 * @author tfossi
 * @version 26.01.2015
 * @modified -
 * @since Java 1.6
 */
public interface _Guard {
	/**
	 * Test, ob Transition passiv werden/bleiben darf (=true) oder aktiv werden/bleiben soll(=false).
	 * 
	 * @param index
	 *            der individuelle Indexwert
	 * @param yk ???
	 * @return true, wenn Transition erlaubt ist
	 */
	public boolean tstGuard4Chg2Passiv(final Object index, double [] yk);
	/**
	 * Test, ob Transition in passiv (==true) oder in aktiv (==false)
	 * <br>
	 * TODO Vergleiche direkt ok<br>
	 * TODO Vergleiche mit Superdata direkt<br>
	 * TODO Verleiche mit gleicher Ebene ?<br>
	 * TODO Verleiche mit Supradata????<br>
	 * @param data ???
	 * @return ???
	 */
	public boolean tstGuard4Chg2Passiv(final ICoreData data);
//	/**
//	 * @return den eingestellten Schwellwert
//	 */
//	public int getTreshhold();


	/**
	 * @return die Source- (<code>Pre<\code>) oder Target- (<code>Post<\code>) Vertex
	 */
	public IGuideData getVertex();

	/**
	 * @return die Klasse des Vergleichstypen
	 */
	public Class<?> getGuardType();

}
