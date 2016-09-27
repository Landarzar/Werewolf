/**
 * 
 */
package net.landarzar.game.werewolf.model;

import net.landarzar.game.werewolf.WerewolfGame;

/**
 * Superklasse für Spieleraktionen
 * @author Kai Sauerwald
 *
 */
public abstract class Action
{
	public String name;
	
	public abstract void perform(WerewolfGame game);
}
