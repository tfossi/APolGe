/**
 * GuiWidget.java
 * Branch io
 * APolGe
 * tfossi-team
 * licence GPLv3  
 */
package tfossi.apolge.io.screenfactory;

import static tfossi.apolge.common.constants.ConstMethod.getScreens;
import static tfossi.apolge.common.constants.ConstValue.LOGGER;
import static tfossi.apolge.common.constants.ConstValue.NTAB;
import static tfossi.apolge.common.constants.ConstValueExtension.APPLICATIONSCREENS;
import static tfossi.apolge.common.constants.ConstValueExtension.MENUSCREENS;
import static tfossi.apolge.common.constants.ConstValueExtension.PARAMETERSCREENS;
import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;
import static tfossi.apolge.common.constants.ConstValueExtension.VIEWSCREENS;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Widget;

import tfossi.apolge.common.hci.IMenu;
import tfossi.apolge.common.hci.IModel;
import tfossi.apolge.common.hci.IView;
import tfossi.apolge.io.ContentWidget;
import tfossi.apolge.io.IContent;
import tfossi.apolge.io.Screen;

/**
 * GUI-Steuerung für GUI
 * 
 * @.pattern Abstract Factory: Concrete Product
 * @see Cntr
 * @see AFactory
 * @see AWidget
 * @author tfossi
 * @version 12.08.2014
 * @modified -
 * @since Java 1.6
 */
public class GuiWidget extends AWidget {

	/** widget */
	private final Map<Screen, Map<Class<?>, Map<String, Widget>>> widget = new HashMap<Screen, Map<Class<?>, Map<String, Widget>>>();

	/** Nimmt die Fassade auf */
	private final Cntr facade;

	/** Speichert die SWT-Instanz */
	private SWTGrafik swt;

