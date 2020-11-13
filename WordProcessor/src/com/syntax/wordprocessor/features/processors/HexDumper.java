package com.syntax.wordprocessor.features.processors;

import com.syntax.wordprocessor.common.constants.Constants;
import com.syntax.wordprocessor.common.utilities.FileHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Used to convert the inpur file to a Hexadecimal dump.
 * <p>
 * Author: Ken McSkimming
 */
public class HexDumper {

    // Constants.
    private static final String DATA_PATH = "C:\\_Intelij\\WordProcessor\\src\\com\\syntax\\wordprocessor\\Data\\";
    private static final String INPUT_PATH = DATA_PATH + "Input\\";
    private static final String OUTPUT_PATH = DATA_PATH + "Output\\";
    private static final String INPUT_TEXT_FILE = INPUT_PATH + "InputText.txt";
    private static final String OUTPUT_HEX_FILE = OUTPUT_PATH + "HexDump.txt";

    // Member variables.
    private int mLineCount = 0;
    private List<String> mInputLines;
    private List<String> mOutputLines;

    /**
     * Write a hexadecimal dump of all characters in the input file.
     */
    public void dump() {
        mInputLines = FileHelper.readAllLines(INPUT_TEXT_FILE);
        mLineCount = mInputLines.size();
        mOutputLines = new ArrayList<>();
        char[] letters = mInputLines.get(0).toCharArray();
        char[] hexCodes = convertToHex(letters[0]);
        mOutputLines = new ArrayList<>();
        mOutputLines.add("Hexadecimal Dump (HexDump)");
        mOutputLines.add("--------------------------");
        mOutputLines.add(String.format("Letter = %s, Hex = %s%s", letters[0], hexCodes[0], hexCodes[1]));
        dumpLines(mInputLines);
        mOutputLines.add(Constants.EMPTY_STRING);
        mOutputLines.add(String.format("Input Line Count = %s", mLineCount));
        FileHelper.writeAllLines(OUTPUT_HEX_FILE, mOutputLines);
        view(OUTPUT_HEX_FILE);
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
     * Convert all lines to HEX and dump to output.
     */
    private void dumpLines(List<String> lines) {
        for (String line : lines) {
            dumpLine(line);
        }
    }

    /**
     * Convert one line to HEX and dump to output.
     */
    private void dumpLine(String line) {
        if (line.trim().length() > 0) {
            char[] letters = line.toCharArray();
            StringBuilder row1 = new StringBuilder();
            StringBuilder row2 = new StringBuilder();
            for (char letter : letters) {
                char[] hexCodes = convertToHex(letter);
                row1.append(hexCodes[0]);
                row2.append(hexCodes[1]);
            }
            mOutputLines.add(Constants.EMPTY_STRING);
            mOutputLines.add(row1.toString());
            mOutputLines.add(row2.toString());
            mOutputLines.add(line);
        }
    }

    private char[] convertToHex(char letter) {
        int asciiCode = (int) letter;
        String hexString = Integer.toHexString(asciiCode).toUpperCase();
        return hexString.toCharArray();
    }
}
