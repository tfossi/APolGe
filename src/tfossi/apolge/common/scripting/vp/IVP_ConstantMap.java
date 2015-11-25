/**
 * IVP_ConstantMap.java
 * branch master
 * APolGe
 * tfossi-team
 * licence GPLv3   
 */
package tfossi.apolge.common.scripting.vp;

import java.util.Map;

/**
 * Speichert alle Konstanten aus ConstValue und Sonderfällen !...
 * 
 * @see PatternMaps
 * 
 * @author tfossi
 * @version 19.08.2014
 * @modified -
 * @since Java 1.6
 */
public interface IVP_ConstantMap extends Map<String, VP_Tokenlist<?>> {
	/**
	 * @return Liefert eine Liste der Einträge
	 * @modified -
	 */
	public String to2String();
}
