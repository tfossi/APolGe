/**
 * TTS.java
 * TODO branch
 * APolGe
 * tfossi-team
 * licence GPLv3   
 */
package tfossi.apolge.data.guide;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

/**
 * Basiskeywords in sgd_*.apo-files
 * <ul>
 * <li>STATE</li>
 * <li>CONTEXT</li>
 * <li>TRANSITION</li>
 * </ul>
 * 
 * @author tfossi
 * @version 10.01.2015
 * @modified -
 * @since Java 1.6
 */
public enum SGD_Cntrl {

	/** STATE */
	STATE("STATE", EnumSet.allOf(STATEIDLE.class), EnumSet
			.allOf(STATEATTRIBUTES.class)) {
		@Override
		public String color() {
			return "silver";
		}
	},
	/** CONTEXT */
	CONTEXT("CONTEXT", null, null) {
		@Override
		public String color() {
			return "silver";
		}
	},
	/** TRANSITION */
	TRANSITION("TRANSITION", null, null) {
		@Override
		public String color() {
			return "silver";
		}
	};

	/** code */
	private final String code;
	/** idle */
	@SuppressWarnings("unused")
	private final EnumSet<?> idle;
	/** attributes */
	private final EnumSet<?> attributes;

	/**
	 * TODO Comment
	 * @return ????
	 * @modified - 
	 */
	public final String getCode() {
		return this.code;
	}

	/**
	 * TODO Comment
	 * @return ???
	 * @modified - 
	 */
	public final EnumSet<?> getIdle() {
		return this.attributes;
	}

	/**
	 * TODO Comment
	 * @return ???
	 * @modified - 
	 */
	public final EnumSet<?> getAttributes() {
		return this.attributes;
	}

	/**
	 * TODO Comment
	 * @return ???
	 * @modified - 
	 */
	public abstract String color();

	/**
	 * TODO Comment
	 * @param code ???
	 * @param i ???
	 * @param e ???
	 * @modified -
	 */
	SGD_Cntrl(String code, EnumSet<?> i, EnumSet<?> e) {
		this.code = code;
		this.idle = i;
		this.attributes = e;
	}

	/**
	 * @return Alle Schlüsselwörter für die Parameter der sgd_*.apo-Files
	 * @modified -
	 */
	public final static List<String> getAllCode() {
		List<String> y = new ArrayList<String>();
		for (SGD_Cntrl x : SGD_Cntrl.values()) {
			y.add(x.getCode());
		}
		return y;
	}

	/** Liste der Basis-Keywords im State 
	 * @return ????*/
	@SuppressWarnings("static-method")
	public final List<SGD_Cntrl> sKeywords() {
		return Arrays.asList(SGD_Cntrl.values());
	}

	/**
	 * TODO Comment
	 *
	 * @author tfossi
	 * @version 26.01.2015
	 * @modified -
	 * @since Java 1.6
	 */
	public enum CONTEXTIDLE {
		/** TERMIN */
		TERMIN, /** STATE */
		@SuppressWarnings("hiding")
		STATE, /** DOSTATE */
		DOSTATE, /*IDLE,*/ /** TRANS */
		TRANS, /** GD */
		GD;
		/**
		 * @return Alle Schlüsselwörter für die Parameter der sgd_*.apo-Files
		 * @modified -
		 */
		public final static List<String> getAllCode() {
			List<String> y = new ArrayList<String>();
			for (SGD_Cntrl.CONTEXTIDLE x : SGD_Cntrl.CONTEXTIDLE.values()) {
				y.add(x.name());
			}
			return y;
		}
	}

	/**
	 * TODO Comment
	 *
	 * @author tfossi
	 * @version 26.01.2015
	 * @modified -
	 * @since Java 1.6
	 */
	public enum STATEIDLE {
		/** IDLE */
		IDLE, /** STATION */
		STATION, /** TERMIN */
		TERMIN, /** STATE */
		@SuppressWarnings("hiding")
		STATE, /** DOSTATE */
		DOSTATE, /** AS */
		AS, /** PS */
		PS, /** AR */
		AR, /** PR */
		PR;
		/**
		 * @return Alle Schlüsselwörter für die Parameter der sgd_*.apo-Files
		 * @modified -
		 */
		public final static List<String> getAllCode() {
			List<String> y = new ArrayList<String>();
			for (SGD_Cntrl.STATEIDLE x : SGD_Cntrl.STATEIDLE.values()) {
				y.add(x.name());
			}
			return y;
		}
	}

	
	/**
	 * TODO Comment
	 *
	 * @author tfossi
	 * @version 26.01.2015
	 * @modified -
	 * @since Java 1.6
	 */
	public enum DIGITALATTRIBUTES {
		/** Beschreibung */
		ACTIVITY,
		/** Indexnummer */
		IDX,
		/** (Lang-)Name */
		NAME,
		/** Übergeordnetes Element */
		PARENT,
		/** Priorität des Elements */
		PRIORITY, /** SOLLWERTSCRIPT */
		SOLLWERTSCRIPT, /** REGLER */
		REGLER, /** STRECKE */
		STRECKE, /** SOLLWERTGEBER */
		SOLLWERTGEBER, /** TRIGGERLISTE */
		TRIGGERLISTE, /** VALUES */
		VALUES, /** SOLLA */
		SOLLA, /** KP */
		KP, /** K */
		K, /** K0 */
		K0, /** Y0 */
		Y0,
		/** Tastverhältnis */
		TV;
		// -- SollA = Zahl
		// -- SollA = {Const, kp=Zahl
		// -- SollA = {Delta, kp=Zahl, K=Zahl}
		// -- SollA = {Ramp, kp=Zahl, K=Zahl}
		// -- SollA = {Sinus, kp=5, K=25}
		// -- SollA = {Euler, K=25, Y0=-5, kp=5}
		// -- SollA = {Delta; kp=3; K=10; TV=10 }
		// -- kp: Verstärkung (5,-5)
		// -- K: Größe des Abtastwertespeicher, Frequenz
		// -- K0: Verschiebung der Funktion (x/t-Achse)
		// -- Y0: Verschiebung der Funktion (Nullachse, y-Achse)
		// -- TV: Tastverhältnis
		/**
		 * @return Alle Schlüsselwörter für die Parameter der sgd_*.apo-Files
		 * @modified -
		 */
		public final static List<String> getAllCode() {
			List<String> y = new ArrayList<String>();
			for (DIGITALATTRIBUTES x : DIGITALATTRIBUTES.values()) {
				y.add(x.name());
			}
			return y;
		}
	}

