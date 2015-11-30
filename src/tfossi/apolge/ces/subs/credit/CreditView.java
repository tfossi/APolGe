/** 
 * CreditView.java
 * Branch ces
 * APolGe
 * tfossi-team
 * licence GPLv3 
 */
package tfossi.apolge.ces.subs.credit;


import static tfossi.apolge.common.constants.ConstValue.FS;
import static tfossi.apolge.common.constants.ConstValue.LOGGER;
import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;
 
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import org.apache.log4j.Logger;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Widget;

import tfossi.apolge.common.cmd.CommandMaps;
import tfossi.apolge.common.hci.AView;
import tfossi.apolge.io.ContentString;
import tfossi.apolge.io.IContent;
import tfossi.apolge.io.screenfactory.ALoop;
import tfossi.apolge.io.screenfactory.AStorage;
import tfossi.apolge.io.screenfactory.AWidget;
import tfossi.apolge.io.screenfactory.Cntr;

/**
 * Viewer zur Anzeige des Credit-Menü<br>
 * Näheres im {@linkplain AView}
 * 
 * @author tfossi
 * @version 13.08.2014
 * @modified -
 * @since Java 1.6
 */
public class CreditView extends AView {

	{	if (LOGGER)
		System.out.println(this.getClass().getSimpleName()+" V" + serialVersionUID);
	}

	/**
	 * TODO Comment
	 * @return -
	 * @modified - 
	 */
	public IContent getC_GuiContent() {
		// Browser: setUrl
		// List: setItem / setItems
//		FIXME 13.01.2015
		return new ContentString("" /*DEVELOP_PATH + "html" + FS + this.getClass().getSimpleName() + ".html"*/);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see name.tfossi.xapolge200rc.common.hci.IView#activateC_Widget(java.util.Map,
	 * name.tfossi.apolge.common.io.IContent)
	 */
	@Override
	public void activateC_Widget(Map<String, Widget> map, IContent content) {
		if(LOGGER) logger.info(map);
		if(LOGGER) logger.info(Arrays.asList(content.getString()));
		if(LOGGER) logger.info(content.getWidget());
		// assert map!=null;
		// assert false:"content einbauen";
		// if(map.containsKey("C")){
		// ((Browser)map.get("C")).setUrl(content.getString()[0]);
		// ((Browser)map.get("C")).layout();
		// }else
		// assert false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * name.tfossi.xapolge200rc.common.hci.IView#buildCWidget(org.eclipse.swt.widgets.Group
	 * )
	 */
	@Override
	public List<Widget> buildCWidget(Group basegrp) {
		List<Widget> listOfWidgets = new ArrayList<Widget>();
		if(LOGGER) logger.debug("GUI::" + this.toString() + "\tNeue View-Widgetinstanz auf [" + basegrp
				+ "] anlegen");
		Browser browser = new Browser(basegrp, SWT.NONE);
		FormData data = new FormData();
		data.right = new FormAttachment(100, 0);
		data.left = new FormAttachment(0, 0);
		data.top = new FormAttachment(0, 0);
		data.bottom = new FormAttachment(100, 0);
		browser.setLayoutData(data);
		listOfWidgets.add(browser);
		return listOfWidgets;
	}

	// ---- Observer -------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * 
	 * @see name.tfossi.xapolge200rc.common.hci.AView#update(java.util.Observable,
	 * java.lang.Object)
	 */
	@Override
	public void update(final Observable menu, final Object notifyScreens) {
		super.update(menu, notifyScreens);
	}

	// ---- Selbstverwaltung -----------------------------------------------------
	/** serialVersionUID */
	private final static long serialVersionUID = VERSION;
	/** logger */
	private final static Logger logger = Logger.getLogger(CreditView.class.getPackage().getName());

	/**
	 * @see ALoop 
	 * @see AWidget 
	 * @see AStorage 
	 * @param cntr
	 *            ist Fassade zur Screenfactory, in der die Screensteunerng hinterlegt
	 *            ist
	 */
	public CreditView(Cntr cntr) {
		super(cntr, new CommandMaps().fetchList((String[]) null));
		if(LOGGER) logger.debug("Habe Viewer eingerichtet.");
	}

}
//
// public Object outputCentralInformation() {
// if (this.statecontext.isGrafik())
// return this.outputCIGrafik();
// return this.outputCIConsole();
// }

// /**
// * @return Object für die grafische Ausgabe des C-Screen
// */
// private final Object outputCIGrafik() {
// Object o = devPath + "apolge" + FS + "html" + FS + this.getClass().getSimpleName()
// + ".html";
// if(LOGGER) logger.debug("Liefer " + o);
// return o;
// }

// public Object outputCIConsole() {
// return new String[] { "", "CreditView", "" };
// }
// /*
// * (non-Javadoc)
// *
// * @see name.tfossi.apolge201.common.hci.IView#getStateCommandInformation()
// */
// public String[] getStateCommandInformation() {
// return null;
// }
// /*
// * (non-Javadoc)
// *
// * @see name.tfossi.apolge201.common.hci.IView#getCentralInformation()
// */
// public Object[] getCentralInformation() {
// return null;
// }

/*
 * (non-Javadoc)
 * 
 * @see name.tfossi.apolge.hci.IView#activateC_Widget(org.eclipse.swt.widgets.Widget,
 * java.lang.Object)
 */
// public void activateC_Widget(Map<String, Widget> map, Object content) {
// if(LOGGER) logger.info(map);
//		
// if(map.containsKey("List")){
// ((List)map.get("List")).setItems((String[])content);
// }
// }
// /*
// * (non-Javadoc)
// *
// * @see name.tfossi.apolge.hci.IView#buildSWTWidget(name.tfossi.apolge.hci.IModel,
// * org.eclipse.swt.widgets.Group, org.eclipse.swt.widgets.Widget)
// */
// /**
// * @param model
// * @param basegrp
// * @param widget
// * @return
// */
// public Map<String, Widget> buildSWTWidget(@SuppressWarnings("unused") IModel model,
// Group basegrp, Widget widget) {
// assert false;
// final Map<String, Widget> widgets = new HashMap<String, Widget>();
// Widget rc = null;
// if (widget == null || widget.isDisposed()) {
// if(LOGGER) logger.debug("SWT::" + this.toString() + "\tNeue Widgetinstanz auf [" + basegrp
// + "] anlegen");
// rc = new List(basegrp, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL);
// ((Control) rc).setLayoutData(new RowData(basegrp.getClientArea().width - 30, basegrp
// .getClientArea().height - 30));
// ((List) rc).setTopIndex(((List) rc).getItemCount() - 3);
// } else if (!widget.isDisposed()) {
// if(LOGGER) logger.debug("SWT::" + this.toString() + "\tVorhandene Widgetinstanz auf Gruppe ["
// + basegrp + "] gelegt");
// ((Control) widget).setParent(basegrp);
// return widgets;
// } else {
// assert false : "GANZ DOOF";
// }
// return widgets;
// }

// /* (non-Javadoc)
// * @see
// name.tfossi.xapolge200rc.common.hci.AView#buildSWTWidget(org.eclipse.swt.widgets.Group)
// */
// @Override
// public Map<String, Widget> buildSWTWidget(@SuppressWarnings("unused") Group group) {
//
// for (ICmd cmd : this.statecommandlist) {
// if(LOGGER) logger.trace("SWT::\tBaue Parameterwidgets für [" + cmd.getClass().getSimpleName()
// + "] zuammen.");
//
// }
//
// return null; // group;
// }
