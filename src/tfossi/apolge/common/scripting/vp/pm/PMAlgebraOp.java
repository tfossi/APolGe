/**
 * PMAlgebraOp.java
 * Branch scripting
 * APolGe
 * tfossi-team
 * licence GPLv3   
 */
package tfossi.apolge.common.scripting.vp.pm;

import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;

import org.apache.log4j.Logger;

import tfossi.apolge.time.pit.PPiT;
import Jama.Matrix;

/**
 * Implementierung Algebra-Operationen
 *
 * @author tfossi
 * @version 26.11.2015
 * @modified -
 * @since Java 1.6
 */
public class PMAlgebraOp {


	/**
	 * Implementierung Algebra Operationen
	 * @modified - 
	 */
	public static void implement(){
		PatternMaps.addOperation("\\^", new AlgebraOperation() {
			@Override
			public int getPriority() {
				return 3;
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * tfossi.apolge.common.scripting.Operation#calculate(java.lang.
			 * Object, java.lang.Object)
			 */
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
					return calculate((Boolean)a, (Boolean)b);
				if (a instanceof String && b instanceof String)
					return calculate((String)a, (String)b);
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
			 * Double, java.lang.Double)
			 */
			@Override
			public final Double calculate(Double a, Double b) {
				return new Double(Math.pow(a.doubleValue(), b.doubleValue()));
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * tfossi.apolge.common.scripting.Operation#calculate(java.lang.
			 * Float, java.lang.Float)
			 */
			@Override
			public final Float calculate(Float a, Float b) {
				return new Float(Math.pow(a.doubleValue(), b.doubleValue()));
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * tfossi.apolge.common.scripting.Operation#calculate(java.lang.
			 * Long, java.lang.Long)
			 */
			@Override
			public final Long calculate(Long a, Long b) {
				return new Long((long) Math.pow(a.doubleValue(),
						b.doubleValue()));
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * tfossi.apolge.common.scripting.Operation#calculate(java.lang.
			 * Integer, java.lang.Integer)
			 */
			@Override
			public final Integer calculate(Integer a, Integer b) {
				return new Integer((int) Math.pow(a.doubleValue(),
						b.doubleValue()));
			}
			@Override
			public final Byte calculate(Byte a, Byte b) {
				return new Byte((byte) Math.pow(a.doubleValue(),
						b.doubleValue()));
			}
			@Override
			public final Short calculate(Short a, Short b) {
				return new Short((short) Math.pow(a.doubleValue(),
						b.doubleValue()));
			}

			@Override
			public final Matrix calculate(Matrix a, Matrix b) {
				return null;
			}

			@Override
			public final Matrix calculate(Matrix a, Number b) {
				return null;
			}

			@Override
			public final Matrix calculate(Number a, Matrix b) {
				return null;
			}

			@Override
			public final String calculate(String a, String b) {
				return new String(a+"^"+b);
			}
			
			@Override
			public String toString() {
				return "\\^";
			}
		});
		
