/**
 * 
 */
package tfossi.apolge.data.guide;

import static tfossi.apolge.common.constants.ConstValue.LOGGER;
import static tfossi.apolge.common.constants.ConstValue.TAB;
import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import tfossi.apolge.common.scripting.vp.VP_ArrayTokenlist;
import tfossi.apolge.common.scripting.vp.VP_Tokenlist;
import tfossi.apolge.common.state.TriggerScript;
import tfossi.apolge.data.guide.SGD_Cntrl.STATETYPES;
import tfossi.apolge.uefkt.UEFT_Parameter;
import tfossi.apolge.uefkt.geber.GeberParameterHashMap;
import tfossi.apolge.uefkt.geber._Geber;

/**
 * Basisklasse für Guidedaten
 * 
 * @author tfossi
 * @version 22.01.2015
 * @modified -
 * @since Java 1.6
 */
abstract public class GuideData implements Cloneable, Serializable, IGuideData {

	// ---- Übergeordneter DataGuidelineLevel ---------------------------------

	/** dgl */
	protected final DataGuidelineLevel dgl;

	/**
	 * @return the dgl
	 */
	public final DataGuidelineLevel getDgl() {
		return this.dgl;
	}

	/** Index */
	protected final int idx;

	/**
	 * @return the idx
	 */
	public final int getIdx() {
		return this.idx;
	}

	/** Schlüssel- und Kurzname */
	protected final String key;

	/**
	 * @return Schlüssel- und Kurzname
	 * @modified -
	 */
	public final String getKey() {
		return this.key;
	}

	/** (Lang-)Name */
	protected final String name;

	/**
	 * @return (Lang-)Name
	 * @modified -
	 */
	public final String getName() {
		return this.name;
	}

	/** Anzeigename */
	protected final String activity;

	/**
	 * @return Anzeigename
	 */
	public final String getActivity() {
		return this.activity;
	}

	/** Datentyp CONST, RADIO, DIGIT */
	protected final SGD_Cntrl.STATETYPES statetype;

	/**
	 * @return Datentyp CONST, RADIO, DIGIT
	 */
	public final SGD_Cntrl.STATETYPES getStatetype() {
		return this.statetype;
	}

	/** isStateGroup */
	protected final boolean isStateGroup;

	/**
	 * @return the isStateGroup
	 */
	public final boolean isStateGroup() {
		return this.isStateGroup;
	}

	/** isContext */
	protected final boolean isContext;

	/** Controller: Object des übergeordneten Elements */
	protected final GuideData parent;

	/**
	 * @return the parent
	 */
	public final GuideData getParent() {
		return this.parent;
	}

	// ---- Selbstverwaltung --------------------------------------------------

	/** serialVersionUID */
	private final static long serialVersionUID = VERSION;

	/** logger */
	private final static Logger logger = Logger.getLogger(GuideData.class
			.getPackage().getName() + "GuideData");

	/**
	 * Definiert dgl, idx, name, activity, parent, statetype, isStateGroup
	 * 
	 * @param dgl
	 *            aktueller DGL
	 * @param parameter
	 *            Array mit den Parametern aus
	 *            {@link SGD_Cntrl.STATEGROUPATTRIBUTES}
	 * @param isStateGroup
	 *            ist Stategroup
	 * @param isContext
	 *            ist Context
	 * @param isTransGuard
	 *            ist TransGuard
	 * @modified -
	 */
	public GuideData(final DataGuidelineLevel dgl,
			final VP_Tokenlist[] parameter, final boolean isStateGroup,
			final boolean isContext, final boolean isTransGuard) {
		this.dgl = dgl;

		// Indexnummer
		this.idx = ((Integer) parameter[SGD_Cntrl.STATEGROUPATTRIBUTES.IDX
				.ordinal()].getValue()).intValue();

		// Schlüssel- oder Kurzname
		this.key = (String) parameter[SGD_Cntrl.STATEGROUPATTRIBUTES.KEY
				.ordinal()].getValue();

		// (Lang-)name
		this.name = (String) parameter[SGD_Cntrl.STATEGROUPATTRIBUTES.NAME
				.ordinal()].getValue();

		// Beschreibung
		this.activity = (String) parameter[SGD_Cntrl.STATEGROUPATTRIBUTES.ACTIVITY
				.ordinal()].getValue();

		// Parentelement. Kann auch null sein!
		if (parameter[SGD_Cntrl.STATEGROUPATTRIBUTES.PARENT.ordinal()] != null)
			this.parent = (GuideData) parameter[SGD_Cntrl.STATEGROUPATTRIBUTES.PARENT
					.ordinal()].getValue();
		else
			this.parent = null;

		// Datentype
		if (parameter[SGD_Cntrl.STATEGROUPATTRIBUTES.TYPE.ordinal()] != null)
			this.statetype = (STATETYPES) parameter[SGD_Cntrl.STATEGROUPATTRIBUTES.TYPE
					.ordinal()].getValue();
		else
			this.statetype = null;

		// Kennzeichen Stategroup
		this.isStateGroup = isStateGroup;

		// Kenzeichen Context
		this.isContext = isContext;
	}

