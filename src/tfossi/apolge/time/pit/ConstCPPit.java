/** 
 * ConstCPPit.java
 * Branch cptime
 * APolGe
 * tfossi-team
 * licence GPLv3  
 */
package tfossi.apolge.time.pit;

import static tfossi.apolge.common.constants.ConstValue.LFCR;
import static tfossi.apolge.common.constants.ConstValue.LOGGER;
import static tfossi.apolge.common.constants.ConstValue.NTAB;
import static tfossi.apolge.common.constants.ConstValue.TAB;

import java.util.Calendar;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import tfossi.apolge.common.constants.ConstValueExtension.Shift;
import tfossi.apolge.common.types.Tage;
import tfossi.apolge.time.pit.CPiT;
import tfossi.apolge.time.pit.PPiT;

/**
 * Konstanten und konstante Methoden für CPPit
 * 
 * @see CPiT
 * @see PPiT
 * 
 * @author tfossi
 * @modified -
 * @version 10.08.2014
 * @since Java 1.6
 */
public class ConstCPPit {

	static {
		// Neutrale Zeitzone einstellen (Weltzeit)
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}

	/**
	 * Korrekturwert DOW: 1.1.1970 (timecounter=0) ist Donnerstag. Tag DOWOffset
	 * nach erstem Wochentag (Ordinal 0!)
	 */
	public final static long DOWoffset = Tage.DONNERSTAG.getOrdinal() - 1;

	// ---- Datum-Konstanten --------------------------------------------------
	/** NULL */
	public final static int NULL = 0;
	/** DATE */
	public final static int DATE = 1;
	/** TIME */
	public final static int TIME = 2;
	/** DOW */
	public final static int DOW = 4;
	/** SHIFT */
	public final static int SHIFT = 8;

	/** sdate */
	private static final String sdate = "[\\p{Alnum}]{0,2}[\\.]"
			+ "[\\p{Alnum}]{0,2}[\\.]?" + "[\\p{Alnum}]{0,4}";
	/** stime */
	private static final String stime = "[\\p{Alnum}]{0,2}:"
			+ "[\\p{Alnum}]{0,2}:?" + "[\\p{Alnum}]{0,2}";
	/** sdow */
	private static final String sdow = "^[\\p{Alpha}]+";
	/** sshift */
	private static final String sshift = "[\\p{Alpha}]+[\\p{Alnum}]?$";

	/** pdate */
	private static final Pattern pdate = Pattern.compile(sdate);
	/** ptime */
	private static final Pattern ptime = Pattern.compile(stime);
	/** pdow */
	private static final Pattern pdow = Pattern.compile(sdow);
	/** pshift */
	private static final Pattern pshift = Pattern.compile(sshift);

	// static int cc = 0;

	/**
	 * Konvertiert String DatumZeit in {@linkplain PPiT} <br>
	 * Formate [TT.MM.YYYY | HH:MM:SS:SHIFT | WOCHENTAG]<br>
	 * Undefinierte Daten oder '*' werden als periodisch ('-1') belegt.
	 * 
	 * @param dataRaw
	 *            ist das Datum, Zeit und/oder Wochentag
	 * @return ist das Datum im Format {@linkplain PPiT}.
	 */
	public final static PPiT string2DataTime(String dataRaw) {

		// Mit 'x' maskierte werden mit '1' belegt.
		String data = dataRaw.replace('x', '1');

		if (data == null)
			return null;
		if (LOGGER)
			logger.trace("START:" + LFCR + TAB + "INPUT: " + data);

		data = data.replace("*", "");
		if (LOGGER)
			logger.trace("START:" + LFCR + TAB + "INPUT: " + data);

		PPiT dt = new PPiT();

		Matcher m;
		m = pdate.matcher(data);
		String dateToken = "*";
		if (m.find()) {
			dateToken = (m.start() > m.end() ? "*" : data.substring(m.start(),
					m.end()));
		}
		if (LOGGER)
			logger.trace(dateToken);
		dt = string2Date(dt, dateToken);

		m = ptime.matcher(data);
		String timeToken = "*";
		if (m.find()) {
			timeToken = (m.start() > m.end() ? "*" : data.substring(m.start(),
					m.end()));
		}
		if (LOGGER)
			logger.trace(timeToken);
		dt = string2Time(dt, timeToken);

		m = pdow.matcher(data);
		String dowToken = "*";
		if (m.find()) {
			dowToken = (m.start() > m.end() ? "*" : data.substring(m.start(),
					m.end()));
			Tage dow = Tage.valueOf(dowToken.toUpperCase());
			if (dow != null)
				dt.setTage(dow);
		}
		m = pshift.matcher(data);
		String shiftToken = "*";
		if (m.find()) {
			shiftToken = (m.start() > m.end() ? "*" : data.substring(m.start(),
					m.end()));
			Shift shift = Shift.valueOf(shiftToken.toUpperCase());
			if (shift != null)
				dt.setShift(shift);
		}

		if (LOGGER)
			logger.trace("END:" + LFCR + TAB + "OUTPUT: " + dt);
		return dt;
	}

