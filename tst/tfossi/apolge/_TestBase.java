/**
 * _TestBase.java
 * Branch base
 * APolGe
 * tfossi-team
 * licence GPLv3 
 */
package tfossi.apolge;

import static tfossi.apolge.common.constants.ConstValue.FS;
import static tfossi.apolge.common.constants.ConstValue.LOGGER;
import static tfossi.apolge.common.constants.ConstValue.NTAB;
import static tfossi.apolge.common.constants.ConstValue.SCRIPT_PATH;

//import tfossi.apolge.common.state.MetaStateGuideline;
//import tfossi.apolge.data.core.DataRoot;
//import tfossi.apolge.data.guide.BuildGuideData;
import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

/**
 * Bietet Hilfsmethoden zur Erzeugung eines Games für komplexe Tests
 * 
 * @author tfossi
 * @version 1.07.2014 {@value #serialVersionUID}
 * @modified Coderevision, tfossi, 31.07.2014 
 * @since Java 1.6
 * @modified -
 */
public abstract class _TestBase {

	{
		if(LOGGER) System.out.println("_TestBase V"+serialVersionUID);
	}
	
//	DOC private final static CDC dsc = new CDC(); 

	/**
	 * Regel für das Testen von <i>System.exit()</i> einbinden
	 */
	@Rule
	public final ExpectedSystemExit exit = ExpectedSystemExit.none();


//	DOC protected MetaStateGuideline msg;
	
//	/** Das Virtuelle Root */
//	DOC protected DataRoot virGame = null;

	/** Simulationsstartzeit */
	protected String start = null;
	/** Simulationsendezeit */
	protected String end = null;

	/** Dauer der Simulation */
	protected long dauer;

//	/** Ergebnisreports */
//	DOC protected List<Report> repList = new ArrayList<Report>(25);
	
	/**
	 * DOC 
	 */
	@BeforeClass
	public static void setUpBeforeClass() {
		// FEATURE Code-@version in Code integrieren

		PropertyConfigurator.configure("log4j.properties");
	}

	/**
	 * DOC 
	 */
	@AfterClass
	public static void tearDownAfterClass()  {
		//
	}

	
	/**
	 * DOC
	 */
	@SuppressWarnings("static-method")
	@Before
	public void setUp(){
		System.out
				.println("SETUP -------------------------------------------------------------------------");
	}

