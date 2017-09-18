package atman;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import cards.CardColor;
import cards.MemoryCard;

public class CardInAtmanTest
{
	
	MemoryCard _green1 = new MemoryCard(CardColor.GREEN, new int[][]{{0, 0},{1, 0}}, 1);
	MemoryCard _purple1 = new MemoryCard(CardColor.PURPLE, new int[][]{{0, 0},{1, 1}}, 1);
	CardInAtman _green1InAtman = new CardInAtman(_green1, null, null, null, null);

	@Test
	public void testConstructor()
	{
		CardInAtman dummy = new CardInAtman(_purple1, 
				_green1InAtman._upperLeft, _green1InAtman._upperRight, 
				_green1InAtman._lowerLeft, _green1InAtman._lowerRight);
		
		assertEquals(dummy.getCard(), _purple1);
		assertTrue(dummy.isVisible());
		assertTrue(dummy.isLowest());
		
		ArrayList<Corner> corners = dummy.getCorners();
		for(Corner c : corners)
		{
			assertTrue(c.isCardVisible());
		}
		
		assertEquals(dummy._upperLeft.isShaded(), _purple1.isUpperLeftShaded());
		assertEquals(dummy._upperRight.isShaded(), _purple1.isUpperRightShaded());
		assertEquals(dummy._lowerLeft.isShaded(), _purple1.isLowerLeftShaded());
		assertEquals(dummy._lowerRight.isShaded(), _purple1.isLowerRightShaded());
	}
//	
//	
//	
//	@Test
//	public void test()
//	{
//		fail("Not yet implemented");
//	}

	
//	well, lot of setters and getters, test if something above goes horribly wrong
}
