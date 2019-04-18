package runner_2048;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import javafx.util.Pair;

/**
 *
 * @author Tim Barber
 */
public class Grid {

    private Square[][] squares;
    public int w;
    public int h;
    private long score = 0;
    private boolean gameOver = false;

    public Grid(int w, int h) {
        this.w = w;
        this.h = h;
        squares = new Square[h][w];

        int h1 = (int) (Math.random() * h);
        int w1 = (int) (Math.random() * w);
        int h2 = (int) (Math.random() * h);
        int w2 = (int) (Math.random() * w);
        if (h2 == h1 && w2 == w1) {
            if (Math.random() > 0.5) {
                // change w
                if (w2 == w - 1) {
                    w2--;
                } else if (w2 == 0) {
                    w2++;
                } else {
                    if (Math.random() > 0.5) {
                        w2++;
                    } else {
                        w2--;
                    }
                }
            } else {
                // change h
                if (h2 == h - 1) {
                    h2--;
                } else if (h2 == 0) {
                    h2++;
                } else {
                    if (Math.random() > 0.5) {
                        h2++;
                    } else {
                        h2--;
                    }
                }
            }
        }
        squares[h1][w1] = new Square();
        squares[h2][w2] = new Square();
    }

    public long getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setSquares(Square[][] s) {
        squares = s;
    }

    public Square[] getRow(int index) {
        return squares[index];
    }

