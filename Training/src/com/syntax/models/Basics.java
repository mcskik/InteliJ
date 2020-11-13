package com.syntax.models;

import com.syntax.common.constants.Constants;
import com.syntax.objects.Car;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Basics class.
 * <p>
 * This sample class demonstrates the class structure and contains methods which demonstrate basic Java statements and constructs.
 * <p>
 * Author: Ken McSkimming
 */
public class Basics {

    //region Constants.
    private static final String UNSPECIFIED_AIRLINE = "YY";
    private static final int MAXIMUM_DEALS = 200;
    //endregion

    //region Enumerations.
    public enum ColorEnum {
        Red,
        Blue,
        Yellow,
        Green,
        Brown,
        Black,
        White
    }
    //endregion

    //region Member Variables.
    private char letter = 'A';
    private String text = "Kenneth";
    private int quantity = 0;
    private long largeQuantity = 0;
    private float size = 4322342131.5f;
    private double measurement = 123412341341234.65756764756;
    private BigDecimal amount = new BigDecimal("1.88");
    private boolean flag = false;
    private Date date = new Date();
    private LocalDateTime dateTime = LocalDateTime.now();
    private Car car = null;
    //endregion

    //region Properties.
    public char getLetter() {
        return letter;
    }

    public void setLetter(char value) {
        letter = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String value) {
        text = value;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int value) {
        quantity = value;
    }

    public long LargeQuantity = Constants.ZERO;

    public float Size = 0;

    public double Measurement = 0;

    public BigDecimal Amount = new BigDecimal(0);

    public boolean Flag = false;

    public LocalDateTime DateTime = LocalDateTime.now();

    public Car getCar() {
        if (car == null) {
            car = new Car();
        }
        return car;
    }
    //endregion

    //region Constructors.
    public Basics() {
    }
    //endregion

    //region Public Methods.
    public void Scope() {
        String text = "";
        int quantity = 0;
        long largeQuantity = 0;
        float size = 0;
        double measurement = 0;
        BigDecimal amount = new BigDecimal(0);
        boolean flag = false;
        LocalDateTime dateTime = LocalDateTime.now();
        Car car = null;
    }

    public void Operators() {
        //Arithmetic operators.
        quantity = 0;
        quantity = quantity + 5;
        quantity = quantity - 5;
        quantity += 5;
        quantity -= 5;
        quantity++;
        quantity--;

        //Boolean operators.
        flag = true;
        flag &= ("A" == "A");

        flag = false;
        flag |= ("A" == "A");

        //Operator precedence.
        double a = 10 + 20 * 2;
        double b = (10 + 20) * 2;

        boolean flag = ((a < 45 && b < 55) || (a > 45 && b > 55));

        if ((a < 45 && b < 55) || (a > 45 && b > 55)) {
        } else {
        }
    }

    public void StringManipulation() {
        text = "Once upon a time" + "\r\n" + "there were three bears.";

        String correction = "Once upon a time" + Constants.NEWLINE + "there were three bears.";

        String request = "Ken said \"Three beers please\".";

        String response = "The barman replied \"That'll be ten pounds\".";

        request = "Ian said \"I don\'t have any change, put it on the tab\".";

        response = "Suzi said \"don't worry it's company drinks!";

        StringBuilder conversation = new StringBuilder();
        conversation.append("Ken said \"Three beers please\".");
        conversation.append(Constants.NEWLINE);
        conversation.append("The barman replied \"That'll be ten pounds\".").append(Constants.NEWLINE);
        conversation.append("Ian said \"I don\'t have any change, put it on the tab\".").append(Constants.NEWLINE);
        conversation.append("Suzi said \"don't worry it's company drinks!").append(Constants.NEWLINE);
    }
    //endregion

