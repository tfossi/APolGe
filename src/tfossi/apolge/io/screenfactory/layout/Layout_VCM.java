/**
 * Layout_VCM.java
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
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;

import tfossi.apolge.io.Screen;

/**
 * Layout für View-CommandMenu-Screen
 * 
 * @author tfossi
 * @version 12.08.2014
 * @modified -
 * @since Java 1.6
 */
public class Layout_VCM {
	/** background */
	private final Color background = new Color(Display.getCurrent(), 127, 64, 64);
	/** foreground */
	private final Color foreground = Display.getCurrent().getSystemColor(SWT.COLOR_YELLOW);
	/** ttt */
	private final String ttt = "Spezial Befehle";

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
		data.top = new FormAttachment(groups.get(Screen.MCM), 0, SWT.BOTTOM);

		g.setLayoutData(data);

		// ---- Layout dieser Gruppe festlegen --------------------------------

		RowLayout rowLayout = new RowLayout();
		rowLayout.type = SWT.HORIZONTAL;
		rowLayout.fill = true;
		rowLayout.justify = true;
		rowLayout.wrap = true;
		rowLayout.marginLeft = 2;
		rowLayout.marginRight = 2;
		rowLayout.marginBottom = 2;
		rowLayout.marginTop = 2;
		rowLayout.spacing = 2;
		g.setLayout(rowLayout);

		return g;
	}
}
