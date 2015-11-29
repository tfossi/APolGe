/** 
 * ACmd.java
 * Branch cmd
 * APolGe
 * tfossi-team
 * licence GPLv3 
 */
package tfossi.apolge.common.cmd;


import static tfossi.apolge.common.constants.ConstValue.LOGGER;
import static tfossi.apolge.common.constants.ConstValue.NTAB;
import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.apache.log4j.Logger;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Widget;

import tfossi.apolge.common.hci.AMenu;
import tfossi.apolge.common.hci.IMenu;
import tfossi.apolge.common.hci.menu.IReceiver;
import tfossi.apolge.io.ContentString;
import tfossi.apolge.io.Screen;
import tfossi.apolge.io.screenfactory.Cntr;

/**
 * Stellt Methoden für Befehle bereit und definiert die Schnittstelle zur
 * Ausführung eines konkreten Befehls.<br />
 * Ein der folgenden Varianten muss der Befehl implementieren:<br>
 * <b>Statewechselbefehl Generell</b><br>
 * {@code this.getReceiver()).generelleMethod();
 * return true; // Menu verlassen und State wechseln } <br>
 * <b>Statewechselbefehl Menuspezifisch</b><br>
 * {@code
 * ((XyzMenu)this.getReceiver()).menuMethod(); return true; // Menu verlassen und State
 * wechseln }
 * <br>
 * <b>Parameterbefehl</b> <br>
 * {@code if (shell!=null) SWT else getParameter().size() -prüfen
 * ... ((XyzMenu) this.getReceiver()).setXyz(getParameter().poll()); return false; //
 * Menu nicht verlassen }
 * <br>
 * 
 * @.pattern Command: abstract command
 * @author tfossi
 * @version 12.08.2014
 * @modified -
 * @since Java 1.6
 */
public abstract class ACmd implements Serializable, Cloneable, ICmd {
	/**
	 * Nachrichten ausgeben, wenn der Command-Befehl die falschen Parameter hat
	 * 
	 * @param message
	 *            Der Nachrichtentext
	 */
	public final void help(String[] message) {
		IMenu menu = (IMenu) this.receiver;
		Cntr cntr = menu.getStateContext().getCntr();
		menu.setInformation(cntr, Screen.M, new ContentString(message), false,
				false, false);
	}

	// ---- Receiver
	// -------------------------------------------------------------

