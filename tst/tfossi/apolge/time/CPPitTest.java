/**
 * CPPitTest.java
 * Branch cptime
 * APolGe
 * tfossi-team
 * licence GPLv3  
 */
package tfossi.apolge.time;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static tfossi.apolge.time.pit.ConstCPPit.*;
import static tfossi.apolge.common.constants.ConstValue.LOGGER;
import static tfossi.apolge.common.constants.ConstValue.TIMEEM;
import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import tfossi.apolge.common.constants.ConstValue;
import tfossi.apolge.common.constants.ConstValueExtension.Shift;
import tfossi.apolge.common.types.Tage;
import tfossi.apolge.time.pit.CPiT;
import tfossi.apolge.time.pit.PPiT;

/**
 * TODO Comment
 * 
 * @author tfossi
 * @version 1.07.2014
 * @modified Coderevision, tfossi, 31.07.2014
 * @since Java 1.6
 */
public class CPPitTest {
	{
		if (LOGGER)
			System.out.println(this.getClass().getSimpleName()+" V" + serialVersionUID);
	}
	/**
	 * Initialisierungstests
	 */
	@SuppressWarnings("static-method")
	@Test
	public void tstPPITBasetest() {
		System.out
				.println("PPIT-Test Basistest: Anlage, Shift, Tage, Wochentag-Korrektur, setDOW");
		
		// Datum korrekt, kein Shift, kein Wochentag
		PPiT pit = new PPiT(2011, 12, 31, 23, 58, 59, Shift.UNDEF, Tage.UNDEF);
		assertEquals("#1", "? 31.12.2011 23:58:59 ?", pit.toString());
		System.out.println(pit.toString());

		// Datum korrekt, mit Shift, mit korrektem Wochentag
		pit = new PPiT(2011, 12, 31, 23, 58, 59, Shift.PRE1, Tage.DONNERSTAG);
		assertEquals("#2", "Sonnabend 31.12.2011 23:58:59 PRE1", pit.toString());
		System.out.println(pit.toString());

		// Datum korrekt, mit Shift, mit falschem Wochentag
		pit = new PPiT(2011, 12, 31, 23, 58, 59, Shift.PRE1, Tage.FREITAG);
		assertEquals("#3", "Sonnabend 31.12.2011 23:58:59 PRE1", pit.toString());
		System.out.println(pit.toString());

		// Datum korrekt, mit Shift, mit korrektem Wochentag
		pit = new PPiT(2011, 12, 31, 23, 58, 59, Shift.PRE1, Tage.DONNERSTAG);
		// Anderer Wochentag gesetzt, keine Prüfung
		pit.setDayOfWeek(2);
		assertEquals("#4", "Montag 31.12.2011 23:58:59 PRE1", pit.toString());
		System.out.println(pit.toString());
	}

	/**
	 * DOC Welche Tests?
	 */
	@SuppressWarnings("static-method")
//	@Test
	public void tstPPITSetTstTest() {
		System.out.println("PPIT-Test set-Methoden");
		PPiT pit = new PPiT(2011, 12, 31, 23, 58, 59, Shift.POST4,
				Tage.DIENSTAG);
		assertEquals("#1", "Sonnabend 31.12.2011 23:58:59 POST4",
				pit.toString());

		assertFalse("#1a", pit.tstDay());
		pit.setDay(-1);
		assertTrue("#1b", pit.tstDay());
		assertEquals("#2", "Sonnabend **.12.2011 23:58:59 POST4",
				pit.toString());

		assertFalse("#2a", pit.tstMonth());
		pit.setMonth(-1);
		assertTrue("#2b", pit.tstMonth());
		assertEquals("#3", "Sonnabend **.**.2011 23:58:59 POST4",
				pit.toString());

		assertFalse("#3a", pit.tstYear());
		pit.setYear(-1);
		assertTrue("#3b", pit.tstYear());
		assertEquals("#4", "Sonnabend **.**.**** 23:58:59 POST4",
				pit.toString());

		assertFalse("#4a", pit.tstHour());
		pit.setHour(-1);
		assertTrue("#4b", pit.tstHour());
		assertEquals("#5", "Sonnabend **.**.**** **:58:59 POST4",
				pit.toString());

		assertFalse("#5a", pit.tstMinute());
		pit.setMinute(-1);
		assertTrue("#5b", pit.tstMinute());
		assertEquals("#6", "Sonnabend **.**.**** **:**:59 POST4",
				pit.toString());

		assertFalse("#6a", pit.tstSecond());
		pit.setSecond(-1);
		assertTrue("#6b", pit.tstSecond());
		assertEquals("#7", "Sonnabend **.**.**** **:**:** POST4",
				pit.toString());

		assertFalse("#7a", pit.tstDoW());
		pit.setDayOfWeek(Tage.NODAY);
		assertTrue("#7b", pit.tstDoW());
		assertEquals("#8", "* **.**.**** **:**:** POST4", pit.toString());

		assertFalse("#8a", pit.tstShift());
		pit.setShift(Shift.UNDEF);
		assertTrue("#8b", pit.tstShift());
		assertEquals("#9", "* **.**.**** **:**:** ?", pit.toString());
	}

