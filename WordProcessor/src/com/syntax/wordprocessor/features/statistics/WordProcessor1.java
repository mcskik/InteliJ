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
public class WordProcessor1 {

    // Constants.
    private static final String DATA_PATH = "C:\\_Intelij\\WordProcessor\\src\\com\\syntax\\wordprocessor\\Data\\";
    private static final String INPUT_PATH = DATA_PATH + "Input\\";
    private static final String OUTPUT_PATH = DATA_PATH + "Output\\";
    private static final String INPUT_TEXT_FILE = INPUT_PATH + "InputText.txt";
    private static final String FORMATTED_FILE = OUTPUT_PATH + "FormattedText.txt";

    // Member variables.
    private int mLineCount = 0;
    private int mWordCount = 0;
    private List<String> mInputLines;
    private List<String> mWordList;
    private List<String> mOutputLines;

    /**
     * Reformat the input text into neat paragraphs.
     */
    public void process() {
        mInputLines = FileHelper.readAllLines(INPUT_TEXT_FILE);
        mLineCount = mInputLines.size();
        mOutputLines = new ArrayList<>();
        mWordCount = readWords(mInputLines);
        mOutputLines.add("Formatted Text");
        mOutputLines.add("-------------------------------------");
        mOutputLines.add(String.format("Line Count                = %s", mLineCount));
        mOutputLines.add(String.format("Word Count                = %s", mWordCount));
        FileHelper.writeAllLines(FORMATTED_FILE, mOutputLines);
        view(FORMATTED_FILE);
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
     * Count all words in the specified list of lines.
     */
    private int countWords(List<String> lines) {
        int count = 0;
        for (String line : lines) {
            count += WordHelper.wordCount(line, "ANY");
        }
        return count;
    }

    /**
     * Count all words in the specified list of lines.
     */
    private int readWords(List<String> lines) {
        List<String> paragraphWords = new ArrayList<>();
        List<String> lineWords = new ArrayList<>();
        int count = 0;
        for (String line : lines) {
            lineWords = WordHelper.wordList(line, "SPACES");
            count += lineWords.size();
            if (lineWords.size() > 0) {
                paragraphWords.addAll(lineWords);
            } else {
                if (paragraphWords.size() > 0) {
                    mOutputLines.add("======= Paragraph =========");
                    for (String word : paragraphWords) {
                        mOutputLines.add(word);
                    }
                }
                paragraphWords = new ArrayList<>();
            }
        }
        if (paragraphWords.size() > 0) {
            mOutputLines.add("======= Last Paragraph =========");
            for (String word : paragraphWords) {
                mOutputLines.add(word);
            }
        }
        return count;
    }
}
