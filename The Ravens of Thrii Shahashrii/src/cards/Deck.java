package cards;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * The draw pile so to speak.
 * consists of at most 40 cards, where only the top card can be removed and either 1 or 3 cards be put on top. 
 */
public class Deck {
	
	Stack<Card> _deck = new Stack<Card>();

	
	public Deck()
	{
		_deck = InitializeDeck.createFullDeckAsList();
		shuffle();
	}

	public Card drawCard()
	{
		return _deck.pop();
	}
	
	public void YellowHighAbility(Card card)
	{
		_deck.push(card);
	}
	
	public void YellowLowAbility(List<Card> list)
	{
		for(Card c : list)
		_deck.push(c);
	}

	public boolean isEmpty() 
	{
		return _deck.isEmpty();
	}

	public void shuffleBackIn(ArrayList<? extends Card> discardPile) 
	{
		_deck.addAll(discardPile);
		shuffle();
	}
	/**
	 * Gets  elegible heart/atmancard (MemoryCards and no 5 values)
	 *  Todo this crashes if there is no eligible card, veryvery unlikely, still should be fixed before shipping
	 */
	public MemoryCard getNot5MemoryCard()
	{
		int pos = 0;
		while(true)
		{
			if(!_deck.get(pos).isRaven())
			{
				// since it is not a raven, it must be a memorycard
				if(!(((MemoryCard) _deck.get(pos)).getValue() == 5))
				{
					Card card = _deck.get(pos);
					_deck.remove(pos);
					return (MemoryCard) card;
				}
			}
			pos++;
		}
	}
	
	/**
	 * getmemoryCard 4 times with a list as return
	 */
	public ArrayList<MemoryCard> get4HeartCards()
	{
		ArrayList<MemoryCard> heart = new ArrayList<MemoryCard>(4);
		for(int i = 0; i < 4; i++)
		{
			heart.add(getNot5MemoryCard());
		}
		return heart;
	}
	
	/**
	 * Fisher-Yates Shuffle
	 */
	private void shuffle()
	{
		//should do the same thing
		Collections.shuffle(_deck);
//		for(int index = _deck.size()-1; index < 0; index--)
//		{
//			Card card = _deck.get((int) (Math.random() * index));
//			_deck.remove(card);
//			_deck.push(card);
//		}
	}
	
	public int size()
	{
		return _deck.size();
	}

}