	/**
	 * TODO Comment
	 *
	 * @author tfossi
	 * @version 26.01.2015
	 * @modified -
	 * @since Java 1.6
	 */
	public enum STATEATTRIBUTES {
		/** ACTIVITY */
		ACTIVITY, /** GD */
		GD, /** IDX */
		IDX, /** INITIAL */
		INITIAL, /** MINYVALUE */
		MINYVALUE, /** MAXYVALUE */
		MAXYVALUE, /** NAME */
		NAME, /** PARENT */
		PARENT, /** PRIORITY */
		PRIORITY, /** SOLLWERTSCRIPT */
		SOLLWERTSCRIPT, /** REGLER */
		REGLER, /** STRECKE */
		STRECKE, /** SOLLWERTGEBER */
		SOLLWERTGEBER, /** TRIGGERLISTE */
		TRIGGERLISTE, /** VALUES */
		VALUES;
		/**
		 * @return Alle Schlüsselwörter für die Parameter der sgd_*.apo-Files
		 * @modified -
		 */
		public final static List<String> getAllCode() {
			List<String> y = new ArrayList<String>();
			for (STATEATTRIBUTES x : STATEATTRIBUTES.values()) {
				y.add(x.name());
			}
			return y;
		}
	}

	/**
	 * TODO Comment
	 *
	 * @author tfossi
	 * @version 26.01.2015
	 * @modified -
	 * @since Java 1.6
	 */
	public enum STATEGROUPATTRIBUTES {
		/** Kurze Beschreibung */
		ACTIVITY("ACTIVITY"), 
		/** GD */
		GD("GD"),
		/** GEBER */
		GEBER("GEBER"), 
		/** Aktive Sollwertscript-Daten */ 
		SOLLA("SOLLA"), 
		/** Passive Sollwertscript-Daten */
		SOLLP("SOLLP"),
		/** Indexnummer des Elements */
		IDX("IDX"),
		/** Initialer Startwert */
		INITIAL("INITIAL"),
		/** Key- und Kurzname */
		KEY("KEY"),
		/** Maximaler Y-Wert */
		MAXYVALUE("MAXYVALUE"),
		/** Minimaler Y-Wert */
		MINYVALUE("MINYVALUE"),
		/** (Lang-)name */
		NAME("NAME"),
		/** Übergeordnetes Element */
		PARENT("PARENT"), 
		/** PRIORITY */
		PRIORITY("PRIORITY"),
		/** Aktive Strecke */
		AS("AS"),
		/** Passiver Strecke */
		PS("PS"),
		/** Aktiver Regler */
		AR("AR"),
		/** Passiver Regler */
		PR("PR"), 
		/** TRIGGER */
		TRIGGER("TRIGGER"),
		/** Datentyp CONST, RADIO, DIGIT */
		TYPE("TYPE"), 
		/** VALUES */
		VALUES("VALUES"),
		/** Listeneintrag für kontextbezogenem Wert */
		ZERO("\\?0");

		/** code */
		private final String code;

		/**
		 * TODO Comment
		 * @return ???
		 * @modified - 
		 */
		public final String getCode() {
			return this.code;
		}

		/**
		 * TODO Comment
		 * @param code ???
		 * @modified -
		 */
		STATEGROUPATTRIBUTES(String code) {
			this.code = code;
		}

		/**
		 * @return Alle Schlüsselwörter für die Parameter der sgd_*.apo-Files
		 * @modified -
		 */
		public final static List<String> getAllCode() {
			List<String> y = new ArrayList<String>();
			for (STATEGROUPATTRIBUTES x : STATEGROUPATTRIBUTES.values()) {
				y.add(x.code);
			}
			return y;
		}
	}

	/**
	 * TODO Comment
	 *
	 * @author tfossi
	 * @version 26.01.2015
	 * @modified -
	 * @since Java 1.6
	 */
	public enum STATETYPES {
		/** CONST */
		CONST, /** RADIO */
		RADIO, /** DIGIT */
		DIGIT;
		/**
		 * @return Alle Schlüsselwörter für die Parameter der sgd_*.apo-Files
		 * @modified -
		 */
		public final static List<String> getAllCode() {
			List<String> y = new ArrayList<String>();
			for (STATETYPES x : STATETYPES.values()) {
				y.add(x.name());
			}
			return y;
		}
	}
}
