package com.syntax.wordprocessor.features.maps;

import com.syntax.wordprocessor.common.constants.Constants;
import com.syntax.wordprocessor.common.utilities.FileHelper;

import java.io.IOException;
import java.util.*;

/**
 * Used to work with HashMaps and TreeMaps.
 * <p>
 * Author: Ken McSkimming
 */
public class Mapper {

    //region Constants.
    private static final String DATA_PATH = "C:\\_Intelij\\WordProcessor\\src\\com\\syntax\\wordprocessor\\Data\\";
    private static final String INPUT_PATH = DATA_PATH + "Input\\";
    private static final String OUTPUT_PATH = DATA_PATH + "Output\\";
    private static final String INPUT_AMOUNTS_FILE = INPUT_PATH + "InputAmounts.txt";
    private static final String OUTPUT_FILE = OUTPUT_PATH + "Maps.txt";
    //endregion

    //region Member variables.
    private List<String> mInputLines;
    private HashMap<String, Double> mHashMap = new HashMap<>();
    private TreeMap<String, Double> mTreeMap = new TreeMap<>();
    private List<Account> mAccounts = new ArrayList<>();
    private List<String> mOutputLines;
    //endregion

    /**
     * Calculate statistics.
     */
    public void loadMaps() {
        mInputLines = FileHelper.readAllLines(INPUT_AMOUNTS_FILE);
        mHashMap = loadHashMap(mInputLines);
        mTreeMap = loadTreeMap(mInputLines);
        mAccounts = loadAccounts(mInputLines);
        mOutputLines = new ArrayList<>();
        mOutputLines.add("HashMap");
        mOutputLines.add("-------------------------------------");
        for (Map.Entry<String, Double> entry : mHashMap.entrySet()) {
            String key = entry.getKey();
            Double value = entry.getValue();
            mOutputLines.add(String.format("Name = %s, Amount = %s", key, value));
        }
        mOutputLines.add(Constants.EMPTY_STRING);
        mOutputLines.add("TreeMap");
        mOutputLines.add("-------------------------------------");
        for (Map.Entry<String, Double> entry : mTreeMap.entrySet()) {
            String key = entry.getKey();
            Double value = entry.getValue();
            mOutputLines.add(String.format("Name = %s, Amount = %s", key, value));
        }
        mOutputLines.add(Constants.EMPTY_STRING);
        mOutputLines.add("Accounts");
        mOutputLines.add("-------------------------------------");
        for (Account account : mAccounts) {
            String name = account.Name;
            Double amount = account.Amount;
            mOutputLines.add(String.format("Name = %s, Amount = %s", name, amount));
        }
        FileHelper.writeAllLines(OUTPUT_FILE, mOutputLines);
        view(OUTPUT_FILE);
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
     * Load all account details into a HashMap.
     */
    private HashMap<String, Double> loadHashMap(List<String> lines) {
        HashMap<String, Double> hashMap = new HashMap<>();
        for (String line : lines) {
            String[] values = line.split(Constants.COMMA);
            if (values.length > 1) {
                String accountName = values[0];
                String amountText = values[1];
                double amount = Double.parseDouble(amountText);
                hashMap.put(accountName, amount);
            }
        }
        return hashMap;
    }

    /**
     * Load all account details into a TreeMap.
     */
    private TreeMap<String, Double> loadTreeMap(List<String> lines) {
        TreeMap<String, Double> treeMap = new TreeMap<>();
        for (String line : lines) {
            String[] values = line.split(Constants.COMMA);
            if (values.length > 1) {
                String accountName = values[0];
                String amountText = values[1];
                double amount = Double.parseDouble(amountText);
                treeMap.put(accountName, amount);
            }
        }
        return treeMap;
    }

    /**
     * Load all accounts into a List<Account>.
     */
    private List<Account> loadAccounts(List<String> lines) {
        List<Account> accounts = new ArrayList<>();
        for (String line : lines) {
            String[] values = line.split(Constants.COMMA);
            if (values.length > 1) {
                String accountName = values[0];
                String amountText = values[1];
                double amount = Double.parseDouble(amountText);
                accounts.add(new Account(accountName, amount));
            }
        }
        return accounts;
    }
}
