/**
 * 
 */
package tfossi.apolge.data.guide;

import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;


import org.apache.log4j.Logger;

import tfossi.apolge.common.state.guard._Guard;
import tfossi.apolge.data.core.DataRoot;
//import tfossi.apolge.data.DataGuidelineLevel;
//import tfossi.apolge.common.state.guard.GuardList;
//import tfossi.apolge.common.state.guard._Guard;
//import tfossi.apolge.data.DataGuidelineLevel;
//import tfossi.apolge.data.core.DataRoot;
//import tfossi.apolge.data.guide.CTransitionGuideline;
//import tfossi.apolge.data.guide.ContextGuideline;
//import tfossi.apolge.data.guide.GDC;
//import tfossi.apolge.data.guide.GTC;
//import tfossi.apolge.data.guide.GuideConstData;
//import tfossi.apolge.data.guide.GuideData;
//import tfossi.apolge.data.guide.GuideDigitData;
//import tfossi.apolge.data.guide.GuideRadioData;
//import tfossi.apolge.data.guide.StateGroupGuideline;
//import tfossi.apolge.data.guide.VTransitionGuideline;

/**
 * MetaStatemodell beinhaltet die Beschreibung aller Zustände (<b>States</b>)
 * für alle Datenklassen vom Typ {@linkplain Data} ausgehend von der Rootklasse
 * {@linkplain DataRoot}.<br>
 * 
 * Das Metamodell enthält die Statemodelle aller
 * {@linkplain StateGroupGuideline}. Diese Gruppen sind zusammenhängende
 * States, wobei immer nur ein State aktiv sein kann mit Ausnahme der Gruppen
 * mit konstanten Werten. Eine Beschreibung der Datenhierarchie ist in
 * {@linkplain DataGuidelineLevel} hinterlegt. <br>
 * 
 * Ein State wird beschrieben durch die Zustandswerte {@linkplain IGuideline}
 * und der Beschreibung der Zustandswechsel {@linkplain VTransitionGuidelines}.<br>
 * 
 * Ein Wächterausdruck {@linkplain _Guard} kann die Transition schützen: die
 * Transition kann nur durchlaufen werden, wenn der Wächterausdruck wahr ist.<br>
 * 
 * Da es in anderen Zusammenhängen {@linkplain ContextGuideline} verschiedene
 * {@linkplain VTransitionGuidelines} geben kann, sind diese dort gruppiert. Der
 * Übergang wird wie oben durch die {@linkplain CTransitionGuideline} gesteuert
 * und durch Wächterausdrücke {@linkplain _Guard} geschützt.<br>
 * 
 * Die Beschreibungen sind in APO-Scripten unter /script/"game"/data/
 * hinterlegt.
 * 
 * <br>
 * Der Aufbau des Datenmodells:
 * 
 * <pre>
 * MetaStateGuideline
 * 	DataHierarchie
 *  	rootclazz
 *  		childclazzes 
 *  	StateGroupMap
 *  		StateGroup
 *  			IGuideline
 *  				VertexGuideline
 *  				RadioGuideline
 *  				ConstGuideline
 *  			ContextGuideline
 *  				VTransitionGuideline
 *  					GuardList
 *  						_Guard
 *  			CTransitionGuideline
 *  				GuardList
 *  					_Guard
 *  			IGuideline_Termine
 *  			VTransitionGuideline_Termine
 *  			ContextGuideline_Termine
 *  BuildGuidelines
 * </pre>
 * 
 *
 * @author tfossi
 * @version 26.01.2015
 * @modified -
 * @since Java 1.6
 */
@SuppressWarnings("javadoc")
public class MetaGuideline {
	 // ---- Selbstverwaltung --------------------------------------------------
	 /** serialVersionUID */
	@SuppressWarnings("unused")
	private final static long serialVersionUID = VERSION;
	 /** logger */
	final static Logger logger = Logger.getLogger(MetaGuideline.class
	 .getPackage().getName()
	 + ".MetaGuideline");


