package atman;

import java.util.HashSet;
import java.util.Set;

import cards.CardColor;

public class RelieveChecker
{
	Set<CardInAtman> _connectedSameColorCards = new HashSet<CardInAtman>();
	Set<CardInAtman> _discoveredSameColorCards = new HashSet<CardInAtman>();
	int _valueSumOfCards = 0;
	CardColor _color;
	int _numberNeededToRelieve = 7; // this gets changes for low blue abilites
	
	public boolean isRelieve(CardInAtman placedCard)
	{
		_color = placedCard.getColor();
		_discoveredSameColorCards.add(placedCard);
		
		while(!_discoveredSameColorCards.isEmpty())
		{
			Set<CardInAtman> thisIteration = new HashSet<CardInAtman>();
			thisIteration.addAll(_discoveredSameColorCards);
			
			for(CardInAtman toBeCheckedCard : thisIteration)
			{
				_discoveredSameColorCards.addAll(findPossibleCardsUpAndDown(toBeCheckedCard));
				_connectedSameColorCards.add(toBeCheckedCard);
				_valueSumOfCards += toBeCheckedCard.getCard().getValue();
				if(_valueSumOfCards > _numberNeededToRelieve)
					return false;
				_discoveredSameColorCards.remove(toBeCheckedCard);
			}
		}
		boolean result = (_valueSumOfCards == _numberNeededToRelieve);
		_numberNeededToRelieve = 7;
		return result;
	}
	
	private Set<CardInAtman> findPossibleCardsUpAndDown(CardInAtman toBeCheckedCard)
	{
		Set<CardInAtman> result = new HashSet<CardInAtman>();
		result.addAll(findPossibleCards(toBeCheckedCard, false));
		result.addAll(findPossibleCards(toBeCheckedCard, true));
		return result;
	}

	/**
	 * Find cards below or above, based on up, that share the same color
	 * @param up true for up false for down
	 */
	private Set<CardInAtman> findPossibleCards(CardInAtman toBeCheckedCard, boolean up)		
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
	 * If you find a wrong colored visible card in between set a flag and consecutively remove the card from the set.
	 */
	private Set<CardInAtman> checkIfNewCardsAreElegible(HashSet<CardInAtman> newCards, CardInAtman checkedCard, boolean up)		
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
		else
			return c.getBelow();
	}

	public void blueLowAbility(boolean trueForPlusOne)
	{
		if(trueForPlusOne)
			_numberNeededToRelieve = 6;
		else
			_numberNeededToRelieve = 8;
	}
}
