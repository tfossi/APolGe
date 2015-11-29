/**
 * AModel.java
 * Branch hci
 * APolGe
 * tfossi-team
 * licence GPLv3  
 */
package tfossi.apolge.common.hci;

import static tfossi.apolge.common.constants.ConstValue.LOGGER;
import static tfossi.apolge.common.constants.ConstValue.NTAB;
import static tfossi.apolge.common.constants.ConstValueExtension.APPLICATIONSCREENS;
import static tfossi.apolge.common.constants.ConstValueExtension.MENU;
import static tfossi.apolge.common.constants.ConstValueExtension.MENUSCREENS;
import static tfossi.apolge.common.constants.ConstValueExtension.PARAMETERSCREENS;
import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;
import static tfossi.apolge.common.constants.ConstValueExtension.VIEWSCREENS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;

import org.apache.log4j.Logger;

import tfossi.apolge.common.cmd.CommandList;
import tfossi.apolge.common.cmd.CommandMaps;
import tfossi.apolge.data.MetaData;
import tfossi.apolge.io.IContent;
import tfossi.apolge.io.Screen;
import tfossi.apolge.io.screenfactory.Cntr;
import tfossi.apolge.io.screenfactory.NotifyScreens;

/**
 * Enthält die Daten des Menus und Viewer persistent vor. Abstrakte Klasse für Modelle<br>
 * Gehört wie {@link AMenu} und {@link AView} zum MVC-Pattern und speichert alle Daten
 * des MVC.
 * 
 * @.pattern MVC: abstract model
 * @see AMenu
 * @see AView
 * @author tfossi
 * @version 13.08.2014
 * @modified -
 * @since Java 1.6
 */
public abstract class AModel extends Observable implements IModel {

	/** Enthält die Daten der möglichen Spiele, ... */
	private final MetaData md = MetaData.getInstance();

	/** @return MetaDatenContainer */
	public final MetaData getMetaData() {
		return this.md;
	}

	/** Titel des Menus */
	private final java.util.List<String> contentTitle = new ArrayList<>(1);

	/** General Menu */
	private final CommandList applcmdlist;

	
	/* (non-Javadoc)
	 * @see tfossi.apolge.common.hci.IModel#getApplCmdListContent()
	 */
	@Override
	public IContent getApplCmdListContent() {
		return this.applcmdlist.getContent();
	}

	/** der aktuelle Viewer */
	private IView activeViewer;

	
	/* (non-Javadoc)
	 * @see tfossi.apolge.common.hci.IModel#setInformation(tfossi.apolge.io.screenfactory.Cntr, tfossi.apolge.common.hci.IMenu, tfossi.apolge.io.Screen, tfossi.apolge.io.IContent, boolean, boolean, boolean)
	 */
	@Override
	public final synchronized void setInformation(Cntr cntr, IMenu menu, final Screen scr,
			final IContent content, final boolean notify, final boolean delete, final boolean input) {
		if(LOGGER) logger.debug("Neue Nachricht für " + scr.name() + NTAB + "von "
				+ menu.getClass().getSimpleName() + NTAB + "\"" + content + "\"" + NTAB
				+ (notify ? "mit " : "ohne ") + "Benachrichtigung des "
				+ this.activeViewer.getClass().getSimpleName() + NTAB + (delete ? "mit " : "ohne ")
				+ "Löschen der alten Informationen.");
		assert content != null;

		if (!(Arrays.asList(APPLICATIONSCREENS).contains(scr)
				|| Arrays.asList(MENUSCREENS).contains(scr)
				|| Arrays.asList(VIEWSCREENS).contains(scr) || Arrays.asList(PARAMETERSCREENS)
				.contains(scr))) {
			if(LOGGER) logger.debug(scr + " in ConstValue. nicht freigeschaltet!");
			return;
		}

		switch (scr) {
		case MI:
		case VI:	
		case M:
		case C:
			cntr.store_Content(menu.getView(), menu, scr, content, delete);			
			break;
		case TITEL:
		case MCM:
		case VCM:
		case CPM:			
		default:
			assert false;
		}
		if (notify) {
			if(LOGGER) logger.debug("Informiere Observer und" + NTAB + "alte Nachrichten werden "
					+ (delete ? "" : "NICHT ") + "gelöscht!");
			this.setChanged();
			// Observer ist AView
			NotifyScreens  scrArray = new NotifyScreens( new Screen[] { scr }, input);
			this.notifyObservers(scrArray);
			if (this.countObservers() == 0)
				assert false : "Es gibt keine Observer im Modell!";
			// Löscht Markierung
			this.clearChanged();
		} else {
			if(LOGGER) logger.debug("Observer werden NICHT informiert und" + NTAB + "alte Nachrichten werden "
					+ (delete ? "" : "NICHT ") + "gelöscht!");
		}
	}
	/* (non-Javadoc)
	 * @see tfossi.apolge.common.hci.IModel#setViewer(tfossi.apolge.common.hci.IView)
	 */
	@Override
	public void setViewer(IView view) {
		this.activeViewer = view;
	}

