package _GUIInterface;


import atman.CardInAtman;
import atman.Corner;
import cards.MemoryCard;
import players.*;

public class GUIToGameLogic implements GUIToGameLogicInterface
{
	Game _game;
	Feth _feth;
	Ren _ren;
	
	public GUIToGameLogic(Game game, Feth feth, Ren ren)
	{
		_game = game;
		_feth = feth;
		_ren = ren;
	}

	@Override
	public void fethDrawsACard()
	{
		_feth.drawCard();
	}

	@Override
	public void fethFinishesDrawing()
	{
		_feth.endDrawing();
	}

	@Override
	public boolean placeCardInTheAtman(MemoryCard card, Corner upperLeft, Corner upperRight, Corner lowerLeft,
			Corner lowerRight)
	{
		return _feth.placeCardInTheAtman(card, upperLeft, upperRight, lowerLeft, lowerRight);
	}

	@Override
	public void fethDiscardsRemainingMemoryRow()
	{
		_feth.discardRemainingMemoryRow();
	}

	@Override
	public void renTakesCardFromAtman(CardInAtman card)
	{
		_game.renTakesCardFromAtman(card);
	}
	
	public void useCard(MemoryCard card, int xposition, int yposition)
	{
		_ren.useCard(card, xposition, yposition);
	}
}
