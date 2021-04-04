package gamelogic;

import biuoop.KeyboardSensor;
import observers.Counter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author Yoav Berger <yoavbrgr@gmail.com> ID 313268393
 * @since 05/06/2020
 */
public class GameFlow {
    //fields
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private Counter scoreCounter;

    /**
     * constructor.
     *
     * @param ar animation runner
     * @param ks keyboard sensor
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.scoreCounter = new Counter(0);
    }

    /**
     * Writing the current score of the game to txt file.
     *
     * @param
     */

    /**
     * Gets levels of the game and runs it.
     *
     * @param levels of the game
     */
    public void runLevels(List<LevelInformation> levels) {
        int numOfBallAtEnd = 0;
        for (LevelInformation levelInfo : levels) {

            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor, this.animationRunner, this.scoreCounter);

            level.initialize();
            //level has more blocks and player has more lives
            while (!level.shouldStop()) {
                level.run();
                numOfBallAtEnd = level.getBallCounterValue();
            }
            //no more lives
            if (level.getBallCounterValue() == 0) {
                GameOverScreen gameOver = new GameOverScreen(this.keyboardSensor, this.scoreCounter);
                KeyPressStoppableAnimation stopAnimation = new KeyPressStoppableAnimation(this.keyboardSensor,
                        "space", gameOver);
                this.animationRunner.run(stopAnimation);
                break;
            }
        }
        //case of winning
        if (numOfBallAtEnd != 0) {
            YouWinScreen youWin = new YouWinScreen(this.keyboardSensor, this.scoreCounter);
            KeyPressStoppableAnimation stopAnim = new KeyPressStoppableAnimation(this.keyboardSensor,
                    "space", youWin);
            this.animationRunner.run(stopAnim);
        }

        //check for high scores
        try {
            //setting the file location
            File highScores = new File("C:\\Users\\Yoav\\IdeaProjects\\ass02\\src\\highscores.txt");
            //if the file does not exists create him
            if (highScores.createNewFile()) {
                //writing the current score into the file
                PrintWriter os = null;
                try {
                    os = new PrintWriter(new OutputStreamWriter(new FileOutputStream(highScores)));
                    os.printf("The highest score so far is: %d", this.scoreCounter.getValue());
                    //if the writing fails
                } catch (IOException e) {
                    System.out.println("Something went wrong while writing!");
                    //closing the writing
                } finally {
                    if (os != null) {
                        os.close();
                    }
                }
            }
            //in case file exists
            else {
                //reading the line in the file
                BufferedReader is = null;
                try {
                    is = new BufferedReader(new InputStreamReader(new FileInputStream(highScores)));
                    String firstLine;
                    firstLine = is.readLine();
                    //getting the score from the line
                    int sumOfText = 0;
                    for (int i = 0; i < firstLine.length(); i++) {
                        if (Character.isDigit(firstLine.toCharArray()[i])) {
                            sumOfText = sumOfText * 10 + Character.getNumericValue(firstLine.toCharArray()[i]);
                        }
                    }
                    //checking if the current sum is bigger than the text's sum
                    if (sumOfText < this.scoreCounter.getValue()) {
                        //writing the current score into the file
                        PrintWriter os = null;
                        try {
                            os = new PrintWriter(new OutputStreamWriter(new FileOutputStream(highScores)));
                            os.printf("The highest score so far is: %d", this.scoreCounter.getValue());
                            //if the writing fails
                        } catch (IOException e) {
                            System.out.println("Something went wrong while writing!");
                            //closing the writing
                        } finally {
                            if (os != null) {
                                os.close();
                            }
                        }
                    }
                    //if cannot read
                } catch (IOException e) {
                    System.out.println("Something went wrong while reading!");
                } finally {
                    if (is != null) {
                        try {
                            //closing
                            is.close();
                            //in case cant close
                        } catch (IOException e) {
                            System.out.println("Failed closing the file!");
                        }
                    }
                }
            }
            //if can't create the file
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
}
