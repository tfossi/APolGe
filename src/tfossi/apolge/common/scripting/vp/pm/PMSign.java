/**
 * PMSign.java
 * Branch master
 * APolGe
 * tfossi-team
 * licence GPLv3   
 */
package tfossi.apolge.common.scripting.vp.pm;

import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;

import java.lang.reflect.Type;

import org.apache.log4j.Logger;

import tfossi.apolge.common.scripting.vp.Function;
import Jama.Matrix;

/**
 * Implementierung Vorzeichenfunktionen
 *
 * @author tfossi
 * @version 26.11.2015
 * @modified -
 * @since Java 1.6
 */
public class PMSign {


	/**
	 * Implementierung Vorzeichenfunktionen
	 * @modified - 
	 */
	public static void implement(){
		PatternMaps.addFunction("\\-", new Type[] { Number.class }, new Function() {

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
		PatternMaps.addFunction("\\+", new Type[] { Number.class }, new Function() {

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
		PatternMaps.addFunction("\\!", new Type[] { Boolean.class }, new Function() {

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
	
	// ---- Selbstverwaltung --------------------------------------------------

	/** serialVersionUID */
	@SuppressWarnings("unused")
	private final static long serialVersionUID = VERSION;

	/** logger */
	final static Logger logger = Logger.getLogger(PMSign.class
			.getPackage().getName() + ".PMSign");
}
