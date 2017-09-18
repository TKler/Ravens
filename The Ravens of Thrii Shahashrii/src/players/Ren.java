package players;

import java.util.ArrayList;

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
	ArrayList<Card> _scorePile;
	
	
	public Ren(Game game) 
	{
		_game = game;
		_scorePile = new ArrayList<Card>();
	}

	/**
	 * @assert Cards are in descending order from first row to last.
	 */
	public void createHeart(ArrayList<MemoryCard> heartCards)
	{
		_poem = new Poem(heartCards);
	}

	public boolean useCard(MemoryCard card, int xposition, int yposition) 
	{
		// TODO Auto-generated method stub
		return false;
	}
	
	public ArrayList<MemoryCard> getHeartCards()
	{
		return _poem.getHeartCards();
	}


	public void addCardToThePoem(MemoryCard card) 
	{
		if(!_poem.addToCardPoem(card))
			_game.discardCard(card);
			
	}
	
	public void relieveHeartCard(int row)
	{
		_poem.revealHeartCard(row);
	}
		
	public ArrayList<MemoryCard> endDream() 
	{
		_scorePile.addAll(_poem.getScoreCards());
		return _poem.endOfDream();
	}
}
