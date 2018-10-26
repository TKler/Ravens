package players;

import java.util.ArrayList;
import java.util.List;

import cards.Card;
import cards.MemoryCard;
import poem.Poem;

/**
 * Ren manages the poem and her scorepile(not yet a class)
 * @author TKler
 */

public class Ren implements RenInterface 
{
	Game _game;
	Poem _poem;
	List<Card> _scorePile;
	
	
	public Ren(Game game) 
	{
		_game = game;
		_scorePile = new ArrayList<Card>();
	}

	/**
	 * @assert Cards are in order from first row to last.
	 */
	@Override
	public void createHeart(List<MemoryCard> list)
	{
		_poem = new Poem(list);
	}

	@Override
	public void useCard(MemoryCard card, int xposition, int yposition) 
	{
		_poem.useCardInPoem(xposition, yposition);
	}
	
	public List<MemoryCard> getHeartCards()
	{
		return _poem.getHeartCards();
	}

	@Override
	public void addCardToThePoem(MemoryCard card) 
	{
		if(!_poem.addToCardPoem(card))
			_game.discardCard(card);
	}
	
	public void relieveHeartCard(int row)
	{
		_poem.revealHeartCard(row);
	}
		
	@Override
	public List<MemoryCard> endDream() 
	{
		_scorePile.addAll(_poem.getScoreCards());
		return _poem.endOfDream();
	}
}
