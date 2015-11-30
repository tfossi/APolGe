package tfossi.apolge.data.core;

import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;

import org.apache.log4j.Logger;

import tfossi.apolge.common.scripting.vp.VP_Tokenlist;

/**
 * Modell/Daten des Spiels<br>
 *
 * @author tfossi
 * @version 26.01.2015
 * @modified -
 * @since Java 1.6
 */
public class DataRoot extends CoreData implements IDataRoot {
//	// private final static CDC dsc = new CDC();
//	//
//	private final static GDC gdc = new GDC();
//
//	/**
//	 * Regelwerk 'Game'
//	 */
//	private MetaStateGuideline msg;
//
//	/**
//	 * Speichert das Regelwerk aller States
//	 * 
//	 * @param in
//	 *            das Regelwert
//	 * @return Liefert das Regelwerk aller States
//	 */
//	public final MetaStateGuideline setMetaStateGuideline(
//			final MetaStateGuideline in) {
//		return this.msg = in;
//	}
//
//	/**
//	 * @return Liefert das Regelwerk aller States
//	 */
//	public final MetaStateGuideline getMetaStateGuideline() {
//		return this.msg;
//	}
//
////	/*
////	 * (non-Javadoc)
////	 * 
////	 * @see tfossi.apolge.data.core.IDataRoot#getDataGuidelineLevel()
////	 */
////	@Override
////	public final DataGuidelineLevel getDataGuidelineLevel() {
////		return gdc.getDGL(this.guidedata);
////	}
//
//	//
//	// /**
//	// * Zeit- und Threadcontroller JB
//	// */
//	// // FIXME 21.02.2014 Testweise herausgenommen private ITimesController tc
//	// = new TimesController();
//
//	/**
//	 * Test mit Log4j IST: Anlage Ist-Werte CHECK: Vergleich Ist-/Sollwerte
//	 */
//	public String logtest = null;
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see tfossi.apolge.data.core.IDataRoot#getLogtest()
//	 */
//	@Override
//	public String getLogtest() {
//		return this.logtest;
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see tfossi.apolge.data.core.IDataRoot#setLogtest(java.lang.String)
//	 */
//	@Override
//	public void setLogtest(final String stringValue) {
//		this.logtest = stringValue;
//	}

	// ---- Selbstverwaltung --------------------------------------------------
	
	
	/** serialVersionUID */
	private final static long serialVersionUID = VERSION;
	/** logger */
	@SuppressWarnings("unused")
	private final static Logger logger = Logger.getLogger(DataRoot.class
			.getPackage().getName() + ".DataRoot");
	



	/**
	 * TODO Comment
	 * @param parameter Parameter
	 * 
	 * @modified -
	 */
	public DataRoot(final VP_Tokenlist[] parameter) {
//		super(new GuideData(new DataGuidelineLevel(null),parameter, false));
		super(null);
		
		
		
		// final DataGuidelineLevel dgl,
		// final String name,
		// final int idx,
		// final String activity,
		// final GuideData superGuideData,
		// final boolean isDigit,
		// final boolean isRadio,
		// final boolean isNonDigit,
		// final boolean isStateGroup

	}

}




//
// /** Spieldatencontroller */
// // FIXME 21.02.2014 Testweise herausgenommen private final CDC dsc = new
// CDC();
//
// /** @return the dsc */
// // FIXME 21.02.2014 Testweise herausgenommen public final CDC getDsc() {
// // return this.dsc;
// // }
// //
// // /**
// // * @param dataSetController
// // * the DataSetController to set
// // */
// // public final void setDsc(final DataSetController dataSetController) {
// // this.dsc = dataSetController;
// // }
// // ---- Transfertabelle
// ---------------------------------------------------
//
// /** Transfertabelle mit den Slots */
// // FIXME 21.02.2014 Testweise herausgenommen private
// TabelleRoleSlotNation[] trsn;
//

