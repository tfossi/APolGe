/**
 * Screen.java
 * Branch io
 * APolGe
 * tfossi-team
 * licence GPLv3  
 */
package tfossi.apolge.io;

import static tfossi.apolge.common.constants.ConstValue.LOGGER;
import static tfossi.apolge.common.constants.ConstValue.NTAB;
import static tfossi.apolge.common.constants.ConstValueExtension.HEADTEXT;
import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;

import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Widget;

import tfossi.apolge.common.constants.ConstValue;
import tfossi.apolge.common.hci.IModel;
import tfossi.apolge.common.hci.IView;
import tfossi.apolge.io.screenfactory.layout.Layout_C;
import tfossi.apolge.io.screenfactory.layout.Layout_M;
import tfossi.apolge.io.screenfactory.layout.Layout_MCM;
import tfossi.apolge.io.screenfactory.layout.Layout_MI;
import tfossi.apolge.io.screenfactory.layout.Layout_SHELL;
import tfossi.apolge.io.screenfactory.layout.Layout_VCM;
import tfossi.apolge.io.screenfactory.layout.Layout_VI;
import tfossi.apolge.io.screenfactory.layout.Layout_VPM;

/**
 * Definiert alle Screen und <br \>
 * <ul>
 * <li>legt den Screenname fest</li>
 * <li>baut das Layout</li>
 * <li>baut das Basewidget</li>
 * <li>enthält den Titel</li>
 * </ul>
 * 
 * Die Basisgruppe, in der die Gruppenarrays liegen. Jeder Bildschirmbereich hat eine
 * Basisgruppe. Die Basisgruppe ist in n erweiterte Gruppen besetzt, mindestens aber
 * eine. Auf dieser Basisgruppe werden die Widgets eingesetz. Dient dazu, die
 * <code>basegrp</code> nochmals in Segemente aufteilen zu können.<br>
 * Group basegrp = new Group(shell, SWT.NONE);
 * 
 * @author tfossi
 * @version 12.08.2014
 * @modified -
 * @since Java 1.6
 */
