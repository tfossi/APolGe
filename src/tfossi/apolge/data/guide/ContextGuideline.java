/**
 * 
 */
package tfossi.apolge.data.guide;

import static tfossi.apolge.common.constants.ConstValue.LFCR;
import static tfossi.apolge.common.constants.ConstValue.LOGGER;
import static tfossi.apolge.common.constants.ConstValue.LOGTAB;
import static tfossi.apolge.common.constants.ConstValue.TAB;
import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import tfossi.apolge.common.scripting.LoadScript;
import tfossi.apolge.common.scripting.LoadScriptException;
import tfossi.apolge.common.scripting.PreAddress;
import tfossi.apolge.common.scripting.ScriptException;
import tfossi.apolge.common.scripting.p.ParseException;
import tfossi.apolge.common.scripting.vp.VP_Tokenlist;
import tfossi.apolge.common.state.TriggerScript;
import tfossi.apolge.common.state.guard.GuardList;
import tfossi.apolge.common.state.guard._Guard;

/**
 * Regelwerk für Context<br>
 * 
 * @author tfossi
 * @version 25.01.2015
 * @modified -
 * @since Java 1.6
 */
public class ContextGuideline extends GuideData {

	// ---- Context-Transition ------------------------------------------------

	/** Liste der Transitionguards für Contextwechsel */
	final List<GuardList> guard = new ArrayList<GuardList>();

	// ---- State-Transition --------------------------------------------------

	/** Liste der Transitionguards für Statewechsel */
	final List<GuardList>[] transGuard;

	// ---- Selbstverwaltung --------------------------------------------------
	/** serialVersionUID */
	private final static long serialVersionUID = VERSION;

	/** logger */
	private final static Logger logger = Logger.getLogger(GuideData.class
			.getPackage().getName() + "GuideData");

