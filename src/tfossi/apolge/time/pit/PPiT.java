/**
 * PPiT.java
 * Branch cptime
 * APolGe
 * tfossi-team
 * licence GPLv3  
 */
package tfossi.apolge.time.pit;

import static tfossi.apolge.time.pit.ConstCPPit.*;
import static tfossi.apolge.common.constants.ConstValue.*;
 
import java.util.Calendar;

import tfossi.apolge.common.constants.ConstValueExtension.Shift;
import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;
import tfossi.apolge.common.types.Tage;
import org.apache.log4j.Logger;

/**
 * Point in Time ist das allgemeine Zeitformat
 * 
 * @author tfossi
 * @version 1.07.2014
 * @modified -
 * @since Java 1.6
 */
public class PPiT implements Cloneable {
	{
		if (LOGGER)
			System.out.println(this.getClass().getSimpleName()+" V" + serialVersionUID);
	}

	/**
	 * Maske mit definierten = 0*2^xMask und undefinierten 1*2^xMask Datumsanteilen
	 * Undefiniert == 1
	 * Definiert = 0
	 */
	private int mask = 0;

	/** year */
	private int year = -1;

	/**
	 * @param year -
	 */
	public final void setYear(int year) {
		if (year == -1)
			this.mask |= (1 << yearMask);
		else
			this.mask &= ~(1 << yearMask);
		this.year = year;
	}
////
////	public final void clrYear() {
////		mask &= ~(1 << yearMask);
////	}

	/**
	 * TODO Comment
	 * @return -
	 * @modified - 
	 */
	public final int getYear() {
		return this.year;
	}

	/**
	 * TODO Comment
	 * @return -
	 * @modified - 
	 */
	public final int getMonth() {
		return this.month;
	}

	/**
	 * TODO Comment
	 * @return -
	 * @modified - 
	 */
	public final int getDay() {
		return this.day;
	}

	/**
	 * TODO Comment
	 * @return -
	 * @modified - 
	 */
	public final int getHour() {
		return this.hour;
	}

	/**
	 * TODO Comment
	 * @return -
	 * @modified - 
	 */
	public final int getMinute() {
		return this.minute;
	}

	/**
	 * TODO Comment
	 * @return - 
	 * @modified - 
	 */
	public final int getSecond() {
		return this.second;
	}

	/**
	 * TODO Comment
	 * @return - 
	 * @modified - 
	 */
	public final Shift getShift() {
		return this.shift;
	}

	/**
	 * TODO Comment
	 * @return - 
	 * @modified - 
	 */
	public final Tage getDoW() {
		return this.dow;
	}

	/** month */
	private int month = -1;

	/**
	 * TODO Comment
	 * @param month - 
	 * @modified - 
	 */
	public final void setMonth(int month) {
		if (month == -1)
			this.mask |= (1 << monthMask);
		else
			this.	mask &= ~(1 << monthMask);
		this.month = month;
	}

	/** day */
	private int day = -1;

	/**
	 * TODO Comment
	 * @param day - 
	 * @modified - 
	 */
	public final void setDay(int day) {
		if (day == -1)
			this.mask |= (1 << dayMask);
		else
			this.mask &= ~(1 << dayMask);
		this.day = day;
	}

	/** hour */
	private int hour = -1;

	/**
	 * TODO Comment
	 * @param hour - 
	 * @modified - 
	 */
	public final void setHour(int hour) {
		if (hour == -1)
			this.	mask |= (1 << hourMask);
		else
			this.	mask &= ~(1 << hourMask);
		this.hour = hour;
	}
	
	/** minute */
	private int minute = -1;

	/**
	 * TODO Comment
	 * @param minute - 
	 * @modified - 
	 */
	public final void setMinute(int minute) {
		if (minute == -1)
			this.mask |= (1 << minuteMask);
		else
			this.mask &= ~(1 << minuteMask);
		this.minute = minute;
	}

	/** second */
	private int second = -1;

	/**
	 * TODO Comment
	 * @param second - 
	 * @modified - 
	 */
	public final void setSecond(int second) {
		if (second == -1)
			this.	mask |= (1 << secondMask);
		else
			this.	mask &= ~(1 << secondMask);
		this.second = second;
	}

	/** shift */
	private Shift shift = Shift.UNDEF;

	/**
	 * TODO Comment
	 * @param shift - 
	 * @modified - 
	 */
	public final void setShift(Shift shift) {
		if (shift==Shift.UNDEF)
			this.	mask |= (1 << shiftMask);
		else
			this.	mask &= ~(1 << shiftMask);
		this.shift = shift;
	}

	/** dow */
	private Tage dow = Tage.UNDEF;

	/**
	 * TODO Comment
	 * @param dow - 
	 * @modified - 
	 */
	public final void setTage(Tage dow) {
		assert !dow.equals(Tage.UNDEF);
		if (dow.equals(Tage.NODAY) || dow.equals(Tage.UNDEF))
			this.	mask |= (1 << dowMask);
		else
			this.	mask &= ~(1 << dowMask);
		this.dow = dow;
	}



