/**
 * 
 */
package net.landarzar.game.werewolf.model.actions;

import java.util.Hashtable;

import net.landarzar.game.werewolf.WerewolfGame;
import net.landarzar.game.werewolf.model.Action;
import net.landarzar.game.werewolf.model.Group;
import net.landarzar.game.werewolf.model.Player;

/**
 * @author Kai Sauerwald
 *
 */
public class KillChoice extends Action
{
	/**
	 * 
	 */
	public KillChoice()
	{
		super.name = "KillChoice";
	}
	
	public Group choiceGroup;
	public Player actor;
	public Player choice;
	
	/* (non-Javadoc)
	 * @see net.landarzar.game.werewolf.model.Action#perform(net.landarzar.game.werewolf.WerewolfGame)
	 */
	@Override
	public void perform(WerewolfGame game)
	{
		if(game.choices.get(actor.role.group) == null)
			game.choices.put(actor.role.group, new Hashtable<>());
		Hashtable<Player, Integer> ht = game.choices.get(actor.role.group);
		int i = ht.get(actor);
		ht.put(actor, i + 1);
	} 
			
}
