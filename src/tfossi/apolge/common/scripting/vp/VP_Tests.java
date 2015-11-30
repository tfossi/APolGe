/**
 * VPTests.java
 * Branch master
 * APolGe
 * tfossi-team
 * licence GPLv3  
 */
package tfossi.apolge.common.scripting.vp;

import static tfossi.apolge.common.constants.ConstValue.CLOSE;
import static tfossi.apolge.common.constants.ConstValue.KOMMA;
import static tfossi.apolge.common.constants.ConstValue.LFCR;
import static tfossi.apolge.common.constants.ConstValue.LOGGER;
import static tfossi.apolge.common.constants.ConstValue.NTAB;
import static tfossi.apolge.common.constants.ConstValue.OPEN;
import static tfossi.apolge.common.constants.ConstValue.extractWissDigittoken;
import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import tfossi.apolge.common.scripting.vp.pm.Operation;
import tfossi.apolge.common.scripting.vp.pm.PatternMaps;

/**
 * Übersetze Stringtoken und berechenbare Größe oder Steuerzeichen
 * 
 * @author tfossi
 * @version 16.08.2014
 * @modified -
 * @since Java 1.6
 */
final class VP_Tests {
	{
		if (LOGGER)
			System.out.println(this.getClass().getSimpleName() + " V"
					+ serialVersionUID);
	}

	/** seperatorValue */
	private static final Pattern seperatorValue = Pattern.compile(",");

	/** closeValue */
	private static final Pattern closeValue = Pattern.compile("\\)"); // {1}?");

	/** openValue */
	private static final Pattern openValue = Pattern.compile("\\(");
	
	/** wissValue */
	private static final Pattern wissValue = Pattern
			.compile(extractWissDigittoken);

	/** doubleValue */
	private static final Pattern doubleValue = Pattern
			.compile("([0-9]*\\.[0-9]*([eE][-+]?[0-9]+)?)$|"
					+ "([0-9]+[eE][-+]?[0-9]+)$"); // [-+]?
	
	/** floatValue */
	private static final Pattern floatValue = Pattern
			.compile("([0-9.]+([eE][-+]?[0-9]+)?)f$|"
					+ "([0-9]+[eE][-+]?[0-9]+)[fF]$");
	
	/** longValue */
	private static final Pattern longValue = Pattern.compile("[0-9]*L$");
	
	/** intValue */
	private static final Pattern intValue = Pattern.compile("^[0-9]+$");
	/** shortValue */
	private  static final Pattern shortValue = Pattern.compile("^[0-9]+s$");
	/** byteValue */
	private static final Pattern byteValue = Pattern.compile("^[0-9]+b$");
	// char
	// hex
	/** digitValue */
	public static final Pattern digitValue = Pattern.compile(doubleValue
			.pattern()
			+ "|"
			+ floatValue.pattern()
			+ "|"
			+ longValue.pattern()
			+ "|" + intValue.pattern()
			+ "|" + shortValue.pattern()
			+ "|" + byteValue.pattern()			
			+ "|" + wissValue.pattern());


	// ---- CAST-Functions ----------------------------------------------------
	 /**
	 * String in Double
	 * @param v Typvorgabe new Double()
	 * @param tk String
	 * @return Ergebnis
	 * @modified -
	 */
	 private static final double casten(Double v, String tk) {
	 return Double.valueOf(tk).doubleValue();
	 }
	
	 /**
	 * String in Float
	 * @param v Typvorgabe new Float()
	 * @param tk String
	 * @return Ergebnis
	 * @modified -
	 */
	 private static final float casten(Float v, String tk) {
	 return Float.valueOf(tk).floatValue();
	 }
	
	 /**
	 * String in Long
	 * @param v Typvorgabe new Long()
	 * @param tk String
	 * @return Ergebnis
	 * @modified -
	 */
	 private static final long casten(Long v, String tk) {
	 return Long.valueOf(tk.substring(0, tk.length() - 1)).longValue();
	 }
	
