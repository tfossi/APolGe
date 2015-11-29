/**
 * TimeThread.java
 * Branch time
 * APolGe
 * tfossi-team
 * licence GPLv3  
 */
package tfossi.apolge.time;

import static tfossi.apolge.common.constants.ConstValueExtension.*;

import org.apache.log4j.Logger;

/**
 * Thread zur Zeitsteuerung. Taktet jede Sekunde den
 * {@linkplain TimesController#schedular()} und die Addition des Zeitschritts
 * 
 * @author tfossi
 * @version 17.08.2014
 * @modified -
 * @since Java 1.6
 */
public class TimeThread extends Thread implements IThread {

	{	if (LOGGER)
		System.out.println(this.getClass().getSimpleName()+" V" + serialVersionUID);
	}
	/** Der Controller des Timers */
	private final ITimes4Thread timesController;

	/** Flag, das den Timer pausiert */
	private boolean pause = true;

	/**
	 * Zeitscheibe pro Takt in ms
	 */
	private long sleep = 250;

	/*
	 * (non-Javadoc)
	 * 
	 * @see tfossi.apolge.time.IThread#setSleep(long)
	 */
	@Override
	public void setSleep(long sleepTime) {
		this.sleep = sleepTime;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Thread#start()
	 */
	@Override
	public synchronized void start() {
		super.start();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Thread#interrupt()
	 */
	@Override
	public void interrupt() {
		super.interrupt();
	}

	/**
	 * Timer pausieren. Auf die Pause reagiert der Run()! Der Thread pausiert
	 * tatsächlich nicht!
	 */
	@Override
	public void pause() {
		this.pause = true;
	}

	/** Timer nach Pause wieder starten */
	@Override
	public void restart() {
		this.pause = false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tfossi.apolge.time.IThread#joinit(long)
	 */
	@Override
	public void interruptafter(long millis) {
		if(LOGGER) logger.info("interruptafter " + millis);
		try {
			this.join(millis);
			this.interrupt();
			while (this.isAlive())
			
			logger.info("Systemabbruch nach "+millis+"ms");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		if(LOGGER) logger.trace("TIMER läuft");

		// Bis zur Threadunterbrechung durchlaufen
		while (!isInterrupted()) {
			long time = System.currentTimeMillis();
			if (!this.pause) {

				
				// Untersuche mit Hilfe des Schedulars nach Zeitereignissen, die
				// ausgelöst werden sollen				
				this.timesController.schedular();
			
				// Füge den Zeitschritt eines Threadlaufes hinzu
				this.timesController.addSekunden();
			} else
				if(LOGGER) logger.trace("*** PAUSE ***");
			long time2 = System.currentTimeMillis() - time;
			try {
				// Der Timer wartet immer eine Sekunde. Welche (Spiel-)Zeit
				// dabei
				// vergeht wird in der Schrittweite festgelegt
				if(!TIMESTEPOFF) 	Thread.sleep(this.sleep-time2);
				if(TIMESTEPOFF) 	Thread.sleep(1L);
			} catch (java.lang.IllegalArgumentException e) {
				this.sleep += 25L;
			} catch (InterruptedException e) {
//				e.printStackTrace();
				logger.fatal("InterruptedException");
				interrupt();
			}
			long time3 = System.currentTimeMillis() - time;
			if(LOGGER) logger.info("TurnAround: "+time3+"ms Schedular: "+time2+"ms Sleep:"+this.sleep+"ms");
		}
		if(LOGGER) logger.trace("Exit");
	}

	// ---- Selbstverwaltung --------------------------------------------------
	
	/** serialVersionUID */
	private final static long serialVersionUID = VERSION;
	/** logger */
	private final static Logger logger = Logger.getLogger(TimeThread.class
			.getPackage().getName()+".TimeThread.class");
 
	/**
	 * Constructor wird ausschliesslich vom TimesController aufgerufen und
	 * instanziert
	 * 
	 * @param timesControllerIn
	 *            ist der Controller
	 */
	public TimeThread(ITimes4Thread timesControllerIn) {
		super();
		this.timesController = timesControllerIn;
	}
}
