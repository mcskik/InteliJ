package com.syntax.wordprocessor.features.statistics;

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
 * Used to calculate all the statistics and counts.
 * <p>
 * Author: Ken McSkimming
 */
public class WordProcessor3 {

    // Constants.
    private static final String DATA_PATH = "C:\\_Intelij\\WordProcessor\\src\\com\\syntax\\wordprocessor\\Data\\";
    private static final String INPUT_PATH = DATA_PATH + "Input\\";
    private static final String OUTPUT_PATH = DATA_PATH + "Output\\";
    private static final String INPUT_TEXT_FILE = INPUT_PATH + "InputText.txt";
    private static final String FORMATTED_FILE = OUTPUT_PATH + "FormattedText.txt";
    private static final int MAX_LINE_LENGTH = 150;
    private static final char SPACE = ' ';
    private static final char HYPHEN = '-';

    // Member variables.
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

    /**
     * Reformat the input text into neat paragraphs.
     */
    public void process() {
        LocalDateTime startDateTime = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        mInputLines = FileHelper.readAllLines(INPUT_TEXT_FILE);
        mOriginalCharacterCount = countCharacters(mInputLines);
        mOriginalNonSpaceCharacterCount = countNonSpaceCharacters(mInputLines);
        mOriginalLineCount = mInputLines.size();
        mOutputLines = new ArrayList<>();
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
        try {
            Thread.sleep(2000);
        } catch (Exception ex) {
        }
        LocalDateTime endDateTime = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        String startText = startDateTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
        String endText = endDateTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
        String elapsedText = formatElapsedTime3(startDateTime, endDateTime);
        mOutputLines.add(Constants.EMPTY_STRING);
        mOutputLines.add(String.format("Started At   : %s", startText));
        mOutputLines.add(String.format("Ended   At   : %s", endText));
        mOutputLines.add(String.format("Elapsed Time : %s", elapsedText));
        FileHelper.writeAllLines(FORMATTED_FILE, mOutputLines);
        view(FORMATTED_FILE);
    }

    private String formatElapsedTime1(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        long MS_PER_SEC = 1000;
        long SEC_PER_MIN = 60;
        long MIN_PER_HOUR = 60;
        long MS_PER_MIN = MS_PER_SEC * SEC_PER_MIN;
        long MS_PER_HOUR = MS_PER_MIN * MIN_PER_HOUR;
        Duration duration = Duration.between(startDateTime, endDateTime);
        long milliseconds = duration.toMillis();
        long hours = TimeUnit.MILLISECONDS.toHours(milliseconds);
        long hoursBackToMs = hours * MS_PER_HOUR;
        milliseconds -= hoursBackToMs;
        long minutes = TimeUnit.MILLISECONDS.toMinutes(milliseconds);
        long minutesBackToMs = minutes * MS_PER_MIN;
        milliseconds -= minutesBackToMs;
        long seconds = TimeUnit.MILLISECONDS.toSeconds(milliseconds);
        long secondsBackToMs = seconds * MS_PER_SEC;
        milliseconds -= secondsBackToMs;
        return "           " + String.format("%02d:%02d:%02d.%03d", hours, minutes, seconds, milliseconds);
    }