	 /**
	 * String in Integer
	 * @param v Typvorgabe new Integer()
	 * @param tk String
	 * @return Ergebnis
	 * @modified -
	 */
	 private static final int casten(Integer v, String tk) {
	 return Integer.valueOf(tk).intValue();
	 }
	
	 /**
	 * String in Byte
	 * @param v Typvorgabe new Byte()
	 * @param tk String
	 * @return Ergebnis
	 * @modified -
	 */
	 private static final byte casten(Byte v, String tk) {
	 return Byte.valueOf(tk.substring(0, tk.length() - 1)).byteValue();
	 }
	 
	 /**
	 * String in Short
	 * @param v Typvorgabe new Short()
	 * @param tk String
	 * @return Ergebnis
	 * @modified -
	 */
	 private  static final byte casten(Short v, String tk) {
	 return Short.valueOf(tk.substring(0, tk.length() - 1)).byteValue();
	 }
		
	 // ---- Tests ------------------------------------------------------------
	 
	/**
	 * Teste auf ',' und trage bei positiven Ergebnis KOMMA ein.
	 * 
	 * @param tokenliste
	 *            -
	 * @param ndx
	 *            -
	 * @param tk
	 *            -
	 * @param tkpre
	 *            -
	 * @return <code>true</code> and KOMMA or <code>false</code>
	 */
	static final boolean testKommaNChg(VP_Tokenlist<Object> tokenliste, int ndx, Object tk,
			Object tkpre) {
		if (!(tk instanceof CharSequence))
			return false;
		Matcher m = seperatorValue.matcher((CharSequence) tk);
		if (m.matches()) {
			if (LOGGER)
				logger.trace("Tausche [,] gegen KOMMA");
			tokenliste.remove(ndx);
			tokenliste.add(ndx, new Character(KOMMA));
			return true;
		}
		return false;
	}

	/** 
	 * Teste auf <code>)</code> und tausche das aktuelle Token bei positivem Testergebnis gegen <code>CLOSE</code> ein.
	 * 
	 * @param valuetokens
	 *       		Alle Token einer Valuezuweisung
	 * @param ndx
	 *       	Position des zu untersuchenden Token in der Valuezuweisung <code>valuetokens</code>
	 * @param tk
	 * 			Das aktuelle Token an der Position <code>ndx</code> der Valuezuweisung <code>valuetokens</code>
	 * @param tkpre
	 * 			Das Token vor dem aktuellen Token oder <code>null</code>, wenn aktuelle Token das Erste ist.
	 * @return <code>true</code> if tk is <code>CLOSE</code>
	 */
	static final boolean testCloseNChg(VP_Tokenlist<Object> valuetokens, int ndx,
			Object tk, Object tkpre) {
		if (!(tk instanceof CharSequence))
			return false;
		Matcher m = closeValue.matcher((CharSequence) tk);
		if (m.matches()) {
			if (LOGGER)
				logger.trace("Tausche ["+tk+"] gegen CLOSE");
			valuetokens.remove(ndx);
			valuetokens.add(ndx, CLOSE);
			return true;
		}
		return false;
	}

	/**
	 * Teste auf <code>(</code> und tausche das aktuelle Token bei positivem Testergebnis gegen <code>OPEN</code> ein.
	 * 
	 * @param valuetokens
	 *       		Alle Token einer Valuezuweisung
	 * @param ndx
	 *       	Position des zu untersuchenden Token in der Valuezuweisung <code>valuetokens</code>
	 * @param tk
	 * 			Das aktuelle Token an der Position <code>ndx</code> der Valuezuweisung <code>valuetokens</code>
	 * @param tkpre
	 * 			Das Token vor dem aktuellen Token oder <code>null</code>, wenn aktuelle Token das Erste ist.
	 * @return <code>true</code> if tk is <code>OPEN</code>
	 */
	static final boolean testOpenNChg(VP_Tokenlist<Object> valuetokens, int ndx,
			Object tk, Object tkpre) {
		if (!(tk instanceof CharSequence))
			return false;
		Matcher m = openValue.matcher((CharSequence) tk);
		if (m.matches()) {
			if (LOGGER)
				logger.trace("Tausche ["+tk+"] gegen OPEN");
			valuetokens.remove(ndx);
			valuetokens.add(ndx, OPEN);
			return true;
		}
		return false;
	}