	/**
	 * Legt den Datencontainer des Gesamt-Regelwerks <i>MetaStateGuideline</i>
	 * an und erstellt das Modell der <i>Datenhierachie</i>
	 * 
	 * @see DataGuidelineLevel
	 * @.pre § Erwartet ein Script 'RootStateGroups.apo' mit einem Table
	 *       'Root'. Ist der Rooteeintrag der Datenhierarchie. *
	 * @param game
	 *            das aktuelles Game, für das das Regelwerk gültig ist.
	 * @param sg_path
	 *            Der Dateipfad zu den Scripten.
	 */
	// public MetaStateGuideline(DataRoot game, String sg_path) {
	public MetaGuideline() {
//		this.rootDGL = new DataGuidelineLevel(null);
	}
}
//
//private final static GDC gdc = new GDC();
//private final static GTC gtc = new GTC();
//
//// private final static DigitGDC dgdc = new DigitGDC();
////
///**
// * Die Datenhierarchie des Datenmodells der 'Data'-Typen
// */
//private final DataGuidelineLevel rootDGL ; //= new DataGuidelineLevel(null);
//
///**
// * @return liefert alle Klassen, die im Guideline eingestellt sind als
// *         Liste.
// */
//public final List<Datenklasse> getAllClasses() {
//	List<Datenklasse> rc = new ArrayList<>();
//	// List<Class<? extends Data>> rc = new ArrayList<Class<? extends
//	// Data>>();
//	List<DataGuidelineLevel> liste = this.getAllDGL(
//			/*
//			new ArrayList<DataGuidelineLevel>(), */ this.rootDGL);
//
//	for (DataGuidelineLevel dgl : liste)
//		rc.add(dgl.getDatenklasse());
//	return rc;
//}
//
///**
// * Geht durch die Datenstruktur liefert eine DataGuidelineLevel-Liste 
// * @param dgl
// * 			ab DataGuidelineLevel [dgl] wird durchsucht 
// * @return eine Liste des DataGuidelineLevel und dessen Childs
// */
//public final List<DataGuidelineLevel> getAllDGL(
//		final DataGuidelineLevel dgl) {
//	List<DataGuidelineLevel> liste = new ArrayList<>();
//	liste.add(dgl);
//	for (DataGuidelineLevel childDGL : dgl.getChildDGL()) {
//		liste.addAll(this.getAllDGL(childDGL));
//	}
//	return liste;
//}
//
///**
// * @return die Datenhierarchie. XXX Da diese Daten manipuliert werden
// *         können, ist zu überlegen einen Clone o.ä. zu liefern.
// */
//public final DataGuidelineLevel getRootDGL() {
//	return this.rootDGL;
//}
//
//
// /**
// * Findet eine Datenhierarchie ab einer bestimmten Klasse vom Typ 'Data'
// *
// * @param dk
// * vom Typ 'Data'
// * @return die Datenhierarchie ab der Klasse 'clazz'
// */
// private final DataGuidelineLevel findDGL(Datenklasse dk) {
// List<DataGuidelineLevel> liste = this.getAllDGL(
// /*new ArrayList<DataGuidelineLevel>(), */ this.rootDGL);
// for (DataGuidelineLevel dgl : liste) {
// if (dgl.getDatenklasse().equals(dk))
// return dgl;
// }
// return null;
// }
//// public final DataGuidelineLevel findDGL(String name) {
//// List<DataGuidelineLevel> liste = this.getAllDH(
//// new ArrayList<DataGuidelineLevel>(), this.rootDGL);
//// for (DataGuidelineLevel dgl : liste) {
//// if (dgl.getDatenklasse().getTyp().equals(name))
//// return dgl;
//// }
//// return null;
//// }
////
//// public final int numberOfStageGroups(String name){
//// return this.findDGL(name).numberOfStageGroupGuidelines();
//// }
//// public final StateGroupGuideline addNewStateGroup(String dglName,
//// StateGroupGuideline sg){
//// DataGuidelineLevel dgl = this.findDGL(dglName);
//// return dgl.addStageGroupGuideline(sg);
//// }
//// /**
//// * Trage eine neue Gruppe von States einer Klasse ein und initiert alle
//// * enthaltenen Einträge der Vertices, Contexte, Transitions (C und V)
//// *
//// * @see StateGroupGuideline
//// * @.pre Alle Scripteinträge einer StateGroup sind erstellt.
//// * @param game
//// * das aktuelle Game
//// * @param dk
//// * die Klasse
//// * @param sg_name
//// * ist der Name der StateGoup
//// * @param sg_type
//// * Vertextypen in der StateGroup
//// * @param sg_table
//// * APO-Scripttabelle der Stategroups
//// */
//// public final int createNewGroupAndMore(final DataRoot game,
//// final Datenklasse dk, final String sg_name,
//// final Class<? extends IGuideline> sg_type, final Table sg_table) {
////
//// DataGuidelineLevel dgl = this.findDGL(dk);
//// // StateGroupMap sg_map = this.findDGL(dk).sg_map;
//// int sg_idx = dgl.numberOfStageGroupGuidelines();
//// // if (LOGGER)
//// // logger.info("Eine neue Gruppe [" + sg_name + "(" + sg_idx + ")]"
//// // + LOGTAB + "in Klassenguideline [" +
//// clazz.getTyp()+"."+clazz.getDepth()
//// // + "] anlegen!");
//// StateGroupGuideline sg = new StateGroupGuideline(game, dgl, sg_idx,
//// sg_name, sg_type, sg_table);
//// dgl.addStageGroupGuideline(sg);
//// return sg_idx;
//// }
////
//// /**
//// * Abfrage, ob eine bestimmte StateGroup existiert
//// *
//// * @param dk
//// * die Klasse vom Typ 'Data' mit den Stategroups
//// * @param sg_name
//// * der Name der StateGroup
//// * @return true, if exist
//// */
//// public final boolean hasGroup(final Datenklasse dk,
//// final String sg_name) {
//// DataGuidelineLevel dgl = this.findDGL(dk);
//// return dgl.hasStageGroupGuideline(sg_name.replace(" ", ""));
//// }