public enum Screen {
	/** Sonderfall: SHELL */
	SHELL("SHELL-Screen") {
		/* (non-Javadoc)
		 * @see name.tfossi.apolge.io.Screen#layout(org.eclipse.swt.widgets.Shell, java.util.Map)
		 */
		@Override
		public final Group layout(Shell shell, Map<Screen, Group> groups) {
			if(LOGGER) logger.debug(this+" layouten");
			return new Layout_SHELL().doIt(shell, groups, this.getTitel());
		}
	},
	/** Screenbereich Headerzeile */
	TITEL("APolGe " + HEADTEXT) {
		/* (non-Javadoc)
		 * @see name.tfossi.apolge.io.Screen#buildWidget(name.tfossi.xapolge201rc.hci.IView, name.tfossi.xapolge201rc.hci.IModel, org.eclipse.swt.widgets.Group)
		 */
		@Override
		public final java.util.List<Widget> buildWidget(IView view,
				IModel model, Group basegrp) {
			if(LOGGER) logger.debug("SWT::" + this.toString() + "\tNeue Widgetinstanz auf [" + basegrp
					+ "] anlegen");
			return new java.util.ArrayList<Widget>();
		}
	},
	/** Screenbereich ApplicationCommandMenu */
	MCM("Menubefehle") {

		/* (non-Javadoc)
		 * @see name.tfossi.apolge.io.Screen#buildWidget(name.tfossi.xapolge201rc.hci.IView, name.tfossi.xapolge201rc.hci.IModel, org.eclipse.swt.widgets.Group)
		 */
		@Override
		public final java.util.List<Widget> buildWidget(IView view,
				IModel model, Group basegrp) {
			if(LOGGER) logger.debug("SWT::" + this.toString() + "\tNeue Widgetinstanz auf [" + basegrp
					+ "] anlegen");
			return model.getApplCmdList().buildSWTWidget(basegrp);
		}

		/* (non-Javadoc)
		 * @see name.tfossi.apolge.io.Screen#layout(org.eclipse.swt.widgets.Shell, java.util.Map)
		 */
		@Override
		public final Group layout(Shell shell, Map<Screen, Group> groups) {
			if(LOGGER) logger.debug(this+" layouten");
			return new Layout_MCM().doIt(shell, groups, this.getTitel());
		}
	},
	/** Screenbereich StateCommandMenu */
	VCM("Viewbefehle") {

		/* (non-Javadoc)
		 * @see name.tfossi.apolge.io.Screen#buildWidget(name.tfossi.xapolge201rc.hci.IView, name.tfossi.xapolge201rc.hci.IModel, org.eclipse.swt.widgets.Group)
		 */
		@Override
		public final java.util.List<Widget> buildWidget(IView view,
				IModel model, Group basegrp) {
			if(LOGGER) logger.debug("SWT::" + this.toString() + "\tNeue Widgetinstanz auf [" + basegrp
					+ "] anlegen");
			return model.getStateCmdList().buildSWTWidget(basegrp);
		}

		/* (non-Javadoc)
		 * @see name.tfossi.apolge.io.Screen#layout(org.eclipse.swt.widgets.Shell, java.util.Map)
		 */
		@Override
		public final Group layout(Shell shell, Map<Screen, Group> groups) {
			if(LOGGER) logger.debug(this+" layouten");
			return new Layout_VCM().doIt(shell, groups, this.getTitel());
		}
	},
	/**
	 * TODO Comment
	 *
	 * @author tfossi
	 * @version 12.08.2014
	 * @modified -
	 * @since Java 1.6
	 */
	CPM("Commandparameter") {
		
		/* (non-Javadoc)
		 * @see name.tfossi.apolge.io.Screen#layout(org.eclipse.swt.widgets.Shell, java.util.Map)
		 */
		@Override
		public final Group layout(Shell shell, Map<Screen, Group> groups) {
			if(LOGGER) logger.debug(this+" layouten");
			return new Layout_VPM().doIt(shell, groups, this.getTitel());
		}
	},
	/**
	 * TODO Comment
	 *
	 * @author tfossi
	 * @version 12.08.2014
	 * @modified -
	 * @since Java 1.6
	 */
	MI("Menuinformationen") {
		
		/* (non-Javadoc)
		 * @see name.tfossi.apolge.io.Screen#buildWidget(name.tfossi.xapolge201rc.hci.IView, name.tfossi.xapolge201rc.hci.IModel, org.eclipse.swt.widgets.Group)
		 */
		@Override
		public final java.util.List<Widget> buildWidget(IView view,
				IModel model, Group basegrp) {
			java.util.List<Widget> listOfWidgets = new java.util.ArrayList<Widget>();
			if(LOGGER) logger.debug("SWT::" + this.toString() + "\tNeue Widgetinstanz auf [" + basegrp
					+ "] anlegen");
			List rc = new List(basegrp, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL);
			FormData data = new FormData();
			data.right = new FormAttachment(100, 0);
			data.left = new FormAttachment(0, 0);
			data.top = new FormAttachment(0, 0);
			data.bottom = new FormAttachment(100, 0);
			((Control) rc).setLayoutData(data);
			rc.setItems(new String[] { "HALLO from SCREENS" });

			rc.setTopIndex(rc.getItemCount() - 3);
			listOfWidgets.add(rc);
			return listOfWidgets;
		}

		/* (non-Javadoc)
		 * @see name.tfossi.apolge.io.Screen#layout(org.eclipse.swt.widgets.Shell, java.util.Map)
		 */
		@Override
		public final Group layout(Shell shell, Map<Screen, Group> groups) {
			if(LOGGER) logger.debug(this+" layouten");
			return new Layout_MI().doIt(shell, groups, this.getTitel());
		}
	},
	/**
	 * TODO Comment
	 *
	 * @author tfossi
	 * @version 12.08.2014
	 * @modified -
	 * @since Java 1.6
	 */
	VI("Viewerinformationen") {

		/* (non-Javadoc)
		 * @see name.tfossi.apolge.io.Screen#buildWidget(name.tfossi.xapolge201rc.hci.IView, name.tfossi.xapolge201rc.hci.IModel, org.eclipse.swt.widgets.Group)
		 */
		@Override
		public final java.util.List<Widget> buildWidget(IView view,
				IModel model, Group basegrp) {
			java.util.List<Widget> listOfWidgets = new java.util.ArrayList<Widget>();
			listOfWidgets.add(view.buildVIWidget(basegrp));			
			return listOfWidgets;
		}

		/* (non-Javadoc)
		 * @see name.tfossi.apolge.io.Screen#layout(org.eclipse.swt.widgets.Shell, java.util.Map)
		 */
		@Override
		public final Group layout(Shell shell, Map<Screen, Group> groups) {
			if(LOGGER) logger.debug(this+" layouten");
			return new Layout_VI().doIt(shell, groups, this.getTitel());
		}
	},
	/**
	 * TODO Comment
	 *
	 * @author tfossi
	 * @version 12.08.2014
	 * @modified -
	 * @since Java 1.6
	 */
	M("Nachrichten") {

		/* (non-Javadoc)
		 * @see name.tfossi.apolge.io.Screen#buildWidget(name.tfossi.xapolge201rc.hci.IView, name.tfossi.xapolge201rc.hci.IModel, org.eclipse.swt.widgets.Group)
		 */
		@Override
		public final java.util.List<Widget> buildWidget(IView view,
				IModel model, Group basegrp) {
			java.util.List<Widget> listOfWidgets = new java.util.ArrayList<Widget>();
			if(LOGGER) logger.debug("SWT::" + this.toString() + " \tNeue Widgetinstanz auf [" + basegrp
					+ "] anlegen");
			List rc = new List(basegrp, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL);
			((Control) rc).setLayoutData(new RowData(basegrp.getClientArea().width - 30, basegrp
					.getClientArea().height - 30));
			rc.setTopIndex(rc.getItemCount() - 3);
			listOfWidgets.add(rc);
			return listOfWidgets;
		}

		/* (non-Javadoc)
		 * @see name.tfossi.apolge.io.Screen#layout(org.eclipse.swt.widgets.Shell, java.util.Map)
		 */
		@Override
		public final Group layout(Shell shell, Map<Screen, Group> groups) {
			if(LOGGER) logger.debug(this+" layouten");
			return new Layout_M().doIt(shell, groups, this.getTitel());
		}
	},
	/** C */
	C("Zentrale Anzeige") {

		/* (non-Javadoc)
		 * @see name.tfossi.apolge.io.Screen#buildWidget(name.tfossi.xapolge201rc.hci.IView, name.tfossi.xapolge201rc.hci.IModel, org.eclipse.swt.widgets.Group)
		 */
		@Override
		public final java.util.List<Widget> buildWidget(IView view,
				IModel model, Group basegrp) {
			if(LOGGER) logger.debug("SWT::" + this.toString() + " \tNeue Widgetinstanz auf [" + basegrp
					+ "] anlegen");
			return view.buildCWidget(/* model, */basegrp/* , null */);
		}

		/* (non-Javadoc)
		 * @see name.tfossi.apolge.io.Screen#layout(org.eclipse.swt.widgets.Shell, java.util.Map)
		 */
		@Override
		public final Group layout(Shell shell, Map<Screen, Group> groups) {
			if(LOGGER) logger.debug(this+" layouten");
			return new Layout_C().doIt(shell, groups, this.getTitel());
		}
	};

