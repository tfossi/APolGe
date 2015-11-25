/**
 * PatternMaps.java
 * Branch master
 * APolGe
 * tfossi-team
 * licence GPLv3   
 */
package tfossi.apolge.common.scripting.vp;

import static tfossi.apolge.common.constants.ConstValue.LFCR;
import static tfossi.apolge.common.constants.ConstValue.LOGGER;
import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import Jama.Matrix;



import tfossi.apolge.common.constants.ConstMethod;
import tfossi.apolge.common.constants.ConstValue;
import tfossi.apolge.time.pit.ConstCPPit;
import tfossi.apolge.time.pit.PPiT;

/**
 * Pattern sind Muster, die bestimmte Methoden, Operatoren charakterisieren und die entsprechende Methode über das Interface DoneMethod
 *   mit der Methode calculate mittels Reflektion ausführen können.   
 * 
 * @author tfossi
 * @version 18.08.2014
 * @modified -
 * @since Java 1.6
 */
public class PatternMaps {

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
	static final Pattern stringQuotePattern = Pattern.compile("\\$\\d+\\$");

	// ---- Operationen, Funktionen --------------------------------------------
	
	/** Eine Liste aller Konstanten aus ConstValue und Sonderfällen !... */
	final static IVP_ConstantMap finalConstants = new VP_ConstantMap();

	/** Eine Liste aller Operationen */
	final static Map<String, Operation> finalOperations = new HashMap<String, Operation>();