	/**
	 * Füllt alle undefinierten Daten '-1' aus rawDate mit den
	 * korrespondierenden Daten aus 'filler' auf.
	 * 
	 * @param rawDate
	 *            das Datum mit den undefinierten Einträgen
	 * @param filler
	 *            das Datum, das die Füllwerte enthält
	 * @return das Ergebnis
	 */
	public final static CPiT fill(PPiT rawDate, CPiT filler) {

		if (LOGGER)
			logger.trace(NTAB + "Fülle \"" + rawDate + "\" mit \"" + filler
					+ "\" auf!");

		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(filler.getTime());
		PPiT rD = rawDate.clone();

		if (rD.tstYear())
			rD.setYear(cal.get(Calendar.YEAR));
		if (rD.tstMonth())
			rD.setMonth(cal.get(Calendar.MONTH) + 1);
		if (rD.tstDay())
			rD.setDay(cal.get(Calendar.DAY_OF_MONTH));
		if (rD.tstHour())
			rD.setHour(cal.get(Calendar.HOUR_OF_DAY));
		if (rD.tstMinute())
			rD.setMinute(cal.get(Calendar.MINUTE));
		if (rD.tstSecond())
			rD.setSecond(cal.get(Calendar.SECOND));
		if (rD.tstShift())
			rD.setShift(cal.get(Calendar.MILLISECOND));
		if (rD.tstDoW())
			rD.setDayOfWeek(cal.get(Calendar.DAY_OF_WEEK) == 0 ? 7 : cal
					.get(Calendar.DAY_OF_WEEK));

		return rD.toCPit();
	}
//
//	/**
//	 * Maskiert PiT. Alle Werte aus mask, die nicht -1 (='*') sind, werden
//	 * ausmaskiert
//	 * 
//	 * @param rawDate
//	 *            das Original
//	 * @param mask
//	 *            die Maske
//	 * @return der maskierte Clone des Originals
//	 */
//	public final static PPiT mask(CPiT rawDate, PPiT mask) {
//		if (LOGGER)
//			logger.trace(NTAB + "Maskiere \"" + rawDate + "\" mit \"" + mask
//					+ "\" auf!");
//		PPiT rD = PPiT.fromTC2PiT(rawDate.getTime());
//		if (mask.getYear() != -1)
//			rD.setYear(-1);
//		if (mask.getMonth() != -1)
//			rD.setMonth(-1);
//		if (mask.getDay() != -1)
//			rD.setDay(-1);
//		if (mask.getHour() != -1)
//			rD.setHour(-1);
//		if (mask.getMinute() != -1)
//			rD.setMinute(-1);
//		if (mask.getSecond() != -1)
//			rD.setSecond(-1);
//		if (mask.getShift() != Shift.UNDEF)
//			rD.setShift(mask.getShift());
//		if (mask.getDoW() != Tage.UNDEF)
//			rD.setDayOfWeek(mask.getDoW());
//
//		return rD;
//	}
//
	/**
	 * Unbestimmte Einträge für Wochentag und Shift konkretisieren:<br>
	 * <code>Tage.UNDEF --> Tage.NODAY<br>
	 * Shift.UNDEF --> Shift.NORMAL</code>
	 * 
	 * @param unDate
	 *            das Datum
	 * @return konkretes Datum
	 */
	public final static PPiT unUNer(PPiT unDate) {

		// Wenn kein Tag definiert ist, dann auf Tage.NODAY
		// setzen
		if (unDate.getDoW() == Tage.UNDEF)
			unDate.setTage(Tage.NODAY);

		// Wenn immer noch kein Shift definiert ist, dann
		// auf Shift.NORMAL setzen
		if (unDate.getShift() == Shift.UNDEF)
			unDate.setShift(Shift.NORMAL);
		return unDate;
	}

