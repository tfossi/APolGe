/**
 * VPTests.java
 * Branch scripting
 * APolGe
 * tfossi-team
 * licence GPLv3  
 */
package tfossi.apolge.common.scripting.vp;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import static tfossi.apolge.common.constants.ConstValue.*;
import static tfossi.apolge.common.constants.ConstValueExtension.*;

import tfossi.apolge.common.scripting.vp.ValueParser;

/**
 * TODO Comment
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
	public static final Pattern seperatorValue = Pattern.compile(",");

	/** closeValue */
	public static final Pattern closeValue = Pattern.compile("\\)"); // {1}?");

	/** openValue */
	public static final Pattern openValue = Pattern.compile("\\(");
	
	/** initialValue */
	public static final Pattern initialValue = Pattern.compile("INIT:");
	
	/** flowValue */
	public static final Pattern flowValue = Pattern.compile("FLOW:");

	/** booleanValue */
	public static final Pattern booleanValue = Pattern
			.compile("(true)|(false)");
	
	/** wissValue */
	public static final Pattern wissValue = Pattern
			.compile(extractWissDigittoken);

	/** doubleValue */
	public static final Pattern doubleValue = Pattern
			.compile("([0-9]*\\.[0-9]*([eE][-+]?[0-9]+)?)$|"
					+ "([0-9]+[eE][-+]?[0-9]+)$"); // [-+]?
	
	/** floatValue */
	public static final Pattern floatValue = Pattern
			.compile("([0-9.]+([eE][-+]?[0-9]+)?)f$|"
					+ "([0-9]+[eE][-+]?[0-9]+)[fF]$");
	
	/** longValue */
	public static final Pattern longValue = Pattern.compile("[0-9]*L$");
	
	/** intValue */
	public static final Pattern intValue = Pattern.compile("^[0-9]+$");
	// short
	public static final Pattern shortValue = Pattern.compile("^[0-9]+s$");
	// byte
	public static final Pattern byteValue = Pattern.compile("^[0-9]+b$");
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

	/** Pattern für INDIVIDUALS */
	public static final Pattern indiValue = Pattern.compile("^#.*");

	/** adressValue */
	public static final Pattern adressValue = Pattern.compile("^@.*");

	/** stringValue */
	public static final Pattern stringValue = Pattern.compile("^\\$.*\\$$");

	// ---- CAST-Functions ----------------------------------------------------
	 /**
	 * TODO Comment
	 * @param v -
	 * @param tk -
	 * @return -
	 * @modified -
	 */
	 public static final double casten(Double v, String tk) {
	 return Double.valueOf(tk).doubleValue();
	 }
	
	 /**
	 * TODO Comment
	 * @param v -
	 * @param tk -
	 * @return -
	 * @modified -
	 */
	 public static final float casten(Float v, String tk) {
	 return Float.valueOf(tk).floatValue();
	 }
	
	 /**
	 * TODO Comment
	 * @param v -
	 * @param tk -
	 * @return -
	 * @modified -
	 */
	 public static final long casten(Long v, String tk) {
	 return Long.valueOf(tk.substring(0, tk.length() - 1)).longValue();
	 }
	
	 /**
	 * TODO Comment
	 * @param v -
	 * @param tk -
	 * @return -
	 * @modified -
	 */
	 public static final int casten(Integer v, String tk) {
	 return Integer.valueOf(tk).intValue();
	 }
	
	 /**
	 * TODO Comment
	 * @param v -
	 * @param tk -
	 * @return -
	 * @modified -
	 */
	 public static final byte casten(Byte v, String tk) {
	 return Byte.valueOf(tk.substring(0, tk.length() - 1)).byteValue();
	 }
	 /**
	 * TODO Comment
	 * @param v -
	 * @param tk -
	 * @return -
	 * @modified -
	 */
	 public static final byte casten(Short v, String tk) {
	 return Short.valueOf(tk.substring(0, tk.length() - 1)).byteValue();
	 }
	
	 /**
	 * TODO Comment
	 * @param v -
	 * @param tk -
	 * @return -
	 * @modified -
	 */
	 public static final boolean casten(Boolean v, String tk) {
	 return Boolean.valueOf(tk).booleanValue();
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
	static final boolean testKommaNChg(VP_Tokenlist tokenliste, int ndx, Object tk,
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
	static final boolean testCloseNChg(VP_Tokenlist valuetokens, int ndx,
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
	static final boolean testOpenNChg(VP_Tokenlist valuetokens, int ndx,
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
	
	static final boolean testInitialNChg(VP_Tokenlist valuetokens, int ndx,
			Object tk, Object tkpre) {
		if (!(tk instanceof CharSequence))
			return false;
		Matcher m = initialValue.matcher((CharSequence) tk);
		if (m.matches()) {
			if (LOGGER)
				logger.trace("Tausche ["+tk+"] gegen INITIAL");
			valuetokens.remove(ndx);
			valuetokens.add(ndx, new Character(INITIAL));
			return true;
		}
		return false;
	}

	static final boolean testFlowNChg(VP_Tokenlist valuetokens, int ndx,
			Object tk, Object tkpre) {
		if (!(tk instanceof CharSequence))
			return false;
		Matcher m = flowValue.matcher((CharSequence) tk);
		if (m.matches()) {
			if (LOGGER)
				logger.trace("Tausche ["+tk+"] gegen FLOW");
			valuetokens.remove(ndx);
			valuetokens.add(ndx, new Character(FLOW));
			return true;
		}
		return false;
	}
	/**
	 * TODO Comment
	 * 
	 * @param tokenliste
	 *            -
	 * @param ndx
	 *            -
	 * @param tk
	 *            -
	 * @param tkpre
	 *            -
	 * @return -
	 * @modified -
	 */
	final static boolean testQuote(VP_Tokenlist tokenliste, int ndx,
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

//	/**
//	 * Teste auf Boolean und trage bei positiven Ergebnis Typ BOOLEAN ein.
//	 * 
//	 * @param vp
//	 *            -
//	 * @param tokenliste
//	 *            -
//	 * @param ndx
//	 *            -
//	 * @param tk
//	 *            -
//	 * @param tkpre
//	 *            -
//	 * @return <code>true</code> and BOOLEAN or <code>false</code>
//	 */
//	static final boolean testNumberNChg(VP_Tokenlist valuetokens, int ndx,
//			Object tk, Object tkpre) {
////	static final boolean testBooleanNChg(ValueParser vp,
////			VP_Tokenlist valuetokens, int ndx, Object tk, Object tkpre) {
//		if (!(tk instanceof CharSequence))
//			return false;
//		CharSequence ctk = (CharSequence) tk;
//		if (ctk.length() == 1 && ctk.charAt(0) == '.')
//			return false;
//		Matcher m = booleanValue.matcher(ctk);
//		if (m.matches()) {
//			// Es handelt sich um ein BOOLEAN!
//			valuetokens.remove(ndx);			
//			valuetokens.add(ndx, PatternMaps.finalConstants.get(tk)); //..booleans.get(tk));
//			if (LOGGER)
//				logger.trace("Type changed to BOOLEAN: " + tk);
//			return true;
//		}
//		return false;
//	}

	/** 
	 * Teste, ob Token ein Marker vom Typ <code>definitions</code> ist.
	 * @param pm ????
	 * @param tk
	 * 			Das aktuelle Token an der Position <code>ndx</code> der Valuezuweisung <code>valuetokens</code>
	 * @return <code>true</code> if tk is in <code>definitions</code>
	 */
//	static final boolean testSVAR(PatternMaps pm, Object tk) {
//		if (!(tk instanceof CharSequence))
//			return false;
//		CharSequence cstk = (CharSequence) tk;
//		Matcher m = pm.svarPattern.matcher(cstk);
//		return m.matches() && pm.svar.containsKey(tk); 
//	}

	/** 
	 * Teste, ob Token ein Marker vom Typ <code>individuals</code> ist.
	 * @param pm ????
	 * @param tk
	 * 			Das aktuelle Token an der Position <code>ndx</code> der Valuezuweisung <code>valuetokens</code>
	 * @return <code>true</code> if tk is in <code>individuals</code>
	 */
//	static final boolean testIndividuals(PatternMaps pm, Object tk) {
//		if (!(tk instanceof CharSequence))
//			return false;
//		CharSequence cstk = (CharSequence) tk;
//		Matcher m = pm.indiPattern.matcher(cstk);
//		return m.matches() && pm.indi.containsKey(tk);
//
//	}

	/** 
	 * Teste, ob Token ein Marker vom Typ <code>tempores</code> ist.
	 * @param pm ????
	 * @param tk
	 * 			Das aktuelle Token an der Position <code>ndx</code> der Valuezuweisung <code>valuetokens</code>
	 * @return <code>true</code> if tk is in <code>tempores</code>
	 */
//	static final boolean testAUXV(PatternMaps pm, Object tk) {
//		if (!(tk instanceof CharSequence))
//			return false;
//		CharSequence cstk = (CharSequence) tk;
//		Matcher m = pm.auxvPattern.matcher(cstk);
//		return m.matches() && pm.auxv.containsKey(tk);
//	}

	/**
	 * TODO Comment
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
	 * @return -
	 * @modified -
	 */
//	static final boolean SCONNChg(ValueParser vp,
//			VP_Tokenlist tokenliste, int ndx, Object tk, Object tkpre) {
//		assert false;
//
//		// Constanten einsetzen
//		Matcher m = vp.patternmaps.sconPattern.matcher((CharSequence) tk);
//		if (m.matches() && vp.patternmaps.scon.containsKey(tk)) {
//			VP_Tokenlist vpt = vp.patternmaps.scon.get(tk);
//			if (LOGGER)
//				logger.trace("Es handelt sich um eine SPEZCONSTANTE " + tk
//						+ "= " + vpt + "!");
//
//
//			tokenliste.remove(ndx);
//
//			assert vpt != null;
//			List<Object> c = vpt;
//
//			tokenliste.addAll(ndx, c);
//
//			if (LOGGER)
//				logger.trace("Trage aus " + tk + " " + c + " ein!");
//
//			return true;
//		}
//		return false;
//	}

	/**
	 * TODO Comment
	 * 
	 * @param vp
	 *            -
	 * @param valuetokens
	 *            -
	 * @param ndx
	 *            -
	 * @param tk
	 *            -
	 * @param tkpre
	 *            -
	 * @return -
	 * @modified -
	 */
	static final boolean testConstantsNChg(ValueParser vp,
			VP_Tokenlist valuetokens, int ndx, Object tk, Object tkpre) {

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
	 * Teste, ob Token ein Marker vom Typ <code>constants</code> ist.
	 * @param pm ????
	 * @param tk
	 * 			Das aktuelle Token an der Position <code>ndx</code> der Valuezuweisung <code>valuetokens</code>
	 * @return <code>true</code> if tk is in <code>constants</code>
	 */
//	static final boolean testConstants(PatternMaps pm, Object tk) {
//		assert false;
//		return false;
//	}

	/**
	 * TODO Comment
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
	 * @return -
	 * @modified -
	 */
//	static final boolean SCON(ValueParser vp,
//			VP_Tokenlist tokenliste, int ndx, Object tk, Object tkpre) {
//		assert false;
//
//
//		// Constanten einsetzen
//		Matcher m = vp.patternmaps.sconPattern.matcher((CharSequence) tk);
//		if (m.matches() && vp.patternmaps.scon.containsKey(tk)) {
//			VP_Tokenlist vpt = vp.patternmaps.scon.get(tk);
//
//			tokenliste.remove(ndx);
//
//			assert vpt != null;
//			List<Object> c = vpt;
//
//			tokenliste.addAll(ndx, c);
//
//			if (LOGGER)
//				logger.trace("Trage für SPEZCONSTANTE [" + tk + "] den token " + c + " ein!");
//
//			return true;
//		}
//		return false;
//	}
	/** 
	 * Teste, ob Token ein Marker vom Typ <code>scon</code> ist.
	 * @param pm ????
	 * @param tk
	 * 			Das aktuelle Token an der Position <code>ndx</code> der Valuezuweisung <code>valuetokens</code>
	 * @return <code>true</code> if tk is in <code>scon</code>
	 */
//	static final boolean SCON(PatternMaps pm, Object tk) {
//		return pm.scon.containsKey(tk);
//	}

	/** 
	 * Teste, ob Token ein Marker vom Typ <code>addresses</code> ist.
	 * @param pm  ????
	 * @param tk
	 * 			Das aktuelle Token an der Position <code>ndx</code> der Valuezuweisung <code>valuetokens</code>
	 * @return <code>true</code> if tk is in <code>addresses</code>
	 */
//	static final boolean testAdresse(PatternMaps pm, Object tk) {
//		if (!(tk instanceof CharSequence))
//			return false;
//		CharSequence cstk = (CharSequence) tk;
//		Matcher m = pm.addressesValue.matcher(cstk);
//		return m.matches() && pm.addresses.containsKey(tk);
//	}

	/** 
	 * Teste, ob Token ein Marker vom Typ <code>preaddresses</code> ist.
	 * @param pm  ????
	 * @param tk
	 * 			Das aktuelle Token an der Position <code>ndx</code> der Valuezuweisung <code>valuetokens</code>
	 * @return <code>true</code> if tk is in <code>preaddresses</code>
	 */
//	static final boolean testPreAdresse(PatternMaps pm, Object tk) {
//		return pm.addresses.containsKey(tk);
//	}
	
	/**
	 * TODO Comment
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
	 * @return -
	 * @modified -
	 */
//	static final boolean testMatrix1(ValueParser vp,
//			List<Object> tokenliste, int ndx, Object tk, Object tkpre) {
//		
//		if (!(tk instanceof CharSequence))
//			return false;
//		// 1. Test: PASS2 Funktion
//		Matcher m = vp.patternmaps.functionValue.matcher((CharSequence) tk);
//		if (m.matches()) {
//			tokenliste.remove(ndx);
//			if (vp.patternmaps.functions.containsKey("\\" + tk)) {
//				if (LOGGER)
//					logger.trace("Es handelt sich um eine R-FUNCTION [" + tk
//							+ "]!");
//				tokenliste.add(ndx, vp.patternmaps.functions.get("\\" + tk));
//			} else {
//
//				if (LOGGER)
//					logger.trace("Ändere Token in L-FUNCTION [" + tk + "]");
//				tokenliste.add(ndx, vp.patternmaps.functions.get(tk));
//			}
//			return true;
//		}
//		return false;
//	}
	
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
			VP_Tokenlist tokenliste, int ndx, Object tk, Object tkpre) {

//		return testFunktionalPass1(vp, tokenliste, ndx, tk, tkpre);
//	static final boolean testFunktionalPass1(ValueParser vp,
//			List<Object> tokenliste, int ndx, Object tk, Object tkpre) {
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
//			return PatternMaps.finalFunctions.get(tk).get(0).function.twoPass();			
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
			VP_Tokenlist tokenliste, int ndx, Object tk, Object tkpre,
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
//		} else if (tkpre instanceof PreAddress) {
//			// Ist das vorherige Zeichen eine Klammer zu, dann ist es
//			// Operabel
//			assert false;

		} else if (!(tkpre instanceof CharSequence)) {
			return false;
//		} else if (vp.patternmaps.constantsPattern.matcher((CharSequence) tkpre)
//				.matches()) {
//	//
//		} else if (vp.patternmaps.svarPattern.matcher((CharSequence) tkpre)
//				.matches()) {
//			// Ist das vorherige Zeichen eine Klammer zu, dann ist es
//			// Operabel
//		} else if (vp.patternmaps.indiPattern.matcher((CharSequence) tkpre)
//				.matches()) {
//			// Ist das vorherige Zeichen eine Klammer zu, dann ist es
//			// Operabel
//		} else if (booleanValue.matcher((CharSequence) tkpre).matches()) {
//			// Ist das vorherige Zeichen eine Klammer zu, dann ist es
//			// Operabel
//		} else if (seperatorValue.matcher((CharSequence) tkpre).matches()) {
//			// Ist das vorherige Zeichen ein SEPARATOR, dann ist es
//			// Operabel
//		} else if (vp.patternmaps.auxvPattern.matcher((CharSequence) tkpre).matches()) {
//			// Ist das vorherige Zeichen ein SEPARATOR, dann ist es
//			// Operabel
//		} else if (vp.patternmaps.sconPattern.matcher((CharSequence) tkpre).matches()) {
//			// Ist das vorherige Zeichen ein SEPARATOR, dann ist es
//			// Operabel
		} else {
			// Ist irgendetwas anderes, was nicht vorgesehen ist.
			// Vergessen?
//			if (LOGGER)
//				logger.error(LFCR + "Number?: " + (tkpre instanceof Number)
//						+ LFCR + "TK: " + tk + LFCR + " other TKPRE: " + tkpre
//						+ LFCR + tkpre.getClass() + LFCR + vp.patternmaps.indi+LFCR+vp.patternmaps.constantsPattern+LFCR+
//						vp.patternmaps.constantsPattern.matcher((CharSequence) tkpre)
//						.matches()		);
//			if (LOGGER)
//				logger.error("INDI:"+LOGTAB+vp.patternmaps.indiPattern.pattern());
//			if (LOGGER)
//				logger.error("SVAR:"+LOGTAB+vp.patternmaps.svarPattern.pattern());
//			if (LOGGER)
//				logger.error("SCON:"+LOGTAB+vp.patternmaps.sconPattern.pattern());
//			if (LOGGER)
//				logger.error("AUXV:"+LOGTAB+vp.patternmaps.auxvPattern.pattern());
			assert false : tkpre + LFCR + tk+LFCR+tkpre.getClass();
		}
		
//

//		String tks = (String)tk;
//		if(tks.length()==1)tks="\\" +tks;
//		else tks = "\\" +tks.charAt(0)+"\\" +tks.charAt(1);
//		if (vp.patternmaps.operations.containsKey(tks)) {
//			if (LOGGER)
//				logger.trace("Es handelt sich um die OPERATION: "
//						+ vp.patternmaps.operations.get(tks));
//			tokenliste.remove(ndx);
//			tokenliste.add(ndx, vp.patternmaps.operations.get(tks));
//			return true;
//		}
//
		return false;
	}

//	static final boolean testMatrix(ValueParser vp,
//			VP_Tokenlist tokenliste, int ndx, Object tk, Object tkpre) {
//
//		return testMatrix1(vp, tokenliste, ndx, tk, tkpre);
//
//	}
//	/**
//	 * TODO Comment
//	 * 
//	 * @param vp
//	 *            -
//	 * @param tokenliste
//	 *            -
//	 * @param ndx
//	 *            -
//	 * @param tk
//	 *            -
//	 * @param tkpre
//	 *            -
//	 * @return -
//	 * @modified -
//	 */
//	static final boolean testFunktional(ValueParser vp,
//			VP_Tokenlist tokenliste, int ndx, Object tk, Object tkpre) {
//
//		return testFunktionalPass1(vp, tokenliste, ndx, tk, tkpre);
//
//	}

	/**
	 * Teste auf <code>[Zahl]</code> und tausche das aktuelle Token bei positivem Testergebnis gegen typenrichtige <code>Number</code> ein.<br>
	 * 
	 * TODO Transfer boolean
	 * TODO Transfer wissdigit
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
