package model;

import java.util.ArrayList;
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