    private String formatElapsedTime2(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        Duration duration = Duration.between(startDateTime, endDateTime);
        long milliseconds = duration.toMillis();
        long hours = TimeUnit.MILLISECONDS.toHours(milliseconds);
        long hoursBackToMs = TimeUnit.HOURS.toMillis(hours);
        milliseconds -= hoursBackToMs;
        long minutes = TimeUnit.MILLISECONDS.toMinutes(milliseconds);
        long minutesBackToMs = TimeUnit.MINUTES.toMillis(minutes);
        milliseconds -= minutesBackToMs;
        long seconds = TimeUnit.MILLISECONDS.toSeconds(milliseconds);
        return "           " + String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    private String formatElapsedTime3(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        Duration duration = Duration.between(startDateTime, endDateTime);
        long milliseconds = duration.toMillis();
        long hours = TimeUnit.MILLISECONDS.toHours(milliseconds);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(milliseconds) -
                TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(milliseconds));
        long seconds = TimeUnit.MILLISECONDS.toSeconds(milliseconds) -
                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(milliseconds));
        return "           " + String.format("%02d:%02d:%02d", hours, minutes, seconds);
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
     * Read all words in the specified list of lines.
     */
    private int readWords(List<String> lines) {
        List<String> paragraphWords = new ArrayList<>();
        List<String> lineWords = new ArrayList<>();
        int count = 0;
        int lineCount = 0;
        for (String line : lines) {
            lineWords = WordHelper.wordList(line, "SPACES");
            count += lineWords.size();
            if (lineWords.size() > 0) {
                lineCount++;
                paragraphWords.addAll(lineWords);
            } else {
                if (lineCount == 1) {
                    bufferHeadingLine(paragraphWords);
                } else {
                    bufferParagraph(paragraphWords);
                }
                paragraphWords = new ArrayList<>();
                lineCount = 0;
            }
        }
        bufferParagraph(paragraphWords);
        return count;
    }

    /**
     * Buffer heading line.
     */
    private void bufferHeadingLine(List<String> paragraphWords) {
        String delimiter = Constants.EMPTY_STRING;
        StringBuilder line = new StringBuilder();
        if (paragraphWords.size() > 0) {
            for (String paragraphWord : paragraphWords) {
                String word = properCase(paragraphWord);
                if (line.length() + word.length() <= MAX_LINE_LENGTH) {
                    line.append(delimiter).append(word);
                } else {
                    mOutputLines.add(line.toString());
                    String underline = WordHelper.makeString(HYPHEN, line.toString().length());
                    mOutputLines.add(underline);
                    line = new StringBuilder(word);
                }
                delimiter = Constants.SPACE;
            }
            if (line.length() > 0) {
                mOutputLines.add(line.toString());
                String underline = WordHelper.makeString(HYPHEN, line.toString().length());
                mOutputLines.add(underline);
            }
            mOutputLines.add(Constants.EMPTY_STRING);
        }
    }

    /**
     * Change the specified word to Proper Case.
     */
    public String properCase(String word) {
        String proper = Constants.EMPTY_STRING;
        word = word.trim();
        if (word.length() > 0) {
            proper = word.substring(0, 1).toUpperCase();
            if (word.length() > 1) {
                proper += word.substring(1).toLowerCase();
            }
        }
        return proper;
    }

    /**
     * Change first letter of word to upper case.
     */
    public String upperFirst(String word) {
        String upper = Constants.EMPTY_STRING;
        word = word.trim();
        if (word.length() > 0) {
            upper = word.substring(0, 1).toUpperCase();
            if (word.length() > 1) {
                upper += word.substring(1);
            }
        }
        return upper;
    }

    /**
     * Change first letter of word to lower case.
     */
    public String lowerFirst(String word) {
        String lower = Constants.EMPTY_STRING;
        word = word.trim();
        if (word.length() > 0) {
            lower = word.substring(0, 1).toLowerCase();
            if (word.length() > 1) {
                lower += word.substring(1);
            }
        }
        return lower;
    }

    /**
     * Buffer paragraph.
     */
    private void bufferParagraph(List<String> paragraphWords) {
        String delimiter = Constants.EMPTY_STRING;
        StringBuilder line = new StringBuilder();
        boolean firstLetterCapital = true;
        if (paragraphWords.size() > 0) {
            for (String paragraphWord : paragraphWords) {
                String word = paragraphWord;
                if (firstLetterCapital) {
                    word = properCase(word);
                    firstLetterCapital = false;
                }
                if (line.length() + word.length() <= MAX_LINE_LENGTH) {
                    line.append(delimiter).append(word);
                } else {
                    mOutputLines.add(line.toString());
                    line = new StringBuilder(word);
                }
                if (word.endsWith(Constants.PERIOD)) {
                    firstLetterCapital = true;
                }
                delimiter = Constants.SPACE;
            }
            if (line.length() > 0) {
                mOutputLines.add(line.toString());
            }
            mOutputLines.add(Constants.EMPTY_STRING);
        }
    }
}
