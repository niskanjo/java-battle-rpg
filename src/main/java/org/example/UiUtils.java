package org.example;

import java.util.Scanner;

public class UiUtils {
    public static String validateInput(String regexPattern, String prompt, String errorMsg) {
        Scanner scanner = new Scanner(System.in);
        String validInput = "";
        // Loop until valid input is provided
        while (true) {
            System.out.println(prompt);
            String input = scanner.nextLine();

            // Check if input matches the regex pattern
            if (input.matches(regexPattern)) {
                validInput = input;
                break;// Break out of the loop if input is valid
            } else {
                System.out.println(errorMsg);
            }
        }

        return validInput;
    }
}