	/**
	 * Ausgabe dieser Regelwerkdaten.
	 */
	@Override
	public String toString() {
		String rc = new String();

		rc = (this.isStateGroup ? "Group [" : (this.isContext ? TAB
				+ "Context [" : TAB + "State ["))
				+ this.key + "] ";
		rc += "Name [" + this.name + "] ";
		rc += "Aktivity [" + this.activity + "] ";
		rc += "IDX [" + this.idx + "] ";
		rc += this.statetype != null ? "TYP [" + this.statetype.name() + "] "
				: "";
		if (this.parent != null)
			rc += "Group [" + this.parent.key + "] ";
		return rc;
	}

	/**
	 * Baue den Guideline für {@link GuideData}
	 * 
	 * @param bgd
	 *            ist der Builder
	 * @param rootDGL
	 *            oberstes DGL-Element
	 * @modified -
	 */
	static void build(final BuildGuideData bgd, final DataGuidelineLevel rootDGL) {

		// Baut die DatenHierarchie ausgehend von der Root-Clazz
		logger.info("- BUILD: States -");

		// Liste der Datenstruktur
		List<DataGuidelineLevel> listOfDatastructure = rootDGL.getAll();

		// Alle DGL durchgehen
		for (DataGuidelineLevel dgl : listOfDatastructure) {

			// Alle Stategroups eines DGL durchgehen
			for (StateGroupGuideline sgg : dgl.getAllStateGroupValues()) {

				// Zuerst den IDLE in Default-Version anlegen!
//				if (sgg.statetype == SGD_Cntrl.STATETYPES.DIGIT) {
//					GuideDigitData.create(dgl, sgg, "IDLE", null);
//				} else if (sgg.statetype == SGD_Cntrl.STATETYPES.RADIO)
//					GuideRadioData.create(dgl, sgg, "IDLE", null);
				

				
				// Jetzt alle definierten States anlegen.
//				for (String stategroupentry : bgd.getDetailScripts()[dgl
//						.getDatenklasse().getIDX()][sgg.idx].scon.keySet()) {
//					// Nur Tables, keine Untereinträge(inhaltlich Parameter),
//					// wie .\?0, .Aktivity ....
//					if (stategroupentry.contains("."))
//						continue;
//					
//					// Zuerst die STATES!
//					if (SGD_Cntrl.getAllCode().contains(
//							stategroupentry.toUpperCase())) {
//						switch (SGD_Cntrl.valueOf(stategroupentry.toUpperCase())) {
//						case STATE:
//							break;
//						default:							
//							continue;
//						}
//					} else{
//						continue;
//					}
//					// Die zur Variable groupname gehörenden Werte ...
//					VP_Tokenlist allStateData = bgd.getDetailScripts()[dgl
//							.getDatenklasse().getIDX()][sgg.idx].scon
//							.get(stategroupentry);
//
//					if (LOGGER)
//						logger.debug("["+stategroupentry + "] mit allen Einträgen: "
//								+ allStateData.getTable().keySet());
//					
//					// Alle Einträge des States durchgehen
//					for (String stateFullName : allStateData.getTable().keySet()) {
//
//						String statename = stateFullName.substring(stateFullName
//								.lastIndexOf(".") + 1);
//
//						// Nicht die Cntrls nehmen 
//						if (SGD_Cntrl.CONTEXTIDLE.getAllCode().contains(
//								statename.toUpperCase())) {
//							continue;
//						}
//						
//						// ... sondern die Stateparameter.
//						VP_Tokenlist statedata = (VP_Tokenlist) allStateData
//								.getTable().get(stateFullName);
//						
//						if (LOGGER)
//							logger.debug("["+statename + "] Stateparameter: "+ statedata.getTable().keySet());
//
//						// GuideData typrichtig anlegen.
//						switch (sgg.statetype) {
//						case CONST:
//							GuideConstData.create(dgl, sgg, statename,
//									statedata);
//							continue;
//						case RADIO:
//							GuideRadioData.create(dgl, sgg, statename,
//									statedata);
//							continue;
//						case DIGIT:
//							GuideDigitData.create(dgl, sgg, statename,
//									statedata);
//							continue;
//						default:
//							assert false;
//						}
//					}
//					
//					// Die Systembeschreibungen der DIGIT einlesen 
//					if (sgg.statetype == SGD_Cntrl.STATETYPES.DIGIT) {
//
//						// Die Sollwertgeber 
//						GuideDigitData.buildDigitGuideGeber(sgg,
//								allStateData.getTable());
//
//						// Die Übertragungsfunnkitonen des Systems
//						GuideDigitData.buildDigitGuideUef(sgg,
//								allStateData.getTable());
//
//						// Die Auslöser zur Berechnung
//						GuideDigitData.buildDigitGuideTrigger(sgg,
//								allStateData.getTable());
//
//					}
//				}

				if (LOGGER)
					logger.debug("CONTEXTE ");
				
//				// ... dann die Contexte anlegen
//				for (String stateentry : bgd.getDetailScripts()[dgl
//						.getDatenklasse().getIDX()][sgg.idx].scon.keySet()) {
//					if (SGD_Cntrl.getAllCode().contains(
//							stateentry.toUpperCase())) {
//						switch (SGD_Cntrl.valueOf(stateentry.toUpperCase())) {
//						case CONTEXT:
//							break;
//						default:
//							continue;
//						}
//					} else
//						continue;
//					
//					// Die zur Variable groupname gehörenden Werte ...
//					VP_Tokenlist groupdata = bgd.getDetailScripts()[dgl
//							.getDatenklasse().getIDX()][sgg.idx].scon
//							.get(stateentry);				
//
//
//					// Weitere Umschaltungen nur bei Digit
//					if (sgg.statetype == SGD_Cntrl.STATETYPES.DIGIT) {
////						// Zuerst den IDLE in Default-Version anlegen!						
////						ContextGuideline.create(dgl, sgg, "IDLE", null);
//						
//						// Anlage Contexte
//						for (String contextAttrFullName : groupdata.getTable().keySet()) {
//							VP_Tokenlist contextAttrData = (VP_Tokenlist) groupdata
//									.getTable().get(contextAttrFullName);
//
//							String contextAttrName = contextAttrFullName.substring(contextAttrFullName
//									.lastIndexOf(".") + 1);
//
//							// Erzeuge Contexte
//							if (!SGD_Cntrl.CONTEXTIDLE.getAllCode().contains(
//									contextAttrName)) {
//								ContextGuideline.create(dgl, sgg, contextAttrName,
//										contextAttrData);
//							}
//						}
//
//						// Anlage Context TERMIN, STATE, DOSTATE;
//						for (String contextAttrFullName : groupdata.getTable().keySet()) {
//							VP_Tokenlist contextAttrData = (VP_Tokenlist) groupdata
//									.getTable().get(contextAttrFullName);
//
//							String contextAttrName = contextAttrFullName.substring(contextAttrFullName
//									.lastIndexOf(".") + 1);
//							
//							if (SGD_Cntrl.CONTEXTIDLE.getAllCode().contains(
//									contextAttrName)) {
//								switch (SGD_Cntrl.CONTEXTIDLE
//										.valueOf(contextAttrName)) {
//								case STATE:
//									ContextGuideline.setState(sgg, contextAttrName,
//											contextAttrData);
//									break;
//								case DOSTATE:
//									ContextGuideline.setDoState(sgg, contextAttrName,
//											contextAttrData);
//									break;
//								case TERMIN:
//									ContextGuideline.setTermin(sgg, contextAttrName,
//											contextAttrData);
//									break;
//								default:
//									break;
//								}
//
//							}
//						}
//						
//						// Default-Context-Transitionen anlegen
//						ContextGuideline.buildContextDefaultTransGuide(sgg);
//
//
//						// Anlage Transition TERMIN, STATE, DOSTATE;
//						ContextGuideline.buildTransCntrlsGuide(sgg,
//								groupdata);
//
//					}
//				}
//				if (LOGGER)
//					logger.debug("TRANSITION ");
//				
//				// ... dann die Transitions anlegen
//				for (String stateentry : bgd.getDetailScripts()[dgl
//						.getDatenklasse().getIDX()][sgg.idx].scon.keySet()) {
//					if (SGD_Cntrl.getAllCode().contains(
//							stateentry.toUpperCase())) {
//						switch (SGD_Cntrl.valueOf(stateentry.toUpperCase())) {
//						case TRANSITION:
//							break;
//						default:
//							continue;
//						}
//					} else
//						continue;
//					
//					// Die zur Variable groupname gehörenden Werte ...
//					VP_Tokenlist groupdata = bgd.getDetailScripts()[dgl
//							.getDatenklasse().getIDX()][sgg.idx].scon
//							.get(stateentry);				
//
//
//					// Weitere Umschaltungen nur bei Digit
//					if (sgg.statetype == SGD_Cntrl.STATETYPES.DIGIT) {
////						// Zuerst den IDLE in Default-Version anlegen!						
////						ContextGuideline.create(dgl, sgg, "IDLE", null);		// Anlage der Statetransitions
//						ContextGuideline.buildTransitionGuide(sgg, groupdata);
//					}
//				}
			}
		}
		logger.info("- BUILD: States - ready");
	}

