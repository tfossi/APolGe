/**
 * TimeController.java
 * Branch time
 * APolGe
 * tfossi-team
 * licence GPLv3  
 */
package tfossi.apolge.time;


import static tfossi.apolge.time.pit.ConstCPPit.*;
import static tfossi.apolge.common.constants.ConstValue.LFCR;
import static tfossi.apolge.common.constants.ConstValue.LOGGER;
import static tfossi.apolge.common.constants.ConstValue.LOGTAB;
import static tfossi.apolge.common.constants.ConstValue.NTAB;
import static tfossi.apolge.common.constants.ConstValue.TAB;
import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;
 
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import tfossi.apolge.common.error.DatumsException;
import tfossi.apolge.common.types.Tage;
import tfossi.apolge.events.DummyExecute;
import tfossi.apolge.events.Event;
import tfossi.apolge.events.IEvent;
import tfossi.apolge.report.IReport;
import tfossi.apolge.time.pit.CPiT;
import tfossi.apolge.time.pit.PPiT;

/**
 * Einrichten und Bedienen der Timer und der Termine.<br>
 * <ul>
 * <li><b>Timer</b> Zur Bedienung des Timers dient die Schnittstelle
 * {@linkplain ITimerThreadControls}</li>
 * <li><b>Controller</b> Zur Bedienung des Controllers dient die Schnittstelle
 * {@linkplain ITimesController}</li>
 * </ul>
 * <br>
 * <br>
 * <b>Anlegen eine Singleevents</b><br>
 * <tt>addTermin(NAME, EXECUTIONOBJECT, TERMIN);<br>
 * </tt> Der Event ist vollqualifiziert mit yyyy,mm,dd,hh,mm,ss,phase,dow<br>
 * <tt>addTermin("Single",<br> 
 * 		new CmdTagesabrechnungPCNData(null,-1), <br>
 * 		new DataTime(2009,04,13,16,52,0, DataTime.NORMAL, Tage.UNDEF));<br>
 * 		<br>
 * <b>Anlegen eine regelmässigen Events</b><br>
 * Der Unterschied liegt zum Singleevent liegt nur darin, das das Datum nicht
 * vollqualifiziert ist:<br>
 * Tagesevents: ... new DataTime(-1,-1,-1,h,m,s, DataTime.NORMAL, Tage.UNDEF)<br>
 * Monatsevents: ... new DataTime(-1,-1, d,h,m,s, DataTime.NORMAL, Tage.UNDEF)<br> 
 * Jahresevents: ... new DataTime(-1, m, d,h,m,s, DataTime.NORMAL, Tage.UNDEF)<br>
 * Zusätzlich Events an bestimmten Wochentagen:<br> 
 * new DataTime((y),(m),(d),(h),(m),(s), (DataTime.NORMAL), Tage.TAG)<br><br>
 * <b>Zeitverschiebung (Phase)</b><br>
 * Soll ein Event zwar zur selben Uhrzeit, aber vor oder nach anderen Events 
 * abgearbeitet werden, kann die Phase zwischen DataTime.PRE5..NORMAL..POST5 
 * geclustert werden. Zwischenwerte sind auch möglich PRE5+0..PRE5-99.
 * 
 * @author tfossi
 * @version 17.08.2014
 * @modified -
 * @since Java 1.6
 */
public class TimesController implements ITimesController, ITimes4Thread {

