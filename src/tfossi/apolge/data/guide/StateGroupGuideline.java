/**
 * 
 */
package tfossi.apolge.data.guide;

import static tfossi.apolge.common.constants.ConstValue.LFCR;
import static tfossi.apolge.common.constants.ConstValue.LOGGER;
import static tfossi.apolge.common.constants.ConstValue.LOGTAB;
import static tfossi.apolge.common.constants.ConstValue.TAB;
import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import tfossi.apolge.common.scripting.PreAddress;
import tfossi.apolge.common.scripting.t.Table;
import tfossi.apolge.common.scripting.vp.VP_ArrayTokenlist;
import tfossi.apolge.common.scripting.vp.VP_Tokenlist;
import tfossi.apolge.common.state.TriggerScript;
import tfossi.apolge.data.guide.SGD_Cntrl.STATEGROUPATTRIBUTES;
//import tfossi.apolge.common.state.TriggerScript;

/**
 * FIXME DOKU und Name GuideStageGroupData sgg Enthält eine Gruppe von
 * Regelwerken aller States eines DataGuidelineLevels<br>
 * 
 * <ul>
 * <li>IGuideline</li>
 * <li>ContextGuideline</li>
 * <li>CTransitionGuideline</li>
 * <li>IGL-Termine</li>
 * <li>VTG-Termine</li>
 * <li>CTG-Termine</li>
 * </ul>
 * 
 *
 * @author tfossi
 * @version 26.01.2015
 * @modified -
 * @since Java 1.6
 */
public class StateGroupGuideline extends GuideData {

	// ---- States in dieser Gruppe ------------------------------------------


	/** listOfStates */
	final Map<String, GuideData> listOfStates = new LinkedHashMap<String, GuideData>();

	// ---- Radioschalter

	/** radioswitch */
	int radioswitch = 0;

	/**
	 * Termine, zu denen die States neu berechnet werden.
	 */
	final List<String> igl_termine = new ArrayList<String>();

	// ---- Context -----------------------------------------------------------

	/**
	 * Die Regelwerke der Contexte
	 */
	final Map<String, GuideData> listOfContexts = new LinkedHashMap<String, GuideData>();

	/**
	 * Termine, zu denen die Contexte-Transitions neu berechnet werden.
	 */
	final List<String> contextTermine = new ArrayList<String>();

	/**
	 * Statetrigger, zu denen die Context-Transitions neu berechnet werden.
	 */
	final List<PreAddress> contextDoStateTrigger = new ArrayList<PreAddress>();

	/** Liste der nachrangig aufzurufende Vertex */
	final List<TriggerScript> contextStateTrigger = new ArrayList<TriggerScript>();

	// ---- Transition --------------------------------------------------------

	/**
	 * Termine, zu denen die State-Transitions neu berechnet werden.
	 */
	final List<String> transitionTermine = new ArrayList<String>();

	/**
	 * Statetrigger, zu denen die State-Transitions neu berechnet werden.
	 */
	final List<PreAddress> transitionDoStateTrigger = new ArrayList<PreAddress>();
	
	/** Liste der nachrangig aufzurufende Vertex */
	final List<TriggerScript> transitionStateTrigger = new ArrayList<TriggerScript>();

	// ---- Selbstverwaltung --------------------------------------------------

	/** serialVersionUID */
	private final static long serialVersionUID = VERSION;
	
	/** logger */
	private final static Logger logger = Logger
			.getLogger(StateGroupGuideline.class.getPackage().getName()
					+ ".StateGroupGuideline");

