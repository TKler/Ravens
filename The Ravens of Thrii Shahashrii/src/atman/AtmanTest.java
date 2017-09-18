package atman;

import static org.junit.Assert.*;

import org.junit.Test;

import cards.CardColor;
import cards.MemoryCard;

//Has GUI interaction, so be careful to neither create splits nor relieves

public class AtmanTest
{
	Atman _atman = new Atman(null);
	MemoryCard _green1 = new MemoryCard(CardColor.GREEN, new int[][]{{0, 0},{1, 0}}, 1);
	MemoryCard _purple1 = new MemoryCard(CardColor.PURPLE, new int[][]{{0, 0},{1, 1}}, 1);
	MemoryCard _green3 = new MemoryCard(CardColor.GREEN, new int[][]{{0, 1},{0, 1}}, 3);
	MemoryCard _purple5 = new MemoryCard(CardColor.PURPLE, new int[][]{{0, 1},{0, 1}}, 5);
	
	@Test
	public void constructorConstructsEmptyList()
	{
		assertTrue(_atman.isEmpty());
	}
	
	@Test
	public void addStartCard()
	{
		_atman.addStartCard(_green1);
		
		assertFalse(_atman.isEmpty());
	}
	
	
	@Test
	public void isPlacable()
	{
		_atman.addStartCard(_green1);
		// the green one on the bottom, the purple on shifted on line down
		assertFalse(_atman.placeCard(_purple1, 
				 _atman._cardsInAtman.get(0)._lowerLeft, _atman._cardsInAtman.get(0)._lowerRight, null, null));
		
		assertEquals(_atman._cardsInAtman.get(0).getCard(), _green1);
		assertTrue(_atman._cardsInAtman.size() == 1);
		assertTrue(_atman._cardsInAtman.get(0)._upperLeft != null);
		assertTrue(_atman._cardsInAtman.get(0)._upperRight != null);
		
		// the green one on the bottom, the purple on shifted one line up
		assertTrue(_atman.placeCard(_purple1, 
				null, null, _atman._cardsInAtman.get(0)._upperLeft, _atman._cardsInAtman.get(0)._upperRight));
		
		// the other green 1 on top of this once shifted to the left
		assertFalse(_atman.placeCard(new MemoryCard(CardColor.GREEN, new int[][]{{1, 0},{0, 0}}, 1), 
				null, _atman._cardsInAtman.get(0)._upperRight, null, _atman._cardsInAtman.get(0)._lowerRight));
		
		// the other green 1 on top of this once shifted to the right
		assertTrue(_atman.placeCard(new MemoryCard(CardColor.GREEN, new int[][]{{1, 0},{0, 0}}, 1), 
				_atman._cardsInAtman.get(1)._lowerRight, null, _atman._cardsInAtman.get(0)._lowerRight, null));
	}
	
	@Test
	public void deleteCard()
	{
		_atman.addStartCard(_green3);
		CardInAtman card = new CardInAtman(_purple5, null, _atman._cardsInAtman.get(0)._upperLeft, null, _atman._cardsInAtman.get(0)._lowerLeft);
		_atman._cardsInAtman.add(card);
		assertTrue(_atman._cardsInAtman.get(0)._upperLeft.getAbove() != null);
		assertTrue(_atman._cardsInAtman.get(0)._lowerLeft.getAbove() != null);
		assertTrue(_atman._cardsInAtman.get(0)._upperRight.getAbove() == null);
		assertTrue(_atman._cardsInAtman.get(0)._lowerRight.getAbove() == null);
		
		assertTrue(_atman._cardsInAtman.get(1)._upperRight.getBelow() != null);
		assertTrue(_atman._cardsInAtman.get(1)._lowerRight.getBelow() != null);
		assertTrue(_atman._cardsInAtman.get(1)._lowerRight.getAbove() == null);
		
		_atman.removeCard(card);
		
		assertTrue(_atman._cardsInAtman.get(0)._upperLeft.getAbove() == null);
		assertTrue(_atman._cardsInAtman.get(0)._lowerLeft.getAbove() == null);
		assertTrue(_atman._cardsInAtman.get(0)._upperRight.getAbove() == null);
		assertTrue(_atman._cardsInAtman.get(0)._lowerRight.getAbove() == null);
	}
	
	@Test
	public void endDream()
	{
		_atman.addStartCard(_green3);
		CardInAtman card = new CardInAtman(_purple5, null, _atman._cardsInAtman.get(0)._upperLeft, null, _atman._cardsInAtman.get(0)._lowerLeft);
		
		assertEquals(_atman._cardsInAtman.size(), _atman.endDream().size());
		assertEquals(_atman._cardsInAtman.get(0).getCard(), _atman.endDream().get(0));
		
		_atman._cardsInAtman.add(card);
		
		assertEquals(_atman._cardsInAtman.size(), _atman.endDream().size());
		assertEquals(_atman._cardsInAtman.get(1).getCard(), _atman.endDream().get(1));
		
		_atman.removeCard(card);
		
		assertEquals(_atman._cardsInAtman.size(), _atman.endDream().size());
		assertEquals(_atman._cardsInAtman.get(0).getCard(), _atman.endDream().get(0));
	}

}
