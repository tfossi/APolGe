/**
 * DataTime.java
 * Branch time
 * APolGe
 * tfossi-team
 * licence GPLv3  
 */
package tfossi.apolge.time;

import static tfossi.apolge.time.pit.ConstCPPit.*;
import static tfossi.apolge.common.constants.ConstValue.LOGGER;
import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;
 
import java.io.Serializable;

import tfossi.apolge.events.IEvent;
import tfossi.apolge.time.pit.CPiT;

import org.apache.log4j.Logger;

/**
 * Enthält alle Termindaten inklusive der Schedularliste
 *  
 * @author tfossi
 * @version 17.08.2014
 * @modified -
 * @since Java 1.6
 */
public class DataTime implements Serializable {

	{	if (LOGGER)
		System.out.println(this.getClass().getSimpleName()+" V" + serialVersionUID);
	}
////
////	private final static long HOUR = 3600L * 1000L;
////	private final static long TAG = 24L * HOUR;
////	private final static long WEEK = 7L * TAG;
////	// private final static long MONTH = 30L * TAG;
////	// private final static long YEAR = 365L * WEEK;
//
//	// ---- TIMER ----------------------------------------------------------------

	/** gewünschte Schrittweite des Timers in ms */
	private long sollstep;

	/**
	 * @return den gewünschte zeitliche Schrittweite FIXME (Zeitraffer)
	 */
	// public for JB
	public final long getSollstep() {
// Old		if (this.sollstep < 0) {
 // Old this.sollstep = 24L * 3600000L * Monate.getDaysFromYear(this.actualdate.getYear());
//			this.sollstep = 1000L * 60L*60L*24L; //* this.actualdate.getTime();
// Old		} else {
// Old			if (Monate.isSchaltjahr(this.actualdate.getYear())) {
// Old				if (this.sollstep >= 365L) {
// Old					this.sollstep = 24L * 3600000L * Monate
// Old							.getDaysFromYear(this.actualdate.getYear());
// Old				}
// Old			} else {
// Old				if (this.sollstep > Monate.getDaysFromYear(this.actualdate.getYear()) * 3600000L * 24L) {
// Old					this.sollstep = 24L * 3600000L * Monate
// Old							.getDaysFromYear(this.actualdate.getYear());
// Old				}
// Old			}
// Old		}

		// assert false; //magicTest(this.actualdate)== 0 : "Days < 0: SOLLSTEP:"+this.sollstep;

		return this.sollstep;
	}
	/**
	 * @param step -
	 */
	// public for JB
	public final void setSollstep(long step) {
		assert step > 0;
		this.sollstep = step;
	}

//	// public for JB
	/**
	 * Temporärer Wert für Zeitschrittweite. Wird im schedular
	 * {@link TimesController#schedular()} berechnet
	 */
	private long tempstep;
	/**
	 * @return the tempstep
	 */
	public final long getTempstep() {
		return this.tempstep;
	}

	/**
	 * @param tempTimestep
	 *            the temporary Range of Timestep to set
	 */
	public final void setTempstep(long tempTimestep) {
		this.tempstep = tempTimestep;
	}
	/**
	 * aktuelle Schrittweite des Timers. Wird vom schedular
	 * {@link TimesController#schedular()} gesetzt, wenn dieser Zeitschritt kleiner als
	 * der gewünschte {@link #getSollstep()} ist, sonst vom Sollwert
	 */
	private long timerstep; // 3600 * 24 * 100;

	/**
	 * @return the timerstep
	 */
	public final long getTimerstep() {
		return this.timerstep;
	}

	/**
	 * @param rangeOfTimerstep
	 *            the Range of Timerstep to set
	 */
	public final void setTimerstep(long rangeOfTimerstep) {
		assert rangeOfTimerstep>=0L;
		this.timerstep = rangeOfTimerstep;
	}
	/**
	 * Datum zum Start
	 */
	private CPiT startdate = null;

	/**
	 * @param startdateOfGame
	 *            the Startdate of the Game to set
	 */
	public final void setStartdate(CPiT startdateOfGame) {
		this.startdate = startdateOfGame;
	}

