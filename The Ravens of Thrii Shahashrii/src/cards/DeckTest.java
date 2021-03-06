package cards;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DeckTest
{
	Deck _dummy;
	MemoryCard _purple5 = new MemoryCard(CardColor.PURPLE, new int[][]{{0, 1},{0, 1}}, 5);
	Raven _redRaven = new Raven(CardColor.RED);
	
	
	@BeforeEach
	void setUp() throws Exception
	{
		 _dummy = new Deck();
	}
	
	@Test
	void all40Cards()
	{
		assertEquals(_dummy._deck.size(), 40);
	}
	
	@Test
	void testGetNot5MemoryCard()
	{
		_dummy._deck.push(_purple5);
		_dummy._deck.push(_redRaven);
		MemoryCard test = _dummy.getNot5MemoryCard();
		assertTrue(test.getValue() < 5);
	}

	@Test
	void shuffleBackIn()
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
	
	@Test
	void heartCards()
	{
		for(int i = 0; i < 7; i++) // 40 cards out of which 30 can be heartcards
		{
			List<MemoryCard> heart = _dummy.get4HeartCards();
			for(MemoryCard c: heart)
			{
				assertTrue(c.getValue() < 5);
			}
		}
	}
}
