/**
 * IRecorder.java
 * Branch macrorecorder
 * APolGe
 * tfossi-team
 * licence GPLv3  
 */
package tfossi.apolge.common.macrorecorder;

import tfossi.apolge.common.cmd.ICmd;
import tfossi.apolge.common.hci.menu.IGeneralReceiver;

/**
 * TODO Comment
 * 
 * @author tfossi
 * @version 12.08.2014
 * @modified -
 * @since Java 1.6
 */

public interface IRecorder {

	/** recorder */
	public static IRecorder recorder = Recorder.itsInstance;

	/**
	 * Laden eines Befehl und Pointer auf den ersten Befehl stellen.
	 * 
	 * @param macroname
	 *            ist Name des Makros, dass zum Abspielen eingeladen werden soll
	 * @return <code>true</code>, wenn die Liste der Makros den Namen enthält
	 */
	public abstract boolean loadRecord(String macroname);

	/**
	 * Löschen eines Makros
	 * 
	 * @param macroname
	 *            ist das zu löschende Makro
	 */
	public abstract void delRecord(String macroname);

	/** Abspielen eines Records. Es wird das Flag gessetzt. */
	public abstract void setPlayON();

	/**
	 * Zeigt an, wenn ein Makro abgespielt wird
	 * 
	 * @return true, wenn gerade abgespielt wird
	 * 
	 */
	public abstract boolean isPlay();

	/**
	 * TODO Comment
	 * 
	 * @return -
	 * @modified -
	 */
	public abstract ICmd getPlay();

	/**
	 * Aufnehmen eines Records.
	 * 
	 * @param macroname
	 *            ist Name des Makros, dass zum Abspielen eingeladen werden soll
	 * @param comment
	 *            ist eine Beschreibung zum Makro
	 * @param ownerIn
	 *            ist die Klasse, in der das Makro gestartet wird
	 */
	public abstract void pushRecRecorder(String macroname, String comment,
			Class<? extends IGeneralReceiver> ownerIn);

	/**
	 * TODO Comment
	 * 
	 * @return -
	 * @modified -
	 */
	public abstract boolean isRecord();

	/**
	 * Nimmt einen einzelnen Befehl auf
	 * 
	 * @param ac
	 *            ist der zu speichernde Befehl
	 * @modified -
	 */
	public abstract void setRecord(ICmd ac);

	
	 /**
	 * @param macroname -
	 * @return liefert einen formatierten String mit Daten des aktuellen
	 Makros
	 */
	public abstract String chkMakro(String macroname);

	/**
	 * Beenden der Aufnahme / Abspielen des Records
	 * 
	 * @.post this.record ist OFF
	 * @.post Wird eine Aufzeichnung beendet, dann wird das Makro noch
	 *        geschrieben
	 * @since 0.00.020
	 */
	public abstract void stop();

	/**
	 * Liefert eine Zusammenstellung der gültigen Macros
	 * 
	 * @param receiver
	 *            schränkt die Zusammenstellung auf diese Klasse ein, wenn es
	 *            nicht <code>null</code> ist;
	 * @param pattern
	 *            ist ein reguläres Muster zur Auswahl, welche Makros eingezeigt
	 *            werden sollen
	 * @param details
	 *            bei true wird auch der Inhalt des Makros angezeigt
	 * @return liefert einen String mit einem Macro pro Zeile
	 */
	public abstract String[] contentln(String receiver, String pattern,
			boolean details);

	/**
	 * Zurücksetzen des Filters auf die originale macroList Gefilterte Gruppe
	 * der Makros wieder mit allen Makros auffüllen
	 */
	public abstract void contentReset();

	/**
	 * Liefert Details zu einem Makro
	 * 
	 * @param key
	 *            der gesuchte Makro param cnslout ist true, wenn eine Ausgabe
	 *            auf Console gewünscht ist.
	 * @return liefert den Detailstring zum Makro
	 */
	public abstract String detail(String key);

	/** @return Liefert SimpleName() der Klasse */
	@Override
	public abstract String toString();

}