//
//
// private final static void subTstString(final DataGuidelineLevel dgl,
// List<String> lsb){
// StringBuilder sb = new StringBuilder();
// for(int i = 0; i<3*dgl.getDatenklasse().getDepth();i++)sb.append('.');
// sb.append("... ");
// String nameidx = dgl.getDatenklasse()+" ";
//
// for(CoreData cd : dgl.registerCoreData){
// String sg = "State: "+gdc.getName(gdc.getSuperGuideData(cd.guidedata))+
// "("+gdc.getIDX(gdc.getSuperGuideData(cd.guidedata))+") "+
// gdc.getActivity(gdc.getSuperGuideData(cd.guidedata))+" ";
//
// String guide = "Guide: "+gdc.getName(cd.guidedata)+
// "("+gdc.getIDX(cd.guidedata)+") "+gdc.getActivity(cd.guidedata)+" Core: ";
// Object y = "";
// if(gdc.isConst(cd.guidedata))
// y = " Erg: "+gdc.getY0((GuideConstData) cd.guidedata);
//
//
// lsb.add(sb.toString()+nameidx+sg+guide+cd.uid+y);
//
// }
// lsb.add(sb.toString());
// //
// // String stateidx = null;
// // if(gdc.getSuperGuideData(cd.guidedata)==null)
// // stateidx = " n.a.";
// // else
// // stateidx =
// gdc.getName(gdc.getSuperGuideData(cd.guidedata))+"("+gdc.getIDX(gdc.getSuperGuideData(cd.guidedata))+")";
// // lsb.add(sb+nameidx+": "+
// //
// gdc.getActivity(cd.guidedata)+" UID:"+cd.uid+" Stategroup:"+stateidx+" des "+gdc.getDGL(cd.guidedata).getDatenklasse().getTyp()+"."+gdc.getDGL(cd.guidedata).getDatenklasse().getDepth());
// //
// //
// lsb.add(sb.toString()+"Core: Anzahl der registrierten Daten: "+dsc.getRegisterCoreDataCounter(cd));
// for(DataGuidelineLevel childDGL: dgl.getChildDGL()){
// subTstString(childDGL, lsb);
// }
// }
// /**
// * Baut für JUnit einen Teststring zusammen
// *
// * @see JUnit
// * @param game
// * das aktuelle Game
// * @return der Teststring
// *
// */
// public static String[] tstString(DataRoot game) {
// List<String> lsb = new ArrayList<String>();
// MetaStateGuideline msg = game.getMetaStateGuideline();
// DataGuidelineLevel dgl = game.getDataGuidelineLevel();
//
// lsb.add("DATA");
// lsb.add("---------");
// lsb.add("");
// subTstString(dgl, lsb);
//
//
// // lsb.add("Erfasste DataGuidelineLevel:");
// // lsb.add("Liste aller Ebenen!");
// // List<DataGuidelineLevel> liste = new ArrayList<DataGuidelineLevel>();
// // msg.getAllDH(liste, msg.getDH());
// // for (DataGuidelineLevel dgl : liste) {
// // lsb.add(new String("... [" + dgl.getDatenklasse().getTyp() + "."
// // + dgl.getDatenklasse().getDepth() + "]"));
//
// // dsc.getgdc..
// // lsb.add(new String("...... Anzahl der Stategruppen: "
// // + dgl.numberOfStageGroupGuidelines()));
// // for (int idx = 0; idx < dgl.numberOfStageGroupGuidelines(); idx++) {
// // StateGroupGuideline sgg = dgl.getStageGroupGuideline(idx);
// // lsb.add(new String("...... [" + gdc.getName(sgg) + "("
// // + gdc.getIDX(sgg) + "): " + gdc.getActivity(sgg) + "]"));
// // lsb.add(new String("......... Anzahl der States: "
// // + gtc.getNumberOfStates(sgg)));
// //
// // lsb.add("......... Erfasste Gruppentermine: "
// // + gtc.getNumberOfIGLTermine(sgg));
// // // Termine, an denen die States aktualisiert werden.
// // // Ist nur notwendig, bei States, die ihre Zustandswerte ändern
// // // können
// // // und termingesteuert sind.
// // for (String temin : gtc.getAll_IGL_Terminevent(sgg))
// // lsb.add("......... State-Termin@ [" + temin + "]");
// //
// // for (GuideData gd : gtc.gglValues(sgg)) {
// // // TODO Const in Single umbenennen
// // if (gdc.isConst(gd)) {
// // lsb.add(new String("......... Const: ["
// // + gdc.getName(gd) + "(" + gdc.getIDX(gd)
// // + "): " + gdc.getActivity(gd) + "]"));
// // lsb.add(new String("............ y0 = ["
// // + gdc.getY0((GuideConstData) gd) + "]"));
// // }
// //
// // if (gdc.isRadio(gd)) {
// // lsb.add(new String("......... Radio: ["
// // + gdc.getName(gd) + "(" + gdc.getIDX(gd)
// // + "): " + gdc.getActivity(gd) + "]"));
// // lsb.add(new String("............ uk = ["
// // + gdc.getUk((GuideRadioData) gd) + "]"));
// // lsb.add(new String("............ y0 = "
// // + gdc.getRadioliste((GuideRadioData) gd)));
// // }
// //
// // if (gdc.isDigit(gd)) {
// // lsb.add(new String("......... Digit: ["
// // + gdc.getName(gd) + "(" + gdc.getIDX(gd)
// // + "): " + gdc.getActivity(gd) + "]"));
// // GuideDigitData vtx = (GuideDigitData) gd;
// // // lsb.add("......... [" + gdc.getName(igl) + "("
// // // + gdc.getIDX(igl) + ")]: [" + gdc.getActivity(igl) +
// // // "]");
// // // // lsb.add("............einheit [" + vtx.getEinheit()
// // // +
// // // // "]");
// // //
// // // lsb.add("............clazz/Group ["
// // // +
// // //
// igl.getDGL().getDatenklasse().getTyp()+"."+igl.getDGL().getDatenklasse().getDepth()
// // // + "]/["
// // // + gdc.getName(igl) + "("
// // // + gdc.getIDX(igl) + ")]");
// // lsb.add("............Y-Range: ["
// // + gdc.getMinYValue(vtx) + "] ... ["
// // + gdc.getMaxYValue(vtx) + "]");
// // lsb.add("............priority: ["
// // + gdc.getPriority(vtx) + "]");
// // // // lsb.add("............vertexName ["
// // // // + vtx.getIGL_Index().igl_name + "]");
// // // // lsb.add("............vtxnr [" +
// // // vtx.getIGL_Index().igl_idx
// // // // + "]");
// // lsb.add("............y0 [" + gdc.getY0(vtx) + "]");
// //
// // lsb.add("............GeberPass1Description ["
// // + gdc.getSollwertScript(vtx)[GDC.AKTIV] + "]");
// // lsb.add("............GeberDescription ["
// // + gdc.getGeberDescription(vtx)[GDC.AKTIV] + "]");
// // lsb.add("............GeberPass1Description ["
// // + gdc.getSollwertScript(vtx)[GDC.PASSIV] + "]");
// // lsb.add("............GeberDescription ["
// // + gdc.getGeberDescription(vtx)[GDC.PASSIV]
// // + "]");
// //
// // lsb.add("............Strecke ["
// // + gdc.getStreckeDescription(vtx)[GDC.AKTIV]
// // + "]");
// // lsb.add("............Strecke ["
// // + gdc.getStreckeDescription(vtx)[GDC.PASSIV]
// // + "]");
// //
// // lsb.add("............Regler ["
// // + gdc.getReglerDescription(vtx)[GDC.AKTIV]
// // + "]");
// // lsb.add("............Regler ["
// // + gdc.getReglerDescription(vtx)[GDC.PASSIV]
// // + "]");
// //
// // lsb.add("............Abtastungen Strecke ["
// // + gdc.getStreckeK(vtx)[GDC.AKTIV] + "]");
// // lsb.add("............Abtastungen Strecke ["
// // + gdc.getStreckeK(vtx)[GDC.PASSIV] + "]");
// // // lsb.add("............StreckeKtoString(true) ["
// // // + gdc.getStreckeKtoString(vtx)[GDC.AKTIV] + "]");
// // // lsb.add("............StreckeKtoString(false) ["
// // // + gdc.getStreckeKtoString(vtx)[GDC.PASSIV] + "]");
// //
// // lsb.add("............Abtastungen Regler ["
// // + gdc.getReglerK(vtx)[GDC.AKTIV] + "]");
// // lsb.add("............Abtastungen Regler ["
// // + gdc.getReglerK(vtx)[GDC.PASSIV] + "]");
// // // lsb.add("............ReglerKtoString(true) ["
// // // + dgdc.getReglerKtoString(vtx, true) + "]");
// // // lsb.add("............ReglerKtoString(false) ["
// // // + dgdc.getReglerKtoString(vtx, false) + "]");
// // // }
// // // }
// // // }
// // //
// // lsb.add("............Trigger ["
// // + gdc.getTriggerlisteAdressen(vtx) + "]");
// // }
// // }
// // lsb.add(new String("......... Anzahl der Contexte: "
// // + gtc.getNumberOfContexte(sgg)));
// // lsb.add("......... Erfasste Transitiontermine f�r Contexte: "
// // + gtc.getNumberOfCTGTermine(sgg));
// // // Termine, an denen die States aktualisiert werden.
// // // Ist nur notwendig, bei States, die ihre Zustandswerte ändern
// // // können
// // // und termingesteuert sind.
// // for (String temin : gtc.getAll_CTG_Terminevent(sgg))
// // lsb.add("......... Context-Transition-Termin@ [" + temin
// // + "]");
// // // "Termine, an denen die Contexte der States aktualisiert werden "
// // // und sich andere Transitionen ergeben können.
// // // Ist nur notwendig, bei Contexten, die termingesteuert sind.
// //
// // lsb.add("......... Erfasste TransitionStates f�r Contexte: "
// // + gtc.getNumberOfCTGStates(sgg));
// // for (PreAddress adresse : gtc.getAll_CTG_Stateevent(sgg))
// // lsb.add("......... Context-Transition-State@ [" + adresse +"]");
// //
// // for (GuideData gd : gtc.cglValues(sgg)) {
// // ContextGuideline cg = (ContextGuideline) gd;
// // lsb.add(new String("......... Context: [" + gdc.getName(gd)
// // + "(" + gdc.getIDX(gd) + "): "
// // + gdc.getActivity(gd) + "]"));
// // CTransitionGuideline ctg = gtc.getCTG(cg);
// // if (ctg != null) {
// //
// // lsb.add("............ CTransition(ID): ["
// // + gdc.getName(ctg) + "(" + gdc.getIDX(ctg)
// // + ")]");
// // for (GuardList gl : gtc.getGuard(ctg)) {
// // lsb.add("...............gl.nr [" + gl.nr + "]");
// // for (_Guard _g : gl.guards) {
// // lsb.add("..................[" + _g.toString()
// // + "]");
// // lsb.add(".................._g.getGuardType() ["
// // + _g.getGuardType().getSimpleName()
// // + "]");
// // // lsb.add(".................._g.getVertex() ["
// // // + _g.getVertex() + "]");
// // // // }
// // // // }
// // // // }
// // }
// // }
// // }
// // lsb.add("............ Erfasste Transitiontermine f�r Vertex: "
// // + gtc.getNumberOfVTGTermine(cg));
// // for (String temin : gtc.getAll_VTG_Terminevent(cg))
// // lsb.add("............ Vertex-Transition-Termin@ [" + temin
// // + "]");
// //
// // lsb.add("............ Erfasste TransitionStates f�r Vertex: "
// // + gtc.getNumberOfVTGStates(cg));
// //
// // for (PreAddress adresse : gtc.getAll_VTG_Stateevent(cg))
// // lsb.add("............ Vertex-Transition-State@ [" + adresse
// // + "]");
// // for(GuideData vtgd : gtc.getAllVTG(cg)){
// // VTransitionGuideline vtg = (VTransitionGuideline)vtgd;
// //
// // lsb.add("............ VTransition(ID): ["
// // + gdc.getName(vtg) + "(" + gdc.getIDX(vtg)
// // + ")]");
// // for (GuardList gl : gtc.getGuard(vtg)) {
// // lsb.add("...............gl.nr [" + gl.nr + "]");
// // for (_Guard _g : gl.guards) {
// // lsb.add("..................[" + _g.toString()+ "]");
// // lsb.add(".................._g.getGuardType() ["
// // + _g.getGuardType().getSimpleName()
// // + "]");
// // // lsb.add(".................._g.getVertex() ["
// // // + _g.getVertex() + "]");
// //// // // }
// //// // // }
// //// // // }
// //// }
// // }
// // }
// // }
// // }
// // }
// // }
//
// // // lsb.add("Erfasste GGL-daten:");
// // // lsb.add("Details aller non-diskreten Zustandswerte.");
// // // for (Datenklasse clazz : msg.getAllClasses()) {
// // // lsb.add("... [" + clazz.getTyp()+"."+clazz.getDepth() + "]");
// // // for (StateGroupGuideline sg : msg.getAllStateGroupValues(clazz)) {
// // // lsb.add("...... State(ID): [" + gdc.getName(sg) + "(" +
// // // gdc.getIDX(sg) + ")] Anzahl: "
// // // + sg.gglAll().size());
// // // for(String key : sg.gglAll()){
// // // lsb.add("......... " + key + ": [" + sg.ggl_get(key) + "]");
// // //
// // // }
// // // }
// // // }
// // //
// // // lsb.add("");
// // // lsb.add("Erfasste Vertexdaten:");
// // // lsb.add("Details aller diskreten Zustandswerte.");
// // // for (Datenklasse clazz : msg.getAllClasses()) {
// // // lsb.add("... [" + clazz.getTyp()+"."+clazz.getDepth() + "]");
// // // for (StateGroupGuideline sg : msg.getAllStateGroupValues(clazz)) {
// // //
// // // lsb.add("...... State(ID): [" + gdc.getName(sg) + "(" +
// // // gdc.getIDX(sg) + ")] Anzahl: "
// // // + sg.gglAll().size());
// // //
// // // for (GuideData igl : sg.gglValues()) {
// // // if (!gdc.isVertex(igl)){
// // // continue;
// // // }
// // // GuideDigitData vtx = (GuideDigitData) igl;
// // // lsb.add("......... [" + gdc.getName(igl) + "("
// // // + gdc.getIDX(igl) + ")]: [" + gdc.getActivity(igl) + "]");
// // // // lsb.add("............einheit [" + vtx.getEinheit() +
// // // // "]");
// // //
// // // lsb.add("............clazz/Group ["
// // // +
// // //
// igl.getDGL().getDatenklasse().getTyp()+"."+igl.getDGL().getDatenklasse().getDepth()
// // // + "]/["
// // // + gdc.getName(igl) + "("
// // // + gdc.getIDX(igl) + ")]");
// // // lsb.add("............Y-Range: [" + dgdc.getMinYValue(vtx)
// // // + "] ... [" + dgdc.getMaxYValue(vtx)
// // // + "]");
// // // // lsb.add("............priority [" + vtx.getPriority() +
// // // // "]");
// // // // lsb.add("............vertexName ["
// // // // + vtx.getIGL_Index().igl_name + "]");
// // // // lsb.add("............vtxnr [" + vtx.getIGL_Index().igl_idx
// // // // + "]");
// // // lsb.add("............y0 [" + dgdc.getY0(vtx) + "]");
// // //
// // //
// // // // lsb.add("............GeberDescription(true) ["
// // // // + vtx.getGeberDescription(true) + "]");
// // // // lsb.add("............GeberDescription(false) ["
// // // // + vtx.getGeberDescription(false) + "]");
// // //
// // // // lsb.add("............sollwertGeberActiv ["
// // // // + vtx.getSollwertGeberActiv().getClass()
// // // // .getSimpleName() + "]");
// // // // lsb.add("............sollwertGeberActiv ["
// // // // + vtx.getSollwertGeberActiv() + "]");
// // //
// // // // lsb.add("............sollwertGeberPassiv ["
// // // // + vtx.getSollwertGeberPassiv().getClass()
// // // // .getSimpleName() + "]");
// // // // lsb.add("............sollwertGeberPassiv ["
// // // // + vtx.getSollwertGeberPassiv()+ "]");
// // // lsb.add("............aktives GeberScript "+
// // // Arrays.asList(dgdc.getSollwertScriptActiv(vtx)));
// // // lsb.add("............aktiver Geber ["
// // // + dgdc.getGeber(vtx, true) + "]");
// // // // lsb.add("............Geber(true) ["
// // // // + vtx.getGeber(true)
// // // // + "]");
// // //
// // // lsb.add("............passives GeberScript "+
// // // Arrays.asList(dgdc.getSollwertScriptPassive(vtx)));
// // // lsb.add("............passiver Geber [" + dgdc.getGeber(vtx, false)
// +
// // // "]");
// // // // lsb.add("............Geber(false) ["
// // // // + vtx.getGeber(false)
// // // // + "]");
// // // lsb.add("............Abtastungen Strecke(true) ["
// // // + dgdc.getStreckeK(vtx, true) + "]");
// // // lsb.add("............Abtastungen Strecke(false) ["
// // // + dgdc.getStreckeK(vtx, false) + "]");
// // // lsb.add("............StreckeKtoString(true) ["
// // // + dgdc.getStreckeKtoString(vtx, true) + "]");
// // // lsb.add("............StreckeKtoString(false) ["
// // // + dgdc.getStreckeKtoString(vtx, false) + "]");
// // //
// // // lsb.add("............Abtastungen Regler(true) ["
// // // + dgdc.getReglerK(vtx, true) + "]");
// // // lsb.add("............Abtastungen Regler(false) ["
// // // + dgdc.getReglerK(vtx, false) + "]");
// // // lsb.add("............ReglerKtoString(true) ["
// // // + dgdc.getReglerKtoString(vtx, true) + "]");
// // // lsb.add("............ReglerKtoString(false) ["
// // // + dgdc.getReglerKtoString(vtx, false) + "]");
// // // }
// // // }
// // // }
// // //
// // // lsb.add("");
// // // lsb.add("Erfasste Radiodaten:");
// // // lsb.add("Details aller statischen Zustandswerte.");
// // // for (Datenklasse clazz : msg.getAllClasses()) {
// // // lsb.add("... [" + clazz.getTyp()+"."+clazz.getDepth() + "]");
// // // for (StateGroupGuideline sg : msg.getAllStateGroupValues(clazz)) {
// // // lsb.add("...... State(ID): [" + gdc.getName(sg) + "(" +
// // // gdc.getIDX(sg) + ")] Anzahl: "
// // // + sg.gglAll().size());
// // //
// // // for (GuideData igl : sg.gglValues()) {
// // // if (!gdc.isRadio(igl))
// // // continue;
// // // RadioGuideline vtx = (RadioGuideline) igl;
// // //
// // // lsb.add("......... [" + gdc.getName(igl) + "]");
// // //
// // // lsb.add("............activity [" + gdc.getActivity(igl) + "]");
// // // lsb.add("............stategroupname ["
// // // + gdc.getName(gdc.getSuperGuideData(igl)) + "]");
// // // lsb.add("............grpnr [" +
// // // gdc.getIDX(gdc.getSuperGuideData(igl))
// // // + "]");
// // // lsb.add("............radioName ["
// // // + gdc.getName(igl) + "]");
// // // lsb.add("............vtxnr [" + gdc.getIDX(igl)
// // // + "]");
// // // lsb.add("............y0 [" + vtx.getY0() + "]");
// // // // lsb.add("............prio [" + vtx.getPriority() + "]");
// // // }
// // // }
// // // }
// // //
// // // lsb.add("");
// // // lsb.add("Erfasste Constdaten:");
// // // lsb.add("Details aller konstanten Zustandswerte.");
// // // for (Datenklasse clazz : msg.getAllClasses()) {
// // // lsb.add("... [" + clazz.getTyp()+"."+clazz.getDepth() + "]");
// // // for (StateGroupGuideline sg : msg.getAllStateGroupValues(clazz)) {
// // // lsb.add("...... State(ID): [" + gdc.getName(sg) + "(" +
// // // gdc.getIDX(sg) + ")] Anzahl: "
// // // + sg.gglAll().size());
// // //
// // // for (GuideData igl : sg.gglValues()) {
// // // if (!gdc.isConst(igl))
// // // continue;
// // // ConstGuideline vtx = (ConstGuideline) igl;
// // //
// // // lsb.add("......... Name: [" + gdc.getName(igl)
// // // + "]");
// // //
// // // lsb.add("............activity [" + gdc.getActivity(igl) + "]");
// // // lsb.add("............stategroupname ["
// // // + gdc.getName(gdc.getSuperGuideData(igl)) + "]");
// // // lsb.add("............grpnr [" +
// // // gdc.getIDX(gdc.getSuperGuideData(igl))
// // // + "]");
// // // lsb.add("............vtxnr [" + gdc.getIDX(igl)
// // // + "]");
// // // lsb.add("............y0 [" + vtx.getY0() + "]");
// // // }
// // // }
// // // }
// // //
// // // lsb.add("");
// // //
// lsb.add("------------------------------------------------------------");
// // //
// // // lsb.add("");
// // // lsb.add("Erfasste Contexttermine:");
// // // lsb
// // //
// .add("Termine, an denen die Contexte der States aktualisiert werden "
// // // + "und sich andere Transitionen ergeben können. "
// // // + "Ist nur notwendig, bei Contexten, die termingesteuert sind.");
// // // for (Datenklasse clazz : msg.getAllClasses()) {
// // // lsb.add("... [" + clazz.getTyp()+"."+clazz.getDepth() + "]");
// // // for (StateGroupGuideline sg : msg.getAllStateGroupValues(clazz)) {
// // // lsb.add("...... State(ID): [" + gdc.getName(sg) + "(" +
// // // gdc.getIDX(sg) + ")]");
// // // for (String temin : sg.getAll_CTG_Terminevent()) {
// // // lsb.add("......... Context-Calculation@ [" + temin + "]");
// // // }
// // // }
// // // }
// // // lsb.add("");
// // // lsb.add("Erfasste Contexttransitionsdaten:");
// // // for (Datenklasse clazz : msg.getAllClasses()) {
// // // lsb.add("... [" + clazz.getTyp()+"."+clazz.getDepth() + "]");
// // // for (StateGroupGuideline sg : msg.getAllStateGroupValues(clazz)) {
// // // lsb.add("...... State(ID): [" + gdc.getName(sg) + "(" +
// // // gdc.getIDX(sg) + ")] Anzahl: "
// // // + sg.cgl_values().size());
// // // for (CTransitionGuideline ctg : sg.ctg_values()) {
// // // lsb.add("......... CTransition(ID): [" + ctg.ctx_name + "("
// // // + ctg.ctx_idx + ")]");
// // // // assert false;
// // // // FIXME lsb.add("............... Guard-Class: " +
// // // // ctg.guard_clazz.getSimpleName());
// // // // assert false;
// // // // FIXME lsb.add("............... Guard-State: " +
// // // // ctg.guard_sg.stategroupname);
// // // // assert false;
// // // // FIXME if(ctg.guard_igl!=null)
// // // // lsb.add("............... Guard-Vertex: "
// // // // + ctg.guard_igl.getIGL_Index().igl_name);
// // //
// // // for (GuardList gl : ctg.getGuard()) {
// // // lsb.add("...............gl.nr [" + gl.nr + "]");
// // // for (_Guard _g : gl.guards) {
// // // lsb
// // // .add("..................[" + _g.toString()
// // // + "]");
// // // lsb.add(".................._g.getGuardType() ["
// // // + _g.getGuardType().getSimpleName() + "]");
// // // lsb.add(".................._g.getVertex() ["
// // // + _g.getVertex() + "]");
// // // }
// // // }
// // // }
// // // }
// // // }
// // //
// // // lsb.add("");
// // //
// lsb.add("------------------------------------------------------------");
// // //
// // // lsb.add("");
// // // lsb.add("Erfasste Transitiontermine:");
// // // lsb
// // //
// .add("Termine, an denen die Transitions der States aktualisiert werden");
// // // for (Datenklasse clazz : msg.getAllClasses()) {
// // // lsb.add("... [" + clazz.getTyp()+"."+clazz.getDepth() + "]");
// // // for (StateGroupGuideline sg : msg.getAllStateGroupValues(clazz)) {
// // // lsb.add("...... State(ID): [" + gdc.getName(sg) + "(" +
// // // gdc.getIDX(sg) + ")]");
// // // for (String temin : sg.getAll_VTG_Terminevent()) {
// // // lsb
// // // .add("......... Transition-Calculation@ [" + temin
// // // + "]");
// // // }
// // // }
// // // }
// // //
// // // lsb.add("");
// // // lsb.add("Erfasste Transitiondaten:");
// // // lsb.add("Details aller Transitions");
// // // for (Datenklasse clazz : msg.getAllClasses()) {
// // // lsb.add("... [" + clazz.getTyp()+"."+clazz.getDepth() + "]");
// // // for (StateGroupGuideline sg : msg.getAllStateGroupValues(clazz)) {
// // // lsb.add("...... State(ID): [" + gdc.getName(sg) + "(" +
// // // gdc.getIDX(sg) + ")] Anzahl: "
// // // + sg.cgl_values().size());
// // // for (ContextGuideline ctx : sg.cgl_values()) {
// // // lsb.add("......... Context(ID): [" + ctx.ctx_name + "("
// // // + ctx.ctx_idx + ")]");
// // // for (VTransitionGuideline vtg : ctx.vtg_values()) {
// // // lsb.add("............ VTransition(ID): ["
// // // + vtg.vtg_name + "(" + vtg.vtg_idx + ")]");
// // // // assert false;
// // // // FIXME assert vtg.getGuard() != null
// // // // && !vtg.getGuard().isEmpty() : vtg.getGuard();
// // // for (GuardList gl : vtg.getGuard()) {
// // // lsb.add("...............gl.nr [" + gl.nr + "]");
// // // for (_Guard _g : gl.guards) {
// // // lsb.add("..................[" + _g.toString()
// // // + "]");
// // // lsb.add(".................._g.getGuardType() ["
// // // + _g.getGuardType().getSimpleName()
// // // + "]");
// // // lsb.add(".................._g.getVertex() ["
// // // + _g.getVertex() + "]");
// // // }
// // // }
// // // }
// // // }
// // // }
// // // }
// // //
// // //
// lsb.add("XXX");
// return lsb.toArray(new String[0]);
// }
//
//
// /**
// * @return the roles
// * <p>
// * NUR FÜR JAVABEANS SAVE/LOAD
// *
// */
// // FIXME 21.02.2014 Testweise herausgenommen public final
// TabelleRoleSlotNation[] getTrsn() {
// // assert false;
// // return this.trsn;
// // }
//
// // /**
// // * @param trsn
// // * the roles to set
// // *
// // * <p>
// // * NUR FÜR JAVABEANS SAVE/LOAD
// // */
// // public final void setTrsn(final TabelleRoleSlotNation[] trsn) {
// // assert false;
// // this.trsn = trsn;
// // }
// //
// // /**
// // * Anlage der TRSN für Tabelle der Spieler/Nationen Slots.
// // * (Welcher Spieler mit welcher Nation)
// // * @param count Anzahl der Slots
// // */
// // public final void setTRSN(final int count) {
// // if(LOGGER)
// logger.debug("... Lege für "+count+" Slot(s) die TRSN an.");
// // this.trsn = new TabelleRoleSlotNation[count];
// // }
// //
// //// public final void setTRSNEntry(final int slotnr, final long
// nationUID) {
// // public final void setTRSNEntry(final int slotnr, final short
// nationUID) {
// // if(LOGGER)
// logger.debug("... Ordne Slot Nr.:["+slotnr+"] die UID ["+nationUID+"] zu.");
// // this.trsn[slotnr] = new TabelleRoleSlotNation();
// // // this.trsn[index].setSlot(index);
// // this.trsn[slotnr].setRoleUID(-1L);
// // this.trsn[slotnr].setNationUID(nationUID);
// // }
// //
// // /** @return Anzahl der Einträge */
// // public final int getTrsnEntries() {
// // assert false;
// // return this.trsn.length;
// // }
// //
// // /**
// // * @param slotnr
// // * Nummer des Slots
// // * @return UID der Rolle
// // */
// // public final long getTrsnRoleUID(final int slotnr) {
// // assert false;
// // return this.trsn[slotnr].getRoleUID();
// // }
// //
// // /**
// // * @param slotnr
// // * Nummer des Slots
// // * @return UID der Nation
// // */
// // public final long getTrsnNationUID(final int slotnr) {
// // assert false;
// // return this.trsn[slotnr].getNationUID();
// // }
//
// // /**
// // * @param group
// // * ist die gewünschte Gruppe
// // *
// // * @return Liefert das Regelwerk aller States einer Gruppe
// // */
// // public final StateGroup getStateGroup(final Data data, final String
// // group) {
// // return this.msgl.getStateGroup(data.getClass(), group);
// // }
// //
// // /**
// // * @param group
// // * ist die gewünschte Gruppe
// // *
// // * @return Liefert das Vertex-Regelwerk aller States einer Gruppe
// // */
// // public final VertexGuidelines getVertexGuidelines(final Data
// data,final
// // String group) {
// // return this.msgl.getVertexGuidelines(data.getClass(),group);
// // }
//
// /**
// * Liefert ein spezifiziertes Vertex-Regelwerk
// * @param data
// * der Datensatz
// * @param grpname
// * ist Bezeichnung für die zusammengehörige Gruppe an States
// * @param sttidx
// * ist Name eines States in diesem Kontext
// * @return das Regelwerk
// */
// // public final IGuideline getVertexGuideline(final Data data,
// // final String grpname, final String vtxname) {
// // if (grpname == null)
// // ErrApp.STATEGUIDELINEFATAL.erraufruf("grpname ist null!");
// //
// // if (this.msg.getStateGroup(data.getClass(), grpname) == null)
// // ErrApp.STATEGUIDELINEFATAL.erraufruf("grpname [" + grpname
// // + "] not found!");
// //
// // if (vtxname == null)
// // ErrApp.STATEGUIDELINEFATAL.erraufruf("vtxname ist null!");
// //
// // if (this.msg.getStateGroup(data.getClass(), grpname)
// // .getVertexGuidelines().getEntry(vtxname) == null){
// // ErrApp.STATEGUIDELINEFATAL.erraufruf("vtxname [" + vtxname
// // + "] not found!");
// // }
// // return this.msg.getStateGroup(data.getClass(), grpname)
// // .getVertexGuidelines().getEntry(vtxname);
// // }
//
// // public final IGuideline getRadioGuideline(final Data data,
// // final String grpname, final String vtxname) {
// // if (grpname == null)
// // ErrApp.STATEGUIDELINEFATAL.erraufruf("grpname ist null!");
// //
// // if (this.msg.getStateGroup(data.getClass(), grpname) == null)
// // ErrApp.STATEGUIDELINEFATAL.erraufruf("grpname [" + grpname
// // + "] not found!");
// //
// // if (vtxname == null)
// // ErrApp.STATEGUIDELINEFATAL.erraufruf("vtxname ist null!");
// //
// // if (this.msg.getStateGroup(data.getClass(),
// grpname).getRadioGuidelines()==null){
// // System.err.println(data.getClass());
// // System.err.println(grpname);
// // System.err.println(this.msg.getStateGroup(data.getClass(),
// grpname).getRadioGuidelines());
// // assert false;
// // ErrApp.STATEGUIDELINEFATAL.erraufruf("Radios [" +
// data.getClass().getSimpleName()+"."+grpname
// // + "] not found!");
// //
// // }
// // if (this.msg.getStateGroup(data.getClass(), grpname)
// // .getRadioGuidelines().getEntry(vtxname) == null){
// // ErrApp.STATEGUIDELINEFATAL.erraufruf("Radioname [" + vtxname
// // + "] not found!");
// // }
// // return this.msg.getStateGroup(data.getClass(), grpname)
// // .getRadioGuidelines().getEntry(vtxname);
// // }
// // public final IGuideline getGuideline(final CoreData data,
// // final int grpidx, final int sttidx) {
// //FIXME // if (grpname == null)
// //// ErrApp.STATEGUIDELINEFATAL.erraufruf("grpname ist null!");
// //
// // if (this.msg.getStateGroup(data.getClass(), grpidx) == null)
// // ErrApp.STATEGUIDELINEFATAL.erraufruf("grpname [" + grpidx
// // + "] not found!");
// //
// // if (sttidx <0)
// // ErrApp.STATEGUIDELINEFATAL.erraufruf("vtxname ist null!");
// //
// // if (this.msg.getStateGroup(data.getClass(),
// grpidx).getGuidelines()==null){
// // System.err.println(data.getClass());
// // System.err.println(grpidx);
// // System.err.println(this.msg.getStateGroup(data.getClass(),
// grpidx).getGuidelines());
// // assert false;
// // ErrApp.STATEGUIDELINEFATAL.erraufruf("Radios [" +
// data.getClass().getSimpleName()+"."+grpidx
// // + "] not found!");
// //
// // }
// // if (this.msg.getStateGroup(data.getClass(), grpidx)
// // .getGuidelines().getEntry(sttidx) == null){
// // ErrApp.STATEGUIDELINEFATAL.erraufruf("Radio/Vertexname [" + sttidx
// // + "] not found!");
// // }
// // Collection <StateGroupGuideline> csg =
// this.msg.getAllStateGroupValues(dsc.getDatenklasse(data));
// // StateGroupGuideline sg = (StateGroupGuideline) csg.toArray()[grpidx];
// // return (IGuideline) sg.gglValues().toArray()[sttidx];
// // }
// //
// // /**
// // * @param data
// // * der Datensatz
// // * @return Liefert das Regelwerk aller States und deren Contexte für
// // Personen
// // */
// // public final Transition getTransition(final Data data, String meta,
// // String group, String state) {
// // assert group != null;
// // assert this.msgl.getStateGroup(data.getClass(),meta) != null :
// // "No Group: ";
// // assert state != null;
// // assert
// //
// this.msgl.getStateGroup(data.getClass(),meta).getTransitionContexttable().get(group)
// // != null : "No State: "
// // + state;
// // return
// //
// this.msgl.getStateGroup(data.getClass(),meta).getTransitionContexttable().get(group)
// // .getTransition(state);
// // }
// //
// // /**
// // * @param group
// // * @return
// // */
// // public final Set<String> getListOfStates(final Data data, String
// group) {
// // return
// //
// this.msgl.getStateGroup(data.getClass(),group).getVertexGuidelines().getVertexes();
// // }
//

