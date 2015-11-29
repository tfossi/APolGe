/** 
 * ServerView.java
 * Branch ces
 * APolGe
 * tfossi-team
 * licence GPLv3 
 */
package tfossi.apolge.ces.server.hci;

import static tfossi.apolge.common.constants.ConstValue.*;
import static tfossi.apolge.common.constants.ConstValueExtension.*;

import java.util.Arrays;
import java.util.Map;
import java.util.Observable;


import org.apache.log4j.Logger;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Widget;

import tfossi.apolge.ces.editor.hci.EditorMenu;
import tfossi.apolge.ces.editor.hci.EditorModel;
import tfossi.apolge.common.cmd.CommandMaps;
import tfossi.apolge.common.error.ErrApp;
import tfossi.apolge.common.hci.AView;
import tfossi.apolge.io.ContentString;
import tfossi.apolge.io.ContentWidget;
import tfossi.apolge.io.IContent;
import tfossi.apolge.io.Screen;
import tfossi.apolge.io.screenfactory.Cntr;

/**
 * Datenaus- und -eingabe des SessionManagers
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
public class ServerView extends AView {


	/* (non-Javadoc)
	 * @see tfossi.apolge.common.hci.AView#buildVIWidget(org.eclipse.swt.widgets.Group)
	 */
	@Override
	public Widget buildVIWidget(Group basegrp) {
		if(LOGGER) logger.debug("SWT::" + this.toString() + "\tNeue Widgetinstanz auf [" + basegrp
				+ "] anlegen");
		List rc = new List(basegrp, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL);

		FormData data = new FormData();
		data.right = new FormAttachment(100, 0);
		data.left = new FormAttachment(0, 0);
		data.top = new FormAttachment(0, 0);
		data.bottom = new FormAttachment(100, 0);
		((Control) rc).setLayoutData(data);
		return rc;
	}


	/* (non-Javadoc)
	 * @see tfossi.apolge.common.hci.AView#get_GuiContent(org.eclipse.swt.widgets.Widget, tfossi.apolge.io.Screen)
	 */
	@Override
	public IContent get_GuiContent(Widget base, Screen scr) {

		switch (scr) {
		case VI:
			java.util.List<Widget> listOfWidgets = new java.util.ArrayList <> ();			
			List rc = (List) base;
			rc.setItems(new String[] { "HALLO from Vienna" });
			rc.setTopIndex(rc.getItemCount() - 3);
			listOfWidgets.add(rc);
			return new ContentWidget(listOfWidgets);
		case TITEL:			
			return null;
		case VCM:			
			return null;
		case C:
//			FIXME 13.01.2015
			return new ContentString("" /*DEVELOP_PATH + "html" + FS + this.getClass().getSimpleName() + ".html"*/);
		default:
			ErrApp.NI_W.erraufruf("SWT::" + this.toString() + "\tContent abholen" + NTAB
					+ "für " + scr + NTAB + "auf Base " + base);
		}
		return null;
	}

	
	/* (non-Javadoc)
	 * @see tfossi.apolge.common.hci.IView#buildCWidget(org.eclipse.swt.widgets.Group)
	 */
	@Override
	public java.util.List<Widget> buildCWidget(
	/* @SuppressWarnings("unused") IModel model, */Group basegrp
	/* , Widget widget */) {
		java.util.List<Widget> listOfWidgets = new java.util.ArrayList<>();
		Browser browser = new Browser(basegrp, SWT.NONE);
		FormData data = new FormData();
		data.right = new FormAttachment(100, 0);
		data.left = new FormAttachment(0, 0);
		data.top = new FormAttachment(0, 0);
		data.bottom = new FormAttachment(100, 0);
		browser.setLayoutData(data);
		listOfWidgets.add(browser);
		if(LOGGER) logger.debug("GUI:: Neue Widgetinstanz [" + listOfWidgets + "]" + NTAB
				+ "für Screen [C]" + NTAB + "auf [" + basegrp + "] anlegen");
		return listOfWidgets;
	}

	// /*
	// * (non-Javadoc)
	// *
	// * @see
	// *
	// name.tfossi.apolge200.common.hci.AView#buildSWTWidget(org.eclipse.swt.widgets.Group
	// * )
	// */
	// @Override
	// public Map<String, Widget> buildSWTWidget(Group group) {
	// assert false;
	// for (ICmd cmd : this.statecommandlist) {
	// if(LOGGER) logger.trace("SWT::\tBaue Parameterwidgets für [" + cmd.getClass().getSimpleName()
	// + "] zuammen.");
	// if (cmd instanceof CSPara) {
	// return ((CSPara) cmd).buildParameterWidgets(group);
	// }
	// }
	//
	// return null; // group;
	// }


	/* (non-Javadoc)
	 * @see tfossi.apolge.common.hci.IView#activateC_Widget(java.util.Map, tfossi.apolge.io.IContent)
	 */
	@Override
	public void activateC_Widget(Map<String, Widget> map, IContent content) {
		if(LOGGER) logger.info(map);
		if(LOGGER) logger.info(Arrays.asList(content.getString()));
		if(LOGGER) logger.info(content.getWidget());
		// assert map!=null;

		assert map.containsKey("C");
		((Browser) map.get("C")).setUrl(content.getString()[0]);
		// ((Browser)map.get("C")).layout();
		// }else
		// assert false;
	}

