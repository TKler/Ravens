package players;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import atman.Atman;
import cards.CardColor;
import cards.MemoryCard;

public class GameTest
{
	Game _game;
	Ren _ren;
	Feth _feth; 
	Atman _atman;
	
	MemoryCard _green1 = new MemoryCard(CardColor.GREEN, new int[][]{{1, 0},{0, 0}}, 1);
	MemoryCard _green2 = new MemoryCard(CardColor.GREEN, new int[][]{{0, 0},{1, 1}}, 2);
	MemoryCard _green3 = new MemoryCard(CardColor.GREEN, new int[][]{{0, 1},{0, 1}}, 3);
	MemoryCard _green4 = new MemoryCard(CardColor.GREEN, new int[][]{{1, 0},{1, 0}}, 4);

	MemoryCard _purple4 = new MemoryCard(CardColor.PURPLE, new int[][]{{0, 1},{1, 0}}, 4);
	MemoryCard _purple5 = new MemoryCard(CardColor.PURPLE, new int[][]{{0, 1},{0, 1}}, 5);
	
	@Before
	public void initialise()
	{
		_game = new Game(null);
		_ren = _game._ren;
		_feth = _game._feth;
		_atman = _game._atman;
		
		List<MemoryCard> list = new ArrayList<MemoryCard>(4);
		list.add(_green2);
		list.add(_green3);
		list.add(_green1);
		list.add(_green4);
		
		_ren.createHeart(list);
	}
	
	@Test
	public void testRenTakesCardFromAtman()
	{
		_atman.addStartCard(_green3);
		_atman.placeCard(_purple4, null, _atman.getCards().get(0).getCorners().get(2), null, null);
		
		assertEquals(_atman.getCards().size(), 2);
		assertEquals(_atman.getCards().get(0).getCard(), _green3);
		assertEquals(_atman.getCards().get(1).getCard(), _purple4);
		
		_game.renTakesCardFromAtman(_atman.getCards().get(0));
	
		assertEquals(_ren._poem.getCardAt(0, 0), _green2);
		assertEquals(_ren._poem.getCardAt(1, 0), _green3);
		assertFalse(_atman.isEmpty());
	}

	// needs gui communication
//	@Test
//	public void testStartDream()
//	{
//		_dummy.startDream();
//		assertFalse(_dummy._ren._poem.getCardAt(0, 0).equals(null));
//		assertEquals(_atman.getCards().size(), 1);
//	}

	// TODO do we really need to test this? wait till big scenario construction
	@Test
	public void testEndDream()
	{
		fail("THIS IS A REMINDER! Nothing here is really tested, "
				+ "since most everything has gui interatction.");
	}

}
