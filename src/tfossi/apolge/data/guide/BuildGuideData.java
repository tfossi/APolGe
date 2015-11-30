package tfossi.apolge.data.guide;

import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;

import org.apache.log4j.Logger;

import tfossi.apolge.common.scripting.vp.pm.PatternMaps;

/**
 * Erstellen der Regelwerke zur initialen und kontinuierlichen Datenverarbeitung<br>
 * DOC Es gibt drei Scriptdateitypen, die zur Auswertung herangezogen werden:<br>
 * <ol>
 * <li>Das <i>structureScript</i> <b>[datastructure.apo]</b><br>
 * liefert die Beschreibung der Datenstruktur der hiarchischen Anordnung.<br>
 * <code><pre>
 * Ebene0 = {
 *  Ebene A
 *  Ebene B = {
 *   Ebene BA = {
 *    Ebene BAA
 *   }	
 *   Ebene BB
 *  }
 * }  
 * </pre></code></li>
 * <li>Die <i>listenScripts</i> <b>[sgl_<i>Ebene XY</i>.apo]</b> oder
 * <b>[sgl_default.apo]</b><br>
 * liefern die Beschreibung der Stategruppen einer Ebene. <br>
 * Gruppen sollten vom SCON (!) sein! <code><pre>  
 * !gruppe1 = <i>TYP</i><br>
 * ...<br>
 * !gruppeN = { <i>TYP</i>; Activity ="<i>[Beschreibung der Gruppe]</i>"}
 * }  
 * </pre></code> TYP
 * <ul>
 * <li>CONST</li>
 * <li>RADIO</li>
 * <li>DIGIT</li>
 * </ul>
 * </li>
 * <li><b>detailScripts</b><br>
 * </li>
 * 
 * 
 * @author tfossi
 * @version 31.12.2014
 * @modified -
 * @since Java 1.6
 */
public class BuildGuideData {
//
//	/**
//	 * Hier sind die Informationen zu der Datenstruktur abgelegt. Daraus können
//	 * dann die DGL abgeleitet werden
//	 */
//	private PatternMaps structureScript;
//
//	/**
//	 * @return die aufbereiteten Daten aus dem Strukturscript
//	 *         [datastructure.apo]
//	 * @modified -
//	 */
//	public final PatternMaps getStructureScript() {
//		return this.structureScript;
//	}
//
	/**
	 * Hier sind die Informationen zu den Stategroups eines jeden DGL abgelegt.
	 * Daraus können die Stategroups abgeleitet werden.
	 */
	private PatternMaps[] listenScripts;

	/**
	 * @return die aufbereiteten Daten aus dem Listenscripts [sgl_*.apo] eines
	 *         jeden DGL für die SGG-Bildungen.
	 * @modified -
	 */
	public final PatternMaps[] getListenScripts() {
		return this.listenScripts;
	}
//	/**
//	 * Hier sind die Informationen zu den State eine jeden SGG abgelegt.
//	 * Daraus können die States abgeleitet werden.
//	 */
//	
	private PatternMaps[][] detailScripts;

