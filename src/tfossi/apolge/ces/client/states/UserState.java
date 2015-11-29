/** 
 * UserState.java
 * Branch ces
 * APolGe
 * tfossi-team
 * licence GPLv3 
 */
package tfossi.apolge.ces.client.states;



import static tfossi.apolge.common.constants.ConstValue.LOGGER;
import static tfossi.apolge.common.constants.ConstValue.LOGTAB;

import org.apache.log4j.Logger;

import tfossi.apolge.common.cmd.cmds.ChgUserState;
import tfossi.apolge.common.error.ErrApp;


/**
 * TODO Comment
 *
 * @author tfossi
 * @version 14.08.2014
 * @modified -
 * @since Java 1.6
 */
public enum UserState implements IUserState {
	/**
	 * TODO Comment
	 *
	 * @author tfossi
	 * @version 14.08.2014
	 * @modified -
	 * @since Java 1.6
	 */
	INIT {
		 
		// Initialer Serverstate
		// Server: User is NOT connected
		// Server In: -
		// Server Out: US-Command to send Passport with ServerKey
		@Override
		public void chgUserState(IUserStateCall u, Object daten,
				String... value) {
			if(LOGGER) logger.trace(u.getCaller().getSimpleName() + " State: " + this
					+ LOGTAB + "Daten: " + daten + LOGTAB + "Value: " + value);
			Object[] o = new Object[] { new Integer(this.hashCode()), "NANUQ",
					this.name().toString() };

			if(LOGGER) logger.trace(u.getCaller().getSimpleName() + " INIT --> CHKPASSPORT");
			u.send(ChgUserState.class.getSimpleName(), o);
			u.setUs(CHKPASSPORT);
		}
	},
	/**
	 * TODO Comment
	 *
	 * @author tfossi
	 * @version 14.08.2014
	 * @modified -
	 * @since Java 1.6
	 */
	CHKPASSPORT {
		int error = 0;
		// Initialer Serverstate
		// Server: User is NOT connected
		// Server In: -
		// Server Out: US-Command to send Passport with ServerKey
		@Override
		public void chgUserState(IUserStateCall u, Object daten,
				String... value) {
			if(LOGGER) logger.trace(u.getCaller().getSimpleName() + " State: " + this
					+ LOGTAB + "Daten: " + daten + LOGTAB + "Value: " + value);
		
			Object[] o = u.verifyPassportUs(daten, value);

			if( o.getClass().equals(ErrApp[].class)){
				this.error++;
				u.setUs(INIT, this.error);
				// TODO Rekursion beseitigen!
				u.send(ChgUserState.class.getSimpleName(), o);
				INIT.chgUserState(u, daten, value);
			}else{				
				if(LOGGER) logger.trace(u.getCaller().getSimpleName() + " INIT --> CONNECTED");
				u.send(ChgUserState.class.getSimpleName(), o);
				u.setUs(ONLINE);
			}
		}
	},
	// if(LOGGER) logger.trace(u.getCaller().getSimpleName() + "  State: " + this
	// + LOGTAB + "Daten: " + daten + LOGTAB + "Value: " + value);

	/**
	 * TODO Comment
	 *
	 * @author tfossi
	 * @version 14.08.2014
	 * @modified -
	 * @since Java 1.6
	 */
	CONNECTED {
		// Initialer Clientstate
		// Server: User is connected
		// Server In: -
		// Server Out: US-Command to send Passport
		// Client: Client is connected
		// Client In: US-Command to send Passport with ServerKey
		// Client Out: Send Passport encripted wirth ServerKey
		// Client: Statechange PASSPORT
		@Override
		public void chgUserState(IUserStateCall u, Object daten,
				String... value) {
			Object[] o = u.passportUs(daten, value);
			if(LOGGER) logger.trace(u.getCaller().getSimpleName()
					+ " CONNECTED --> PASSPORT" + LOGTAB + "Daten: " + daten
					+ LOGTAB + "Value: " + value);
			u.send(ChgUserState.class.getSimpleName(), o);
			u.setUs(PASSPORT);
		}
	},
	
	/**
	 * TODO Comment
	 *
	 * @author tfossi
	 * @version 14.08.2014
	 * @modified -
	 * @since Java 1.6
	 */
	PASSPORT {
		int error = 0;

		// Client-Identity checken
		// Server: Warte auf ID
		// Server In: ID
		// Server Out: US-Command togo ONLINE or REPEAT or END
		// Client: Client has send ID
		// Client In: US-Command togo ONLINE or REPEAT or END
		// Client Out: Send Passport
		// Client: Statechange PASSPORT
		// User hat Namen/ Passport gesendet
		// In: Anfrage Passwort
		// Out: Statement Passwort
		@Override
		public void chgUserState(IUserStateCall u, Object daten,
				String... value) {
			if (daten == null && value == null) {
				CONNECTED.chgUserState(u, daten, value);
			}

			Object[] o = u.verifyPassportUs(daten, value);

			if( o.getClass().equals(ErrApp[].class)){
				if(LOGGER) logger.trace(u.getCaller().getSimpleName() + " STATERESET!"
						+ LOGTAB + "PASSWORT --> ERROR(" + (this.error + 1)
						+ ")" + LOGTAB + "Daten: " + o[0] + LOGTAB + "Value: "
						+ value);
					this.error++;
					u.setUs(CONNECTED, this.error);
			} else {				
				if(LOGGER) logger.trace(u.getCaller().getSimpleName() + LOGTAB
						+ (o.length == 1 ? o[0].getClass() : "") + " RESET"
						+ LOGTAB + " PASSWORT --> ONLINE" + LOGTAB + "Daten: "
						+ daten + LOGTAB + "Value: " + value);
				u.setUs(ONLINE);
				u.openServer(daten, value);
			}
		}
	},
	/**
	 * TODO Comment
	 *
	 * @author tfossi
	 * @version 14.08.2014
	 * @modified -
	 * @since Java 1.6
	 */
	ONLINE {
		// User hat Passwort gesendet
		// In: Statement OK / Nicht OK
		// Out: State RUN
		@Override
		public void chgUserState(IUserStateCall u, Object daten,
				String... value) {
			if(LOGGER) logger.trace(u.getCaller().getSimpleName() + " PASSWORT --> ONLINE"
					+ LOGTAB + "Daten: " + daten + LOGTAB + "Value: " + value);
		}
	};

	/**
	 * TODO Comment
	 * @modified - 
	 */
	public void wasbesonderes() {
		//
	}

	/** logger */
	final static Logger logger = Logger.getLogger(UserState.class.getPackage()
			.getName());
}
