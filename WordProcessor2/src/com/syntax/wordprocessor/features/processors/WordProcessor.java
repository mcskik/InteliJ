package com.syntax.wordprocessor.features.processors;

import com.syntax.wordprocessor.common.constants.Constants;
import com.syntax.wordprocessor.common.utilities.FileHelper;
import com.syntax.wordprocessor.common.utilities.WordHelper;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Used to reformat a badly formatted text file into neat paragraphs.
 * <p>
 * Author: Ken McSkimming
 */
public class WordProcessor {

    //region Constants.
    private static final String DATA_PATH = "C:\\_Intelij\\WordProcessor2\\src\\com\\syntax\\wordprocessor\\Data\\";
    private static final String INPUT_PATH = DATA_PATH + "Input\\";
    private static final String OUTPUT_PATH = DATA_PATH + "Output\\";
    private static final String INPUT_TEXT_FILE = INPUT_PATH + "InputText.txt";
    private static final String FORMATTED_FILE = OUTPUT_PATH + "FormattedText.txt";
    private static final int MAX_LINE_LENGTH = 150;
    private static final char SPACE = ' ';
    private static final char HYPHEN = '-';
    //endregion

    //region Member variables.
    private int mOriginalCharacterCount = 0;
    private int mOriginalNonSpaceCharacterCount = 0;
    private int mOriginalLineCount = 0;
    private int mOriginalWordCount = 0;
    private int mFormattedCharacterCount = 0;
    private int mFormattedNonSpaceCharacterCount = 0;
    private int mFormattedLineCount = 0;
    private int mFormattedWordCount = 0;
    private List<String> mInputLines;
    private List<String> mWordList;
    private List<String> mOutputLines;
    //endregion

    /**
     * Reformat the input text into neat paragraphs.
     */
    public void process() {
        //TODO: Create a LocalDataTime field to hold the Date and Time when this method starts running.

        mInputLines = FileHelper.readAllLines(INPUT_TEXT_FILE);
        mOriginalCharacterCount = countCharacters(mInputLines);
        mOriginalNonSpaceCharacterCount = countNonSpaceCharacters(mInputLines);
        mOriginalLineCount = mInputLines.size();
        mOutputLines = new ArrayList<>();
        // The call to readWords is where most of the processing occurs.
        mOriginalWordCount = readWords(mInputLines);
        mFormattedCharacterCount = countCharacters(mOutputLines);
        mFormattedNonSpaceCharacterCount = countNonSpaceCharacters(mOutputLines);
        mFormattedLineCount = mOutputLines.size();
        mFormattedWordCount = countWords(mOutputLines);
        mOutputLines.add(String.format("Original  Character Count           = %s", mOriginalCharacterCount));
        mOutputLines.add(String.format("Original  Non-Space Character Count = %s", mOriginalNonSpaceCharacterCount));
        mOutputLines.add(String.format("Original  Line Count                = %s", mOriginalLineCount));
        mOutputLines.add(String.format("Original  Word Count                = %s", mOriginalWordCount));
        mOutputLines.add(Constants.EMPTY_STRING);
        mOutputLines.add(String.format("Formatted Character Count           = %s", mFormattedCharacterCount));
        mOutputLines.add(String.format("Formatted Non-Space Character Count = %s", mFormattedNonSpaceCharacterCount));
        mOutputLines.add(String.format("Formatted Line Count                = %s", mFormattedLineCount));
        mOutputLines.add(String.format("Formatted Word Count                = %s", mFormattedWordCount));
        //TODO: When working on the start and end DateTimes make this method temporarily delay for 2 seconds.
        try {
            //TODO: Insert the call to sleep for 2 seconds here.
        } catch (Exception ex) {
        }
        //TODO: Create a LocalDataTime field to hold the Date and Time when this method finishes running.

        //TODO: Format the startDateTime into a text String in this format: yyyy/MM/dd HH:mm:ss
        String startText = "yyyy/MM/dd HH:mm:ss <-- Replace this with formatted startDateTime";
        //TODO: Format the endtDateTime into a text String in this format: yyyy/MM/dd HH:mm:ss
        String endText = "yyyy/MM/dd HH:mm:ss <-- Replace this with formatted endDateTime";
        String elapsedText = "HH:mm:ss <-- Uncomment the line below when the start and end DateTimes have been added.";
        //String elapsedText = formatElapsedTime(startDateTime, endDateTime);
        mOutputLines.add(Constants.EMPTY_STRING);
        mOutputLines.add(String.format("Started At   : %s", startText));
        mOutputLines.add(String.format("Ended   At   : %s", endText));
        mOutputLines.add(String.format("Elapsed Time : %s", elapsedText));
        FileHelper.writeAllLines(FORMATTED_FILE, mOutputLines);
        view(FORMATTED_FILE);
    }

    /**
     * Calculates the run duration / elapsed time and then displays it in HH:mm:ss format.
     */
    private String formatElapsedTime(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        Duration duration = Duration.between(startDateTime, endDateTime);
        long milliseconds = duration.toMillis();
        long hours = TimeUnit.MILLISECONDS.toHours(milliseconds);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(milliseconds) -
                TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(milliseconds));
        long seconds = TimeUnit.MILLISECONDS.toSeconds(milliseconds) -
                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(milliseconds));
        return "           " + String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    /**
     * View the specified file in notepad.
     */
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
     * Read all the words in the specified list of lines.
     */
    private int readWords(List<String> lines) {
        List<String> paragraphWords = new ArrayList<>();
        List<String> lineWords = new ArrayList<>();
        int count = 0;
        for (String line : lines) {
            //TODO; Read all the words in the line.
            lineWords = new ArrayList<>();
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
                //TODO: Clear all words from the paragraphWords list so that it becomes an empty list again.
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

    /**
     * Change the specified word to Proper Case.
     *
     * Proper case just means that the first letter should be upper case and the rest should be lower case.
     */
    public String properCase(String word) {
        String proper = Constants.EMPTY_STRING;
        word = word.trim();
        if (word.length() > 0) {
            //TODO: Complete this method by entering the code to convert the word to proper case.
        }
        return proper;
    }

    /**
     * Buffer paragraph.
     */
    private void bufferParagraph(List<String> paragraphWords) {
        String delimiter = Constants.EMPTY_STRING;
        StringBuilder line = new StringBuilder();
        if (paragraphWords.size() > 0) {
            for (String paragraphWord : paragraphWords) {
                String word = paragraphWord;
                //TODO: Write the paragraph words into multiple lines each with a maximum length of 150.
                //TODO: There should be one space between each word.
            }
            if (line.length() > 0) {
                mOutputLines.add(line.toString());
            }
            //TODO: There should be one blank line at the end of each paragraph.
        }
    }
}
