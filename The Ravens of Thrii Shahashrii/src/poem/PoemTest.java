package poem;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import cards.CardColor;
import cards.MemoryCard;

public class PoemTest
{
	
	MemoryCard _green1 = new MemoryCard(CardColor.GREEN, new int[][]{{1, 0},{0, 0}}, 1);
	MemoryCard _green2 = new MemoryCard(CardColor.GREEN, new int[][]{{0, 0},{1, 1}}, 2);
	MemoryCard _green3 = new MemoryCard(CardColor.GREEN, new int[][]{{0, 1},{0, 1}}, 3);
	MemoryCard _green4 = new MemoryCard(CardColor.GREEN, new int[][]{{1, 0},{1, 0}}, 4);
	
	MemoryCard _purple4 = new MemoryCard(CardColor.PURPLE, new int[][]{{0, 1},{1, 0}}, 4);
	MemoryCard _purple5 = new MemoryCard(CardColor.PURPLE, new int[][]{{0, 1},{0, 1}}, 5);
	
	Poem _dummyPoem;
	
	@Before
	public void initialisePoem()
	{
		ArrayList<MemoryCard> list = new ArrayList<MemoryCard>();
		list.add(0, _green3);
		list.add(1, _green2);
		list.add(2, _green1);
		list.add(3, _green4);
		
		_dummyPoem = new Poem(list);
	}
	
	
	
	@Test
	public void constructor()
	{
		assertEquals(_dummyPoem._poem.get(0).getHeartCard().getStatus(), CardStatusInPoem.UNREVEALED);
		assertEquals(_dummyPoem._poem.get(1).getHeartCard().getStatus(), CardStatusInPoem.UNREVEALED);
		assertEquals(_dummyPoem._poem.get(2).getHeartCard().getStatus(), CardStatusInPoem.UNREVEALED);
		assertEquals(_dummyPoem._poem.get(3).getHeartCard().getStatus(), CardStatusInPoem.UNREVEALED);
	}
	
	@Test
	public void addCardToPoem()
	{
		assertFalse(_dummyPoem.addToCardPoem(_purple5));
		assertTrue(_dummyPoem.addToCardPoem(_purple4));
		assertEquals(_dummyPoem._poem.get(0).getHeartCard().getStatus(), CardStatusInPoem.UNREVEALED);
		
		assertTrue(_dummyPoem.addToCardPoem(_green2));
		assertEquals(_dummyPoem._poem.get(1).getHeartCard().getStatus(), CardStatusInPoem.BURNED);
	}

	@Test
	public void getScoreCards()
	{
		_dummyPoem.revealHeartCard(1);
		_dummyPoem.revealHeartCard(2);
		_dummyPoem.useCardInPoem(0, 2);
		
		ArrayList<MemoryCard> list = _dummyPoem.getScoreCards();
		
		assertTrue(list.contains(_green2));
		assertTrue(list.contains(_green1));
		assertTrue(list.size() == 2);
	}
	
	@Test
	public void endOfDream()
	{
		_dummyPoem.addToCardPoem(_purple4);
		_dummyPoem.addToCardPoem(_purple5);
		
		ArrayList<MemoryCard> list = _dummyPoem.endOfDream();
		
		assertTrue(list.contains(_green1));
		assertTrue(list.contains(_purple5));
		assertTrue(list.size() == 6);
	}
	
}
