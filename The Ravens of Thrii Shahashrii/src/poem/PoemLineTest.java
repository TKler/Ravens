package poem;

import static org.junit.Assert.*;

import org.junit.Test;

import cards.CardColor;
import cards.MemoryCard;

public class PoemLineTest
{
	MemoryCard _purple4 = new MemoryCard(CardColor.PURPLE, new int[][]{{0, 1},{1, 0}}, 4); 
	PoemLine _dummy = new PoemLine(7, _purple4);
	
	MemoryCard _green2 = new MemoryCard(CardColor.GREEN, new int[][]{{0, 0},{1, 1}}, 2);
	MemoryCard _green3 = new MemoryCard(CardColor.GREEN, new int[][]{{0, 1},{0, 1}}, 3);
	MemoryCard _green4 = new MemoryCard(CardColor.GREEN, new int[][]{{1, 0},{1, 0}}, 4);
	
	@Test
	public void constructor()
	{
		assertEquals(_dummy._limit, 7);
		assertEquals(_dummy._valueSum, 4);
		assertEquals(_dummy.getHeartCard().getCard(), _purple4);
		assertEquals(_dummy._line.get(0)._status, CardStatusInPoem.UNREVEALED);
	}
	
	@Test
	public void addCardToLine()
	{
		assertFalse(_dummy.addCardToLine(_green4));
		assertTrue(_dummy.addCardToLine(_green3));
		assertFalse(_dummy.addCardToLine(_green2));
	}
	
	@Test
	public void valueSum()
	{
		assertTrue(_dummy.getValueSum() == 4);
		_dummy.addCardToLine(_green4);
		assertTrue(_dummy.getValueSum() == 4);
		_dummy.addCardToLine(_green2);
		assertTrue(_dummy.getValueSum() == 6);
		_dummy.addCardToLine(_green3);
		assertTrue(_dummy.getValueSum() == 6);
	}
	
	@Test
	public void useCard()
	{
		_dummy.addCardToLine(_green2);
		_dummy.useCard(1);
		assertEquals(_dummy._line.get(1).getStatus(), CardStatusInPoem.USED);
	}
	
	@Test
	public void burnHeartCard()
	{
		_dummy.burnHeartCard();
		assertEquals(_dummy._line.get(0).getStatus(), CardStatusInPoem.BURNED);
	}
	
	@Test
	public void revealHeartCard()
	{
		assertEquals(_dummy._line.get(0).getStatus(), CardStatusInPoem.UNREVEALED);
		
		_dummy.addCardToLine(_green2);
		_dummy.useCard(1);
		assertEquals(_dummy._line.get(1).getStatus(), CardStatusInPoem.USED);
		_dummy.revealHeartCard();
		
		assertEquals(_dummy._line.get(0).getStatus(), CardStatusInPoem.USABLE);
		assertEquals(_dummy._line.get(1).getStatus(), CardStatusInPoem.USABLE);
	}

}
