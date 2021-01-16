package com.sportradar.acuesta.scoreboard.comparator;

import java.util.Comparator;

import com.sportradar.acuesta.scoreboard.entity.Game;

/**
 * Abstract class for chaining comparations. Implements CoR pattern
 * 
 * @author acuesta
 *
 */
public abstract class ChainedComparator implements Comparator<Game> {

    /**
     * Next element in the chain of comparation. Is only used as tie breaker
     */
    private ChainedComparator next;

    /**
     * Returns the next comparator in the chain
     * 
     * @return The comparator
     */
    public ChainedComparator getNext() {
	return next;
    }

    /**
     * Sets the next comparator in the chain
     * 
     * @param next The comparator
     */
    public void setNext(ChainedComparator next) {
	this.next = next;
    }

    @Override
    public int compare(Game g1, Game g2) {
	int comparation = compareMatches(g1, g2);
	return 0 == comparation && null != next ? next.compare(g1, g2) : comparation;
    }

    /**
     * Abstract method for single comparation.
     * 
     * @param g1 First game to be comparated
     * @param g2 Second game to be comparated
     * @return Returns a negative integer,zero, or a positive integer as the first
     *         argument is less than, equalto, or greater than the second
     */
    protected abstract int compareMatches(Game g1, Game g2);

}
