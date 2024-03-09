package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validate {
    public static boolean validate_pro_name(String input) {
            return Pattern.matches("^[a-zA-Z]+(?:\\w+?\\s?)*$",input);
    }
    public static boolean validate_pro_unitePrice(String input) {
        return Pattern.matches("^-?(?:0|[1-9][0-9]*)\\.?[0-9]+$",input);
    }
    public static boolean validate_pro_qty(String input) {
           return Pattern.matches("^[0-9]+$",input);
    }
    public static boolean validate_set_row(String input) {
        return Pattern.matches("^[1-9]+$",input);
    }
    public static boolean validate_only_number(String input) {
        return Pattern.matches("^[0-9]+$",input);
    }
    public static boolean validate_answer(String input) {
        return Pattern.matches("^[ynYN]+$",input);
    }
    public static boolean validate_string(String input) {
        return Pattern.matches("^[a-zA-Z]+$",input);
    }
}