// // @Override
// // public boolean tstReady() {
// // // this.setReady(false);
// // // if (this.getUid() == -1L)
// // // return false;
// // // if (this.getName() == null)
// // // return false;
// // // if (this.getShortname() == null)
// // // return false;
// // // // if (this.description == null)
// // // // return false;
// // // // if (this.author == null)
// // // // return false;
// // // // if (this.actualdate == null)
// // // // return false;
// // // // if (this.playerUID == null)
// // // // return false;
// // // this.setReady(true);
// // return true;
// // }
// //
// // // ---- Getter / Setter
// // ------------------------------------------------------
//
// // /**
// // * Setzt Start- und aktuelles Datum Kann nur einmal gesetzt werden!
// // *
// // * @param wann
// // * Startdatum als [DD.MM.YYYY HH:MM:SS]"
// // */
// // public final void setStartdate(final String wann) {
// // this.tc.setStartdate(wann);
// // }
// //
// // /**
// // * Setzt das Endedatum
// // *
// // * @param wann
// // * Endedatum als [DD.MM.YYYY HH:MM:SS]"
// // */
// // public final void setEnddate(final String wann) {
// // this.tc.setEnddate(wann);
// // }
// //
// // public final void setTestCounter(final int counts, final
// IExecuteTermin ext) {
// // this.tc.setTestCounter(counts, ext);
// // }
// //
// // /**
// // * @return das Startdatum
// // */
// // public final CPiT getStartdate() {
// // return this.tc.getStartdate();
// // }
// //
// // /**
// // * @return das Endedatum oder <code>null</code> bei 'Open End'
// // */
// // public final CPiT getEnddate() {
// // return this.tc.getEnddate();
// // }
// //
// // /**
// // * @return das aktuelle Datum
// // */
// // public final CPiT getActualdate() {
// // return this.tc.getActualdate();
// // }
// //
// // /**
// // * @param step
// // * gewünschter maximale Zeitschritte der Termine.<br>
// // * Bei 0L wird der maximal mögliche Step angenommen.
// // */
// // public final void setSollstep(final long step) {
// // this.tc.setSollstep(step);
// // }
// //
// // /**
// // * @param step
// // * Kalkulierter Timerstep ( <= Sollstep)
// // */
// // public final void setTimerstep(final long step) {
// // this.tc.setTimerstep(step);
// // }
// //
// // /**
// // * Set the Timerspeed.
// // *
// // * @param sleep
// // * the Timerspeed (default: 1s)
// // */
// // public final void setSleep(final long sleep) {
// // this.tc.setSleep(sleep);
// // }
// //
// // /** Starten des Timerthreads */
// // public final void start() {
// // if(LOGGER) logger.trace("Start the game");
// // this.tc.start();
// // }
// //
// // /**
// // * Beenden des TimeThreads nach x Millisekunden.
// // *
// // * @param millis
// // * stop after x milliseconds or 0L for never
// // */
// // public void interruptafter(final long millis) {
// // this.tc.interruptafter(millis);
// // }
// //
// // public final void schedularstart() {
// // this.tc.schedularstart();
// // }
// //
// // /** Starten des Schedulars */
// // public final void schedularpause() {
// // this.tc.schedularpause();
// // }
// //
// // /**
// // * @return Liste der etablierten Termine
// // */
// // public final List<String> showSchedular() {
// // return this.tc.showSchedular();
// // }
// //
// // /**
// // * @return Liste der neuen Termine (Kandidaten)
// // */
// // public final List<String> showNeuerTermin() {
// // return this.tc.showNeuerTermin();
// // }
// //
// // /**
// // * Die Löschaufträge anzeigen
// // *
// // * @return Die Liste der Löschaufträge
// // */
// // public final List<String> showDeleteTermin() {
// // return this.tc.showDeleteTermin();
// // }
// //
// // /**
// // * Erzeugt einen neuen Event vom Typ {@link IEvent.Typen#} und fügt ihn
// in
// // * die Terminliste ein
// // *
// // * @param reporter
// // * Reportbericht
// // * @param reportnumber
// // * Reportnummer (frei, je nach Report)
// // * @param was
// // * Name/Bezeichnung des Events
// // * @param wann
// // * ist der Termin, zudem das Event ausgeführt werden soll.
// // * <ul>
// // * <li>Form [TAG DD.MM.YYYY HH:MM:SS SHIFT].</li>
// // * <li>Periodische Anweisungen sind mit [*] einzutragen.</li>
// // * <li>[TAG] muss nicht gesetzt sein, dann gilt jeder Wochentag.</li>
// // * <li>[SHIFT] wird nicht periodisiert.</li>
// // * </ul>
// // * @param wie
// // * die ausführende Klasse bei Eventeintritt
// // */
// // public void createTermin(final Reporter reporter,final int
// reportnumber, final String was,
// // final PPiT /*String*/ wann, final IExecuteTermin wie) {
// // try {
// // this.tc.createTermin(reporter, reportnumber, was, wann, wie);
// // } catch (DatumsException e) {
// // e.printStackTrace();
// // assert false;
// // }
// //
// // }
// //
// // /**
// // * Erzeugt einen neuen Event vom Typ {@link IEvent.Typen#} und fügt ihn
// in
// // * die Terminliste ein
// // *
// // * @param reporter
// // * Reportbericht
// // * @param reportnumber
// // * Reportnummer (frei, je nach Report)
// // * @param was
// // * Name/Bezeichnung des Events
// // * @param wann
// // * Eventdatum
// // * @param bis
// // * ist das Endetermin des (periodischen) Events in vollständiger
// // * Form
// // * @param wann
// // * Eventdatum
// // * @param wie
// // * die ausführende Klasse bei Eventeintritt
// // */
// // public void createTermin(final Reporter reporter, final int
// reportnumber, final String was,
// // final PPiT /*String*/ wann, final CPiT bis, final IExecuteTermin wie)
// {
// // try {
// // this.tc.createTermin(reporter, reportnumber, was, wann, bis, wie);
// // } catch (DatumsException e) {
// // // TODO Auto-generated catch block
// // e.printStackTrace();
// // }
// //
// // }
// //
//  public void tst() {
//  super.tst("Rootdaten");
//  System.err.println("UID        : "+uid);
//  System.err.println("MSG        : "+msg.toString());
//  System.err.println("TC         : "+tc.toString());
//  System.err.println("TRSN       : "+trsn);
 // TRSN
