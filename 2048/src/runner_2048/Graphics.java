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
    public static final double BUTTON_MARGINS = 20;
    public static final double[] NUMBER_X_ADD = {39, 27, 18, 15};
    public static final double[] NUMBER_Y_ADD = {22, 22, 24, 24};
    public static final double[] SCORE_X_ADD = {66, 58, 43, 50, 49, 44, 39};
    public static final double[] NUMBER_FONT_SIZE = {48, 48, 48, 48};

    public static final double BORDER_MARGIN = 15;
    public static final double BORDER_RADIUS = 15;
    public static final double BORDER_SIZE = 500;
    public static final double SQUARE_SIZE = 107;
    public static final double SQUARE_RADIUS = 12;
    public static final double NUMBER_HEIGHT = 37;

    public static final Color BG_COLOR = Color.color(251 / 255.0, 248 / 255.0, 239 / 255.0);
    public static final Color SCORE_COLOR = Color.color(243 / 255.0, 231 / 255.0, 217 / 255.0);
    public static final Color BORDER_COLOR = Color.color(187 / 255.0, 173 / 255.0, 160 / 255.0);
    public static final Color[] NUMBER_BGCOLORS = {Color.color(205 / 255.0, 193 / 255.0, 180 / 255.0), // null
        Color.color(238 / 255.0, 228 / 255.0, 218 / 255.0), // 2
        Color.color(236 / 255.0, 224 / 255.0, 200 / 255.0), // 4
        Color.color(235 / 255.0, 179 / 255.0, 128 / 255.0), // 8
        Color.color(236 / 255.0, 140 / 255.0, 80 / 255.0), // 16
        Color.color(247 / 255.0, 123 / 255.0, 95 / 255.0), // 32
        Color.color(234 / 255.0, 90 / 255.0, 56 / 255.0), // 64
        Color.color(237 / 255.0, 207 / 255.0, 114 / 255.0), // 128
        Color.color(241 / 255.0, 208 / 255.0, 75 / 255.0), // 256
        Color.color(236 / 255.0, 200 / 255.0, 90 / 255.0), // 512
        Color.color(225 / 255.0, 186 / 255.0, 20 / 255.0), // 1024
        Color.color(234 / 255.0, 198 / 255.0, 0 / 255.0), // 2048
        Color.color(96 / 255.0, 217 / 255.0, 146 / 255.0), // 4096
        Color.color(251 / 255.0, 37 / 255.0, 29 / 255.0) // 8192 and above
};

    public static final Color[] NUMBER_COLORS = {Color.color(205 / 255.0, 193 / 255.0, 180 / 255.0), // null
        Color.color(119 / 255.0, 110 / 255.0, 101 / 255.0), // 2
        Color.color(119 / 255.0, 110 / 255.0, 101 / 255.0), // 4
        Color.color(249 / 255.0, 246 / 255.0, 242 / 255.0), // 8
        Color.color(249 / 255.0, 246 / 255.0, 242 / 255.0), // 16
        Color.color(249 / 255.0, 246 / 255.0, 242 / 255.0), // 128
        Color.color(249 / 255.0, 246 / 255.0, 242 / 255.0), // 256
        Color.color(249 / 255.0, 246 / 255.0, 242 / 255.0), // 512
        Color.color(249 / 255.0, 246 / 255.0, 242 / 255.0), // 1024
        Color.color(249 / 255.0, 246 / 255.0, 242 / 255.0), // 2048
        Color.color(249 / 255.0, 246 / 255.0, 242 / 255.0), // 4096
        Color.color(249 / 255.0, 246 / 255.0, 242 / 255.0) // 8192 and above
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
        gc.setFill(BG_COLOR);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        gc.setFill(BORDER_COLOR);
        gc.fillRoundRect(BORDER_X, BORDER_Y, BORDER_SIZE, BORDER_SIZE, BORDER_RADIUS, BORDER_RADIUS); // background / border

        gc.setFill(NUMBER_BGCOLORS[11]);
        gc.fillRoundRect(BORDER_X, 0, (BORDER_SIZE - BUTTON_MARGINS * 2) / 3, (BORDER_SIZE - BUTTON_MARGINS * 2) / 3, BORDER_RADIUS, BORDER_RADIUS);

        gc.setFill(BORDER_COLOR);
        // score
        gc.fillRoundRect(BORDER_X + (BORDER_SIZE - BUTTON_MARGINS * 2) / 3 + BUTTON_MARGINS, 0, (BORDER_SIZE - BUTTON_MARGINS * 2) / 3, (BORDER_SIZE - BUTTON_MARGINS * 2) / 6, BORDER_RADIUS, BORDER_RADIUS);
        // best
        gc.fillRoundRect(BORDER_X + ((BORDER_SIZE - BUTTON_MARGINS * 2) / 3 + BUTTON_MARGINS) * 2, 0, (BORDER_SIZE - BUTTON_MARGINS * 2) / 3, (BORDER_SIZE - BUTTON_MARGINS * 2) / 6, BORDER_RADIUS, BORDER_RADIUS);

        gc.setFill(SCORE_COLOR);
        gc.setFont(new Font("Calibri bold", 25));
        gc.fillText("SCORE", BORDER_X + (BORDER_SIZE - BUTTON_MARGINS * 2) / 3 + BUTTON_MARGINS + 40, 30);
        gc.fillText("BEST", BORDER_X + ((BORDER_SIZE - BUTTON_MARGINS * 2) / 3 + BUTTON_MARGINS) * 2 + 45, 30);

        gc.setFill(NUMBER_COLORS[3]);
        gc.setFont(new Font("Calibri bold", 30));
        gc.fillText(String.valueOf(grid.getScore()), BORDER_X + (BORDER_SIZE - BUTTON_MARGINS * 2) / 3 + BUTTON_MARGINS + SCORE_X_ADD[String.valueOf(grid.getScore()).length() - 1], 55);

        for (int r = 0; r < grid.h; r++) {
            int c = 0;
            for (Square s : grid.getRow(r)) {
                if (s != null) {
                    try {
                        gc.setFill(NUMBER_BGCOLORS[(int) (Math.log(s.getValue()) / Math.log(2))]);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Number bigger than 8096");
                        gc.setFill(NUMBER_BGCOLORS[NUMBER_BGCOLORS.length - 1]);
                    }
                    gc.fillRoundRect(BORDER_X + BORDER_MARGIN * (c + 1) + SQUARE_SIZE * c, BORDER_Y + BORDER_MARGIN * (r + 1) + SQUARE_SIZE * r, SQUARE_SIZE, SQUARE_SIZE, SQUARE_RADIUS, SQUARE_RADIUS);
                    try {
                        gc.setFill(NUMBER_COLORS[(int) (Math.log(s.getValue()) / Math.log(2))]);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Number bigger than 8096");
                        gc.setFill(NUMBER_COLORS[3]);
                    }
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
