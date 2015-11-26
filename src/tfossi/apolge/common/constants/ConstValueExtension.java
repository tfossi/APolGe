/**
 * ConstValueExtension.java
 * Branch master
 * APolGe
 * tfossi-team
 * licence GPLv3  
 */
package tfossi.apolge.common.constants;

import tfossi.apolge.common.scripting.vp.pm.PatternMaps;

/**
 * Konstanten, die aus dem Script 'config' eingelesen werden. 
 *
 */
public class ConstValueExtension extends ConstValue {
	/** Konfigurationsscript */
//	public final static LoadScript CONFIG_SCRIPT;
	public static PatternMaps CONFIG_SCRIPT = null;

	

	// ---- Version -----------------------------------------------------------
	/**
	 * Enthält die Versionsnummer von APolGe in 1000er Schritten. Wird genutzt,
	 * damit sich die <code>serialVersionUID</code> nur in den Versionsnummer
	 * x.xxx ändert
	 * ACHTUNG: <br>
	 * Zum Scripttesten mit Loadscript ist eine fixe VERSIONS-Nummer zu verwenden!
	 */ 
	public final static long VERSION = -1L;
	// ---- Zeitsteuerung: Time-Shifts ----------------------------------------

}