// 
//  }
//
// // // public final void addNation(DataNation nation){
// // // System.out.println("-->"+nation.getShortname());
// // // this.setCounterRegister(this.getCounterRegister()
// // +1);
// //
// //
// //
// // //
// // //// Loads Lua script for this race.
// // // LoadScript script = new
// // LoadScript(ConstValue.devPath +
// // "scripts"+ConstValue.FS+"nations"
// // // + ConstValue.FS + nation + ".lua");
// // // DataNation dn = new DataNation();
// // // System.out.println("DN----DN");
// // // script.runScriptFunction("create", dn);
// // // System.out.println("DN----DN");
// // //
// // //
// // // }
// //
// // // public final String getNationPath(){
// // // return ConstValue.devPath +
// // "scripts"+ConstValue.FS+"nations"+ConstValue.FS;
// // // }
// // // /**
// // // * Initiales Start- und Aktuelles Datum einstellen
// // // * @param startdate
// // // * das Startdatum
// // // * @param lua
// // // * die LUA-Datei, die ausgeführt wird.
// // // */
// // // public final void setStartdate(String startdate){
// // // this.getTc().setStartdate(startdate);
// // //
// // // }
// // // --------------------_>>>>>>>
//
// // public void setSGLValue(Data data) {
// //
// //
// //
// // }
//
// // //
// // // /**
// // // * @return the autosave
// // // */
// // // public final boolean isAutosave() {
// // // return this.autosave;
// // // }
// // //
// // // /**
// // // * @param autosave
// // // * the autosave to set
// // // */
// // // public final void setAutosave(boolean autosave) {
// // // this.autosave = autosave;
// // // }
//
// // private String description = null;
// // /** @return the description */
// // public final String getDescription() {
// // return this.description;
// // }
// //
// // /**
// // * @param description
// // * the description to set
// // */
// // public final void setDescription(String description)
// // {
// // this.description = description;
// // }
// // private String shortname = null;
// // /** @return the description */
// // public final String getShortname() {
// // return this.shortname;
// // }
// //
// // /**
// // * @param description
// // * the description to set
// // */
// // public final void setShortname(String shortname) {
// // this.shortname = shortname;
// // }
// // private String name = null;
// // /** @return the description */
// // public final String getName() {
// // return this.name;
// // }
// //
// // /**
// // * @param name
// // * the name to set
// // */
// // public final void setName(String name) {
// // this.name = name;
// // }
// // //
// // // /**
// // // * @return the leader
// // // */
// // // public final DataPlayer getLeader() {
// // // return this.leader;
// // // }
// // //
// // // /**
// // // * @param leader
// // // * the leader to set
// // // */
// // // public final void setLeader(DataPlayer leader) {
// // // this.leader = leader;
// // // }
// // public String toString(){
// // return null;
// //
// // }
