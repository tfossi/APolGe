/** 
 * EditorViewNation.java
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
public class EditorViewNation /*extends EditorView*/ {
//
//	public String[] output(EditorMenu menu, long uid, long gameUID) {
//		logger.trace("Enter GameUID:" + gameUID + " NationUID:"+uid);
//		MetaData md = MetaData.getInstance();
//		DataGame dgame = md.getDataGame(gameUID);
//		if (dgame == null)
//			return null;
//		DataNation dn = dgame.getDsc().getDataNation(uid);
//		if (dn == null) {
//			return new String[] { "Nation #" + uid + " nicht gefunden" };
//		}
//		String[] output = new String[] { "Daten von \'" + dn.getName() + "\'(" + dn.getUid()
//				+ ") im Spiel \"" + dgame.getName() + "\"[" + dgame.getShortname() + "]" + LFCR
//				+ LFCR };
//		 output[0] += "Datensatz gespeichert: " + (dgame.isSaved() ? "JA" : "NEIN")
//		 + LFCR;
//		output[0] += "Datensatz vollständig: " + (dn.tstReady() ? "JA" : "NEIN") + LFCR;
//		output[0] += "Name                 : " + dn.getName() + LFCR;
//		output[0] += ".                    : " + dn.getShortname() + LFCR;
//		output[0] += "SuperUID             : " + dn.getSuperUID() + LFCR;
//		// output[0] += "(d $)           Beschreibung:" + LFCR + dn.getDescription() +
//		// LFCR;
//		output[0] += "Anzahl der Clans     : " + dn.getCounterRegister() + LFCR;
//		int countEinwohner = 0;		
//		for (Long cuid : dn.getRegisterUID()) {			
//			DataClan dc = dgame.getDsc().getDataClan(cuid.longValue());
//			countEinwohner += dc.getRegisterUID().size();
//			output[0] += dc.getShortname()+" [" + cuid +"]"+TAB;
//		 }
//		output[0] += LFCR;
//		output[0] += "Anzahl der Einwohner : " + countEinwohner + LFCR;
//		// output[0] += "Autor       : "+ dg.getAuthor().getName();
//		// output[0] += "Leader      : "+ dg.getLeader().getName();
//		// output[0] += "(s)             Start am : " +
//		// ConstMethod.toString(dg.getStartdate()) + TAB;
//		// output[0] += " (Aktuell: " + ConstMethod.toString(dg.getActualdate()) + ")" +
//		// LFCR;
//		output[0] += LFCR;
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
//	public EditorViewNation(){
//		super();
//		logger.trace("Constructor");	
//	}
}
