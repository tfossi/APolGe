/**
 * 
 */
package tfossi.apolge.data.guide;

import static tfossi.apolge.common.constants.ConstValue.LOGGER;
import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import tfossi.apolge.common.scripting.vp.VP_Tokenlist;
/**
 * Basis für die Beschreibungsdaten der Regelwerke für Radio.<br>
 * Radiodaten sind NonDigit-Daten, die mehrere Object-Wertigkeiten annehmen
 * können und als Umschalt-Konstanten geeignet sind.<br>
 * Eine Steuerung der Wertigkeiten ist notwendig. Eine Regelung ist nicht
 * vorgesehen und entfällt daher.<br>
 *
 * @author tfossi
 * @version 26.01.2015
 * @modified -
 * @since Java 1.6
 */
public class GuideRadioData extends GuideData {
	//
	// /** Steller */
	// int uk = -1;

	/** Startwert Y0 und Werteliste */
	final List<Object> yk = new LinkedList<Object>();

	// ---- Selbstverwaltung --------------------------------------------------
	/** serialVersionUID */
	 private final static long serialVersionUID = VERSION;
	/** logger */
	private final static Logger logger = Logger.getLogger(GuideData.class
			.getPackage().getName() + "GuideData");

	/**
	 * Anlage Radiodaten für Guidelines.<br>
	 * Radiodaten sind NonDigit-Daten, die mehrere Object-Wertigkeiten annehmen
	 * können und daher als Umschalt-Konstanten geeignet sind.
	 * 
	 * @param dgl aktueller DGL	 
	 * @param parameter Parameter
	 * @modified -
	 */
	public GuideRadioData(final DataGuidelineLevel dgl, 
			final VP_Tokenlist[] parameter) {

		super(dgl, parameter, false, false, false);

		this.yk.add(parameter[SGD_Cntrl.STATEGROUPATTRIBUTES.INITIAL.ordinal()]
				.getValue());
	}

	/* (non-Javadoc)
	 * @see tfossi.apolge.data.guide.GuideData#toString()
	 */
	@Override
	public String toString() {
		return super.toString() + "Initial " + this.yk;
	}

	/**
	 * 
	 * STATE = { STATION = aktives Radio radio1 = initial radio2 = { initial
	 * -or- Initial = [initial] Name = [Langname] Activity = [Beschreibung] } }
	 * 
	 * @param dgl aktueller DGL	 
	 * @param sgg aktueller SGG
	 * @param statekey Schlüssel- oder Kurzname
	 * @param statedata Daten 
	 * @return anlage erfolgreich?
	 * @modified -
	 */
	static boolean create(final DataGuidelineLevel dgl,
			final StateGroupGuideline sgg, final String statekey,
			final VP_Tokenlist statedata) {

		if (LOGGER)
			logger.debug(statekey + " im " + dgl.getDatenklasse().toString()+": "+statedata);

		if (statekey.contains("."))
			return false;

		if (SGD_Cntrl.STATEIDLE.getAllCode().contains(statekey)) {
			switch (SGD_Cntrl.STATEIDLE.valueOf(statekey)) {
			case STATION:
				sgg.radioswitch = ((Integer) statedata.getValue())
						.intValue();
				return true;
			default:
				break;
			}
		}
		
		int idx = sgg.listOfStates.size();
		assert idx < 2;

		// initiale Parameter anlegen und vorbelegen		
		VP_Tokenlist[] parameter = GuideData.defaultParameter(sgg,
				sgg.statetype, idx, statekey);	

		// groupdata enthält eine Tabelle { ... }
		if (statedata != null && statedata.isTable()) {

			// Komplexe Beschreibung
			for (String aName : statedata.getTable().keySet()) {
				
				VP_Tokenlist attributes = (VP_Tokenlist) statedata.getTable()
						.get(aName);
				String a = aName.substring(aName.lastIndexOf(".") + 1);
				
				if (SGD_Cntrl.STATEIDLE.getAllCode().contains(a)) {
					switch (SGD_Cntrl.STATEIDLE.valueOf(a)) {
					case STATION:
						sgg.radioswitch = ((Integer) attributes.getValue())
								.intValue();
						assert false;
						continue;
					default:
						break;
					}
				}
				
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
		GuideRadioData build = new GuideRadioData(dgl, parameter);
		// State registrieren
		sgg.listOfStates.put(statekey.toUpperCase(), build);
		return true;
	}
}
