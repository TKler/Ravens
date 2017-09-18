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
public class SplitCheckerTest
{
	Atman _atman = new Atman(null);
	SplitChecker _split = new SplitChecker();

	
	MemoryCard _green1 = new MemoryCard(CardColor.GREEN, new int[][]{{1, 0},{0, 0}}, 1);
	MemoryCard _green2 = new MemoryCard(CardColor.GREEN, new int[][]{{1, 1},{0, 0}}, 2);
	MemoryCard _green2_2 = new MemoryCard(CardColor.GREEN, new int[][]{{0, 0},{1, 1}}, 2);
	MemoryCard _green3 = new MemoryCard(CardColor.GREEN, new int[][]{{0, 1},{0, 1}}, 3);
	MemoryCard _green4 = new MemoryCard(CardColor.GREEN, new int[][]{{1, 0},{1, 0}}, 4);
	MemoryCard _green5 = new MemoryCard(CardColor.GREEN, new int[][]{{0, 1},{1, 1}}, 5);
	
	MemoryCard _purple2 = new MemoryCard(CardColor.PURPLE, new int[][]{{1, 0},{1, 0}}, 2);
	MemoryCard _purple4 = new MemoryCard(CardColor.PURPLE, new int[][]{{0, 1},{1, 0}}, 4);
	MemoryCard _purple5 = new MemoryCard(CardColor.PURPLE, new int[][]{{0, 1},{0, 1}}, 5);

	MemoryCard _yellow3 = new MemoryCard(CardColor.YELLOW, new int[][]{{1, 0},{0, 1}}, 3);
	
	@Before
	public void setUp() throws Exception
	{
		_atman = new Atman(null);
		_atman.addStartCard(_green3);
	}

	@Test
	public void simpleAddNo()
	{
		_atman._cardsInAtman.add(new CardInAtman(_purple5, null, _atman._cardsInAtman.get(0)._upperLeft, null, _atman._cardsInAtman.get(0)._lowerLeft));
		assertFalse(_split.checkForSplit(_atman._cardsInAtman));
	}
	
	@Test
	public void simpleAddYes()
	{
		_atman._cardsInAtman.add(new CardInAtman(_purple5, null, _atman._cardsInAtman.get(0)._upperLeft, null, _atman._cardsInAtman.get(0)._lowerLeft));
		_atman._cardsInAtman.add(new CardInAtman(_purple4, _atman._cardsInAtman.get(0)._upperRight, null, _atman._cardsInAtman.get(0)._lowerRight, null));
		assertTrue(_split.checkForSplit(_atman._cardsInAtman));
	}

	@Test
	public void complexerAddYes()
	{
		_atman._cardsInAtman.add(new CardInAtman(_purple4, _atman._cardsInAtman.get(0)._upperRight, null, _atman._cardsInAtman.get(0)._lowerRight, null));
		_atman._cardsInAtman.add(new CardInAtman(_purple2, _atman._cardsInAtman.get(1)._upperRight, null, _atman._cardsInAtman.get(1)._lowerRight, null));
		_atman._cardsInAtman.add(new CardInAtman(_green2_2, null, _atman._cardsInAtman.get(0)._lowerLeft, null, null));
		_atman._cardsInAtman.add(new CardInAtman(_green1, null, null, null, _atman._cardsInAtman.get(3)._lowerRight));
		_atman._cardsInAtman.add(new CardInAtman(_yellow3, null, _atman._cardsInAtman.get(0)._upperLeft, _atman._cardsInAtman.get(3)._upperLeft, _atman._cardsInAtman.get(3)._upperRight));
		assertTrue(_split.checkForSplit(_atman._cardsInAtman));
		assertEquals(_split.getCC().size(), 2);
	}
	
	@Test
	public void complexerAddYesThreeWay()
	{
		_atman = new Atman(null);
		_atman.addStartCard(_green5);
		_atman._cardsInAtman.add(new CardInAtman(_purple5, null, _atman._cardsInAtman.get(0)._upperLeft, null, _atman._cardsInAtman.get(0)._lowerLeft));
		_atman._cardsInAtman.add(new CardInAtman(_purple4, null, null, _atman._cardsInAtman.get(0)._upperRight, null));
		_atman._cardsInAtman.add(new CardInAtman(_green1, _atman._cardsInAtman.get(0)._lowerRight, null, null, null));
		assertTrue(_split.checkForSplit(_atman._cardsInAtman));
		assertEquals(_split.getCC().size(), 3);
	}
	
	@Test
	public void simpleDeleteNo()
	{
		CardInAtman card1 = new CardInAtman(_purple5, null, _atman._cardsInAtman.get(0)._upperLeft, null, _atman._cardsInAtman.get(0)._lowerLeft);
		_atman._cardsInAtman.add(card1);
		card1.removeThisCard();
		_atman._cardsInAtman.remove(card1);
		assertFalse(_split.checkForSplit(_atman._cardsInAtman));
	}
	
	@Test
	public void simpleDeleteYes()
	{
		CardInAtman card1 = new CardInAtman(_purple5, null, _atman._cardsInAtman.get(0)._upperLeft, null, _atman._cardsInAtman.get(0)._lowerLeft);
		_atman._cardsInAtman.add(card1);
		CardInAtman card2 = new CardInAtman(_purple4, null, _atman._cardsInAtman.get(1)._lowerLeft, null, null);
		_atman._cardsInAtman.add(card2);
		card1.removeThisCard();
		_atman._cardsInAtman.remove(card1);
		assertTrue(_split.checkForSplit(_atman._cardsInAtman));
	}
	
	@Test
	public void simpleNoWithStartCard()
	{
//		CardInAtman card1 = new CardInAtman(_purple5, null, _atman._cardsInAtman.get(0)._upperLeft, null, _atman._cardsInAtman.get(0)._lowerLeft);
//		_atman._cardsInAtman.add(card1);
		_atman.placeCard(_purple5, null, _atman._cardsInAtman.get(0)._upperLeft, null, _atman._cardsInAtman.get(0)._lowerLeft);
		
		_atman.removeCard(_atman.getCards().get(0));
		assertFalse(_split.checkForSplit(_atman._cardsInAtman));
	}
}
