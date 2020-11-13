package com.syntax.wordprocessor;

import com.syntax.wordprocessor.common.constants.Constants;
import com.syntax.wordprocessor.features.maps.Mapper;
import com.syntax.wordprocessor.features.processors.HexDumper;
import com.syntax.wordprocessor.features.processors.WordProcessor;
import com.syntax.wordprocessor.features.search.SearchEngine;
import com.syntax.wordprocessor.features.statistics.Sorter;

import java.io.Console;

public class Main {

    /**
     * Main program.
     */
    public static void main(String[] args) {
        //Counter counter = new Counter();
        //counter.calculateStatistics();
        //DirectoryLister lister = new DirectoryLister();
        //lister.printDirectoryListing();
        //lister.printFullDirectoryTreeListing();
        Sorter sorter = new Sorter();
        sorter.sort();
        Mapper mapper = new Mapper();
        mapper.loadMaps();
        //String findText = readLineFromConsole("Find Text : ");
        //String replacementText = readLineFromConsole("Replacement Text : ");
        //String findText = "unix";
        //String replacementText = "linux";
        SearchEngine searchEngine = new SearchEngine();
        searchEngine.search("unix");
        searchEngine.searchAndReplace("unix", "linux");
        WordProcessor wordProcessor = new WordProcessor();
        wordProcessor.process();
        //HexDumper hexDumper = new HexDumper();
        //hexDumper.dump();
    }

    public static String readLineFromConsole(String promptText) {
        // Grab hold of a reference to the system console.
        Console console = System.console();
        // Check that the system console was found.
        if (console == null) {
            System.out.println("No console available");
            return Constants.EMPTY_STRING;
        }
        // Read a line from the console.
        String line = console.readLine(promptText);
        return line;
    }
}
