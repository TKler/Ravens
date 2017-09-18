package cards;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class DeckTest
{
	Deck _dummy;
	MemoryCard _purple5 = new MemoryCard(CardColor.PURPLE, new int[][]{{0, 1},{0, 1}}, 5);
	Raven _redRaven = new Raven(CardColor.RED);
	
	
	@Before
	public void setUp() throws Exception
	{
		 _dummy = new Deck();
	}
	
	@Test
	public void all40Cards()
	{
		assertEquals(_dummy._deck.size(), 40);
	}
	
	@Test
	public void testGetNot5MemoryCard()
	{
		_dummy._deck.push(_purple5);
		_dummy._deck.push(_redRaven);
		MemoryCard test = _dummy.getNot5MemoryCard();
		assertTrue(test.getValue() < 5);
	}

	@Test
	public void shuffleBackIn()
	{ 
		_dummy._deck.remove(_purple5);
		_dummy._deck.remove(_redRaven);
		assertFalse(_dummy._deck.contains(_purple5));
		assertFalse(_dummy._deck.contains(_redRaven));
		ArrayList<Card> list = new ArrayList<Card>();
		list.add(_purple5);
		_dummy.shuffleBackIn(list);
		
		assertTrue(_dummy._deck.contains(_purple5));
	}
}
