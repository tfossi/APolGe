/**
 * Layout_MI.java
 * Branch io
 * APolGe
 * tfossi-team
 * licence GPLv3  
 */
package tfossi.apolge.io.screenfactory.layout;


import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;

import tfossi.apolge.io.Screen;

/**
 * Layout für Menu-Information-Screen
 * 
 * @author tfossi
 * @version 12.08.2014
 * @modified -
 * @since Java 1.6
 */
public class Layout_MI {
	/** background */
	private final Color background = Display.getCurrent().getSystemColor(SWT.COLOR_DARK_MAGENTA);
	/** foreground */
	private final Color foreground = Display.getCurrent().getSystemColor(SWT.COLOR_WHITE);
	/** ttt */
	private final String ttt = "... von wichtigen";

	/**
	 * TODO Comment
	 * @param shell -
	 * @param groups -
	 * @param titel -
	 * @return -
	 * @modified - 
	 */
	public Group doIt(Shell shell, Map<Screen, Group> groups, String titel) {
		// ---- Erzeuge eine Gruppe -------------------------------------------

		Group g = new Group(shell, SWT.NONE);
		g.setText(titel);
		g.setBackground(this.background);
		g.setForeground(this.foreground);
		g.setFont(shell.getFont());
		g.setToolTipText(this.ttt);

		// ---- Ordne die Gruppe auf dem Screen ein ---------------------------

		FormData data = new FormData();
//		FIXME 13.01.2015
		data.width = 0; //stdwidth;
		data.right = new FormAttachment(100, 0);
		data.top = new FormAttachment(groups.get(Screen.CPM), 0, SWT.BOTTOM);
		data.bottom = new FormAttachment(groups.get(Screen.M), 0, SWT.TOP);
		g.setLayoutData(data);

		// ---- Layout dieser Gruppe festlegen --------------------------------

		FormLayout formLayout = new FormLayout();
		formLayout.marginLeft = 0;
		formLayout.marginRight = 0;
		formLayout.marginTop = 0;
		formLayout.marginBottom = 0;
		formLayout.spacing = 10;
		g.setLayout(formLayout);

		return g;
	}
}
