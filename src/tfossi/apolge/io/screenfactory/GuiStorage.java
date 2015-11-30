/**
 * GuiStorage.java
 * Branch io
 * APolGe
 * tfossi-team
 * licence GPLv3  
 */
package tfossi.apolge.io.screenfactory;
import static tfossi.apolge.common.constants.ConstValue.LOGGER;
import static tfossi.apolge.common.constants.ConstValueExtension.APPLICATIONSCREENS;
import static tfossi.apolge.common.constants.ConstValueExtension.MENUSCREENS;
import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;
import static tfossi.apolge.common.constants.ConstValueExtension.VIEWSCREENS;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import tfossi.apolge.common.hci.IMenu;
import tfossi.apolge.common.hci.IView;
import tfossi.apolge.io.IContent;
import tfossi.apolge.io.Screen;

/**
 * Screendatenspeicherung für GUI
 * 
 * @.pattern Abstract Factory: Concrete Product
 * @see Cntr
 * @see AFactory
 * @see AStorage
 * @author tfossi
 * @version 12.08.2014
 * @modified -
 * @since Java 1.6
 */
public class GuiStorage extends AStorage {
//	/** Nimmt die Fassade auf */
//	private final Cntr facade;
//	/** Anzahl der Zeilen für Screen.VI */
//	private final int maxrow = 15;

	/** Speicher für Screen.VI Zeileneinträge */
	private final Map<Screen, Map<Class<?>, IContent>> content = 
			new HashMap<Screen, Map<Class<?>, IContent>>();
	
//	private final Map<Class<? extends IView>, IContent> contentTITEL = new HashMap<Class<? extends IView>, IContent>();
//	private final Map<Class<? extends IView>, IContent> contentVCM = new HashMap<Class<? extends IView>, IContent>();
//
//	/** Anzahl der Zeilen für Screen.M */
//	private final int maxrowM = 25;
//
//	/** Speicher für Screen.M Zeileneinträge */
//	private final java.util.List<String> contentM = new ArrayList<String>(this.maxrowM);
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see
//	 * name.tfossi.apolge.io.screenfactory.AStorage#storeM_Content(name.tfossi.apolge
//	 * .io.IContent, boolean)
//	 */
//	@Override
//	public final void storeM_Content(IContent content, boolean delete) {
//		String[] s = content.getString();
//
//		if (delete)
//			this.contentM.clear();
//		else
//			while (this.contentM.size() > this.maxrowM - (s != null ? s.length : 0))
//				this.contentM.remove(this.maxrowM - (s != null ? s.length : 0));
//		this.contentM.addAll(Arrays.asList(s));
//		this.facade.activate_Widget(null, Screen.M);
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see name.tfossi.apolge.io.screenfactory.AStorage#getM_Content()
//	 */
//	@Override
//	public final IContent getM_Content() {
//		return new ContentString(this.contentM.toArray(new String[0]));
//	}
//
//	/** Anzahl der Zeilen für Screen.MI */
//	private final int maxrowMI = 8;
//
//	/** Speicher für Screen.MI Zeileneinträge */
//	private final Map<Class<? extends IMenu>, List<String>> contentMI = new HashMap<Class<? extends IMenu>, List<String>>();
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see
//	 * name.tfossi.apolge.io.screenfactory.AStorage#storeMI_Content(java.lang.Class
//	 * , name.tfossi.apolge.io.IContent, boolean)
//	 */
//	@Override
//	final void storeMI_Content(Class<? extends IMenu> menuClass, IContent content, boolean delete) {
//		String[] s = content.getString();
//		if (!this.contentMI.containsKey(menuClass))
//			this.contentMI.put(menuClass, new ArrayList<String>(this.maxrowMI));
//		if (delete)
//			this.contentMI.get(menuClass).clear();
//		else
//			while (this.contentMI.get(menuClass).size() > this.maxrowMI
//					- (s != null ? s.length : 0))
//				this.contentMI.get(menuClass).remove(this.maxrowMI - (s != null ? s.length : 0));
//		this.contentMI.get(menuClass).addAll(0, Arrays.asList(s));
//		this.facade.activate_Widget(null, menuClass, Screen.MI);
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see
//	 * name.tfossi.apolge.io.screenfactory.AStorage#getMI_Content(java.lang.Class)
//	 */
//	@Override
//	public final IContent getMI_Content(Class<? extends IMenu> menuClass) {
//		/* Initialisiere Datencontainer für jedes Menu */
//		if (!this.contentMI.containsKey(menuClass))
//			this.contentMI.put(menuClass, new ArrayList<String>());
//		return new ContentString(this.contentMI.get(menuClass).toArray(new String[0]));
//	}
//
//	// OLDSCHOOL
//	// /** Anzahl der Zeilen für Screen.VI */
//	// private final int maxrowVI = 18;
//
//	// OLDSCHOOL
//	// /** Speicher für Screen.VI Zeileneinträge */
//	// private final Map<Class<? extends IView>, List<String>> contentVI = new
//	// HashMap<Class<? extends IView>, List<String>>();
//	/** Speicher für Screen.VI Widgets */
//	private final Map<Class<? extends IView>, IContent> contentVI = new HashMap<Class<? extends IView>, IContent>();
//
	
