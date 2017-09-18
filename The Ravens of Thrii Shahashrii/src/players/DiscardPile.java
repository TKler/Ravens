package players;

import java.util.ArrayList;

import cards.Card;

/**
 * The discard pile right now has no real functionality besides basic list operations
 * @author TKler
 *
 */

public class DiscardPile 
{
	ArrayList<Card> _list = new ArrayList<Card>();
	
	public void addAll(ArrayList<? extends Card> remaining) 
	{
		_list.addAll(remaining);
	}
	
	public void add(Card card)
	{
		_list.add(card);
	}

	public ArrayList<Card> endDream()
	{
		return _list;
	}
	
	public void yellowLowAbility(ArrayList<Card> cards)
	{
		_list.removeAll(cards);
	}
	
	public ArrayList<Card> getDiscardedCards()
	{
		return _list;
	}
}
