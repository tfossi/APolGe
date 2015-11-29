/**
 * Event.java
 * Branch master
 * APolGe
 * tfossi-team
 * licence GPLv3  
 */
package tfossi.apolge.events;

import static tfossi.apolge.time.pit.ConstCPPit.*;
import static tfossi.apolge.common.constants.ConstValue.*;
import static tfossi.apolge.common.constants.ConstValue.NTAB;
import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;
  
import java.util.LinkedList;
import java.util.Queue;


import tfossi.apolge.common.error.DatumsException;
import tfossi.apolge.report.IReport;
import tfossi.apolge.time.IExecuteTermin;
import tfossi.apolge.time.ITimes4Thread;
import tfossi.apolge.time.ITimesController;
import tfossi.apolge.time.TimesController;
import tfossi.apolge.time.pit.CPiT;
import tfossi.apolge.time.pit.PPiT;

import org.apache.log4j.Logger;

/**
 * Der Event ermöglicht eine zeitgesteuerte Bearbeitung von Ereignissen. Ein
 * Event beinhaltet:
 * <ul>
 * <li>Name</li>
 * <li>Object das ausgeführt wird:
 * <ul>
 * <li>LUA-Script: link #func}</li>
 * <li>JAVA: {@link #wer}</li>
 * </ul>
 * </li>
 * <li>Schnittstelle zum aktuellen {@link TimesController}</li>
 * </ul>
 * 
 * 
 * @author  tfossi
 * @version 1.0
 * @since java version "1.6.0_0"
 */
public class Event implements IEvent {
	{
		if (LOGGER)
			System.out.println(this.getClass().getSimpleName()+" V" + serialVersionUID);
	}

	/** Schnittstelle zum aktuellen TimesController */
	protected ITimes4Thread tc;

	// ---- JAVA-Aufruf

	/** Java-Instanz, die diesen Event bearbeitet */
	private IExecuteTermin wer;
	/* (non-Javadoc)
	 * @see tfossi.apolge.events.IEvent#getExT()
	 */
	@Override
	public final IExecuteTermin getExT(){
		return this.wer;
	}
	
	/** reporter */
	private final IReport reporter;
	

	/** report */
	Queue <Object> report = new LinkedList<Object>();

//	private long lTime;
	/*
	 * (non-Javadoc)
	 * 
	 * @see tfossi.apolge.time.IEvent#termin()
	 */
	@Override
	public final void termin() {
		
//		lTime =   System.currentTimeMillis();
		
		if(LOGGER) logger.info("DoIt "+NTAB+"wann: "+this.tc.getActualdate().getDatetime(
				DOW | DATE | TIME | SHIFT)
				+ NTAB + " was: " + this.getName()
				+ NTAB + " wer: " + this.wer+ LFCR+this.rules);
		this.report.add("DoIt "+NTAB+"wann: "+this.tc.getActualdate().getDatetime(
				DOW | DATE | TIME | SHIFT)
				+ NTAB + " was: " + this.getName()
				+ NTAB + " wer: " + this.wer
				+ NTAB + "Rule: "+this.rules);
		this.reporter.reporting(this.report);
		this.report.clear();
		if (this.rules != null && (this.rules.tstDayOfWeek() && (this.rules.tstDay()||this.rules.tstMonth()||this.rules.tstYear()))) {

			this.termin = calcNextRecurringPiT(this.rules,
					this.tc.getActualdate());
//			System.err.println(System.currentTimeMillis() - lTime+" calc (10)");
//			lTime =   System.currentTimeMillis();
			// Einen neuen Termin gibt es wenn
			// neuerTermin.Shift definiert ist und
			// kein Abbruchtermin definiert ist oder ein Abbruchtermin definiert
			// ist, der nach dem neuen Temin liegt
			assert !this.rules.tstShift();
	
			if ((this.until == null || after(this.until, this.termin))) {
				this.tc.addTermin(this.clone());
//				System.err.println(System.currentTimeMillis() - lTime+" add (4)");
//				lTime =   System.currentTimeMillis();
			}
		}
		Queue <Object> queue = this.wer.execute();
//		System.err.println(System.currentTimeMillis() - lTime+" exec (200)");
//		lTime =   System.currentTimeMillis();
		if(LOGGER) logger.debug("Message from "+this.wer.getClass().getSimpleName()+" for "+this.name+": " + queue);
		
		this.reporter.reporting(queue);
//		System.err.println(System.currentTimeMillis() - lTime+" report (6)");
//		lTime =   System.currentTimeMillis();
	}

	/** Name des Events */
	private transient String name;

