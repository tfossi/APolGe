/**
 * ReportTest.java
 * Branch report
 * APolGe
 * tfossi-team
 * licence GPLv3  
 */
package tfossi.apolge.report;

import static org.junit.Assert.assertEquals;
import static tfossi.apolge.common.constants.ConstValue.LFCR;
import static tfossi.apolge.common.constants.ConstValueExtension.*;

import java.util.LinkedList;
import java.util.Queue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import org.apache.log4j.Logger;
/**
 * Das Reporting testen
 * 
 * $ Report: Formatübergabe der Daten mit Name, Datentyp
 * § Report: Datenübergabe entsprechend des Formats
 * § Report: Kalkulation mit Name 1, Name 2, Funktion/Operation
 * $ Report: Auswerten in formatierten Text, FEATURE RawData, FEATURE spez. RawData
 * @author tfossi
 * @version 16.08.2014
 * @modified Coderevision, tfossi, 31.07.2014 
 * @since Java 1.6
 * 
 */
public class ReportTest {

	{
		if (LOGGER)
			System.out.println(this.getClass().getSimpleName()+" V" + serialVersionUID);
	}
	
	/**
	 * DOC Welcher Test?
	 */
	@SuppressWarnings("static-method")
	@Test
	public final void testReportFormat() {
		
		System.out.println("Reporting Test: Format");
		String[][]c = new String[][]{
				new String[]{"Spalte1 | String | class java.lang.String | 7",
				"Spalte2 | PPiT | class tfossi.apolge.time.pit.PPiT | 7"}
		};
		
		IReport ir = new Report();
		
		try {
			ir.formatNew(new String[]{
					"Spalte1","String"});
			ir.formatAdd(new String[]{
					"Spalte2","PPiT"});
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i =0; i < ir.formatGet().length; i++){
			assertEquals(c[0][i], ir.formatGet()[i].toString());
		}
	}

	/**
	 * DOC Welcher Test
	 */
	@SuppressWarnings("static-method")
	@Test
	public final void testReportSimpleCol() {


		System.out.println("Reporting Test: Data & Simple Col-Operation");
		
		String[][]c = new String[][]{
				new String[]{"Spalte1 | Integer | class java.lang.Integer | 7",
				"Spalte2 | Double | class java.lang.Double | 7",
				"Spalte4 | Double | class java.lang.Double | 7",
				"Spalte3 | String | class java.lang.String | 7"}
		};
		String a = new String(
		"Auswertung:"+LFCR+
			"Spalte1 | Spalte2 | Spalte4 | Spalte3          | Spalte4+Spalte2 | "+LFCR+
			"1234    | 2.71    | 2.71    | Ein langer Text  | 5.42            | "+LFCR+
			"5678    | 1.23    | 1.23    | Ein zweiter Text | 2.46            | "+LFCR);

		IReport ir = new Report();
		
		try {
			ir.formatNew(new String[]{
					"Spalte1","Integer"});
			ir.formatAdd(new String[]{
					"Spalte2","Double"});	
			ir.formatAdd(new String[]{
					"Spalte4","Double"});	
			ir.formatAdd(new String[]{
					"Spalte3","String"});	
					
			for(int i =0; i < ir.formatGet().length; i++){
				assertEquals(c[0][i], ir.formatGet()[i].toString());
			}
			
			Queue<Object> report = new LinkedList<Object>();
			
			report.add(new Integer(1234));
			report.add(new Double(2.71));
			report.add(new Double(2.71));
			report.add("Ein langer Text");
			ir.reporting(report);
			
			ir.calc("Spalte4", "Spalte2", "+");
			
			report.add(new Integer(5678));
			report.add(new Double(1.23));
			report.add(new Double(1.23));
			report.add("Ein zweiter Text");
			ir.reporting(report);
			assertEquals(a,ir.auswertung());
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * DOC Welcher Test
	 */
	@SuppressWarnings("static-method")
	@Test
	public final void testReportComplexCol() {
		System.out.println("Reporting Test: Data & Complex Col-Operation");
	}

	/**
	 * DOC Welcher Test
	 */
	@SuppressWarnings("static-method")
	@Test
	public final void testReportSimpleRow() {
		System.out.println("Reporting Test: Data & Simple Row-Operation");
	}

	/**
	 * DOC Welcher Test
	 */
	@SuppressWarnings("static-method")
	@Test
	public final void testReportComplexRow() {
		System.out.println("Reporting Test: Data & Complex Row-Operation");
	}

	/**
	 * DOC Welcher Test
	 */
	@SuppressWarnings("static-method")
	@Test
	public final void testeReportIntegrata() {
		System.out.println("Reporting Test: Data & Complex Integrata Operation");
	}
	

	

	/**
	 * @throws java.lang.Exception
	 * 			DOC
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.err
				.println("-------------------------------------------------------------------------------");
		System.out.println("BEFORE");

	}

	/**
	 * @throws java.lang.Exception
	 * 			DOC
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("AFTER");
	}

	/**
	 * @throws java.lang.Exception
	 * 			DOC
	 */
	@SuppressWarnings("static-method")
	@Before
	public void setUp() throws Exception {
		System.err
				.println("-------------------------------------------------------------------------------");
		System.out.println("SETUP");
	}

	/**
	 * @throws java.lang.Exception
	 * 			DOC
	 */
	@SuppressWarnings("static-method")
	@After
	public void tearDown() throws Exception {
		System.out.println("TEARDOWN");
	}
	// ---- Selbstverwaltung --------------------------------------------------

	/** serialVersionUID */
	private final static long serialVersionUID = VERSION;
	/** logger */
	@SuppressWarnings("unused")
	private final static Logger logger = Logger.getLogger(ReportTest.class
			.getPackage().getName());
}