	/**
	 * @return true, wenn ein eindeutiges Datum erzeugt werden kann.
	 */
	public final boolean tstDefinite() {
		if(LOGGER)logger.trace("D PPIT MASKE: "+this.mask);
		return (this.mask & ~(1 << dowMask)) == 0;
	}

	/**
	 * TODO Comment
	 * @return - 
	 * @modified - 
	 */
	public final boolean tstYear() {
		return (this.mask & 1 << yearMask) != 0;
	}

	/**
	 * TODO Comment
	 * @return - 
	 * @modified - 
	 */
	public final boolean tstMonth() {
		return (this.mask & 1 << monthMask) != 0;
	}

	/**
	 * TODO Comment
	 * @return - 
	 * @modified - 
	 */
	public final boolean tstDay() {
		return (this.mask & 1 << dayMask) != 0;
	}

	/**
	 * TODO Comment
	 * @return - 
	 * @modified - 
	 */
	public final boolean tstHour() {
		return (this.mask & 1 << hourMask) != 0;
	}

	/**
	 * TODO Comment
	 * @return - 
	 * @modified - 
	 */
	public final boolean tstMinute() {
		return (this.mask & 1 << minuteMask) != 0;
	}

	/**
	 * TODO Comment
	 * @return - 
	 * @modified - 
	 */
	public final boolean tstSecond() {
		return (this.mask & 1 << secondMask) != 0;
	}

	/**
	 * TODO Comment
	 * @return - 
	 * @modified - 
	 */
	public final boolean tstShift() {
		return (this.mask & 1 << shiftMask) != 0;
	}

	/**
	 * TODO Comment
	 * @return - 
	 * @modified - 
	 */
	public final boolean tstDoW() {
		return (this.mask & 1 << dowMask) != 0;
	}

	/** Jahr 0 .. */
	private final static int yearMask = 7;
	/** Monat 1-12 */
	private final static int monthMask = 6;
	/** Tag 1- 28/29/30/31 */
	private final static int dayMask = 5;
	/** Stunde 0-23 */
	private final static int hourMask = 4;
	/** Minute 0-59 */
	private final static int minuteMask = 3;
	/** Sekunde 0-59 */
	private final static int secondMask = 2;
	/** Shift START...POST5 */
	private final static int shiftMask = 1;
	/** Wochentag Sonntag...Sonnabend */
	private final static int dowMask = 0;
//
//	/** ist 0 bzw PRE1 ... POST5. Im Konstruktor ist der Name Phase */
//	// private Shift shift = Shift.UNDEF;
//	/** Wochentag */
//	// private Tage dayOfWeek = Tage.UNDEF;
//
//	// private long timecounter = 0L;
//	// //
//	// // @SuppressWarnings("unused")
//	// // private String dateJB;
//	// //
//	// // public String getDateJB(){
//	// // return toString();
//	// // }
//	// // public void setDateJB(String datetime){
//	// // PiT tmp = string2DateTime(datetime, null, false);
//	// // this.year = tmp.year;
//	// // this.month = tmp.month;
//	// // this.day = tmp.day;
//	// // this.hour = tmp.hour;
//	// // this.minute = tmp.minute;
//	// // this.second = tmp.second;
//	// // this.shift = tmp.shift;
//	// // this.dayOfWeek = tmp.dayOfWeek;
//	// // tmp=null;
//	// // }
//	//
	/**
	 * @param mode
	 *            NULL, DATE, TIME, DOW. SHIFT
	 * @return the datetime
	 */
	public final String getDatetime(int mode) {
		return Date2String(this, mode);
	}

	/**
	 * Stellt Zeit und Datum ein. Periodische Zeiten(UNDEF/'*') werden mit '-1' belegt.
	 * 
	 * @param datetime
	 *            the datetime to set
	 * @return -
	 */
	public final static PPiT setDatetime(final String datetime) {
		return unUNer(string2DataTime(datetime));
	}

	/**
	 * Legt einen leeren PiT an
	 */
	public PPiT() {
		// leer
	}

