/**
 * 
 */
package net.landarzar.game.werewolf;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.stream.Collectors;

import net.landarzar.game.werewolf.model.Action;
import net.landarzar.game.werewolf.model.Group;
import net.landarzar.game.werewolf.model.Player;

/**
 * Ein Werewolf Spiel
 * 
 * @author Kai Sauerwald
 *
 */
public abstract class WerewolfGame
{
	/***
	 * Die Liste an Spielern
	 */
	public LinkedList<Player> players = new LinkedList<>();

	/***
	 * Nächste Aktion
	 */
	public LinkedList<Action> nextActions = new LinkedList<>();

	/***
	 * Historie der Aktionen
	 */
	public LinkedList<Action> history = new LinkedList<>();
	
	/***
	 * Die Auswahlen dieser Phase
	 */
	public Hashtable<Group, Hashtable<Player, Integer>> choices = new Hashtable<>();

	/***
	 * Ist es gerade Tag
	 */
	boolean day = false;
	/***
	 * Gibt an ob es zwischen Tag oder Nacht ist.
	 */
	boolean betweenTheDN = true;

	/***
	 * Gibt an ob es gerade Tag ist
	 * 
	 * @return
	 */
	public boolean isInDay()
	{
		return day && !betweenTheDN;
	}

	/**
	 * Gibt an ob es gerade Nacht ist
	 * 
	 * @return
	 */
	public boolean isInNight()
	{
		return day && !betweenTheDN;
	}

	/***
	 * Startet den Tagmodus
	 * 
	 * @throws Exception
	 */
	protected void startDay() throws Exception
	{
		if (!day || !betweenTheDN)
			throw new Exception("Kann nicht den Tag starten, wenn es noch Nacht ist.");
		betweenTheDN = false;
	}

	/***
	 * Wird aufgerufen wenn der Tag startet
	 * 
	 * @param game
	 */
	protected abstract void onStartDay(WerewolfGame game);

	/***
	 * Beendet den Tagmodus
	 * 
	 * @throws Exception
	 *             Wenn es nicht Tag ist.
	 */
	protected void finishDay() throws Exception
	{
		if (!day || betweenTheDN)
			throw new Exception("Kann nicht den Tag beenden, wenn es Nacht ist.");
		day = false;
		betweenTheDN = true;
	}

	/***
	 * Wird aufgerufen wenn der Tag endet
	 * 
	 * @param game
	 */
	protected abstract void onFinishDay(WerewolfGame game, LinkedList<Action> actions);

	/***
	 * Startet den Nachtmodus
	 * 
	 * @throws Exception
	 */
	protected void startNight() throws Exception
	{
		if (day || !betweenTheDN)
			throw new Exception("Kann nicht die Nacht starten, wenn es noch Tag ist.");
		betweenTheDN = false;
	}

	/***
	 * Wird aufgerufen wenn die Nacht startet
	 * 
	 * @param game
	 */
	protected abstract void onStartNight(WerewolfGame game);

	/***
	 * Beendet den Nachtmodus
	 * 
	 * @throws Exception
	 */
	protected void finishNight() throws Exception
	{
		if (day || betweenTheDN)
			throw new Exception("Kann nicht die Nacht beenden, wenn es Tag ist.");
		day = true;
		betweenTheDN = true;

		LinkedList<Action> puffer = new LinkedList<>();
		
		for (Player player : players) {
			LinkedList<?> ll = new LinkedList<>();
			player.role.verifyActions(this, player, nextActions.stream().filter((a) -> a.actor == player).collect(Collectors.toList()), this::onInvalidAction);
			
			puffer.removeAll(ll);
		}

		for (Action action : nextActions) {
			puffer.add(action);
			
		}

		onFinishNight(this, puffer);
	}

	/***
	 * Wird aufgerufen wenn die Nacht endet
	 * 
	 * @param game
	 */
	protected abstract void onFinishNight(WerewolfGame game, LinkedList<Action> actions);

	/***
	 * Wird aufgerufen wenn eine Aktion nicht valide ist.
	 * 
	 * @param game
	 */
	protected abstract void onInvalidAction(WerewolfGame game, Action action, String reason);

	/***
	 * Wird aufgerufen wenn eine Aktion nicht bekannt ist.
	 * 
	 * @param game
	 */
	protected abstract void onUnknownAction(WerewolfGame game, Action action);
}
