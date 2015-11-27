/**
 * PMApo.java
 * Branch master
 * APolGe
 * tfossi-team
 * licence GPLv3   
 */

package tfossi.apolge.common.scripting.vp.pm;

import static tfossi.apolge.common.constants.ConstValue.LOGGER;
import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

import tfossi.apolge.common.constants.ConstMethod;
import tfossi.apolge.common.constants.ConstValue;
import tfossi.apolge.common.scripting.vp.Function;

/**
 * Implementierung APO-Funktionen
 *
 * @author tfossi
 * @version 26.11.2015
 * @modified -
 * @since Java 1.6
 */
public class PMApo {


	/**
	 * Implementierung APO-Funktionen
	 * @modified - 
	 */
	public static void implement(){
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

			PatternMaps.addFunction(name, meth.getParameterTypes(), new Function() {

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
	// ---- Selbstverwaltung --------------------------------------------------

	/** serialVersionUID */
	@SuppressWarnings("unused")
	private final static long serialVersionUID = VERSION;

	/** logger */
	final static Logger logger = Logger.getLogger(PMApo.class
			.getPackage().getName() + ".PMApo");
}
