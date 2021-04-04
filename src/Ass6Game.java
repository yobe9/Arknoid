import biuoop.GUI;
import gamelogic.AnimationRunner;
import gamelogic.DirectHitLevel;
import gamelogic.FinalFourLevel;
import gamelogic.GameFlow;
import gamelogic.Green3Level;
import gamelogic.LevelInformation;
import gamelogic.WideEasyLevel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yoav Berger <yoavbrgr@gmail.com> ID 313268393
 * @since 06/06/2020
 */
public class Ass6Game {
    /**
     * Main function.
     * Create a game object, initializes and runs it
     *
     * @param args main
     */
    public static void main(String[] args) {
        //in case we dont have argument
        if (args.length == 0) {
            //creating gui
            GUI gui = new GUI("Game Time", 800, 600);
            //creating animation runner
            AnimationRunner ar = new AnimationRunner(gui);
            //creating game flow
            GameFlow gameFlow = new GameFlow(ar, gui.getKeyboardSensor());
            //creating the levels list and adding the levels
            List<LevelInformation> levels = new ArrayList<LevelInformation>();
            //creating and adding level 1
            DirectHitLevel levelOne = new DirectHitLevel();
            levels.add(levelOne);
            //creating and adding level 2
            WideEasyLevel levelTwo = new WideEasyLevel();
            levels.add(levelTwo);
            //creating and adding level 3
            Green3Level levelThree = new Green3Level();
            levels.add(levelThree);
            //creating and adding level 4
            FinalFourLevel levelFour = new FinalFourLevel();
            levels.add(levelFour);
            //running the game flow
            gameFlow.runLevels(levels);
            gui.close();
        } else {
            //creating the levels list and adding the levels
            List<LevelInformation> levels = new ArrayList<LevelInformation>();
            //checking which levels the player want
            for (int i = 0; i < args.length; i++) {
                if (args[i].equals("1")) {
                    //creating and adding level 1
                    DirectHitLevel levelOne = new DirectHitLevel();
                    levels.add(levelOne);
                }
                if (args[i].equals("2")) {
                    //creating and adding level 2
                    WideEasyLevel levelTwo = new WideEasyLevel();
                    levels.add(levelTwo);
                }
                if (args[i].equals("3")) {
                    //creating and adding level 3
                    Green3Level levelThree = new Green3Level();
                    levels.add(levelThree);
                }
                if (args[i].equals("4")) {
                    //creating and adding level 4
                    FinalFourLevel levelFour = new FinalFourLevel();
                    levels.add(levelFour);
                }
            }
            //if we have correct arguments run the game
            if (!levels.isEmpty()) {
                //creating gui
                GUI gui = new GUI("Game Time", 800, 600);
                //creating animation runner
                AnimationRunner ar = new AnimationRunner(gui);
                //creating game flow
                GameFlow gameFlow = new GameFlow(ar, gui.getKeyboardSensor());
                //running the game flow
                gameFlow.runLevels(levels);
                gui.close();
            } else {
                //creating gui
                GUI gui = new GUI("Game Time", 800, 600);
                //creating animation runner
                AnimationRunner ar = new AnimationRunner(gui);
                //creating game flow
                GameFlow gameFlow = new GameFlow(ar, gui.getKeyboardSensor());
                //creating and adding level 1
                DirectHitLevel levelOne = new DirectHitLevel();
                levels.add(levelOne);
                //creating and adding level 2
                WideEasyLevel levelTwo = new WideEasyLevel();
                levels.add(levelTwo);
                //creating and adding level 3
                Green3Level levelThree = new Green3Level();
                levels.add(levelThree);
                //creating and adding level 4
                FinalFourLevel levelFour = new FinalFourLevel();
                levels.add(levelFour);
                //running the game flow
                gameFlow.runLevels(levels);
                gui.close();
            }
        }
    }
}