	/**
	 * Fügt der Operatorenliste eine weitere Operation hinzu
	 * 
	 * @param name
	 *            Name der Operation in "\op"
	 * @param operation
	 *            die Operation
	 */
	private final static void addOperation(String name, Operation operation) {
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
	private final static void addFunction(String name, Type[] parameterType,
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
	 * TODO Comment
	 * 
	 * @modified -
	 */
	public PatternMaps() {
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

		

	// Mathematikfunktionen erfassen
	static {
		final List<String> twoPass = Arrays.asList(ConstValue.twoPass);
		final List<String> threePass = Arrays.asList(ConstValue.threePass);
		for (Method meth : Math.class.getMethods()) {
			final Method method = meth;
			String name = meth.getName();
			
			
			addFunction(name, meth.getGenericParameterTypes(), new Function() {
				
				String methName = method.getName();
				boolean pass2 = twoPass.contains(this.methName);
				boolean pass3 = threePass.contains(this.methName);
				Type retType = method.getReturnType();
				
				/*
				 * (non-Javadoc)
				 * 
				 * @see
				 * tfossi.apolge.common.scripting.Function#calculate(java.lang
				 * .Object[])
				 */
				@Override
				public Object calculate(Object[] values) {

					try {

						if (LOGGER)
							logger.trace("Berechne mathematische Funktion \""
									+ method.getName() + "\"" + LFCR
									+ "mit Parameter " + Arrays.asList(values));
						return method.invoke(null, values);
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					} catch (SecurityException e) {
						e.printStackTrace();
					}
					if (LOGGER)
						logger.trace("Do " + toString());
					assert false;
					return null;
				}

				/* (non-Javadoc)
				 * @see tfossi.apolge.common.scripting.VP.Function#twoPass()
				 */
				@Override
				public boolean twoPass(){
					return this.pass2;
				}
			
				/* (non-Javadoc)
				 * @see tfossi.apolge.common.scripting.VP.Function#threePass()
				 */
				@Override
				public boolean threePass(){
					return this.pass3;
				}
				
				@Override
				public void setTwoPass(){
					this.pass2=true;
				}
				@Override
				public void setThreePass(){
					this.pass3=true;
				}
				
				@Override
				public String toString() {
					return this.methName;
				}
				@Override
				public Type retType(){
					return this.retType;
				}
			});
		}
	}
	// Randomfunktionen erfassen
	static {
		final List<String> twoPass = Arrays.asList(ConstValue.twoPass);
		final List<String> threePass = Arrays.asList(ConstValue.threePass);
		for (Method meth : Random.class.getMethods()) {
			final Method method = meth;			
			String name = meth.getName();
			
			
			addFunction(name, meth.getGenericParameterTypes(), new Function() {
				
				String methName = method.getName();
				boolean pass2 = twoPass.contains(this.methName);
				boolean pass3 = threePass.contains(this.methName);
				Type retType = method.getReturnType();
				/*
				 * (non-Javadoc)
				 * 
				 * @see
				 * tfossi.apolge.common.scripting.Function#calculate(java.lang
				 * .Object[])
				 */
				@Override
				public Object calculate(Object[] values) {

					try {

						if (LOGGER)
							logger.trace("Berechne Zufallsfunktion \""
									+ method.getName() + "\"" + LFCR
									+ "mit Parameter " + Arrays.asList(values));
						return method.invoke(null, values);
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					} catch (SecurityException e) {
						e.printStackTrace();
					}
					if (LOGGER)
						logger.trace("Do " + toString());
					assert false;
					return null;
				}

				/* (non-Javadoc)
				 * @see tfossi.apolge.common.scripting.VP.Function#twoPass()
				 */
				@Override
				public boolean twoPass(){
					return this.pass2;
				}
			
				/* (non-Javadoc)
				 * @see tfossi.apolge.common.scripting.VP.Function#threePass()
				 */
				@Override
				public boolean threePass(){
					return this.pass3;
				}	
				@Override
				public void setTwoPass(){
					this.pass2=true;
				}
				@Override
				public void setThreePass(){
					this.pass3=true;
				}
				@Override
				public String toString() {
					return this.methName;
				}

				@Override
				public Type retType(){
					return this.retType;
				}
			});
		}
	}
	
	// APO-funktionen erfassen
	static {
		if(LOGGER)logger.info("ConstMethod einlesen....");
		final List<String> twoPass = Arrays.asList(ConstValue.twoPass);
		final List<String> threePass = Arrays.asList(ConstValue.threePass);
		for (Method meth : ConstMethod.class.getMethods()) {

			final Method method = meth;
			String name = meth.getName();
			final int countArguments = meth.getGenericParameterTypes().length;

			if (LOGGER)
				logger.trace(meth.getName()
						+ " "
						+ (countArguments == 0 ? void.class : meth
								.getParameterTypes()[0]));

			addFunction(name, meth.getParameterTypes(), new Function() {

				String methName = method.getName();
				boolean pass2 = twoPass.contains(this.methName);
				boolean pass3 = threePass.contains(this.methName);
				Type retType = method.getReturnType();

				/*
				 * (non-Javadoc)
				 * 
				 * @see
				 * tfossi.apolge.common.scripting.Function#calculate(java.lang
				 * .Object[])
				 */
				@Override
				public Object calculate(Object[] values) {

					try {

						if (LOGGER)
							logger.trace("Methode  : " + method.getName());
						if (LOGGER)
							logger.trace("Parameterclassen: " + Arrays.asList(method.getParameterTypes()));
						if (LOGGER)
							logger.trace("Aufrufclassen: " + (values!=null?((Object[])values[0]).getClass():"keine"));
						if (LOGGER)
							logger.trace("Aufrufparameter: " + (values!=null?Arrays.asList(((Object[])values[0])):"keine"));
//						Methode  : darr
//						Parameterclassen: [class [Ljava.lang.Object;]
//						Aufrufclassen: class [Ljava.lang.Object;
//						Aufrufparameter: [1, 2, 3]
						
//						Methode  : MATRIX
//						Parameterclassen: [class [[Ljava.lang.Object;]
//						Aufrufclassen: class [Ljava.lang.Object;
//						Aufrufparameter: [[Ljava.lang.Double;@75ca3e]
						
						// MATRIX(final Object ...o)
						// MATRIX(final Object[] ...o)
						Object rc = method.invoke(null, values);

						return rc;
					} catch (IllegalArgumentException  e) {

						
						if (LOGGER)
							logger.trace("Führe aus: " + ((Object[])values[0])[0]);
						e.printStackTrace();
						assert false;

					} catch ( IllegalAccessException e){
						e.printStackTrace();
						assert false;
					}catch ( InvocationTargetException e){
						e.printStackTrace();
						assert false; 
					}catch ( SecurityException e) {
						e.printStackTrace();
						assert false;
					} catch (Exception e) {
						e.printStackTrace();
						assert false;
					}
					if (LOGGER)
						logger.debug("Do " + toString());
					assert false;
					return null;
				}

				/* (non-Javadoc)
				 * @see tfossi.apolge.common.scripting.VP.Function#twoPass()
				 */
				@Override
				public boolean twoPass(){
					return this.pass2;
				}
			
				/* (non-Javadoc)
				 * @see tfossi.apolge.common.scripting.VP.Function#threePass()
				 */
				@Override
				public boolean threePass(){
					return this.pass3;
				}
				
				@Override
				public void setTwoPass(){
					this.pass2=true;
				}
				@Override
				public void setThreePass(){
					this.pass3=true;
				}
				@Override
				public String toString() {
					return this.methName;
				}
				@Override
				public Type retType(){
					return this.retType;
				}
			});
		}
	}

	// Vorzeichenfunktionen
	static {
		addFunction("\\-", new Type[] { Number.class }, new Function() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * tfossi.apolge.common.scripting.Function#calculate(java.lang.Object
			 * [])
			 */
			@Override
			public Object calculate(Object[] values) {
				if (values[0] instanceof Double)
					return new Double(-((Double) values[0]).doubleValue());
				if (values[0] instanceof Float)
					return new Float(-((Float) values[0]).floatValue());
				if (values[0] instanceof Long)
					return new Long(-((Long) values[0]).longValue());
				if (values[0] instanceof Integer)
					return new Integer(-((Integer) values[0]).intValue());
				if (values[0] instanceof Short)
					return new Short((short) -((Short) values[0]).shortValue());
				if (values[0] instanceof Byte)
					return new Byte((byte) -((Byte) values[0]).byteValue());
				if (values[0] instanceof Character)
					return new Character((char) -((Character) values[0])
							.charValue());
				assert false;
				if (values[0] instanceof Matrix)
					return ((Matrix) values[0]).inverse();
				return null;
			}

			/* (non-Javadoc)
			 * @see tfossi.apolge.common.scripting.VP.Function#twoPass()
			 */
			@Override
			public boolean twoPass(){
				return false;
			}
		
			/* (non-Javadoc)
			 * @see tfossi.apolge.common.scripting.VP.Function#threePass()
			 */
			@Override
			public boolean threePass(){
				return false;
			}
			
			@Override
			public void setTwoPass(){
				// nothing;
			}
			@Override
			public void setThreePass(){
				// nothing
			}
			@Override
			public String toString() {
				return "-";
			}

			@Override
			public Type retType(){
				return Number.class;
			}
		});
		addFunction("\\+", new Type[] { Number.class }, new Function() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * tfossi.apolge.common.scripting.Function#calculate(java.lang.Object
			 * [])
			 */
			@Override
			public Object calculate(Object[] values) {
				if (values[0] instanceof Double)
					return new Double(-((Double) values[0]).doubleValue());
				if (values[0] instanceof Float)
					return new Float(-((Float) values[0]).floatValue());
				if (values[0] instanceof Long)
					return new Long(-((Long) values[0]).longValue());
				if (values[0] instanceof Integer)
					return new Integer(-((Integer) values[0]).intValue());
				if (values[0] instanceof Short)
					return new Short((short) -((Short) values[0]).shortValue());
				if (values[0] instanceof Byte)
					return new Byte((byte) -((Byte) values[0]).byteValue());
				if (values[0] instanceof Character)
					return new Character((char) -((Character) values[0])
							.charValue());
				assert false;
				if (values[0] instanceof Matrix)
					return values[0];
				return null;
			}

			/* (non-Javadoc)
			 * @see tfossi.apolge.common.scripting.VP.Function#twoPass()
			 */
			@Override
			public boolean twoPass(){
				return false;
			}
		
			/* (non-Javadoc)
			 * @see tfossi.apolge.common.scripting.VP.Function#threePass()
			 */
			@Override
			public boolean threePass(){
				return false;
			}
			
			@Override
			public void setTwoPass(){
				// nothing
			}
			@Override
			public void setThreePass(){
				// nothing
			}
			@Override
			public String toString() {
				return "+";
			}

			@Override
			public Type retType(){
				return Number.class;
			}
		});
		addFunction("\\!", new Type[] { Boolean.class }, new Function() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * tfossi.apolge.common.scripting.Function#calculate(java.lang.Object
			 * [])
			 */
			@Override
			public Object calculate(Object[] values) {
				if (values[0] instanceof Boolean)
					return new Boolean(!((Boolean) values[0]).booleanValue());
				return null;
			}