	// @Test
	// public void tstPPITToStringTest() {
	// System.out.println("PPIT-Test toString-Methoden");
	// PPiT pit = new PPiT(2011, 12, 31, 23, 58, 59, Shift.POST4,
	// Tage.DIENSTAG);
	//
	// assertEquals("#1","31.12.2011",pit.toString(DATE));
	// assertEquals("#2","23:58:59",pit.toString(TIME));
	// assertEquals("#3","Sonnabend",pit.toString(DOW));
	// assertEquals("#4","POST4",pit.toString(SHIFT));
	//
	// assertEquals("#5","31.12.2011 23:58:59",pit.toString(DATE+TIME));
	// assertEquals("#6","Sonnabend 31.12.2011",pit.toString(DATE+DOW));
	// assertEquals("#7","31.12.2011 POST4",pit.toString(DATE+SHIFT));
	//
	// assertEquals("#8","Sonnabend 23:58:59",pit.toString(TIME+DOW));
	// assertEquals("#9","23:58:59 POST4",pit.toString(TIME+SHIFT));
	//
	// assertEquals("#10","Sonnabend POST4",pit.toString(DOW+SHIFT));
	//
	// assertEquals("#11","Sonnabend 31.12.2011 23:58:59",pit.toString(DATE+TIME+DOW));
	// assertEquals("#12","31.12.2011 23:58:59 POST4",pit.toString(DATE+TIME+SHIFT));
	// assertEquals("#13","Sonnabend 23:58:59 POST4",pit.toString(TIME+DOW+SHIFT));
	//
	// assertEquals("#14","Sonnabend 31.12.2011 23:58:59 POST4",pit.toString(DATE+TIME+DOW+SHIFT));
	//
	// }
	// private final String[][] PPITdate2time = new String[][] {
	//
	// {"0","Sonnabend 31.12.2011 23:58:59 PRE1",
	// "Sonnabend 31.12.2011 23:58:59 PRE1"},
	//
	// // // Test 1.0 -- Test 1.8
	// { "1 DATE", "20.06.1960", "? 20.06.1960 **:**:** ?",
	// "* 20.06.1960 **:**:** NORMAL" },
	// { "2 DATE", "20.06.", "? 20.06.**** **:**:** ?",
	// "* 20.06.**** **:**:** NORMAL" },
	// { "3 DATE", "20.06.*", "? 20.06.**** **:**:** ?",
	// "* 20.06.**** **:**:** NORMAL" },
	// // // NO: Punkt am Monat fehlt: "20.06",
	// // // "* 20.06.**** **:**:** NORMAL",
	// { "4 DATE", "20..1960", "? 20.**.1960 **:**:** ?",
	// "* 20.**.1960 **:**:** NORMAL" },
	// { "5 DATE", "20.*.1960", "? 20.**.1960 **:**:** ?",
	// "* 20.**.1960 **:**:** NORMAL" },
	// { "6 DATE", "20..", "? 20.**.**** **:**:** ?",
	// "* 20.**.**** **:**:** NORMAL" },
	// { "7 DATE", "20.*.", "? 20.**.**** **:**:** ?",
	// "* 20.**.**** **:**:** NORMAL" },
	// { "8 DATE", "20..*", "? 20.**.**** **:**:** ?",
	// "* 20.**.**** **:**:** NORMAL" },
	// { "9 DATE", "20.*.*", "? 20.**.**** **:**:** ?",
	// "* 20.**.**** **:**:** NORMAL" },
	// { "10 DATE", "20.", "? 20.**.**** **:**:** ?",
	// "* 20.**.**** **:**:** NORMAL" },
	// { "11 DATE", "20.*", "? 20.**.**** **:**:** ?",
	// "* 20.**.**** **:**:** NORMAL" },
	//
	// { "12 DATE", ".06.1960", "? **.06.1960 **:**:** ?",
	// "* **.06.1960 **:**:** NORMAL" },
	// { "13 DATE", "*.06.1960", "? **.06.1960 **:**:** ?",
	// "* **.06.1960 **:**:** NORMAL" },
	//
	// { "14 DATE", ".06.", "? **.06.**** **:**:** ?",
	// "* **.06.**** **:**:** NORMAL" },
	// { "15 DATE", "*.06.", "? **.06.**** **:**:** ?",
	// "* **.06.**** **:**:** NORMAL" },
	//
	// { "16 DATE", ".06.*", "? **.06.**** **:**:** ?",
	// "* **.06.**** **:**:** NORMAL" },
	// { "17 DATE", "*.06.*", "? **.06.**** **:**:** ?",
	// "* **.06.**** **:**:** NORMAL" },
	//
	// { "18 DATE", "..1960", "? **.**.1960 **:**:** ?",
	// "* **.**.1960 **:**:** NORMAL" },
	// { "19 DATE", ".*.1960", "? **.**.1960 **:**:** ?",
	// "* **.**.1960 **:**:** NORMAL" },
	//
	// { "20 DATE", "*..1960", "? **.**.1960 **:**:** ?",
	// "* **.**.1960 **:**:** NORMAL" },
	// { "21 DATE", "*.*.1960", "? **.**.1960 **:**:** ?",
	// "* **.**.1960 **:**:** NORMAL" },
	//
	// { "22 DATE", ".", "? **.**.**** **:**:** ?",
	// "* **.**.**** **:**:** NORMAL" },
	// { "23 DATE", "*.", "? **.**.**** **:**:** ?",
	// "* **.**.**** **:**:** NORMAL" },
	//
	// { "24 DATE", ".*", "? **.**.**** **:**:** ?",
	// "* **.**.**** **:**:** NORMAL" },
	// { "25 DATE", "*.*", "? **.**.**** **:**:** ?",
	// "* **.**.**** **:**:** NORMAL" },
	//
	// { "1 TIME","13:45:24 NORMAL", "? **.**.**** 13:45:24 NORMAL" },
	// { "2 TIME", "13:45:24 PRE1", "? **.**.**** 13:45:24 PRE1",
	// "* **.**.**** 13:45:24 PRE1" },
	// { "3 TIME", "13:45: PRE2", "? **.**.**** 13:45:** PRE2",
	// "* **.**.**** 13:45:** PRE2" },
	// { "4 TIME", "13:45:* PRE2", "? **.**.**** 13:45:** PRE2",
	// "* **.**.**** 13:45:** PRE2" },
	// { "5 TIME", "13::26 PRE3", "? **.**.**** 13:**:26 PRE3",
	// "* **.**.**** 13:**:26 PRE3" },
	// { "6 TIME", "13:*:26 PRE3", "? **.**.**** 13:**:26 PRE3",
	// "* **.**.**** 13:**:26 PRE3" },
	// { "7 TIME", "13:: PRE4", "? **.**.**** 13:**:** PRE4",
	// "* **.**.**** 13:**:** PRE4" },
	// { "8 TIME", "13:*:* PRE4", "? **.**.**** 13:**:** PRE4",
	// "* **.**.**** 13:**:** PRE4" },
	// { "9 TIME", "13: PRE5", "? **.**.**** 13:**:** PRE5",
	// "* **.**.**** 13:**:** PRE5" },
	// { "10 TIME", "13:* PRE5", "? **.**.**** 13:**:** PRE5",
	// "* **.**.**** 13:**:** PRE5" },
	// { "11 TIME", ":45:28 POST1", "? **.**.**** **:45:28 POST1",
	// "* **.**.**** **:45:28 POST1" },
	// { "12 TIME", "*:45:28 POST1", "? **.**.**** **:45:28 POST1",
	// "* **.**.**** **:45:28 POST1" },
	// { "13 TIME", ":45: POST2", "? **.**.**** **:45:** POST2",
	// "* **.**.**** **:45:** POST2" },
	// { "14 TIME", "*:45:* POST2", "? **.**.**** **:45:** POST2",
	// "* **.**.**** **:45:** POST2" },
	// { "15 TIME", "::30 POST3", "? **.**.**** **:**:30 POST3",
	// "* **.**.**** **:**:30 POST3" },
	// { "16 TIME", "*:*:30 POST3", "? **.**.**** **:**:30 POST3",
	// "* **.**.**** **:**:30 POST3" },
	// { "18 TIME", ":: START", "? **.**.**** **:**:** START",
	// "* **.**.**** **:**:** START" },
	// { "19 TIME", "*:*:* START", "? **.**.**** **:**:** START",
	// "* **.**.**** **:**:** START" },
	//
	// { "36", "Donnerstag 20.06.1960 13:45:23 NORMAL",
	// "Donnerstag 20.06.1960 13:45:23 NORMAL",
	// "Donnerstag 20.06.1960 13:45:23 NORMAL" },
	// { "37", "Freitag 21.06.1960", "Freitag 21.06.1960 **:**:** ?",
	// "Freitag 21.06.1960 **:**:** NORMAL" },
	// { "38", "Sonnabend 21.06.1960 NORMAL",
	// "Sonnabend 21.06.1960 **:**:** NORMAL",
	// "Sonnabend 21.06.1960 **:**:** NORMAL" },
	// { "39", "Sonnabend 22.06.1960 NORMAL",
	// "Sonnabend 22.06.1960 **:**:** NORMAL",
	// "Sonnabend 22.06.1960 **:**:** NORMAL" },
	// { "40", "Sonnabend 22.06.* NORMAL",
	// "Sonnabend 22.06.**** **:**:** NORMAL",
	// "Sonnabend 22.06.**** **:**:** NORMAL" },
	//
	// };
	// @Test
	// public void tstPPITSetDateTimeUnEr(){
	// System.out.println("PPIT-Test mask-Methoden");
	// // FIXME Umstellen der Reihenfolge:
	// assertEquals("Sonnabend 31.12.2011 23:58:59 PRE1",
	// PPiT.setDatetime("31.12.2011 23:58:59 PRE1 Sonnabend").toString());
	//
	// for (int testnr = 0; testnr < this.PPITdate2time.length; testnr++) {
	// assertEquals(
	// this.PPITdate2time[testnr][0],
	// this.PPITdate2time[testnr][2],
	// string2DataTime(this.PPITdate2time[testnr][1]).toString());
	//
	// }
	//
	// }
	//
	//
	// @Test
	// public void tstCPiTBasetest() {
	//
	// String str = "31.12.2011 23:58:59";
	// assertEquals(str,string2DataTime(str).toString(DATE+TIME));
	// CPiT cpit = new CPiT(string2DataTime(str) );
	// assertEquals(str, cpit.getDatetime(TIME+DATE));
	//
	// assertEquals(Tage.SONNABEND, cpit.getDayOfWeek());
	//
	// cpit.addTime(1000L);
	// assertEquals("Sonnabend 31.12.2011 23:59:00",
	// cpit.getDatetime(TIME+DATE+DOW));
	// cpit.addTime(60L*1000L);
	// assertEquals("Sonntag 01.01.2012 00:00:00",
	// cpit.getDatetime(TIME+DATE+DOW));
	//
	// String str2 = "30.12.2011 23:59:59";
	//
	// cpit = new CPiT(string2DataTime(str2) );
	//
	// assertEquals("Freitag 30.12.2011 23:59:59",
	// cpit.getDatetime(TIME+DATE+DOW));
	// cpit.addTime(1000L);
	// assertEquals(Tage.SONNABEND,cpit.getDayOfWeek());
	// assertEquals("Sonnabend 31.12.2011 00:00:00",
	// cpit.getDatetime(TIME+DATE+DOW));
	//
	// }
	// @Test
	// public void tstCPiTSetTstTest() {
	// PPiT ppit = PPiT.setDatetime("Sonnabend 31.12.2011 23:58:59 POST4");
	// CPiT cpit = ppit.toCPit();
	//
	// assertEquals(ppit.toString(),PPiT.fromTC2PiT(cpit.getTime()).toString());
	// assertNotSame(cpit,PPiT.fromTC2PiT(cpit.getTime()));
	// }
	// @Test
	// public void tstPPiTBasetest() {
	// PPiT pit = new PPiT(2011, 12, 10, 23, 58, 59, Shift.NORMAL, Tage.UNDEF);
	//
	// PPiT epit = new PPiT(2011, 12, 10, 23, 58, 59, Shift.NORMAL, Tage.UNDEF);
	//
	// PPiT bpit = new PPiT(2011, 12, 9, 23, 58, 59, Shift.NORMAL, Tage.UNDEF);
	// PPiT b2pit = new PPiT(2010, 12, 11, 23, 58, 59, Shift.NORMAL,
	// Tage.UNDEF);
	// PPiT b3pit = new PPiT(2010, 12, 9, 23, 58, 59, Shift.NORMAL, Tage.UNDEF);
	//
	// PPiT apit = new PPiT(2012, 1, 1, 23, 58, 59, Shift.NORMAL, Tage.UNDEF);
	//
	//
	// assertFalse(after(epit.toCPit(), pit.toCPit()));
	// assertFalse(after(bpit.toCPit(), pit.toCPit()));
	// assertFalse(after(b2pit.toCPit(), pit.toCPit()));
	// assertFalse(after(b3pit.toCPit(), pit.toCPit()));
	// assertTrue(after(apit.toCPit(), pit.toCPit()));
	//
	// assertTrue(equal(epit.toCPit(), pit.toCPit()));
	// assertFalse(equal(bpit.toCPit(), pit.toCPit()));
	// assertFalse(equal(b2pit.toCPit(), pit.toCPit()));
	// assertFalse(equal(b3pit.toCPit(), pit.toCPit()));
	// assertFalse(equal(apit.toCPit(), pit.toCPit()));
	//
	// assertFalse(before(epit.toCPit(), pit.toCPit()));
	// assertTrue(before(bpit.toCPit(), pit.toCPit()));
	// assertTrue(before(b2pit.toCPit(), pit.toCPit()));
	// assertTrue(before(b3pit.toCPit(), pit.toCPit()));
	// assertFalse(before(apit.toCPit(), pit.toCPit()));
	//
	// assertEquals(0, alter(pit.toCPit(), epit.toCPit()));
	// assertEquals(0, alter(pit.toCPit(), bpit.toCPit()));
	// assertEquals(0, alter(pit.toCPit(), b2pit.toCPit()));
	// assertEquals(1, alter(pit.toCPit(), b3pit.toCPit()));
	// assertEquals(0, alter(pit.toCPit(), apit.toCPit()));
	//
	// }
	//

