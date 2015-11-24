/**
 * ScriptException.java
 * Branch master
 * APolGe
 * tfossi-team
 * licence GPLv3   
 */
package tfossi.apolge.common.scripting;

/**
 * General Script-Exception
 *
 * @author tfossi
 * @version 01.12.2014
 * @modified -
 * @since Java 1.6
 */
public class ScriptException extends Exception  {
	
		/**
		 * 
		 * @param string
		 * 			Exceptionstext
		 * @modified -
		 */
		public ScriptException(String string) {
			super(string);
		}

		/** serialVersionUID */
		private static final long serialVersionUID = 1L;

	
}