	/**
	 * Erzeugung von Defaultparametersatz für GuideData-Erzeugung
	 * 
	 * @param parent
	 *            übergeordnetes Element
	 * @param type
	 *            Datentype CONST, RADIO oder DIGIT
	 * @param idx
	 *            Indexnummer des Elements
	 * @param key
	 *            Schlüssel- oder Kurzname des Elements
	 * @return die Defaultparameter
	 * @modified -
	 */
	final static VP_Tokenlist[] defaultParameter(final GuideData parent,
			final SGD_Cntrl.STATETYPES type, final int idx, final String key) {

		// Parameter anlegen und vorbelegen
		VP_Tokenlist[] parameter = new VP_Tokenlist[SGD_Cntrl.STATEGROUPATTRIBUTES
				.values().length];

		parameter[SGD_Cntrl.STATEGROUPATTRIBUTES.ACTIVITY.ordinal()] = new VP_ArrayTokenlist(
				"Autogenerated " + key);

		parameter[SGD_Cntrl.STATEGROUPATTRIBUTES.GEBER.ordinal()] = new VP_ArrayTokenlist(
				new _Geber[2]);

		parameter[SGD_Cntrl.STATEGROUPATTRIBUTES.SOLLA.ordinal()] = new VP_ArrayTokenlist(
				new GeberParameterHashMap());

		parameter[SGD_Cntrl.STATEGROUPATTRIBUTES.SOLLP.ordinal()] = new VP_ArrayTokenlist(
				new GeberParameterHashMap());

		parameter[SGD_Cntrl.STATEGROUPATTRIBUTES.IDX.ordinal()] = new VP_ArrayTokenlist(
				new Integer(idx));

		parameter[SGD_Cntrl.STATEGROUPATTRIBUTES.INITIAL.ordinal()] = new VP_ArrayTokenlist(
				new Double(5.));

		parameter[SGD_Cntrl.STATEGROUPATTRIBUTES.KEY.ordinal()] = new VP_ArrayTokenlist(
				key.toUpperCase());

		parameter[SGD_Cntrl.STATEGROUPATTRIBUTES.MAXYVALUE.ordinal()] = new VP_ArrayTokenlist(
				new Double(5.));

		parameter[SGD_Cntrl.STATEGROUPATTRIBUTES.MINYVALUE.ordinal()] = new VP_ArrayTokenlist(
				new Double(-5.));

		parameter[SGD_Cntrl.STATEGROUPATTRIBUTES.NAME.ordinal()] = new VP_ArrayTokenlist(
				key);

		parameter[SGD_Cntrl.STATEGROUPATTRIBUTES.PARENT.ordinal()] = new VP_ArrayTokenlist(
				parent);

		parameter[SGD_Cntrl.STATEGROUPATTRIBUTES.PRIORITY.ordinal()] = new VP_ArrayTokenlist(
				new Integer(0));

		parameter[SGD_Cntrl.STATEGROUPATTRIBUTES.AS.ordinal()] = new VP_ArrayTokenlist(
				new UEFT_Parameter());

		parameter[SGD_Cntrl.STATEGROUPATTRIBUTES.AR.ordinal()] = new VP_ArrayTokenlist(
				new UEFT_Parameter());

		parameter[SGD_Cntrl.STATEGROUPATTRIBUTES.PS.ordinal()] = new VP_ArrayTokenlist(
				new UEFT_Parameter());

		parameter[SGD_Cntrl.STATEGROUPATTRIBUTES.PR.ordinal()] = new VP_ArrayTokenlist(
				new UEFT_Parameter());

		parameter[SGD_Cntrl.STATEGROUPATTRIBUTES.TRIGGER.ordinal()] = new VP_ArrayTokenlist(
				new ArrayList<TriggerScript>());

		parameter[SGD_Cntrl.STATEGROUPATTRIBUTES.TYPE.ordinal()] = new VP_ArrayTokenlist(
				type);

		parameter[SGD_Cntrl.STATEGROUPATTRIBUTES.VALUES.ordinal()] = new VP_ArrayTokenlist(
				new LinkedList<Object>());

		parameter[SGD_Cntrl.STATEGROUPATTRIBUTES.ZERO.ordinal()] = new VP_ArrayTokenlist(
				new Double(-5.));

		return parameter;
	}

