/**
 * Initwork.java
 * Branch master
 * APolGe
 * tfossi-team
 * licence GPLv3
 */
package tfossi.apolge.common.scripting.p;

import static tfossi.apolge.common.constants.ConstValue.LOGGER;
import static tfossi.apolge.common.constants.ConstValue.NTAB;
import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

/**
 * Die Bedingungscodes durch Methode <i>WENN(a,b,c)</i> ablösen.<br>
 * Kann auch geschachtelt sein: <i>IF A THEN B IF C THEN D ELSE E</i>
 * 
 * @author tfossi
 * @version 01.08.2014
 * @modified -
 * @since Java 1.6
 */
public class Initwork {

	/**
	 * Die Bedingungscodes durch Methode <i>WENN(a,b,c)</i> ablösen.<br>
	 * Kann auch geschachtelt sein: <i>IF A THEN B IF C THEN D ELSE E</i>
	 * 
	 * @param resultbuffer
	 *            der Eingabebuffer
	 */
	public Initwork(StringBuffer resultbuffer) {
		if (LOGGER)
			logger.debug("Input: " + NTAB + resultbuffer);


		Matcher mifelse = ifelsePattern.matcher(resultbuffer);
		while (mifelse.find()) {
			if (LOGGER)
				logger.trace("ifelse: "
						+ NTAB
						+ resultbuffer.substring(mifelse.start(), mifelse.end()));

			// IF finden
			Matcher mif = ifPattern.matcher(resultbuffer.substring(
					mifelse.start(), mifelse.end()));
			int offset = 0;
			while (mif.find()) {
				if (LOGGER)
					logger.trace("if: "
							+ NTAB
							+ resultbuffer.substring(
									mifelse.start() + mif.start(),
									mifelse.end() + offset));

				// Setzte Abschlußklammer
				resultbuffer.insert(mifelse.end() - 1 + offset++, ")");
				if (LOGGER)
					logger.trace("if): "
							+ NTAB
							+ resultbuffer.substring(mifelse.start(),
									mifelse.end() + offset));

				// Lösche ELSE
				Matcher melse = elsePattern.matcher(resultbuffer.substring(
						mifelse.start(), mifelse.end() + offset));
				if (melse.find()) {
					// Lösche ELSE
					resultbuffer.delete(mifelse.start() + melse.start(),
							mifelse.start() + melse.end());
					offset -= (melse.end() - melse.start());
					if (LOGGER)
						logger.trace("else: "
								+ NTAB
								+ resultbuffer.substring(mifelse.start(),
										mifelse.end() + offset));
				}

				// Lösche THEN
				Matcher mthen = thenPattern.matcher(resultbuffer.substring(
						mifelse.start(), mifelse.end() + offset));
				while (mthen.find()) {
					// Lösche THEN
					resultbuffer.delete(mifelse.start() + mthen.start(),
							mifelse.start() + mthen.end());
					offset -= (mthen.end() - mthen.start());
					if (LOGGER)
						logger.trace("then: "
								+ NTAB
								+ resultbuffer.substring(mifelse.start(),
										mifelse.end() + offset));
					mthen.reset(resultbuffer.substring(mifelse.start(),
							mifelse.end() + offset));
				}

				// Tausche IF, gegen WENN(
				resultbuffer.replace(mifelse.start() + mif.start(),
						mifelse.start() + mif.end(), ",WENN(");
				offset += ",WENN(".length() - (mif.end() - mif.start());
				if (LOGGER)
					logger.trace("WENN: "
							+ NTAB
							+ resultbuffer.substring(mifelse.start(),
									mifelse.end() + offset));

				Matcher mequko = equkoPattern.matcher(resultbuffer.substring(
						mifelse.start(), mifelse.end() + offset));
				if (mequko.find()) {
					if (mequko.start() == 0) {
						resultbuffer.deleteCharAt(mifelse.start());
						offset--;
					}
					if (LOGGER)
						logger.trace("=,: "
								+ NTAB
								+ resultbuffer.substring(mifelse.start(),
										mifelse.end() + offset));

				}
				if (LOGGER)
					logger.trace(offset
							+ NTAB
							+ resultbuffer.substring(mifelse.start(),
									mifelse.end() + offset)
							+ NTAB
							+ "------------------------------------------------------------"
							+ NTAB + resultbuffer);
				mif.reset(resultbuffer.substring(mifelse.start(), mifelse.end()
						+ offset));
			}
			mifelse.reset();
		}
		if (LOGGER)
			logger.debug("Output: " + NTAB + resultbuffer);
	}


	/** ifelsePattern */
	private final static Pattern ifelsePattern = Pattern.compile("IF.*?ELSE.*?(;|}){1}?");

	/** ifPattern */
	private final static Pattern ifPattern = Pattern.compile(";?IF,");

	/** elsePattern */
	private final static Pattern elsePattern = Pattern.compile("(;|,)?ELSE");

	/** thenPattern */
	private final static Pattern thenPattern = Pattern.compile("THEN,");

	/** equkoPattern */
	private final static Pattern equkoPattern = Pattern.compile(",");
	
	
	// ---- Selbstverwaltung --------------------------------------------------

	/**
	 * serialVersionUID<br>
	 * Hint: Maybe <code>VERSION</code> does not exists at this moment
	 */
	@SuppressWarnings("unused")
	private final static long serialVersionUID = VERSION;

	/** logger */
	private final static Logger logger = Logger.getLogger(Initwork.class
			.getPackage().getName() + ".Initwork");

}
