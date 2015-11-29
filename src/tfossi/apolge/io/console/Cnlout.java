/** 
 * Cnlout.java
 * Branch io
 * APolGe
 * tfossi-team
 * licence GPLv3 
 */
package tfossi.apolge.io.console;
import static tfossi.apolge.common.constants.ConstValue.DOT;
import static tfossi.apolge.common.constants.ConstValue.LEER;
import static tfossi.apolge.common.constants.ConstValue.LFCR;
import static tfossi.apolge.common.constants.ConstValue.MINUS;
import static tfossi.apolge.common.constants.ConstValue.PIPE;
import static tfossi.apolge.common.constants.ConstValue.PLUS;
import static tfossi.apolge.common.constants.ConstValue.PROZENT;
import static tfossi.apolge.common.constants.ConstValue.S;
import static tfossi.apolge.common.constants.ConstValue.TAB;
import static tfossi.apolge.common.constants.ConstValueExtension.BREITE;
import static tfossi.apolge.common.constants.ConstValueExtension.MAXBREITE;

import java.util.Arrays;

/**
 * Ausgaben auf Console
 * 
 * @see Key
 * @author tfossi
 * @version 11.08.2014
 * @modified -
 * @since Java 1.6
 */
public class Cnlout  {

	/**
	 * poutln gibt Strings auf dem 'out'-Channel mit <LF><CR> aus.
	 * 
	 * @param str
	 *            Auszugebender String
	 */
	static final void poutln(final String str) {
		System.out.println("" + str);
	}

	/** toutFrameRow gibt den vierspaltigen Tabellenkopf/-fuß aus */
	public static final void toutFrameRow() {
		poutln(PLUS + Spacer(BREITE, MINUS) + PLUS + Spacer(BREITE, MINUS) + PLUS + PLUS
				+ Spacer(BREITE, MINUS) + PLUS + Spacer(BREITE, MINUS) + PLUS);
	}

	/**
	 * toutLeftText gibt die linke Tabellenhälfte mit einer Überschrift aus
	 * 
	 * @param txt
	 *            Text der Überschrift
	 */
	public static final void toutLeftText(final String txt) {
		String txtout = new String(txt.substring(0, Math.min(BREITE, txt.length())));
		poutln("" + PLUS + txtout
				+ Spacer(BREITE - txtout.length(), MINUS) + PLUS + Spacer(BREITE, MINUS) + PLUS
				+ PLUS + Spacer(BREITE, MINUS) + PLUS + Spacer(BREITE, MINUS) + PLUS);
	}

	/**
	 * tout4 gibt die vierspaltige Tabelle aus
	 * 
	 * @param txt1
	 *            Spalte 1
	 * @param txt2
	 *            Spalte 2
	 * @param txt3
	 *            Spalte 3
	 * @param txt4
	 *            Spalte 4
	 * @param trenner
	 *            Trennzeichen zwischen den Spalten
	 */
	public static final void tout4(final String txt1, final String txt2, final String txt3, final String txt4, final char trenner) {
		String txt1out = new String(txt1.substring(0, Math.min(BREITE, txt1.length())));
		String txt2out = new String(txt2.substring(0, Math.min(BREITE, txt2.length())));
		String txt3out = new String(txt3.substring(0, Math.min(BREITE, txt3.length())));
		String txt4out = new String(txt4.substring(0, Math.min(BREITE, txt4.length())));
		poutln(PIPE + txt1out + Spacer(BREITE - txt1out.length(), LEER) + trenner + txt2out
				+ Spacer(BREITE - txt2out.length(), LEER) + trenner + trenner + txt3out
				+ Spacer(BREITE - txt3out.length(), LEER) + trenner + txt4out
				+ Spacer(BREITE - txt4out.length(), LEER) + PIPE);
	}

	/**
	 * touttxt gibt in der Tabelle einen Text aus. CRLF wird umgebrochen in Leerzeichen,
	 * TAB in vier Leerzeichen.<br>
	 * Ist der Text länger als die verfügbare Breite, wird der Text automatisch
	 * umgebrochen.
	 * 
	 * @param roughtxt
	 *            Text der Überschrift
	 */
	public static final  void touttxt(final String roughtxt) {
		assert roughtxt != null;
		// Zwischen Anfang und erstem CR
		int start = 0;
		int end = roughtxt.indexOf(LFCR, 0);
		while (true) {
			String absatz = null; // Arbeitsstring
			// Absatz herausarbeiten
			if (end < start)
				absatz = roughtxt.substring(start);
			else
				absatz = roughtxt.substring(start, end);

			absatz = absatz.replace(LFCR, " ");
			absatz = absatz.replace(TAB, "    ");

			// Leerzeichen entfernen
			if (absatz.length() > 1)
				absatz = absatz.trim();

			// if(absatz.length()<=MAXBREITE) {
			// poutln(""
			// + PIPE
			// + LEER
			// + LEER
			// + String.format("" + PROZENT + MINUS + BREITE * 4 + DOT + BREITE * 4 + S,
			// absatz + LEER + LEER + PIPE));
			// assert false:absatz;
			// break;
			// }

			for (int i = 0; i < absatz.length();) {
				int von = i;
				int bis = Math.min(i + MAXBREITE, absatz.length());
				if (bis < absatz.length()) {
					bis = absatz.substring(von, bis).lastIndexOf(" ");
					i += (bis + 1);
					bis += von;
				} else {
					i += MAXBREITE;
				}
				poutln(""
						+ PIPE
						+ LEER
						+ LEER
						+ String.format("" + PROZENT + MINUS + BREITE * 4 + DOT + BREITE * 4 + S,
								absatz.substring(von, bis)) + LEER + LEER + PIPE);
			}
			if (end <= start)
				break;
			// Zwischen aktuellem CR und nächstem CR
			start = end;
			end = roughtxt.indexOf(LFCR, end + 1);
		}
	}

	/**
	 * Spacer liefert einen String der Länge 'len' gefüllt mit den Zeichen 'fill'.
	 * 
	 * @param len
	 *            Länge des Strings
	 * @param fill
	 *            Füllzeichen
	 * @return der String.
	 */
	private static final String Spacer(final int len, final char fill) {
		if (len < 0)
			return null;
		char[] cs = new char[len];
		Arrays.fill(cs, fill);
		return new String(cs);
	}

}
