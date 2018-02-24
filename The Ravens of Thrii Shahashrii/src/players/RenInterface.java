package players;

import java.util.ArrayList;

import cards.MemoryCard;

public interface RenInterface 
{
	/**
	 * call to manage card usage, aka switch the card
	 */
	public void useCard(MemoryCard card, int xposition, int yposition);
	
	
	/**
	 *  takes a card from the Atman and tries to put  it onto the poem, depending on the poem either add or discard
	 **/
	public void addCardToThePoem(MemoryCard card);


	/**
	 * Creates Rens heart with 4 eligible memorycards
	 * @param list the for memorycards, first goes first and so on
	 */
	public void createHeart(ArrayList<MemoryCard> list);


	/**
	 * the dream ends for ren, meaning her scored cards get added to the scorepile and the rest gets discarded
	 * @return the cards to be discarded
	 */
	public ArrayList<MemoryCard> endDream();
	
	
	
}