    //region Private Methods.
    private void Formatting() {
        LocalDate today = LocalDate.now();
        String dateText = today.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));

        LocalDateTime now = LocalDateTime.now();
        String nowText = now.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
        String timeText = now.format(DateTimeFormatter.ofPattern("HH:mm:ss"));

        DecimalFormat decimalFormat = new DecimalFormat("#,###.00");
        BigDecimal amount = new BigDecimal("123456123456.78");
        String amountText = decimalFormat.format(amount);

        BigDecimal value = new BigDecimal("456");
        String valueText = decimalFormat.format(value);

        String message = String.format("Date: %s1, Time: %s2, Amount: %s3", dateText, timeText, amountText);
    }

    private void Casts() {
        double amount = 24.56;
        int quantity = (int) amount;
        float realNumber1 = 34.56F;
        double realNumber2 = (double) realNumber1;
    }

    private void Selection() {
        //Example 1
        boolean isValid = true;

        //This is the wrong way to wrtie the if test.
        if (isValid == true) {
            System.out.println("Success");
        } else {
            System.out.println("Validation Error");
        }

        //This is the correct way to write the if test.
        if (isValid) {
            System.out.println("Success");
        } else {
            System.out.println("Validation Error");
        }

        //Example 2
        double a = 10 + 20 * 2;
        double b = (10 + 20) * 2;
        if ((a < 45 && b < 55) || (a > 45 && b > 55)) {
            System.out.println(String.format("A = %f, B = %f", a, b));
        } else {
            System.out.println(String.format("A = %f, B = %f", a, b));
            if ((a < 45 && b < 55) || (a > 45 && b > 55)) {
                System.out.println(String.format("A = %f, B = %f", a, b));
            } else {
                System.out.println(String.format("A = %f, B = %f", a, b));
            }
        }

        //Example 3
        ColorEnum color = ColorEnum.Blue;
        if (color == ColorEnum.Blue) {
            System.out.println("BLUE");
        } else if (color == ColorEnum.Green) {
            System.out.println("GREEN");
        } else if (color == ColorEnum.Red) {
            System.out.println("RED");
        } else {
            System.out.println("UNKNOWN");
        }

        //Example 4
        switch (color) {
            case Blue:
                System.out.println("BLUE");
                break;
            case Green:
                System.out.println("GREEN");
                break;
            case Red:
                System.out.println("RED");
                break;
            default:
                System.out.println("UNKNOWN");
                break;
        }

        //Example 5
        switch (color) {
            case Blue:
            case Red:
            case Yellow:
                System.out.println("PRIMARY COLOUR");
                break;
            case Black:
            case White:
                System.out.println("MONOCHROME");
                break;
            default:
                System.out.println("UNKNOWN");
                break;
        }

        //Extra bits I didn't cover in the first session.
        int x = 25;
        int y = 50;
        int z = (x > y) ? x : y;

        String sentence = "Budweiser ia an American beer, Budvar is not.";
        final String BUDWEISER = "Budweiser";
        final String BUDVAR = "Budvar";
        int budweiserPos = sentence.indexOf(BUDWEISER);
        int budvarPos = sentence.indexOf(BUDVAR);
        String furthestAlongBeer = budweiserPos > budvarPos ? BUDWEISER : BUDVAR;

        String firstName = null;
        String surname = "Smith";
        String fullName = firstName != null ? firstName : "John";
        fullName += " ";
        fullName += surname != null ? surname : "Jones";

        Integer nullableNumber = null;
        Boolean nullableFlag = null;

        nullableNumber = nullableNumber != null ? nullableNumber : 25;
        nullableFlag = nullableFlag != null ? nullableFlag : false;
    }

    private void Loops() {
        //Example 1
        final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int pos = 0; pos < ALPHABET.length(); pos++) {
            String letter = ALPHABET.substring(pos, 1);
            System.out.println(String.format("Letter is: %s", letter));
        }

        //Example 2
        char[] alphabet = ALPHABET.toCharArray();
        for (int pos = 0; pos < alphabet.length; pos++) {
            char letter = alphabet[pos];
            System.out.println(String.format("Letter is: %s", letter));
            if (pos > 10) {
                break;
            }
        }

        //Example 3
        for (char letter : alphabet) {
            System.out.println(String.format("Letter is: %s", letter));
            if (letter == 'X') {
                break;
            }
        }

        //Example 4
        int ptr = 0;
        while (ptr < alphabet.length) {
            char letter = alphabet[ptr];
            System.out.println(String.format("Letter is: %s", letter));
            ptr++;
        }

        //Example 5
        int pointer = 0;
        do {
            char letter = alphabet[pointer];
            System.out.println(String.format("Letter is: %s", letter));
            pointer++;
        } while (pointer < alphabet.length);

        //Example 6
        List<String> names = new ArrayList<String>();
        names.add("Ian");
        names.add("Ken");
        names.add("Niten");
        for (String name : names) {
            System.out.println(String.format("Name is: %s", name));
        }

        //Example 7
        String[] firstNames = names.toArray(new String[names.size()]);
        for (int index = 0; index < firstNames.length; index++) {
            String firstName = firstNames[index];
            System.out.println(String.format("First Name is: %s", firstName));
        }
    }
    //endregion
}