	/**
	 * Zuordnung der Scriptwerte zu den Parametern. 
	 * 
	 * @param parameter
	 *            Parameterliste
	 * @param attributCode
	 *            Code des Parameterattributes
	 * @param attributData
	 *            Daten des Parameterattributes
	 * @modified -
	 */
	final static void parameterzuordnung(final VP_Tokenlist[] parameter,
			final String attributCode, final VP_Tokenlist attributData) {

		// Große Buchstaben sicherstellen
		String attributCODE = attributCode.toUpperCase();

		// Einzelwerte
		if (SGD_Cntrl.STATEGROUPATTRIBUTES.getAllCode().contains(attributCODE)) {
			switch (SGD_Cntrl.STATEGROUPATTRIBUTES.valueOf(attributCODE)) {
			case ACTIVITY:
				parameter[SGD_Cntrl.STATEGROUPATTRIBUTES.ACTIVITY.ordinal()] = new VP_ArrayTokenlist(
						attributData.getValue());
				break;
			case INITIAL:
				parameter[SGD_Cntrl.STATEGROUPATTRIBUTES.INITIAL.ordinal()] = new VP_ArrayTokenlist(
						attributData.getValue());
				break;
			case NAME:
				parameter[SGD_Cntrl.STATEGROUPATTRIBUTES.NAME.ordinal()] = new VP_ArrayTokenlist(
						attributData.getValue());
				break;
			case ZERO:
				parameter[SGD_Cntrl.STATEGROUPATTRIBUTES.INITIAL.ordinal()] = new VP_ArrayTokenlist(
						attributData.getValue());
				break;
			case VALUES:
				assert false;
				break;
			case GD:
				parameter[SGD_Cntrl.STATEGROUPATTRIBUTES.GD.ordinal()] =
				new VP_ArrayTokenlist(attributData.getValue());				
				break;
			case GEBER:
				assert false;
				break;
			case SOLLA:
				break;
			case SOLLP:
				break;
			case IDX:
				assert false;
				break;
			case KEY:
				assert false;
				break;
			case MAXYVALUE:
				assert false;
				break;
			case MINYVALUE:
				assert false;
				break;
			case PARENT:
				assert false;
				break;
			case PRIORITY:
				assert false;
				break;
			case AS:
				if (attributData.isTable())
					parameter[SGD_Cntrl.STATEGROUPATTRIBUTES.AS.ordinal()] = new VP_ArrayTokenlist(
							attributData.getTable());
				else
					parameter[SGD_Cntrl.STATEGROUPATTRIBUTES.AS.ordinal()] = new VP_ArrayTokenlist(
							attributData.getValue());
				break;
			case AR:
				if (attributData.isTable())
					parameter[SGD_Cntrl.STATEGROUPATTRIBUTES.AR.ordinal()] = new VP_ArrayTokenlist(
							attributData.getTable());
				else
					parameter[SGD_Cntrl.STATEGROUPATTRIBUTES.AR.ordinal()] = new VP_ArrayTokenlist(
							attributData.getValue());
				break;
			case PS:
				if (attributData.isTable())
					parameter[SGD_Cntrl.STATEGROUPATTRIBUTES.PS.ordinal()] = new VP_ArrayTokenlist(
							attributData.getTable());
				else
					parameter[SGD_Cntrl.STATEGROUPATTRIBUTES.PS.ordinal()] = new VP_ArrayTokenlist(
							attributData.getValue());
				break;
			case PR:
				if (attributData.isTable())
					parameter[SGD_Cntrl.STATEGROUPATTRIBUTES.PR.ordinal()] = new VP_ArrayTokenlist(
							attributData.getTable());
				else
					parameter[SGD_Cntrl.STATEGROUPATTRIBUTES.PR.ordinal()] = new VP_ArrayTokenlist(
							attributData.getValue());
				break;
			case TRIGGER:
				parameter[SGD_Cntrl.STATEGROUPATTRIBUTES.TRIGGER.ordinal()] = new VP_ArrayTokenlist(
						attributData.getValue());
				break;
			case TYPE:
				assert false;
				break;
			default:
				assert false;
				break;
			}
		} else {
			// Bei einfacher Zuordnung ist die Beziehung [attributCode] ist Name
			// und [attributData] sind initiale Daten
			parameter[SGD_Cntrl.STATEGROUPATTRIBUTES.INITIAL.ordinal()] = new VP_ArrayTokenlist(
					attributData.getValue());
			parameter[SGD_Cntrl.STATEGROUPATTRIBUTES.NAME.ordinal()] = new VP_ArrayTokenlist(
					attributCode);
		}
	}
	
