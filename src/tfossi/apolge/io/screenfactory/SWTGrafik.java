/**
 * SWTGrafik.java
 * Branch io
 * APolGe
 * tfossi-team
 * licence GPLv3  
 */
package tfossi.apolge.io.screenfactory;

import static tfossi.apolge.common.constants.ConstValue.LOGGER;
import static tfossi.apolge.common.constants.ConstValue.NTAB;
import static tfossi.apolge.common.constants.ConstValueExtension.HEADTEXT;
import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Widget;

import tfossi.apolge.common.hci.IView;
import tfossi.apolge.io.Screen;

/**
 * Richtet eine SWT-Grafik ein, speichert Groups, Shell und Display.<br>
 * 
 * @author tfossi
 * @version 12.08.2014
 * @modified -
 * @since Java 1.6
 */
public class SWTGrafik {

	/** Nimmt die Displayinstanz auf */
	private Display display;

	/**
	 * Wartet, wenn Display bereit ist, auf GUI-Events
	 */
	final void displayLive() {
		if (!this.display.readAndDispatch())
			this.display.sleep();
	}

	/** Nimmt die Shellinstanz auf */
	private Shell shell;

	/** Nimmt die Basegroups mit den Layouts auf */
	private final Map<Screen, Group> baseGroups = new HashMap<>();

	/**
	 * @param scr
	 *            der Screen
	 * @return die Group
	 */
	final Group getBaseGroup(Screen scr) {
		return this.baseGroups.get(scr);
	}

	/** @return die Shell */
	final Shell getShell() {
		return this.shell;
	}

	/**
	 * Richte Basis-Grafik ein mit FormLayout
	 * 
	 * 
	 * @.post Display ist definiert
	 * @.post Shell ist definiert
	 * @.post Shell ist layoutet
	 * @.post Die Groups für ApplicationCommandMenu, StateCommandMenu, StateParamterMenu,
	 *        GeneralInfo, SpecialInfo, Message und Center sind definiert
	 */
	private final void initialisiereSWT() {		
		if(LOGGER) logger.trace("SWT::Richte Display und Shell ein!");
		if (this.display == null)
			this.display = new Display();
		this.shell = new Shell(this.display);

		assert Screen.SHELL != null;

		// Die Reihenfolge ist wichtig!
		if(LOGGER) logger.trace("SWT::Stelle der Layouts der Screen SHELL, MCM,  ...M,C ein!");
		Screen.SHELL.layout(this.shell, null);
		this.baseGroups.put(Screen.VI, Screen.VI.layout(this.shell, this.baseGroups));
		this.baseGroups.put(Screen.MCM, Screen.MCM.layout(this.shell, this.baseGroups));
		this.baseGroups.put(Screen.VCM, Screen.VCM.layout(this.shell, this.baseGroups));
		this.baseGroups.put(Screen.CPM, Screen.CPM.layout(this.shell, this.baseGroups));
		this.baseGroups.put(Screen.M, Screen.M.layout(this.shell, this.baseGroups));
		this.baseGroups.put(Screen.MI, Screen.MI.layout(this.shell, this.baseGroups));
		this.baseGroups.put(Screen.C, Screen.C.layout(this.shell, this.baseGroups));
	}

	/**
	 * Ausgehend von der Basisgruppe des Screen werden alle Gruppenelemente des Screen
	 * (neu) layouted
	 * @param screen -
	 * @param view -
	 */
	final void refreshScreen(Screen screen, IView view) {
		Group basegroup;
		switch (screen) {
		case M:
		case MCM:
		case VCM:
		case CPM:
		case VI:
		case MI:
		case C:
			basegroup = this.baseGroups.get(screen);
			if (basegroup != null && !basegroup.isDisposed()) {
				basegroup.layout();

				for (Control cntrl : basegroup.getChildren()) {
					if (!(cntrl instanceof Composite)) {
						continue;
					}
					Composite comp = (Composite) cntrl;
					comp.layout();
				}
			}
			break;
		case TITEL:
			this.shell.setText(HEADTEXT + view.getClass().getSimpleName());
			break;
		default:
			assert false : screen;
		}
	}

