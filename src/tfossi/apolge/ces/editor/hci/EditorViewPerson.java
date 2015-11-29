/** 
 * EditorViewPerson.java
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
public class EditorViewPerson /*extends EditorView*/ {
//
//	public final String[] output(EditorMenu menu, long uid, long gameUID) {
//		logger.trace("Enter GameUID:" + gameUID + " Personuid: " + uid);
//		MetaData md = MetaData.getInstance();
//		DataGame dgame = md.getDataGame(gameUID);
//		if (dgame == null)
//			return null;
//		DataPerson dp = dgame.getDsc().getDataPerson(uid);
//		if (dp == null) {
//			return new String[] { "Person #" + uid + " nicht gefunden" };
//		}
//		DataClan dclan = dgame.getDsc().getDataClan(dp.getSuperUID());
//		DataNation dnation = dgame.getDsc().getDataNation(dclan.getSuperUID());
//		String[] output = new String[] { "Daten von \'" + dp.getName() + "\'(" + dp.getUid() + ")"
//				+ LFCR + " im Clan \"" + dclan.getShortname() + "\" [" + dclan.getUid() + "]"
//				+ LFCR + "aus der Nation \"" + dnation.getShortname() + "\" [" + dnation.getUid()
//				+ "]"
//
//				+ LFCR + LFCR };
//		 output[0] += "       Datensatz gespeichert: " + (dgame.isSaved() ? "JA" : "NEIN")
//		 + LFCR;
//		 output[0] += "       Datensatz vollständig: " + (dp.tstReady() ? "JA" :
//		 "NEIN") + LFCR;
//		 output[0] += "(n $)                   Name: " + dp.getName() + LFCR;
//		 output[0] += "(r $)                       : " + dp.getShortname() + LFCR;
//		 output[0] += "-                Geschlecht : " + dp.getGender().text() + LFCR;
//		 output[0] += "-                Geburtstag : " + dp.getDayOfBirth().getDatetime(DATE) + LFCR;
//		 output[0] += LFCR;
//		return  output;
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
//	public EditorViewPerson(){
//		super();
//		logger.trace("Constructor");	
//	}
}