	/** Beschreibung des Screen */
	private final String titel;

	/**
	 * Flags des Screensets Damit werden die diversen Screen angesteuert.
	 * 
	 * @param titel
	 *            Beschreibung des Screen
	 */
	Screen(final String titel) {
		this.titel = titel;
	}

	/** @return Überschrift des Screen */
	final String getTitel() {
		return this.titel;
	}

	/**
	 * Es wird auf die Basegroup eine Widgetgruppe gebaut. 
	 * 
	 * @see ConstValue
	 *  @param  view
	 *  		aktueller View
	 * @param model
	 *            das dazugehörige Model (ist nur für MCM-Screen notwendig!)
	 * @param basegrp
	 *            die Basisgruppe, auf der die Widgets liegen
	 * @return das Map mit dem Namen und den Widgets
	 */
	public java.util.List<Widget> buildWidget(IView view, IModel model, Group basegrp) {
		assert false : this.name() + NTAB + "View: " + view + NTAB + "Model: " + model + NTAB
				+ "Gruppe: " + basegrp;
		return null;
	}

	/**
	 * Stellt das Layout des Subscreens und der <code>SHELL</code> ein.
	 * 
	 * @param shell
	 *            <code>Shell</code>
	 * @param group
	 *            Widgetgruppe, die layoutet werden soll
	 * @return -
	 */
	@SuppressWarnings("static-method")
	public Group layout(final Shell shell, final Map<Screen, Group> group) {
		assert false : "...sollte überschrieben sein!";
		if(LOGGER) logger.trace(group + "/" + shell);
		return null;
	}

	// ---- Selbstverwaltung -----------------------------------------------------
	/** serialVersionUID */
	private final static long serialVersionUID = VERSION;

	/** logger */
	final static Logger logger = Logger.getLogger(Screen.class.getPackage().getName());

}
