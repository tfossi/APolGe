/**
 * DoMAT.java
 * Branch master
 * APolGe
 * tfossi-team
 * licence GPLv3
 */
package tfossi.apolge.common.scripting.p;

import static tfossi.apolge.common.constants.ConstValue.LOGGER;
import static tfossi.apolge.common.constants.ConstValue.NTAB;
import static tfossi.apolge.common.constants.ConstValue.operator;
import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

/**
 * 
 * In der Wertezuweisung ist [ ] eine Matrix/Vektor Aus [ a b ...] wird ein
 * double ArrayParameter double[] darr( a,b, ...) Aus [ A ][ B ]... wird ein
 * double[] ArrayParameter
 * 
 * @author tfossi
 * @version 01.08.2014
 * @modified -
 * @since Java 1.6
 */
public class DoMAT {

	/**
	 * In der Wertezuweisung ist [ ] eine Matrix/Vektor Aus [ a b ...] wird ein
	 * double ArrayParameter double[] darr( a,b, ...) Aus [ A ][ B ]... wird ein
	 * double[] ArrayParameter
	 * 
	 * @param resultbuffer
	 *            der Eingabebuffer
	 */
	@SuppressWarnings("unused")
	public DoMAT(StringBuffer resultbuffer) {

		if (LOGGER)
			logger.debug("Input: " + NTAB + resultbuffer);

		// Die Analyse startet nach der Zuweisung '='
		int start = 0;

		// Aktueller Pointer
		int sbstart = start;

		// Stelle sbstart auf die Position der Zuweisung ein
		Matcher mequal = equal.matcher(resultbuffer);
		if (mequal.find())
			sbstart = mequal.start();
		else
			assert true : "Kein == gefunden.";

		while (doAdr(resultbuffer))
			if (LOGGER)
				logger.trace("doAdr" + NTAB + resultbuffer);

		// Die Matrizen umsetzen:
		// [[ ... ]] in MATRIX([ ... ])
		// [[ ... ]]T in MATRIXT([ ... ])
		while (doMatrix(resultbuffer))
			if (LOGGER)
				logger.trace("doMatrix" + NTAB + resultbuffer);

		// Die Vektoren umsetzen:
		// [ ... ] in VEKTOR(...,...,...)
		// [ ... ]T in VEKTORT(...,...,...)
		while (doVektor(resultbuffer))
			if (LOGGER)
				logger.trace("doVektor" + NTAB + resultbuffer);

		if (LOGGER)
			logger.debug("Output: " + NTAB + resultbuffer);

	}

	/**
	 * Suche Vektoreinträge und tausche gegen Vektormethode
	 * 
	 * @param resultbuffer
	 *            der Eingabebuffer
	 * @return <i>true</i>, wenn noch ein austausch stattgefunden hat
	 * @modified -
	 */
	@SuppressWarnings("static-method")
	private final boolean doVektor(StringBuffer resultbuffer) {

		if (LOGGER)
			logger.trace("Suche [ Eintrag:" + NTAB + resultbuffer);

		Matcher mdopen = dopen.matcher(resultbuffer);

		int start = 0;
		while (mdopen.find(start)) {
			if (LOGGER)
				logger.trace("Find [: " + NTAB
						+ resultbuffer.substring(mdopen.start()) + NTAB
						+ mdopen.group());
			start = mdopen.start();
			
			// Suche ] Eintrag
			Matcher mdTclose = dTclose.matcher(resultbuffer.substring(mdopen
					.start()));
			Matcher mdclose = dclose.matcher(resultbuffer.substring(mdopen
					.start()));

			mdTclose.find();
			mdclose.find();

			if (LOGGER)
				logger.trace("Find ]: "
						+ resultbuffer.substring(mdopen.start()) + NTAB
						+ mdTclose.lookingAt() + NTAB + mdclose.lookingAt());

			if ((mdTclose.lookingAt() && mdclose.lookingAt() && mdTclose.end() <= mdclose
					.end() + 1)
					|| (mdTclose.lookingAt() && !mdclose.lookingAt())) {
				if (LOGGER)
					logger.trace("Find ]T: "
							+ NTAB
							+ resultbuffer.substring(mdopen.start(),
									mdopen.start() + mdTclose.end()) + NTAB
							+ mdTclose.group());
				resultbuffer.replace(mdopen.start() + mdTclose.end() - 2,
						mdopen.start() + mdTclose.end(), ")");
				resultbuffer.replace(mdopen.start(), mdopen.start() + 1,
						"VEKTORT(");

			} else if (mdclose.lookingAt()) {
				if (LOGGER)
					logger.trace("Find ]: "
							+ NTAB
							+ resultbuffer.substring(mdopen.start(),
									mdopen.start() + mdclose.end()) + NTAB
							+ mdclose.group());
				resultbuffer.replace(mdopen.start() + mdclose.end() - 1,
						mdopen.start() + mdclose.end(), ")");
				resultbuffer.replace(mdopen.start(), mdopen.start() + 1,
						"VEKTOR(");
			} else {
				assert false : "Syntax-Error: Kein ]-B1" + NTAB
						+ mdclose.matches() + NTAB + mdTclose.matches();
			}
			return true;
		}
		return false;
	}