	/**
	 * Berechnet den nächstmöglichen Termin nach dem Datum <code>actual</code>
	 * mit der Regel <code>periodic</code>
	 * 
	 * @param periodic
	 *            die Datums- und Zeitregel
	 * @param actual
	 *            das Vergleichsdatum
	 * @return der nächste Termin, oder null, wenn es keinen gibt
	 */
	public final static CPiT calcNextRecurringPiT(PPiT periodic, CPiT actual) {

		CPiT out = fill(periodic, actual);

		if (LOGGER)
			logger.trace("OUT rul(PPiT): " + periodic.toString());
		if (LOGGER)
			logger.trace("OUT act(CPiT): " + actual.toString());
		if (LOGGER)
			logger.trace("OUT new(CPiT): " + out.toString());

		// Ist DoW undef, dann checke, ob ein anderer Wochentag besser geeignet
		// ist.
		// Geht allenfalls nur bei DoW und einem zweiten undefinierten Tageswert
		if (periodic.tstDayOfWeek()
				&& (periodic.tstDay() || periodic.tstMonth() || periodic
						.tstYear())) {

			return null;
		}

		while (true) {
			CPiT out2 = ss(periodic, out);
			if (out2 == null)
				return null;

			// Überprüfung des Wochentages
			System.err.println("3 " + out + "=" + out2);
			if (out.equals(out2)) {
				int aTag = out2.getDayOfWeek().getOrdinal() - 1;
				int pTag = periodic.getDoW().ordinal() - 1;
				System.err.println("2 " + aTag + "=" + pTag);
				if (aTag == pTag)
					return out2;
				int diff = (-aTag + pTag + 7 - 1) % 7;
				out.addTime(diff * 1000L * 60L * 60L * 24L);
			}
		}
	}

	/**
	 * Sucht den nächsten möglichen Zeitwert nach out
	 * 
	 * @param rules
	 *            die Regeln
	 * @param out
	 *            der Zeitwert
	 * @return der nächste Wert oder NULL, wenn es keinen Wert gibt!
	 */
	private static CPiT ss(PPiT rules, CPiT out) {

		if (rules.tstSecond()) {
			logger.trace(rules);
			out.addTime(1000L);
			return out;
		}
		return mmi(rules, out);
	}

	/**
	 * Sind die Minuten nicht gesetzt, dann +1min
	 * 
	 * @param rules
	 *            die Regeln
	 * @param out
	 *            der Zeitwert
	 * @return der nächste Wert
	 */
	private static CPiT mmi(PPiT rules, CPiT out) {
		if (rules.tstMinute()) {
			logger.trace(rules);
			out.addTime(60L * 1000L);
			return out;
		}
		return hh(rules, out);
	}

	/**
	 * Sind die Stunden nicht gesetzt, dann +1h
	 * 
	 * @param rules
	 *            die Regeln
	 * @param out
	 *            der Zeitwert
	 * @return der nächste Wert
	 */
	private static CPiT hh(PPiT rules, CPiT out) {
		if (rules.tstHour()) {
			logger.trace(rules);
			out.addTime(60L * 60L * 1000L);
			return out;
		}
		return dd(rules, out);
	}

	/**
	 * Sind die Tage nicht gesetzt, dann +1d
	 * 
	 * @param rules
	 *            die Regeln
	 * @param out
	 *            der Zeitwert
	 * @return der nächste Wert
	 */
	private static CPiT dd(PPiT rules, CPiT out) {
		if (rules.tstDay()) {
			logger.trace(rules);
			out.addTime(24L * 60L * 60L * 1000L);
			return out;
		}
		return mm(rules, out);
	}

	/**
	 * Sind die Monate nicht gesetzt, dann +1Monat
	 * 
	 * @param rules
	 *            die Regeln
	 * @param out
	 *            der Zeitwert
	 * @return der nächste Wert
	 */
	private static CPiT mm(PPiT rules, CPiT out) {
		if (rules.tstMonth()) {
			logger.trace(rules);
			Calendar cal = Calendar.getInstance();
			cal.setTimeInMillis(out.getTime());
			cal.add(Calendar.MONTH, 1);

			out.setTime(cal.getTimeInMillis());
			cal = null;
			return out;
		}
		return yy(rules, out);
	}

