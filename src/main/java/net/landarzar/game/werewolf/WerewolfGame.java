/**
 * 
 */
package net.landarzar.game.werewolf;

import java.util.LinkedList;

import net.landarzar.game.werewolf.model.Player;

/**
 * Ein Werewolf Spiel
 * 
 * @author Kai Sauerwald
 *
 */
public abstract class WerewolfGame
{
	LinkedList<Player> player = new LinkedList<>();

	/***
	 * Ist es gerade Tag
	 */
	boolean day = false;
	/***
	 * Gibt an ob es zwischen Tag oder Nacht ist.
	 */
	boolean betweenTheDN = true;

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
	 * @param game
	 */
	protected abstract void onFinishDay(WerewolfGame game);

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
	 * @param game
	 */
	protected abstract void onStartNight(WerewolfGame game);

	/***
	 * Beendet den Nachtmodus
	 * @throws Exception 
	 */
	protected void finishNight() throws Exception
	{
		if (day || betweenTheDN)
			throw new Exception("Kann nicht die Nacht beenden, wenn es Tag ist.");
		day = true;
		betweenTheDN = true;
	}
	
	/***
	 * Wird aufgerufen wenn die Nacht endet
	 * @param game
	 */
	protected abstract void onFinishNight(WerewolfGame game);
}
