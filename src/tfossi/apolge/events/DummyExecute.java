/**
 * DummyExecute.java
 * Branch master
 * APolGe
 * tfossi-team
 * licence GPLv3  
 */
package tfossi.apolge.events;

import static tfossi.apolge.common.constants.ConstValue.LFCR;
import static tfossi.apolge.common.constants.ConstValue.LOGGER;
import static tfossi.apolge.common.constants.ConstValue.LOGTAB;
import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;

import java.util.LinkedList;
import java.util.Queue;

import org.apache.log4j.Logger;

import tfossi.apolge.data.core.IDataRoot;
import tfossi.apolge.time.IExecuteTermin;
import tfossi.apolge.time.IThread;
/**
 * Ist ein temporärer Platzhalter für echte ausführende Termine in der Entwicklung
 * 
 * @author tfossi
 * @version 17.08.2014
 * @modified -
 * @since Java 1.6
 */
public class DummyExecute implements IExecuteTermin{
	{
		if (LOGGER)
			System.out.println(this.getClass().getSimpleName()+" V" + serialVersionUID);
	}
	/** game */
	@SuppressWarnings("unused")
	private final IDataRoot game; 
	/** tt */
	private final IThread tt;
	/** report */
	Queue <Object> report = new LinkedList<Object>();
	/* (non-Javadoc)
	 * @see tfossi.apolge.time.IExecuteTermin#execute()
	 */
	@Override
	public Queue <Object> execute() {
		
		if(this.tt!=null)
			this.tt.interrupt();
		this.report.add(LFCR+LOGTAB+"Nachrichten vom Dummy"+LFCR);
		return this.report;
//		return new String[]{this.getClass().getSimpleName().toString()};
	}
	/**
	 * TODO Comment
	 * @modified -
	 */
	public DummyExecute(){		
		this.tt = null;
		this.game=null;
	}

	/**
	 * TODO Comment
	 * @param game -
	 * @param tt -
	 * @modified -
	 */
	public DummyExecute(final IDataRoot game, IThread tt){
		this.game = game;
		this.tt = tt;
	}
//	/* (non-Javadoc)
//	 * @see tfossi.apolge.time.IExecuteTermin#nullexecute()
//	 */
//	@Override
//	public void nullexecute() {
//		return new String[]{this.getClass().getSimpleName().toString()};
//	}
	/**
	 * TODO Comment
	 * @param game -
	 * @modified -
	 */
	public DummyExecute(final IDataRoot game){
		this.game = game;
		this.tt = null;
	}

	// ---- Selbstverwaltung --------------------------------------------------
	/** serialVersionUID */
	private final static long serialVersionUID = VERSION;
	/** Loggerinstanz */
	@SuppressWarnings("unused")
	private final static Logger logger = Logger.getLogger(DummyExecute.class
			.getPackage().getName());
}

