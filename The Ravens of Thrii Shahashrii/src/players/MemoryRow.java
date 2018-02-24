package players;
import java.util.ArrayList;

import cards.Card;
import cards.MemoryCard;
import cards.Raven;

/**
 * Drawn Cards are placed into the memory row until Feth decides to stop drawing.
 * Ravens drawn outside of the ravenrow need to be buffered till the end of drawing
 **/

public class MemoryRow 
{
	int _cardsDrawn, _sizeOfSafeSpace;
	ArrayList<MemoryCard> _list;
	ArrayList<Raven> _drawnInsideSafe;
	ArrayList<Raven> _drawnOutsideSafe;
	
	
	public MemoryRow()
	{
		_list = new ArrayList<MemoryCard>();
		_drawnInsideSafe = new ArrayList<Raven>(5);
		_drawnOutsideSafe = new ArrayList<Raven>(5);
		_cardsDrawn = 0;
		_sizeOfSafeSpace = 0;
	}

	public void addCard(Card card)
	{
		_cardsDrawn++;
		
		if(card.isRaven())
		{
			if(_cardsDrawn <= _sizeOfSafeSpace)
				_drawnInsideSafe.add((Raven) card);
			else
				_drawnOutsideSafe.add((Raven) card);
		}
		else
			_list.add((MemoryCard) card);
	}

	public ArrayList<Raven> getDiscardedRavens() 
	{
		return _drawnInsideSafe;
	}

	public ArrayList<Raven> getNewRavens() 
	{
		return _drawnOutsideSafe;
	}

	public void updateSizeOfSafeSpace(int safeSpaceSize)
	{
		_sizeOfSafeSpace = safeSpaceSize;
	}

	public ArrayList<MemoryCard> discardRemaining() 
	{
		return _list;
	}

	public void reset() 
	{
		_list.clear();
		_drawnInsideSafe.clear();
		_drawnOutsideSafe.clear();
		_cardsDrawn = 0;
		// The ravennmb needs to be updated at the start, (which is is) so leave it for now
	}
}
