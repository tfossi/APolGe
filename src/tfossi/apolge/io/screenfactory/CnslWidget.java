/**
 * CnslWidget.java
 * Branch io
 * APolGe
 * tfossi-team
 * licence GPLv3  
 */
package tfossi.apolge.io.screenfactory;

import static tfossi.apolge.common.constants.ConstValue.LOGGER;
import static tfossi.apolge.common.constants.ConstValue.NTAB;
import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;

import org.apache.log4j.Logger;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Widget;

import tfossi.apolge.common.hci.IMenu;
import tfossi.apolge.common.hci.IModel;
import tfossi.apolge.common.hci.IView;
import tfossi.apolge.io.Screen;

/**
 * GUI-Steuerung für Cnsl(==nope)
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
public class CnslWidget extends AWidget {

	/* (non-Javadoc)
	 * @see name.tfossi.apolge.io.screenfactory.AWidget#layout()
	 */
	@Override
	final void layout(){
		// Not used
	}
	
	/* (non-Javadoc)
	 * @see name.tfossi.apolge.io.screenfactory.AWidget#initApplWidgets(name.tfossi.xapolge201rc.hci.IModel)
	 */
	@Override
	final void initApplWidgets(IModel model) {
		// Not used
	}

	/* (non-Javadoc)
	 * @see name.tfossi.apolge.io.screenfactory.AWidget#initMenuWidgets(name.tfossi.xapolge201rc.hci.IMenu)
	 */
	@Override
	final void initMenuWidgets(IMenu menu) {
		// Not used	
	}

	/* (non-Javadoc)
	 * @see name.tfossi.apolge.io.screenfactory.AWidget#initViewWidgets(name.tfossi.xapolge201rc.hci.IView, name.tfossi.xapolge201rc.hci.IMenu)
	 */
	@Override
	final void initViewWidgets(IView view, IMenu menu) {
		// Not used	
	}
	
	/* (non-Javadoc)
	 * @see name.tfossi.apolge.io.screenfactory.AWidget#refreshScreens(name.tfossi.apolge.io.Screen[], name.tfossi.xapolge201rc.hci.IView)
	 */
	@Override
	final void refreshScreens(Screen[] screens, IView view) {
		// Not used
		}
	
	/* (non-Javadoc)
	 * @see name.tfossi.apolge.io.screenfactory.AWidget#isDisposed()
	 */
	@Override
	final boolean isDisposed() {
		return false;
	}

	/* (non-Javadoc)
	 * @see name.tfossi.apolge.io.screenfactory.AWidget#disposedApplicationScreen()
	 */
	@Override
	final void disposedApplicationScreen() {
		// Not used
		}

	/* (non-Javadoc)
	 * @see name.tfossi.apolge.io.screenfactory.AWidget#disposedParameterScreen()
	 */
	@Override
	final void disposedParameterScreen() {
		// Not used
	}

	/* (non-Javadoc)
	 * @see name.tfossi.apolge.io.screenfactory.AWidget#displayLive()
	 */
	@Override
	final void displayLive() {
		// Not used		
	}

	/* (non-Javadoc)
	 * @see name.tfossi.apolge.io.screenfactory.AWidget#storeWidget(name.tfossi.apolge203rc.common.hci.IView, name.tfossi.apolge203rc.common.hci.IMenu, java.util.List, name.tfossi.apolge.io.Screen)
	 */
	@Override
	final boolean storeWidget(IView view, final IMenu menu, java.util.List<Widget> listOfWidgets, Screen scr) {
		// Not used		
		return true;
	}
	
//	/* (non-Javadoc)
//	 * @see name.tfossi.apolge.io.screenfactory.AWidget#activateM_Widget()
//	 */
//	@Override
//	final void activateM_Widget() {
//		// Not used		
//	}
////
//	/* (non-Javadoc)
//	 * @see name.tfossi.apolge.io.screenfactory.AWidget#activateMI_Widget(java.lang.Class)
//	 */
//	@Override
//	final void activateMI_Widget(@SuppressWarnings("unused") Class<? extends IMenu> clazz){
//		// Not used
//	}
//		

	/* (non-Javadoc)
	 * @see name.tfossi.apolge.io.screenfactory.AWidget#activate_Widget(name.tfossi.apolge203rc.common.hci.IView, name.tfossi.apolge203rc.common.hci.IMenu, name.tfossi.apolge.io.Screen)
	 */
	@Override
	final void activate_Widget(
			final IView view,
			final IMenu menu,
			final Screen scr) {
		// Not used
	}

	/* (non-Javadoc)
	 * @see name.tfossi.apolge.io.screenfactory.AWidget#getCPMBaseGroup()
	 */
	@Override
	final Group getCPMBaseGroup() {
		// Not used
		return null;
	}

