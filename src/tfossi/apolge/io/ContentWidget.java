/**
 * ContentWidget.java
 * Branch io
 * APolGe
 * tfossi-team
 * licence GPLv3  
 */
package tfossi.apolge.io;
import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;

import java.util.List;

import org.eclipse.swt.widgets.Widget;

/**
 * Screencontent für GUI
 *
 * @author tfossi
 * @version 12.08.2014
 * @modified -
 * @since Java 1.6
 */
public class ContentWidget implements IContent {

	/** listOfWidgets */
	private final List<Widget> listOfWidgets;

	/**
	 * 	 
	 * TODO Comment
	 * @param listOfWidgets -
	 * @modified -
	 */
	public ContentWidget(List <Widget> listOfWidgets) {
		this.listOfWidgets = listOfWidgets;
	}
	
	/* (non-Javadoc)
	 * @see name.tfossi.apolge.common.io.IContent#getString()
	 */
	@Override
	public String[] getString() {
// Not used
		return null;
	}
	
	/* (non-Javadoc)
	 * @see name.tfossi.apolge.common.io.IContent#getWidget()
	 */
	@Override
	public List<Widget> getWidget(){
		return this.listOfWidgets;
	}
	// ---- Selbstverwaltung --------------------------------------------------
	/** serialVersionUID */
	private final static long serialVersionUID = VERSION;

}
