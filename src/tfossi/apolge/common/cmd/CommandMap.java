/** 
 * CommandMap.java
 * Branch cmd
 * APolGe
 * tfossi-team
 * licence GPLv3 
 */
package tfossi.apolge.common.cmd;

import java.util.Map;


/**
 * Markerinterface für {@link CommandHash} und Erweiterung von {@link java.util.Map} für
 * die Commands mit dem festem Typen <code>Map &lt;String, ACmd&gt;</code>
 * 
 * @see CommandHash
 * @see java.util.Map
 * @author tfossi
 * @version 13.08.2014
 * @modified -
 * @since Java 1.6
 */
public interface CommandMap extends Map<String, ACmd> {
	// Markerinterface für CommandHash mit festem Typen
}
