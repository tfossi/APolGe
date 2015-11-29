/**
 * AMenuReceiver.java
 * Branch hci
 * APolGe
 * tfossi-team
 * licence GPLv3  
 */
package tfossi.apolge.common.hci.menu;


import static tfossi.apolge.common.constants.ConstValue.LOGGER;
import static tfossi.apolge.common.constants.ConstValue.NTAB;
import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;

import org.apache.log4j.Logger;

import tfossi.apolge.common.hci.AMenu;
import tfossi.apolge.common.hci.IState;
import tfossi.apolge.common.hci.IStateContext;
import tfossi.apolge.common.macrorecorder.IRecorder;
import tfossi.apolge.io.Content;
import tfossi.apolge.io.IContent;
import tfossi.apolge.io.Screen;

/**
 * Alle Methoden zur Befehlssteuerung
 *
 * @author tfossi
 * @version 14.08.2014
 * @modified -
 * @since Java 1.6
 */
public abstract class AMenuReceiver extends AMenuMVC implements IGeneralReceiver, IReceiver{

	
	// ---- Befehle -----------------------------------------------------------

	/* (non-Javadoc)
	 * @see tfossi.apolge.common.hci.menu.IGeneralReceiver#exit()
	 */
	@Override
	public final void exit() {
		
			// Loggen, das Programm beendet wird
			if(LOGGER) logger.info("Programmende");

			// Voherige States löschen
			this.getStateContext().setClearState();
		// Menu verlassen (Statewechsel, Konsole)
		this.setExitMenu();
		// Menu verlassen (Statewechsel, SWT)
		this.getView().setExitSWTView();
		// Voherigen State einstellen
		this.getStateContext().setPrevState();
	}
	/**
	 * TODO Comment
	 * @modified - 
	 */
	public final void help() {
//		FIXME new Help().doIt(this.getParameter(), ((IMenu) this.getReceiver()).getView().getSWT());

	}