			/* (non-Javadoc)
			 * @see tfossi.apolge.common.scripting.VP.Function#twoPass()
			 */
			@Override
			public boolean twoPass(){
				return false;
			}
		
			/* (non-Javadoc)
			 * @see tfossi.apolge.common.scripting.VP.Function#threePass()
			 */
			@Override
			public boolean threePass(){
				return false;
			}
			
			@Override
			public void setTwoPass(){
				// nothing
			}
			@Override
			public void setThreePass(){
				// nothing
			}
			@Override
			public String toString() {
				return "!";
			}

			@Override
			public Type retType(){
				return Boolean.class;
			}
		});
	}

	// Operationen
	static {
		addOperation("\\^", new Operation() {
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
			public final Byte calculate(Byte a, Byte b) {
				return new Byte((byte) Math.pow(a.doubleValue(),
						b.doubleValue()));
			}
			public final Short calculate(Short a, Short b) {
				return new Short((short) Math.pow(a.doubleValue(),
						b.doubleValue()));
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
			 * tfossi.apolge.common.scripting.Operation#calculate(java.lang.
			 * Boolean, java.lang.Boolean)
			 */
			@Override
			public final Boolean calculate(Boolean a, Boolean b) {
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
			public String toString() {
				return "\\^";
			}
		});

		addOperation("\\+", new Operation() {
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
			public final Short calculate(Short a, Short b) {
				return new Short((short) (a.shortValue() + b.shortValue()));
			}
			public final Byte calculate(Byte a, Byte b) {
				return new Byte((byte) (a.byteValue() + b.byteValue()));
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
				return a + b;
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
			public String toString() {
				return "\\+";
			}
		});

		addOperation("\\-", new Operation() {
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
					return calculate((Boolean) a, (Boolean) b);
				if (a instanceof String)
					return calculate((String) a, (String) b);
				if (a instanceof PPiT)
					return calculate((PPiT) a, (PPiT) b);
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
			public final Short calculate(Short a, Short b) {
				return new Short((short) (a.shortValue() - b.shortValue()));
			}
			public final Byte calculate(Byte a, Byte b) {
				return new Byte((byte) (a.byteValue() - b.byteValue()));
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
			 * tfossi.apolge.common.scripting.Operation#calculate(java.lang.
			 * Boolean, java.lang.Boolean)
			 */
			@Override
			public final Boolean calculate(Boolean a, Boolean b) {
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
				return new Long(ConstCPPit.difference(a.toCPit(), b.toCPit()));
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

		addOperation("\\*", new Operation() {
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
					return calculate((Boolean) a, (Boolean) b);
				if (a instanceof String)
					return calculate((String) a, (String) b);
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
			public final Short calculate(Short a, Short b) {
				return new Short((short) (a.shortValue() * b.shortValue()));
			}
			public final Byte calculate(Byte a, Byte b) {
				return new Byte((byte) (a.byteValue() * b.byteValue()));
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
			 * tfossi.apolge.common.scripting.Operation#calculate(java.lang.
			 * Boolean, java.lang.Boolean)
			 */
			@Override
			public final Boolean calculate(Boolean a, Boolean b) {
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

			@Override
			public final Matrix calculate(Matrix a, Matrix b) {
				return a.times(b);
			}

			@Override
			public final Matrix calculate(Matrix a, Number b) {
				return (Matrix) a.times(((Double) b).doubleValue());
			}

			@Override
			public final Matrix calculate(Number a, Matrix b) {
				return (Matrix) b.times(((Double) a).doubleValue());
			}

			@Override
			public String toString() {
				return "\\*";
			}
		});

		addOperation("\\/", new Operation() {
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
					return calculate((Boolean) a, (Boolean) b);
				if (a instanceof String)
					return calculate((String) a, (String) b);
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
			public final Short calculate(Short a, Short b) {
				return new Short((short) (a.shortValue() / b.shortValue()));
			}
			public final Byte calculate(Byte a, Byte b) {
				return new Byte((byte) (a.byteValue() / b.byteValue()));
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
			 * tfossi.apolge.common.scripting.Operation#calculate(java.lang.
			 * Boolean, java.lang.Boolean)
			 */
			@Override
			public final Boolean calculate(Boolean a, Boolean b) {
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

		addOperation("\\<", new Operation() {
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
			 * Double, java.lang.Double)
			 */
			@Override
			public final Double calculate(Double a, Double b) {
				return null;
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
				return null;
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
				return null;
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
				return null;
			}
			public final Short calculate(Short a, Short b) {
				return null;
			}
			public final Byte calculate(Byte a, Byte b) {
				return null;
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
			 * tfossi.apolge.common.scripting.Operation#calculate(java.lang.
			 * Boolean, java.lang.Boolean)
			 */
			@Override
			public final Boolean calculate(Boolean a, Boolean b) {
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
		addOperation("\\>", new Operation() {
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
			 * Double, java.lang.Double)
			 */
			@Override
			public final Double calculate(Double a, Double b) {
				return null;
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
				return null;
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
				return null;
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
				return null;
			}

			public final Short calculate(Short a, Short b) {
				return null;
			}
			public final Byte calculate(Byte a, Byte b) {
				return null;
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
			 * tfossi.apolge.common.scripting.Operation#calculate(java.lang.
			 * Boolean, java.lang.Boolean)
			 */
			@Override
			public final Boolean calculate(Boolean a, Boolean b) {
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
		addOperation("\\>\\=", new Operation() {
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
			 * Double, java.lang.Double)
			 */
			@Override
			public final Double calculate(Double a, Double b) {
				return null;
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
				return null;
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
				return null;
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
				return null;
			}

			public final Short calculate(Short a, Short b) {
				return null;
			}
			public final Byte calculate(Byte a, Byte b) {
				return null;
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
			 * tfossi.apolge.common.scripting.Operation#calculate(java.lang.
			 * Boolean, java.lang.Boolean)
			 */
			@Override
			public final Boolean calculate(Boolean a, Boolean b) {
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
		addOperation("\\<\\=", new Operation() {
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
			 * Double, java.lang.Double)
			 */
			@Override
			public final Double calculate(Double a, Double b) {
				return null;
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
				return null;
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
				return null;
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
				return null;
			}

			@Override
			public final Short calculate(Short a, Short b) {
				return null;
			}
			@Override
			public final Byte calculate(Byte a, Byte b) {
				return null;
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
			 * tfossi.apolge.common.scripting.Operation#calculate(java.lang.
			 * Boolean, java.lang.Boolean)
			 */
			@Override
			public final Boolean calculate(Boolean a, Boolean b) {
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
		addOperation("\\<\\>", new Operation() {
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
			 * Double, java.lang.Double)
			 */
			@Override
			public final Double calculate(Double a, Double b) {
				return null;
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
				return null;
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
				return null;
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
				return null;
			}

			public final Short calculate(Short a, Short b) {
				return null;
			}
			public final Byte calculate(Byte a, Byte b) {
				return null;
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
			 * tfossi.apolge.common.scripting.Operation#calculate(java.lang.
			 * Boolean, java.lang.Boolean)
			 */
			@Override
			public final Boolean calculate(Boolean a, Boolean b) {
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
		addOperation("\\=\\=", new Operation() {
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
			 * Double, java.lang.Double)
			 */
			@Override
			public final Double calculate(Double a, Double b) {
				return null;
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
				return null;
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
				return null;
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
				return null;
			}

			public final Short calculate(Short a, Short b) {
				return null;
			}
			public final Byte calculate(Byte a, Byte b) {
				return null;
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
			 * tfossi.apolge.common.scripting.Operation#calculate(java.lang.
			 * Boolean, java.lang.Boolean)
			 */
			@Override
			public final Boolean calculate(Boolean a, Boolean b) {
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

		addOperation("\\|\\|", new Operation() {
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
			 * Double, java.lang.Double)
			 */
			@Override
			public final Double calculate(Double a, Double b) {
				return null;
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
				return null;
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
				return null;
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
				return null;
			}
			@Override
			public final Short calculate(Short a, Short b) {
				return null;
			}
			@Override
			public final Byte calculate(Byte a, Byte b) {
				return null;
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
			 * @see
			 * tfossi.apolge.common.scripting.Operation#calculate(tfossi.apolge
			 * .time.pit.PPiT, tfossi.apolge.time.pit.PPiT)
			 */
			@Override
			public final Long calculate(PPiT a, PPiT b) {
				return null;
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
		addOperation("\\!\\|", new Operation() {
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
			 * Double, java.lang.Double)
			 */
			@Override
			public final Double calculate(Double a, Double b) {
				return null;
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
				return null;
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
				return null;
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
				return null;
			}

			public final Short calculate(Short a, Short b) {
				return null;
			}
			public final Byte calculate(Byte a, Byte b) {
				return null;
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
			 * @see
			 * tfossi.apolge.common.scripting.Operation#calculate(tfossi.apolge
			 * .time.pit.PPiT, tfossi.apolge.time.pit.PPiT)
			 */
			@Override
			public final Long calculate(PPiT a, PPiT b) {
				return null;
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
		addOperation("\\&\\&", new Operation() {
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
			 * Double, java.lang.Double)
			 */
			@Override
			public final Double calculate(Double a, Double b) {
				return null;
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
				return null;
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
				return null;
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
				return null;
			}

			@Override
			public final Short calculate(Short a, Short b) {
				return null;
			}
			@Override
			public final Byte calculate(Byte a, Byte b) {
				return null;
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
			 * @see
			 * tfossi.apolge.common.scripting.Operation#calculate(tfossi.apolge
			 * .time.pit.PPiT, tfossi.apolge.time.pit.PPiT)
			 */
			@Override
			public final Long calculate(PPiT a, PPiT b) {
				return null;
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
		addOperation("\\!\\&", new Operation() {
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
			 * Double, java.lang.Double)
			 */
			@Override
			public final Double calculate(Double a, Double b) {
				return null;
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
				return null;
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
				return null;
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
				return null;
			}

			public final Short calculate(Short a, Short b) {
				return null;
			}
			public final Byte calculate(Byte a, Byte b) {
				return null;
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
			 * @see
			 * tfossi.apolge.common.scripting.Operation#calculate(tfossi.apolge
			 * .time.pit.PPiT, tfossi.apolge.time.pit.PPiT)
			 */
			@Override
			public final Long calculate(PPiT a, PPiT b) {
				return null;
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

	// Konstanten
	static {
		finalConstants.put("e", new VP_ArrayTokenlist(new Double(Math.E)));
		finalConstants.put("pi", new VP_ArrayTokenlist(new Double(Math.PI)));
		finalConstants.put("PI", new VP_ArrayTokenlist(new Double(Math.PI)));

		finalConstants.put("true", new VP_ArrayTokenlist(new Boolean(true)));
		finalConstants.put("false", new VP_ArrayTokenlist(new Boolean(false)));

		StringBuffer r = new StringBuffer();
		for (String key : finalConstants.keySet())
			r.append("(" + key + ")|");
		r.deleteCharAt(r.lastIndexOf("|"));
		finalConstantsPattern = Pattern.compile(r.toString());

		r = new StringBuffer();
		r.append("(");
		for (String key : finalFunctions.keySet())
			r.append(key + "|");
		r.deleteCharAt(r.lastIndexOf("|"));
		r.append(")");
		finalFunctionPattern = Pattern.compile(r.toString());

		r = new StringBuffer();
		r.append("(");
		for (String key : finalOperations.keySet())
			r.append("(" + key + ")" + "|");
		r.deleteCharAt(r.lastIndexOf("|"));
		r.append(")");
		finalOperationPattern = Pattern.compile(r.toString());

	}
}

/**
 * Beschreibt die Parameter einer Funktion und legt die Aufrufmethode 'calculate' fest.
 * 
 * @author tfossi
 * @version 01.08.2014
 * @modified -
 * @since Java 1.6
 */
class FuncPType {
	/** Array der parameterTypen des Methodenaufrufs */
	Type[] parameterType;
	
	/** Aufrufmethode 'calculate' in der Schnittstelle Function */
	Function function;
	
	Object[] values;

	/**
	 * Stringpräsentaion der Funktion und ihrer Parametertypen
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public final String toString() {
		String rc;
		if(this.values==null)
		rc = "f:=" + this.function + "("
				+ Arrays.asList(this.parameterType) + ") Nur Typen, keine Parameter!";
		else{
			rc = "f:=" + this.function + "( ";
					
			if(this.values.length>0){
			for(int i = 0; i < this.values.length;i++){
				if(this.values[i].getClass().getComponentType()==null)
					rc += this.values[i]+", ";
				else
					rc += Arrays.asList((Object[])this.values[i])+", ";
			}
			}else{
				rc += " ";
			}
			rc = rc.substring(0, rc.length()-2)+" ) mit Parameter!";
		}
		return rc;
	}
}
