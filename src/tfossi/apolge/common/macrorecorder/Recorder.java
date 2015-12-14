/**
 * Recorder.java
 * Branch macrorecorder
 * APolGe
 * tfossi-team
 * licence GPLv3  
 */
package tfossi.apolge.common.macrorecorder;


import static tfossi.apolge.common.constants.ConstValue.INTUNDEF;
import static tfossi.apolge.common.constants.ConstValue.LFCR;
import static tfossi.apolge.common.constants.ConstValue.LOGGER;
import static tfossi.apolge.common.constants.ConstValue.LOGTAB;
import static tfossi.apolge.common.constants.ConstValue.MACRO_PATH;
import static tfossi.apolge.common.constants.ConstValue.NTAB;
import static tfossi.apolge.common.constants.ConstValue.OFF;
import static tfossi.apolge.common.constants.ConstValue.ON;
import static tfossi.apolge.common.constants.ConstValue.TAB;
import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.apache.log4j.Logger;

import tfossi.apolge.common.cmd.ACmd;
import tfossi.apolge.common.cmd.CommandArray;
import tfossi.apolge.common.cmd.CommandList;
import tfossi.apolge.common.cmd.CommandMaps;
import tfossi.apolge.common.cmd.ICmd;
import tfossi.apolge.common.hci.menu.IGeneralReceiver;
import tfossi.apolge.io.console.Key;

/**
 * Klasse zum Aufzeichnen und Abspielen von Befehlen (Makros). Zwingend
 * erforderlich ist die Anwendung des Command-Patterns, damit das Funktioniert.<br>
 * Die Records werden in der Klasse {@linkplain Key} abbgespielt, wenn ein Makro
 * aktiviert ist!
 * 
 * @see Macro
 * @see ACmd
 * see AReceiver
 * see CGRecorder
 * @author tfossi
 * @version 11.08.2014
 * @modified -
 * @since Java 1.6
 */
class Recorder implements IRecorder {
	
	
	
	/**
	 * Schalter zum Abspielen von Macros ON/OFF<br>
	 * <code>play==OFF</code> ist default
	 */
	private boolean play = OFF;

	/**
	 * Schalter zum Aufzeichnen von Macros ON/OFF<br>
	 * <code>record==OFF</code> ist default
	 */
	private boolean record = OFF;

	/**
	 * macrolist ist die Map der Makros. Dabei enthält der Key den Namen des
	 * Makros, Content ist die Liste der Commands/Befehle mit deren Parametern
	 */
	private final Map<String, Macro> macroList = new HashMap<String, Macro>();

	/** Gefilterte macroList */
	private Map<String, Macro> macroListFilter;

	/** Aktueller Macroname */
	private String actMacroName = null;

	/** Macroname des aufnehmenden Records */
	private String recMacroName = null;

	/** Kommentar des aufnehmenden Records */
	private String recMacroComment = null;

	/** Receiver, dem das Makro zugeordnet ist und die Aufnahme startet */
	private Class<? extends IGeneralReceiver> recOwner;

	/**
	 * Aufnahmerecord. Hier werden die Commands abgelegt, die gerade
	 * aufgezeichnet werden
	 */
	private CommandList recCommands = null;

	/**
	 * Pointer zum Abspielen eines Makros. Zeigt die Position des aktuellen bzw.
	 * nächsten Befehls an.<br>
	 * <code>value -1 </code> signalisiert, das die Makros noch geladen werden
	 * müssen.
	 */
	private int recordPointer = INTUNDEF;

	// ---- Macrosteuerung ----------------------------------------------------