	/**
	 * Sind die Jahre nicht gesetzt, dann +1y
	 * 
	 * @param rules
	 *            die Regeln
	 * @param out
	 *            der Zeitwert
	 * @return der nächste Wert
	 */
	private static CPiT yy(PPiT rules, CPiT out) {
		if (rules.tstYear()) {
			logger.trace(rules);
			Calendar cal = Calendar.getInstance();
			cal.setTimeInMillis(out.getTime());
			cal.add(Calendar.YEAR, 1);

			out.setTime(cal.getTimeInMillis());
			cal = null;
			return out;
		}
		return ww(rules, out);
	}

	/**
	 * Sind die Wochen nicht gesetzt, dann später weiter
	 * 
	 * @param rules
	 *            die Regeln
	 * @param out
	 *            der Zeitwert
	 * @return der nächste Wert
	 */
	private static CPiT ww(PPiT rules, CPiT out) {
		// if (rules.tstDayOfWeek()) {
		// logger.trace("DoW ist undef/* " + rules + LOGTAB + "Return "
		// + out.toString());
		// return null;
		// //// return out;
		// }
		// logger.trace(out + " ist def " + rules);
		// // return null; // undef();
		// return out;
		return null;
	}

	// /**
	// * @return einen undefinierten Zeitwert (Shift.UNDEF)
	// */
	// private static final CPiT undef() {
	// PPiT ppit = new PPiT();
	// ppit.setShift(Shift.UNDEF);
	// return new CPiT(ppit);
	// }

	/** smonat */
	private static final String smonat = "\\.[\\p{Alnum}]{0,4}[\\.]";
	/** sjahr */
	private static final String sjahr = "\\.[\\p{Alnum}]{0,4}[^\\.]$";
	/** stag */
	private static final String stag = "^[^\\.][\\p{Alnum}]{0,2}\\.";
	/** pday */
	private static final Pattern pday = Pattern.compile(stag);
	/** pmonth */
	private static final Pattern pmonth = Pattern.compile(smonat);
	/** pyear */
	private static final Pattern pyear = Pattern.compile(sjahr);

	/**
	 * Konvertiert einen String in Datum.<br>
	 * Zuerst der Tag, dann Monat und zum Schluß Jahr. Fehlt ein Eintrag, werden
	 * die folgenden auch nicht mehr behandelt. Ausnahme: Ist compare gesetzt,
	 * wird der entsprechende Wert aus 'compare' genommen.
	 * 
	 * @param dtIn
	 *            ist die vorbelegte DataTime
	 * @param date
	 *            ist der Datumsstring
	 * @return das konvertierte Datum
	 */
	private final static PPiT string2Date(PPiT dtIn, String date) {
		if (LOGGER)
			logger.trace("Convert " + date);

		Matcher m = pday.matcher(date);
		String dayToken = "*";
		if (m.find()) {
			dayToken = (m.start() >= m.end() ? "*" : date.substring(m.start(),
					m.end() - 1));
		}

		m = pmonth.matcher(date);
		String monthToken = "*";
		if (m.find()) {
			monthToken = (m.start() > m.end() ? "*" : date.substring(
					m.start() + 1, m.end() - 1));
		}

		m = pyear.matcher(date);
		String yearToken = "*";
		if (m.find()) {
			yearToken = (m.start() >= m.end() ? "*" : date.substring(
					m.start() + 1, m.end()));
		} else {
			if (LOGGER)
				logger.trace("yearToken not matched" + dayToken + "."
						+ monthToken + "." + yearToken);
		}

		if (LOGGER)
			logger.trace("Token " + dayToken + "." + monthToken + "."
					+ yearToken);
		if (yearToken.equals("*"))
			dtIn.setYear(-1);
		else if (yearToken.isEmpty())
			dtIn.setYear(-1);
		else
			dtIn.setYear(Integer.parseInt(yearToken));

		if (monthToken.equals("*"))
			dtIn.setMonth(-1);
		else if (monthToken.isEmpty())
			dtIn.setMonth(-1);
		else
			dtIn.setMonth(Integer.parseInt(monthToken));

		if (dayToken.equals("*"))
			dtIn.setDay(-1);
		else if (dayToken.isEmpty())
			dtIn.setDay(-1);
		else
			dtIn.setDay(Integer.parseInt(dayToken));

		return dtIn;
	}

