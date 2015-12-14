/**
 * PreAddress.java
 * Branch scripting
 * APolGe
 * tfossi-team
 * licence GPLv3  
 */
package tfossi.apolge.common.scripting;

import static tfossi.apolge.common.constants.ConstValue.LFCR;
import static tfossi.apolge.common.constants.ConstValue.LOGGER;
import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import tfossi.apolge.common.scripting.p.ParseException;

/**
 * Klasse, um Adressangaben beim und nach dem Parsen typesieren zu können.<br>
 * 				Guide	CORE
 * DGL:	Clan	OK		Welcher? 					
 * SGG:	Const	OK		Welche?
 * STT: Name	OK		Welcher?
 * DAT: ---				----
 * 
 * aus untergeordnetem DGL (Personen im Clan)
 * 		Menge @SUB.DGL.SET.: Auswahl 0..n
 * 		@SUB.dglW0.set1 		
 * aus übergeordnetem DGL (Person Nachname)
 * 		@SUPER.SGG.STT
 * 		@SUPER.sggX.name
 * aus selben DGL (Person Hunger)
 * 		@.SGG.STT
 * 		@.sggY.hunger
 * 		@..hunger
 * aus gleichen DGL (Person Heirat)
 * 		Menge aus @SAME..SET: Auswahl 0..n
 * 		@SAME..setZ
 * aus anderem DGL (Person Irgentwas)
 * 		Menge aus @OTHER.DGL.SET: Auswahl 0..n
 * 		@OTHER.dglA.setB
 * 
 *  @SUB.dglW0.set1 	
 *  @SUPER.sggX.name
 *  @.sggY.hunger
 * 	@..hunger
 *  @SAME..setZ
 *  @OTHER.dglA.setB
 * 					
 * SET											
 * Alle Personen 
 * in gleicher Nation			@.SGG1.NATION == Nation 
 * AND Unverheiratet   			@.SGG1.STATUS==ledig
 * AND Anderes Geschlecht       @.SGG1.STATUS==W
 * AND Etwa gleiches Alter		@.SGG1.ALTER == (-5 +5)
 * AND Anderer Clan				@.SGG1.CLAN <> Clan						
 * 
 * @author tfossi
 * @version 01.08.2014
 * @modified 27.01.2015
 * @since Java 1.6
 */
public class PreAddress {
	{
//		if (LOGGER)
//			System.out.println(this.getClass().getSimpleName()+" V" + serialVersionUID);
	}
	/** Die drei Ebenen einer Adresse: Codierung SUB, SUPER, SAME, OTHER */
	public final String code;
	/** Die drei Ebenen einer Adresse: DGL oder SGG */
	public final String dglsgg;
	/** Die drei Ebenen einer Adresse: Y0 von STATE oder Menge */
	public final String stateset;

	/**
	 * @param token
	 * 			der Adresstoken in Form @[A].[B].C
	 * @throws ParseException
	 * DOC
	 */
	@SuppressWarnings("boxing")
	public PreAddress(final String token) throws ParseException {
		Pattern dot = Pattern.compile("@(\\w*)\\.(\\w*)\\.(\\w)");
		Matcher m = dot.matcher(token);
		if (LOGGER)
			logger.trace(token);
		if (LOGGER)
			logger.trace(m.groupCount());
		if (m.find()) {
			this.code = m.group(1);
			if (LOGGER)
				logger.trace("CODE " + this.code);
			this.dglsgg = m.group(2);
			if (LOGGER)
				logger.trace("DGLSGG " + this.dglsgg);
			this.stateset = m.group(3);
			if (LOGGER)
				logger.trace("STATESET " + this.stateset);

		} else {
			throw new ParseException("Fehler in der Adresse: " + token + LFCR
					+ m.groupCount());
		}
		if ((this.stateset.isEmpty() && this.dglsgg.isEmpty() && this.code
				.isEmpty())
				|| ((this.stateset.isEmpty() || this.dglsgg.isEmpty()) && !this.code.isEmpty())) {
			// § Bei gesetzter Klasse muss die Kette bis igl definiert sein!
			// § Eine null-Adresse ist nicht erlaubt.
			throw new ParseException("Fehler in der Adresse: " + token + LFCR
					+ this.code + LFCR + this.dglsgg + LFCR + this.stateset);
		}

		// FIXME Check auf Inhalt OK

	}

	/** @return Liefert umgesetzte PreAdresse als String */
	@Override
	public final String toString() {
		return this.code + "." + this.dglsgg + "." + this.stateset;
	}

	// ---- Selbstverwaltung --------------------------------------------------

	/** serialVersionUID */
	@SuppressWarnings("unused")
	private final static long serialVersionUID = VERSION;
		/** logger */
	private final static Logger logger = Logger.getLogger(PreAddress.class
			.getPackage().getName() + ".PreAddress.class");

}
