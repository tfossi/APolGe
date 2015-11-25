/**
 * TableMap.java
 * Branch master
 * APolGe
 * tfossi-team
 * licence GPLv3  
 */
package tfossi.apolge.common.scripting.t;
import java.util.LinkedHashMap;


/**
 * TableMap ist special HashMap für LoadScript-Objecte Apo mit Schnittstelle
 * Table. Dient zur Typsicherung
 * 
 * @see Table
 * @author tfossi
 * @version 01.08.2014
 * @since Java 1.6
 */
public class TableMap extends LinkedHashMap<String, Object> 
implements Table {

	/** superblock */
	private final Table superblock;
	/** root */
	private final Table root;
	
	/** blockname */
	private final String blockname;
	
	/* (non-Javadoc)
	 * @see tfossi.apolge.common.scripting.Table#superblock()
	 */
	@Override
	public Table supertable() {
		// TODO Auto-generated method stub
		return this.superblock;
	}
	
	@Override
	public Table root() {
		// TODO Auto-generated method stub
		return this.root;
	}
		
	/* (non-Javadoc)
	 * @see tfossi.apolge.common.scripting.Table#getBlockName()
	 */
	@Override
	public final String getTablename(){
		return this.blockname;
	}
	
	/** serialVersionUID<br>
	 * Hint: <code>VERSION</code> does not exists at this moment */
	private final static long serialVersionUID = -1L;
	/**
	 * Konstruktor
	 * @author tfossi
	 * @version 01.08.2014
	 * @param root Addresse des Roottable
	 * @param supertable Addresse der übergeordneten Table
	 * @param tablename Name dieser Table
	 * @modified -
	 * @since Java 1.6
	 */
	public TableMap(Table root, Table supertable, String tablename) {
		super();
		if(root==null)
			this.root = this;
		else
			this.root = root;
		if(supertable==null)
			this.blockname=".";
		else
			this.blockname = supertable.getTablename()+tablename;
		this.superblock = supertable;
		
	}	
}
