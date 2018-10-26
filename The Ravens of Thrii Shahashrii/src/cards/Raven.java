package cards;

import java.util.ArrayList;

public class Raven extends Card
{
	ArrayList<MemoryCard> _stolenMemories;
		
	public Raven(CardColor color) 
	{
		super();
		_color = color;
		_stolenMemories = new ArrayList<MemoryCard>();
	}

	@Override
	public boolean isRaven()
	{
		return true;
	}
	
	/**
	 * @assert card is of the same color as raven
	 */
	public void stealMemories(MemoryCard card)
	{
		_stolenMemories.add(card);
	}
	
	public void devourMemories()
	{
		_stolenMemories.clear();
	}
	
	public ArrayList<MemoryCard> showStolenMemories()
	{
		return _stolenMemories;
	}
	
	public void yellowAbility(MemoryCard card)
	{
		_stolenMemories.remove(card);
	}
	
	public ArrayList<MemoryCard> beingRelieved()
	{
		@SuppressWarnings("unchecked")
		ArrayList<MemoryCard> stolen = (ArrayList<MemoryCard>) _stolenMemories.clone();
		_stolenMemories.clear();
		return stolen;
	}
}
