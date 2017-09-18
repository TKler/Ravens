package players;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import cards.CardColor;
import cards.MemoryCard;
import poem.Poem;

public class RenTest
{
	static Game _game;
	static Ren _dummy;
	static MemoryCard _green1;
	static MemoryCard _green2;
	static MemoryCard _green3;
	static MemoryCard _green4;
	
	static MemoryCard _purple4;
	static MemoryCard _purple5;
	static Poem _dummyPoem;
	//The other methods are pretty straight forward, test if things go horribly wrong :)
	
	@BeforeClass
	public static void setup()
	{
		_game = new Game(null);
		_dummy = new Ren(_game);
		
		_green1 = new MemoryCard(CardColor.GREEN, new int[][]{{1, 0},{0, 0}}, 1);
		_green2 = new MemoryCard(CardColor.GREEN, new int[][]{{0, 0},{1, 1}}, 2);
		_green3 = new MemoryCard(CardColor.GREEN, new int[][]{{0, 1},{0, 1}}, 3);
		_green4 = new MemoryCard(CardColor.GREEN, new int[][]{{1, 0},{1, 0}}, 4);
		
		
		_purple4 = new MemoryCard(CardColor.PURPLE, new int[][]{{0, 1},{1, 0}}, 4); 
		_purple5 = new MemoryCard(CardColor.PURPLE, new int[][]{{0, 1},{0, 1}}, 5);
		
		
		ArrayList<MemoryCard> list = new ArrayList<MemoryCard>();
		list.add(_green3);
		list.add(_green2);
		list.add(_green1);
		list.add(_green4);
		
		_dummyPoem = new Poem(list);
	}
	
	
	@Test
	public void testEndDream()
	{
		_dummy._poem = _dummyPoem;
		_dummy.relieveHeartCard(1);
		_dummy.relieveHeartCard(2);
		_dummy._poem.useCardInPoem(0, 2);
		_dummy.addCardToThePoem(_purple4);
		_dummy.addCardToThePoem(_purple5);
		
		_dummy.endDream();
		
		assertEquals(_dummy._scorePile.size(), 2);
		assertTrue(_dummy._scorePile.contains(_green2));
		assertTrue(_dummy._scorePile.contains(_green1));
	}

}