	/**
	 * Anlage Contextguideline für Digitdaten.<br>
	 * 
	 * @param dgl
	 *            aktueller DGL
	 * @param parameter
	 *            Liste der Parameter nach {@link SGD_Cntrl.STATEATTRIBUTES}
	 * @modified -
	 */
	@SuppressWarnings("unchecked")
	public ContextGuideline(final DataGuidelineLevel dgl,
			final VP_Tokenlist[] parameter) {
		// super(dgl, name, idx, activity, superGuideData, false, false, true,
		// false);
		super(dgl, parameter, false, true, false);
		int s = ((StateGroupGuideline) super.parent).listOfStates.size();
		this.transGuard = new ArrayList[s];
		for (int i = 0; i < s; i++)
			this.transGuard[i] = new ArrayList<GuardList>();

		VP_Tokenlist gd = parameter[SGD_Cntrl.STATEGROUPATTRIBUTES.GD.ordinal()];
		analyseGuardlistTable(gd, this.guard);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public final String toString() {
		String transition = LFCR + TAB + TAB + TAB + "Transitions:";
		for (int i = 0; i < this.transGuard.length; i++)
			transition += LFCR + TAB + TAB + TAB + TAB
					+ this.transGuard[i].toString();
		return super.toString() + LFCR + TAB + TAB + TAB + "ContextGuard: "
				+ this.guard.toString() + transition;
		// rc = "Context : "+name+ LFCR;
		// rc += "Aktivity: " + this.activity + LFCR;
		// rc += "IDX     : " + this.idx + LFCR;
		// rc += "Group   : " + this.superGuideData.name;
		// rc += "CTG     : " + ctg.toString();
		// return rc;
	}

	/**
	 * Baue eine Context-Guideline.<br>
	 * 
	 * @param dgl
	 *            aktueller DGL
	 * @param sgg
	 *            aktueller SGG
	 * @param contextKey
	 *            Schlüssel- oder Kurzname des neuen Contexts
	 * @param contextdata
	 *            Die neuen Contextdaten
	 * @return ist erfolgreich angelegt?
	 * @modified -
	 */
	public static boolean create(DataGuidelineLevel dgl,
			StateGroupGuideline sgg, String contextKey,
			final VP_Tokenlist contextdata) {

		if (LOGGER)
			logger.debug(contextKey + " im " + dgl.getDatenklasse().toString());

		if (contextKey.contains("."))
			return false;

		int idx = sgg.listOfContexts.size();

		if (SGD_Cntrl.CONTEXTIDLE.getAllCode().contains(contextKey)) {
			switch (SGD_Cntrl.CONTEXTIDLE.valueOf(contextKey)) {
			case STATE:
				// return setState(sgg, contextKey, contextdata);
			case DOSTATE:
				// return setDoState(sgg, contextKey, contextdata);
			case TERMIN:
				// return setTermin(sgg, contextKey, contextdata);
			case TRANS:
				return false;
			default:
				break;
			}
		}

		// initiale Parameter anlegen und vorbelegen
		VP_Tokenlist[] parameter = GuideData.defaultContextParameter(sgg, idx,
				contextKey);

		// groupdata enthält eine Tabelle { ... }
		if (contextdata != null && contextdata.isTable()) {

			// Komplexe Beschreibung
			for (String contextElementFullName : contextdata.getTable()
					.keySet()) {
				VP_Tokenlist contextElementData = (VP_Tokenlist) contextdata
						.getTable().get(contextElementFullName);
				String contextElementName = contextElementFullName
						.substring(contextElementFullName.lastIndexOf(".") + 1);
				if (contextElementData.isTable()) {
					// Tabelle
					GuideData.parameterzuordnung(parameter, contextElementName,
							contextElementData);
				} else {
					// [{STATE.State.AS.\?0=[P], STATE.State.AS.Kp=[2]}]
					// [Not Overriding]
					GuideData.parameterzuordnung(parameter, contextElementName,
							contextElementData);
				}
			}
		} else if (contextdata != null) {
			// Einzelwert als Parameter kann nur initial sein
			GuideData.parameterzuordnung(parameter, contextKey, contextdata);
		}

		// ... und Context erzeugen
		ContextGuideline build = new ContextGuideline(dgl, parameter);

		// Context registrieren
		sgg.listOfContexts.put(contextKey.toUpperCase(), build);
		return true;
	}

	/**
	 * Anlage des Transitionauslösers für Contexte durch Termine.<br>
	 * 
	 * @param sgg
	 *            akktueller SGG
	 * @param contextAttrName
	 *            Schlüssel- oder Kurzname des ContextAttributes
	 * @param contextAttrData
	 *            ContextAttribut Daten
	 * @return anlage erfolgreich?
	 * @modified -
	 */
	final static boolean setTermin(final StateGroupGuideline sgg,
			final String contextAttrName, final VP_Tokenlist contextAttrData) {
		if (contextAttrData != null && contextAttrData.isTable()) {
			// Komplexe Beschreibung
			for (String contextAttrElementFullName : contextAttrData.getTable()
					.keySet()) {
				String wann = (String) ((VP_Tokenlist) contextAttrData
						.getTable().get(contextAttrElementFullName)).getValue();
				sgg.contextTermine.add(wann);
			}
		} else if (contextAttrData != null) {
			assert false : "Ungeklärt, was bei einfachen Einträgen passieren soll.";
		}
		return true;
	}

	/**
	 * Anlage des Transitionauslösers für Contexte durch Direktaufruf eine
	 * Stateausführung.<br>
	 * 
	 * @param sgg
	 *            akktueller SGG
	 * @param contextAttrName
	 *            Schlüssel- oder Kurzname des ContextAttributes
	 * @param contextAttrData
	 *            ContextAttribut Daten
	 * @return anlage erfolgreich?
	 * @modified -
	 */
	final static boolean setDoState(final StateGroupGuideline sgg,
			final String contextAttrName, final VP_Tokenlist contextAttrData) {

		if (contextAttrData != null && contextAttrData.isTable()) {
			// Komplexe Beschreibung
			for (String contextAttrElementFullName : contextAttrData.getTable()
					.keySet()) {
				PreAddress wer = null;
				try {
					wer = new PreAddress(
							(String) ((VP_Tokenlist) contextAttrData.getTable()
									.get(contextAttrElementFullName))
									.getValue());
				} catch (ParseException e) {
					logger.fatal("Abbruch! DOSTATE-Parse-Fehler." + LFCR
							+ e.getMessage());
					System.exit(0);
				}
				sgg.contextDoStateTrigger.add(wer);
			}
		} else if (contextAttrData != null) {
			assert false : "Ungeklärt, was bei einfachen Einträgen passieren soll.";
		}
		return true;
	}

	/**
	 * Anlage des Transitionauslösers für Contexte durch State.<br>
	 * 
	 * @param sgg
	 *            akktueller SGG
	 * @param contextAttrName
	 *            Schlüssel- oder Kurzname des ContextAttributes
	 * @param contextAttrData
	 *            ContextAttribut Daten
	 * @return anlage erdolgreich?
	 * @modified -
	 */
	final static boolean setState(final StateGroupGuideline sgg,
			final String contextAttrName, final VP_Tokenlist contextAttrData) {

		if (contextAttrData != null && contextAttrData.isTable()) {

			// Komplexe Beschreibung
			for (String contextAttrElementFullName : contextAttrData.getTable()
					.keySet()) {

				TriggerScript ts = new TriggerScript();
				try {
					ts.adr = new PreAddress(
							(String) ((VP_Tokenlist) contextAttrData.getTable()
									.get(contextAttrElementFullName))
									.getValue());
				} catch (ParseException e) {
					logger.fatal("Abbruch! STATE-Parse-Fehler." + LFCR
							+ e.getMessage());
					System.exit(0);
				}
				sgg.contextStateTrigger.add(ts);
			}
		} else if (contextAttrData != null) {
			assert false : "Ungeklärt, was bei einfachen Einträgen passieren soll.";
		}
		return true;
	}

	/**
	 * Erstelle für jeden CONTEXT eine Default-Transition
	 * 
	 * @param sgg
	 *            aktueller SGG
	 * @return Anlage erfolgreich?
	 * @modified -
	 */
	final static boolean buildContextDefaultTransGuide(
			final StateGroupGuideline sgg) {

		// Alle Contexte durchgehen
		for (String contextName : sgg.listOfContexts.keySet()) {
			ContextGuideline contextData = (ContextGuideline) sgg.listOfContexts
					.get(contextName);

			if (LOGGER)
				logger.trace(contextName + LOGTAB
						+ "ContextTransitionGuideline für [" + contextData.key
						+ "] eingestellt." + LFCR + contextData);
			// Eintragen eines Defaultguards
			if (contextData.guard.size() == 0) {
				String defaultGD = "!GD = { !default={ !IndexGT = 1.}}";
				VP_Tokenlist gd = null;
//				try {
					gd = null; //new LoadScript(null, defaultGD).valueParser(PMODEFLAT).scon.get("GD");
//
//				} catch (ScriptException e) {
//
//					logger.fatal("Abbruch wegen Parsefehler!" + LFCR
//							+ e.getMessage());
//					e.printStackTrace();
//					System.exit(-1);
//				} catch (ParseException e) {
//
//					logger.fatal("Abbruch wegen Parsefehler!" + LFCR
//							+ e.getMessage());
//					e.printStackTrace();
//					System.exit(-1);
//				} catch (LoadScriptException e) {
//
//					logger.fatal("Abbruch wegen Parsefehler!" + LFCR
//							+ e.getMessage());
//					e.printStackTrace();
//					System.exit(-1);
//				}

				ContextGuideline.analyseGuardlistTable(gd, contextData.guard);

			} else {
				// Default GD nicht notwendig.
			}
		}
		return true;
	}

	/**
	 * Erzeugt für jeden Guard eine PreAddress-Instanz.<br>
	 * 
	 * FIXME Weitergehende Detailierung erforderlich.
	 * 
	 * @param guardAPO
	 *            APO-Script mit den Guards
	 * @param guard
	 *            Liste, in der die Guards eingetragen werden
	 * @modified -
	 */
	private final static void analyseGuardlistTable(
			final VP_Tokenlist guardAPO, final List<GuardList> guard) {

		// Alle Guards im Script durchgehen
		// GD[0], ..., GD[n]
		for (String index : guardAPO.getTable().keySet()) {
			// Guardattribute extrahieren
			VP_Tokenlist gdAttributes = (VP_Tokenlist) guardAPO.getTable().get(
					index);

			// Guardlisteninformation aus APOTable
			GuardList to = new GuardList();

			// Indexnummer des Guards
			// § Wer zuerst kommt, wird zuerst bewertet
			to.nr = guard.size();

			// § Gleiche, ältere Guardindizes werden überschrieben
			for (GuardList gl : guard) {
				if (gl.nr == to.nr) {
					guard.remove(gl);
					break;
				}
			}

			PreAddress adresse = null;
			// § Die Guards bekommen die Meßwerte grundsätzlich vom IDLE-State
			try {
				adresse = new PreAddress("@..IDLE");
			} catch (ParseException e1) {
				logger.fatal("Fehler in der Addresseingabe: " + LFCR
						+ e1.getMessage());
				System.exit(0);

			}

			for (String guardtype : gdAttributes.getTable().keySet()) {
				VP_Tokenlist gdTypeAttributes = (VP_Tokenlist) gdAttributes
						.getTable().get(guardtype);
				String guardTypeName = guardtype.substring(guardtype
						.lastIndexOf(".") + 1);

				// Ist Adresse
				if (guardTypeName.equals("\\?0"))
					continue;
				try {
					_Guard _g = null;

					@SuppressWarnings("unchecked")
					Class<_Guard> afkt = (Class<_Guard>) Class
							.forName(_Guard.class.getPackage().getName() + "."
									+ guardTypeName + "_");

					if (guardtype.equals("Post") || guardtype.equals("Pre")
							|| guardtype.equals("PreHT")) {
						// // assert vts != null;
						// // Constructor<?> constructor = afkt
						// // .getConstructor(VertexGuideline.class);
						// // igl =
						// vts.searchEntry((LoadScript.getStringValue(
						// // guardtable, key)).trim());
						// // to.guards.add((_Guard)
						// constructor.newInstance(igl));
						// // }else if(key.equals("IndexGH20")){
						// // Constructor<?> constructor =
						// // afkt.getConstructor(Object.class);
						assert false : "NI";
					} else {

						Constructor<?> constructor = afkt.getConstructor(
								PreAddress.class, Object.class);
						if (gdTypeAttributes.isTable()) {
							// if (LoadScript.isTable(guardtable, guardtype)) {
							// // Parameter einlesen
							//
							// int entries =
							// LoadScript.getTableValue(guardtable,
							// guardtype).size();
							// Table t2 = LoadScript.getTableValue(guardtable,
							// guardtype);
							// Double[] oa = new Double[entries];
							//
							// for (int i = 0; i < entries; i++) {
							// oa[i] = new Double(LoadScript.getDoubleValue(
							// t2, "?" + String.valueOf(i)));
							// }
							// _g = (_Guard) constructor.newInstance(adresse,
							// oa);
							//
							assert false;
						} else {
							// Funktion mit einem Parameter instanzieren
							_g = (_Guard) constructor.newInstance(adresse,
									gdTypeAttributes.getValue());
						}
						to.guards.add(_g);
					}
				} catch (ClassNotFoundException e) {
					logger.fatal("Abbruch! Fehler bei der Guarderstellung."
							+ LFCR + guardAPO + LFCR + e.getMessage());
					e.printStackTrace();
					e.getCause();
					System.exit(0);
				} catch (NoSuchMethodException e) {
					logger.fatal("Abbruch! Fehler bei der Guarderstellung."
							+ LFCR + guardAPO + LFCR + e.getMessage());
					e.printStackTrace();
					e.getCause();
					System.exit(0);
				}

				catch (SecurityException e) {
					logger.fatal("Abbruch! Fehler bei der Guarderstellung."
							+ LFCR + guardAPO + LFCR + e.getMessage());
					e.printStackTrace();
					e.getCause();
					System.exit(0);
				} catch (InstantiationException e) {
					logger.fatal("Abbruch! Fehler bei der Guarderstellung."
							+ LFCR + guardAPO + LFCR + e.getMessage());
					e.printStackTrace();
					e.getCause();
					System.exit(0);
				}

				catch (IllegalAccessException e) {
					logger.fatal("Abbruch! Fehler bei der Guarderstellung."
							+ LFCR + guardAPO + LFCR + e.getMessage());
					e.printStackTrace();
					e.getCause();
					System.exit(0);
				} catch (IllegalArgumentException e) {
					logger.fatal("Abbruch! Fehler bei der Guarderstellung."
							+ LFCR + guardAPO + LFCR + e.getMessage());
					e.printStackTrace();
					e.getCause();
					System.exit(0);
				}	catch (InvocationTargetException e) {
					logger.fatal("Abbruch! Fehler bei der Guarderstellung."
							+ LFCR + guardAPO + LFCR + e.getMessage());
					e.printStackTrace();
					e.getCause();
					System.exit(0);
				}
			}
			// Hinzufügen des neuen Guards zur Liste
			guard.add(to);
		}
	}

	/**
	 * Anlage der ContextStatetransition TRANS.<br>
	 * 
	 * @param sgg
	 *            aktueller SGG
	 * @param contextData
	 *            aktuelle Contextdaten
	 * @return Anlage erfolgreich?
	 * @modified -
	 */
	final static boolean buildTransCntrlsGuide(final StateGroupGuideline sgg,
			VP_Tokenlist contextData) {

		// Gehe alle Einträge durch
		for (String contextAttrFullName : contextData.getTable().keySet()) {
			VP_Tokenlist contextAttrData = (VP_Tokenlist) contextData
					.getTable().get(contextAttrFullName);
			String contextAttrName = contextAttrFullName
					.substring(contextAttrFullName.lastIndexOf(".") + 1);

			if (SGD_Cntrl.CONTEXTIDLE.getAllCode().contains(contextAttrName)) {
				switch (SGD_Cntrl.CONTEXTIDLE.valueOf(contextAttrName)) {
				case TRANS:
					break;
				default:
					continue;
				}
			} else {
				continue;
			}

			// Die TRANS-Cntrls einbauen
			for (String contextTransAttrFullNName : contextAttrData.getTable()
					.keySet()) {
				VP_Tokenlist contextTransAttrData = (VP_Tokenlist) contextAttrData
						.getTable().get(contextTransAttrFullNName);
				String contextTransAttrName = contextTransAttrFullNName
						.substring(contextTransAttrFullNName.lastIndexOf(".") + 1);

				if (SGD_Cntrl.CONTEXTIDLE.getAllCode().contains(
						contextTransAttrName)) {
					switch (SGD_Cntrl.CONTEXTIDLE.valueOf(contextTransAttrName)) {
					case STATE:
						setTransitionState(sgg, contextTransAttrData,
								sgg.transitionStateTrigger);
						break;
					case DOSTATE:
						setTransitionDoState(sgg, contextTransAttrData,
								sgg.transitionDoStateTrigger);
						break;
					case TERMIN:
						setTransitionTermin(sgg, contextTransAttrData,
								sgg.transitionTermine);
						break;
					default:
						break;
					}

				}
			}
		}
		return true;
	}

	/**
	 * Anlage der ContextStatetransitionen.<br>
	 * 
	 * @param sgg
	 *            aktueller SGG
	 * @param contextData
	 *            aktuelle Contextdaten
	 * @return Anlage erfolgreich?
	 * @modified -
	 */
	final static boolean buildTransitionGuide(final StateGroupGuideline sgg,
			VP_Tokenlist contextData) {

		// Gehe die eingetragenen Contexte durch
		for (String contextName : sgg.listOfContexts.keySet()) {
			boolean found = false;

			// Gehe die Context-APO-Einträge durch
			for (String contextAPOFullname : contextData.getTable().keySet()) {
				String contextAPOName = contextAPOFullname
						.substring(contextAPOFullname.lastIndexOf(".") + 1);

				// Die Zuordnung zwischen Context und APO-Eintrag herstellen
				if (contextAPOName.equalsIgnoreCase(contextName)) {
					found = true;

					for (String contextStateAttrFullName : ((VP_Tokenlist) contextData
							.getTable().get(contextAPOFullname)).getTable()
							.keySet()) {

						String contextStateAttrName = contextStateAttrFullName
								.substring(contextStateAttrFullName
										.lastIndexOf(".") + 1);

						// Eingetragene States durchgehen
						for (String stateFullName : sgg.listOfStates.keySet()) {

							// Die Zuordnung zwischen State und APO-Eintrag
							// herstellen
							if (contextStateAttrName
									.equalsIgnoreCase(stateFullName)) {

								VP_Tokenlist contextStateTransData = (VP_Tokenlist) ((VP_Tokenlist) contextData
										.getTable().get(contextAPOFullname))
										.getTable().get(
												contextStateAttrFullName);

								GuideData gd = sgg.listOfStates
										.get(stateFullName);

								setContextStateTransitionGuards(
										(ContextGuideline) sgg.listOfContexts
												.get(contextName),
										gd.idx, contextStateTransData);
							}
						}
					}
				}
			}
			if (!found) {
				logger.warn("buildTransitionGuide - Context " + contextName
						+ " not found!");
				// TODO Etwas gezielter auf fehlendes Element reagieren.
			}
		}
		return true;
	}

	/**
	 * Aufbau der ContextTransState Guards
	 * 
	 * @param cg
	 *            aktuelle ContextGuideline
	 * @param sIdx
	 *            aktueller Stateindex
	 * @param contextdata
	 *            aktuelle ContextTransState-Daten
	 * @modified -
	 */
	private final static void setContextStateTransitionGuards(
			final ContextGuideline cg, final int sIdx,
			final VP_Tokenlist contextdata) {

		List<GuardList> guardList = cg.transGuard[sIdx];

		for (String guardFullName : contextdata.getTable().keySet()) {

			VP_Tokenlist guardData = (VP_Tokenlist) contextdata.getTable().get(
					guardFullName);
			String guardName = guardFullName.substring(guardFullName
					.lastIndexOf(".") + 1);

			if (SGD_Cntrl.CONTEXTIDLE.getAllCode().contains(guardName)) {
				switch (SGD_Cntrl.CONTEXTIDLE.valueOf(guardName)) {
				case GD:
					break;
				default:
					continue;
				}
			} else {
				continue;
			}
			analyseGuardlistTable(guardData, guardList);
		}
	}

	/**
	 * Anlage der Transitionauslöser für States durch Termine
	 * 
	 * @param sgg
	 *            aktueller SGG
	 * @param contextTransAttrData
	 *            aktueller Datensatz
	 * @param transitionTermine
	 *            Speicher für die Termine
	 * @return Anlage erfolgreich?
	 * @modified -
	 */
	private final static boolean setTransitionTermin(
			final StateGroupGuideline sgg,
			final VP_Tokenlist contextTransAttrData,
			List<String> transitionTermine) {

		if (contextTransAttrData != null && contextTransAttrData.isTable()) {
			// Komplexe Beschreibung
			for (String contextTransAttrElementName : contextTransAttrData
					.getTable().keySet()) {
				String wann = (String) ((VP_Tokenlist) contextTransAttrData
						.getTable().get(contextTransAttrElementName))
						.getValue();
				transitionTermine.add(wann);
			}
		} else if (contextTransAttrData != null) {
			assert false : "Einzelangabe nicht Konzeptiert";
		}
		return true;
	}

	/**
	 * Anlage der Transitionauslöser für States durch andere DoStates
	 * 
	 * @param sgg
	 *            aktueller SGG
	 * @param contextTransAttrData
	 *            aktueller Datensatz
	 * @param triggerAddress
	 *            Speicher für die Triggerauslöseradessen
	 * @return Anlage erfolgreich?
	 * @modified -
	 */
	private final static boolean setTransitionDoState(
			final StateGroupGuideline sgg,
			final VP_Tokenlist contextTransAttrData,
			List<PreAddress> triggerAddress) {

		if (contextTransAttrData != null && contextTransAttrData.isTable()) {
			// Komplexe Beschreibung
			for (String contextTransAttrElementName : contextTransAttrData
					.getTable().keySet()) {
				PreAddress wer = null;
				try {
					wer = new PreAddress(
							(String) ((VP_Tokenlist) contextTransAttrData
									.getTable()
									.get(contextTransAttrElementName))
									.getValue());
				} catch (ParseException e) {
					logger.fatal("Abbruch! Parse-Fehler." + LFCR
							+ e.getMessage());
					System.exit(0);
				}
				triggerAddress.add(wer);
			}
		} else if (contextTransAttrData != null) {
			assert false : "Einzelangabe nicht Konzeptiert";
		}
		return true;
	}

	/**
	 * Anlage der Transitionauslöser für States durch andere States
	 * 
	 * @param sgg
	 *            aktueller SGG
	 * @param contextTransAttrData
	 *            aktueller Datensatz
	 * @param transitionStateTrigger
	 *            Speicher für die Transaktion
	 * @return Anlage erfolgreich?
	 * @modified -
	 */
	final static boolean setTransitionState(final StateGroupGuideline sgg,
			final VP_Tokenlist contextTransAttrData,
			List<TriggerScript> transitionStateTrigger) {

		if (contextTransAttrData != null && contextTransAttrData.isTable()) {
			// Komplexe Beschreibung
			for (String contextTransAttrElementName : contextTransAttrData
					.getTable().keySet()) {

				TriggerScript ts = new TriggerScript();
				try {
					ts.adr = new PreAddress(
							(String) ((VP_Tokenlist) contextTransAttrData
									.getTable()
									.get(contextTransAttrElementName))
									.getValue());
				} catch (ParseException e) {
					logger.fatal("Abbruch! Parse-Fehler." + LFCR
							+ e.getMessage());
					System.exit(0);
				}
				transitionStateTrigger.add(ts);
			}
		} else if (contextTransAttrData != null) {
			assert false : "Einzelangabe nicht Konzeptiert";
		}
		return true;
	}
}