	{	if (LOGGER)
		System.out.println(this.getClass().getSimpleName()+" V" + serialVersionUID);
	}
	/** testCounter */
	private int testCounter = Integer.MIN_VALUE;
	/** testExT */
	private IExecuteTermin testExT = null;

	
	/* (non-Javadoc)
	 * @see tfossi.apolge.time.ITimesController#setTestCounter(int, tfossi.apolge.time.IExecuteTermin)
	 */
	@Override
	public final int setTestCounter(int counts, IExecuteTermin ext) {
		if(LOGGER) logger.trace(counts+" / "+this.testExT+" / "+ext);
		if (this.testExT == null) {
			this.testCounter = counts;
			this.testExT = ext;
		}
		return this.testCounter;
	}

	
	/* (non-Javadoc)
	 * @see tfossi.apolge.time.ITimesController#setStartdate(java.lang.String, tfossi.apolge.report.IReport)
	 */
	@Override
	public void setStartdate(String wann, IReport report) {
		if (this.time.getStartdate() == null) {
			// FIXME Wochentage raus und '*' rein!
			this.time.setStartdate(wann + " PRE5");
			this.time.setActualdate(wann + " START");
		}

		// Das Startdatum als einmaligen Event einplanen
		try {
			String was = "Startdatum";
			IExecuteTermin wie = new DummyExecute();
////			Report report = new Report();
//			try {
//				report.formatNew(new String[]{
//						was,"String"});
////				report.reporting(report)
//			} catch (ClassNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}	
			
//			report.formatNew(new String)null, 1, was) {
//				@Override
//				public void reporting(final Queue<?> report) {
//					System.out.println("STARTDATEREPORT" + LFCR
//							+ this.getClass().getSimpleName() + "#?" + TAB
//							+ report);
//				}
//
//				public void auswertung() {
//					System.out.println("STARTDATEREPORT" + LFCR
//							+ this.getClass().getSimpleName() + "#?");
//				}
//			};
			this.createTermin(report, 1, was, string2DataTime(wann + " PRE5"),
					null, wie);
		} catch (DatumsException e) {
			e.printStackTrace();
			System.exit(0);
		}

	}

	
	/* (non-Javadoc)
	 * @see tfossi.apolge.time.ITimesController#setEnddate(java.lang.String, tfossi.apolge.report.IReport)
	 */
	@Override
	public void setEnddate(String wann, IReport report) {
		if (wann == null)
			return;
		if (this.time.getEnddate() == null) {
			this.time.setEnddate(wann + " PRE5");
		}

		// Das Enddatum als einmaligen Event einplanen
		try {
			String was = "Enddatum";
			IExecuteTermin wie = new DummyExecute(null, this.tt);
//			IReport report = new Report();
//			try {
//				report.formatNew(new String[]{
//						was,"String"});
////				report.reporting(report)
//			} catch (ClassNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			
			this.createTermin(report, 1, was, string2DataTime(wann + " PRE5"),
					null, wie);
		} catch (DatumsException e) {
			e.printStackTrace();
			System.exit(0);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tfossi.apolge.time.ITimesController#createTermin(tfossi.apolge
	 * .time.IReport, int, java.lang.String, java.lang.String,
	 * tfossi.apolge.time.IExecuteTermin)
	 */
	@Override
	public final IEvent createTermin(final IReport reporter,
			final int reportnumber, final String was,
			final PPiT /* String */wann, final IExecuteTermin wie)
			throws DatumsException {
		return this.createTermin(reporter, reportnumber, was, wann, null, wie);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tfossi.apolge.time.ITimesController#createTermin(tfossi.apolge
	 * .time.IReport, int, java.lang.String, java.lang.String,
	 * java.lang.String, tfossi.apolge.time.IExecuteTermin)
	 */
	@Override
	public final IEvent createTermin(final IReport reporter,
			final int reportnumber, final String was, final PPiT wann /*
																	 * String
																	 * wann
																	 */,
			final CPiT bis, final IExecuteTermin wie) throws DatumsException {
		return new Event(reporter, reportnumber, was, wann, bis, wie, this);
	}

	// ---- Zeitdaten
	// ------------------------------------------------------------

	/**
	 * Speichert die Hauptzeitdaten JB
	 */
	private DataTime time = new DataTime();

	/**
	 * @return the time
	 */
	public final DataTime getTime() {
		return this.time;
	}

	/**
	 * @param time
	 *            the time to set
	 */
	public final void setTime(final DataTime time) {
		this.time = time;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tfossi.apolge.time.ITimesController#setSollstep(long)
	 */
	@Override
	public void setSollstep(final long step) {
		if (step == 0L)
			this.time.setSollstep(Long.MAX_VALUE);
		else
			this.time.setSollstep(step);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tfossi.apolge.time.ITimesController#setTimerstep(long)
	 */
	@Override
	public void setTimerstep(final long step) {
		this.time.setTimerstep(step);
	}

	/**
	 * @param step -
	 */
	public void setTempstep(final long step) {
		this.time.setTempstep(step);
	}

	// // private XMLEncoder enc;
	// // /**
	// // * @param enc the enc to set
	// // */
	// // public final void setEnc(XMLEncoder enc) {
	// // this.enc = enc;
	// // }
	// //

	// ---- Schedular --------------------------------------------------------

	/**
	 * Liste für Termine, die aus der Schedularliste gelöscht werden können. <li>
	 * Da sie in der Vergangenheit liegen.</li> <li>
	 * oder manuell hierher geschoben werden.</li>
	 */
	private final List<IEvent> delTermine = new ArrayList<IEvent>();

	/**
	 * Liste von Terminen, die neu zur Schedularliste hinzugefügt werden sollen.
	 * Diese Liste dient als Zwischenspeicher, da während der Abarbeitung der
	 * Schedularliste dort selber keine neuen Einträge hinzufügt werden können.
	 */
	private TerminArray<IEvent> neuerTermin = new TerminArray<IEvent>(1000);

	// {
	//
	// private static final long serialVersionUID = 1L;
	//
	// // add modifizieren, um Termine nach
	// // Zeitpunkt der Ausführung
	// // einzusortieren.
	// @Override
	// public boolean add(IEvent e) {
	// // if (e == null)
	// // throw new DatumsException("Event ist null!");
	// // if (before(e.getPiT(), TimesController.this.getActualdate()))
	// // throw new DatumsException("Event" + NTAB
	// // + e.getPiT().getDatetime(DATE | TIME | SHIFT) + NTAB
	// // + "ist in der Vergangenheit!");
	// int index = -1;
	// try {
	// index = sort(e);
	// } catch (Exception e1) {
	// e1.printStackTrace();
	// }
	// super.add(index, e);
	// return true;
	// }
	//
	// // addAll modifizieren, um Terminlisten nach
	// // Zeitpunkt der Ausführung
	// // einzusortieren.
	// public boolean addAll(Collection<? extends IEvent> c) {
	// // if (c == null)
	// // throw new Exception("Terminliste ist null!");
	// for (IEvent e : c) {
	// // if (e == null)
	// // throw new DatumsException("Event ist null!");
	// // if (before(e.getPiT(), TimesController.this.getActualdate()))
	// // throw new DatumsException("Event" + NTAB
	// // + e.getPiT().getDatetime(DATE | TIME | SHIFT)
	// // + NTAB + " ist in der Vergangenheit!");
	// try {
	// super.add(sort(e), e);
	// } catch (Exception e1) {
	// e1.printStackTrace();
	// }
	// }
	// return false;
	// }
	//
	// // Sortierer.
	// private int sort(IEvent e) throws Exception {
	// if (e == null)
	// throw new DatumsException("Event ist null!");
	// int index = 0;
	// PiT pit = e.getPiT();
	// for (IEvent e2 : this) {
	// if (before(pit, e2.getPiT()))
	// return index;
	// index++;
	// }
	// return index;
	// }
	// };

	// /**
	// * @return the neuerTermin
	// */
	// public final List<IEvent> getNeuerTermin() {
	// return this.neuerTermin;
	// }
	//
	// /**
	// * @param neuerTermin the neuerTermin to set
	// */
	// public final void setNeuerTermin(List<IEvent> neuerTermin) {
	// this.neuerTermin = neuerTermin;
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tfossi.apolge.time.ITimes4Thread#addTermin(tfossi.apolge.time.
	 * IEvent)
	 */
	@Override
	public final void addTermin(final IEvent event) {
		assert this.neuerTermin != null;
		assert this.time != null;

		// if(TIMEEM)System.out.println((System.currentTimeMillis()-ConstValue.
		// applicationstarttime)+" ............ add Event");
		logger.info(this.time.getActualdate());
		
		// if (this.time.getActualdate() == null)
		// throw new Exception("Aktuelle Zeit ist null");
		long difference = -1;
		try {

			difference = event.getPiT().getTime()- this.time.getActualdate().getTime(); 
			//difference(event.getPiT(), this.time.getActualdate());
			logger.info(new Long(difference));
			
			//if(TIMEEM)System.out.println((System.currentTimeMillis()-ConstValue
			// .applicationstarttime)+" ............ add Event diff");
			if (difference >= 0) {
				this.neuerTermin.add(event);
				this.time
						.setTempstep(difference < this.time.getTempstep() ? difference
								: this.time.getTempstep());
				if (LOGGER)
					logger.trace("Füge neues Event [" + event.getName() + "] "
							+ NTAB + "zum Termin [" + event.getPiT()
							+ "] in CANDIDATES ein.");
				// if(TIMEEM)System.out.println((System.currentTimeMillis()-
				// ConstValue
				// .applicationstarttime)+" ............ add Event new");
			} else {
				if (LOGGER)
					logger
							.trace("Das neue Event ["
									+ event.getName()
									+ "] "
									+ NTAB
									+ "zum Termin ["
									+ event.getPiT()
									+ "] wird nicht eingeführt, da es vor dem aktuellen Datum"
									+ NTAB
									+ "["
									+ this.time.getActualdate().getDatetime(
											DATE | TIME | DOW | SHIFT)
									+ "] liegt!");
			}
		} catch (Exception e) {
			e.printStackTrace();

			// ErrApp.DATUMSFORMATWRONG.erraufruf(logger, e.getMessage());
			// } catch (TerminException e) {
			// ErrApp.TERMINISWRONG.erraufruf(logger, e.getMessage());
			// } catch (Exception e) {
			// ErrApp.NDEF.erraufruf(logger, e.getMessage());
		}
//		assert false;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tfossi.apolge.time.ITimes4Thread#delTermin(tfossi.apolge.time.
	 * IEvent)
	 */
	@Override
	public final void delTermin(IEvent event) {
		if (event == null) {
			return;
		}
		if (LOGGER)
			logger.trace("Lösche " + event.getName());
		// Den Event aus der Schedularliste löschen
		if (this.time.getSchedularliste().contains(event)) {
			this.delTermine.add(event);
			return;
		}
		// Den Event aus der Liste der neuen Termine löschen
		if (this.neuerTermin.remove(event)) {
			return;
		}

		if (LOGGER)
			logger.trace(event.getName()
					+ "kann nicht gefunden werden. Nichts zu löschen");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tfossi.apolge.time.ITimesController#showSchedular()
	 */ 
	@Override
	public final List<String> showSchedular() {
		List<String> str = new ArrayList<String>();
		str.add(new String("SCHEDULAR:"));
		for (IEvent event : this.time.getSchedularliste()) {
			str.add(event.getPiT().getDatetime(DATE | TIME | DOW | SHIFT)
					+ " [" + event.getName() + "]");
		}
		return str;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tfossi.apolge.time.ITimesController#showNeuerTermin()
	 */
	@Override
	public final List<String> showNeuerTermin() {
		List<String> str = new ArrayList<String>();
		str.add(new String("CANDIDATES:"));
		for (IEvent event : this.neuerTermin) {
			str.add(event.getPiT().getDatetime(DATE | TIME | DOW | SHIFT)
					+ " [" + event.getName() + "]");
		}
		return str;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tfossi.apolge.time.ITimesController#showDeleteTermin()
	 */
	@Override
	public final List<String> showDeleteTermin() {
		List<String> str = new ArrayList<String>();
		str.add(new String("DEL:"));
		for (IEvent event : this.delTermine) {
			str
					.add(event.getPiT().getDatetime(DATE | TIME | DOW | SHIFT)
							+ " ");
		}
		return str;
	}

	// ---- TimeThread bearbeiten --------------------------------------------

	/*
	 * (non-Javadoc)
	 * 
	 * @see tfossi.apolge.time.ITimes4Thread#schedular()
	 */
	@Override
	public final synchronized void schedular() {
		assert this.time.getActualdate() != null : "Aktuelle Zeit ist null";


		// long lTime = System.currentTimeMillis();
		// System.err.println((lTime-System.currentTimeMillis())+
		// " schedualr start....");
		// lTime = System.currentTimeMillis();
		// if(LOGGER) logger.info( this.time.getActualdate().getDatetime(DOW |
		// DATE | TIME
		// | SHIFT));
		if (LOGGER)
			logger.debug(NTAB
					+ "AKTUELL: "
					+ this.time.getActualdate().getDatetime(
							DOW | DATE | TIME | SHIFT) + NTAB
					+ this.showSchedular() + NTAB + this.showNeuerTermin()+NTAB+
					this.showDeleteTermin());
		// if(LOGGER) logger.trace("ZEIT:"
		// + NTAB
		// + "START  : "
		// + this.time.getStartdate().getDatetime(
		// DOW | DATE | TIME | SHIFT));

		// Standardvorgabe: Minimaler Zeitschritt in ms wird vom User über
		// timerstepSoll vorgegeben,
		this.time.setTempstep(this.time.getSollstep());
		this.time.setTimerstep(this.time.getSollstep());
		// System.err.println((lTime -
		// System.currentTimeMillis())+" step (13)");
		// lTime = System.currentTimeMillis();

		// Die aufgelaufenen Löschaufträge ausführen
		this.time.getSchedularliste().removeAll(this.delTermine);
		this.delTermine.clear();
		// System.err.println((lTime -
		// System.currentTimeMillis())+" delTermine (20)");
		// lTime = System.currentTimeMillis();

		if (LOGGER)
			logger.trace("Die neuen Termine eintragen: " + NTAB
					+ this.showNeuerTermin());
		// Die aufgelaufenden Implementationsaufträge ausführen
		this.time.getSchedularliste().addAll(this.neuerTermin);
		// Die Aufträge in "Neuer Event" löschen. Sind im Schedular.
		this.neuerTermin.clear();

		// System.err.println((lTime -
		// System.currentTimeMillis())+" neuTermine (259)");
		// lTime = System.currentTimeMillis();

		// assert !this.time.getSchedularliste().isEmpty():"Leer";
		if (this.time.getSchedularliste().isEmpty()) {
			if (LOGGER)
				logger.info("Schedular-Ende: Keine Einträge mehr!" + LOGTAB
						+ "Die aktuellen Einträge noch abarbeiten!");
			this.tt.interrupt();			
		}

		// Alle Termine im Schedular durchgehen um den nächsten Timestep zu
		// bestimmen
		// Ist die Liste sortiert, dann ist es der nächste!
		CPiT actual = this.time.getActualdate();

		if (LOGGER)
			logger.trace("Die Listeneinträge mit aktuellem Datum"
					+ NTAB
					+ "["
					+ this.time.getActualdate().getDatetime(
							DOW | DATE | TIME | SHIFT) + "] abarbeiten:" + NTAB
					+ this.showSchedular());

		// System.err.println((lTime -
		// System.currentTimeMillis())+" all Termine ()");
		// lTime = System.currentTimeMillis();
		// Alle Termine duchgehen und ausführen
		boolean setInterrupt = false;
		for (IEvent event : this.time.getSchedularliste()) {
			if (this.testCounter == 0
					&& event.getExT().equals(this.testExT)
					){
				setInterrupt = true;
			}
			else if (this.testCounter > 0
					&& event.getExT().equals(this.testExT)) {
				this.testCounter--;
				if (LOGGER)
					logger.debug(new Integer(this.testCounter));
			}
			
			long diff;
//			try {
				diff = event.getPiT().getTime() -actual.getTime();
				//difference(event.getPiT(), actual);
				 
//			} catch (DatumsException e) {
//				// ErrApp.DATUMSFORMATWRONG.erraufruf(logger, e.getMessage());
//				continue;
//			}
			// Überprüfen, ob der Wochentag definiert ist und der exakte
			// Wochentag
			// vorliegt
			if (LOGGER)
				logger.trace("KANDIDAT [" + event.getName() + "]");
			// +NTAB
			// + Date2String(event.getPiT(), DOW | DATE | TIME | SHIFT)
			// + NTAB + "Differenz: " + diff + NTAB + "   (Tage): "
			// + (diff / 3600L / 1000L / 24L));
			//
			if (diff == 0
					&& event.getPiT().getDayOfWeek() != Tage.NODAY
					&& !event.getPiT().getDayOfWeek().equals(
							this.time.getActualdate().getDayOfWeek())) {
				// Abbruch wegen falschem Wochentag!
				if (LOGGER)
					logger.trace("Abbruch wegen falschem Wochentag!" + NTAB
							+ "Kandidat: " + event.getPiT().getDayOfWeek()
							+ " ? " + Tage.NODAY + NTAB + "Aktuell : "
							+ this.time.getActualdate().getDayOfWeek());
				diff = -3600;
			}
			// Liegt der Event in der Vergangenheit (diff<0), dann ist er aus
			// der
			// Arbeitsliste zu löschen, bzw. hier ersteinmal in der Löschliste
			// aufzunehmen
			if (diff < 0) {
				if (LOGGER)
					logger.trace(event.getName() + " ***LÖSCHVERMERK***");
				this.delTermine.add(event);
				continue;
			}
			// Ist Die Zeitdifferent == null, ist der Termincall auszulösen
			if (diff == 0
					&& (event.getPiT().getDayOfWeek() == Tage.NODAY || event
							.getPiT().getDayOfWeek().equals(
									this.time.getActualdate().getDayOfWeek()))) {
				// Event ausführen

				// System.err.println((lTime -
				// System.currentTimeMillis())+" bev termin()");
				// lTime = System.currentTimeMillis();
				event.termin();
				if(LOGGER) if(event.getExT().equals(this.testExT)){
					logger.trace(this.testExT+": "+this.testCounter);
				}
				// System.err.println((lTime -
				// System.currentTimeMillis())+" aft termin()");
				// lTime = System.currentTimeMillis();
		
				// Ist der Event ausgeführt, dann ist er aus der Arbeitsliste zu
				// löschen, bzw. hier ersteinmal in der Löschliste aufzunehmen
				this.delTermine.add(event);

				// Liegt der nächste Event innerhalb des timesteps, dann den
				// timestep auf den nächsten Event einstellen....
			} else if (diff < this.time.getTempstep() && diff > 0) {
				if (LOGGER)
					logger.trace("Neuer kleinster Timestep [" + diff
							+ "ms] Soll:" + this.time.getSollstep() + "ms");
				this.time.setTempstep(diff);
			}
			// für den nächsten Zeitsprung neu setzen, der wird durch ThreadTime
			// ausgelöst
			if (this.time.getTempstep() < this.time.getSollstep()) {
				if (LOGGER)
					logger.trace("Neuer Timestep [" + this.time.getTempstep()
							+ "ms]" + TAB
							+ (this.time.getTempstep() / 3600L / 1000L / 24L)
							+ " Tage");
				this.time.setTimerstep(this.time.getTempstep());
			}
			// Die Termine sind sortiert. Ist ein Event in der Zukunft,
			// sind alle weiteren Termine ebenfalls in der Zukunft. Weitere
			// Untersuchungen können abgebrochen werden.
			if (diff > 0) {
				break;
			}
		}

		

		// Die ausgeführten, vergangenen Termine aus dem Schedular löschen
		if (LOGGER)
			logger.trace("Mögliche Löschungen" + LFCR + this.showDeleteTermin()
					+ LFCR + this.showSchedular() + LFCR
					+ this.showNeuerTermin());
		this.time.getSchedularliste().removeAll(this.delTermine);
		this.delTermine.clear();
		// if(LOGGER)
		// logger.trace(this.showDel()+LFCR+this.showSchedular()+LFCR+
		// this.showNeuerTermin());

		// Gibt es neue Termine, dann werden dies jetzt hinzugefügt
		if (this.neuerTermin != null) {

			for (IEvent e : this.neuerTermin) {
				this.time.getSchedularliste().add(e);
			}
			// Da die neuen Termine eingearbeitet sind, kann die Liste wieder
			// zurück
			// gesetzt werden.
			this.neuerTermin.clear();
		}
		if(setInterrupt)this.tt.interrupt();
		// System.err.println((lTime -
		// System.currentTimeMillis())+" schedular");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tfossi.apolge.time.ITimesController#addSekunden()
	 */
	@Override
	public final void addSekunden() {

		final long ms = this.time.getTimerstep();
		if (LOGGER)
			logger.trace("Zeitschritt [" + ms + "ms / " + (ms / 24L / 3600000L)
					+ " Tage]");
		// Das aktuelle Datum
		this.time.getActualdate().addTime(ms);

		// long sekunden = (dt.shift.ms + ms) / 1000;
		// // Schrittweite Shift == 10. s.unten "/ 10];"
		// dt.shift = Shift.values()[(int) ((dt.shift.ms + ms) % 1000) / 10];
		//
		// // Zu addierende Minuten
		// long overMD = (dt.getSecond() + sekunden) / 60;
		//
		// // Rest == Sekunden addieren
		// dt.setSecond((int) (dt.getSecond() + sekunden) % 60);
		// // Zu addierende Stunden
		// int overH = (int) (dt.getMinute() + overMD) / 60;
		// // Rest == Minuten addieren
		// dt.setMinute((int) (dt.getMinute() + overMD) % 60);
		// // Zu addierende Tagen
		// overMD = (dt.getHour() + overH) / 24;
		// // Rest == Stunden addieren
		// dt.setHour((dt.getHour() + overH) % 24); // 59 + 05 % 60 = 4
		// boolean ddFlag = 0 < overMD ? true : false;
		// while (0 < overMD--) {
		//
		// dt.setDay(dt.getDay()+1);
		// int days = Monate.values()[dt.getMonth()].getDays(dt.getYear());
		// if (dt.getDay() > days) {
		// dt.setDay(1);
		// dt.setMonth(dt.getMonth()+1);
		// if (dt.getMonth() > 12) {
		// dt.setMonth(1);
		// dt.setYear(dt.getYear()+1);
		// }
		// }
		//
		// }
		// if (ddFlag)
		// getDoomsday(dt); // , true);
		//
		// // if(LOGGER) logger.trace("NEW: " + NTAB
		// // + Date2String(dt, DATE | TIME | DOW | SHIFT));
		// this.time.setActualdate(dt);
		// // Observer informieren
		// // this.model.gamemenu.screenUpdate(this.model.gamemenu);
		//
	}

	// /** @return Zeitschrittweite */
	// private final long getTimerstep() {
	// // Der Timerstep beschreibt die maximale Schrittweite zum nächsten
	// Event
	// // Der TimerstepSoll beschreibt die gewünschte maximale Schrittweite
	// // Der kleinere Wert zieht!
	// return this.time.getTimerstep() > this.time.getSollstep() ?
	// this.time.getSollstep()
	// : this.time.getTimerstep();
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see tfossi.apolge.time.thread.ITimesController#getStartdate()
	 */
	@Override
	public final CPiT getStartdate() {
		return this.time.getStartdate();
	}

	/* (non-Javadoc)
	 * @see tfossi.apolge.time.ITimesController#getEnddate()
	 */
	@Override
	public final CPiT getEnddate() {
		return this.time.getEnddate();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tfossi.apolge.time.ITimesController#getActualdate()
	 */
	@Override
	public CPiT getActualdate() {
		return this.time.getActualdate();
	}

	// ---- ThreadTime -------------------------------------------------------

	/** Der Timerthread */
	private final IThread tt;

	@Override
	public final void setSleep(final long sleep) {
		this.tt.setSleep(sleep);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tfossi.apolge.time.ITimesController#start()
	 */
	@Override
	public void start() {
		if (LOGGER)
			logger.trace("Timer ist gestartet.");
		this.tt.start();

	}

	@Override
	public void interruptafter(long millis) {
		this.tt.interruptafter(millis);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tfossi.apolge.time.ITimesController#timerstart()
	 */
	@Override
	public void schedularstart() {
		if (LOGGER)
			logger.trace("Schedular ist gestartet.");
		this.tt.restart();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tfossi.apolge.time.ITimesController#timerpause()
	 */
	@Override
	public void schedularpause() {
		if (LOGGER)
			logger.trace("Timerpause");
		this.tt.pause();
	}

	//
	// // ---- Daten laden/speichern
	// ------------------------------------------------
	//
	// // ---- Views
	// ----------------------------------------------------------------
	//
	// ---- Selbstverwaltung --------------------------------------------------
	/** serialVersionUID */
		private final static long serialVersionUID = VERSION;
	/** logger */
	private final static Logger logger = Logger.getLogger(TimesController.class
			.getPackage().getName()
			+ ".TimesController.class");

	/**
	 * Constructor mit Thread-Instanzierung JB
	 */
	public TimesController() {
		this.tt = new TimeThread(this);
	}
	// //
	// //// private String tstString;
	// ////
	// ////
	// ////
	// //// /**
	// //// * @return the tstString
	// //// */
	// //// public final String getTstString() {
	// //// return this.tstString;
	// //// }
	// ////
	// //// /**
	// //// * @param tstString the tstString to set
	// //// */
	// //// public final void setTstString(String tstString) {
	// //// this.tstString = tstString;
	// //// System.out.println("-------------------------"+tstString);
	// //// Event y = new YearlyEvent();
	// //// y.setName("TESTTEST----------------------------TESTTEST");
	// ////
	// //// assert this.testTermin!=null:"this.testTermin==null";
	// //// try {
	// //// this.testTermin.add(y);
	// //// } catch (Exception e) {
	// //// // TODO Auto-generated catch block
	// //// e.printStackTrace();
	// //// }
	// //// System.out.println("TESTTERMIN: "+this.testTermin);
	// ////// try {
	// ////// this.testTermin.add(new YearlyTestEvent("TEST"));
	// ////// } catch (DatumsException e) {
	// ////// TODO Auto-generated catch block
	// ////// e.printStackTrace();
	// ////// }
	// //// }
	// //
	// // /* (non-Javadoc)
	// // * @see tfossi.apolge.time.IExecuteEvent#execute()
	// // */
	// // @Override
	// // public Object[] execute() {
	// // // TODO Auto-generated method stub
	// // return new Object[]{"hi"};
	// // }
	// ////
	// //// private List<IEvent> testTermin = new TerminArray();
	// ////
	// //// /**
	// //// * @return the testTermin
	// //// */
	// //// public List<IEvent> getTestTermin() {
	// //// return this.testTermin;
	// //// }
	// ////
	// //// /**
	// //// * @param testTermin the testTermin to set
	// //// */
	// //// public final void setTestTermin(List<IEvent> testTermin) {
	// //// this.testTermin = testTermin;
	// //// }
}
