/**
 * TimeControllerTest.java
 * Branch time
 * APolGe
 * tfossi-team
 * licence GPLv3  
 */
package tfossi.apolge.time;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static tfossi.apolge.common.constants.ConstValue.LOGGER;
import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;

import java.util.LinkedList;
import java.util.Queue;

import org.apache.log4j.Logger;
import org.junit.Test;

import tfossi.apolge._TestBase;
import tfossi.apolge.common.error.DatumsException;
import tfossi.apolge.events.DummyExecute;
import tfossi.apolge.report.IReport;
import tfossi.apolge.report.Report;
import tfossi.apolge.time.pit.CPiT;
import tfossi.apolge.time.pit.ConstCPPit;
import tfossi.apolge.time.pit.PPiT;

/**
 * @author tfossi
 * @version 1.07.2014
 * @modified Coderevision, tfossi, 31.07.2014
 * @since Java 1.6
 */
public class TimeControllerTest { //extends _TestBase {
	/** Simulationsstartzeit */
	protected String start = null;
	/** Simulationsendezeit */
	protected String end = null;
	
	{	if (LOGGER)
		System.out.println(this.getClass().getSimpleName()+" V" + serialVersionUID);
	
			/** Startdatum */
		this.start = "Montag 31.12.2011 0:0:0";
		/** Endedatum */
		this.end = "Montag 1.1.2012 12:0:0";
	}

	// IExecuteTermin eventExecutor = null;
	// ITimes4Thread iT4Th = null;
	//
	/** Timecontroller-Instanz */
	ITimesController tc = new TimesController();


	//
	// int reportnumber = 0;
	//
	// // IReport reporter = new Report(null, 1 , "Basisreport");
	// DummyExecute wie = new DummyExecute();


	/**
	 * DOC Welche Tests?
	 */
	 @Test
	public void testTCBasetest() {
		IReport report = new Report();

		System.out.println("Timer Test: Startdatum, aktuelles und Endedatum");

		this.tc.setStartdate(this.start, report);
		this.tc.setEnddate(this.end, report);

		assertEquals("#1", "Sonnabend 31.12.2011 00:00:00 PRE5", this.tc
				.getStartdate().toString());
		assertEquals("#2", "Sonnabend 31.12.2011 00:00:00 START", this.tc
				.getActualdate().toString());
		assertEquals("#3", "Sonntag 01.01.2012 12:00:00 PRE5", this.tc.getEnddate()
				.toString());

		assertArrayEquals("#4", new String[] { "SCHEDULAR:" }, this.tc
				.showSchedular().toArray(new String[0]));
		assertArrayEquals("#5", new String[] { "CANDIDATES:",
				"Sonnabend 31.12.2011 00:00:00 PRE5 [Startdatum]",
				"Sonntag 01.01.2012 12:00:00 PRE5 [Enddatum]" }, this.tc
				.showNeuerTermin().toArray(new String[0]));
		assertArrayEquals("#6", new String[] { "DEL:" }, this.tc.showDeleteTermin()
				.toArray(new String[0]));
	}

	/**
	 * DOC Welche Tests?
	 */
//	 @Test
	public void testTCTurnAroundBasetest() {

		System.out
				.println("Timer Test: Event, Schedular, Execution and Reports");
		IReport report = new Report();
		Queue<Object> q = new LinkedList<Object>();

		this.	tc = new TimesController();
		this.	tc.setStartdate(this.start, report);
		this.	tc.setEnddate(this.end, report);
		this.	tc.setSollstep(1000L * 60L * 60L);

		String was = "Anlage eines Events";
		PPiT wann = ConstCPPit.string2DataTime("31.12.2011 23:58:59");
		@SuppressWarnings("unused")
		CPiT bis = ConstCPPit.string2DataTime("31.12.2011 23:58:59").toCPit();
		IExecuteTermin wie = new DummyExecute();
		try {
			report.formatNew(new String[] { "Object", "Object" });
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//
		try {
			this.tc.createTermin(report, 0, was, wann, wie);
		} catch (DatumsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		while (!this.tc.showSchedular().toString().equals("[SCHEDULAR:]")
				|| !this.tc.showNeuerTermin().toString().equals("[CANDIDATES:]")) {
			// Untersuche mit Hilfe des Schedulars nach Zeitereignissen, die
			// ausgelöst werden sollen
			((TimesController) this.tc).schedular();
			// Füge den Zeitschritt eines Threadlaufes hinzu
			((TimesController)this. tc).addSekunden();

			q.add(this.tc.getActualdate().toString());
			report.reporting(q);
			q.clear();
		}
		System.err.println(report.auswertung());
		// //
		// //
		// // assertEquals("#1","? 31.12.2011 23:58:59 ?", pit.toString());
		// //
		// // pit = new PPiT(2011, 12, 31, 23, 58, 59, Shift.PRE1,
		// Tage.DONNERSTAG);
		// // assertEquals("#2","Sonnabend 31.12.2011 23:58:59 PRE1",
		// pit.toString());
		// //
		// // pit = new PPiT(2011, 12, 25, 23, 58, 59, Shift.PRE1,
		// Tage.DONNERSTAG);
		// // assertEquals("#3","Sonntag 25.12.2011 23:58:59 PRE1",
		// pit.toString());
		// //
		// // pit = new PPiT(2011, 12, 31, 23, 58, 59, Shift.PRE1,
		// Tage.DONNERSTAG);
		// // pit.setDayOfWeek(2);
		// // assertEquals("#4","Montag 31.12.2011 23:58:59 PRE1",
		// pit.toString());

	} 

	// ---- Selbstverwaltung --------------------------------------------------
	/** serialVersionUID */
	private final static long serialVersionUID = VERSION;
	/** Loggerinstanz */
	@SuppressWarnings("unused")
	private final static Logger logger = Logger.getLogger(TimeControllerTest.class
			.getPackage().getName());
}