// /**
// * @param dk
// * Klasse, deren Gruppen gesucht werden.
// * @return Eine Sammlung aller StateGroups der Klasse <i>clazz</i>
// */
// public final Collection<StateGroupGuideline> getAllStateGroupValues(
// final Datenklasse dk) {
// DataGuidelineLevel dgl = this.findDGL(dk);
// return dgl.getAllStateGroupValues();
// }

//// /**
//// * @param dk
//// * Klasse, in der die Gruppe liegt.
//// * @param sg_name
//// * ist der Name einer StateGroup einer <i>clazz</i>, die gesucht
//// * wird
//// * @return ist die StateGroup oder <code>null</code>
//// * @throws NoSuchFieldException
//// */
//// public final StateGroupGuideline getStateGroup(final Datenklasse dk,
//// final String sg_name) throws NoSuchFieldException {
//// DataGuidelineLevel dgl = this.findDGL(dk);
////
//// StateGroupGuideline sg = dgl.getStageGroupGuideline(sg_name.replace(" ",
//// ""));
//// if (sg == null)
//// throw new java.lang.NoSuchFieldException("Group ["+
//// sg_name+"] in Clazz ["
//// + dk.getTyp()+"."+dk.getDepth() + "] in Liste "
//// + dgl.getAllStateGroupNames() + " existiert nicht");
//// return sg;
//// }
////
//// /**
//// * Suche in im Datensatz der Klasse <i>clazz</i> unter dem
//// StateGpoup-Index
//// * <i>sg_idx</i> einen bestimmten State.
//// *
//// * @param clazz
//// * Klasse des Datensatzes vom Typ {@linkplain Data}
//// * @param sg_idx
//// * StateGpoup-Index
//// * @param igl_name
//// * Name des {@linkplain IGuideline}
//// * @return <code>igl_idx</code> oder -1 (not founded)
//// */
//// public final int findIGL_idx(final Datenklasse clazz, String sg_name,
//// final String igl_name) {
//// assert false: "NI";
//// // StateGroupMap sg_map = this.findDGL(clazz).sg_map;
//// // for (IGuideline igl : sg_map.get(sg_name.replace(" ",
//// "")).igl_values()) {
//// // if (igl.getIGL_Index().igl_name.equals(igl_name))
//// // return igl.getIGL_Index().igl_idx;
//// // }
//// return -1;
//// }
//// public final GuideData findIGL(final Datenklasse clazz, String sg_name,
//// final String igl_name) {
//// StateGroupGuideline sg = null;
//// try {
//// sg = this.getStateGroup(clazz, sg_name);
//// } catch (NoSuchFieldException e) {
//// // TODO Auto-generated catch block
//// e.printStackTrace();
//// assert false;
//// }
////
//// for (GuideData igl : sg.gglValues()) {
//// if (gdc.getName(igl).equals(igl_name))
//// return igl;
//// }
//// return null;
//// }
////
///**
// * Baut für JUnit einen Teststring zusammen
// * 
// * @see JUnit
// * @param game
// *            das aktuelle Game
// * @return der Teststring
// * 
// */
//public static String[] tstString(DataRoot game) {
//	List<String> lsb = new ArrayList<String>();
//	MetaStateGuideline msg = game.getMetaStateGuideline();
//
//	lsb.add("GUIDELINE");
//	lsb.add("---------");
//	lsb.add("");
//	lsb.add("Erfasste DataGuidelineLevel:");
//	lsb.add("Liste aller Ebenen!");
//	List<DataGuidelineLevel> liste = new ArrayList<DataGuidelineLevel>();
//	msg.getAllDH(liste, msg.rootDGL);
//	for (DataGuidelineLevel dgl : liste) {
//		lsb.add(new String("... [" + dgl.getDatenklasse().getTyp() + "."
//				+ dgl.getDatenklasse().getDepth() + "]"));
//		lsb.add(new String("...... Anzahl der Stategruppen: "
//				+ dgl.numberOfStageGroupGuidelines()));
//		for (int idx = 0; idx < dgl.numberOfStageGroupGuidelines(); idx++) {
//			StateGroupGuideline sgg = dgl.getStageGroupGuideline(idx);
//			lsb.add(new String("...... [" + gdc.getName(sgg) + "("
//					+ gdc.getIDX(sgg) + "): " + gdc.getActivity(sgg) + "]"));
//			lsb.add(new String("......... Anzahl der States: "
//					+ gtc.getNumberOfStates(sgg)));
//
//			lsb.add("......... Erfasste Gruppentermine: "
//					+ gtc.getNumberOfIGLTermine(sgg));
//			// Termine, an denen die States aktualisiert werden.
//			// Ist nur notwendig, bei States, die ihre Zustandswerte ändern
//			// können
//			// und termingesteuert sind.
//			for (String temin : gtc.getAll_IGL_Terminevent(sgg))
//				lsb.add("......... State-Termin@ [" + temin + "]");
//
//			for (GuideData gd : gtc.gglValues(sgg)) {
//				// TODO Const in Single umbenennen
//				if (gdc.isConst(gd)) {
//					lsb.add(new String("......... Const: ["
//							+ gdc.getName(gd) + "(" + gdc.getIDX(gd)
//							+ "): " + gdc.getActivity(gd) + "]"));
//					lsb.add(new String("............ y0 = ["
//							+ gdc.getY0((GuideConstData) gd) + "]"));
//				}
//
//				if (gdc.isRadio(gd)) {
//					lsb.add(new String("......... Radio: ["
//							+ gdc.getName(gd) + "(" + gdc.getIDX(gd)
//							+ "): " + gdc.getActivity(gd) + "]"));
//					lsb.add(new String("............ uk = ["
//							+ gdc.getUk((GuideRadioData) gd) + "]"));
//					lsb.add(new String("............ y0 = "
//							+ gdc.getRadioliste((GuideRadioData) gd)));
//				}
//
//				if (gdc.isDigit(gd)) {
//					lsb.add(new String("......... Digit: ["
//							+ gdc.getName(gd) + "(" + gdc.getIDX(gd)
//							+ "): " + gdc.getActivity(gd) + "]"));
//					GuideDigitData vtx = (GuideDigitData) gd;
//					// lsb.add("......... [" + gdc.getName(igl) + "("
//					// + gdc.getIDX(igl) + ")]: [" + gdc.getActivity(igl) +
//					// "]");
//					// // lsb.add("............einheit [" + vtx.getEinheit()
//					// +
//					// // "]");
//					//
//					// lsb.add("............clazz/Group ["
//					// +
//					// igl.getDGL().getDatenklasse().getTyp()+"."+igl.getDGL().getDatenklasse().getDepth()
//					// + "]/["
//					// + gdc.getName(igl) + "("
//					// + gdc.getIDX(igl) + ")]");
//					lsb.add("............Y-Range: ["
//							+ gdc.getMinYValue(vtx) + "] ... ["
//							+ gdc.getMaxYValue(vtx) + "]");
//					lsb.add("............priority: ["
//							+ gdc.getPriority(vtx) + "]");
//					// // lsb.add("............vertexName ["
//					// // + vtx.getIGL_Index().igl_name + "]");
//					// // lsb.add("............vtxnr [" +
//					// vtx.getIGL_Index().igl_idx
//					// // + "]");
//					lsb.add("............y0 [" + gdc.getY0(vtx) + "]");
//
//					lsb.add("............GeberPass1Description ["
//							+ gdc.getSollwertScript(vtx)[GDC.AKTIV] + "]");
//					lsb.add("............GeberDescription ["
//							+ gdc.getGeberDescription(vtx)[GDC.AKTIV] + "]");
//					lsb.add("............GeberPass1Description ["
//							+ gdc.getSollwertScript(vtx)[GDC.PASSIV] + "]");
//					lsb.add("............GeberDescription ["
//							+ gdc.getGeberDescription(vtx)[GDC.PASSIV]
//							+ "]");
//
//					lsb.add("............Strecke ["
//							+ gdc.getStreckeDescription(vtx)[GDC.AKTIV]
//							+ "]");
//					lsb.add("............Strecke ["
//							+ gdc.getStreckeDescription(vtx)[GDC.PASSIV]
//							+ "]");
//
//					lsb.add("............Regler ["
//							+ gdc.getReglerDescription(vtx)[GDC.AKTIV]
//							+ "]");
//					lsb.add("............Regler ["
//							+ gdc.getReglerDescription(vtx)[GDC.PASSIV]
//							+ "]");
//
//					lsb.add("............Abtastungen Strecke ["
//							+ gdc.getStreckeK(vtx)[GDC.AKTIV] + "]");
//					lsb.add("............Abtastungen Strecke ["
//							+ gdc.getStreckeK(vtx)[GDC.PASSIV] + "]");
//					// lsb.add("............StreckeKtoString(true) ["
//					// + gdc.getStreckeKtoString(vtx)[GDC.AKTIV] + "]");
//					// lsb.add("............StreckeKtoString(false) ["
//					// + gdc.getStreckeKtoString(vtx)[GDC.PASSIV] + "]");
//
//					lsb.add("............Abtastungen Regler ["
//							+ gdc.getReglerK(vtx)[GDC.AKTIV] + "]");
//					lsb.add("............Abtastungen Regler ["
//							+ gdc.getReglerK(vtx)[GDC.PASSIV] + "]");
//					// lsb.add("............ReglerKtoString(true) ["
//					// + dgdc.getReglerKtoString(vtx, true) + "]");
//					// lsb.add("............ReglerKtoString(false) ["
//					// + dgdc.getReglerKtoString(vtx, false) + "]");
//					// }
//					// }
//					// }
//					//
//					lsb.add("............Trigger ["
//							+ gdc.getTriggerlisteAdressen(vtx) + "]");
//				}
//			}
//			lsb.add(new String("......... Anzahl der Contexte: "
//					+ gtc.getNumberOfContexte(sgg)));
//			lsb.add("......... Erfasste Transitiontermine f�r Contexte: "
//					+ gtc.getNumberOfCTGTermine(sgg));
//			// Termine, an denen die States aktualisiert werden.
//			// Ist nur notwendig, bei States, die ihre Zustandswerte ändern
//			// können
//			// und termingesteuert sind.
//			for (String temin : gtc.getAll_CTG_Terminevent(sgg))
//				lsb.add("......... Context-Transition-Termin@ [" + temin
//						+ "]");
//			// "Termine, an denen die Contexte der States aktualisiert werden "
//			// und sich andere Transitionen ergeben können.
//			// Ist nur notwendig, bei Contexten, die termingesteuert sind.
//
//			lsb.add("......... Erfasste TransitionStates f�r Contexte: "
//					+ gtc.getNumberOfCTGStates(sgg));
//			for (PreAddress adresse : gtc.getAll_CTG_Stateevent(sgg))
//				lsb.add("......... Context-Transition-State@ [" + adresse +"]");
//			
//			for (GuideData gd : gtc.cglValues(sgg)) {
//				ContextGuideline cg = (ContextGuideline) gd;
//				lsb.add(new String("......... Context: [" + gdc.getName(gd)
//						+ "(" + gdc.getIDX(gd) + "): "
//						+ gdc.getActivity(gd) + "]"));
//				CTransitionGuideline ctg = gtc.getCTG(cg);
//				if (ctg != null) {
//
//					lsb.add("............ CTransition(ID): ["
//							+ gdc.getName(ctg) + "(" + gdc.getIDX(ctg)
//							+ ")]");
//					for (GuardList gl : gtc.getGuard(ctg)) {
//						lsb.add("...............gl.nr [" + gl.nr + "]");
//						for (_Guard _g : gl.guards) {
//							lsb.add("..................[" + _g.toString()
//									+ "]");
//							lsb.add(".................._g.getGuardType() ["
//									+ _g.getGuardType().getSimpleName()
//									+ "]");
//							// lsb.add(".................._g.getVertex() ["
//							// + _g.getVertex() + "]");
//							// // }
//							// // }
//							// // }
//						}
//					}
//				}		
//				lsb.add("............ Erfasste Transitiontermine f�r Vertex: "
//						+ gtc.getNumberOfVTGTermine(cg));
//				for (String temin : gtc.getAll_VTG_Terminevent(cg))
//							lsb.add("............ Vertex-Transition-Termin@ [" + temin
//									+ "]");
//				
//				lsb.add("............ Erfasste TransitionStates f�r Vertex: "
//						+ gtc.getNumberOfVTGStates(cg));
//
//				for (PreAddress adresse : gtc.getAll_VTG_Stateevent(cg))
//					lsb.add("............ Vertex-Transition-State@ [" + adresse
//							+ "]");
//				for(GuideData vtgd : gtc.getAllVTG(cg)){
//					VTransitionGuideline vtg = (VTransitionGuideline)vtgd;
//					
//					lsb.add("............ VTransition(ID): ["
//										+ gdc.getName(vtg) + "(" + gdc.getIDX(vtg)
//										+ ")]");
//					for (GuardList gl : gtc.getGuard(vtg)) {
//						lsb.add("...............gl.nr [" + gl.nr + "]");
//						for (_Guard _g : gl.guards) {
//							lsb.add("..................[" + _g.toString()+ "]");
//							lsb.add(".................._g.getGuardType() ["
//												+ _g.getGuardType().getSimpleName()
//												+ "]");
//										// lsb.add(".................._g.getVertex() ["
//										// + _g.getVertex() + "]");
////										// // }
////										// // }
////										// // }
////									}
//						}
//					}	
//				}
//			}
//		}
//	}
//
//	// lsb.add("Erfasste GGL-daten:");
//	// lsb.add("Details aller non-diskreten Zustandswerte.");
//	// for (Datenklasse clazz : msg.getAllClasses()) {
//	// lsb.add("... [" + clazz.getTyp()+"."+clazz.getDepth() + "]");
//	// for (StateGroupGuideline sg : msg.getAllStateGroupValues(clazz)) {
//	// lsb.add("...... State(ID): [" + gdc.getName(sg) + "(" +
//	// gdc.getIDX(sg) + ")] Anzahl: "
//	// + sg.gglAll().size());
//	// for(String key : sg.gglAll()){
//	// lsb.add("......... " + key + ": [" + sg.ggl_get(key) + "]");
//	//
//	// }
//	// }
//	// }
//	//
//	// lsb.add("");
//	// lsb.add("Erfasste Vertexdaten:");
//	// lsb.add("Details aller diskreten Zustandswerte.");
//	// for (Datenklasse clazz : msg.getAllClasses()) {
//	// lsb.add("... [" + clazz.getTyp()+"."+clazz.getDepth() + "]");
//	// for (StateGroupGuideline sg : msg.getAllStateGroupValues(clazz)) {
//	//
//	// lsb.add("...... State(ID): [" + gdc.getName(sg) + "(" +
//	// gdc.getIDX(sg) + ")] Anzahl: "
//	// + sg.gglAll().size());
//	//
//	// for (GuideData igl : sg.gglValues()) {
//	// if (!gdc.isVertex(igl)){
//	// continue;
//	// }
//	// GuideDigitData vtx = (GuideDigitData) igl;
//	// lsb.add("......... [" + gdc.getName(igl) + "("
//	// + gdc.getIDX(igl) + ")]: [" + gdc.getActivity(igl) + "]");
//	// // lsb.add("............einheit [" + vtx.getEinheit() +
//	// // "]");
//	//
//	// lsb.add("............clazz/Group ["
//	// +
//	// igl.getDGL().getDatenklasse().getTyp()+"."+igl.getDGL().getDatenklasse().getDepth()
//	// + "]/["
//	// + gdc.getName(igl) + "("
//	// + gdc.getIDX(igl) + ")]");
//	// lsb.add("............Y-Range: [" + dgdc.getMinYValue(vtx)
//	// + "] ... [" + dgdc.getMaxYValue(vtx)
//	// + "]");
//	// // lsb.add("............priority [" + vtx.getPriority() +
//	// // "]");
//	// // lsb.add("............vertexName ["
//	// // + vtx.getIGL_Index().igl_name + "]");
//	// // lsb.add("............vtxnr [" + vtx.getIGL_Index().igl_idx
//	// // + "]");
//	// lsb.add("............y0 [" + dgdc.getY0(vtx) + "]");
//	//
//	//
//	// // lsb.add("............GeberDescription(true) ["
//	// // + vtx.getGeberDescription(true) + "]");
//	// // lsb.add("............GeberDescription(false) ["
//	// // + vtx.getGeberDescription(false) + "]");
//	//
//	// // lsb.add("............sollwertGeberActiv ["
//	// // + vtx.getSollwertGeberActiv().getClass()
//	// // .getSimpleName() + "]");
//	// // lsb.add("............sollwertGeberActiv ["
//	// // + vtx.getSollwertGeberActiv() + "]");
//	//
//	// // lsb.add("............sollwertGeberPassiv ["
//	// // + vtx.getSollwertGeberPassiv().getClass()
//	// // .getSimpleName() + "]");
//	// // lsb.add("............sollwertGeberPassiv ["
//	// // + vtx.getSollwertGeberPassiv()+ "]");
//	// lsb.add("............aktives GeberScript "+
//	// Arrays.asList(dgdc.getSollwertScriptActiv(vtx)));
//	// lsb.add("............aktiver Geber ["
//	// + dgdc.getGeber(vtx, true) + "]");
//	// // lsb.add("............Geber(true) ["
//	// // + vtx.getGeber(true)
//	// // + "]");
//	//
//	// lsb.add("............passives GeberScript "+
//	// Arrays.asList(dgdc.getSollwertScriptPassive(vtx)));
//	// lsb.add("............passiver Geber [" + dgdc.getGeber(vtx, false) +
//	// "]");
//	// // lsb.add("............Geber(false) ["
//	// // + vtx.getGeber(false)
//	// // + "]");
//	// lsb.add("............Abtastungen Strecke(true) ["
//	// + dgdc.getStreckeK(vtx, true) + "]");
//	// lsb.add("............Abtastungen Strecke(false) ["
//	// + dgdc.getStreckeK(vtx, false) + "]");
//	// lsb.add("............StreckeKtoString(true) ["
//	// + dgdc.getStreckeKtoString(vtx, true) + "]");
//	// lsb.add("............StreckeKtoString(false) ["
//	// + dgdc.getStreckeKtoString(vtx, false) + "]");
//	//
//	// lsb.add("............Abtastungen Regler(true) ["
//	// + dgdc.getReglerK(vtx, true) + "]");
//	// lsb.add("............Abtastungen Regler(false) ["
//	// + dgdc.getReglerK(vtx, false) + "]");
//	// lsb.add("............ReglerKtoString(true) ["
//	// + dgdc.getReglerKtoString(vtx, true) + "]");
//	// lsb.add("............ReglerKtoString(false) ["
//	// + dgdc.getReglerKtoString(vtx, false) + "]");
//	// }
//	// }
//	// }
//	//
//	// lsb.add("");
//	// lsb.add("Erfasste Radiodaten:");
//	// lsb.add("Details aller statischen Zustandswerte.");
//	// for (Datenklasse clazz : msg.getAllClasses()) {
//	// lsb.add("... [" + clazz.getTyp()+"."+clazz.getDepth() + "]");
//	// for (StateGroupGuideline sg : msg.getAllStateGroupValues(clazz)) {
//	// lsb.add("...... State(ID): [" + gdc.getName(sg) + "(" +
//	// gdc.getIDX(sg) + ")] Anzahl: "
//	// + sg.gglAll().size());
//	//
//	// for (GuideData igl : sg.gglValues()) {
//	// if (!gdc.isRadio(igl))
//	// continue;
//	// RadioGuideline vtx = (RadioGuideline) igl;
//	//
//	// lsb.add("......... [" + gdc.getName(igl) + "]");
//	//
//	// lsb.add("............activity [" + gdc.getActivity(igl) + "]");
//	// lsb.add("............stategroupname ["
//	// + gdc.getName(gdc.getSuperGuideData(igl)) + "]");
//	// lsb.add("............grpnr [" +
//	// gdc.getIDX(gdc.getSuperGuideData(igl))
//	// + "]");
//	// lsb.add("............radioName ["
//	// + gdc.getName(igl) + "]");
//	// lsb.add("............vtxnr [" + gdc.getIDX(igl)
//	// + "]");
//	// lsb.add("............y0 [" + vtx.getY0() + "]");
//	// // lsb.add("............prio [" + vtx.getPriority() + "]");
//	// }
//	// }
//	// }
//	//
//	// lsb.add("");
//	// lsb.add("Erfasste Constdaten:");
//	// lsb.add("Details aller konstanten Zustandswerte.");
//	// for (Datenklasse clazz : msg.getAllClasses()) {
//	// lsb.add("... [" + clazz.getTyp()+"."+clazz.getDepth() + "]");
//	// for (StateGroupGuideline sg : msg.getAllStateGroupValues(clazz)) {
//	// lsb.add("...... State(ID): [" + gdc.getName(sg) + "(" +
//	// gdc.getIDX(sg) + ")] Anzahl: "
//	// + sg.gglAll().size());
//	//
//	// for (GuideData igl : sg.gglValues()) {
//	// if (!gdc.isConst(igl))
//	// continue;
//	// ConstGuideline vtx = (ConstGuideline) igl;
//	//
//	// lsb.add("......... Name: [" + gdc.getName(igl)
//	// + "]");
//	//
//	// lsb.add("............activity [" + gdc.getActivity(igl) + "]");
//	// lsb.add("............stategroupname ["
//	// + gdc.getName(gdc.getSuperGuideData(igl)) + "]");
//	// lsb.add("............grpnr [" +
//	// gdc.getIDX(gdc.getSuperGuideData(igl))
//	// + "]");
//	// lsb.add("............vtxnr [" + gdc.getIDX(igl)
//	// + "]");
//	// lsb.add("............y0 [" + vtx.getY0() + "]");
//	// }
//	// }
//	// }
//	//
//	// lsb.add("");
//	// lsb.add("------------------------------------------------------------");
//	//
//	// lsb.add("");
//	// lsb.add("Erfasste Contexttermine:");
//	// lsb
//	// .add("Termine, an denen die Contexte der States aktualisiert werden "
//	// + "und sich andere Transitionen ergeben können. "
//	// + "Ist nur notwendig, bei Contexten, die termingesteuert sind.");
//	// for (Datenklasse clazz : msg.getAllClasses()) {
//	// lsb.add("... [" + clazz.getTyp()+"."+clazz.getDepth() + "]");
//	// for (StateGroupGuideline sg : msg.getAllStateGroupValues(clazz)) {
//	// lsb.add("...... State(ID): [" + gdc.getName(sg) + "(" +
//	// gdc.getIDX(sg) + ")]");
//	// for (String temin : sg.getAll_CTG_Terminevent()) {
//	// lsb.add("......... Context-Calculation@ [" + temin + "]");
//	// }
//	// }
//	// }
//	// lsb.add("");
//	// lsb.add("Erfasste Contexttransitionsdaten:");
//	// for (Datenklasse clazz : msg.getAllClasses()) {
//	// lsb.add("... [" + clazz.getTyp()+"."+clazz.getDepth() + "]");
//	// for (StateGroupGuideline sg : msg.getAllStateGroupValues(clazz)) {
//	// lsb.add("...... State(ID): [" + gdc.getName(sg) + "(" +
//	// gdc.getIDX(sg) + ")] Anzahl: "
//	// + sg.cgl_values().size());
//	// for (CTransitionGuideline ctg : sg.ctg_values()) {
//	// lsb.add("......... CTransition(ID): [" + ctg.ctx_name + "("
//	// + ctg.ctx_idx + ")]");
//	// // assert false;
//	// // FIXME lsb.add("............... Guard-Class: " +
//	// // ctg.guard_clazz.getSimpleName());
//	// // assert false;
//	// // FIXME lsb.add("............... Guard-State: " +
//	// // ctg.guard_sg.stategroupname);
//	// // assert false;
//	// // FIXME if(ctg.guard_igl!=null)
//	// // lsb.add("............... Guard-Vertex: "
//	// // + ctg.guard_igl.getIGL_Index().igl_name);
//	//
//	// for (GuardList gl : ctg.getGuard()) {
//	// lsb.add("...............gl.nr [" + gl.nr + "]");
//	// for (_Guard _g : gl.guards) {
//	// lsb
//	// .add("..................[" + _g.toString()
//	// + "]");
//	// lsb.add(".................._g.getGuardType() ["
//	// + _g.getGuardType().getSimpleName() + "]");
//	// lsb.add(".................._g.getVertex() ["
//	// + _g.getVertex() + "]");
//	// }
//	// }
//	// }
//	// }
//	// }
//	//
//	// lsb.add("");
//	// lsb.add("------------------------------------------------------------");
//	//
//	// lsb.add("");
//	// lsb.add("Erfasste Transitiontermine:");
//	// lsb
//	// .add("Termine, an denen die Transitions der States aktualisiert werden");
//	// for (Datenklasse clazz : msg.getAllClasses()) {
//	// lsb.add("... [" + clazz.getTyp()+"."+clazz.getDepth() + "]");
//	// for (StateGroupGuideline sg : msg.getAllStateGroupValues(clazz)) {
//	// lsb.add("...... State(ID): [" + gdc.getName(sg) + "(" +
//	// gdc.getIDX(sg) + ")]");
//	// for (String temin : sg.getAll_VTG_Terminevent()) {
//	// lsb
//	// .add("......... Transition-Calculation@ [" + temin
//	// + "]");
//	// }
//	// }
//	// }
//	//
//	// lsb.add("");
//	// lsb.add("Erfasste Transitiondaten:");
//	// lsb.add("Details aller Transitions");
//	// for (Datenklasse clazz : msg.getAllClasses()) {
//	// lsb.add("... [" + clazz.getTyp()+"."+clazz.getDepth() + "]");
//	// for (StateGroupGuideline sg : msg.getAllStateGroupValues(clazz)) {
//	// lsb.add("...... State(ID): [" + gdc.getName(sg) + "(" +
//	// gdc.getIDX(sg) + ")] Anzahl: "
//	// + sg.cgl_values().size());
//	// for (ContextGuideline ctx : sg.cgl_values()) {
//	// lsb.add("......... Context(ID): [" + ctx.ctx_name + "("
//	// + ctx.ctx_idx + ")]");
//	// for (VTransitionGuideline vtg : ctx.vtg_values()) {
//	// lsb.add("............ VTransition(ID): ["
//	// + vtg.vtg_name + "(" + vtg.vtg_idx + ")]");
//	// // assert false;
//	// // FIXME assert vtg.getGuard() != null
//	// // && !vtg.getGuard().isEmpty() : vtg.getGuard();
//	// for (GuardList gl : vtg.getGuard()) {
//	// lsb.add("...............gl.nr [" + gl.nr + "]");
//	// for (_Guard _g : gl.guards) {
//	// lsb.add("..................[" + _g.toString()
//	// + "]");
//	// lsb.add(".................._g.getGuardType() ["
//	// + _g.getGuardType().getSimpleName()
//	// + "]");
//	// lsb.add(".................._g.getVertex() ["
//	// + _g.getVertex() + "]");
//	// }
//	// }
//	// }
//	// }
//	// }
//	// }
//	//
//	//
//	lsb.add("XXX");
//	return lsb.toArray(new String[0]);
//}
//
//