	/**
	 * DOC Welche Tests?
	 */
	@SuppressWarnings("static-method")
//	@Test
	public void tstNextDate() {

		final CPiT cpit = new CPiT(PPiT.setDatetime("* 23:58:59 10.12.2011"));

		PPiT ppit = PPiT.setDatetime("* 23:58:59 10.*.*");

		CPiT next = calcNextRecurringPiT(ppit, cpit);

		if (TIMEEM)
			System.out
					.println((System.currentTimeMillis() - ConstValue.applicationstarttime)
							+ " ............ calcNextRecurringPiTX");

		assertEquals("10.01.2012", next.getDatetime(DATE));

		ppit = PPiT.setDatetime("* 23:58:59 *.*.*");
		next = calcNextRecurringPiT(ppit, cpit);
		if (TIMEEM)
			System.out
					.println((System.currentTimeMillis() - ConstValue.applicationstarttime)
							+ " ............ calcNextRecurringPiTX");

		assertEquals("11.12.2011 23:58:59", next.getDatetime(DATE + TIME));

		ppit = PPiT.setDatetime("* 23:*:59 10.12.2011");
		next = calcNextRecurringPiT(ppit, cpit);
		if (TIMEEM)
			System.out
					.println((System.currentTimeMillis() - ConstValue.applicationstarttime)
							+ " ............ calcNextRecurringPiTX");
		assertEquals("10.12.2011 23:59:59", next.getDatetime(DATE + TIME));

		ppit = PPiT.setDatetime("Sonntag 23:58:59 .2011");
		next = calcNextRecurringPiT(ppit, cpit);
		if (TIMEEM)
			System.out
					.println((System.currentTimeMillis() - ConstValue.applicationstarttime)
							+ " ............ calcNextRecurringPiTX");

		System.err
				.println("------------------------------------------------------------------");
		// * 23:58:59 10.12.2011
		ppit = PPiT.setDatetime("Sonnabend 23:58:59 .2011");
		next = calcNextRecurringPiT(ppit, cpit);
		if (TIMEEM)
			System.out
					.println((System.currentTimeMillis() - ConstValue.applicationstarttime)
							+ " ............ calcNextRecurringPiTX");
		assertEquals("Sonnabend 17.12.2011 23:58:59",
				next.getDatetime(DATE + TIME + DOW));

		ppit = PPiT.setDatetime("23:58:59 10.12.2011");
		next = calcNextRecurringPiT(ppit, cpit);
		if (TIMEEM)
			System.out
					.println((System.currentTimeMillis() - ConstValue.applicationstarttime)
							+ " ............ calcNextRecurringPiTX");
		assertEquals("10.12.2011 23:58:59", next.getDatetime(DATE + TIME));

	}