	/* (non-Javadoc)
	 * @see tfossi.apolge.common.hci.IModel#getViewer()
	 */
	@Override
	public IView getViewer() {
		return this.activeViewer;
	}

	// ---- Command Pattern ------------------------------------------------------
	
	
	/* (non-Javadoc)
	 * @see tfossi.apolge.common.hci.IModel#getApplCmdList()
	 */
	@Override
	public final CommandList getApplCmdList() {
		return this.applcmdlist;
	}

	/* (non-Javadoc)
	 * @see tfossi.apolge.common.hci.IModel#getStateCmdList()
	 */
	@Override
	public final CommandList getStateCmdList() {
		assert this.activeViewer != null;
		return this.activeViewer.getViewCmdList();
	}
	
	/* (non-Javadoc)
	 * @see tfossi.apolge.common.hci.IModel#getParaCmdList()
	 */
	@Override
	public final CommandList getParaCmdList() {
		assert this.activeViewer != null;
		return this.activeViewer.getParaCmdList();
	}


	// ---- Filtergruppe ------------------------------------------------------

	/**
	 * Receiver oder null
	 */
	private String receiverFilter = null;
	/**
	 * @return the receiver
	 */
	@Override
	public final String getReceiverFilter() {
		return this.receiverFilter;
	}
	/**
	 * @param receiver the receiver to set
	 */
	@Override
	public final void setReceiverFilter(String receiver) {
		this.receiverFilter = receiver;
	}

	/**
	 * Ergebnisliste zurücksetzen
	 */
	private boolean resetFilter = false;
	/**
	 * @return the reset
	 */
	@Override
	public final boolean isResetFilter() {
		return this.resetFilter;
	}
	/**
	 * @param reset the reset to set
	 */
	@Override
	public final void setResetFilter(boolean reset) {
		this.resetFilter = reset;
	}

	/** 
	 * Ausgabe von Details
	 */
	private boolean detailsFilter = false;
	/**
	 * @return the details
	 */
	@Override
	public final boolean isDetailsFilter() {
		return this.detailsFilter;
	}
	/**
	 * @param details the details to set
	 */
	@Override
	public final void setDetailsFilter(boolean details) {
		this.detailsFilter = details;
	}

	/**
	 * PatternFilter
	 */
	private String patternFilter = ".*";
	/**
	 * @return the filter
	 */
	@Override
	public final String getPatternFilter() {
		return this.patternFilter;
	}

	/**
	 * @param filter the filter to set
	 */
	@Override
	public final void setPatternFilter(String filter) {
		this.patternFilter = filter;
	}


	// ---- Selbstverwaltung --------------------------------------------------
	/** serialVersionUID */
	@SuppressWarnings("unused")
	private final static long serialVersionUID = VERSION;
	/** logger */
	private final static Logger logger = Logger.getLogger(AModel.class.getPackage().getName());

	/** Stellt das abstrakte Grundmodell im MVC bereit 

	 * @param titel
	 * 			Titel des MVC
	 */
	protected AModel(final String titel) {
		this.contentTitle.add(titel);
		System.out.println(Arrays.asList(MENU));
		this.applcmdlist =  new CommandMaps().fetchList(MENU);
	}

	/** @return Liefert SimpleName() der Klasse */
	@Override
	public final String toString() {
		return this.getClass().getSimpleName();
	}
}

// public final void disposeScreen(){
// if (this.grfcntr.getGrafik() != null) {
// // Alle Applikationsscreens einrichten
// for (Screen scr : APPLICATIONSCREENS){
// this.grfcntr.getGrafik().disposeScreen(scr);
// }
// }
// }

//
// /*
// * (non-Javadoc)
// *
// * @see name.tfossi.apolge.hci.IModel#addName(java.lang.String,
// * java.lang.String)
// */
// public void addName(@SuppressWarnings("unused") String string,
// @SuppressWarnings("unused") String para2) {
// //
// }
//
// /*
// * (non-Javadoc)
// *
// * @see name.tfossi.apolge.hci.IModel#removeName(java.lang.String,
// * java.lang.String)
// */
// public void removeName(@SuppressWarnings("unused") String string,
// @SuppressWarnings("unused") String para2) {
// //
// }
//
// /*
// * (non-Javadoc)
// *
// * @see name.tfossi.apolge.hci.IModel#renameName(java.lang.String,
// * java.lang.String, java.lang.String)
// */
// public void renameName(@SuppressWarnings("unused") String string,
// @SuppressWarnings("unused") String string2, @SuppressWarnings("unused") String
// string3) {
// //
// }

