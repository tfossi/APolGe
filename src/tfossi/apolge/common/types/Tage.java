/**
 * Tage.java
 * Branch master
 * APolGe
 * tfossi-team
 * licence GPLv3 
 */
package tfossi.apolge.common.types;

/**
 * Konstanten zum Thema "Tage"
 * 
 * @author tfossi
 * @version 1.0
 * @modified -
 * @since java version "1.6.0_0"
 */
public enum Tage {
	/** Ist undefinierter Tag */
	UNDEF(null),
	/** Sonntag */
	SONNTAG("Sonntag"),
	/** Montag */
	MONTAG("Montag"),
	/** Dienstag */
	DIENSTAG("Dienstag"),
	/** Mittwoch */
	MITTWOCH("Mittwoch"),
	/** Donnerstag */
	DONNERSTAG("Donnerstag"),
	/** Freitag */
	FREITAG("Freitag"),
	/** Sonnabend */
	SONNABEND("Sonnabend"),
	/** Tag ist nicht festgelegt */
	NODAY("");

	/** Name des Tags */
	private String name;

	/**
	 * Constructor
	 * 
	 * @param nameIn
	 *            Name des Tags
	 */
	private Tage(String nameIn) {
		this.name = nameIn;
	}

	/** @return Name des Wochentags */
	public String getName() {
		return this.name;
	}

	/** @return Ordnungsnummer (0..7) */
	public int getOrdinal() {
		return this.ordinal();
	}
}