	/** @return the startdate */
	public final CPiT getStartdate() {
		return this.startdate;
	}

	/** enddate */
	private CPiT enddate = null;
	/** @return the startdate */
	public final CPiT getEnddate() {
		return this.enddate;
	}
	/**
	 * TODO Comment
	 * @param enddatetime -
	 * @modified - 
	 */
	public final void setEnddate(String enddatetime) {
		if(LOGGER) logger.trace("gewünschtes Endedatum: " + enddatetime);
		assert this.enddate==null;
		this.enddate = string2DataTime(enddatetime).toCPit();
// Old		getDoomsday(this.enddate);
		if(LOGGER) logger.trace("ENDDATE: "+ this.enddate);
	}
	/**
	 * Einstellen des Startdates, des aktuellen Dates und des Einrichtungsscripts
	 * @param startdatetime
	 *            das Startdatum
	 * @modified - 
	 */
	public final void setStartdate(String startdatetime) {
		assert startdatetime != null;
		assert this.startdate ==null;
		if(LOGGER) logger.trace("gewünschtes Startdatum: " + startdatetime);
		this.startdate = unUNer(string2DataTime(startdatetime)).toCPit();
// Old		getDoomsday(this.startdate);
		if(LOGGER) logger.trace("STARTDATE: "+ this.startdate);
	}

	/**
	 * Aktuelles Datum JB
	 */
	private CPiT actualdate = null;

	/** @return das aktuelle Datum */
	// public for JAVABEANS
	public CPiT getActualdate() {
		return this.actualdate;
	}

	/**
	 * Setzt das aktuelle Datum
	 * 
	 * @param actualdateIn
	 *            ist das aktuelle Datum
	 */
	// public for JAVABEANS
	public void setActualdate(CPiT actualdateIn) {
		assert actualdateIn != null;
		assert actualdateIn.getTime()>=0L;
		this.actualdate = actualdateIn;
	}
	/**
	 * Einstellen des Startdates, des aktuellen Dates und des Einrichtungsscripts
	 * 
	 * @param datetime
	 *            das Startdatum
	 * @modified - 
	 */
	public final void setActualdate(String datetime) {
		assert datetime != null;
		assert this.actualdate==null;
		if(LOGGER) logger.trace("gewünschtes aktuelles Datum: " + datetime);
		this.actualdate = string2DataTime(datetime).toCPit();
		
		if(LOGGER) logger.warn("MagicTest: " + this.actualdate);
		int mt = 0; //magicTest(this.actualdate);
		if(mt > 0)
			assert false:("Gewähltes Datum zu hoch!");
		else if(mt < 0)
			assert false:("Gewähltes Datum zu niedrig!");
// Old		getDoomsday(this.actualdate);
		if(LOGGER) logger.trace("ACTUAL: "+this.actualdate);
	}
	
	// ---- Schedular ------------------------------------------------------------

	/**
	 * Enthält die Liste der vollqualifizierten Termine. Die Termine werden sortiert nach
	 * Zeitpunkt des Auftretens (Der nächste Event zuerst)
	 */
	private final TerminArray<IEvent> schedularliste;
	
	
	/**
	 * @return the schedularliste
	 */
	public final TerminArray<IEvent> getSchedularliste() {
		return this.schedularliste;
	}
//// /**
//	// * Stellt eine Schedularliste ein. Ist nur für JavaBeans-load() vorgesehen
//	// *
//	// * @param schedularlisteIn
//	// * the schedularliste to set
//	// */
//	/**
//	 * @param schedularliste
//	 *            the schedularliste to set
//	 */
//	public final void setSchedularliste(List<IEvent> schedularliste) {
//		this.schedularliste = schedularliste;
//	}
//
	// ---- Selbstverwaltung -----------------------------------------------------
	/** serialVersionUID */
	private final static long serialVersionUID = VERSION;
	/** logger */
	private final static Logger logger = Logger.getLogger(DataTime.class.getPackage().getName());


	/**
	 * TODO Comment
	 * @modified -
	 */
	public DataTime() {
		this.schedularliste = new TerminArray<IEvent>(1000); 
	}
}
