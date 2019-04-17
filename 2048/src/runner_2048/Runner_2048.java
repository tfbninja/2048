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

    @Override
    public void start(Stage primaryStage) {
        g = new Graphics(new Grid(4, 4));
        Square[][] test = {{new Square(), new Square(), new Square(), null}, {null, null, null, null}, {null, null, null, null}, {null, null, null, null}};
        Grid temp = new Grid(4, 4);
        temp.setSquares(test);
        g.setGrid(temp);
        StackPane root = new StackPane();
        root.getChildren().add(g.getCanvas());

        Scene scene = new Scene(root, 600, 800);

        primaryStage.setTitle("2048");
        primaryStage.setScene(scene);
        primaryStage.show();

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                frame++;
                g.draw();
            }

        }.start();

        // Input handling
        scene.setOnMousePressed((MouseEvent event) -> {

        });

        scene.setOnMouseDragged((MouseEvent event) -> {

        });

        scene.setOnMouseReleased((MouseEvent event) -> {

        });

        scene.setOnKeyPressed((KeyEvent eventa) -> {
            if (null != eventa.getCode()) {
                switch (eventa.getCode()) {
                    case RIGHT:
                        g.getGrid().shiftRight();
                        break;
                    case LEFT:
                        g.getGrid().shiftLeft();
                        break;
                    case UP:
                        g.getGrid().shiftUp();
                        break;
                    case DOWN:
                        g.getGrid().shiftDown();
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
