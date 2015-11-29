/**
 * Layout_M.java
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
import static tfossi.apolge.common.constants.ConstValueExtension.*;

import tfossi.apolge.io.Screen;

/**
 * Layout für Message-Screen
 * 
 * @author tfossi
 * @version 12.08.2014
 * @modified -
 * @since Java 1.6
 */
public class Layout_M {
	/** background */
	private final Color background = Display.getCurrent().getSystemColor(SWT.COLOR_INFO_BACKGROUND);
	/** foreground */
	private final Color foreground = Display.getCurrent().getSystemColor(SWT.COLOR_INFO_FOREGROUND);
	/** ttt */
	private final String ttt = "... aus aller Welt";

	/**
	 * TODO Comment
	 * @param shell -
	 * @param groups -
	 * @param titel -
	 * @return -
	 * @modified - 
	 */
	public Group doIt(Shell shell, Map<Screen, Group> groups,
			String titel) {
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
		data.width = 0; //stddbwidth;
//		FIXME 13.01.2015
		data.height = 0; //stdhalfheight;
		data.right = new FormAttachment(100, 0);
		data.bottom = new FormAttachment(100, 0);

		g.setLayoutData(data);

		// ---- Layout dieser Gruppe festlegen --------------------------------

		RowLayout rowLayout = new RowLayout();
		rowLayout.pack = false;
		rowLayout.type = SWT.VERTICAL;
		rowLayout.marginLeft = 5;
		rowLayout.marginRight = 5;
		rowLayout.marginBottom = 5;
		rowLayout.marginTop = 5;
		rowLayout.spacing = 1;
		g.setLayout(rowLayout);

		return g;
	}
}
