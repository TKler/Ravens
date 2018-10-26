package players;

import java.util.ArrayList;
import java.util.List;

import cards.Raven;
import cards.CardColor;
import cards.MemoryCard;

/**
 *  This does not manage the access for the ravens!
 **/

public class RavenRow 
{
	List<Raven> _active;
	List<Raven> _defeated;
	List<CardColor> _deactivatedRavensForNextOccurance;
	
	public RavenRow()
	{
		_active = new ArrayList<Raven>(5);
		_defeated = new ArrayList<Raven>(5);
		_deactivatedRavensForNextOccurance = new ArrayList<CardColor>(5);
	}
	
	public void addRaven(List<Raven> list)
	{
		_active.addAll(list);
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

	public List<Raven> getActiveRavens() 
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
	 * @assert gets called once for each instance of discards. meaning once for the end of the dream
	 **/
	public List<MemoryCard> discardCards(List<MemoryCard> combinedMemoryList)
	{
		for(Raven raven : _active)
		{
			for(MemoryCard m : combinedMemoryList)
			{	
				if((raven.getColor() == m.getColor()) && !_deactivatedRavensForNextOccurance.contains(raven.getColor()) )
				{
					raven.stealMemories(m);
					combinedMemoryList.remove(m);
				}
			}
		}
		_deactivatedRavensForNextOccurance.clear();
		return combinedMemoryList;
	}

	public void yellowHighAbility(MemoryCard card)
	{
		for(Raven raven : _active)
		{
			if(raven.getColor() == card.getColor())
				raven.yellowAbility(card);
		}
	}
	
	public void purpleHighAbility(CardColor color)
	{
		_deactivatedRavensForNextOccurance.add(color);
	}
}
