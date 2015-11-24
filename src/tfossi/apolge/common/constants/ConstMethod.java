/** 
 * ConstMethod.java
 * Branch master
 * APolGe
 * tfossi-team
 * licence GPLv3 
 */
package tfossi.apolge.common.constants;

import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;
import org.apache.log4j.Logger;



/**
 * Globale Methoden
 * 
 * @author tfossi
 * @since Java 1.6
 * @version 10.08.2014
 * @modified -
 */
public class ConstMethod extends ConstValue{
	
	/**
	 * Testmethode Element-ID
	 * @return ID
	 * @modified - 
	 */
	public static int ident(){
		return 1;
	}
	/**
	 * Testvariante Script-Adressen 
	 * @param str Addressenstring
	 * @return Datenzugriff
	 * @modified - 
	 */
	public static Object ADR(String ... str){
		for(String s : str)
			System.err.println("CM:ADR="+s);
		return new Long(5);
	}

	// ---- Selbstverwaltung --------------------------------------------------
	/** serialVersionUID */
	@SuppressWarnings("unused")
	private final static long serialVersionUID = VERSION;
	/** logger */
	@SuppressWarnings("unused")
	private final static Logger logger = Logger.getLogger(ConstMethod.class
			.getPackage().getName()+".ConstMethod");

}
