/**
 * LoadScriptTest.java
 * Branch master
 * APolGe
 * tfossi-team
 * licence GPLv3  
 */
package tfossi.apolge.common.scripting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static tfossi.apolge.common.constants.ConstValue.FS;
import static tfossi.apolge.common.constants.ConstValue.LFCR;
import static tfossi.apolge.common.constants.ConstValue.SCRIPT_PATH;

import org.apache.log4j.Logger;
import org.junit.Test;

import tfossi.apolge.common.constants.ConstValueExtension;
import tfossi.apolge.common.scripting.p.ParseException;
import tfossi.apolge.common.scripting.t.TableException;
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
public class ValueParserTest {
	/** TESTPATH incl. abschl. FS */
	private final static String TESTPATH = SCRIPT_PATH + "tst" + FS + "valueparser"
			+ FS;
	

/** Instanz ValueParser */
final ValueParser vp = new ValueParser();

	/** Einfache Operationen */
	String[][] simpleTestdaten = new String[][] {
//			{ "Test Byte",	"A Simple" + FS + "01 Simple Byte" },
//			{ "Test Short",	"A Simple" + FS + "02 Simple Short" },
//			{ "Test Integer", "A Simple" + FS + "03 Simple Integer",
//				"{g=[false], plus=[3], klg=[true], minus=[-1], ug=[true], grg=[false], durch=[2], gr=[false], mal=[30], kl=[true], h=[64]}" },
//			{ "Test Long", "A Simple" + FS + "04 Long Integer" },
//			{ "Test Float", "A Simple" + FS + "05 Float Integer" },
//			{ "Test Double", "A Simple" + FS + "06 Double Integer" } 
			{ "Test String", "A Simple" + FS + "07 Simple String",
				"Elementname: Test String"+LFCR+
				"g: g/false/Boolean"+LFCR+
				"plus: plus/AB/String"+LFCR+
				"ug: ug/true/Boolean"+LFCR } 
			};

	/** Einfache Funktionen */
	String[][] simpleFunctions = new String[][] {
//			{ "Test Byte",	"A Simple" + FS + "01 Simple Byte" },
//			{ "Test Short",	"A Simple" + FS + "02 Simple Short" },
			{ "Test Pass0 Integer", "B Simple" + FS + "01 Simple Math",
				"Elementname: Test Pass0 Integer"+LFCR+
				"a: a/2/Integer"+LFCR 
			},
//			{ "Test Long", "A Simple" + FS + "04 Long Integer" },
//			{ "Test Float", "A Simple" + FS + "05 Float Integer" },
//			{ "Test Double", "A Simple" + FS + "06 Double Integer" } 
			{ "Test Condition", "B Simple" + FS + "08 Condition",
				"Elementname: Test Condition"+LFCR+
				"b: b/1/Integer"+LFCR+
				"a: a/8/Integer"+LFCR 
			} 
			};

	/** Pass2 Funktionen */
	String[][] pass2Functions = new String[][] {
//			{ "Test Byte",	"A Simple" + FS + "01 Simple Byte" },
//			{ "Test Short",	"A Simple" + FS + "02 Simple Short" },
//			{ "Test Pass2", "C Pass2" + FS + "01 Math",
//				"{a=[f:=rint( 100.0 ) mit Pass2-Parameter!]}" },
			{ "Test Pass2indirect", "C Pass2" + FS + "02 IndirectMath",
				"{a=[f:=rint( 100.0 ) mit Pass2-Parameter!]}" },
//			{ "Test Long", "A Simple" + FS + "04 Long Integer" },
//			{ "Test Float", "A Simple" + FS + "05 Float Integer" },
//			{ "Test Double", "A Simple" + FS + "06 Double Integer" } 
			};

	/** Pass3 Funktionen */
	String[][] pass3Functions = new String[][] {
//			{ "Test Byte",	"A Simple" + FS + "01 Simple Byte" },
//			{ "Test Short",	"A Simple" + FS + "02 Simple Short" },
			{ "Test Pass2", "D Pass3" + FS + "01 Adress",
				"{a=[f:=ADR( [A, B, INIT] ) mit Pass3-Parameter!]}" },
//			{ "Test Long", "A Simple" + FS + "04 Long Integer" },
//			{ "Test Float", "A Simple" + FS + "05 Float Integer" },
//			{ "Test Double", "A Simple" + FS + "06 Double Integer" } 
			};
	
	/** Config Funktionen */
	String[][] configFunctions = new String[][] {
//			{ "Test Byte",	"A Simple" + FS + "01 Simple Byte" },
//			{ "Test Short",	"A Simple" + FS + "02 Simple Short" },
			{ "Test config", "configs" + FS + "config",
				"{VERSION=[25], BREITE=[25], MAXBREITE=[100]}" },
//			{ "Test Long", "A Simple" + FS + "04 Long Integer" },
//			{ "Test Float", "A Simple" + FS + "05 Float Integer" },
//			{ "Test Double", "A Simple" + FS + "06 Double Integer" } 
			};

