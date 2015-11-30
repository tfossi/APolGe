/**
 * 
 */
package tfossi.apolge.data.guide;

import static tfossi.apolge.common.constants.ConstValue.LFCR;
import static tfossi.apolge.common.constants.ConstValue.LOGGER;
import static tfossi.apolge.common.constants.ConstValue.LOGTAB;
import static tfossi.apolge.common.constants.ConstValue.SCRIPT_DATASTRUCTURFILE;
import static tfossi.apolge.common.constants.ConstValue.SCRIPT_EXTENSION;
import static tfossi.apolge.common.constants.ConstValue.TAB;
import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;

import tfossi.apolge.common.scripting.t.Table;
import tfossi.apolge.common.scripting.vp.VP_Tokenlist;

/**
 * Beschreibt die Daten und Richtlinien eines Levels.<br>
 * Hier sind die Childs angemeldet.<br>
 * Kennt den Parent.<br>
 * 
 * Alle Daten sind vom Type 'Data'.<br>
 * 
 *
 * @author tfossi
 * @version 26.01.2015
 * @modified -
 * @since Java 1.6
 */
public class DataGuidelineLevel implements Cloneable {
	
	/**
	 * Kennwerte Namen und Ebene des DataGuideLevel
	 */
	private final Datenklasse dk = new Datenklasse();

	/**
	 * @return liefert die Datenklasse
	 * @modified -
	 */
	public final Datenklasse getDatenklasse() {
		return this.dk;
	}

	// ---- Childs -----------------------------------------------------------

	/** Childklassen vom Typ {@linkplain DataGuidelineLevel} */
	private final List<DataGuidelineLevel> childDGL = new ArrayList<DataGuidelineLevel>();

	/**
	 * Legt einen neuen DataGuidelineLevel als Child unter diesem an!
	 * 
	 * @return der neue Child DataGuidelineLevel
	 * @modified -
	 */
	public final DataGuidelineLevel newChildDGL() {
		DataGuidelineLevel child = new DataGuidelineLevel(this);
		this.childDGL.add(child);
		return child;
	}
	
	/**
	 * @return liefert eine komplette Liste aller DGL-Elemente
	 * @modified - 
	 */
	public final List<DataGuidelineLevel> getAll() {
		List<DataGuidelineLevel> rc = new ArrayList<DataGuidelineLevel>();
		for (DataGuidelineLevel dgl : this.childDGL)
			rc.addAll(dgl.getAll());
		rc.add(this);
		return rc;
	}

	/**
	 * @return liefert Anzahl aller DGL-Elemente
	 * @modified - 
	 */
	public final int childCounter() {
		int rc = 1;
		for (DataGuidelineLevel dgl : this.childDGL)
			rc += dgl.childCounter();
		return rc;
	}

	// ---- Parent -----------------------------------------------------------

	/** Der übergeordnete Parent DataGuidelineLevel */
	@SuppressWarnings("unused")
	private final DataGuidelineLevel parentDGL;

	// ---- Gruppe der StateGuidelines ----------------------------------------

	/**
	 * Daten aller StateGroups ({@linkplain StateGroupGuideline}) in der Klasse vom
	 * Typ {@linkplain GuideData}
	 */
	private final StateGroupGuidelineMap sgg_map = new StateGroupGuidelineHashMap();

	/**
	 * @return Aktuelle Anzahl der eingetragenen StateGroupGuidelines
	 * @modified -
	 */
	public final int numberOfStageGroupGuidelines() {
		return this.sgg_map.size();
	}

	/**
	 * Fügt eine neue StateGroup unter ihrem Namen dem DataGuidelineLevel hinzu
	 * 
	 * @param sgg
	 *            Die neue StateGroupGuideline
	 * @return die neue StateGroupGuideline
	 * @modified -
	 */
	public final StateGroupGuideline addStageGroupGuideline(
			final StateGroupGuideline sgg) {
		if (LOGGER)
			logger.trace("Trage für " + this.dk.toString()
					+ " die neue StateGroup [" + sgg.getKey() + "] ein!");
		this.sgg_map.put(sgg.getKey(), sgg);
		return sgg;
	}
	
	/**
	 * @return alle Stategroups eines DGL-Elements
	 * @modified -
	 */
	public final Collection<StateGroupGuideline> getAllStateGroupValues() {
		return this.sgg_map.values();
	}

	// ---- Selbstverwaltung --------------------------------------------------

	/** serialVersionUID */
	@SuppressWarnings("unused")
	private final static long serialVersionUID = VERSION;

	/** logger */
	private final static Logger logger = Logger
			.getLogger(DataGuidelineLevel.class.getPackage().getName()
					+ ".DataGuidelineLevel");

	/**
	 * Erstellt ein DataGuidelineLevel-Objekt
	 * 
	 * @param parent
	 *            übergeordneter DataGuidelineLevel oder null bei höchstem Level
	 *            (Root)
	 * @modified -
	 */
	public DataGuidelineLevel(final DataGuidelineLevel parent) {
		this.parentDGL = parent;
	}

	/**
	 * Ausgabe dieser DGL-Daten inklusive der Childs
	 */
	@Override
	public final String toString() {
		String rc = new String(LFCR);
		for (DataGuidelineLevel dgl : this.childDGL)
			rc += dgl.toString();

		String s = new String();
		for (String sggName : this.sgg_map.keySet()) {
			StateGroupGuideline sgg = this.sgg_map.get(sggName);
			s += LFCR+TAB + sggName + " (" + sgg.toString();
		}
		
		return this.dk.toString() + s + rc;
	}