	/** shour */
	private static final String shour = "^[^:][\\p{Alnum}]{0,2}:";
	/** sminute */
	private static final String sminute = ":[\\p{Alnum}]{0,2}:";
	/** ssecond */
	private static final String ssecond = ":[\\p{Alnum}]{0,2}$";

	/** phour */
	private static final Pattern phour = Pattern.compile(shour);
	/** pminute */
	private static final Pattern pminute = Pattern.compile(sminute);
	/** psecond */
	private static final Pattern psecond = Pattern.compile(ssecond);

	/**
	 * Konvertiert einen String in Zeit
	 * 
	 * @param dtIn
	 *            ist die vorbelegte DataTime
	 * @param time
	 *            ist der Zeitstring return die konvertierte Zeit
	 * @return Aus String konvertierte PPiT-Zeit
	 */
	private final static PPiT string2Time(PPiT dtIn, String time) {

		Matcher m = phour.matcher(time);
		String hourToken = "*";
		if (m.find()) {
			hourToken = (m.start() >= m.end() ? "*" : time.substring(m.start(),
					m.end() - 1));
		}

		m = pminute.matcher(time);
		String minuteToken = "*";
		if (m.find()) {
			minuteToken = (m.start() > m.end() ? "*" : time.substring(
					m.start() + 1, m.end() - 1));
		}

		m = psecond.matcher(time);
		String secondToken = "*";
		if (m.find()) {
			secondToken = (m.start() >= m.end() ? "*" : time.substring(
					m.start() + 1, m.end()));
		}

		if (hourToken.equals("*"))
			dtIn.setHour(-1);
		else if (hourToken.isEmpty())
			dtIn.setHour(-1);
		else
			dtIn.setHour(Integer.parseInt(hourToken));

		if (minuteToken.equals("*"))
			dtIn.setMinute(-1);
		else if (minuteToken.isEmpty())
			dtIn.setMinute(-1);
		else
			dtIn.setMinute(Integer.parseInt(minuteToken));

		if (secondToken.equals("*"))
			dtIn.setSecond(-1);
		else if (secondToken.isEmpty())
			dtIn.setSecond(-1);
		else
			dtIn.setSecond(Integer.parseInt(secondToken));

		return dtIn;
	}

	/**
	 * @param dt1
	 *            der PointInTime PiT
	 * @param mode
	 *            <ul>
	 *            DATE: Datum <br>
	 *            TIME: Uhrzeit<br>
	 *            DOW: Wochentag<br>
	 *            SHIFT: Pre5...Post5<br>
	 *            </ul>
	 * @return Datumsstring
	 * 
	 */
	@SuppressWarnings("boxing")
	public final static String Date2String(final PPiT dt1, int mode) {
		if (dt1 == null)
			return "DoW DD.MM.YYYY hh:mm:ss Shift";

		String dow = new String();
		if ((mode & DOW) == DOW) {
			if (dt1.getDoW() == Tage.NODAY)
				dow = "*";
			else if (dt1.getDoW() == Tage.UNDEF)
				dow = "?";
			else
				dow = dt1.getDoW().getName();
		}

		String date = new String();
		if ((mode & DATE) == DATE) {
			String dateY, dateM, dateD;
			if (dt1.tstYear())
				dateY = ".****";
			else
				dateY = String.format(".%04d", dt1.getYear());

			if (dt1.tstMonth())
				dateM = ".**";
			else
				dateM = String.format(".%02d", dt1.getMonth());

			if (dt1.tstDay())
				dateD = "**";
			else
				dateD = String.format("%02d", dt1.getDay());
			date = dateD + dateM + dateY;
		}

		String time = new String();
		if ((mode & TIME) == TIME) {
			String timeH, timeM, timeS;
			if (dt1.tstHour())
				timeH = "**";
			else
				timeH = String.format("%02d", dt1.getHour());
			if (dt1.tstMinute())
				timeM = ":**";
			else
				timeM = String.format(":%02d", dt1.getMinute());

			if (dt1.tstSecond())
				timeS = ":**";
			else
				timeS = String.format(":%02d", dt1.getSecond());
			time = timeH + timeM + timeS;
		}
		String shift = new String();
		if ((mode & SHIFT) == SHIFT) {
			if (dt1.getShift() == Shift.UNDEF)
				shift = "?";
			else
				shift = dt1.getShift().name();
		}
		switch (mode) {
		case DATE + TIME + DOW + SHIFT:
			return String.format("%s %s %s %s", dow, date, time, shift);
		case DATE + TIME + DOW:
			return String.format("%s %s %s", dow, date, time);
		case DATE + TIME + SHIFT:
			return String.format("%s %s %s", date, time, shift);
		case DATE + DOW + SHIFT:
			return String.format("%s %s %s", dow, date, shift);
		case TIME + DOW + SHIFT:
			return String.format("%s %s %s", dow, time, shift);
		case DATE + TIME:
			return String.format("%s %s", date, time);
		case DATE + DOW:
			return String.format("%s %s", dow, date);
		case DATE + SHIFT:
			return String.format("%s %s", date, shift);
		case TIME + DOW:
			return String.format("%s %s", dow, time);
		case TIME + SHIFT:
			return String.format("%s %s", time, shift);
		case DOW + SHIFT:
			return String.format("%s %s", dow, shift);
		case DATE:
			return String.format("%s", date);
		case TIME:
			return String.format("%s", time);
		case DOW:
			return String.format("%s", dow);
		case SHIFT:
			return String.format("%s", shift);
		default:
			throw new IllegalArgumentException("mode = " + mode);
		}
	}

