package com.syntax.wordprocessor.features.maps;

import com.syntax.wordprocessor.common.constants.Constants;

/**
 * Account class.
 */
public class Account {
    public String Name = Constants.EMPTY_STRING;
    public double Amount = 0;

    public Account(String name, double amount) {
        Name = name;
        Amount = amount;
    }
}
