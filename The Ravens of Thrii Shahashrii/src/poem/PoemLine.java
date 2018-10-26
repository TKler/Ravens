package poem;
import java.util.ArrayList;
import java.util.List;

import cards.MemoryCard;

class PoemLine
{
	List<CardInPoem> _line = new ArrayList<CardInPoem>(7);
	int _limit;
	int _valueSum;
	
	public PoemLine (int limit, MemoryCard card)
	{
		_limit = limit;
		_line.add(new CardInPoem(card, CardStatusInPoem.UNREVEALED));
		_valueSum = card.getValue();
	}
	
	public boolean isComplete() 
	{
		if(_valueSum == _limit)
			return true;
		return false;
	}

	/**
	 * True if the card could be added
	 * false if this is not possible and the card needs to be discarded
	 **/
	public boolean addCardToLine(MemoryCard card) 
	{	
		if(card.getValue() + _valueSum > _limit)
			return false;
		
		_valueSum += card.getValue();
		_line.add(new CardInPoem(card, CardStatusInPoem.USABLE));
		return true;
	}

	public int getValueSum()
	{
		return _valueSum;
	}

	public MemoryCard getCard(int xposition) 
	{
		return _line.get(xposition).getCard();
	}

	public void useCard(int xposition) 
	{
		_line.get(xposition).use();
	}

	public void burnHeartCard() 
	{
		_line.get(0).burn();
	}

	public void revealHeartCard() 
	{
		for(CardInPoem card: _line)
			card.relieve();
	}

	public CardInPoem getHeartCard() 
	{
		return _line.get(0);
	}

	public void removeCard(CardInPoem card) 
	{
		_line.remove(card);		
	}

	/**
	 * strips away the layer of CardInPoem
	 * (for discarding)
	 * @return
	 */
	public List<MemoryCard> getAllCards()
	{
		List<MemoryCard> cards = new ArrayList<MemoryCard>();
		for(CardInPoem cip : _line)
		{
			cards.add(cip.getCard());
		}
		return cards;
	}
}