	/**
	 * DOC Welche Tests?
	 */
	@SuppressWarnings("static-method")
//	@Test
	public void startdate() {
		String startdatetime = "1.1.2011 0:0:0 PRE5";
		PPiT ppit = string2DataTime(startdatetime);
		assertEquals("? 01.01.2011 00:00:00 PRE5", ppit.toString());
		PPiT ppit2 = unUNer(string2DataTime(startdatetime));
		assertEquals("* 01.01.2011 00:00:00 PRE5", ppit2.toString());

		CPiT cpit = unUNer(string2DataTime(startdatetime)).toCPit();

		assertEquals("Sonnabend 01.01.2011 00:00:00 PRE5", cpit.toString());
	}

	/**
	 * DOC Welche Tests?
	 */
	@SuppressWarnings("static-method")
//	@Test
	public void alterTest() {

		String ref = "6.9.2010 0:0:0 PRE5";

		CPiT cref = unUNer(string2DataTime(ref)).toCPit();

		String t1 = "7.9.2010 0:0:0 PRE5";
		CPiT ct1 = unUNer(string2DataTime(t1)).toCPit();
		assertEquals(0, alter(ct1, cref));

		String t2 = "7.9.2011 0:0:0 PRE5";
		CPiT ct2 = unUNer(string2DataTime(t2)).toCPit();
		assertEquals(1, alter(ct2, cref));

		String t3 = "6.9.2011 0:0:0 PRE5";
		CPiT ct3 = unUNer(string2DataTime(t3)).toCPit();
		assertEquals(1, alter(ct3, cref));

	}

