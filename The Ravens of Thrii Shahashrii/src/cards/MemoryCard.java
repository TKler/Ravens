package cards;

public class MemoryCard extends Card
{
	/**
	 * 2 dimensional Array of bool
	 * where true (1) means shaded
	 * [x][y] x is left or right, y is top or bottom
	 */
	boolean[][] _array = new boolean [2][2];
	
	int _value;
	
	Ability _ability;
	CardColor _bufferColor; // needed for blueHighAbility
	
	public MemoryCard(CardColor color, boolean[][] array, int value)
	{
		_array = array;
		_color = color;
		_value = value;
		_ability = checkAbility();
	}
	
	public MemoryCard(CardColor color, int[][] array, int value)
	{
		_array = integerToBooleanHelper(array);
		_color = color;
		_value = value;
		_ability = checkAbility();
	}
	
	/*
	 * 1-3 and 4,5 of each color have the same ability
	 */
	//no idea if you fancy one return at the end or otherwise, i switch and don't care :)
	private Ability checkAbility() 
	{
		if(_value <= 3)
		{
			switch(_color)
			{
			case RED: return Ability.REDLOW;
			case YELLOW: return Ability.YELLOWLOW;
			case GREEN: return Ability.GREENLOW;
			case BLUE: return Ability.BLUELOW;
			case PURPLE: return Ability.PURPLELOW;
			}
		}
		else
		{
			switch(_color)
			{
			case RED: return Ability.REDHIGH;
			case YELLOW: return Ability.YELLOWHIGH;
			case GREEN: return Ability.GREENHIGH;
			case BLUE: return Ability.BLUEHIGH;
			case PURPLE: return Ability.PURPLEHIGH;
			}
		}
		// will never be reached (exeption etc would be appropriate)
		return null;
	}

	
	/**
	 * Little helper method to convert from int[][] to boolean[][]
	 * @param array
	 * @return
	 */
	private boolean[][] integerToBooleanHelper(int[][] array) 
	{
		boolean[][] boolArray = new boolean [2][2];

		int row = 0, line = 0;
		for(int i = 0; i < 4; i++)
		{
			line = i % 2;
			row = i * 1/2;
			if(array[row][line] == 1)
				boolArray[row][line] = true;
			else
				boolArray[row][line] = false;
		}
		
		return boolArray;
	}
	
	
	public boolean isRaven()
	{
		return false;
	}
	
	public boolean isUpperLeftShaded()
	{
		return _array[0][0];
	}
	
	public boolean isUpperRightShaded()
	{
		return _array[1][0];
	}
	
	public boolean isLowerLeftShaded()
	{
		return _array[0][1];
	}
	
	public boolean isLowerRightShaded()
	{
		return _array[1][1];
	}

	public int getValue() 
	{
		return _value;
	}
	
	public Ability getAbility()
	{
		return _ability;
	}

	public void blueHighAbility(CardColor color)
	{
		_bufferColor = _color;
		_color = color;
	}
	
	public void blueHighAbilityRestoreColor()
	{
		_color = _bufferColor;
	}
}
