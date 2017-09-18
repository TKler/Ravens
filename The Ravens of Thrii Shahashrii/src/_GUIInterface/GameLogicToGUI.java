package _GUIInterface;

import java.util.ArrayList;

import atman.CardInAtman;
import cards.CardColor;
import cards.MemoryCard;

public interface GameLogicToGUI 
{
	/**
	 * Informs the GUI that a slpit occured and 
	 * @param arrayList the helper with the possible groups to keep
	 * @return the index of the group to keep
	 */
	public int atmanSplitOccured(ArrayList<ArrayList<CardInAtman>> arrayList);
	
	public void gameLost();
	
	public void gameWon();

	
	public ArrayList<MemoryCard> renHeartCardOrder(ArrayList<MemoryCard> startDreamRen);

	//Obviously we could use some little commuication token if wanted.
	// We could also check and send only valid Cards etc, depends on the guis ideas for representaion and workload
	/**
	 * Called when there is a relieve situation.
	 * Should query Ren all 4 HeartCards though only the correct ones can be chosen
	 * Nothing - -1
	 * @param heartCards list of rens 4 hearcards
	 * @param colorToRelieve the color being relieved
	 * @return 0,1,2,3 or -1 depening on if and which card to relieve
	 */
	public int askRenIfWhichSheHasAHeartCardToReveal(ArrayList<MemoryCard> heartCards, CardColor colorToRelieve);
}
