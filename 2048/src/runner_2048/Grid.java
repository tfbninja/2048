package runner_2048;

import java.util.ArrayList;
import java.util.Collections;
import javafx.util.Pair;

/**
 *
 * @author Tim Barber
 */
public class Grid {

    private Square[][] squares;
    public int w;
    public int h;
    private int score = 0;

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

    public int getScore() {
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

    public void removeNull(ArrayList<Square> list) {
        for (int i = list.size() - 1; i >= 0; i--) {
            if (list.get(i) == null) {
                list.remove(i);
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

    public void shiftRight() {
        Square[][] before = new Square[h][w];
        int r_ = 0;
        for (Square[] sl : squares) {
            int c_ = 0;
            for (Square s : sl) {
                if (s == null) {
                    before[r_][c_] = null;
                } else {
                    before[r_][c_] = new Square(s.getValue());
                }
                c_++;
            }
            r_++;
        }

        for (int r = 0;
                r < h;
                r++) {
            ArrayList<Square> row = new ArrayList<>();
            for (Square s : squares[r]) {
                row.add(s);
            }
            removeAndDoubleDupes(row);
            squares[r] = new Square[squares[r].length];
            for (int i = 0; i < row.size(); i++) {
                squares[r][squares[r].length - row.size() + i] = row.get(i);
            }
        }
        // check equality
        boolean same = true;
        for (int _r = 0;
                _r < h;
                _r++) {
            for (int _c = 0; _c < w; _c++) {
                if (before[_r][_c] == null) {
                    same = same && squares[_r][_c] == null;
                } else if (squares[_r][_c] == null) {
                    same = false;
                } else {
                    same = same && squares[_r][_c].getValue() == before[_r][_c].getValue();
                }

            }
        }
        if (!same) {
            // add square
            ArrayList<Pair<Integer, Integer>> spots = new ArrayList<>();
            for (int _r = 0; _r < h; _r++) {
                for (int _c = 0; _c < w; _c++) {
                    if (squares[_r][_c] == null) {
                        spots.add(new Pair<>(_r, _c));
                    }
                }
            }
            if (spots.size() > 0) {
                // add square
                Collections.shuffle(spots);
                squares[spots.get(0).getKey()][spots.get(0).getValue()] = new Square();
            } else {
                // game over
            }
        }
    }

    public void shiftLeft() {
        Square[][] before = new Square[h][w];
        int r_ = 0;
        for (Square[] sl : squares) {
            int c_ = 0;
            for (Square s : sl) {
                if (s == null) {
                    before[r_][c_] = null;
                } else {
                    before[r_][c_] = new Square(s.getValue());
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
            removeAndDoubleDupes(row);
            squares[r] = new Square[squares[r].length];
            for (int i = 0; i < row.size(); i++) {
                squares[r][i] = row.get(i);
            }
        }
        // check equality
        boolean same = true;
        for (int _r = 0; _r < h; _r++) {
            for (int _c = 0; _c < w; _c++) {
                if (before[_r][_c] == null) {
                    same = same && squares[_r][_c] == null;
                } else if (squares[_r][_c] == null) {
                    same = false;
                } else {
                    same = same && squares[_r][_c].getValue() == before[_r][_c].getValue();
                }
            }
        }
        if (!same) {
            // add square
            ArrayList<Pair<Integer, Integer>> spots = new ArrayList<>();
            for (int _r = 0; _r < h; _r++) {
                for (int _c = 0; _c < w; _c++) {
                    if (squares[_r][_c] == null) {
                        spots.add(new Pair<>(_r, _c));
                    }
                }
            }
            if (spots.size() > 0) {
                // add square
                Collections.shuffle(spots);
                squares[spots.get(0).getKey()][spots.get(0).getValue()] = new Square();
            } else {
                // game over
            }
        }
    }

    public void shiftDown() {
        Square[][] before = new Square[h][w];
        int r_ = 0;
        for (Square[] sl : squares) {
            int c_ = 0;
            for (Square s : sl) {
                if (s == null) {
                    before[r_][c_] = null;
                } else {
                    before[r_][c_] = new Square(s.getValue());
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
                if (before[_r][_c] == null) {
                    same = same && squares[_r][_c] == null;
                } else if (squares[_r][_c] == null) {
                    same = false;
                } else {
                    same = same && squares[_r][_c].getValue() == before[_r][_c].getValue();
                }

            }
        }
        if (!same) {
            // add square
            ArrayList<Pair<Integer, Integer>> spots = new ArrayList<>();
            for (int _r = 0; _r < h; _r++) {
                for (int _c = 0; _c < w; _c++) {
                    if (squares[_r][_c] == null) {
                        spots.add(new Pair<>(_r, _c));
                    }
                }
            }
            if (spots.size() > 0) {
                // add square
                Collections.shuffle(spots);
                squares[spots.get(0).getKey()][spots.get(0).getValue()] = new Square();
            } else {
                // game over
            }
        }
    }

    public void shiftUp() {
        Square[][] before = new Square[h][w];
        int r_ = 0;
        for (Square[] sl : squares) {
            int c_ = 0;
            for (Square s : sl) {
                if (s == null) {
                    before[r_][c_] = null;
                } else {
                    before[r_][c_] = new Square(s.getValue());
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
                squares[col.size() - (1 + i)][c] = col.get(i);
            }
        }
        // check equality
        boolean same = true;
        for (int _r = 0; _r < h; _r++) {
            for (int _c = 0; _c < w; _c++) {
                if (before[_r][_c] == null) {
                    same = same && squares[_r][_c] == null;
                } else if (squares[_r][_c] == null) {
                    same = false;
                } else {
                    same = same && squares[_r][_c].getValue() == before[_r][_c].getValue();
                }

            }
        }
        if (!same) {
            // add square
            ArrayList<Pair<Integer, Integer>> spots = new ArrayList<>();
            for (int _r = 0; _r < h; _r++) {
                for (int _c = 0; _c < w; _c++) {
                    if (squares[_r][_c] == null) {
                        spots.add(new Pair<>(_r, _c));
                    }
                }
            }
            if (spots.size() > 0) {
                // add square
                Collections.shuffle(spots);
                squares[spots.get(0).getKey()][spots.get(0).getValue()] = new Square();
            } else {
                // game over
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