	/**
	 * DOC
	 */
	@SuppressWarnings("static-method")
	@After
	public void tearDown() {
		System.out
				.println("TEARDOWN ----------------------------------------------------------------------");
	}
//	CODECLEAN
//	/**
//	 * @throws java.lang.Exception
//	 */
//	@Before
//	public abstract void setUp() throws Exception;
//
//	/**
//	 * @throws java.lang.Exception
//	 */
//	@After
//	public void tearDown() throws Exception {
//
//		System.out.println();
//		System.out
//				.println("--------------------------------------------------------------------------------");
//		System.out.println();
//		
//	}
	/**
	 * DOC 
	 * @param desc
	 * 			Beschreibung des Testfalls
	 * @param testScriptsFolder
	 * 			Ordner mit den Testscripten
	 * @modified rename, tfossi, 31.07.2014
	 */
	protected void buildTestGame(final String desc, final String testScriptsFolder){
		System.out
				.println("--------------------------------------------------------------------------------");

		System.out.println(desc);
		System.out

		.println("--------------------------------------------------------------------------------");
		// Testdaten initieren und Sollwerte einlesen
		buildTestGame(testScriptsFolder);
	}

	
	/**
	 * Anlage eines Testgames
	 * Testdaten initieren und Sollwertdaten einlesen 
	 * 
	 * @param testScriptsFolder
	 * 			Ordner mit den Testscripten
	 * @modified rename, tfossi, 31.07.2014
	 */
	@SuppressWarnings("static-method")
	protected void buildTestGame(final String testScriptsFolder) {

		// Erzeuge eine Grundspiel
		
		// CODECLEAN Ist hier im Test unter Setup()zu finden this.virGame = new
		// DataRoot();
		// this.virGame.setShortname(this.virGame.getName());

		// this.virGame.setDescription(this.virGame.getName());
		// this.virGame.setGame(this.virGame);


		// Vollständiger Pfad zu den TestScripten zum Bauen des Guidelines	
		String path2Guideline = SCRIPT_PATH + "test" + FS + testScriptsFolder
				+ FS + "guideline" + FS;
//		String gn = SCRIPT_PATH + "test" + FS + path
//				+ FS + "game" + FS;

		logger.info("");
		logger.info("---------- BUILD THE GAME ----------");

		logger.info("File: "+NTAB + path2Guideline);
		logger.info("");
		
		// 
		// TESTCASE 1. Regelwerk für die States erstellen 
//		BuildGuideData build = new BuildGuideData();
//		build.buildMetaStateGuide(virGame, fn);
		
//		OLD		
//		if (null == BuildGuidelines.buildMetaStateGuideline(this.virGame, fn))
//			fail("BuildGuidelines.buildMetaStateGuideline liefert null");

		// OFF 1. Regelwerk für die States erstellen
		// BuildGuideData build = new BuildGuideData();
		// build.buildMetaStateGuide(virGame, fn);

		// OLD
		// if (null == BuildGuidelines.buildMetaStateGuideline(this.virGame,
		// fn))
		// fail("BuildGuidelines.buildMetaStateGuideline liefert null");

		//
		//
		// // 3. Datenstruktur erzeugen
		// BuildData.createData(gn, this.virGame, 0, this.virGame,
		// this.virGame.getMetaStateGuideline().getDH());
		//
		// // 2. Konkrete Sollwertgeber für die Vertices erstellen
		// BuildGeber.buildVertexGeber(this.virGame);
		//
		// // 3a. Trigger einsetzen
		// BuildData.recursionTriggeredChangeVertex(this.virGame, this.virGame);
		//
		// // 4. Geberfunktionen erzeugen
		// BuildGeber.recursionGeberDataArray(this.virGame, this.virGame);
		//
		// // 5. Valuecontainer erzeugen
		// BuildData.recursionVertexDataArray(this.virGame, this.virGame);
		//
		// // 6. ActiveFlagcontainer erzeugen
		// BuildData.recursionActiveFlagDataArray(this.virGame, this.virGame);
		//
		// // 7. Vertex mit Daten füllen
		// BuildGame.recursionFillVertexArray(gn, this.virGame, this.virGame);
		//
		// // 8. Vertex eigenem DatenGeber zuordnen
		// BuildGeber.recursionGeberToVertexArray(this.virGame, this.virGame,
		// 0);
		//
		// // 9. Sollwert [w] initial berechnen
		// BuildGame.recursionGeberToW(this.virGame, this.virGame, 0);
		//
		//
		// this.virGame.tst();
		//
		// String[] sollGuidelineScript=null;
		// String[] ist=null;
		// if(this.virGame.logtest!=null &&
		// this.virGame.logtest.equals("CHECK")) {
		// sollGuidelineScript = this.readSollGuidelinedaten(SCRIPT_PATH
		// + "test" + FS + dsc.getDatenklasse(virGame)+dsc.getUID(virGame) + FS
		// + "data" + FS
		// + "sollGuideline.txt");
		// ist = MetaStateGuideline.tstString(this.virGame);
		//
		// try {
		// if (sollGuidelineScript.length != ist.length)
		// throw new java.lang.ArrayIndexOutOfBoundsException(
		// "Längen falsch");

		// for (int i = 0; i < ist.length; i++) {
		// System.err.println(ist[i]);
		// }
		// assertArrayEquals("TestScript fehlerhaft", sollGuidelineScript, ist);
		//
		// logger.info(""+dsc.getDatenklasse(virGame)+dsc.getUID(virGame) +
		// "-Test OK");
		//
		// } catch (java.lang.ArrayIndexOutOfBoundsException e) {
		// System.err.println(e.getMessage());
		// // for (int i = 0; i < sollTestScript.length; i++) {
		// // System.out.println(ist[i]);
		// // System.out.println(TAB + sollTestScript[i]);
		// // }
		// for (int i = 0; i < ist.length; i++) {
		// System.err.println(ist[i]);
		// // System.out.println(TAB + sollTestScript[i]);
		// }
		// fail("Länge falsch");
		// }
		// }
		// if(this.virGame.logtest!=null && this.virGame.logtest.equals("IST"))
		// {
		// ist = MetaStateGuideline.tstString(this.virGame);
		// for (int i = 0; i < ist.length; i++) {
		// System.out.println(ist[i]);
		// }
		// }
	}

	/** Test durchführen 
	 * 
	 * @param lines
	 *            -
	 * @modified -
	 */
	protected void runTest(int lines) {
		this.tstPrepareSchedular();
		this.tstBeschreibung();
		this.tstVertex(lines);
	}

