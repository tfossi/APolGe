/** 
 * CommandHash.java
 * Branch cmd
 * APolGe
 * tfossi-team
 * licence GPLv3 
 */
package tfossi.apolge.common.cmd;

import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;

import java.util.HashMap;


/**
 * Erweitert das {@link java.util.HashMap} für die Commands mit dem festem Typen
 * <code>HashMap &lt;String, ACmd&gt;</code>
 * 
 * @see java.util.HashMap
 * @author tfossi
 * @version 13.08.2014
 * @modified -
 * @since Java 1.6
 */
public class CommandHash extends HashMap<String, ACmd> implements CommandMap {

	// ---- Selbstverwaltung -----------------------------------------------------
	/** serialVersionUID */
	private final static long serialVersionUID = VERSION;

	// Kein Logger(!)

	/** Liefert einen typisierten HashMap &lt;String, ACmd&gt; */
	public CommandHash() {
		super();
	}
}
