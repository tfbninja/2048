package runner_2048;

import java.util.ArrayList;

/**
 *
 * @author Tim Barber
 */
public class Grid {

    private final Square[][] squares;
    public final int w;
    public final int h;

    public Grid(int w, int h) {
        this.w = w;
        this.h = h;
        squares = new Square[h][w];

        int h1 = (int) (Math.random() * h);
        int w1 = (int) (Math.random() * w);
        int h2 = (int) (Math.random() * h);
        int w2 = (int) (Math.random() * w);
        if (h2 == h1) {
            if (Math.random() >= 0.5) {
                h2++;
            } else {
                h2--;
            }
        }
        if (w2 == w1) {
            if (Math.random() >= 0.5) {
                w2++;
            } else {
                w2--;
            }
        }
        squares[h1][w1] = new Square();
        squares[h2][w2] = new Square();
    }

    public Square[] getRow(int index) {
        return squares[index];
    }

    public void shiftRight() {
        for (int r = 0; r < h; r++) {
            for (int c = w; c >= 0; c--) {
                if (squares[r][c] == null) {
                    // shift other squares right
                    for (int c2 = c - 1; c >= 0; c--) {
                        squares[r][c] = squares[r][c2];
                    }
                } else if (c > 0 && squares[r][c].getValue() == squares[r][c - 1].getValue()) {
                    // if this square and the one to it's left are the same, combine them
                    squares[r][c].dbl();
                    squares[r][c - 1] = null;
                } else {
                    // nothing to be done with this square
                }
            }
        }
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
