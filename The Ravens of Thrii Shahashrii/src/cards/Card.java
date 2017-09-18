package cards;

/*
 * Cards can be either Ravens or normal Playing Cards
 * Both have one of five Colors, described in CardColor
 * Nonravens are partially shaded in some quadrants, ravens are always full, but dont care playwise.
 */
public abstract class Card 
{
	CardColor _color;
	
	public CardColor getColor() 
	{
		return _color;
	}
	
	public abstract boolean isRaven();
}
