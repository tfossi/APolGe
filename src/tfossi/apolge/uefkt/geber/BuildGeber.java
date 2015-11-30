/**
 * 
 */
package tfossi.apolge.uefkt.geber;

import static tfossi.apolge.common.constants.ConstValue.LOGGER;
import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import tfossi.apolge.common.scripting.vp.VP_ArrayTokenlist;
import tfossi.apolge.common.scripting.vp.VP_Tokenlist;
import tfossi.apolge.data.guide.SGD_Cntrl;

/**
 * Sollwertgeber erstellen Nur für kontinuierliche States
 * 
 * <pre>
 * Const:	y = kp *f(IndC, IndS, IndV)
 * Delta:	y = Y0 + kp * ð(k-K0, TV, K, IndC, IndS, IndV)
 * Ramp:	y = Y0 + kp * 1(1ð, k-K0, K, IndC, IndS, IndV)
 * Sinus:	y = Y0 + kp * sin(k-K0, K, IndC, IndS, IndV)
 * Euler:	y = Y0 + kp * e(k-K0, K, IndC, IndS, IndV)
 * </pre>
 *
 * @author tfossi
 * @version 26.01.2015
 * @modified -
 * @since Java 1.6
 */
public class BuildGeber {
	// private final static CDC cdc = new CDC();
	// private final static GDC gdc = new GDC();

	// // private final static DigitGDC dgdc = new DigitGDC();
	//
	/**
	 * Map der Sollwertparameter und deren Default-Werte
	 * <ol>
	 * <li>?0 [Default: Const]</li>
	 * <li>OC [Default: null]</li>
	 * <li>OS [Default: null]</li>
	 * <li>OV [Default: null]</li>
	 * <li>kp [Default: new Double(1.0)]</li>
	 * <li>K [Default: new Integer(-1)]</li>
	 * <li>K0 [Default: new Integer(0)]</li>
	 * <li>Y0 [Default: new Double(0)]</li>
	 * <li>TV [Default: new Integer(50)]</li>
	 * </ol>
	 * Hinweis: Unbedingt LinkedHashMap, da die Reihenfolge der Parameter NICHT
	 * egal ist!
	 */
	static Map<String, Object> sollwertParameterStructure = new LinkedHashMap<String, Object>();

	// Sollwertparameter initieren
	static {
		sollwertParameterStructure.put("?0", "Const");
		// sollwertParameterStructure.put("OC", null);
		// sollwertParameterStructure.put("OS", null);
		// sollwertParameterStructure.put("OV", null);

		sollwertParameterStructure.put("Adr", null);
		sollwertParameterStructure.put("kp", new Double(1.0));
		sollwertParameterStructure.put("K", new Integer(-1));
		sollwertParameterStructure.put("K0", new Integer(0));
		sollwertParameterStructure.put("Y0", new Double(0));
		sollwertParameterStructure.put("TV", new Integer(50));
	}

	// //
	// // private final static CDC dsc = new CDC();
	// //
	/**
	 * Lese PASS1-Sollwertscript ein und trage die Parameter des Sollwertgebers
	 * ein.
	 * 
	 * FIXME 25.02.2014
	 * @param solldata  APO-Script Table mit den Sollwerten
	 * @param code 
	 * code SOLLA oder SOLLP	 
	 * @return Parameter des Sollwertgebers
	 */
	public static final GeberParameter buildGeberScriptGuideline(
			VP_ArrayTokenlist<?> solldata, SGD_Cntrl.STATEGROUPATTRIBUTES code) {
//		if (LOGGER)
//			logger.trace(solldata);

		GeberParameter gp = new GeberParameterHashMap();
		
		if (solldata.isTable()) {

			for (String key : sollwertParameterStructure.keySet()) {

				if (solldata.getTable().containsKey(code.name() + "." + key)) {
//					if (LOGGER)
//						logger.trace("FOUND " + key + " in Solldata " + solldata);
					gp.put(key, solldata.getTable().get(code.name() + "." + key));
				} else {
//					if (LOGGER)
//						logger.trace("DEFAULT " + key + " in Solldata " + solldata);
					gp.put(key, sollwertParameterStructure.get(key));
				}
			}
			if (LOGGER)
			logger.trace(gp);

			return gp;
		}
		for (String key : sollwertParameterStructure.keySet()) {

			if ("kp".equals(key)) {
//				if (LOGGER)
//					logger.trace("FOUND " + key + " in Solldata " + solldata.getValue()+" "+code.name());
				gp.put(key, solldata.getValue());
			} else {
//				if (LOGGER)
//					logger.trace("DEFAULT " + key + " in Solldata " + solldata);
				gp.put(key, sollwertParameterStructure.get(key));
			}
		}
		if (LOGGER)
		logger.trace(gp);

		return gp;

	}

