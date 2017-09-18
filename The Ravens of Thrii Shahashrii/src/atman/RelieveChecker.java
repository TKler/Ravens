package atman;

import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import cards.CardColor;

public class RelieveChecker
{
	HashSet<CardInAtman> _connectedSameColorCards = new HashSet<CardInAtman>();
	HashSet<CardInAtman> _discoveredSameColorCards = new HashSet<CardInAtman>();
	int _valueSumOfCards = 0;
	CardColor _color;
	
	public boolean isRelieve(CardInAtman placedCard)
	{
		_color = placedCard.getColor();
		_discoveredSameColorCards.add(placedCard);
		
		while(!_discoveredSameColorCards.isEmpty())
		{
			@SuppressWarnings("unchecked")
			HashSet<CardInAtman> thisIteration = (HashSet<CardInAtman>) _discoveredSameColorCards.clone();
			for(CardInAtman toTestCard : thisIteration)
			{
				_discoveredSameColorCards.addAll(findPossibleCardsUpAndDown(toTestCard));
				_connectedSameColorCards.add(toTestCard);
				_valueSumOfCards += toTestCard.getCard().getValue();
				if(_valueSumOfCards > 7)
					return false;
				_discoveredSameColorCards.remove(toTestCard);
			}
		}
		return _valueSumOfCards == 7;
	}
	
	private HashSet<CardInAtman> findPossibleCardsUpAndDown(CardInAtman toTestCard)
	{
		//srsly?
		return (HashSet<CardInAtman>) Stream.concat(findPossibleCards(toTestCard, false).stream(), findPossibleCards(toTestCard, true).stream()).collect(Collectors.toSet());
	}

	/**
	 * Find cards below or above, based on up, that share the same color
	 * @param toBeCheckedCard
	 * @param up
	 */
	private HashSet<CardInAtman> findPossibleCards(CardInAtman toBeCheckedCard, boolean up)		
	{
		HashSet<CardInAtman> newCards = new HashSet<CardInAtman>();
		for(Corner c : toBeCheckedCard.getCorners())
		{
			if(c!= null)
			{
				while(getAboveOrBelow(c, up) != null)
				{
					c = getAboveOrBelow(c, up);
					if(c.getParentCard().getColor() == _color && c.getParentCard().isVisible() && !_connectedSameColorCards.contains(c.getParentCard()) && !_discoveredSameColorCards.contains(c.getParentCard()))
					{
						newCards.add(c.getParentCard());
					}
				}
			}
		}
		return checkIfNewCardsAreElegible(newCards, toBeCheckedCard, up);
	}
	
	/**
	 * checks whether there is a another false colored card between the checkedCard and the results thus making the card ineligible
	 * From the placed card go down/up until you find the added cards, or you hit the ceiling/bottom. 
	 * If you find a wrong colored visible card inbetween set a flag and consecutively remove the card from the set.
	 */
	private HashSet<CardInAtman> checkIfNewCardsAreElegible(HashSet<CardInAtman> newCards, CardInAtman checkedCard, boolean up)		
	{
		boolean flagWrongColorCardInBetween = false;
		
		for(Corner c: checkedCard.getCorners())
		{
			flagWrongColorCardInBetween = false;
			while(getAboveOrBelow(c, up) != null)
			{
				c = getAboveOrBelow(c, up);
				if(newCards.contains(c.getParentCard()))
				{
					if(flagWrongColorCardInBetween)
						newCards.remove(c.getParentCard());
					break;
				}
				if((c.getParentCard().getColor() != _color) && (c.getParentCard().isVisible()))
				{
					flagWrongColorCardInBetween = true;
				}
			}
		}
		return newCards;
	}

	/**
	 * returns c.getAbove if up
	 * or c.getBelow if not up
	 */
	private Corner getAboveOrBelow(Corner c, boolean up)
	{
		if(up)
			return c.getAbove();
		return c.getBelow();
	}
}
