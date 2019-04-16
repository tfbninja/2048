package runner_2048;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 *
 * @author Tim Barber
 */
public class Graphics {

    private Grid grid;
    private final Canvas canvas;

    public static final double BORDER_X = 0;
    public static final double BORDER_Y = 187;
    public static final double[] NUMBER_X_ADD = {39, 26, 18, 15};
    public static final double[] NUMBER_Y_ADD = {22, 22, 24, 24};
    public static final double[] NUMBER_FONT_SIZE = {48, 48, 48, 48};

    public static final double BORDER_MARGIN = 15;
    public static final double BORDER_RADIUS = 5;
    public static final double BORDER_SIZE = 500;
    public static final double SQUARE_SIZE = 107;
    public static final double SQUARE_RADIUS = 2;
    public static final double NUMBER_HEIGHT = 37;

    public static final Color BORDER_COLOR = Color.color(187 / 255.0, 173 / 255.0, 160 / 255.0);
    public static final Color[] NUMBER_BGCOLORS = {Color.color(205 / 255.0, 193 / 255.0, 180 / 255.0), // null
        Color.color(238 / 255.0, 228 / 255.0, 218 / 255.0), // 2
        Color.color(237 / 255.0, 224 / 255.0, 200 / 255.0), // 4
        Color.color(242 / 255.0, 177 / 255.0, 121 / 255.0), // 8
        Color.color(145 / 255.0, 149 / 255.0, 99 / 255.0), // 16
        Color.color(237 / 255.0, 207 / 255.0, 114 / 255.0) // 128
};

    public static final Color[] NUMBER_COLORS = {Color.color(205 / 255.0, 193 / 255.0, 180 / 255.0), // null
        Color.color(119 / 255.0, 110 / 255.0, 101 / 255.0), // 2
        Color.color(119 / 255.0, 110 / 255.0, 101 / 255.0), // 4
        Color.color(249 / 255.0, 246 / 255.0, 242 / 255.0), // 8
        Color.color(249 / 255.0, 246 / 255.0, 242 / 255.0), // 16
        Color.color(249 / 255.0, 246 / 255.0, 242 / 255.0), // 128
};

    public Graphics(Grid g) {
        grid = g;
        canvas = new Canvas(BORDER_SIZE + BORDER_X, BORDER_SIZE + BORDER_Y);
    }

    public Grid getGrid() {
        return grid;
    }

    public void setGrid(Grid g) {
        this.grid = g;
    }

    public void draw() {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setFill(BORDER_COLOR);
        gc.fillRoundRect(BORDER_X, BORDER_Y, BORDER_SIZE, BORDER_SIZE, BORDER_RADIUS, BORDER_RADIUS); // background / border
        for (int r = 0; r < grid.h; r++) {
            int c = 0;
            for (Square s : grid.getRow(r)) {
                if (s != null) {
                    gc.setFill(NUMBER_BGCOLORS[(int) (Math.log(s.getValue()) / Math.log(2))]);
                    gc.fillRoundRect(BORDER_X + BORDER_MARGIN * (c + 1) + SQUARE_SIZE * c, BORDER_Y + BORDER_MARGIN * (r + 1) + SQUARE_SIZE * r, SQUARE_SIZE, SQUARE_SIZE, SQUARE_RADIUS, SQUARE_RADIUS);
                    gc.setFill(NUMBER_COLORS[(int) (Math.log(s.getValue()) / Math.log(2))]);
                    gc.setFont(new Font("Calibri bold", 48.0));
                    gc.fillText(String.valueOf(s.getValue()), BORDER_X + BORDER_MARGIN * (c + 1) + SQUARE_SIZE * c + NUMBER_X_ADD[String.valueOf(s.getValue()).length() - 1], BORDER_Y + BORDER_MARGIN * (r + 1) + SQUARE_SIZE * r + NUMBER_Y_ADD[String.valueOf(s.getValue()).length() - 1] + gc.getFont().getSize());
                } else {
                    gc.setFill(NUMBER_BGCOLORS[0]);
                    gc.fillRoundRect(BORDER_X + BORDER_MARGIN * (c + 1) + SQUARE_SIZE * c, BORDER_Y + BORDER_MARGIN * (r + 1) + SQUARE_SIZE * r, SQUARE_SIZE, SQUARE_SIZE, SQUARE_RADIUS, SQUARE_RADIUS);
                }
                c++;
            }
        }
    }

    public Canvas getCanvas() {
        return canvas;
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
