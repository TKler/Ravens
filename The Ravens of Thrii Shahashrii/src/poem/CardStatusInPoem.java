package poem;

/**
 * There are 4 stati for cards in a Poem
 * Unrevealed can only apply to the 4 starting cards before they are revealed
 * Added Cards are Open, so are starting cards that are revealed through relieving.
 * burned cards revealed starting cards that were not relieved but instead show due to cards of the same color played besides them
 * USED cards are cards which abilities were used.
 * @author TKler
 *
 */


public enum CardStatusInPoem 
{
	UNREVEALED, USED, BURNED, USABLE
}