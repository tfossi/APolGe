/**
 * GameState.java 
 * Branch hci
 * APolGe
 * tfossi-team
 * licence GPLv3  
 */
package tfossi.apolge.common.hci;


/**
 * TODO Comment
 *
 * @author tfossi
 * @version 13.08.2014
 * @modified -
 * @since Java 1.6
 */
public enum GameState implements IGameState {
	/**
	 * TODO Comment
	 *
	 * @author tfossi
	 * @version 13.08.2014
	 * @modified -
	 * @since Java 1.6
	 */
	OFFLINE {
		// Ausgangszustand Game
		// In: 
		// Out: Anmeldung am Game
		/* (non-Javadoc)
		 * @see tfossi.apolge.common.hci.IGameState#chgGameState(tfossi.apolge.common.hci.IGameStateCall, java.lang.Object, java.lang.String[])
		 */
		@Override
		public void chgGameState(IGameStateCall g, Object data, String ... value){
			// Ist laufendes Game (State waiting)
			g.setGs(KNOCKING);
			g.send("gamestate", "game + userid");
			// Ist schlafendes Game (State sleeping)
			g.setGs(WAKEING);
			g.send("gamestate", "game + userid");			
		}
	},
	/**
	 * TODO Comment
	 *
	 * @author tfossi
	 * @version 13.08.2014
	 * @modified -
	 * @since Java 1.6
	 */
	KNOCKING{
		/* (non-Javadoc)
		 * @see tfossi.apolge.common.hci.IGameState#chgGameState(tfossi.apolge.common.hci.IGameStateCall, java.lang.Object, java.lang.String[])
		 */
		@Override
		public void chgGameState(IGameStateCall g, Object data, String ... value){
//			
		}
	},
	/**
	 * TODO Comment
	 *
	 * @author tfossi
	 * @version 13.08.2014
	 * @modified -
	 * @since Java 1.6
	 */
	WAKEING{
		// Game ist aktiviert
		// In: Game aktive
		// Control Game
		/* (non-Javadoc)
		 * @see tfossi.apolge.common.hci.IGameState#chgGameState(tfossi.apolge.common.hci.IGameStateCall, java.lang.Object, java.lang.String[])
		 */
		@Override
		public void chgGameState(IGameStateCall g, Object data, String ... value){
//			
		}		
	},
	/**
	 * TODO Comment
	 *
	 * @author tfossi
	 * @version 13.08.2014
	 * @modified -
	 * @since Java 1.6
	 */
	RUN{
		/* (non-Javadoc)
		 * @see tfossi.apolge.common.hci.IGameState#chgGameState(tfossi.apolge.common.hci.IGameStateCall, java.lang.Object, java.lang.String[])
		 */
		@Override
		public void chgGameState(IGameStateCall g, Object data, String ... value){
//			
		}	
	},
	/**
	 * TODO Comment
	 *
	 * @author tfossi
	 * @version 13.08.2014
	 * @modified -
	 * @since Java 1.6
	 */
	PAUSE{
		/* (non-Javadoc)
		 * @see tfossi.apolge.common.hci.IGameState#chgGameState(tfossi.apolge.common.hci.IGameStateCall, java.lang.Object, java.lang.String[])
		 */
		@Override
		public void chgGameState(IGameStateCall g, Object data, String ... value){
//			
		}	
	},
	/**
	 * TODO Comment
	 *
	 * @author tfossi
	 * @version 13.08.2014
	 * @modified -
	 * @since Java 1.6
	 */
	SAVE{
		/* (non-Javadoc)
		 * @see tfossi.apolge.common.hci.IGameState#chgGameState(tfossi.apolge.common.hci.IGameStateCall, java.lang.Object, java.lang.String[])
		 */
		@Override
		public void chgGameState(IGameStateCall g, Object data, String ... value){
//			
		}	
	},
	/**
	 * TODO Comment
	 *
	 * @author tfossi
	 * @version 13.08.2014
	 * @modified -
	 * @since Java 1.6
	 */
	LOAD{
		/* (non-Javadoc)
		 * @see tfossi.apolge.common.hci.IGameState#chgGameState(tfossi.apolge.common.hci.IGameStateCall, java.lang.Object, java.lang.String[])
		 */
		@Override
		public void chgGameState(IGameStateCall g, Object data, String ... value){
//			
		}	
	}
}
