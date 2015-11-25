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
import tfossi.apolge.data.core._ElementBuilder;

/**
 * Testet die Funktionalität der Scriptverarbeitung.<br>
 * 
 * ACHTUNG: Zum Scripttesten mit Loadscript ist eine fixe VERSIONS-Nummer zu
 * verwenden! Einzustellen in {@link ConstValueExtension#VERSION}
 * 
 * Voraussetzung für alle weiteren Vorgänge ist das Funktionieren des
 * Scriptings. <br>
 * 
 * TODO Testen der Datumstypen.<br>
 * TODO Testen der Stringberechnungen <br>
 * TODO Testen der Datumfunktionen<br>
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

	/** Dateien für testFilesystem gibt es nicht */
	String[][] simpleTestdaten = new String[][] {
//			{ "Test Byte",	"A Simple" + FS + "01 Simple Byte" },
//			{ "Test Short",	"A Simple" + FS + "02 Simple Short" },
			{ "Test Integer", "A Simple" + FS + "03 Simple Integer" },
//			{ "Test Long", "A Simple" + FS + "04 Long Integer" },
//			{ "Test Float", "A Simple" + FS + "05 Float Integer" },
//			{ "Test Double", "A Simple" + FS + "06 Double Integer" } 
			};



	/** Testen der Typzuordnung */
	String[][] typenzuordnungTestdaten = new String[][] {
			{ "Test Byte", "C Typenzuordnung" + FS + "01 byte.apo", ";a=5b;",
					"[;, a, =, 5b, ;]", "{_={a=[5b]}}" },
			{ "Test Short", "C Typenzuordnung" + FS + "02 short.apo", ";a=5s;",
					"[;, a, =, 5s, ;]", "{_={a=[5s]}}" },
			{ "Test Integer", "C Typenzuordnung" + FS + "03 integer.apo",
					";a=5;", "[;, a, =, 5, ;]", "{_={a=[5]}}" },
			{ "Test Long", "C Typenzuordnung" + FS + "04 long.apo", ";a=5L;",
					"[;, a, =, 5L, ;]", "{_={a=[5L]}}" },
			{ "Test Float", "C Typenzuordnung" + FS + "05 float.apo", ";a=5f;",
					"[;, a, =, 5f, ;]", "{_={a=[5f]}}" },
			{ "Test Double", "C Typenzuordnung" + FS + "06 double.apo",
					";a=5.;", "[;, a, =, 5., ;]", "{_={a=[5.]}}" },
			{ "Test Bool", "C Typenzuordnung" + FS + "07 bool.apo",
					";a=true;b=false;", "[;, a, =, true, ;, b, =, false, ;]",
					"{_={a=[true], b=[false]}}" },
			{ "Test Hexa", "C Typenzuordnung" + FS + "08 hexa.apo", ";a=0x1C;",
					"[;, a, =, 0x1C, ;]", "{_={a=[0x1C]}}" },
			{ "Test Octal", "C Typenzuordnung" + FS + "09 octa.apo", ";a=0o7;",
					"[;, a, =, 0o7, ;]", "{_={a=[0o7]}}" },
			{ "Test Binär", "C Typenzuordnung" + FS + "10 binaer.apo",
					";a=01;", "[;, a, =, 01, ;]", "{_={a=[01]}}" },
			{ "Test Char", "C Typenzuordnung" + FS + "11 char.apo", ";a='A';",
					"[;, a, =, A, ;]", "{_={a=[A]}}" },
			{ "Test String", "C Typenzuordnung" + FS + "12 string.apo",
					";a=$0$;", "[;, a, =, $0$, ;]", "{_={a=[$0$]}}" },

	};



	/**
	 * Testen der elemmentaren Rechenfunktionen
	 * 
	 * @modified -
	 */
	@Test
	public final void testASimple() {

		for (int row = 0; row < this.simpleTestdaten.length; row++) {
			System.out.println("Post2String: "
					+ this.simpleTestdaten[row][0] + LFCR
					+ this.simpleTestdaten[row][1]);
			try {
				LoadScript ls = new LoadScript(TESTPATH
						+ this.simpleTestdaten[row][1], null);
				ls.generateTokenlist();
				ls.generateTable();
				_ElementBuilder eb =
				this.vp.valueParser(null, ls.getTable(), ls.getTable(), ls.getQuotes(), null, (byte)0);
		
//		assertEquals(this.testgruppe[row][0], this.testgruppe[row][5],
//				eb.toString());
				System.out.println(eb.toString());
		
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
				fail(e.getMessage());

			} catch (ArrayIndexOutOfBoundsException e) {
				fail(e.getMessage());
			} catch (NullPointerException e) {
				e.printStackTrace();
				fail(e.getMessage());
			} catch (TableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ScriptException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
	private final static Logger logger = Logger.getLogger(ValueParserTest.class
			.getPackage().getName());
}
