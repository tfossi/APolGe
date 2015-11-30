/** 
 * MetaData.java
 * Branch data
 * APolGe
 * tfossi-team
 * licence GPLv3 
 */
package tfossi.apolge.data;

import static tfossi.apolge.common.constants.ConstValue.LOGGER;
import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;

import org.apache.log4j.Logger;

/**
 * Variable Werte für die gesamte Applikation
 * 
 * see CDC 
 * TODO Comment
 *
 * @author tfossi
 * @version 17.08.2014
 * @modified -
 * @since Java 1.6
 */
public class MetaData {
	{
		if (LOGGER)
			System.out.println(this.getClass().getSimpleName()+" V" + serialVersionUID);
	}
//	private final static CDC dsc = new CDC(); 
////	static {
////		System.out.println((System.currentTimeMillis()-ConstValue.applicationstarttime)+" Lade Konstanten...");
////	}
////	
//	// /* Script */
//	// private LoadScript script;
//	//
//	// --- Eigennamen ----------------------------------------------------------
//
//	/**
//	 * @.hist 0.00.128 DataNamesSet Initiales Anlegen von DataNamesSet
//	 * @.reviewedby tfossi 20.03.2011 Warnings abarbeiten REV 0.0.149
//	 * @.reviewedby tfossi 20.03.2011 Task-Meldungen überarbeiten REV 0.0.148
//	 * @.reviewedby tfossi 20.03.2011 Fehlerbehandlung nacharbeiten REV 0.0.147
//	 * @.reviewedby tfossi 19.07.2010 REV150
//	 */
//	public enum DataNamesSet {
//		/** NICE Comment */
//		Man(null, "Liste der Männernamen:" + LFCR),
//		/** NICE Comment */
//		Woman(null, "Liste der Frauennamen:" + LFCR),
//		/** NICE Comment */
//		Clan(null, "Liste der Clannamen:" + LFCR);
//		// /** NICE Comment */
//		// Nation(null, "Liste der Nationennamen:" + LFCR),
//		// /** NICE Comment */
//		// Town(null, "Liste der Städtenamen:" + LFCR),
//		// /** NICE Comment */
//		// Nature(null, "Liste der Landschaftsnamen:" +
//		// LFCR);
//		// // /** NICE Comment */
//		// // Game(Arrays.asList(new String[]{"Holla",
//		// "Die Waldfee"}),
//		// // "Liste der Games:"+ConstValue.LFCR);
//
//		/** Enthält die Liste der Namen */
//		public List<String> name = new ArrayList<String>();
//		/** Enthält die Titelzeile */
//		private final String titel;
//
//		public final String getTitel() {
//			return this.titel;
//		}
//
//		/**
//		 * @param names
//		 * @param titel
//		 */
//		private DataNamesSet(List<String> names, final String titelOfSet) {
//			if (names == null)
//				this.name = new ArrayList<String>(0);
//			else
//				this.name = names;
//
//			this.titel = titelOfSet;
//		}
//
//		//
//		// /** @return Liefert die Ausgabe der Namen für den
//		// Screen */
//		// public String[] output() {
//		// String[] output = new String[] { this.titel };
//		// for (String n : this.name)
//		// output[0] += n + TAB;
//		// output[0] += LFCR + LFCR;
//		// return output;
//		// }
//		//
//		// // /**
//		// // * Liefer die Ausgabe des Spieldatensatzes
//		// // *
//		// // * @param name
//		// // * Name des Spieldatensatzes oder
//		// <code>null</code> für Überblick
//		// // * @return die Ausgabe
//		// // */
//		// // private String[] outputDataName(DataName name)
//		// {
//		// // if(LOGGER) logger.trace("Enter Name des Games: " + name);
//		// // if (name == null) {
//		// // String[] output = new String[] {
//		// "Die vorhandenen Games" + LFCR + LFCR };
//		// // for (String n : this.games.keySet()) {
//		// // if (this.getDataGame(n).isReady())
//		// // output[0] += n + TAB;
//		// // else
//		// // output[0] += n + "*" + TAB;
//		// // }
//		// // output[0] += LFCR + LFCR;
//		// // if(LOGGER) logger.trace("Return " + output[0]);
//		// // return output;
//		// // } else {
//		// // DataGame dg = name; // this.getDataGame(name);
//		// // String[] output = new String[] {
//		// "Daten von \'" + dg.getName() + "\'("
//		// // + dg.getUid() + ")" + LFCR + LFCR };
//		// // output[0] += "       Datensatz gespeichert: "
//		// + "NEIN" + LFCR;
//		// // output[0] += "       Datensatz vollständig: "
//		// + (dg.isReady() ? "JA" : "NEIN")
//		// // + LFCR;
//		// // output[0] += "(d $)        Beschreibung: " +
//		// LFCR + dg.getDescription() +
//		// // LFCR;
//		// // output[0] += "(pl #) Anzahl der Players: "
//		// // + (dg.getPlayers() != null ?
//		// dg.getPlayers().length : 0) + LFCR;
//		// // if (dg != null && dg.getPlayers() != null &&
//		// dg.getPlayers().length > 0)
//		// // for (int anzahlPlayers = 0; anzahlPlayers <
//		// dg.getPlayers().length;
//		// // anzahlPlayers++) {
//		// // output[0] += "(n  " + anzahlPlayers +
//		// " #)           Nation: " + LFCR;
//		// //
//		// // output[0] += "(pl "
//		// // + anzahlPlayers
//		// // + " #)           Player: "
//		// // + (dg.getPlayers()[anzahlPlayers] == null ?
//		// "UNDEF" : dg
//		// // .getPlayers()[anzahlPlayers].getName()) +
//		// LFCR;
//		// // }
//		// //
//		// // // output[0] += "Autor       : "+
//		// dg.getAuthor().getName();
//		// // // output[0] += "Leader      : "+
//		// dg.getLeader().getName();
//		// // output[0] += "(s)             Start am : "
//		// // + ConstMethod.toString(dg.getStartdate()) +
//		// TAB;
//		// // output[0] += " (Aktuell: " +
//		// ConstMethod.toString(dg.getActualdate()) + ")" +
//		// // LFCR;
//		// // output[0] += LFCR;
//		// // if(LOGGER) logger.trace("Return " + output[0]);
//		// // return output;
//		// // }
//		// // // if(LOGGER) logger.trace("Return null");
//		// // // return null;
//		// // }
//
//		/** Lade die Liste der Namen */
//		public void loadName() {
//
//			String filename = SCRIPT_PATH /* homePath */+ "data" + FS
//					+ this.toString().toLowerCase() + SCRIPT_EXTENSION;
//
//			if(LOGGER) logger.info("... Filename: " + filename);
//			LoadScript script=null;
//			try {
//				script = new LoadScript(filename, null).valueParsing();
//			} catch (DateiNotFoundException e) {
//				logger.fatal("Script [" + filename + "] nicht gefunden: "
//						+ e.getMessage());
//				System.exit(-2);
//			} catch (FolderNotFoundException e) {
//				logger.fatal("Ordner [" + filename + "] nicht gefunden: "
//						+ e.getMessage());
//				System.exit(-3);
//			}
//			
//			try {
//				this.name = Arrays.asList(script.getStringArray("name"));
//			} catch (ParseException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				assert false;
//			}
//		}
//		//
//		// /** Speicher die Liste der Namen */
//		// public void saveName() {
//		//
//		// String filename = devPath /* homePath */+
//		// "default" + FS + this.toString() + XXT;
//		// XMLEncoder enc = null;
//		// try {
//		// enc = new XMLEncoder(new BufferedOutputStream(new
//		// FileOutputStream(filename)));
//		// for (String n : this.name) {
//		// enc.writeObject(n);
//		// }
//		// enc.close();
//		// } catch (FileNotFoundException e1) {
//		// if(LOGGER) logger.info("Die Datei \"" + this.toString() +
//		// XXT + "\" existiert  nicht!" + NTAB
//		// + "Bei Bedarf wird eine neue Datei angelegt!");
//		// }
//		// }
//		//
//		// // ---- Selbstverwaltung
//		// --------------------------------------------------
//		// private final static long serialVersionUID =
//		// ConstValue.VERSION;
//		// private final static Logger logger =
//		// Logger.getLogger("name.tfossi.apolge.data");
//	}
//
//	// // ---
//	//
//	// /**
//	// * Trage einen neuen Namen ein
//	// *
//	// * @param dns
//	// * der DataNamesSet-Key
//	// * @param name
//	// * der neue Name
//	// */
//	// public void renameName(String dns, String oldname, String newname) {
//	// if(LOGGER) logger.trace("Enter");
//	// String valueOf = dns.substring(0, 1).toUpperCase()
//	// + dns.toString().substring(1).toLowerCase();
//	// DataNamesSet.valueOf(valueOf).name.remove(oldname);
//	// DataNamesSet.valueOf(valueOf).name.add(newname);
//	// if(LOGGER) logger.trace("Exit");
//	// }
//	//
//	// /**
//	// * Trage einen neuen Namen ein
//	// *
//	// * @param dns
//	// * der DataNamesSet-Key
//	// * @param name
//	// * der neue Name
//	// */
//	// public void addName(String dns, String name) {
//	// if(LOGGER) logger.trace("Enter");
//	// String valueOf = dns.substring(0, 1).toUpperCase()
//	// + dns.toString().substring(1).toLowerCase();
//	// DataNamesSet.valueOf(valueOf).name.add(name);
//	// if(LOGGER) logger.trace("Exit");
//	// }
//	//
//	// /**
//	// * Lösche den Namen
//	// *
//	// * @param dns
//	// * der DataNamesSet-Key
//	// * @param name
//	// * der zu löschende Name
//	// */
//	// public void removeName(String dns, String name) {
//	// if(LOGGER) logger.trace("Enter");
//	// String valueOf = dns.substring(0, 1).toUpperCase()
//	// + dns.toString().substring(1).toLowerCase();
//	// DataNamesSet.valueOf(valueOf).name.remove(name);
//	// if(LOGGER) logger.trace("Exit");
//	// }
//	//
//	// // ---- Metazugriff ----------------------------------------------------------
//	//
//	// // /**
//	// // * Erzeuge Daten
//	// // *
//	// // * @param clazz
//	// // * Name der Klasse, die zu erzeugen ist
//	// // * @param name
//	// // * Keyname des Datensatzes
//	// // * @return das erzeugte Object oder <code>null</code> bei Fehler
//	// // *
//	// // */
//	// // public final long createData(Class<?> clazz, String name) {
//	// // if (clazz.isAssignableFrom(DataGame.class))
//	// // return this.createDataGame(name);
//	// // if (clazz.isAssignableFrom(DataPlayer.class))
//	// // return this.createDataPlayer(name);
//	// // return -1;
//	// // }
//	//
//	// /**
//	// * Liefert Datensatz
//	// *
//	// * @param clazz
//	// * Name der Klasse, deren Datensatz zu liefern ist
//	// * @param name
//	// * Name des Datensatzes
//	// * @return der Datensatz oder <code>null</code> bei Fehler
//	// */
//	// public final Data getData(Class<?> clazz, String name) {
//	// if (clazz.isAssignableFrom(DataGame.class))
//	// return this.getDataGame(name);
//	// if (clazz.isAssignableFrom(DataRole.class))
//	// return this.getDataRole(name);
//	// return null;
//	// }
//	//
//	// /**
//	// * Lade Datensatz
//	// *
//	// * @param clazz
//	// * Name der Klasse, deren Datensätze zu laden sind
//	// */
//	// public final void loadData(@SuppressWarnings("unused") EditorViewGame av) {
//	// this.loadDataGames();
//	// }
//	//
//	// // public final void loadData(EditorViewRole av) {
//	// // this.loadDataRole();
//	// // }
//	//
//	// // /**
//	// // * Speicher Datensatz
//	// // *
//	// // * @param clazz
//	// // * Name der Klasse, deren Datensatz zu speichern ist
//	// // * @param name
//	// // * Dummy
//	// // */
//	// // public final void saveData(EditorViewGame av) {
//	// // this.saveDataGames();
//	// // }
//	//
//	// // public final void saveData(EditorViewRole av) {
//	// // this.saveDataRole();
//	// // }
//	//
//	// // ---- Gamedaten ------------------------------------------------------------
//	//
//	// private Map<Long, DataGame> games = new HashMap<Long, DataGame>();
//	// private long nextGameID = 0;
//	//
//	// /**
//	// * @return
//	// */
//	// public List<DataGame> getDataGameTemplates() {
//	// // TODO Auto-generated method stub
//	// File f1 = new File(scriptPath + ConstValue.FS + "games" + ConstValue.FS);
//	// FilenameFilter only = new OnlyExt("lua"); // ext
//	// String files[] = f1.list(only); // array of files
//	// for (int i = 0; i < files.length; i++) {
//	// System.out.println(files[i]);
//	// }
//	//
//	// return null;
//	// }
//	//
//	// class OnlyExt implements FilenameFilter {
//	// String ext;
//	//
//	// public OnlyExt(String ext) {
//	// this.ext = "." + ext;
//	// }
//	//
//	// public boolean accept(@SuppressWarnings("unused") File dir, String name) {
//	// return name.endsWith(this.ext);
//	// }
//	// }
//	//
//	// public final Object[] callDataGameScript(String filename) {
//	// // System.out.println(ConstValue.Sigma.valueOf("S9973"));
//	//
//	// // Loads Lua script for this race.
//	// this.script = new LoadScript(ConstValue.scriptPath + "gameloader.lua");
//	// Object[] o = this.script
//	// .runScriptFunction("createGame", 1, new Object[] { filename, this });
//	// MetaData.getInstance().script.closeScript();
//	//
//	// return o;
//	// }
//	//
//	// /**
//	// * Erzeugt einen Spieldatensatz mit den minmalen Einträgen <li><i>Shortname</i> ist
//	// * der Kurzname</li> <li><i>UID</i> ist die Identnummer</li><br>
//	// * und trägt das Spiel in der Map "games" ein.
//	// *
//	// * @param game
//	// * Druch das Script erzeugte Game
//	// * @return die UID des erzeugten Game-Datensatzes
//	// */
//	// public final long createDataGame(DataGame game) {
//	// if (game == null) {
//	// if(LOGGER) logger.debug("Neues Game wird nicht erzeugt!");
//	// return -1L;
//	// }
//	// if(LOGGER) logger.debug("Erzeuge neues Game \"" + game.getShortname() + "\"");
//	// game.setUid(this.nextGameID++);
//	// this.games.put(Long.valueOf(game.getUid()), game);
//	// return game.getUid();
//	// }
//	//
//	// /**
//	// * Löscht den Spieldatensatz
//	// *
//	// * @param name
//	// * (Kurz-)Name des Spiels
//	// * @return <code>null</code>, wenn das Game nicht gelöscht wurde, sonst das gelöschte
//	// * Game
//	// */
//	// @SuppressWarnings("boxing")
//	// public final DataGame deleteDataGame(long uid) {
//	// if(LOGGER) logger.debug("Lösche Spiel \"" + this.games.get(uid).getName() + "\"");
//	// return this.games.remove(uid);
//	// }
//	//
//	// //
//	// // /** Moved Spieldatensatz (unbenötigt) */
//	// // // private final void moveDataGame() {
//	// // /** Fügt Spieldatensatz hinzu (unbenötigt) */
//	// // // private final void addDataGame()
//	// /** Ändert den Spieldatensatz? */
//	// public final void changeDataGame(@SuppressWarnings("unused") String oldname,
//	// @SuppressWarnings("unused") String newname) {
//	// assert false;
//	// // if(LOGGER) logger.debug("Lösche Spiel \"" + name + "\"");
//	// // return this.games.remove(name);
//	// }
//	//
//	// /**
//	// * Liefer Spieldatensatz
//	// *
//	// * @param name
//	// * (Kurz-)Name des Spiels
//	// * @return liefert den Datensatz <i>name</i> oder <code>null</code>, wenn das Spiel
//	// * nicht existiert
//	// */
//	// public final DataGame getDataGame(final String shortname) {
//	// for (Long uid : this.games.keySet())
//	// if (this.games.get(uid).getShortname().equals(shortname))
//	// return this.games.get(uid);
//	// return null;
//	// }
//	//
//	// @SuppressWarnings("boxing")
//	// public final DataGame getDataGame(final long uid) {
//	// return this.games.get(uid);
//	// }
//	//
//	// /** @return Keys von allen Games */
//	// public final Set<Long> getDataGame() {
//	// return this.games.keySet();
//	// }
//	//
//	// // ----Playerdaten -----------------------------------------------------------
//	//
//	// private Map<Long, DataRole> roles = new HashMap<Long, DataRole>();
//	// private long nextRoleID = 0;
//	//
//	// /**
//	// * Erzeugt einen Spielerdatensatz mit den minmalen Einträgen<br>
//	// * <i>Avatar-name</i> und <i>UID</i>
//	// *
//	// * @param name
//	// * (Kurz-)Name des Role
//	// * @return den neu erzeugten GameDatensatz
//	// */
//	// public final long createDataRole(String name) {
//	// DataRole role = new DataRole();
//	// role.setUid(this.nextRoleID++);
//	// role.setShortname(name);
//	// role.setName(null);
//	// if(LOGGER) logger.debug("Erzeuge neuen Spieler (" + name + ") #" + role.getUid() + "L");
//	// this.roles.put(Long.valueOf(role.getUid()), role);
//	// // this.editPlayer=name;
//	// return role.getUid();
//	// }
//	//
//	// /**
//	// * Löscht den Spielerdatensatz
//	// *
//	// * @param name
//	// * (Kurz-)Name des Spielers
//	// */
//	// public final DataRole deleteDataRole(Long uid) {
//	// if(LOGGER) logger.debug("Lösche Spieler \"" + this.roles.get(uid).getName() + "\"");
//	// return this.roles.remove(uid);
//	// }
//	//
//	// /** Ändert den Spieldatensatz? */
//	// public final void changeDataRole(@SuppressWarnings("unused") String oldname,
//	// @SuppressWarnings("unused") String newname) {
//	// assert false;
//	// // if(LOGGER) logger.debug("Lösche Spiel \"" + name + "\"");
//	// // return this.games.remove(name);
//	// }
//	//
//	// //
//	// // /** Moved Spieldatensatz (unbenötigt) */
//	// // // private final void moveDataGame() {
//	// // /** Fügt Spieldatensatz hinzu (unbenötigt) */
//	// // // private final void addDataGame()
//	// // /** Ändert den Spieldatensatz (unbenötigt) */
//	// // // private final void changeDataGame()
//	//
//	// /**
//	// * Liefer einen Spielerdatansatz
//	// *
//	// * @param name
//	// * Name des Spielers
//	// * @return liefert den Datensatz <i>name</i>
//	// */
//	// public final DataRole getDataRole(final String shortname) {
//	// for (Long uid : this.roles.keySet())
//	// if (this.roles.get(uid).getShortname().equals(shortname))
//	// return this.roles.get(uid);
//	// return null;
//	// }
//	//
//	// public final DataRole getDataRole(final Long uid) {
//	// return this.roles.get(uid);
//	// }
//	//
//	// /** @return Keys von allen Games */
//	// public final Set<Long> getDataRole() {
//	// return this.roles.keySet();
//	// }
//	//
//	// ---- Datenbackup ----------------------------------------------------------
//
//	/** Die Daten speichern */
//	public void saveDaten() {
//		// this.saveDataGames();
//		// for (DataNamesSet dns : DataNamesSet.values())
//		// this.saveNames(dns.toString());
//		// this.saveDataRole();
//	}
//
//	/** Die Daten laden
//	 * <li>Namen Frauen</li>
//	 * <li>Namen Männer</li>
//	 * <li>Namen Clans</li> */
//	public static void loadDaten() {
//		if(LOGGER) logger.info("Allgemeine Daten laden...");
//		// this.loadDataGames();
//		for (DataNamesSet dns : DataNamesSet.values())
//			MetaData.loadNames(dns.toString());
//
//		// this.loadDataRole();
//	}
//
//	/**
//	 * Lade die Namensdaten in DataNamesSet
//	 * 
//	 * @param dns
//	 *            ist der DataNamesSet oder <code>null</code> für Alle
//	 */
//	public static void loadNames(final String dns) {
//		if(LOGGER) logger.info("... Lade "
//				+ (dns == null ? "Alle" : dns.substring(0, 1).toUpperCase()
//						+ dns.substring(1).toLowerCase()));
//		if (dns == null) {
//			for (DataNamesSet d : DataNamesSet.values()) {
//				d.loadName();
//			}
//		} else
//			DataNamesSet.valueOf(
//					dns.substring(0, 1).toUpperCase() + dns.substring(1).toLowerCase()).loadName();
//	}
//	
//	// /** Die Spieldaten laden */
//	// @SuppressWarnings("unchecked")
//	// public void loadDataGames() {
//	// String filename = devPath /* homePath */+ "save" + FS + "Game" + XXT;
//	// try {
//	// XMLDecoder dec = null;
//	// dec = new XMLDecoder(new BufferedInputStream(new FileInputStream(filename)));
//	// while (true) {
//	// try {
//	// if(LOGGER) logger.debug("Lade Daten....");
//	// this.nextGameID = ((Long) dec.readObject()).longValue();
//	// this.games = (Map<Long, DataGame>) dec.readObject();
//	// } catch (ArrayIndexOutOfBoundsException e) {
//	// // Wird erreicht, wenn keine Daten mehr im File sind.
//	// // oder File ist Panne!
//	// break;
//	// }
//	// }
//	// } catch (FileNotFoundException e1) {
//	// ErrApp.NDEF.erraufruf(logger, "");
//	// }
//	// }
//	//
//	// /** Die Daten laden */
//	// /** Die Spielerdaten laden */
//	// @SuppressWarnings("unchecked")
//	// public void loadDataRole() {
//	// String filename = devPath /* homePath */+ "default" + FS + "Role" + XXT;
//	// try {
//	// XMLDecoder dec = null;
//	// dec = new XMLDecoder(new BufferedInputStream(new FileInputStream(filename)));
//	// while (true) {
//	// try {
//	// if(LOGGER) logger.debug("Lade Daten....");
//	// this.nextRoleID = ((Long) dec.readObject()).longValue();
//	// this.roles = (Map<Long, DataRole>) dec.readObject();
//	// } catch (ArrayIndexOutOfBoundsException e) {
//	// // Wird erreicht, wenn keine Daten mehr im File sind.
//	// // oder File ist Panne!
//	// break;
//	// }
//	// }
//	// } catch (FileNotFoundException e1) {
//	// ErrApp.NDEF.erraufruf(logger, "");
//	// }
//	// }
//	//
//	// /** Die Spieldaten speichern */
//	// public void saveDataGames() {
//	//
//	// // DEV Vorbelegung zum Testen
//	// // for (String key : this.games.keySet()) {
//	// // this.games.get(key).setDescription("Beschreibung des Spiels");
//	// // this.games.get(key).setActualdate(ConstMethod.string2DataTime("08.02.114 08:35"));
//	// // this.games.get(key).setStartdate(ConstMethod.string2DataTime("20.06.2010 10:10:30"));
//	// // this.games.get(key).setNameOfGame("Name des Spiels");
//	// // }
//	// String filename = devPath /* homePath */+ "save" + FS + "Game" + XXT;
//	// XMLEncoder enc = null;
//	// try {
//	// enc = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(filename)));
//	//
//	// if(LOGGER) logger.debug("Speicher Daten \""+filename+"\"");
//	// enc.writeObject(new Long(this.nextGameID));
//	// enc.writeObject(this.games);
//	//
//	//
//	//
//	// } catch (IOException e) {
//	// e.printStackTrace();
//	// } finally {
//	// if (enc != null)
//	// enc.close();
//	// }
//	// }
//	//
//	// /** Die Spielerdaten speichern */
//	// public void saveDataRole() {
//	//
//	// String filename = devPath /* homePath */+ "default" + FS + "Role" + XXT;
//	// XMLEncoder enc = null;
//	// try {
//	// enc = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(filename)));
//	//
//	// if(LOGGER) logger.debug("Speicher Daten....");
//	// enc.writeObject(new Long(this.nextRoleID));
//	// enc.writeObject(this.roles);
//	//
//	// } catch (IOException e) {
//	// e.printStackTrace();
//	// } finally {
//	// if (enc != null)
//	// enc.close();
//	// }
//	// }
//
	// ---- Selbstverwaltung --------------------------------------------------
	/** serialVersionUID */
	private final static long serialVersionUID = VERSION;
	/** logger */
	final static Logger logger = Logger.getLogger(MetaData.class.getPackage().getName());

