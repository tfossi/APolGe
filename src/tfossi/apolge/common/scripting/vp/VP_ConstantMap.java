/**
 * VP_ConstantMap.java
 * branch master
 * APolGe
 * tfossi-team
 * licence GPLv3   
 */
package tfossi.apolge.common.scripting.vp;

import java.util.LinkedHashMap;

import tfossi.apolge.common.scripting.vp.pm.PatternMaps;

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
public class VP_ConstantMap extends LinkedHashMap<String, VP_Tokenlist<?>> implements IVP_ConstantMap {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see tfossi.apolge.common.scripting.vp.IVP_ConstantMap#to2String()
	 */
	@Override
	public String to2String(){
		String rc = new String(" ");
		for(String key: this.keySet())
			rc = ","+key+"="+this.get(key); //+ (this.get(key).isAddressesMarker()?" +ADR":"");
		return rc.substring(1);
	}
 //
}