	/**
	 * Liest die PASS1-Scriptdaten nach Vorlage der
	 * {@link #sollwertParameterStructure} für eine abstrakte Vorlage/Template
	 * eines Gebers ein. FIXME 25.02.2014
	 *
	 * @param solldata APO-Script Table mit den Sollwerten
	 * @param pre ???
	 * @return Parameter des Sollwertgebers
	 * @modified - 
	 */
	@SuppressWarnings("unused")
	private static final GeberParameter readGeberScript(
			final VP_Tokenlist solldata // geberTable
			, String pre) {

		GeberParameter gp = new GeberParameterHashMap();

		for (String key : sollwertParameterStructure.keySet()) {

			if (solldata.getTable().containsKey(pre + "." + key)) {
				if (LOGGER)
					logger.trace("FOUND " + key + " in Solldata " + solldata);
				gp.put(key, solldata.getTable().get(pre + "." + key));
			} else {
				if (LOGGER)
					logger.trace("DEFAULT " + key + " in Solldata " + solldata);
				gp.put(key, sollwertParameterStructure.get(key));
			}
		}

		return gp;
	}

	// /**
	// * Erstellt den Sollwertgeber für die Vertex
	// *
	// * @param game
	// * aktuelles Game
	// */
	// public synchronized final static void buildVertexGeber(final IDataRoot
	// game) {
	// logger.info(LOGTAB + "----- Geber -----" + LOGTAB
	// + "Es werden die Sollwertgeber für die States" + LOGTAB
	// + "vom Typ [DIGIT] erstellt." + LFCR);
	//
	// MetaStateGuideline msg = game.getMetaStateGuideline();
	//
	// // Alle Klassen durchgehen
	// // // // Class<? extends Data>
	// for (Datenklasse clazz : msg.getAllClasses()) {
	// if (LOGGER)
	// logger.debug("[" + clazz + "]");
	//
	// // Alle Stategroups durchgehen
	// for (StateGroupGuideline sg : msg.getAllStateGroupValues(clazz)) {
	// if (LOGGER)
	// logger.debug("... Group: [" + gdc.getName(sg) + "("
	// + gdc.getIDX(sg) + ")]");
	//
	// // Betrifft nur Vertex!
	// if (!gdc.isDigit(sg)) {
	// if (LOGGER)
	// logger.debug("...... Geber nur für DIGIT!");
	// continue;
	// }
	// assert false;
	// // if
	// //
	// (!VertexGuideline.class.equals(sg.getDGL().getDatenklasse().getTyp())){
	// // if (LOGGER)
	// // logger.debug("Geber nur für Vertex!");
	// // continue;
	// // }
	//
	// // Alle States der Gruppe durchgehen
	// // for (GuideData igl : sg.gglValues()) {
	// // // GuideDigitData vtx = (GuideDigitData) igl;
	// // // if (LOGGER)
	// // // logger.debug("......... Geberscript erstellen."
	// // // + LOGTAB + "Klasse: [" + clazz
	// // // + "]" + LOGTAB + "Gruppe: ["
	// // // + gdc.getName(gdc.getSuperGuideData(igl)) + "("
	// // // + gdc.getIDX(gdc.getSuperGuideData(igl)) + ")" + LOGTAB
	// // // + "Vertex: [" + gdc.getName(igl)
	// // // + "(" + gdc.getIDX(igl) + ")]");
	// // //
	// // // // Aktives Geberscript
	// // // if (dgdc.getGeber(vtx, true) == null) {
	// // // dgdc.setSollwertGeberActiv(vtx, createGeber(true, game,
	// // vtx));
	// // // } else {
	// // // // Ist schon erstellt
	// // // logger.fatal("Aktives Geberscript ist schon erstellt."
	// // // + LFCR + dgdc.getGeber(vtx, true).toString());
	// // // assert false : "Aktives Geberscript ist schon erstellt.";
	// // // System.exit(-14);
	// // // }
	// // //
	// // // // Passives Geberscript
	// // // if (dgdc.getGeber(vtx, false) == null) {
	// // // dgdc.setSollwertGeberPassiv(vtx,createGeber(false, game,
	// // vtx));
	// // // } else {
	// // // // Ist schon erstellt
	// // // logger.fatal("Passives Geberscript ist schon erstellt."
	// // // + LFCR + dgdc.getGeber(vtx, false).toString());
	// // // assert false : "Passives Geberscript ist schon erstellt.";
	// // // System.exit(-14);
	// // //
	// // // }
	// // }
	// }
	// }
	// if (LOGGER) {
	// logger.info("Alle Sollwertgeber sind erstellt.");
	// logger.info("");
	// }
	// }

