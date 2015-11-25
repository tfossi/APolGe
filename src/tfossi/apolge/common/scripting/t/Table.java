/**
 * Table.java
 * Branch master
 * APolGe
 * tfossi-team
 * licence GPLv3  
 */
package tfossi.apolge.common.scripting.t;

import java.util.Map;

/**
 * Table ist (Marker-)Interface von TableMap
 * 
 * @author tfossi
 * @version 01.08.2014
 * @modified -
 * @since Java 1.6
 */
public interface Table extends Map<String, Object> {

	/**
	 * @return Addresse der Roottable
	 * @modified -
	 */
	public Table root();

	/**
	 * @return Addresse der übergeordneten Table
	 * @modified -
	 */
	public Table supertable();

	/**
	 * @return Name der aktuellen Table
	 * @modified -
	 */
	public String getTablename();

}
