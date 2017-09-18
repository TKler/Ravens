package players;

import java.util.ArrayList;

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
	MemoryRow _memory;
	RavenRow _ravenRow;
	Deck _deck;
	
	
	public Feth(Game game) 
	{
		_game = game;
		_memory = new MemoryRow();
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
		_memory.updateNmbOfRavens(_ravenRow.getNumberOfActiveRavens());
	}
	
	public void drawCard() 
	{
		Card card = _deck.drawCard();
		
		if(card == null)
			_game.gameLost();
		
		_memory.addCard(card);
	}
	
	/*
	 * At the end of drawing the ravens get discarded/moved into the ravenrow depending on their position
	 */
	public void endDrawing()
	{
		_game.discardCards(_memory.getDiscardedRavens());
		_ravenRow.addRaven(_memory.getNewRavens());
	}
	

	public void discardRemainingMemoryRow() 
	{
		_ravenRow.discardCards(_memory.discardRemaining());
		_game.discardCards(_memory.discardRemaining());
		_memory.reset();
	}
	
	
	public void relieveMemory(CardColor color)
	{
		_ravenRow.relieve(color);
	}
	
	
	public boolean useCard(Card card, int xposition, int yposition) 
	{
		// TODO Card Abilities
		return false;
	}
	
	/**
	 * The way discarding at the end of the dream works is, that there are 3 sources of cards: atman, poem and discard pile
	 * each may be subjected to the ravens which steal memories (before eating them)
	 * afterwards the cards are shuffled back into the deck
	 * Then the ravenRow needs to be reset/feasting
	 * @param discardPile
	 * @param atmanCards
	 * @param poemCards
	 */
	public void endDream(ArrayList<Card> discardPile, ArrayList<MemoryCard> atmanCards, ArrayList<MemoryCard> poemCards)
	{
		_deck.shuffleBackIn(_ravenRow.discardCards(atmanCards));
		_deck.shuffleBackIn(_ravenRow.discardCards(poemCards));
		ArrayList<MemoryCard> discardPileWithoutRavens = new ArrayList<MemoryCard>();
		ArrayList<Raven> discardedRavens = new ArrayList<Raven>();
		for(Card card : discardPile)
		{
			if(card.isRaven())
				discardedRavens.add((Raven) card);
			else
				discardPileWithoutRavens.add((MemoryCard) card);
		}
		
		_deck.shuffleBackIn(_ravenRow.discardCards(discardPileWithoutRavens));
		_deck.shuffleBackIn(discardedRavens);
		_ravenRow.dreamEnd();
	}
	
	public MemoryCard getHeartCardForAtman()
	{
		return _deck.getNot5MemoryCard();
	}
	
	public ArrayList<MemoryCard> getHeartCardsForRen()
	{
		startTurn();
		return _deck.get4HeartCards();
	}


	public boolean placeCardInTheAtman(MemoryCard card, Corner upperLeft, Corner upperRight, Corner lowerLeft,
			Corner lowerRight)
	{
		return _game.placeCardInTheAtman(card, lowerRight, lowerRight, lowerRight, lowerRight);
	}
		
}
