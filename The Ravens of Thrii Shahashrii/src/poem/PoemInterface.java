package poem;

import cards.MemoryCard;

public interface PoemInterface 
{

	/**
	 * True if the card could be added
	 * false if this is not possible and the card needs to be discarded
	 **/
	boolean addToCardPoem(MemoryCard card);

	// questionable, as use card should be sufficient
	MemoryCard getCardAt(int xposition, int yposition);
//TODO decide this
	// could be with params: MemorxCard card
	void useCardInPoem(int xposition, int yposition);

	void revealHeartCard(int line);

}