package com.sportradar.acuesta.scoreboard.exception;

/**
 * Exception for games
 * @author acuesta
 *
 */
public class GameException extends Exception {
    
    public static final String HOME_TEAM_NAME_MANDATORY = "Home team must be a valid name";
    public static final String AWAY_TEAM_NAME_MANDATORY = "Away team must be a valid name";
    public static final String GAME_CANNOT_BE_NULL = "Game cannot be null";

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public GameException() {
	super();
    }

    public GameException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
	super(message, cause, enableSuppression, writableStackTrace);
    }

    public GameException(String message, Throwable cause) {
	super(message, cause);
    }

    public GameException(String message) {
	super(message);
    }

    public GameException(Throwable cause) {
	super(cause);
    }
    
    

}