	/**
	 * Im Receiver ist der eigentlichen Befehl definiert. Grundsätzlich wird in
	 * jedem konkretem Befehl der Aufruf ICmd#command() implementiert. Der
	 * leitet in der Regel den Aufruf an die jeweilige Methode im Receiver
	 * weiter, wo die eigentliche Befehlsausführung geschieht.
	 * 
	 * @see AMenu
	 */
	private transient IReceiver receiver = null;
	/** Signalisiert, ob der Befehl "temporär nicht auswählbar" ist. SWT-only */
	protected transient boolean grayed = false;
	/** Eigenschaft des Befehls, ist nicht manuell eingebbar */
	protected transient boolean unvisible = true;
	/** Manueller Eingabecode */
	private transient String keycode;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * name.tfossi.apolge200.command.ICmd#setReceiver(name.tfossi.apolge200.command
	 * .IReceiver )
	 */
	@Override
	public final void setReceiver(final IReceiver menu, String[] grayedArray) {
		assert grayedArray != null;
		assert menu != null;
		this.receiver = menu;
		if(LOGGER) logger.debug("Für das Command [" + this.toString() + "]" + NTAB
				+ "den Receiver [" + menu.toString() + "] eingetragen.");
		
		for (String code : grayedArray) {
			if (this.getCode().equals(code)) {
				this.setGrayed(); // ((IState)menu).getStateContext().getCntr().grayed(this);
				break;
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see name.tfossi.apolge200.command.ICmd#getReceiver()
	 */
	@Override
	public final IReceiver getReceiver() {
		if(LOGGER) logger.debug("Liefere Receiver [" + this.receiver + "]");
		return this.receiver;
	}

	// ---- Parameter
	// ------------------------------------------------------------

	/** Parameterqueue für Befehle mit Daten */
	private Queue<String> parameter = new LinkedList<>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see name.tfossi.apolge200.command.ICmd#clearParameter()
	 */
	@Override
	public final void clearParameter() {
		if(LOGGER) logger.trace("Lösche alle Parameter des Befehls [" + this.toString()
				+ "]");
		this.parameter.clear();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see name.tfossi.apolge200.command.ICmd#setParameter(java.lang.Object)
	 */
	@Override
	public final void setParameter(final String parameterIn) {
		assert parameterIn != null : "Die Parameterqueue ist <null>";
		this.parameter.offer(parameterIn);
		if(LOGGER) logger.trace("Trage Parameter \"" + parameterIn + "\" ein.");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see name.tfossi.apolge.common.cmd.ICmd#getParameter()
	 */
	@Override
	public final Queue<String> getParameter() {
		return this.parameter;
	}
//
//	// public final void setParameter(Queue<Object> parameter) {
//	// this.parameter = parameter;
//	// }
//
//
	// ---- Data
	// ------------------------------------------------------------
	/** data */
	private Object data;

	/* (non-Javadoc)
	 * @see name.tfossi.apolge.common.cmd.ICmd#setData(java.lang.Object)
	 */
	@Override
	public final void setData(Object data){
		this.data = data;
	}
	/* (non-Javadoc)
	 * @see name.tfossi.apolge.common.cmd.ICmd#getData()
	 */
	@Override
	public final Object getData(){
		return this.data;
	}
//
	// ---- SWT
	// ------------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * 
	 * @see name.tfossi.apolge200.common.cmd.ICmd#showParameterCommand()
	 */
	@Override
	public void showParameterCommand(
			SelectionEvent e) {
		// not used
	}

	/** Widgetinstanz des Befehls */
	protected List<Widget> widget = new ArrayList<>();

	/** group */
	protected Group group = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * name.tfossi.apolge.command.ICmd#buildSWTWidget(org.eclipse.swt.widgets
	 * .Group)
	 */
	@Override
	public abstract List<Widget> buildSWTWidget(Group grp);

	@Override
	public final void setGrayed() {
		if (this.widget != null && this.widget.size() > 0) {

			// && this.widget.get(this.getCode()) != null) {
			// ((Button) this.widget.get(this.getCode())).setGrayed(true);
			// ((Button)
			// this.widget.get(this.getCode())).setBackground(Display.getCurrent()
			// .getSystemColor(SWT.COLOR_DARK_GRAY));
			// ((Button)
			// this.widget.get(this.getCode())).setForeground(Display.getCurrent()
			// .getSystemColor(SWT.COLOR_GRAY));
			for (Widget w : this.widget) {

				if (!w.isDisposed()) {
					Button wb = (Button) w;
					wb.setBackground(Display.getCurrent().getSystemColor(
							SWT.COLOR_DARK_GRAY));
					wb.setForeground(Display.getCurrent().getSystemColor(
							SWT.COLOR_GRAY));
				}
			}
		}
		this.grayed = true;
	}

	/* (non-Javadoc)
	 * @see name.tfossi.apolge203rc.common.cmd.ICmd#isUnvisible()
	 */
	@Override
	public final boolean isUnvisible(){
		return this.unvisible;
	}
	/** parameterwidget */
	protected List<Widget> parameterwidget = new ArrayList<>(0);

	@Override
	public void /* List<Widget> */buildParameterWidgets(
			final Group grp) {
		assert false;
		// return null;
	}

	// ---- Selbstverwaltung --------------------------------------------------
	/** serialVersionUID */
	private final static long serialVersionUID = VERSION;
	/** logger */
	private final static Logger logger = Logger.getLogger(ACmd.class
			.getPackage().getName());

	/**
	 * Stellt Methoden für Befehle bereit und definiert die Schnittstelle zur
	 * Ausführung eines konkreten Befehls
	 */
	public ACmd() {
		// leer
	}
	/**
	 * TODO Comment
	 * @param name -
	 * @param unvisibleMode -
	 * @modified -
	 */
	protected ACmd(final String name, final boolean unvisibleMode) {

		this.unvisible = unvisibleMode;
		this.keycode = name;
		if(LOGGER) logger.trace(this.getClass().getSimpleName());
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#clone()
	 */
	@Override
	public ACmd clone() {
		ACmd result = null;
		try {
			result = (ACmd) super.clone();
			result.parameter = new LinkedList<>();
			for (String o : this.parameter)
				result.parameter.add(o);
		} catch (CloneNotSupportedException e) {
			assert false;
		}
		assert result != null;
		if(LOGGER) logger.trace("Clone des Commands \"" + this + "\" liefern.");
		return result;
	}
//
//	/** @return Liefert SimpleName() der Klasse */
//	@Override
//	public String toString() {
//		return this.getClass().getSimpleName();
//	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see name.tfossi.apolge.common.cmd.ICmd#getCode()
	 */
	@Override
	public String getCode() {
		return this.keycode;
	}
}