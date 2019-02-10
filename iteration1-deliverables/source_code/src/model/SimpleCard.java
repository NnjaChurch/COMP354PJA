/**
 * SimpleCard object used to store the codename and associated hints (for future use in iteration 2)
 * @author Karim LouLou - Iteration 1
 */
package model;

import java.util.Arrays;

public class SimpleCard {

    String word;
    String[] hints;


    public SimpleCard(String word) {
        this.word = word;
    }

    public SimpleCard(String word, String[]hints) {
        this.word = word;
        this.hints = hints;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        word = word;
    }

    public String[] getHints() {
        return hints;
    }

    public void setHints(String[]hints) {
        this.hints = hints;
    }

    @Override
    public String toString() {
        return "SimpleCard{" +
                "word='" + word + '\'' +
                ", hints=" + Arrays.toString(hints) +
                '}';
    }
}
