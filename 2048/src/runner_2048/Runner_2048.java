package runner_2048;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Tim Barber
 */
public class Runner_2048 extends Application {

    private static int frame = 0;
    private static Graphics g;

    public static final Button TRY_AGAIN = new Button(Graphics.BORDER_X + 194, Graphics.BORDER_Y + 231, (Graphics.BORDER_SIZE / 2 - 194) * 2, 40);
    public static boolean wonAlready = false;
    public static boolean displayWin = false;

    public static int getBestScore() {
        return bestScore;
    }

    public static void setBestScore(int bestScore) {
        Runner_2048.bestScore = bestScore;
    }
    public boolean keyUpU = true;
    public boolean keyUpD = true;
    public boolean keyUpL = true;
    public boolean keyUpR = true;
    public static int bestScore = 0;

    public static boolean isDisplayWin() {
        return displayWin;
    }

    public static void setDisplayWin(boolean displayWin) {
        Runner_2048.displayWin = displayWin;
    }

    public static boolean isWonAlready() {
        return wonAlready;
    }

    public static void setWonAlready(boolean wonAlready) {
        Runner_2048.wonAlready = wonAlready;
    }

    @Override
    public void start(Stage primaryStage) {
        g = new Graphics(new Grid(4, 4));
        //Square[][] test = {
        //    {new Square(2), new Square(2), new Square(2), null},
        //    {new Square(2), new Square(2), new Square(2), new Square(2)},
        //    {new Square(2), new Square(2), new Square(1024), new Square(8192)},
        //    {null, null, null, new Square(4096)}
        //};
        //Grid temp = new Grid(4, 4);
        //temp.setScore(9999);
        //temp.setSquares(test);
        //g.setGrid(temp);
        StackPane root = new StackPane();
        root.getChildren().add(g.getCanvas());

        Scene scene = new Scene(root, Graphics.BORDER_X + Graphics.BORDER_SIZE, Graphics.BORDER_Y + Graphics.BORDER_SIZE);
        primaryStage.setTitle("2048");
        primaryStage.setScene(scene);
        primaryStage.show();

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                frame++;
                if (!g.getGrid().isGameOver()) {
                    g.getGrid().checkLost();
                }
                if (g.getGrid().getScore() > bestScore) {
                    bestScore = g.getGrid().getScore();
                }
                g.draw();
            }

        }.start();

        // Input handling
        scene.setOnMousePressed((MouseEvent event) -> {
            double mX = event.getX() - root.getInsets().getLeft() + 10;
            double mY = event.getY() - root.getInsets().getTop() + 10;
            System.out.println(mX + ", " + mY);
            System.out.println(TRY_AGAIN);

            if (TRY_AGAIN.inBounds(mX, mY) && (g.getGrid().isGameOver() || displayWin == true)) {
                newGame();
            }
        });

        scene.setOnMouseDragged((MouseEvent event) -> {

        });

        scene.setOnMouseReleased((MouseEvent event) -> {

        });

        scene.setOnKeyReleased((KeyEvent eventa) -> {
            if (null != eventa.getCode()) {
                switch (eventa.getCode()) {
                    case D:
                    case RIGHT:
                        keyUpR = true;
                        break;
                    case A:
                    case LEFT:
                        keyUpL = true;
                        break;
                    case W:
                    case UP:
                        keyUpU = true;
                        break;
                    case S:
                    case DOWN:
                        keyUpD = true;
                        break;
                    default:
                        break;
                }
            }
        });

        scene.setOnKeyPressed((KeyEvent eventa) -> {
            if (null != eventa.getCode()) {
                switch (eventa.getCode()) {
                    case D:
                    case RIGHT:
                        if (keyUpR) {
                            g.getGrid().shiftRight();
                            displayWin = false;
                            keyUpR = false;
                        }
                        break;
                    case A:
                    case LEFT:
                        if (keyUpL) {
                            g.getGrid().shiftLeft();
                            displayWin = false;
                            keyUpL = false;
                        }
                        break;
                    case W:
                    case UP:
                        if (keyUpU) {
                            g.getGrid().shiftUp();
                            displayWin = false;
                            keyUpU = false;
                        }
                        break;
                    case S:
                    case DOWN:
                        if (keyUpD) {
                            g.getGrid().shiftDown();
                            displayWin = false;
                            keyUpD = false;
                        }
                        break;
                    default:
                        break;
                }
            }
        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public static void newGame() {
        wonAlready = false;
        displayWin = false;
        frame = 0;
        g.setGrid(new Grid(4, 4));
    }

}
/*
 * The MIT License
 *
 * Copyright (c) 2019 Tim Barber.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