	/**
	 * Testen der elemmentaren Rechenfunktionen
	 * 
	 * @modified -
	 */
//	@Test
	public final void testASimple() {

		_ElementBuilderDirector ebd = new _ElementBuilderDirector();
		
		for (int row = 0; row < this.simpleTestdaten.length; row++) {
			System.out.println("Post2String: "
					+ this.simpleTestdaten[row][0] + LFCR
					+ this.simpleTestdaten[row][1]);
			try {
				LoadScript ls = new LoadScript(TESTPATH
						+ this.simpleTestdaten[row][1], null);
				ls.generateTokenlist();
				ls.generateTable();
				this.vp.valueParser(ls.getTable(), ls.getQuotes(), (byte)0);

				_ElementBuilder eb = new _ElementBuilder(this.simpleTestdaten[row][0], null,null,  ls.getTable(), TESTPATH);
				assertEquals(this.simpleTestdaten[row][0], this.simpleTestdaten[row][2], eb.toString());

		
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
			} catch (TableException e) {
				e.printStackTrace();
				fail(e.getMessage());
			} catch (ScriptException e) {
				e.printStackTrace();
				fail(e.getMessage());
			}

		}
	}

	/**
	 * Testen der Funktionen und Methoden
	 * 
	 * @modified -
	 */
//	@Test
	public final void testBFunction() {
		_ElementBuilderDirector ebd = new _ElementBuilderDirector();
		for (int row = 0; row < this.simpleFunctions.length; row++) {
			System.out.println("Post2String: "
					+ this.simpleFunctions[row][0] + LFCR
					+ this.simpleFunctions[row][1]);
			try {
				LoadScript ls = new LoadScript(TESTPATH
						+ this.simpleFunctions[row][1], null);
				ls.generateTokenlist();
				ls.generateTable();
				this.vp.valueParser( ls.getTable(), ls.getQuotes(), (byte)0);

				_ElementBuilder eb = new _ElementBuilder(this.simpleFunctions[row][0], null, null, ls.getTable(), TESTPATH);
				assertEquals(this.simpleFunctions[row][0], this.simpleFunctions[row][2], eb.toString());

		
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
			} catch (TableException e) {
				e.printStackTrace();
				fail(e.getMessage());
			} catch (ScriptException e) {
				e.printStackTrace();
				fail(e.getMessage());
			}

		}
	}
	
	/**
	 * Testen Pass2 
	 * 
	 * @modified -
	 */
	@Test
	public final void testCPass2() {
		_ElementBuilderDirector ebd = new _ElementBuilderDirector();
		for (int row = 0; row < this.pass2Functions.length; row++) {
			System.out.println("Post2String: "
					+ this.pass2Functions[row][0] + LFCR
					+ this.pass2Functions[row][1]);
			try {
				LoadScript ls = new LoadScript(TESTPATH
						+ this.pass2Functions[row][1], null);
				ls.generateTokenlist();
				ls.generateTable();
				logger.trace("Table: "+LFCR+ls.getTable());
				this.vp.valueParser(ls.getTable(), ls.getQuotes(), (byte)0);
				logger.trace("Table: "+LFCR+ls.getTable());
				assertEquals(this.pass2Functions[row][0], this.pass2Functions[row][2], ls.getTable());
				
//				_ElementBuilder eb = new _ElementBuilder(this.pass2Functions[row][0], null, null, ls.getTable(), TESTPATH);
//				assertEquals(this.pass2Functions[row][0], this.pass2Functions[row][2], eb.toString());

//				System.err.print(VERSION);
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
			} catch (TableException e) {
				e.printStackTrace();
				fail(e.getMessage());
			} catch (ScriptException e) {
				e.printStackTrace();
				fail(e.getMessage());
			}

		}
	}
	
	/**
	 * Testen Pass3 
	 * 
	 * @modified -
	 */
	@Test
	public final void testDPass3() {
		_ElementBuilderDirector ebd = new _ElementBuilderDirector();
		for (int row = 0; row < this.pass3Functions.length; row++) {
			System.out.println("Post2String: "
					+ this.pass3Functions[row][0] + LFCR
					+ this.pass3Functions[row][1]);
			try {
				LoadScript ls = new LoadScript(TESTPATH
						+ this.pass3Functions[row][1], null);
				ls.generateTokenlist();
				ls.generateTable();
				this.vp.valueParser( ls.getTable(), ls.getQuotes(), (byte)0);

				_ElementBuilder eb = new _ElementBuilder(this.pass3Functions[row][0], null, null, ls.getTable(), TESTPATH);
				assertEquals(this.pass3Functions[row][0], this.pass3Functions[row][2], eb.toString());

		
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
			} catch (TableException e) {
				e.printStackTrace();
				fail(e.getMessage());
			} catch (ScriptException e) {
				e.printStackTrace();
				fail(e.getMessage());
			}

		}
	}

	


	/**
	 * Testen Pass3 
	 * 
	 * @modified -
	 */
//	@Test
	public final void testConfig() {
		for (int row = 0; row < this.configFunctions.length; row++) {
			System.out.println("Post2String: "
					+ this.configFunctions[row][0] + LFCR
					+ this.configFunctions[row][1]);
			try {
				LoadScript ls = new LoadScript(this.configFunctions[row][1], null);
				ls.generateTokenlist();
				ls.generateTable();
				this.vp.valueParser( ls.getTable(), ls.getQuotes(), (byte)0);
				
				assertEquals(this.configFunctions[row][0], this.configFunctions[row][2],ls.getTable().toString()); 
					
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
			} catch (TableException e) {
				e.printStackTrace();
				fail(e.getMessage());
			} catch (ScriptException e) {
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
	private final static Logger logger = Logger.getLogger(ValueParserTest.class
			.getPackage().getName()+".ValueParserTest");
}
