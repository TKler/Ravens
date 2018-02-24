package _GUIInterface;

import atman.CardInAtman;
import atman.Corner;
import cards.MemoryCard;

public interface GUIToGameLogicInterface 
{
	/**
	 * Called when Feth draws a Card from the deck
	 */
	public void fethDrawsACard();
	
	/**
	 * This may only be allowed after at least one drawn memorycard
	 */
	public void fethFinishesDrawing();
	
	/**
	 * The card and its place in the Atman
	 * @return if this is a correct place
	 */
	public boolean placeCardInTheAtman(MemoryCard card, Corner upperLeft, Corner upperRight, Corner lowerLeft, Corner lowerRight);
	
	
	/**
	 * doubles as end of Feth turn
	 */
	public void fethDiscardsRemainingMemoryRow();
	
	/**
	 *  doubles as end of Ren turn, since every action from here on is deterministic
	 */
	public void renTakesCardFromAtman(CardInAtman card);
	
	/**
	 * The card that is being used
	 * @assert correct player uses this.
	 */
	public void useCard(MemoryCard card, int x, int y);
}
