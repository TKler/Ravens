package atman;

import static org.junit.Assert.*;

import org.junit.Test;

import cards.CardColor;
import cards.MemoryCard;

public class CornerTest
{
	MemoryCard _green11 = new MemoryCard(CardColor.GREEN, new int[][]{{0, 0},{1, 0}}, 1);
	MemoryCard _green12 = new MemoryCard(CardColor.GREEN, new int[][]{{1, 0},{0, 0}}, 1);
	CardInAtman _green11InAtman = new CardInAtman(_green11, null, null, null, null);
	CardInAtman _green12InAtman = new CardInAtman(_green12, _green11InAtman._upperRight, null,  _green11InAtman._lowerRight, null);
	CardInAtman _dummyCard = new CardInAtman(_green11, _green12InAtman._upperLeft, _green12InAtman._upperRight,  _green12InAtman._lowerLeft, _green12InAtman._lowerRight);
	
	Corner _testCorner = new Corner(null, _green12InAtman, true);
	Corner _testCorner2 = new Corner(null, _green11InAtman, false);
	
	@Test
	public void getParent()
	{
		assertEquals(_testCorner.getParentCard(), _green12InAtman);
	}
	
	@Test
	public void changeBelow()
	{
		assertEquals(_testCorner.getBelow(), null);
		_testCorner.changeBelow(_testCorner2);
		assertEquals(_testCorner.getBelow(), _testCorner2);
	}
	
	@Test
	public void changeAbove()
	{
		assertEquals(_testCorner.getAbove(), null);
		_testCorner.changeAbove(_testCorner2);
		assertEquals(_testCorner.getAbove(), _testCorner2);
	}
	
	@Test
	public void isShaded()
	{
		assertTrue(_testCorner.isShaded());
		assertFalse(_testCorner2.isShaded());
	}
	
	@Test
	public void removeThisCard()
	{
		_green12InAtman.removeThisCard();
		assertEquals(_green11InAtman.getCorners().get(1)._above, _dummyCard.getCorners().get(0));
		assertEquals(_dummyCard.getCorners().get(2)._below, _green11InAtman.getCorners().get(3));
	}

	@Test
	public void isCardVisible()
	{
		assertTrue(_green11InAtman.isVisible());
		assertFalse(_green12InAtman.isVisible());
	}
	// isCardLowest and isCardVisible are simple pipes that should be tested at CardInAtmanTest
	
}
