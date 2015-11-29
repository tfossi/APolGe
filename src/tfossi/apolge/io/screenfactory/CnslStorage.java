/**
 * CnslStorage.java
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import tfossi.apolge.common.hci.IMenu;
import tfossi.apolge.common.hci.IView;
import tfossi.apolge.io.ContentString;
import tfossi.apolge.io.IContent;
import tfossi.apolge.io.Screen;

/**
 * Screendatenspeicherung für Cnsl
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
public class CnslStorage extends AStorage {

//	/** Anzahl der Zeilen für Screen.M */
//	private final int maxrowM = 25;

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
//	final void storeM_Content(IContent content, boolean delete) {
//		String[] s = content.getString();
//		if (delete)
//			this.contentM.clear();
//		else
//			while (this.contentM.size() > this.maxrowM - (s != null ? s.length : 0))
//				this.contentM.remove(this.maxrowM - (s != null ? s.length : 0));
//		this.contentM.addAll(Arrays.asList(s));
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see name.tfossi.apolge.io.screenfactory.AStorage#getM_Content()
//	 */
//	@Override
//	final IContent getM_Content() {
//		return new ContentString(this.contentM.toArray(new String[0]));
//	}
//
//	/** Anzahl der Zeilen für Screen.MI */
//	private final int maxrowMI = 8;
//
//	/** Speicher für Screen.MI Zeileneinträge */
//	private final Map<Class<? extends IMenu>, List<String>> contentMI = new HashMap<Class<? extends IMenu>, List<String>>();

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
//		if (this.contentMI.get(menuClass) == null)
//			this.contentMI.get(menuClass);
//		if (delete)
//			this.contentMI.get(menuClass).clear();
//		else
//			while (this.contentMI.get(menuClass).size() > this.maxrowMI
//					- (s != null ? s.length : 0))
//				this.contentMI.get(menuClass).remove(this.maxrowMI - (s != null ? s.length : 0));
//		this.contentMI.get(menuClass).addAll(0, Arrays.asList(s));
//	}

