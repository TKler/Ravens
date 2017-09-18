package cards;

import static org.junit.Assert.*;

import org.junit.Test;

public class MemoryCardTest
{
	MemoryCard _green11 = new MemoryCard(CardColor.GREEN, new boolean[][]{{false, false},{true, false}}, 1);
	MemoryCard _purple1 = new MemoryCard(CardColor.PURPLE, new int[][]{{0, 0},{1, 1}}, 1);
//	MemoryCard _purple1 = new MemoryCard(CardColor.PURPLE, new boolean[][]{{false, false},{true, true}}, 1);
//	private static void createGreens()
//	{
//		_deck.add(new MemoryCard(CardColor.GREEN, new int[][]{{0, 0},{1, 0}}, 1));
//		_deck.add(new MemoryCard(CardColor.GREEN, new int[][]{{1, 0},{0, 0}}, 1));
//		
//		_deck.add(new MemoryCard(CardColor.GREEN, new int[][]{{1, 1},{0, 0}}, 2));
//		_deck.add(new MemoryCard(CardColor.GREEN, new int[][]{{0, 0},{1, 1}}, 2));
//		
//		_deck.add(new MemoryCard(CardColor.GREEN, new int[][]{{0, 1},{0, 1}}, 3));
//		
//		_deck.add(new MemoryCard(CardColor.GREEN, new int[][]{{1, 0},{1, 0}}, 4));
//		
//		_deck.add(new MemoryCard(CardColor.GREEN, new int[][]{{0, 1},{1, 1}}, 5));
//	}
	
	@Test
	public void isRaven()
	{
		assertFalse(_green11.isRaven());
	}
	
	@Test
	public void getValue()
	{
		assertEquals(_green11.getValue(), 1);
	}
	
	@Test
	public void getColor()
	{
		assertEquals(_green11.getColor(), CardColor.GREEN);
	}
	
	@Test
	public void getAbility()
	{
		assertEquals(_green11.getAbility(), Ability.GREENLOW);
	}

	@Test
	public void shades()
	{
		assertFalse(_green11.isUpperLeftShaded());
		assertTrue(_green11.isUpperRightShaded());
		assertFalse(_green11.isLowerLeftShaded());
		assertFalse(_green11.isLowerRightShaded());
		
		assertFalse(_purple1.isUpperLeftShaded());
		assertTrue(_purple1.isUpperRightShaded());
		assertFalse(_purple1.isLowerLeftShaded());
		assertTrue(_purple1.isLowerRightShaded());
	}
}
