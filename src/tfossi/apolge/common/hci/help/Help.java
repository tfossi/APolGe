/**
 * Help.java
 * Branch hci
 * APolGe
 * tfossi-team
 * licence GPLv3  
 */
package tfossi.apolge.common.hci.help;

import static tfossi.apolge.common.constants.ConstValue.LOGGER;
import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;

import org.apache.log4j.Logger;




/**
 * Help ist für alle Hilfefunktionen zuständig
 * 
 * @author tfossi
 * @version 12.08.2014
 * @modified -
 * @since Java 1.6
 */
public class Help{
//	/** GUI-Hilfe beenden. */
//	boolean exit = false;
//
//	/**
//	 * Stellt die Hilfe da
//	 * 
//	 * @param what
//	 *        ist das Thema der Hilfe
//	 * @param swt
//	 *        Bei GUI ist eine SWTGrafik-Instanz vorhanden, bei Console <code>null</code>
//	 */
//	public final void doIt(final Queue<Object> what, final SWTGrafik swt) {
//		FileInputStream fis = null;
//		StringBuffer filename = new StringBuffer(installPath + "html/");
//		if (what.isEmpty()) {
//			if(LOGGER) logger.trace("Enter ");
//			if(LOGGER) logger.debug("Hilfeübersicht");
//			filename.append("help.html");
//		} else {
//			if(LOGGER) logger.trace("Enter (" + what.element() + ")");
//			if(LOGGER) logger.debug("Hilfe für " + what.element() + ".html");
//			filename.append(what.poll() + "Help.html");
//		}
//		this.exit = false;
//		if (swt != null) {
////			assert false;
//
//			// Neue Dialog-Shell erzeugen
//			final Shell dialogshell = new Shell(swt.getShell(), SWT.DIALOG_TRIM
//			        | SWT.APPLICATION_MODAL);
//			FormLayout formLayout = new FormLayout();
//			formLayout.marginLeft = 10;
//			formLayout.marginRight = 10;
//			formLayout.marginTop = 5;
//			formLayout.marginBottom = 10;
//			dialogshell.setLayout(formLayout);
//			// Dialogtitel auf Shell-Titel übertragen
//			dialogshell.setText("Hilfe");
//			dialogshell.setBounds(swt.getShell().getBounds());
//
//			Button button1 = new Button(dialogshell, SWT.PUSH);
//			button1.setText("Exit");
//			FormData data = new FormData();
//			data.width = 80;
//			data.height = 40;
//			data.bottom = new FormAttachment(100, 0);
//			// data.bottom = new FormAttachment (40, 0);
//			// data.left = new FormAttachment (shell.getBounds().width/2-40,
//			// shell.getBounds().width/2+40);
//
//			button1.setLayoutData(data);
//			button1.setSize(75, 30);
//			button1.addSelectionListener(new SelectionAdapter() {
//				@Override
//				public void widgetSelected(SelectionEvent e) {
//					Help.this.exit = true;
//				}
//			});
//
//			final Browser browser;
//			try {
//
//				browser = new Browser(dialogshell, SWT.NONE);
//				FormData data2 = new FormData();
//				data2.width = dialogshell.getBounds().width;
//				data2.height = dialogshell.getBounds().height;
//				data2.bottom = new FormAttachment(button1, SWT.TOP);
//				browser.setLayoutData(data2);
//				try {
//					fis = new FileInputStream(filename.toString());
//				} catch (FileNotFoundException e1) {
//
//					filename = new StringBuffer(installPath + "/html/help.html");
//
//				}
//				browser.setUrl(filename.toString());
//				browser.setBounds(dialogshell.getBounds());
//			} catch (SWTError e) {
//				MessageBox messageBox = new MessageBox(swt.getShell(), SWT.ICON_ERROR | SWT.OK);
//				messageBox.setMessage("Browser cannot be initialized.");
//				messageBox.setText("Exit");
//				messageBox.open();
//				System.exit(-1);
//			}
//
//			dialogshell.open();
//			while (!swt.getShell().isDisposed() && !this.exit) {
//				if (!swt.getShell().getDisplay().readAndDispatch())
//					swt.getShell().getDisplay().sleep();
//			}
//			dialogshell.dispose();
//
//		} else {
//			try {
//				fis = new FileInputStream(filename.toString());
//			} catch (FileNotFoundException e1) {
//				if(LOGGER) logger.warn("Die Hilfeseite " + filename.toString() + " existiert nicht.");
//				try {
//					fis = new FileInputStream(installPath + "/html/help.html");
//				} catch (FileNotFoundException e) {
//					e.printStackTrace();
//				}
//			}
//			String str = new String();
//			StringBuffer sb = new StringBuffer();
//			try {
//				InputStream in = new BufferedInputStream(fis);
//				InputStreamReader isr = new InputStreamReader(in);
//				BufferedReader d = new BufferedReader(isr);
//				while ((str = d.readLine()) != null) {
//					sb.append(str);
//				}
//				ConstMethod.html2txt(sb); // HTML-Text in TEXT wandeln
//				System.out.println("" + sb);
//			} catch (FileNotFoundException e) {
//				// Geht daneben
//				e.printStackTrace();
//				System.exit(0);
//			} catch (IOException e) {
//				// Geht daneben
//				e.printStackTrace();
//				System.exit(0);
//			} finally {
//				if (fis != null)
//					try {
//						fis.close();
//					} catch (IOException e) {
//						// leer
//					}
//			}
//		}
//		if(LOGGER) logger.trace("Exit ");
//	}
//
	// ---- Selbstverwaltung -----------------------------------------------------
	/** serialVersionUID */
	@SuppressWarnings("unused")
	private final static long serialVersionUID = VERSION;
	/** logger */
	private final static Logger logger = Logger.getLogger(Help.class.getPackage().getName());
	/**
	 * TODO Comment
	 * @modified -
	 */
	public Help(){
		if(LOGGER) logger.info("Nicht implementiert!");
//		ErrApp.NI_W.erraufruf("");
		
	}
}