//	/* (non-Javadoc)
//	 * @see name.tfossi.apolge.io.screenfactory.AWidget#activateC_Widget(name.tfossi.xapolge201rc.hci.IView)
//	 */
//	@Override
//	final void activateC_Widget(@SuppressWarnings("unused") IView view) {
//		// Not used
//	}
//
//	/* (non-Javadoc)
//	 * @see name.tfossi.apolge.io.screenfactory.AWidget#storeApplWidget(java.lang.Class, java.lang.Class, java.util.Map, name.tfossi.apolge.io.Screen)
//	 */
//	@Override
//	final void storeApplWidget(@SuppressWarnings("unused") Class<? extends IView> viewClazz, @SuppressWarnings("unused") Class<? extends IMenu> menuClazz,
//			@SuppressWarnings("unused") Map<String, Widget> widgetMap, @SuppressWarnings("unused") Screen scr) {
//		// Not used
//	}
//
//	/* (non-Javadoc)
//	 * @see name.tfossi.apolge.io.screenfactory.AWidget#storeMenuWidget(java.lang.Class, java.lang.Class, java.util.Map, name.tfossi.apolge.io.Screen)
//	 */
//	@Override
//	final void storeMenuWidget(@SuppressWarnings("unused") Class<? extends IView> viewClazz, @SuppressWarnings("unused") Class<? extends IMenu> menuClazz,
//			@SuppressWarnings("unused") Map<String, Widget> widgetMap, @SuppressWarnings("unused") Screen scr) {
//		// Not used
//	}
//
//	/* (non-Javadoc)
//	 * @see name.tfossi.apolge.io.screenfactory.AWidget#storeParameterWidget(java.lang.Class, java.util.Map, name.tfossi.apolge.io.Screen)
//	 */
//	@Override
//	final void storeParameterWidget(@SuppressWarnings("unused") Class<? extends IView> clazz, @SuppressWarnings("unused") Map<String, Widget> widgetMap,
//			@SuppressWarnings("unused") Screen scr) {
//		// Not used	
//	}
//
//	/* (non-Javadoc)
//	 * @see name.tfossi.apolge.io.screenfactory.AWidget#storeViewWidget(java.lang.Class, java.lang.Class, java.util.Map, name.tfossi.apolge.io.Screen)
//	 */
//	@Override
//	final boolean storeViewWidget(@SuppressWarnings("unused") Class<? extends IView> clazz, @SuppressWarnings("unused") Class<? extends IMenu> menuClazz,
//			@SuppressWarnings("unused") Map<String, Widget> widgetMap, @SuppressWarnings("unused") Screen scr) {
//		return true;
//	}

	/* (non-Javadoc)
	 * @see name.tfossi.apolge.io.screenfactory.AWidget#unvisibleMenuScreen()
	 */
	@Override
	final void unvisibleMenuScreen() {
		// Not used
	}

	/* (non-Javadoc)
	 * @see name.tfossi.apolge.io.screenfactory.AWidget#unvisibleParameterScreen()
	 */
	@Override
	final void unvisibleParameterScreen() {
		// Not used
	}

	/* (non-Javadoc)
	 * @see name.tfossi.apolge.io.screenfactory.AWidget#unvisibleViewScreen()
	 */
	@Override
	final void unvisibleViewScreen() {
		// Not used	
	}

	/* (non-Javadoc)
	 * @see name.tfossi.apolge.io.screenfactory.AWidget#visibleViewScreen(java.lang.Class)
	 */
	@Override
	final void visibleViewScreen(Class<? extends IView> clazz) {
		// Not used	
	}
	
	// ---- Selbstverwaltung --------------------------------------------------
	/** serialVersionUID */
	@SuppressWarnings("unused")
	private final static long serialVersionUID = VERSION;
	/** logger */
	private final static Logger logger = Logger.getLogger(CnslWidget.class.getPackage().getName());
	
	/**
	 * @param cntr
	 * 			Fassade
	 * @param swt
	 * 			SWTGrafik
	 */
	public CnslWidget(Cntr cntr, SWTGrafik swt) {
		if(LOGGER) logger.trace("Factoryprodukt"+NTAB+cntr.getClass().getSimpleName() + " & " + swt + " Not used");
		// Not used
	}

}
