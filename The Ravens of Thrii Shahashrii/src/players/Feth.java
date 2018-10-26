package players;

import java.util.ArrayList;
import java.util.List;

import atman.Corner;
import cards.Card;
import cards.CardColor;
import cards.Deck;
import cards.MemoryCard;
import cards.Raven;

/**
 * Feth manages the ravenrow, the memoryrow, and the deck 
 * @author TKler
 *
 */

public class Feth
{
	Game _game;
	MemoryRow _memoryRow;
	RavenRow _ravenRow;
	Deck _deck;
	
	public Feth(Game game) 
	{
		_game = game;
		_memoryRow = new MemoryRow();
		_deck = new Deck();
		_ravenRow = new RavenRow();
	}


	/*
	 * at the start of feth turn we need to check if we lost due to an empty deck,
	 * update the memoryrow about the size of the safespace aka the active ravens
	 */
	public void startTurn()
	{
		if(_deck.isEmpty() || _ravenRow.checkForAllRavens())
			_game.gameLost();
		_memoryRow.updateSizeOfSafeSpace(_ravenRow.getNumberOfActiveRavens());
	}
	
	public void drawCard() 
	{
		Card card = _deck.drawCard();
		
		if(card == null)
			_game.gameLost();
		
		_memoryRow.addCard(card);
	}
	
	/*
	 * At the end of drawing the ravens get discarded/moved into the ravenrow depending on their position
	 */
	public void endDrawing()
	{
		_game.discardCards(_memoryRow.getDiscardedRavens());
		_ravenRow.addRaven(_memoryRow.getNewRavens());
	}
	

	public void discardRemainingMemoryRow() 
	{
		_ravenRow.discardCards(_memoryRow.discardRemaining());
		_game.discardCards(_memoryRow.discardRemaining());
		_memoryRow.reset();
	}
	
	
	public void relieveMemory(CardColor color)
	{
		_ravenRow.relieve(color);
	}
	
	
	/**
	 * The way discarding at the end of the dream works is, that there are 3 sources of cards: atman, poem and discard pile
	 * each may be subjected to the ravens which steal memories (before eating them)
	 * afterwards the cards are shuffled back into the deck
	 * Then the ravenRow needs to be reset/feasting
	 */
	public void endDream(List<Card> memoryCardList, List<MemoryCard> memoryCardList2, 
			List<MemoryCard> ravenList)
	{
		List<MemoryCard> combinedMemoryList = new ArrayList<MemoryCard>();
		
		combinedMemoryList.addAll(memoryCardList2);
		combinedMemoryList.addAll(ravenList);

		List<Raven> discardedRavens = new ArrayList<Raven>(5);
		for(Card card : memoryCardList)
		{
			if(card.isRaven())
				discardedRavens.add((Raven) card);
			else
				combinedMemoryList.add((MemoryCard) card);
		}
		
		_deck.shuffleBackIn(_ravenRow.discardCards(combinedMemoryList));
		_deck.shuffleBackIn(discardedRavens);
		_ravenRow.dreamEnd();
	}
	
	public MemoryCard getHeartCardForAtman()
	{
		return _deck.getNot5MemoryCard();
	}
	
	public List<MemoryCard> getHeartCardsForRen()
	{
		startTurn();
		return _deck.get4HeartCards();
	}


	public boolean placeCardInTheAtman(MemoryCard card, Corner upperLeft, Corner upperRight, Corner lowerLeft,
			Corner lowerRight)
	{
		return _game.placeCardInTheAtman(card, lowerRight, lowerRight, lowerRight, lowerRight);
	}
	
	public void yellowHighAbility(MemoryCard card)
	{
		_ravenRow.yellowHighAbility(card);
		_deck.YellowHighAbility(card);
	}


	public void yellowLowAbility(List<Card> cards)
	{
		_deck.YellowLowAbility(cards);
	}


	public void purpleLowAbility()
	{
		_memoryRow._sizeOfSafeSpace += 2;
	}
}
