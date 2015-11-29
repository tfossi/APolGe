/** 
 * EditorViewName.java
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
public class EditorViewName /*extends EditorView*/ {
//	/* (non-Javadoc)
//	 * @see name.tfossi.apolge.hci.IView#buildSWTWidget(name.tfossi.apolge.hci.IModel, org.eclipse.swt.widgets.Group, org.eclipse.swt.widgets.Widget)
//	 */
//	@Override
//	public Map<String, Widget> buildSWTWidget(IModel model, Group basegrp, Widget widget) {
//		Widget rc = null;
//		if (widget == null || widget.isDisposed()) {
//			logger.debug("SWT::" + this.toString()
//					+ "\tNeue Widgetinstanz auf [" + basegrp + "] anlegen");
//			rc = new List(basegrp, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL
//					| SWT.H_SCROLL);
//			((Control) rc).setLayoutData(new RowData(
//					basegrp.getClientArea().width - 30,
//					basegrp.getClientArea().height - 30));
//			((List) rc).setTopIndex(((List) rc).getItemCount() - 3);
//		} else if (!widget.isDisposed()) {
//			logger.debug("SWT::" + this.toString()
//					+ "\tVorhandene Widgetinstanz auf Gruppe [" + basegrp
//					+ "] gelegt");
//			((Control) widget).setParent(basegrp);
//			return null;
//		} else {
//			assert false : "GANZ DOOF";
//		}
//		return null;
//	}
//
//	/**
//	 * Ausgabe aller Namen
//	 * 
//	 * @param dns
//	 *            der DataNamesSet-Key
//	 */
//	public String[] output(CCName.c name) {
//		String[] output = null;
//		logger.trace("Enter Namenssatz: " + name);
//		if (name == null) {
//			output = new String[] { "Die vorhandenen Namenssätze" + ConstValue.LFCR +  ConstValue.LFCR };
//			CCName.c.values();
//			for (CCName.c n : CCName.c.values()) {
//				if (n.getShow())
//					output[0] += n +  ConstValue.TAB;
//			}
//			output[0] +=  ConstValue.LFCR +  ConstValue.LFCR;
//			logger.trace("Return " + output[0]);
//
//		} else {
//			switch (name) {
////			case man:
////				return DataNamesSet.Man.output();
////			case woman:
////				return DataNamesSet.Woman.output();
////			case clan:
////				return DataNamesSet.Clan.output();
////			case nation:
////				return DataNamesSet.Nation.output();
////			case town:
////				return DataNamesSet.Town.output();
////			case nature:
////				return DataNamesSet.Nature.output();
//			default:
//				assert false;
//				break;
//			}
//		}
//		logger.trace("Return null");
//		return output;
//
//	}
////
////	// ---- Observer -------------------------------------------------------------
////	
////	/*
////	 * (non-Javadoc)
////	 * 
////	 * @see name.tfossi.apolge.hci.AbstractView#update(java.util.Observable,
////	 * java.lang.Object)
////	 */
////	@Override
////	public void update(final Observable editorMenu, final Object notifyScreens) {
////		logger.trace("Enter ");
////		this.show((IMenu) editorMenu, (Screen[])notifyScreens);
////
////		EditorMenu menu = (EditorMenu) editorMenu;
////		// Zur weiteren Bearbeitung
////		super.update(menu, notifyScreens);
////		logger.trace("Exit ");
////	}
//		
//	/* (non-Javadoc)
//	 * @see name.tfossi.apolge.general.gui.IBuildSWTWidget#buildSWTWidget(org.eclipse.swt.widgets.Group)
//	 */
//	@Override
//	public Map<String, Widget> buildSWTWidget(Group group){	
//		for(ICmd cmd : this.statecommandlist){
//			if(cmd instanceof CCName){
//				return ((CCName) cmd).buildParameterWidgets(group);
//			}
//		}	
//		return null;
//	}
//	/* (non-Javadoc)
//	 * @see name.tfossi.apolge.hci.IView#activateC_Widget(org.eclipse.swt.widgets.Widget, java.lang.Object)
//	 */
//	public void activateC_Widget(Widget widget, Object content) {
//		((List)widget).setItems((String[])content);
//	}
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
//	public EditorViewName() {
//		super();
//		// ---- TIPP: VOREINSTELLUNGEN BEI ERSTANLAGE ----------------------------
//		// Ausgeführt bei der ersten Anlage des Viewers
////		logger.debug("Voreinstellungen bei Erstanlage");
////		((IMenu)menu).setInformation(Screen.MI, new String[] { "EditorViewName (init)" }, true, false);
////		((IMenu)menu).setInformation(Screen.C, new String[] { "EditorViewName (init)" }, true, false);
//		// -----------------------------------------------------------------------
//	}
}
