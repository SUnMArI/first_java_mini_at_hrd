package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validate {
    public static boolean validate_option_char(String input) {
        try {
            String pattern = "^[FPNLGWRUDSfpnlgwruds]$";
            Pattern regex = Pattern.compile(pattern);
            Matcher matcher = regex.matcher(input);
            if (!matcher.matches()) {
                throw new IllegalArgumentException("Invalid input. It allow only one character.");
            }
            return true;
        } catch (Exception e) {
            throw new IllegalArgumentException("Error validating input: " + e.getMessage());
        }
    }
    public static void validate_option(String input) {
        try {
            String pattern = "^[1-6]+$";
            Pattern regex = Pattern.compile(pattern);
            Matcher matcher = regex.matcher(input);
            if (!matcher.matches()) {
                throw new IllegalArgumentException("Invalid input. It allow only 1-6.");
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Error validating input: " + e.getMessage());
        }
    }
    public static void validate_pro_name(String input) {
        try {
            String pattern = "^[a-zA-Z]+$";
            Pattern regex = Pattern.compile(pattern);
            Matcher matcher = regex.matcher(input);
            if (!matcher.matches()) {
                throw new IllegalArgumentException("Invalid input. It allow only String.");
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Error validating input: " + e.getMessage());
        }
    }
    public static void validate_pro_unitePrice(String input) {
        try {
            String pattern = "^-?(?:0|[1-9][0-9]*)\\.?[0-9]+$";
//            ^[1-9][0-9]*$
            Pattern regex = Pattern.compile(pattern);
            Matcher matcher = regex.matcher(input);
            if (!matcher.matches()) {
                throw new IllegalArgumentException("Invalid input. It only number.");
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Error validating input: " + e.getMessage());
        }
    }
    public static void validate_pro_qty(String input) {
        try {
            String pattern = "^[0-9]+$";
            Pattern regex = Pattern.compile(pattern);
            Matcher matcher = regex.matcher(input);
            if (!matcher.matches()) {
                throw new IllegalArgumentException("Invalid input. It only INT number.");
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Error validating input: " + e.getMessage());
        }
    }
}
