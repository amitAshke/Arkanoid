package game;

import game.levels.SetsOfLevelsReader;
import visuals.AnimationRunner;
import visuals.MenuAnimation;
import visuals.HighScoresAnimation;
import visuals.Menu;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

/**
 * This is the main class that's used to run the game.
 */
public class Ass6Game {
    /**
     * The function sets the options and submenus in the main menu of the game.
     *
     * @param menu                a "MenuAnimation" variable that represents the main menu.
     * @param runner              a "AnimationRunner" variable to be added to the submenus.
     * @param sensor              a "KeyboardSensor" variable to be added to the submenus.
     * @param args                either empty or containing the path to the level sets file.
     * @param highScoresAnimation a "HighscoreAnimation" variable to be added to the main menu's option.
     * @param table               a "HighScoresTable" variable to be added to the submenus.
     * @param gui                 the game's GUI.
     */
    public static void setMainMenu(MenuAnimation menu, AnimationRunner runner, KeyboardSensor sensor, String[] args,
                                   HighScoresAnimation highScoresAnimation, HighScoresTable table, GUI gui) {
        MenuAnimation startGameSubMenu = new MenuAnimation("Start Game", sensor, runner);
        try {
            Reader reader;
            if (args.length == 0) {
                reader = new InputStreamReader(ClassLoader.getSystemClassLoader().getResourceAsStream(
                        "definitions/level_sets.txt"));
            } else {
                reader = new InputStreamReader(ClassLoader.getSystemClassLoader().getResourceAsStream(args[0]));
            }
            SetsOfLevelsReader setsOfLevelsReader = new SetsOfLevelsReader();
            List<Option> starGameSubOptions = setsOfLevelsReader.levelSetsReader(reader, runner, sensor, table);
            for (int index = 0; index < starGameSubOptions.size(); index++) {
                startGameSubMenu.addSelection(starGameSubOptions.get(index));
            }
        } catch (Exception error) {
            System.out.println("Error during receiving the level sets.");
        }
        menu.addSubMenu("s", "Start game", startGameSubMenu);
        menu.addSelection("h", "High Score", new ShowHiScoreTask(runner, highScoresAnimation, sensor));
        menu.addSelection("q", "Quit", new QuitTask(gui));
    }

    /**
     * The main function that runs the game.
     *
     * @param args no use.
     */
    public static void main(String[] args) {
        final int windowWidth = 800;
        final int windowHeight = 600;
        HighScoresTable highscores = HighScoresTable.loadFromFile(new File("highscore"));
        GUI gui = new GUI("Arkanoid", windowWidth, windowHeight);
        KeyboardSensor keyboardSensor = gui.getKeyboardSensor();
        AnimationRunner animationRunner = new AnimationRunner(gui);
        MenuAnimation mainMenu = new MenuAnimation("Arkanoid", keyboardSensor, animationRunner);
        HighScoresAnimation highScoresAnimation = new HighScoresAnimation(highscores, keyboardSensor);
        try {
            highscores.save(new File("highscore"));
        } catch (IOException e) {
            System.out.println("An error occurred while saving2!");
        }
        setMainMenu(mainMenu, animationRunner, keyboardSensor, args, highScoresAnimation, highscores, gui);
        while (true) {
            animationRunner.run(60, mainMenu, keyboardSensor, null);
            if (mainMenu.getStatus() instanceof Menu) {
                Object newMenu = mainMenu.getStatus();
                animationRunner.run(60, (Menu) newMenu, keyboardSensor, null);
                ((Task) ((Menu) newMenu).getStatus()).run();
                ((MenuAnimation) newMenu).reset();
                mainMenu.reset();
            } else if (mainMenu.getStatus() instanceof Task) {
                ((Task) mainMenu.getStatus()).run();
                mainMenu.reset();
            }
        }
    }
}