	// // /**
	// // * Erstellt einen konkreten Geber für Sollwerte anhand von
	// Scripteinträgen
	// // * Die indirekten Zugriffe bleiben offen (Nicht alle Vertex sind
	// bekannt!)
	// // * und müssen nachgereicht werden.
	// // * @param active
	// // * true:= Activ<br>
	// // * false:= Passiv
	// // * @param game
	// // * das aktuelle Game
	// // * @param vtx
	// // * der aktuelle Vertex, für den die Geber erstellt werden sollen
	// // * @return der Geber
	// // */
	// // private static final _Geber createGeber(final boolean active,
	// // final DataRoot game, final GuideDigitData vtx) {
	// //
	// // _Geber _g = null;
	// //
	// // if (active ? dgdc.getSollwertScriptActiv(vtx) == null
	// // : dgdc.getSollwertScriptPassive(vtx) == null) {
	// // return null;
	// // }
	// //
	// // // Der Classenname des Gebers
	// // String unitName = (active ? dgdc.getSollwertScriptActiv(vtx).get("?0")
	// // : dgdc.getSollwertScriptPassive(vtx).get("?0")) + "unitTemplate";
	// // if (LOGGER)
	// // logger.debug("......... Unitname: " + unitName);
	// // Class<?> afkt = null;
	// // try {
	// // afkt = Class.forName(_Geber.class.getPackage().getName()+"." +
	// unitName);
	// // Constructor<?> constructor[] = afkt.getConstructors();
	// //
	// // // Es liegt der Konstruktor vor.
	// // // Instanziere den neuen Geber
	// // _g = newInstanceGeber(active, game, vtx, constructor);
	// // } catch (ClassNotFoundException e) {
	// // logger.fatal("Sollwertgebertyp ["+unitName+"] nicht vorhanden!");
	// // e.printStackTrace();
	// // assert false;
	// // System.exit(-10);
	// // } catch (Exception e) {
	// // // SecurityException, NoSuchMethodException,
	// // // IllegalArgumentException,
	// // // IllegalAccessException,
	// // // InvocationTargetException
	// // logger.fatal("Allgemeiner Fehler beim Instanzieren !");
	// // e.printStackTrace();
	// // assert false;
	// // System.exit(-1);
	// // }
	// //
	// // return _g;
	// // }
	// //
	// // /**
	// // * Erzeugt einen konkreten Geber.<br>
	// // *
	// // * @param active
	// // * aktiver oder passiver Geber
	// // * @param game
	// // * aktuelles Game
	// // * @param vtx
	// // * VertexGuideline
	// // * @param constructor
	// // * Liste der möglichen Konstruktoren
	// // * @return der Sollwertgeber oder null
	// // *
	// // * @throws ClassNotFoundException
	// // * @throws IllegalArgumentException
	// // * @throws InstantiationException
	// // * @throws IllegalAccessException
	// // * @throws InvocationTargetException
	// // * @throws NoSuchFieldException
	// // */
	// // @SuppressWarnings("unchecked")
	// // private static final _Geber newInstanceGeber(final boolean active,
	// // final DataRoot game, final GuideDigitData vtx,
	// // Constructor<?> constructor[]) throws ClassNotFoundException,
	// // IllegalArgumentException, InstantiationException,
	// // IllegalAccessException, InvocationTargetException,
	// // NoSuchFieldException {
	// //
	// // MetaStateGuideline msg = game.getMetaStateGuideline();
	// //
	// // if (LOGGER)
	// // logger.trace("......... Instanziere Geber: " + LFCR
	// // + Arrays.asList(constructor));
	// // if (LOGGER)
	// // logger.trace("......... Parameterliste: "
	// // + LFCR
	// // + Arrays.asList(active ? dgdc.getSollwertScriptActiv(vtx)
	// // : dgdc.getSollwertScriptPassive(vtx))
	// // + LOGTAB
	// // + "Länge: "
	// // + (active ? dgdc.getSollwertScriptActiv(vtx).size()
	// // : dgdc.getSollwertScriptPassive(vtx).size()));
	// //
	// // // ParameterArray für die Instanzierung des Objects
	// // Object[] parameterArray = null;
	// // int scriptLen = active ? dgdc.getSollwertScriptActiv(vtx).size()
	// // : dgdc.getSollwertScriptPassive(vtx).size();
	// //
	// // for (Constructor<?> c : constructor) {
	// // if (c.getParameterTypes().length == sollwertParameterStructure
	// // .size() + 2) {
	// // if (LOGGER)
	// // logger.trace("............ Length OK! Check and Implement!"
	// // + c.getParameterTypes().length + ":" + scriptLen);
	// // parameterArray = new Object[sollwertParameterStructure.size() + 2];
	// // int nr = 0;
	// //
	// // // RootDaten 0:
	// // parameterArray[nr++] = game;
	// // if (LOGGER)
	// // logger.trace("............ f : " + (nr - 1) + ": "
	// // + parameterArray[nr - 1]);
	// //
	// // // Stammdaten
	// // // StateGroupIndex 1:
	// // parameterArray[nr++] = new
	// Integer(gdc.getIDX(gdc.getSuperGuideData(vtx)));
	// // if (LOGGER)
	// // logger.trace("............ s : " + (nr - 1) + ": "
	// // + parameterArray[nr - 1]);
	// // // IGLIndex 2:
	// // parameterArray[nr++] = new Integer(gdc.getIDX(vtx));
	// // if (LOGGER)
	// // logger.trace("............ i : " + (nr - 1) + ": "
	// // + parameterArray[nr - 1]);
	// //
	// // // OC
	// // parameterArray[nr++] = active ?
	// dgdc.getSollwertScriptActiv(vtx).get("OC")
	// // : dgdc.getSollwertScriptPassive(vtx).get("OC");
	// // if (LOGGER)
	// // logger.trace("............ OC: " + (nr - 1) + ": "
	// // + parameterArray[nr - 1]);
	// //
	// // // OtherClazz, OtherGroup, OtherVertex
	// //// Class<? extends Data> oclazz = null;
	// // Datenklasse odk = null;
	// // StateGroupGuideline osg = null;
	// // GuideData igl = null;
	// // DataGuidelineLevel dgl = null;
	// // if((dgl = msg.findDGL((String)parameterArray[nr - 1]))!=null){
	// // System.err.println((String)parameterArray[nr - 1]);
	// // assert false;
	// // odk = dgl.getDatenklasse();
	// // }
	// //// try {
	// //// // Test: gibt es diese Klasse?
	// ////// (Class<? extends Data>)
	// ////// oclazz =
	// //// Object o = (Class<? extends Data>) Class
	// //// .forName("tfossi.apolge.data.Data"
	// //// + parameterArray[nr - 1]);
	// //// assert false;
	// //// } catch (ClassNotFoundException e) {
	// //// // Default
	// //// assert false;
	// //// }
	// //
	// // parameterArray[nr - 1] = odk;
	// //
	// // // OS
	// // parameterArray[nr++] = active ?
	// dgdc.getSollwertScriptActiv(vtx).get("OS")
	// // : dgdc.getSollwertScriptPassive(vtx).get("OS");
	// // if (LOGGER)
	// // logger.trace("............ OS: " + (nr - 1) + ": "
	// // + parameterArray[nr - 1]);
	// //
	// // try {
	// // // Test: gibt es diese StateGroup in der Klasse oclazz?
	// // if (parameterArray[nr - 1] == null) {
	// // throw new NoSuchFieldException();
	// // }
	// // osg = game.getMetaStateGuideline().getStateGroup(
	// // odk == null ? vtx.getDGL().getDatenklasse() : odk,
	// // (String) parameterArray[nr - 1]);
	// // parameterArray[nr - 1] = osg == null ? -1
	// // : gdc.getIDX(osg);
	// // } catch (NoSuchFieldException e) {
	// // // Dann nicht
	// // parameterArray[nr - 1] = -1;
	// // }
	// //
	// // // OV
	// // parameterArray[nr++] = active ?
	// dgdc.getSollwertScriptActiv(vtx).get("OV")
	// // : dgdc.getSollwertScriptPassive(vtx).get("OV");
	// // if (LOGGER)
	// // logger.trace("............ OV: " + (nr - 1) + ": "
	// // + parameterArray[nr - 1]);
	// // try {
	// //
	// // if (parameterArray[nr - 1] == null) {
	// // throw new NoSuchFieldException();
	// // }
	// //
	// // StateGroupGuideline tst = game.getMetaStateGuideline()
	// // .getStateGroup(
	// // odk == null ? vtx.getDGL().getDatenklasse()
	// // : odk,
	// // osg == null ? gdc.getName(gdc.getSuperGuideData(vtx))
	// // : gdc.getName(osg));
	// // igl = tst.ggl_get((String) parameterArray[nr - 1]);
	// //
	// // parameterArray[nr - 1] = igl == null ? -1 : gdc.getIDX(igl);
	// // } catch (NoSuchFieldException e) {
	// // // Dann nicht
	// // parameterArray[nr - 1] = -1;
	// // }
	// //
	// // if (nr >= parameterArray.length) {
	// // return (_Geber) c.newInstance(parameterArray);
	// // }
	// // // kp 3:
	// // parameterArray[nr++] = active ?
	// dgdc.getSollwertScriptActiv(vtx).get("kp")
	// // : dgdc.getSollwertScriptPassive(vtx).get("kp");
	// // if (LOGGER)
	// // logger.trace("............ kp : " + (nr - 1) + ": "
	// // + parameterArray[nr - 1]);
	// //
	// // if (nr >= parameterArray.length) {
	// // return (_Geber) c.newInstance(parameterArray);
	// // }
	// // // K
	// // parameterArray[nr++] = active ?
	// dgdc.getSollwertScriptActiv(vtx).get("K")
	// // : dgdc.getSollwertScriptPassive(vtx).get("K");
	// // if (LOGGER)
	// // logger.trace("............ K: " + (nr - 1) + ": "
	// // + parameterArray[nr - 1]);
	// //
	// // if (nr >= parameterArray.length) {
	// // return (_Geber) c.newInstance(parameterArray);
	// // }
	// //
	// // // K0
	// // parameterArray[nr++] = active ?
	// dgdc.getSollwertScriptActiv(vtx).get("K0")
	// // : dgdc.getSollwertScriptPassive(vtx).get("K0");
	// // if (LOGGER)
	// // logger.trace("............ K0: " + (nr - 1) + ": "
	// // + parameterArray[nr - 1]);
	// //
	// // if (nr >= parameterArray.length) {
	// // return (_Geber) c.newInstance(parameterArray);
	// // }
	// //
	// // // Y0
	// // parameterArray[nr++] = active ?
	// dgdc.getSollwertScriptActiv(vtx).get("Y0")
	// // : dgdc.getSollwertScriptPassive(vtx).get("Y0");
	// // if (LOGGER)
	// // logger.trace("............ Y0: " + (nr - 1) + ": "
	// // + parameterArray[nr - 1]);
	// //
	// // if (nr >= parameterArray.length) {
	// // return (_Geber) c.newInstance(parameterArray);
	// // }
	// //
	// // // TV
	// // parameterArray[nr++] = active ?
	// dgdc.getSollwertScriptActiv(vtx).get("TV")
	// // : dgdc.getSollwertScriptPassive(vtx).get("TV");
	// // if (LOGGER)
	// // logger.trace("............ TV: " + (nr - 1) + ": "
	// // + parameterArray[nr - 1]);
	// //
	// // if (LOGGER) {
	// // logger.debug("............ Create with: "+ LFCR
	// // + Arrays.asList(parameterArray));
	// // }
	// // return (_Geber) c.newInstance(parameterArray);
	// // }
	// // }
	// // return null;
	// // }
	//
	// /**
	// * Die Datenarrays des Gebers werden für jeden Datensatz angelegt. Dabei
	// * wird ausgehend vom Root alle hiarchischen Datensätze durchgegangen
	// *
	// * @param data
	// * der aktuelle Datensatz
	// * @param game
	// * ist der Rootdatensatz
	// */
	// public synchronized final static void recursionGeberDataArray(
	// final IDataRoot data, final IDataRoot game) {
	// if (LOGGER) {
	// logger.info(LOGTAB + "----- GenericGebercontainer ----" + LOGTAB
	// + LFCR);
	// }
	// // //// if (LOGGER)
	// // //// logger.debug("... Shortname: [" +
	// // data.getName()+data.getShortname()+data.getDatenklasse().getTyp() +
	// // "]");
	// createGeberDataArray((CoreData) data, game);
	// // // for (CoreData subData : dsc.getRegisterCoreData(data))
	// // // recursionGeberDataArray(subData, game);
	// // //// if (LOGGER)
	// // //// logger.debug("... Shortname: [" +
	// // data.getName()+data.getShortname()+data.getDatenklasse().getTyp() +
	// // "] angelegt");
	// }
	//
	// /**
	// * GeberDataArray erzeugen
	// *
	// * @param data
	// * aktueller Datensatz
	// * @param game
	// * aktuelles Game
	// */
	// private synchronized final static void createGeberDataArray(
	// final CoreData data, final IDataRoot game) {
	// // //
	// // ////####DK Class<? extends Data> clazz = data.getClass();
	// Datenklasse clazz = cdc.getDatenklasse(data);
	// MetaStateGuideline msg = game.getMetaStateGuideline();
	// // //
	// // // // Pro Datenklasse alle Gruppen für die aktiven Vertexdatensätze
	// // ////####DK int anzahlGroups =
	// // msg.getAllStateGroupValues(clazz).size();
	// // // int anzahlGroups =
	// // msg.getAllStateGroupValues(dsc.getDatenklasse(data)).size();
	// // // if (LOGGER)
	// // // logger.debug("- CREATE GEBER [" + clazz + "] -");
	// // //
	// // // if (LOGGER)
	// // // logger.debug("...... Groups: " + anzahlGroups);
	// // //
	// // // // Pro Datenklasse alle Gruppen für die aktiven und passiven
	// // // // Vertexdatensätze einrichten
	// // // _Geber[][] _gga = new _Geber[anzahlGroups][];
	// // // _Geber[][] _ggp = new _Geber[anzahlGroups][];
	// // //
	// for (StateGroupGuideline sg : msg.getAllStateGroupValues(clazz)) {
	// if (gdc.isConst(sg)) {
	// if (LOGGER) {
	// logger.info("Keine Geber für NONDIGIT Const!" + LOGTAB
	// + gdc.getName(sg) + "(" + gdc.getIDX(sg)
	// + ") sind Constanten");
	// }
	// continue;
	// }
	// assert false;
	// // // if (LOGGER)
	// // // logger.debug("...... Group: [" + gdc.getName(sg) + "(" +
	// // gdc.getIDX(sg)+ ")]");
	// // //
	// // // int anzahlIgls = sg.gglAll().size();
	// // // if (LOGGER)
	// // // logger.debug("......... Vertices: " + anzahlIgls);
	// // // // Pro Gruppe alle Vertexdatensätze für Activ-Geber
	// // // _Geber[] _gva = new _Geber[anzahlIgls];
	// // // // Pro Gruppe alle Vertexdatensätze für Passiv-Geber
	// // // _Geber[] _gvp = new _Geber[anzahlIgls];
	// // // try {
	// // // // Der aktive Geberdatensatz der Gruppe zuordnen
	// // // _gga[gdc.getIDX(sg)] = _gva;
	// // // // Der passive Geberdatensatz der Gruppe zuordnen
	// // // _ggp[gdc.getIDX(sg)] = _gvp;
	// // // } catch (java.lang.ArrayIndexOutOfBoundsException e) {
	// // // logger.fatal("Klasse: "+clazz.getTyp()+LOGTAB
	// // // + "_gga länge="+_gga.length + ": " + anzahlIgls+LOGTAB
	// // // + "_ggp länge="+_ggp.length + ": " + anzahlIgls+LOGTAB
	// // // +msg.getAllStateGroupValues(clazz));
	// // // e.printStackTrace();
	// // // assert false;
	// // // System.exit(-11);
	// // // }
	// }
	// // // // Die Array dem Datensatz zuordnen
	// // // dsc.setGeber(data, _gga, _ggp);
	// }
	//
	// /**
	// * Aus den generischen Sollwertgeber werden konkrete, datensatzspezifische
	// * Geber durch klonen erzeugt.
	// *
	// * @param data
	// * aktueller Datensatz
	// * @param game
	// * Rootdatensatz
	// * @param ebene
	// * der Rekursion
	// */
	// public static void recursionGeberToVertexArray(final IDataRoot data,
	// final IDataRoot game, final int ebene) {
	//
	// if (LOGGER) {
	// logger.info(LOGTAB + "----- Create Geber for Vertex ----" + LOGTAB
	// + LFCR);
	// }
	// if (LOGGER) {
	// String tilde = "-";
	// for (int i = 0; i < ebene; ++i, tilde += "-")
	// ;
	// logger.debug(tilde + " CREATE GEBER ["
	// + cdc.getDatenklasse((CoreData) data).getLevelelement()
	// + "] " + tilde);
	// }
	// cloneGeber2Vertices((CoreData) data, game);
	//
	// // // for (CoreData subData : dsc.getRegisterCoreData(data))
	// // // recursionGeberToVertexArray(subData, game, ebene + 1);
	//
	// }
	//
	// /**
	// * Alle Vertex zur Implementierung der Geber bereitstellen und Geber-Klone
	// * implementieren
	// *
	// * @param data
	// * aktueller Datensatz
	// * @param game
	// * Rootdatensatz
	// */
	// private synchronized final static void cloneGeber2Vertices(
	// final CoreData data, final IDataRoot game) {
	// // //// Class<? extends Data>
	// Datenklasse clazz = cdc.getDatenklasse(data);
	// MetaStateGuideline msg = game.getMetaStateGuideline();
	//
	// if (LOGGER)
	// logger.debug("... [" + clazz.getLevelelement() + "]");
	//
	// for (StateGroupGuideline sg : msg.getAllStateGroupValues(clazz)) {
	// if (LOGGER) {
	// logger.info("Keine Sollwertgeber für Non-Digit Const!" + LOGTAB
	// + gdc.getName(sg) + "(" + gdc.getIDX(sg)
	// + ") ist Constant!");
	// }
	// if(gdc.isConst(sg))
	// continue;
	// assert false;
	// //
	// // // if (LOGGER)
	// // // logger.debug("...... Group: [" + gdc.getName(sg) + "(" +
	// // gdc.getIDX(sg) + ")]");
	// // // for (GuideData vtx : sg.gglValues()) {
	// // //
	// // // if (LOGGER)
	// // // logger.debug("......... Vertex: ["
	// // // + gdc.getName(vtx) + "("
	// // // + gdc.getIDX(gdc.getSuperGuideData(vtx)) + "." + "."
	// // // + gdc.getIDX(vtx) + ")]");
	// // //
	// // // cloneGeber(data, vtx);
	// // // }
	// }
	//
	// }

