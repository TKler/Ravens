package poem;
import java.util.ArrayList;
import java.util.List;

import cards.MemoryCard;

/**
 * A Poem consists of 4 rows with 1 up side down starting card each.
 * Cards can be added to the topmost active row, where rows with less than 7-7-7-5 added up value respectivly are considered active.
 * If a poem reaches 7-7-7-5 is is considered complete.
 * 
 * @author TKler
 *
 **/
public class Poem implements PoemInterface 
{
	
	int _activeLine;
	List<PoemLine> _poem; 
	List<MemoryCard> _heartCards;
	
	public Poem(List<MemoryCard> list)
	{
		_heartCards = list;
		_poem = new ArrayList<PoemLine>(4);
		
		_poem.add(new PoemLine(7, list.get(0)));
		_poem.add(new PoemLine(7, list.get(1)));
		_poem.add(new PoemLine(7, list.get(2)));
		_poem.add(new PoemLine(5, list.get(3)));
		
		_activeLine = 0;
	}
	
	
	@Override
	public boolean addToCardPoem(MemoryCard card)
	{
		boolean result = _poem.get(_activeLine).addCardToLine(card);
		
		//row complete?   // obviously could be their own methods, question of capsualtion, but doesn't matter much here
		if(_poem.get(_activeLine).isComplete())
			_activeLine++;
		
		// burn heartcard? only if it actually get added or? //TODO: osprey rulequestion
		if(_poem.get(_activeLine).getHeartCard().getColor() == card.getColor() && result)
			_poem.get(_activeLine).burnHeartCard();
		
		return result;
	}

	
	@Override
	public MemoryCard getCardAt(int xposition, int yposition)
	{
		return _poem.get(yposition).getCard(xposition);
	}
	
	
	@Override
	public void useCardInPoem(int xposition, int yposition)
	{
		_poem.get(yposition).useCard(xposition);
	}
	
	
	@Override
	public void revealHeartCard(int row)
	{
		_poem.get(row).revealHeartCard();
	}
	
	public List<MemoryCard> getScoreCards() 
	{
		List<MemoryCard> score = new ArrayList<MemoryCard>();
		for(PoemLine pl : _poem)
		{
			if((pl.getHeartCard().getStatus() == CardStatusInPoem.USABLE) || (pl.getHeartCard().getStatus() == CardStatusInPoem.USED) )
			{
				score.add(pl.getHeartCard().getCard());
				pl.removeCard(pl.getHeartCard());
			}
		}
		return score;
	}
	
	/**
	 * @assert getScoreCards is called before
	 */
	public List<MemoryCard> endOfDream()
	{
		List<MemoryCard> remaining = new ArrayList<MemoryCard>();
		
		for(PoemLine pl : _poem)
		{
			remaining.addAll(pl.getAllCards());
		}
		
		return remaining;
	}

	public List<MemoryCard> getHeartCards()
	{
		return _heartCards;
	}
}