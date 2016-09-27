/**
 * 
 */
package net.landarzar.game.werewolf.model;

/**
 * Repräsentiert einen Menschlichen Spielteinehmer
 * 
 * @author Kai Sauerwald
 *
 */
public class Player
{
	/***
	 * Ein Identifier für den Spieler (sollte über alle Spiele gleich sein, für
	 * die stats)
	 */
	public String id;

	/***
	 * Die Rolle des Spielers
	 */
	public Role role;

	// Zustand der Spieler:

	/***
	 * Gibt an ob der Spieler noch lebt
	 */
	public boolean isAlive = true;
	
	/***
	 * Gibt an ob der Spieler gewonnen hat
	 */
	public boolean hasWon = false;
	
	/**
	 * Variable um Wahlen des Spieler zu hinterlegen
	 */
	public Player choice = null;
}
