/** 
 * PreLoad.java
 * Branch base
 * APolGe
 * tfossi-team
 * licence GPLv3 
 */
package tfossi.apolge.common.system;

import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;

import org.apache.log4j.Logger;

import tfossi.apolge.APolGe;

import tfossi.apolge.common.macrorecorder.IRecorder;

/**
 * PreLoad ist ein Thread, der beim Start von {@link APolGe} ausgeführt wird, um parallel
 * einige Daten während der Benutzeraktionen vorzuladen. Damit soll ein schneller
 * Programmstart erreicht werden.
 * <p>
 * <ul>
 * <b>Hinweis</b><br>
 * 
 * Da SWT im Thread der Applikation läuft, gibt es eine Kollision mit diesem Thread. Um
 * nicht ständig display.asyncExec() nutzen zu müssen, wird SWT in den Applikationen
 * geladen.
 * </ul>
 * 
 * @author tfossi
 * @version 11.08.2014
 * @modified -
 * @since Java 1.6
 */
public class PreLoad extends Thread {

	// --- Thread -------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public final void run() {
		System.out.println("Bitte warten. Es werden Daten geladen...");
		// Hinweis:
		// Da SWT im Thread der Applikation läuft, gibt es eine Kollision mit diesem
		// Thread.
		// Um nicht ständig display.asyncExec() nutzen zu müssen, wird SWT in den
		// Applikationen geladen.
		// @modified tfossi, 14.08.2014, Interface IRecorder
		 @SuppressWarnings("unused")
		IRecorder recorder = IRecorder.recorder;
		// Application.getInstance();
		System.out.println("...Fertig!");
	}

	/** Instanzieren und Starten von PreLoad */
	public PreLoad() {
		this.setName("PreLoad");
		this.start(); // Thread starten
	}

	// ---- Selbstverwaltung -----------------------------------------------------
	/** serialVersionUID */
	@SuppressWarnings("unused")
	private final static long serialVersionUID = VERSION;
	/** logger */
	@SuppressWarnings("unused")
	private final static Logger logger = Logger.getLogger(PreLoad.class.getPackage().getName());
}
