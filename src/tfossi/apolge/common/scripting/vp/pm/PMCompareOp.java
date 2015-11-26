/**
 * PMCompareOp.java
 * Branch master
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
 * Implementierung Vergleichs Operationen
 *
 * @author tfossi
 * @version 26.11.2015
 * @modified -
 * @since Java 1.6
 */
public class PMCompareOp {

	/**
	 * Implementierung Vergleichs Operationen
	 * @modified - 
	 */
	public static void implement(){
		
		PatternMaps.addOperation("\\<", new CompareOperation() {
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
			@SuppressWarnings("cast")
			@Override
			public final Object calculate(Object a, Object b) {

				if (a instanceof Double)
					return new Boolean(
							((Double) a).doubleValue() < ((Double) b)
									.doubleValue());
				if (a instanceof Float)
					return new Boolean(((Float) a).doubleValue() < ((Float) b)
							.doubleValue());
				if (a instanceof Long)
					return new Boolean(((Long) a).doubleValue() < ((Long) b)
							.doubleValue());
				if (a instanceof Integer)
					return new Boolean(
							((Integer) a).doubleValue() < ((Integer) b)
									.doubleValue());
				if (a instanceof Short)
					return new Boolean(
							((Short) a).doubleValue() < ((Short) b)
									.doubleValue());
				if (a instanceof Byte)
					return new Boolean(
							((Byte) a).doubleValue() < ((Byte) b)
									.doubleValue());
				if (a instanceof Boolean)
					return new Boolean(false);
				if (a instanceof String)
					return new Boolean(false);
				if (a instanceof Matrix)
					return calculate((Matrix) a, (Matrix) b);
				if (a instanceof Matrix)
					return calculate((Number) a, (Matrix) b);
				if (a instanceof Matrix)
					return calculate((Matrix) a, (Number) b);
				return new Boolean(false);
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * tfossi.apolge.common.scripting.Operation#calculate(java.lang.
			 * String, java.lang.String)
			 */
			@Override
			public final String calculate(String a, String b) {
				return null;
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * tfossi.apolge.common.scripting.Operation#calculate(tfossi.apolge
			 * .time.pit.PPiT, tfossi.apolge.time.pit.PPiT)
			 */
			@Override
			public final Long calculate(PPiT a, PPiT b) {
				return null;
			}


			/*
			 * (non-Javadoc)
			 * 
			 * @see java.lang.Object#toString()
			 */
			@Override
			public String toString() {
				return "\\<";
			}
		});
	
		PatternMaps.addOperation("\\>", new CompareOperation() {
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
			@SuppressWarnings("cast")
			@Override
			public final Object calculate(Object a, Object b) {

				if (a instanceof Double)
					return new Boolean(
							((Double) a).doubleValue() > ((Double) b)
									.doubleValue());
				if (a instanceof Float)
					return new Boolean(((Float) a).doubleValue() > ((Float) b)
							.doubleValue());
				if (a instanceof Long)
					return new Boolean(((Long) a).doubleValue() > ((Long) b)
							.doubleValue());
				if (a instanceof Integer)
					return new Boolean(
							((Integer) a).doubleValue() > ((Integer) b)
									.doubleValue());
				if (a instanceof Short)
					return new Boolean(
							((Short) a).doubleValue() > ((Short) b)
									.doubleValue());
				if (a instanceof Byte)
					return new Boolean(
							((Byte) a).doubleValue() > ((Byte) b)
									.doubleValue());
				if (a instanceof Boolean)
					return new Boolean(false);
				if (a instanceof String)
					return new Boolean(false);
				if (a instanceof Matrix)
					return calculate((Matrix) a, (Matrix) b);
				if (a instanceof Matrix)
					return calculate((Number) a, (Matrix) b);
				if (a instanceof Matrix)
					return calculate((Matrix) a, (Number) b);
				return new Boolean(false);
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * tfossi.apolge.common.scripting.Operation#calculate(java.lang.
			 * String, java.lang.String)
			 */
			@Override
			public final String calculate(String a, String b) {
				return null;
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * tfossi.apolge.common.scripting.Operation#calculate(tfossi.apolge
			 * .time.pit.PPiT, tfossi.apolge.time.pit.PPiT)
			 */
			@Override
			public final Long calculate(PPiT a, PPiT b) {
				return null;
			}


			/*
			 * (non-Javadoc)
			 * 
			 * @see java.lang.Object#toString()
			 */
			@Override
			public String toString() {
				return "\\>";
			}
		});
		
		PatternMaps.addOperation("\\>\\=", new CompareOperation() {
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
			@SuppressWarnings("cast")
			@Override
			public final Object calculate(Object a, Object b) {
				if (a instanceof Double)
					return new Boolean(
							((Double) a).doubleValue() >= ((Double) b)
									.doubleValue());
				if (a instanceof Float)
					return new Boolean(((Float) a).doubleValue() >= ((Float) b)
							.doubleValue());
				if (a instanceof Long)
					return new Boolean(((Long) a).doubleValue() >= ((Long) b)
							.doubleValue());
				if (a instanceof Integer)
					return new Boolean(
							((Integer) a).doubleValue() >= ((Integer) b)
									.doubleValue());
				if (a instanceof Boolean)
					return new Boolean(false);
				if (a instanceof String)
					return new Boolean(false);
				if (a instanceof Matrix)
					return calculate((Matrix) a, (Matrix) b);
				if (a instanceof Matrix)
					return calculate((Number) a, (Matrix) b);
				if (a instanceof Matrix)
					return calculate((Matrix) a, (Number) b);
				return new Boolean(false);
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * tfossi.apolge.common.scripting.Operation#calculate(java.lang.
			 * String, java.lang.String)
			 */
			@Override
			public final String calculate(String a, String b) {
				return null;
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * tfossi.apolge.common.scripting.Operation#calculate(tfossi.apolge
			 * .time.pit.PPiT, tfossi.apolge.time.pit.PPiT)
			 */
			@Override
			public final Long calculate(PPiT a, PPiT b) {
				return null;
			}


			/*
			 * (non-Javadoc)
			 * 
			 * @see java.lang.Object#toString()
			 */
			@Override
			public String toString() {
				return "\\>\\=";
			}
		});
		
		PatternMaps.addOperation("\\<\\=", new CompareOperation() {
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
			@SuppressWarnings("cast")
			@Override
			public final Object calculate(Object a, Object b) {

				if (a instanceof Double)
					return new Boolean(
							((Double) a).doubleValue() <= ((Double) b)
									.doubleValue());
				if (a instanceof Float)
					return new Boolean(((Float) a).doubleValue() <= ((Float) b)
							.doubleValue());
				if (a instanceof Long)
					return new Boolean(((Long) a).doubleValue() <= ((Long) b)
							.doubleValue());
				if (a instanceof Integer)
					return new Boolean(
							((Integer) a).doubleValue() <= ((Integer) b)
									.doubleValue());
				if (a instanceof Short)
					return new Boolean(
							((Short) a).doubleValue() <= ((Short) b)
									.doubleValue());
				if (a instanceof Byte)
					return new Boolean(
							((Byte) a).doubleValue() <= ((Byte) b)
									.doubleValue());
				if (a instanceof Boolean)
					return new Boolean(false);
				if (a instanceof String)
					return new Boolean(false);
				if (a instanceof Matrix)
					return calculate((Matrix) a, (Matrix) b);
				if (a instanceof Matrix)
					return calculate((Number) a, (Matrix) b);
				if (a instanceof Matrix)
					return calculate((Matrix) a, (Number) b);
				return new Boolean(false);
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * tfossi.apolge.common.scripting.Operation#calculate(java.lang.
			 * String, java.lang.String)
			 */
			@Override
			public final String calculate(String a, String b) {
				return null;
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * tfossi.apolge.common.scripting.Operation#calculate(tfossi.apolge
			 * .time.pit.PPiT, tfossi.apolge.time.pit.PPiT)
			 */
			@Override
			public final Long calculate(PPiT a, PPiT b) {
				return null;
			}


			/*
			 * (non-Javadoc)
			 * 
			 * @see java.lang.Object#toString()
			 */
			@Override
			public String toString() {
				return "\\<\\=";
			}
		});
	
		PatternMaps.addOperation("\\<\\>", new CompareOperation() {
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
			@SuppressWarnings("cast")
			@Override
			public final Object calculate(Object a, Object b) {
				if (a instanceof Double)
					return new Boolean(
							((Double) a).doubleValue() != ((Double) b)
									.doubleValue());
				if (a instanceof Float)
					return new Boolean(((Float) a).doubleValue() != ((Float) b)
							.doubleValue());
				if (a instanceof Long)
					return new Boolean(((Long) a).doubleValue() != ((Long) b)
							.doubleValue());
				if (a instanceof Integer)
					return new Boolean(
							((Integer) a).doubleValue() != ((Integer) b)
									.doubleValue());
				if (a instanceof Boolean)
					return new Boolean(false);
				if (a instanceof String)
					return new Boolean(false);
				if (a instanceof Matrix)
					return calculate((Matrix) a, (Matrix) b);
				if (a instanceof Matrix)
					return calculate((Number) a, (Matrix) b);
				if (a instanceof Matrix)
					return calculate((Matrix) a, (Number) b);
				return new Boolean(false);
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * tfossi.apolge.common.scripting.Operation#calculate(java.lang.
			 * String, java.lang.String)
			 */
			@Override
			public final String calculate(String a, String b) {
				return null;
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * tfossi.apolge.common.scripting.Operation#calculate(tfossi.apolge
			 * .time.pit.PPiT, tfossi.apolge.time.pit.PPiT)
			 */
			@Override
			public final Long calculate(PPiT a, PPiT b) {
				return null;
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see java.lang.Object#toString()
			 */
			@Override
			public String toString() {
				return "\\<\\>";
			}
		});
		
		PatternMaps.addOperation("\\=\\=", new CompareOperation() {
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
			@SuppressWarnings("cast")
			@Override
			public final Object calculate(Object a, Object b) {
				if (a instanceof Double)
					return new Boolean(
							((Double) a).doubleValue() == ((Double) b)
									.doubleValue());
				if (a instanceof Float)
					return new Boolean(((Float) a).doubleValue() == ((Float) b)
							.doubleValue());
				if (a instanceof Long)
					return new Boolean(((Long) a).doubleValue() == ((Long) b)
							.doubleValue());
				if (a instanceof Integer)
					return new Boolean(
							((Integer) a).doubleValue() == ((Integer) b)
									.doubleValue());
				if (a instanceof Boolean)
					return new Boolean(false);
				if (a instanceof String)
					return new Boolean(false);
				if (a instanceof Matrix)
					return calculate((Matrix) a, (Matrix) b);
				if (a instanceof Matrix)
					return calculate((Number) a, (Matrix) b);
				if (a instanceof Matrix)
					return calculate((Matrix) a, (Number) b);
				return new Boolean(false);
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * tfossi.apolge.common.scripting.Operation#calculate(java.lang.
			 * String, java.lang.String)
			 */
			@Override
			public final String calculate(String a, String b) {
				return null;
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * tfossi.apolge.common.scripting.Operation#calculate(tfossi.apolge
			 * .time.pit.PPiT, tfossi.apolge.time.pit.PPiT)
			 */
			@Override
			public final Long calculate(PPiT a, PPiT b) {
				return null;
			}


			/*
			 * (non-Javadoc)
			 * 
			 * @see java.lang.Object#toString()
			 */
			@Override
			public String toString() {
				return "\\=\\=";
			}
		});
	
	}

	// ---- Selbstverwaltung --------------------------------------------------

	/** serialVersionUID */
	@SuppressWarnings("unused")
	private final static long serialVersionUID = VERSION;

	/** logger */
	final static Logger logger = Logger.getLogger(PMCompareOp.class
			.getPackage().getName() + ".PMCompareOp");
}
