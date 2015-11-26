/**
 * PMBoolOp.java
 * Branch scripting
 * APolGe
 * tfossi-team
 * licence GPLv3   
 */
package tfossi.apolge.common.scripting.vp.pm;

import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;

import org.apache.log4j.Logger;

import Jama.Matrix;

/**
 * Implementierung Bool Operationen
 *
 * @author tfossi
 * @version 26.11.2015
 * @modified -
 * @since Java 1.6
 */
public class PMBoolOp {


	/**
	 * Implementierung Bool Operationen
	 * @modified - 
	 */
	public static void implement(){
		
		PatternMaps.addOperation("\\|\\|", new BoolOperation() {
			@Override
			public int getPriority() {
				return 4;
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * tfossi.apolge.common.scripting.Operation#calculate(java.lang.
			 * Object, java.lang.Object)
			 */
			@SuppressWarnings("cast")
			@Override
			public final Object calculate(Object a, Object b) {

				if (a instanceof Double)
					return calculate((Double) a, (Double) b);
				if (a instanceof Float)
					return calculate((Float) a, (Float) b);
				if (a instanceof Long)
					return calculate((Long) a, (Long) b);
				if (a instanceof Integer)
					return calculate((Integer) a, (Integer) b);
				if (a instanceof Boolean)
					return calculate((Boolean) a, (Boolean) b);
				if (a instanceof String)
					return calculate((String) a, (String) b);
				if (a instanceof Matrix)
					return calculate((Matrix) a, (Matrix) b);
				if (a instanceof Matrix)
					return calculate((Number) a, (Matrix) b);
				if (a instanceof Matrix)
					return calculate((Matrix) a, (Number) b);
				assert false : a.getClass();
				return null;
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * tfossi.apolge.common.scripting.Operation#calculate(java.lang.
			 * Boolean, java.lang.Boolean)
			 */
			@Override
			public final Boolean calculate(Boolean a, Boolean b) {
				return new Boolean(a.booleanValue() || b.booleanValue());
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see java.lang.Object#toString()
			 */
			@Override
			public String toString() {
				return "\\|\\|";
			}

		});
		
		PatternMaps.addOperation("\\!\\|", new BoolOperation() {
			@Override
			public int getPriority() {
				return 4;
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * tfossi.apolge.common.scripting.Operation#calculate(java.lang.
			 * Object, java.lang.Object)
			 */
			@SuppressWarnings("cast")
			@Override
			public final Object calculate(Object a, Object b) {

				if (a instanceof Double)
					return calculate((Double) a, (Double) b);
				if (a instanceof Float)
					return calculate((Float) a, (Float) b);
				if (a instanceof Long)
					return calculate((Long) a, (Long) b);
				if (a instanceof Integer)
					return calculate((Integer) a, (Integer) b);
				if (a instanceof Boolean)
					return calculate((Boolean) a, (Boolean) b);
				if (a instanceof String)
					return calculate((String) a, (String) b);
				if (a instanceof Matrix)
					return calculate((Matrix) a, (Matrix) b);
				if (a instanceof Matrix)
					return calculate((Number) a, (Matrix) b);
				if (a instanceof Matrix)
					return calculate((Matrix) a, (Number) b);
				assert false : a.getClass();
				return null;
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * tfossi.apolge.common.scripting.Operation#calculate(java.lang.
			 * Boolean, java.lang.Boolean)
			 */
			@Override
			public final Boolean calculate(Boolean a, Boolean b) {
				// assert
				// false:a.booleanValue()+" !| "+b.booleanValue()+" | "+(a.booleanValue()
				// != b.booleanValue());
				return new Boolean(a.booleanValue() != b.booleanValue());
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see java.lang.Object#toString()
			 */
			@Override
			public String toString() {
				return "\\!\\|";
			}

		});
		
		PatternMaps.addOperation("\\&\\&", new BoolOperation() {
			@Override
			public int getPriority() {
				return 4;
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * tfossi.apolge.common.scripting.Operation#calculate(java.lang.
			 * Object, java.lang.Object)
			 */
			@SuppressWarnings("cast")
			@Override
			public final Object calculate(Object a, Object b) {

				if (a instanceof Double)
					return calculate((Double) a, (Double) b);
				if (a instanceof Float)
					return calculate((Float) a, (Float) b);
				if (a instanceof Long)
					return calculate((Long) a, (Long) b);
				if (a instanceof Integer)
					return calculate((Integer) a, (Integer) b);
				if (a instanceof Boolean)
					return calculate((Boolean) a, (Boolean) b);
				if (a instanceof String)
					return calculate((String) a, (String) b);
				if (a instanceof Matrix)
					return calculate((Matrix) a, (Matrix) b);
				if (a instanceof Matrix)
					return calculate((Number) a, (Matrix) b);
				if (a instanceof Matrix)
					return calculate((Matrix) a, (Number) b);
				assert false : a.getClass();
				return null;
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * tfossi.apolge.common.scripting.Operation#calculate(java.lang.
			 * Boolean, java.lang.Boolean)
			 */
			@Override
			public final Boolean calculate(Boolean a, Boolean b) {
				return new Boolean(a.booleanValue() && b.booleanValue());
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see java.lang.Object#toString()
			 */
			@Override
			public String toString() {
				return "\\&\\&";
			}

		});
		
		PatternMaps.addOperation("\\!\\&", new BoolOperation() {
			@Override
			public int getPriority() {
				return 4;
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * tfossi.apolge.common.scripting.Operation#calculate(java.lang.
			 * Object, java.lang.Object)
			 */
			@SuppressWarnings("cast")
			@Override
			public final Object calculate(Object a, Object b) {

				if (a instanceof Double)
					return calculate((Double) a, (Double) b);
				if (a instanceof Float)
					return calculate((Float) a, (Float) b);
				if (a instanceof Long)
					return calculate((Long) a, (Long) b);
				if (a instanceof Integer)
					return calculate((Integer) a, (Integer) b);
				if (a instanceof Boolean)
					return calculate((Boolean) a, (Boolean) b);
				if (a instanceof String)
					return calculate((String) a, (String) b);
				if (a instanceof Matrix)
					return calculate((Matrix) a, (Matrix) b);
				if (a instanceof Matrix)
					return calculate((Number) a, (Matrix) b);
				if (a instanceof Matrix)
					return calculate((Matrix) a, (Number) b);
				assert false : a.getClass();
				return null;
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * tfossi.apolge.common.scripting.Operation#calculate(java.lang.
			 * Boolean, java.lang.Boolean)
			 */
			@Override
			public final Boolean calculate(Boolean a, Boolean b) {
				// assert
				// false:a.booleanValue()+" !& "+b.booleanValue()+" | "+(a.booleanValue()
				// == b.booleanValue());
				return new Boolean(a.booleanValue() == b.booleanValue());

			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see java.lang.Object#toString()
			 */
			@Override
			public String toString() {
				return "\\!\\&";
			}
		});
	
	}


	// ---- Selbstverwaltung --------------------------------------------------

	/** serialVersionUID */
	@SuppressWarnings("unused")
	private final static long serialVersionUID = VERSION;

	/** logger */
	final static Logger logger = Logger.getLogger(PMBoolOp.class
			.getPackage().getName() + ".PMBoolOp");
}
