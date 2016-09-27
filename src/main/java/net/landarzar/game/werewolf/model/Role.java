/**
 * 
 */
package net.landarzar.game.werewolf.model;

import java.util.LinkedList;
import java.util.List;

import net.landarzar.function.TriConsumer;
import net.landarzar.game.werewolf.WerewolfGame;

/**
 * @author Kai Sauerwald
 *
 */
public abstract class Role
{
	/***
	 * Name der Rolle;
	 */
	public String name;

	/***
	 * Gibt an ob die Rolle wärend der Nacht aktiv ist
	 */
	public boolean isActiveAtNight = false;

	/***
	 * Gibt die Priorität an, bei der die Rolle in der Nacht ausgeführt wird.
	 * Niedriegste zuerst.
	 */
	public int nightPriority = Integer.MAX_VALUE;
	
	/***
	 * Die Gruppe zu der die Rolle gehört
	 */
	public Group group;
	
	/***
	 * Verifiziert ob die Aktionen eine Spielers korrekt sind
	 * @param game
	 * @param actions
	 * @param consumer
	 * @return
	 */
	public abstract List<Action> verifyActions(WerewolfGame game, Player player, List<Action> actions, TriConsumer<WerewolfGame, Action,String> consumer);
	
	public abstract boolean hasWon(WerewolfGame game, Player player);

	/***
	 * Wird aufgerufen wenn ein Spieler gekillt wird von einem anderen Spieler
	 * 
	 * @param game
	 *            Das Spiel
	 * @param killer
	 *            Spieler der den anderen getötet hat (null wenn es die Umwelt
	 *            war)
	 * @param target
	 *            Das Ziel
	 */
	public void onGetKilled(WerewolfGame game, Player killer, Player target)
	{
	}

	public void onBeginDay(WerewolfGame game, Player player)
	{
	}

	public void onEndDay(WerewolfGame game, Player player)
	{
	}

	public void onBeginNight(WerewolfGame game, Player player)
	{
	}

	public void onEndNight(WerewolfGame game, Player player)
	{
	}
}