	// // /**
	// // * Geberklone implementieren.
	// // *
	// // * @param data
	// // * aktueller Datensatz
	// // * @param g
	// // * State
	// // */
	// // @SuppressWarnings("unchecked")
	// // private synchronized final static void cloneGeber(final CoreData data,
	// final GuideData g) {
	// //
	// // CoreData dloop = data;
	// // while(!(dloop instanceof DataRoot))
	// // dloop = dsc.getSuperCoreData(dloop);
	// //
	// // MetaStateGuideline msg =((DataRoot)dloop).getMetaStateGuideline();
	// //
	// // if (!(gdc.isVertex(g)))
	// // return;
	// // GuideDigitData vtx = (GuideDigitData) g;
	// // if (LOGGER)
	// // logger.trace("......... Create for [" +
	// data+": "+dsc.getDatenklasse(data).getTyp() + "] "
	// // + gdc.getIDX(gdc.getSuperGuideData(vtx)) + "."
	// // + gdc.getIDX(vtx));
	// // _Geber _ga = dgdc.getSollwertGeberActiv(vtx);
	// // assert _ga != null : "Der Sollwertgeber Aktiv ist nicht definiert!";
	// // _Geber _gp = dgdc.getSollwertGeberPassiv(vtx);
	// // assert _gp != null : "Der Sollwertgeber Passiv ist nicht definiert!";
	// //
	// // if (LOGGER)
	// // logger.trace("......... Aktiver Geber:"+LFCR+_ga.description() + " ");
	// // if (LOGGER) logger.trace(_gp==null?
	// // "......... Passiver Geber:"+LFCR+"Nicht definiert!":
	// // "......... Passiver Geber:"+LFCR+_gp.description() + " ");
	// //
	// // CoreData oaData = null;
	// // boolean subOfOtherAData = false;
	// //// Class<? extends Data> oclazz = null;
	// // Datenklasse odk = null;
	// // DataGuidelineLevel dgl = null;
	// // if((dgl =
	// msg.findDGL((String)dgdc.getSollwertScriptActiv(vtx).get("OC")))!=null){
	// //
	// System.err.println((String)dgdc.getSollwertScriptPassive(vtx).get("OC"));
	// // assert false;
	// // odk = dgl.getDatenklasse();
	// // }
	// //// try {
	// //// oclazz = (Class<? extends Data>) Class
	// //// .forName("tfossi.apolge.data.Data"
	// //// + vtx.sollwertScriptActiv.get("OC"));
	// //// } catch (ClassNotFoundException e) {
	// //// // Default
	// //// }
	// // if (odk != null) {
	// // // Identifikation des liefernden Data
	// // oaData = data;
	// // // Check: Superdata oder
	// // do {
	// // oaData = dsc.getSuperCoreData(oaData);
	// // } while (oaData != null && !odk.equals(oaData.getClass()));
	// //
	// // if (oaData == null) {
	// // // ... registriertes Subdata
	// // if (dsc.getRegisterCoreData(data).get(0).getClass().equals(odk)) {
	// // oaData = data;
	// // subOfOtherAData = true;
	// // }
	// // }
	// // }
	// // dsc.setGeber(data, true, vtx, _ga.clone(data, oaData,
	// subOfOtherAData));
	// //
	// // CoreData opData = null;
	// // boolean subOfOtherPData = false;
	// // if((dgl =
	// msg.findDGL((String)dgdc.getSollwertScriptActiv(vtx).get("OC")))!=null){
	// //
	// System.err.println((String)dgdc.getSollwertScriptPassive(vtx).get("OC"));
	// // assert false;
	// // odk = dgl.getDatenklasse();
	// // }
	// //// try {
	// //// oclazz = (Class<? extends Data>) Class
	// //// .forName("tfossi.apolge.data.Data"
	// //// + vtx.sollwertScriptPassiv.get("OC"));
	// //// } catch (ClassNotFoundException e) {
	// //// // Default
	// //// }
	// // if (odk != null) {
	// // // Identifikation des liefernden Data
	// // opData = data;
	// // // Check: Superdata oder
	// // do {
	// // opData = dsc.getSuperCoreData(opData);
	// // } while (opData != null && !odk.equals(opData.getClass()));
	// //
	// // if (opData == null) {
	// // // ... registriertes Subdata
	// // if (dsc.getRegisterCoreData(data).get(0).getClass().equals(odk)) {
	// // opData = data;
	// // subOfOtherPData = true;
	// // }
	// // }
	// // }
	// // dsc.setGeber(data, false, vtx, _gp.clone(data, opData,
	// subOfOtherPData));
	// // }

	// ---- Selbstverwaltung --------------------------------------------------

	/** serialVersionUID */
	@SuppressWarnings("unused")
	private final static long serialVersionUID = VERSION;	
	
	/** logger */
	private final static Logger logger = Logger.getLogger(BuildGeber.class
			.getPackage().getName() + ".BuildGeber");

}