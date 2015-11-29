/**
 * IBuildSWTWidget.java
 * Branch io
 * APolGe
 * tfossi-team
 * licence GPLv3  
 */
package tfossi.apolge.io.screenfactory;

import java.util.List;

import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Widget;

/**
 * SWT-Schnittstelle zu den Commands und Screen
 * 
 * @author tfossi
 * @version 12.08.2014
 * @modified -
 * @since Java 1.6
 */
public interface IBuildSWTWidget {
	/**
	 * Erstellt und liefert eine neue Widgetinstanz in der Parentgroup <code>grp</code>,
	 * wenn
	 * <ul>
	 * <li>keine existiert oder</li>
	 * <li><code>disposed</code> ist.</li>
	 * </ul>
	 * Sonst wird das vorhandene Widget auf die Gruppe gestellt.
	 * <p>
	 * Wichtig ist es, das es eine MAP ist, da mehrere Widgets auf der gleichen Ebene
	 * plaziert werden können (Buttons bspw.).
	 * </p>
	 * 
	 * @param group
	 *            ist die <code>Group</code>, in der der Widget liegen soll
	 * @return Widgetinstanz
	 */
	public List<Widget> buildSWTWidget(Group group);
}
