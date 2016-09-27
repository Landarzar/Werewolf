/**
 * 
 */
package net.landarzar.game.werewolf.model.roles;

import java.util.LinkedList;

import net.landarzar.function.TriConsumer;
import net.landarzar.game.werewolf.WerewolfGame;
import net.landarzar.game.werewolf.model.Action;
import net.landarzar.game.werewolf.model.Group;
import net.landarzar.game.werewolf.model.Player;
import net.landarzar.game.werewolf.model.Role;
import net.landarzar.game.werewolf.model.actions.KillChoice;

/**
 * @author Kai Sauerwald
 *
 */
public class Werewolf extends Role
{

	/**
	 * 
	 */
	public Werewolf()
	{
		super.name = "Werwolf";
		super.isActiveAtNight = true;
		super.nightPriority = 10;
		super.group = Group.Werewolf;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.landarzar.game.werewolf.model.Role#verifyActions(net.landarzar.game.
	 * werewolf.WerewolfGame, java.util.LinkedList,
	 * net.landarzar.function.TriConsumer)
	 */
	@Override
	public LinkedList<Action> verifyActions(WerewolfGame game, Player player, LinkedList<Action> actions, TriConsumer<WerewolfGame, Action, String> onFailure)
	{
		if (!player.isAlive) {
			for (Action action : actions) {
				onFailure.consume(game, action, "Töte Spieler können keine Aktionen ausführen");
			}
			return null;
		}

		LinkedList<Action> dope = new LinkedList<>();

		boolean haschoosen = false;
		for (Action action : actions) {
			if (action instanceof KillChoice) {
				KillChoice kc = (KillChoice) action;
				if (haschoosen) {
					onFailure.consume(game, kc, "Man kann nur einmal wählen");
					continue;
				}

				if (kc.choiceGroup == Group.Villagers && !game.isInDay()) {
					onFailure.consume(game, kc, "Man kann nur eine Dorfbewohnerstimme am Tag abgeben.");
					continue;
				} else if (kc.choiceGroup == Group.Werewolf && !game.isInNight()) {
					onFailure.consume(game, kc, "Man kann als Werewolf nur in der Nacht jemand töten.");
					continue;
				} else if (kc.choiceGroup != Group.Villagers && kc.choiceGroup != Group.Werewolf) {
					onFailure.consume(game, kc, "Man kann nur als Dorfbewohner oder Werewolf abstimmen");
					continue;
				}
				haschoosen = true;
				dope.add(kc);
			}
			else
				onFailure.consume(game, action, "Operation kann ein Werewolf nicht ausführen");
		}

		return dope;
	}

	/* (non-Javadoc)
	 * @see net.landarzar.game.werewolf.model.Role#hasWon(net.landarzar.game.werewolf.WerewolfGame, net.landarzar.game.werewolf.model.Player)
	 */
	@Override
	public boolean hasWon(WerewolfGame game, Player player)
	{
		
		return false;
	}
}
