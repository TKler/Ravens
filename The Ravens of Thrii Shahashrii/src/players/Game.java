package players;
import java.util.ArrayList;

import _GUIInterface.GameLogicToGUI;
import atman.Atman;
import atman.CardInAtman;
import atman.Corner;
import cards.Card;
import cards.CardColor;
import cards.MemoryCard;

/**
 * The game manages the Atman, the discardPile and Feth and Ren. 
 * Furthermore manages if the game is lost/won.
 * And holds access to the gui together with the atman (tad smelly)
 **/

public class Game 
{

	Ren _ren;
	Feth _feth;
	
	Atman _atman;
	DiscardPile _discardPile;
	int _dream;
	
	GameLogicToGUI _gui;
	
	public Game (GameLogicToGUI gui)
	{
		_gui = gui;
		
		_ren = new Ren(this);
		_feth = new Feth(this);
		
		_atman = new Atman(this);
		_discardPile = new DiscardPile();
		_dream = 1;
	}
	
	public boolean placeCardInTheAtman(MemoryCard card, Corner upperLeft, Corner upperRight, Corner lowerLeft,
			Corner lowerRight)
	{
		return _atman.placeCard(card, upperLeft, upperRight, lowerLeft, lowerRight);
	}
	
	public void renTakesCardFromAtman(CardInAtman card)
	{
		_ren.addCardToThePoem(card.getCard());
		_atman.removeCard(card);
		if(_atman.isEmpty())
			gameLost();	
	}
	
	public void relievingMemory(CardColor color)
	{
		_feth.relieveMemory(color);
		int indexOfCardToRelieve = _gui.askRenIfWhichSheHasAHeartCardToReveal(_ren.getHeartCards(), color);
		if(indexOfCardToRelieve != -1)
			_ren.relieveHeartCard(indexOfCardToRelieve);
	}
	
	public void gameLost() 
	{
		_gui.gameLost();
	}
	
	public void gameWon() 
	{
		_gui.gameWon();
	}

	
	public void startDream()
	{
		_atman.addStartCard(_feth.getHeartCardForAtman());
		
		_ren.createHeart(_gui.renHeartCardOrder(_feth.getHeartCardsForRen()));
	}
	
	public void endDream()
	{
		_feth.endDream(_discardPile.endDream(), _atman.endDream(), _ren.endDream());
	}

	public void discardCard(MemoryCard card) 
	{
		ArrayList<MemoryCard> list = new ArrayList<MemoryCard>(1);
		list.add(card);
		discardCards(list);
	}
	
	public void discardCards(ArrayList<? extends Card> remaining) 
	{
		_discardPile.addAll(remaining);
	}

	public int atmanSplitOccured(ArrayList<ArrayList<CardInAtman>> arrayList)
	{
		return _gui.atmanSplitOccured(arrayList);
	}
}
