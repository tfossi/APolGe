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
public class LoadScriptTest {
	/** TESTPATH incl. abschl. FS */
	private final static String TESTPATH = SCRIPT_PATH + "tst" + FS + "base"
			+ FS;

	/** Dateien für testFilesystem gibt es nicht */
	String[][] filesystemTestdaten = new String[][] {
			{ "Test falscher Pfad",
					"A Filesystem" + FS + "No Pfad" + FS + "Gibt nicht" },
			{ "Test falsche Datei", "A Filesystem" + FS + "Gibt es nicht" }, };

	/** Testen der Basisfunktionen des Scriptparsers */
	String[][] basisTestdaten = new String[][] {
			{ "Test leere Datei", "B Basis" + FS + "00 leereDatei.apo", ";",
					"[;]", "{_={}}" },
			{ "Test Zeilenkommentar",
					"B Basis" + FS + "01 zeilenkommentar.apo", ";", "[;]",
					"{_={}}" },
			{ "Test Blockkommentar", "B Basis" + FS + "02 blockkommentar.apo",
					";", "[;]", "{_={}}" },
			{ "Test Quoten/String", "B Basis" + FS + "03 quoten.apo",
					";?0=$0$;", "[;, ?0, =, $0$, ;]", "{_={?0=[Dies ist ein String]}}" },
			{ "Test Whiteblancs", "B Basis" + FS + "04 whiteblancs.apo", ";",
					"[;]", "{_={}}" },
			{ "Test WrapLines", "B Basis" + FS + "05 wrap.apo",
					";a=5+25;b=12;",
					"[;, a, =, 5, +, 25, ;, b, =, 12, ;]",
					"{_={a=[5, +, 25], b=[12]}}" }, };

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
					";a=$0$;", "[;, a, =, $0$, ;]", "{_={a=[Dies ist "+LFCR+"ein "+LFCR+"String]}}" },

	};

	/** Testen von Block, Index und Liste */
	String[][] blockIndexListeTestdaten = new String[][] {

			{ "Test Listen",
					"D Block, Index und Liste" + FS + "01 listen.apo",
					";?0=1;?1=2;?2=3;k=7;?3=A;?4=B;",
					"[;, ?0, =, 1, ;, ?1, =, 2, ;, ?2, =, 3, ;, k, =, 7, ;, ?3, =, A, ;, ?4, =, B, ;]",
					"{_={?0=[1], ?1=[2], ?2=[3], k=[7], ?3=[A], ?4=[B]}}" },
			{"Test Block",
					"D Block, Index und Liste" + FS + "02 block.apo",
					";a={i2=2;i1={?0=asd1;?1=asd2};z=7};b={?2=k;?3=y};c=VEKTOR(A,B,C);d={?4=x};e=VEKTORT(1,2,3,4);",
					"[;, a, =, {, i2, =, 2, ;, i1, =, {, ?0, =, asd1, ;, ?1, =, asd2, }, ;, z, =, 7, }, ;, b, =, {, ?2, =, k, ;, ?3, =, y, }, ;, c, =, VEKTOR, (, A, ,, B, ,, C, ), ;, d, =, {, ?4, =, x, }, ;, e, =, VEKTORT, (, 1, ,, 2, ,, 3, ,, 4, ), ;]",
					"{_={a=[{i2=[2], i1=[{?0=[asd1], ?1=[asd2]}], z=[7]}], b=[{?2=[k], ?3=[y]}], c=[VEKTOR, (, A, ,, B, ,, C, )], d=[{?4=[x]}], e=[VEKTORT, (, 1, ,, 2, ,, 3, ,, 4, )]}}", 
					},
			{ "Test Index", "D Block, Index und Liste" + FS + "03 index.apo",
					";a={0=1};", "[;, a, =, {, 0, =, 1, }, ;]",
					"{_={a=[{0=[1]}]}}" },

	};

	/** Testen der Allgemeinen Funktionen, Addressen, Vektoren und Matrizen */
	String[][] funktionengruppeTestdaten = new String[][] {
			{
					"Test Funktion",
					"E Allgemeine Funktionen, Addressen, Vektoren, Matrizen"
							+ FS + "01 function.apo", ";a=max(A,B,C);",
					"[;, a, =, max, (, A, ,, B, ,, C, ), ;]",
					"{_={a=[max, (, A, ,, B, ,, C, )]}}" },
			{
					"Test Adresse",
					"E Allgemeine Funktionen, Addressen, Vektoren, Matrizen"
							+ FS + "02 adress.apo",
					";a=4+ADR(EBName,EName,SubEName,Attribute)*17;b=a;c=b;",
					"[;, a, =, 4, +, ADR, (, EBName, ,, EName, ,, SubEName, ,, Attribute, ), *, 17, ;, b, =, a, ;, c, =, b, ;]",
					"{_={a=[4, +, ADR, (, EBName, ,, EName, ,, SubEName, ,, Attribute, ), *, 17], b=[a], c=[b]}}" },
			{
					"Test Vektor",
					"E Allgemeine Funktionen, Addressen, Vektoren, Matrizen"
							+ FS + "03 vector.apo",
					";a=VEKTOR(1,2,3);b=VEKTORT(A,B,C);",
					"[;, a, =, VEKTOR, (, 1, ,, 2, ,, 3, ), ;, b, =, VEKTORT, (, A, ,, B, ,, C, ), ;]",
					"{_={a=[VEKTOR, (, 1, ,, 2, ,, 3, )], b=[VEKTORT, (, A, ,, B, ,, C, )]}}" },
			{
					"Test Matrix",
					"E Allgemeine Funktionen, Addressen, Vektoren, Matrizen"
							+ FS + "04 matrix.apo",
					";a=MATRIX(VEKTOR(1,2,3)VEKTOR(4,5,6)VEKTOR(7,8,9));b=MATRIXT(VEKTOR(A,B,C)VEKTOR(D,E,F)VEKTOR(G,H,I));",
					"[;, a, =, MATRIX, (, VEKTOR, (, 1, ,, 2, ,, 3, ), VEKTOR, (, 4, ,, 5, ,, 6, ), VEKTOR, (, 7, ,, 8, ,, 9, ), ), ;, b, =, MATRIXT, (, VEKTOR, (, A, ,, B, ,, C, ), VEKTOR, (, D, ,, E, ,, F, ), VEKTOR, (, G, ,, H, ,, I, ), ), ;]",
					"{_={a=[MATRIX, (, VEKTOR, (, 1, ,, 2, ,, 3, ), VEKTOR, (, 4, ,, 5, ,, 6, ), VEKTOR, (, 7, ,, 8, ,, 9, ), )], b=[MATRIXT, (, VEKTOR, (, A, ,, B, ,, C, ), VEKTOR, (, D, ,, E, ,, F, ), VEKTOR, (, G, ,, H, ,, I, ), )]}}" }, };

	/** Testen der Basisberechnungen */
	String[][] basisrechnungenTestdaten = new String[][] {
			{
					"Test Algebra-Operatoren",
					"F Basisrechnungen" + FS + "01 algebra.apo",
					";a=1+2*3-4/5+6^2+-7+2*(8+9);",
					"[;, a, =, 1, +, 2, *, 3, -, 4, /, 5, +, 6, ^, 2, +, -, 7, +, 2, *, (, 8, +, 9, ), ;]",
					"{_={a=[1, +, 2, *, 3, -, 4, /, 5, +, 6, ^, 2, +, -, 7, +, 2, *, (, 8, +, 9, )]}}" },
			{
					"Test Bool-Operatoren",
					"F Basisrechnungen" + FS + "02 logic.apo",
					";a=true||false&&!false|&a>b&|a<b||a>=b||a<=b||a<>b||a==b;",
					"[;, a, =, true, ||, false, &&, !, false, a, >, b, a, <, b, ||, a, >=, b, ||, a, <=, b, ||, a, <>, b, ||, a, ==, b, ;]",
					"{_={a=[true, ||, false, &&, !, false, a, >, b, a, <, b, ||, a, >=, b, ||, a, <=, b, ||, a, <>, b, ||, a, ==, b]}}" },

	};

	/** Testen der Marker: Init, Flow, Alternative, 2Pass, 3Pass */
	String[][] datenstrukturenTestdaten = new String[][] {
			{	"Test InitialFlow",
					"G Marker" + FS + "01 initialflow.apo",
					";a={Init=true};b={Flow=false};c=b.Flow+a.Init;",
					"[;, a, =, {, Init, =, true, }, ;, b, =, {, Flow, =, false, }, ;, c, =, b.Flow, +, a.Init, ;]",
					"{_={a=[{Init=[true]}], b=[{Flow=[false]}], c=[b.Flow, +, a.Init]}}" 
					},
			{	"Test Alternative",
					"G Marker" + FS + "02 alternative.apo",
					";c=5;a=WENN(A,B,WENN(C,D,EE));e=2;b=WENN(F,G,WENN(H,I,JJ));d=10;",
					"[;, c, =, 5, ;, a, =, WENN, (, A, ,, B, ,, WENN, (, C, ,, D, ,, EE, ), ), ;, e, =, 2, ;, b, =, WENN, (, F, ,, G, ,, WENN, (, H, ,, I, ,, JJ, ), ), ;, d, =, 10, ;]",
					"{_={c=[5], a=[WENN, (, A, ,, B, ,, WENN, (, C, ,, D, ,, EE, ), )], e=[2], b=[WENN, (, F, ,, G, ,, WENN, (, H, ,, I, ,, JJ, ), )], d=[10]}}" 
					},
			{ "Test InitialFlowAlternative",
					"G Marker" + FS + "03 initialflowalternative.apo",
					";a={INIT=WENN(A,B,WENN(C,D,E))};",
					"[;, a, =, {, INIT, =, WENN, (, A, ,, B, ,, WENN, (, C, ,, D, ,, E, ), ), }, ;]",
					"{_={a=[{INIT=[WENN, (, A, ,, B, ,, WENN, (, C, ,, D, ,, E, ), )]}]}}", "" },
			{ "Test 2Pass", "G Marker" + FS + "04 2passfunction.apo",
					";a=rint(5.);",
					"[;, a, =, rint, (, 5., ), ;]",
					"{_={a=[rint, (, 5., )]}}", "" },
			{ "Test 3Pass", "G Marker" + FS + "05 3passfunction.apo",
					";a=ADR(A,B,C,D);",
					"[;, a, =, ADR, (, A, ,, B, ,, C, ,, D, ), ;]",
					"{_={a=[ADR, (, A, ,, B, ,, C, ,, D, )]}}", "" }, };

	/** Testen komplexer Berechnungen */
	String[][] komplexerechnungenTestdaten = new String[][] {
			{
					"Test Algebra",
					"H Komplexe Rechnungen" + FS + "01 algebra.apo",
					";:WORK:a=1+2*3-4/5+6^2+-7+2*(8+9);",
					"[;, :WORK:a, =, 1, +, 2, *, 3, -, 4, /, 5, +, 6, ^, 2, +, -, 7, +, 2, *, (, 8, +, 9, ), ;]",
					"{_={:WORK:a=[1, +, 2, *, 3, -, 4, /, 5, +, 6, ^, 2, +, -, 7, +, 2, *, (, 8, +, 9, )]}}" },
			{
					"Test Bool",
					"H Komplexe Rechnungen" + FS + "02 logic.apo",
					";:WORK:a=true||false&&!false|&a>b&|a<b||a>=b||a<=b||a<>b||a==b;",
					"[;, :WORK:a, =, true, ||, false, &&, !, false, a, >, b, a, <, b, ||, a, >=, b, ||, a, <=, b, ||, a, <>, b, ||, a, ==, b, ;]",
					"{_={:WORK:a=[true, ||, false, &&, !, false, a, >, b, a, <, b, ||, a, >=, b, ||, a, <=, b, ||, a, <>, b, ||, a, ==, b]}}" }, };

	/** Testen der Elementenanlage im 0., 1. nd 2. Pass */
	String[][] elementanlageTestdaten = new String[][] { {
			"Test Simple",
			"I Elementanlage" + FS + "01 simple.apo",
			";:WORK:a=1+2*3-4/5+6^2+-7+2*(8+9);",
			"[;, :WORK:a, =, 1, +, 2, *, 3, -, 4, /, 5, +, 6, ^, 2, +, -, 7, +, 2, *, (, 8, +, 9, ), ;]",
			"{_={:WORK:a=[1, +, 2, *, 3, -, 4, /, 5, +, 6, ^, 2, +, -, 7, +, 2, *, (, 8, +, 9, )]}}" },

	};

	/**
	 * Testen der elemmentaren Filefunktionen
	 * 
	 * @modified -
	 */
	@Test
	public final void testAFileSystem() {

		for (int row = 0; row < this.filesystemTestdaten.length; row++) {
			System.out.println("Post2String: "
					+ this.filesystemTestdaten[row][0] + LFCR
					+ this.filesystemTestdaten[row][1]);
			try {
				LoadScript ls = new LoadScript(TESTPATH
						+ this.filesystemTestdaten[row][1], null);
				assertEquals(this.filesystemTestdaten[row][0],
						this.filesystemTestdaten[row][2],
						ls.postscript2String());

				ls.generateTokenlist();
				System.out.println(this.filesystemTestdaten[row][0]
						+ " POST OK");

				assertEquals(this.filesystemTestdaten[row][0],
						this.filesystemTestdaten[row][3], ls.tokenlist2String());
				System.out.println(this.filesystemTestdaten[row][0]
						+ " TOKEN OK");

				ls.generateTable();
				assertEquals(this.filesystemTestdaten[row][0],
						this.filesystemTestdaten[row][4], ls.block2String());
				System.out.println(this.filesystemTestdaten[row][0]
						+ " BLOCK OK");
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
				e.printStackTrace();
				fail(e.getMessage());
			} catch (ScriptException e) {
				e.printStackTrace();
				fail(e.getMessage());
			}

		}
	}

	/**
	 * Testen der Basisfunktionen des Parsers
	 * 
	 * @modified -
	 */
	@Test
	public final void testBBasis() {

		for (int row = 0; row < this.basisTestdaten.length; row++) {
			System.out.println("Post2String: " + this.basisTestdaten[row][0]
					+ LFCR + this.basisTestdaten[row][1]);
			try {
				LoadScript ls = new LoadScript(TESTPATH
						+ this.basisTestdaten[row][1], null);
				assertEquals(this.basisTestdaten[row][0],
						this.basisTestdaten[row][2], ls.postscript2String());

				ls.generateTokenlist();
				System.out.println(this.basisTestdaten[row][0] + " POST OK");

				assertEquals(this.basisTestdaten[row][0],
						this.basisTestdaten[row][3], ls.tokenlist2String());
				System.out.println(this.basisTestdaten[row][0] + " TOKEN OK");

				ls.generateTable();
				assertEquals(this.basisTestdaten[row][0],
						this.basisTestdaten[row][4], ls.block2String());
				System.out.println(this.basisTestdaten[row][0] + " BLOCK OK");
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
				e.printStackTrace();
				fail(e.getMessage());
			} catch (ScriptException e) {
				e.printStackTrace();
				fail(e.getMessage());
			}

		}
	}

	/**
	 * Testen der Typenzuordnungen
	 * 
	 * @modified -
	 */
	@Test
	public final void testCTypenzuordnung() {

		for (int row = 0; row < this.typenzuordnungTestdaten.length; row++) {
			System.out.println("Post2String: "
					+ this.typenzuordnungTestdaten[row][0] + LFCR
					+ this.typenzuordnungTestdaten[row][1]);
			try {
				LoadScript ls = new LoadScript(TESTPATH
						+ this.typenzuordnungTestdaten[row][1], null);
				assertEquals(this.typenzuordnungTestdaten[row][0],
						this.typenzuordnungTestdaten[row][2],
						ls.postscript2String());

				ls.generateTokenlist();
				System.out.println(this.typenzuordnungTestdaten[row][0]
						+ " POST OK");

				assertEquals(this.typenzuordnungTestdaten[row][0],
						this.typenzuordnungTestdaten[row][3],
						ls.tokenlist2String());
				System.out.println(this.typenzuordnungTestdaten[row][0]
						+ " TOKEN OK");

				ls.generateTable();
				assertEquals(this.typenzuordnungTestdaten[row][0],
						this.typenzuordnungTestdaten[row][4], ls.block2String());
				System.out.println(this.typenzuordnungTestdaten[row][0]
						+ " BLOCK OK");
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
				e.printStackTrace();
				fail(e.getMessage());
			} catch (ScriptException e) {
				e.printStackTrace();
				fail(e.getMessage());
			}

		}
	}

	/**
	 * Testen von Block, Index und Listen
	 * 
	 * @modified -
	 */
	@Test
	public final void testDBlockIndexListe() {

		for (int row = 0; row < this.blockIndexListeTestdaten.length; row++) {
			System.out.println("Post2String: "
					+ this.blockIndexListeTestdaten[row][0] + LFCR
					+ this.blockIndexListeTestdaten[row][1]);
			try {
				LoadScript ls = new LoadScript(TESTPATH
						+ this.blockIndexListeTestdaten[row][1], null);
				assertEquals(this.blockIndexListeTestdaten[row][0],
						this.blockIndexListeTestdaten[row][2],
						ls.postscript2String());

				ls.generateTokenlist();
				System.out.println(this.blockIndexListeTestdaten[row][0]
						+ " POST OK");

				assertEquals(this.blockIndexListeTestdaten[row][0],
						this.blockIndexListeTestdaten[row][3],
						ls.tokenlist2String());
				System.out.println(this.blockIndexListeTestdaten[row][0]
						+ " TOKEN OK");

				ls.generateTable();
				assertEquals(this.blockIndexListeTestdaten[row][0],
						this.blockIndexListeTestdaten[row][4],
						ls.block2String());
				System.out.println(this.blockIndexListeTestdaten[row][0]
						+ " BLOCK OK");
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
				e.printStackTrace();
				fail(e.getMessage());
			} catch (ScriptException e) {
				e.printStackTrace();
				fail(e.getMessage());
			}

		}
	}

	/**
	 * Testen der Allgemeinen Funktionen, Addressen, Vektoren und Matrizen
	 * 
	 * @modified -
	 */
	@Test
	public final void testEFunktionengruppe() {

		for (int row = 0; row < this.funktionengruppeTestdaten.length; row++) {
			System.out.println("Post2String: "
					+ this.funktionengruppeTestdaten[row][0] + LFCR
					+ this.funktionengruppeTestdaten[row][1]);
			try {
				LoadScript ls = new LoadScript(TESTPATH
						+ this.funktionengruppeTestdaten[row][1], null);
				assertEquals(this.funktionengruppeTestdaten[row][0],
						this.funktionengruppeTestdaten[row][2],
						ls.postscript2String());

				ls.generateTokenlist();
				System.out.println(this.funktionengruppeTestdaten[row][0]
						+ " POST OK");

				assertEquals(this.funktionengruppeTestdaten[row][0],
						this.funktionengruppeTestdaten[row][3],
						ls.tokenlist2String());
				System.out.println(this.funktionengruppeTestdaten[row][0]
						+ " TOKEN OK");

				ls.generateTable();
				assertEquals(this.funktionengruppeTestdaten[row][0],
						this.funktionengruppeTestdaten[row][4],
						ls.block2String());
				System.out.println(this.funktionengruppeTestdaten[row][0]
						+ " BLOCK OK");
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
				e.printStackTrace();
				fail(e.getMessage());
			} catch (ScriptException e) {
				e.printStackTrace();
				fail(e.getMessage());
			}

		}
	}

	/**
	 * Testen der Basisberechnungen
	 * 
	 * @modified -
	 */
	@Test
	public final void testFBasisrechnungen() {

		for (int row = 0; row < this.basisrechnungenTestdaten.length; row++) {
			System.out.println("Post2String: "
					+ this.basisrechnungenTestdaten[row][0] + LFCR
					+ this.basisrechnungenTestdaten[row][1]);
			try {
				LoadScript ls = new LoadScript(TESTPATH
						+ this.basisrechnungenTestdaten[row][1], null);
				assertEquals(this.basisrechnungenTestdaten[row][0],
						this.basisrechnungenTestdaten[row][2],
						ls.postscript2String());

				ls.generateTokenlist();
				System.out.println(this.basisrechnungenTestdaten[row][0]
						+ " POST OK");

				assertEquals(this.basisrechnungenTestdaten[row][0],
						this.basisrechnungenTestdaten[row][3],
						ls.tokenlist2String());
				System.out.println(this.basisrechnungenTestdaten[row][0]
						+ " TOKEN OK");

				ls.generateTable();
				assertEquals(this.basisrechnungenTestdaten[row][0],
						this.basisrechnungenTestdaten[row][4],
						ls.block2String());
				System.out.println(this.basisrechnungenTestdaten[row][0]
						+ " BLOCK OK");
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
				e.printStackTrace();
				fail(e.getMessage());
			} catch (ScriptException e) {
				e.printStackTrace();
				fail(e.getMessage());
			}

		}
	}

	/**
	 * Testen der Elementenanlage im 0., 1. nd 2. Pass
	 * 
	 * @modified -
	 */
	@Test
	public final void testGMarker() {

		for (int row = 0; row < this.datenstrukturenTestdaten.length; row++) {
			System.out.println("Post2String: "
					+ this.datenstrukturenTestdaten[row][0] + LFCR
					+ this.datenstrukturenTestdaten[row][1]);
			try {
				LoadScript ls = new LoadScript(TESTPATH
						+ this.datenstrukturenTestdaten[row][1], null);
				assertEquals(this.datenstrukturenTestdaten[row][0],
						this.datenstrukturenTestdaten[row][2],
						ls.postscript2String());

				ls.generateTokenlist();
				System.out.println(this.datenstrukturenTestdaten[row][0]
						+ " POST OK");

				assertEquals(this.datenstrukturenTestdaten[row][0],
						this.datenstrukturenTestdaten[row][3],
						ls.tokenlist2String());
				System.out.println(this.datenstrukturenTestdaten[row][0]
						+ " TOKEN OK");

				ls.generateTable();
				assertEquals(this.datenstrukturenTestdaten[row][0],
						this.datenstrukturenTestdaten[row][4],
						ls.block2String());
				System.out.println(this.datenstrukturenTestdaten[row][0]
						+ " BLOCK OK");
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
	@SuppressWarnings("unused")
	private final static Logger logger = Logger.getLogger(LoadScriptTest.class
			.getPackage().getName());
}
