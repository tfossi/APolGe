package tfossi.apolge.common.state.guard;

import java.util.ArrayList;
import java.util.List;


/**
* Enthält die Informationen für die Guardlisten der Transaktionen.<br>
*
 *
 * @author tfossi
 * @version 26.01.2015
 * @modified -
 * @since Java 1.6
 */
public class GuardList {
	/** nr */
	public int nr;
	/** guards */
	public final List<_Guard> guards = new ArrayList<_Guard>();
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString(){
		String str = new String("Guard "+this.nr);
		
		for(_Guard g : this.guards)
			str+=" "+g.toString();
		return str;
	}
}
