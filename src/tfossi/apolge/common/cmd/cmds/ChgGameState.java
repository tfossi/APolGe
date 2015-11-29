/** 
 * ChgGameState.java
 * Branch cmd
 * APolGe
 * tfossi-team
 * licence GPLv3 
 */
package tfossi.apolge.common.cmd.cmds;

import static tfossi.apolge.common.constants.ConstMethod.setTestMenuFont;
import static tfossi.apolge.common.constants.ConstValue.LOGGER;
import static tfossi.apolge.common.constants.ConstValue.LOGTAB;
import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;

import java.io.IOException;
import java.io.InvalidClassException;
import java.net.SocketException;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Widget;

import tfossi.apolge.ces.client.states.IGameStateCall;
import tfossi.apolge.ces.subs.credit.CreditMenu;
import tfossi.apolge.common.cmd.ACmd;
import tfossi.apolge.common.cmd.Command;
import tfossi.apolge.common.cmd.ICmd;
import tfossi.apolge.common.cmd.IUnvisible;
import tfossi.apolge.common.hci.AMenu;
import tfossi.apolge.io.ContentString;
/**
 * Befehl: Rufe Creditmenü auf<br>
 * Gehört zur Gruppe der menuspezifischen Befehle.<br/>
 * <b>Befehl: </b>credit<br>
 * <b>Optionen:</b> [keine] <br>
 * 
 * @.pattern Command: concrete command
 * @see ACmd
 * @see AMenu
 * @see CreditMenu
 * @author tfossi
 * @version 13.08.2014
 * @modified -
 * @since Java 1.6
 */
public class ChgGameState extends ACmd implements IUnvisible {

	// ---- Command Pattern
	// ------------------------------------------------------
	/**
	 * TODO Comment
	 *
	 * @author tfossi
	 * @version 13.08.2014
	 * @modified -
	 * @since Java 1.6
	 */
	public enum c {
		/** sw */
		sw(false, null, null, 0, 0),
		/** file */
		file(false, null, null, 0, 0);

		/** show */
		final boolean show;
		/** buttontext */
		public final String buttontext;
		/** tooltiptext */
		final String tooltiptext;
		/** parameterMin */
		public final int parameterMin;
		/** parameterMax */
		public final int parameterMax;

		/**
		 * @param show
		 *            Anzeige im GUI
		 * @param buttontext
		 *            Test des Buttons
		 * @param tooltiptest
		 *            Hilfetext
		 * @param parameterMin
		 *            Minimalzahl der Parameter
		 * @param parameterMax
		 *            Maximalzahl der Parameter
		 */
		private c(boolean show,
				String buttontext,
				String tooltiptest,
				int parameterMin,
				int parameterMax) {
			this.show = show;
			this.buttontext = buttontext;
			this.tooltiptext = tooltiptest;
			this.parameterMin = parameterMin;
			this.parameterMax = parameterMax;
		}

		/**
		 * @return liefert den Parameterwert für GUI
		 */
		public static ContentString getParameter() {
			return new ContentString((String[]) null);
		}

		/**
		 * Aufruf der Parameterfunktion
		 * 
		 * @param cmd
		 *            der Befehl selber
		 * @param value
		 *            Queue mit den Parameterstrings incl ggfs. deren Werten
		 * @throws IOException -
		 * @throws SocketException -
		 * @throws InvalidClassException -
		 */
		public static void call(ICmd cmd, String... value) throws InvalidClassException, SocketException, IOException {
			((IGameStateCall) cmd.getReceiver()).aufrufGs(cmd.getData(),
			value);
		}
		/** @return bei <code>true</code> anzeigen, sonst nicht. */
		public boolean getShow() {
			return this.show;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see name.tfossi.apolge.command.ICmd#command()
	 */
	@Override
	public final void command() {			
		Command.parameterCheck(this, true);
	}

	/** Vorbereitung und Aufruf der Hilfefunktion */
	@Override
	public final void help() {
		//
	}

	// ---- SWT
	// ------------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * name.tfossi.apolge.command.AbstractCmd#buildSWTButton(org.eclipse.swt.
	 * widgets.Group )
	 */
	@Override
	public final List<Widget> buildSWTWidget(final Group grp) {
		assert grp != null;
		this.group = grp;
		// assert false;
		if (this.widget == null || this.widget.size() == 0) {
			if(LOGGER) logger.debug("SWT::" + this.toString()
					+ "\tNeue Widgetinstanz auf [" + grp + "] anlegen");
			Button button = new Button(grp, SWT.PUSH);

			setTestMenuFont(button);

			button.setText(this.getCode());
			button.setToolTipText("Lizenz und Autoren");
			button.setEnabled(true);
			button.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					command();
				}
			});
			this.widget.add(button);
		} else {
			for (Widget w : this.widget) {// if
											// (!this.widget.get(0).isDisposed())
											// {

				if(LOGGER) logger.debug("SWT::" + this.toString()
						+ "\tVorhandene Widgetinstanz auf Gruppe [" + grp
						+ "] gelegt");
				((Button) w).setParent(grp);
			}
		}
		return this.widget;
	}

	// ---- Selbstverwaltung
	// -----------------------------------------------------
	/** serialVersionUID */
	private final static long serialVersionUID = VERSION;
	/** logger */
	private final static Logger logger = Logger.getLogger(ChgGameState.class
			.getPackage().getName());


	/**
	 * Befehl: Rufe Creditmenu auf
	 * @param name -
	 * @param unvisibleMode - 
	 * @modified -
	 */
	public ChgGameState(final String name, boolean unvisibleMode) {
		super(name, unvisibleMode);
	}

	/* (non-Javadoc)
	 * @see name.tfossi.apolge.common.cmd.ICmd#testParameter(java.lang.String)
	 */
	@Override
	public final String testParameter(final String parameter) {
		try {
			// Testen, ob Parameter bekannt ist
			c.valueOf(parameter);
		} catch (IllegalArgumentException e) {
			// Testen, ob es ein Sonderzeichen '?' im Button etc ist
			for (c cp : c.values()) {
				
				if (cp.buttontext!=null && cp.buttontext.equals(parameter)) {
					return cp.name();
				}
			}
			return null;
		}
		return parameter;
	}

	/* (non-Javadoc)
	 * @see name.tfossi.apolge.common.cmd.ICmd#maxParameter(java.lang.String)
	 */
	@Override
	public final int maxParameter(final String parameter) {
		return c.valueOf(parameter).parameterMax;
	}

	/* (non-Javadoc)
	 * @see name.tfossi.apolge.common.cmd.ICmd#minParameter(java.lang.String)
	 */
	@Override
	public final int minParameter(final String parameter) {
		return c.valueOf(parameter).parameterMin;
	}

	/* (non-Javadoc)
	 * @see name.tfossi.apolge.common.cmd.ICmd#call(java.lang.String, java.lang.String[])
	 */
	@SuppressWarnings("static-access")
	@Override
	public final void call(final String parameter, final String... value) {

		
		try {
			if(LOGGER) logger.trace("Parameter: "+parameter+LOGTAB+"Value: "+Arrays.toString(value));
			c.valueOf(parameter).call(this, value);
		} catch (InvalidClassException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