	/* (non-Javadoc)
	 * @see tfossi.apolge.common.hci.menu.IGeneralReceiver#credit()
	 */
	@Override
	public final void credit() {

		if(LOGGER) logger.trace("Menuwechsel");

		// Den Credit-State einstellen
		IState creditMenu = null; //new CreditMenu((AMenu)this, this.getStateContext());
		// Loggen, das zum nächstem Menu gesprungen wird
		if(LOGGER) logger.trace("Weiter" + NTAB + "Eintrag \"fromState\" ist: \"" + this + "\"" + NTAB
				+ "Nächster State (Menu) \"" + creditMenu + "\" wird aufgerufen.");
		// Menu verlassen (Statewechsel, Konsole)
		this.setExitMenu();
		// Menu verlassen (Statewechsel, SWT)
		this.getView().setExitSWTView();

		// Neuen State CreditMenu einstellen
		this.getStateContext().setActualState(creditMenu);

		this.nextView = null;
		if(LOGGER) logger.info("MVC::\tVerlasse View [" + this.getView().getClass().getSimpleName() + "]!");
		// this.getView().setExitSWTView();

		if(LOGGER) logger.trace(NTAB + NTAB + "Exit " + NTAB + NTAB);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see name.tfossi.apolge.command.IReceiver#show(java.lang.String)
	 */
	// /**
	// * Umschalten auf Subscreen 'which'
	// *
	// * @param which
	// * ist der Subscreen mit dem Namen 'which'
	// */
	// public void showGui(final String which) {
	// this.getModel().switchCContainer(which);
	// }

	/* (non-Javadoc)
	 * @see tfossi.apolge.common.hci.menu.IGeneralReceiver#back()
	 */
	@Override
	public final void back() {
		if (this.fromState() == null)
			return;
		
			// Loggen, das zum vorherigen Menu gesprungen wird
			if(LOGGER) logger.info("Zurück" + NTAB + "Eintrag \"fromState\" ist: \""
					+ this.fromState() + "\"" + NTAB
					+ "Aufrufender State (vorheriges Menu) wird aufgerufen.");

		// Menu verlassen (Statewechsel, Konsole)
		this.setExitMenu();
		// Menu verlassen (Statewechsel, SWT)
		this.getView().setExitSWTView();
		// Voherigen State einstellen
		this.getStateContext().setPrevState();
	}

	/**
	 * TODO Comment
	 * @modified - 
	 */
	public abstract void first();

	/**
	 * TODO Comment
	 * @modified - 
	 */
	@SuppressWarnings("static-method")
	public final void showCommands(){
		assert false:"Ausgabe einer Kurzerklärung der Befehle";
//		this.setInformation(session, scr, content, notify, delete);
	}
//	 ---- Recorder
	// -------------------------------------------------------------

	/*
	 * Eigentliche Recorderfunktion zum Abspielen eines Macros, siehe dazu
	 * link #playRecorder(String)}
	 * 
	 * @.pre Klasse ist definiert
	 * @param macroname
	 *            ist der Name des abzuspielenden Makros
	 * @param clazz
	 *            ist die startende Klasse des Makros
	 */
	/* (non-Javadoc)
	 * @see tfossi.apolge.common.hci.menu.IGeneralReceiver#playRecorder(java.lang.String[])
	 */
	@Override
	public final void playRecorder(final String ... value) {
		String macroname = value[0];
		// Die Klassengebundenen Makroausführung
		if(LOGGER) logger.trace("Enter playRecorder(" + macroname + ")");
		if (IRecorder.recorder.loadRecord(macroname))
			IRecorder.recorder.setPlayON();
		this.getView().setExitSWTView();
		if(LOGGER) logger.trace("Exit playRecorder(" + macroname + ")");
	}

	/**
	 * Eigentliche Recorderfunktion zum Aufnehmen eines Macros, siehe dazu
	 * {@link IRecorder#pushRecRecorder}
	 * 
	 * @param macroname
	 *            ist der Name des aufzunehmenden Makros
	 * @param comment
	 *            ist Kommentar und Kurzbeschreibung zum Makro
	 */
	@Override
	public final void recRecorder(final String macroname, final String comment) {
		IRecorder.recorder.pushRecRecorder(macroname, comment, this.getClass());
	}

	/**
	 * TODO Comment
	 * @param macroname -
	 * @modified - 
	 */
	public final static void delRecorder(final String macroname) {
		IRecorder.recorder.delRecord(macroname);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see name.tfossi.apolge.command.IReceiver#stopRecorder()
	 */
	@Override
	public final void stopRecorder() {
		IRecorder.recorder.stop();
	}

	
	/** contentRecorder */
	private IContent contentRecorder = new Content();
//	{
//		/**
//		 * 
//		 */
//		private static final long serialVersionUID = 1L;
//		@Override
//		public String[] getString(){
//			List<String> rc1 = new ArrayList<String>();
//			rc1.add("Receiver:" + AMenuReceiver.this.getModel().getReceiverFilter());
//			rc1.add("Pattern :" + AMenuReceiver.this.getModel().getPatternFilter());
//			rc1.add("details :" + AMenuReceiver.this.getModel().isDetailsFilter());
//			rc1.add("reset   :" + AMenuReceiver.this.getModel().isResetFilter());
//			rc1.add("---------");
////			if (AMenuReceiver.this.getModel().isResetFilter())
////				IRecorder.recorder.contentReset();
//			// null: alle Makros, this meine Makros
//			// String[go][nogo]	
//			String[] a = IRecorder.recorder.contentln(
//					AMenuReceiver.this.getModel().getReceiverFilter()
//					, AMenuReceiver.this.getModel().getPatternFilter(),
//					AMenuReceiver.this.getModel().isDetailsFilter());
//			
////			String[][] rc = new String[][]{
////					rc1.toArray(new String[0]),
////					a[0],
////					new String[]{LFCR,"---------","NOGOS:"},
////					a[1]				
////			};
//			return a;
//		}
//		@Override
//		public List<Widget> getWidget(){
//			assert false;
//			return null;
//		}
//	};
	/*
	 * (non-Javadoc)
	 * 
	 * @see name.tfossi.apolge.command.IReceiver#contentRecorder()
	 */
	@Override
	public final void contentRecorder(){
		
		this.setInformation(this.getStateContext().getCntr(), 
				Screen.VI, this.contentRecorder, true, false, false);	
//		
//		
////			final String receiver,
////			final String pattern, final boolean details, final boolean reset) {
//		
//		
////		if(LOGGER) logger.debug("Receiver:" + receiver + NTAB + "Pattern :" + pattern
////				+ NTAB + "details :" + details + NTAB + "reset   :" + reset);
//		
//		List<String> rc1 = new ArrayList<String>();
//		rc1.add("Receiver:" + this.getModel().getReceiverFilter());
//		rc1.add("Pattern :" + this.getModel().getPatternFilter());
//		rc1.add("details :" + this.getModel().isDetailsFilter());
//		rc1.add("reset   :" + this.getModel().isResetFilter());
//		rc1.add("---------");
//		if (this.getModel().isResetFilter())
//			IRecorder.recorder.contentReset();
//		// null: alle Makros, this meine Makros
//		// String[go][nogo]	
//		String[] a = IRecorder.recorder.contentln(this.getModel().getReceiverFilter()
//				, this.getModel().getPatternFilter(),
//				this.getModel().isDetailsFilter());
//		
////		String[][] rc = new String[][]{
////				rc1.toArray(new String[0]),
////				a[0],
////				new String[]{LFCR,"---------","NOGOS:"},
////				a[1]				
////		};
//		if(LOGGER) logger.debug("Liefert Information:"+NTAB+Arrays.asList(a));
//		this.setInformation(this.getStateContext().getCntr(), 
//				Screen.VI, new ContentString(rc1.toArray(new String[0])), true, false);
////		return a; //rc1.toArray(new String[0]);
//		final List list = new List (shell, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL);
//		for (int i=0; i<128; i++) list.add ("Item " + i);
//		Rectangle clientArea = shell.getClientArea ();
//		list.setBounds (clientArea.x, clientArea.y, 100, 100);
//		list.addListener (SWT.Selection, new Listener () {
//			public void handleEvent (Event e) {
//				String string = "";
//				int [] selection = list.getSelectionIndices ();
//				for (int i=0; i<selection.length; i++) string += selection [i] + " ";
//				System.out.println ("Selection={" + string + "}");
//			}
//		});
//		list.addListener (SWT.DefaultSelection, new Listener () {
//			public void handleEvent (Event e) {
//				String string = "";
//				int [] selection = list.getSelectionIndices ();
//				for (int i=0; i<selection.length; i++) string += selection [i] + " ";
//				System.out.println ("DefaultSelection={" + string + "}");
//			}
//		});
	}
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see name.tfossi.apolge.command.IReceiver#detailRecorder(java.lang.String)
	 */
	/**
	 * TODO Comment
	 * @param makro -
	 * @return -
	 * @modified - 
	 */
	public final static String detailRecorder(final String makro) {
		return IRecorder.recorder.detail(makro);
	}
//	 ---- Spieldaten
	// -----------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * 
	 * @see name.tfossi.apolge.command.IReceiver#loadall()
	 */
	/**
	 * TODO Comment
	 * @modified - 
	 */
	public static void loadall() {
//		MetaData.loadDaten();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see name.tfossi.apolge.command.IReceiver#saveall()
	 */
	/**
	 * TODO Comment
	 * @modified - 
	 */
	public static void saveall() {
//		MetaData.getInstance().saveDaten();
	}


//	@Override
//	public Exception statement(String string, Object object)
//			throws InvalidClassException, SocketException, IOException {
//		// TODO Auto-generated method stub
//		return new Exception();
//	}
	
	// ---- Selbstverwaltung
	// -----------------------------------------------------
	/** serialVersionUID */
	@SuppressWarnings("unused")
	private final static long serialVersionUID = VERSION;

	/** logger */
	private final static Logger logger = Logger
			.getLogger(AMenuReceiver.class.getPackage().getName());

	/**
	 * Stellt die Methoden für {@link IState} bereit , IMenu und IReceiver
	 * bereit
	 * 
	 * @.post this.caller = callFromMenu: Das aufrufende Menu/State<br>
	 * @.post this.sc = statecontext: IStateContext<br>
	 * @param fromState
	 *            <ul>
	 *            <li>!=null: Enthält das aufrufende (==instanzierende) Menu.
	 *            Es ist der Aufrufer, der bei {@link #back()} wieder
	 *            angesprungen wird.</li>
	 *            <li>==null: Ist das initiale Menu und das Programm wird beim
	 *            Rücksprung {@link #back()} beendet.</li>
	 *            </ul>
	 * @param statecontext
	 *            Enthält den {@link IStateContext StateContext}, der die
	 *            Statesteuerung übernimmt. Siehe auch
	 *            {@link #getStateContext()}
	 * @param grayedMenu -
	 */
	public AMenuReceiver(final AMenu fromState, final IStateContext statecontext,final String[] grayedMenu) {
		super(fromState, statecontext, grayedMenu);
	}

	/** @return Liefert SimpleName() der Klasse */
	@Override
	public String toString() {
		return this.getClass().getSimpleName();
	}
}