	/**
	 * @param screen
	 *            Löschen der Widges eines Screen
	 */
	final void disposeScreen(Screen screen) {
		Group basegroup = this.baseGroups.get(screen);
		switch (screen) {
		case M:
			if (basegroup != null && !basegroup.isDisposed()) {
				if(LOGGER) logger.trace("****Lösche Elemente der Gruppe****" + NTAB + basegroup);
				for (Control cntrl : basegroup.getChildren()) {
					Widget comp = cntrl;
					if(LOGGER) logger.trace("****Lösche Element****" + NTAB + comp);
					comp.dispose();
				}
			}
			break;
		case MI:
			if (basegroup != null && !basegroup.isDisposed()) {
				if(LOGGER) logger.trace("****Lösche Elemente der Gruppe****" + NTAB + basegroup);
				for (Control cntrl : basegroup.getChildren()) {
					Widget comp = cntrl;
					if(LOGGER) logger.trace("****Lösche Element****" + NTAB + comp);
					comp.dispose();
				}
			}
			break;
		case VCM:
			if (basegroup != null && !basegroup.isDisposed()) {
				if(LOGGER) logger.trace("****Lösche Elemente der Gruppe****" + NTAB + basegroup);
				for (Control cntrl : basegroup.getChildren()) {
					Widget comp = cntrl;
					if(LOGGER) logger.trace("****Lösche Element****" + NTAB + comp);
					comp.dispose();
				}
			}
			break;

		case CPM:
			if (basegroup != null && !basegroup.isDisposed()) {
				if(LOGGER) logger.trace("****Lösche Elemente der Gruppe****" + NTAB + basegroup);
				for (Control cntrl : basegroup.getChildren()) {
					Widget comp = cntrl;
					if(LOGGER) logger.trace("****Lösche Element****" + NTAB + comp);
					comp.dispose();
				}
			}
			break;
		case VI:
			if (basegroup != null && !basegroup.isDisposed()) {
				if(LOGGER) logger.trace("****Lösche Elemente der Gruppe****" + NTAB + basegroup);
				for (Control cntrl : basegroup.getChildren()) {
					Widget comp = cntrl;
					if(LOGGER) logger.trace("****Lösche Element****" + NTAB + comp);
					comp.dispose();
				}
			}
			break;
		case C:
			if (basegroup != null && !basegroup.isDisposed()) {
				if(LOGGER) logger.trace("****Lösche Elemente der Gruppe****" + NTAB + basegroup);
				for (Control cntrl : basegroup.getChildren()) {
					Widget comp = cntrl;
					if(LOGGER) logger.trace("****Lösche Element****" + NTAB + comp);
					comp.dispose();
				}
			}
			break;
		case TITEL:
			// Ist kein SWT-Element
			break;
		case MCM:
			if (basegroup != null && !basegroup.isDisposed()) {
				if(LOGGER) logger.trace("****Lösche Elemente der Gruppe****" + NTAB + basegroup);
				for (Control cntrl : basegroup.getChildren()) {
					Widget comp = cntrl;
					if(LOGGER) logger.trace("****Lösche Element****" + NTAB + comp);
					comp.dispose();
				}
			}
			break;
		default:
			assert false : screen.toString();
		}
		if (!this.shell.isDisposed())
			this.shell.layout();
	}

	/**
	 * Screen wieder sichtbar machen
	 * 
	 * @param screen
	 *            der Screen
	 * @param widgetsMap
	 *            die WidgetsMap
	 */
	final void visibleScreen(Screen screen, Map<String, Widget> widgetsMap) {
		Group basegroup = this.baseGroups.get(screen);
		switch (screen) {
		case M:
			if (basegroup != null && !basegroup.isDisposed()) {
				if(LOGGER) logger.trace("****Blende Elemente der Gruppe aus****" + NTAB + basegroup);
				for (Control cntrl : basegroup.getChildren()) {
					cntrl.setVisible(false);
				}
			}
			assert false;
			break;
		case MI:
			if (basegroup != null && !basegroup.isDisposed()) {
				if(LOGGER) logger.trace("GUI:: ****Blende Elemente der Gruppe ein****" + NTAB + basegroup);
				for (Control cntrl : basegroup.getChildren()) {
					for (String set : widgetsMap.keySet()) {
						if (widgetsMap.get(set).hashCode() == cntrl.hashCode()) {
							cntrl.setVisible(true);
						}
					}
				}
			}
			break;
		case VCM:
			if (basegroup != null && !basegroup.isDisposed()) {
				if(LOGGER) logger.trace("****Blende Elemente der Gruppe ein****" + NTAB + basegroup);
				for (Control cntrl : basegroup.getChildren()) {
					for (String set : widgetsMap.keySet()) {
						if (widgetsMap.get(set).hashCode() == cntrl.hashCode()) {
							System.out.println("> " + widgetsMap.get(set).hashCode());
							cntrl.setVisible(true);
						}
					}
				}
			}
			assert false;
			break;
		case CPM:
			if (basegroup != null && !basegroup.isDisposed()) {
				if(LOGGER) logger.trace("****Blende Elemente der Gruppe aus****" + NTAB + basegroup);
				for (Control cntrl : basegroup.getChildren()) {
					cntrl.setVisible(false);
				}
			}
			assert false;
			break;
		case VI:
			if (basegroup != null && !basegroup.isDisposed()) {
				if(LOGGER) logger.trace("GUI:: ****Blende Elemente der Gruppe ein****" + NTAB + basegroup);
				for (Control cntrl : basegroup.getChildren()) {
					for (String set : widgetsMap.keySet()) {
						if (widgetsMap.get(set).hashCode() == cntrl.hashCode()) {
							cntrl.setVisible(true);
						}
					}
				}
			}
			break;
		case C:
			if (basegroup != null && !basegroup.isDisposed()) {
				if(LOGGER) logger.trace("****Blende Elemente der Gruppe ein****" + NTAB + basegroup);
				for (Control cntrl : basegroup.getChildren()) {
					for (String set : widgetsMap.keySet()) {
						if (widgetsMap.get(set).hashCode() == cntrl.hashCode()) {
							cntrl.setVisible(true);
						}
					}
				}
			}
			break;
		case TITEL:
			// Ist kein SWT-Element
			break;
		case MCM:assert false;
			if (basegroup != null && !basegroup.isDisposed()) {
				if(LOGGER) logger.trace("GUI:: ****Blende Elemente der Gruppe ein****" + NTAB + basegroup);
				assert false;
				for (Control cntrl : basegroup.getChildren()) {
					for (String set : widgetsMap.keySet()) {
						if (widgetsMap.get(set).hashCode() == cntrl.hashCode()) {
							System.out.println("> " + widgetsMap.get(set).hashCode());
							cntrl.setVisible(true);
						}
					}
				}
			}
			assert false;

			break;
		default:
			assert false : screen.toString();
		}
		if (!this.shell.isDisposed())
			this.shell.layout();
	}