		PatternMaps.addOperation("\\+", new AlgebraOperation() {
			@Override
			public int getPriority() {
				return 1;
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * tfossi.apolge.common.scripting.Operation#calculate(java.lang.
			 * Object, java.lang.Object)
			 */
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
				if (a instanceof Byte){
					return calculate((Byte) a, (Byte) b);
				}
				if (a instanceof Short){
					return calculate((Short) a, (Short) b);
				}
				if (a instanceof Boolean)
					return calculate((Boolean)a, (Boolean)b);
				if (a instanceof String && b instanceof String)
					return calculate((String)a, (String)b);
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
			 * Double, java.lang.Double)
			 */
			@Override
			public final Double calculate(Double a, Double b) {
				return new Double(a.doubleValue() + b.doubleValue());
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * tfossi.apolge.common.scripting.Operation#calculate(java.lang.
			 * Float, java.lang.Float)
			 */
			@Override
			public final Float calculate(Float a, Float b) {
				return new Float(a.floatValue() + b.floatValue());
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * tfossi.apolge.common.scripting.Operation#calculate(java.lang.
			 * Long, java.lang.Long)
			 */
			@Override
			public final Long calculate(Long a, Long b) {
				return new Long(a.longValue() + b.longValue());
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * tfossi.apolge.common.scripting.Operation#calculate(java.lang.
			 * Integer, java.lang.Integer)
			 */
			@Override
			public final Integer calculate(Integer a, Integer b) {
				return new Integer(a.intValue() + b.intValue());
			}
			@Override
			public final Short calculate(Short a, Short b) {
				return new Short((short) (a.shortValue() + b.shortValue()));
			}
			@Override
			public final Byte calculate(Byte a, Byte b) {
				return new Byte((byte) (a.byteValue() + b.byteValue()));
			}

			@Override
			public final Matrix calculate(Matrix a, Matrix b) {
				return a.plus(b);
			}

			@Override
			public final Matrix calculate(Matrix a, Number b) {
				return null;
			}

			@Override
			public final Matrix calculate(Number a, Matrix b) {
				return null;
			}

			@Override
			public final String calculate(String a, String b) {
				return new String(a+b);
			}
			
			@Override
			public String toString() {
				return "\\+";
			}
		});
	
		PatternMaps.addOperation("\\-", new AlgebraOperation() {
			@Override
			public int getPriority() {
				return 1;
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * tfossi.apolge.common.scripting.Operation#calculate(java.lang.
			 * Object, java.lang.Object)
			 */
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
					return calculate((Boolean)a, (Boolean)b);
				if (a instanceof String && b instanceof String)
					return calculate((String)a, (String)b);
				if (a instanceof PPiT)
					return calculate(a, b);
				if (a instanceof Matrix)
					return calculate((Matrix) a, (Matrix) b);
				if (a instanceof Matrix)
					return calculate((Number) a, (Matrix) b);
				if (a instanceof Matrix)
					return calculate((Matrix) a, (Number) b);
				assert false;
				return null;
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * tfossi.apolge.common.scripting.Operation#calculate(java.lang.
			 * Double, java.lang.Double)
			 */
			@Override
			public final Double calculate(Double a, Double b) {
				return new Double(a.doubleValue() - b.doubleValue());
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * tfossi.apolge.common.scripting.Operation#calculate(java.lang.
			 * Float, java.lang.Float)
			 */
			@Override
			public final Float calculate(Float a, Float b) {
				return new Float(a.floatValue() - b.floatValue());
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * tfossi.apolge.common.scripting.Operation#calculate(java.lang.
			 * Long, java.lang.Long)
			 */
			@Override
			public final Long calculate(Long a, Long b) {
				return new Long(a.longValue() - b.longValue());
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * tfossi.apolge.common.scripting.Operation#calculate(java.lang.
			 * Integer, java.lang.Integer)
			 */
			@Override
			public final Integer calculate(Integer a, Integer b) {
				return new Integer(a.intValue() - b.intValue());
			}
			@Override
			public final Short calculate(Short a, Short b) {
				return new Short((short) (a.shortValue() - b.shortValue()));
			}
			@Override
			public final Byte calculate(Byte a, Byte b) {
				return new Byte((byte) (a.byteValue() - b.byteValue()));
			}
			
			@Override
			public final Matrix calculate(Matrix a, Matrix b) {
				return a.minus(b);
			}

			@Override
			public final Matrix calculate(Matrix a, Number b) {
				return null;
			}

			@Override
			public final Matrix calculate(Number a, Matrix b) {
				return null;
			}

			@Override
			public final String calculate(String a, String b) {
				return new String(a+"-"+b);
			}
			
			/*
			 * (non-Javadoc)
			 * 
			 * @see java.lang.Object#toString()
			 */
			@Override
			public String toString() {
				return "\\-";
			}
		});
	
		PatternMaps.addOperation("\\*", new AlgebraOperation() {
			@Override
			public int getPriority() {
				return 2;
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * tfossi.apolge.common.scripting.Operation#calculate(java.lang.
			 * Object, java.lang.Object)
			 */
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
					return calculate((Boolean)a, (Boolean)b);
				if (a instanceof String && b instanceof String)
					return calculate((String)a, (String)b);
				if (a instanceof Matrix && b instanceof Matrix)
					return calculate((Matrix) a, (Matrix) b);
				if (b instanceof Matrix)
					return calculate((Number) a, (Matrix) b);
				if (a instanceof Matrix)
					return calculate((Matrix) a, (Number) b);
				assert false;
				return null;
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * tfossi.apolge.common.scripting.Operation#calculate(java.lang.
			 * Double, java.lang.Double)
			 */
			@Override
			public final Double calculate(Double a, Double b) {
				return new Double(a.doubleValue() * b.doubleValue());
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * tfossi.apolge.common.scripting.Operation#calculate(java.lang.
			 * Float, java.lang.Float)
			 */
			@Override
			public final Float calculate(Float a, Float b) {
				return new Float(a.floatValue() * b.floatValue());
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * tfossi.apolge.common.scripting.Operation#calculate(java.lang.
			 * Long, java.lang.Long)
			 */
			@Override
			public final Long calculate(Long a, Long b) {
				return new Long(a.longValue() * b.longValue());
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * tfossi.apolge.common.scripting.Operation#calculate(java.lang.
			 * Integer, java.lang.Integer)
			 */
			@Override
			public final Integer calculate(Integer a, Integer b) {
				return new Integer(a.intValue() * b.intValue());
			}
			@Override
			public final Short calculate(Short a, Short b) {
				return new Short((short) (a.shortValue() * b.shortValue()));
			}
			@Override
			public final Byte calculate(Byte a, Byte b) {
				return new Byte((byte) (a.byteValue() * b.byteValue()));
			}

			@Override
			public final Matrix calculate(Matrix a, Matrix b) {
				return a.times(b);
			}

			@Override
			public final Matrix calculate(Matrix a, Number b) {
				return a.times(((Double) b).doubleValue());
			}

			@Override
			public final Matrix calculate(Number a, Matrix b) {
				return b.times(((Double) a).doubleValue());
			}

			@Override
			public final String calculate(String a, String b) {
				return new String(a+" "+b);
			}
			
			@Override
			public String toString() {
				return "\\*";
			}
		});
	
		PatternMaps.addOperation("\\/", new AlgebraOperation() {
			@Override
			public int getPriority() {
				return 2;
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * tfossi.apolge.common.scripting.Operation#calculate(java.lang.
			 * Object, java.lang.Object)
			 */
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
					return calculate((Boolean)a, (Boolean)b);
				if (a instanceof String && b instanceof String)
					return calculate((String)a, (String)b);
				if (a instanceof Matrix)
					return calculate((Matrix) a, (Matrix) b);
				if (a instanceof Matrix)
					return calculate((Number) a, (Matrix) b);
				if (a instanceof Matrix)
					return calculate((Matrix) a, (Number) b);
				assert false;
				return null;
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * tfossi.apolge.common.scripting.Operation#calculate(java.lang.
			 * Double, java.lang.Double)
			 */
			@Override
			public final Double calculate(Double a, Double b) {
				return new Double(a.doubleValue() / b.doubleValue());
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * tfossi.apolge.common.scripting.Operation#calculate(java.lang.
			 * Float, java.lang.Float)
			 */
			@Override
			public final Float calculate(Float a, Float b) {
				return new Float(a.floatValue() / b.floatValue());
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * tfossi.apolge.common.scripting.Operation#calculate(java.lang.
			 * Long, java.lang.Long)
			 */
			@Override
			public final Long calculate(Long a, Long b) {
				return new Long(a.longValue() / b.longValue());
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * tfossi.apolge.common.scripting.Operation#calculate(java.lang.
			 * Integer, java.lang.Integer)
			 */
			@Override
			public final Integer calculate(Integer a, Integer b) {
				return new Integer(a.intValue() / b.intValue());
			}
			@Override
			public final Short calculate(Short a, Short b) {
				return new Short((short) (a.shortValue() / b.shortValue()));
			}
			@Override
			public final Byte calculate(Byte a, Byte b) {
				return new Byte((byte) (a.byteValue() / b.byteValue()));
			}

			@Override
			public final Matrix calculate(Matrix a, Matrix b) {
				return null;
			}

			@Override
			public final Matrix calculate(Matrix a, Number b) {
				return null;
			}

			@Override
			public final Matrix calculate(Number a, Matrix b) {
				return null;
			}

			@Override
			public final String calculate(String a, String b) {
				return new String(a+", "+b);
			}
			/*
			 * (non-Javadoc)
			 * 
			 * @see java.lang.Object#toString()
			 */
			@Override
			public String toString() {
				return "\\/";
			}
		});
	}

	// ---- Selbstverwaltung --------------------------------------------------

	/** serialVersionUID */
	@SuppressWarnings("unused")
	private final static long serialVersionUID = VERSION;

	/** logger */
	final static Logger logger = Logger.getLogger(PMAlgebraOp.class
			.getPackage().getName() + ".PMAlgebraOp");
}