	/**
	 * Erzeugt eine PiT
	 * 
	 * @param year
	 *            Jahreszahl
	 * @param month
	 *            Monat 1..12
	 * @param day
	 *            Tag 1..28/29/30/31
	 * @param hour
	 *            Stunde 0..23
	 * @param minute
	 *            Minute 0..59
	 * @param second
	 *            Sekunde 0..59
	 * @param shift
	 *            NOSHIFT, PRE5, PRE4, PRE3, PRE2, PRE1, NORMAL, POST1, POST2,
	 *            POST3, POST4, POST5
	 * @param dayOfWeek
	 *            NODAY, MONTAG, DIENSTAG, MITTWOCH, DONNERSTAG, FREITAG,
	 *            SONNABEND, SONNTAG
	 */
	public PPiT(int year, int month, int day, int hour, int minute, int second,
			Shift shift, Tage dayOfWeek) {
		setYear(year);
		setMonth(month);
		setDay(day);
		setHour(hour);
		setMinute(minute);
		setSecond(second);
		setShift(shift);

		if (!(dayOfWeek == Tage.NODAY || dayOfWeek == Tage.UNDEF) && !tstYear()
				&& !tstMonth() && !tstDay()) {
			Calendar cal = Calendar.getInstance();
			cal.set(year, month - 1, day, 0, 0, 0);
			int nr = cal.get(Calendar.DAY_OF_WEEK);
			this.setDayOfWeek(nr);
		} else if (dayOfWeek == Tage.NODAY) {
			setTage(dayOfWeek);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#clone()
	 */

	@Override
	public PPiT clone() {
		try {
			return (PPiT) super.clone();
		} catch (CloneNotSupportedException e) {
			// this shouldn't happen, since we are Cloneable
			throw new InternalError();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return Date2String(this, DATE | TIME | DOW | SHIFT);
	}

//	/**
//	 * Returns a string representation of the object. In general, the toString
//	 * method returns a string that "textually represents" this object. The
//	 * result should be a concise but informative representation that is easy
//	 * for a person to read. It is recommended that all subclasses override this
//	 * method.<br>
//	 * 
//	 * The toString method for class Object returns a string consisting of the
//	 * name of the class of which the object is an instance, the at-sign
//	 * character `@', and the unsigned hexadecimal representation of the hash
//	 * code of the object. In other words, this method returns a string equal to
//	 * the value of:<br>
//	 * 
//	 * <code>getClass().getName() + '@' + Integer.toHexString(hashCode())</code>
//	 * 
//	 * @param mode
//	 *            Stellt ein, welche Datumsinformationen erstellt werden:<br>
//	 *            <code>DATE<br>TIME<br>DOW<br> SHIFT<br></code>
//	 * @return a string representation of the object.
//	 */
//	public String toString(int mode) {
//		return Date2String(this, mode);
//	}
//
	/**
	 * TODO Comment
	 * @return - 
	 * @modified - 
	 */
	public final CPiT toCPit() {
		if (this.tstDefinite()) {
			return new CPiT(this);
		}
		assert false:this.toString();
		return null;
	}

	/**
	 * TODO Comment
	 * @param timecounter - 
	 * @return - 
	 * @modified - 
	 */
	public static PPiT fromTC2PiT(long timecounter) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(timecounter);
		Shift shift = Shift.UNDEF;
		for (Shift sh : Shift.values()) {
			if (sh.ms == cal.get(Calendar.MILLISECOND))
				shift = sh;
		}
		PPiT pit = new PPiT(cal.get(Calendar.YEAR),
				cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH),
				cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE),
				cal.get(Calendar.SECOND), shift,
//		FIX SONNTAG=1		Tage.values()[cal.get(Calendar.DAY_OF_WEEK) == 0 ? 7 : cal
//						.get(Calendar.DAY_OF_WEEK)]
				Tage.values()[cal.get(Calendar.DAY_OF_WEEK) == 0 ? Tage.NODAY.getOrdinal() : 
					cal.get(Calendar.DAY_OF_WEEK)]
								);
		return pit;
	}

	/**
	 * TODO Comment
	 * @return - 
	 * @modified - 
	 */
	public boolean tstDayOfWeek() {
		if (this.dow == Tage.UNDEF || this.dow == Tage.NODAY)
			return true;
		return false;
	}

	/**
	 * TODO Comment
	 * @param i - 
	 * @modified - 
	 */
	public void setShift(int i) {
		for (Shift sh : Shift.values()) {
			if (sh.ms == i)
				this.shift = sh;
		}
	}

	/**
	 * Stellt den Wochentag ein. 
	 * KEINE Prüfung, ob es stimmt. 
	 * @param i
	 *            der Wochentag (SONNTAG = 1!)
	 */
	public void setDayOfWeek(int i) {
		
		this.dow = Tage.values()[i];
//		FIXME SONNTAG=1 for (Tage tage : Tage.values()) {
//			if (i == 1)
//				this.dow = Tage.SONNTAG;
//			else if (tage.getOrdinal() == i - 1)
//				this.dow = tage;
//		}
	}

	/**
	 * TODO Comment
	 * @param t - 
	 * @modified - 
	 */
	public void setDayOfWeek(Tage t) {

		if (t==Tage.UNDEF || t==Tage.NODAY)
			this.mask |= (1 << dowMask);
		else
			this.mask &= ~(1 << dowMask);
		this.dow = t;
	}	
	
	// ---- Selbstverwaltung
	// -----------------------------------------------------
	/** serialVersionUID */
	private final static long serialVersionUID = VERSION;
	/** logger */
	private final static Logger logger = Logger.getLogger(PPiT.class
			.getPackage().getName());
}