	/**
	 * Teste auf quote
	 * 
	 * @param tokenliste
	 *       		Alle Token einer Valuezuweisung
	 * @param ndx
	 *       	Position des zu untersuchenden Token in der Valuezuweisung <code>valuetokens</code>
	 * @param tk
	 * 			Das aktuelle Token an der Position <code>ndx</code> der Valuezuweisung <code>valuetokens</code>
	 * @param tkpre
	 * 			Das Token vor dem aktuellen Token oder <code>null</code>, wenn aktuelle Token das Erste ist.
	 * @return <code>true</code> if tk is Quote
	 * @modified -
	 */
	final static boolean testQuote(VP_Tokenlist<?> tokenliste, int ndx,
			Object tk, Object tkpre) {
		if (!(tk instanceof CharSequence))
			return false;
		Matcher m = PatternMaps.stringQuotePattern.matcher((CharSequence) tk);
		if (m.matches()) {
			if (LOGGER)
				logger.trace("Es handelt sich um eine QUOTE: " + tk);
			return true;
		}
		return false;
	}

	/**
	 * Teste auf Constante und gegen in PM definierten Wert tauschen
	 * @param vp 
	 * 			ValueParser
	 * @param valuetokens 
	 *       		Alle Token einer Valuezuweisung
	 * @param ndx
	 *       	Position des zu untersuchenden Token in der Valuezuweisung <code>valuetokens</code>
	 * @param tk
	 * 			Das aktuelle Token an der Position <code>ndx</code> der Valuezuweisung <code>valuetokens</code>
	 * @param tkpre
	 * 			Das Token vor dem aktuellen Token oder <code>null</code>, wenn aktuelle Token das Erste ist.
	 * @return <code>true</code> if tk is Constante
	 * @modified -
	 */
	static final boolean testConstantsNChg(ValueParser vp,
			VP_Tokenlist<Object> valuetokens, int ndx, Object tk, Object tkpre) {

		if (!(tk instanceof CharSequence))
			return false;

		// Constanten einsetzen
		Matcher m = PatternMaps.finalConstantsPattern.matcher((CharSequence) tk);		
		if (m.matches()) {
			if (LOGGER)
				logger.trace("Tausche ["+tk+"] gegen ");
			valuetokens.remove(ndx);
			valuetokens.addAll(ndx, PatternMaps.finalConstants.get(tk));
			return true;
		}
		return false;
	}

	/**
	 * Teste, ob token eine Methode sein könnte
	 * 
	 * @param vp
	 *            -
	 * @param tokenliste
	 *            -
	 * @param ndx
	 *            -
	 * @param tk
	 *            -
	 * @param tkpre
	 *            -
	 * @return <i>true</i> Es handelt sich um eine Funktion 
	 * @modified -
	 */
	static final boolean testFunktional(ValueParser vp,
			VP_Tokenlist<Object> tokenliste, int ndx, Object tk, Object tkpre) {

		if (!(tk instanceof CharSequence))
			return false;
		
		Matcher m = PatternMaps.finalFunctionPattern.matcher((CharSequence) tk);
		
		if (m.matches()) {
			tokenliste.remove(ndx);
			if (PatternMaps.finalFunctions.containsKey("\\" + tk)) {
				if (LOGGER)
					logger.trace("Es handelt sich um eine R-FUNCTION [" + tk
							+ "]!");
				tokenliste.add(ndx, PatternMaps.finalFunctions.get("\\" + tk));
				return true;
			} 
			if (LOGGER)
				logger.trace("Ändere Token in L-FUNCTION [" + tk + "]");
			tokenliste.add(ndx, PatternMaps.finalFunctions.get(tk));
			return true;			
		}
		return false;
	}

