package poem;

import cards.CardColor;
import cards.MemoryCard;

/**
 * Cards in a Poem are a Pair of a card and a status.
 * @author TKler
 *
 * Heartcards (the cards at 0) can be burned or relieved
 * all other cards can be used or relieved (turned back to open)
 */

public class CardInPoem 
{
	MemoryCard _card;
	CardStatusInPoem _status;
	
	public CardInPoem(MemoryCard card, CardStatusInPoem status) 
	{
		_card = card;
		_status = status;
	}
	
	public MemoryCard getCard() 
	{
		return _card;
	}
	
	public CardColor getColor()
	{
		return _card.getColor();
	}
	
	public void use() 
	{
		_status = CardStatusInPoem.USED;
	}

	public void burn() 
	{
		_status = CardStatusInPoem.BURNED;
	}

	public void relieve() 
	{
		_status = CardStatusInPoem.USABLE;
	}
	
	public CardStatusInPoem getStatus()
	{
		return _status;
	} 
}