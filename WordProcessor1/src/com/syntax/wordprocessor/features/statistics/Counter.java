package com.syntax.wordprocessor.features.statistics;

import com.syntax.wordprocessor.common.utilities.FileHelper;
import com.syntax.wordprocessor.common.utilities.WordHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Used to calculate all the statistics and counts.
 * <p>
 * Author: Ken McSkimming
 */
public class Counter {

    // Constants.
    private static final String DATA_PATH = "C:\\_Intelij\\WordProcessor\\src\\com\\syntax\\wordprocessor\\Data\\";
    private static final String INPUT_PATH = DATA_PATH + "Input\\";
    private static final String OUTPUT_PATH = DATA_PATH + "Output\\";
    private static final String INPUT_TEXT_FILE = INPUT_PATH + "InputText.txt";
    private static final String INPUT_AMOUNTS_FILE = INPUT_PATH + "InputAmounts.txt";
    private static final String OUTPUT_FILE = OUTPUT_PATH + "Statistics.txt";
    private static final char SPACE = ' ';

    // Member variables.
    private int mCharacterCount = 0;
    private int mNonSpaceCharacterCount = 0;
    private int mLineCount = 0;
    private int mWordCount = 0;
    private double mTotalAmount = 0;
    private List<String> mInputLines;
    private List<String> mInputAmounts;
    private List<String> mOutputLines;

    /**
     * Calculate statistics.
     */
    public void calculateStatistics() {
        mInputLines = FileHelper.readAllLines(INPUT_TEXT_FILE);
        mLineCount = mInputLines.size();
        mCharacterCount = countCharacters(mInputLines);
        mNonSpaceCharacterCount = countNonSpaceCharacters(mInputLines);
        mWordCount = countWords(mInputLines);
        mInputAmounts = FileHelper.readAllAmounts(INPUT_AMOUNTS_FILE);
        mTotalAmount = calculateTotal(mInputAmounts);
        mOutputLines = new ArrayList<>();
        mOutputLines.add("Statistics");
        mOutputLines.add("-------------------------------------");
        mOutputLines.add(String.format("Character Count           = %s", mCharacterCount));
        mOutputLines.add(String.format("Non-Space Character Count = %s", mNonSpaceCharacterCount));
        mOutputLines.add(String.format("Line Count                = %s", mLineCount));
        mOutputLines.add(String.format("Word Count                = %s", mWordCount));
        mOutputLines.add(String.format("Total Amount              = %.2f", mTotalAmount));
        FileHelper.writeAllLines(OUTPUT_FILE, mOutputLines);
        view(OUTPUT_FILE);
    }

    private void view(String fileSpec) {
        try {
            ProcessBuilder pb = new ProcessBuilder("Notepad.exe", fileSpec);
            pb.start();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Count all characters in the specified list of lines.
     */
    private int countCharacters(List<String> lines) {
        int count = 0;
        for (String line : lines) {
            char[] letters = line.toCharArray();
            for (char letter : letters) {
                count++;
            }
        }
        return count;
    }

    /**
     * Count all non-space characters in the specified list of lines.
     */
    private int countNonSpaceCharacters(List<String> lines) {
        int count = 0;
        for (String line : lines) {
            char[] letters = line.toCharArray();
            for (char letter : letters) {
                if (letter != SPACE) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Count all words in the specified list of lines.
     */
    private int countWords(List<String> lines) {
        int count = 0;
        for (String line : lines) {
            count += WordHelper.wordCount(line, "SPACES");
        }
        return count;
    }

    /**
     * Calculate the total of all amounts in the specified list.
     */
    private double calculateTotal(List<String> amounts) {
        double total = 0;
        for (String item : amounts) {
            double amount = Double.parseDouble(item);
            total += amount;
        }
        return total;
    }
}