	/**
	 * TODO Comment
	 * 
	 * @param timecounter
	 *            absoluter Zeitzähler
	 * @param mode
	 *            <ul>
	 *            DATE: Datum <br>
	 *            TIME: Uhrzeit<br>
	 *            DOW: Wochentag<br>
	 *            SHIFT: Pre5...Post5<br>
	 *            </ul>
	 * @return Datumsstring
	 * @author tfossi
	 * @version 11.08.2014
	 * @since Java 1.6
	 * @modified -
	 */
	public final static String Date2String(final long timecounter, int mode) {
		return Date2String(PPiT.fromTC2PiT(timecounter), mode);
	}

	/**
	 * Liegt der Zeitpunkt NACH dem Vergleichszeitpunkt
	 * 
	 * @param dt1
	 *            ist der Zeitpunkt
	 * @param dt2
	 *            ist Vergleichszeitpunkt
	 * @return true, liegt der Zeitpunkt NACH dem Vergleichszeitpunkt
	 * @since 0.00.066
	 */
	public final static boolean after(final CPiT dt1, final CPiT dt2) {
		if (dt1.getTime() > dt2.getTime())
			return true;

		return false;
	}
//
//	/**
//	 * Liegt der Zeitpunkt VOR dem Vergleichszeitpunkt
//	 * 
//	 * @param dt1
//	 *            Zeitpunkt
//	 * @param dt2
//	 *            ist Vergleichszeitpunkt
//	 * @return true, liegt der Zeitpunkt VOR dem Vergleichszeitpunkt
//	 */
//	public final static boolean before(final CPiT dt1, final CPiT dt2) {
//		if (dt1.getTime() < dt2.getTime())
//			return true;
//		return false;
//	}
//
//	/**
//	 * Ist der Zeitpunkt GLEICH dem Vergleichszeitpunkt
//	 * 
//	 * @param dt1
//	 *            Zeitpunkt
//	 * @param dt2
//	 *            ist Vergleichszeitpunkt
//	 * @return true, ist der Zeitpunkt GLEICH dem Vergleichszeitpunkt
//	 * @since 0.00.066
//	 */
//	public final static boolean equal(final CPiT dt1, final CPiT dt2) {
//		if (dt1.getTime() == dt2.getTime())
//			return true;
//		return false;
//	}
//
	/**
	 * Bestimmt das alter / jahresdifferenz zwischen zwei DateTimes
	 * 
	 * @param dt1
	 *            probe
	 * @param dt2
	 *            referenz
	 * @return Anzahl der ganzen Jahre
	 */
	public final static int alter(final CPiT dt1, final CPiT dt2) {
		assert dt1 != null;
		assert dt2 != null;
		Calendar cal1 = Calendar.getInstance();
		cal1.setTimeInMillis(dt1.getTime());
		Calendar cal2 = Calendar.getInstance();
		cal2.setTimeInMillis(dt2.getTime());

		int alter = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);