	/**
	 * @return die aufbereiteten Daten aus dem Detailscripts [sgd_*.apo] ener jeden SGG für die States-Bildungen.
	 * @modified - 
	 */
	public final PatternMaps[][] getDetailScripts() {
		return this.detailScripts;
	}
//	
//	/**
//	 * Einlesen und Vorbereiten der Scripte.<br>
//	 * Die Scripte werden ausgelesen, interpretiert und in den Guidelines vom
//	 * Grundtyp {@link GuideData} abgelegt. In {@link DataGuidelineLevel} ist
//	 * die Beschreibung der Datenstruktur abgelegt.
//	 * 
//	 * @param sg_path
//	 *            Pfad zu den Scriptdateien
//	 * @param mode
//	 *            Modus zum Einlesen der Daten. Standard: <code>PMODEFLAT</code>
//	 * @modified -
//	 */
//	public final void readNBuildGuidelineFiles(final String sg_path,
//			final byte mode) {
//
//		// Initiale Anlage der Datenstruktur in den DataGuidelineLevel
//		this.structureScript = readDataStructureFile(sg_path, PMODEFLAT);
//		if (LOGGER)
//			logger.info("Die Datenstruktur ist eingelesen.");
//
//		// Aufbauen der Guideline für die Datenstruktur in den
//		DataGuidelineLevel dglRoot = DataGuidelineLevel.build(this);
//		if (LOGGER) {
//			logger.info("Die Guideline der Datenstruktur sind aufgebaut:"
//					+ LFCR + dglRoot.toString());
//		}
//
//		// Initale Anlage der Stategroups
//		this.listenScripts = readStateGroupFile(dglRoot, sg_path, mode);
//		if (LOGGER)
//			logger.info("Die StateGroups sind eingelesen.");
//
//		StateGroupGuideline.build(this, dglRoot);
//		if (LOGGER) {
//			logger.info("Die Guideline der Datenstruktur und Stategroups sind aufgebaut:"
//					+ LFCR + dglRoot.toString());
//		}
//
//		// Initale Anlage der States
//		this.detailScripts = readNBuildStateFile(dglRoot, sg_path, mode);
//		if (LOGGER)
//			logger.info("Die States sind eingelesen.");
//
//		GuideData.build(this, dglRoot);
//		if (LOGGER) {
//			logger.info("Die Guideline der Datenstruktur, Stategroups und States sind aufgebaut:"
//					+ LFCR + dglRoot.toString());
//		}
//	}
//
//	/**
//	 * Wertes das Script [datastructure.apo] für die Beschreibung der
//	 * Datenstruktur aus.<br>
//	 * 
//	 * @param sg_path
//	 *            der aktuelle Pfad zur [datastructure.apo]
//	 * @param mode
//	 *            Mode ist derzeit gefixt auf PMODEFLAT
//	 * @return PatternMaps mit der Strukturangaben.
//	 * @modified -
//	 */
//	private final static PatternMaps readDataStructureFile(
//			final String sg_path, final byte mode) {
//		PatternMaps rc = null;
//
//		// Pfadangabe.
//		// § Die Datenstruktur ist in der Datei "../guideline/structure.apo"
//		// abgelegt!
//		String fn = (sg_path == null ? null : sg_path + SCRIPT_DATASTRUCTURFILE
//				+ SCRIPT_EXTENSION);
//		try {
//			// Einlesen
//			rc = new LoadScript(fn, null).valueParser(PMODEFLAT);
//		} catch (ScriptException e) {
//			System.err.println(LFCR + "Abbruch: " + e.getMessage());
//			e.printStackTrace();
//			System.exit(-2);
//		} catch (LoadScriptException e) {
//			System.err.println(LFCR + "Abbruch: " + e.getMessage());
//			System.exit(-2);
//		} catch (tfossi.apolge.common.scripting.p.ParseException e) {
//			System.err.println(LFCR + "Abbruch: " + e.getMessage());
//			e.printStackTrace();
//			System.exit(-2);
//		}
//		return rc;
//	}
//
//	/**
//	 * Wertes die Scripte [sgl_*.apo] für die Beschreibung der Stategroups aus.<br>
//	 * 
//	 * @param rootDGL
//	 *            oberste DGL
//	 * @param sg_path
//	 *            der aktuelle Pfad zu den [sgl_*.apo]
//	 * @param mode
//	 *            Mode ist derzeit gefixt auf PMODEFLAT
//	 * @return PatternMaps mit der Strukturangaben.
//	 * @modified -
//	 */
//	private final static PatternMaps[] readStateGroupFile(
//			final DataGuidelineLevel rootDGL, final String sg_path,
//			final byte mode) {
//
//		// Returncode
//		PatternMaps[] rc = null;
//
//		// Array mit Anzahl der Einträgen reservieren
//		rc = new PatternMaps[rootDGL.childCounter()];
//
//		// Liste der Datenstruktur
//		List<DataGuidelineLevel> listOfDatastructure = rootDGL.getAll();
//
//		// Alle DGL durchgehen und die einzelnen [sgl_*.apo] laden
//		for (DataGuidelineLevel dgl : listOfDatastructure) {
//			rc[dgl.getDatenklasse().getIDX()] = readStateGroupFile(sg_path,
//					dgl, mode);
//		}
//		return rc;
//	}
//
//	/**
//	 * Einlesen des [sgl_*.apo]-File des Levels DGL
//	 * 
//	 * @param sg_path
//	 *            der aktuelle Pfad zu den [sgl_*.apo]-Scripten
//	 * @param dgl
//	 *            aktueller DataGuidelineLevel
//	 * @param mode
//	 *            Einlesemodus [default=PMODE0] FIXME Aktuell ist der Mode in
//	 *            PMODEFLAT gefixed
//	 * @return Ergebnis des Einlesens
//	 * @modified -
//	 */
//	private final static PatternMaps readStateGroupFile(final String sg_path,
//			final DataGuidelineLevel dgl, final byte mode) {
//
//		PatternMaps pm = null;
//		// Die Definition der Stategruppen laden.
//		// § In dem Script sgl_'...'.apo ist die Liste der
//		// Stategruppen enthalten.
//
//		String fn = (sg_path == null ? null : sg_path + SCRIPT_STATEGROUPLIST
//				+ dgl.getDatenklasse().getLevelelement() + SCRIPT_EXTENSION);
//		String fdefault = sg_path + SCRIPT_STATEGROUPLIST + "default"
//				+ SCRIPT_EXTENSION;
//
//		try {
//			pm = new LoadScript(fn, null).valueParser(mode);
//		} catch (ScriptException e) {
//			logger.fatal("Abbruch wegen Scriptfehler!" + LFCR + e.getMessage());
//			e.printStackTrace();
//			System.exit(-1);
//		} catch (LoadScriptException e) {
//			logger.warn("StateGroupFile-Script zum Datensatz ["
//					+ dgl.getDatenklasse().getLevelelement()
//					+ "] nicht gefunden: " + LOGTAB
//					+ "Weitere Ausführung mit [" + SCRIPT_STATEGROUPLIST
//					+ "default" + SCRIPT_EXTENSION + "]");
//
//			try {
//				pm = new LoadScript(fdefault, null).valueParser(mode);
//			} catch (ScriptException e1) {
//				logger.fatal("Abbruch wegen Scriptfehler!" + LFCR
//						+ e1.getMessage());
//				e1.printStackTrace();
//				System.exit(-1);
//			} catch (LoadScriptException e1) {
//				logger.fatal(e1.getMessage() + LFCR
//						+ "StateGroups-Script zum Datensatz ["
//						+ dgl.getDatenklasse().getLevelelement() + "]" + LOGTAB
//						+ " nicht gefunden: " + LOGTAB
//						+ "Weitere Ausführung sind nicht möglich.");
//				System.exit(-2);
//			} catch (ParseException e1) {
//				logger.fatal("Abbruch wegen Parsefehler!" + LFCR
//						+ e1.getMessage());
//				e1.printStackTrace();
//				System.exit(-1);
//			}
//		} catch (ParseException e) {
//			logger.fatal("Abbruch wegen Parsefehler!" + LFCR + e.getMessage());
//			e.printStackTrace();
//			System.exit(-1);
//		}
//		return pm;
//	}
//
//	/**
//	 * Einlesen der [sgd_*.apo]-File der Stategroup SGG des Levels DGL
//	 * 
//	 * @param sg_path
//	 *            der aktuelle Pfad zu den [sgd_*.apo]-Scripten
//	 * @param rootDGL
//	 *            oberster DGL
//	 * @param mode
//	 *            Einlesemodus [default=PMODE0] FIXME Aktuell ist der Mode in
//	 *            PMODEFLAT gefixed
//	 * @return Ergebnis des Einlesens
//	 * @modified -
//	 */
//	private final static PatternMaps[][] readNBuildStateFile(
//			final DataGuidelineLevel rootDGL, final String sg_path,
//			final byte mode) {
//
//		PatternMaps[][] rc = null;
//
//		// Array mit Anzahl der DGL-Einträgen reservieren
//		rc = new PatternMaps[rootDGL.childCounter()][];
//
//		// Liste der Datenstruktur
//		List<DataGuidelineLevel> listOfDatastructure = rootDGL.getAll();
//
//		// Alle DGL durchgehen
//		for (DataGuidelineLevel dgl : listOfDatastructure) {
//
//			// Array mit der Anzahl der SGG-Einträge im aktuellen DGL
//			// reservieren
//			rc[dgl.getDatenklasse().getIDX()] = new PatternMaps[dgl
//					.numberOfStageGroupGuidelines()];
//
//			// Alle SGG im aktuellen DGL durchgehe nund die einzelnen
//			// [sgd_*.apo] laden
//			for (StateGroupGuideline sgg : dgl.getAllStateGroupValues()) {
//				rc[dgl.getDatenklasse().getIDX()][sgg.idx] = readStateFile(
//						sg_path, dgl, sgg, mode);
//			}
//		}
//		return rc;
//	}
//
//	/**
//	 * Laden der Statefiles
//	 * 
//	 * @param sg_path
//	 *            der aktuelle Pfad zu den [sgd_*.apo]-Scripten *
//	 * @param dgl
//	 *            aktueller DataGuidelineLevel
//	 * @param sgg
//	 *            aktuelle StateGroupGuideline
//	 * @param mode
//	 *            Einlesemodus [default=PMODE0]
//	 * @return Ergebnis des Einlesens
//	 * @modified -
//	 */
//	private final static PatternMaps readStateFile(final String sg_path,
//			final DataGuidelineLevel dgl, final StateGroupGuideline sgg,
//			final byte mode) {
//
//		PatternMaps pm = null;
//		
////		FIXME default IDLE-Modus einführen.
////		PatternMaps pm2 = null;
////		String idle = 
////				"!STATE = {"+LFCR+
////				"!IDLE2 = {"+LFCR+
////				"		!Activity = \"Default Idle\""+LFCR+
////				"		!SollA = 1"+LFCR+
////				"		!AS = { P; !Kp = 1. }"+LFCR+
////				"}"+LFCR+
////				"}";
//		
//		// Die Definition der States laden.
//		// § In dem Script sgd_'...'.apo ist die Beschreibung der States
//		// enthalten.
//
//		String fn = (sg_path == null ? null : sg_path
//				+ SCRIPT_STATEGROUPDEFINITION
//				+ dgl.getDatenklasse().getLevelelement() + "." + sgg.getKey()
//				+ SCRIPT_EXTENSION);
//
//		String fdefault = sg_path + SCRIPT_STATEGROUPDEFINITION
//				+ sgg.statetype.name() + ".default" + SCRIPT_EXTENSION;
//		try {
//			
////			pm2 = new LoadScript(null, idle).valueParser(PMODEFLAT);
//			pm = new LoadScript(fn, null).valueParser(mode);
//
//		} catch (ScriptException e) {
//			logger.fatal("Abbruch wegen Scriptfehler!" + LFCR + e.getMessage());
//			e.printStackTrace();
//			System.exit(-1);
//		} catch (LoadScriptException e) {
//			logger.warn("StateGroupFile-Script zum Datensatz ["
//					+ dgl.getDatenklasse().getLevelelement()
//					+ "] nicht gefunden: " + LOGTAB
//					+ "Weitere Ausführung mit [" + SCRIPT_STATEGROUPDEFINITION
//					+ "typ.default" + SCRIPT_EXTENSION + "]");
//
//			try {
//				pm = new LoadScript(fdefault, null).valueParser(mode);
//			} catch (ScriptException e1) {
//				logger.fatal("Abbruch wegen Scriptfehler!" + LFCR
//						+ e1.getMessage());
//				e1.printStackTrace();
//				System.exit(-1);
//			} catch (LoadScriptException e1) {
//				logger.fatal(e1.getMessage() + LFCR
//						+ "StateGroups-Script zum Datensatz ["
//						+ dgl.getDatenklasse().getLevelelement() + "]" + LOGTAB
//						+ " nicht gefunden: " + LOGTAB
//						+ "Weitere Ausführung sind nicht möglich.");
//				System.exit(-2);
//			} catch (ParseException e1) {
//				logger.fatal("Abbruch wegen Parsefehler!" + LFCR
//						+ e1.getMessage());
//				e1.printStackTrace();
//				System.exit(-1);
//			}
//		} catch (ParseException e) {
//			logger.fatal("Abbruch wegen Parsefehler!" + LFCR + e.getMessage());
//			e.printStackTrace();
//			System.exit(-1);
//		}
//		
//		return pm;
//	}

	// ---- Selbstverwaltung --------------------------------------------------
	/** serialVersionUID */
	@SuppressWarnings("unused")
	private final static long serialVersionUID = VERSION;

	/** logger */
	private final static Logger logger = Logger.getLogger(BuildGuideData.class
			.getPackage().getName() + ".BuildGuideData");

}