	/**
	 * Testen, ob Token Operator ist
	 * 
	 * @param vp
	 *            -
	 * @param tokenliste
	 *            -
	 * @param ndx
	 *            -
	 * @param tk
	 *            -
	 * @param tkpre
	 *            -
	 * @param mode
	 *            -
	 * @return -
	 * @modified -
	 */
	static final boolean testOperableNChg(ValueParser vp,
			VP_Tokenlist<Object> tokenliste, int ndx, Object tk, Object tkpre,
			final byte mode) {

		if(LOGGER)logger.trace("Check "+tk
				+NTAB+tkpre);
		
		if (!(tk instanceof CharSequence))
			return false;

		// Operatoren haben vor sich was stehen: ndx>0
		if (ndx == 0) {
			return false;
		}
		
		// Methode der Operation eintragen
		// Sonderfall: Vorzeichen sind keine Operation, sondern Funktion
		Matcher mo = PatternMaps.finalOperationPattern.matcher((CharSequence) tk);

		if (!mo.matches()) {
			// Ist das Zeichen kein Operator, dann braucht nicht weiter
			// getestet werden.
			return false;
		} else if (tkpre instanceof Number) {
			// // Ist das vorheriges Zeichen eine Zahl, ist das Operabel

			String fOP = "\\";
			if( tk.toString().length()==2){
				fOP+= tk.toString().charAt(0)+"\\"+tk.toString().charAt(1);
			}else
				fOP+= tk;
			
			tokenliste.remove(ndx);
			tokenliste.add(ndx, PatternMaps.finalOperations.get(fOP)); //"\\" + tk));
			
				if (LOGGER)
					logger.trace("Tausche ["+tk+"] gegen OPERATION "+fOP);
				
				
			return true;
		} else if (tkpre instanceof Boolean) {
			// // Ist das vorheriges Zeichen eine Boolsche Zahl, ist das Operabel

			assert false;
			return false;
		} else if (tkpre instanceof String) {
			// Ist das vorheriges Zeichen ein String, ist das Operabel

			String fOP = "\\";
			if( tk.toString().length()==2){
				fOP+= tk.toString().charAt(0)+"\\"+tk.toString().charAt(1);
			}else
				fOP+= tk;
			
			tokenliste.remove(ndx);
			tokenliste.add(ndx, PatternMaps.finalOperations.get(fOP)); //"\\" + tk));
			
				if (LOGGER)
					logger.trace("Tausche ["+tk+"] gegen OPERATION "+fOP);
				
				
			return true;
		} else if (tkpre instanceof Operation) {
			// Ist das vorherige Zeichen eine Operation, dann ist es
			// nicht Operabel, sondern Funktion
			return false;

		} else if (tkpre.equals(OPEN)) {

			// Ist das vorherige Zeichen eine Klammer auf, dann ist
			// es nicht Operabel, sondern Funktion

			assert false;
			return false;
		} else if (tkpre.equals(CLOSE)) {
			String fOP = "\\";
			if( tk.toString().length()==2){
				fOP+= tk.toString().charAt(0)+"\\"+tk.toString().charAt(1);
			}else
				fOP+= tk;
			tokenliste.remove(ndx);
			tokenliste.add(ndx, PatternMaps.finalOperations.get(fOP));
			if (LOGGER)
				logger.trace("Tausche ["+tk+"] gegen OPERATION "+fOP);
			
			
			return true;
		} else if (!(tkpre instanceof CharSequence)) {
			return false;
		} else {			
			assert false : tkpre + LFCR + tk+LFCR+tkpre.getClass();
		}

		return false;
	}


