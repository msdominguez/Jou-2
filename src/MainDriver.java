/*
 * TO-DO:
 * - joptionpane extended class
 * - add bgs/fonts to jdialogs
 * - add new font themes
 * - add more settings
 * - make entries save in the right folder (organized?)
 * - redo home page layout with this many btns
 * - can redesign every panel to be consistent in btn placement
 * - help should include help for every individual panel too
 * - more features!
 * - FIX:
 *  - fonts
 *  - make edit btn only appear after u have an entry loaded?
 *   - dont want to hide things from user/but also wanna clean up?
 *  - label for which entry ur on needs to look better
 * 
 */

public class MainDriver {
	public static void main(String[] args) {
		MainPanel view = new MainPanel();
		new MainController(view);
	}
}