	/** DOC Schedular vorbereiten 
	 * 
	 * @modified -
	 */
	@SuppressWarnings("static-method")
	private void tstPrepareSchedular() {
		if (LOGGER)
			logger.info("---------- SCHEDULAR ----------");
		// this.virGame.setStartdate(this.start);
		//
		// BuildChanger.recursionChangeVertex(this.virGame, this.virGame,
		// this.repList, 25);
		//
		// if (LOGGER)
		// logger.info(this.virGame.showNeuerTermin());
		//
		// BuildChanger.recursionChangeTransition(this.virGame, this.virGame,
		// this.repList, 25);
		//
		// BuildChanger.recursionChangeSwitch(this.virGame, this.virGame,
		// this.repList, 25);

	}

	/**
	 * Den Test durchführen
	 *  
	 * @param lines
	 *            -
	 * @modified -
	 *  		
	 */
	private void tstVertex(int lines) {
		// Double[][] sollYScript = this.readSolldaten(lines, SCRIPT_PATH +
		// "test"
		// + FS + dsc.getDatenklasse(virGame)+dsc.getUID(virGame) + FS + "data"
		// + FS
		// + "sollY.txt");
		//
		// this.virGame.start();
		// if (LOGGER)
		// logger.info("Start der Simulation");
		// if (LOGGER)
		// logger.info("________________________________________");
		// this.virGame.schedularstart();
		//
		// this.virGame.interruptafter(this.dauer);
		//
		// this.auswertung();
		//
		// if (LOGGER)
		// logger.info("________________________________________");
		// if (LOGGER)
		// logger.info("Ende der Simulation");
		//
		// if (!this.repList.isEmpty()) {
		// Report r = this.repList.get(0);
		// Double[] yl = r.ylist.toArray(new Double[0]);
		// for (int i = 0; i < yl.length; i++)
		// yl[i] = new Double(
		// Math.round(100. * yl[i].doubleValue()) / 100.);
		// assertArrayEquals("Y-Test", sollYScript[0], yl);
		// }
	}

	/** DOC Beschreibung des Testumfelds 
	 * @modified -
     */
	private final void tstBeschreibung() {
		//
	}

	
	// /**
	// * Solldaten einlesen
	// *
	// * @param sollfile
	// * Sollwert-File
	// * @return Sollwert-Daten
	// */
	// private final Double[][] readSolldaten(int lines, final String sollfile)
	// {
	//
	// File file = new File(sollfile);
	//
	// Double[][] sollTestScript = new Double[lines][];
	// String[][] rc = new String[lines][];
	//
	// // FIXME Konzept für Mehrzeilige Ergebnisse
	// lines = 1;
	//
	// for (int line = 0; line < lines; line++) {
	// try {
	// BufferedReader in = new BufferedReader(new FileReader(file));
	//
	// rc[line] = in.readLine().split(",");
	//
	// in.close();
	// sollTestScript[line] = new Double[rc[line].length];
	// for (int i = 0; i < rc[line].length; i++)
	// sollTestScript[line][i] = Double.valueOf(rc[line][i]);
	// } catch (FileNotFoundException e) {
	// e.printStackTrace();
	// System.exit(-1);
	// } catch (IOException e) {
	// e.printStackTrace();
	// System.exit(-1);
	// }
	// }
	// return sollTestScript;
	// }
	//
	// private final String[] readSollGuidelinedaten(final String sollfile) {
	// String line;
	// int z = 0;
	// File file = new File(sollfile);
	//
	// String[] sollTestScript = null;
	// try {
	// BufferedReader in = new BufferedReader(new FileReader(file));
	//
	// while ((line = in.readLine()) != null) {
	// line = line.trim();
	// z++;
	// }
	// sollTestScript = new String[z];
	// z = 0;
	// in = new BufferedReader(new FileReader(file));
	// while ((line = in.readLine()) != null) {
	// line = line.trim();
	// sollTestScript[z++] = line;
	//
	// }
	// in.close();
	// } catch (FileNotFoundException e) {
	// e.printStackTrace();
	// System.exit(-1);
	// } catch (IOException e) {
	// e.printStackTrace();
	// System.exit(-1);
	// }
	//
	// return sollTestScript;
	// }
	// ---- Selbstverwaltung --------------------------------------------------

	/** serialVersionUID.java */
	private final static long serialVersionUID = VERSION;
	/** logger */
	private final static Logger logger = Logger.getLogger(_TestBase.class
			.getPackage().getName() + "._TestBase.class");

	/**
	 * Basisklasse für komplexe Gametests.
	 * Anlage eine Testgames
	 * Standardisierte Testdurchführungen
	 * FEATURE Tests für Scriptentwickler
	 * 
	 * @modified -
	 */
	public _TestBase() {
		System.out.println(this.getClass().getSimpleName() + " "
				+ serialVersionUID);
	}
}
