/**
 * Function.java
 * Branch master
 * APolGe
 * tfossi-team
 * licence GPLv3  
 */
package tfossi.apolge.common.scripting.vp;

import static tfossi.apolge.common.constants.ConstValueExtension.*;

import java.lang.reflect.Type;
/**
 * Eine Funktionen bekommt eine Liste von Argumenten und berechnet etwas daraus,
 * eine typische Funktion ist "max", welche das Maximum aus mehreren Zahlen
 * berechnet.
 * 
 * @author tfossi
 * @version 1.07.2014 {@value #serialVersionUID}
 * @modified Coderevision, tfossi, 31.07.2014 
 * @since Java 1.6
 */
public interface Function {
	/** serialVersionUID */
	public final static long serialVersionUID = VERSION;
	
	/**
	 * Berechnet diese Funktion.
	 * 
	 * @param values
	 *            die Argumente
	 * @return irgendeine Berechnung
	 */
	public Object calculate(Object[] values);
	
	/**
	 * @return <i>true</v> if Function/Method is a 2Pass
	 * @modified - 
	 */
	public boolean twoPass();
	/**
	 * set Function/Method to 2Pass
	 * @modified - 
	 */
	public void setTwoPass();

	/**
	 * @return <i>true</v> if Function/Method is a 3Pass
	 */
	public boolean threePass();
	
	/**
	 * set Function/Method to 3Pass
	 * @modified - 
	 */
	public void setThreePass();

	/**
	 * @return Method Returntype 
	 * @modified - 
	 */
	Type retType();

}
