/**
 * 
 */
package tfossi.apolge.data.guide;

import static tfossi.apolge.common.constants.ConstValue.AKTIV;
import static tfossi.apolge.common.constants.ConstValue.LFCR;
import static tfossi.apolge.common.constants.ConstValue.LOGGER;
import static tfossi.apolge.common.constants.ConstValue.NTAB;
import static tfossi.apolge.common.constants.ConstValue.PASSIV;
import static tfossi.apolge.common.constants.ConstValue.TAB;
import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import tfossi.apolge.common.scripting.LoadScript;
import tfossi.apolge.common.scripting.LoadScriptException;
import tfossi.apolge.common.scripting.PreAddress;
import tfossi.apolge.common.scripting.ScriptException;
import tfossi.apolge.common.scripting.p.ParseException;
import tfossi.apolge.common.scripting.t.Table;
import tfossi.apolge.common.scripting.vp.VP_Tokenlist;
import tfossi.apolge.common.state.TriggerScript;
import tfossi.apolge.uefkt.BuildUEF;
import tfossi.apolge.uefkt.UEFT_Parameter;
import tfossi.apolge.uefkt.geber.BuildGeber;
import tfossi.apolge.uefkt.geber.GeberParameter;
import tfossi.apolge.uefkt.geber._Geber;

/**
 * Anlage Digitdaten für Guidelines.<br>
 * Digitdaten sind Daten, die diskrete-Wertigkeiten annehmen können und daher
 * Steuer- und Regelbar sind.<br>
 * @author tfossi
 * @version 26.01.2015
 * @modified -
 * @since Java 1.6
 */
public class GuideDigitData extends GuideData {

	/** parameter */
	private final VP_Tokenlist[] parameter;

	/** Startwert Y0 */
	final double y0;

	/** Minmaler Wert */
	final double minYValue;

	/** Maximaler Wert */
	final double maxYValue;

	/**
	 * Priorität des Vertex.
	 * <ul>
	 * <li>von 0 == unwichtig</li>
	 * <li>bis 100 = wichtig</li>
	 * </ul>
	 */
	final int priority;

	/** Sollwertgeber */
	final GeberParameter[] sollwertScript = new GeberParameter[2];

	/** Liefert den Sollwert des aktiven Vertex */
	final _Geber[] sollwertGeber = new _Geber[2];

	/** Parameter der Übertragungsfunktion der Strecke */
	UEFT_Parameter[] strecke = new UEFT_Parameter[2];

	/** Parameter der Übertragungsfunktion des Reglers (Active) */
	UEFT_Parameter[] regler = new UEFT_Parameter[2];

	/** Liste der nachrangig aufzurufende Vertex */
	final List<TriggerScript> triggerliste = new ArrayList<TriggerScript>();

	// ---- Selbstverwaltung --------------------------------------------------

	/** serialVersionUID */
	private final static long serialVersionUID = VERSION;

	/** logger */
	private final static Logger logger = Logger.getLogger(GuideData.class
			.getPackage().getName() + "GuideData");

