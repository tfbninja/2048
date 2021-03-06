package runner_2048;

/**
 *
 * @author Tim Barber
 */
public class Square {

    private long value;

    public static int[] MIN_VALUES = {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 4};

    public Square() {
        value = MIN_VALUES[(int) (Math.random() * MIN_VALUES.length)];
    }

    public Square(long value) {
        this();
        if (value > MIN_VALUES[0]) {
            this.value = value;
        }
    }

    public long getValue() {
        return value;
    }

    public void setValue(int val) {
        if (value > MIN_VALUES[0]) {
            this.value = val;
        }
    }

    public void dbl() { // double, but can't name it a primitive
        this.value *= 2;
    }

    public long combine(Square other) {
        value += other.value;
        return value;
    }

    @Override
    public Square clone() {
        return new Square(value);
    }

    @Override
    public String toString() {
        return "Square{" + getValue() + "}";
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