//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see
//	 * name.tfossi.apolge.io.screenfactory.AStorage#getMI_Content(java.lang.Class)
//	 */
//	@Override
//	final IContent getMI_Content(Class<? extends IMenu> menuClass) {
//		/* Initialisiere Datencontainer für jedes Menu */
//		if (!this.contentMI.containsKey(menuClass))
//			this.contentMI.put(menuClass, new ArrayList<String>());
//		return new ContentString(this.contentMI.get(menuClass).toArray(new String[0]));
//	}
//
//	/** Anzahl der Zeilen für Screen.VI */
//	private final int maxrowVI = 15;
//
//	/** Speicher für Screen.VI Zeileneinträge */
//	private final Map<Class<? extends IView>, List<String>> contentVI = new HashMap<Class<? extends IView>, List<String>>();
	//
	/** Anzahl der Zeilen für Screen.VI */
	private final int maxrow = 15;

	/** Speicher für Screen.VI Zeileneinträge */
	private final Map<Screen, Map<Class<?>, List<String>>> content = 
			new HashMap<>();

	
	/* (non-Javadoc)
	 * @see name.tfossi.apolge.io.screenfactory.AStorage#store_Content(name.tfossi.apolge203rc.common.hci.IView, name.tfossi.apolge.io.Screen, name.tfossi.apolge.io.IContent, boolean)
	 */
	@Override
	final void store_Content(IView view, IMenu menu, Screen scr, @SuppressWarnings("hiding") IContent content, boolean delete) {
		String[] s = content.getString();
		switch (scr) {
		case VI:
		case MI:
		case M:
		case C:
			Class<?> clazz;
			assert this.content!=null;
			if (this.content.get(scr)==null)
				this.content.put(scr, new HashMap<Class<?>, List<String>>());
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
//			assert view==null ^ menu==null: NTAB+view +NTAB+menu;
//			Arrays.asList(APPLICATIONSCREENS).contains(scr)
//			|| Arrays.asList(MENUSCREENS).contains(scr)
//			|| Arrays.asList(VIEWSCREENS)
//			assert Arrays.asList(VIEWSCREENS).contains(scr):scr;
//			assert !Arrays.asList(VIEWSCREENS).contains(scr):scr;
			if (!this.content.get(scr).containsKey(clazz))
				this.content.get(scr).put(clazz, 
						new ArrayList<String>(this.maxrow));
			if (delete)
				this.content.get(scr).get(clazz).clear();
			else
				while (this.content.get(scr).get(clazz).size() > this.maxrow
						- (s != null ? s.length : 0))
					this.content.get(scr).get(clazz).remove(
							this.maxrow - (s != null ? s.length : 0));
			this.content.get(scr).get(clazz).addAll(0, Arrays.asList(s));
			break;
//		case C:
//			Class<? extends IView> clazz = view.getClass();
//			if (!this.contentC.containsKey(clazz))
//				this.contentC.put(clazz, new ArrayList<String>(this.maxrowC));
//			if (delete)
//				this.contentC.get(clazz).clear();
//			else
//				while (this.contentC.get(clazz).size() > this.maxrowC - (s != null ? s.length : 0))
//					this.contentC.get(clazz).remove(this.maxrowC - (s != null ? s.length : 0));
//			this.contentC.get(clazz).addAll(0, Arrays.asList(s));
//			break;
		default:
			assert false:"store_Content: "+scr;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * name.tfossi.apolge.io.screenfactory.AStorage#getVI_Content(java.lang.Class)
	 */
	@Override
	final IContent get_Content(IView view, IMenu menu, Screen scr) {
		/* Initialisiere Datencontainer für jeden View */
//		switch(scr){
//		case VI:
//		case C:
//		case MI:
//		case M:
			Class<?> clazz;
			assert this.content!=null;
			if (this.content.get(scr)==null)
				this.content.put(scr, new HashMap<Class<?>, List<String>>());
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
		if (!this.content.get(scr).containsKey(clazz))
			this.content.get(scr).put(clazz, new ArrayList<String>());
		return new ContentString(this.content.get(scr).get(clazz).toArray(new String[0]));
		// default:
		// assert false: scr;
		// }
//		return null;
	}

//	/** Anzahl der Zeilen für Screen.C */
//	private final int maxrowC = 25;
//
//	/** Speicher für Screen.C Zeileneinträge */
//	private final Map<Class<? extends IView>, List<String>> contentC = new HashMap<Class<? extends IView>, List<String>>();

//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see
//	 * name.tfossi.apolge.io.screenfactory.AStorage#getC_Content(java.lang.Class)
//	 */
//	@Override
//	final IContent getC_Content(Class<? extends IView> viewClass) {
//		return new ContentString(this.contentC.get(viewClass).toArray(new String[0]));
//	}

	/*
//	 * (non-Javadoc)
//	 * 
//	 * @seename.tfossi.apolge.io.screenfactory.AStorage#storeC_Content(name.tfossi.
//	 * xapolge201rc.hci.IView, name.tfossi.apolge.io.IContent, boolean)
//	 */
//	@Override
//	final void storeC_Content(IView view, IContent content, boolean delete) {
//		String[] s = content.getString();
//		Class<? extends IView> clazz = view.getClass();
//		if (!this.contentC.containsKey(clazz))
//			this.contentC.put(clazz, new ArrayList<String>(this.maxrowC));
//		if (delete)
//			this.contentC.get(clazz).clear();
//		else
//			while (this.contentC.get(clazz).size() > this.maxrowC - (s != null ? s.length : 0))
//				this.contentC.get(clazz).remove(this.maxrowC - (s != null ? s.length : 0));
//		this.contentC.get(clazz).addAll(0, Arrays.asList(s));
//	}

	// ---- Selbstverwaltung -----------------------------------------------------
	/** serialVersionUID */
	@SuppressWarnings("unused")
	private final static long serialVersionUID = VERSION;
	/** logger */
	private final static Logger logger = Logger.getLogger(CnslStorage.class.getPackage().getName());

	
	/**
	 * TODO Comment
	 * @modified -
	 */
	public CnslStorage() {
		if(LOGGER) logger.trace("Factoryprodukt Storage");
	}
}
