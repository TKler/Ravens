package atman;

import java.util.ArrayList;

import cards.CardColor;
import cards.MemoryCard;
import players.Game;

public class Atman implements AtmanInterface
{
	
	ArrayList<CardInAtman> _cardsInAtman;
	Game _game;
	RelieveChecker _relieve;
	SplitChecker _split;
	
	boolean _flagGreenHigh, _flagBlueHigh = false;
	CardInAtman _blueHighChangedCard;

	
	public Atman(Game game)
	{
		_split = new SplitChecker();
		_relieve = new RelieveChecker();
		_cardsInAtman = new ArrayList<CardInAtman>();
		_game = game;
	}
	
	/**
	 * @param
	 * Card card the card to be placed
	 * Corner ... the corners it is placed upon, if there is a card, the corresponding corner, if there is no card null
	 * @return false if the card can't be placed there.
	 * THIS IS A NONDEFINITIV CHECK. YOU CAN PROBABLY STILL PLACE THE CARD WEIRD IF YOU TRY
	 * @assert the four corners are adjacent and in the right order etc except greenLowAbility
	 */
	public boolean placeCard(MemoryCard card, Corner upperLeft, Corner upperRight, Corner lowerLeft, Corner lowerRight) 
	{
		if(!isPlacable(card, upperLeft, upperRight, lowerLeft, lowerRight))
			return false;
			
		CardInAtman newCard = new CardInAtman(card, upperLeft, upperRight, lowerLeft, lowerRight);
		_cardsInAtman.add(newCard);
		
		if(checkForRelievingMemories(newCard))
			_game.relievingMemory(newCard.getCard().getColor());
			
		if(splitPossiblethroughAdd(createList(upperLeft, upperRight, lowerLeft, lowerRight)))
		{
			checkForActualSplit();
		}
		
		if(_flagBlueHigh)
			_blueHighChangedCard.getCard().blueHighAbilityRestoreColor();
		
		return true;
	}
	
	private boolean checkForRelievingMemories(CardInAtman newCard)
	{
		return _relieve.isRelieve(newCard);
	}
	
	private void checkForActualSplit()
	{
		while(_split.checkForSplit(_cardsInAtman))
		{
			System.out.println("This should not happen");
			handleSplit(_split.getCC());
		}
	}
	
	/**
	 * Queries the GUI for the player to choose which Atman (cc) to keep
	 * @param theCCs
	 */
	private void handleSplit(ArrayList<ArrayList<CardInAtman>> theCCs) 
	{
		int groupToKeep = _game.atmanSplitOccured(theCCs);
		
		for(ArrayList<CardInAtman> list : theCCs)
		{
			_cardsInAtman.removeAll(list);
		}
		_cardsInAtman.addAll(theCCs.get(groupToKeep));
	}

	/**
	 *	A split can only occur if one of the cards, the new one is placed upon, goes invis.
	 */
	private boolean splitPossiblethroughAdd(ArrayList<Corner> corners) 
	{
		for(Corner c : corners)
		{
			if(checkIfLowerCardGotInvis(c))
				return true;
		}
		return false;
	}

	private boolean checkIfLowerCardGotInvis(Corner corner) 
	{
		if(corner != null)
			if(!corner.isCardVisible())
				return true;
		return false;
	}

	/**
	 * checks for the three criteria to be fulfilled to place the card.
	 * a) the card is placed on other cards
	 * b) the card is not placed directly above another fully visible card
	 * c) the shading fits and at least one shaded area overlaps
	 */
	private boolean isPlacable(MemoryCard card, Corner upperLeft, Corner upperRight, Corner lowerLeft, Corner lowerRight) 
	{
		if(_flagGreenHigh)
		{
			_flagGreenHigh = false;
			return true;
			//TODO osprey rules question: what are the grid rules/which checks need to be done
		}
		
		if(!checkIfCardIsPlacedOnTop(createList(upperLeft, upperRight, lowerLeft, lowerRight)))
			return false;
		
		if(checkIfCardIsPlacedFullyUponAnother(createList(upperLeft, upperRight, lowerLeft, lowerRight)))
			return false;
		
		return shadingFitAndOverlaps(card, upperLeft, upperRight, lowerLeft, lowerRight);
	}

