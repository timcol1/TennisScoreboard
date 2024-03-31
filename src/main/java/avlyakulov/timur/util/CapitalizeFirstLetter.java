package avlyakulov.timur.util;

public class CapitalizeFirstLetter {
    public static String capitalizeFirstLetter(String input) {
        return input.substring(0, 1).toUpperCase().concat(input.substring(1).toLowerCase());
    }
}
