/** 
 * GameState.java
 * Branch ces
 * APolGe
 * tfossi-team
 * licence GPLv3 
 */
package tfossi.apolge.ces.client.states;


import static tfossi.apolge.common.constants.ConstValue.LOGGER;
import static tfossi.apolge.common.constants.ConstValue.LOGTAB;

import java.util.Arrays;

import org.apache.log4j.Logger;

import tfossi.apolge.common.cmd.cmds.ChgGameState;

/**
 * TODO Comment
 *
 * @author tfossi
 * @version 14.08.2014
 * @modified -
 * @since Java 1.6
 */
public enum GameState implements IGameState {

	/**
	 * TODO Comment
	 *
	 * @author tfossi
	 * @version 14.08.2014
	 * @modified -
	 * @since Java 1.6
	 */
	REQUEST {
		// Anforderung des Clients an ein Game
		// Server: WAITING
		// Client: ANSWER
		@Override
		public void chgGameState(final IGameStateCall g, final Object daten,
				final String... value) {

			if(LOGGER) logger.trace(g.getCaller().getSimpleName() + " State: " + this
					+ LOGTAB + "Daten: " + daten + LOGTAB + "Value: "
					+ Arrays.toString(value));
			if(LOGGER) logger.trace(g.getCaller().getSimpleName() + " REQUEST --> ANSWER"
					+ LOGTAB + Arrays.toString(value));
			g.setGs(ANSWER);
			g.send(ChgGameState.class.getSimpleName(),
					new String[] { value[0] });
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
	ANSWER {
		// Anwort des Servers an den Beitrittswunsch
		int error = 0;

		// Initialer Serverstate
		// Server: User is NOT connected
		// Server OK In : GAMERONLINE
		// Server ERROR In: WAITING
		// Server Out: US-Command to send Passport with ServerKey
		// Client In: ANSWER
		// Client Out: ONLINE
		@Override
		public void chgGameState(final IGameStateCall g, final Object daten,
				final String... value) {

			if(LOGGER) logger.trace(g.getCaller().getSimpleName() + " State: " + this
					+ LOGTAB + "Daten: " + daten + LOGTAB + "Value: " + value);

			Object[] o = g.verifyAnswerGs(daten, value);

//			if (o.getClass().equals(ErrApp[].class)) {
//				this.error++;
//				g.setGs(REQUEST, this.error);
//				// FIXME Rekursion beseitigen!
//				// Der Aufruf kann theoretisch x-Mal erfolgen, dabei wird
//				// jedesmal eine Instanz erzeugt
//				g.send(ChgUserState.class.getSimpleName(), o);
//				REQUEST.chgGameState(g, daten, value);
//			} else {
				if(LOGGER) logger.trace(g.getCaller().getSimpleName()
						+ " REQUEST --> ANSWER");
				g.send(ChgGameState.class.getSimpleName(), o);
				g.setGs(ONLINE);
//			}
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
		// Mit einem Game verbunden
		@Override
		public void chgGameState(final IGameStateCall g, final Object daten,
				final String... value) {
			//
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
	START {
		// Game starten
		@Override
		public void chgGameState(final IGameStateCall g, final Object daten,
				final String... value) {
			//
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
	WAITING {
		// Warte auf Filename zur Anmeldung.
		// Server In: WAITING
		// Server Out: GAMERONLINE
		// Client: ANSWER
		@Override
		public void chgGameState(final IGameStateCall g, final Object daten,
				final String... value) {
			if(LOGGER) logger.trace("Client meldet sich:" + LOGTAB + "Daten: "
					+ Arrays.toString((Object[]) daten) + LOGTAB + "Value: "
					+ Arrays.toString(value));
			g.loadGs(null, daten, value);
			g.setGs(GAMERONLINE);
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
	GAMERONLINE {
		// Game startet;
		@Override
		public void chgGameState(final IGameStateCall g, final Object daten,
				final String... value) {
			g.setGs(GAMERONLINE);
		}
	};

	/** logger */
	final static Logger logger = Logger.getLogger(GameState.class.getPackage()
			.getName());

	// OFFLINE {
	// // Ausgangszustand Game
	// // In:
	// // Out: Anmeldung am Game
	//
	// public void chgGameState(IGameStateCall g, Object data, String ...
	// value){
	// // Ist laufendes Game (State waiting)
	// g.setGs(KNOCKING);
	// g.send("gamestate", "game + userid");
	// // Ist schlafendes Game (State sleeping)
	// g.setGs(WAKEING);
	// g.send("gamestate", "game + userid");
	// }
	// },
	// KNOCKING{
	//
	// public void chgGameState(IGameStateCall g, Object data, String ...
	// value){
	// //
	// }
	// },
	// WAKEING{
	// // Game ist aktiviert
	// // In: Game aktive
	// // Control Game
	//
	// public void chgGameState(IGameStateCall g, Object data, String ...
	// value){
	// //
	// }
	// },
	// RUN{
	//
	// public void chgGameState(IGameStateCall g, Object data, String ...
	// value){
	// //
	// }
	// },
	// PAUSE{
	//
	// public void chgGameState(IGameStateCall g, Object data, String ...
	// value){
	// //
	// }
	// },
	// SAVE{
	//
	// public void chgGameState(IGameStateCall g, Object data, String ...
	// value){
	// //
	// }
	// },
	// LOAD{
	//
	// public void chgGameState(IGameStateCall g, Object data, String ...
	// value){
	// //
	// }
	// }
	// ;

}