	/* (non-Javadoc)
	 * @see tfossi.apolge.common.macrorecorder.IRecorde#loadRecord(java.lang.String)
	 */
	@Override
	public final boolean loadRecord(final String macroname) {
		if(LOGGER) logger.trace("Lade Record: " + macroname);

		if (this.macroList.containsKey(macroname)) {
			this.actMacroName = macroname;
			this.recordPointer = 0;
		} else {
			assert false; //	ErrApp.MACRONOTFOUND.erraufruf("Name: \"" + macroname
//					+ "\"");
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see tfossi.apolge.common.macrorecorder.IRecorde#delRecord(java.lang.String)
	 */
	@Override
	public final void delRecord(final String macroname) {
		if (this.macroList.containsKey(macroname)) {
			this.macroList.remove(macroname);
			this.macroListFilter.remove(macroname);
			this.save();
			if(LOGGER) logger.info("Makro " + macroname + " wurde gelöscht!");
		} else {
			assert false; //	ErrApp.MACRONOTFOUND.erraufruf("Name: \"" + macroname
//					+ "\"");
		}
	}

	/* (non-Javadoc)
	 * @see tfossi.apolge.common.macrorecorder.IRecorde#setPlayON()
	 */
	@Override
	public final void setPlayON() {
		this.play = ON;
		if(LOGGER) logger.trace("Record wird abgespielt!");
	}

	/* (non-Javadoc)
	 * @see tfossi.apolge.common.macrorecorder.IRecorde#isPlay()
	 */
	@Override
	public final boolean isPlay() {
		return this.play;
	}

	/* (non-Javadoc)
	 * @see tfossi.apolge.common.macrorecorder.IRecorde#getPlay()
	 */
	@Override
	public final ICmd getPlay() {
	

		if (!this.play) {
			if(LOGGER) logger.trace("Exit: DataPlayer ist OFF");
			return null;
		}

		if (this.actMacroName == null) {
			if(LOGGER) logger.trace("Exit: Kein aktueller Makroname eingestellt (actMacroName==null)");
			return null;
		}
		if (this.macroList == null) {
			if(LOGGER) logger.trace("Exit: Makroliste ist leer (macroList==null)");
			return null;
		}

		if (this.macroList.get(this.actMacroName) == null) {
			if(LOGGER) logger.trace("Exit: Makro existiert nicht in Liste (macroList.get("
					+ this.actMacroName + ") == null)");
			return null;
		}

		if (this.macroList.get(this.actMacroName).getDcrlist() == null) {
			if(LOGGER) logger.trace("Exit: Kommandoliste des Makros ist leer (macroList.get(actMacroName).getDcrList() == null)");
			return null;
		}

		if (this.recordPointer >= this.macroList.get(this.actMacroName)
				.getDcrlist().size()) {
			if(LOGGER) logger.trace("Exit: Makro ist zu Ende" + NTAB + this.recordPointer
					+ NTAB
					+ this.macroList.get(this.actMacroName).getDcrlist().size());
			this.actMacroName = null;
			this.play = OFF;
			return null;
		}
		ICmd rc = this.macroList.get(this.actMacroName).getDcrlist()
				.get(this.recordPointer++);
		if (this.recordPointer >= this.macroList.get(this.actMacroName)
				.getDcrlist().size()) {
			if(LOGGER) logger.trace("Player aus.");
			this.play = OFF;
		}
		if(LOGGER) logger.debug("[" + rc.getCode() + "]" + NTAB + "Makro: "
				+ this.actMacroName + NTAB + "Makrolänge: "
				+ this.macroList.get(this.actMacroName).getDcrlist().size()
				+ NTAB + "Zeigerposition: " + this.recordPointer + NTAB);
		return rc;
	}

	/* (non-Javadoc)
	 * @see tfossi.apolge.common.macrorecorder.IRecorde#pushRecRecorder(java.lang.String, java.lang.String, java.lang.Class)
	 */
	@Override
	public final void pushRecRecorder(final String macroname,
			final String comment,
			final Class<? extends IGeneralReceiver> ownerIn) {
		if(LOGGER) logger.debug("Starte Macrorecording für" + NTAB + "Klasse: " + ownerIn
				+ NTAB + "Macro  : " + macroname + NTAB + "Comment: " + comment);
		this.record = ON;
		this.recMacroName = macroname;
		this.recMacroComment = comment;
		this.recCommands = new CommandArray();
		this.recOwner = ownerIn;
	}

	/* (non-Javadoc)
	 * @see tfossi.apolge.common.macrorecorder.IRecorder#isRecord()
	 */
	@Override
	public final boolean isRecord() {
		return this.record;
	}
//
//	/**
//	 * Nimmt einen einzelnen Befehl auf
//	 * 
//	 * @param ac
//	 *            ist der zu speichernde Befehl
//	 */
	/* (non-Javadoc)
	 * @see tfossi.apolge.common.macrorecorder.IRecorde#setRecord(tfossi.apolge.common.cmd.ICmd)
	 */
	@Override
	public final void setRecord(final ICmd ac) {
		if(LOGGER) logger.trace("Aufnahme vom Befehl [" + ac.getCode()
				+ "] mit Parameter " + NTAB + ac.getParameter());
		this.recCommands.add(ac.clone());
	}
//
//	/**
//	 * @return liefert einen formatierten String mit Daten des aktuellen Makros
//	 */
	/* (non-Javadoc)
	 * @see tfossi.apolge.common.macrorecorder.IRecorde#chkMakro(java.lang.String)
	 */
	@Override
	public final String chkMakro(final String macroname) {
		if (this.macroList.containsKey(macroname)) {
			Macro m = this.macroList.get(macroname);
		
		String str = "Aktuelles Makro:" + LFCR;
		str += "Name     : " +macroname + LFCR;
		str += "Kommentar: " + m.getComment() + LFCR;
		str += "Owner    : " + m.getOwner() + LFCR;
		if (m.getDcrlist() != null) {
			str += "# of Cmds: " + m.getDcrlist().size() + LFCR;
			for (ICmd cmd : m.getDcrlist()) {
				str += TAB + "Command  : " + cmd.getCode() + LFCR;
				//str += TAB + "Receiver : " + (cmd.getReceiver()==null?"- offen -":cmd.getReceiver().toString()) + LFCR;
				str += TAB + "# of Para: " + cmd.getParameter().size() + LFCR;
				for (String p : cmd.getParameter()) {
					str += TAB + "         : " + p;
				}
			}
		}
		return str;
		}
		assert false; //		ErrApp.MACRONOTFOUND.erraufruf("Name: \"" + macroname
//					+ "\"");
			return "";
	
	}

	/**
	 * Ergänzt die Makroliste um das neue Makro und speichert alles ab.
	 * 
	 * @.pre recMacroName enthält den Macronamen
	 * @.pre recOwner enthält die initiierende Klasse (Receiver)
	 * @.pre recCommands enthält ein oder mehrer AbstractCmds
	 * @.post recMacroName, recOwnerclass und recCommands = null;
	 * @.post macroList hat eine Position mehr
	 */
	private final void writeMacro() {
		if(LOGGER) logger.trace("Neues Makro abspeichern:" + this.recMacroName);
		Macro macro = new Macro(this.recMacroComment, this.recOwner,
				this.recCommands);
		this.macroList.put(this.recMacroName, macro);
		this.save();
		this.recMacroName = null;
		this.recMacroComment = null;
		this.recOwner = null;
		this.recCommands = null;
		this.record = OFF;
		this.recordPointer = 0;
	}

	/* (non-Javadoc)
	 * @see tfossi.apolge.common.macrorecorder.IRecorde#stop()
	 */
	@Override
	public final void stop() {
		if(LOGGER) logger.trace("Aufnahme stoppen:" + this.recMacroName);
		if (this.record)
			this.writeMacro();
		if (this.record)
			this.record = OFF;
		else
			// Ist gerade ausgeschaltet, da sonst DataPlayer und Recorder
			// gleichzeitig nicht
			// bis zum Schluss betrieben werden können
			this.play = OFF;
	}

	/* (non-Javadoc)
	 * @see tfossi.apolge.common.macrorecorder.IRecorde#contentln(java.lang.String, java.lang.String, boolean)
	 */
	@Override
	public final String[] contentln(final String receiver,
			final String pattern, boolean details) {
		if(LOGGER) logger.trace("Anfrage:"+LOGTAB+"Receiver: "+receiver+
				LOGTAB+"Pattern: "+pattern+LOGTAB+"Detail: "+details);
		String go = "";

		String godetail = "";
		// String nogo = "";
		// List<String> rc = new ArrayList<String>();
		// Die Liste der Elemente, die herausgefiltert werden sollen
		List<String> negativ = new ArrayList<String>();
		Set<String> keySet = this.macroListFilter.keySet();
		if(LOGGER) logger.trace("gefilterte Liste der Macros"+keySet);
		for (String key : keySet) {
			if(LOGGER) logger.trace("Untersuche: "+key);
			List<MatchResult> results = new ArrayList<MatchResult>();
			try {
				for (Matcher m = Pattern.compile(pattern).matcher(key); m
						.find();){
					results.add(m.toMatchResult());

					if(LOGGER) logger.trace("Neuer Filterwert "+key+": "+m.toMatchResult());
					
				}
				String owner =  this.macroListFilter.get(key).getOwner().getName();
				for (Matcher m = Pattern.compile(pattern).matcher(owner); m
						.find();){
					results.add(m.toMatchResult());

					if(LOGGER) logger.trace("Neuer Filterwert "+key+": "+m.toMatchResult());
					
				}
		
				
				
			} catch (PatternSyntaxException e) {
				return new String[] { "Fehlerhafte Patterneingabe: ", pattern };
			}
			
			if(LOGGER) logger.trace("Ergebnis: "+results);
			if (results.isEmpty()){		
				negativ.add(key);
				if(LOGGER) logger.trace("Negativ: "+key+LOGTAB+negativ);
			}
		}

		if(LOGGER) logger.trace("Liste der Macros, die nicht mehr gefiltert werden:"+LOGTAB+negativ);
		for (String key : negativ) {
			this.macroListFilter.remove(key);
		}
		
		
		if(LOGGER) logger.trace("Liste der Macros, die gefiltert werden:"+LOGTAB+this.macroListFilter.keySet());
		
		for (String key : this.macroListFilter.keySet()) {
			Macro m = this.macroListFilter.get(key);
			if (receiver == null
					|| receiver.equals(m.getOwner().getSimpleName())) {
				// rc.add(key + ": " + m.getComment() + " [" +
				// m.getOwner().getSimpleName() + "]");
				go += ";" + key + ": " + m.getComment() + " ["
						+ m.getOwner().getSimpleName() + "]";
				if (details){
					for (ICmd ac : m.getDcrlist()) {
						godetail += ";:" + TAB + ac.getCode();
						for (Object str : ac.getParameter()) {
							godetail += TAB + TAB + str;
						}
					}
					
					go += godetail;
					
				}
			} else if (!receiver.equals(m.getOwner().getSimpleName())) {
				// nogo += ";" + key + ": " + m.getComment() + " [" +
				// m.getOwner().getSimpleName()
				// + "]";
			}
		}
		if (go.length() > 0)
			go = go.substring(1);
		// if(nogo.length()>0)
		// nogo = nogo.substring(1);
		if(LOGGER) logger.trace("Details"+NTAB+Arrays.asList(go.split(";")));
		return // new String[][]{
		go.split(";");
		// nogo.split(";")
		// };

		// rc.addAll(Arrays.asList(s.split(";")));
		// rc.addAll(Arrays.asList(no_s.split(";")));
		// return rc.toArray(new String[0]);
	}

	/* (non-Javadoc)
	 * @see tfossi.apolge.common.macrorecorder.IRecorde#contentReset()
	 */
	@Override
	public final void contentReset() {
		this.macroListFilter = new HashMap<String,Macro>(this.macroList);		
	}

	/* (non-Javadoc)
	 * @see tfossi.apolge.common.macrorecorder.IRecorde#detail(java.lang.String)
	 */
	@Override
	public final String detail(final String key) {
		String rc=null;
		Macro m = this.macroList.get(key);
		rc = key + ": " + m.getComment() + " [" + m.getOwner().getSimpleName()
				+ "]";

		for (ICmd ac : m.getDcrlist()) {
			rc += LFCR + ac.getCode();
			for (Object str : ac.getParameter()) {
				rc += LFCR + TAB + str;
			}
		}

		return rc;
	}

	// ---- Save and Load -----------------------------------------------------

	/** Speichern der Makros */
	/**
	 * TODO Comment
	 * @modified - 
	 */
	private final void save() {
		String filename = MACRO_PATH + "macrostest.ser.xml";
		if(LOGGER) logger.trace("Makros speichern. File: " + NTAB + filename);
		@SuppressWarnings("resource")
		XMLEncoder enc = null;
		try {
			enc = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(
					filename)));

			Set<String> keys = this.macroList.keySet();
			for (String key : keys) {
				enc.writeObject(key); // Macroname
				Macro macro = this.macroList.get(key); // Macro holen
				enc.writeObject(macro.getComment()); // Macrobeschreibung
				enc.writeObject(macro.getOwner()); // Startende Klasse
				enc.writeObject(new Integer(macro.getDcrlist().size())); // Anzahl
																			// der
				// Befehle
				for (ICmd ac : macro.getDcrlist()) {
					// Liste der Befehle abarbeiten
					enc.writeObject(ac.getClass().getSimpleName()); // Der Command
					Queue<String> q = ac.getParameter();
					enc.writeObject(new Integer(q.size())); // Anzahl der
															// Parameter
					for (Iterator<String> it = q.iterator(); it.hasNext();) {
						String o = it.next();
						enc.writeObject(o); // Parameter eintragen
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (enc != null)
				enc.close();
		}
	}

	/** Laden aller Makros */
	@SuppressWarnings({ "unchecked" })
	private final void load() {
		String filename = MACRO_PATH + "macrostest.ser.xml";
		File f = new File(filename);
		if (!f.exists()) {
			if(LOGGER) logger.trace("File anlegen: " + filename);
			this.save();
		}
		if(LOGGER) logger.trace("Makros einlesen." + NTAB + "File: " + filename);
		@SuppressWarnings("resource")
		XMLDecoder dec = null;
		try {
			dec = new XMLDecoder(new BufferedInputStream(new FileInputStream(
					filename)));
			while (true) {
				if(LOGGER) logger.trace("Load-Schleife");
				String code;
				String comment;
				Class<IGeneralReceiver> receiver;
				int size;
				CommandList list;
				try {
					Object tmpCode = dec.readObject();
					Object tmpComment = dec.readObject();
					Object tmpReceiver = dec.readObject();
					Object tmpSize = dec.readObject();
					if(LOGGER) logger.trace("readObject:" + NTAB + "Read code   : "
							+ tmpCode + NTAB + "Read comment: " + tmpComment
							+ NTAB + "Read class  : " + tmpReceiver + NTAB
							+ "Read entries: " + tmpSize);
					code = (String) tmpCode;
					comment = (String) tmpComment;
					receiver = (Class<IGeneralReceiver>) tmpReceiver;
					size = Integer.parseInt(tmpSize.toString());
					list = new CommandArray();
					for (int i = 0; i < size; i++) {
						String command = (String) dec.readObject();
						if(LOGGER) logger.trace("Identifiziere Command #" + i + ": ["
								+ command + "]");
						ICmd cmd = new CommandMaps().fetchCmd(command);

						int qlen = Integer
								.parseInt(dec.readObject().toString());
						if(LOGGER) logger.trace("Anzahl der Parameter des Befehls ["
								+ cmd.getClass().getSimpleName() + "]: " + qlen);

						for (int qi = 0; qi < qlen; qi++) {
							Object parameter = dec.readObject();
							if(LOGGER) logger.trace("Parameter: " + parameter);
							cmd.setParameter(parameter.toString());
						}
						list.add(cmd);
					}
					if(LOGGER) logger.debug("Makro " + code);
					this.macroList
							.put(code, new Macro(comment, receiver, list));
				} catch (ArrayIndexOutOfBoundsException e) {
					// Wird erreicht, wenn keine Daten mehr im File sind.
					// oder File ist Panne!
					break;
				}
			}

		} catch (FileNotFoundException e) {
			if(LOGGER) logger.debug("Lege einen neuen Makrofile an!" + NTAB + filename);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (dec != null)
				dec.close();
		}
		this.macroListFilter = new HashMap<String,Macro>(this.macroList);
	}

	// ---- Selbstverwaltung --------------------------------------------------
	/** serialVersionUID */
	@SuppressWarnings("unused")
	private final static long serialVersionUID = VERSION;
	/** logger */
	private final static Logger logger = Logger.getLogger(Recorder.class
			.getPackage().getName());

	/** itsInstance */
	protected static final IRecorder itsInstance = new Recorder();

	/**
	 * Instanzieren (Singleton) des Makro-Rekorders und dabei alle Makros
	 * vorladen
	 */
	private Recorder() {
		try {
			if(LOGGER) logger.trace("Lade alle Makros");
			this.load();

		} catch (Exception e) {
			assert false; //		ErrApp.IOERROR.erraufruf("");
		}
	}


	/**
	 * TODO Comment
	 * @return @return Instanz des Recorders 
	 * @modified - 
	 */
	public static final synchronized IRecorder getInstance() {
		return itsInstance;
	}

	/* (non-Javadoc)
	 * @see tfossi.apolge.common.macrorecorder.IRecorde#toString()
	 */
	@Override
	public final String toString() {
		return this.getClass().getSimpleName();

	}
}
