package com.syntax.wordprocessor.common.utilities;

import com.syntax.wordprocessor.common.constants.Constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Word helper class.
 * <p>
 * Author: Ken McSkimming
 */
public class WordHelper {

    /**
     * Return a count of words in the input string.
     */
    public static int wordCount(String textString, String textDelimiter) {
        String special = Constants.EMPTY_STRING;
        String delimiter = Constants.EMPTY_STRING;
        String text = Constants.EMPTY_STRING;
        StringBuilder word = new StringBuilder();
        String letter = Constants.EMPTY_STRING;
        int pos = 0;
        int len = 0;
        int count = 0;
        special = Constants.SPECIAL;
        text = textString.trim() + Constants.SPACE + Constants.SPACE;
        if (text.compareTo(Constants.SPACE + Constants.SPACE) <= 0) {
            return 0;
        }
        switch (textDelimiter.toUpperCase()) {
            case Constants.DELIMITER_SPACE:
            case Constants.DELIMITER_SPACES:
                delimiter = Constants.SPACE;
                break;
            case Constants.DELIMITER_SPECIAL:
            case Constants.DELIMITER_ANY:
            case Constants.EMPTY_STRING:
                delimiter = special;
                break;
            default:
                delimiter = textDelimiter;
                break;
        }
        len = text.length();
        letter = text.substring(pos, pos + 1);
        do {
            word = new StringBuilder();
            while (pos < len - 1 && delimiter.contains(letter)) {
                pos++;
                letter = text.substring(pos, pos + 1);
            }
            while (pos < len - 1 && !delimiter.contains(letter)) {
                word.append(letter);
                pos++;
                letter = text.substring(pos, pos + 1);
            }
            if (word.toString().compareTo(Constants.EMPTY_STRING) > 0) {
                count++;
            }
        } while (!word.toString().equals(Constants.EMPTY_STRING));
        return count;
    }

    /**
     * Return the array words found in the input string.
     * This method was previously called wordArray but was renamed since it returns a list.
     */
    public static List<String> wordList(String textString, String textDelimiter) {
        List<String> wordArray = new ArrayList<>();
        String special = Constants.EMPTY_STRING;
        String delimiter = Constants.EMPTY_STRING;
        String text = Constants.EMPTY_STRING;
        StringBuilder word = new StringBuilder();
        String letter = Constants.EMPTY_STRING;
        int pos = 0;
        int len = 0;
        int count = 0;
        special = Constants.SPECIAL;
        text = textString.trim() + Constants.SPACE + Constants.SPACE;
        if (text.compareTo(Constants.SPACE + Constants.SPACE) <= 0) {
            return new ArrayList<>();
        }
        switch (textDelimiter.toUpperCase()) {
            case Constants.DELIMITER_SPACE:
            case Constants.DELIMITER_SPACES:
                delimiter = Constants.SPACE;
                break;
            case Constants.DELIMITER_SPECIAL:
            case Constants.DELIMITER_ANY:
            case Constants.EMPTY_STRING:
                delimiter = special;
                break;
            default:
                delimiter = textDelimiter;
                break;
        }
        len = text.length();
        letter = text.substring(pos, pos + 1);
        do {
            word = new StringBuilder();
            while (pos < len - 1 && delimiter.contains(letter)) {
                pos++;
                letter = text.substring(pos, pos + 1);
            }
            while (pos < len - 1 && !delimiter.contains(letter)) {
                word.append(letter);
                pos++;
                letter = text.substring(pos, pos + 1);
            }
            if (word.toString().compareTo(Constants.EMPTY_STRING) > 0) {
                count++;
                wordArray.add(word.toString());
            }
        } while (!word.toString().equals(Constants.EMPTY_STRING));
        return wordArray;
    }

    public static String makeString(char letter, int length) {
        char[] chars = new char[length];
        Arrays.fill(chars, letter);
        return new String(chars);
    }
}
