package cards;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class RavenTest
{
	Raven _redRaven = new Raven(CardColor.RED);
	MemoryCard _red11 = new MemoryCard(CardColor.RED, new int[][]{{0, 1},{0, 0}}, 1);
	MemoryCard _red12 = new MemoryCard(CardColor.RED, new int[][]{{0, 0},{0, 1}}, 1);
	
	@Test
	public void isRaven()
	{
		assertTrue(_redRaven.isRaven());
	}
	
	// The others are straight forward getters/setters test if errors etc
	
	@Test
	public void beingRelieved()
	{
		_redRaven.stealMemories(_red11);
		_redRaven.stealMemories(_red12);
		
		ArrayList<MemoryCard> list = _redRaven.beingRelieved();
		
		assertTrue(list.contains(_red11));
		assertTrue(list.contains(_red12));
		assertFalse(_redRaven.showStolenMemories().contains(_red11));
		assertFalse(_redRaven.showStolenMemories().contains(_red12));
	}

}
