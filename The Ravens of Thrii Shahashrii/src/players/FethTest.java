package players;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import cards.CardColor;
import cards.MemoryCard;
import cards.Raven;

public class FethTest
{
	Feth _feth;
	Raven _blueRaven = new Raven(CardColor.BLUE);
	
	@Before
	public void setUp() throws Exception
	{
		_feth = new Feth(new Game(null));
	}

	@Test
	public void testStartTurn()
	{
		_feth.startTurn();
		assertTrue(_feth._memoryRow._sizeOfSafeSpace == 0);
		addBlueRaven();
		_feth.startTurn();
		assertTrue(_feth._memoryRow._sizeOfSafeSpace == 1);
		assertTrue(_feth._ravenRow.getActiveRavens().size() == 1);
	}


	@Test
	public void testEndDream()
	{
		assertEquals(_feth._deck.size(), 40);
		do
		{
			_feth.drawCard();
			_feth.endDrawing();
			_feth.discardRemainingMemoryRow();
		}while(_feth._ravenRow.getActiveRavens().size() !=  1);
		
		Raven raven = _feth._ravenRow._active.remove(0);
		_feth._game._discardPile.add(raven);
		
		ArrayList<MemoryCard> list = new ArrayList<MemoryCard>();
		
		_feth.endDream(_feth._game._discardPile._list, list, list);
		
		assertEquals(_feth._deck.size(), 40);
	}

	private void addBlueRaven()
	{
		ArrayList<Raven> list = new ArrayList<Raven>();
		list.add(_blueRaven);
		_feth._ravenRow.addRaven(list);
	}
}
