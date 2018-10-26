package players;

import java.util.ArrayList;
import java.util.List;

import cards.Card;

/**
 * The discard pile right now has no real functionality besides 
 * wrapping basic list operations 
 * @author TKler
 *
 */

public class DiscardPile 
{
	List<Card> _list = new ArrayList<Card>();
	
	public void addAll(List<? extends Card> remaining) 
	{
		_list.addAll(remaining);
	}
	
	public void add(Card card)
	{
		_list.add(card);
	}

	public List<Card> endDream()
	{
		return _list;
	}
	
	public void yellowLowAbility(List<Card> cards)
	{
		_list.removeAll(cards);
	}
	
	public List<Card> getDiscardedCards()
	{
		return _list;
	}
}