	/**
	 * Baue den Guideline für {@link DataGuidelineLevel}
	 * 
	 * @param bgd
	 *            ist der Builder
	 * @return die Datenstruktur aller DGL vom der obersten Ebene an.
	 * @modified -
	 */
	final static DataGuidelineLevel build(final BuildGuideData bgd) {

		// Oberster DataGuidelineLevel
		DataGuidelineLevel rootDGL = new DataGuidelineLevel(null);

		// Name des obersten DGL
		String rootguideline = null;

		// Baut die DatenHierarchie ausgehend von der Root-Clazz
		logger.info("- BUILD: Datenstruktur DGL-");

		// § Der Einlesemodus für die Datenstruktur ist PMODEFLAT!
//		for (String key : bgd.getStructureScript().scon.keySet()) {
//			// Erste Steuerzeichen herausholen ...
//			if (key.equals("OUTPUT")) {
//				// FIXME Überarbeiten. game steht nicht zur Verfügung
//				// game.setLogtest(this.structureScript.getStringValue("OUTPUT"));
//				// logger.info("Set OUTPUT to " + game.getLogtest());
//				continue;
//			}
//
//			// ... dann auf Mehrfachnennung prüfen ...
//			if (rootguideline != null && !key.startsWith(rootguideline + ".")) {
//				logger.fatal("In [" + SCRIPT_DATASTRUCTURFILE
//						+ SCRIPT_EXTENSION
//						+ "] wurde mehr als ein Rooteintrag gefunden." + LFCR
//						+ "TOO MUCH: " + key + LFCR + "IST BELEGT MIT: "
//						+ rootguideline);
//				System.exit(-1);
//			} else if (rootguideline == null)
//				// ... dann zuordnen
//				rootguideline = key;
//		}

		if (LOGGER)
			logger.debug("Ausgehend von [" + rootguideline
					+ "], der Bezeichnung " + LOGTAB
					+ "der ROOTGUIDELINE, wird die Datenstruktur aufgebaut.");

		// Rekursiver Aufruf. Die Daten werden hierarchisch beginnend mit
		// [rootDGL] abgelegt
		// § In der obersten Ebene gibt es nur ein Element [rootDGL]
//		rootDGL.createDGL(rootDGL, rootDGL, rootguideline, 0,
//				bgd.getStructureScript().scon.get(rootguideline));

		logger.info("- BUILD: Datenstruktur DGL - ready");

		return rootDGL;
	}

	/**
	 * Erzeugt rekursiv die Datenstruktur des Datenmodells aus dem Strukurscript [datastructure.apo]<br>
	 * 
	 * @param rootDGL
	 * 			das oberste DGL-element
	 * @param parent
	 *            aktuelle Ebene, von der aus die nächste Ebene erstellt wird
	 * @param parentName
	 *            der Name des Datensatzes von Typ 'DataGuide' 
	 * @param parentDepth
	 *            Tiefe der Ebene ausgehend von Root=0
	 * @param structDesciption
	 *            die Tokenliste des Datenstrukturmodells
	 * @modified -
	 */
	private final void createDGL(final DataGuidelineLevel rootDGL,
			final DataGuidelineLevel parent, final String parentName,
			final int parentDepth, final VP_Tokenlist structDesciption) {
		
		if (parentName == null)
			return;

		// Erzeuge die Datenbezeichnung der aktuellen Hierarchiestufe
		// [parentName]
		parent.getDatenklasse().setLevelelement(parentName);
		parent.getDatenklasse().setLevel(parentDepth);
		parent.getDatenklasse().setIDX(rootDGL.childCounter() - 1);

		// Wenn es keine Tabelle ist, dann ist es eine einfache Angabe
		if (!structDesciption.isTable()) // || structName == null||
			// structName.isEmpty())
			return;

		 // Die unter [structName] untergeordneten DataGuidelineLevel
		DataGuidelineLevel dgl = null;

		// Hole die Tabelleneinträge in structName. Values sind oben
		// ausgeschlossen.
		Table subStruct = structDesciption.getTable();

		// Alle Elemente durchgehen
		for (String keyName : subStruct.keySet()) {
			// Einen neuen Child-DGL unter Parent erzeugen
			dgl = parent.newChildDGL();

			// Tokenliste
			VP_Tokenlist childStructName = (VP_Tokenlist) subStruct
					.get(keyName);
			if (childStructName.isTable()) {
				// Komplexere Angaben
				String childDataName = keyName.substring(keyName
						.lastIndexOf(".") + 1);
				// Ebene
				int childDepth = parentDepth + 1;
				if (LOGGER)
					logger.debug("CREATE [" + childDataName + "]");
				// Rekursionssprung
				createDGL(rootDGL, dgl, childDataName, childDepth,
						childStructName);
			} else {
				// Ist Einzelwert
				String childDataName = (String) childStructName.getValue();
				// Ebene
				int childDepth = parentDepth + 1;
				if (LOGGER)
					logger.debug("SINGLE [" + childDataName + "]");
				// Rekursionssprung
				createDGL(rootDGL, dgl, childDataName, childDepth,
						childStructName);
			}
		}
	}
}
