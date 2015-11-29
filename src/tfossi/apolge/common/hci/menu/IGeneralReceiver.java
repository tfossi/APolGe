/** 
 * IGeneralReceiver.java
 * Branch hci
 * APolGe
 * tfossi-team
 * licence GPLv3  
 */
package tfossi.apolge.common.hci.menu;

import tfossi.apolge.common.cmd.ACmd;



/**
 * Schnittstelle zum Receiver<br>
 * Enthält die Methoden, die der Receiver beherrschen muss. Diese Methoden werden durch
 * die Befehle (Cmd...) aufgerufen und sind daher mindestens im AMenu oder -
 * individualisiert- in den abgeleiteten Menu-Klassen implementiert:<br>
 * <ul>
 * <li>back</li>
 * <li>Rekorderfunktionen</li>
 * </ul>
 * 
 * @.pattern Command: receiver
 * @see ACmd
 * @author tfossi
 * @version 12.08.2014
 * @modified -
 * @since Java 1.6
 */
public interface IGeneralReceiver {
	/**
	 * zurück zum vorherigen Menü resp. Programmende, wenn es kein vorherigens Menü gibt.
	 */
	public void back();

	//
	//	
	// /** zum FirstView */
	// public void first();
	//	
	// /** Zeige die möglichen Befehle */
	// public void showCommands();
	//	
	 /*
	 * Inhaltsangabe der geladenen Macros ausgeben
	 *
	 * @param receiver
	 * null alle Makros anzeigen, ...Menu nur der passende
	 * @param pattern
	 * ist ein reguläres Muster zur Auswahl, welche Makros eingezeigt werden
	 * sollen
	 * @param details
	 * bei true wird auch der Inhalt des Makros angezeigt
	 * @param reset
	 * Makroliste erst wieder auf das ungefilterte Original zurücksetzen
	 * @return Ergebnisliste für SWT/Dialog
	 */
	 /**
	 * TODO Comment
	 * @modified - 
	 */
	public void /*String[][]*/ contentRecorder();
//	 String receiver, String pattern, boolean details,
//	 boolean reset);
	//
	// /**
	// * Detailausgabe eines Makros
	// *
	// * @param makro
	// * das Makro
	// * @return Ergebnisliste für SWT/Dialog
	// */
	// public String detailRecorder(String makro);
	//
	/**
	 * Schalte Recorder auf Aufnahme
	 * 
	 * @param macroname
	 *            Ist der Name des Makros
	 * @param comment
	 *            ist der Kommentar zum Makro
	 */
	public void recRecorder(String macroname, String comment);

	/** Schalte Recorder auf Stop */
	public void stopRecorder();

	/**
	 * Spiele den Recorder ab
	 * 
	 * @param value
	 *            [0] ist das Makro, das abgespielt werden soll<br>
	 *            Üblicherweise rufen die konkreten Klassen den
	 *            link #playRecorder(String)} auf, damit der Rekorder die
	 *            Klasseninformation erhält. Die Klasseninformation legt fest, welche
	 *            Makros überhaupt gestartet werden dürfen, da die meisten nur bei
	 *            bestimmten Klassen gültig sind und Ergebnisse liefern können.
	 */
	public void playRecorder(String ... value);
	//
	// /**
	// * Makro löschen
	// *
	// * @param macroname
	// * ist das zu löschende Makro
	// */
	// public void delRecorder(String macroname);
	//	
	// /** Alle Spielparameter laden */
	// public void loadall();
	//	
	// /** Alle Spielparameter speichern */
	// public void saveall();

	/**
	 * Programm beenden
	 */
	public void exit();

	/**
	 * Credits anzeigen
	 */
	public void credit();
//
//	/**
//	 * NetStatement (Command) abgeben:
//	 * @param cmdparameter Command mit String-Parametern
//	 * @param object Binäre Daten
//	 * @return Exception
//	 * @throws InvalidClassException
//	 * @throws SocketException
//	 * @throws IOException
//	 */
//	public Exception statement(String cmdparameter, Object object) throws InvalidClassException, SocketException, IOException;
//

}