	/**
	 * Anlage Digitdaten für Guidelines.<br>
	 * Digitdaten sind Daten, die diskrete-Wertigkeiten annehmen können und
	 * daher Steuer- und Regelbar sind.
	 * 
	 * @param dgl
	 *            aktueller DGL
	 * @param parameter
	 *            Liste der Parameter nach {@link SGD_Cntrl.STATEATTRIBUTES}
	 * @modified -
	 */
	public GuideDigitData(final DataGuidelineLevel dgl,
			final VP_Tokenlist[] parameter) {
		super(dgl, parameter, false, false, false);

		this.parameter = parameter;

		this.y0 = ((Double) parameter[SGD_Cntrl.STATEGROUPATTRIBUTES.INITIAL
				.ordinal()].getValue()).doubleValue();
		this.maxYValue = ((Double) parameter[SGD_Cntrl.STATEGROUPATTRIBUTES.MAXYVALUE
				.ordinal()].getValue()).doubleValue();

		this.minYValue = ((Double) parameter[SGD_Cntrl.STATEGROUPATTRIBUTES.MINYVALUE
				.ordinal()].getValue()).doubleValue();

		this.priority = ((Integer) parameter[SGD_Cntrl.STATEGROUPATTRIBUTES.PRIORITY
				.ordinal()].getValue()).intValue();

		_Geber[] geber = (_Geber[]) parameter[SGD_Cntrl.STATEGROUPATTRIBUTES.GEBER
				.ordinal()].getValue();

		this.sollwertGeber[AKTIV] = geber[AKTIV];
		this.sollwertGeber[PASSIV] = geber[PASSIV];
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tfossi.apolge.data.guide.GuideData#toString()
	 */
	@Override
	public String toString() {
		return super.toString()
				+ "Initial ["
				+ this.y0
				+ "] "
				+ NTAB
				+ TAB
				+ TAB
				+ "Y-Bereich ["
				+ this.minYValue
				+ "..."
				+ this.maxYValue
				+ "] "
				+ "Prio ["
				+ this.priority
				+ "] "
				+ (this.sollwertScript[AKTIV] != null ? NTAB + TAB + TAB
						+ "A-GeberScript: "
						+ this.sollwertScript[AKTIV].toString() : "")
				+ (this.sollwertScript[PASSIV] != null ? NTAB + TAB + TAB
						+ "P-GeberScript: "
						+ this.sollwertScript[PASSIV].toString() : "")
				+ (this.strecke[AKTIV] != null ? NTAB + TAB + TAB
						+ "A-Strecke: " + this.strecke[AKTIV].getKtoString()
						: "")
				+ (this.regler[AKTIV] != null ? NTAB + TAB + TAB + "A-Regler: "
						+ this.regler[AKTIV].getKtoString() : "")
				+ (this.strecke[PASSIV] != null ? NTAB + TAB + TAB
						+ "P-Strecke: " + this.strecke[PASSIV].getKtoString()
						: "")
				+ (this.regler[PASSIV] != null ? NTAB + TAB + TAB
						+ "P-Regler: " + this.regler[PASSIV].getKtoString()
						: "") + NTAB + TAB + TAB + "Trigger: "
				+ this.triggerliste.toString();
	}

	/**
	 * Initiale Anlage eines digitalen States.
	 * 
	 * @param dgl
	 *            aktuelle DGL
	 * @param sgg
	 *            aktuelle SGG
	 * @param statekey
	 *            Schlüssel- oder Kurzname des States
	 * @param statedata
	 *            Datensatz des States
	 * @return ist erfolgreich angelegt?
	 * @modified -
	 */
	static boolean create(final DataGuidelineLevel dgl,
			final StateGroupGuideline sgg, final String statekey,
			final VP_Tokenlist statedata) {

		if (LOGGER)
			logger.debug("[" + statekey + "] im "
					+ dgl.getDatenklasse().toString());

		if (statekey.contains("."))
			return false;

		// Aktueller Index
		int idx = sgg.listOfStates.size();

		if (SGD_Cntrl.STATEIDLE.getAllCode().contains(statekey)) {
			switch (SGD_Cntrl.STATEIDLE.valueOf(statekey)) {
			// Ruhezustand der Stategruppe
			case IDLE:
				// Sollte 0 sein, da Defaultaufruf, sonst explizit eingestellt
				// assert idx == 0;
				// idx = 0;
				break;
			// Terminauslöser zur Stateberechnung
			case TERMIN:
				return setTermin(sgg, statekey, statedata);
				// Zustandswertauslöser zur Stateberechnung
			case STATE:
				assert false;
				return false;
				// Zustandsberechnung eines anderen States als Auslöser zur
				// Stateberechnung
			case DOSTATE:
				assert false;
				return false;
			default:
				break;
			}
		}

		// initiale Parameter anlegen und vorbelegen
		VP_Tokenlist[] parameter = GuideData.defaultParameter(sgg,
				sgg.statetype, idx, statekey);

		// groupdata enthält eine Tabelle { ... }
		if (statedata != null && statedata.isTable()) {

			// Komplexe Beschreibung
			for (String attributFullCode : statedata.getTable().keySet()) {
				VP_Tokenlist attributes = (VP_Tokenlist) statedata.getTable()
						.get(attributFullCode);
				String attributCode = attributFullCode
						.substring(attributFullCode.lastIndexOf(".") + 1);
				if (attributes.isTable()) {
					// Tabelle
					GuideData.parameterzuordnung(parameter, attributCode,
							attributes);
				} else {
					// Einzelwert
					GuideData.parameterzuordnung(parameter, attributCode,
							attributes);
				}

			}
		} else if (statedata != null) {
			// Einzelwert als Parameter kann nur initial sein
			GuideData.parameterzuordnung(parameter, statekey, statedata);
		}

		// ... und State erzeugen
		GuideDigitData build = new GuideDigitData(dgl, parameter);
		// State registrieren
		sgg.listOfStates.put(statekey.toUpperCase(), build);

		return true;
	}

	/**
	 * Anlage eines Termintriggers zur Berechnung des digitalen States.
	 * @param sgg aktuelle SGG
	 * @param statekey Schlüssel- oder Kurzname des States
	 * @param statedata Datensatz des States
	 * @return ist erfolgreich angelegt?
	 * @modified - 
	 */
	final static boolean setTermin(final StateGroupGuideline sgg,
			final String statekey, final VP_Tokenlist statedata) {
		// groupdata enthält eine Tabelle { ... }
		if (statedata != null && statedata.isTable()) {
			// Komplexe Beschreibung
			for (String aName : statedata.getTable().keySet()) {
				String wann = (String) ((VP_Tokenlist) statedata.getTable()
						.get(aName)).getValue();
				sgg.igl_termine.add(wann);
			}
		} else if (statedata != null) {
			assert false;
		}
		return true;
	}

	// ---- Baue Sollwert-Geberscript -------------------------------------

	/**
	 * Bauen der Sollwertgeber.<br>
	 * Erstellen eines aktiven Geberbauplan. Entweder das Object oder null. Im
	 * letzten Fall wird ein Default-Geber eingesetzt
	 * 
	 * @param sgg
	 *            aktuelle Stategroup
	 * @param table
	 *            VP_Tokenlist-Tabelle
	 * @return ist erfolgreich?
	 * @modified -
	 */
	final static boolean buildDigitGuideGeber(final StateGroupGuideline sgg,
			final Table table) {

		// boolean isIdle = false;

		// Alle State-Attribute durchgehen
		for (String stateAttrFullName : table.keySet()) {
			VP_Tokenlist statedata = (VP_Tokenlist) table
					.get(stateAttrFullName);
			String stateAttrName = stateAttrFullName
					.substring(stateAttrFullName.lastIndexOf(".") + 1);

			if (SGD_Cntrl.STATEIDLE.getAllCode().contains(stateAttrName)) {
				switch (SGD_Cntrl.STATEIDLE.valueOf(stateAttrName)) {
				case TERMIN:
					continue;
				case IDLE:
					// isIdle = true;
					break;
				default:
					break;
				}
			}

			GuideDigitData stateAttrData = (GuideDigitData) sgg.listOfStates
					.get(stateAttrName.toUpperCase());

			// Zuerst eine Defaulteinstellung laden.
//			try {
//				stateAttrData.sollwertScript[AKTIV] = BuildGeber
//						.buildGeberScriptGuideline(
//								new LoadScript(
//										null,
//										null,
//										new String(
//												"SOLLA = { kp="
//														+ new Double(
//																4.
//																		* (stateAttrData.maxYValue - stateAttrData.minYValue)
//																		/ 5.
//																		+ stateAttrData.minYValue))
//												+ "}").valueParser(PMODEFLAT).scon
//										.get("SOLLA"),
//								SGD_Cntrl.STATEGROUPATTRIBUTES.SOLLA);
//				stateAttrData.sollwertScript[PASSIV] = BuildGeber
//						.buildGeberScriptGuideline(
//								new LoadScript(
//										null,
//										new String(
//												"SOLLP = { kp="
//														+ new Double(
//																1.
//																		* (stateAttrData.maxYValue - stateAttrData.minYValue)
//																		/ 5.
//																		+ stateAttrData.minYValue))
//												+ "}").valueParser(PMODEFLAT).scon
//										.get("SOLLP"),
//								SGD_Cntrl.STATEGROUPATTRIBUTES.SOLLP);
//			} catch (ScriptException | ParseException | LoadScriptException e) {
//				logger.fatal("Abbruch! Idle-SollA/P-Fehler." + LFCR
//						+ e.getMessage());
//				System.exit(0);
//			}

			// Alle AtrributElemente durchgehen
			for (String stateAttrElementFullName : statedata.getTable()
					.keySet()) {

				// AttributElementDaten
				VP_Tokenlist stateAttrElementData = (VP_Tokenlist) statedata
						.getTable().get(stateAttrElementFullName);

				// Kurzbezeichnung extrahieren
				String stateAttrElementName = stateAttrElementFullName
						.substring(
								stateAttrElementFullName.lastIndexOf(".") + 1)
						.toUpperCase();

				// SollA und SollP finden und zuordnen
				if (SGD_Cntrl.STATEGROUPATTRIBUTES.getAllCode().contains(
						stateAttrElementName)) {
					switch (SGD_Cntrl.STATEGROUPATTRIBUTES
							.valueOf(stateAttrElementName)) {
					case SOLLA:
//						stateAttrData.sollwertScript[AKTIV] = BuildGeber
//								.buildGeberScriptGuideline(
//										stateAttrElementData,
//										SGD_Cntrl.STATEGROUPATTRIBUTES.SOLLA);
						break;
					case SOLLP:
//						stateAttrData.sollwertScript[PASSIV] = BuildGeber
//								.buildGeberScriptGuideline(
//										stateAttrElementData,
//										SGD_Cntrl.STATEGROUPATTRIBUTES.SOLLP);
						break;
					default:
						break;
					}
				}
			}
		}
		return true;
	}

	// --- Übertragungsfunktion ------------------------------------------

	/**
	 * Übertragungsfunktionen der aktiven und passiven Strecke und Regler
	 * erstellen.<br>
	 * § Es gibt immer eine Übertragungsfunktion der aktiven Strecke: Default:
	 * P-Regler mit k=1.0<br>
	 * § Ist die passive Strecke = null, dann werden die Daten der aktiven
	 * Strecke genommen. <br>
	 * § Regler haben eine Übertragungsfunktion, sonst sind es nur Steller <br>
	 * § Ist der passive Regler = null, dann werden die Daten des aktiven
	 * Reglers genommen. <br>
	 * § Ist der aktive Regler = null, dann ist der passive Regler ebenfalls
	 * null (--> Steller)<br>
	 * 
	 * @param sgg
	 *            aktuelle Stategroup
	 * @param table
	 *            VP_Tokenlist-Tabelle
	 * @return ist erfolgreich?
	 * @modified -
	 */
	final static boolean buildDigitGuideUef(final StateGroupGuideline sgg,
			final Table table) {
		//
		// boolean isIdle = false;

		// Alle State-Attribute durchgehen
		for (String stateAttrFullName : table.keySet()) {

			// VP_Tokenlist statedata = (VP_Tokenlist) table
			// .get(stateAttrFullName);
			String stateAttrName = stateAttrFullName
					.substring(stateAttrFullName.lastIndexOf(".") + 1);

			if (SGD_Cntrl.STATEIDLE.getAllCode().contains(stateAttrName)) {
				switch (SGD_Cntrl.STATEIDLE.valueOf(stateAttrName)) {
				case TERMIN:
					continue;
				case IDLE:
					// isIdle = true;
					break;
				default:
					break;
				}
			}

			GuideDigitData data = (GuideDigitData) sgg.listOfStates
					.get(stateAttrName.toUpperCase());

			// Zuerst eine Defaulteinstellung laden.
//			try {
//				if (data.parameter[SGD_Cntrl.STATEGROUPATTRIBUTES.AS.ordinal()]
//						.getTable() == null)
//					data.strecke[AKTIV] = BuildUEF.analyseUefkt(new LoadScript(
//							null, new String("AS = { P" + LFCR + "Kp=1.}"))
//							.valueParser(PMODEFLAT).scon.get("AS"),
//							SGD_Cntrl.STATEGROUPATTRIBUTES.AS.name(), data);
//
//			} catch (ScriptException e) {
//				logger.fatal("Abbruch! Default UEFP-Fehler." + LFCR
//						+ e.getMessage());
//				System.exit(0);
//
//			}  catch (ParseException e) {
//				logger.fatal("Abbruch! Default UEFP-Fehler." + LFCR
//						+ e.getMessage());
//				System.exit(0);
//
//			} 	 catch (LoadScriptException e) {
//				logger.fatal("Abbruch! Default UEFP-Fehler." + LFCR
//						+ e.getMessage());
//				System.exit(0);
//								
//
//			} 		 catch ( NoSuchFieldException e) {
//				logger.fatal("Abbruch! Default UEFP-Fehler." + LFCR
//						+ e.getMessage());
//				System.exit(0);
//
//			} 			 catch (SecurityException e) {
//				logger.fatal("Abbruch! Default UEFP-Fehler." + LFCR
//						+ e.getMessage());
//				System.exit(0);
//			}

			// Logik:
			// § AS gibt es immer, notfalls als Default
			// § PS gibt es immer, notfalls von AS
			// § AR gibt es, dann gibt es auch PR, notfalls von AR
			// § AR gibt es nicht, dann gibt es auch kein PR

			try {
				data.strecke[AKTIV] = BuildUEF.analyseUefkt(
						data.parameter[SGD_Cntrl.STATEGROUPATTRIBUTES.AS
								.ordinal()], stateAttrFullName + "."
								+ SGD_Cntrl.STATEGROUPATTRIBUTES.AS.name(),
						data);

				// § PS gibt es immer, notfalls von AS
				if (data.parameter[SGD_Cntrl.STATEGROUPATTRIBUTES.PS.ordinal()]
						.getTable() == null)
					data.strecke[PASSIV] = data.strecke[AKTIV];
				else
					data.strecke[PASSIV] = BuildUEF.analyseUefkt(
							data.parameter[SGD_Cntrl.STATEGROUPATTRIBUTES.PS
									.ordinal()], stateAttrFullName + "."
									+ SGD_Cntrl.STATEGROUPATTRIBUTES.PS.name(),
							data);

				if (data.parameter[SGD_Cntrl.STATEGROUPATTRIBUTES.AR.ordinal()]
						.getTable() != null) {

					data.regler[AKTIV] = BuildUEF.analyseUefkt(
							data.parameter[SGD_Cntrl.STATEGROUPATTRIBUTES.AR
									.ordinal()], stateAttrFullName + "."
									+ SGD_Cntrl.STATEGROUPATTRIBUTES.AR.name(),
							data);

					// § AR gibt es, dann gibt es auch PR, notfalls von AR
					if (data.parameter[SGD_Cntrl.STATEGROUPATTRIBUTES.PR
							.ordinal()].getTable() == null)
						data.regler[PASSIV] = data.regler[AKTIV];
					else
						data.regler[PASSIV] = BuildUEF
								.analyseUefkt(
										data.parameter[SGD_Cntrl.STATEGROUPATTRIBUTES.PR
												.ordinal()],
										stateAttrFullName
												+ "."
												+ SGD_Cntrl.STATEGROUPATTRIBUTES.PR
														.name(), data);
				} else {
					// § AR gibt es nicht, dann gibt es auch kein PR
				}

			} catch (NoSuchFieldException e) {
				logger.fatal("Abbruch! UEFA/P-Fehler." + LFCR + e.getMessage());
				System.exit(0);
			} catch (SecurityException e) {
				logger.fatal("Abbruch! UEFA/P-Fehler." + LFCR + e.getMessage());
				System.exit(0);
			}
		}
		return true;
	}

	// --- Triggerfunktion ------------------------------------------------

	/**
	 * Zeittrigger zur Berechnung der States erstellen.<br>
	 * FIXME STATE und DOSTATE einrichten?
	 * 
	 * @param sgg
	 *            aktuelle Stategroup
	 * @param table
	 *            VP_Tokenlist-Tabelle
	 * @return ist erfolgreich?
	 * @modified -
	 */
	final static boolean buildDigitGuideTrigger(final StateGroupGuideline sgg,
			final Table table) {
		//
		// boolean isIdle = false;

		// Alle State-Attribute durchgehen
		for (String stateAttrFullName : table.keySet()) {

			// VP_Tokenlist statedata = (VP_Tokenlist)
			// table.get(stateAttrFullName);

			String stateAttrName = stateAttrFullName
					.substring(stateAttrFullName.lastIndexOf(".") + 1);

			if (SGD_Cntrl.STATEIDLE.getAllCode().contains(stateAttrName)) {
				switch (SGD_Cntrl.STATEIDLE.valueOf(stateAttrName)) {
				case TERMIN:
					continue;
				case IDLE:
					// isIdle = true;
					break;
				default:
					break;
				}
			}

			GuideDigitData data = (GuideDigitData) sgg.listOfStates
					.get(stateAttrName.toUpperCase());

			if (data.parameter[SGD_Cntrl.STATEGROUPATTRIBUTES.TRIGGER.ordinal()]
					.getTable() != null) {
				for (String t : data.parameter[SGD_Cntrl.STATEGROUPATTRIBUTES.TRIGGER
						.ordinal()].getTable().keySet()) {
					VP_Tokenlist triggerData = (VP_Tokenlist) data.parameter[SGD_Cntrl.STATEGROUPATTRIBUTES.TRIGGER
							.ordinal()].getTable().get(t);

					TriggerScript ts = new TriggerScript();
					try {
						ts.adr = new PreAddress((String) triggerData.getValue());
					} catch (ParseException e) {
						logger.fatal("Abbruch! TRIGGER-Address-Fehler." + LFCR
								+ e.getMessage());
						System.exit(0);
					}
					data.triggerliste.add(ts);
				}
			}
		}
		return true;
	}
}
