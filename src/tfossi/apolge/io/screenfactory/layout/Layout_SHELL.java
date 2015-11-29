/**
 * Layout_SHELL.java
 * Branch io
 * APolGe
 * tfossi-team
 * licence GPLv3  
 */
package tfossi.apolge.io.screenfactory.layout;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;

import tfossi.apolge.io.Screen;

/**
 * TODO Comment
 *
 * @author tfossi
 * @version 12.08.2014
 * @modified -
 * @since Java 1.6
 */
public class Layout_SHELL {
	
	/** background */
	private final Color background = new Color(Display.getCurrent(), 100, 100, 150);
	/** foreground */
	private final Color foreground = new Color(Display.getCurrent(), 200, 200, 50);
	/** font */
	private final Font font = new Font(Display.getCurrent(),"Arial",12,SWT.ITALIC);
	/** ttt */
	private final String ttt =  "... aus aller Welt";
	
	/**
	 * TODO Comment
	 * @param shell -
	 * @param groups -
	 * @param titel -
	 * @return -
	 * @modified - 
	 */
	public Group doIt(Shell shell, Map<Screen, Group> groups, String titel){
//		 Grösse des Screen orientiert sich an der Monitorauflösung
		shell.setSize(3*Display.getCurrent().getPrimaryMonitor().getClientArea().width /10, 8*Display.getCurrent()
				.getPrimaryMonitor().getClientArea().height/10);
		shell.setLocation(0,0);
		shell.setBackground(this.background);
		shell.setForeground(this.foreground);
		shell.setToolTipText(this.ttt);
		shell.setFont(this.font);
		// Formlayout anwenden. Damit werden die Bildschirmgruppen flexibel
		// gruppiert
		FormLayout formLayout = new FormLayout();
		formLayout.marginLeft = 10;
		formLayout.marginRight = 10;
		formLayout.marginTop = 5;
		formLayout.marginBottom = 10;
		formLayout.spacing = 10;
		shell.setLayout(formLayout);
		return null;
	}
}