	//
	// private final String[] date2timeCompare = new String[] {
	//
	// // Test 2.0 -- Test 2.8
	// "20.06.1960",
	// "* 20.06.1960 23:58:59 POST4",
	// "20.06.",
	// "* 20.06.2011 23:58:59 POST4",
	// // NO: Punkt am Monat fehlt: "20.06",
	// // "* 20.06.**** **:**:** NORMAL",
	// "20..1960",
	// "* 20.12.1960 23:58:59 POST4",
	// "20..",
	// "* 20.12.2011 23:58:59 POST4",
	// "20.",
	// "* 20.12.2011 23:58:59 POST4",
	// ".06.1960",
	// "* 31.06.1960 23:58:59 POST4",
	// ".06.",
	// "* 31.06.2011 23:58:59 POST4",
	// "..1960",
	// "* 31.12.1960 23:58:59 POST4",
	// ".",
	// "* 31.12.2011 23:58:59 POST4",
	//
	// // Test 2.9 -- Test 2.18
	// "13:45:23", "* 31.12.2011 13:45:23 POST4", "13:45:24 PRE1",
	// "* 31.12.2011 13:45:24 PRE1", "13:45: PRE2",
	// "* 31.12.2011 13:45:59 PRE2", "13::26 PRE3",
	// "* 31.12.2011 13:58:26 PRE3", "13:: PRE4",
	// "* 31.12.2011 13:58:59 PRE4", "13: PRE5",
	// "* 31.12.2011 13:58:59 PRE5",
	// ":45:28 POST1",
	// "* 31.12.2011 23:45:28 POST1",
	// ":45: POST2",
	// "* 31.12.2011 23:45:59 POST2",
	// "::30 POST3",
	// "* 31.12.2011 23:58:30 POST3",
	// ":: START",
	// "* 31.12.2011 23:58:59 START",
	//
	// // Test 2.19 -- Test 2.23
	// "Donnerstag 20.06.1960 13:45:23 NORMAL",
	// "Donnerstag 20.06.1960 13:45:23 NORMAL", "Freitag 21.06.1960",
	// "Freitag 21.06.1960 23:58:59 POST4", "Sonnabend 21.06.1960",
	// "Sonnabend 21.06.1960 23:58:59 POST4", "Sonnabend 22.06.1960",
	// "Sonnabend 22.06.1960 23:58:59 POST4", "Sonnabend 22.06.*",
	// "Sonnabend 22.06.2011 23:58:59 POST4", };
	//
	// @Test
	// public final void testString2DateTimeFill() {
	// for (int testnr = 0; testnr < this.date2timeCompare.length / 2; testnr++)
	// {
	// System.out.println(LFCR + "Test 2#" + testnr);
	// assertEquals(
	// "Testfall #" + testnr,
	// this.date2timeCompare[testnr * 2 + 1],
	// fill(string2DataTime(this.date2timeCompare[testnr * 2]),
	// this.pit).toString(DATE | TIME | DOW | SHIFT));
	// }
	// }
	//
	// private final String[] date2timeDow = new String[] {
	// "Donnerstag 20.06.1960 13:45:23 NORMAL",
	// "Montag 20.06.1960 13:45:23 NORMAL", "Freitag 21.06.1960",
	// "Dienstag 21.06.1960 **:**:** UNDEF", "Sonnabend 21.06.1960",
	// "Dienstag 21.06.1960 **:**:** UNDEF", "Sonntag 22.06.1960",
	// "Mittwoch 22.06.1960 **:**:** UNDEF", "Sonnabend 22.06.*",
	// "Sonnabend 22.06.**** **:**:** UNDEF", };
	//
	// @Test
	// public final void testString2DateTimeShiftDow() {
	// for (int testnr = 0; testnr < this.date2timeDow.length / 2; testnr++) {
	// System.out.println(LFCR + "Test 3#" + testnr);
	// assertEquals("Testfall #" + testnr,
	// this.date2timeDow[testnr * 2 + 1],
	// getDoomsday(string2DataTime(this.date2timeDow[testnr * 2]))
	// .toString(DATE | TIME | DOW | SHIFT));
	// }
	// }
	//
	// private final String[] date2ShiftDate4Dow = new String[] {
	// "Donnerstag 20.06.1960 13:45:23 NORMAL",
	// "Donnerstag 23.06.1960 13:45:23 NORMAL", "Freitag 21.06.1960",
	// "Freitag 24.06.1960 **:**:** UNDEF", "Sonnabend 21.06.1960",
	// "Sonnabend 25.06.1960 **:**:** UNDEF", "Sonntag 22.06.1960",
	// "Sonntag 26.06.1960 **:**:** UNDEF", "Sonnabend 22.06.*",
	// "Sonnabend 22.06.**** **:**:** UNDEF", };
	//
	// @Test
	// public final void testString2ShiftDate4Dow() {
	// for (int testnr = 0; testnr < this.date2timeDow.length / 2; testnr++) {
	// System.out.println(LFCR + "Test 3#" + testnr);
	// assertEquals(
	// "Testfall #" + testnr,
	// this.date2ShiftDate4Dow[testnr * 2 + 1],
	// shiftDate4Dow(
	// string2DataTime(this.date2ShiftDate4Dow[testnr * 2]))
	// .toString(DATE | TIME | DOW | SHIFT));
	// }
	// }
	//
	// private final String[][] date2timeCompareDow = new String[][] {
	// { "Donnerstag 20.06.1960 13:45:23 NORMAL",
	// "Donnerstag 23.06.1960 13:45:23 NORMAL" },
	// { "Freitag 21.06.1960", "Freitag 24.06.1960 23:58:59 POST4" },
	// { "Sonnabend 21.06.1960", "Sonnabend 25.06.1960 23:58:59 POST4" },
	// { "Sonntag 22.06.1960", "Sonntag 26.06.1960 23:58:59 POST4" },
	// { "Sonnabend 22.06.*", "Sonnabend 25.06.2011 23:58:59 POST4" },
	// { "* PRE2", "* 02.01.2012 23:58:59 PRE2" }, };
	//
	// @Test
	// public final void testString2DateTimeFillDow() {
	// for (int testnr = 0; testnr < this.date2timeCompareDow.length; testnr++)
	// {
	// System.out.println(LFCR + "Test 4#" + testnr);
	// assertEquals(
	// "Testfall #" + testnr,
	// this.date2timeCompareDow[testnr][1],
	// shiftDate4Dow(
	// fill(string2DataTime(this.date2timeCompareDow[testnr][0]),
	// this.pit)).toString(
	// DATE | TIME | DOW | SHIFT));
	// }
	// }
	//
	// PiT actual = null; //new PiT(1960, 02, 28, 23, 59, 59, Shift.POST5,
	// Tage.MONTAG);
	// PiT actualSimple = null; //new PiT(1960, 02, 29, 23, 59, 59, Shift.POST5,
	// // Tage.MONTAG);
	//
	// private final String[][] date2timeRecurring = new String[][] {
	// // 0 - 5
	// { "Montag 29.02.1960 23:59:59 POST3",
	// "? **.**.**** **:**:** UNDEF",
	// "Montag 29.02.1960 23:59:59 POST3" },
	// { "28.02.* 23:59:59 POST3", "* 28.02.1961 23:59:59 POST3",
	// "Montag 28.02.1960 23:59:59 POST3" },
	// {
	// // !!!"29.02.* 23:59:59 POST3","* 28.02.1961 23:59:59 POST3",
	// // "Montag 29.02.1960 23:59:59 POST3",
	// "29.*.1960 23:59:59 POST3", "* 29.03.1960 23:59:59 POST3",
	// "Montag 29.02.1960 23:59:59 POST3" },
	// { "29.*.1960 23:59:59 POST3", "? **.**.**** **:**:** UNDEF",
	// "Montag 29.12.1960 23:59:59 POST3" },
	// { "*.02.1960 23:59:59 POST3", "* 29.02.1960 23:59:59 POST3",
	// "Montag 28.02.1960 23:59:59 POST3" },
	// { "*.02.1960 23:59:59 POST3", "? **.**.**** **:**:** UNDEF",
	// "Montag 29.02.1960 23:59:59 POST3" },
	//
	// // 6 + 7
	// { "29.*.* 23:59:59 POST3", "* 29.03.1960 23:59:59 POST3",
	// "Montag 29.02.1960 23:59:59 POST3" },
	// { "29.*.* 23:59:59 POST3", "* 29.01.1961 23:59:59 POST3",
	// "Montag 29.12.1960 23:59:59 POST3" },
	//
	// // 8 + 9
	// { "*.02.* 23:59:59 POST3", "* 29.02.1960 23:59:59 POST3",
	// "Montag 28.02.1960 23:59:59 POST3" },
	// { "*.02.* 23:59:59 POST3", "* 01.02.1961 23:59:59 POST3",
	// "Montag 29.02.1960 23:59:59 POST3" },
	//
	// // 10 - 12
	//
	// { "*.*.1960 23:59:59 POST3", "* 29.02.1960 23:59:59 POST3",
	// "Montag 28.02.1960 23:59:59 POST3" },
	// { "*.*.1960 23:59:59 POST3", "* 01.03.1960 23:59:59 POST3",
	// "Montag 29.02.1960 23:59:59 POST3" },
	// { "*.*.1960 23:59:59 POST3", "? **.**.**** **:**:** UNDEF",
	// "Montag 31.12.1960 23:59:59 POST3" },
	//
	// // 13
	//
	// { "31.*.* 23:59:59 POST3", "* 31.01.1961 23:59:59 POST3",
	// "Montag 31.12.1960 23:59:59 POST3" },
	//
	// // 14 - 17
	//
	// { "31.12.1960 23:59:* POST3", "? **.**.**** **:**:** UNDEF",
	// "Montag 31.12.1960 23:59:59 POST3" },
	// { "31.12.1960 23:*:* POST3", "? **.**.**** **:**:** UNDEF",
	// "Montag 31.12.1960 23:59:59 POST3" },
	// { "31.12.1960 *:*:* POST3", "? **.**.**** **:**:** UNDEF",
	// "Montag 31.12.1960 23:59:59 POST3" },
	// { "31.12.* *:*:* POST3", "* 31.12.1961 00:00:00 POST3",
	// "Montag 31.12.1960 23:59:59 POST3" },
	//
	// // 18 -
	// // Jeder Mittwoch im Februar 1960
	// { "Mittwoch *.02.1960 10:00:00 POST3",
	// "Mittwoch 03.02.1960 10:00:00 POST3",
	// "Montag 01.02.1960 23:59:59 POST3" },
	// { "Mittwoch *.02.1960 10:00:00 POST3",
	// "? **.**.**** **:**:** UNDEF",
	// "Montag 28.02.1960 23:59:59 POST3" },
	// { "Mittwoch *.*.1960 10:00:00 POST3",
	// "Mittwoch 02.03.1960 10:00:00 POST3",
	// "Montag 29.02.1960 23:59:59 POST3" },
	// { "Mittwoch *.02.* 10:00:00 POST3",
	// "Mittwoch 01.02.1961 10:00:00 POST3",
	// "Montag 29.02.1960 23:59:59 POST3" },
	// { "Mittwoch *.02.* 10:00:00 POST3",
	// "Mittwoch 24.02.1960 10:00:00 POST3",
	// "Dienstag 23.02.1960 23:59:59 POST3" },
	// { "Mittwoch 01.01.* 10:00:00 POST3",
	// "Mittwoch 01.01.1964 10:00:00 POST3",
	// "Freitag 01.01.1960 23:59:59 POST3" },
	// { "Mittwoch 01.*.* 10:00:00 POST3",
	// "Mittwoch 01.06.1960 10:00:00 POST3",
	// "Montag 23.02.1960 23:59:59 POST3" },
	// { "Mittwoch 01.*.* 10:00:00 POST3",
	// "Mittwoch 01.02.1961 10:00:00 POST3",
	// "Montag 01.06.1960 23:59:59 POST3" },
	// { "Mittwoch *.*.* 10:00:00 POST3",
	// "Mittwoch 24.02.1960 10:00:00 POST3",
	// "Montag 23.02.1960 23:59:59 POST3" },
	// { "Mittwoch *.*.* 10:00:00 POST3",
	// "Mittwoch 02.03.1960 10:00:00 POST3",
	// "25.02.1960 23:59:59 POST3" },
	// { "Mittwoch *.*.* 10:00:00 POST3",
	// "Mittwoch 04.01.1961 10:00:00 POST3",
	// "29.12.1960 23:59:59 POST3" },
	//
	// };
	//
	// @Test
	// public final void testRecurringEvents() {
	//
	// for (int testnr = 0; testnr < this.date2timeRecurring.length; testnr++) {
	// System.out.println(LFCR + "Test 5#" + testnr);
	// // PiT in = string2DataTime(this.date2timeRecurring[testnr][0]);
	// //
	// // System.out.println(this.date2timeRecurring[testnr][0] + "-->"
	// // + in.toString());
	//
	// // try {
	// PiT result = calcNextRecurringPiT(
	// unUNer(string2DataTime(this.date2timeRecurring[testnr][0])),
	// string2DataTime(this.date2timeRecurring[testnr][2]));
	// if (result != null)
	// assertEquals("Testfall #" + testnr,
	// this.date2timeRecurring[testnr][1], result.toString());
	// else
	// System.err.println("KEIN ERGEBNIS! OK");
	// //
	//
	// }
	// }
	// //
	// // @Test
	// // public final void testDate2String() {
	// // fail("Not yet implemented");
	// // }
	// //
	// // @Test
	// // public final void testAfter() {
	// // fail("Not yet implemented");
	// // }
	// //
	// // @Test
	// // public final void testBefore() {
	// // fail("Not yet implemented");
	// // }
	// //
	// // @Test
	// // public final void testEqual() {
	// // fail("Not yet implemented");
	// // }
	// //
	// // @Test
	// // public final void testDifference() {
	// // fail("Not yet implemented");
	// // }
	// //
	// // @Test
	// // public final void testAlter() {
	// // fail("Not yet implemented");
	// // }
	// //
	// // @Test
	// // public final void testGetDoomsday() {
	// // fail("Not yet implemented");
	// // }
	// //
	// // @Test
	// // public final void testShiftDate4Dow() {
	// // fail("Not yet implemented");
	// // }
	//
	/**
	 * @throws java.lang.Exception
	 *             DOC
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.err
				.println("-------------------------------------------------------------------------------");
		System.out.println("BEFORE");
	}

	/**
	 * @throws java.lang.Exception
	 *             DOC
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("AFTER");
	}

	/**
	 * @throws java.lang.Exception
	 *             DOC
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
	 *             DOC
	 */
	@SuppressWarnings("static-method")
	@After
	public void tearDown() throws Exception {
		System.out.println("TEARDOWN");
	}

	// ---- Selbstverwaltung --------------------------------------------------
	/** serialVersionUID */
	private final static long serialVersionUID = VERSION;
	/** Loggerinstanz */
	private final static Logger logger = Logger.getLogger(CPPitTest.class
			.getPackage().getName());
}