	/** itsInstance */
	private static final MetaData itsInstance = new MetaData();
//
//	private static DataRoot game = null;
//	
//
//	public static long starttime;
//	
//	public static void main(String[] args) throws DateiNotFoundException, FolderNotFoundException {
//		System.out.println((System.currentTimeMillis()-ConstValue.applicationstarttime)+" Start...");
//		PropertyConfigurator.configure("log4j.properties");
//		if(LOGGER) logger.info("---------- METADATA ----------");
//		if(TIMEEM)System.out.println((System.currentTimeMillis()-ConstValue.applicationstarttime)+" X");
//		MetaData.getInstance();
//		if(TIMEEM)System.out.println((System.currentTimeMillis()-ConstValue.applicationstarttime)+" X1");
//		MetaData.loadDaten();
//		if(TIMEEM)System.out.println((System.currentTimeMillis()-ConstValue.applicationstarttime)+" X2");
//		game = new BuildGame("test", "g1.apo").newGame();
//		if(TIMEEM)System.out.println((System.currentTimeMillis()-ConstValue.applicationstarttime)+" X3");
////		MetaStateGuideline.inventurString(game);
////		MetaData.inventurString(game);
//		
//		int e = 0;
//		for (CoreData nData : dsc.getRegisterCoreData(game)) {			
//			for (CoreData cData : dsc.getRegisterCoreData(nData)) {
//				e += dsc.getRegisterCoreDataCounter(cData);
//			}
//		}
//		System.err.println(e);
//		long dauer = 50000;
//		 
//		game.start();
//		if(LOGGER) logger.info("Start der Simulation");
//		if(LOGGER) logger.info("________________________________________");
//		game.schedularstart();
//		game.interruptafter(dauer);
//		if(LOGGER) logger.info("________________________________________");
//		if(LOGGER) logger.info("Ende der Simulation");
//		
//		
//
////		for (Report r : this.repList) {
////			System.out.println();
////			r.report0();
////			r.auswertung();
////		}
//		if(LOGGER) logger.info("Ende des Tests");
//		System.out.println((System.currentTimeMillis()-ConstValue.applicationstarttime)+" Ende");
//	}
//
//	private MetaData() {
//		// leer
//	}
//
	/** @return Instanz der MetaData */
	public static final synchronized MetaData getInstance() {
		return itsInstance;
	}
//
//	/**
//	 * @return Liefert SimpleName() der Klasse
//	 */
//	@Override
//	public String toString() {
//		return this.getClass().getSimpleName();
//	}
//	public static void inventurString(DataRoot game){
//		// System.out.println(game);
//		// out.println(game.getPersonMetaStateGuideline().toString());
//		// System.exit(0);
//
//		out.println("");
//		out.println("  DATA   ");
//		out.println("---------");
//		out.println("");
////		out.println("Titel         : " + game.getShortname());
//		out.println("Titel         : " + dsc.getNonDigit(game, "The StateGroup Const Test", "Shortname"));//game.getConstant("The StateGroup Const Test", "Shortname"));
////		out.println("Name          : " + game.getName());
//		out.println("Name          : " + dsc.getNonDigit(game, "The StateGroup Const Test", "Name"));
////		out.println("Name          : " + game.getVertex(Data.YK, "The StateGroup Const Test", "Name"));
////		out.println("Author        : " + game.getAuthor());
//		out.println("Author        : " + dsc.getNonDigit(game, "The StateGroup Const Test", "Author"));
////		
////		out.println("Author        : " + game.getVertex(0, 1, 0));
////		out.println("Start         : " + game.getStartdate().getDatetime(DATE | TIME | DOW));
//		out.println("Start         : " + dsc.getNonDigit(game, "The StateGroup Const Test", "Startdate"));
//		out.println("Aktuell       : " + game.getActualdate().getDatetime(DATE | TIME | DOW));
//		out.println("Beschreibung  : " + dsc.getNonDigit(game, "The StateGroup Const Test", "Description"));
//		out.println("Registeratur  : " + dsc.getRegisterCoreDataCounter(game));
//		for (CoreData nData : dsc.getRegisterCoreData(game)) {
//			short nuid = dsc.getUID(nData);
//			out.println("-------------");
////			DataNation nation = dsc.getDataNation(nuid);
//			
//			out.println(nuid + "# Titel         : " + dsc.getNonDigit(nData,"?","Shortname"));
//			out.println(nuid + "# Name          : " + dsc.getNonDigit(nData,"?","Name"));
//			out.println(nuid + "# Author        : " + dsc.getNonDigit(nData,"?","Author"));
//			out.println(nuid + "# Beschreibung  : " + dsc.getNonDigit(nData,"?","Description"));
//			// System.out.println("\t\tClans " +
//			// nData.getFormName());
//			// System.out.println("\t\tClans " +
//			// nData.getGeburtenrate());
//			out.println(nuid + "# Registeratur  : " + dsc.getRegisterCoreDataCounter(nData));
//			for (CoreData cData : dsc.getRegisterCoreData(nData)) {
//				short cuid = dsc.getUID(cData);
//				out.println("-------------");
////				DataClan clan = dsc.getDataClan(cuid);
//				out.println(nuid + "." + cuid + "# Titel         : " + dsc.getNonDigit(cData,"?","Shortname"));
//				out.println(nuid + "." + cuid + "# Name          : " + dsc.getNonDigit(cData,"?","Name"));
//				// // System.out.println("\t\t\t(" + cuid +
//				// ") " + cData.getName() + " - "
//				// // + cData.getShortname());
//				out.println(nuid + "." + cuid + "# Registeratur  : " + dsc.getRegisterCoreDataCounter(cData));
//				for (CoreData pData : dsc.getRegisterCoreData(cData)) {
//					@SuppressWarnings("unused")
//					short puid = dsc.getUID(pData);
//					out.println("-------------");
////					DataPerson person = (DataPerson) pData;
////					out.println(nuid + "." + cuid + "." + puid);
////					out.println(TAB + "Stammdaten");
////					out.println(TAB + TAB + /*
////											 * nuid + "." + cuid + "." + puid +
////											 */"# Vorname       : " + person.getShortname());
////					out.println(TAB + TAB + /*
////											 * nuid + "." + cuid + "." + puid +
////											 */"# Name          : " + person.getName());
////					out.println(TAB + TAB + /*
////											 * nuid + "." + cuid + "." + puid +
////											 */"# Sex           : " + person.getGender());
////					out.println(TAB + TAB + /*
////											 * nuid + "." + cuid + "." + puid +
////											 */"# DoB           : "
////							+ person.getDayOfBirth().getDatetime(DATE));
////					out.println(TAB + TAB + /*
////											 * nuid + "." + cuid + "." + puid +
////											 */"# Partner       : " + person.getPartnerUID());
////					out.println(TAB + TAB + /*
////											 * nuid + "." + cuid + "." + puid +
////											 */"# Mutter        : " + person.getMutterUID());
////					out.println(TAB + TAB + /*
////											 * nuid + "." + cuid + "." + puid +
////											 */"# Vater         : " + person.getVaterUID());
////					out.println(TAB + "Status");
////					for (StateGroup sg : game.getMetaStateGuideline().getAllStateGroupValues(person.getClass())) {
////						out.println(TAB + TAB
////								+ /* nuid + "." + cuid + "." + puid + */"#         Group : "
////								+ sg.stategroupname+"("+sg.stategroupindex+")");
////						out.println(TAB + TAB
////								+ /* nuid + "." + cuid + "." + puid + */"#        Active : "
////								+ person.getActiveState(sg.stategroupindex));
////						
////							for (IGuideline vertex : sg.igl_values()) {
//////								out.println(TAB + TAB + TAB
//////										+ /* nuid + "." + cuid + "." + puid + */"#         State : "
//////										+ vertex.vertexName + " "
//////										+ Arrays.asList(dsc.getVertex(person, Data.YK, vertex.grpnr, vertex.vtxnr)));
////								out.println(TAB + TAB + TAB
////										+ /* nuid + "." + cuid + "." + puid + */"#         State : "
////										+ vertex.getIGL_Index().igl_name);
////							}
////						}
//					
//
//				}
//				out.println("-------------");
//			}
//			out.println("-------------");
//		}
//		
//		for(String str:  game.showSchedular())
//			out.println(str);
//
//		for(String str: game.showNeuerTermin())
//			out.println(str); 
//		out.println("");
//		out.println("");
//
//	}
//
//	

}
