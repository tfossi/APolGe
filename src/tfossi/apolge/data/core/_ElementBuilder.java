/**
 * _ElementBuilder.java
 * branch master
 * APolGe
 * tfossi-team
 * licence GPLv3   
 */
package tfossi.apolge.data.core;

import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;

import org.apache.log4j.Logger;

import tfossi.apolge.common.scripting.t.Table;

/**
 * Enthält einen Elelemtbauplan und erstellt Elemente
 * FIXME Testvariante für Parsertests
 * 
 * @author tfossi
 * @version 29.10.2015
 * @modified -
 * @since Java 1.6
 */
public class _ElementBuilder {
	
//	/** firstPassMap */
//	private final Map<String, VP_Tokenlist<?>> firstPassMap = new HashMap<String,VP_Tokenlist<?>>();

	/** attributes */
	private Table attributes = null;
	/**
	 * @param block attributeblock des Elements
	 * @modified - 
	 */
	public void addEigenschaften(Table block) {
		this.attributes = block;
		
	}
	
	/**
	 * Bestandteile des Elements
	 *
	 * @author tfossi
	 * @version 26.11.2015
	 * @modified -
	 * @since Java 1.6
	 */
	enum scpt {
		/** ELEMENT */
		ELEMENT, /** EIGENSCHAFT */
		EIGENSCHAFT, /** DATENTYP */
		DATENTYP, /** INITIALDATEN */
		INITIALDATEN, /** INITIALPARAMETER */
		INITIALPARAMETER;

	}
	
//	/**
//	 * Füge eine 0-Pass-Eigenschaft hinzu
//	 * @param key
//	 * 			Name der Eigenschaft
//	 * @param valuetokens
//	 * 			Wert der Eigenschaft
//	 * @modified - 
//	 */
//	public void addEigenschaften(final String key, final VP_Tokenlist<?> valuetokens){
//		if(LOGGER)
//			logger.info("Register "+key);
//		this.firstPassMap.put(key, valuetokens);
//
//	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
//		String rc = new String();
//		
//		for(String key :this.firstPassMap.keySet())
//			rc += key+": "+this.firstPassMap.get(key)+LFCR;
//		return rc;
//		return this.firstPassMap.toString();
		return this.attributes.toString();
	}
	
	// ---- Selbstverwaltung --------------------------------------------------
	/** serialVersionUID */
	@SuppressWarnings("unused")
	private final static long serialVersionUID = VERSION;

	/** logger */
	@SuppressWarnings("unused")
	private final static Logger logger = Logger.getLogger(_ElementBuilder.class
			.getPackage().getName() + "._ElementBuilder");




}