	/*
	 * (non-Javadoc)
	 * 
	 * @see name.tfossi.apolge.io.screenfactory.AWidget#layout()
	 */
	@Override
	final void layout() {
		this.swt.getShell().layout();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see name.tfossi.apolge.io.screenfactory.AWidget#getCPMBaseGroup()
	 */
	@Override
	final Group getCPMBaseGroup() {
		return this.getBaseGroup(Screen.CPM);
	}

	/**
	 * @param scr
	 *            aktueller Screen
	 * @return die Group des Screen
	 */
	private final Group getBaseGroup(Screen scr) {
		return this.swt.getBaseGroup(scr);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seename.tfossi.apolge.io.screenfactory.AWidget#initApplWidgets(name.tfossi.
	 * xapolge201rc.hci.IModel)
	 */
	@Override
	final void initApplWidgets(IModel model) {
		if(LOGGER) logger.trace("GUI::Initiiere APPLICATIONSCREENS");
		for (Screen scr : APPLICATIONSCREENS) {
			
			this.storeWidget(
					null, 
					null, 
					scr.buildWidget(null, model, this.getBaseGroup(scr)), 
					scr);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seename.tfossi.apolge.io.screenfactory.AWidget#initMenuWidgets(name.tfossi.
	 * xapolge201rc.hci.IMenu)
	 */
	@Override
	final void initMenuWidgets(IMenu menu) {
		if(LOGGER) logger.trace("GUI:: Initiiere MENUSCREENS für [" + menu.getClass().getSimpleName() + "]");
		for (Screen scr : MENUSCREENS) {
			if(LOGGER) logger.trace("GUI:: Check + initiate [" + scr + "]");
			if (this.isInitiate_Menu(menu.getClass(), scr)) {

				this.visibleMenuScreen(menu.getClass());
				if(LOGGER) logger.trace("GUI:: VISIBLE!");
				continue;
			}
			if(LOGGER) logger.trace("GUI:: NEU ANLEGEN!");
//			Map<String, Widget> widgetMap = scr.buildWidget(null, menu.getModel(), this
//					.getBaseGroup(scr));
			this.storeWidget(
					null, 
					menu, 
					scr.buildWidget(null, menu.getModel(), this
							.getBaseGroup(scr)), 
					scr);
		}
		this.layout();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seename.tfossi.apolge.io.screenfactory.AWidget#initViewWidgets(name.tfossi.
	 * xapolge201rc.hci.IView, name.tfossi.xapolge201rc.hci.IMenu)
	 */
	@Override
	final void initViewWidgets(IView view, IMenu menu) {
		if(LOGGER) logger.trace("GUI:: Initiiere VIEWSCREENS für [" + view.getClass().getSimpleName() + "]");
		for (Screen scr : getScreens(VIEWSCREENS)) {
			if(LOGGER) logger.trace("GUI:: Check + initiate [" + scr + "]");
			if (this.isInitiate_View(view.getClass(), null, scr)) {
				this.visibleViewScreen(view.getClass());
				if(LOGGER) logger.trace("GUI:: VISIBLE!");

				continue;
			}
			if(LOGGER) logger.trace("GUI:: NEU ANLEGEN UND SPEICHERN!");
			// Anlegen und Speichern des Widgets
			this.storeWidget(view, null, scr.buildWidget(view, menu.getModel(), this
					.getBaseGroup(scr)), scr);
//
//			// Anlegen und Speichern des Contents
//			Map<String, Widget> map = this.get_Widget(view.getClass(), scr);
//			assert map.containsKey(scr.name()) : map.keySet();
//			Widget widget = this.get_Widget(view.getClass(), scr).get(scr.name());
//			assert widget != null;
//			IContent content = view.get_GuiContent(widget, scr);
//			this.facade.store_Content(view, scr, content, true);

		}
		this.layout();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * name.tfossi.apolge.io.screenfactory.AWidget#refreshScreens(name.tfossi.apolge
	 * .io.Screen[], name.tfossi.xapolge201rc.hci.IView)
	 */
	@Override
	final void refreshScreens(Screen[] screens, IView view) {
		if(LOGGER) logger.debug("GUI::Refresh " + Arrays.asList(screens));
		for (Screen screen : screens) {
			this.swt.refreshScreen(screen, view);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see name.tfossi.apolge.io.screenfactory.AWidget#isDisposed()
	 */
	@Override
	final boolean isDisposed() {
		return this.swt.getShell().isDisposed();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see name.tfossi.apolge.io.screenfactory.AWidget#displayLive()
	 */
	@Override
	final void displayLive() {
		this.swt.displayLive();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see name.tfossi.apolge.io.screenfactory.AWidget#disposedApplicationScreen()
	 */
	@Override
	final void disposedApplicationScreen() {
		if(LOGGER) logger.trace("GUI::Dispose APPLICATIONSCREENS");
		for (Screen scr : APPLICATIONSCREENS) {
			this.swt.disposeScreen(scr);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see name.tfossi.apolge.io.screenfactory.AWidget#disposedParameterScreen()
	 */
	@Override
	final void disposedParameterScreen() {
		if(LOGGER) logger.trace("GUI::Dispose PARAMETERSCREENS");
		for (Screen screen : PARAMETERSCREENS) {
			this.swt.disposeScreen(screen);
		}
	}

	/**
	 * Menuscreen wieder sichtbar machen
	 * 
	 * @param clazz
	 *            aktuelles Menu
	 */
	private final void visibleMenuScreen(Class<? extends IMenu> clazz) {
		this.swt.visibleScreen(Screen.MI, this.widget.get(Screen.MI).get(clazz));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * name.tfossi.apolge.io.screenfactory.AWidget#visibleViewScreen(java.lang.
	 * Class)
	 */
	@Override
	final void visibleViewScreen(Class<? extends IView> clazz) {
		for (Screen scr : getScreens(VIEWSCREENS)) {
			this.swt.visibleScreen(scr, this.widget.get(scr).get(clazz));
		}

		this.swt.visibleScreen(Screen.C, this.widget.get(Screen.C).get(clazz));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see name.tfossi.apolge.io.screenfactory.AWidget#unvisibleMenuScreen()
	 */
	@Override
	final void unvisibleMenuScreen() {
		for (Screen screen : MENUSCREENS) {
			this.swt.unvisibleScreen(screen);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see name.tfossi.apolge.io.screenfactory.AWidget#unvisibleViewScreen()
	 */
	@Override
	final void unvisibleViewScreen() {
		for (Screen screen : VIEWSCREENS) {
			this.swt.unvisibleScreen(screen);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see name.tfossi.apolge.io.screenfactory.AWidget#unvisibleParameterScreen()
	 */
	@Override
	final void unvisibleParameterScreen() {
		for (Screen screen : PARAMETERSCREENS) {
			this.swt.unvisibleScreen(screen);
		}
	}

	// /*
	// * (non-Javadoc)
	// *
	// * @see
	// *
	// name.tfossi.apolge.io.screenfactory.AWidget#storeApplWidget(java.lang.Class,
	// * java.lang.Class, java.util.Map, name.tfossi.apolge.io.Screen)
	// */
	// @Override
	// final void storeApplWidget(@SuppressWarnings("unused") Class<? extends IView>
	// viewClazz,
	// @SuppressWarnings("unused") Class<? extends IMenu> menuClazz,
	// Map<String, Widget> widgetMap, Screen scr) {
	// if(LOGGER) logger.debug("GUI::Speicher APPLICATIONSCREEN-Widget für " + scr);
	// assert widgetMap != null;
	// switch (scr) {
	// case M:
	// this.storeM_Widget(widgetMap);
	// break;
	// case MCM:
	// case VCM:
	// case CPM:
	// case MI:
	// case VI:
	// case TITEL:
	// default:
	// assert false;
	// }
	// }

	// /*
	// * (non-Javadoc)
	// *
	// * @see
	// *
	// name.tfossi.apolge.io.screenfactory.AWidget#storeMenuWidget(java.lang.Class,
	// * java.lang.Class, java.util.Map, name.tfossi.apolge.io.Screen)
	// */
	// @Override
	// final void storeMenuWidget(@SuppressWarnings("unused") Class<? extends IView>
	// viewClazz,
	// Class<? extends IMenu> menuClazz, Map<String, Widget> map, Screen scr) {
	// if(LOGGER) logger.debug("GUI::Speicher MENUSCREEN-Widget auf " + scr + NTAB + "für "
	// + menuClazz.getSimpleName() + NTAB + map);
	//
	// switch (scr) {
	// case MI:
	// this.storeMI_Widget(menuClazz, map);
	// break;
	// case MCM:
	// this.storeMCM_Widget(menuClazz, map);
	// break;
	// case VCM:
	// case M:
	// case CPM:
	// case VI:
	// case C:
	// case TITEL:
	// default:
	// assert false : scr;
	// }
	// }

	/* (non-Javadoc)
	 * @see name.tfossi.apolge.io.screenfactory.AWidget#storeWidget(name.tfossi.apolge.common.hci.IView, name.tfossi.apolge.common.hci.IMenu, java.util.List, name.tfossi.apolge.io.Screen)
	 */
	@SuppressWarnings("null")
	@Override
	final boolean storeWidget(IView view, IMenu menu, java.util.List<Widget> listOfWidgets, Screen scr) {
//if (menu!=null && (scr==Screen.MCM && menu.getClass().getSimpleName().equals("CreditMenu"))){
//	assert false: listOfWidgets;
//}
		if(LOGGER) logger.debug("GUI::Speicher Widget: "+NTAB+
				"["+(view!=null?view.getClass().getSimpleName():null)+"]"+NTAB+
				"["+(menu!=null?menu.getClass().getSimpleName():null)+"]"+NTAB+
				(listOfWidgets!=null?listOfWidgets:null)+NTAB+
				"["+(scr!=null?scr:null)+"]");
		if (!(view == null ^ menu == null) && view!=menu ) {
			assert false;
			assert false; //ErrApp.STORECLAZZ.erraufruf(LFCR+"view: " + view + LFCR + "menu: " + menu);
			return false;
		}

//		assert this.widget.containsKey(scr): scr;
//		assert this.widget.get(scr).containsKey(view);
//		assert this.widget.get(scr).get(view) != null;
		// Anlegen und Speichern des Contents
		// Map <String, Widget> map = this.get_Widget(view.getClass(), scr);
		// assert map.containsKey(scr.name()):map.keySet();
		// Widget widget = this.get_Widget(view.getClass(), scr).get(scr.name());
		// assert widget!=null;
		assert listOfWidgets!=null:listOfWidgets;
		assert scr!=null;
		assert scr.name()!=null;
		IContent content = new ContentWidget(listOfWidgets);
		this.facade.store_Content(view, menu, scr, content, true);

		// switch (scr) {
		// case VI:

		// Class<?> clazz = viewClazz != null ? viewClazz : menuClazz;
		// if(LOGGER) logger.debug("GUI::Speicher SCREEN-Widget auf " + scr + NTAB + "für "
		// + clazz.getSimpleName() + NTAB + map);
		// if (this.widget.containsKey(scr)) {
		// Map<Class<?>, Map<String, Widget>> widgetSCR = this.widget.get(scr);
		//
		// if (widgetSCR.containsKey(clazz)) {
		// if(LOGGER) logger.debug("GUI::Widgets\tüberschreiben!" + NTAB + "Widgets MAP für Class "
		// + clazz.getSimpleName() + " an!");
		// assert false;
		// }
		// if(LOGGER) logger.debug("GUI::Widgets\tLege MAP für Class " + clazz.getSimpleName() +
		// " an!");
		// widgetSCR.put(clazz, map);
		// if(LOGGER) logger.debug("WIDGET [" + scr + "]" + NTAB + "STATE: " + clazz.getSimpleName()
		// + NTAB + "WIDGET: " + this.widget.get(clazz));
		// return true;
		// }
		// return false;
		// case VCM:
		// this.storeVCM_Widget(viewClazz, map);
		// break;
		// case C:
		// assert map != null;
		// this.storeC_Widget(viewClazz, map);
		// break;
		// case TITEL:
		// break;
		//
		// case MI:
		// this.storeMI_Widget(menuClazz, map);
		// break;
		// case MCM:
		// this.storeMCM_Widget(menuClazz, map);
		// case CPM:
		// this.storeCPM_Widget(clazz, map);
		// default:
		// assert false : scr;
		// }
//		assert false;

		 return false;
	}

	// /*
	// * (non-Javadoc)
	// *
	// * @see
	// * name.tfossi.apolge.io.screenfactory.AWidget#storeParameterWidget(java.lang
	// * .Class, java.util.Map, name.tfossi.apolge.io.Screen)
	// */
	// @Override
	// final void storeParameterWidget(Class<? extends IView> clazz, Map<String, Widget>
	// map,
	// Screen scr) {
	// if(LOGGER) logger.debug("GUI::Speicher PARAMETERSCREEN-Widget für " + scr);
	// switch (scr) {
	// case CPM:
	// this.storeCPM_Widget(clazz, map);
	// break;
	// case VI:
	// case C:
	// case TITEL:
	// case VCM:
	// case MI:
	// case MCM:
	// case M:
	// default:
	// assert false : scr;
	// }
	//
	// }
	//
	// /**
	// * Speicher für M-Widgets
	// */
	// private org.eclipse.swt.widgets.List widgetM;

	// /**
	// * Speichert M-Widgets
	// *
	// * @param map
	// * Widgets
	// */
	// private final void storeM_Widget(Map<String, Widget> map) {
	// assert map != null;
	// if (this.widgetM != null && this.widgetM.isDisposed()) {
	// if(LOGGER) logger.trace("GUI::Widgets\t" + map + " ist disposed!");
	// assert false;
	// } else if (this.widgetM != null && !this.widgetM.isDisposed()) {
	// if(LOGGER) logger.trace("GUI::Widgets\t" + map + " ist schon angelegt!");
	// assert false;
	// } else {
	// if(LOGGER) logger.trace("GUI::Widgets" + NTAB + "Lege " + map + " an!");
	// this.widgetM = (org.eclipse.swt.widgets.List) map.get(Screen.M.name());
	// }
	//
	// IContent content = this.facade.getM_Content();
	// this.widgetM.setItems(content.getString());
	//
	// if(LOGGER) logger.debug("APPLICATIONWIDGET [M]" + NTAB + "WIDGET: " + this.widgetM);
	// }

	// /*
	// * (non-Javadoc)
	// *
	// * @see name.tfossi.apolge.io.screenfactory.AWidget#activateM_Widget()
	// */
	// @Override
	// final void activateM_Widget() {
	// IContent content = this.facade.getM_Content();
	// assert content != null;
	// this.widgetM.setItems(content.getString());
	// }
	//
	// /**
	// * Speicher für MCM-Widgets
	// */
	// private final Map<Class<? extends IMenu>, Map<String, Widget>> widgetMCM = new
	// HashMap<Class<? extends IMenu>, Map<String, Widget>>();

	// /**
	// * Speichert MCM-Widgets
	// *
	// * @param clazz
	// * aktuelles Menu
	// * @param map
	// * Widgets
	// */
	// private final void storeMCM_Widget(Class<? extends IMenu> clazz, Map<String,
	// Widget> map) {
	// if (this.widgetMCM.containsKey(clazz)) {
	// this.widgetMCM.remove(clazz);
	// if(LOGGER) logger.debug("GUI::Widgets\tüberschreiben!" + NTAB + "Widgets MAP für Class "
	// + clazz.getSimpleName() + " an!");
	// }
	// if(LOGGER) logger.debug("GUI::Widgets\tLege MAP für Class " + clazz.getSimpleName() +
	// " an!");
	// this.widgetMCM.put(clazz, map);
	// if(LOGGER) logger.debug("STATEWIDGET [MCM]" + NTAB + "STATE: " + clazz.getSimpleName() + NTAB
	// + "WIDGET: " + this.widgetMCM.get(clazz));
	// }
	//
	// /**
	// * Speicher für VCM-Widgets
	// */
	// private final Map<Class<? extends IView>, Map<String, Widget>> widgetVCM = new
	// HashMap<Class<? extends IView>, Map<String, Widget>>();

	// /**
	// * Speichert VCM-Widgets
	// *
	// * @param clazz
	// * aktueller View
	// * @param map
	// * Widgets
	// */
	// private final void storeVCM_Widget(Class<? extends IView> clazz, Map<String,
	// Widget> map) {
	// if (this.widgetVCM.containsKey(clazz)) {
	// if(LOGGER) logger.debug("GUI::Widgets\tist vorhanden!" + NTAB + "Widgets MAP für Class "
	// + clazz.getSimpleName() + " an!");
	// assert false;
	// }
	// if(LOGGER) logger.trace("GUI::Widgets\tLege " + map + " für Class " + clazz.getSimpleName() +
	// " an!");
	// this.widgetVCM.put(clazz, map);
	// if(LOGGER) logger.debug("STATEWIDGET [SCM]" + NTAB + "STATE: " + clazz.getSimpleName() + NTAB
	// + "WIDGET: " + this.widgetVCM.get(clazz));
	// }

	// /**
	// * Speicher für CPM-Widgets
	// */
	// private final Map<Class<? extends IView>, Map<String, Widget>> widgetCPM = new
	// HashMap<Class<? extends IView>, Map<String, Widget>>();

	// /**
	// * Speichert CPM-Widgets
	// *
	// * @param clazz
	// * aktueller View
	// * @param map
	// * Widgets
	// */
	// private final void storeCPM_Widget(Class<? extends IView> clazz, Map<String,
	// Widget> map) {
	// if (map == null)
	// return;
	// assert clazz != null;
	// if (this.widgetCPM.containsKey(clazz)) {
	// if(LOGGER) logger.trace("GUI::Widgets\t" + this.widgetCPM + " für Class " +
	// clazz.getSimpleName()
	// + " ist disposed!");
	// return;
	// }
	// if(LOGGER) logger.trace("GUI::Widgets\tLege Map für Class " + clazz.getSimpleName() +
	// " an!");
	// this.widgetCPM.put(clazz, map);
	// }
	//
	// /**
	// * Speicher für MI-Widgets
	// */
	// private final Map<Class<? extends IMenu>, Map<String, Widget>> widgetMI = new
	// HashMap<Class<? extends IMenu>, Map<String, Widget>>();

	// /**
	// * Speichet MI-Widgets
	// *
	// * @param clazz
	// * aktuelles Menu
	// * @param map
	// * Widget
	// */
	// private final void storeMI_Widget(Class<? extends IMenu> clazz, Map<String,
	// Widget> map) {
	// if (this.widgetMI.containsKey(clazz)) {
	// if(LOGGER) logger.debug("GUI::Widgets\tist vorhanden!" + NTAB + "Widgets MAP für Class "
	// + clazz.getSimpleName() + " an!");
	// assert false;
	// }
	// if(LOGGER) logger.debug("GUI::Widgets\tLege MAP für Class " + clazz.getSimpleName() +
	// " an!");
	// this.widgetMI.put(clazz, map);
	//
	// if(LOGGER) logger.debug("STATEWIDGET [MI]" + NTAB + "STATE: " + clazz.getSimpleName() + NTAB
	// + "WIDGET: " + this.widgetMI.get(clazz));
	//
	// IContent content = this.facade.getMI_Content(clazz);
	// ((List)
	// this.widgetMI.get(clazz).get(Screen.MI.name())).setItems(content.getString());
	// }

	// /*
	// * (non-Javadoc)
	// *
	// * @see
	// * name.tfossi.apolge.io.screenfactory.AWidget#activateMI_Widget(java.lang.
	// * Class)
	// */
	// @Override
	// final void activateMI_Widget(Class<? extends IMenu> clazz) {
	// IContent content = this.facade.getMI_Content(clazz);
	// assert content != null;
	// ((List) this.widgetMI.get(clazz).get("MI")).setItems(content.getString());
	// }

	// /**
	// * Speicher für VI-Widgets 1. Schlüssel: Klassenname des Screens: z.B. ServerView
	// 1.
	// * Value: Map mit den Widgets 2. Schlüssel: 2. Value: Das einzelne Widget
	// */
	// private final Map<Class<? extends IView>, Map<String, Widget>> widgetVI = new
	// HashMap<Class<? extends IView>, Map<String, Widget>>();

	// /**
	// * Speichert VI-Widgets
	// *
	// * @param clazz
	// * aktueller Viewer
	// * @param map
	// * Widget
	// */
	// private final void storeVI_Widget(Class<? extends IView> clazz, Map<String,
	// Widget> map) {
	// if (this.widgetVI.containsKey(clazz)) {
	// if(LOGGER) logger.debug("GUI::Widgets\tüberschreiben!" + NTAB + "Widgets MAP für Class "
	// + clazz.getSimpleName() + " an!");
	// assert false;
	// }
	// if(LOGGER) logger.debug("GUI::Widgets\tLege MAP für Class " + clazz.getSimpleName() +
	// " an!");
	// this.widgetVI.put(clazz, map);
	// if(LOGGER) logger.debug("VIEWWIDGET [VI]" + NTAB + "STATE: " + clazz.getSimpleName() + NTAB
	// + "WIDGET: " + this.widgetVI.get(clazz));
	// }

	/**
	 * TODO Comment
	 * @param clazz -
	 * @param scr -
	 * @return -
	 * @modified - 
	 */
	@SuppressWarnings("unused")
	private final Map<String, Widget> get_Widget(Class<? extends IView> clazz, Screen scr) {
		// switch (scr) {
		// case VI:
		assert this.widget.containsKey(scr);
		assert this.widget.get(scr).containsKey(clazz);
		assert this.widget.get(scr).get(clazz) != null;
		if (this.widget.containsKey(scr) && this.widget.get(scr).containsKey(clazz)) {
			return this.widget.get(scr).get(clazz);
		}
		// break;
		// case TITEL:
		// break;
		// case VCM:
		// break;
		// case C:
		// if (this.widgetC.containsKey(clazz)) {
		// return this.widgetC.get(clazz);
		// }
		// break;
		// default:
		// assert false : scr;
		// }
		return new HashMap<String, Widget>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seename.tfossi.apolge.io.screenfactory.AWidget#activate_Widget(name.tfossi.
	 * apolge203rc.common.hci.IView, name.tfossi.apolge203rc.common.hci.IMenu,
	 * name.tfossi.apolge.io.Screen)
	 */
	@Override
	final void activate_Widget(IView view, IMenu menu, Screen scr) {
		IContent content = this.facade.get_Content(view, menu, scr);
		((List) this.widget.get(scr).get(view.getClass()).get(scr.name())).setItems(content
				.getString());
		this.visibleViewScreen(view.getClass());
	}

	// /**
	// * Speicher für C-Widgets
	// */
	// private final Map<Class<? extends IView>, Map<String, Widget>> widgetC = new
	// HashMap<Class<? extends IView>, Map<String, Widget>>();

	// /**
	// * Speichert C-Widgets
	// *
	// * @param clazz
	// * aktueller Viewer
	// * @param map
	// * Widgets
	// */
	// private final void storeC_Widget(Class<? extends IView> clazz, Map<String, Widget>
	// map) {
	// if (this.widget.get(Screen.C).containsKey(clazz)) {
	// if(LOGGER) logger.debug("GUI::Widgets\tüberschreiben!" + NTAB + "Widgets MAP für Class "
	// + clazz.getSimpleName() + " an!");
	// }
	// if(LOGGER) logger.debug("GUI::Widgets\tLege MAP für Class " + clazz.getSimpleName() +
	// " an!");
	// this.widget.get(Screen.C).put(clazz, map);
	// if(LOGGER) logger.debug("VIEWWIDGET [C]" + NTAB + "STATE: " + clazz.getSimpleName() + NTAB
	// + "WIDGET: " + this.widget.get(Screen.C).get(clazz).get("C"));
	// }

	// /*
	// * (non-Javadoc)
	// *
	// * @see
	// * name.tfossi.apolge.io.screenfactory.AWidget#activateC_Widget(name.tfossi.
	// * xapolge201rc.hci.IView)
	// */
	// @Override
	// final void activateC_Widget(IView view) {
	// if(LOGGER) logger.trace("GUI:: Zuweisung Storage an Widget");
	// // IContent content = view.getC_GuiContent();
	// IContent content = view.get_GuiContent(this.get_Widget(view.getClass(),
	// Screen.C).get(
	// Screen.C.name()), Screen.C);
	// view.activateC_Widget(this.widget.get(Screen.C).get(view.getClass()), content);
	// }

	/**
	 * Prüft, ob Menuwidget existiert
	 * 
	 * @param clazz
	 *            aktuelles Menu
	 * @param scr
	 *            Screen
	 * @return Prüfergebnis
	 */
	private final boolean isInitiate_Menu(Class<? extends IMenu> clazz, Screen scr) {
		if(!this.widget.containsKey(scr)){
			if(LOGGER) logger.debug("GUI::Widget für [" + scr + "] ist NICHT gespeichert!");
			return false;
		}
		assert this.widget.get(scr).containsKey(clazz);
		assert this.widget.get(scr).get(clazz) != null;
		if(LOGGER) logger.debug("GUI::Ist MENUSCREEN-Widget für [" + scr + "] gespeichert?");
		switch (scr) {
		case MI:
			return this.widget.get(scr).containsKey(clazz)
					&& !this.widget.get(scr).get(clazz).isEmpty();
		case MCM:
			this.widget.get(scr).remove(clazz);
			return false;
		case CPM:
		case VI:
		case VCM:
		case C:
		case TITEL:
		case M:
		default:
			return false;
		}
	}

	/**
	 * Prüft, ob Viewwidget existiert
	 * 
	 * @param clazz
	 *            aktueller View
	 * @param map
	 *            Map mit den Widgets (unused)
	 * @param scr
	 *            Screen
	 * @return Prüfergebnis
	 */
	private final boolean isInitiate_View(Class<? extends IView> clazz,
			Map<String, Widget> map, Screen scr) {
		if(LOGGER) logger.debug("GUI::Ist VIEWSCREEN-Widget für " + scr + " gespeichert?");
		// switch (scr) {
		// case CPM:
		// return this.widgetCPM.containsKey(clazz) &&
		// !this.widgetCPM.get(clazz).isEmpty();
		// case VI:
		if (!this.widget.containsKey(scr))
			return false;
		if(LOGGER) logger.trace("GUI:: " + this.widget.get(scr).containsKey(clazz));
		return this.widget.get(scr).containsKey(clazz);
		// case VCM:
		// this.widgetVCM.remove(clazz);
		// return false;
		// case C:
		// return this.widgetC.containsKey(clazz);
		// case TITEL:
		// case MI:
		// case MCM:
		// case M:
		// default:
		// return false;
		// }
	}

	// ---- Selbstverwaltung ------------------------------------------------
	/** serialVersionUID */
	@SuppressWarnings("unused")
	private final static long serialVersionUID = VERSION;
	/** logger */
	private final static Logger logger = Logger.getLogger(GuiWidget.class.getPackage().getName());

	/**
	 * @param cntr
	 *            Fassade
	 * @param swt
	 *            SWTGrafik
	 */
	public GuiWidget(Cntr cntr, SWTGrafik swt) {
		if(LOGGER) logger.trace("Factoryprodukt");
		this.swt = swt;
		this.facade = cntr;
	}
}