	/**
	 * Erzeugung von Defaultparametersatz für Context-GuideData-Erzeugung
	 * 
	 * @param parent
	 *            übergeordnetes Element
	 * @param idx
	 *            Indexnummer des Elements
	 * @param key
	 *            Schlüssel- oder Kurzname des Elements
	 * @return die Defaultparameter
	 * @modified -
	 */
	final static VP_Tokenlist[] defaultContextParameter(final GuideData parent,
			final int idx, final String key) {


		// // Parameter anlegen und vorbelegen
		VP_Tokenlist[] parameter = new VP_Tokenlist[SGD_Cntrl.STATEGROUPATTRIBUTES
				.values().length];

		parameter[SGD_Cntrl.STATEGROUPATTRIBUTES.ACTIVITY.ordinal()] = new VP_ArrayTokenlist(
				"Autogenerated " + key);
		
		parameter[SGD_Cntrl.STATEGROUPATTRIBUTES.IDX.ordinal()] = new VP_ArrayTokenlist(
				new Integer(idx));

		parameter[SGD_Cntrl.STATEGROUPATTRIBUTES.KEY.ordinal()] = new VP_ArrayTokenlist(
				key.toUpperCase());

		parameter[SGD_Cntrl.STATEGROUPATTRIBUTES.NAME.ordinal()] = new VP_ArrayTokenlist(
				key);

		parameter[SGD_Cntrl.STATEGROUPATTRIBUTES.PARENT.ordinal()] = new VP_ArrayTokenlist(
				parent);
		
		parameter[SGD_Cntrl.STATEGROUPATTRIBUTES.GD.ordinal()] = new VP_ArrayTokenlist(
				null);

		return parameter;
	}
}
