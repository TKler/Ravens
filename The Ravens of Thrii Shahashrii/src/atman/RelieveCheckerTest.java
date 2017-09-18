package atman;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import cards.CardColor;
import cards.MemoryCard;
/**
 * In order to test this we have to directly place the cards in the _atman_cardsInAtman list to avoid the isPlacable() method with its gui call (or we could use mocks)
 * I still would advise to comment this line out and build your testscenarios with the normal adding to the atman to check for atleast some stupid construction errors first.
 * @author TKler
 *
 */
public class RelieveCheckerTest
{
	Atman _atman = new Atman(null);
	RelieveChecker _checker = new RelieveChecker();
	
	MemoryCard _green1 = new MemoryCard(CardColor.GREEN, new int[][]{{1, 0},{0, 0}}, 1);
	MemoryCard _green2 = new MemoryCard(CardColor.GREEN, new int[][]{{1, 1},{0, 0}}, 2);
	MemoryCard _green3 = new MemoryCard(CardColor.GREEN, new int[][]{{0, 1},{0, 1}}, 3);
	MemoryCard _green4 = new MemoryCard(CardColor.GREEN, new int[][]{{1, 0},{1, 0}}, 4);
	
	MemoryCard _purple4 = new MemoryCard(CardColor.PURPLE, new int[][]{{0, 1},{1, 0}}, 4);
	MemoryCard _purple5 = new MemoryCard(CardColor.PURPLE, new int[][]{{0, 1},{0, 1}}, 5);
	
	@Before
	public void setUp() throws Exception
	{
		_atman = new Atman(null);
		_atman.addStartCard(_green3);
	}

	@Test
	public void isRelieve1()
	{
		_atman._cardsInAtman.add(new CardInAtman(_green4, null, _atman._cardsInAtman.get(0)._lowerLeft, null, null));
		assertTrue(_checker.isRelieve(_atman._cardsInAtman.get(1)));
	}
	
	@Test
	public void isRelieve2()
	{
		_atman._cardsInAtman.add(new CardInAtman(_purple5, null, _atman._cardsInAtman.get(0)._upperLeft, _atman._cardsInAtman.get(0)._lowerLeft, null));
		_atman._cardsInAtman.add(new CardInAtman(_green4, _atman._cardsInAtman.get(1)._lowerLeft, _atman._cardsInAtman.get(1)._lowerRight, null, null));
		assertFalse(_checker.isRelieve(_atman._cardsInAtman.get(2)));
	}
	
	@Test
	public void isRelieve3()
	{
		_atman._cardsInAtman.add(new CardInAtman(_purple5, null, _atman._cardsInAtman.get(0)._upperLeft, null, _atman._cardsInAtman.get(0)._lowerLeft));
		_atman._cardsInAtman.add(new CardInAtman(_green4, _atman._cardsInAtman.get(1)._lowerRight, _atman._cardsInAtman.get(0)._lowerRight, null, null));
		assertFalse(_checker.isRelieve(_atman._cardsInAtman.get(2)));
	}
	
	/**
	 * green 4 on the bottom right of the green 3
	 * purple 5 to cover the 3 completely
	 * green 1 on the green 4 shited to the right
	 * green 2 on the green 1 shited to the top
	 */
	@Test
	public void isRelieve4()
	{
		_atman._cardsInAtman.add(new CardInAtman(_green4, _atman._cardsInAtman.get(0)._lowerRight, null, null, null));
		_atman._cardsInAtman.add(new CardInAtman(_purple5, _atman._cardsInAtman.get(0)._upperLeft, _atman._cardsInAtman.get(0)._upperRight, _atman._cardsInAtman.get(0)._lowerLeft, _atman._cardsInAtman.get(1)._upperLeft));
		_atman._cardsInAtman.add(new CardInAtman(_green1, _atman._cardsInAtman.get(1)._upperRight, null, _atman._cardsInAtman.get(1)._lowerRight, null));
		_atman._cardsInAtman.add(new CardInAtman(_green2, null, null, _atman._cardsInAtman.get(3)._upperLeft, _atman._cardsInAtman.get(3)._upperRight));
		assertTrue(_checker.isRelieve(_atman._cardsInAtman.get(4)));
	}

}
