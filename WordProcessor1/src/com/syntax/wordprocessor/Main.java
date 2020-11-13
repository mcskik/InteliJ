package com.syntax.wordprocessor;

import com.syntax.wordprocessor.features.statistics.Counter;
import com.syntax.wordprocessor.features.statistics.DirectoryLister;
import com.syntax.wordprocessor.features.statistics.Sorter;
import com.syntax.wordprocessor.features.statistics.WordProcessor3;

public class Main {

    /**
     * Main program.
     */
    public static void main(String[] args) {
        Counter counter = new Counter();
        counter.calculateStatistics();
        DirectoryLister lister = new DirectoryLister();
        lister.printDirectoryListing();
        Sorter sorter = new Sorter();
        sorter.sort();
        //WordProcessor1 wordProcessor = new WordProcessor1();
        //wordProcessor.process();
        //WordProcessor2 wordProcessor = new WordProcessor2();
        //wordProcessor.process();
        WordProcessor3 wordProcessor = new WordProcessor3();
        wordProcessor.process();
    }
}
