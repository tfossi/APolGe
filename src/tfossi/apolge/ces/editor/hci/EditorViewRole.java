/** 
 * EditorViewRole.java
 * Branch ces
 * APolGe
 * tfossi-team
 * licence GPLv3 
 */
package tfossi.apolge.ces.editor.hci;




/**
 * Datenaus- und -eingabe zur Bedienung des Editormenus. Ist Viewer im MVC
 * 
 * @.pattern MVC: concrete viewer
 * @.pattern Observer (EditorMenu)
 * @see EditorMenu
 * @see EditorModel
 * TODO Comment
 *
 * @author tfossi
 * @version 13.08.2014
 * @modified -
 * @since Java 1.6
 */
public class EditorViewRole /*extends EditorView*/ {
//	@SuppressWarnings("boxing")
//	public String[] output(EditorMenu menu, long uid) {
//		logger.trace("Enter Playeruid: " + uid);
//		MetaData md = MetaData.getInstance();
//		if (uid < 0L) {
//			String[] output = new String[] { "Die angelegten Playertemplates" + LFCR + LFCR };
//			for (long playerUID : md.getDataRole()) {
//				String name = md.getDataRole(playerUID).getShortname();
//				if (md.getDataRole(playerUID).tstReady())
//					output[0] += name + "[" + playerUID + "]" + TAB;
//				else {
//					output[0] += name + "[" + playerUID + "]°" + TAB;
//				}
//			}
//			output[0] += LFCR + LFCR;
//			logger.trace("Return " + output[0]);
//			return output;
//		}
//
//
//		DataRole dp = md.getDataRole(uid);
//		String[] output = new String[] { "Daten von \'" + dp.getName() + "\'(" + dp.getUid() + ")"
//				+ LFCR + LFCR };
//		output[0] += "-      Datensatz gespeichert: " + (dp.isSaved() ? "JA" : "NEIN") + LFCR;
//		output[0] += "-      Datensatz vollständig: " + (dp.tstReady() ? "JA" : "NEIN") + LFCR;
//		output[0] += "-                      Human: " + (dp.isHuman() ? "JA" : "NEIN") + LFCR;
//		output[0] += "-                       Name: " + dp.getName() + LFCR;
//		output[0] += "-                           : " + dp.getShortname() + LFCR;
//		output[0] += LFCR;
//		logger.trace("Return " + output[0]);
//		return output;
//	}
//
//	
//	// ---- Selbstverwaltung -----------------------------------------------------
//	private final static long serialVersionUID = ConstValue.VERSION;
//	private final static Logger logger = Logger.getLogger("name.tfossi.apolge.hci.editor");
//
//	/**
//	 * Datenaus- und -eingabe zur Bedienung des EditorMenus. Ist Viewer im MVP.
//	 * 
//	 * @param menu
//	 *            ist zu beobachtendes Object {@link EditorMenu}. Sobald sich dieses
//	 *            ändert wird die Anzeige aktualisiert.
//	 * @param statecontext
//	 *            ist der IStateContext.
//	 */
//	public EditorViewRole(){
//		super();
//		logger.trace("Constructor");	
//	}
}