	/*
	 * (non-Javadoc)
	 * 
	 * @see tfossi.apolge.time.IEvent#getName()
	 */
	@Override
	public final String getName() {
		return this.name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tfossi.apolge.time.IEvent#setName(java.lang.String)
	 */
	@Override
	public final void setName(final String nameIn) {
		this.name = nameIn;
	}

	/**
	 * Zeitpunkt des Events. Ist <code>rules==null</code>dann ist es ein einmaliges
	 * Event.
	 */
	private final transient PPiT rules;

	/**
	 * Zeitpunkt des Events. Ist <code>rules==null</code>, dann ist es ein einmaliges
	 * Event.
	 */
	private transient CPiT termin;

	/**
	 * Zeitpunkt des letzen (periodischen) Eventtermin. Ist <code>until==null</code>,
	 * dann ist der Endetermin ungültig
	 */
	private transient CPiT until = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see tfossi.apolge.time.IEvent#setPiT(tfossi.apolge.time.CPiT)
	 */
	@Override
	public final void setPiT(final CPiT pit) {
		this.termin = pit;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tfossi.apolge.time.IEvent#getDataTime()
	 */
	@Override
	public final CPiT getPiT() {
		return this.termin;
	}

	// ---- Selbstverwaltung
	// -----------------------------------------------------
	/** serialVersionUID */
	private final static long serialVersionUID = VERSION;
	/** logger */
	private final static Logger logger = Logger.getLogger(Event.class
			.getPackage().getName());

	/**
	 * Einrichten des Termins
	 * @param reporter -
	 * @param reportnumber - 
	 * 
	 * @param was
	 *            ist Name des Events
	 * @param wann
	 *            ist Event in Form "TAG DD:MM:YYYY HH:MM:SS SHIFT" Periodische
	 *            Anweisungen sind * TAG muss nicht gesetzt sein, dann giltet
	 *            jeder Tag Shift wird nicht periodisiert
	 * @param eventExecutor
	 *            ist Klasse, die bei Eventaufruf ausgeführt werden soll <li>
	 *            JavaObject
	 *            <ol>
	 *            <li>Aufrufklasse mit Schnittstelle link IExecuteEvent}</li>
	 *            </ol>
	 *            </li></u>
	 * @param iT4Th
	 *            ist der {@link ITimesController}
	 * @throws DatumsException -
	 */
	private Event(IReport reporter, int reportnumber, String was, PPiT wann /*String wann*/, IExecuteTermin eventExecutor,
			ITimes4Thread iT4Th) throws DatumsException {

//		if(TIMEEM)System.out.println((System.currentTimeMillis()-ConstValue.applicationstarttime)+" ......... Event...");
	
		this.reporter = reporter;
		this.tc = iT4Th;		
		this.wer = eventExecutor;
		this.setName(was);

		assert this.tc!=null;
		assert this.tc.getActualdate()!=null;
		if(LOGGER) logger.trace("WANN " + wann);
		
		this.rules = unUNer(wann);
	
//		if(TIMEEM)System.out.println((System.currentTimeMillis()-ConstValue.applicationstarttime)+" ......... Event peri");
	
		if (this.rules == null || !(this.rules.tstDayOfWeek() && (this.rules.tstDay()||this.rules.tstMonth()||this.rules.tstYear()))) {
			// this.rules.second != -1 && this.rules.minute != -1
			// && this.rules.hour != -1 && this.rules.day != -1
			// && this.rules.month != -1 && this.rules.year != -1) {
//			System.err.println(wann);
//			System.err.println(rules);
//			System.err.println(string2DataTime(wann));
//			System.err.println(unUNer(string2DataTime(wann)));
//			System.err.println(tstPeriodical(unUNer(string2DataTime(wann))));
			this.termin = unUNer(wann /*string2DataTime(wann)*/).toCPit();

			if(LOGGER) logger.trace("Einmaliger Termin [" + this.termin + "]");
//			if(TIMEEM)System.out.println((System.currentTimeMillis()-ConstValue.applicationstarttime)+" ......... Event r=null");
		
		} else {
			// Periodischer Termin
			CPiT p = this.tc.getActualdate().clone();
			p.addTime(-1L);
			this.termin = calcNextRecurringPiT(this.rules, p);
			if(LOGGER) logger.trace("Periodischer Termin [" + this.rules + "]");

//			if(TIMEEM)System.out.println((System.currentTimeMillis()-ConstValue.applicationstarttime)+" ......... Event r <> null");
			
		}
		if(LOGGER) logger.trace("Erzeuge Event \"" + was + "\"" + NTAB + wann + NTAB
				+ "RULES: " + this.rules + NTAB + "TERMIN: " + this.termin
				+ NTAB + this.tc.getActualdate());

		this.tc.addTermin(this);

		if(LOGGER) 
		logger.info("--- CREATE "+was);
//		if(TIMEEM)System.out.println((System.currentTimeMillis()-ConstValue.applicationstarttime)+" ......... Event");
	}

	/**
	 * Einrichten des Termins
	 * @param reporter -
	 * @param reportnumber - 
	 * 
	 * @param was
	 *            ist Name des Events
	 * @param wann
	 *            ist Event in Form "TAG DD:MM:YYYY HH:MM:SS SHIFT" Periodische
	 *            Anweisungen sind * TAG muss nicht gesetzt sein, dann gildet
	 *            jeder Tag Shift wird nicht periodisiert
	 * @param bis
	 *            ist das Endetermin des (periodischen) Events in vollständiger
	 *            Form
	 * @param eventExecutor
	 *            ist Klasse, die bei Eventaufruf ausgeführt werden soll <li>
	 *            JavaObject
	 *            <ol>
	 *            <li>Aufrufklasse mit Schnittstelle link IExecuteEvent}</li>
	 *            </ol>
	 *            </li></u>
	 * @param iT4Th
	 *            ist der {@link ITimesController}
	 * @throws DatumsException -
	 */
	public Event(IReport reporter, int reportnumber, String was, PPiT wann /*String wann*/, CPiT bis,
			IExecuteTermin eventExecutor, ITimes4Thread iT4Th)
			throws DatumsException {
		this(reporter, reportnumber, was, wann, eventExecutor, iT4Th);
		this.until = bis; //string2DataTime(bis);
		if(LOGGER) logger.trace("Abbruch bei " + this.until);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#clone()
	 */
	@Override
	public Event clone() {
		try {
			return (Event) super.clone();
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

		return this.name;
	}

}
