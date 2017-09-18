package atman;

import cards.MemoryCard;

public interface AtmanInterface 
{

	/**
	 *  An Atman has a Startingcard.
	 *  Cards are added into it, which need to adhere to a set of rules.
	 *  Afterwards a Card is removed
	 *  through each action a split atman can occour which means there basicly exist 2 or more atmans now of which only one remains.
	 *  
	 **/
	
	public boolean placeCard(MemoryCard card, Corner upperLeft, Corner upperRight, Corner lowerLeft, Corner lowerRight);
	
	public void removeCard(CardInAtman card);
	
	public boolean isEmpty();
}