	/**
	 * Teste auf <code>[Zahl]</code> und tausche das aktuelle Token bei positivem Testergebnis gegen typenrichtige <code>Number</code> ein.<br>
	 * 
	 * @param valuetokens
	 *       		Alle Token einer Valuezuweisung
	 * @param ndx
	 *       	Position des zu untersuchenden Token in der Valuezuweisung <code>valuetokens</code>
	 * @param tk
	 * 			Das aktuelle Token an der Position <code>ndx</code> der Valuezuweisung <code>valuetokens</code>
	 * @param tkpre
	 * 			Das Token vor dem aktuellen Token oder <code>null</code>, wenn aktuelle Token das Erste ist.
	 * @return Zahlenwert mit richtigem Typen.
	 */
	static final boolean testNumberNChg(VP_Tokenlist<Object> valuetokens, int ndx,
			Object tk, Object tkpre) {
		if (!(tk instanceof CharSequence))
			return false;
		CharSequence ctk = (CharSequence) tk;
		if (ctk.length() == 1 && ctk.charAt(0) == '.')
			return false;
		Matcher m = digitValue.matcher(ctk);
		if (m.matches()) {
			// Es handelt sich um eine ZAHL!
			valuetokens.remove(ndx);
			if (doubleValue.matcher(ctk).matches()) {

				valuetokens.add(ndx,
						new Double(casten(new Double(0), (String) tk)));
				if (LOGGER)
					logger.trace("Type changed to DOUBLE: " + tk);
			} else if (floatValue.matcher(ctk).matches()) {
				valuetokens.add(ndx,
						new Float(casten(new Float(0), (String) tk)));
				if (LOGGER)
					logger.trace("Type changed to FLOAT: " + tk);

			} else if (longValue.matcher(ctk).matches()) {

				valuetokens.add(ndx, new Long(casten(new Long(0), (String) tk)));
				if (LOGGER)
					logger.trace("Type changed to LONG: " + valuetokens.get(ndx));
			} else if (intValue.matcher(ctk).matches()) {

				valuetokens.add(ndx,
						new Integer(casten(new Integer(0), (String) tk)));
				if (LOGGER)
					logger.trace("Type changed to INTEGER: " + tk);
				// Byte
			} else if (byteValue.matcher(ctk).matches()) {

				valuetokens.add(ndx,
						new Byte(casten(new Byte((byte)0), (String) tk)));
				if (LOGGER)
					logger.trace("Type changed to BYTE: " + tk);
				// Byte
			} else if (shortValue.matcher(ctk).matches()) {

				valuetokens.add(ndx,
						new Short(casten(new Short((short) 0), (String) tk)));
				if (LOGGER)
					logger.trace("Type changed to SHORT: " + tk);
				// Byte
			} else if (wissValue.matcher(ctk).matches()) {
				CharSequence cs = (CharSequence) tk;
				if (LOGGER)
					logger.trace("WISSDIGIT PASST..." + tk);
				assert false;
				if (cs.charAt(cs.length() - 1) == 'f') {
					valuetokens.add(ndx,
							new Float(casten(new Float(0), (String) tk)));
				} else {
					valuetokens.add(ndx,
							new Double(casten(new Double(0), (String) tk)));
				}
			} else {
				if (LOGGER)
					logger.trace("KEIN DIGIT PASST..." + tk);
				if (LOGGER)
					logger.trace(new Boolean(wissValue.matcher(ctk).matches()));
				assert false;
			}
			return true;
		}
		return false;
	}

	// ---- Selbstverwaltung --------------------------------------------------
	/** serialVersionUID */
	private final static long serialVersionUID = VERSION;

	/** logger */
	private final static Logger logger = Logger.getLogger(VP_Tests.class
			.getPackage().getName()+".VP_Tests");
	
}
