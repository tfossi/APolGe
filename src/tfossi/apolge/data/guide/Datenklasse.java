/**
 * Datenklasse.java
 * XXX Branch klären
 * APolGe
 * tfossi-team
 * licence GPLv3  
 */
package tfossi.apolge.data.guide;

import static tfossi.apolge.common.constants.ConstValue.LOGGER;
import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;

import org.apache.log4j.Logger;

import tfossi.apolge.common.scripting.vp.VP_Tokenlist;

/**
 * Kennwerte Namen und Ebenes des DataGuideLevel-Elements
 * 
 * @author tfossi
 * @version 08.01.2015
 * @modified -
 * @since Java 1.6
 */
public class Datenklasse {
	/** Name des Levelelements  */
	private String levelelement;
	
	/**
	 * @return den Namen des Levelelements
	 * @modified - 
	 */
	public String getLevelelement() {
		if(LOGGER)logger.trace("Levelelement= "+this.levelelement);
		return this.levelelement;
	}
	/**
	 * @param le
	 * 		Eintrag des Names des Levelelements
	 * @modified - 
	 */
	public void setLevelelement(String le) {
		if(LOGGER)logger.trace("Levelelement= "+this.levelelement);
		this.levelelement = le;
	}	

	/** Ebene des Levelelements */
	private int level;
	/**
	 * @return die Ebene des Elements
	 * @modified - 
	 */
	public int getLevel() {
		if(LOGGER)logger.trace("Level= "+this.level);
		return this.level;
	}
	/**
	 * @param level
	 * 		Eintrag der Ebene des Levelelements
	 * @modified - 
	 */
	public void setLevel(int level) {
		if(LOGGER)logger.trace("Level= "+this.level);
		this.level = level;
	}
	
	/** Eindeutige Nummer in der Ebene */
	private int index;
	
	/**
	 * @param idx die eindeutige Nummer
	 * @modified - 
	 */
	public void setIDX(int idx) {
		this.index = idx;		
	}
	
	/**
	 * @return die eindeutige Nummer
	 * @modified - 
	 */
	public int getIDX() {
		return this.index;		
	}

	/** Anweisung zur initialen Einrichtung */
	private VP_Tokenlist initial = null;
	
	/**
	 * @param groupdata
	 * 			Tokenliste mit initialen Wertzuweisung
	 * @modified - 
	 */
	public final void setInitial(VP_Tokenlist groupdata) {
		this.initial = groupdata;		
	}
	/**
	 * @return Tokenliste mit initialen Wertzuweisung
	 * @modified - 
	 */
	public final VP_Tokenlist getInitial() {
		return this.initial;		
	}
	/**
	 * Konstruktor
	 * @modified -
	 */
	public Datenklasse(){		
		// leer
	}
	
	// ---- Selbstverwaltung --------------------------------------------------
	/** serialVersionUID */
	@SuppressWarnings("unused")
	private final static long serialVersionUID = VERSION;
	/** logger */
	private final static Logger logger = Logger.getLogger(Datenklasse.class
			.getPackage().getName() + ".Datenklasse");

	/**
	 * Ausgabe der Levelelementdaten dieser Datenklasse
	 */
	@Override
	public final String toString(){
		return "Levelelement: ["+this.levelelement+"("+this.index+")]  in Ebene ["+this.level+"])";
	}

}
