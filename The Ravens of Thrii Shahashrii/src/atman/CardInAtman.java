package atman;

import java.util.ArrayList;

import cards.CardColor;
import cards.MemoryCard;

public class CardInAtman 
{

	// The corresponding MemoryCard
	MemoryCard _card;
	// A Card is visible if all 4 corners are visible and _isVisible == 4;
	// A Card is the lowest if all corners hold null below and _isLowest == 0;
	int _isVisible, _isLowest = 0; 
	// the 4 corners with additional information
	Corner _upperLeft, _upperRight, _lowerLeft, _lowerRight;
	//The corners this card
	ArrayList<Corner> _cornersAsList;
	
	public CardInAtman(MemoryCard card, Corner upperLeft, Corner upperRight, Corner lowerLeft, Corner lowerRight) 
	{
		_isVisible = 4;
		_card = card;
		
		_upperLeft = new Corner(upperLeft, this, card.isUpperLeftShaded());
		if(upperLeft != null)
			upperLeft.changeAbove(_upperLeft);
		
		_upperRight = new Corner(upperRight, this, card.isUpperRightShaded());
		if(upperRight != null)
			upperRight.changeAbove(_upperRight);
		
		_lowerLeft = new Corner(lowerLeft, this, card.isLowerLeftShaded());
		if(lowerLeft != null)
			lowerLeft.changeAbove(_lowerLeft);
		
		_lowerRight = new Corner(lowerRight, this, card.isLowerRightShaded());
		if(lowerRight != null)
			lowerRight.changeAbove(_lowerRight);

		createList();
		
		checkLowest();
	}


	private void createList() 
	{
		_cornersAsList = new ArrayList<Corner>();
		_cornersAsList.add(_upperLeft);
		_cornersAsList.add(_upperRight);
		_cornersAsList.add(_lowerLeft);
		_cornersAsList.add(_lowerRight);
	}


	public void cornerWentInvis() 
	{
		_isVisible--;
	}

	public void cornerGotVisible() 
	{
		_isVisible++;
	}

	public void cornerIsNolongerLowest() 
	{
		_isLowest++;
	}

	public void cornerIsNowLowest() 
	{
		_isLowest--;		
	}

	public CardColor getColor() 
	{
		return _card.getColor();
	}
	
	public boolean isVisible()
	{
		if(_isVisible == 0)
			return false;
		return true;
	}
	
	public boolean isLowest()
	{
		if(_isLowest == 0)
			return true;
		return false;
	}
	
	private void checkLowest() 
	{
		for(Corner c : _cornersAsList)
		{
			if(c.getBelow() == null)
				_isLowest++;
		}
	}


	public MemoryCard getCard() 
	{
		return _card;
	}


	public void removeThisCard() 
	{
		for(Corner c : _cornersAsList)
		{
			if(c!= null)
				c.removeThisCard();
		}
	}


	public ArrayList<Corner> getCorners() 
	{
		return _cornersAsList;
	}
}
