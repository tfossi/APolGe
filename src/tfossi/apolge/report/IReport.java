/**
 * IReport.java
 * Branch report
 * APolGe
 * tfossi-team
 * licence GPLv3  
 */
package tfossi.apolge.report;

import java.util.Queue;

/**
 
 * FIXME Reportkonzeption erarbeiten
 
 *
 * @author tfossi
 * @version 16.08.2014
 * @modified -
 * @since Java 1.6
 */
public interface IReport {
	// // Neues Format
	// public final static int NEW = 1;
	// // Format hinzufügen
	// public final static int ADD = 2;
	// // Format löschen
	// public final static int DEL = 3;
	/**
	 * Neues Berichtsformat initial einstellen. Ein Eintrag enhält folgende
	 * Informationen in der Reihenfolge:
	 * <ol>
	 * <li>enthält den Namen des Eintrags</li>
	 * <li>enthält den Datentyp des Eintrags</li>
	 * </ol>
	 * 
	 * @param colArr
	 *            Feld mit Berichtsformateinträgen
	 * @throws ClassNotFoundException -
	 */
	public void formatNew(final String[][] colArr)
			throws ClassNotFoundException;

	/**
	 * Berichtsformat initial einstellen. Ein Eintrag enhält folgende
	 * Informationen in der Reihenfolge:
	 * <ol>
	 * <li>enthält den Namen des Eintrags</li>
	 * <li>enthält den Datentyp des Eintrags</li>
	 * </ol>
	 * 
	 * @param col
	 *            ein Berichtsformat
	 * @throws ClassNotFoundException -
	 */
	public void formatNew(final String[] col) throws ClassNotFoundException;

	/**
	 * Berichtsformate hinzufügen. Ein Eintrag enhält folgende Informationen in
	 * der Reihenfolge:
	 * <ol>
	 * <li>enthält den Namen des Eintrags</li>
	 * <li>enthält den Datentyp des Eintrags</li>
	 * </ol>
	 * 
	 * @param colArr
	 *            Feld mit Berichtsformateinträgen
	 * @throws ClassNotFoundException -
	 */
	public void formatAdd(final String[][] colArr)
			throws ClassNotFoundException;

	/**
	 * Berichtsformat hinzufügen. Ein Eintrag enhält folgende Informationen in
	 * der Reihenfolge:
	 * <ol>
	 * <li>enthält den Namen des Eintrags</li>
	 * <li>enthält den Datentyp des Eintrags</li>
	 * </ol>
	 * 
	 * @param col
	 *            ein Berichtsformateintrag
	 * @throws ClassNotFoundException -
	 */
	public void formatAdd(final String[] col) throws ClassNotFoundException;

	/**
	 * @return die Spaltenformate
	 */
	public RColFormat[] formatGet();

	/**
	 * Liest die Daten in Reihenfolge der Spalten einer Zeile 
	 * 
	 * @param report
	 *            die Spaltenwerte
	 */
	public void reporting(Queue<Object> report);

	/**
	 * Liest die Daten in Reihenfolge der Spalten ein, mindestens eine Zeile
	 * oder ein Vielfaches davon
	 * 
	 * @param report
	 *            die Spaltenwerte
	 */
	public void reporting(Queue<Object>[] report);

	/**
	 * Fügt eine Berechnung der Spaltenwerte hinzu. 
	 * @param sop1
	 * 		Name der ersten Spalte
	 * @param sop2
	 * 		Name der zweiten Spalte
	 * @param sop
	 * 		Operation: *+/-|&^
	 */
	public void calc(final String sop1, final String sop2, final String sop);
	
	/**
	 * Auswertung mit ggfs. Nachkalkulation bei neuen Berechnungen
	 * @return -
	 */
	public String auswertung();

}
