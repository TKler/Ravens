package atman;

import java.util.ArrayList;
import java.util.List;

public class SplitChecker
{
	List<CardInAtman> _possibleCards;
	List<List<CardInAtman>> _connectedComponents;
	
	/**
	 * @assert non-empty list
	 * @param _cardsInAtman the cards in the atman
	 * @return true if the atman has a split
	 */
	public boolean checkForSplit(List<CardInAtman> _cardsInAtman)
	{
		initialiseAndPrune(_cardsInAtman);
		
		while(! _possibleCards.isEmpty())
		{
			//reset lists for this iteration
			List<CardInAtman> oneCC = new ArrayList<CardInAtman>();
			List<CardInAtman> discover = new ArrayList<CardInAtman>();
			//add the new group to the CClist
			_connectedComponents.add(oneCC);
			
			//add the first card to the discover queue (and remove it from possible
			discover.add(_possibleCards.get(0));
			_possibleCards.remove(0);

			// BFS over this Card and its neighbours (up and down)
			while(! discover.isEmpty())
			{
				// as we start with the discoverqueue we can add it to the cc
				oneCC.add(discover.get(0));

				for(Corner c : discover.get(0).getCorners())
				{
					if(c.getAbove() != null)
					{
						Corner above = c.getAbove();
						// the card my not already be in the CC or in the discovery queue and it must be visible
						if(!(oneCC.contains(above.getParentCard()) || discover.contains(above.getParentCard())) && above.getParentCard().isVisible())
						{
								discover.add(above.getParentCard());
								_possibleCards.remove(above.getParentCard());
						}
					}
					
					if(c.getBelow() != null)
					{
						Corner below = c.getBelow();
						if(!(oneCC.contains(below.getParentCard()) || discover.contains(below.getParentCard())) && below.getParentCard().isVisible())
						{
							discover.add(below.getParentCard());
							_possibleCards.remove(below.getParentCard());
					}
					}
				}
				// remove the card from discover as we added it to cc above
				discover.remove(0);
			}
		}
		// if there is more than one cc we got a split (0 is not an option since the atman cant be empty)
		return (_connectedComponents.size() != 1);
	}

	/**
	 * resets the lists and deletes all invisible cards, since we dont care about them
	 * @param _cardsInAtman
	 */
	//we could probably do without possible and just check for visibility every step, we still gotta 
	// account for it, since pruning the atman doesnt delete the connection between the cards
	private void initialiseAndPrune(List<CardInAtman> _cardsInAtman)
	{
		_possibleCards = new ArrayList<CardInAtman>();
		_connectedComponents = new ArrayList<List<CardInAtman>>();
		
		for(CardInAtman card : _cardsInAtman)
		{
			if(card.isVisible())
				_possibleCards.add(card);
		}
	}

	public List<List<CardInAtman>> getCC()
	{
		return _connectedComponents;
	}
}