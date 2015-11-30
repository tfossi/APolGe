/**
 * 
 */
package tfossi.apolge.data.guide;

import static tfossi.apolge.common.constants.ConstValue.LOGGER;
import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;

import org.apache.log4j.Logger;

import tfossi.apolge.common.scripting.vp.VP_ArrayTokenlist;
import tfossi.apolge.common.scripting.vp.VP_Tokenlist;

/**
 * Basis für die Beschreibungsdaten der Regelwerke für Constanten.<br>
 * Constantdaten sind NonDigit-Daten, die nur eine Object-Wertigkeit annehmen
 * können und als Konstanten geeignet sind. Bei möglichen Änderungen muss ein
 * neues Object angelegt werden.<br>
 * Eine Steuerung und Regelung ist nicht vorgesehen und entfällt daher.<br>
 */
public class GuideConstData extends GuideData {

	/** Startwert Y0 und einziger Wert */
	final Object y0;

	// ---- Selbstverwaltung --------------------------------------------------
	
	/** serialVersionUID */
	private final static long serialVersionUID = VERSION;
	/** logger */
	private final static Logger logger = Logger.getLogger(GuideData.class
			.getPackage().getName() + "GuideData");
	/**
	 * Anlage Constantdaten für Guidelines.<br>
	 * Constantdaten sind NonDigit-Daten, die nur eine Object-Wertigkeit
	 * annehmen können und daher als Konstanten geeignet sind.
	 * 
	 * @param dgl aktueller DGL
	 * @param parameter Parameterdatensatz
	 * @modified -
	 */
	public GuideConstData(final DataGuidelineLevel dgl,
			final VP_ArrayTokenlist<?>[] parameter) {
		super(dgl,parameter, false, false, false);
		
		this.y0 = 
				parameter[SGD_Cntrl.STATEGROUPATTRIBUTES.INITIAL
				           					.ordinal()].getValue();
	}
	
	/* (non-Javadoc)
	 * @see tfossi.apolge.data.guide.GuideData#toString()
	 */
	@Override
	public String toString() {
		return super.toString()+"Initial ["+this.y0+"] ";
	}
	/**
	 * Anlage eines GuideConstData aus den Tabellen.<br>
	 * Kein Idle! Notwendige Informationen BASIC
	 * 
	 * STATE = {
	 * 		const1 = initial
	 * 		const2 = {
	 * 				initial  -or-
	 * 				Initial = [initial] 
	 * 				Name = [Langname]
	 * 				Activity = [Beschreibung]
	 * 			}
	 * 		}
	 *
	 * @param dgl aktueller DGL
	 * @param sgg aktueller SGG
	 * @param statekey Schlüssel- oder Kurzname
	 * @param statedata Datensatz
	 * @return Anlage erfolgreich?
	 * @modified - 
	 */
	static boolean create(final DataGuidelineLevel dgl,
			final StateGroupGuideline sgg,
			final String statekey, final VP_Tokenlist statedata) {
		if (LOGGER)
			logger.debug(statekey + " im "
					+ dgl.getDatenklasse().toString());

		if (statekey.contains("."))
			return false;

			int idx = sgg.listOfStates.size();
			assert idx<2;

			// initiale Parameter anlegen und vorbelegen		
			VP_Tokenlist[] parameter = GuideData.defaultParameter(sgg,
					sgg.statetype, idx, statekey);	

			// groupdata enthält eine Tabelle { ... }
			if (statedata != null && statedata.isTable()) {
				
			// Komplexe Beschreibung
			for (String aName : statedata.getTable().keySet()) {
				
				VP_Tokenlist attributes = (VP_Tokenlist) statedata.getTable().get(
						aName);
				String a = aName.substring(aName.lastIndexOf(".") + 1);
				
				if (attributes.isTable()) {
					// Tabelle
					assert false;
				} else {
					GuideData.parameterzuordnung(parameter, a, attributes);					
				}
			}
		} else if(statedata!=null) {
			// Einzelwert als Parameter kann nur initial sein
			GuideData.parameterzuordnung(parameter, statekey, statedata);
		}
		
		// ... und State erzeugen
		GuideConstData build = new GuideConstData(dgl, (VP_ArrayTokenlist<?>[]) parameter);
		// State registrieren
		sgg.listOfStates.put(statekey.toUpperCase(), build);
		
		return true;
	}
	
}
