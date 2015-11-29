/**
 * IContent.java
 * Branch io
 * APolGe
 * tfossi-team
 * licence GPLv3  
 */
package tfossi.apolge.io;
import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;

import java.io.Serializable;
import java.util.List;

import org.eclipse.swt.widgets.Widget;

/**
 * Schnittstelle zum Screencontent für Cnsl und GUI
 * <ul><li>Ein Stringarray für die zeilendarstellung auf Cnsl</li>
 * <li>Ein Widget für GUI</li></ul> 
 * @author tfossi
 * @version 12.08.2014
 * @modified -
 * @since Java 1.6
 */
public interface IContent extends Serializable {
	/**
	 * @return Zeilenweise im Array abgelegte Informationen. Im Wesentlichen für Cnsl
	 *         geeignet
	 */
	public String[] getString();

	/**
	 * @return Im Widget abgelegte Informationen. Nur für GUI geeignet.
	 */
	public List<Widget> getWidget();

	// ---- Selbstverwaltung --------------------------------------------------
	/** serialVersionUID */
	public final static long serialVersionUID = VERSION;

}
