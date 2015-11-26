/**
 * PatternMaps.java
 * Branch master
 * APolGe
 * tfossi-team
 * licence GPLv3   
 */
package tfossi.apolge.common.scripting.vp.pm;

import static tfossi.apolge.common.constants.ConstValue.LOGGER;
import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import tfossi.apolge.common.constants.ConstValue;
import tfossi.apolge.common.scripting.vp.Function;
import tfossi.apolge.common.scripting.vp.IVP_ConstantMap;
import tfossi.apolge.common.scripting.vp.VP_ArrayTokenlist;
import tfossi.apolge.common.scripting.vp.VP_ConstantMap;

/**
 * Pattern sind Muster, die bestimmte Methoden, Operatoren charakterisieren und die entsprechende Methode über das Interface DoneMethod
 *   mit der Methode calculate mittels Reflektion ausführen können.   
 * 
 * @author tfossi
 * @version 18.08.2014
 * @modified -
 * @since Java 1.6
 */
public final class PatternMaps {

	/** Mathematische Konstanten, wie e, Pi usw. */
	public static final Pattern finalConstantsPattern;
	
	/**
	 * Pattern für alle bekannten Methoden. Notwendig beim Parsen, um die Methoden zu identifizieren.
	 */
	public static final Pattern finalFunctionPattern;
	
	/**
	 * operationValue default für Parser
	 */
	public static final Pattern finalOperationPattern;

	/** stringQuoteValue */
	public static final Pattern stringQuotePattern = Pattern.compile("\\$\\d+\\$");

	// ---- Operationen, Funktionen --------------------------------------------
	
	/** Eine Liste aller Konstanten aus ConstValue und Sonderfällen !... */
	public final static IVP_ConstantMap finalConstants = new VP_ConstantMap();

	/** Eine Liste aller Operationen */
	public final static Map<String, Operation> finalOperations = new HashMap<String, Operation>();

	/**
	 * Fügt der Operatorenliste eine weitere Operation hinzu
	 * 
	 * @param name
	 *            Name der Operation in "\op"
	 * @param operation
	 *            die Operation
	 */
	public final static void addOperation(String name, Operation operation) {
		assert !finalOperations.containsKey(name);
		finalOperations.put(name, operation);
	}

	/**
	 * Eine Liste aller Methoden die benutzt werden.
	 */
	public final static Map<String, List<FuncPType>> finalFunctions = new HashMap<String, List<FuncPType>>();
	
	/**
	 * Fügt der Funktionenliste eine weitere Funktion hinzu
	 * 
	 * @param name
	 *            Name der Funktion
	 * @param parameterType
	 *            Liste der Parametertypen der Funktion
	 * @param function
	 *            die Funktion selber
	 */
	public final static void addFunction(String name, Type[] parameterType,
			Function function) {
		
		// Parametertypen einer Methode
		FuncPType fpt = new FuncPType();
		
		// Parametertypen der Methode speichern
		fpt.parameterType = parameterType;
		
		// Beinhaltet die Aufrufmethode 'calculate' zu Ausfürhung der Methode
		fpt.function = function;

		
		if( ConstValue.noRegister.contains(name))
			return;
		
		if(LOGGER)
			if(!finalFunctions.containsKey(name))
				logger.info("Register: "+name);
		// Methode registrieren
		if (finalFunctions.containsKey(name))
			// bekannte Methode, neue Parametertypen des Methodenaufruf
			finalFunctions.get(name).add(fpt);
		else {
			// neue Methode mit Parametertypen des Methodenaufruf
			List<FuncPType> fptl = new ArrayList<FuncPType>();
			fptl.add(fpt);
			finalFunctions.put(name, fptl);
		}
	}



	// ---- Selbstverwaltung --------------------------------------------------

	/** serialVersionUID */
	@SuppressWarnings("unused")
	private final static long serialVersionUID = VERSION;

	/** logger */
	final static Logger logger = Logger.getLogger(PatternMaps.class
			.getPackage().getName() + ".PatternMaps");

	/**
	 * Alles statisch! Keine Objektzuordnung erlauben! 
	 * 
	 * @modified -
	 */
	private PatternMaps() {
	}

	/**
	 * Formatierte Ausgabe der PatternMaps
	 */
	@Override
	public final String toString() {
		assert false;
		String rc = new String();

		return rc;
	}

	// ---- Initiales Laden der Funktionen und Operationen --------------------		


	static {
		
		// Mathematikfunktionen erfassen		
		PMMath.implement();
	
		// Randomfunktionen erfassen
		PMRandom.implement();		
		
		// APO-funktionen erfassen	
		PMApo.implement();		
	
		// Vorzeichenfunktionen	
		PMSign.implement();			

		// Operationen	
		PMAlgebraOp.implement();		
		PMCompareOp.implement();		
		PMBoolOp.implement();		
	
		// Konstanten
		finalConstants.put("e", new VP_ArrayTokenlist<Double>(new Double(Math.E)));
		finalConstants.put("pi", new VP_ArrayTokenlist<Double>(new Double(Math.PI)));
		finalConstants.put("PI", new VP_ArrayTokenlist<Double>(new Double(Math.PI)));
		finalConstants.put("true", new VP_ArrayTokenlist<Boolean>(new Boolean(true)));
		finalConstants.put("false", new VP_ArrayTokenlist<Boolean>(new Boolean(false)));

		// Registrieren aller Funktionen, Operatoren und Konstanten 
		
		// ...Konstanten
		StringBuffer r = new StringBuffer();
		for (String key : finalConstants.keySet())
			r.append("(" + key + ")|");
		r.deleteCharAt(r.lastIndexOf("|"));
		finalConstantsPattern = Pattern.compile(r.toString());

		// ... Funktionen
		r = new StringBuffer();
		r.append("(");
		for (String key : finalFunctions.keySet())
			r.append(key + "|");
		r.deleteCharAt(r.lastIndexOf("|"));
		r.append(")");
		finalFunctionPattern = Pattern.compile(r.toString());

		// ... Operatoren
		r = new StringBuffer();
		r.append("(");
		for (String key : finalOperations.keySet())
			r.append("(" + key + ")" + "|");
		r.deleteCharAt(r.lastIndexOf("|"));
		r.append(")");
		finalOperationPattern = Pattern.compile(r.toString());

	}
}

