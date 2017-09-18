package players;

import static org.junit.Assert.*;

import org.junit.Test;

import cards.CardColor;
import cards.Deck;
import cards.MemoryCard;
import cards.Raven;

public class MemoryRowTest
{
	Deck _deck = new Deck();
	MemoryRow _dummy = new MemoryRow();
	
	Raven _redRaven = new Raven(CardColor.RED);
	Raven _greenRaven = new Raven(CardColor.GREEN);
	MemoryCard _green1 = new MemoryCard(CardColor.GREEN, new int[][]{{1, 0},{0, 0}}, 1);
	MemoryCard _green2 = new MemoryCard(CardColor.GREEN, new int[][]{{0, 0},{1, 1}}, 2);
	MemoryCard _green3 = new MemoryCard(CardColor.GREEN, new int[][]{{0, 1},{0, 1}}, 3);
	MemoryCard _green4 = new MemoryCard(CardColor.GREEN, new int[][]{{1, 0},{1, 0}}, 4);

	@Test
	public void addCard()
	{
		assertEquals(_dummy._numberOfRavens, 0);
		_dummy._numberOfRavens = 1;
		
		assertEquals(_dummy._drawnInsideSafe.size(), 0);
		_dummy.addCard(_greenRaven);
		assertEquals(_dummy._drawnInsideSafe.size(), 1);
		
		assertEquals(_dummy._cardsDrawn, 1);
		//we need a memorycard, not a raven, so we use this.
		_dummy.addCard(_deck.getNot5MemoryCard());
		assertEquals(_dummy._cardsDrawn, 2);
		
		assertEquals(_dummy._drawnOutsideSafe.size(), 0);
		_dummy.addCard(_redRaven);
		assertEquals(_dummy._drawnOutsideSafe.size(), 1);
	}

}
