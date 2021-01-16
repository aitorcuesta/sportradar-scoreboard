package com.sportradar.acuesta.scoreboard.exception;

/**
 * Exception for scoreboard repository
 * @author acuesta
 *
 */
public class ScoreboardRepositoryException extends Exception{
    
    public static final String GAME_NOT_STORED = "The game is not stored in our system";

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public ScoreboardRepositoryException() {
	super();
    }

    public ScoreboardRepositoryException(String message, Throwable cause, boolean enableSuppression,
	    boolean writableStackTrace) {
	super(message, cause, enableSuppression, writableStackTrace);
    }

    public ScoreboardRepositoryException(String message, Throwable cause) {
	super(message, cause);
    }

    public ScoreboardRepositoryException(String message) {
	super(message);
    }

    public ScoreboardRepositoryException(Throwable cause) {
	super(cause);
    }
    
    

}
