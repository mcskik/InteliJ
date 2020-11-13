package com.syntax.wordprocessor.features.search;

import com.syntax.wordprocessor.common.constants.Constants;
import com.syntax.wordprocessor.common.utilities.FileHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Used to perform searches and global changes.
 * <p>
 * Author: Ken McSkimming
 */
public class SearchEngine {

    //region Constants.
    private static final String DATA_PATH = "C:\\_Intelij\\WordProcessor2\\src\\com\\syntax\\wordprocessor\\Data\\";
    private static final String INPUT_PATH = DATA_PATH + "Input\\";
    private static final String OUTPUT_PATH = DATA_PATH + "Output\\";
    private static final String INPUT_TEXT_FILE = INPUT_PATH + "InputText.txt";
    private static final String OUTPUT_FILE_1 = OUTPUT_PATH + "OutputText1.txt";
    private static final String OUTPUT_FILE_2 = OUTPUT_PATH + "OutputText2.txt";
    private static final char SPACE = ' ';
    //endregion

    //region Member variables.
    private int mLineCount = 0;
    private int mFoundCount = 0;
    private List<String> mInputLines;
    private List<String> mOutputLines;
    //endregion

    /**
     * Search for all forms of the specified find text.
     */
    public void search(String findText) {
        mInputLines = FileHelper.readAllLines(INPUT_TEXT_FILE);
        mLineCount = mInputLines.size();
        //TODO: There are three forms that the findText could be found in:
        //TODO: unix, Unix, UNIX
        //TODO: We will have to search for all three forms of findText.
        //TODO: Covert each item in the list to the correct form.
        List<String> findList = new ArrayList<>();
        findList.add("Convert findText to lower case");
        findList.add("Convert findText to proper case");
        findList.add("Convert findTExt to upper case");
        StringBuilder heading = new StringBuilder("Search for: ");
        String delimiter = Constants.EMPTY_STRING;
        for (String find : findList) {
            //TODO: Complete the heading line with all three forms of findText separated by spaces.
        }
        mOutputLines = new ArrayList<>();
        mOutputLines.add(heading.toString());
        mOutputLines.add("-------------------------------------");
        int lineNumber = 0;
        for (String line : mInputLines) {
            lineNumber++;
            boolean found = false;
            //TODO: Search the line to see if any of the three forms of the findText can be found.
            //TODO: For the search, as soon as you find one of the forms of findText, you don't
            //TODO: have to search for the other forms of findText for that line.
            if (found) {
                mFoundCount++;
                mOutputLines.add(String.format("(%s) - %s", lineNumber, line));
            }
        }
        mOutputLines.add(Constants.EMPTY_STRING);
        mOutputLines.add(String.format("Line  Count                = %s", mLineCount));
        mOutputLines.add(String.format("Found Count                = %s", mFoundCount));
        FileHelper.writeAllLines(OUTPUT_FILE_1, mOutputLines);
        view(OUTPUT_FILE_1);
    }

    /**
     * Search for all forms of the specified find text
     * and replace with the matching form of the replacement text.
     */
    public void searchAndReplace(String findText, String replacementText) {
        mInputLines = FileHelper.readAllLines(INPUT_TEXT_FILE);
        mLineCount = mInputLines.size();
        //TODO: There are three forms that the findText and the replacementText could be found in:
        //TODO: findText: unix, Unix, UNIX
        //TODO: replacementText: linux, Linux, LINUX
        //TODO: We will have to search for all three forms of findText,
        //TODO: and replace them with the matching form of the replacementText.
        //TODO: Covert each key and value in each entry of the TreeMap to the correct form.
        TreeMap<String, String> findMap = new TreeMap<>();
        findMap.put("Convert findText to lower case", "Convert replacementText to lower case");
        findMap.put("Convert findText to proper case", "Convert replacementText to proper case");
        findMap.put("Convert findText to upper case", "Convert replacementText to upper case");
        StringBuilder headingFind = new StringBuilder("Search for   : ");
        StringBuilder headingReplacement = new StringBuilder("Replace with : ");
        String delimiter = Constants.EMPTY_STRING;
        for (Map.Entry<String, String> entry : findMap.entrySet()) {
            //TODO: Complete the headingFind line with all three forms of findText separated by spaces.
            //TODO: Complete the headingReplacement line with all three forms of replacementText separated by spaces.
        }
        mOutputLines = new ArrayList<>();
        mOutputLines.add(headingFind.toString());
        mOutputLines.add(headingReplacement.toString());
        mOutputLines.add("-------------------------------------");
        int lineNumber = 0;
        for (String line : mInputLines) {
            lineNumber++;
            boolean found = false;
            String modifiedLine = line;
            //TODO: Search the line for each form of the findText and if found replace with the matching form of the replacementText.
            for (Map.Entry<String, String> entry : findMap.entrySet()) {
            }
            if (found) {
                mFoundCount++;
                mOutputLines.add(Constants.EMPTY_STRING);
                mOutputLines.add(String.format("(%s) - %s", lineNumber, line));
                mOutputLines.add(String.format("(%s) - %s", lineNumber, modifiedLine));
            }
        }
        mOutputLines.add(Constants.EMPTY_STRING);
        mOutputLines.add(String.format("Line  Count                = %s", mLineCount));
        mOutputLines.add(String.format("Found Count                = %s", mFoundCount));
        FileHelper.writeAllLines(OUTPUT_FILE_2, mOutputLines);
        view(OUTPUT_FILE_2);
    }

    /**
     * View the specified file in Notepad.
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
}