		// Korrektur für gleiches Jahr
		if (alter >= 0
				&& cal1.get(Calendar.DAY_OF_YEAR) < cal2
						.get(Calendar.DAY_OF_YEAR))
			return --alter;
		else if (alter < 0
				&& cal1.get(Calendar.DAY_OF_YEAR) > cal2
						.get(Calendar.DAY_OF_YEAR))
			return ++alter;
		return alter;
	}
//
//	/**
//	 * Abstand in Milli-Sekunden
//	 * 
//	 * @param dt1
//	 *            ist der Meßzeitpunkt
//	 * @param dt2
//	 *            ist Vergleichszeitpunkt
//	 * @return Abstand in Milli-Sekunden
//	 */
//	public final static long difference(final CPiT dt1, final CPiT dt2) {
//		// throws DatumsException {
//		// // Die Gesamtsumme in ms
//		// long diff = 0;
//		//
//		// // Jahresberechnung
//		// if (dt1.getYear() < 0)
//		// throw new DatumsException("dt1.getYear() < 0");
//		// if (dt2.getYear() < 0)
//		// throw new DatumsException("dt2.getYear() < 0");
//		//
//		// // Die Anzahl der Tage zwischen den Jahren!
//		// diff = Monate.getDaysFromToYear(dt2.getYear(), dt1.getYear());
//		//
//		// // Monatsberechnung
//		// if (dt1.getMonth() < 1 || dt1.getMonth() > 12)
//		// throw new
//		// DatumsException("dt1.getMonth() < 1 || dt1.getMonth() > 12: "
//		// + dt1.getMonth());
//		// if (dt2.getMonth() < 1 || dt2.getMonth() > 12)
//		// throw new
//		// DatumsException("dt2.getMonth() < 1 || dt2.getMonth() > 12: "
//		// + dt2.getMonth());
//		//
//		// diff +=
//		// Monate.values()[dt1.getMonth()].getDaysToMonth(dt1.getYear());
//		// diff -=
//		// Monate.values()[dt2.getMonth()].getDaysToMonth(dt2.getYear());
//		//
//		// // Tagesberechnung
//		// if (dt1.getDay() < 1
//		// || dt1.getDay() >
//		// Monate.values()[dt1.getMonth()].getDays(dt1.getYear()))
//		// throw new DatumsException("dt1.getDay() < 1 || dt1.getDay() > "
//		// + Monate.values()[dt1.getMonth()].getDays(dt1.getYear()) + ": "
//		// + dt1.getDay() + LFCR + "Jahr: " + dt1.getYear() + LFCR + "Monat: "
//		// + dt1.getMonth() + LFCR + "Tag: " + dt1.getDay());
//		// if (dt2.getDay() < 1
//		// || dt2.getDay() >
//		// Monate.values()[dt2.getMonth()].getDays(dt2.getYear()))
//		// throw new DatumsException("dt2.getDay() < 1 || dt2.getDay() > "
//		// + Monate.values()[dt2.getMonth()].getDays(dt2.getYear()) + ": "
//		// + dt2.getDay());
//		// diff += dt1.getDay() - dt2.getDay();
//		// diff *= 24L;
//		//
//		// // Stunden
//		// if (dt1.getHour() < 0 || dt1.getHour() > 23)
//		// throw new DatumsException("dt1.getHour() < 0 || dt1.getHour() > 23: "
//		// + dt1.getHour());
//		// if (dt2.getHour() < 0 || dt2.getHour() > 23)
//		// throw new DatumsException("dt2.getHour() < 0 || dt2.getHour() > 23: "
//		// + dt2.getHour());
//		// diff += dt1.getHour() - dt2.getHour();
//		// diff *= 60L;
//		//
//		// // Minuten
//		// if (dt1.getMinute() < 0 || dt1.getMinute() > 59)
//		// throw new
//		// DatumsException("dt1.getMinute() < 0 || dt1.getMinute() > 59: "
//		// + dt1.getMinute());
//		// if (dt2.getMinute() < 0 || dt2.getMinute() > 59)
//		// throw new
//		// DatumsException("dt2.getMinute() < 0 || dt2.getMinute() > 59: "
//		// + dt2.getMinute());
//		// diff += dt1.getMinute() - dt2.getMinute();
//		// diff *= 60L;
//		//
//		// // Sekunden
//		// if (dt1.getSecond() < 0 || dt1.getSecond() > 59)
//		// throw new
//		// DatumsException("dt1.getSecond() < 0 || dt1.getSecond() > 59: "
//		// + dt1.getSecond());
//		// if (dt2.getSecond() < 0 || dt2.getSecond() > 59)
//		// throw new
//		// DatumsException("dt2.getSecond() < 0 || dt2.getSecond() > 59: "
//		// + dt2.getSecond());
//		// diff += dt1.getSecond() - dt2.getSecond();
//		// diff *= 1000L;
//		//
//		// // Shift
//		// diff += dt1.shift.ms - dt2.shift.ms;
//		//
//		// return diff;
//		return dt1.getTime() - dt2.getTime();
//	}
//
//	/**
//	 * TODO Comment
//	 * 
//	 * @param delta
//	 *            -
//	 * @param absolut
//	 *            -
//	 * @return Zeitwert der Addition
//	 * @author tfossi
//	 * @version 11.08.2014
//	 * @since Java 1.6
//	 * @modified -
//	 */
//	public final static PPiT add(PPiT delta, PPiT absolut) {
//		assert false;
//		PPiT rc = absolut.clone();
//		//
//		// int over = 0;
//		// if (delta.getSecond() > -1) {
//		// rc.setSecond(rc.getSecond() + delta.getSecond());
//		// }
//		//
//		// if (rc.getSecond() > 59) {
//		// over = 1;
//		// rc.setSecond(rc.getSecond() - 60);
//		// } else if (rc.getSecond() < 0) {
//		// over = -1;
//		// rc.setSecond(rc.getSecond() + 60);
//		// }
//		//
//		// if (delta.getMinute() > -1)
//		// rc.setMinute(rc.getMinute() + delta.getMinute() + over);
//		// else
//		// rc.setMinute(rc.getMinute() + over);
//		// over = 0;
//		// if (rc.getMinute() > 59) {
//		// over = 1;
//		// rc.setMinute(rc.getMinute() - 60);
//		// } else if (rc.getMinute() < 0) {
//		// over = -1;
//		// rc.setMinute(rc.getMinute() + 60);
//		// }
//		//
//		// if (delta.getHour() > -1)
//		// rc.setHour(rc.getHour() + delta.getHour() + over);
//		// else
//		// rc.setHour(rc.getHour() + over);
//		// over = 0;
//		// if (rc.getHour() > 23) {
//		// over = 1;
//		// rc.setHour(rc.getHour() - 24);
//		// } else if (rc.getHour() < 0) {
//		// over = -1;
//		// rc.setHour(rc.getHour() + 24);
//		// }
//		//
//		// int mover = 0;
//		// if (delta.getMonth() > -1)
//		// rc.setMonth(rc.getMonth() + delta.getMonth() + mover);
//		// else
//		// rc.setMonth(rc.getMonth() + mover);
//		// mover = 0;
//		// if (rc.getMonth() > 12) {
//		// mover = 1;
//		// rc.setMonth(rc.getMonth() - 12);
//		// } else if (rc.getMonth() < 1) {
//		// mover = -1;
//		// rc.setMonth(rc.getMonth() + 12);
//		// }
//		//
//		// if (delta.getYear() > -1)
//		// rc.setYear(rc.getYear() + delta.getYear() + mover);
//		// else
//		// rc.setYear(rc.getYear() + mover);
//		//
//		// mover = 0;
//		//
//		// // TODO Das ist logisch nicht ganz richtig!
//		// if (delta.getDay() > -1)
//		// rc.setDay(rc.getDay() + delta.getDay() + over);
//		// else
//		// rc.setDay(rc.getDay() + over);
//		// over = 0;
//		// if (rc.getDay() >
//		// Monate.values()[rc.getMonth()].getDays(rc.getYear())) {
//		// over = 1;
//		// rc.setDay(rc.getDay() -
//		// Monate.values()[rc.getMonth()].getDays(rc.getYear()));
//		// } else if (rc.getDay() < 0) {
//		// over = -1;
//		// rc.setDay(rc.getDay() +
//		// Monate.values()[rc.getMonth()].getDays(rc.getYear()));
//		// }
//		//
//		return rc;
//	}

	// ---- Selbstverwaltung --------------------------------------------------
	/** logger */
	private final static Logger logger = Logger.getLogger(ConstCPPit.class
			.getPackage().getName() + ".ConstCPPit");
}
