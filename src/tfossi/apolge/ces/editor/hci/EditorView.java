/** 
 * EditorView.java
 * Branch ces
 * APolGe
 * tfossi-team
 * licence GPLv3 
 */
package tfossi.apolge.ces.editor.hci;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;


import org.apache.log4j.Logger;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Widget;

import static tfossi.apolge.common.constants.ConstValue.*;
import static tfossi.apolge.common.constants.ConstValueExtension.*;
import tfossi.apolge.common.cmd.CommandMaps;
import tfossi.apolge.common.hci.AView;
import tfossi.apolge.common.hci.IModel;
import tfossi.apolge.io.IContent;
import tfossi.apolge.io.screenfactory.Cntr;

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
public class EditorView extends AView {

	/**
	 * TODO Comment
	 * @param model -
	 * @param basegrp -
	 * @param widget -
	 * @return -
	 * @modified - 
	 */
	public Map<String, Widget> buildSWTWidget(IModel model, Group basegrp, Widget widget) {
		final Map<String, Widget> widgets = new HashMap<String, Widget>();
		
		if (widget == null || widget.isDisposed()) {
			if(LOGGER) logger.debug("SWT::" + this.toString()
					+ "\tNeue Widgetinstanz auf [" + basegrp + "] anlegen");
			Browser browser = new Browser(basegrp, SWT.NONE);			
			FormData data = new FormData();
			data.right = new FormAttachment(100, 0);
			data.left = new FormAttachment(0, 0);
			data.top = new FormAttachment(0, 0);
			data.bottom = new FormAttachment(100, 0);			
			browser.setLayoutData(data);
			widgets.put("Browser", browser);
			
		} else if (!widget.isDisposed()) {
			if(LOGGER) logger.debug("SWT::" + this.toString()
					+ "\tVorhandene Widgetinstanz auf Gruppe [" + basegrp
					+ "] gelegt");
			((Control) widget).setParent(basegrp);
			return widgets;
		} else {
			assert false : "GANZ DOOF";
		}
		return widgets;
	}

	/**
	 * TODO Comment
	 * @param map -
	 * @param content -
	 * @modified - 
	 */
	public void activateC_Widget(Map<String, Widget> map, Object content) {
		if(LOGGER) logger.info(map);
//		FIXME 13.01.2015
		if(map.containsKey("Browser")){
			((Browser)map.get("Browser")).setUrl("" /*DEVELOP_PATH+"html"+FS+this.getClass().getSimpleName()+".html"*/);
		}
		
		
	}

//	/* (non-Javadoc)
//	 * @see name.tfossi.apolge200.common.hci.IView#output()
//	 */
//	public Object outputCentralInformation(){
//		if(this.statecontext.isGrafik())
//			return this.outputCIGrafik();
//		return this.outputCIConsole();
//	}
//	
//	/* (non-Javadoc)
//	 * @see name.tfossi.apolge200.common.hci.IView#outputGrafik()
//	 */
//	private final Object outputCIGrafik() {
//		return (Object)devPath+"html"+FS+this.getClass().getSimpleName()+".html";
//	}
//	private final Object outputCIConsole() {
//		return new String[] { "TEST", " TEST", "  TEST" };
//	}

	// ---- Observer -------------------------------------------------------------

	/* (non-Javadoc)
	 * @see tfossi.apolge.common.hci.AView#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(final Observable editorMenu, final Object notifyScreens) {
		if(LOGGER) logger.trace("Enter ");
		EditorMenu menu = (EditorMenu) editorMenu;
		// Zur weiteren Bearbeitung
		super.update(menu, notifyScreens);
		if(LOGGER) logger.trace("Exit ");
	}


	// ---- Selbstverwaltung -----------------------------------------------------
	/** serialVersionUID */
	@SuppressWarnings("unused")
	private final static long serialVersionUID = VERSION;
	/** logger */
	private final static Logger logger = Logger.getLogger(EditorView.class.getPackage().getName());

	/**
	 * Datenaus- und -eingabe zur Bedienung des EditorMenus. Ist Viewer im MVP.
	
	 * TODO Comment
	 * @param cntr -
	 * @modified -
	 */
	public EditorView(Cntr cntr) {
		super(cntr, new CommandMaps().fetchList((String[]) null));
		if(LOGGER) logger.trace("Habe Viewer eingerichtet.");
	}
//
//
//	/* (non-Javadoc)
//	 * @see name.tfossi.apolge201.common.hci.IView#getCentralInformation()
//	 */
//	public Object[] getCentralInformation() {
//		// TODO Auto-generated method stub
//		return null;
//	}



	/* (non-Javadoc)
	 * @see tfossi.apolge.common.hci.IView#buildCWidget(org.eclipse.swt.widgets.Group)
	 */
	@Override
	public List<Widget> buildCWidget(Group basegrp) {
		// TODO Auto-generated method stub
		return null;
	}

//
//	/* (non-Javadoc)
//	 * @see name.tfossi.apolge201.common.hci.IView#getStateCommandInformation()
//	 */
//	public String[] getStateCommandInformation() {
//		// TODO Auto-generated method stub
//		return null;
//	}


	/* (non-Javadoc)
	 * @see tfossi.apolge.common.hci.IView#activateC_Widget(java.util.Map, tfossi.apolge.io.IContent)
	 */
	@Override
	public void activateC_Widget(Map<String, Widget> map, IContent content) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * TODO Comment
	 * @return -
	 * @modified - 
	 */
	@SuppressWarnings("static-method")
	public IContent getC_GuiContent() {
		// TODO Auto-generated method stub
		return null;
	}
}