//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see name.tfossi.xapolge201rc.hci.IView#getC_GuiContent()
//	 */
//	public IContent getC_GuiContent() {
//		// Browser: setUrl
//		// List: setItem / setItems
//		return new ContentString(devPath + "html" + FS + this.getClass().getSimpleName() + ".html");
//	}

	// /*
	// * (non-Javadoc)
	// *
	// * @see name.tfossi.apolge200.common.hci.IView#getCentralInformation()
	// */
	// public Object[] getCentralInformation() {
	// assert false;
	// if (this.statecontext.isGrafik())
	// return this.outputCIGrafik();
	// return this.outputCIConsole();
	// }
	//
	// /**
	// * @return Object für die grafische Ausgabe des C-Screen
	// */
	// private final Object[] outputCIGrafik() {
	// assert false;
	// Object[] o = new Object[] { devPath + "apolge" + FS + "html" + FS
	// + this.getClass().getSimpleName() + ".html" };
	// if(LOGGER) logger.debug("Liefer " + o);
	// return o;
	// }
	//
	// /**
	// * @return String [] für die textuale Ausgabe des C-Screen
	// */
	// private final Object[] outputCIConsole() {
	// return new String[] { "TEST", " TEST", "  TEST" };
	// }

	// private final Object outputGIConsole(){
	// return new String[] { "MI", " MI", "  MI" };
	// }

	// ---- Observer -------------------------------------------------------------
	/*
	 * (non-Javadoc)
	 * 
	 * @see name.tfossi.apolge.hci.AbstractView#update(java.util.Observable,
	 * java.lang.Object)
	 */
	@Override
	public void update(final Observable editorMenu, final Object notifyScreens) {
		ServerMenu menu = (ServerMenu) editorMenu;
		// Zur weiteren Bearbeitung
		super.update(menu, notifyScreens);
	}

	// ---- Selbstverwaltung -----------------------------------------------------
	/** serialVersionUID */
	@SuppressWarnings("unused")
	private final static long serialVersionUID = VERSION;
	/** logger */
	private final static Logger logger = Logger.getLogger(ServerView.class.getPackage().getName());

	/**
	 *
	 * TODO Comment
	 * @param cntr -
	 * @modified -
	 */
	public ServerView(Cntr cntr) {
		// , "Name", "Game",
		// "Role", "Nation", "Clan", "Person");

		super(cntr, new CommandMaps().fetchList(SERVERVIEW));
		if(LOGGER) logger.trace("Habe Viewer eingerichtet.");
		// ((IMenu) menu).setCentral(new Central(new String[] { "EDITOR V1.0",
		// "Pforsicht!" }));
	}

	// /*
	// * (non-Javadoc)
	// *
	// * @see name.tfossi.apolge201.common.hci.IView#getStateCommandInformation()
	// */
	// public String[] getStateCommandInformation() {
	// // TODO Auto-generated method stub
	// return null;
	// }
}