	/**
	 * @param screen
	 *            Screen unsichtbar machen
	 */
	final void unvisibleScreen(Screen screen) {
		Group basegroup = this.baseGroups.get(screen);
		switch (screen) {
		case M:
			// Messages
			if (basegroup != null && !basegroup.isDisposed()) {
				if(LOGGER) logger.trace("****Blende Elemente der Gruppe aus****" + NTAB + basegroup);
				for (Control cntrl : basegroup.getChildren()) {
					cntrl.setVisible(false);
				}
			}
			break;
		case MI:
			if (basegroup != null && !basegroup.isDisposed()) {
				if(LOGGER) logger.trace("****Blende Elemente der Gruppe aus****" + NTAB + basegroup);
				for (Control cntrl : basegroup.getChildren()) {
					cntrl.setVisible(false);
				}
			}
			break;
		case VCM:
			if (basegroup != null && !basegroup.isDisposed()) {
				if(LOGGER) logger.trace("****Blende Elemente der Gruppe aus****" + NTAB + basegroup);
				for (Control cntrl : basegroup.getChildren()) {
					// cntrl.setVisible(false);
					cntrl.dispose();
				}
			}
			break;

		case CPM:
			if (basegroup != null && !basegroup.isDisposed()) {
				if(LOGGER) logger.trace("****Blende Elemente der Gruppe aus****" + NTAB + basegroup);
				for (Control cntrl : basegroup.getChildren()) {
					cntrl.dispose();
				}
			}
			break;
		case VI:
			if (basegroup != null && !basegroup.isDisposed()) {
				if(LOGGER) logger.trace("****Blende Elemente der Gruppe aus****" + NTAB + basegroup);
				for (Control cntrl : basegroup.getChildren()) {
					if(LOGGER) logger.trace(cntrl + ": " + cntrl.getVisible());
					cntrl.setVisible(false);
				}
			}
			break;
		case C:
			if (basegroup != null && !basegroup.isDisposed()) {
				if(LOGGER) logger.trace("****Blende Elemente der Gruppe aus****" + NTAB + basegroup);
				for (Control cntrl : basegroup.getChildren()) {
					cntrl.setVisible(false);
				}
			}
			break;
		case TITEL:
			// Ist kein SWT-Element
			break;
		case MCM:
			if (basegroup != null && !basegroup.isDisposed()) {
				if(LOGGER) logger.trace("****Blende Elemente der Gruppe aus****" + NTAB + basegroup);
//				for (Control cntrl : basegroup.getChildren()) {
//					cntrl.setVisible(false);
//					cntrl.dispose();
//				}
			}
			break;
		default:
			assert false : screen.toString();
		}
		if (!this.shell.isDisposed())
			this.shell.layout();
	}

	// ---- Selbstverwaltung -----------------------------------------------------
	/** serialVersionUID */
	@SuppressWarnings("unused")
	private final static long serialVersionUID = VERSION;
	/** logger */
	private final static Logger logger = Logger.getLogger(SWTGrafik.class.getPackage().getName());

	/**
	 * Richtet eine SWT-Grafik ein.
	 * <ul>
	 * <li>Display</li>
	 * <li>Shell</li> *
	 * <li>Layouts</li>
	 * <li></li>
	 * <li></li>
	 * </ul>
	 * 
	 * @see Screen#layout(Shell, Map)
	 * */
	private final long threadId;
	/**
	 * TODO Comment
	 * @param threadId -
	 * @modified -
	 */
	public SWTGrafik(long threadId) {
		this.threadId = threadId;
//		private SWTGrafik() {
		this.initialisiereSWT();
		this.shell.setText(""+this.threadId);
		if(LOGGER) logger.info("SWT::Öffne Shell");
		this.shell.open();
	}

//	private final static SWTGrafik instance = new SWTGrafik();
//
//	/**
//	 * @return Instanz der SWTGrafik
//	 */
//	public final static SWTGrafik getSWTGrafik() {
//		return SWTGrafik.instance;
//	}

}