    public Square[] getColumn(int index) {
        Square[] col = new Square[squares.length];
        for (int r = 0; r < squares.length; r++) {
            col[r] = squares[r][index];
        }
        return col;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public void removeNull(ArrayList<Square> list) {
        for (int i = list.size() - 1; i >= 0; i--) {
            if (list.get(i) == null) {
                list.remove(i);
            }
        }
    }

    public void testRemoveAndDoubleDupes(ArrayList<Square> list) {
        removeNull(list);
        if (list.size() > 1) {
            for (int i = list.size() - 1; i > 0; i--) {
                if (list.get(i).getValue() == list.get(i - 1).getValue()) {
                    list.get(i).dbl();
                    list.remove(i - 1);
                    i--;
                }
            }
        }
    }

    public void removeAndDoubleDupes(ArrayList<Square> list) {
        removeNull(list);
        if (list.size() > 1) {
            for (int i = list.size() - 1; i > 0; i--) {
                if (list.get(i).getValue() == list.get(i - 1).getValue()) {
                    list.get(i).dbl();
                    score += list.get(i).getValue();
                    list.remove(i - 1);
                    i--;
                }
            }
        }
    }

    public Square getRandom() {
        if (Math.random() > 0.9) {
            return new Square();
        } else {
            return null;
        }
    }

    public boolean containsDupesInARowOrNull(ArrayList<Long> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) == null || list.get(i + 1) == null) {
                return true;
            }
            if (Objects.equals(list.get(i), list.get(i + 1))) {
                return true;
            }
        }
        return false;
    }

    public void checkLost() {
        for (int r = 0; r < h; r++) {
            ArrayList<Long> row = new ArrayList<>();
            for (int c = 0; c < w; c++) {
                if (squares[r][c] == null) {
                    return;
                } else {
                    row.add(squares[r][c].getValue());
                }
            }
            if (containsDupesInARowOrNull(row)) {
                return;
            }
            if (row.size() < h) {
                return;
            }
        }
        for (int c = 0; c < w; c++) {
            ArrayList<Long> col = new ArrayList<>();
            for (int r = 0; r < h; r++) {
                if (squares[r][c] == null) {
                    return;
                } else {
                    col.add(squares[r][c].getValue());
                }
            }
            if (containsDupesInARowOrNull(col)) {
                return;
            }
            if (col.size() < w) {
                return;
            }
        }
        gameOver = true;
    }

    public boolean checkWon() {
        for (int r = 0; r < h; r++) {
            for (int c = 0; c < w; c++) {
                if (squares[r][c] != null && squares[r][c].getValue() >= 2048) {
                    return true;
                }
            }
        }
        return false;
    }

    public void shiftRight() {
        if (!gameOver) {
            long[][] before = new long[h][w];
            int r_ = 0;
            for (Square[] sl : squares) {
                int c_ = 0;
                for (Square s : sl) {
                    if (s == null) {
                        before[r_][c_] = -1;
                    } else {
                        before[r_][c_] = s.getValue();
                    }
                    c_++;
                }
                r_++;
            }

            for (int r = 0; r < h; r++) {
                ArrayList<Square> row = new ArrayList<>();
                row.addAll(Arrays.asList(squares[r]));
                removeAndDoubleDupes(row);
                squares[r] = new Square[squares[r].length];
                for (int i = 0; i < row.size(); i++) {
                    squares[r][squares[r].length - row.size() + i] = row.get(i);
                }
            }
            // check equality
            boolean same = true;
            for (int _r = 0; _r < h; _r++) {
                for (int _c = 0; _c < w; _c++) {
                    if (before[_r][_c] == -1) {
                        same = same && squares[_r][_c] == null;
                    } else if (squares[_r][_c] == null) {
                        same = false;
                    } else {
                        same = same && squares[_r][_c].getValue() == before[_r][_c];
                    }

                }
            }
            ArrayList<Pair<Integer, Integer>> spots = new ArrayList<>();
            for (int _r = 0; _r < h; _r++) {
                for (int _c = 0; _c < w; _c++) {
                    if (squares[_r][_c] == null) {
                        spots.add(new Pair<>(_r, _c));
                    }
                }
            }
            if (spots.size() > 0) {
                if (!same) {
                    // add square
                    Collections.shuffle(spots);
                    squares[spots.get(0).getKey()][spots.get(0).getValue()] = new Square();
                }
            }
        }
    }

    public void shiftLeft() {
        if (!gameOver) {
            long[][] before = new long[h][w];
            int r_ = 0;
            for (Square[] sl : squares) {
                int c_ = 0;
                for (Square s : sl) {
                    if (s == null) {
                        before[r_][c_] = -1;
                    } else {
                        before[r_][c_] = s.getValue();
                    }
                    c_++;
                }
                r_++;
            }

            for (int r = 0; r < h; r++) {
                ArrayList<Square> row = new ArrayList<>();
                for (Square s : squares[r]) {
                    row.add(s);
                }
                Collections.reverse(row);
                removeAndDoubleDupes(row);
                Collections.reverse(row);
                squares[r] = new Square[squares[r].length];
                for (int i = 0; i < row.size(); i++) {
                    squares[r][i] = row.get(i);
                }
            }
            // check equality
            boolean same = true;
            for (int _r = 0; _r < h; _r++) {
                for (int _c = 0; _c < w; _c++) {
                    if (before[_r][_c] == -1) {
                        same = same && squares[_r][_c] == null;
                    } else if (squares[_r][_c] == null) {
                        same = false;
                    } else {
                        same = same && squares[_r][_c].getValue() == before[_r][_c];
                    }

                }
            }
            ArrayList<Pair<Integer, Integer>> spots = new ArrayList<>();
            for (int _r = 0; _r < h; _r++) {
                for (int _c = 0; _c < w; _c++) {
                    if (squares[_r][_c] == null) {
                        spots.add(new Pair<>(_r, _c));
                    }
                }
            }
            if (spots.size() > 0) {
                if (!same) {
                    // add square
                    Collections.shuffle(spots);
                    squares[spots.get(0).getKey()][spots.get(0).getValue()] = new Square();
                }
            }
        }
    }

    public void shiftDown() {
        if (!gameOver) {
            long[][] before = new long[h][w];
            int r_ = 0;
            for (Square[] sl : squares) {
                int c_ = 0;
                for (Square s : sl) {
                    if (s == null) {
                        before[r_][c_] = -1;
                    } else {
                        before[r_][c_] = s.getValue();
                    }
                    c_++;
                }
                r_++;
            }

            for (int c = 0; c < w; c++) {
                ArrayList<Square> col = new ArrayList<>();
                for (Square s : getColumn(c)) {
                    col.add(s);
                }
                removeAndDoubleDupes(col);
                for (int r = 0; r < h; r++) {
                    squares[r][c] = null;
                }
                for (int i = 0; i < col.size(); i++) {
                    squares[h - col.size() + i][c] = col.get(i);
                }
            }
            // check equality
            boolean same = true;
            for (int _r = 0; _r < h; _r++) {
                for (int _c = 0; _c < w; _c++) {
                    if (before[_r][_c] == -1) {
                        same = same && squares[_r][_c] == null;
                    } else if (squares[_r][_c] == null) {
                        same = false;
                    } else {
                        same = same && squares[_r][_c].getValue() == before[_r][_c];
                    }

                }
            }
            ArrayList<Pair<Integer, Integer>> spots = new ArrayList<>();
            for (int _r = 0; _r < h; _r++) {
                for (int _c = 0; _c < w; _c++) {
                    if (squares[_r][_c] == null) {
                        spots.add(new Pair<>(_r, _c));
                    }
                }
            }
            if (spots.size() > 0) {
                if (!same) {
                    // add square
                    Collections.shuffle(spots);
                    squares[spots.get(0).getKey()][spots.get(0).getValue()] = new Square();
                }
            }
        }
    }

    public void shiftUp() {
        if (!gameOver) {
            long[][] before = new long[h][w];
            int r_ = 0;
            for (Square[] sl : squares) {
                int c_ = 0;
                for (Square s : sl) {
                    if (s == null) {
                        before[r_][c_] = -1;
                    } else {
                        before[r_][c_] = s.getValue();
                    }
                    c_++;
                }
                r_++;
            }

            for (int c = 0; c < w; c++) {
                ArrayList<Square> col = new ArrayList<>();
                for (Square s : getColumn(c)) {
                    col.add(s);
                }
                Collections.reverse(col);
                removeAndDoubleDupes(col);
                Collections.reverse(col);
                for (int r = 0; r < h; r++) {
                    squares[r][c] = null;
                }
                for (int i = 0; i < col.size(); i++) {
                    squares[col.size() - (1 + i)][c] = col.get(col.size() - (1 + i));
                }
            }
            // check equality
            boolean same = true;
            for (int _r = 0; _r < h; _r++) {
                for (int _c = 0; _c < w; _c++) {
                    if (before[_r][_c] == -1) {
                        same = same && squares[_r][_c] == null;
                    } else if (squares[_r][_c] == null) {
                        same = false;
                    } else {
                        same = same && squares[_r][_c].getValue() == before[_r][_c];
                    }

                }
            }
            ArrayList<Pair<Integer, Integer>> spots = new ArrayList<>();
            for (int _r = 0; _r < h; _r++) {
                for (int _c = 0; _c < w; _c++) {
                    if (squares[_r][_c] == null) {
                        spots.add(new Pair<>(_r, _c));
                    }
                }
            }
            if (spots.size() > 0) {
                if (!same) {
                    // add square
                    Collections.shuffle(spots);
                    squares[spots.get(0).getKey()][spots.get(0).getValue()] = new Square();
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
