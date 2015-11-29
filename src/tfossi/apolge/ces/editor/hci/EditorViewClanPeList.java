/** 
 * EditorViewClanPeList.java
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
public class EditorViewClanPeList /*extends EditorView*/ {
//
//	@SuppressWarnings("boxing")
//	public String[] output(EditorMenu menu, long uid, long gameUID) {
//		logger.trace("Enter GameUID:" + gameUID + " Clanuid: " + uid);
//		MetaData md = MetaData.getInstance();
//		DataGame dgame = md.getDataGame(gameUID);
//		if (dgame == null)
//			return null;
//		DataClan dc = dgame.getDsc().getDataClan(uid);
//		if (dc == null) {
//			return new String[] { "Clan #" + uid + " nicht gefunden" };
//		}
//		DataNation dnation = dgame.getDsc().getDataNation(dc.getSuperUID());
//
//		String[] output = new String[] { "Mitglieder des Clans \'" + dc.getName() + "\'("
//				+ dc.getUid() + ") aus der Nation \'" + dnation.getShortname() + "\'person ["
//				+ dnation.getUid() + "]" + LFCR + LFCR };
//		if (dc.getRegisterUID().size() == 0)
//			output[0] += "keine" + LFCR;
//		else {
//			// output[0] += String.format("%-10s  %3s  ", "Person", "UID");
//			//
//			// output[0] += String.format("%1s ", "G");
//			// output[0] += String.format("%-10.10s ", "Geburtstag");
//			// output[0] += String.format("%12s ", "Stand");
//			// output[0] += String.format("%-10s  %3s  ", "Partner", "UID");
//			// output[0] += String.format("%-10s  %3s  ", "Vater", "UID");
//			// output[0] += String.format("%-10s  %3s  ", "Mutter", "UID");
//			// output[0] += LFCR;
//			for (Long puid : dc.getRegisterUID()) {
//				try {
//					DataPerson dp = dgame.getDsc().getDataPerson(puid.longValue());
//
//					output[0] += String.format("%-10s [%03d] ", dp.getName(), puid);
//					// output[0] += dp.getName() + " : ";
//					output[0] += String.format("%1s ", dp.getGender());
//					if(dp.getDayOfBirth()!=null){
//						dp.getDayOfBirth().dayOfWeek = null;
//						output[0] += String.format("%-10.10s ", dp.getDayOfBirth().getDatetime(DATE));
//					}
////					// Bespiel Filterung
////					if (dgame.getDsc().chkPersonFilter(
////							DataPerson.class.getMethod("getFamilienstand", (Class [])null)))
////						output[0] += String.format("%12s ", dp.getFamilienstand().getText());
//					long partneruid = dp.getPartnerUID();
//					if (partneruid >= 0L) {
//						DataPerson dpartner = dgame.getDsc().getDataPerson(partneruid);
//						output[0] += String.format("%-10s [%03d] ", dpartner.getName(), partneruid);
//					} else
//						output[0] += String.format("%-10s [%03d] ", "", -1L);
////
//					long vateruid = dp.getVaterUID();
//					if (vateruid >= 0L) {
//						DataPerson dpvater = dgame.getDsc().getDataPerson(vateruid);
//						output[0] += String.format("%-10s [%03d] ", dpvater.getName(), vateruid);
//
//					} else
//						output[0] += String.format("%-10s [%03d] ", "", -1L);
//					long mutteruid = dp.getMutterUID();
//					if (mutteruid >= 0L) {
//						DataPerson dpmutter = dgame.getDsc().getDataPerson(mutteruid);
//						output[0] += String.format("%-10s [%03d] ", dpmutter.getName(), mutteruid);
//					} else
//						output[0] += String.format("%-10s [%03d] ", "", -1L);
//					 output[0] += dp.getFamilienstand().getText() + " : ";
//					output[0] += LFCR;
//				} catch (SecurityException e) {
//					ErrApp.NDEF.erraufruf(logger, "");
////				} catch (NoSuchMethodException e) {
////					// Auto-generated catch block
////					e.printStackTrace();
//				}
//			}
//		}
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
//	public EditorViewClanPeList(){
//		super();
//		logger.trace("Constructor");	
//	}
}
