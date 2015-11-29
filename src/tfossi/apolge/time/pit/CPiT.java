/**
 * CPiT.java
 * Branch master
 * APolGe
 * tfossi-team
 * licence GPLv3  
 */
package tfossi.apolge.time.pit;

import java.util.Calendar;

import org.apache.log4j.Logger;

import tfossi.apolge.common.types.Tage;
import static tfossi.apolge.common.constants.ConstValue.*;
import static tfossi.apolge.common.constants.ConstValueExtension.*;
import static tfossi.apolge.time.pit.ConstCPPit.*;

/**
 * Point in Time ist das allgemeine Zeitformat
 *
 * @see PPiT
 * 
 * @author tfossi
 * @version 1.07.2014
 * @modified -
 * @since Java 1.6
 */
public class CPiT implements Cloneable {
	{
		if (LOGGER)
			System.out.println(this.getClass().getSimpleName()+" V" + serialVersionUID);
	}
//
//	/** ppit */
//	@SuppressWarnings("unused")
//	private PPiT ppit;
	/** dayOfWeek */
	private Tage dayOfWeek = Tage.UNDEF;

	/**
	 * TODO Comment
	 * @return - 
	 * @modified - 
	 */
	public final Tage getDayOfWeek() {
		return this.dayOfWeek;
	}

	/**
	 * Tagesmilisekundenzähler
	 */
	private long timecounter = 0L;

	/**
	 * @param mode
	 *            NULL, DATE, TIME, DOW. SHIFT
	 * @return the datetime
	 */
	public final String getDatetime(int mode) {
		return Date2String(this.timecounter, mode);
	}
//
//	/*
//	 * Erzeugt eine PiT
//	 * 
//	 * @param year
//	 *            Jahreszahl
//	 * @param month
//	 *            Monat 1..12
//	 * @param day
//	 *            Tag 1..28/29/30/31
//	 * @param hour
//	 *            Stunde 0..23
//	 * @param minute
//	 *            Minute 0..59
//	 * @param second
//	 *            Sekunde 0..59
//	 * @param shift
//	 *            NOSHIFT, PRE5, PRE4, PRE3, PRE2, PRE1, NORMAL, POST1, POST2,
//	 *            POST3, POST4, POST5
//	 * @param dayOfWeek
//	 *            NODAY, MONTAG, DIENSTAG, MITTWOCH, DONNERSTAG, FREITAG,
//	 *            SONNABEND, SONNTAG
//	 */
	/**
	 * TODO Comment
	 * @param ppit -
	 * @modified -
	 */
	public CPiT(PPiT ppit) {
		if (!ppit.tstDefinite())
			return;
		Calendar cal = Calendar.getInstance();
		cal.set(ppit.getYear(), ppit.getMonth() - 1, ppit.getDay(),
				ppit.getHour(), ppit.getMinute(), ppit.getSecond());
				
		cal.set(Calendar.MILLISECOND, (int) ppit.getShift().ms);
		this.timecounter = cal.getTimeInMillis();
		
		
//		this.dayOfWeek = (ppit.getDoW() == Tage.NODAY ? Tage.NODAY : Tage
//				.values()[(cal.get(Calendar.DAY_OF_WEEK) == 1 ? 7 : cal
//				.get(Calendar.DAY_OF_WEEK)) - 1]);
		this.dayOfWeek = (ppit.getDoW() == Tage.NODAY ? Tage.NODAY : Tage
				.values()[(cal.get(Calendar.DAY_OF_WEEK))]) ;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return Date2String(this.timecounter, DATE | TIME | DOW | SHIFT);
	}
//
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
//		return Date2String(this.timecounter, mode);
//	}
//
	/**
	 * TODO Comment
	 * @return - 
	 * @modified - 
	 */
	public final long getTime() {
		return this.timecounter;
	}

	/**
	 * TODO Comment
	 * @param ms - 
	 * @modified - 
	 */
	public final void setTime(long ms) {
		this.timecounter = ms;
	}

	
	/**
	 * Addiere (Subtrahiere) Zeit
	 * 
	 * @param l
	 *            Zeit in ms
	 */
	public void addTime(long l) {
		this.timecounter += l;

		if (this.dayOfWeek != Tage.NODAY) {
			// Wochentag. Bei timecounter = 0 ist DONNERSTAG! (1.1.1970)
			long nDay = (this.timecounter/(24L*3600L*1000L))%7;
			// neuer Wochentag 1(SONNTAG) - 7(SONNABEND)
			long dday = ((DOWoffset+nDay)%7)+1;
			// ... eintragen
			this.dayOfWeek = Tage.values()[(int) dday];
		}
	}

	@Override
	public CPiT clone() {
		try {
			return (CPiT) super.clone();
		} catch (CloneNotSupportedException e) {
			// this shouldn't happen, since we are Cloneable
			throw new InternalError();
		}
	}

	// ---- Selbstverwaltung --------------------------------------------------

	/** serialVersionUID */
	private final static long serialVersionUID = VERSION;
	
	/** Loggerinstanz */
	@SuppressWarnings("unused")
	private final static Logger logger = Logger.getLogger(CPiT.class
			.getPackage().getName());
}