	/**
	 *  when someone knows a better way to do this, let me know -.-
	 *  A place is valid if all 4 corners either lay above null or their own shaded status and at least one of those is shaded
	 */
	private boolean shadingFitAndOverlaps(MemoryCard card, Corner upperLeft, Corner upperRight, Corner lowerLeft, Corner lowerRight)
	{
		boolean shadedOverlap = false;
		
		if(upperLeft != null)
		{
			if(upperLeft.isShaded() == card.isUpperLeftShaded())
			{
				if(upperLeft.isShaded())
					shadedOverlap = true;
			}
			else
				return false;
		}
		
		if(upperRight != null)
		{
			if(upperRight.isShaded() == card.isUpperRightShaded())
			{
				if(upperRight.isShaded())
					shadedOverlap = true;
			}
			else
				return false;
		}
		
		if(lowerLeft != null)
		{
			if(lowerLeft.isShaded() == card.isLowerLeftShaded())
			{
				if(lowerLeft.isShaded())
					shadedOverlap = true;
			}
			else
				return false;
		}
		
		if(lowerRight != null)
		{
			if(lowerRight.isShaded() == card.isLowerRightShaded())
			{
				if(lowerRight.isShaded())
					shadedOverlap = true;
			}
			else
				return false;
		}
		
		if(shadedOverlap)
			return true;
		
		return false;
	}
	/**
	 * A Card is fully placed upon another if all for corners have the same parent.
	 * This can't be the case if any is null.
	 */
	private boolean checkIfCardIsPlacedFullyUponAnother(ArrayList<Corner> corners)
	{
		
		for(int i = 0; i < 4; i++)
		{
			if(corners.get(i) == null)
				return false;
		}
		
		CardInAtman upperLeft = corners.get(0).getParentCard();
		for(int i = 1; i < 4; i++)
		{
			if(!corners.get(i).getParentCard().equals(upperLeft))
				return false;
		}
		return true;
	}
	

	private boolean checkIfCardIsPlacedOnTop(ArrayList<Corner> corners)
	{
		for(Corner c : corners)
		{
			if(c != null)
				if(c.getAbove() != null)
					return false;
		}
		return true;
	}
	
	private ArrayList<Corner> createList(Corner upperLeft, Corner upperRight, Corner lowerLeft, Corner lowerRight)
	{
		ArrayList<Corner> corners = new ArrayList<Corner>(4);
		corners.add(upperLeft);
		corners.add(upperRight);
		corners.add(lowerLeft);
		corners.add(lowerRight);
		
		return corners;
	}
	
	public void removeCard(CardInAtman card) 
	{
		// delete from the linked lists
		card.removeThisCard();
		// delete from the list of cards in the Atman
		_cardsInAtman.remove(card);
		
//		if(splitThroughRemovePossible());
//		{
			checkForActualSplit();
//		}
	}

//	//to do check if this comment is actually true after implementation^^
//	/**
//	 * checks whether it is possible that a split occurs when you remove a card
//	 * this is only the case if there is no path between the 4 corners to each other, since this is similar in cost as cc we 
//	 * only check for connection in the depth of 2
//	 * @return true if a split is possible
//	 */
//	private boolean splitThroughRemovePossible()
//	{
//		//  Auto-generated method stub
//		return false;
//	}
	
	public boolean isEmpty() 
	{
		return _cardsInAtman.isEmpty();
	}
	
	public void addStartCard(MemoryCard card)
	{
		_cardsInAtman.add(new CardInAtman(card, null, null, null, null));
	}
	
	/**
	 * removes the encapsulation for the atmans cards 
	 * @return and returns the remaining cards
	 */
	public ArrayList<MemoryCard> endDream()
	{
		ArrayList<MemoryCard> toDiscard = new ArrayList<MemoryCard>();
		for(CardInAtman atmanCard : _cardsInAtman)
		{
			toDiscard.add(atmanCard.getCard());
		}
		return toDiscard;
	}
	public ArrayList<CardInAtman> getCards()
	{
		return _cardsInAtman;
	}

	public void greenHighAbility()
	{
		_flagGreenHigh = true;
	}

	public void blueHighAbility(CardInAtman card, CardColor color)
	{
		_cardsInAtman.get(_cardsInAtman.indexOf(card)).getCard().blueHighAbility(color);
		_blueHighChangedCard = card;
	}

	public void blueLowAbility(boolean trueForPlusOne)
	{
		_relieve.blueLowAbility(trueForPlusOne);
	}
}