	/**
	 * Suche Matrixeinträge und tausche gegen Matrixmethode
	 * 
	 * @param resultbuffer
	 *            der Eingabebuffer
	 * @return <i>true</i>, wenn noch ein austausch stattgefunden hat
	 * @modified -
	 */
	@SuppressWarnings("static-method")
	private final boolean doMatrix(StringBuffer resultbuffer) {

		// Suche [[ Eintrag
		Matcher mdopen = dMopen.matcher(resultbuffer);
		if (mdopen.find()) {
			//Suche ]] Eintrag
			Matcher mdclose = dMclose.matcher(resultbuffer);
			if (mdclose.find()) {
				int Bx = mdclose.start();
				// Suche n?chsten [[ Eintrag
				Matcher mdopenA2 = dMopen.matcher(resultbuffer.substring(mdopen.end()));

				
				if (mdopenA2.find() && mdopenA2.start() < Bx) {
					assert false : "Noch einbauen";
				} else {
					// Ist letztes Zeichen ein T?
					Matcher mdT = dT.matcher(resultbuffer.substring(mdclose.end()));
					if (mdT.find()) {

						// ]]T --> ])
						resultbuffer.replace(mdclose.start(),
								mdclose.end() + mdT.end(), "])");
						// [[ --> MATRIX([
						resultbuffer.replace(mdopen.start(),
								mdopen.end(), "MATRIXT([");

					} else {
						// ]] --> ])
						resultbuffer.replace(mdclose.start(),
								mdclose.end(), "])");
						// [[ --> MATRIX([
						resultbuffer.replace(mdopen.start(),
								mdopen.end(), "MATRIX([");
					}
				}
			} else {
				assert false : "Syntax-Error: Kein ]]";
			}
			return true;
		}
		return false;
	}

	/**
	 * Suche Addresseinträge und tausche gegen Addressmethode
	 * @param resultbuffer
	 *            der Eingabebuffer
	 * @return <i>true</i>, wenn noch ein austausch stattgefunden hat
	 * @modified - 
	 */
	@SuppressWarnings("static-method")
	private final boolean doAdr(StringBuffer resultbuffer) {
		
		if (LOGGER)
			logger.trace("Adresse" + NTAB + resultbuffer + NTAB + resultbuffer);

		Matcher mdopen = dAopen.matcher(resultbuffer);

		while (mdopen.find()) {
			if (LOGGER)
				logger.trace("Adresse Start " + mdopen.start() + NTAB
						+ resultbuffer.substring(mdopen.start()));

			Matcher mdclose = dAclose.matcher(resultbuffer.substring(mdopen.start()));
			if (mdclose.find()) {
				if (LOGGER)
					logger.trace("Adresse End " + mdclose.start() + NTAB
							+ resultbuffer.substring(mdclose.start()));
				if (LOGGER)
					logger.trace("Adresse End "
							+ mdclose.end()
							+ NTAB
							+ resultbuffer.substring(mdopen.start(), mdopen.start() + mdclose.end()));

				String rp = resultbuffer.substring(mdopen.start(),
						mdopen.start() + mdclose.end()).replaceAll(
						"\\.", ",");
				rp = rp.replaceAll("@", "ADR(") + ")";
				resultbuffer.replace(mdopen.start(), mdopen.start()
						+ mdclose.end(), rp);
				if (LOGGER)
					logger.trace("XChange" + NTAB + resultbuffer);
			}

		}
		return false;
	}

	/** Zuweisung '=' */
	private final static Pattern equal = Pattern.compile("\\="); // {1}?");

	/** dopen [ kein a-zA-Z0-9]= */
	private final static Pattern dopen = Pattern
			.compile("\\[(?!(\\p{Alnum}*?\\]))");
	/** dclose */
	private final static Pattern dclose = Pattern.compile("\\[.*?\\](?!=)");
	/** dTclose */
	private final static Pattern dTclose = Pattern.compile("\\[.*?\\]T(?!=)");
	/** dT */
	private final static Pattern dT = Pattern.compile("^ *T");
	/** dMopen */
	private final static Pattern dMopen = Pattern.compile("\\[ *\\[");
	/** dMclose */
	private final static Pattern dMclose = Pattern.compile("\\] *\\]");
	/** dAopen */
	private final static Pattern dAopen = Pattern.compile("\\@");
	/** dAclose */
	private final static Pattern dAclose =
	Pattern.compile("(\\p{Alpha}|\\.)*(?=[" + operator + ";,])");
	// ---- Selbstverwaltung --------------------------------------------------

	/**
	 * serialVersionUID<br>
	 * Hint: Maybe <code>VERSION</code> does not exists at this moment
	 */
	@SuppressWarnings("unused")
	private final static long serialVersionUID = VERSION;

	/** logger */
	private final static Logger logger = Logger.getLogger(DoMAT.class
			.getPackage().getName() + ".DoMAT");

}