	/**
	 * Erzeugt eine neue Stategruppe mit allen Einträgen.<br>
	 * § Stategruppen sind zusammengehörige States mit konkurierenden Verhalten.
	 * Nur ein State zur Zeit kann aktiv sein.<br>
	 * § (N.I.) Ausnahme bilden die Konstantenstates, die ihren Zustand nicht
	 * ändern.<br>
	 * Erzeuge alle Contexte, Transitions (V und C) , Vertices (V, R und C)
	 *
	 * @param dgl Aktueller DGL
	 * @param tokenlist 
	 * 			ArrayListe der Parameter aus {@link STATEGROUPATTRIBUTES}
	 * @modified -
	 */
	public StateGroupGuideline(final DataGuidelineLevel dgl,
			final VP_Tokenlist[] tokenlist) {
		super(dgl, tokenlist, true, false, false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public final String toString() {		
		String context = LFCR + TAB + TAB + "Contexttermine : ";
		
		for (String wann : this.contextTermine) {
			context += wann + "  ";
		}

		context += LFCR + TAB + TAB + "ContextStates  : ";
		
		for (TriggerScript wann : this.contextStateTrigger) {
			context += wann.adr + "  ";
		}
		
		context += LFCR + TAB + TAB + "ContextDoStates: ";
		
		for (PreAddress wer : this.contextDoStateTrigger) {			
			context += wer.toString() + "  ";
		}

		for (String contextName : this.listOfContexts.keySet()) {
			context += LFCR + TAB + this.listOfContexts.get(contextName).toString();
		}
		
		String transition = LFCR + TAB + TAB + "Transitiontermine : ";
		for (String wann : this.transitionTermine) {
			transition += wann + "  ";
		}
		
		transition += LFCR + TAB + TAB + "TransitionStates  : ";
		
		for (TriggerScript wann : this.transitionStateTrigger) {
			transition += wann.adr + "  ";
		}
		
		transition += LFCR + TAB + TAB + "TransitionDoStates: ";
		
		for (PreAddress wer : this.transitionDoStateTrigger) {
			transition += wer.toString() + "  ";
		}
		
		String statesTermine = LFCR + TAB + TAB + "Statetermine: ";

		for (String wann : this.igl_termine) {			
			statesTermine += wann + "  ";
		}
		
		String states = "";
		for (String stateName : this.listOfStates.keySet()) {
			states += LFCR + TAB + this.listOfStates.get(stateName).toString();
		}

		return super.toString()
				+ (this.statetype == SGD_Cntrl.STATETYPES.RADIO ? "Switch ["
						+ this.radioswitch + "]" : "") + statesTermine

				+ states +transition + context; 
	}

	/**
	 * Baue den Guideline für {@link StateGroupGuideline}. Die Ergebnisse werden direkt in die DGL gepusht!
	 *
	 * @param bgd
	 * 			 ist der Builder. Verweis auf aufrufende {@link BuildGuideData} für den Zugriff auf die Scriptdaten
	 * @param rootDGL
	 * 			oberste DGL-Element
	 * @modified - 
	 */
	final static void build(final BuildGuideData bgd,
			final DataGuidelineLevel rootDGL) {

		// Baut die DatenHierarchie ausgehend von der Root-Clazz
		logger.info("- BUILD: StategroupGuideline SGG -");

		// Liste der Datenstruktur
		List<DataGuidelineLevel> listOfDatastructure = rootDGL.getAll();

		for (DataGuidelineLevel dgl : listOfDatastructure) {

			// Index der DGL für den Zugriff auf die Scripte
			int idx = dgl.getDatenklasse().getIDX();

			// Daten der Einträge
			VP_Tokenlist groupdata = null;

//			// § Der Einlesemodus für die StateGroupsGuideline ist PMODEFLAT!
//			// Erst DGL-Steuerdaten herausholen. Hole die Initiale Wertzuweisung.
//			// § Mit INTIAL wird die Anzahl der zu erzeugenden CoreDaten angegeben!
//			// INITIAL = 5 ==> DGL wird 5 mal angelegt. 
//			if (bgd.getListenScripts()[idx].scon.containsKey("INITIAL")) {
//				groupdata = bgd.getListenScripts()[idx].scon.get("INITIAL");
//				bgd.getListenScripts()[idx].scon.remove("INITIAL");
//
//			} else if (bgd.getListenScripts()[idx].svar.containsKey("INITIAL")) {
//				bgd.getListenScripts()[idx].svar.get("INITIAL");
//				bgd.getListenScripts()[idx].svar.remove("INITIAL");
//
//			} else if (bgd.getListenScripts()[idx].indi.containsKey("INITIAL")) {
//				groupdata = bgd.getListenScripts()[idx].indi.get("INITIAL");
//				bgd.getListenScripts()[idx].indi.remove("INITIAL");
//
//			} else {
//				groupdata = new VP_ArrayTokenlist(new Integer(1));
//			}

			// Anzahl der Objekte dieses DGL-Elements
			dgl.getDatenklasse().setInitial(groupdata);

			// Alle Stategroups durchgehen.
//			for (String groupname : bgd.getListenScripts()[idx].scon.keySet()) {
//				// Nur Tables, keine Untereinträge(inhaltlich Parameter), wie
//				// .\?0, .Aktivity ....
//				if (groupname.contains("."))
//					continue;
//
//				// Die zur Variable groupname gehörenden Werte ...
//				groupdata = bgd.getListenScripts()[idx].scon.get(groupname);//
//
//				// § Ist keine Gruppe festgelegt, ist eine weitere Bearbeitung
//				// sinnlos
//				if (groupdata == null || groupdata.isEmpty()) {
//					logger.fatal("Eine vollständige sgl_"
//							+ dgl.getDatenklasse().getLevelelement()
//							+ ".apo Datei fehlt!");
//					System.exit(-1);
//				}
//				if (LOGGER)
//					logger.debug(groupname + " im "
//							+ dgl.getDatenklasse().toString());
//				
//				// Baue Stategroup
//				StateGroupGuideline.create(dgl, groupname, groupdata);
//
//			}
		}

		logger.info("- BUILD: StategroupGuideline SGG - ready");
	}

	/**
	 * Erstelle Daten der Stategroup aus Script
	 * 
	 * @param dgl
	 *            aktueller DataGuidelineLevel
	 * @param groupname
	 *            Name der Stategruppe
	 * @param groupdata
	 *            Liste der Token
	 * @return Anlage erfolgreich?            
	 */
	private final static boolean create(final DataGuidelineLevel dgl,
			final String groupname, final VP_Tokenlist groupdata) {

		// Nur Tables, keine Untereinträge(inhaltlich Parameter), wie
		// .\?0, .Aktivity ....
		if (groupname.contains("."))
			return false;

		String activity = "Auto-Generated " + groupname;

		 // Parameter anlegen und vorbelegen
		VP_Tokenlist[] parameter = new VP_Tokenlist[SGD_Cntrl.STATEGROUPATTRIBUTES
				.values().length];

		// Eintragsnummer im DGL ermitteln und eintragen
		parameter[SGD_Cntrl.STATEGROUPATTRIBUTES.IDX.ordinal()] = new VP_ArrayTokenlist(
				new Integer(dgl.numberOfStageGroupGuidelines()));

		// Gruppenkurznamen eintragen
		parameter[SGD_Cntrl.STATEGROUPATTRIBUTES.KEY.ordinal()] = new VP_ArrayTokenlist(
				groupname);
		
		// Gruppen(lang-)namen eintragen
		parameter[SGD_Cntrl.STATEGROUPATTRIBUTES.NAME.ordinal()] = new VP_ArrayTokenlist(
				groupname);

		parameter[SGD_Cntrl.STATEGROUPATTRIBUTES.ZERO.ordinal()] = new VP_ArrayTokenlist(
				"SOMETHING");
		
		// Gruppenbeschreibung eintragen
		parameter[SGD_Cntrl.STATEGROUPATTRIBUTES.ACTIVITY.ordinal()] = new VP_ArrayTokenlist(
				activity);

		// Gruppendatentyp eintragen
		parameter[SGD_Cntrl.STATEGROUPATTRIBUTES.TYPE.ordinal()] = new VP_ArrayTokenlist(
				null);

		// groupdata enthält eine Tabelle { ... }
		if (groupdata.isTable()) {

			Table grouptable = groupdata.getTable();

			// Alle Tabelleneinträge durchgehen und zuordnen
			for (String tableFullentry : grouptable.keySet()) {
				String tableEntry = tableFullentry
						.substring(tableFullentry.lastIndexOf(".") + 1);
				if (LOGGER)
					logger.trace("Check: " + tableEntry + "= "
							+ grouptable.get(tableFullentry));

				// Ist Keyword in der Liste aller Codes
				if (SGD_Cntrl.STATEGROUPATTRIBUTES.getAllCode().contains(
						tableEntry.toUpperCase())) {
					int index = SGD_Cntrl.STATEGROUPATTRIBUTES.getAllCode()
							.indexOf(tableEntry.toUpperCase());
					if (LOGGER)
						logger.trace("FOUND: " + tableEntry + LOGTAB + "Pos: " + index);

					// Ermittel den Keyindex und trage dies im Parameterarray ein.
					if (index == SGD_Cntrl.STATEGROUPATTRIBUTES.ZERO.ordinal()) {
						// Sonderfall Listenelement. Hier: TYPE
						String v = ((VP_Tokenlist) grouptable.get(tableFullentry))
								.getValue().toString().toUpperCase();

						if (SGD_Cntrl.STATETYPES.getAllCode().contains(v)) {
							index = SGD_Cntrl.STATEGROUPATTRIBUTES.TYPE
									.ordinal();
							parameter[index] = new VP_ArrayTokenlist(
									SGD_Cntrl.STATETYPES.valueOf(v));
						} else
							assert false : v;
					} else {
						// ... eintragen
						parameter[index] = (VP_Tokenlist) grouptable
								.get(tableFullentry);
					}
				} else
					// Keyword existiert nicht
					assert false : SGD_Cntrl.STATEGROUPATTRIBUTES.getAllCode();
			}
		} else {
			// Einzelwert als Parameter kann nur Stateart sein
			String groupvalue = ((String) groupdata.getValue()).toUpperCase();

			// Gibt es den Einzelwert als Parameterschlüssel?
			if (SGD_Cntrl.STATETYPES.getAllCode().contains(groupvalue)) {
				parameter[SGD_Cntrl.STATEGROUPATTRIBUTES.TYPE.ordinal()] = new VP_ArrayTokenlist(
						SGD_Cntrl.STATETYPES.valueOf(groupvalue));
			} else
				assert false;
		}

		// ... und Stategroup erzeugen
		StateGroupGuideline build = new StateGroupGuideline(dgl, parameter);
		// StateGroup registrieren
		dgl.addStageGroupGuideline(build);

		return true;
	}
}