//
//// @SuppressWarnings({ "unused", "unchecked" })
//// @Deprecated
//// private final void createDHOld(final DataGuidelineLevel dh, final String
//// dataName,
//// final Table structure) {
////
//// if (dataName == null)
//// return;
////
//// // Erzeuge die Rootclazz in der aktuellen Hierarchiestufe
//// Class<? extends Data> clazz = null;
//// try {
//// // TODO Pfade nciht direkt eingeben, sondern parametrisiert!
//// clazz = (Class<? extends Data>) Class
//// .forName("tfossi.apolge.data.Data" + dataName);
//// if (LOGGER)
//// logger.debug("Guideline-Container für [" + dataName + "]");
//// // dh.rootclazz = clazz;
////
//// } catch (ClassNotFoundException e) {
//// logger.fatal("Class [Data" + dataName + "] existiert nicht!");
//// System.exit(-14);
//// }
////
//// // FIXME parallele Datenstrukturen sind nicht möglich Game ->
//// // Lobby||Nation
//// // Gehe die Childclazz der aktuellen Hierarchiestufe durch
//// for (String subDataName : structure.keySet()) {
//// if (LOGGER)
//// logger.debug("SUB [" + subDataName + "] " + structure);
//// Table scripteintrag = null;
//// try {
//// scripteintrag = LoadScript
//// .getTableValue(structure, subDataName);
//// } catch (NullPointerException e) {
//// logger.fatal(e.getMessage());
//// e.printStackTrace();
//// System.exit(-1);
//// } catch (NoSuchFieldException e) {
//// logger.fatal(e.getMessage());
//// e.printStackTrace();
//// System.exit(-1);
//// }
//// if (LoadScript.has(scripteintrag, "0")) {
//// // assert false;
//// continue;
//// }
//// DataGuidelineLevel subDh = new DataGuidelineLevel();
//// dh.childDGL.add(subDh);
//// createDHOld(subDh, subDataName, scripteintrag);
//// }
//// }