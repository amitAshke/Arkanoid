package game;

import biuoop.GUI;

/**
 * The task of quiting the game.
 */
public class QuitTask implements Task {
    private GUI gui;

    /**
     * A constructor function.
     *
     * @param gui1 the game's GUI.
     */
    public QuitTask(GUI gui1) {
        this.gui = gui1;
    }

    /**
     * The function quit the game by closing the gui.
     *
     * @return null;
     */
    public Void run() {
        this.gui.close();
        return null;
    }
}
