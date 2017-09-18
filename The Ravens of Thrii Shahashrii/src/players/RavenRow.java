package players;

import java.util.ArrayList;

import cards.Raven;
import cards.CardColor;
import cards.MemoryCard;

/**
 *  This does not manage the access for the ravens!
 **/

public class RavenRow 
{
	ArrayList<Raven> _active;
	ArrayList<Raven> _defeated;
	
	public RavenRow()
	{
		_active = new ArrayList<Raven>(5);
		_defeated = new ArrayList<Raven>(5);
	}
	
	public void addRaven(ArrayList<Raven> arrayList)
	{
		_active.addAll(arrayList);
	}
	
	public void relieve(CardColor color)
	{
		for(Raven r : _active)
		{
			if(r.getColor() == color)
				defeatRaven(r);
		}
	}

	private void defeatRaven(Raven raven) 
	{
		_active.remove(raven);
		raven.beingRelieved();
		_defeated.add(raven);
	}
	
	/**
	 * moves the defeated ravens back into the ravenrow
	 */
	public void dreamEnd()
	{
		_active.addAll(_defeated);
	}
	
	/**
	 * checks one of the losing conditions, if all 5 ravens are in the ravenrow active.
	 * @return true if lost
	 */
	public boolean checkForAllRavens()
	{
		if(_active.size() == 5)
			return true;
		
		return false;
	}

	/**
	 * use checkForAllRavens for lose conditions,
	 */
	public int getNumberOfActiveRavens() 
	{
		return _active.size();
	}

	public ArrayList<Raven> getActiveRavens() 
	{
		return _active;
	}
	
	/**
	 * method for the 4 situations when cards need to go past raven consumption
	 * a) discarding the remaining memoryrow 
	 * b) discarding cards from the atman after a split 
	 * c) discarding cards from the poem if they dont fit 
	 * d) at the end of the dream, poem and atman
	 * 
	 * @return return the nonstolen memories
	 **/
	public ArrayList<MemoryCard> discardCards(ArrayList<MemoryCard> discardedCards)
	{
		for(Raven raven : _active)
		{
			for(MemoryCard m : discardedCards)
			{	
				if(raven.getColor() == m.getColor())
				{
					raven.stealMemories(m);
					discardedCards.remove(m);
				}
			}
		}
		return discardedCards;
	}
}
