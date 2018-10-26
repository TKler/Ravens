package players;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

import cards.CardColor;
import cards.MemoryCard;
import cards.Raven;

public class RavenRowTest
{
	Raven _redRaven = new Raven(CardColor.RED);
	Raven _blueRaven = new Raven(CardColor.BLUE);
	Raven _greenRaven = new Raven(CardColor.GREEN);
	RavenRow _dummy = new RavenRow();
	List<Raven> _ravenList = new ArrayList<Raven>();
	
	MemoryCard _blue3 = new MemoryCard(CardColor.BLUE, new int[][]{{0, 0},{1, 1}}, 3);
	MemoryCard _blue4 = new MemoryCard(CardColor.BLUE, new int[][]{{1, 1},{0, 0}}, 4);
	MemoryCard _blue5 = new MemoryCard(CardColor.BLUE, new int[][]{{1, 0},{1, 1}}, 5);
	MemoryCard _red3 = new MemoryCard(CardColor.RED, new int[][]{{1, 0},{1, 0}}, 3);

	
	@Test
	public void addRaven()
	{
		_ravenList.add(_redRaven);
		_dummy.addRaven(_ravenList);
		assertEquals(_dummy._active.get(0), _redRaven);
	}
	
	@Test
	public void relieve()
	{
		_ravenList.add(_redRaven);
		_ravenList.add(_blueRaven);
		_dummy.addRaven(_ravenList);
		_dummy.relieve(CardColor.RED);
		
		assertEquals(_dummy._active.get(0), _blueRaven);
		assertFalse(_dummy._active.contains(_redRaven));
		assertTrue(_dummy._defeated.contains(_redRaven));
	}
	
	public void discardCards()
	{
		List<MemoryCard> list = new ArrayList<MemoryCard>();
		list.add(_blue4);
		list.add(_blue5);
		list.add(_red3);
		_ravenList.add(_blueRaven);
		_dummy.addRaven(_ravenList);
		
		list = _dummy.discardCards(list);
		
		assertTrue(list.contains(_red3));
		assertFalse(list.contains(_blue4));
		assertFalse(list.contains(_blue5));
		assertTrue(_dummy._active.get(0).showStolenMemories().contains(_blue4));
		assertTrue(_dummy._active.get(0).showStolenMemories().contains(_blue5));
	}
}