	/* (non-Javadoc)
	 * @see tfossi.apolge.io.screenfactory.AStorage#store_Content(tfossi.apolge.common.hci.IView, tfossi.apolge.common.hci.IMenu, tfossi.apolge.io.Screen, tfossi.apolge.io.IContent, boolean)
	 */
	@Override
	final void store_Content(IView view, IMenu menu, Screen scr, @SuppressWarnings("hiding") IContent content,
			boolean delete) {
		

		// OLDSCHOOL
		// String[] s = content.getString();
		// if (!this.contentVI.containsKey(viewClass))
		// this.contentVI.put(viewClass, new ArrayList<String>(this.maxrowVI));
		// if (delete)
		// this.contentVI.get(viewClass).clear();
		// else
		// while (this.contentVI.get(viewClass).size() > this.maxrowVI
		// - (s != null ? s.length : 0))
		// this.contentVI.get(viewClass).remove(this.maxrowVI - (s != null ? s.length :
		// 0));
		// this.contentVI.get(viewClass).addAll(0, Arrays.asList(s));
		// this.facade.activateVI_Widget(viewClass);
		switch (scr) {
		case VI:
		case MI:
		case M:
//			
//			this.contentVI.put(view.getClass(), content);
//			break;
		case TITEL:
//			this.contentTITEL.put(view.getClass(), content);
//			break;
		case VCM:
//			this.contentVCM.put(view.getClass(), content);
//			break;
		case MCM:
		case C:
//			this.contentC.put(view.getClass(), content);
//			break;
			Class<?> clazz;
			assert this.content!=null;
			if (this.content.get(scr)==null)
				this.content.put(scr, new HashMap<Class<?>, IContent>());
			if(Arrays.asList(VIEWSCREENS).contains(scr)){
				clazz = view.getClass();
			}else if(Arrays.asList(MENUSCREENS).contains(scr)){
				clazz = menu.getClass();
			}else if(Arrays.asList(APPLICATIONSCREENS).contains(scr)){
				clazz = null;
			}else{
				assert false:"??? "+scr;
				return;
			}
			this.content.get(scr).put(clazz, content);						
			break;
		default:
			assert false: scr;
		}
		// this.facade.activateVI_Widget(view);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * name.tfossi.apolge.io.screenfactory.AStorage#getVI_Content(java.lang.Class)
	 */
	@Override
	final IContent get_Content(IView view, IMenu menu, Screen scr) {
		switch(scr){
		case VI:
			Class<?> clazz;
			assert this.content!=null;
			if (this.content.get(scr)==null)
				this.content.put(scr, new HashMap<Class<?>, IContent>());
			if(Arrays.asList(VIEWSCREENS).contains(scr)){
				clazz = view.getClass();
			}else if(Arrays.asList(MENUSCREENS).contains(scr)){
				clazz = menu.getClass();
			}else if(Arrays.asList(APPLICATIONSCREENS).contains(scr)){
				clazz = null;
			}else{
				assert false:"??? "+scr;
				return null;
			}
			return this.content.get(scr).get(clazz);
			// // OLDSCHOOL
			// /* Initialisiere Datencontainer für jeden View */
			// if (!this.contentVI.containsKey(viewClass))
			// // this.contentVI.put(viewClass, new ArrayList<String>());
			// return null;
			//
			// // OLDSCHOOL
			// // return new ContentString(this.contentVI.get(viewClass).toArray(new
			// // String[0]));
			// return this.contentVI.get(viewClass);
		default:
		assert false:scr;
		}
		return null;
	}
//
//	/** Speicher für Screen.C generischer Typ */
//	private final Map<Class<? extends IView>, IContent> contentC = new HashMap<Class<? extends IView>, IContent>();

//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @seename.tfossi.apolge.io.screenfactory.AStorage#storeC_Content(name.tfossi.
//	 * xapolge201rc.hci.IView, name.tfossi.apolge.io.IContent, boolean)
//	 */
//	@Override
//	final void storeC_Content(IView view, IContent content,
//			@SuppressWarnings("unused") boolean delete) {
//		if(LOGGER) logger.trace("GUI:: Auftrag diesen Content zu speichern:" + NTAB
//				+ Arrays.asList(content.getString()) + NTAB + "- oder -" + NTAB
//				+ content.getWidget());
//		this.contentC.remove(view.getClass());
//		this.contentC.put(view.getClass(), content);
//		this.facade.activateC_Widget(view);
//	}

//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see
//	 * name.tfossi.apolge.io.screenfactory.AStorage#getC_Content(java.lang.Class)
//	 */
//	@Override
//	final IContent getC_Content(Class<? extends IView> clazz) {
//		return this.contentC.get(clazz);
//	}

	// ---- Selbstverwaltung -----------------------------------------------------
	/** serialVersionUID */
	@SuppressWarnings("unused")
	private final static long serialVersionUID = VERSION;
	/** logger */
	private final static Logger logger = Logger.getLogger(GuiStorage.class.getPackage().getName());

	/**
	 * TODO Comment
	 * @modified -
	 */
	public GuiStorage() {
		if(LOGGER) logger.trace("Factoryprodukt Storage");
//		this.facade = cntr;
	}

}
