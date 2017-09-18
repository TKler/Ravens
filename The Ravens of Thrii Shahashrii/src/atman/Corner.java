package atman;

/*
 * A Corner is similar to a linked List.
 * It nows the Corner of a Card above and below, if there is no Card the Cornerreference is null.
 */
public class Corner 
{
	Corner _above, _below;
	CardInAtman _parent;
	boolean _isShaded;
	
	public Corner(Corner below, CardInAtman parent, boolean isShaded)
	{
		_below = below;
		_above = null;
		_parent = parent;
		_isShaded = isShaded;
	}
	
	public void changeBelow(Corner below)
	{
		if(below == null && _below != null)
			_parent.cornerIsNowLowest();
		if(below != null && _below == null)
			_parent.cornerIsNolongerLowest();
		
		_below = below;
	}
	
	public Corner getBelow()
	{
		return _below;
	}
	
	public void changeAbove(Corner above)
	{
		if(above == null && _above != null)
			_parent.cornerGotVisible();
		if(above != null && _above == null)
			_parent.cornerWentInvis();
		
		_above = above;
	}
	
	public Corner getAbove()
	{
		return _above;
	}
	
	public CardInAtman getParentCard()
	{
		return _parent;
	}
	
	public boolean isCardLowest()
	{
		return _parent.isLowest();
	}
	
	public boolean isCardVisible()
	{
		return _parent.isVisible();
	}

	public boolean isShaded() 
	{
		return _isShaded;
	}

	public void removeThisCard() 
	{
		if(_above != null)
			_above.changeBelow(_below);
		if(_below != null)
			_below.changeAbove(_above);
	}
	
}