// /*
// * (non-Javadoc)
// *
// * @see name.tfossi.apolge.hci.IModel#setInformation(name.tfossi.apolge.data.ISession,
// * name.tfossi.apolge.hci.IMenu,
// * name.tfossi.apolge.general.screen.Screens, java.lang.Object, boolean,
// * boolean)
// */
// public final void setInformation(IGrfCntr gfkcntr, IMenu menu,
// final Screen scr, final Widget content, final boolean notify,
// final boolean delete) {
// if(LOGGER) logger.debug("Neue Nachricht für " + scr.name() + NTAB +
// "von " + menu.getClass().getSimpleName()+NTAB+"\""
// + content + "\""+NTAB+
// (notify?"mit ":"ohne ")+"Benachrichtigung des "+this.activeViewer.getClass().getSimpleName()
// + NTAB+
// (delete?"mit ":"ohne ")+"Löschen der alten Informationen.");
// assert content != null;
//
//
// if (!(Arrays.asList(APPLICATIONSCREENS).contains(scr)
// || Arrays.asList(MENUSCREENS).contains(scr) || Arrays.asList(
// VIEWSCREENS).contains(scr))) {
// if(LOGGER) logger.debug(scr + " in ConstValue. nicht freigeschaltet!");
// return;
// }
//	
// switch (scr) {
// case TITEL:
// assert false;
// //
// // this.contentTitle.clear();
// // if (content instanceof String)
// // this.contentTitle.add(0, (String) content);
// // else if (content instanceof String[])
// // this.contentTitle.addAll(0, Arrays.asList((String[]) content));
// break;
// case MI:
// assert false;
// // gfkcntr.storeSI_Content(menu.getClass(), content, delete);
// // if (gfkcntr.isGrafik())
// // gfkcntr.activateSI_Widget(menu.getClass());
// break;
// case VI:
// assert false;
// // gfkcntr.storeVI_Content(menu.getView().getClass(), content, delete);
// // if (gfkcntr.isGrafik())
// // gfkcntr.activateVI_Widget(menu.getView().getClass());
// break;
// case M:
// assert false;
// // gfkcntr.storeM_Content(content, delete);
// // if (gfkcntr.isGrafik())
// // gfkcntr.activateM_Widget();
// break;
// case MCM:
// assert false;
// // Informationen liegen fest in CommandList applicationcommand in
// // jeder Applikation
// break;
// case SCM:
// assert false;
// // Informationen liegen fest in CommandList statecommand in jedem
// // Viewer
// break;
// case CPM:
// assert false;
// // Informationen liegen indirekt fest in CommandList statecommand in
// // jedem Viewer
// break;
// case C:
// gfkcntr.storeC_Content(menu.getView().getClass(), content, delete);
// // if (gfkcntr.isGrafik())
// // gfkcntr.activateC_Widget(menu, this.activeViewer, content);
// break;
// default:
// assert false;
// }
// if (notify) {
// if(LOGGER) logger.info("Informiere Observer und" + NTAB
// + "alte Nachrichten werden " + (delete ? "" : "NICHT ")
// + "gelöscht!");
// this.setChanged();
// // Observer ist AView
// Screen[] scrArray = new Screen[] { scr };
// this.notifyObservers(scrArray);
// if (this.countObservers() == 0)
// assert false : "Es gibt keine Observer im Modell!";
// // Löscht Markierung
// this.clearChanged();
// } else {
// if(LOGGER) logger.info("Observer werden NICHT informiert und" + NTAB
// + "alte Nachrichten werden " + (delete ? "" : "NICHT ")
// + "gelöscht!");
// }
// }

// /*
// * (non-Javadoc)
// *
// * @see
// name.tfossi.apolge.hci.IModel#getInformation(name.tfossi.apolge.general.screen.Screens )
// */
// public final String[] getInformation(Cntr cntr, IMenu menu, Screen scr) {
// assert false;
// ICmd ic = null;
// if(LOGGER) logger.trace("I/O::" + scr);
// switch (scr) {
// case TITEL:
// return new String[]{this.contentTitle.get(0)};
// case MI:
// return cntr.getSI_Content(menu.getClass()).getString();
// case VI:
// return cntr.getVI_Content(this.activeViewer.getClass()).getString();
// case M:
// return cntr.getM_Content().getString();
// case MCM:
// return null; //applcmdlist.getInformation();
// case SCM:
// return this.activeViewer.getStateCommandInformation();
// case CPM:
// return this.activeViewer.getCmdParameterInformation(ic);
// case C:
// return cntr.getC_Content(this.activeViewer.getClass()).getString();
// // . menu.getCentralInformation(this.activeViewer);
// // return this.activeViewer.getCentralInformation();
// // return session.getC_Content(this.activeViewer.getClass());
// default:
// return new String[]{"Dies","   ist","      Default"};
// // assert false;
// }
// // return null;
// }
