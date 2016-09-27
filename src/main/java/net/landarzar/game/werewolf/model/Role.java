/**
 * 
 */
package net.landarzar.game.werewolf.model;

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
	protected abstract void onGetKilled(WerewolfGame game, Player killer, Player target);

	protected void performNightRole(WerewolfGame game, Player player)
	{
	}
}
