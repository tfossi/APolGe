/**
 * ElementTest.java
 * Branch master
 * APolGe
 * tfossi-team
 * licence GPLv3  
 */
package tfossi.apolge.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static tfossi.apolge.common.constants.ConstValue.FS;
import static tfossi.apolge.common.constants.ConstValue.LFCR;
import static tfossi.apolge.common.constants.ConstValue.SCRIPT_PATH;

import org.apache.log4j.Logger;
import org.junit.Test;

import tfossi.apolge.common.constants.ConstValueExtension;
import tfossi.apolge.common.scripting.LoadScript;
import tfossi.apolge.common.scripting.LoadScriptException;
import tfossi.apolge.common.scripting.p.ParseException;
import tfossi.apolge.common.scripting.t.TableMap;
import tfossi.apolge.common.scripting.vp.ValueParser;
import tfossi.apolge.data.core.Element;
import tfossi.apolge.data.core._ElementBuilder;
import tfossi.apolge.data.core._ElementBuilderDirector;

/**
 * Testet die Funktionalität der Scriptverarbeitung.<br>
 * 
 * ACHTUNG: Zum Scripttesten mit Loadscript ist eine fixe VERSIONS-Nummer zu
 * verwenden! Einzustellen in {@link ConstValueExtension#VERSION}
 * 
 * Voraussetzung für alle weiteren Vorgänge ist das Funktionieren des
 * Scriptings. <br>
 * 
 * @author tfossi
 * @version 1.07.2014
 * @modified Coderevision, tfossi, 31.07.2014
 * @since Java 1.6
 */
public class ElementTest {
	/** TESTPATH incl. abschl. FS */
	private final static String TESTPATH = SCRIPT_PATH + "tst" +FS + "elements"
			+ FS;
	

/** Instanz ValueParser */
final ValueParser vp = new ValueParser();

//	/** Einfache Operationen */
//	String[][] simpleTestdaten = new String[][] {
////			{ "Test Byte",	"A Simple" + FS + "01 Simple Byte" },
////			{ "Test Short",	"A Simple" + FS + "02 Simple Short" },
//			{ "Test Integer", "A Simple" + FS + "03 Simple Integer",
//				"{g=[false], plus=[3], klg=[true], minus=[-1], ug=[true], grg=[false], durch=[2], gr=[false], mal=[30], kl=[true], h=[64]}" },
////			{ "Test Long", "A Simple" + FS + "04 Long Integer" },
////			{ "Test Float", "A Simple" + FS + "05 Float Integer" },
////			{ "Test Double", "A Simple" + FS + "06 Double Integer" } 
//			};
//
//	/** Einfache Funktionen */
//	String[][] simpleFunctions = new String[][] {
////			{ "Test Byte",	"A Simple" + FS + "01 Simple Byte" },
////			{ "Test Short",	"A Simple" + FS + "02 Simple Short" },
//			{ "Test Pass0 Integer", "B Simple" + FS + "01 Simple Math",
//				"{a=[2]}" },
////			{ "Test Long", "A Simple" + FS + "04 Long Integer" },
////			{ "Test Float", "A Simple" + FS + "05 Float Integer" },
////			{ "Test Double", "A Simple" + FS + "06 Double Integer" } 
//			};
//
//	/** Pass2 Funktionen */
//	String[][] pass2Functions = new String[][] {
////			{ "Test Byte",	"A Simple" + FS + "01 Simple Byte" },
////			{ "Test Short",	"A Simple" + FS + "02 Simple Short" },
//			{ "Test Pass2", "C Pass2" + FS + "01 Math",
//				"{a=[f:=rint( 100.0 ) mit Pass2-Parameter!]}" },
////			{ "Test Long", "A Simple" + FS + "04 Long Integer" },
////			{ "Test Float", "A Simple" + FS + "05 Float Integer" },
////			{ "Test Double", "A Simple" + FS + "06 Double Integer" } 
//			};
//
//	/** Pass3 Funktionen */
//	String[][] pass3Functions = new String[][] {
////			{ "Test Byte",	"A Simple" + FS + "01 Simple Byte" },
////			{ "Test Short",	"A Simple" + FS + "02 Simple Short" },
//			{ "Test Pass2", "D Pass3" + FS + "01 Adress",
//				"{a=[f:=ADR( [A, B, INIT] ) mit Pass3-Parameter!]}" },
////			{ "Test Long", "A Simple" + FS + "04 Long Integer" },
////			{ "Test Float", "A Simple" + FS + "05 Float Integer" },
////			{ "Test Double", "A Simple" + FS + "06 Double Integer" } 
//			};
	
	/** createElements */
	String[][] createElements = new String[][] {
//			{ "Test Byte",	"A Simple" + FS + "01 Simple Byte" },
//			{ "Test Short",	"A Simple" + FS + "02 Simple Short" },
			{ "Test create", "root",
				"Elementname: root"+LFCR+"b: b/1.0/Double"+LFCR+"c: c/$0$/String"+LFCR+"a: a/1/Integer"+LFCR },
//			{ "Test Long", "A Simple" + FS + "04 Long Integer" },
//			{ "Test Float", "A Simple" + FS + "05 Float Integer" },
//			{ "Test Double", "A Simple" + FS + "06 Double Integer" } 
			};

//	/** id */
//	public int id = 0; 
	/**
	 * Testen der elemmentaren Rechenfunktionen
	 * 
	 * @modified -
	 */
	@Test
	public final void testASimple() {
		
		_ElementBuilderDirector ebd = new _ElementBuilderDirector();
		
		
		for (int row = 0; row < this.createElements.length; row++) {
			System.out.println(LFCR+LFCR+"-------------------------------------------------------------------------"+
		LFCR+LFCR+LFCR+"Post2String: "
					+ this.createElements[row][0] + LFCR
					+ this.createElements[row][1]+LFCR);
			try {
				String name = "root";
				_ElementBuilder parent = null;
				LoadScript ls = new LoadScript(TESTPATH
						+ this.createElements[row][1], null,true);
				
				_ElementBuilder eb = new _ElementBuilder(name, (TableMap)null, parent, ls.getTable(), TESTPATH);
				
				System.out.println(eb.toString());
				
				Element e = new Element();
				eb.createRoot(e);
				
				assertEquals(this.createElements[row][0], this.createElements[row][2], eb.toString());

		
			} catch (LoadScriptException e) {
				System.out.println(e.getLocalizedMessage());
				if (e.getLocalizedMessage().contains("Ordner ["))
					System.out.println("OK");
				else if (e.getLocalizedMessage().contains("Datei [")
						&& e.getLocalizedMessage().contains("Gibt es nicht"))
					System.out.println("OK");
				else {
					e.printStackTrace();
					fail(e.getMessage());
				}
			} catch (ParseException e) {
				e.printStackTrace();
				fail(e.getMessage());
			} catch (ArrayIndexOutOfBoundsException e) {
				e.printStackTrace();
				fail(e.getMessage());
			} catch (NullPointerException e) {
				e.printStackTrace();
				fail(e.getMessage());
			} 
		}
	}


	// ---- Selbstverwaltung --------------------------------------------------

	/**
	 * serialVersionUID<br>
	 * Hint: <code>VERSION</code> does not exists at this moment
	 */
	@SuppressWarnings("unused")
	private final static long serialVersionUID = -1L;
	/** logger */
	@SuppressWarnings("unused")
	private final static Logger logger = Logger.getLogger(ElementTest.class
			.getPackage().getName());
}
