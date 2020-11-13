package com.syntax.models;

/**
 * Methods class.
 * <p>
 * This sample class demonstrates functions, methods, and parameter passing.
 */
public class Methods {

    //region Constructors.
    public Methods() {
    }
    //endregion

    //region Public Methods.

    /**
     * Displays "Hello" on the console.
     */
    public void Hello() {
        System.out.println("Hello");
    }

    /**
     * Capitalizes the first letter of a word and lower cases the rest.
     *
     * @param input - Original input word
     * @return Original word changed to proper case
     */
    public String ProperCase(String input) {
        String output = "";
        input = input.trim();
        if (input.length() > 0) {
            if (input.length() > 1) {
                String first = input.substring(0, 1).toUpperCase();
                String remainder = input.substring(1).toLowerCase();
                output = first + remainder;
            } else {
                output = input.toLowerCase();
            }
        }
        return output;
    }
    //endregion
}
