package cards;

import java.util.Stack;

public final class InitializeDeck 
{
	public static Stack<Card> createFullDeckAsList()
	{
		Stack<Card> deck = new Stack<Card>();
		
		deck.addAll(createRavens());
		deck.addAll(createBlues());
		deck.addAll(createYellows());
		deck.addAll(createPurples());
		deck.addAll(createGreens());
		deck.addAll(createReds());
		
		return deck;
	}

	private static Stack<Card> createGreens()
	{
		Stack<Card> list = new Stack<Card>();
		list.add(new MemoryCard(CardColor.GREEN, new int[][]{{0, 0},{1, 0}}, 1));
		list.add(new MemoryCard(CardColor.GREEN, new int[][]{{1, 0},{0, 0}}, 1));
		
		list.add(new MemoryCard(CardColor.GREEN, new int[][]{{1, 1},{0, 0}}, 2));
		list.add(new MemoryCard(CardColor.GREEN, new int[][]{{0, 0},{1, 1}}, 2));
		
		list.add(new MemoryCard(CardColor.GREEN, new int[][]{{0, 1},{0, 1}}, 3));
		
		list.add(new MemoryCard(CardColor.GREEN, new int[][]{{1, 0},{1, 0}}, 4));
		
		list.add(new MemoryCard(CardColor.GREEN, new int[][]{{0, 1},{1, 1}}, 5));
		
		return list;
	}

	private static Stack<Card> createPurples()
	{
		Stack<Card> list = new Stack<Card>();
		list.add(new MemoryCard(CardColor.PURPLE, new int[][]{{0, 0},{1, 1}}, 1));
		list.add(new MemoryCard(CardColor.PURPLE, new int[][]{{1, 1},{0, 0}}, 1));
		
		list.add(new MemoryCard(CardColor.PURPLE, new int[][]{{1, 0},{1, 0}}, 2));
		list.add(new MemoryCard(CardColor.PURPLE, new int[][]{{0, 0},{1, 1}}, 2));
		
		list.add(new MemoryCard(CardColor.PURPLE, new int[][]{{1, 0},{0, 1}}, 3));
		
		list.add(new MemoryCard(CardColor.PURPLE, new int[][]{{0, 1},{1, 0}}, 4));
		
		list.add(new MemoryCard(CardColor.PURPLE, new int[][]{{0, 1},{0, 1}}, 5));
		
		return list;
	}

	private static Stack<Card> createYellows()
	{
		Stack<Card> list = new Stack<Card>();
		list.add(new MemoryCard(CardColor.YELLOW, new int[][]{{0, 0},{0, 1}}, 1));
		list.add(new MemoryCard(CardColor.YELLOW, new int[][]{{0, 0},{1, 0}}, 1));
		
		list.add(new MemoryCard(CardColor.YELLOW, new int[][]{{1, 1},{0, 0}}, 2));
		list.add(new MemoryCard(CardColor.YELLOW, new int[][]{{0, 0},{1, 1}}, 2));
		
		list.add(new MemoryCard(CardColor.YELLOW, new int[][]{{1, 0},{0, 1}}, 3));
		
		list.add(new MemoryCard(CardColor.YELLOW, new int[][]{{0, 1},{1, 0}}, 4));
		
		list.add(new MemoryCard(CardColor.YELLOW, new int[][]{{1, 1},{0, 1}}, 5));
		
		return list;
	}

	private static Stack<Card> createReds()
	{
		Stack<Card> list = new Stack<Card>();
		list.add(new MemoryCard(CardColor.RED, new int[][]{{0, 1},{0, 0}}, 1));
		list.add(new MemoryCard(CardColor.RED, new int[][]{{0, 0},{0, 1}}, 1));
		
		list.add(new MemoryCard(CardColor.RED, new int[][]{{0, 1},{1, 0}}, 2));
		list.add(new MemoryCard(CardColor.RED, new int[][]{{1, 0},{0, 1}}, 2));
		
		list.add(new MemoryCard(CardColor.RED, new int[][]{{1, 0},{1, 0}}, 3));
		
		list.add(new MemoryCard(CardColor.RED, new int[][]{{0, 1},{0, 1}}, 4));
		
		list.add(new MemoryCard(CardColor.RED, new int[][]{{1, 1},{1, 0}}, 5));
		
		return list;
	}

	private static Stack<Card> createBlues() 
	{
		Stack<Card> list = new Stack<Card>();
		list.add(new MemoryCard(CardColor.BLUE, new int[][]{{1, 0},{0, 0}}, 1));
		list.add(new MemoryCard(CardColor.BLUE, new int[][]{{0, 1},{0, 0}}, 1));
		
		list.add(new MemoryCard(CardColor.BLUE, new int[][]{{0, 1},{1, 0}}, 2));
		list.add(new MemoryCard(CardColor.BLUE, new int[][]{{1, 0},{0, 1}}, 2));
		
		list.add(new MemoryCard(CardColor.BLUE, new int[][]{{0, 0},{1, 1}}, 3));
		
		list.add(new MemoryCard(CardColor.BLUE, new int[][]{{1, 1},{0, 0}}, 4));
		
		list.add(new MemoryCard(CardColor.BLUE, new int[][]{{1, 0},{1, 1}}, 5));
		
		return list;
	}

	private static Stack<Card> createRavens() 
	{
		Stack<Card> list = new Stack<Card>();
		for(CardColor e : CardColor.values())
			list.add(new Raven(e));
		
		return list;
	}
	
}
