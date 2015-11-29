/**
 * PMRandom.java
 * Branch master
 * APolGe
 * tfossi-team
 * licence GPLv3   
 */
package tfossi.apolge.common.scripting.vp.pm;

import static tfossi.apolge.common.constants.ConstValue.LFCR;
import static tfossi.apolge.common.constants.ConstValue.LOGGER;
import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;

import tfossi.apolge.common.constants.ConstValue;
import tfossi.apolge.common.scripting.vp.Function;

/**
 * Implementierung Zufallsfunktionen
 *
 * @author tfossi
 * @version 26.11.2015
 * @modified -
 * @since Java 1.6
 */
public class PMRandom {
	/**
	 * Implementierung Zufallsfunktionen
	 * @modified - 
	 */
	public static void implement(){
		final List<String> twoPass = Arrays.asList(ConstValue.twoPass);
		final List<String> threePass = Arrays.asList(ConstValue.threePass);
		
		final Random methObject = new Random();
		
		for (Method meth : Random.class.getMethods()) {
			final Method method = meth;			
			String name = meth.getName();
			
			
			PatternMaps.addFunction(name, meth.getGenericParameterTypes(), new Function() {
				
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
									+ "mit Parameter " + Arrays.asList(values));//												
						return method.invoke(methObject, values);
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


	// ---- Selbstverwaltung --------------------------------------------------

	/** serialVersionUID */
	@SuppressWarnings("unused")
	private final static long serialVersionUID = VERSION;

	/** logger */
	final static Logger logger = Logger.getLogger(PMRandom.class
			.getPackage().getName() + ".PMRandom");
}
